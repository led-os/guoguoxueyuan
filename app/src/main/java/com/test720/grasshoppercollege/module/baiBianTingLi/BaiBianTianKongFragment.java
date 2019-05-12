package com.test720.grasshoppercollege.module.baiBianTingLi;

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
 * Created by 水东流 on 2018/6/26.
 */

public class BaiBianTianKongFragment extends BaseBaiBianTiMuFragment {
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
    public void setListener() {

    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDataBean() == null) return;

        String s = dataBean.getList().get(0).getQuestion();
        try {
            ranges = new ArrayList<>();
            ranges.clear();
            for (int i = 0; i < dataBean.getList().get(0).getSubscript().size(); i++) {
                int t = Integer.parseInt(dataBean.getList().get(0).getSubscript().get(i));
                ranges.add(new RangBean(t, t + 4));
            }
            editable.setData(s, ranges);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int setlayoutResID() {
        return R.layout.bai_bain_tian_kong;
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
        if (editable.getAnswers() == null || editable.getAnswers().size() < dataBean.getList().get(0).getSubscript().size()) {
            ShowToast("请做出您的答案！");
            return;
        }
        if (isAnswer) {
            return;
        }
        /*显示答案*/
        for (int i = 0; i < editable.getAnswers().size(); i++) {
            if (dataBean.getList().get(0).getAnswer().size() < i) {
                ShowToast("答案题目不一致，请联系管理员");
                break;
            }
            String eds = editable.getAnswers().get(i);
            String ans = dataBean.getList().get(0).getAnswer().get(i);
            /*判断正确还是错误*/
            /*不区分大小写和空格*/
            eds = eds.toLowerCase();
            ans = ans.toLowerCase();
            eds = eds.replaceAll(" ", "");
            ans = ans.replaceAll(" ", "");

            /*判断正确还是错误*/
            if (!eds.equals(ans)) {
                answerIsRight = false;
                getBaseTiMuActivity().setFen(getBaseTiMuActivity().getFen() - 1);
                break;
            }
        }
        isAnswer = true;
        AnswerDialog answerDialog = new AnswerDialog();
        answerDialog.setRight(isAnswerIsRight());
        answerDialog.setDeceTxt(getDataBean().getList().get(0).getDesc());
        answerDialog.setAnswerTxt(dataBean.getList().get(0).getAnswer().toString());
        answerDialog.setAnswerInterface(new AnswerDialog.AnswerInterface() {
            @Override
            public void nextClick() {
                if (getViewPager().getCurrentItem() < getViewPager().getAdapter().getCount() - 1) {
                    getViewPager().setCurrentItem(getViewPager().getCurrentItem() + 1);
                    stop();
                } else {
                    AddGuoGuoDou();
                }
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

    }
}
