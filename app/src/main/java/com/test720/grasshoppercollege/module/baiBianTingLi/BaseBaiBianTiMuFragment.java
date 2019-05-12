package com.test720.grasshoppercollege.module.baiBianTingLi;

import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.widget.SeekBar;

import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.BaseTiMuOkFragment;
import com.test720.grasshoppercollege.HttpBean.BaiBianTingLiTiMuData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.LogUtil;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by 水东流 on 2018/6/26.
 */

public abstract class BaseBaiBianTiMuFragment extends BaseTiMuOkFragment {

    BaiBianTingLiTiMuData.DataBean dataBean;

    public void setDataBean(BaiBianTingLiTiMuData.DataBean dataBean) {
        this.dataBean = dataBean;
    }

    public BaiBianTingLiTiMuData.DataBean getDataBean() {
        return dataBean;
    }

    int index;

    public abstract SeekBar getSeekbar();

    @Override
    public void onStart() {
        super.onStart();
        if (getSeekbar() != null) /*快进监听*/
            getSeekbar().setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if (fromUser) {
                        int duration = mediaPlayer.getDuration();
                        int lg = progress * duration / seekBar.getMax();
                        mediaPlayer.seekTo(lg);
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
    }

    /**
     * 播放音频
     */
    public boolean startPlay() {
        if (getDataBean() == null) return false;
        play(getDataBean().getVoice());
        return true;
    }

    @Override
    public void initData() {

    }

    @Override
    public String status() {
        return null;
    }

    @Override
    public void TongGuan() {
        BaiBianTiMuActivity baiBianTiMuActivity = (BaiBianTiMuActivity) getBaseTiMuActivity();
        /***标记当前关卡已通过***/
        HttpParams httpParams1 = new HttpParams();
        httpParams1.put("uid", MyApplication.getmInstance().getUid());
        httpParams1.put("fraction", getBaseTiMuActivity().getFen());
        httpParams1.put("listening_id", getBaseTiMuActivity().getId());
        httpParams1.put("foot", "foot" + baiBianTiMuActivity.getFeetType());
        post(HttpUntil.GetIntent().EnListeningadopt(), httpParams1, 124);
    }

    @Override
    public String GXShareTitile() {
        return "百辩听力";
    }

    /*mp3播放*/
    MediaPlayer mediaPlayer;

    /*播放*/
    boolean prepareing = false;

    public void play(String url) {
        if (url == null || url.equals("")) {
            return;
        }
        try {
            if (mediaPlayer != null) {
                if (prepareing) {
                    ShowToast("努力加载音频中，请稍后!");
                    return;
                }
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
            mediaPlayer = null;
        }
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(url); // 设置数据源
            //开始播放
            mediaPlayer.prepareAsync();
            prepareing = true;
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                    prepareing = false;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayerToSeekBar();
    }

    /*将播放进度展示到seek*/
    private Timer mTimer; // 计时器

    class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            if (mediaPlayer == null || getSeekbar() == null)
                return;
            try {
                if (mediaPlayer.isPlaying() && getSeekbar().isPressed() == false) {
                    Message message = new Message();
                    message.what = 790;
                    handler.sendMessage(message); // 发送消息
                }
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mTimer != null) {
            mTimer.cancel();
        }
    }

    private void mediaPlayerToSeekBar() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
        mTimer = new Timer();
        mTimer.schedule(new MyTimerTask(), 0, 1000);
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                @Override
                public void onBufferingUpdate(MediaPlayer mp, int percent) {
                    getSeekbar().setSecondaryProgress(percent);
                }
            });
        }
    }

    @Override
    public String corrId() {
        return null;
    }

    @Override
    public String questionId() {
        return null;
    }


    public Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 790) {
                try {
                    if (mediaPlayer == null) return true;
                    int position = mediaPlayer.getCurrentPosition();
                    int duration = mediaPlayer.getDuration();
                    if (duration > 0) {
                        // 计算进度（获取进度条最大刻度*当前音乐播放位置 / 当前音乐时长）
                        if (getSeekbar() != null) {
                            long pos = getSeekbar().getMax() * position / duration;
                            getSeekbar().setProgress((int) Math.floor(pos));
                        }
                    }

                } catch (IllegalStateException e) {
                    e.printStackTrace();
                }
            }
            return true;
        }
    });


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (!isVisibleToUser) {
            LogUtil.logError("停止播放");
            stop();
        } else {
            startPlay();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stop();
    }

    public int getPostion() {
        return index;
    }

    public void setPostion(int postion) {
        this.index = postion;
    }

    public void stop() {
        if (mTimer != null) {
            mTimer.cancel();
        }
        try {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }
}
