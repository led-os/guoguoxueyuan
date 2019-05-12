package com.test720.grasshoppercollege.module.fanYiChuangGuan.tixing;

import android.view.View;
import android.widget.TextView;

import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.publicQuestion.AnswerDialog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 水东流 on 2018/5/17.
 */

public class DanXuanFragment extends BaseFanYiTiFragment {

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
    public void initData() {

    }

    @Override
    public int setlayoutResID() {
        return R.layout.fan_yi_dan_xuan_frament;
    }


    @Override
    public void onStart() {
        super.onStart();
        if (bean != null) {
            if (bean.getSelect_a() != null) {
                a.setText("A " + bean.getSelect_a());
                a.setVisibility(View.VISIBLE);
            } else {
                a.setVisibility(View.GONE);
            }
            if (bean.getSelect_b() != null) {
                b.setText("B " + bean.getSelect_b());
                b.setVisibility(View.VISIBLE);
            } else {
                b.setVisibility(View.GONE);
            }
            if (bean.getSelect_c() != null) {
                c.setText("C  " + bean.getSelect_c());
                c.setVisibility(View.VISIBLE);
            } else {
                c.setVisibility(View.GONE);
            }
            if (bean.getSelect_d() != null) {
                d.setText("D  " + bean.getSelect_d());
                d.setVisibility(View.VISIBLE);
            } else {
                d.setVisibility(View.GONE);
            }
            String q = "";
            if (bean.getQuestion() != null) q = bean.getQuestion();
            if (bean.getQuestion_content() != null) q = q + "\n" + bean.getQuestion_content();
            title.setText(q);

        }

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
        if (isAnswer) {
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

        if (bean.getAnswer().equals(myAnswer)) {//正确
            setAnswerIsRight(true);
            textview.setBackgroundResource(R.drawable.light_bule_back);
        } else {//错误
            setAnswerIsRight(false);
            textview.setBackgroundResource(R.drawable.red_light_back);
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

    @Override
    public void TongGuan() {

    }
}
