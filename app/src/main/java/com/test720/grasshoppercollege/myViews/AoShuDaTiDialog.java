package com.test720.grasshoppercollege.myViews;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.musicMedia.MusicUntil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by 水东流 on 2018/4/26.
 */

public class AoShuDaTiDialog extends DialogFragment {
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.answer)
    TextView answer;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.dec)
    ImageView dec;
    @BindView(R.id.next)
    TextView next;
    Unbinder unbinder;
    String s, rightAnswer;
    boolean right = false;
    Next nextIn;
    MusicUntil musicUntil = new MusicUntil();

    public void setNextIn(Next nextIn) {
        this.nextIn = nextIn;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public String getS() {
        return s;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public void setS(String s) {
        this.s = s;
    }

    /**
     * 系统调用这个来获取DialogFragment的布局，不管是否显示为对话框或嵌入的片段.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT); //宽高
        View view = inflater.inflate(R.layout.ao_shu_da_ti_dialog, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        musicUntil.canlce();
    }

    @Override
    public void onStart() {
        super.onStart();
        text.setMovementMethod(ScrollingMovementMethod.getInstance());
        text.setText(s);
        if (right) {
            title.setText("恭喜您，回答正确!");
            musicUntil.playRaw(getContext(), R.raw.righten);
            title.setTextColor(getResources().getColor(R.color.green));
        } else {
            title.setText("啊哦，再好好想想哦!");
            musicUntil.playRaw(getContext(), R.raw.wrong);
            title.setTextColor(getResources().getColor(R.color.red));
        }
        if (rightAnswer != null) {
            answer.setText(rightAnswer);
        }
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

    @OnClick({R.id.dec, R.id.next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.dec:
                dismissAllowingStateLoss();
                break;
            case R.id.next:
                nextIn.nextClick();
                dismissAllowingStateLoss();
                break;
        }
    }

    public interface Next {
        void nextClick();
    }

}
