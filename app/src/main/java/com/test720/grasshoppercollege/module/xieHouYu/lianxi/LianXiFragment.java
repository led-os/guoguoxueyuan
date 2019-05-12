package com.test720.grasshoppercollege.module.xieHouYu.lianxi;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.BaseTiMuOkFragment;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.publicQuestion.AnswerDialog;
import com.test720.grasshoppercollege.untils.HttpUntil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 水东流 on 2018/5/16.
 */

public class LianXiFragment extends BaseTiMuOkFragment {

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
    LianXIData.DataBean dataBean;
    AnswerDialog danCiTiMuAnswerDialog;


    @Override
    public void initData() {

    }

    public void toBig(View view) {
        view.setPivotX(view.getWidth() / 2);  // X方向中点
        view.setPivotY(view.getHeight() / 2);   // Y方向底边
        AnimatorSet animatorSet = new AnimatorSet();  //组合动画
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1f, 1.2f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1f, 1.2f, 1f);
        animatorSet.setDuration(1000);  //动画时间
        animatorSet.setInterpolator(new DecelerateInterpolator());  //设置插值器
        animatorSet.play(scaleX).with(scaleY);  //同时执行
        animatorSet.start();  //启动动画
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDataBean() != null) {
            question.setText(getDataBean().getQuestion_content());
            if (getDataBean().getSelect_a() != null) {
                String a = "A " + getDataBean().getSelect_b();
                textA.setText(a);
                textA.setVisibility(View.VISIBLE);
            } else {
                textA.setVisibility(View.GONE);
            }
            if (getDataBean().getSelect_b() != null) {
                String b = "B " + getDataBean().getSelect_b();
                textB.setText(b);
                textB.setVisibility(View.VISIBLE);
            } else {
                textB.setVisibility(View.GONE);
            }
            if (getDataBean().getSelect_c() != null) {
                String c = "C  " + getDataBean().getSelect_c();
                textC.setText(c);
                textC.setVisibility(View.VISIBLE);
            } else {
                textC.setVisibility(View.GONE);
            }
            if (getDataBean().getSelect_d() != null) {
                String d = "D  " + getDataBean().getSelect_d();
                textD.setText(d);
                textD.setVisibility(View.VISIBLE);
            } else {
                textD.setVisibility(View.GONE);
            }
        }

    }

    @Override
    public int setlayoutResID() {
        return R.layout.xie_hou_lian_xi_fragment;
    }

    @Override
    public void TongGuan() {
        HttpParams httpParams1 = new HttpParams();
        httpParams1.put("uid", MyApplication.getmInstance().getUid());
        httpParams1.put("fraction", getBaseTiMuActivity().getFen());
        httpParams1.put("xhy_id", getBaseTiMuActivity().getId());
        httpParams1.put("type", getBaseTiMuActivity().getType());
        post(HttpUntil.GetIntent().ChineseXieHouYuadopt(), httpParams1, 124);
    }

    @Override
    public String GXShareTitile() {
        return "歇后语练习";
    }

    @Override
    public String corrId() {
        return getDataBean().getCorr_id();
    }

    @Override
    public String questionId() {
        return getDataBean().getQuestion_id();
    }

    public LianXIData.DataBean getDataBean() {
        return dataBean;
    }

    public void setDataBean(LianXIData.DataBean dataBean) {
        this.dataBean = dataBean;
    }


    /*答题*/
    private void answer(String str) {
        if (isAnswer) return;
        if (str.equals(getDataBean().getAnswer())) {
            setAnswerIsRight(true);
        } else {
            setAnswerIsRight(false);
            getBaseTiMuActivity().setFen(getBaseTiMuActivity().getFen() - 1);
        }
        isAnswer = true;
        danCiTiMuAnswerDialog = new AnswerDialog();
        String a = getDataBean().getAnswer();
        danCiTiMuAnswerDialog.setAnswerTxt(a);
        danCiTiMuAnswerDialog.setDeceTxt(getDataBean().getDesc());
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
                toBig(textA);
                break;
            case R.id.text_b:
                answer("B");
                toBig(textC);
                break;
            case R.id.text_c:
                answer("C");
                toBig(textC);
                break;
            case R.id.text_d:
                answer("D");
                toBig(textD);
                break;
        }
    }
}
