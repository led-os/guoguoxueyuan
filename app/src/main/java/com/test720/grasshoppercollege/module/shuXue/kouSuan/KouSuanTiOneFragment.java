package com.test720.grasshoppercollege.module.shuXue.kouSuan;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.musicMedia.MusicUntil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 水东流 on 2018/7/27.
 */

public class KouSuanTiOneFragment extends KouSuanTiMufragment {
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
    public void TongGuan() {

    }

    @Override
    public String GXShareTitile() {
        return "口算练习";
    }

    @Override
    public String corrId() {
        return getListBean().getCorr_id();
    }

    @Override
    public String questionId() {
        return getListBean().getQuestion_id();
    }

    @Override
    public void initData() {

    }

    @Override
    public void onStart() {
        super.onStart();
        if (getListBean() != null) {
            if (getListBean().getQuestion_content_type().equals("2")) {
                quesitionImg.setVisibility(View.VISIBLE);
                Glide.with(getActivity()).load(getListBean().getQuestion_content()).into(quesitionImg);
            } else {
                quesitionImg.setVisibility(View.GONE);
                question.setText(getListBean().getQuestion_content());
            }
        }
    }

    @Override
    public int setlayoutResID() {
//        return R.layout.kou_suan_ti_mu;
        return R.layout.kou_suan_biao_ge;
    }

    /*回答*/
    private void Toanswer() {
        KouSuanTiMuActivity kouSuanTiMuActivity = (KouSuanTiMuActivity) getBaseTiMuActivity();
        if (!kouSuanTiMuActivity.isStartplay()) {
            ShowToast("点击“开始”按钮，进行口算练习哦！");
            return;
        }
        /*已经作答，则切换下一题*/
        if (isAnswer) {
            if (getViewPager().getCurrentItem() < getViewPager().getAdapter().getCount() - 1) {
                if (kouSuanTiMuActivity.getTimeInt() == 0) {
                    ShowToast("考核时间到！");
                    return;
                }
                getViewPager().setCurrentItem(getViewPager().getCurrentItem() + 1);
            } else {
                AddGuoGuoDou();
            }
        } else {
//            okText.setTextSize(20);
            ok.setTextSize(20);
//            okText.setText("下一题");
            ok.setText("下一题");
            /*如果是订正模式，则订正*/
            if (stringBuffer.toString().equals(getListBean().getAnswer().get(0))) {
                rightOrWrong.setText("回答正确");
                musicUntil.playRaw(getContext(), R.raw.righten);
                setAnswerIsRight(true);
                getBaseTiMuActivity().setFen(getBaseTiMuActivity().getFen() + 1);
            } else {
                setAnswerIsRight(false);
                rightOrWrong.setText("回答错误");
                musicUntil.playRaw(getContext(), R.raw.wrong);
                rightOrWrong.setTextColor(getResources().getColor(R.color.red));
                if (kouSuanTiMuActivity.getTimeInt() == 0) {
                    ShowToast("考核时间到！");
                    return;
                }
            }
            isAnswer = true;
        }

    }


    @OnClick({R.id.fenhao, R.id.dian, R.id.dele, R.id.ok, R.id.one, R.id.two, R.id.three, R.id.four, R.id.five, R.id.six, R.id.seven, R.id.eight, R.id.nine, R.id.zero})
    public void onViewClicked(View view) {
        if (isAnswer && view.getId() != R.id.ok) return;
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
    /*  @BindView(R.id.okText)
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
