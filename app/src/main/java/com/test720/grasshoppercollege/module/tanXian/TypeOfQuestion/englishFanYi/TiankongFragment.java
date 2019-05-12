package com.test720.grasshoppercollege.module.tanXian.TypeOfQuestion.englishFanYi;

import android.text.InputType;
import android.widget.TextView;

import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.bean.RangBean;
import com.test720.grasshoppercollege.module.tanXian.TypeOfQuestion.BaseTanXianTiMuFragment;
import com.test720.grasshoppercollege.myViews.EditableTextView;
import com.test720.grasshoppercollege.publicQuestion.AnswerDialog;

import java.util.ArrayList;

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
public class TiankongFragment extends BaseTanXianTiMuFragment {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.answer)
    EditableTextView answer;

    @Override
    public void startShow() {
        answer.getInput().setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        if (getBean() != null) {
            /*标题*/
            if (getBean().getQuestion() != null) {
                title.setText(getBean().getQuestion());
            }
            /*补充空*/
            if (getBean().getQuestion_content() != null) {
                String s = getBean().getQuestion_content();
                ArrayList<RangBean> ranges = new ArrayList<>();
                for (int i = 0; i < getBean().getSubscript().size(); i++) {
                    int t = Integer.parseInt(getBean().getSubscript().get(i));
                    ranges.add(new RangBean(t, t + 4));
                }
                answer.setData(s, ranges);
            }
            /*答案*/
        }
    }

    @Override
    public int setlayoutResID() {
        return R.layout.fragment_bu_chong_ti;
    }


    @OnClick(R.id.ok)
    public void onViewClicked() {
        answerOk();
    }

    /*回答*/
    private void answerOk() {
        if (isAnswer()) {
            return;
        }
        if (answer.getAnswers() == null || answer.getAnswers().size() < getBean().getSubscript().size()) {
            ShowToast("请做出您的答案！");
            return;
        }

        /*显示答案*/

        for (int i = 0; i < answer.getAnswers().size(); i++) {
            if (getBean().getAnswer().size() < i) {
                ShowToast("答案题目不一致，请联系管理员");
                break;
            }
            /*判断正确还是错误*/
            /*不区分大小写和空格*/
            String m = answer.getAnswers().get(i);
            String q = getBean().getAnswer().get(i);
            m = m.toLowerCase();
            q = q.toLowerCase();
            m = m.replaceAll(" ", "");
            q = q.replaceAll(" ", "");
            if (!m.equals(q)) {
                setAnswerIsRight(false);
                break;
            }
        }
        if (!isAnswerIsRight()) {
            getBaseTiMuActivity().setFen(getBaseTiMuActivity().getFen() - 1);
        }
        setAnswer(true);

        AnswerDialog danCiTiMuAnswerDialog = new AnswerDialog();
        String a = getBean().getAnswer().toString();
        danCiTiMuAnswerDialog.setAnswerTxt(a);
        danCiTiMuAnswerDialog.setDeceTxt(getBean().getDesc());
        danCiTiMuAnswerDialog.setRight(isAnswerIsRight());
        danCiTiMuAnswerDialog.setAnswerInterface(new AnswerDialog.AnswerInterface() {
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
        assert getFragmentManager() != null;
        danCiTiMuAnswerDialog.show(getFragmentManager(), "answer");
    }

}
