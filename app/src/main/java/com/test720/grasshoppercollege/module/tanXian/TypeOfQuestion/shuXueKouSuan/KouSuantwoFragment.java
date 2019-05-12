package com.test720.grasshoppercollege.module.tanXian.TypeOfQuestion.shuXueKouSuan;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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
public class KouSuantwoFragment extends BaseTanXianTiMuFragment {
    StringBuffer stringBuffer = new StringBuffer();
    boolean isTop = true;
    MusicUntil musicUntil = new MusicUntil();

    @Override
    public void onDestroy() {
        super.onDestroy();
        musicUntil.canlce();
    }

    @Override
    public void startShow() {
        if (getBean() != null) {
            if (getBean().getQuestion_content_type().equals("2")) {
                Glide.with(getActivity()).load(getBean().getQuestion_content()).into(quesitionImg);
            } else {
                quesitionImg.setVisibility(View.GONE);
                question.setText(getBean().getQuestion_content());
            }
        }
    }

    /*回答*/
    private void Toanswer() {
        /*已经作答，则切换下一题*/
        if (!isAnswer()) {
            okText.setTextSize(20);
            okText.setText("下一题");
            /*如果是订正模式，则订正*/
            if (answerTop.getText().toString().equals(getBean().getAnswer().get(0))
                    && answerDown.getText().toString().equals(getBean().getAnswer().get(1))) {
                rightOrWrong.setText("回答正确");
                musicUntil.playRaw(getContext(), R.raw.righten);
                setAnswerIsRight(true);
            } else {
                setAnswerIsRight(false);
                rightOrWrong.setText("回答错误");
                musicUntil.playRaw(getContext(), R.raw.wrong);
                rightOrWrong.setTextColor(getResources().getColor(R.color.red));
                getBaseTiMuActivity().setFen(getBaseTiMuActivity().getFen() - 1);
            }

            setAnswer(true);
        } else {
            nextItem();
        }
    }


    @OnClick({R.id.answerTop, R.id.answerDown, R.id.dele, R.id.ok, R.id.one, R.id.two, R.id.three, R.id.four, R.id.five, R.id.six, R.id.seven, R.id.eight, R.id.nine, R.id.zero})
    public void onViewClicked(View view) {
        if (isAnswer() && view.getId() != R.id.ok) return;
        switch (view.getId()) {
            case R.id.answerDown:
                isTop = false;
                stringBuffer = new StringBuffer();
                answerDown.setBackgroundResource(R.color.bluebantoumingtwo);
                answerTop.setBackgroundResource(R.color.bantoumingblue);
                break;
            case R.id.answerTop:
                isTop = true;
                stringBuffer = new StringBuffer();
                answerTop.setBackgroundResource(R.color.bluebantoumingtwo);
                answerDown.setBackgroundResource(R.color.bantoumingblue);
                break;
            case R.id.dele:

                break;
            case R.id.ok:
                Toanswer();
                break;
            case R.id.one:
                stringBuffer.append("1");
                if (isTop) {
                    answerTop.setText(stringBuffer.toString());
                } else {
                    answerDown.setText(stringBuffer.toString());
                }
                break;
            case R.id.two:
                stringBuffer.append("2");
                if (isTop) {
                    answerTop.setText(stringBuffer.toString());
                } else {
                    answerDown.setText(stringBuffer.toString());
                }
                break;
            case R.id.three:
                stringBuffer.append("3");
                if (isTop) {
                    answerTop.setText(stringBuffer.toString());
                } else {
                    answerDown.setText(stringBuffer.toString());
                }
                break;
            case R.id.four:
                stringBuffer.append("4");
                if (isTop) {
                    answerTop.setText(stringBuffer.toString());
                } else {
                    answerDown.setText(stringBuffer.toString());
                }
                break;
            case R.id.five:
                stringBuffer.append("5");
                if (isTop) {
                    answerTop.setText(stringBuffer.toString());
                } else {
                    answerDown.setText(stringBuffer.toString());
                }
                break;
            case R.id.six:
                stringBuffer.append("6");
                if (isTop) {
                    answerTop.setText(stringBuffer.toString());
                } else {
                    answerDown.setText(stringBuffer.toString());
                }
                break;
            case R.id.seven:
                stringBuffer.append("7");
                if (isTop) {
                    answerTop.setText(stringBuffer.toString());
                } else {
                    answerDown.setText(stringBuffer.toString());
                }
                break;
            case R.id.eight:
                stringBuffer.append("8");
                if (isTop) {
                    answerTop.setText(stringBuffer.toString());
                } else {
                    answerDown.setText(stringBuffer.toString());
                }
                break;
            case R.id.nine:
                stringBuffer.append("9");
                if (isTop) {
                    answerTop.setText(stringBuffer.toString());
                } else {
                    answerDown.setText(stringBuffer.toString());
                }
                break;
            case R.id.zero:
                stringBuffer.append("0");
                if (isTop) {
                    answerTop.setText(stringBuffer.toString());
                } else {
                    answerDown.setText(stringBuffer.toString());
                }
                break;
        }
    }

    @BindView(R.id.question)
    TextView question;
    @BindView(R.id.quesitionImg)
    ImageView quesitionImg;
    @BindView(R.id.answerTop)
    TextView answerTop;
    @BindView(R.id.answerDown)
    TextView answerDown;
    @BindView(R.id.rightOrWrong)
    TextView rightOrWrong;
    @BindView(R.id.dele)
    RelativeLayout dele;
    @BindView(R.id.okText)
    TextView okText;
    @BindView(R.id.ok)
    RelativeLayout ok;
    @BindView(R.id.one)
    RelativeLayout one;
    @BindView(R.id.two)
    RelativeLayout two;
    @BindView(R.id.three)
    RelativeLayout three;
    @BindView(R.id.four)
    RelativeLayout four;
    @BindView(R.id.five)
    RelativeLayout five;
    @BindView(R.id.six)
    RelativeLayout six;
    @BindView(R.id.seven)
    RelativeLayout seven;
    @BindView(R.id.eight)
    RelativeLayout eight;
    @BindView(R.id.nine)
    RelativeLayout nine;
    @BindView(R.id.zero)
    RelativeLayout zero;

    @Override
    public int setlayoutResID() {
        return R.layout.kou_suan_two_ti_mu;
    }
}
