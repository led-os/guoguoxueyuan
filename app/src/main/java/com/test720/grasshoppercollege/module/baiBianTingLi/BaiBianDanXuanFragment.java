package com.test720.grasshoppercollege.module.baiBianTingLi;

import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.publicQuestion.AnswerDialog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 水东流 on 2018/5/14.
 */

public class BaiBianDanXuanFragment extends BaseBaiBianTiMuFragment {
    @BindView(R.id.seekBar)
    SeekBar seekBar;
    @BindView(R.id.text_a)
    TextView textA;
    @BindView(R.id.text_b)
    TextView textB;
    @BindView(R.id.text_c)
    TextView textC;
    @BindView(R.id.text_d)
    TextView textD;
    @BindView(R.id.play)
    ImageView play;

    @Override
    public void onStart() {
        super.onStart();
        if (getDataBean() == null) return;
        textA.setText("A " + getDataBean().getList().get(0).getSelect_a());
        textB.setText("B " + getDataBean().getList().get(0).getSelect_b());
        textC.setText("C " + getDataBean().getList().get(0).getSelect_c());
        textD.setText("D " + getDataBean().getList().get(0).getSelect_d());
    }

    @Override
    public void initData() {
    }

    @Override
    public void setListener() {
    }

    @Override
    public int setlayoutResID() {
        return R.layout.bai_bian__dan_xuan_fragment;
    }


    @OnClick({R.id.text_a, R.id.text_b, R.id.text_c, R.id.text_d, R.id.play})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text_a:
                myAnswer("A");
                break;
            case R.id.text_b:
                myAnswer("B");
                break;
            case R.id.text_c:
                myAnswer("C");
                break;
            case R.id.text_d:
                myAnswer("D");
                break;
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
                    if(startPlay()){
                        play.setImageResource(R.mipmap.zant);
                    }
                }
                break;
        }
    }


    /*作答*/
    private void myAnswer(String a) {
        if (isAnswer()) return;
        if (a.equals(dataBean.getList().get(0).getAnswer().get(0))) {
            setAnswerIsRight(true);
        } else {
            setAnswerIsRight(false);
            getBaseTiMuActivity().setFen(getBaseTiMuActivity().getFen() - 1);
        }
        switch (a) {
            case "A":
                if (isAnswerIsRight()) {
                    textA.setBackgroundResource(R.mipmap.duxiao);
                } else {
                    textA.setBackgroundResource(R.mipmap.kucuo);
                }
                break;
            case "B":
                if (isAnswerIsRight()) {
                    textB.setBackgroundResource(R.mipmap.duxiao);
                } else {
                    textB.setBackgroundResource(R.mipmap.kucuo);
                }
                break;
            case "C":
                if (isAnswerIsRight()) {
                    textC.setBackgroundResource(R.mipmap.duxiao);
                } else {
                    textC.setBackgroundResource(R.mipmap.kucuo);
                }
                break;
            case "D":
                if (isAnswerIsRight()) {
                    textD.setBackgroundResource(R.mipmap.duxiao);
                } else {
                    textD.setBackgroundResource(R.mipmap.kucuo);
                }
                break;
        }
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
        setAnswer(true);
    }

    @Override
    public SeekBar getSeekbar() {
        return seekBar;
    }
}
