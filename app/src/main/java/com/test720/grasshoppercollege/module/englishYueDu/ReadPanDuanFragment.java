package com.test720.grasshoppercollege.module.englishYueDu;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.publicQuestion.AnswerDialog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 水东流 on 2018/6/25.
 */

public class ReadPanDuanFragment extends readTimuFragment {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.myAnswerF)
    ImageView myAnswerF;
    @BindView(R.id.right)
    RelativeLayout right;
    @BindView(R.id.myAnswerT)
    ImageView myAnswerT;
    @BindView(R.id.wrong)
    RelativeLayout wrong;


    @Override
    public void initData() {

    }

    @Override
    public void onStart() {
        super.onStart();
        if (getQuestionBean() != null) {
            title.setText(getQuestionBean().getQuestion_content());
        }
    }


    @Override
    public void setListener() {

    }

    @Override
    public int setlayoutResID() {
        return R.layout.read_pan_duan;
    }

    @OnClick({R.id.duanwen, R.id.right, R.id.wrong})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.duanwen:
                Intent intent = new Intent(getActivity(), ReadTextActivity.class);
                intent.putExtra("read_id", getBaseTiMuActivity().getId());
                jumpToActivity(intent, false);
                break;
            case R.id.right:
                myAnswer(true);
                break;
            case R.id.wrong:
                myAnswer(false);
                break;
        }
    }

    private void myAnswer(boolean b) {
        if (isAnswer()) return;
        if (getQuestionBean().getAnswer().equals("1")) {
            answerIsRight = b;
        }
        if (getQuestionBean().getAnswer().equals("0")) {
            answerIsRight = !b;
        }
        /*错一题，总分减一*/
        if (!answerIsRight) {
            getBaseTiMuActivity().setFen(getBaseTiMuActivity().getFen() - 1);
        }
        if (b) {
            myAnswerF.setVisibility(View.VISIBLE);
            if (answerIsRight) {
                myAnswerF.setImageResource(R.mipmap.duifor);
            } else {
                myAnswerF.setImageResource(R.mipmap.hongcha);
            }
        }

        if (!b) {
            myAnswerT.setVisibility(View.GONE);
            if (answerIsRight) {
                myAnswerT.setImageResource(R.mipmap.duifor);
            } else {
                myAnswerT.setImageResource(R.mipmap.hongcha);
            }
        }
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
