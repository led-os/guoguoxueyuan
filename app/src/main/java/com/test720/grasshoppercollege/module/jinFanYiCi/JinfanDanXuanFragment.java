package com.test720.grasshoppercollege.module.jinFanYiCi;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.BaseTiMuOkFragment;
import com.test720.grasshoppercollege.HttpBean.JInFanYITiMuData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.publicQuestion.AnswerDialog;
import com.test720.grasshoppercollege.untils.HttpUntil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 水东流 on 2018/5/15.
 */

public class JinfanDanXuanFragment extends BaseTiMuOkFragment {
    @BindView(R.id.quesition)
    TextView quesition;
    @BindView(R.id.text_a)
    TextView textA;
    @BindView(R.id.text_b)
    TextView textB;
    @BindView(R.id.text_c)
    TextView textC;
    @BindView(R.id.text_d)
    TextView textD;

    JInFanYITiMuData.DataBean dataBean;
    private AnswerDialog danCiTiMuAnswerDialog;

    @Override
    public void initData() {

    }

    @Override
    public int setlayoutResID() {
        return R.layout.jin_fan_dan_xuan_fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDataBean() == null) {
            return;
        }
        String a = "A：" + getDataBean().getSelect_a();
        String b = "B：" + getDataBean().getSelect_b();
        String c = "C：" + getDataBean().getSelect_c();
        String d = "D：" + getDataBean().getSelect_d();
        textA.setText(a);
        textB.setText(b);
        textC.setText(c);
        textD.setText(d);
        quesition.setText(getDataBean().getQuestion_content());

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

    @OnClick({R.id.text_a, R.id.text_b, R.id.text_c, R.id.text_d})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text_a:
                answer("A");
                toBig(textA);
                break;
            case R.id.text_b:
                answer("B");
                toBig(textB);
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
        if (danCiTiMuAnswerDialog != null) return;
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


    @Override
    public void TongGuan() {
        HttpParams httpParams1 = new HttpParams();
        httpParams1.put("uid", MyApplication.getmInstance().getUid());
        httpParams1.put("fraction", getBaseTiMuActivity().getFen());
        httpParams1.put("jf_id", getBaseTiMuActivity().getId());
        post(HttpUntil.GetIntent().ChineseJFadopt(), httpParams1, 124);

    }

    @Override
    public String GXShareTitile() {
        return "近反义词";
    }

    @Override
    public String corrId() {
        return getDataBean().getCorr_id();
    }

    @Override
    public String questionId() {
        return getDataBean().getQuestion_id();
    }

    public JInFanYITiMuData.DataBean getDataBean() {
        return dataBean;
    }

    public void setDataBean(JInFanYITiMuData.DataBean dataBean) {
        this.dataBean = dataBean;
    }
}
