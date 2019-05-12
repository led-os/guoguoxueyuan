package com.test720.grasshoppercollege.module.yuFaTest;

import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseTiMuOkFragment;
import com.test720.grasshoppercollege.HttpBean.YuFaTextTiMuData;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.bean.RangBean;
import com.test720.grasshoppercollege.myViews.ChooseTextView;
import com.test720.grasshoppercollege.publicQuestion.AnswerDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 水东流 on 2018/7/11.
 */

public class YuFaLianXiBuCongFragment extends BaseTiMuOkFragment {
    YuFaTextTiMuData.DataBean bean;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.tiJiao)
    Button tiJiao;
    @BindView(R.id.recyclerView)
//    RecyclerView recyclerView;
            LinearLayout linearLayout;
    MyBaseRecyclerAdapter adapter;
    List<ChooseTextView> chooseTextViewList = new ArrayList<>();

    @Override
    public void TongGuan() {

    }

    @Override
    public void onStart() {
        super.onStart();
        if (getBean() == null) {
            return;
        }
        /*标题*/
        String s = getBean().getQuestion();
        text.setText(s);
        if (chooseTextViewList.size() == 0)
            for (int i = 0; i < getBean().getQuestion_content1().size(); i++) {
                ChooseTextView chooseTextView = new ChooseTextView(getActivity());
                LinearLayout.LayoutParams LP_WW = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                chooseTextView.setLayoutParams(LP_WW);
                ArrayList<RangBean> ranges = new ArrayList<>();
                int t = Integer.parseInt(getBean().getSubscript().get(i));
                ranges.add(new RangBean(t, t + 4));
                chooseTextView.setData(getBean().getQuestion_content1().get(i), ranges, getBean().getQuestion_select());
                chooseTextViewList.add(chooseTextView);
                linearLayout.addView(chooseTextView);
            }

      /*  recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);

        if (adapter == null) {
            adapter = new MyBaseRecyclerAdapter<String>(getBean().getQuestion_content1(), getActivity(), R.layout.bu_cong_item) {
                @Override
                public void convert(BaseRecyclerHolder holder, String item, int postion) {
                    ArrayList<RangBean> ranges = new ArrayList<>();
                    int t = Integer.parseInt(getBean().getSubscript().get(postion));
                    ranges.add(new RangBean(t, t + 4));
                    ChooseTextView chooseTextView = holder.getView(R.id.chooseText);
                    chooseTextView.setData(item, ranges, getBean().getQuestion_select());
                    chooseTextViewList.add(chooseTextView);
                }
            };
            recyclerView.setAdapter(adapter);
        }*/
    }

    @Override
    public String GXShareTitile() {
        return "语法练习";
    }

    @Override
    public String corrId() {
        return getBean().getCorr_id();
    }

    @Override
    public String questionId() {
        return getBean().getQuestion_id();
    }

    @Override
    public void initData() {
    /*    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);*/
    }

    @OnClick(R.id.tiJiao)
    public void onViewClicked() {
        if (isAnswer) return;
        outterLoop:
        for (int i = 0; i < chooseTextViewList.size(); i++) {
            if (chooseTextViewList.get(i).getAnswers() == null || chooseTextViewList.get(i).getAnswers().size() == 0 || chooseTextViewList.get(i).getAnswers().get(0).equals("")) {
                ShowToast("请做出您的答案！");
                return;
            }
            for (int t = 0; t < chooseTextViewList.get(i).getAnswers().size(); t++) {
                if (!chooseTextViewList.get(i).getAnswers().get(t).equals(getBean().getAnswer().get(i))) {
                    setAnswerIsRight(false);
                    getBaseTiMuActivity().setFen(getBaseTiMuActivity().getFen() - 1);
//                    break outterLoop;
                }
            }
        }
        isAnswer = true;
        AnswerDialog answerDialog = new AnswerDialog();
        answerDialog.setRight(answerIsRight);
        answerDialog.setAnswerTxt(bean.getAnswer().toString());
        answerDialog.setDeceTxt(bean.getDesc());
        answerDialog.setAnswerInterface(new AnswerDialog.AnswerInterface() {
            @Override
            public void nextClick() {
                if (getViewPager().getCurrentItem() < getViewPager().getAdapter().getCount() - 1) {
                    getViewPager().setCurrentItem(getViewPager().getCurrentItem() + 1);
                } else {
                    AddGuoGuoDou();
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
                return R.mipmap.yufalianxianswer;
            }
        });
        answerDialog.show(getChildFragmentManager(), "answer");

    }


    @Override
    public int setlayoutResID() {
        return R.layout.yu_fa_bu_cong_ti;
    }

    public YuFaTextTiMuData.DataBean getBean() {
        return bean;
    }

    public void setBean(YuFaTextTiMuData.DataBean bean) {
        this.bean = bean;
    }
}
