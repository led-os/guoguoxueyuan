package com.test720.grasshoppercollege.module.meiRiYiJu;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.musicMedia.MusicUntil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 佛祖保佑        永无BUG
 * 佛曰:
 * 程序园里程序天，程序天里程序员；
 * 程序猿人写程序，又拿程序换肉钱。
 * 肉饱继续桌前坐，饱暖还是桌前眠；
 * 半迷半醒日复日，码上码下年复年。
 * 但愿叱咤互联世，不愿搬砖码当前；
 * 诸葛周瑜算世事，我算需求得加钱。
 * 别人笑我忒直男，我说自己是程猿；
 * 但见成都府国内，处处地地程序员。
 * 作者：水东流 编于 2018/11/22
 */
public class MeiRiAnswerDialog extends DialogFragment {
    @BindView(R.id.answer)
    TextView answer;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.next)
    TextView next;
    Unbinder unbinder;
    String rightAnswer;
    boolean right = false;
    MusicUntil musicUntil = new MusicUntil();
    Next nextIn;


    public void setNextIn(Next nextIn) {
        this.nextIn = nextIn;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }


    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }


    /**
     * 系统调用这个来获取DialogFragment的布局，不管是否显示为对话框或嵌入的片段.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT); //宽高
        View view = inflater.inflate(R.layout.mei_ri_yi_ju_dialog, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (rightAnswer != null) {
            answer.setText(rightAnswer);
        }
        if (right) {
            musicUntil.playRaw(getActivity(), R.raw.righten);
            title.setTextColor(getResources().getColor(R.color.green));
            title.setText("恭喜您，回答正确!");
        } else {
            musicUntil.playRaw(getActivity(), R.raw.wrong);
            title.setTextColor(getResources().getColor(R.color.red));
            title.setText("啊哦，再好好想想哦!");
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

    @OnClick({R.id.next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
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