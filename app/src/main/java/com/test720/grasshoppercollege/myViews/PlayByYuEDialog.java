package com.test720.grasshoppercollege.myViews;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.test720.grasshoppercollege.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 水东流 on 2018/6/4.
 */

public class PlayByYuEDialog extends DialogFragment {
    @BindView(R.id.close)
    ImageView close;
    @BindView(R.id.money)
    TextView money;
    @BindView(R.id.pay_password)
    PayPasswordView payPassword;
    Unbinder unbinder;
    PlayOk playOk;
    String moneyStr;

    public void setMoneyStr(String moneyStr) {
        this.moneyStr = moneyStr;
    }

    public void setPlayOk(PlayOk playOk) {
        this.playOk = playOk;
    }

    /**
     * 系统调用这个来获取DialogFragment的布局，不管是否显示为对话框或嵌入的片段.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.play_by_yu_e, container, false);
        unbinder = ButterKnife.bind(this, view);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (playOk != null) playOk.close();
                dismissAllowingStateLoss();
            }
        });
        payPassword.setOnFinishInput(new PayPasswordView.OnPasswordInputFinish() {
            @Override
            public void inputFinish() {
                playOk.playOk(payPassword.getStrPassword());
                dismissAllowingStateLoss();
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (moneyStr != null) {
            String s=getResources().getString(R.string.jinE)+ moneyStr;
            money.setText(s);
        }
    }

    public interface PlayOk {
        void playOk(String pwd);

        void close();
    }
}
