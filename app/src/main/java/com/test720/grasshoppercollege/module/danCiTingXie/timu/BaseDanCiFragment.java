package com.test720.grasshoppercollege.module.danCiTingXie.timu;

import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;

import com.test720.grasshoppercollege.BaseTiMuOkFragment;
import com.test720.grasshoppercollege.HttpBean.FanYiNeiRongdata;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.musicMedia.MusicUntil;

/**
 * Created by 水东流 on 2018/7/3.
 */

public abstract class BaseDanCiFragment extends BaseTiMuOkFragment {

    FanYiNeiRongdata.DataBean.ListBean bean;//当前页数据
    int index;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
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
    public String GXShareTitile() {
        return "单词听写";
    }

    MusicUntil musicUntil;

    public void playVoic() {
        if (bean != null && bean.getQuestion_content() != null) play(bean.getQuestion_content());
    }

    @Override
    public void onStart() {
        super.onStart();
        if (musicUntil == null) {
            musicUntil = new MusicUntil(1f);
            laBa().setImageResource(R.drawable.la_ba);
            final AnimationDrawable anim = (AnimationDrawable) laBa().getDrawable();
            musicUntil.setStartListener(new MusicUntil.StartListener() {
                @Override
                public void startPlay() {
                    anim.start();
                }
            });
            musicUntil.setEndComListener(new MusicUntil.EndComListener() {
                @Override
                public void endListener() {
                    anim.stop();
                }
            });
            if (index == 0) playVoic();
        }
    }

    public abstract ImageView laBa();

    /*播放*/
    public void play(String s) {
        musicUntil.playMusic(s);
        /*显示进度*/
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stop();
        musicUntil = null;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (!isVisibleToUser) {
            stop();
        }
    }

    public void stop() {
        if (musicUntil != null) {
            musicUntil.canlce();
        }

    }

    public FanYiNeiRongdata.DataBean.ListBean getBean() {
        return bean;
    }

    public void setBean(FanYiNeiRongdata.DataBean.ListBean bean) {
        this.bean = bean;
    }

}
