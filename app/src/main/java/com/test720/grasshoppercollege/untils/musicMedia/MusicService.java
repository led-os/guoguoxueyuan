package com.test720.grasshoppercollege.untils.musicMedia;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;

import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.NotificationBroadcastReceiver;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.youEr.baoBaoTingTing.bean.GuShiNeiRongData;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

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
 * Created by 水东流 on 2018/8/13.
 */

public class MusicService extends Service {


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }


    @Override
    public void onDestroy() {
        CurrencyEvent currencyEvent = new CurrencyEvent(CurrencyEvent.BAOBAOPAUSE, "停止播放");
        EventBus.getDefault().post(currencyEvent);
        if (mBinder != null) mBinder.closeMedia();
        if (notificationManager != null) notificationManager.cancel(111);
        super.onDestroy();
    }

    public class MyBinder extends Binder {

        /**
         * 是否正在播放
         */
        public boolean isPlay() {
            try {
                if (mMediaPlayer.isPlaying()) {
                    return true;
                }
            } catch (IllegalStateException e) {
                return false;
            }
            return false;
        }

        /**
         * 设置是否单曲循环
         */
        public void setSpeed(boolean speed) {
            MusicServiceData.getInstance().setSpeed(speed);
        }


        /**
         * 更换播放数据
         *
         * @param
         */
        public void setData(GuShiNeiRongData data) {
            MusicServiceData.getInstance().setInfo(data.getData().getInfo());
            MusicServiceData.getInstance().getList().clear();
            MusicServiceData.getInstance().getList().addAll(data.getData().getList());
            if (mMediaPlayer != null) {
                mMediaPlayer.reset();
            }
            MusicServiceData.getInstance().setIndex(Integer.parseInt(data.getData().getInfo().getSubscript()));
            iniMediaPlayerFile();
            if (notification == null) {
                initNotification();
            }
            updateNotification(0);
        }

        /**
         * 播放音乐
         */
        public void playMusic() {
            if (!mMediaPlayer.isPlaying()) {
                //如果还没开始播放，就开始
                mMediaPlayer.start();
            }
            updateNotification(1);
        }

        /**
         * 暂停播放
         */
        public void pauseMusic() {
            if (mMediaPlayer.isPlaying()) {
                //如果还没开始播放，就开始
                mMediaPlayer.pause();
                CurrencyEvent currencyEvent = new CurrencyEvent(CurrencyEvent.BAOBAOPAUSE, "开始播放");
                EventBus.getDefault().post(currencyEvent);
            }
            updateNotification(2);
        }

        /**
         * 重新加载
         */
        public void resetMusic() {
            if (mMediaPlayer != null) {
                //如果还没开始播放，就开始
                mMediaPlayer.reset();
                iniMediaPlayerFile();
            }
        }

        /**
         * 关闭播放器
         */
        public void closeMedia() {
            if (mMediaPlayer != null) {
                mMediaPlayer.stop();
                mMediaPlayer.release();
            }
            CurrencyEvent currencyEvent = new CurrencyEvent(CurrencyEvent.BAOBAOPAUSE, "停止播放");
            EventBus.getDefault().post(currencyEvent);
        }

        /**
         * 下一首
         */
        public void nextMusic() {
            if (mMediaPlayer != null
                    && MusicServiceData.getInstance().getIndex() < MusicServiceData.getInstance().getList().size()
                    && MusicServiceData.getInstance().getIndex() >= 0) {
                //切换歌曲reset()很重要很重要很重要，没有会报IllegalStateException
                mMediaPlayer.reset();
                //这里的if只要是为了不让歌曲的序号越界
                if (MusicServiceData.getInstance().getIndex() == MusicServiceData.getInstance().getList().size() - 1) {
                    MusicServiceData.getInstance().setIndex(0);
                } else {
                    MusicServiceData.getInstance().setIndex(MusicServiceData.getInstance().getIndex() + 1);
                }
                iniMediaPlayerFile();
            }
        }

        /**
         * 上一首
         */
        public void preciousMusic() {
            if (mMediaPlayer != null && MusicServiceData.getInstance().getIndex() < MusicServiceData.getInstance().getList().size() && MusicServiceData.getInstance().getIndex() > 0) {
                mMediaPlayer.reset();
                if (MusicServiceData.getInstance().getIndex() == 1) {
                    MusicServiceData.getInstance().setIndex(MusicServiceData.getInstance().getList().size() - 1);
                } else {
                    MusicServiceData.getInstance().setIndex(MusicServiceData.getInstance().getIndex() - 1);
                }
                iniMediaPlayerFile();
            }
        }

        /**
         * 获取歌曲长度
         **/
        public int getProgress() {
            return mMediaPlayer.getDuration();
        }

        /**
         * 获取播放位置
         */
        public int getPlayPosition() {
            return mMediaPlayer.getCurrentPosition();
        }

        /**
         * 播放指定位置
         */
        public void seekToPositon(int msec) {
            mMediaPlayer.seekTo(msec);
        }


    }

    /**
     * 添加file文件到MediaPlayer对象并且准备播放音频
     */
    private void iniMediaPlayerFile() {
        if (MusicServiceData.getInstance().getInfo() == null ||
                MusicServiceData.getInstance().getList().size() == 0) return;
        //获取文件路径
        try {
            //此处的两个方法需要捕获IO异常
            //设置音频文件到MediaPlayer对象中
            mMediaPlayer.setDataSource(MusicServiceData.getInstance().getPath());
            //让MediaPlayer对象准备
            //3 准备播放
            mMediaPlayer.prepareAsync();
            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                }
            });
            //自動切換下一曲F
            mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    if (MusicServiceData.getInstance().isNeedNext()) {
                        if (!MusicServiceData.getInstance().isSpeed()) {
                            mBinder.nextMusic();
                        } else {
                            mMediaPlayer.start();
                            mMediaPlayer.setLooping(true);
                        }
                    }
                }
            });
        } catch (IOException e) {
            Log.d(TAG, "设置资源，准备阶段出错");
            e.printStackTrace();
        }
        CurrencyEvent currencyEvent = new CurrencyEvent(CurrencyEvent.BAOBAOSTART, "开始播放");
        EventBus.getDefault().post(currencyEvent);
    }

    /**
     * 初始化通知栏
     */
    private NotificationManager notificationManager;
    static String PALYER_TAG;
    public Notification notification;

    private void initNotification() {
        /*通知栏的布局*/
        RemoteViews contentView = new RemoteViews(getPackageName(),
                R.layout.notification_control);
        contentView.setImageViewResource(R.id.iv_pic, R.mipmap.icon_app);//图片展示
        contentView.setImageViewResource(R.id.iv_play, R.mipmap.zant1);//button显示为正在播放

        /*暫停或繼續事件*/
        Intent notificationPause = new Intent(getApplicationContext(), NotificationBroadcastReceiver.class);
        notificationPause.setAction("notification_item");
        notificationPause.putExtra(NotificationBroadcastReceiver.TYPE, 1);
        PendingIntent intentPause = PendingIntent.getBroadcast(getApplicationContext(), 0, notificationPause, PendingIntent.FLAG_UPDATE_CURRENT);
        contentView.setOnClickPendingIntent(R.id.iv_play, intentPause);


        /*创建通知栏的点击事件*/
        Intent notificationIntent = new Intent(getApplicationContext(), NotificationBroadcastReceiver.class);
        notificationIntent.setAction("notification_clicked");
        notificationIntent.putExtra(NotificationBroadcastReceiver.TYPE, 1);
        PendingIntent intent = PendingIntent.getBroadcast(getApplicationContext(), 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        /*滑动删除通知栏后，广播监听*/
        Intent dele = new Intent(getApplicationContext(), NotificationBroadcastReceiver.class);
        dele.setAction("notification_cancelled");
        dele.putExtra(NotificationBroadcastReceiver.TYPE, 1);
        PendingIntent deletIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, dele, PendingIntent.FLAG_UPDATE_CURRENT);


        /*创建*/
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), "music");
        mBuilder.setContent(contentView)
                .setSmallIcon(R.mipmap.icon_app)
                .setWhen(System.currentTimeMillis())// 通知栏时间，一般是直接用系统的
                .setPriority(Notification.DEFAULT_ALL)//
                .setContentIntent(intent)//点击事件
                .setDeleteIntent(deletIntent);//滑动事件
        notification = mBuilder.build();
//        notification.flags = notification.FLAG_NO_CLEAR;//设置通知点击或滑动时不被清除
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(PALYER_TAG, 111, notification);//开启通知

    }


    /**
     * 更新状态栏
     *
     * @param type 图标样式：1是正在播放状态，2是停止状态； 3播放完成
     */

    public void updateNotification(int type) {
        if (notification == null) return;
        //【5】更新操作
        if (type == 1) {//播放中
            notification.contentView.setImageViewResource(R.id.iv_play, R.mipmap.zant2);
        } else {//暂停或者播放完成
            notification.contentView.setImageViewResource(R.id.iv_play, R.mipmap.zant1);
        }
        notification.contentView.setTextViewText(R.id.tv_title, MusicServiceData.getInstance().getTitle());
        notification.contentView.setTextViewText(R.id.tv_extra, MusicServiceData.getInstance().getZhuanJiCount());

        notificationManager.notify(PALYER_TAG, 111, notification);//开启通知
    }


    //初始化MediaPlayer
    public MediaPlayer mMediaPlayer = new MediaPlayer();
    private static final String TAG = "MediaService";
    private MyBinder mBinder = new MyBinder();

}
