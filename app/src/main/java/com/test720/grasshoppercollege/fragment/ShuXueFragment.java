package com.test720.grasshoppercollege.fragment;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.test720.grasshoppercollege.BaseFragment;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.shuXue.jiChu.JiChuShouActivity;
import com.test720.grasshoppercollege.module.shuXue.aoShu.AoShuShouYeActivity;
import com.test720.grasshoppercollege.module.shuXue.aoShuLianXi.AoShuLIanXiShouActivity;
import com.test720.grasshoppercollege.module.shuXue.dongHuaShuXue.DongHuaShuXueShouYeActivity;
import com.test720.grasshoppercollege.module.shuXue.kouSuan.KouSuanShouActivity;
import com.test720.grasshoppercollege.module.shuXue.yingYong.YingYongShouActivity;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by 水东流 on 2018/4/18.
 */

public class ShuXueFragment extends BaseFragment {
    @BindView(R.id.mBackground)
    ImageView mBackground;
    @BindView(R.id.one)
    LinearLayout one;
    @BindView(R.id.two)
    LinearLayout two;
    @BindView(R.id.root)
    RelativeLayout root;
    Unbinder unbinder;
    @BindView(R.id.text_one)
    TextView textOne;
    @BindView(R.id.text_two)
    TextView textTwo;
    @BindView(R.id.text_three)
    TextView textThree;
    @BindView(R.id.three)
    LinearLayout three;
    @BindView(R.id.text_five)
    TextView textFive;
    @BindView(R.id.five)
    LinearLayout five;
    @BindView(R.id.text_six)
    TextView textSix;
    @BindView(R.id.six)
    LinearLayout six;
    @BindView(R.id.text_seven)
    TextView textSeven;
    @BindView(R.id.seven)
    LinearLayout seven;
    @BindView(R.id.scrollView)
    HorizontalScrollView scrollView;
    Unbinder unbinder1;

    @Override
    public void initData() {
        type = textOne.getText().toString();
        playAnim(one);
        index = 1;
        mBackground.setImageResource(R.mipmap.kousuanback);
    }

    @Override
    public void setListener() {

    }

    @Override
    public int setlayoutResID() {
        return R.layout.fragment_shu_xue;
    }

    @Override
    public void getSuccess(String s, int what) {

    }


    @OnClick({R.id.btn, R.id.one, R.id.two, R.id.three, R.id.five, R.id.six, R.id.seven})
    public void onViewClicked(View view) {
        playAnimBack();
        switch (view.getId()) {
            case R.id.one:
                type = textOne.getText().toString();
                playAnim(one);
                index = 1;
                mBackground.setImageResource(R.mipmap.kousuanback);
                break;
            case R.id.two:
                type = textTwo.getText().toString();
                playAnim(two);
                index = 2;
                mBackground.setImageResource(R.mipmap.yingyongback);
                break;
            case R.id.three:
                type = textThree.getText().toString();
                playAnim(three);
                index = 3;
                mBackground.setImageResource(R.mipmap.jichulianxiback);
                break;
            case R.id.five:
                type = textFive.getText().toString();
                playAnim(five);
                index = 5;
                mBackground.setImageResource(R.mipmap.donghuashuxueback);
                break;
            case R.id.six:
                type = textSix.getText().toString();
                playAnim(six);
                index = 6;
                mBackground.setImageResource(R.mipmap.aoshudonghuaback);
                break;
            case R.id.seven:
                type = textSeven.getText().toString();
                playAnim(seven);
                index = 7;
                mBackground.setImageResource(R.mipmap.aoshulianxiback);
                break;
            case R.id.btn:
                jump();
                break;
        }
    }

    //初始化偏移量
    private int offset = 0;
    private int scrollViewWidth = 0;

    /*点击移动,上移动画*/
    private void playAnim(View view) {
        ObjectAnimator transAnim = ObjectAnimator.ofFloat(view, "translationY", 0, -200, 0, -100);
        transAnim.setDuration(1000);
        transAnim.start();

        //获取位置下标
        scrollViewWidth = scrollView.getWidth();
        if ((scrollViewWidth + offset) < (view.getRight() + 100)) {//需要向右移动
            scrollView.smoothScrollBy(200, 0);
            offset += 200;
        }

        if (offset > (view.getLeft() - 100)) {//需要向左移动
            scrollView.smoothScrollBy(-200, 0);
            offset += -200;
        }
    }

    int index;//标记上次被点击坐标

    /*点击移动,复原动画*/
    private void playAnimBack() {
        View view;
        switch (index) {
            case 1:
                view = one;
                break;
            case 2:
                view = two;
                break;
            case 3:
                view = three;
                break;
            case 5:
                view = five;
                break;
            case 6:
                view = six;
                break;
            case 7:
                view = seven;
                break;
            default:
                view = one;
        }
        ObjectAnimator transAnim = ObjectAnimator.ofFloat(view, "translationY", -50, 50, 50, 0);
        transAnim.setDuration(1000);
        transAnim.start();
    }


    /*当前选中模块*/
    String type = "奥数动画";

    private void jump() {
        switch (type) {
            case "口算训练":
                Intent intent = new Intent(getActivity(), KouSuanShouActivity.class);
                intent.putExtra("type", 22);
                jumpToActivity(intent, false);
                break;
            case "应用练习":
                jumpToActivity(YingYongShouActivity.class, false);
                break;

            case "基础练习":
                jumpToActivity(JiChuShouActivity.class, false);
                break;
            case "数学课堂":
                jumpToActivity(DongHuaShuXueShouYeActivity.class, false);
                break;
            case "奥数课堂":
                jumpToActivity(AoShuShouYeActivity.class, false);
                break;

            case "奥数练习":
                jumpToActivity(AoShuLIanXiShouActivity.class, false);
                break;
        }
    }


}
