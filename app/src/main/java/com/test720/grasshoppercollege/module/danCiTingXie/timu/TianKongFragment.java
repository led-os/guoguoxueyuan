package com.test720.grasshoppercollege.module.danCiTingXie.timu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.publicQuestion.AnswerDialog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 水东流 on 2018/7/3.
 */

public class TianKongFragment extends BaseDanCiFragment {


    @BindView(R.id.en)
    TextView en;
    @BindView(R.id.myAnswer)
    EditText myAnswer;
    @BindView(R.id.video)
    LinearLayout video;
    @BindView(R.id.ok)
    TextView ok;
    @BindView(R.id.laba)
    ImageView laba;

    @Override
    public void initData() {

    }

    @Override
    public int setlayoutResID() {
        return R.layout.dan_ci_ting_xie_tian_kong;
    }

    @Override
    public void onStart() {
        super.onStart();
        myAnswer.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    //隐藏输入框
                    InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                }
                return true;
            }
        });

        if (bean != null) {
            if (bean.getQuestion_type().equals("1")) {
                video.setVisibility(View.GONE);
                if (bean.getQuestion() != null) {
                    String question = bean.getQuestion_content().replace("\\n", "\n");
                    en.setText(question);
                }
            } else if (bean.getQuestion_type().equals("3")) {
                video.setVisibility(View.VISIBLE);
                if (bean.getQuestion() != null) {
                    String question = bean.getQuestion().replace("\\n", "\n");
                    en.setText(question);
                }
            }

        }
    }

    @Override
    public ImageView laBa() {
        return laba;
    }

    @OnClick({R.id.ok, R.id.video})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.video:
                play(getBean().getQuestion_content());
                break;
            case R.id.ok:
                huida();
                break;
        }
    }

    private void huida() {
        if (isAnswer) return;
        if (TextUtils.isEmpty(myAnswer.getText())) {
            ShowToast("请输入您的答案！");
            return;
        }
        String m = myAnswer.getText().toString();
        String q = bean.getAnswer();
        m = m.toLowerCase();
        q = q.toLowerCase();
        m = m.replaceAll(" ", "");
        q = q.replaceAll(" ", "");

        Drawable drawable;//做出选择后输入框状态更改
        if (m.equals(q)) {
            setAnswerIsRight(true);
            myAnswer.setTextColor(getResources().getColor(R.color.green));
            drawable = getResources().getDrawable(R.mipmap.dui);

        } else {
            setAnswerIsRight(false);
            myAnswer.setTextColor(getResources().getColor(R.color.red));
            drawable = getResources().getDrawable(R.mipmap.cuo);
            getBaseTiMuActivity().setFen(getBaseTiMuActivity().getFen() - 1);
        }
        isAnswer = true;
/// 这一步必须要做,否则不会显示.
        drawable.setBounds(0, 0, 50, 50);
        myAnswer.setCompoundDrawables(null, null, drawable, null);

        AnswerDialog danCiTiMuAnswerDialog = new AnswerDialog();
        String a = bean.getAnswer();
        danCiTiMuAnswerDialog.setAnswerTxt(a);
        danCiTiMuAnswerDialog.setDeceTxt(bean.getDesc());
        danCiTiMuAnswerDialog.setRight(answerIsRight);
        danCiTiMuAnswerDialog.setAnswerInterface(new AnswerDialog.AnswerInterface() {
            @Override
            public void nextClick() {
                stop();
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


    }


}
