package com.test720.grasshoppercollege.publicQuestion;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test720.grasshoppercollege.BaseOkDialogFragment;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.musicMedia.MusicUntil;

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
 * 作者：水东流 编于 2018/9/26
 */
public class AnswerDialog extends BaseOkDialogFragment {
    @BindView(R.id.answer)
    TextView answer;
    @BindView(R.id.dece)
    TextView dece;
    @BindView(R.id.next)
    TextView next;
    @BindView(R.id.rightOrNot)
    TextView rightOrNot;
    @BindView(R.id.lin)
    LinearLayout lin;

    String answerTxt = "";
    String deceTxt = "";
    boolean isRight = true;
    MusicUntil musicUntil = new MusicUntil();

    @Override
    public DialogInit dialogInit() {
        return new DialogInit() {
            @Override
            public boolean outTouch() {
                return false;
            }

            @Override
            public int gravity() {
                return Gravity.BOTTOM;
            }

            @Override
            public int widthLayout() {
                return WindowManager.LayoutParams.MATCH_PARENT;
            }

            @Override
            public int heightLayout() {
                return WindowManager.LayoutParams.WRAP_CONTENT;
            }
        };
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(false);
    }

    @Override
    public int layoutId() {
        return R.layout.fan_yi_chuang_guan_answer_dialog;
    }


    @Override
    public void start() {
        if (answerInterface != null && answerInterface.linBack() != -1) {
            lin.setBackgroundResource(answerInterface.linBack());
        }
        if (answerInterface != null && answerInterface.nextBack() != -1) {
            next.setBackgroundResource(answerInterface.nextBack());
            if (answerInterface.nextTxt() != null) next.setText(answerInterface.nextTxt());
        }
        if (isRight) {
            rightOrNot.setTextColor(getResources().getColor(R.color.green));
            rightOrNot.setText("回答正确");
            musicUntil.playRaw(mContext, R.raw.righten);
            if (rightBackAndWrongInterface != null)
                lin.setBackgroundResource(rightBackAndWrongInterface.rightBack());
        } else {
            rightOrNot.setTextColor(getResources().getColor(R.color.red));
            String a = "回答错误";
            musicUntil.playRaw(mContext, R.raw.wrong);
            rightOrNot.setText(a);
            if (rightBackAndWrongInterface != null)
                lin.setBackgroundResource(rightBackAndWrongInterface.wrongback());
        }
        String an = "正确答案:\t\t" + answerTxt;
        answer.setText(an);
        dece.setText(deceTxt);
    }

    @Override
    public void getSuccess(String s, int what) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        musicUntil.canlce();
    }

    @OnClick(R.id.next)
    public void onViewClicked() {
        if (answerInterface != null) {
            answerInterface.nextClick();
        }
        dismissAllowingStateLoss();
    }


    public void setRight(boolean right) {
        isRight = right;
    }

    public void setAnswerTxt(String answerTxt) {
        this.answerTxt = answerTxt;
    }

    public void setDeceTxt(String deceTxt) {
        this.deceTxt = deceTxt;
    }

    AnswerInterface answerInterface;

    public void setAnswerInterface(AnswerInterface answerInterface) {
        this.answerInterface = answerInterface;
    }

    public interface AnswerInterface {
        void nextClick();

        String nextTxt();

        @DrawableRes
        int nextBack();

        @DrawableRes
        int linBack();
    }

    RightBackAndWrongInterface rightBackAndWrongInterface;

    public void setRightBackAndWrongInterface(RightBackAndWrongInterface rightBackAndWrongInterface) {
        this.rightBackAndWrongInterface = rightBackAndWrongInterface;
    }

    public interface RightBackAndWrongInterface {
        @DrawableRes
        int rightBack();

        @DrawableRes
        int wrongback();
    }

}