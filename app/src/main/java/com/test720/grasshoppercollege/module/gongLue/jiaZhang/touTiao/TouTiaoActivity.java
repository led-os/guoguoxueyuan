package com.test720.grasshoppercollege.module.gongLue.jiaZhang.touTiao;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.BaseTwoRecyclerViewActivity;
import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.HttpBean.PingLunData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.gongLue.jiaZhang.bean.TouTiaoData;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.pinLunUntil.PinLunUntil;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;
import q.rorbin.badgeview.QBadgeView;

public class TouTiaoActivity extends BaseTwoRecyclerViewActivity<PingLunData> {

    WebView webView;
    PinLunUntil pinLunUntil;

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_tou_tiao;
    }

    @Override
    protected void initData() {
        @SuppressLint("InflateParams") View head = LayoutInflater.from(this).inflate(R.layout.shang_pin_foot, null);
        webView = head.findViewById(R.id.webView);
        webView.setVisibility(View.VISIBLE);
        webView.getSettings().setJavaScriptEnabled(true); // 启用js
        webView.getSettings().setBlockNetworkImage(false); // 解决图片不显示
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        pinLunUntil = new PinLunUntil(getList(), mcontext, new PinLunUntil.PinLunInterface() {
            @Override
            public void Zan(String commentId, int pos) {
                dianZan(commentId);
            }

            @Override
            public void Reply(String commentId, String name) {
                Intent in = new Intent(mcontext, TouTiaoHuiFuActivity.class);
                in.putExtra("comment_id", commentId);
                in.putExtra("name", name);
                in.putExtra("headline_id", getIntent().getStringExtra("headline_id"));
                jumpToActivity(in, false);
            }

            @Override
            public void ItemClick(PingLunData item) {
                Intent in = new Intent(mcontext, TouTiaoPinLunXiangQingActivity.class);
                in.putExtra("comment_id", item.getComment_id());
                in.putExtra("name", item.getNickname());
                in.putExtra("headline_id", getIntent().getStringExtra("headline_id"));
                jumpToActivity(in, false);
            }
        });
        setAdapter(pinLunUntil.getAdapter());
        getAdapter().setHeaderView(head);
        recyclerView.setAdapter(getAdapter());
    }

    private void dianZan(String commentId) {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("uid", MyApplication.getmInstance().getUid());
        builder.add("comment_id", commentId);
        builder.add("headline_id", getIntent().getStringExtra("headline_id"));
        Post(HttpUntil.GetIntent().parentHeadlineThumbUp(), builder, 5);
    }

    @Override
    public ReflshInit reflshInit() {
        return new ReflshInit() {
            @Override
            public String reTitle() {
                return getIntent().getStringExtra("name");
            }

            @Override
            public FormBody.Builder reFormBuilder() {
                FormBody.Builder builder = new FormBody.Builder();
                builder.add("headline_id", getIntent().getStringExtra("headline_id"));
                builder.add("p", page + "");
                builder.add("uid", MyApplication.getmInstance().getUid());
                return builder;
            }

            @Override
            public String reUrl() {
                return HttpUntil.GetIntent().StrategyheadlineInfo();
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
    protected void onDestroy() {
        super.onDestroy();
        webView.destroy();
        webView = null;
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        super.GetHttpBackStr(str, what);
        if (what == 5 && codeIsOne(str)) {
            ShuaXin();
        }
    }
    @Override
    public void onEvent(CurrencyEvent currencyEvent) {
        super.onEvent(currencyEvent);
        if (currencyEvent.getWhat() == CurrencyEvent.PINGLUNOK && currencyEvent.getMsg().equals("评论成功")) {
            ShuaXin();
        }
    }
    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            TouTiaoData data = new Gson().fromJson(str, TouTiaoData.class);
            new QBadgeView(this).bindTarget(one).setBadgeNumber(Integer.parseInt(data.getData().getThumb_up_count())).setBadgeTextSize(7, true);
//            new QBadgeView(this).bindTarget(two).setBadgeNumber(Integer.parseInt(data.getData().getShare_count())).setBadgeTextSize(7, true);
            new QBadgeView(this).bindTarget(three).setBadgeNumber(Integer.parseInt(data.getData().getComment_count())).setBadgeTextSize(7, true);

            getList().clear();
            getList().addAll(data.getData().getComment());
            getAdapter().notifyDataSetChanged();
            webView.loadUrl(data.getData().getDescWeb());
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            TouTiaoData data = new Gson().fromJson(str, TouTiaoData.class);
            getList().addAll(data.getData().getComment());
            getAdapter().notifyDataSetChanged();
        }
    }

    public void PingJia() {
        Intent in = new Intent(mcontext, TouTiaoPinJiaActivity.class);
        in.putExtra("comment_id", "0");
        in.putExtra("headline_id", getIntent().getStringExtra("headline_id"));
        startActivityForResult(in, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 1) {
            ShuaXin();
        }
    }

    @OnClick({R.id.pinlunEditText, R.id.zan, R.id.pinlun})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zan:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (!MyApplication.getmInstance().getUid().equals("")) {
                    dianZan("0");
                }
                break;

            case R.id.pinlun:
            case R.id.pinlunEditText:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (!MyApplication.getmInstance().getUid().equals("")) {
                    PingJia();
                }
                break;
        }
    }

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.one)
    View one;
    @BindView(R.id.three)
    View three;

}
