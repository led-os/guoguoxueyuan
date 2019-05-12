package com.test720.grasshoppercollege.fragment;


import android.animation.ObjectAnimator;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.test720.grasshoppercollege.BaseFragment;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.woAidanCiTingXie.WoAiTingXieShouYeActivity;
import com.test720.grasshoppercollege.module.baiBianTingLi.BaiBianShouYeActivity;
import com.test720.grasshoppercollege.module.ciDai.CiDaiShouYeActivity;
import com.test720.grasshoppercollege.module.danCiTingXie.DanCiTingXieShouYeActivity;
import com.test720.grasshoppercollege.module.diandu.DianDuShouYeActivity;
import com.test720.grasshoppercollege.module.englishYueDu.YueDuShouActivity;
import com.test720.grasshoppercollege.module.fanYiChuangGuan.FanYiShouYeActivity;
import com.test720.grasshoppercollege.module.jiangjie.JiangJieShouYeActivity;
import com.test720.grasshoppercollege.module.kouYuShiJian.KouYuShouYeActivity;
import com.test720.grasshoppercollege.module.meiRiYiJu.MeiRiShouYeActivity;
import com.test720.grasshoppercollege.module.peiYin.PeiYinShouActivity;
import com.test720.grasshoppercollege.module.tingLiTest.TingLiShouYeActivity;
import com.test720.grasshoppercollege.module.yinBiao.YinBiaoShouYeActivity;
import com.test720.grasshoppercollege.module.yinMeiWenHua.YinMeiShouYeActivity;
import com.test720.grasshoppercollege.module.yuFaJiangJie.YuFaShouYeActivity;
import com.test720.grasshoppercollege.module.yuFaTest.shouYe.YuFaFirstActivity;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;


public class YinYuFragment extends BaseFragment {


    @BindView(R.id.mBackground)
    ImageView mBackground;
    @BindView(R.id.one)
    LinearLayout one;
    @BindView(R.id.two)
    LinearLayout two;
    @BindView(R.id.three)
    LinearLayout three;
    @BindView(R.id.four)
    LinearLayout four;
    @BindView(R.id.five)
    LinearLayout five;
    @BindView(R.id.six)
    LinearLayout six;
    @BindView(R.id.seven)
    LinearLayout seven;
    @BindView(R.id.eight)
    LinearLayout eight;
    @BindView(R.id.nine)
    LinearLayout nine;
    @BindView(R.id.ten)
    LinearLayout ten;
    @BindView(R.id.eleven)
    LinearLayout eleven;
    @BindView(R.id.root)
    RelativeLayout root;
    String type = "课本点读";
    int index = 1;//标记之前移动过的view标
    @BindView(R.id.one_txt)
    TextView oneTxt;
    @BindView(R.id.two_txt)
    TextView twoTxt;
    @BindView(R.id.three_txt)
    TextView threeTxt;
    @BindView(R.id.four_txt)
    TextView fourTxt;
    @BindView(R.id.five_txt)
    TextView fiveTxt;
    @BindView(R.id.six_txt)
    TextView sixTxt;
    @BindView(R.id.seven_txt)
    TextView sevenTxt;
    @BindView(R.id.eight_txt)
    TextView eightTxt;
    @BindView(R.id.nine_txt)
    TextView nineTxt;
    @BindView(R.id.ten_txt)
    TextView tenTxt;
    @BindView(R.id.eleven_txt)
    TextView elevenTxt;
    Unbinder unbinder;
    @BindView(R.id.twelve_txt)
    TextView twelveTxt;
    @BindView(R.id.twelve)
    LinearLayout twelve;
    @BindView(R.id.thirteen_txt)
    TextView thirteenTxt;
    @BindView(R.id.thirteen)
    LinearLayout thirteen;
    @BindView(R.id.fourteen_txt)
    TextView fourteenTxt;
    @BindView(R.id.fourteen)
    LinearLayout fourteen;
    @BindView(R.id.fifteen_txt)
    TextView fifteenTxt;
    @BindView(R.id.fifteen)
    LinearLayout fifteen;
    @BindView(R.id.sixteen_txt)
    TextView sixteenTxt;
    @BindView(R.id.sixteen)
    LinearLayout sixteen;
    @BindView(R.id.seventeen_txt)
    TextView seventeenTxt;
    @BindView(R.id.seventeen)
    LinearLayout seventeen;
    @BindView(R.id.scrollView)
    HorizontalScrollView scrollView;

    @Override
    public void initData() {
        mYresume();
    }

    @Override
    public void setListener() {

    }

    @Override
    public int setlayoutResID() {
        return R.layout.fragment_yin_yu;
    }

    @Override
    public void getSuccess(String s, int what) {

    }

    int i = 1000;

    private void mYresume() {
        index = 1;
        type = oneTxt.getText().toString();
        i = 1000;
        startAnim(one);
        startAnim(two);
        startAnim(three);
        startAnim(four);
        startAnim(five);
        startAnim(six);
        startAnim(seven);
        startAnim(eight);
        startAnim(nine);
        startAnim(ten);
        startAnim(eleven);
        startAnim(twelve);
        startAnim(thirteen);
        startAnim(fourteen);
        startAnim(fifteen);
        startAnim(sixteen);
        startAnim(seventeen);
    }

    /*起始动画*/
    private void startAnim(View view) {
        i += 200;
        ObjectAnimator transAnimy;
        transAnimy = ObjectAnimator.ofFloat(view, "translationY", -view.getY(), 0, -355, 0);
        transAnimy.setDuration(i);
        transAnimy.start();

        ObjectAnimator transAnimx = ObjectAnimator.ofFloat(view, "translationX", -view.getX(), 0);
        transAnimx.setDuration(i);
        transAnimx.start();
    }

    //初始化偏移量
    private int offset = 0;

    /*点击移动,上移动画*/
    private void playAnim(View view) {
        ObjectAnimator transAnim = ObjectAnimator.ofFloat(view, "translationY", 0, -200, 0, -100);
        transAnim.setDuration(1000);
        transAnim.start();

//获取位置下标
        int scrollViewWidth = scrollView.getWidth();
        if ((scrollViewWidth + offset) < (view.getRight() + 100)) {//需要向右移动
            scrollView.smoothScrollBy(250, 0);
            offset += 250;
        }

        if (offset > (view.getLeft() - 100)) {//需要向左移动
            scrollView.smoothScrollBy(-250, 0);
            offset += -250;
        }
    }

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
                view = twelve;
                break;
            case 13:
                view = thirteen;
                break;
            case 14:
                view = fourteen;
                break;
            case 15:
                view = fifteen;
                break;
            case 16:
                view = sixteen;
                break;
            case 17:
                view = seventeen;
                break;
            default:
                view = one;
        }
        ObjectAnimator transAnim = ObjectAnimator.ofFloat(view, "translationY", -50, 50, 50, 0);
        transAnim.setDuration(1000);
        transAnim.start();
    }


    @OnClick({R.id.one, R.id.two, R.id.three, R.id.four, R.id.five,
            R.id.six, R.id.seven, R.id.nine, R.id.eight, R.id.ten,
            R.id.eleven, R.id.twelve, R.id.thirteen, R.id.fourteen, R.id.fifteen,
            R.id.sixteen, R.id.seventeen, R.id.btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.one:
                if (index == 1) return;
                playAnimBack();
                index = 1;
                playAnim(one);
                mBackground.setImageResource(R.mipmap.dianduback);
                type = oneTxt.getText().toString();
                break;
            case R.id.two:
                if (index == 2) return;
                playAnimBack();
                index = 2;
                playAnim(two);
                mBackground.setImageResource(R.mipmap.jiangjieback);
                type = twoTxt.getText().toString();
                break;
            case R.id.three:
                if (index == 3) return;
                playAnimBack();
                index = 3;
                playAnim(three);
                mBackground.setImageResource(R.mipmap.danciback);
                type = threeTxt.getText().toString();
                break;
            case R.id.four:
                if (index == 4) return;
                playAnimBack();
                index = 4;
                playAnim(four);
                mBackground.setImageResource(R.mipmap.fanyiback);
                type = fourTxt.getText().toString();
                break;
            case R.id.five:
                if (index == 5) return;
                playAnimBack();
                index = 5;
                playAnim(five);
                mBackground.setImageResource(R.mipmap.bai_bian_ting_li);
                type = fiveTxt.getText().toString();
                break;
            case R.id.six:
                if (index == 6) return;
                playAnimBack();
                index = 6;
                playAnim(six);
                mBackground.setImageResource(R.mipmap.cidaiback);
                type = sixTxt.getText().toString();
                break;
            case R.id.seven:
                if (index == 7) return;
                playAnimBack();
                index = 7;
                playAnim(seven);
                mBackground.setImageResource(R.mipmap.guojiyingbiaoback);
                type = sevenTxt.getText().toString();
                break;
            case R.id.eight:
                if (index == 8) return;
                playAnimBack();
                index = 8;
                playAnim(eight);
                mBackground.setImageResource(R.mipmap.dan_ci_bain_yin_back);
                type = eightTxt.getText().toString();
                break;
            case R.id.nine:
                if (index == 9) return;
                playAnimBack();
                index = 9;
                playAnim(nine);
                mBackground.setImageResource(R.mipmap.yufaback);
                type = nineTxt.getText().toString();
                break;
            case R.id.ten:
                if (index == 10) return;
                playAnimBack();
                index = 10;
                playAnim(ten);
                mBackground.setImageResource(R.mipmap.yufaxuexiback);
                type = tenTxt.getText().toString();
                break;
            case R.id.eleven:
                if (index == 11) return;
                playAnimBack();
                index = 11;
                playAnim(eleven);
                mBackground.setImageResource(R.mipmap.yueduback);
                type = elevenTxt.getText().toString();
                break;
            case R.id.twelve:
                if (index == 12) return;
                playAnimBack();
                index = 12;
                playAnim(twelve);
//                mBackground.setImageResource(R.mipmap.dongmanback);
                type = twelveTxt.getText().toString();
                break;
            case R.id.thirteen:
                if (index == 13) return;
                playAnimBack();
                index = 13;
                playAnim(thirteen);
//                mBackground.setImageResource(R.mipmap.wo_ai_pei_yin_back);
                type = thirteenTxt.getText().toString();
                break;
            case R.id.fourteen:
                if (index == 14) return;
                playAnimBack();
                index = 14;
                playAnim(fourteen);
                mBackground.setImageResource(R.mipmap.woaitingxie);
                type = fourteenTxt.getText().toString();
                break;
            case R.id.fifteen:
                if (index == 15) return;
                playAnimBack();
                index = 15;
                playAnim(fifteen);
                mBackground.setImageResource(R.mipmap.meiriyijuback);
                type = fifteenTxt.getText().toString();
                break;
            case R.id.sixteen:
                if (index == 16) return;
                playAnimBack();
                index = 16;
                playAnim(sixteen);
                mBackground.setImageResource(R.mipmap.keyushijianback);
                type = sixteenTxt.getText().toString();
                break;
            case R.id.seventeen:
                if (index == 17) return;
                playAnimBack();
                index = 17;
                playAnim(seventeen);
                mBackground.setImageResource(R.mipmap.ymwhback);
                type = seventeenTxt.getText().toString();
                break;
            case R.id.btn:
                jumpTo();
                break;
        }
    }

    /*绑定的跳转*/
    private void jumpTo() {
        switch (type) {
            case "磁带播放":
                jumpToActivity(CiDaiShouYeActivity.class, false);
                break;
            case "阅读训练":
                jumpToActivity(YueDuShouActivity.class, false);
                break;
            case "每日一句":
                jumpToActivity(MeiRiShouYeActivity.class, false);
                break;
            case "百辩听力":
                jumpToActivity(BaiBianShouYeActivity.class, false);
                break;
            case "语法练习":
                jumpToActivity(YuFaFirstActivity.class, false);
                break;
            case "课本点读":
                jumpToActivity(DianDuShouYeActivity.class, false);
                break;
            case "课文讲解":
                jumpToActivity(JiangJieShouYeActivity.class, false);
                break;
            case "单词辨音":
                jumpToActivity(TingLiShouYeActivity.class, false);
                break;
            case "单词听写":
                jumpToActivity(DanCiTingXieShouYeActivity.class, false);
                break;
            case "翻译闯关":
                jumpToActivity(FanYiShouYeActivity.class, false);
                break;
            case "我爱配音":
                jumpToActivity(PeiYinShouActivity.class, false);
                break;
            case "口语时间":
                jumpToActivity(KouYuShouYeActivity.class, false);
                break;
            case "国际音标":
                jumpToActivity(YinBiaoShouYeActivity.class, false);
                break;
            case "我爱听写":
                jumpToActivity(WoAiTingXieShouYeActivity.class, false);
                break;
            case "语法讲解":
                jumpToActivity(YuFaShouYeActivity.class, false);
                break;
            case "英语沙龙":
                jumpToActivity(YinMeiShouYeActivity.class, false);
                break;
        }
    }

}
