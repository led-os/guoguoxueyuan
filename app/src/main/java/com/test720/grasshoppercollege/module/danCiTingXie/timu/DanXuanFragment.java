package com.test720.grasshoppercollege.module.danCiTingXie.timu;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.publicQuestion.AnswerDialog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 水东流 on 2018/5/11.
 */

public class DanXuanFragment extends BaseDanCiFragment {

    String myAnswer = "";//我的答案

    @Override
    public void initData() {

    }

    @Override
    public int setlayoutResID() {
        return R.layout.dan_ci_ting_xie_dan_xuan;
    }


    @Override
    public void onStart() {
        super.onStart();
        if (bean != null) {
            if (bean.getQuestion_type().equals("1")) {
                video.setVisibility(View.GONE);
                if (bean.getQuestion() != null) {
                    String question = bean.getQuestion_content().replace("\\n", "\n");
                    en.setText(question);
                }
            } else if (bean.getQuestion_type().equals("3")) {
                video.setVisibility(View.VISIBLE);
                if (bean.getQuestion() != null) {
                    String question = bean.getQuestion().replace("\\n", "\n");
                    en.setText(question);
                }
            }


            if (bean.getSelect_a() != null) {
                textA.setText(bean.getSelect_a());
                textA.setVisibility(View.VISIBLE);
            } else {
                textA.setVisibility(View.GONE);
            }

            if (bean.getSelect_b() != null) {
                String b = "B " + bean.getSelect_b();
                textB.setText(b);
                textB.setVisibility(View.VISIBLE);
            } else {
                textB.setVisibility(View.GONE);
            }

            if (bean.getSelect_c() != null) {
                String c = "C  " + bean.getSelect_c();
                textC.setText(c);
                textC.setVisibility(View.VISIBLE);
            } else {
                textC.setVisibility(View.GONE);
            }

            if (bean.getSelect_d() != null) {
                String d = "D  " + bean.getSelect_d();
                textD.setText(d);
                textD.setVisibility(View.VISIBLE);
            } else {
                textD.setVisibility(View.GONE);
            }

        }

    }

    @Override
    public ImageView laBa() {
        return laba;
    }

    @OnClick({R.id.video, R.id.text_a, R.id.text_b, R.id.text_c, R.id.text_d})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.video:
                play(bean.getQuestion_content());
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
        if (isAnswer) return;
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
        if (getBean().getAnswer().equals(myAnswer)) {//正确
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
        isAnswer = true;

        AnswerDialog danCiTiMuAnswerDialog = new AnswerDialog();
        String a = bean.getAnswer();
        danCiTiMuAnswerDialog.setAnswerTxt(a);
        danCiTiMuAnswerDialog.setDeceTxt(bean.getDesc());
        danCiTiMuAnswerDialog.setRight(answerIsRight);
        danCiTiMuAnswerDialog.setAnswerInterface(new AnswerDialog.AnswerInterface() {
            @Override
            public void nextClick() {
                stop();
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
                return R.mipmap.jiexikuang;
            }
        });
        assert getFragmentManager() != null;
        danCiTiMuAnswerDialog.show(getFragmentManager(), "answer");
    }


    @Override
    public void TongGuan() {

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
}
