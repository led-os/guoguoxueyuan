package com.test720.grasshoppercollege.module.yuFaTest;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.test720.grasshoppercollege.BaseTiMuOkFragment;
import com.test720.grasshoppercollege.HttpBean.YuFaTextTiMuData;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.publicQuestion.AnswerDialog;
import com.test720.grasshoppercollege.untils.musicMedia.MusicUntil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 水东流 on 2018/7/11.
 */

public class YuFaLianXiZhaoChaFragment extends BaseTiMuOkFragment {
    YuFaTextTiMuData.DataBean bean;
    @BindView(R.id.flow)
    TagFlowLayout flow;
    @BindView(R.id.myCheck)
    TextView myCheck;
    @BindView(R.id.myAnswer)
    EditText myAnswer;
    @BindView(R.id.answer)
    TextView answer;
    @BindView(R.id.next)
    Button next;
    List<TextView> textViewlist = new ArrayList<>();
    int index = -1;//选择的位置
    TagAdapter adapter;
    MusicUntil musicUntil = new MusicUntil();

    @Override
    public void onDestroy() {
        super.onDestroy();
        musicUntil.canlce();
    }

    @Override
    public void TongGuan() {

    }

    @Override
    public void onStart() {
        super.onStart();
        if (getBean() != null) {
            adapter = new TagAdapter<YuFaTextTiMuData.DataBean.QuestionContentBean>(getBean().getQuestion_content()) {
                @Override
                public View getView(FlowLayout flowLayout, int i, YuFaTextTiMuData.DataBean.QuestionContentBean o) {
                    final int pos = i;
                    View view = LayoutInflater.from(getActivity()).inflate(R.layout.flow_text_item, null);
                    TextView textView = view.findViewById(R.id.text);
                    textView.setText(o.getWord());
                    if (index == i) {
                        textView.setBackgroundResource(R.color.appColor);
                        textView.setTextColor(getResources().getColor(R.color.white));
                    } else {
                        textView.setBackgroundResource(R.drawable.bule_bian_kuang);
                        textView.setTextColor(getResources().getColor(R.color.appColor));
                    }
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            index = pos;
                            myCheck.setText(textViewlist.get(pos).getText().toString());
                            adapter.notifyDataChanged();
                            myAnswer.setText("");
                        }
                    });
                    textViewlist.add(textView);
                    return view;
                }
            };
            flow.setAdapter(adapter);

        }
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
        myAnswer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = "";
                for (int i = 0; i < getBean().getQuestion_content().size(); i++) {
                    if (i == index) {
                        str = str + s + "  ";
                    } else {
                        str = str + getBean().getQuestion_content().get(i).getWord() + "  ";
                    }
                }
                answer.setText(str);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public int setlayoutResID() {
        return R.layout.yu_fa_zhao_cha_fragment;
    }

    @OnClick(R.id.next)
    public void onViewClicked() {
        if (isAnswer) return;
        if (TextUtils.isEmpty(answer.getText())) {
            ShowToast("请给出你的答案");
            return;
        }
        String str = "";
        for (int i = 0; i < getBean().getQuestion_content().size(); i++) {
            str = str + getBean().getQuestion_content().get(i).getAnswer() + "";
        }
        String answerStr = answer.getText().toString();
        str = str.replace(" ", "");
        answerStr = answerStr.replace(" ", "");
        if (answerStr.equals(str)) {
            setAnswerIsRight(true);
            musicUntil.playRaw(getContext(), R.raw.righten);
        } else {
            setAnswerIsRight(false);
            musicUntil.playRaw(getContext(), R.raw.wrong);
            getBaseTiMuActivity().setFen(getBaseTiMuActivity().getFen() - 1);
        }
        next.setVisibility(View.GONE);

        isAnswer = true;
        AnswerDialog answerDialog = new AnswerDialog();
        answerDialog.setAnswerTxt(bean.getAnswer().toString());
        answerDialog.setDeceTxt(bean.getDesc());
        answerDialog.setRight(answerIsRight);
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

    public YuFaTextTiMuData.DataBean getBean() {
        return bean;
    }

    public void setBean(YuFaTextTiMuData.DataBean bean) {
        this.bean = bean;
    }
}
