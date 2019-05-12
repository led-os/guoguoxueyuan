package com.test720.grasshoppercollege.module.yuWen.tingXieZhuShou;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.HttpBean.TingXieTiMuData;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.myViews.TextDialog;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;

public class TingXieTiMuActivity extends BaseToolActivity {


    @BindView(R.id.back)
    RelativeLayout back;

    int fen = 100;
    int type = 1;//题目类型
    int listening_id = 1;
    @BindView(R.id.seekBar)
    SeekBar seekBar;
    @BindView(R.id.play)
    ImageView play;
    @BindView(R.id.ciDaiLin)
    LinearLayout ciDaiLin;
    TingXieTiMuData data;
    @BindView(R.id.answer)
    ImageView answer;
    @BindView(R.id.title)
    TextView title;
    private Timer mTimer = new Timer(); // 计时器
    int click = 0;

    public int getFen() {
        return fen;
    }

    public void setFen(int fen) {
        this.fen = fen;
    }

    private MediaPlayer mediaPlayer;

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.ting_xie_ti_mu_back;
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        type = intent.getIntExtra("type", 1);
        listening_id = intent.getIntExtra("id", 1);
        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("type", type + "");//请求参数一
        formBuilder.add("listen_id", listening_id + "");//请求参数一
        Post(HttpUntil.GetIntent().ChineseListeningcontent(), formBuilder, 1);

        switch (type) {
            case 1:
                title.setText("生字听写");
                break;
            case 2:
                title.setText("拼音听写");
                break;
            case 3:
                title.setText("词语听写");
                break;
            case 4:
                title.setText("生字本听写");
                break;
        }

         /*快进监听*/
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                try {
                    if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                        int duration = mediaPlayer.getDuration();
                        int lg = progress * duration / seekBar.getMax();
                        mediaPlayer.seekTo(lg);
                    }
                } catch (IllegalStateException e) {
                    e.printStackTrace();
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

    @Override
    public void GetHttpBackStr(String str, int what) {
        if (codeIsOne(str)) {
            data = new Gson().fromJson(str, TingXieTiMuData.class);
            if (data != null) {
                playUrl(data.getData().getVoice());
                Glide.with(this).load(data.getData().getWord_pic()).into(answer);
            }
        }
    }


    /*主线程方法*/
    @Override
    public void MainHandlerVoid(Message msg) {
        super.MainHandlerVoid(msg);
        if (msg.what == 790) {
            int position = mediaPlayer.getCurrentPosition();
            int duration = mediaPlayer.getDuration();
            if (duration > 0) {
                // 计算进度（获取进度条最大刻度*当前音乐播放位置 / 当前音乐时长）
                long pos = seekBar.getMax() * position / duration;
                seekBar.setProgress((int) Math.floor(pos));
            }
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
            }
        }
    };

    /*初始化播放器和进度条关联 */
    public void initPlay() {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                seekBar.setSecondaryProgress(percent);
            }
        });
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });
    }

    public void playUrl(String url) {
        play.setImageResource(R.mipmap.magnetic_tape_play);
        try {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
                mediaPlayer = new MediaPlayer();
            } else {
                initPlay();
            }
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    play.setImageResource(R.mipmap.magnetic_tape_stop);
                    seekBar.setProgress(seekBar.getMax());
                    showGongXiPop();
                }
            });

            mediaPlayer.setDataSource(url); // 设置数据源
            mediaPlayer.prepare();
            mediaPlayer.start();// prepare自动播放
            mediaPlayerToSeekBar();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("1111111111111111111111", "" + e);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            if (mediaPlayer != null) {
                mediaPlayer.release();
                mediaPlayer.stop();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*将播放进度展示到seek*/
    private void mediaPlayerToSeekBar() {
        if (mediaPlayer != null) {
            mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                @Override
                public void onBufferingUpdate(MediaPlayer mp, int percent) {
                    seekBar.setSecondaryProgress(percent);
                }
            });
            mTimer.schedule(timerTask, 0, 500);
        }
    }

    @OnClick({R.id.back, R.id.play})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.play:
                try {
                    if (click == 1) {
                        click = 0;
                        play.setImageResource(R.mipmap.magnetic_tape_play);
                        mediaPlayer.start();
                    } else {
                        click = 1;
                        play.setImageResource(R.mipmap.magnetic_tape_stop);
                        mediaPlayer.pause();
                    }
                } catch (Exception e) {

                }
                break;
        }
    }

    /*恭喜弹框*/
    public void showGongXiPop() {
        TextDialog textDialog = new TextDialog();
        textDialog.setTextStr("恭喜你，获得" + data.getData().getPoints() + "果果豆");
        textDialog.setOkText("查看答案");
        textDialog.setOkClick(new TextDialog.OkClick() {
            @Override
            public void click() {
                answer.setVisibility(View.VISIBLE);
                ciDaiLin.setVisibility(View.GONE);
                title.setText("答案");
            }

            @Override
            public void no() {

            }
        });
        textDialog.show(getSupportFragmentManager().beginTransaction(), "text");
    }


}