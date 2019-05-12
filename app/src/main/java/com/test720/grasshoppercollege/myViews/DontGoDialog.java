package com.test720.grasshoppercollege.myViews;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test720.grasshoppercollege.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by 水东流 on 2018/6/14.
 */

public class DontGoDialog extends DialogFragment {
    @BindView(R.id.fanqi)
    TextView fanqi;
    @BindView(R.id.jixu)
    TextView jixu;
    Unbinder unbinder;
    Click click;

    public void setClick(Click click) {
        this.click = click;
    }

    /**
     * 系统调用这个来获取DialogFragment的布局，不管是否显示为对话框或嵌入的片段.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.dot_go_dialog, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.fanqi, R.id.jixu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fanqi:
                click.fangqi();
                dismissAllowingStateLoss();
                break;
            case R.id.jixu:
                click.jixu();
                dismissAllowingStateLoss();
                break;
        }
    }

    public interface Click {
        void fangqi();

        void jixu();
    }
}
