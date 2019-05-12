package com.test720.grasshoppercollege.module.tanXian.TypeOfQuestion.bianyin;

import android.view.View;
import android.widget.ImageView;
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
public class DanXuanFragment extends BaseTanXianTiMuFragment {
    @BindView(R.id.one)
    TextView one;
    @BindView(R.id.oneImg)
    ImageView oneImg;
    @BindView(R.id.two)
    TextView two;
    @BindView(R.id.twoImg)
    ImageView twoImg;
    @BindView(R.id.three)
    TextView three;
    @BindView(R.id.threeImg)
    ImageView threeImg;
    @BindView(R.id.four)
    TextView four;
    @BindView(R.id.fourImg)
    ImageView fourImg;
    @BindView(R.id.timu)
    TextView timu;

    @Override
    public void startShow() {
        if (getBean() == null) {
            return;
        }
        one.setText("A\t\t\t" + getBean().getSelect_a());
        two.setText("B\t\t\t" + getBean().getSelect_b());
        three.setText("C\t\t\t" + getBean().getSelect_c());
        four.setText("D\t\t\t" + getBean().getSelect_d());
        timu.setText(getBean().getQuestion());
    }

    @OnClick({R.id.one, R.id.oneImg, R.id.two, R.id.twoImg, R.id.three, R.id.threeImg, R.id.four, R.id.fourImg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.one:
                answer("A");
                break;
            case R.id.oneImg:
                break;
            case R.id.two:
                answer("B");
                break;
            case R.id.twoImg:
                break;
            case R.id.three:
                answer("C");
                break;
            case R.id.threeImg:
                break;
            case R.id.four:
                answer("D");
                break;
            case R.id.fourImg:

                break;
        }
    }


    /*答题*/
    private void answer(String str) {
        if (isAnswer()) return;
        if (str.equals(getBean().getAnswer().get(0))) {
            setAnswerIsRight(true);
        } else {
            setAnswerIsRight(false);
            getBaseTiMuActivity().setFen(getBaseTiMuActivity().getFen() - 1);
        }

        setAnswer(true);

        AnswerDialog danCiTiMuAnswerDialog = new AnswerDialog();
        String a = getBean().getAnswer().toString();
        danCiTiMuAnswerDialog.setAnswerTxt(a);
        danCiTiMuAnswerDialog.setDeceTxt(getBean().getDesc());
        danCiTiMuAnswerDialog.setRight(isAnswerIsRight());
        danCiTiMuAnswerDialog.setRightBackAndWrongInterface(new AnswerDialog.RightBackAndWrongInterface() {
            @Override
            public int rightBack() {
                return R.mipmap.righttikuang;
            }

            @Override
            public int wrongback() {
                return R.mipmap.wrongtikuang;
            }
        });
        danCiTiMuAnswerDialog.setAnswerInterface(new AnswerDialog.AnswerInterface() {
            @Override
            public void nextClick() {
                nextItem();
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
                return R.mipmap.jiexi;
            }
        });
        assert getFragmentManager() != null;
        danCiTiMuAnswerDialog.show(getFragmentManager(), "answer");
    }

    @Override
    public int setlayoutResID() {
        return R.layout.fragment_dan_xuan;
    }
}
