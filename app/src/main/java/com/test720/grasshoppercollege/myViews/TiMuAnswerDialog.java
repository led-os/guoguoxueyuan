package com.test720.grasshoppercollege.myViews;

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
 * Created by 水东流 on 2018/8/13.
 */

public class TiMuAnswerDialog extends DialogFragment {
    int answerback = -1;
    String jieXi, answerTxt;

    @BindView(R.id.answer)
    TextView answer;
    @BindView(R.id.dece)
    TextView dece;
    @BindView(R.id.next)
    TextView next;
    @BindView(R.id.isright)
    TextView isright;
    @BindView(R.id.answerLin)
    LinearLayout answerLin;
    Unbinder unbinder;

    boolean isRight = true;
    MusicUntil musicUntil = new MusicUntil();

    public void setRight(boolean right) {
        isRight = right;
    }

    public void setJieXi(String jieXi) {
        this.jieXi = jieXi;
    }

    public void setAnswerTxt(String answerTxt) {
        this.answerTxt = answerTxt;
    }

    /**
     * 系统调用这个来获取DialogFragment的布局，不管是否显示为对话框或嵌入的片段.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.ti_mu_answer_dialog, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        musicUntil.canlce();
    }

    @OnClick(R.id.next)
    public void onViewClicked() {
        dismissAllowingStateLoss();
        if (nextTiMu != null) {
            nextTiMu.next();
        }
    }

    public interface Next {
        void go();
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
            params.gravity = Gravity.START;
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.height = WindowManager.LayoutParams.MATCH_PARENT;
            window.setAttributes(params);
        }
        if (getAnswerback() != -1) {
            answerLin.setBackgroundResource(getAnswerback());
        }
      /*  Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(), "fonts/hkhbt.ttf");
        answer.setTypeface(typeFace);*/
        if (answerTxt != null) answer.setText(answerTxt);
        if (jieXi != null) {
            dece.setText(jieXi);
        }

        if (isRight) {
            isright.setText("回答正确");
            musicUntil.playRaw(getActivity(), R.raw.righten);
            isright.setTextColor(getResources().getColor(R.color.green));
        } else {
            isright.setText("回答错误");
            musicUntil.playRaw(getActivity(), R.raw.wrong);
            isright.setTextColor(getResources().getColor(R.color.red));
        }
    }

    public int getAnswerback() {
        return answerback;
    }

    public void setAnswerback(int answerback) {
        this.answerback = answerback;
    }

    NextTiMu nextTiMu;

    public void setNextTiMu(NextTiMu nextTiMu) {
        this.nextTiMu = nextTiMu;
    }

    public interface NextTiMu {
        void next();
    }
}
