package com.test720.grasshoppercollege.myViews;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.WebViewActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by 水东流 on 2018/4/19.
 */

public class ShouYeGuangGaoDialog extends DialogFragment {
    @BindView(R.id.go)
    TextView go;
    @BindView(R.id.del)
    ImageView del;
    Unbinder unbinder;
    String url;
    @BindView(R.id.time)
    RotateTextView time;
    String timeStr;

    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 系统调用这个来获取DialogFragment的布局，不管是否显示为对话框或嵌入的片段.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.shou_ye_ad, container, false);
        unbinder = ButterKnife.bind(this, view);
        time.setDegrees(358);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().post(new CurrencyEvent("签到", CurrencyEvent.ADDIALOG));
    }

    @Override
    public void onStart() {
        super.onStart();
        if (timeStr != null) {
            time.setText(timeStr);
        }
    }

    @OnClick({R.id.go, R.id.del})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.go:
                Intent in = new Intent(getActivity(), WebViewActivity.class);
                in.putExtra("url", getUrl());
                startActivity(in);
                dismissAllowingStateLoss();
                break;
            case R.id.del:
                dismissAllowingStateLoss();
                break;
        }
    }
}
