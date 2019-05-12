package com.test720.grasshoppercollege.module.meiRiYiJu.game;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test720.grasshoppercollege.HttpBean.MeiRiGameData;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.meiRiYiJu.MeiRiAnswerDialog;
import com.test720.grasshoppercollege.module.meiRiYiJu.MeiRiYiJuFragment;
import com.test720.grasshoppercollege.myViews.MoveTextView;
import com.test720.grasshoppercollege.untils.CountDownTimerUtil;
import com.test720.grasshoppercollege.untils.PublicUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 水东流 on 2018/5/15.
 */

public class MeiRiYiJuTianKongYouXiFragment extends MeiRiYiJuFragment implements MoveTextView.AcctionUp {
    MeiRiGameData data;
    private List<String> gameList = new ArrayList<>();//打乱顺序的集合
    int index = 0;//答案标
    CountDownTimerUtil countDownTimerUtil;

    public MeiRiGameData getData() {
        return data;
    }

    public void setData(MeiRiGameData data) {
        this.data = data;
        gameList.addAll(data.getData().getGame());
        gameList = PublicUntil.randomList((ArrayList<String>) gameList);
        countDownTimerUtil = new CountDownTimerUtil(6);
        countDownTimerUtil.setTimeInterface(new CountDownTimerUtil.TimeInterface() {
            @Override
            public void upDataUi(int t) {
                time.setText(t + "");
            }

            @Override
            public void timeIsOver() {
                time.setVisibility(View.GONE);
                ok.setVisibility(View.GONE);
                startMove();
            }
        });
    }

    @Override
    public void initData() {

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
            tiltle.setText(getData().getData().getCn());
            text.setText(getData().getData().getEn());
        }
    }

    /**
     * 倒计时完毕，进行打乱
     */
    private void startMove() {
        text.setText("");
        for (int i = 0; i < gameList.size(); i++) {
            switch (i) {
                case 0:
                    one.setVisibility(View.VISIBLE);
                    one.setText(gameList.get(i));
                    one.setAcctionUp(this);
                    move(one, 1);
                    break;
                case 1:
                    two.setVisibility(View.VISIBLE);
                    two.setText(gameList.get(i));
                    two.setAcctionUp(this);
                    move(two, 2);
                    break;
                case 2:
                    three.setVisibility(View.VISIBLE);
                    three.setText(gameList.get(i));
                    three.setAcctionUp(this);
                    move(three, 3);
                    break;
                case 3:
                    four.setVisibility(View.VISIBLE);
                    four.setText(gameList.get(i));
                    four.setAcctionUp(this);
                    move(four, 4);
                    break;
                case 4:
                    five.setVisibility(View.VISIBLE);
                    five.setText(gameList.get(i));
                    five.setAcctionUp(this);
                    move(five, 5);
                    break;
                case 5:
                    six.setVisibility(View.VISIBLE);
                    six.setText(gameList.get(i));
                    six.setAcctionUp(this);
                    move(six, 6);
                    break;
                case 6:
                    seven.setVisibility(View.VISIBLE);
                    seven.setText(gameList.get(i));
                    seven.setAcctionUp(this);
                    move(seven, 7);
                    break;
                case 7:
                    eight.setVisibility(View.VISIBLE);
                    eight.setText(gameList.get(i));
                    eight.setAcctionUp(this);
                    move(eight, 8);
                    break;
                case 8:
                    nine.setVisibility(View.VISIBLE);
                    nine.setText(gameList.get(i));
                    nine.setAcctionUp(this);
                    move(nine, 9);
                    break;
            }

        }
    }


    /*动画移动*/
    public void move(MoveTextView view, int i) {
        ObjectAnimator transAnimx = ObjectAnimator.ofFloat(view, "translationX", 0, 220 * (i % 3));
        transAnimx.setDuration(1000);
        transAnimx.start();

        int j = i / 3;
        ObjectAnimator transAnim = ObjectAnimator.ofFloat(view, "translationY", 0, 100 * j + 500);
        transAnim.setDuration(1000);
        transAnim.start();
    }


    @Override
    public void setListener() {

    }

    @Override
    public int setlayoutResID() {
        return R.layout.mei_ri_yi_ju_tian_kong;
    }

    @Override
    public void getSuccess(String s, int what) {

    }


    @Override
    protected boolean answerOk() {
        String ans = text.getText().toString();
        String en = getData().getData().getEn();
        ans = ans.toLowerCase();
        en = en.toLowerCase();
        ans = ans.replaceAll(" ", "");
        en = en.replaceAll(" ", "");
        if (ans.equals(en)) {
            setAnswerIsRight(true);
        } else {
            setAnswerIsRight(false);
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

    /*移动到格子中*/
    @Override
    public void acctionUp(MoveTextView moveTextView) {
        if (moveTextView.getY() < 300) {
            index++;
            moveTextView.setVisibility(View.GONE);
            String s = text.getText().toString() + "  " + moveTextView.getText().toString();
            text.setText(s);
            if (index == getData().getData().getGame().size()) {
                answerOk();
            }
        }
    }


    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.ok)
    TextView ok;
    @BindView(R.id.play)
    ImageView play;
    @BindView(R.id.tiltle)
    TextView tiltle;
    @BindView(R.id.top)
    LinearLayout top;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.one)
    MoveTextView one;
    @BindView(R.id.two)
    MoveTextView two;
    @BindView(R.id.three)
    MoveTextView three;
    @BindView(R.id.four)
    MoveTextView four;
    @BindView(R.id.five)
    MoveTextView five;
    @BindView(R.id.six)
    MoveTextView six;
    @BindView(R.id.seven)
    MoveTextView seven;
    @BindView(R.id.eight)
    MoveTextView eight;
    @BindView(R.id.nine)
    MoveTextView nine;

}
