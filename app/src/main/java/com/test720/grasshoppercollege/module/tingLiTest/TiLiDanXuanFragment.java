package com.test720.grasshoppercollege.module.tingLiTest;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.publicQuestion.AnswerDialog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * _oo0oo_
 * o8888888o
 * 88" . "88
 * (| -_- |)
 * 0\  =  /0
 * ___/`---'\___
 * .' \\|     |// '.
 * / \\|||  :  |||// \
 * / _||||| -卍-|||||- \
 * |   | \\\  -  /// |   |
 * | \_|  ''\---/''  |_/ |
 * \  .-\__  '-'  ___/-. /
 * ___'. .'  /--.--\  `. .'___
 * ."" '<  `.___\_<|>_/___.' >' "".
 * | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 * \  \ `_.   \_ __\ /__ _/   .-` /  /
 * =====`-.____`.___ \_____/___.-`___.-'=====
 * `=---='
 * 佛祖保佑        永无BUG
 * 佛曰:
 * 程序园里程序天，程序天里程序员；
 * 程序猿人写程序，又拿程序换肉钱。
 * 肉饱继续桌前坐，饱暖还是桌前眠；
 * 半迷半醒日复日，码上码下年复年。
 * 但愿叱咤互联世，不愿搬砖码当前；
 * 诸葛周瑜算世事，我来算出得加钱。
 * 别人笑我忒直男，我笑自己太像猿；
 * 但见成都府国内，处处地地程序员。
 * Created by 水东流 on 2018/3/21 0021.
 */

public class TiLiDanXuanFragment extends TingLiTiMuFragment {
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
    public void initData() {

    }

    @Override
    public void setListener() {

    }

    @Override
    public int setlayoutResID() {
        return R.layout.fragment_dan_xuan;
    }

    @Override
    public void onStart() {
        super.onStart();

        if (getBean() == null) {
            return;
        }
        one.setText("A\t\t\t" + getBean().getSelect_a());
        two.setText("B\t\t\t" + getBean().getSelect_b());
        three.setText("C\t\t\t" + getBean().getSelect_c());
        four.setText("D\t\t\t" + getBean().getSelect_d());
        timu.setText(getBean().getQuestion_content());


    }

    @OnClick({R.id.one, R.id.oneImg, R.id.two, R.id.twoImg, R.id.three, R.id.threeImg, R.id.four, R.id.fourImg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.one:
                one.setTextColor(getResources().getColor(R.color.green));
                answer("A");
                break;
            case R.id.oneImg:
                break;
            case R.id.two:
                two.setTextColor(getResources().getColor(R.color.green));
                answer("B");
                break;
            case R.id.twoImg:
                break;
            case R.id.three:
                three.setTextColor(getResources().getColor(R.color.green));
                answer("C");
                break;
            case R.id.threeImg:
                break;
            case R.id.four:
                four.setTextColor(getResources().getColor(R.color.green));
                answer("D");
                break;
            case R.id.fourImg:

                break;
        }
    }


    /*答题*/
    private void answer(String str) {
        if (isAnswer) return;
        if (str.equals(getBean().getAnswer())) {
            setAnswerIsRight(true);
        } else {
            setAnswerIsRight(false);
            getBaseTiMuActivity().setFen(getBaseTiMuActivity().getFen() - 1);
        }
        isAnswer = true;

        AnswerDialog danCiTiMuAnswerDialog = new AnswerDialog();
        String a = bean.getAnswer();
        danCiTiMuAnswerDialog.setAnswerTxt(a);
        danCiTiMuAnswerDialog.setDeceTxt(bean.getDesc());
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
        danCiTiMuAnswerDialog.setRight(answerIsRight);
        danCiTiMuAnswerDialog.setAnswerInterface(new AnswerDialog.AnswerInterface() {
            @Override
            public void nextClick() {
                if (getViewPager().getAdapter() == null) return;
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
                return R.mipmap.jiexi;
            }
        });
        assert getFragmentManager() != null;
        danCiTiMuAnswerDialog.show(getFragmentManager(), "answer");
    }
}
