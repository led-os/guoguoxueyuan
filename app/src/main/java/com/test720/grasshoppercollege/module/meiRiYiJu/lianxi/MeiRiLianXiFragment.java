package com.test720.grasshoppercollege.module.meiRiYiJu.lianxi;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.text.InputType;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test720.grasshoppercollege.HttpBean.MeiRiYiJuLianXiAndGameData;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.bean.RangBean;
import com.test720.grasshoppercollege.module.meiRiYiJu.MeiRiAnswerDialog;
import com.test720.grasshoppercollege.module.meiRiYiJu.MeiRiYiJuFragment;
import com.test720.grasshoppercollege.myViews.EditableTextView;
import com.test720.grasshoppercollege.untils.CountDownTimerUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 水东流 on 2018/6/28.
 */

public class MeiRiLianXiFragment extends MeiRiYiJuFragment {


    @BindView(R.id.play)
    ImageView play;
    @BindView(R.id.editable)
    EditableTextView editable;
    @BindView(R.id.ok)
    TextView ok;
    @BindView(R.id.startText)
    TextView startText;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.tiltle)
    TextView tiltle;
    @BindView(R.id.top)
    LinearLayout top;

    CountDownTimerUtil countDownTimerUtil;

    @Override
    public void initData() {

    }

    @Override
    public void setListener() {

    }

    @Override
    public int setlayoutResID() {
        return R.layout.activity_mei_ri_lian_xi;
    }

    @Override
    public void getSuccess(String s, int what) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (countDownTimerUtil != null) countDownTimerUtil.over();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getData() == null) return;
        if (countDownTimerUtil.getTime() > 5) {
            countDownTimerUtil.start();
            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    countDownTimerUtil.finishCount();
                }
            });
            startText.setText(getData().getData().getEn());
            tiltle.setText(getData().getData().getCn());
        }
    }


    public void OkQuestion() {
        try {
            for (int i = 0; i < getData().getData().getSubscript().size(); i++) {
                int t = Integer.parseInt(getData().getData().getSubscript().get(i));
                getRanges().add(new RangBean(t, t + 4));
            }
            editable.setData(getData().getData().getQuestion(), getRanges());
            if (editable.getInput() != null)
                editable.getInput().setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    answerOk();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*回答*/
    @Override
    public boolean answerOk() {
        if (editable.getAnswers() == null || editable.getAnswers().size() < getData().getData().getSubscript().size()) {
            ShowToast("请做出您的答案！");
            return false;
        }
        ok.setVisibility(View.GONE);

        for (int i = 0; i < getData().getData().getAnswer().size(); i++) {
            if (getData().getData().getAnswer().size() < i) {
                ShowToast("答案题目不一致，请联系管理员");
                break;
            }
            /*判断正确还是错误*/
            String s = editable.getAnswers().get(i);
            String an = getData().getData().getAnswer().get(i);
            s = s.toLowerCase();
            an = an.toLowerCase();
            s = s.replaceAll(" ", "");
            an = an.replaceAll(" ", "");
            if (!s.equals(an)) {
                setAnswerIsRight(false);
                break;
            }
        }

        MeiRiAnswerDialog dialog = new MeiRiAnswerDialog();
        dialog.setRightAnswer(getData().getData().getEn());
        dialog.setRight(isAnswerIsRight());
        dialog.setNextIn(new MeiRiAnswerDialog.Next() {
            @Override
            public void nextClick() {
                getActivity().finish();
            }
        });
        dialog.show(getChildFragmentManager(), "answ");
        isAnswer = true;
        return true;
    }


    @OnClick(R.id.play)
    public void onViewClicked() {
        if (getData() == null) return;
        toBig(top);
        playUrl(getData().getData().getVoice());
    }

    public void toBig(View view) {
        view.setPivotX(view.getWidth() / 2);  // X方向中点
        view.setPivotY(view.getHeight() / 2);   // Y方向底边
        AnimatorSet animatorSet = new AnimatorSet();  //组合动画
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1f, 1.2f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1f, 1.2f, 1f);
        animatorSet.setDuration(1000);  //动画时间
        animatorSet.setInterpolator(new DecelerateInterpolator());  //设置插值器
        animatorSet.play(scaleX).with(scaleY);  //同时执行
        animatorSet.start();  //启动动画
    }

    public void setData(MeiRiYiJuLianXiAndGameData data) {
        this.data = data;
        countDownTimerUtil = new CountDownTimerUtil(6);
        countDownTimerUtil.setTimeInterface(new CountDownTimerUtil.TimeInterface() {
            @Override
            public void upDataUi(int t) {
                time.setText(t + "");
            }

            @Override
            public void timeIsOver() {
                startText.setVisibility(View.GONE);
                time.setVisibility(View.GONE);
                ok.setText("完成");
                OkQuestion();
            }
        });
    }

    public MeiRiYiJuLianXiAndGameData getData() {
        return data;
    }

    MeiRiYiJuLianXiAndGameData data;
}
