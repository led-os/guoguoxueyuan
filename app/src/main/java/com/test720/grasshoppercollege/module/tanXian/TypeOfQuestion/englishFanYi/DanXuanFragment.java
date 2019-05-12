package com.test720.grasshoppercollege.module.tanXian.TypeOfQuestion.englishFanYi;

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
 * 作者：水东流 编于 2018/11/10
 */
public class DanXuanFragment extends BaseTanXianTiMuFragment {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.a)
    TextView a;
    @BindView(R.id.b)
    TextView b;
    @BindView(R.id.c)
    TextView c;
    @BindView(R.id.d)
    TextView d;

    String myAnswer = "";//我的答案

    @Override
    public void startShow() {
        if (getBean().getSelect_a() != null) {
            a.setText("A " + getBean().getSelect_a());
            a.setVisibility(View.VISIBLE);
        } else {
            a.setVisibility(View.GONE);
        }
        if (getBean().getSelect_b() != null) {
            b.setText("B " + getBean().getSelect_b());
            b.setVisibility(View.VISIBLE);
        } else {
            b.setVisibility(View.GONE);
        }
        if (getBean().getSelect_c() != null) {
            c.setText("C  " + getBean().getSelect_c());
            c.setVisibility(View.VISIBLE);
        } else {
            c.setVisibility(View.GONE);
        }
        if (getBean().getSelect_d() != null) {
            d.setText("D  " + getBean().getSelect_d());
            d.setVisibility(View.VISIBLE);
        } else {
            d.setVisibility(View.GONE);
        }
        String q = "";
        if (getBean().getQuestion() != null) q = getBean().getQuestion();
        if (getBean().getQuestion_content() != null) q = q + "\n" + getBean().getQuestion_content();
        title.setText(q);
    }

    @Override
    public int setlayoutResID() {
        return R.layout.fan_yi_dan_xuan_frament;
    }


    @OnClick({R.id.a, R.id.b, R.id.c, R.id.d})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.a:
                huiDa(a);
                break;
            case R.id.b:
                huiDa(b);
                break;
            case R.id.c:
                huiDa(c);
                break;
            case R.id.d:
                huiDa(d);
                break;
        }
    }

    /*给出答案*/
    private void huiDa(TextView textview) {
        if (isAnswer()) {
            return;
        }
        a.setBackgroundResource(R.drawable.dack_bian_kaung);
        b.setBackgroundResource(R.drawable.dack_bian_kaung);
        c.setBackgroundResource(R.drawable.dack_bian_kaung);
        d.setBackgroundResource(R.drawable.dack_bian_kaung);

        switch (textview.getId()) {
            case R.id.a:
                myAnswer = "A";
                break;
            case R.id.b:
                myAnswer = "B";
                break;
            case R.id.c:
                myAnswer = "C";
                break;
            case R.id.d:
                myAnswer = "D";
                break;
        }

        if (getBean().getAnswer().get(0).equals(myAnswer)) {//正确
            setAnswerIsRight(true);
            textview.setBackgroundResource(R.drawable.light_bule_back);
        } else {//错误
            setAnswerIsRight(false);
            textview.setBackgroundResource(R.drawable.red_light_back);
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
