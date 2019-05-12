package com.test720.grasshoppercollege.myViews;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.luYinUntil.AudioRecoderUtils;
import com.test720.grasshoppercollege.untils.luYinUntil.PopupWindowFactory;
import com.test720.grasshoppercollege.untils.luYinUntil.TimeUtils;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 水东流 on 2018/6/25.
 */

public class DianDuSeekDialog extends DialogFragment {

    String yuanurl;//原地址

    public void setYuanurl(String yuanurl) {
        this.yuanurl = yuanurl;
    }

    private MediaPlayer mediaPlayer;
    private Timer mTimer; // 计时器
    String newFilePath;//录音存放地址
    private AudioRecoderUtils mAudioRecoderUtils;
    private PopupWindowFactory mPop;
    private ImageView mImageView;
    private TextView mTextView;
    float speedF = 1;//语速
    String cn;
    boolean isDan = false;//是否单曲循环
    boolean isFan = false;//是否显示翻译
    boolean needLuYin = false;
    RelClick relClick;


    public void setRelClick(RelClick relClick) {
        this.relClick = relClick;
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams windowParams = window.getAttributes();
        windowParams.dimAmount = 0.0f;
        window.setAttributes(windowParams);

        play(yuanurl);

        mediaPlayerToSeekBar();
        initluYin();//初始化录音
        /*快进监听*/
        fanyitext.setText(getCn());
        /*是否显示翻译*/
        if (isFan) {
            fanyitext.setVisibility(View.VISIBLE);
        } else {
            fanyitext.setVisibility(View.GONE);
        }
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    try {
                        if (mediaPlayer != null) {
                            int duration = mediaPlayer.getDuration();
                            int lg = progress * duration / seekBar.getMax();
                            mediaPlayer.seekTo(lg);
                        }
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    }
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
    private void mediaPlayerToSeekBar() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
        mTimer = new Timer();
        mTimer.schedule(timerTask, 0, 1000);
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

    /**
     * 系统调用这个来获取DialogFragment的布局，不管是否显示为对话框或嵌入的片段.
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        Dialog dialog = new Dialog(getActivity(), R.style.NoTitleFullscreen);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        dialog.setContentView(R.layout.seek_dialog);
        dialog.setCanceledOnTouchOutside(true); // 外部点击取消

        // 设置宽度为屏宽, 靠近屏幕底部。
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM; // 紧贴底部
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT; // 宽度持平
        window.setAttributes(lp);
        ButterKnife.bind(this, dialog); // Dialog即View
        return dialog;
    }

    /*播放录音*/
    private void play(String url) {
        if (url == null || url.equals("") || url.equals(HttpUntil.GetIntent().filePathHead(getActivity()))) {
            return;
        }
        try {
            if (mediaPlayer != null) {
                mediaPlayer.reset();
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        mediaPlayer = new MediaPlayer();
        mediaPlayer.reset();
        try {
            mediaPlayer.setDataSource(getActivity(), Uri.parse(url)); // 设置数据源
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.prepare();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mediaPlayer.start();
                }
            });
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//语速调节，只支持23以上api
                mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(getSpeedF()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (isDan) {
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer arg0) {
                    mediaPlayer.start();
                    mediaPlayer.setLooping(true);
                }
            });
        } else {
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    if (!needLuYin) dismissAllowingStateLoss();
                }
            });
        }
    }

    /*录音*/
    public void initluYin() {
        //PopupWindow的布局文件
        View view = View.inflate(getActivity(), R.layout.layout_microphone, null);
        mPop = new PopupWindowFactory(getActivity(), view);
        //PopupWindow布局文件里面的控件
        mImageView = (ImageView) view.findViewById(R.id.iv_recording_icon);
        mTextView = (TextView) view.findViewById(R.id.tv_recording_time);

        mAudioRecoderUtils = new AudioRecoderUtils();
        //录音回调
        mAudioRecoderUtils.setOnAudioStatusUpdateListener(new AudioRecoderUtils.OnAudioStatusUpdateListener() {
            //录音中....db为声音分贝，time为录音时长
            @Override
            public void onUpdate(double db, long time) {
                mImageView.getDrawable().setLevel((int) (3000 + 6000 * db / 100));
                mTextView.setText(TimeUtils.long2String(time));
            }

            //录音结束，filePath为保存路径
            @Override
            public void onStop(String filePath) {
                newFilePath = filePath;
                mTextView.setText(TimeUtils.long2String(0));
            }
        });
        StartListener();
    }

    /*常按监听*/
    @SuppressLint("ClickableViewAccessibility")
    public void StartListener() {
        //Button的touch监听
        startLuYin.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mPop.showAtLocation(v, Gravity.CENTER, 0, -400);
                        mAudioRecoderUtils.startRecord();
                        break;
                    case MotionEvent.ACTION_UP:
                        mAudioRecoderUtils.stopRecord();    //结束录音（保存录音文件）
                        mPop.dismiss();
                        break;
                }
                return true;
            }
        });
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
                        long end = duration;
                        endTime.setText(formatLongToTimeStr(end));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return true;
        }
    });

    @OnClick({R.id.yuan, R.id.my, R.id.LinLuYin, R.id.rel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rel:
                dismissAllowingStateLoss();
                break;
            case R.id.LinLuYin:
                needLuYin = true;
                ciDaiLin.setVisibility(View.VISIBLE);
                LinLuYin.setVisibility(View.GONE);
                break;
            case R.id.yuan:
                play(yuanurl);
                break;
            case R.id.my:
                if (newFilePath == null) {
                    Toast.makeText(getActivity(), "请先录音", Toast.LENGTH_SHORT).show();
                    return;
                }
                play(newFilePath);
                break;
        }
    }


    public interface RelClick {
        void relClick();
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

    // 计时器
    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            if (mediaPlayer == null)
                return;
            try {
                if (mediaPlayer.isPlaying() && seekBar.isPressed() == false) {
                    Message message = new Message();
                    message.what = 790;
                    mainHandler.sendMessage(message); // 发送消息
                }
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        }
    };

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
        if (relClick != null) {
            relClick.relClick();
        }

    }

    public float getSpeedF() {
        return speedF;
    }

    public void setSpeedF(float speedF) {
        this.speedF = speedF;
    }

    public boolean isDan() {
        return isDan;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public void setDan(boolean dan) {
        isDan = dan;
    }

    public boolean isFan() {
        return isFan;
    }

    public void setFan(boolean fan) {
        isFan = fan;
    }

    @BindView(R.id.fanyitext)
    TextView fanyitext;
    @BindView(R.id.startTime)
    TextView startTime;
    @BindView(R.id.seekBar)
    SeekBar seekBar;
    @BindView(R.id.endTime)
    TextView endTime;
    @BindView(R.id.yuan)
    ImageView yuan;
    @BindView(R.id.startLuYin)
    ImageView startLuYin;
    @BindView(R.id.my)
    ImageView my;
    @BindView(R.id.boFangLin)
    LinearLayout boFangLin;
    @BindView(R.id.LinLuYin)
    LinearLayout LinLuYin;
    @BindView(R.id.ciDaiLin)
    LinearLayout ciDaiLin;


}
