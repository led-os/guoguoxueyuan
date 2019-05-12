package com.test720.grasshoppercollege.module.meiRiYiJu;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;

import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.bean.RangBean;
import com.test720.grasshoppercollege.fragment.BaseTiMuFragment;
import com.test720.grasshoppercollege.untils.musicMedia.MusicUntil;

import java.util.ArrayList;

/**
 * Created by 水东流 on 2018/6/28.
 */

public abstract class MeiRiYiJuFragment extends BaseTiMuFragment {

    boolean answerIsRight = true;
    MusicUntil musicUntil;

    private ArrayList<RangBean> ranges = new ArrayList<>();

    @Override
    public String SharePoint() {
        return null;
    }
    @Override
    public void ShareAddGuoGuo() {

    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        musicUntil = new MusicUntil(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

            }
        }, 1f);
    }


    /*确认答案F*/
    protected abstract boolean answerOk();

    @Override
    public LinearLayout answerLin() {
        return null;
    }

    @Override
    public HttpParams CuoTiHttpParams() {
        return null;
    }

    @Override
    public String corrId() {
        return null;
    }

    @Override
    public String questionId() {
        return null;
    }

    @Override
    public String status() {
        return null;
    }

    @Override
    public void AddGuoGuoDou() {

    }

    public void playUrl(String url) {
        musicUntil.playMusic(url);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (musicUntil != null) musicUntil.canlce();
    }

    @Override
    public boolean isAnswerIsRight() {
        return answerIsRight;
    }

    @Override
    public void setAnswerIsRight(boolean answerIsRight) {
        this.answerIsRight = answerIsRight;
    }

    public ArrayList<RangBean> getRanges() {
        return ranges;
    }
    public void setRanges(ArrayList<RangBean> ranges) {
        this.ranges = ranges;
    }


}
