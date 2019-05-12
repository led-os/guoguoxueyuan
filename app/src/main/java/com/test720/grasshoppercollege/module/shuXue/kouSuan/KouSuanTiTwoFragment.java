package com.test720.grasshoppercollege.module.shuXue.kouSuan;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.musicMedia.MusicUntil;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by 水东流 on 2018/7/27.
 */

public class KouSuanTiTwoFragment extends KouSuanTiMufragment {
    StringBuffer stringBuffer = new StringBuffer();
    boolean isTop = true;
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
    public void onStart() {
        super.onStart();
        if (getListBean() != null) {
            if (getListBean().getQuestion_content_type().equals("2")) {
                Glide.with(getActivity()).load(getListBean().getQuestion_content()).into(quesitionImg);
            } else {
                quesitionImg.setVisibility(View.GONE);
                question.setText(getListBean().getQuestion_content());
            }
        }
    }


    @Override
    public void initData() {

    }

    @Override
    public int setlayoutResID() {
        return R.layout.kou_suan_two_ti_mu;
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
            okText.setTextSize(20);
            okText.setText("下一题");
            /*如果是订正模式，则订正*/
            if (answerTop.getText().toString().equals(getListBean().getAnswer().get(0))
                    && answerDown.getText().toString().equals(getListBean().getAnswer().get(1))) {
                rightOrWrong.setText("回答正确");
                musicUntil.playRaw(getContext(), R.raw.righten);
                setAnswerIsRight(true);
                getBaseTiMuActivity().setFen(getBaseTiMuActivity().getFen() + 1);
            } else {
                setAnswerIsRight(false);
                rightOrWrong.setText("回答错误");
                musicUntil.playRaw(getContext(), R.raw.wrong);
                rightOrWrong.setTextColor(getResources().getColor(R.color.red));
            }

            if (kouSuanTiMuActivity.getTimeInt() == 0) {
                ShowToast("考核时间到！");
                return;
            }
            isAnswer = true;
        }
    }


    @OnClick({R.id.answerTop, R.id.answerDown, R.id.dele, R.id.ok, R.id.one, R.id.two, R.id.three, R.id.four, R.id.five, R.id.six, R.id.seven, R.id.eight, R.id.nine, R.id.zero})
    public void onViewClicked(View view) {
        if (isAnswer && view.getId() != R.id.ok) return;
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
    Unbinder unbinder;
}
