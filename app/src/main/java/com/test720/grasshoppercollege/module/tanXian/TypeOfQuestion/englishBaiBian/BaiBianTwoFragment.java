package com.test720.grasshoppercollege.module.tanXian.TypeOfQuestion.englishBaiBian;

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
public class BaiBianTwoFragment extends BaseBaibianTiMu {
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
        if (getBean() == null) return;
        textA.setText(getBean().getSelect_a());
        textB.setText(getBean().getSelect_b());
        textC.setText(getBean().getSelect_c());
        textD.setText(getBean().getSelect_d());
    }

    @Override
    public void startShow() {

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
                return R.mipmap.benzizi;
            }
        });
        answerDialog.show(getChildFragmentManager(), "");
        setAnswer(true);
    }

    @Override
    public int setlayoutResID() {
        return R.layout.bai_bian_ju_dan_xuan;
    }

}
