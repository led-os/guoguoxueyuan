package com.test720.grasshoppercollege.module.englishYueDu;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.publicQuestion.AnswerDialog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 水东流 on 2018/5/15.
 */

public class YueDuDanXuanFragment extends readTimuFragment {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.text_a)
    TextView textA;
    @BindView(R.id.text_b)
    TextView textB;
    @BindView(R.id.text_c)
    TextView textC;
    @BindView(R.id.text_d)
    TextView textD;


    @Override
    public void initData() {

    }

    @Override
    public void setListener() {

    }

    @Override
    public void onStart() {
        super.onStart();
        if (getQuestionBean() != null) {
            title.setText(getQuestionBean().getQuestion_content());
            textA.setText("A:   " + getQuestionBean().getSelect_a());
            textB.setText("B:   " + getQuestionBean().getSelect_b());
            textC.setText("C:   " + getQuestionBean().getSelect_c());
            textD.setText("D:   " + getQuestionBean().getSelect_d());
        }
    }


    @Override
    public int setlayoutResID() {
        return R.layout.yue_du_dan_xuan_fragment;
    }


    @OnClick({R.id.duanwen, R.id.text_a, R.id.text_b, R.id.text_c, R.id.text_d})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.duanwen:
                Intent intent = new Intent(getActivity(), ReadTextActivity.class);
                intent.putExtra("read_id", getBaseTiMuActivity().getId());
                jumpToActivity(intent, false);
                break;
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

        }
    }

    private void myAnswer(String a) {

        if (isAnswer) return;
        if (a.equals(getQuestionBean().getAnswer())) {
            answerIsRight = true;
        } else {
            answerIsRight = false;
            /*错一题，总分减一*/
            getBaseTiMuActivity().setFen(getBaseTiMuActivity().getFen() - 1);

        }
        switch (a) {
            case "A":
                if (answerIsRight) {
                    textA.setBackgroundResource(R.mipmap.duixuan);
                } else {
                    textA.setBackgroundResource(R.mipmap.cuox);
                }
                break;
            case "B":
                if (answerIsRight) {
                    textB.setBackgroundResource(R.mipmap.duixuan);
                } else {
                    textB.setBackgroundResource(R.mipmap.cuox);
                }
                break;
            case "C":
                if (answerIsRight) {
                    textC.setBackgroundResource(R.mipmap.duixuan);
                } else {
                    textC.setBackgroundResource(R.mipmap.cuox);
                }
                break;
            case "D":
                if (answerIsRight) {
                    textD.setBackgroundResource(R.mipmap.duixuan);
                } else {
                    textD.setBackgroundResource(R.mipmap.cuox);
                }
                break;
        }
        isAnswer = true;

        AnswerDialog danCiTiMuAnswerDialog = new AnswerDialog();
        String an = getQuestionBean().getAnswer();
        danCiTiMuAnswerDialog.setAnswerTxt(an);
        danCiTiMuAnswerDialog.setDeceTxt(getQuestionBean().getDesc());
        danCiTiMuAnswerDialog.setRight(answerIsRight);
        danCiTiMuAnswerDialog.setAnswerInterface(new AnswerDialog.AnswerInterface() {
            @Override
            public void nextClick() {
                if (getViewPager().getAdapter() == null) return;
                if (getViewPager().getCurrentItem() < getViewPager().getAdapter().getCount() - 1) {
                    getViewPager().setCurrentItem(getViewPager().getCurrentItem() + 1);
                } else {
                    AddGuoGuoDou();
                }
            }

            @Override
            public String nextTxt() {
                return null;
            }

            @Override
            public int nextBack() {
                return -1;
            }

            @Override
            public int linBack() {
                return R.mipmap.jxkuang3;
            }
        });
        assert getFragmentManager() != null;
        danCiTiMuAnswerDialog.show(getFragmentManager(), "answer");
    }


}
