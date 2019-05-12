package com.test720.grasshoppercollege.module.jinFanYiCi;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.jinShai.JinShaiShouYeActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class JinFanYiShouActivity extends BaseToolActivity {

    @BindView(R.id.content_frame)
    FrameLayout contentFrame;
    @BindView(R.id.img1)
    ImageView img1;
    @BindView(R.id.text1)
    TextView text1;
    @BindView(R.id.lin1)
    public LinearLayout lin1;
    @BindView(R.id.img2)
    ImageView img2;
    @BindView(R.id.text2)
    TextView text2;
    @BindView(R.id.lin2)
    LinearLayout lin2;
    @BindView(R.id.img3)
    ImageView img3;
    @BindView(R.id.text3)
    TextView text3;
    @BindView(R.id.lin3)
    LinearLayout lin3;
    @BindView(R.id.img4)
    ImageView img4;
    @BindView(R.id.text4)
    TextView text4;
    @BindView(R.id.lin4)
    LinearLayout lin4;
    @BindView(R.id.main_tabhost)
    LinearLayout mainTabhost;
    // 管理Fragment
    FragmentManager manager;
    Fragment fragmentShow = null;
    // 管理Fragment
    ArrayList<Fragment> fragmentList;
    int index = 0;

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_jin_fan_yi_ci;
    }

    @Override
    protected void initData() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new JinFanYIShouFragment());
        fragmentList.add(new BaoDianFragment());
        manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.content_frame, fragmentList.get(0)).commit();
        fragmentShow = fragmentList.get(0);
        lin1.setClickable(true);
    }

    @Override
    public void GetHttpBackStr(String str, int what) {

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

    @OnClick({R.id.lin1, R.id.lin2, R.id.lin3, R.id.lin4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin1:
                playAnimBack();
                index = 0;
                switchContent(fragmentShow, fragmentList.get(0));
                playAnim();
                break;
            case R.id.lin2:
                playAnimBack();
                index = 1;
                playAnim();
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (!MyApplication.getmInstance().getUid().equals("")) {
                    Intent intent = new Intent(this, JinShaiShouYeActivity.class);
                    intent.putExtra("type", "25");
                    jumpToActivity(intent, false);
                }
                break;
            case R.id.lin3:
                playAnimBack();
                index = 2;
                playAnim();
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (!MyApplication.getmInstance().getUid().equals("")) {
                    jumpToActivity(JInFanYiJiLeiActivity.class, false);
                }
                break;
            case R.id.lin4:
                playAnimBack();
                index = 3;
                playAnim();
                switchContent(fragmentShow, fragmentList.get(1));
                break;
        }
    }

    /**
     * 判断是否被add过
     * 否则   隐藏当前的fragment，显示下一个
     */
    public void switchContent(Fragment from, Fragment to) {
        if (fragmentShow != to) {
            fragmentShow = to;
            FragmentTransaction transaction = manager.beginTransaction();
            if (!to.isAdded()) {// 先判断是否被add过
                transaction.hide(from).add(R.id.content_frame, to).commitAllowingStateLoss(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.hide(from).show(to).commitAllowingStateLoss(); // 隐藏当前的fragment，显示下一个
            }
        }
    }
}
