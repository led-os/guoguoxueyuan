package com.test720.grasshoppercollege.fragment;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Handler;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;

import com.test720.grasshoppercollege.BaseFragment;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.shangCheng.ShangChengActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 水东流 on 2018/6/6.
 */

public class MainPoPFragment extends BaseFragment {
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



    @Override
    public void initData() {

    }

    @Override
    public void onStart() {
        super.onStart();
        moveXanim();
    }

    @Override
    public void setListener() {

    }

    @Override
    public int setlayoutResID() {
        return R.layout.fragment_main_pop;
    }

    @Override
    public void getSuccess(String s, int what) {

    }

    public void moveXanim() {

        new Handler().postDelayed(new Runnable() {
            public void run() {
                six.setVisibility(View.VISIBLE);
                ObjectAnimator transAnimy = ObjectAnimator.ofFloat(six, "translationY", 300, 0);
                transAnimy.setDuration(100);
                transAnimy.start();
            }
        }, 500);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                five.setVisibility(View.VISIBLE);
                ObjectAnimator transAnimy = ObjectAnimator.ofFloat(five, "translationY", 300, 0);
                transAnimy.setDuration(100);
                transAnimy.start();
            }
        }, 600);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                four.setVisibility(View.VISIBLE);

                ObjectAnimator transAnimy = ObjectAnimator.ofFloat(four, "translationY", 300, 0);
                transAnimy.setDuration(100);
                transAnimy.start();

            }
        }, 700);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                three.setVisibility(View.VISIBLE);
                ObjectAnimator transAnimy = ObjectAnimator.ofFloat(three, "translationY", 300, 0);
                transAnimy.setDuration(100);
                transAnimy.start();

            }
        }, 800);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                two.setVisibility(View.VISIBLE);
                ObjectAnimator transAnimy = ObjectAnimator.ofFloat(two, "translationY", 300, 0);
                transAnimy.setDuration(100);
                transAnimy.start();

            }
        }, 900);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                one.setVisibility(View.VISIBLE);
                ObjectAnimator transAnimy = ObjectAnimator.ofFloat(one, "translationY", 300, 0);
                transAnimy.setDuration(100);
                transAnimy.start();

            }
        }, 1000);


    }

    public void toBig(View view) {
        view.setPivotX(view.getWidth() / 2);  // X方向中点
        view.setPivotY(view.getHeight());   // Y方向底边
        AnimatorSet animatorSet = new AnimatorSet();  //组合动画
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 0f, 1.3f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 0f, 1.3f, 1f);
        animatorSet.setDuration(1500);  //动画时间
        animatorSet.setInterpolator(new DecelerateInterpolator());  //设置插值器
        animatorSet.play(scaleX).with(scaleY);  //同时执行
        animatorSet.start();  //启动动画
    }


    @OnClick({R.id.one, R.id.two, R.id.three, R.id.four, R.id.five, R.id.six})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.one:
                toBig(one);
                jumpToActivity(ShangChengActivity.class, false);
                if (click != null) {
                    click.click();
                }
                break;
            case R.id.two:
                toBig(two);
                break;
            case R.id.three:
                toBig(three);
                break;
            case R.id.four:
                toBig(four);
                break;
            case R.id.five:
                toBig(five);
                break;
            case R.id.six:
                toBig(six);
                break;
        }
    }

    public interface Click {
        void click();
    }

    Click click;

    public void setClick(Click click) {
        this.click = click;
    }
}
