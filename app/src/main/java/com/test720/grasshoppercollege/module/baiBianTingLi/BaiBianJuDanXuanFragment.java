package com.test720.grasshoppercollege.module.baiBianTingLi;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.publicQuestion.AnswerDialog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 水东流 on 2018/6/26.
 */

public class BaiBianJuDanXuanFragment extends BaseBaiBianTiMuFragment {
    @BindView(R.id.play)
    ImageView play;
    @BindView(R.id.seekBar)
    SeekBar seekBar;
    @BindView(R.id.title_a)
    TextView titleA;
    @BindView(R.id.text_a)
    TextView textA;
    @BindView(R.id.oneImg)
    ImageView oneImg;
    @BindView(R.id.one)
    LinearLayout one;
    @BindView(R.id.title_b)
    TextView titleB;
    @BindView(R.id.text_b)
    TextView textB;
    @BindView(R.id.twoImg)
    ImageView twoImg;
    @BindView(R.id.two)
    LinearLayout two;
    @BindView(R.id.text_c)
    TextView textC;
    @BindView(R.id.threeImg)
    ImageView threeImg;
    @BindView(R.id.three)
    LinearLayout three;
    @BindView(R.id.title_d)
    TextView titleD;
    @BindView(R.id.text_d)
    TextView textD;
    @BindView(R.id.fourImg)
    ImageView fourImg;
    @BindView(R.id.four)
    LinearLayout four;


    @Override
    public SeekBar getSeekbar() {
        return seekBar;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDataBean() == null) return;
        textA.setText(getDataBean().getList().get(0).getSelect_a());
        textB.setText(getDataBean().getList().get(0).getSelect_b());
        textC.setText(getDataBean().getList().get(0).getSelect_c());
        textD.setText(getDataBean().getList().get(0).getSelect_d());
    }

    @Override
    public void setListener() {

    }

    @Override
    public int setlayoutResID() {
        return R.layout.bai_bian_ju_dan_xuan;
    }


    @OnClick({R.id.one, R.id.two, R.id.three, R.id.four, R.id.play})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.one:
                myAnswer("A");
                break;
            case R.id.two:
                myAnswer("B");
                break;
            case R.id.three:
                myAnswer("C");
                break;
            case R.id.four:
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
                oneImg.setVisibility(View.VISIBLE);
                if (isAnswerIsRight()) {
                    oneImg.setImageResource(R.mipmap.dui);
                } else {
                    oneImg.setImageResource(R.mipmap.cuo);
                }
                break;
            case "B":
                twoImg.setVisibility(View.VISIBLE);
                if (isAnswerIsRight()) {
                    twoImg.setImageResource(R.mipmap.dui);
                } else {
                    twoImg.setImageResource(R.mipmap.cuo);
                }
                break;
            case "C":
                threeImg.setVisibility(View.VISIBLE);
                if (isAnswerIsRight()) {
                    threeImg.setImageResource(R.mipmap.dui);
                } else {
                    threeImg.setImageResource(R.mipmap.cuo);
                }
                break;
            case "D":
                fourImg.setVisibility(View.VISIBLE);
                if (isAnswerIsRight()) {

                    fourImg.setImageResource(R.mipmap.dui);
                } else {
                    fourImg.setImageResource(R.mipmap.cuo);
                }
                break;
        }
        setAnswer(true);
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
                return R.mipmap.benzizi;
            }
        });
        answerDialog.show(getChildFragmentManager(), "");

    }
}
