package com.test720.grasshoppercollege.module.tanXian.TypeOfQuestion.englishDanCiTingXie;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
public class DanXuanFragment extends BaseTanXianTiMuFragment {
    String myAnswer = "";//我的答案

    @Override
    public void startShow() {
        if (getBean() != null) {
            if (getBean().getVoice().equals("")) {
                video.setVisibility(View.GONE);
            } else {
                video.setVisibility(View.VISIBLE);
            }
            if (!getBean().getQuestion_content().equals("")) {
                String question = getBean().getQuestion_content().replace("\\n", "\n");
                en.setText(question);
            }
            if (getBean().getSelect_a() != null) {
                textA.setText(getBean().getSelect_a());
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

   /* @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            play(laba);
        } else {
            stop();
        }
    }*/

    @Override
    public void play() {
        play(laba);
    }

    @OnClick({R.id.video, R.id.text_a, R.id.text_b, R.id.text_c, R.id.text_d})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.video:
                play(laba);
                break;
            case R.id.text_a:
                huiDa(textA);
                break;
            case R.id.text_b:
                huiDa(textB);
                break;
            case R.id.text_c:
                huiDa(textC);
                break;
            case R.id.text_d:
                huiDa(textD);
                break;

        }
    }

    /*给出答案*/
    private void huiDa(TextView textview) {
        if (isAnswer()) return;
        switch (textview.getId()) {
            case R.id.text_a:
                myAnswer = "A";
                break;
            case R.id.text_b:
                myAnswer = "B";
                break;
            case R.id.text_c:
                myAnswer = "C";
                break;
            case R.id.text_d:
                myAnswer = "D";
                break;
        }
        if (getBean().getAnswer().get(0).equals(myAnswer)) {//正确
            setAnswerIsRight(true);
            textview.setTextColor(getResources().getColor(R.color.green));
            Drawable drawable1 = getResources().getDrawable(R.mipmap.dui);
/// 这一步必须要做,否则不会显示.
            drawable1.setBounds(0, 0, 50, 50);
            textview.setCompoundDrawables(null, null, drawable1, null);
        } else {//错误
            setAnswerIsRight(false);
            textview.setTextColor(getResources().getColor(R.color.red));
            Drawable drawable1 = getResources().getDrawable(R.mipmap.cuo);
/// 这一步必须要做,否则不会显示.
            drawable1.setBounds(0, 0, 80, 80);
            textview.setCompoundDrawables(null, null, drawable1, null);
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


    @BindView(R.id.en)
    TextView en;
    @BindView(R.id.video)
    LinearLayout video;
    @BindView(R.id.text_a)
    TextView textA;
    @BindView(R.id.text_b)
    TextView textB;
    @BindView(R.id.text_c)
    TextView textC;
    @BindView(R.id.text_d)
    TextView textD;
    @BindView(R.id.laba)
    ImageView laba;

    @Override
    public int setlayoutResID() {
        return R.layout.dan_ci_ting_xie_dan_xuan;
    }
}
