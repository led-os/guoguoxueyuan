package com.test720.grasshoppercollege.module.shuXue.jiChu.tiXing;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.myViews.ZuoYeRightOrWrongDialog;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by 水东流 on 2018/7/2.
 */

public class JiChuPanDuanFragment extends BaseMathTiMuFragment {
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.timu)
    TextView timu;
    @BindView(R.id.right)
    TextView right;
    @BindView(R.id.wrong)
    TextView wrong;
    Unbinder unbinder;

    @Override
    public LinearLayout answerLin() {
        return null;
    }

    @Override
    public void initData() {

    }

    @Override
    public void setListener() {

    }


    @Override
    public int setlayoutResID() {
        return R.layout.ji_chu_pan_duan;
    }


    @OnClick({R.id.right, R.id.wrong})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.right:
                answer(true);
                break;
            case R.id.wrong:
                answer(false);
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDataBean() == null) {
            return;
        }
        timu.setText(getDataBean().getQuestion_content1());
        text.setText(getDataBean().getQuestion());
        if (getDataBean().getQuestion_content_type().equals("2")) {
            Glide.with(getActivity()).load(getDataBean().getQuestion_content()).into(img);
        } else {
            img.setVisibility(View.GONE);
        }

    }

    /*回答*/
    private void answer(boolean b) {
        if (isAnswer) return;
        if (getDataBean().getAnswer().get(0).equals("1") && b == true) {
            setAnswerIsRight(true);
            right.setBackgroundResource(R.drawable.bule_back_kuang);
            right.setTextColor(getResources().getColor(R.color.white));
        } else if (getDataBean().getAnswer().equals("0") && b == false) {
            setAnswerIsRight(true);
            wrong.setBackgroundResource(R.drawable.bule_back_kuang);
            wrong.setTextColor(getResources().getColor(R.color.white));
        } else {
            if (b) {
                right.setBackgroundResource(R.drawable.red_back);
                right.setTextColor(getResources().getColor(R.color.white));
            } else {
                wrong.setBackgroundResource(R.drawable.red_back);
                wrong.setTextColor(getResources().getColor(R.color.white));
            }
            setAnswerIsRight(false);
            getBaseTiMuActivity().setFen(getBaseTiMuActivity().getFen() - 1);
        }


        ZuoYeRightOrWrongDialog zuoYeRightOrWrongDialog = new ZuoYeRightOrWrongDialog();
        zuoYeRightOrWrongDialog.setRight(isAnswerIsRight());
        zuoYeRightOrWrongDialog.setJieXi("");
        zuoYeRightOrWrongDialog.setGo(new ZuoYeRightOrWrongDialog.Next() {
            @Override
            public void go() {
                if (getViewPager().getCurrentItem() < getViewPager().getAdapter().getCount() - 1) {
                    getViewPager().setCurrentItem(getViewPager().getCurrentItem() + 1);
                } else {
                    AddGuoGuoDou();
                }
            }
        });
        zuoYeRightOrWrongDialog.show(getActivity().getSupportFragmentManager().beginTransaction(), "right");
        isAnswer = true;
    }
}
