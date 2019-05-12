package com.test720.grasshoppercollege.module.peiYin;
/**
 * Created by 水东流 on 2018/8/10.
 */

import android.media.MediaPlayer;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.test720.grasshoppercollege.BaseFragment;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.peiYin.bean.KaiShiPeiYinData;
import com.test720.grasshoppercollege.myViews.VerticalViewPager;
import com.test720.grasshoppercollege.untils.musicMedia.MusicUntil;
import com.test720.grasshoppercollege.untils.luYinUntil.AudioRecoderUtils;
import com.test720.grasshoppercollege.untils.luYinUntil.TimeUtils;

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
 * 别人笑我忒直男，我笑自己太像猿；
 * 但见成都府国内，处处地地程序员。
 * 作者 水东流 on 2018/8/10
 */

public class LuYinFragment extends BaseFragment {
    AudioRecoderUtils audioRecoderUtils;
    int position;
    String path;//录音地址
    VerticalViewPager viewPager;
    PeiYinHeChengActivity peiYinHeChengActivity;
    KaiShiPeiYinData.DataBean.ListBean listBean;
    MusicUntil musicUntil;

    @Override
    public void initData() {
        audioRecoderUtils = new AudioRecoderUtils();
        //录音回调
        audioRecoderUtils.setOnAudioStatusUpdateListener(new AudioRecoderUtils.OnAudioStatusUpdateListener() {
            //录音中....db为声音分贝，time为录音时长
            @Override
            public void onUpdate(double db, long time) {
                int pro = (int) (time / (Float.parseFloat(listBean.getTime()) * 10));
                progressColor.setProgress(pro);
                tvSecond.setText(TimeUtils.long2String(time));
                if (pro >= 100) {
                    audioRecoderUtils.stopRecord();
                    layoutPlay.setVisibility(View.VISIBLE);
                    if (position < getViewPager().getAdapter().getCount() - 1) {
                        right.setVisibility(View.VISIBLE);
                    }
                }
            }

            //录音结束，filePath为保存路径
            @Override
            public void onStop(String filePath) {
                path = filePath;
                if (pathBack != null) {
                    pathBack.pathback(path);
                }
                peiYinHeChengActivity.yinLiang(1);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        if (position == 0) {
            left.setVisibility(View.GONE);
        }
        if (path != null) {
            right.setVisibility(View.VISIBLE);
        }
        tvNum.setText((position + 1) + "");
        tvChinese.setText(listBean.getCn());
        tvEnglish.setText(listBean.getEn());
        tvSecond.setText(listBean.getTime());
        musicUntil = new MusicUntil(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                itemPlay.setImageResource(R.mipmap.dubbing_stop);
                peiYinHeChengActivity.yinLiang(1);
            }
        }, 1f);
    }


    @OnClick({R.id.item_play, R.id.item_begin, R.id.left, R.id.right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left:
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                break;
            case R.id.right:
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                break;

            case R.id.item_play:
                itemPlay.setImageResource(R.mipmap.book_play);
                peiYinHeChengActivity.yinLiang(0);
                musicUntil.playMusic(path);
                break;
            case R.id.item_begin:
                audioRecoderUtils.startRecord();
                peiYinHeChengActivity.yinLiang(0);
                break;
        }
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public VerticalViewPager getViewPager() {
        return viewPager;
    }

    public void setViewPager(VerticalViewPager viewPager) {
        this.viewPager = viewPager;
    }


    public void setPathBack(PathBack pathBack) {
        this.pathBack = pathBack;
    }


    public void setPeiYinHeChengActivity(PeiYinHeChengActivity peiYinHeChengActivity) {
        this.peiYinHeChengActivity = peiYinHeChengActivity;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (musicUntil != null) {
            // 为解决第二次播放时抛出的IllegalStateException，这里做了try-catch处理
            musicUntil.canlce();
        }
    }

    @Override
    public void setListener() {

    }

    @Override
    public void getSuccess(String s, int what) {

    }

    public void setListBean(KaiShiPeiYinData.DataBean.ListBean listBean) {
        this.listBean = listBean;
    }

    @Override
    public int setlayoutResID() {
        return R.layout.pei_yin_item;
    }

    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.tv_english)
    TextView tvEnglish;
    @BindView(R.id.tv_chinese)
    TextView tvChinese;
    @BindView(R.id.progress_color)
    ProgressBar progressColor;
    @BindView(R.id.tv_second)
    TextView tvSecond;
    @BindView(R.id.item_play)
    ImageView itemPlay;
    @BindView(R.id.layout_play)
    RelativeLayout layoutPlay;
    @BindView(R.id.item_begin)
    ImageView itemBegin;
    @BindView(R.id.id_city)
    LinearLayout idCity;
    @BindView(R.id.lin)
    LinearLayout lin;
    @BindView(R.id.left)
    ImageView left;
    @BindView(R.id.right)
    ImageView right;

    PathBack pathBack;

    public interface PathBack {
        void pathback(String path);
    }

}
