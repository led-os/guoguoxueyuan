package com.test720.grasshoppercollege.module.yuWen.yueDu.tiXing;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.bean.RangBean;
import com.test720.grasshoppercollege.myViews.EditableTextView;
import com.test720.grasshoppercollege.myViews.ZuoYeRightOrWrongDialog;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 水东流 on 2018/7/5.
 */

public class YuWenYueDuTianKongFragment extends YuWenYueDuTiFragment {
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.answer)
    EditableTextView answer;
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
    public void setListener() {

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
        }
          /*补充空*/
        if (getDataBean().getQuestion_content1() != null) {
            String s = getDataBean().getQuestion_content1();
            ranges = new ArrayList<>();
            for (int i = 0; i < getDataBean().getSubscript().size(); i++) {
                int t = Integer.parseInt(getDataBean().getSubscript().get(i));
                ranges.add(new RangBean(t, t + 4));
            }
            answer.setData(s, ranges);
        }
    }

    @Override
    public int setlayoutResID() {
        return R.layout.ji_chu_tian_kong;
    }


    @OnClick(R.id.tiJiao)
    public void onViewClicked() {

        if (answer.getAnswers() == null || answer.getAnswers().size() < getDataBean().getSubscript().size()) {
            ShowToast("请做出您的答案！");
            return;
        }
        /*显示答案*/

        for (int i = 0; i < answer.getAnswers().size(); i++) {
            if (getDataBean().getAnswer().size() < i) {
                ShowToast("答案题目不一致，请联系管理员");
                continue;
            }
            if (answer.getAnswers().get(i).equals("")) {
                ShowToast("请做出您的答案！");
                return;
            }
            /*判断正确还是错误*/
            if (!answer.getAnswers().get(i).equals(getDataBean().getAnswer().get(i))) {
                answerIsRight = false;
                getBaseTiMuActivity().setFen(getBaseTiMuActivity().getFen() - 1);
                continue;
            }
        }
        ZuoYeRightOrWrongDialog zuoYeRightOrWrongDialog = new ZuoYeRightOrWrongDialog();
        zuoYeRightOrWrongDialog.setRight(isAnswerIsRight());
        zuoYeRightOrWrongDialog.setJieXi("正确答案:" + getDataBean().getAnswer().toString());
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