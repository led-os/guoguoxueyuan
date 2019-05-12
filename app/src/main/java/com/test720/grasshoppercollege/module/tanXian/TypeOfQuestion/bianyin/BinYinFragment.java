package com.test720.grasshoppercollege.module.tanXian.TypeOfQuestion.bianyin;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.tanXian.TypeOfQuestion.BaseTanXianTiMuFragment;
import com.test720.grasshoppercollege.untils.musicMedia.MusicUntil;

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
public class BinYinFragment extends BaseTanXianTiMuFragment {
    @BindView(R.id.dui)
    SeekBar dui;
    @BindView(R.id.cuo)
    SeekBar cuo;
    @BindView(R.id.answer)
    TextView answer;
    @BindView(R.id.righOrWrong)
    TextView righOrWrong;
    @BindView(R.id.dece)
    TextView dece;
    @BindView(R.id.next)
    TextView next;
    @BindView(R.id.answerLin)
    LinearLayout answerLin;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.answerF)
    ImageView answerF;
    @BindView(R.id.answerImg)
    ImageView answerImg;

    MusicUntil musicUntil = new MusicUntil();

    @Override
    public void onDestroy() {
        super.onDestroy();
        musicUntil.canlce();
    }

    @Override
    public void startShow() {
        dui.setMax(100);
        cuo.setMax(100);
        cuo.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (isAnswer()) {
                    return;
                }
                if (seekBar.getProgress() < 35) {
                    answerF.setImageResource(R.mipmap.cuod);
                    cuo.setVisibility(View.GONE);
                    answerLin.setVisibility(View.VISIBLE);
                    if (getBean().getAnswer().get(0).equals("1")) {
                        setAnswerIsRight(false);
                        righOrWrong.setText("回答错误");
                        righOrWrong.setTextColor(getResources().getColor(R.color.red));
                        musicUntil.playRaw(getContext(), R.raw.wrong);
                        getBaseTiMuActivity().setFen(getBaseTiMuActivity().getFen() - 1);
                    } else {
                        setAnswerIsRight(true);
                        musicUntil.playRaw(getContext(), R.raw.righten);
                        righOrWrong.setText("回答正确");
                        righOrWrong.setTextColor(getResources().getColor(R.color.green));
                    }
                    setAnswer(true);
                } else {
                    cuo.setProgress(90);
                }

            }
        });

        dui.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (isAnswer()) {
                    return;
                }
                if (seekBar.getProgress() > 65) {
                    answerF.setImageResource(R.mipmap.duid);
                    dui.setVisibility(View.GONE);
                    answerLin.setVisibility(View.VISIBLE);
                    if (getBean().getAnswer().get(0).equals("0")) {
                        setAnswerIsRight(false);
                        musicUntil.playRaw(getContext(), R.raw.wrong);
                        righOrWrong.setText("回答错误");
                        righOrWrong.setTextColor(getResources().getColor(R.color.red));
                        getBaseTiMuActivity().setFen(getBaseTiMuActivity().getFen() - 1);
                    } else {
                        setAnswerIsRight(true);
                        musicUntil.playRaw(getContext(), R.raw.righten);
                        righOrWrong.setText("回答正确");
                        righOrWrong.setTextColor(getResources().getColor(R.color.green));
                    }
                    setAnswer(true);
                } else {
                    dui.setProgress(10);
                }
            }
        });


        getBean().setQuestion_content(getBean().getQuestion_content().replace("\\n", "\n"));
        title.setText(getBean().getQuestion_content());
        dece.setText(getBean().getDesc());
        if (getBean().getAnswer().equals("0")) {
            answerImg.setImageResource(R.mipmap.cuod);
        } else {
            answerImg.setImageResource(R.mipmap.duid);
        }

    }

    @Override
    public int setlayoutResID() {
        return R.layout.fragment_bian_yin;
    }


    @OnClick({R.id.moudlRel, R.id.next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.moudlRel:
                break;
            case R.id.next:
                //*切换到下一题*//
                if (isAnswer()) {
                    nextItem();
                }
                break;
        }
    }
}
