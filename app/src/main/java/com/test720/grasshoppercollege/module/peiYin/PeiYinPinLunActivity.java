package com.test720.grasshoppercollege.module.peiYin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.makeramen.roundedimageview.RoundedImageView;
import com.test720.grasshoppercollege.BaseTwoRecyclerViewActivity;
import com.test720.grasshoppercollege.HttpBean.PingLunData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.peiYin.bean.PeiYinPinLuListData;
import com.test720.grasshoppercollege.module.peiYin.pinLun.PeiYinHuiFuActivity;
import com.test720.grasshoppercollege.module.peiYin.pinLun.PeiYinPinJiaActivity;
import com.test720.grasshoppercollege.module.peiYin.pinLun.PeiYinPingLunXiangQingActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.pinLunUntil.PinLunUntil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCMediaManager;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import okhttp3.FormBody;

public class PeiYinPinLunActivity extends BaseTwoRecyclerViewActivity<PingLunData> implements View.OnClickListener {
    View head;
    ViewHolderHead viewHolderHead;
    PinLunUntil pinLunUntil;
    PeiYinPinLuListData data;

    @Override
    public ReflshInit reflshInit() {
        return new ReflshInit() {
            @Override
            public String reTitle() {
                return "配音";
            }

            @Override
            public FormBody.Builder reFormBuilder() {
                FormBody.Builder builder = new FormBody.Builder();
                builder.add("pei_id", getIntent().getStringExtra("pei_id"));
                builder.add("uid", MyApplication.getmInstance().getUid());
                builder.add("p", page + "");
                return builder;
            }

            @Override
            public String reUrl() {
                return HttpUntil.GetIntent().DubbingdubbingInfo();
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

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_pei_yin_ping_lun;
    }

    @SuppressLint("InflateParams")
    @Override
    protected void initData() {
        head = LayoutInflater.from(mcontext).inflate(R.layout.activity_pei_yin_pin_lun, null);
        viewHolderHead = new ViewHolderHead(head);
        viewHolderHead.zan.setOnClickListener(this);

        pinLunUntil = new PinLunUntil(getList(), mcontext, new PinLunUntil.PinLunInterface() {
            @Override
            public void Zan(String commentId, int pos) {
                FormBody.Builder builder = new FormBody.Builder();
                builder.add("pei_id", getIntent().getStringExtra("pei_id"));
                builder.add("uid", MyApplication.getmInstance().getUid());
                builder.add("comment_id", commentId);
                Post(HttpUntil.GetIntent().DubbingthumbUp(), builder, 5, false);
            }

            @Override
            public void Reply(String commentId, String name) {
                Intent in = new Intent(mcontext, PeiYinHuiFuActivity.class);
                in.putExtra("comment_id", commentId);
                in.putExtra("name", name);
                in.putExtra("pei_id", getIntent().getStringExtra("pei_id"));
                jumpToActivity(in, false);
            }

            @Override
            public void ItemClick(PingLunData item) {
                Intent in = new Intent(mcontext, PeiYinPingLunXiangQingActivity.class);
                in.putExtra("comment_id", item.getComment_id());
                in.putExtra("name", item.getNickname());
                in.putExtra("pei_id", getIntent().getStringExtra("pei_id"));
                jumpToActivity(in, false);
            }
        });
        setAdapter(pinLunUntil.getAdapter());
        getAdapter().setHeaderView(head);
        recyclerView.setAdapter(getAdapter());
    }


    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            data = new Gson().fromJson(str, PeiYinPinLuListData.class);
            getList().clear();
            getList().addAll(data.getData().getComment());
            getAdapter().notifyDataSetChanged();

            viewHolderHead.jcPlayStandard.setUp(data.getData().getInfo().getVideo_path(), JCVideoPlayer.SCREEN_LAYOUT_NORMAL, "");
            viewHolderHead.jcPlayStandard.startButton.performClick();
            viewHolderHead.zanNum.setText(data.getData().getInfo().getThumb_up_count());
            Glide.with(mcontext)
                    .load(data.getData().getInfo().getHeader())
                    .placeholder(R.mipmap.pic_head_default)
                    .error(R.mipmap.pic_head_default)
                    .into(viewHolderHead.pic);
            viewHolderHead.name.setText(data.getData().getInfo().getNickname());
            viewHolderHead.pinglunNum.setText(data.getData().getComment().size());
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            PeiYinPinLuListData data = new Gson().fromJson(str, PeiYinPinLuListData.class);
            getList().addAll(data.getData().getComment());
            getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zan:
                FormBody.Builder builder = new FormBody.Builder();
                builder.add("pei_id", getIntent().getStringExtra("pei_id"));
                builder.add("uid", MyApplication.getmInstance().getUid());
                builder.add("comment_id", "0");
                Post(HttpUntil.GetIntent().DubbingthumbUp(), builder, 5, false);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (JCMediaManager.instance() != null) {
            JCVideoPlayer.releaseAllVideos();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            if (JCMediaManager.instance().mediaPlayer != null) {
                JCMediaManager.instance().mediaPlayer.pause();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            if (JCMediaManager.instance() != null) {
                if (JCMediaManager.instance().mediaPlayer != null) {
                    JCMediaManager.instance().mediaPlayer.start();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.pinLun, R.id.kaiShiPeiYin, R.id.share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pinLun:
                Intent in = new Intent(mcontext, PeiYinPinJiaActivity.class);
                in.putExtra("comment_id", "0");
                in.putExtra("pei_id", getIntent().getStringExtra("pei_id"));
                jumpToActivity(in, false);
                break;
            case R.id.kaiShiPeiYin:
                if (data == null) return;
                Intent intent = new Intent(mcontext, PeiYinXiangQingActivity.class);
                intent.putExtra("con_id", data.getData().getInfo().getCon_id());
                jumpToActivity(intent, false);
                break;
            case R.id.share:
                break;
        }
    }

    static class ViewHolderHead {
        @BindView(R.id.jc_play_standard)
        JCVideoPlayerStandard jcPlayStandard;
        @BindView(R.id.pic)
        RoundedImageView pic;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.zan_num)
        TextView zanNum;
        @BindView(R.id.zan)
        ImageView zan;
        @BindView(R.id.pinglunNum)
        TextView pinglunNum;

        ViewHolderHead(View view) {
            ButterKnife.bind(this, view);
        }
    }

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.pinLun)
    TextView pinLun;
    @BindView(R.id.kaiShiPeiYin)
    TextView kaiShiPeiYin;
    @BindView(R.id.share)
    TextView share;
}
