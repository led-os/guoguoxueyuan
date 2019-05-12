package com.test720.grasshoppercollege.module.jinShai;

import android.view.View;
import android.widget.TextView;

import com.test720.grasshoppercollege.BaseFragment;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.musicMedia.MusicUntil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 水东流 on 2018/5/10.
 */

public class JinShaiTiFragment extends BaseFragment {
    @BindView(R.id.title)
    TextView tiMu;
    @BindView(R.id.A)
    TextView A;
    @BindView(R.id.B)
    TextView B;
    @BindView(R.id.C)
    TextView C;
    @BindView(R.id.D)
    TextView D;
    MusicUntil musicUntil = new MusicUntil();
    String aText;
    String bText;
    String cText;
    String dText;
    String title;
    String answer;
    TallRightOrNot tallRightOrNot;
    boolean isAnswer = false;

    public void setTallRightOrNot(TallRightOrNot tallRightOrNot) {
        this.tallRightOrNot = tallRightOrNot;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setdText(String dText) {
        this.dText = dText;
    }

    public void setaText(String aText) {
        this.aText = aText;
    }

    public void setbText(String bText) {
        this.bText = bText;
    }

    public void setcText(String cText) {
        this.cText = cText;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void initData() {

    }

    @Override
    public void setListener() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        musicUntil.canlce();
    }

    @Override
    public int setlayoutResID() {
        return R.layout.fragment_jin_sai_ti;
    }

    @Override
    public void getSuccess(String s, int what) {

    }

    @Override
    public void onStart() {
        super.onStart();
        A.setText(aText + "");
        B.setText(bText + "");
        C.setText(cText + "");
        D.setText(dText + "");
        tiMu.setText(title + "");
    }


    @OnClick({R.id.A, R.id.B, R.id.C, R.id.D})
    public void onViewClicked(View view) {
        if (isAnswer) return;
        switch (view.getId()) {
            case R.id.A:
                if (answer.equals("A")) {
                    musicUntil.playRaw(getContext(), R.raw.righten);
                    A.setBackground(getResources().getDrawable(R.mipmap.duikuang1));
                    tallRightOrNot.tallRightOrNot(true);
                } else {
                    musicUntil.playRaw(getContext(), R.raw.wrong);
                    A.setBackground(getResources().getDrawable(R.mipmap.cuobj));
                    tallRightOrNot.tallRightOrNot(false);
                }
                break;
            case R.id.B:
                if (answer.equals("B")) {
                    musicUntil.playRaw(getContext(), R.raw.righten);
                    B.setBackground(getResources().getDrawable(R.mipmap.duikuang1));
                    tallRightOrNot.tallRightOrNot(true);
                } else {
                    musicUntil.playRaw(getContext(), R.raw.wrong);
                    B.setBackground(getResources().getDrawable(R.mipmap.cuobj));
                    tallRightOrNot.tallRightOrNot(false);
                }
                break;
            case R.id.C:
                if (answer.equals("C")) {
                    musicUntil.playRaw(getContext(), R.raw.righten);
                    C.setBackground(getResources().getDrawable(R.mipmap.duikuang1));
                    tallRightOrNot.tallRightOrNot(true);
                } else {
                    musicUntil.playRaw(getContext(), R.raw.wrong);
                    C.setBackground(getResources().getDrawable(R.mipmap.cuobj));
                    tallRightOrNot.tallRightOrNot(false);
                }
                break;
            case R.id.D:
                if (answer.equals("D")) {
                    musicUntil.playRaw(getContext(), R.raw.righten);
                    D.setBackground(getResources().getDrawable(R.mipmap.duikuang1));
                    tallRightOrNot.tallRightOrNot(true);
                } else {
                    musicUntil.playRaw(getContext(), R.raw.wrong);
                    D.setBackground(getResources().getDrawable(R.mipmap.cuobj));
                    tallRightOrNot.tallRightOrNot(false);
                }
                break;
        }
        isAnswer = true;
    }

    public interface TallRightOrNot {
        void tallRightOrNot(boolean b);
    }
}
