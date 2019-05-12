package com.test720.grasshoppercollege.untils.musicMedia;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RawRes;
import android.widget.ImageView;

import com.test720.grasshoppercollege.untils.LogUtil;

import java.io.File;
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
 * 作者：水东流 编于 2018/8/27
 */
public class MusicUntil {
    private MediaPlayer mediaPlayer;
    private boolean isSpeed = false;//是否单曲循环
    MediaPlayer.OnCompletionListener onCompletionListener;
    AnimationDrawable animationDrawable;//动画
    /**
     * 播放完成监听
     */
    EndComListener endComListener;
    /**
     * 开始播放监听
     */
    StartListener startListener;

    float speed = 1f;//语速
    String url = "";//播放地址

    public MusicUntil() {
    }

    public MusicUntil(float speed) {
        this.speed = speed;
    }

    public MusicUntil(MediaPlayer.OnCompletionListener onCompletionListener, float speed) {
        this.onCompletionListener = onCompletionListener;
        this.speed = speed;
    }

    public MusicUntil(boolean isSpeed, MediaPlayer.OnCompletionListener onCompletionListener, float speed) {
        this.isSpeed = isSpeed;
        this.onCompletionListener = onCompletionListener;
        this.speed = speed;
    }


    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setIsSpeed(boolean speed) {
        isSpeed = speed;
        //如果不是单曲循环，重写播完监听
        if (!isSpeed && mediaPlayer != null) {
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    if (endComListener != null) endComListener.endListener();
                }
            });
        } else if (isSpeed && mediaPlayer != null) {
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mediaPlayer.start();
                    mediaPlayer.setLooping(true);
                    if (endComListener != null) endComListener.endListener();
                }
            });
        }
    }

    /**
     * 播放
     * 异步播放
     *
     * @param url
     */
    boolean preparing = false;//是否缓冲中

    public void playMusic(final String url) {
        if (url == null) return;
        if (url.equals("")) return;
        this.url = url;
        try {
            if (mediaPlayer != null) {
                if (preparing) {
                    return;
                }
                if (animationDrawable != null) animationDrawable.stop();
                if (endComListener != null) endComListener.endListener();
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            }
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setDataSource(url); // 设置数据源
                //开始播放
                mediaPlayer.prepareAsync();
                preparing = true;
                //设置语速
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//语速调节，只支持23以上api
                    mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(speed));
                }
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mediaPlayer.start();
                        preparing = false;
                    }
                });
                if (startListener != null) startListener.startPlay();
                addListener();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    /**
     * 包含动画的播放
     *
     * @param url
     * @param imageView
     */
    public void playHaveAm(String url, ImageView imageView) {
        if (url == null) return;
        if (url.equals("")) return;
        if (animationDrawable != null) animationDrawable.stop();
        animationDrawable = (AnimationDrawable) imageView.getDrawable();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        this.url = url;
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(url); // 设置数据源
        } catch (IOException e) {
            e.printStackTrace();
        }
        //开始播放
        mediaPlayer.prepareAsync();
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mediaPlayer.start();
                animationDrawable.start();
            }
        });
        setEndComListener(new EndComListener() {
            @Override
            public void endListener() {
                animationDrawable.stop();
            }
        });
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                animationDrawable.stop();
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            }
        });
        //设置语速
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//语速调节，只支持23以上api
            mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(speed));
        }
    }

    /**
     * 播放
     *
     * @param url
     */
    public void playMusic(Context context, final String url) {
        if (url == null) return;
        if (url.equals("")) return;
        if (this.url.equals(url) && mediaPlayer != null) {
            mediaPlayer.start();
            if (startListener != null) startListener.startPlay();
            return;
        }
        this.url = url;
        Uri uri = Uri.parse(url);
        try {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            }
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setDataSource(context, uri); // 设置数据源
                //开始播放
                mediaPlayer.prepare();
                //设置语速
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//语速调节，只支持23以上api
                    mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(speed));
                }
                mediaPlayer.start();
                if (startListener != null) startListener.startPlay();
                addListener();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    /**
     * 播放
     *
     * @param url
     */
    public void dianDuPlayMusic(Context context, final String url) {
        if (url == null) return;
        if (url.equals("")) return;
        //上一首还未结束时切换到下一首，需触发上一首的结束回调
        if (!this.url.equals(url) && mediaPlayer != null && mediaPlayer.isPlaying()) {
            if (endComListener != null) endComListener.endListener();
        }
        this.url = url;
        if (startListener != null) startListener.startPlay();
        Uri uri = Uri.parse(url);
        File file = new File(url);
        if (file.exists()) {
            LogUtil.logError("url" + url + "存在");
        } else {
            LogUtil.logError("url" + url + "不存在");
        }
        try {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            }
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setDataSource(context, uri); // 设置数据源
                mediaPlayer.prepare();
                //设置语速
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//语速调节，只支持23以上api
                    mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(speed));
                }
                mediaPlayer.start();
                //单曲循环
                if (isSpeed) {
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
                            if (endComListener != null) endComListener.endListener();
                            mediaPlayer.stop();
                            mediaPlayer.release();
                            mediaPlayer = null;
                        }
                    });
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }


    /**
     * 播放本地
     *
     * @param mContext、、
     */
    public void playRaw(Context mContext, @RawRes int resid) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        mediaPlayer = MediaPlayer.create(mContext, resid);
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if (endComListener != null) endComListener.endListener();
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            }
        });
    }

    /**
     * 添加监听
     */
    private void addListener() {
        //单曲循环
        if (isSpeed) {
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer arg0) {
                    mediaPlayer.start();
                    if (startListener != null) startListener.startPlay();
                    mediaPlayer.setLooping(true);
                }
            });
        } else {
            if (onCompletionListener != null)
                mediaPlayer.setOnCompletionListener(onCompletionListener);
            else
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        if (endComListener != null) endComListener.endListener();
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                });
        }
    }

    /**
     * 暂停
     */
    public boolean pauseOrStart() {
        if (mediaPlayer == null) return false;
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            return true;
        } else {
            mediaPlayer.start();
            return false;
        }
    }

    /**
     * 暂停播放
     */
    public void pause() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    /**
     * 是否在播放
     *
     * @return
     */
    public boolean isPlaying() {
        if (mediaPlayer == null) return false;
        if (mediaPlayer.isPlaying()) return true;
        return false;
    }


    /**
     * 取消
     */
    public void canlce() {
        if (mediaPlayer != null) {
            // 为解决第二次播放时抛出的IllegalStateException，这里做了try-catch处理
            try {
                if (endComListener != null) endComListener.endListener();
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            } catch (IllegalStateException e) {
                mediaPlayer = null;
            }
        }
    }

    public String getUrl() {
        return url;
    }


    public void setEndComListener(EndComListener endComListener) {
        this.endComListener = endComListener;
    }

    public interface EndComListener {
        void endListener();
    }


    public void setStartListener(StartListener startListener) {
        this.startListener = startListener;
    }

    public interface StartListener {
        void startPlay();
    }
}
