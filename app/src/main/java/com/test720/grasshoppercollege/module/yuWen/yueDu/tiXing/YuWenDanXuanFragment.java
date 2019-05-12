package com.test720.grasshoppercollege.module.yuWen.yueDu.tiXing;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.myViews.ZuoYeRightOrWrongDialog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 水东流 on 2018/7/5.
 */

public class YuWenDanXuanFragment extends YuWenYueDuTiFragment {

    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.timu)
    TextView timu;
    @BindView(R.id.a)
    TextView a;
    @BindView(R.id.b)
    TextView b;
    @BindView(R.id.c)
    TextView c;
    @BindView(R.id.d)
    TextView d;

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
    public void onStart() {
        super.onStart();

        if (getDataBean() == null) {
            return;
        }
        a.setText("A  "+getDataBean().getSelect_a());
        b.setText("B  "+getDataBean().getSelect_b());
        c.setText("C  "+getDataBean().getSelect_c());
        d.setText("D  "+getDataBean().getSelect_d());
        timu.setText(getDataBean().getQuestion_content1());
        text.setText(getDataBean().getQuestion());
        if (getDataBean().getQuestion_content_type().equals("2")) {
            Glide.with(getActivity()).load(getDataBean().getQuestion_content()).into(img);
        } else {
            img.setVisibility(View.GONE);
        }

    }

    @Override
    public int setlayoutResID() {
        return R.layout.shu_xue_ji_chu_dan_xuan;
    }

    @OnClick({R.id.a, R.id.b, R.id.c, R.id.d})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.a:
                answer("A");
                break;
            case R.id.b:
                answer("B");
                break;
            case R.id.c:
                answer("C");
                break;
            case R.id.d:
                answer("D");
                break;
        }

    }

    /*答题*/
    private void answer(String str) {
        if (isAnswer) return;
        if (str.equals(getDataBean().getAnswer().get(0))) {
            setAnswerIsRight(true);
        } else {
            setAnswerIsRight(false);
            getBaseTiMuActivity().setFen(getBaseTiMuActivity().getFen() - 1);
        }
        switch (str) {
            case "A":
                answerXuan(a);
                break;
            case "B":
                answerXuan(b);
                break;
            case "C":
                answerXuan(c);
                break;
            case "D":
                answerXuan(d);
                break;
        }
        ZuoYeRightOrWrongDialog zuoYeRightOrWrongDialog = new ZuoYeRightOrWrongDialog();
        zuoYeRightOrWrongDialog.setRight(isAnswerIsRight());
        zuoYeRightOrWrongDialog.setJieXi("正确答案是"+getDataBean().getAnswer().get(0));
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
    private void answerXuan(TextView textView) {
        if (answerIsRight) {
            textView.setBackgroundResource(R.drawable.bule_back_kuang);
            textView.setTextColor(getResources().getColor(R.color.white));
        } else {
            textView.setBackgroundResource(R.drawable.red_back);
            textView.setTextColor(getResources().getColor(R.color.white));
        }
    }
}