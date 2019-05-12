package com.test720.grasshoppercollege.module.shangCheng;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.shangCheng.fragment.GouWuCheFragment;
import com.test720.grasshoppercollege.module.shangCheng.fragment.MyOrderFragment;
import com.test720.grasshoppercollege.module.shangCheng.fragment.ShangChengFenLeiFragment;
import com.test720.grasshoppercollege.module.shangCheng.fragment.ShangChengTuiJianFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class ShangChengActivity extends BaseToolActivity {


    @BindView(R.id.fragme)
    FrameLayout fragme;

    @BindView(R.id.lin1)
    LinearLayout lin1;

    @BindView(R.id.lin2)
    LinearLayout lin2;

    @BindView(R.id.lin3)
    LinearLayout lin3;

    @BindView(R.id.lin4)
    LinearLayout lin4;

    // 管理Fragment
    private FragmentManager supportFragmentManager;
    // 管理Fragment
    private ShangChengTuiJianFragment shangChengTuiJianFragment;
    private GouWuCheFragment gouWuCheFragment;
    private ShangChengFenLeiFragment fenLeiFragment;
    private MyOrderFragment myOrderFragment;
    int index = 0;

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_shang_cheng;
    }

    @Override
    protected void initData() {
        supportFragmentManager = getSupportFragmentManager();
        //开启事务
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        shangChengTuiJianFragment = new ShangChengTuiJianFragment();
        fragmentTransaction.add(R.id.fragme, shangChengTuiJianFragment).commit();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        //支付成功，跳转订单界面
        if (intent.getStringExtra("pay") != null && intent.getStringExtra("pay").equals("1")) {
            FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
            hideFrag();
            playAnimBack();
            index = 3;
            if (myOrderFragment == null) {
                myOrderFragment = new MyOrderFragment();
                fragmentTransaction.add(R.id.fragme, myOrderFragment).commit();
            } else {
                fragmentTransaction.show(myOrderFragment).commit();
            }
            playAnim();
        }
    }

    @Override
    public void GetHttpBackStr(String str, int what) {

    }


    @OnClick({R.id.lin1, R.id.lin2, R.id.lin3, R.id.lin4})
    public void onViewClicked(View view) {
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        switch (view.getId()) {
            case R.id.lin1:
                hideFrag();
                playAnimBack();
                index = 0;
                fragmentTransaction.show(shangChengTuiJianFragment).commit();
                playAnim();
                break;
            case R.id.lin2:
                hideFrag();
                playAnimBack();
                index = 1;
                if (fenLeiFragment == null) {
                    fenLeiFragment = new ShangChengFenLeiFragment();
                    fragmentTransaction.add(R.id.fragme, fenLeiFragment).commit();
                } else {
                    fragmentTransaction.show(fenLeiFragment).commit();
                }
                playAnim();
                break;
            case R.id.lin3:
                hideFrag();
                playAnimBack();
                index = 2;
                if (gouWuCheFragment == null) {
                    //实例化fragment2
                    gouWuCheFragment = new GouWuCheFragment();
                    fragmentTransaction.add(R.id.fragme, gouWuCheFragment).commit();
                } else {
                    //有的话就显示
                    fragmentTransaction.show(gouWuCheFragment).commit();
                }
                playAnim();
                break;
            case R.id.lin4:
                hideFrag();
                playAnimBack();
                index = 3;
                if (myOrderFragment == null) {
                    myOrderFragment = new MyOrderFragment();
                    fragmentTransaction.add(R.id.fragme, myOrderFragment).commit();
                } else {
                    fragmentTransaction.show(myOrderFragment).commit();
                }
                playAnim();
                break;

        }
    }

    /*点击移动,上移动画*/
    private void playAnim() {
        View view;
        switch (index) {
            case 0:
                view = lin1;
                break;
            case 1:
                view = lin2;
                break;
            case 2:
                view = lin3;
                break;
            case 3:
                view = lin4;
                break;
            default:
                return;
        }
        ObjectAnimator transAnim = ObjectAnimator.ofFloat(view, "translationY", 0, -100, 0, -50);
        transAnim.setDuration(1000);
        transAnim.start();
    }

    /*点击移动,复原动画*/
    private void playAnimBack() {
        View view;
        switch (index) {
            case 0:
                view = lin1;
                break;
            case 1:
                view = lin2;
                break;
            case 2:
                view = lin3;
                break;
            case 3:
                view = lin4;
                break;
            default:
                return;
        }
        ObjectAnimator transAnim = ObjectAnimator.ofFloat(view, "translationY", -50, 50, 50, 0);
        transAnim.setDuration(1000);
        transAnim.start();
    }

    /*销毁缓存*/
    private void hideFrag() {
        //再重新获取一个开启事务
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        //不等于空或者是否添加的时候
        if (shangChengTuiJianFragment != null && shangChengTuiJianFragment.isAdded()) {
            fragmentTransaction.hide(shangChengTuiJianFragment);
        }
        if (myOrderFragment != null && myOrderFragment.isAdded()) {
            fragmentTransaction.hide(myOrderFragment);
        }
        if (gouWuCheFragment != null && gouWuCheFragment.isAdded()) {
            fragmentTransaction.hide(gouWuCheFragment);
        }
        if (fenLeiFragment != null && fenLeiFragment.isAdded()) {
            fragmentTransaction.hide(fenLeiFragment);
        }
        fragmentTransaction.commit();
    }

}
