package com.test720.grasshoppercollege.module.tanXian.TypeOfQuestion.englishBaiBian;

import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.widget.SeekBar;

import com.test720.grasshoppercollege.module.tanXian.TypeOfQuestion.BaseTanXianTiMuFragment;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

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
public abstract class BaseBaibianTiMu extends BaseTanXianTiMuFragment {

    public abstract SeekBar getSeekbar();


    @Override
    public void onStart() {
        super.onStart();
        getSeekbar().setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
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
        if (getBean() == null) return false;
        if (getBean().getVoice() == null) return false;
        if (getBean().getVoice().equals("")) return false;
        play(getBean().getVoice());
        return true;
    }

    /*mp3播放*/
    MediaPlayer mediaPlayer;

    boolean preparing = false;//是否缓冲中

    /*播放*/
    public void play(String url) {
        if (url == null || url.equals("")) {
            return;
        }
        try {
            if (mediaPlayer != null) {
                if (preparing) {
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
            //开始缓存播放
            mediaPlayer.prepareAsync();
            preparing = true;
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    preparing = false;
                    mp.start();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaPlayerToSeekBar();
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


    /*将播放进度展示到seek*/
    private Timer mTimer; // 计时器

    class MyTime extends TimerTask {

        @Override
        public void run() {
            if (mediaPlayer == null || getSeekbar() == null)
                return;
            try {
                if (mediaPlayer.isPlaying() && !getSeekbar().isPressed()) {
                    Message message = new Message();
                    message.what = 790;
                    handler.sendMessage(message); // 发送消息
                }
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        }
    }

    private void mediaPlayerToSeekBar() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
        mTimer = new Timer();
        mTimer.schedule(new MyTime(), 0, 1000);
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                @Override
                public void onBufferingUpdate(MediaPlayer mp, int percent) {
                    getSeekbar().setSecondaryProgress(percent);
                }
            });
        }
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

    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

}
