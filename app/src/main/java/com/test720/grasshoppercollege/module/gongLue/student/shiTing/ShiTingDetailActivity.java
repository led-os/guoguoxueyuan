package com.test720.grasshoppercollege.module.gongLue.student.shiTing;

import android.content.Intent;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseTwoRecyclerViewActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.WebViewActivity;
import com.test720.grasshoppercollege.module.gongLue.dingDan.BuyClassDingDanActivity;
import com.test720.grasshoppercollege.module.gongLue.student.bean.ShiTingClassData;
import com.test720.grasshoppercollege.module.gongLue.student.onLine.ContactDialog;
import com.test720.grasshoppercollege.module.gongLue.student.smallClass.SmallClassXiangQingActivity;
import com.test720.grasshoppercollege.module.gongLue.student.smallClass.SmallPinLunListActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCMediaManager;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import okhttp3.FormBody;

public class ShiTingDetailActivity extends BaseTwoRecyclerViewActivity {


    String microId = "";
    ShiTingClassData data;
    WebView webView;
    ViewHolder viewHolder;
    /***
     * 打开点击
     */
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (viewHolder == null) return;
            viewHolder.jcBtn.setEnabled(true);
        }
    };

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_small_class_xiang_qing;
    }

    @Override
    protected void initData() {
        setAdapter(new MyBaseRecyclerAdapter<String>(getList(), mcontext, R.layout.image_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, String item, int postion) {

            }
        });
        View view = LayoutInflater.from(mcontext).inflate(R.layout.shi_ting_head, null);
        viewHolder = new ViewHolder(view);
        viewHolder.zuoYe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data != null) {
                    Intent intent = new Intent(mcontext, WebViewActivity.class);
                    intent.putExtra("path", data.getData().getWeb_url());
                    jumpToActivity(intent, false);
                }
            }
        });
        viewHolder.zhuanJi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data == null) return;
                Intent intent = new Intent(mcontext, SmallClassXiangQingActivity.class);
                intent.putExtra("micro_id", data.getData().getMicro_id());
                intent.putExtra("name", data.getData().getName());
                jumpToActivity(intent, false);
            }
        });
        View foot = LayoutInflater.from(mcontext).inflate(R.layout.shang_pin_foot, null);
        webView = foot.findViewById(R.id.webView);
        webView.setVisibility(View.VISIBLE);
        webView.getSettings().setJavaScriptEnabled(true); // 启用js
        webView.getSettings().setBlockNetworkImage(false); // 解决图片不显示
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        getAdapter().setHeaderView(view);
        getAdapter().setmFoot(foot);
        recyclerView.setAdapter(getAdapter());
    }

    @Override
    public ReflshInit reflshInit() {
        return new ReflshInit() {
            @Override
            public String reTitle() {
                if (getIntent().getStringExtra("title") != null)
                    return getIntent().getStringExtra("title");
                return "试听课";
            }

            @Override
            public FormBody.Builder reFormBuilder() {
                microId = getIntent().getStringExtra("micro_class_id");
                FormBody.Builder httpParams = new FormBody.Builder();
                httpParams.add("micro_class_id", microId);
                httpParams.add("uid", MyApplication.getmInstance().getUid());
                return httpParams;
            }

            @Override
            public String reUrl() {
                return HttpUntil.GetIntent().StrategytryListeningInfo();
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
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            data = new Gson().fromJson(str, ShiTingClassData.class);
            if (data.getData().getIs_pay().equals("1")) {//已经购买
                viewHolder.jcBtn.setVisibility(View.GONE);
                viewHolder.jcVideo.setUp(data.getData().getPath(), JCVideoPlayer.SCREEN_LAYOUT_NORMAL, "");
                viewHolder.jcVideo.startButton.performClick();
            } else {
                viewHolder.jcBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ShowToast("请先购买");
                        viewHolder.jcBtn.setEnabled(false);
                        mainHandler.postDelayed(runnable, 1000);
                    }
                });
            }
            viewHolder.onLineTitle.setText(data.getData().getName());
            viewHolder.onLineCount.setText(data.getData().getDesc());
            String n = data.getData().getSubscribe_count() + "人购买";
            viewHolder.num.setText(n);
            String v = "vip " + data.getData().getVip_price();
            viewHolder.vipPrice.setText(v);
            String p = "￥" + data.getData().getPrice();
            viewHolder.price.setText(p);
            viewHolder.title.setText(data.getData().getName());

            String q = "/" + data.getData().getExpiry_date() + "天";
            viewHolder.priceBeiZhu.setText(q);
            viewHolder.vipPriceBeiZhu.setText(q);

            //专辑信息
            viewHolder.zhuanJiTitle.setText(data.getData().getAlbum().getName());
            Glide.with(mcontext).load(data.getData().getAlbum().getCover()).into(viewHolder.zhuanJiPic);
            viewHolder.content.setText(data.getData().getAlbum().getCount());
//已经购买不显示购买按钮
            if (data.getData().getIs_pay().equals("1")) {
                buy.setVisibility(View.GONE);
            }

            webView.loadUrl(data.getData().getCourse_desc());
            viewHolder.daYi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mcontext, WebViewActivity.class);
                    intent.putExtra("path", data.getData().getCommon_question());
                    jumpToActivity(intent, false);
                }
            });

            viewHolder.pinLun.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mcontext, SmallPinLunListActivity.class);
                    intent.putExtra("micro_class_id", microId);
                    jumpToActivity(intent, false);
                }
            });
        }
    }

    @Override
    public void gengDuo(String str) {

    }


    @OnClick({R.id.contactPinTai, R.id.buy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.contactPinTai:
                if (data == null) return;
                ContactDialog contactDialog = new ContactDialog();
                contactDialog.setPath(data.getData().getContact_customer());
                contactDialog.show(getSupportFragmentManager(), "dialog");
                break;
            case R.id.buy:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (MyApplication.getmInstance().getUid().equals("")) return;
                if (data != null) {
                    Intent in = new Intent(mcontext, BuyClassDingDanActivity.class);
                    in.putExtra("key_id", microId);
                    in.putExtra("type", data.getData().getType());
                    in.putExtra("status", "1");
                    in.putExtra("startTime", "");
                    in.putExtra("endTime", data.getData().getExpiry_date() + "天");
                    in.putExtra("time_slot", "");
                    in.putExtra("day", "");
                    startActivity(in);
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webView != null) webView.destroy();
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (webView != null) webView.onPause();
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
        if (webView != null) webView.onResume();
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

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.contactPinTai)
    TextView contactPinTai;
    @BindView(R.id.buy)
    TextView buy;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    static class ViewHolder {
        @BindView(R.id.back)
        ImageView back;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.zuoYe)
        ImageView zuoYe;
        @BindView(R.id.jcVideo)
        JCVideoPlayerStandard jcVideo;
        @BindView(R.id.jcBtn)
        Button jcBtn;
        @BindView(R.id.on_line_title)
        TextView onLineTitle;
        @BindView(R.id.on_line_count)
        TextView onLineCount;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.priceBeiZhu)
        TextView priceBeiZhu;
        @BindView(R.id.vipPrice)
        TextView vipPrice;
        @BindView(R.id.vipPriceBeiZhu)
        TextView vipPriceBeiZhu;
        @BindView(R.id.num)
        TextView num;
        @BindView(R.id.daYi)
        LinearLayout daYi;
        @BindView(R.id.pinluntext)
        TextView pinluntext;
        @BindView(R.id.pinLun)
        LinearLayout pinLun;
        @BindView(R.id.zhuanJiPic)
        ImageView zhuanJiPic;
        @BindView(R.id.zhuanJiTitle)
        TextView zhuanJiTitle;
        @BindView(R.id.content)
        TextView content;
        @BindView(R.id.lin)
        LinearLayout lin;
        @BindView(R.id.zhuanJi)
        LinearLayout zhuanJi;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
