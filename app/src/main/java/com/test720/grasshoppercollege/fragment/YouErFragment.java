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
import com.test720.grasshoppercollege.module.youEr.baoBao.YouErBaoBaoShouActivity;
import com.test720.grasshoppercollege.module.youEr.kouSuanDaWang.KouSuanDaWangShouActivity;
import com.test720.grasshoppercollege.module.youEr.baoBao.BaoBaoShouActivity;
import com.test720.grasshoppercollege.module.youEr.baoBaoTingTing.BaoBaoTingTingShouActivity;
import com.test720.grasshoppercollege.module.youEr.erGeShiJie.ErGeShiJieShouActivity;
import com.test720.grasshoppercollege.module.youEr.shiZiDongHua.ShiZiShouActivity;
import com.test720.grasshoppercollege.module.youEr.yingWenHuiBen.HuiBenShouActivity;
import com.test720.grasshoppercollege.module.youEr.youXi.YouXiShouYeActivity;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by 水东流 on 2018/7/16.
 */

public class YouErFragment extends BaseFragment {


    @BindView(R.id.mBackground)
    ImageView mBackground;
    @BindView(R.id.text_one)
    TextView textOne;
    @BindView(R.id.one)
    LinearLayout one;
    @BindView(R.id.text_two)
    TextView textTwo;
    @BindView(R.id.two)
    LinearLayout two;
    @BindView(R.id.text_three)
    TextView textThree;
    @BindView(R.id.three)
    LinearLayout three;
    @BindView(R.id.text_four)
    TextView textFour;
    @BindView(R.id.four)
    LinearLayout four;
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
    @BindView(R.id.text_eight)
    TextView textEight;
    @BindView(R.id.eight)
    LinearLayout eight;
    @BindView(R.id.text_nine)
    TextView textNine;
    @BindView(R.id.nine)
    LinearLayout nine;
    @BindView(R.id.text_ten)
    TextView textTen;
    @BindView(R.id.ten)
    LinearLayout ten;
    @BindView(R.id.text_eleven)
    TextView textEleven;
    @BindView(R.id.eleven)
    LinearLayout eleven;
    @BindView(R.id.text_tweven)
    TextView textTweven;
    @BindView(R.id.tweven)
    LinearLayout tweven;
    @BindView(R.id.text_thirth)
    TextView textThirth;
    @BindView(R.id.thirth)
    LinearLayout thirth;
    @BindView(R.id.text_fourth)
    TextView textFourth;
    @BindView(R.id.fourth)
    LinearLayout fourth;
    @BindView(R.id.scrollView)
    HorizontalScrollView scrollView;
    @BindView(R.id.root)
    RelativeLayout root;
    Unbinder unbinder;

    @Override
    public void initData() {
        /*默认第一*/
        index = 1;
        playAnim(one);
        mBackground.setImageResource(R.mipmap.yeszdhback);
        type = textOne.getText().toString();
    }

    @Override
    public void setListener() {

    }

    @Override
    public int setlayoutResID() {
        return R.layout.fragment_you_er;
    }

    @Override
    public void getSuccess(String s, int what) {

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
            scrollView.smoothScrollBy(250, 0);
            offset += 250;
        }

        if (offset > (view.getLeft() - 100)) {//需要向左移动
            scrollView.smoothScrollBy(-250, 0);
            offset += -250;
        }
    }

    int index = 1;

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
            case 4:
                view = four;
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
            case 8:
                view = eight;
                break;
            case 9:
                view = nine;
                break;
            case 10:
                view = ten;
                break;
            case 11:
                view = eleven;
                break;
            case 12:
                view = tweven;
                break;
            case 13:
                view = thirth;
                break;
            case 14:
                view = fourth;
                break;
            default:
                view = one;
        }
        ObjectAnimator transAnim = ObjectAnimator.ofFloat(view, "translationY", -50, 50, 50, 0);
        transAnim.setDuration(1000);
        transAnim.start();
    }


    /*****
     * 1识字动画
     2汉字宝宝
     3拼音动画
     4拼音宝宝
     5数学动画
     6口算大王
     7英语影院
     8英语宝宝
     9英文绘本
     10我爱看书
     11国学经典
     12宝宝听听
     13儿歌世界
     14益智游戏
     */
    /*当前选中模块*/
    String type = "识字动画";

    private void jump() {
        switch (type) {
            case "识字动画":
                jumpToActivity(ShiZiShouActivity.class, false);
                break;
            case "汉字宝宝":
                Intent intent = new Intent(getActivity(), BaoBaoShouActivity.class);
                intent.putExtra("type", 47);
                jumpToActivity(intent, false);
                break;
            case "拼音动画":
                jumpToActivity(com.test720.grasshoppercollege.module.youEr.pinYinDongHua.ShiZiShouActivity.class, false);
                break;
            case "拼音宝宝":
                Intent intent1 = new Intent(getActivity(), BaoBaoShouActivity.class);
                intent1.putExtra("type", 48);
                jumpToActivity(intent1, false);
                break;
            case "数学动画":
                jumpToActivity(com.test720.grasshoppercollege.module.youEr.shuXueDongHua.ShiZiShouActivity.class, false);
                break;
            case "口算大王":
                Intent intent9 = new Intent(getActivity(), KouSuanDaWangShouActivity.class);
                intent9.putExtra("type", 50);
                jumpToActivity(intent9, false);
                break;
            case "英语影院":
                jumpToActivity(com.test720.grasshoppercollege.module.youEr.yingYuYinYuan.ShiZiShouActivity.class, false);
                break;
            case "点读乐园":
                jumpToActivity(YouErBaoBaoShouActivity.class, false);
                break;
            case "我爱绘本":
                Intent huiBen = new Intent(getActivity(), HuiBenShouActivity.class);
                huiBen.putExtra("type", 52);
                jumpToActivity(huiBen, false);
                break;
            case "我爱看书":
                Intent kanShu = new Intent(getActivity(), HuiBenShouActivity.class);
                kanShu.putExtra("type", 52);
                jumpToActivity(kanShu, false);
                break;
            case "国学经典":
                jumpToActivity(com.test720.grasshoppercollege.module.youEr.guoXueJingDian.ShiZiShouActivity.class, false);
                break;
            case "宝宝听听":
                jumpToActivity(BaoBaoTingTingShouActivity.class, false);
                break;
            case "儿歌世界":
                jumpToActivity(ErGeShiJieShouActivity.class, false);
                break;
            case "益智游戏":
                jumpToActivity(YouXiShouYeActivity.class, false);
                break;
        }
    }


    @OnClick({R.id.btn, R.id.one, R.id.two, R.id.three, R.id.four, R.id.five, R.id.six, R.id.seven, R.id.eight, R.id.nine, R.id.ten, R.id.eleven, R.id.tweven, R.id.thirth, R.id.fourth})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn:
                jump();
                break;
            case R.id.one:
                if (index == 1) return;
                playAnimBack();
                index = 1;
                playAnim(one);
                mBackground.setImageResource(R.mipmap.yeszdhback);
                type = textOne.getText().toString();
                break;
            case R.id.two:
                if (index == 2) return;
                playAnimBack();
                index = 2;
                playAnim(two);
                mBackground.setImageResource(R.mipmap.yehzbbback);
                type = textTwo.getText().toString();
                break;
            case R.id.three:
                if (index == 3) return;
                playAnimBack();
                index = 3;
                playAnim(three);
                mBackground.setImageResource(R.mipmap.yepydhback);
                type = textThree.getText().toString();
                break;
            case R.id.four:
                if (index == 4) return;
                playAnimBack();
                index = 4;
                playAnim(four);
                mBackground.setImageResource(R.mipmap.yepybbback);
                type = textFour.getText().toString();
                break;
            case R.id.five:
                if (index == 5) return;
                playAnimBack();
                index = 5;
                playAnim(five);
                mBackground.setImageResource(R.mipmap.yesxdhback);
                type = textFive.getText().toString();
                break;
            case R.id.six:
                if (index == 6) return;
                playAnimBack();
                index = 6;
                playAnim(six);
                mBackground.setImageResource(R.mipmap.yeksdwback);
                type = textSix.getText().toString();
                break;
            case R.id.seven:
                if (index == 7) return;
                playAnimBack();
                index = 7;
                playAnim(seven);
                mBackground.setImageResource(R.mipmap.yeyyyyback);
                type = textSeven.getText().toString();
                break;
            case R.id.eight:
                if (index == 8) return;
                playAnimBack();
                index = 8;
                playAnim(eight);
                mBackground.setImageResource(R.mipmap.dianduleyuanback);
                type = textEight.getText().toString();
                break;
            case R.id.nine:
                if (index == 9) return;
                playAnimBack();
                index = 9;
                playAnim(nine);
                mBackground.setImageResource(R.mipmap.woaihuibenback);
                type = textNine.getText().toString();
                break;
            case R.id.ten:
                if (index == 10) return;
                playAnimBack();
                index = 10;
                playAnim(ten);
                mBackground.setImageResource(R.mipmap.yewaksback);
                type = textTen.getText().toString();
                break;
            case R.id.eleven:
                if (index == 11) return;
                playAnimBack();
                index = 11;
                playAnim(eleven);
                mBackground.setImageResource(R.mipmap.yegxjdback);
                type = textEleven.getText().toString();
                break;
            case R.id.tweven:
                if (index == 12) return;
                playAnimBack();
                index = 12;
                playAnim(tweven);
                mBackground.setImageResource(R.mipmap.yebbttback);
                type = textTweven.getText().toString();
                break;
            case R.id.thirth:
                if (index == 13) return;
                playAnimBack();
                index = 13;
                playAnim(thirth);
                mBackground.setImageResource(R.mipmap.yeegsjback);
                type = textThirth.getText().toString();
                break;
            case R.id.fourth:
                if (index == 14) return;
                playAnimBack();
                index = 14;
                playAnim(fourth);
                mBackground.setImageResource(R.mipmap.yeyzyxback);
                type = textFourth.getText().toString();
                break;
        }
    }

}
