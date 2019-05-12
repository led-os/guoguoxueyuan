package com.test720.grasshoppercollege.module.tanXian.TypeOfQuestion;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.test720.grasshoppercollege.BaseFragment;
import com.test720.grasshoppercollege.BaseTiMuActivity;
import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.tanXian.bean.TanXianTiMuBean;
import com.test720.grasshoppercollege.untils.LogUtil;
import com.test720.grasshoppercollege.untils.musicMedia.MusicUntil;

import org.greenrobot.eventbus.EventBus;

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
 * 作者：水东流 编于 2018/11/10
 */
public abstract class BaseTanXianTiMuFragment extends BaseFragment {

    public abstract void startShow();//展现ui

    BaseTiMuActivity baseTiMuActivity;

    TanXianTiMuBean.DataBean bean;
    boolean isAnswer = false;
    boolean answerIsRight = true;
    MusicUntil musicUntil;

    public MusicUntil getMusicUntil() {
        return musicUntil;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.logError("oncreat" + bean);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        LogUtil.logError("oncreatview" + bean);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtil.logError("fen" + getBaseTiMuActivity().getFen());
//        if (musicUntil != null) musicUntil.prepare(getBean().getVoice());
        startShow();
    }

    @Override
    public void initData() {
        musicUntil = new MusicUntil(1f);
    }

    @Override
    public void setListener() {
//        LogUtil.logError("listener" + bean);
    }

    @Override
    public void getSuccess(String s, int what) {

    }


    /**
     * 播放音频
     */
    public void play() {
        if (getBean() != null && getBean().getVoice() != null && !getBean().getVoice().equals("")) {
            musicUntil.playMusic(getBean().getVoice());
        }
    }

    public void play(ImageView imageView) {
        if (getBean() != null && getBean().getVoice() != null && !getBean().getVoice().equals("")) {
            imageView.setImageResource(R.drawable.la_ba);
            final AnimationDrawable anim = (AnimationDrawable) imageView.getDrawable();
            musicUntil.setEndComListener(new MusicUntil.EndComListener() {
                @Override
                public void endListener() {
                    anim.stop();
                }
            });
            musicUntil.setStartListener(new MusicUntil.StartListener() {
                @Override
                public void startPlay() {
                    anim.start();
                }
            });
            musicUntil.playMusic(getBean().getVoice());
        }
    }

    /**
     * 停止播放
     */
    public void stop() {
        if (musicUntil != null)
            musicUntil.canlce();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (!isVisibleToUser) {
            stop();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        stop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stop();
        if (musicUntil != null) musicUntil = null;
    }

    /**
     * 题目完成，切换到下一题
     */


    public void nextItem() {
        CurrencyEvent currencyEvent = new CurrencyEvent(CurrencyEvent.TANXIANTIMU, "题目完成");
        EventBus.getDefault().post(currencyEvent);
    }


    public boolean isAnswer() {
        return isAnswer;
    }

    public void setAnswer(boolean answer) {
        isAnswer = answer;
    }

    public boolean isAnswerIsRight() {
        return answerIsRight;
    }

    public void setAnswerIsRight(boolean answerIsRight) {
        this.answerIsRight = answerIsRight;
    }

    public void setBean(TanXianTiMuBean.DataBean bean) {
        this.bean = bean;
    }

    public TanXianTiMuBean.DataBean getBean() {
        return bean;
    }

    public BaseTiMuActivity getBaseTiMuActivity() {
        return baseTiMuActivity;
    }

    public void setBaseTiMuActivity(BaseTiMuActivity baseTiMuActivity) {
        this.baseTiMuActivity = baseTiMuActivity;
    }

}
