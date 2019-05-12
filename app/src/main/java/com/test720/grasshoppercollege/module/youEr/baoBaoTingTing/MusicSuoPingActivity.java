package com.test720.grasshoppercollege.module.youEr.baoBaoTingTing;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.luYinUntil.TimeUtils;
import com.test720.grasshoppercollege.untils.musicMedia.MusicService;

import butterknife.BindView;
import butterknife.OnClick;

public class MusicSuoPingActivity extends BaseToolActivity {
    private Handler mHandler = new Handler();
    Intent MediaServiceIntent;
    private MusicService.MyBinder mMyBinder;
    private ServiceConnection mServiceConnection;
    /**
     * 更新ui的runnable，进度条
     */
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            if (mMyBinder.isPlay()) {
                int position = mMyBinder.getPlayPosition();
                int duration = mMyBinder.getProgress();
                if (duration > 0) {
                    // 计算进度（获取进度条最大刻度*当前音乐播放位置 / 当前音乐时长）
                    seekbar.setMax(duration);
                    seekbar.setProgress(position);
                    start.setText(TimeUtils.long2String(mMyBinder.getPlayPosition()));
                    end.setText(TimeUtils.long2String(mMyBinder.getProgress()));
                }
            }
            mHandler.postDelayed(mRunnable, 1000);
        }
    };

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_music_suo_ping;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mRunnable != null) {
            mHandler.removeCallbacks(mRunnable);
        }
    }

    @Override
    protected void initData() {
        mServiceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                mMyBinder = (MusicService.MyBinder) service;
                seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        //这里很重要，如果不判断是否来自用户操作进度条，会不断执行下面语句块里面的逻辑，然后就会卡顿卡顿
                        if (fromUser) {
                            mMyBinder.seekToPositon(seekBar.getProgress());
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });
                mHandler.postDelayed(mRunnable, 1000);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
            }
        };
        MediaServiceIntent = new Intent(mcontext, MusicService.class);
        //判断权限够不够，不够就给
        if (ContextCompat.checkSelfPermission(mcontext, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(mcontext, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, 1);
        } else {
            //够了就设置路径等，准备播放
            bindService(MediaServiceIntent, mServiceConnection, BIND_AUTO_CREATE);
        }
    }

    @Override
    public void GetHttpBackStr(String str, int what) {

    }


    @OnClick({R.id.precious, R.id.play, R.id.next, R.id.jiesuo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.precious:
                mMyBinder.preciousMusic();
                break;
            case R.id.play:
                if (mMyBinder.isPlay()) {
                    mMyBinder.pauseMusic();
                    play.setImageResource(R.mipmap.zant1);
                } else {
                    mMyBinder.playMusic();
                    play.setImageResource(R.mipmap.zant2);
                }
                break;
            case R.id.next:
                mMyBinder.nextMusic();
                break;
            case R.id.jiesuo:
                finish();
                break;
        }
    }

    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.seekbar)
    SeekBar seekbar;
    @BindView(R.id.start)
    TextView start;
    @BindView(R.id.end)
    TextView end;
    @BindView(R.id.precious)
    ImageView precious;
    @BindView(R.id.play)
    ImageView play;
    @BindView(R.id.next)
    ImageView next;
}
