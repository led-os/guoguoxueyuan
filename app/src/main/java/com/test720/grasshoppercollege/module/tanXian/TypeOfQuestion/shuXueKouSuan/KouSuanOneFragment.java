package com.test720.grasshoppercollege.module.tanXian.TypeOfQuestion.shuXueKouSuan;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
public class KouSuanOneFragment extends BaseTanXianTiMuFragment {
    StringBuffer stringBuffer = new StringBuffer();
    @BindView(R.id.quesitionImg)
    ImageView quesitionImg;
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
                quesitionImg.setVisibility(View.VISIBLE);
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
            ok.setTextSize(20);
            ok.setText("下一题");
            /*如果是订正模式，则订正*/
            if (stringBuffer.toString().equals(getBean().getAnswer().get(0))) {
                rightOrWrong.setText("回答正确");
                setAnswerIsRight(true);
                musicUntil.playRaw(getContext(), R.raw.righten);
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


    @OnClick({R.id.fenhao, R.id.dian, R.id.dele, R.id.ok, R.id.one, R.id.two, R.id.three, R.id.four, R.id.five, R.id.six, R.id.seven, R.id.eight, R.id.nine, R.id.zero})
    public void onViewClicked(View view) {
        if (isAnswer() && view.getId() != R.id.ok) return;
        switch (view.getId()) {
            case R.id.fenhao:
                if (stringBuffer.length() > 0 && stringBuffer.indexOf(".") == -1 && stringBuffer.indexOf("/") == -1) {
                    stringBuffer.append("/");
                    answer.setText(stringBuffer.toString());
                }
                break;

            case R.id.dian:
                if (stringBuffer.length() > 0 && stringBuffer.indexOf(".") == -1 && stringBuffer.indexOf("/") == -1) {
                    stringBuffer.append(".");
                    answer.setText(stringBuffer.toString());
                }
                if (stringBuffer.length() == 0) {
                    stringBuffer.append("0.");
                    answer.setText(stringBuffer.toString());
                }
                break;
            case R.id.dele:
                if (stringBuffer.length() > 0) {
                    stringBuffer.deleteCharAt(stringBuffer.length() - 1);
                    answer.setText(stringBuffer.toString());
                }
                break;
            case R.id.ok:
                Toanswer();
                break;
            case R.id.one:
                stringBuffer.append("1");
                answer.setText(stringBuffer.toString());
                break;
            case R.id.two:
                stringBuffer.append("2");
                answer.setText(stringBuffer.toString());
                break;
            case R.id.three:
                stringBuffer.append("3");
                answer.setText(stringBuffer.toString());
                break;
            case R.id.four:
                stringBuffer.append("4");
                answer.setText(stringBuffer.toString());
                break;
            case R.id.five:
                stringBuffer.append("5");
                answer.setText(stringBuffer.toString());
                break;
            case R.id.six:
                stringBuffer.append("6");
                answer.setText(stringBuffer.toString());
                break;
            case R.id.seven:
                stringBuffer.append("7");
                answer.setText(stringBuffer.toString());
                break;
            case R.id.eight:
                stringBuffer.append("8");
                answer.setText(stringBuffer.toString());
                break;
            case R.id.nine:
                stringBuffer.append("9");
                answer.setText(stringBuffer.toString());
                break;
            case R.id.zero:
                stringBuffer.append("0");
                answer.setText(stringBuffer.toString());
                break;
        }
    }

    @Override
    public int setlayoutResID() {
//        return R.layout.kou_suan_ti_mu;
        return R.layout.tan_xian_kou_suan;
    }

    @BindView(R.id.question)
    TextView question;
    @BindView(R.id.answer)
    TextView answer;
    @BindView(R.id.rightOrWrong)
    TextView rightOrWrong;
    @BindView(R.id.dian)
    Button dian;
    @BindView(R.id.dele)
    Button dele;
    /* @BindView(R.id.okText)
     TextView okText;*/
    @BindView(R.id.ok)
    Button ok;
    @BindView(R.id.one)
    Button one;
    @BindView(R.id.two)
    Button two;
    @BindView(R.id.three)
    Button three;
    @BindView(R.id.four)
    Button four;
    @BindView(R.id.five)
    Button five;
    @BindView(R.id.six)
    Button six;
    @BindView(R.id.seven)
    Button seven;
    @BindView(R.id.eight)
    Button eight;
    @BindView(R.id.nine)
    Button nine;
    @BindView(R.id.zero)
    Button zero;
}
