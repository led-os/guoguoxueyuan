package com.test720.grasshoppercollege.module.gongLue.student.onLine;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseTwoRecyclerViewActivity;
import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.WebViewActivity;
import com.test720.grasshoppercollege.module.gongLue.dingDan.DingDanDialog;
import com.test720.grasshoppercollege.module.gongLue.student.bean.OnLineXiangQingData;
import com.test720.grasshoppercollege.untils.HttpUntil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.FormBody;

public class OnLineActivity extends BaseTwoRecyclerViewActivity<String> {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.buy)
    TextView buy;

    String line_id = "";
    OnLineXiangQingData data;
    ViewHolder viewHolder;
    WebView webView;


    @Override
    protected int setlayoutResID() {
        return R.layout.activity_on_line_xiang_qing;
    }

    @Override
    protected void initData() {
        setAdapter(new MyBaseRecyclerAdapter<String>(getList(), mcontext, R.layout.image_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, String item, int postion) {

            }
        });
        View view = LayoutInflater.from(mcontext).inflate(R.layout.on_line_head, null);
        viewHolder = new ViewHolder(view);
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
                return "详情";
            }

            @Override
            public FormBody.Builder reFormBuilder() {
                line_id = getIntent().getStringExtra("line_id");
                FormBody.Builder builder = new FormBody.Builder();
                builder.add("line_id", line_id);
                builder.add("uid", MyApplication.getmInstance().getUid());
                return builder;
            }

            @Override
            public String reUrl() {
                return HttpUntil.GetIntent().StrategylineInfo();
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
                return swipeRefreshLayout;
            }
        };
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        super.GetHttpBackStr(str, what);
        switch (what) {
            case 1:
                if (codeIsOne(str, false)) {
                    data = new Gson().fromJson(str, OnLineXiangQingData.class);
                    Glide.with(mcontext).load(data.getData().getPic()).into(viewHolder.ivHeader);
                    viewHolder.onLineTitle.setText(data.getData().getName());
                    viewHolder.onLineCount.setText(data.getData().getAims());
                    String baomingnum = data.getData().getSign_count() + "人报名";
                    viewHolder.num.setText(baomingnum);
                    String kechengnum = "/" + data.getData().getCourse_number() + "节课程";
                    viewHolder.priceBeiZhu.setText(kechengnum);
                    viewHolder.vipPriceBeiZhu.setText(kechengnum);
                    String pr = "￥" + data.getData().getVip_price();
                    viewHolder.vipPrice.setText(pr);
                    String p = "￥" + data.getData().getPrice();
                    viewHolder.price.setText(p);

                    if (data.getData().getClose_pay().equals("1")) {
                        buy.setVisibility(View.GONE);
                    } else {
                        buy.setVisibility(View.VISIBLE);
                    }
                    if (data.getData().getIs_pay().equals("0")) {
                        buy.setText("立即购买");
                        buy.setEnabled(true);
                    } else {
                        buy.setEnabled(false);
                        buy.setText("已购买");
                    }

                    viewHolder.pinLun.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mcontext, OnLinePinLunListActivity.class);
                            intent.putExtra("line_id", line_id);
                            jumpToActivity(intent, false);
                        }
                    });

                    webView.loadUrl(data.getData().getCourse_desc());
                    viewHolder.daYi.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mcontext, WebViewActivity.class);
                            intent.putExtra("title", data.getData().getName());
                            intent.putExtra("path", data.getData().getCommon_question());
                            jumpToActivity(intent, false);
                        }
                    });
                }
                break;
            case 5:
                codeIsOne(str);
                break;
        }
    }

    @Override
    public void shuaXin(String str) {

    }

    @Override
    public void gengDuo(String str) {

    }

    @OnClick({R.id.contactPinTai, R.id.buy, R.id.yuYue})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.contactPinTai:
                if (data == null) return;
                ContactDialog contactDialog = new ContactDialog();
                contactDialog.setPath(data.getData().getContact_customer());
                contactDialog.show(getSupportFragmentManager(), "dialog");
                break;
            case R.id.buy:
                assert data != null;
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (MyApplication.getmInstance().getUid().equals("")) return;

                DingDanDialog dingDanDialog = new DingDanDialog();
                Bundle bundle = new Bundle();
                bundle.putString("title", data.getData().getName());
                bundle.putString("price", data.getData().getPrice());
                bundle.putString("vip_price", data.getData().getVip_price());
                bundle.putString("startTime", data.getData().getWeek().getStart_time());
                bundle.putString("endTime", data.getData().getWeek().getEnd_time());
                bundle.putString("key_id", line_id);
                bundle.putString("type", data.getData().getType());
                dingDanDialog.setArguments(bundle);
                dingDanDialog.setWeek(data.getData().getWeek().getWeek());
                dingDanDialog.show(getSupportFragmentManager(), "dialog");
                break;
            case R.id.yuYue:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (MyApplication.getmInstance().getUid().equals("")) return;
                YuYueDialog yuYueDialog = new YuYueDialog();
                yuYueDialog.setClick(new YuYueDialog.Click() {
                    @Override
                    public void nameAndPhone(String name, String phone, String nianji) {
                        HttpParams httpParams = new HttpParams();
                        httpParams.put("line_id", line_id);
                        httpParams.put("name", name);
                        httpParams.put("phone", phone);
                        httpParams.put("grade", nianji);
                        httpParams.put("uid", MyApplication.getmInstance().getUid());
                        postResponse(HttpUntil.GetIntent().StrategylineYuYue(), httpParams, 5, true);
                    }
                });
                yuYueDialog.show(getSupportFragmentManager(), "yuYue");
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webView != null) webView.destroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (webView != null) webView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (webView != null) webView.onResume();
    }

    @Override
    public void onEvent(CurrencyEvent currencyEvent) {
        super.onEvent(currencyEvent);
        if (currencyEvent.getWhat() == CurrencyEvent.GONGLUEBUGOK) {
            ShuaXin();
        }
    }

    static class ViewHolder {
        @BindView(R.id.iv_header)
        ImageView ivHeader;
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
        @BindView(R.id.pinLun)
        LinearLayout pinLun;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
