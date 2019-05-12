package com.test720.grasshoppercollege.module.tingLiTest;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.musicMedia.MusicUntil;

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

public class BianYinFragment extends TingLiTiMuFragment {
    @BindView(R.id.dui)
    SeekBar dui;
    @BindView(R.id.cuo)
    SeekBar cuo;
    @BindView(R.id.answer)
    TextView answer;
    @BindView(R.id.dece)
    TextView dece;
    @BindView(R.id.righOrWrong)
    TextView righOrWrong;
    @BindView(R.id.next)
    TextView next;
    @BindView(R.id.answerLin)
    LinearLayout answerLin;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.answerF)
    ImageView answerF;
    @BindView(R.id.answerImg)
    ImageView answerImg;

    MusicUntil musicUntil = new MusicUntil();


    @Override
    public void onDestroy() {
        super.onDestroy();
        musicUntil.canlce();
    }

    @Override
    public void initData() {

    }

    @Override
    public void setListener() {
        dui.setMax(100);
        cuo.setMax(100);
        cuo.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (isAnswer) {
                    cuo.setProgress(90);
                    return;
                }
                if (seekBar.getProgress() < 35) {
                    answerF.setImageResource(R.mipmap.cuod);
                    cuo.setVisibility(View.GONE);
                    answerLin.setVisibility(View.VISIBLE);
                    if (getBean().getAnswer().equals("1")) {
                        setAnswerIsRight(false);
                        righOrWrong.setText("回答错误");
                        musicUntil.playRaw(getContext(), R.raw.wrong);
                        righOrWrong.setTextColor(getResources().getColor(R.color.red));
                        getBaseTiMuActivity().setFen(getBaseTiMuActivity().getFen() - 1);
                    } else {
                        setAnswerIsRight(true);
                        righOrWrong.setText("回答正确");
                        musicUntil.playRaw(getContext(), R.raw.righten);
                        righOrWrong.setTextColor(getResources().getColor(R.color.green));
                    }
                    isAnswer = true;
                } else {
                    cuo.setProgress(90);
                }

            }
        });

        dui.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (isAnswer) {
                    dui.setProgress(10);
                    return;
                }
                if (seekBar.getProgress() > 65) {
                    answerF.setImageResource(R.mipmap.duid);
                    dui.setVisibility(View.GONE);
                    answerLin.setVisibility(View.VISIBLE);
                    if (getBean().getAnswer().equals("0")) {
                        setAnswerIsRight(false);
                        righOrWrong.setText("回答错误");
                        musicUntil.playRaw(getContext(), R.raw.wrong);
                        righOrWrong.setTextColor(getResources().getColor(R.color.red));
                        getBaseTiMuActivity().setFen(getBaseTiMuActivity().getFen() - 1);
                    } else {
                        setAnswerIsRight(true);
                        righOrWrong.setText("回答正确");
                        musicUntil.playRaw(getContext(), R.raw.righten);
                        righOrWrong.setTextColor(getResources().getColor(R.color.green));
                    }
                    isAnswer = true;
                } else {
                    dui.setProgress(10);
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getBean() != null) {
            getBean().setQuestion_content(getBean().getQuestion_content().replace("\\n", "\n"));
            title.setText(getBean().getQuestion_content());
            dece.setText(getBean().getDesc());
            if (getBean().getAnswer().equals("0")) {
                answerImg.setImageResource(R.mipmap.cuod);
            } else {
                answerImg.setImageResource(R.mipmap.duid);
            }

        }
    }

    @Override
    public int setlayoutResID() {
        return R.layout.fragment_bian_yin;
    }

    @Override
    public void getSuccess(String s, int what) {

    }


    @OnClick({R.id.moudlRel, R.id.next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.moudlRel:
                break;
            case R.id.next:
                //*切换到下一题*//
                if (getViewPager().getAdapter().getCount() - 1 > getViewPager().getCurrentItem()) {
                    getViewPager().setCurrentItem(getViewPager().getCurrentItem() + 1);
                } else {
                    AddGuoGuoDou();
                }
                break;
        }
    }


}
