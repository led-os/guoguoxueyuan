package com.test720.grasshoppercollege.module.tanXian.TypeOfQuestion.XieHouYuLianXi;

import android.view.View;
import android.widget.TextView;

import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.tanXian.TypeOfQuestion.BaseTanXianTiMuFragment;
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
public class XieHouYuLianXiFragment extends BaseTanXianTiMuFragment {
    @BindView(R.id.guanNum)
    TextView guanNum;
    @BindView(R.id.question)
    TextView question;
    @BindView(R.id.text_a)
    TextView textA;
    @BindView(R.id.text_b)
    TextView textB;
    @BindView(R.id.text_c)
    TextView textC;
    @BindView(R.id.text_d)
    TextView textD;
    AnswerDialog danCiTiMuAnswerDialog;

    @Override
    public void startShow() {
        if (getBean() != null) {
            question.setText(getBean().getQuestion_content());
            if (getBean().getSelect_a() != null) {
                String a = "A " + getBean().getSelect_a();
                textA.setText(a);
                textA.setVisibility(View.VISIBLE);
            } else {
                textA.setVisibility(View.GONE);
            }
            if (getBean().getSelect_b() != null) {
                String b = "B " + getBean().getSelect_b();
                textB.setText(b);
                textB.setVisibility(View.VISIBLE);
            } else {
                textB.setVisibility(View.GONE);
            }
            if (getBean().getSelect_c() != null) {
                String c = "C  " + getBean().getSelect_c();
                textC.setText(c);
                textC.setVisibility(View.VISIBLE);
            } else {
                textC.setVisibility(View.GONE);
            }
            if (getBean().getSelect_d() != null) {
                String d = "D  " + getBean().getSelect_d();
                textD.setText(d);
                textD.setVisibility(View.VISIBLE);
            } else {
                textD.setVisibility(View.GONE);
            }
        }
    }

    /*答题*/
    private void answer(String str) {
        if (isAnswer()) return;
        if (str.equals(getBean().getAnswer().get(0))) {
            setAnswerIsRight(true);
        } else {
            setAnswerIsRight(false);
            getBaseTiMuActivity().setFen(getBaseTiMuActivity().getFen() - 1);
        }
        setAnswer(true);
        danCiTiMuAnswerDialog = new AnswerDialog();
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
                return null;
            }

            @Override
            public int nextBack() {
                return -1;
            }

            @Override
            public int linBack() {
                return R.mipmap.jiexikuang;
            }
        });
        assert getFragmentManager() != null;
        danCiTiMuAnswerDialog.show(getFragmentManager(), "answer");
    }


    @OnClick({R.id.text_a, R.id.text_b, R.id.text_c, R.id.text_d})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text_a:
                answer("A");
                break;
            case R.id.text_b:
                answer("B");
                break;
            case R.id.text_c:
                answer("C");
                break;
            case R.id.text_d:
                answer("D");
                break;
        }
    }

    @Override
    public int setlayoutResID() {
        return R.layout.xie_hou_lian_xi_fragment;
    }
}
