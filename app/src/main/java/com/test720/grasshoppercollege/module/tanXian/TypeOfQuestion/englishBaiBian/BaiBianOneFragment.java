package com.test720.grasshoppercollege.module.tanXian.TypeOfQuestion.englishBaiBian;

import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.publicQuestion.AnswerDialog;

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
public class BaiBianOneFragment extends BaseBaibianTiMu {
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
        textA.setText("A " + getBean().getSelect_a());
        textB.setText("B " + getBean().getSelect_b());
        textC.setText("C " + getBean().getSelect_c());
        textD.setText("D " + getBean().getSelect_d());
    }

    @Override
    public void startShow() {
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
                    if (startPlay()) {
                        play.setImageResource(R.mipmap.zant);
                    }
                }
                break;
        }
    }


    /*作答*/
    private void myAnswer(String a) {
        if (isAnswer()) return;
        if (a.equals(getBean().getAnswer().get(0))) {
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
    public SeekBar getSeekbar() {
        return seekBar;
    }

    @Override
    public int setlayoutResID() {
        return R.layout.bai_bian__dan_xuan_fragment;
    }
}
