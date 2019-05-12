package com.test720.grasshoppercollege.myViews;

import android.app.Dialog;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 水东流 on 2018/8/3.
 */

public class CiDaiBoFangDialog extends DialogFragment {


    String url;//地址
    float speedF = 1;//语速
    private MediaPlayer mediaPlayer;
    private Timer mTimer; // 计时器


    @Override
    public void onStart() {
        super.onStart();
        play();
        mediaPlayerToSeekBar();
        /*快进监听*/
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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

    /*播放*/
    private void play() {
        if (url == null || url.equals("") || url.equals(HttpUntil.GetIntent().filePathHead(getContext()))) {
            return;
        }
        try {
            if (mediaPlayer != null) {
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
            mediaPlayer.prepare();
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mediaPlayer.start();
                    mediaPlayer.setLooping(true);
                }
            });
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//语速调节，只支持23以上api
                mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(getSpeedF()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*将播放进度展示到seek*/
    private void mediaPlayerToSeekBar() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    if (mediaPlayer != null && mediaPlayer.isPlaying() && !seekBar.isPressed()) {
                        Message message = new Message();
                        message.what = 790;
                        mainHandler.sendMessage(message); // 发送消息
                    }
                } catch (IllegalStateException e) {
                }
            }
        }, 0, 1000);

        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            long end = mediaPlayer.getDuration();
            endTime.setText(formatLongToTimeStr(end));
            mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                @Override
                public void onBufferingUpdate(MediaPlayer mp, int percent) {
                    seekBar.setSecondaryProgress(percent);
                }
            });
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            // 为解决第二次播放时抛出的IllegalStateException，这里做了try-catch处理
            try {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            } catch (IllegalStateException e) {
                mediaPlayer = null;
            }

        }

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        Dialog dialog = new Dialog(getActivity(), R.style.NoAdminBottomDialog);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        dialog.setContentView(R.layout.ci_dai_bo_dialog);
        dialog.setCanceledOnTouchOutside(true); // 外部点击取消

        // 设置宽度为屏宽, 靠近屏幕底部。
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM; // 紧贴底部
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        window.setAttributes(lp);
        ButterKnife.bind(this, dialog); // Dialog即View
        return dialog;
    }

    /*主线程*/
    Handler mainHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 790) {
                try {
                    int position = mediaPlayer.getCurrentPosition();
                    int duration = mediaPlayer.getDuration();
                    if (duration > 0) {
                        // 计算进度（获取进度条最大刻度*当前音乐播放位置 / 当前音乐时长）
                        long pos = seekBar.getMax() * position / duration;
                        seekBar.setProgress((int) Math.floor(pos));
                        long start = position;
                        startTime.setText(formatLongToTimeStr(start));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return false;
        }
    });

    @OnClick({R.id.play})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.play:
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    play.setImageResource(R.mipmap.huibenluyinmy);
                    mediaPlayer.pause();
                } else {
                    play.setImageResource(R.mipmap.huibenzanting);
                    mediaPlayer.start();
                }
                break;
        }
    }

    /**
     * 毫秒数转   00:00
     *
     * @param l
     * @return
     */
    private static String formatLongToTimeStr(Long l) {
        int minute = 0;
        int second = 0;

        second = l.intValue() / 1000;

        if (second > 60) {
            minute = second / 60;
            second = second % 60;
        }
        if (minute > 60) {
//            hour = minute / 60;
            minute = minute % 60;
        }
        return (getTwoLength(minute) + ":" + getTwoLength(second));
    }

    private static String getTwoLength(final int data) {
        if (data < 10) {
            return "0" + data;
        } else {
            return "" + data;
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public float getSpeedF() {
        return speedF;
    }

    public void setSpeedF(float speedF) {
        this.speedF = speedF;
    }

    @BindView(R.id.startTime)
    TextView startTime;
    @BindView(R.id.seekBar)
    SeekBar seekBar;
    @BindView(R.id.endTime)
    TextView endTime;
    @BindView(R.id.play)
    ImageView play;
    @BindView(R.id.ciDaiLin)
    LinearLayout ciDaiLin;
}
