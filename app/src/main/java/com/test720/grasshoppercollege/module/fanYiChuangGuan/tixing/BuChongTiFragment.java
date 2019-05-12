package com.test720.grasshoppercollege.module.fanYiChuangGuan.tixing;

import android.text.InputType;
import android.widget.TextView;

import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.bean.RangBean;
import com.test720.grasshoppercollege.myViews.EditableTextView;
import com.test720.grasshoppercollege.publicQuestion.AnswerDialog;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Lenovo on 2018/4/2.
 */

public class BuChongTiFragment extends BaseFanYiTiFragment {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.answer)
    EditableTextView answer;


    @Override
    public void initData() {

    }

    @Override
    public void onStart() {
        super.onStart();
        answer.getInput().setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        if (bean != null) {
            /*标题*/
            if (bean.getQuestion() != null) {
                title.setText(bean.getQuestion());
            }
            /*补充空*/
            if (bean.getQuestion_content() != null) {
                String s = bean.getQuestion_content();
                ArrayList<RangBean> ranges = new ArrayList<>();
                for (int i = 0; i < bean.getSubscript().size(); i++) {
                    int t = Integer.parseInt(bean.getSubscript().get(i));
                    ranges.add(new RangBean(t, t + 4));
                }
                answer.setData(s, ranges);
            }
            /*答案*/
        }
    }


    @Override
    public void TongGuan() {

    }

    @Override
    public int setlayoutResID() {
        return R.layout.fragment_bu_chong_ti;
    }

    @Override
    public void getSuccess(String s, int what) {

    }

    @OnClick(R.id.ok)
    public void onViewClicked() {
        answerOk();
    }

    /*回答*/
    private void answerOk() {
        if (answer.getAnswers() == null || answer.getAnswers().size() < bean.getSubscript().size()) {
            ShowToast("请做出您的答案！");
            return;
        }
        if (isAnswer) {
            return;
        }
        /*显示答案*/

        for (int i = 0; i < answer.getAnswers().size(); i++) {
            if (bean.getAnswer1().size() < i) {
                ShowToast("答案题目不一致，请联系管理员");
                break;
            }
            /*判断正确还是错误*/
            /*不区分大小写和空格*/
            String m = answer.getAnswers().get(i);
            String q = bean.getAnswer1().get(i);
            m = m.toLowerCase();
            q = q.toLowerCase();
            m = m.replaceAll(" ", "");
            q = q.replaceAll(" ", "");
            if (!m.equals(q)) {
                answerIsRight = false;
                break;
            }
        }
        if (!answerIsRight) {
            getBaseTiMuActivity().setFen(getBaseTiMuActivity().getFen() - 1);
        }
        isAnswer = true;
        AnswerDialog danCiTiMuAnswerDialog = new AnswerDialog();
        String a = bean.getAnswer();
        danCiTiMuAnswerDialog.setAnswerTxt(a);
        danCiTiMuAnswerDialog.setDeceTxt(bean.getDesc());
        danCiTiMuAnswerDialog.setRight(answerIsRight);
        danCiTiMuAnswerDialog.setAnswerInterface(new AnswerDialog.AnswerInterface() {
            @Override
            public void nextClick() {
                if (isAnswer) {
                    if (getViewPager().getAdapter() != null && getViewPager().getCurrentItem() < getViewPager().getAdapter().getCount() - 1) {
                        getViewPager().setCurrentItem(getViewPager().getCurrentItem() + 1);
                    } else {
                        AddGuoGuoDou();
                    }
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
                return R.mipmap.jiexit;
            }
        });
        assert getFragmentManager() != null;
        danCiTiMuAnswerDialog.show(getFragmentManager(), "answer");
    }

}
