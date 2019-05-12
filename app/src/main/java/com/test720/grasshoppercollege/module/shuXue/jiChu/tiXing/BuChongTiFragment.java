package com.test720.grasshoppercollege.module.shuXue.jiChu.tiXing;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.bean.RangBean;
import com.test720.grasshoppercollege.myViews.ChooseTextView;
import com.test720.grasshoppercollege.myViews.ZuoYeRightOrWrongDialog;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 水东流 on 2018/7/2.
 */

public class BuChongTiFragment extends BaseMathTiMuFragment {

    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.quesition2)
    TextView quesition2;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.chooseText)
    ChooseTextView chooseText;
    @BindView(R.id.tiJiao)
    TextView tiJiao;

    private ArrayList<RangBean> ranges;

    @Override
    public LinearLayout answerLin() {
        return null;
    }

    @Override
    public void initData() {


    }

    @Override
    public void onStart() {
        super.onStart();

        if (getDataBean() == null) {
            return;
        }
        text.setText(getDataBean().getQuestion());
        if (getDataBean().getQuestion_content_type().equals("2")) {
            Glide.with(getActivity()).load(getDataBean().getQuestion_content()).into(img);
        } else {
            img.setVisibility(View.GONE);
            quesition2.setText(getDataBean().getQuestion_content());
        }
        /*补充空*/
        if (getDataBean().getQuestion_content1() != null) {
            String s = getDataBean().getQuestion_content1();
            ranges = new ArrayList<>();
            for (int i = 0; i < getDataBean().getSubscript().size(); i++) {
                int t = Integer.parseInt(getDataBean().getSubscript().get(i));
                ranges.add(new RangBean(t, t + 4));
            }
            chooseText.setData(s, ranges, getDataBean().getAnswer_select());
        }
    }

    @Override
    public void setListener() {

    }

    @Override
    public int setlayoutResID() {
        return R.layout.ji_chu_bu_chong_fragment;
    }

    @OnClick(R.id.tiJiao)
    public void onViewClicked() {
        if (isAnswer) return;
        if (chooseText.getAnswers() == null || chooseText.getAnswers().size() < getDataBean().getSubscript().size()) {
            ShowToast("请做出您的答案！");
            return;
        }
        /*显示答案*/
        setAnswerIsRight(true);
        for (int i = 0; i < chooseText.getAnswers().size(); i++) {
            if (chooseText.getAnswers().get(i).equals("")) {
                ShowToast("请做出您的答案！");
                return;
            }
            if (getDataBean().getAnswer().size() < i) {
                ShowToast("答案题目不一致，请联系管理员");
                return;
            }
            /*判断正确还是错误*/
            if (!chooseText.getAnswers().get(i).equals(getDataBean().getAnswer().get(i))) {
                setAnswerIsRight(false);
                break;
            }
        }
        isAnswer = true;
        ZuoYeRightOrWrongDialog zuoYeRightOrWrongDialog = new ZuoYeRightOrWrongDialog();
        zuoYeRightOrWrongDialog.setRight(isAnswerIsRight());
        zuoYeRightOrWrongDialog.setJieXi(getDataBean().getDesc());
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
    }

}
