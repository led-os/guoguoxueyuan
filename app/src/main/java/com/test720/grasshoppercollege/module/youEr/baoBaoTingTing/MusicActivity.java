package com.test720.grasshoppercollege.module.youEr.baoBaoTingTing;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.BaseTwoRecyclerViewActivity;
import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.HttpBean.PingLunData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.youEr.baoBaoTingTing.bean.GuShiNeiRongData;
import com.test720.grasshoppercollege.module.youEr.baoBaoTingTing.pingLun.HuiFuActivity;
import com.test720.grasshoppercollege.module.youEr.baoBaoTingTing.pingLun.PinJiaActivity;
import com.test720.grasshoppercollege.module.youEr.baoBaoTingTing.pingLun.PinLunXiangQingActivity;
import com.test720.grasshoppercollege.sharePage.ShareUntil;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.LogUtil;
import com.test720.grasshoppercollege.untils.glideUntil.GlideUntil;
import com.test720.grasshoppercollege.untils.luYinUntil.TimeUtils;
import com.test720.grasshoppercollege.untils.musicMedia.MusicService;
import com.test720.grasshoppercollege.untils.musicMedia.MusicServiceData;
import com.test720.grasshoppercollege.untils.pinLunUntil.PinLunUntil;
import com.umeng.socialize.UMShareAPI;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.FormBody;
import q.rorbin.badgeview.QBadgeView;

public class MusicActivity extends BaseTwoRecyclerViewActivity<PingLunData> implements View.OnClickListener {
    ViewHolder viewHolder;
    private Handler mHandler = new Handler();
    Intent MediaServiceIntent;
    private MusicService.MyBinder mMyBinder;
    private ServiceConnection mServiceConnection;
    PinLunUntil pinLunUntil;

    public QBadgeView zanQB;
    public QBadgeView zhuanQB;
    public QBadgeView pinLunQB;

    public String shareTitle = "蝈蝈学苑";//分享标题
    public String shareBody = "蝈蝈学苑 ";//分享内容

    boolean isFirst = true;
    private boolean updataPinLun = false;//是否是更新评论数据
    /**
     * 更新ui的runnable，进度条
     */
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            if (viewHolder != null && mMyBinder != null && mMyBinder.isPlay()) {
                guShiUi();
                int position = mMyBinder.getPlayPosition();
                int duration = mMyBinder.getProgress();
                if (duration > 0) {
                    // 计算进度（获取进度条最大刻度*当前音乐播放位置 / 当前音乐时长）
                    viewHolder.seekbar.setMax(duration);
                    viewHolder.seekbar.setProgress(position);
                    viewHolder.start.setText(TimeUtils.long2String(mMyBinder.getPlayPosition()));
                    viewHolder.end.setText(TimeUtils.long2String(mMyBinder.getProgress()));
                }
            }
            mHandler.postDelayed(mRunnable, 1000);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        zanQB = new QBadgeView(this);
        zhuanQB = new QBadgeView(this);
        pinLunQB = new QBadgeView(this);
    }

    @Override
    public ReflshInit reflshInit() {
        return new ReflshInit() {
            @Override
            public String reTitle() {
                return null;
            }

            @Override
            public FormBody.Builder reFormBuilder() {
                if (MusicServiceData.getInstance().isNeedUpdata()) {
                    FormBody.Builder builder = new FormBody.Builder();
                    builder.add("uid", MyApplication.getmInstance().getUid());
                    builder.add("p", page + "");
                    builder.add("con_id", MusicServiceData.getInstance().getId());
                    return builder;
                } else {
                    MusicServiceData.getInstance().setNeedUpdata(true);
                    return null;
                }
            }

            @Override
            public String reUrl() {
                return HttpUntil.GetIntent().ChildBabyListeningcontent();
            }

            @Override
            public int reCount() {
                return 1;
            }

            @Override
            public RecyclerView reRecyclerView() {
                return recyclerView;
            }

            @Override
            public SwipeRefreshLayout reSwipeRefreshLayout() {
                return swipeRefresh;
            }
        };
    }

    /**
     * 收藏，取消收藏
     */
    public void collection(String conId) {
        MyApplication.getmInstance().setLoginDialogIsShow(false);
        if (MyApplication.getmInstance().getUid().equals("")) return;
        HttpParams httpParams = new HttpParams();
        httpParams.put("con_id", conId);
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        postResponse(HttpUntil.GetIntent().ChildBabyListeningcollection(), httpParams, 5, false);
        updataPinLun = false;

    }

    @Override
    protected void initData() {
        initAdapter();
        mHandler.postDelayed(mRunnable, 1000);
    }


    private void initAdapter() {
        @SuppressLint("InflateParams") View head = LayoutInflater.from(mcontext).inflate(R.layout.ting_ting_music_head, null);
        viewHolder = new ViewHolder(head);

        pinlun.setOnClickListener(this);
        pinlunEditText.setOnClickListener(this);
        dianZan.setOnClickListener(this);
        zhuan.setOnClickListener(this);

        /*绑定服务器*/
        initMusic();

        viewHolder.speed.setOnClickListener(this);
        viewHolder.next.setOnClickListener(this);
        viewHolder.play.setOnClickListener(this);
        viewHolder.precious.setOnClickListener(this);
        viewHolder.back.setOnClickListener(this);
        viewHolder.time.setOnClickListener(this);
        viewHolder.doc.setOnClickListener(this);
        viewHolder.shouChang.setOnClickListener(this);
        viewHolder.zhuanJi.setOnClickListener(this);

        pinLunUntil = new PinLunUntil(MusicServiceData.getInstance().getComment(), mcontext, new PinLunUntil.PinLunInterface() {
            @Override
            public void Zan(String commentId, int pos) {
                FormBody.Builder builder = new FormBody.Builder();
                builder.add("album_id", MusicServiceData.getInstance().getAlbumId());
                builder.add("uid", MyApplication.getmInstance().getUid());
                builder.add("comment_id", commentId);
                Post(HttpUntil.GetIntent().ChildBabyListeningthumbUp(), builder, 6);
            }

            @Override
            public void Reply(String commentId, String name) {
                Intent in = new Intent(mcontext, HuiFuActivity.class);
                in.putExtra("comment_id", commentId);
                in.putExtra("name", name);
                in.putExtra("album_id", MusicServiceData.getInstance().getAlbumId());
                jumpToActivity(in, false);
            }

            @Override
            public void ItemClick(PingLunData item) {
                Intent in = new Intent(mcontext, PinLunXiangQingActivity.class);
                in.putExtra("comment_id", item.getComment_id());
                in.putExtra("name", item.getNickname());
                in.putExtra("album_id", MusicServiceData.getInstance().getAlbumId());
                jumpToActivity(in, false);
            }
        });
        pinLunUntil.getAdapter().setHeaderView(head);
        setAdapter(pinLunUntil.getAdapter());
        recyclerView.setAdapter(getAdapter());
        updataUi();
    }

    /**
     * 绑定
     */
    private void initMusic() {
        mServiceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                mMyBinder = (MusicService.MyBinder) service;
                viewHolder.seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
            }
        };
        MediaServiceIntent = new Intent(mcontext, MusicService.class);
        bindService(MediaServiceIntent, mServiceConnection, BIND_AUTO_CREATE);
    }


    @Override
    protected void onDestroy() {
        MusicServiceData.getInstance().setNeedUpdata(false);
        mainHandler.removeCallbacks(mRunnable);
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pinlun:
            case R.id.pinlunEditText:
                if (MusicServiceData.getInstance().getInfo() == null) return;
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (MyApplication.getmInstance().getUid().equals("")) return;
                Intent intent = new Intent(mcontext, PinJiaActivity.class);
                intent.putExtra("album_id", MusicServiceData.getInstance().getAlbumId());
                startActivityForResult(intent, 1);
                break;
            case R.id.dianZan:
                dianZan();
                break;
            case R.id.zhuan:
                share();
                break;
            case R.id.speed:
                if (mMyBinder != null)
                    mMyBinder.setSpeed(!MusicServiceData.getInstance().isSpeed());
                if (MusicServiceData.getInstance().isSpeed()) {
                    viewHolder.speed.setImageResource(R.mipmap.bbttdanqu);
                } else {
                    viewHolder.speed.setImageResource(R.mipmap.bbttsunxu);
                }
                break;

            case R.id.precious:
                if (mMyBinder != null) {
                    mMyBinder.preciousMusic();
                    guShiUi();
                    viewHolder.play.setImageResource(R.mipmap.bbttbf);
                    if (MusicServiceData.getInstance().getGuShiBack() != null && !MusicServiceData.getInstance().getGuShiBack().equals(""))
                        GlideUntil.getInstance().imgUrl(mcontext, viewHolder.img, MusicServiceData.getInstance().getGuShiBack());
                }
                break;

            case R.id.play:
                if (mMyBinder == null) return;
                if (mMyBinder.isPlay()) {
                    mMyBinder.pauseMusic();
                    viewHolder.play.setImageResource(R.mipmap.bbttzt);
                } else {
                    mMyBinder.playMusic();
                    viewHolder.play.setImageResource(R.mipmap.bbttbf);
                }
                break;

            case R.id.next:
                if (mMyBinder != null) {
                    mMyBinder.nextMusic();
                    guShiUi();
                    viewHolder.play.setImageResource(R.mipmap.bbttbf);
                    if (MusicServiceData.getInstance().getGuShiBack() != null && !MusicServiceData.getInstance().getGuShiBack().equals(""))
                        GlideUntil.getInstance().imgUrl(mcontext, viewHolder.img, MusicServiceData.getInstance().getGuShiBack());
                }
                break;

            case R.id.back:
                finish();
                break;

            case R.id.time:
                TimeDialog timeDialog = new TimeDialog();
                timeDialog.show(getSupportFragmentManager(), "time");
                break;
            case R.id.doc:
                GuShiListDialog guShiListDialog = new GuShiListDialog();
                guShiListDialog.setItemClick(new GuShiListDialog.ItemClick() {
                    @Override
                    public void itemClick() {
                        if (mMyBinder != null) mMyBinder.resetMusic();
                    }
                });
                guShiListDialog.show(getSupportFragmentManager(), "doc");
                break;
            case R.id.shouChang:
                collection(MusicServiceData.getInstance().getId());
                break;
            case R.id.zhuanJi:
                Intent intent9 = new Intent(mcontext, ZhuanJiGuShiListActivity.class);
                intent9.putExtra("title", MusicServiceData.getInstance().getZhuanJiTitle());
                intent9.putExtra("album_id", MusicServiceData.getInstance().getAlbumId());
                jumpToActivity(intent9, true);
                break;
        }
    }

    private void guShiUi() {
        viewHolder.title.setText(MusicServiceData.getInstance().getTitle());
        viewHolder.zhengTitle.setText(MusicServiceData.getInstance().getTitle());
        viewHolder.smallTitle.setText(MusicServiceData.getInstance().getSmallTitle());
    }

    /*
    点赞
     */
    private void dianZan() {
        MyApplication.getmInstance().setLoginDialogIsShow(false);
        if (MyApplication.getmInstance().getUid().equals("")) return;
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("album_id", MusicServiceData.getInstance().getAlbumId());
        builder.add("uid", MyApplication.getmInstance().getUid());
        builder.add("comment_id", "0");
        Post(HttpUntil.GetIntent().ChildBabyListeningthumbUp(), builder, 6);
        updataPinLun = true;
    }


    /**
     * 分享
     */
    private void share() {
        MyApplication.getmInstance().setLoginDialogIsShow(false);
        if (MyApplication.getmInstance().getUid().equals("")) return;
        ShareUntil.getInstance().share(71, shareTitle, shareBody, mcontext);
    }


    @Override
    public void onEvent(CurrencyEvent currencyEvent) {
        super.onEvent(currencyEvent);
        if (currencyEvent.getWhat() == CurrencyEvent.SHAREOK) {
            updataPinLun = true;
            FormBody.Builder builder = new FormBody.Builder();
            builder.add("type", "61");
            builder.add("video_id", MusicServiceData.getInstance().getAlbumId());
            builder.add("uid", MyApplication.getmInstance().getUid());
            builder.add("points", "0");
            Post(HttpUntil.GetIntent().addShareScore(), builder, 6, false);
        }

        if (currencyEvent.getWhat() == CurrencyEvent.FINISHMUSIC) {
            finish();
        }

        if (currencyEvent.getWhat() == CurrencyEvent.PINGLUNOK) {
            LogUtil.logError("评论刷新");
            updataPinLun = true;
            ShuaXin();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 1) {
            updataPinLun = true;
        }
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        super.GetHttpBackStr(str, what);
        switch (what) {
            case 5:
                if (codeIsOne(str, false)) {
                    MusicServiceData.getInstance().setCollection();
                    if (MusicServiceData.getInstance().isCollection()) {
                        viewHolder.shouChangImg.setImageResource(R.mipmap.bbttsc);
                    } else {
                        viewHolder.shouChangImg.setImageResource(R.mipmap.aixinsc);
                    }
                }
                break;
            case 6:
                if (codeIsOne(str, true)) {
                    ShuaXin();
                }
                break;
        }
    }

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            GuShiNeiRongData data = new Gson().fromJson(str, GuShiNeiRongData.class);
            LogUtil.logError(str);
            if (mMyBinder != null && isFirst) mMyBinder.setData(data);

            zanQB.bindTarget(one).setBadgeNumber(Integer.parseInt(data.getData().getInfo().getAlbum().getThumb_up_count())).setBadgeTextSize(7, true);
            zhuanQB.bindTarget(two).setBadgeNumber(Integer.parseInt(data.getData().getInfo().getAlbum().getShare_count())).setBadgeTextSize(7, true);
            pinLunQB.bindTarget(three).setBadgeNumber(Integer.parseInt(data.getData().getInfo().getAlbum().getComment_count())).setBadgeTextSize(7, true);

            shareTitle = data.getData().getInfo().getAlbum().getShare_title();
            shareBody = data.getData().getInfo().getAlbum().getShare_content();

            /*点赞状态*/
            if (data.getData().getInfo().getAlbum().getIs_thumb_up() == 1) {
                dianZan.setImageResource(R.mipmap.zan);
            } else {
                dianZan.setImageResource(R.mipmap.meizan);
            }

            MusicServiceData.getInstance().getComment().clear();
            MusicServiceData.getInstance().getComment().addAll(data.getData().getComment());
            getAdapter().notifyDataSetChanged();

            if (!updataPinLun) updataUi();
            updataPinLun = false;
            isFirst = false;
        }
    }

    /**
     * 更新ui
     */
    private void updataUi() {
        viewHolder.title.setText(MusicServiceData.getInstance().getTitle());
        viewHolder.zhengTitle.setText(MusicServiceData.getInstance().getTitle());
        viewHolder.smallTitle.setText(MusicServiceData.getInstance().getSmallTitle());
        Glide.with(mcontext).load(MusicServiceData.getInstance().getGuShiBack()).into(viewHolder.img);

        viewHolder.titleT.setText(MusicServiceData.getInstance().getZhuanJiTitle());
        Glide.with(mcontext).load(MusicServiceData.getInstance().getZhuanJiBack()).into(viewHolder.littleImg);
        String count = "共" + MusicServiceData.getInstance().getZhuanJiCount() + "个故事";
        viewHolder.count.setText(count);
        String size = MusicServiceData.getInstance().getComment().size() + "";

        new QBadgeView(this).bindTarget(three).setBadgeNumber(Integer.parseInt(size)).setBadgeTextSize(7, true);

        if (MusicServiceData.getInstance().isCollection()) {
            viewHolder.shouChangImg.setImageResource(R.mipmap.bbttsc);
        } else {
            viewHolder.shouChangImg.setImageResource(R.mipmap.aixinsc);
        }

        if (MusicServiceData.getInstance().isSpeed()) {
            viewHolder.speed.setImageResource(R.mipmap.bbttdanqu);
        } else {
            viewHolder.speed.setImageResource(R.mipmap.bbttsunxu);
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            GuShiNeiRongData data = new Gson().fromJson(str, GuShiNeiRongData.class);
            MusicServiceData.getInstance().getComment().addAll(data.getData().getComment());
            getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_music_layout;
    }


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;


    @BindView(R.id.pinlunEditText)
    TextView pinlunEditText;
    @BindView(R.id.dianZan)
    ImageView dianZan;
    @BindView(R.id.one)
    View one;
    @BindView(R.id.zan)
    RelativeLayout zan;
    @BindView(R.id.shareImg)
    ImageView shareImg;
    @BindView(R.id.two)
    View two;
    @BindView(R.id.zhuan)
    RelativeLayout zhuan;
    @BindView(R.id.three)
    View three;
    @BindView(R.id.pinlun)
    RelativeLayout pinlun;
    @BindView(R.id.down)
    LinearLayout down;


    static class ViewHolder {
        @BindView(R.id.back)
        LinearLayout back;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.rightImg)
        ImageView rightImg;
        @BindView(R.id.right)
        RelativeLayout right;
        @BindView(R.id.topRel)
        RelativeLayout topRel;
        @BindView(R.id.speed)
        ImageView speed;
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
        @BindView(R.id.shouChang)
        LinearLayout shouChang;
        @BindView(R.id.shouChangImg)
        ImageView shouChangImg;
        @BindView(R.id.down)
        LinearLayout down;
        @BindView(R.id.time)
        LinearLayout time;
        @BindView(R.id.doc)
        ImageView doc;
        @BindView(R.id.littleImg)
        ImageView littleImg;
        @BindView(R.id.title_t)
        TextView titleT;
        @BindView(R.id.count)
        TextView count;
        @BindView(R.id.zhengTitle)
        TextView zhengTitle;
        @BindView(R.id.smallTitle)
        TextView smallTitle;
        @BindView(R.id.zhuanJi)
        RelativeLayout zhuanJi;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
