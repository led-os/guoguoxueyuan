package com.test720.grasshoppercollege.module.shuXue.aoShuLianXi;

import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.test720.grasshoppercollege.BaseTiMuOkFragment;
import com.test720.grasshoppercollege.HttpBean.AoShuLianXiNeiRongData;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.myViews.AoShuDaTiDialog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 水东流 on 2018/4/26.
 */

public class AoShuLianXiDanXuanFragment extends BaseTiMuOkFragment {

    @Override
    public void initData() {

    }

    @Override
    public void onStart() {
        super.onStart();
        tiMu.setMovementMethod(ScrollingMovementMethod.getInstance());
        if (listBean.getSelect_a() != null) {
            a.setVisibility(View.VISIBLE);
            a.setText(listBean.getSelect_a());
        } else {
            a.setVisibility(View.GONE);
        }
        if (listBean.getSelect_b() != null) {
            b.setVisibility(View.VISIBLE);
            b.setText(listBean.getSelect_b());
        } else {
            b.setVisibility(View.GONE);
        }

        if (listBean.getSelect_c() != null) {
            c.setVisibility(View.VISIBLE);
            c.setText(listBean.getSelect_c());
        } else {
            c.setVisibility(View.GONE);
        }
        if (listBean.getSelect_d() != null) {
            d.setVisibility(View.VISIBLE);
            d.setText(listBean.getSelect_d());
        } else {
            d.setVisibility(View.GONE);
        }
        if (listBean.getQuestion() != null) tiMu.setText(listBean.getQuestion_content());

    }

    @Override
    public int setlayoutResID() {
        return R.layout.ying_yong_dan_xuan;
    }

    @Override
    public void TongGuan() {

    }

    @Override
    public String GXShareTitile() {
        return "奥数练习";
    }

    @Override
    public String corrId() {
        return getListBean().getCorr_id();
    }

    @Override
    public String questionId() {
        return getListBean().getQuestion_id();
    }

    @OnClick({R.id.a, R.id.b, R.id.c, R.id.d})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.a:
                check("A");
                break;
            case R.id.b:
                check("B");
                break;
            case R.id.c:
                check("C");
                break;
            case R.id.d:
                check("D");
                break;
        }
    }

    //*给出了答案*//
    private void check(String s) {
        if (isAnswer) return;
        if (dialog != null) {
            dialog.show(getChildFragmentManager(), "dialog");
            return;
        }
        dialog = new AoShuDaTiDialog();
        dialog.setRightAnswer(listBean.getAnswer().get(0));
        dialog.setS(listBean.getDesc());
        if (listBean.getAnswer().get(0).equals(s)) {
            setAnswerIsRight(true);
        } else {
            setAnswerIsRight(false);
            getBaseTiMuActivity().setFen(getBaseTiMuActivity().getFen() - 1);
        }
        dialog.setRight(isAnswerIsRight());

        dialog.setNextIn(new AoShuDaTiDialog.Next() {
            @Override
            public void nextClick() {
                if (getViewPager().getAdapter().getCount() > (getViewPager().getCurrentItem() + 1)) {
                    getViewPager().setCurrentItem(getViewPager().getCurrentItem() + 1, true);
                } else {
                    ShowToast("已经是最后一题");
                    AddGuoGuoDou();
                }
            }
        });
        dialog.show(getChildFragmentManager(), "dialog");
        isAnswer = true;
    }


    @BindView(R.id.tiMu)
    TextView tiMu;
    @BindView(R.id.a)
    TextView a;
    @BindView(R.id.b)
    TextView b;
    @BindView(R.id.c)
    TextView c;
    @BindView(R.id.d)
    TextView d;
    AoShuDaTiDialog dialog;
    AoShuLianXiNeiRongData.DataBean listBean;

    public AoShuLianXiNeiRongData.DataBean getListBean() {
        return listBean;
    }

    public void setListBean(AoShuLianXiNeiRongData.DataBean listBean) {
        this.listBean = listBean;
    }
}
