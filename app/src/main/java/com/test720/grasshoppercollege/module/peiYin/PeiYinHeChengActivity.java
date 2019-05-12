package com.test720.grasshoppercollege.module.peiYin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.androidkun.breakpoints.event.DownloadData;
import com.androidkun.breakpoints.event.EventMessage;
import com.androidkun.breakpoints.utils.DownloadUtils;
import com.google.gson.Gson;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.peiYin.bean.KaiShiPeiYinData;
import com.test720.grasshoppercollege.myViews.VerticalViewPager;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.LogUtil;
import com.test720.grasshoppercollege.untils.peiYinUntil.Mp4ParseUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import VideoHandle.EpEditor;
import VideoHandle.OnEditorListener;
import butterknife.BindView;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCMediaManager;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import okhttp3.FormBody;

import static fm.jiecao.jcvideoplayer_lib.JCVideoPlayer.CURRENT_STATE_PAUSE;

public class PeiYinHeChengActivity extends BaseToolActivity {
    String outfilePath;//合成后的地址
    List<Fragment> list = new ArrayList<>();
    List<String> audioList = new ArrayList<>();
    String audioPath;//合成后的音频地址
    String videoPath = Environment.getExternalStorageDirectory().getAbsolutePath()
            + "/downloads/";//下载到本地后保存的视频地址
    File file;//下载的文件
    ProgressDialog dialog;//加载进度框
    KaiShiPeiYinData data;//
    String conId = "";
    String fileName = "duihua.mp4";
    /**
     * 定时任务,定时触发，调整播放进度
     */
    Runnable runnable;
    /**
     * 合成成功之后，播放，并且上传文件
     */
    Runnable videoRun = new Runnable() {
        @Override
        public void run() {
            try {
                if (JCMediaManager.instance().mediaPlayer != null) {
                    JCMediaManager.instance().mediaPlayer.pause();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            Intent intent = new Intent(mcontext, PeiYinWanChengActivity.class);
            intent.putExtra("path", outfilePath);
            intent.putExtra("con_id", conId);
            startActivityForResult(intent, 1);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == 1 && resultCode == 1) {//返回修改
            try {
                if (JCMediaManager.instance() != null) {
                    mp4Adjust(data.getData().getList().get(viewPager.getCurrentItem()));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (requestCode == 1 && resultCode == 2) {//已经配音完成，并发布
            finish();
        }
    }

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_pei_yin_he_cheng;
    }

    @Override
    protected void initData() {
        if (getIntent().getStringExtra("name") != null) {
            title.setText(getIntent().getStringExtra("name"));
        }

        if (getIntent().getStringExtra("con_id") != null) {
            conId = getIntent().getStringExtra("con_id");
        }

        jcPlayStandard.backButton.setVisibility(View.GONE);
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("con_id", conId);
        Post(HttpUntil.GetIntent().DubbingstartDubbing(), builder, 1);

        audioPath = this.getCacheDir() + "/download/audio.ACC";
        outfilePath = this.getCacheDir() + "/download/out.mp4";

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                seekBar.setProgress(position);
                String s = (position + 1) + "/" + list.size();
                jindu.setText(s);
                if (data.getData().getList() != null && data.getData().getList().size() > position) {
                    mp4Adjust(data.getData().getList().get(position));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 初始化 录音
     */
    private void initViewPager() {
        for (int i = 0; i < data.getData().getList().size(); i++) {
            final int j = i;
            LuYinFragment luYinFragment = new LuYinFragment();
            luYinFragment.setViewPager(viewPager);
            luYinFragment.setPeiYinHeChengActivity(this);
            luYinFragment.setPosition(i);
            luYinFragment.setListBean(data.getData().getList().get(i));
            audioList.add("");//初始化录音存储的路径
            //获取录音路径
            luYinFragment.setPathBack(new LuYinFragment.PathBack() {
                @Override
                public void pathback(String path) {
                    audioList.set(j, path);
                }
            });
            list.add(luYinFragment);
        }
        seekBar.setMax(list.size());
        String t = 1 + "/" + list.size();
        jindu.setText(t);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
    }


    /**
     * 调节进度
     *
     * @param listBean 数据
     */

    private void mp4Adjust(KaiShiPeiYinData.DataBean.ListBean listBean) {

        float t = Float.parseFloat(listBean.getTime());
        final int timet = (int) (t * 1000);//播放时间
        LogUtil.logError("intt=" + timet);
        float s = Float.parseFloat(listBean.getStart());//开始位置
        final int startt = (int) (s * 1000);
        LogUtil.logError("ints=" + startt);
        if (runnable != null) {
            mainHandler.removeCallbacks(runnable);
            runnable = null;
        }
        runnable = new Runnable() {
            @Override
            public void run() {
                /*定时触发*/
                if (jcPlayStandard.currentState == CURRENT_STATE_PAUSE) {
                    JCMediaManager.instance().mediaPlayer.start();
                }
                JCMediaManager.instance().mediaPlayer.seekTo(startt);
                int duration = jcPlayStandard.getDuration();
                int progressa = (int) (100 * ((double) startt / (double) duration));
                jcPlayStandard.progressBar.setProgress(progressa);
                jcPlayStandard.startProgressTimer();
                mainHandler.postDelayed(runnable, timet);
            }
        };
        mainHandler.postDelayed(runnable, 0);
    }

    /**
     * 合成
     */
    Runnable heRun = new Runnable() {
        @Override
        public void run() {
            Mp4ParseUtil.appendAacList(audioList, audioPath);
            EpEditor.music(videoPath + "duihua.mp4", audioPath, outfilePath, 0.5f, 1f, new OnEditorListener() {
                @Override
                public void onSuccess() {
                    Log.e("lxl", "合成成功");
                    Log.e("lxl", outfilePath);
                    mainHandler.postDelayed(videoRun, 500);
                    DismissDialong();
                }

                @Override
                public void onFailure() {
                    Log.e("lxl", "合成失败");
                    DismissDialong();
                }

                @Override
                public void onProgress(float progress) {
                    //这里获取处理进度
                    Log.e("lxl 合成", progress + "");
                }
            });
            DismissDialong();
        }
    };

    public void heCheng() {
        if (runnable != null) {
            mainHandler.removeCallbacks(runnable);
            runnable = null;
        }
        ShowDialong();
        mainHandler.postDelayed(heRun, 0);
       /* new AsyncTask<String, Integer, Boolean>() {
            @Override
            protected Boolean doInBackground(String... params) {
                return Mp4ParseUtil.appendAacList(audioList, audioPath);
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                super.onPostExecute(aBoolean);
                if (aBoolean) {
                    //参数分别是视频路径，音频路径，输出路径,原始视频音量(1为100%,0.7为70%,以此类推),添加音频音量
                    EpEditor.music(videoPath + "duihua.mp4", audioPath, outfilePath, 0.5f, 1f, new OnEditorListener() {
                        @Override
                        public void onSuccess() {
                            Log.e("lxl", "合成成功");
                            Log.e("lxl", outfilePath);
                            mainHandler.postDelayed(videoRun, 500);
                            DismissDialong();
                        }

                        @Override
                        public void onFailure() {
                            Log.e("lxl", "合成失败");
                            DismissDialong();
                        }

                        @Override
                        public void onProgress(float progress) {
                            //这里获取处理进度
                            Log.e("lxl 合成", progress + "");
                        }
                    });
                } else {
                    Log.e("lxl", "音频合成失败");
                    DismissDialong();
                }
            }
        }.execute();*/
    }

    /**
     * 调节音量
     */
    public void yinLiang(float f) {
        if (JCMediaManager.instance().mediaPlayer.isPlaying()) {
            JCMediaManager.instance().mediaPlayer.setVolume(f, f);
        }
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        switch (what) {
            case 1:
                data = new Gson().fromJson(str, KaiShiPeiYinData.class);
                initViewPager();
                jcPlayStandard.setUp(data.getData().getInfo().getVideo_path(), JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "");
                jcPlayStandard.startButton.performClick();
                //直接开始下载
                DownloadUtils.downLoad(mcontext, data.getData().getInfo().getVideo_path_q(), videoPath, fileName, 3);
                dialog = new ProgressDialog(this);
                dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                dialog.setCanceledOnTouchOutside(false);
                dialog.setIcon(R.drawable.icon_app);//这里指的是标题左侧的图标。注意：如果没有设置title只设置Icon的话，是不会显示图标的
                dialog.setTitle("提示");
                dialog.setMax(100);
                dialog.setMessage("正在加载，请稍后……");
                dialog.show();
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (runnable != null) {
            mainHandler.removeCallbacks(runnable);
            runnable = null;
        }
        JCVideoPlayer.releaseAllVideos();
        //清理缓存
        if (file != null && file.exists()) {
            file.delete();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (runnable != null) {
            mainHandler.removeCallbacks(runnable);
            runnable = null;
        }
        try {
            if (JCMediaManager.instance().mediaPlayer != null) {
                JCMediaManager.instance().mediaPlayer.pause();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onEvent(CurrencyEvent eventMessage) {
        super.onEvent(eventMessage);
        switch (eventMessage.getType()) {
            case EventMessage.TYPE_START://开始下载
                DownloadData start = (DownloadData) eventMessage.getObject();
                LogUtil.logError("开始下载");
                break;
            case EventMessage.TYPE_PROGRESS://下载
                DownloadData progress = (DownloadData) eventMessage.getObject();
                dialog.setProgress(progress.getProgress());
                LogUtil.logError("开始下载" + progress.getProgress());
                break;
            case EventMessage.TYPE_FINISHED://下载完成
                DownloadData finished = (DownloadData) eventMessage.getObject();
                dialog.dismiss();
                LogUtil.logError("下载完成");
                file = new File(finished.getFilePath());
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (data.getData().getList() != null && data.getData().getList().size() > 0) {
                            mp4Adjust(data.getData().getList().get(0));
                        }
                    }
                }, 500);
                break;
            case EventMessage.TYPE_ERROR://下载失败
                DownloadData error = (DownloadData) eventMessage.getObject();
                LogUtil.logError("下载失败" + error.getMsg());
                break;
        }
    }


    @OnClick({R.id.begin, R.id.back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.begin:
                heCheng();
                break;
        }
    }


    @BindView(R.id.jc_play_standard)
    JCVideoPlayerStandard jcPlayStandard;
    @BindView(R.id.one_step_listview)
    VerticalViewPager viewPager;
    @BindView(R.id.layout_video_bg)
    LinearLayout layoutVideoBg;
    @BindView(R.id.seekBar)
    SeekBar seekBar;
    @BindView(R.id.jindu)
    TextView jindu;
    @BindView(R.id.begin)
    TextView begin;
    @BindView(R.id.title)
    TextView title;


}
