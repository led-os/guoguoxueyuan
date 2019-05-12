package com.test720.grasshoppercollege.module.diandu;

import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.test720.grasshoppercollege.BaseFragment;
import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.diandu.bean.BookDataBean;
import com.test720.grasshoppercollege.module.diandu.bean.PointReadingStatus;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.LogUtil;
import com.test720.grasshoppercollege.untils.PublicUntil;
import com.test720.grasshoppercollege.untils.glideUntil.GlideUntil;
import com.test720.grasshoppercollege.untils.luYinUntil.LuYinUntil;
import com.test720.grasshoppercollege.untils.musicMedia.MusicUntil;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;


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
 * 作者：水东流 编于 2018/10/12
 */
public class BookFragment extends BaseFragment {

    BookDataBean.DataBean.ContentBeanX.LessonBean.PageBean pageBean;
    List<TextView> list = new ArrayList<>();
    String unit = "";
    String lesson = "";
    String pageUrl = "";
    private MusicUntil musicUntil;
    String yuanurl;//原地址
    private Timer mTimer; // 计时器
    String newFilePath;//录音存放地址
    boolean needLuYin = false;

    @Override
    public void onPause() {
        super.onPause();
        stop();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stop();
    }

    @Override
    public void initData() {
        initRel();
    }


    /*加载界面*/
    private void initRel() {
        if (pageBean == null) return;
        new LuYinUntil(getActivity(), new LuYinUntil.LuYinInterface() {
            @Override
            public View root() {
                return rel;
            }

            @Override
            public View changAnView() {
                return startLuYin;
            }

            @Override
            public void filePath(String path) {
                newFilePath = path;
            }

            @Override
            public TextView shuChuTime() {
                return null;
            }
        });
        //根据主界面属性，设置播放器
        musicUntil = new MusicUntil();
        musicUntil.setIsSpeed(PointReadingStatus.getInstance().isDanQu());
        musicUntil.setSpeed(PointReadingStatus.getInstance().getSpeedF());
        musicUntil.setEndComListener(new MusicUntil.EndComListener() {
            @Override
            public void endListener() {
                fanYiText.setVisibility(View.GONE);
            }
        });
        mediaPlayerToSeekBar();
        pageUrl = HttpUntil.GetIntent().filePathHead(mContext) + pageBean.getVoice_page_loca();
        String path = HttpUntil.GetIntent().filePathHead(mContext) + pageBean.getPic_loca();
        GlideUntil.getInstance().imgBack(mContext, rel, path);
        for (int i = 0; i < pageBean.getContent().size(); i++) {
            final BookDataBean.DataBean.ContentBeanX.LessonBean.PageBean.ContentBean bean = pageBean.getContent().get(i);
            final TextView textView = new TextView(getActivity());
            textView.setClickable(true);
            List xy = PublicUntil.StringToXY(bean.getZb());
            List wh = PublicUntil.StringToXY(bean.getGk());
            int x = (int) xy.get(0) * w / 750;
            int y = (int) xy.get(1) * h / 1334;
            int width = (int) wh.get(0) * w / 750;
            int height = (int) wh.get(1) * h / 1334;
            textView.setX(x - 20);
            textView.setY(y - 20);
            textView.setWidth(width + 40);
            textView.setHeight(height + 40);
            if (PointReadingStatus.getInstance().isDianDuQu()) {
                textView.setBackgroundResource(R.drawable.dian_du_qu_yu_kuang);
            } else {
                textView.setBackgroundResource(R.color.touming);
            }
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //为防止动态添加的坐标textview的点击事件在其他page时触发，给textview添加标签，只有当标签为当前index时才触发点击事件
                    myOnClick(bean);
                    for (int i = 0; i < list.size(); i++) {
                        dianDuQu();
                    }
                    textView.setBackgroundResource(R.drawable.diandubiankuang);
                }
            });
            rel.addView(textView);
            list.add(textView);
        }
    }


    /*点击句子事件*/
    public void myOnClick(BookDataBean.DataBean.ContentBeanX.LessonBean.PageBean.ContentBean bean) {
        fanYiText.setText(bean.getCn());
        //显示翻译框
        if (PointReadingStatus.getInstance().isFanYi()) {
            fanYiText.setVisibility(View.VISIBLE);
        }
        //录音框显示，进度框隐藏
        LinLuYin.setVisibility(View.VISIBLE);
        ciDaiLin.setVisibility(View.GONE);

        yuanurl = HttpUntil.GetIntent().filePathHead(mContext) + bean.getVoice_loca();
        musicUntil.dianDuPlayMusic(mContext, yuanurl);
    }

    @Override
    public void onEvent(CurrencyEvent currencyEvent) {
        if (currencyEvent.getWhat() == 91) {//单曲状态改变
            if (musicUntil != null) {
                musicUntil.setIsSpeed(PointReadingStatus.getInstance().isDanQu());
            }
        } else if (currencyEvent.getWhat() == 92) {//翻译状态
            LogUtil.logError("翻译状态更改");
            if (PointReadingStatus.getInstance().isFanYi()) {
                fanYiText.setVisibility(View.VISIBLE);
            } else {
                fanYiText.setVisibility(View.GONE);
            }
        } else if (currencyEvent.getWhat() == 93) {//点读区
            dianDuQu();
        } else if (currencyEvent.getWhat() == 94) {//语速
            if (musicUntil != null)
                musicUntil.setSpeed(PointReadingStatus.getInstance().getSpeedF());
        } else if (currencyEvent.getWhat() == CurrencyEvent.DDCDBF && currencyEvent.getMsg().equals("磁带播放")) {
            if (musicUntil != null) {
                musicUntil.pause();
            }
        }
    }

    /*点击点读区域，将可点读的区域加边框*/
    public void dianDuQu() {
        for (int i = 0; i < list.size(); i++) {
            TextView textView = list.get(i);
            if (PointReadingStatus.getInstance().isDianDuQu())
                textView.setBackgroundResource(R.drawable.dian_du_qu_yu_kuang);
            else textView.setBackgroundResource(R.color.touming);
        }
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (getView() != null) getView().setVisibility(View.VISIBLE);
        } else {
            stop();
            if (getView() != null) getView().setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.yuan, R.id.my, R.id.LinLuYin, R.id.rel, R.id.ciDaiLin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ciDaiLin:
                break;
            case R.id.rel:
                ciDaiLin.setVisibility(View.GONE);
                LinLuYin.setVisibility(View.GONE);
                break;
            case R.id.LinLuYin:
                needLuYin = true;
                ciDaiLin.setVisibility(View.VISIBLE);
                LinLuYin.setVisibility(View.GONE);
                break;
            case R.id.yuan:
                musicUntil.playMusic(yuanurl);
                break;
            case R.id.my:
                if (newFilePath == null) {
                    Toast.makeText(getActivity(), "请先录音", Toast.LENGTH_SHORT).show();
                    return;
                }
                musicUntil.playMusic(newFilePath);
                break;
        }
    }

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
    @BindView(R.id.fanyitext)
    TextView fanYiText;
    @BindView(R.id.boFangLin)
    LinearLayout boFangLin;
    @BindView(R.id.LinLuYin)
    LinearLayout LinLuYin;
    @BindView(R.id.ciDaiLin)
    LinearLayout ciDaiLin;
    @BindView(R.id.index)
    TextView index;
    @BindView(R.id.rel)
    RelativeLayout rel;
    private int h;
    private int w;


    /**
     * 滑动翻页时结束当前页逻辑
     */
    public void stop() {
        if (musicUntil != null) musicUntil.canlce();
        if (fanYiText != null) fanYiText.setVisibility(View.GONE);
        if (ciDaiLin != null) ciDaiLin.setVisibility(View.GONE);
        if (LinLuYin != null) LinLuYin.setVisibility(View.GONE);

    }

    @Override
    public void setListener() {

    }

    @Override
    public void getSuccess(String s, int what) {
    }

    @Override
    public int setlayoutResID() {
        return R.layout.fragment_dian_du;
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

    public RelativeLayout getRel() {
        return rel;
    }

    public void setPageBean(BookDataBean.DataBean.ContentBeanX.LessonBean.PageBean pageBean) {
        this.pageBean = pageBean;
    }

    public String getUnit() {
        return unit;
    }

    public String getLesson() {
        return lesson;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public void setH(int h) {
        this.h = h;
    }

    public void setW(int w) {
        this.w = w;
    }

    public String getPageUrl() {
        return pageUrl;
    }


    //进度
    /*主线程*/
    Handler mainHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 790) {
                try {
                    int position = musicUntil.getMediaPlayer().getCurrentPosition();
                    int duration = musicUntil.getMediaPlayer().getDuration();
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

    //计时器
    class MyTimetask extends TimerTask {

        @Override
        public void run() {
            try {
                if (musicUntil != null &&
                        musicUntil.getMediaPlayer() != null &&
                        musicUntil.getMediaPlayer().isPlaying() &&
                        seekBar.isPressed() == false) {
                    Message message = new Message();
                    message.what = 790;
                    mainHandler.sendMessage(message); // 发送消息
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /*将播放进度展示到seek*/
    private void mediaPlayerToSeekBar() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
        mTimer = new Timer();
        mTimer.schedule(new MyTimetask(), 0, 100);
        if (musicUntil.getMediaPlayer() != null && musicUntil.getMediaPlayer().isPlaying()) {
            long end = musicUntil.getMediaPlayer().getDuration();
            endTime.setText(formatLongToTimeStr(end));
            musicUntil.getMediaPlayer().setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                @Override
                public void onBufferingUpdate(MediaPlayer mp, int percent) {
                    seekBar.setSecondaryProgress(percent);
                }
            });
        }
    }

}
