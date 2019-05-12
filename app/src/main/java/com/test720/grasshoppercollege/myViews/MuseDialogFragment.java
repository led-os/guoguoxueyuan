package com.test720.grasshoppercollege.myViews;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;

import com.test720.grasshoppercollege.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by 水东流 on 2018/7/11.
 */

public class MuseDialogFragment extends DialogFragment {
    @BindView(R.id.caiDan)
    Button caiDan;
    @BindView(R.id.yixue)
    Button yixue;
    @BindView(R.id.weiXue)
    Button weiXue;
    @BindView(R.id.zhengxue)
    Button zhengxue;
    Unbinder unbinder;
    ItemClick itemClick;

    public ItemClick getItemClick() {
        return itemClick;
    }

    public void setItemClick(ItemClick itemClick) {
        this.itemClick = itemClick;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, android.R.style.Theme_Holo_Light);// 全屏
    }

    /**
     * 系统调用这个来获取DialogFragment的布局，不管是否显示为对话框或嵌入的片段.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 设置宽度为屏宽, 靠近屏幕底部。
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.TOP | Gravity.LEFT; // 紧贴顶部
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        lp.height = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        window.setAttributes(lp);
        View view = inflater.inflate(R.layout.si_muse_dialog, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams windowParams = window.getAttributes();
        windowParams.dimAmount = 0.0f;
        window.setAttributes(windowParams);
        getDialog().setCanceledOnTouchOutside(true);
        startAnim(caiDan, 0, 300);
        toBig(caiDan);
        startAnim(yixue, 135, 265);
        toBig(yixue);
        startAnim(weiXue, 265, 135);
        toBig(weiXue);
        startAnim(zhengxue, 300, 0);
        toBig(zhengxue);
    }

    /*起始动画*/
    private void startAnim(View view, int y, int x) {
        ObjectAnimator transAnimy;
        transAnimy = ObjectAnimator.ofFloat(view, "translationY", -view.getY(), 0, y);
        transAnimy.setDuration(500);
        transAnimy.start();

        ObjectAnimator transAnimx = ObjectAnimator.ofFloat(view, "translationX", 0, x);
        transAnimx.setDuration(500);
        transAnimx.start();
    }

    /*起始动画*/
    private void backtAnim(View view, int x, int y) {
        ObjectAnimator transAnimy;
        transAnimy = ObjectAnimator.ofFloat(view, "translationY", -view.getY(), y, 0);
        transAnimy.setDuration(1000);
        transAnimy.start();

        ObjectAnimator transAnimx = ObjectAnimator.ofFloat(view, "translationX", x, 0);
        transAnimx.setDuration(1000);
        transAnimx.start();

        view.setPivotX(view.getWidth() / 2);  // X方向中点
        view.setPivotY(view.getHeight());   // Y方向底边
        AnimatorSet animatorSet = new AnimatorSet();  //组合动画
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1f, 0f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1f, 0f);
        animatorSet.setDuration(1000);  //动画时间
        animatorSet.setInterpolator(new DecelerateInterpolator());  //设置插值器
        animatorSet.play(scaleX).with(scaleY);  //同时执行
        animatorSet.start();  //启动动画

        dismissAllowingStateLoss();
    }

    public void toBig(View view) {
        view.setPivotX(view.getWidth() / 2);  // X方向中点
        view.setPivotY(view.getHeight());   // Y方向底边
        AnimatorSet animatorSet = new AnimatorSet();  //组合动画
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 0f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 0f, 1f);
        animatorSet.setDuration(500);  //动画时间
        animatorSet.setInterpolator(new DecelerateInterpolator());  //设置插值器
        animatorSet.play(scaleX).with(scaleY);  //同时执行
        animatorSet.start();  //启动动画
    }

    boolean open = true;

    @OnClick({R.id.root,R.id.caiDan, R.id.yixue, R.id.weiXue, R.id.zhengxue})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.root:
                dismissAllowingStateLoss();
                break;
            case R.id.caiDan:
                if (itemClick != null) itemClick.quanBu();
                dismissAllowingStateLoss();
                break;
            case R.id.yixue:
                if (itemClick != null) itemClick.yixue();
                dismissAllowingStateLoss();
                break;
            case R.id.weiXue:
                if (itemClick != null) itemClick.weixue();
                dismissAllowingStateLoss();
                break;
            case R.id.zhengxue:
                if (itemClick != null) itemClick.zhengxue();
                dismissAllowingStateLoss();
                break;
        }
    }

    public interface ItemClick {
        void yixue();

        void weixue();

        void zhengxue();

        void quanBu();
    }
}
