package com.test720.grasshoppercollege.myViews;

import android.graphics.Color;
import android.graphics.Typeface;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.musicMedia.MusicUntil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 水东流 on 2018/5/2.
 */

public class ZuoYeRightOrWrongDialog extends DialogFragment {
    @BindView(R.id.type)
    TextView type;
    @BindView(R.id.jiexi)
    TextView jiexi;
    @BindView(R.id.next)
    TextView next;
    @BindView(R.id.img)
    ImageView img;
    Unbinder unbinder;
    String jieXi;
    boolean right = true;
    Next go;
    MusicUntil musicUntil = new MusicUntil();

    public Next getGo() {
        return go;
    }

    public void setGo(Next go) {
        this.go = go;
    }

    public String getJieXi() {
        return jieXi;
    }

    public void setJieXi(String jieXi) {
        this.jieXi = jieXi;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }


    /**
     * 系统调用这个来获取DialogFragment的布局，不管是否显示为对话框或嵌入的片段.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.zuo_ye_right_or_wrong, container, false);
        unbinder = ButterKnife.bind(this, view);
        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(), "fonts/hkhbt.ttf");
        type.setTypeface(typeFace);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissAllowingStateLoss();
                if (go != null) go.go();
            }
        });
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(false);
    }

    public interface Next {
        void go();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        musicUntil.canlce();
    }

    @Override
    public void onStart() {
        super.onStart();
        //得到dialog对应的window
        Window window = getDialog().getWindow();
        if (window != null) {
            WindowManager.LayoutParams windowParams = window.getAttributes();
            windowParams.dimAmount = 0.0f;

            window.setAttributes(windowParams);
            //得到LayoutParams
            WindowManager.LayoutParams params = window.getAttributes();
            //修改gravity
            params.gravity = Gravity.LEFT;
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.height = WindowManager.LayoutParams.MATCH_PARENT;
            window.setAttributes(params);
        }
        if (right) {
            img.setImageResource(R.mipmap.zqtu);
            type.setText("回答正确");
            musicUntil.playRaw(getContext(), R.raw.righten);
            type.setTextColor(getResources().getColor(R.color.appColor));
            jiexi.setTextColor(getResources().getColor(R.color.green));
        } else {
            type.setText("回答错误");
            musicUntil.playRaw(getContext(), R.raw.wrong);
            img.setImageResource(R.mipmap.kuwawa);
            type.setTextColor(getResources().getColor(R.color.red));
            jiexi.setTextColor(getResources().getColor(R.color.red));
        }
        if (jieXi != null) {
            jiexi.setText(jieXi);
        }
    }
}
