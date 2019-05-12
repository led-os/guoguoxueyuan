package com.test720.grasshoppercollege.module.tanXian.TypeOfQuestion.englishDanCiTingXie;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.tanXian.TypeOfQuestion.BaseTanXianTiMuFragment;
import com.test720.grasshoppercollege.publicQuestion.AnswerDialog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 佛祖保佑        永无BUG
 * 佛曰:
 * 程序园里程序天，程序天里程序员；
 * 程序猿人写程序，又拿程序换肉钱。
 * 肉饱继续桌前坐，饱暖还是桌前眠；
 * 半迷半醒日复日，码上码下年复年。
 * 但愿叱咤互联世，不愿搬砖码当前；
 * 诸葛周瑜算世事，我算需求得加钱。
 * 别人笑我忒直男，我说自己是程猿；
 * 但见成都府国内，处处地地程序员。
 * 作者：水东流 编于 2018/11/12
 */
public class TiankongFragment extends BaseTanXianTiMuFragment {
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
    public void startShow() {
        if (getBean().getVoice().equals("")) {
            video.setVisibility(View.GONE);
        } else {
            video.setVisibility(View.VISIBLE);
        }
        if (!getBean().getQuestion().equals("")) {
            String question = getBean().getQuestion().replace("\\n", "\n");
            en.setText(question);
        }
    }

   /* @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            play(laba);
        } else {
            stop();
        }
    }*/

    @Override
    public void play() {
        play(laba);
    }

    @OnClick({R.id.ok, R.id.video})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.video:
                play(laba);
                break;
            case R.id.ok:
                huida();
                break;
        }
    }

    private void huida() {
        if (isAnswer()) return;
        if (TextUtils.isEmpty(myAnswer.getText())) {
            ShowToast("请输入您的答案！");
            return;
        }
        String m = myAnswer.getText().toString();
        String q = getBean().getAnswer().get(0);
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
        setAnswer(true);
/// 这一步必须要做,否则不会显示.
        drawable.setBounds(0, 0, 50, 50);
        myAnswer.setCompoundDrawables(null, null, drawable, null);

        AnswerDialog danCiTiMuAnswerDialog = new AnswerDialog();
        String a = getBean().getAnswer().toString();
        danCiTiMuAnswerDialog.setAnswerTxt(a);
        danCiTiMuAnswerDialog.setDeceTxt(getBean().getDesc());
        danCiTiMuAnswerDialog.setRight(isAnswerIsRight());
        danCiTiMuAnswerDialog.setAnswerInterface(new AnswerDialog.AnswerInterface() {
            @Override
            public void nextClick() {
                stop();
                nextItem();
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
    public int setlayoutResID() {
        return R.layout.dan_ci_ting_xie_tian_kong;
    }
}
