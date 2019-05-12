package com.test720.grasshoppercollege;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.just.agentweb.AgentWeb;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.HttpBean.PingLunData;
import com.test720.grasshoppercollege.myViews.DontGoDialog;
import com.test720.grasshoppercollege.myViews.Mp4GXDialog;
import com.test720.grasshoppercollege.sharePage.ShareUntil;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.LogUtil;
import com.test720.grasshoppercollege.untils.pinLunUntil.PinLunUntil;
import com.umeng.socialize.UMShareAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCMediaManager;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import okhttp3.FormBody;
import q.rorbin.badgeview.QBadgeView;

/*播放器加web*/
public abstract class Mp4Activity extends BaseRecyclerViewActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.one)
    public View one;
    @BindView(R.id.zan)
    RelativeLayout zan;
    @BindView(R.id.two)
    public View two;
    @BindView(R.id.zhuan)
    RelativeLayout zhuan;
    @BindView(R.id.down)
    LinearLayout down;
    @BindView(R.id.three)
    public View three;
    @BindView(R.id.pinlun)
    RelativeLayout pinlun;
    @BindView(R.id.activity_mp3_and_view_pager)
    LinearLayout activityMp3AndViewPager;
    @BindView(R.id.dianZan)
    public ImageView dianZan;

    public QBadgeView zanQB;
    public QBadgeView zhuanQB;
    public QBadgeView pinLunQB;

    public TextView title;
    public RelativeLayout share;
    public JCVideoPlayerStandard jcVideo;
    public MyBaseRecyclerAdapter adapter;
    ViewHolder viewHolder;
    View head;

    PinLunUntil pinLunUntil;
    public List<PingLunData> list = new ArrayList<>();
    public boolean isWeb = false;//区分是h5还是视频字段
    public boolean noShare = false;//是否显示分享
    private int zanIndex = -1;//点赞的位置
    protected AgentWeb mAgentWeb;
    public String share_points = "0";//分享果果豆积分
    public String points = "0";//视频积分
    public String is_share = "0";//是否已经分享过；
    public String is_reading = "0";//是否已经分享过；
    public String shareTitle = "蝈蝈学苑";//分享标题
    public String shareBody = "蝈蝈学苑 ";//分享内容
    public long time = 1000 * 3 * 6;//时间判断，到达后加豆豆
    public View headTwo;//顶部视频下添加布局
    RightTopListenter rightTopListenter;//顶部右边点击监听

    public void setRightTopListenter(RightTopListenter rightTopListenter) {
        this.rightTopListenter = rightTopListenter;
    }

    public View getHeadTwo() {
        return headTwo;
    }

    public void setHeadTwo(View headTwo) {
        this.headTwo = headTwo;
    }

    @Override
    public RecyclerView initRecyclerView() {
        return recyclerView;
    }

    @Override
    public SwipeRefreshLayout initSwipeRefreshLayout() {
        return swipeRefresh;
    }


    @Override
    protected String setTitle() {
        return null;
    }//节操播放器，不可设置自定义的状态栏，否则将找不到窗口


    @Override
    public void init() {
        super.init();
        if (noShare) zhuan.setVisibility(View.GONE);
        zanQB = new QBadgeView(this);
        zhuanQB = new QBadgeView(this);
        pinLunQB = new QBadgeView(this);
        initHead();
        jcVideo = viewHolder.jcVideo;
        share = viewHolder.share;
        title = viewHolder.title;

        if (getIntent().getStringExtra("title") != null)
            title.setText(getIntent().getStringExtra("title"));

        jcVideo.backButton.setVisibility(View.GONE);
        jcVideo.titleTextView.setVisibility(View.GONE);
        jcVideo.tinyBackImageView.setVisibility(View.GONE);

        /*区分h5*/
        isWeb = getIntent().getBooleanExtra("h5", false);
        if (isWeb) {//如果是h5，则隐藏播放器，显示webview
            viewHolder.webView.setVisibility(View.VISIBLE);
            jcVideo.setVisibility(View.GONE);
        } else {
            viewHolder.webView.setVisibility(View.GONE);
            jcVideo.setVisibility(View.VISIBLE);
        }
        initAdapter();
    }

    @SuppressLint("InflateParams")
    private void initHead() {
        head = LayoutInflater.from(mcontext).inflate(R.layout.mp4_head, null);
        viewHolder = new ViewHolder(head);
        if (rightTopListenter != null) {
            viewHolder.rightImg.setImageResource(rightTopListenter.imgRouse());
        }
        viewHolder.heng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int screenNum = getResources().getConfiguration().orientation;
                if (screenNum == 1) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    swipeRefresh.setVisibility(View.GONE);
                    down.setVisibility(View.GONE);
                    viewHolder.centerRel.setVisibility(View.GONE);
                    viewHolder.topRel.setVisibility(View.GONE);
                } else {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    swipeRefresh.setVisibility(View.VISIBLE);
                    down.setVisibility(View.VISIBLE);
                    viewHolder.centerRel.setVisibility(View.VISIBLE);
                    viewHolder.topRel.setVisibility(View.VISIBLE);
                }
            }
        });
        viewHolder.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                end();
            }
        });
        viewHolder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rightTopListenter != null) rightTopListenter.clickListener();
            }
        });
    }


    private void initAdapter() {
        pinLunUntil = new PinLunUntil(list, mcontext, new PinLunUntil.PinLunInterface() {
            @Override
            public void Zan(String commentId, int pos) {
                dianZan(commentId);
                zanIndex = pos;
            }

            @Override
            public void Reply(String commentId, String name) {
                huiFu(commentId, name);
            }

            @Override
            public void ItemClick(PingLunData item) {
                pingLunXiangQing(item);
            }
        });
        adapter = pinLunUntil.getAdapter();
        adapter.setHeaderView(head);
//视频下添加的布局
        if (getHeadTwo() != null) {
            viewHolder.centerRel.setVisibility(View.GONE);
            adapter.setmHeadViewTwo(getHeadTwo());
        }
        recyclerView.setAdapter(adapter);
    }

    public abstract void dianZan(String comment_id);

    public abstract void huiFu(String comment_id, String name);

    public abstract void pingLunXiangQing(PingLunData item);

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_mp4_video;
    }

    @Override
    public int setCount() {
        return 1;
    }

    @OnClick({R.id.pinlunEditText, R.id.zan, R.id.pinlun, R.id.zhuan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zan:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (!MyApplication.getmInstance().getUid().equals("")) {
                    dianZan("0");
                    zanIndex = -1;
                }
                break;

            case R.id.pinlun:
            case R.id.pinlunEditText:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (!MyApplication.getmInstance().getUid().equals("")) {
                    PingJia();
                }
                break;
            case R.id.zhuan://分享
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (!MyApplication.getmInstance().getUid().equals("")) {
                    Mp4Share();
                }
                break;
        }
    }

    /*退出，介绍方法*/
    @SuppressLint("CommitTransaction")
    private void end() {
        if (currentSecond < time && !is_reading.equals("1") && !MyApplication.getmInstance().getUid().equals("")) {
            DontGoDialog dontGoDialog = new DontGoDialog();
            dontGoDialog.setClick(new DontGoDialog.Click() {
                @Override
                public void fangqi() {
                    if (JCVideoPlayer.backPress()) {
                        return;
                    }
                    if (mAgentWeb != null) {
                        mAgentWeb.destroy();
                        mAgentWeb = null;
                    }
                    finish();
                }

                @Override
                public void jixu() {

                }
            });
            dontGoDialog.show(getSupportFragmentManager().beginTransaction(), "dialog");
            return;
        }

        if (JCVideoPlayer.backPress()) {
            return;
        }
        /*关闭h5界面 */
        if (mAgentWeb != null) {
            mAgentWeb.destroy();
            mAgentWeb = null;
        }
        finish();
    }


    public abstract void PingJia();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        JCVideoPlayer.releaseAllVideos();
        if (mAgentWeb != null) mAgentWeb.destroy();
        mAgentWeb = null;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mAgentWeb != null) mAgentWeb.getWebLifeCycle().onPause();
        try {
            if (JCMediaManager.instance().mediaPlayer != null) {
                JCMediaManager.instance().mediaPlayer.pause();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        isPause = true;//计时器

    }

    @Override
    public void afterShuaXin() {
        super.afterShuaXin();
        recyclerView.smoothScrollToPosition(0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mAgentWeb != null) mAgentWeb.getWebLifeCycle().onResume();
        try {
            if (JCMediaManager.instance() != null) {
                if (JCMediaManager.instance().mediaPlayer != null) {
                    JCMediaManager.instance().mediaPlayer.start();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        isPause = false;//计时器
    }

    @Override
    public void onBackPressed() {
        int screenNum = getResources().getConfiguration().orientation;
        if (screenNum != 1) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            swipeRefresh.setVisibility(View.VISIBLE);
            down.setVisibility(View.VISIBLE);
            viewHolder.centerRel.setVisibility(View.VISIBLE);
            viewHolder.topRel.setVisibility(View.VISIBLE);
            return;
        }
        end();
    }


    @Override
    public void setSys() {
        super.setSys();
        noSuPing = true;
    }

    /*********html5相关****************/
    /*调用web*/
    public void initWeb(String url) {
        jcVideo.setVisibility(View.GONE);
        viewHolder.webRel.setVisibility(View.VISIBLE);
        viewHolder.webView.setVisibility(View.VISIBLE);
        mAgentWeb = AgentWeb.with(this)//传入Activity
                .setAgentWebParent(viewHolder.webRel, new LinearLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams
                .useDefaultIndicator()// 使用默认进度条
                .defaultProgressBarColor() // 使用默认进度条颜色
                .createAgentWeb()//
                .ready()
                .go(url);
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        super.GetHttpBackStr(str, what);
        if (what == 5) {
            if (codeIsOne(str)) {
                try {
                    JSONObject jsonObject = new JSONObject(str);
                    String num = jsonObject.getJSONObject("data").getString("thumb_up_count");
                    int status = jsonObject.getJSONObject("data").getInt("is_thumb_up");
                    if (zanIndex != -1) {//对评论的点赞
                        list.get(zanIndex).setIs_thumb_up(status);
                        list.get(zanIndex).setThumb_up_count(num);
                        pinLunUntil.getAdapter().notifyDataSetChanged();
                    } else {//对视频的点赞
                        zanQB.bindTarget(one).setBadgeNumber(Integer.parseInt(num)).setBadgeTextSize(7, true);
                        if (num.equals("0")) {
                            zanQB.setVisibility(View.GONE);
                        } else {
                            zanQB.setVisibility(View.VISIBLE);
                        }
                        /*点赞状态*/
                        if (status == 1) {
                            dianZan.setImageResource(R.mipmap.zan);
                        } else {
                            dianZan.setImageResource(R.mipmap.meizan);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 顶部右上角点击接口
     */
    public interface RightTopListenter {
        @DrawableRes
        int imgRouse();

        void clickListener();
    }


    /*****************果果豆计时器*******************/
    private Runnable timeRunable = new Runnable() {
        @Override
        public void run() {
            currentSecond = currentSecond + 1000;
            if (!isPause) {
                //递归调用本runable对象，实现每隔一秒一次执行任务
                mhandle.postDelayed(this, 1000);
                if (currentSecond > (3 * 60 * 1000)) {
                    isPause = true;
                    if (is_reading == null || !is_reading.equals("1")) {
                        AddGuoGuoDou("1");
                        showGongXiPop();
                    }
                }
            }
        }
    };


    //计时器
    private Handler mhandle = new Handler();
    private boolean isPause = false;//是否暂停
    private long currentSecond = 0;//当前毫秒数

    /*公用分享方法*/
    public void Mp4Share() {
        if (share_points != null) {
            share(vodeoType() + 10);
        }
    }

    /**
     * 模块type id
     *
     * @return
     */
    public abstract int vodeoType();

    /**
     * 视频id
     *
     * @return
     */
    public abstract String vodeoId();

    /**
     * 增加果果豆
     *
     * @param type 1是视频观看增加，2 是分享加
     */

    public void AddGuoGuoDou(String type) {
        if (points == null || share_points == null) return;
        if (vodeoId() == null) return;
        if (vodeoType() == -1) return;
        if (type.equals("1")) {
            FormBody.Builder builder = new FormBody.Builder();
            builder.add("type", vodeoType() + "");
            builder.add("video_id", vodeoId());
            builder.add("uid", MyApplication.getmInstance().getUid());
            builder.add("points", points);
            Post(HttpUntil.GetIntent().addReadingScore(), builder, 6, false);
        } else if (type.equals("2")) {
            FormBody.Builder builder = new FormBody.Builder();
            builder.add("type", vodeoType() + "");
            builder.add("video_id", vodeoId());
            builder.add("book_id", bookId());
            builder.add("unit", unitId());
            builder.add("uid", MyApplication.getmInstance().getUid());
            builder.add("points", share_points);
            Post(HttpUntil.GetIntent().addShareScore(), builder, 7, false);
        }
    }

    /**
     * 返回上级id
     *
     * @return id
     */
    public String bookId() {
        return "";
    }

    /**
     * 上上级
     *
     * @return unit
     */
    public String unitId() {
        return "";
    }

    @Override
    protected void onStart() {
        super.onStart();
        timeRunable.run();
    }

    /*恭喜弹框*/
    @SuppressLint("CommitTransaction")
    public void showGongXiPop() {
        if (is_reading != null && is_reading.equals("1") || MyApplication.getmInstance().getUid().equals("")) {
            return;
        }
        if (points.equals("0")) {
            return;
        }
        Mp4GXDialog dialog = new Mp4GXDialog();
        dialog.setPoints(points);
        dialog.setShare_points(share_points);
        dialog.setShareClick(new Mp4GXDialog.Share() {
            @Override
            public void share() {
                Mp4Share();
            }

            @Override
            public void back() {

            }
        });
        dialog.show(getSupportFragmentManager().beginTransaction(), "share");
    }

    /*蝈蝈豆提示*/

    /******************************分享*****************/
    public void share(int type) {
        ShareUntil.getInstance().share(type, shareTitle, shareBody, mcontext);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onEvent(CurrencyEvent event) {
        if (event.getWhat() == CurrencyEvent.SHAREOK) {
            AddGuoGuoDou("2");
            ShuaXin();
        }
        if (event.getWhat() == CurrencyEvent.PINGLUNOK && event.getMsg().equals("评论成功")) {
            LogUtil.logError("评论刷新通知");
            ShuaXin();
        }
    }


    static class ViewHolder {
        @BindView(R.id.topRel)
        RelativeLayout topRel;
        @BindView(R.id.back)
        LinearLayout back;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.rightImg)
        ImageView rightImg;
        @BindView(R.id.right)
        RelativeLayout share;
        @BindView(R.id.jcVideo)
        JCVideoPlayerStandard jcVideo;
        @BindView(R.id.webRel)
        RelativeLayout webRel;
        @BindView(R.id.heng)
        ImageView heng;
        @BindView(R.id.webView)
        RelativeLayout webView;
        @BindView(R.id.centerRel)
        RelativeLayout centerRel;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
