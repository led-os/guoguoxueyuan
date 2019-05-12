package com.test720.grasshoppercollege.myViews;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.userData.vip.GouMaiVipActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by 水东流 on 2018/6/11.
 */

public class TextDialog extends DialogFragment {
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.quxiao)
    Button quxiao;
    @BindView(R.id.ok)
    Button ok;
    Unbinder unbinder;
    String textStr;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.vip)
    public Button vip;
    String okText;
    String titleStr;

    float textsize = -1;// 字体大小
    boolean noCache = false;//是否显示取消键
    boolean noVip = false;//是否显示vip
    boolean Mycancelable = true;

    public void setNoVip(boolean noVip) {
        this.noVip = noVip;
    }

    public boolean isMycancelable() {
        return Mycancelable;
    }

    public void setTextsize(float textsize) {
        this.textsize = textsize;
    }

    public void setMycancelable(boolean mycancelable) {
        Mycancelable = mycancelable;
    }

    public void setTitleStr(String titleStr) {
        this.titleStr = titleStr;
    }

    public void setNoCache(boolean noCache) {
        this.noCache = noCache;
    }

    public String getOkText() {
        return okText;
    }

    public void setOkText(String okText) {
        this.okText = okText;
    }

    public void setTextStr(String textStr) {
        this.textStr = textStr;
    }

    OkClick okClick;

    public void setOkClick(OkClick okClick) {
        this.okClick = okClick;
    }


    /*系统调用这个来获取DialogFragment的布局，不管是否显示为对话框或嵌入的片段.*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.text_ti_shi_dialog, container, false);

        unbinder = ButterKnife.bind(this, view);
        ok.setText("确定");
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!isMycancelable()) {
            getDialog().setCancelable(isMycancelable());
            getDialog().setCanceledOnTouchOutside(isMycancelable());
            getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        return true;
                    }
                    return false;
                }
            });
        }
        if (noCache) {
            quxiao.setVisibility(View.GONE);
        }
        if (textStr != null) {
            text.setText(textStr);
        }
        if (titleStr != null) {
            title.setText(titleStr);
        }
        if (textsize != -1) {
            text.setTextSize(textsize);
        }
        if (MyApplication.getmInstance().userData != null) {
            if (MyApplication.getmInstance().userData.getData().getVip().equals("1") || noVip) {
                vip.setVisibility(View.INVISIBLE);
            } else {
                vip.setVisibility(View.INVISIBLE);
            }
        }
        if (okText != null) {
            ok.setText(getOkText());
        }
    }

    @OnClick({R.id.quxiao, R.id.ok, R.id.vip})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.quxiao:
                dismissAllowingStateLoss();
                if (okClick != null) {
                    okClick.no();
                }
                break;
            case R.id.ok:
                dismissAllowingStateLoss();
                if (okClick != null) {
                    okClick.click();
                }
                break;
            case R.id.vip:
                Intent in = new Intent(getActivity(), GouMaiVipActivity.class);
                startActivity(in);
                break;
        }
    }


    public interface OkClick {
        void click();

        void no();
    }
}
