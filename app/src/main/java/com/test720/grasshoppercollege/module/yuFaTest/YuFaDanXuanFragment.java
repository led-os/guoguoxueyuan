package com.test720.grasshoppercollege.module.yuFaTest;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test720.grasshoppercollege.BaseTiMuOkFragment;
import com.test720.grasshoppercollege.HttpBean.YuFaTextTiMuData;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.musicMedia.MusicUntil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 水东流 on 2018/5/14.
 */

public class YuFaDanXuanFragment extends BaseTiMuOkFragment {
    YuFaTextTiMuData.DataBean bean;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.text_a)
    TextView textA;
    @BindView(R.id.text_b)
    TextView textB;
    @BindView(R.id.text_c)
    TextView textC;
    @BindView(R.id.text_d)
    TextView textD;
    @BindView(R.id.answer)
    TextView answer;
    @BindView(R.id.righOrWrong)
    TextView righOrWrong;
    @BindView(R.id.dece)
    TextView dece;

    @BindView(R.id.answerLin)
    LinearLayout answerLin;
    String myAnswer = "";//我的答案
    MusicUntil musicUntil = new MusicUntil();


    public YuFaTextTiMuData.DataBean getDataBean() {
        return bean;
    }

    public void setDataBean(YuFaTextTiMuData.DataBean dataBean) {
        this.bean = dataBean;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        musicUntil.canlce();
    }

    @Override
    public void initData() {

    }


    @Override
    public int setlayoutResID() {
        return R.layout.yu_fa_dan_xuan;
    }


    @Override
    public String corrId() {
        return getDataBean().getCorr_id();
    }

    @Override
    public String questionId() {
        return getDataBean().getQuestion_id();
    }


    @Override
    public void TongGuan() {

    }

    @Override
    public String GXShareTitile() {
        return "语法练习";
    }


    @Override
    public void onStart() {
        super.onStart();
        if (bean != null) {
            if (bean.getSelect_a() != null) {
                textA.setText("A " + bean.getSelect_a());
                textA.setVisibility(View.VISIBLE);
            } else {
                textA.setVisibility(View.GONE);
            }
            if (bean.getSelect_b() != null) {
                textB.setText("B " + bean.getSelect_b());
                textB.setVisibility(View.VISIBLE);
            } else {
                textB.setVisibility(View.GONE);
            }
            if (bean.getSelect_c() != null) {
                textC.setText("C  " + bean.getSelect_c());
                textC.setVisibility(View.VISIBLE);
            } else {
                textC.setVisibility(View.GONE);
            }
            if (bean.getSelect_d() != null) {
                textD.setText("D  " + bean.getSelect_d());
                textD.setVisibility(View.VISIBLE);
            } else {
                textD.setVisibility(View.GONE);
            }
            if (bean.getQuestion() != null) title.setText(bean.getQuestion_select().get(0));
            if (bean.getDesc() != null) dece.setText(bean.getDesc());
        }

    }

    @OnClick({R.id.text_a, R.id.text_b, R.id.text_c, R.id.text_d, R.id.next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
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
            case R.id.next:
                if (!isAnswer) return;
                if (getViewPager().getAdapter().getCount() > getViewPager().getCurrentItem() + 1) {
                    getViewPager().setCurrentItem(getViewPager().getCurrentItem() + 1);
                } else {
                    AddGuoGuoDou();
                }
                break;
        }
    }

    private void huiDa(TextView textview) {
        if (isAnswer) return;
        answerLin.setVisibility(View.VISIBLE);
        String a = "正确答案：" + getDataBean().getAnswer().get(0);
        answer.setText(a);

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
        if (getDataBean().getAnswer().get(0).equals(myAnswer)) {//正确
            setAnswerIsRight(true);
            musicUntil.playRaw(getContext(), R.raw.righten);
            textview.setTextColor(getResources().getColor(R.color.green));
            righOrWrong.setText("回答正确");
            righOrWrong.setTextColor(getResources().getColor(R.color.green));
            Drawable drawable1 = getResources().getDrawable(R.mipmap.dui);
/// 这一步必须要做,否则不会显示.
            drawable1.setBounds(0, 0, 50, 50);
            textview.setCompoundDrawables(null, null, drawable1, null);
        } else {//错误
            setAnswerIsRight(false);
            musicUntil.playRaw(getContext(), R.raw.wrong);
            textview.setTextColor(getResources().getColor(R.color.red));
            righOrWrong.setText("回答错误");
            righOrWrong.setTextColor(getResources().getColor(R.color.red));
            Drawable drawable1 = getResources().getDrawable(R.mipmap.cuo);
/// 这一步必须要做,否则不会显示.
            drawable1.setBounds(0, 0, 80, 80);
            textview.setCompoundDrawables(null, null, drawable1, null);
            getBaseTiMuActivity().setFen(getBaseTiMuActivity().getFen() - 1);
        }
        isAnswer = true;
    }

}
