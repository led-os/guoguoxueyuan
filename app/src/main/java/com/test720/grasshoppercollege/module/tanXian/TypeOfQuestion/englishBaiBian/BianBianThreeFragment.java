package com.test720.grasshoppercollege.module.tanXian.TypeOfQuestion.englishBaiBian;

import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.bean.RangBean;
import com.test720.grasshoppercollege.myViews.EditableTextView;
import com.test720.grasshoppercollege.publicQuestion.AnswerDialog;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

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
 * 作者：水东流 编于 2018/11/12
 */
public class BianBianThreeFragment extends BaseBaibianTiMu {
    @BindView(R.id.play)
    ImageView play;
    @BindView(R.id.seekBar)
    SeekBar seekBar;
    @BindView(R.id.editable)
    EditableTextView editable;
    @BindView(R.id.ok)
    TextView ok;

    private ArrayList<RangBean> ranges;

    @Override
    public SeekBar getSeekbar() {
        return seekBar;
    }

    @Override
    public void onStart() {
        super.onStart();
        String s = getBean().getQuestion_content();
        ranges = new ArrayList<>();
        ranges.clear();
        for (int i = 0; i < getBean().getSubscript().size(); i++) {
            int t = Integer.parseInt(getBean().getSubscript().get(i));
            ranges.add(new RangBean(t, t + 4));
        }
        editable.setData(s, ranges);
    }

    @Override
    public void startShow() {
    }

    @OnClick({R.id.play, R.id.ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.play:
                if (getMediaPlayer() != null) {
                    if (getMediaPlayer().isPlaying()) {
                        play.setImageResource(R.mipmap.bofang1);
                        getMediaPlayer().pause();
                    } else {
                        play.setImageResource(R.mipmap.zant);
                        getMediaPlayer().start();
                    }
                } else {
                    if (startPlay()) {
                        play.setImageResource(R.mipmap.zant);
                    }
                }
                break;
            case R.id.ok:
                answerOk();
                break;

        }
    }

    /*回答*/
    private void answerOk() {
        if (editable.getAnswers() == null || editable.getAnswers().size() < getBean().getSubscript().size()) {
            ShowToast("请做出您的答案！");
            return;
        }
        if (isAnswer()) {
            nextItem();
            return;
        }
        /*显示答案*/
        for (int i = 0; i < editable.getAnswers().size(); i++) {
            if (getBean().getAnswer().size() < i) {
                ShowToast("答案题目不一致，请联系管理员");
                break;
            }
            /*判断正确还是错误*/
            if (!editable.getAnswers().get(i).equals(getBean().getAnswer().get(i))) {
                setAnswerIsRight(false);
                getBaseTiMuActivity().setFen(getBaseTiMuActivity().getFen() - 1);
                break;
            }
        }

        AnswerDialog answerDialog = new AnswerDialog();
        answerDialog.setRight(isAnswerIsRight());
        answerDialog.setDeceTxt(getBean().getDesc());
        answerDialog.setAnswerTxt(getBean().getAnswer().toString());
        answerDialog.setAnswerInterface(new AnswerDialog.AnswerInterface() {
            @Override
            public void nextClick() {
                nextItem();
            }

            @Override
            public String nextTxt() {
                return "下一题";
            }

            @Override
            public int nextBack() {
                return R.mipmap.xiati;
            }

            @Override
            public int linBack() {
                return R.mipmap.jiexit;
            }
        });
        answerDialog.show(getChildFragmentManager(), "");
        setAnswer(true);
    }

    @Override
    public int setlayoutResID() {
        return R.layout.bai_bain_tian_kong;
    }

}
