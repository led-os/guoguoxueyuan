package com.test720.grasshoppercollege.module.guShiCi;

import android.animation.ObjectAnimator;
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
import com.test720.grasshoppercollege.module.guShiCi.shou.NianJiFragment;
import com.test720.grasshoppercollege.module.guShiCi.shou.ShouChangFragment;
import com.test720.grasshoppercollege.module.guShiCi.shou.TypeFragment;
import com.test720.grasshoppercollege.module.guShiCi.shou.ZuoZheFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class GuShiShouYeActivity extends BaseToolActivity {
    @BindView(R.id.content_frame)
    FrameLayout contentFrame;
    @BindView(R.id.img1)
    ImageView img1;
    @BindView(R.id.text1)
    TextView text1;
    @BindView(R.id.lin1)
    LinearLayout lin1;
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
    @BindView(R.id.main_tabhost)
    LinearLayout mainTabhost;

    int index = 0;

    // 管理Fragment
    FragmentManager manager;
    Fragment fragmentShow = null;
    // 管理Fragment
    ArrayList<Fragment> fragmentList;
    @BindView(R.id.img4)
    ImageView img4;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.text4)
    TextView text4;
    @BindView(R.id.lin4)
    LinearLayout lin4;

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_gu_shi_shou_ye;
    }

    @Override
    protected void initData() {
        MyApplication.getmInstance().setLoginDialogIsShow(false);
        fragmentList = new ArrayList<>();
        fragmentList.add(new NianJiFragment());
        fragmentList.add(new ZuoZheFragment());
        fragmentList.add(new TypeFragment());
        fragmentList.add(new ShouChangFragment());
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

    @OnClick({R.id.back, R.id.lin1, R.id.lin2, R.id.lin3, R.id.lin4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lin1:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (MyApplication.getmInstance().getUid().equals("")) {
                    return;
                }
                back.setVisibility(View.VISIBLE);
                playAnimBack();
                index = 0;
                switchContent(fragmentShow, fragmentList.get(0));
                playAnim();
                break;
            case R.id.lin2:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (MyApplication.getmInstance().getUid().equals("")) {
                    return;
                }
                back.setVisibility(View.VISIBLE);
                playAnimBack();
                index = 1;
                switchContent(fragmentShow, fragmentList.get(1));
                playAnim();
                break;
            case R.id.lin3:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (MyApplication.getmInstance().getUid().equals("")) {
                    return;
                }
                back.setVisibility(View.VISIBLE);
                playAnimBack();
                index = 2;
                switchContent(fragmentShow, fragmentList.get(2));
                playAnim();
                break;
            case R.id.lin4:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (MyApplication.getmInstance().getUid().equals("")) {
                    return;
                }
                back.setVisibility(View.GONE);
                playAnimBack();
                index = 3;
                switchContent(fragmentShow, fragmentList.get(3));
                playAnim();
                break;
            case R.id.back:
                finish();
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
