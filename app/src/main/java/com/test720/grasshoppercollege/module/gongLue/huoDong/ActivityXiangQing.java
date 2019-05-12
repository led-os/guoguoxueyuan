package com.test720.grasshoppercollege.module.gongLue.huoDong;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.makeramen.roundedimageview.RoundedImageView;
import com.test720.grasshoppercollege.Adapter.HeadGridViewAdapter;
import com.test720.grasshoppercollege.BaseTwoRecyclerViewActivity;
import com.test720.grasshoppercollege.HttpBean.PingLunData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.gongLue.huoDong.bean.ActivityData;
import com.test720.grasshoppercollege.myViews.NoScrollGridView;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.pinLunUntil.PinLunUntil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.FormBody;
import q.rorbin.badgeview.QBadgeView;

public class ActivityXiangQing extends BaseTwoRecyclerViewActivity<PingLunData> {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.huoDongStatus)
    TextView huoDongStatus;
    @BindView(R.id.liJiBaoMing)
    TextView liJiBaoMing;

    ViewHolder viewHolder;
    WebView webView;
    PinLunUntil pinLunUntil;


    @Override
    public ReflshInit reflshInit() {
        return new ReflshInit() {
            @Override
            public String reTitle() {
                return "活动专区";
            }

            @Override
            public FormBody.Builder reFormBuilder() {
                FormBody.Builder builder = new FormBody.Builder();
                builder.add("activity_id", getIntent().getStringExtra("activity_id"));
                builder.add("uid", MyApplication.getmInstance().getUid());
                builder.add("p", page + "");
                return builder;
            }

            @Override
            public String reUrl() {
                return HttpUntil.GetIntent().StrategyactivityInfo();
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
        return R.layout.activity_huo_dong_xiang_qing;
    }

    @Override
    protected void initData() {
        pinLunUntil = new PinLunUntil(getList(), mcontext, new PinLunUntil.PinLunInterface() {
            @Override
            public void Zan(String commentId, int pos) {
                dianZan(commentId);
            }

            @Override
            public void Reply(String commentId, String name) {
                Intent in = new Intent(mcontext, HuiFuActivity.class);
                in.putExtra("comment_id", commentId);
                in.putExtra("name", name);
                in.putExtra("activity_id", getIntent().getStringExtra("activity_id"));
                jumpToActivity(in, false);
            }

            @Override
            public void ItemClick(PingLunData item) {
                Intent in = new Intent(mcontext, PinLunXiangQingActivity.class);
                in.putExtra("comment_id", item.getComment_id());
                in.putExtra("name", item.getNickname());
                in.putExtra("activity_id", getIntent().getStringExtra("activity_id"));
                jumpToActivity(in, false);
            }
        });
        setAdapter(pinLunUntil.getAdapter());
        @SuppressLint("InflateParams") View head = LayoutInflater.from(mcontext).inflate(R.layout.huo_dong_xiang_qing_head_one, null);
        viewHolder = new ViewHolder(head);
        viewHolder.pinLunNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext, HuoDongPinLunListActivity.class);
                intent.putExtra("activity_id", getIntent().getStringExtra("activity_id"));
                jumpToActivity(intent, false);
            }
        });
        viewHolder.zanNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dianZan("0");
            }
        });

        @SuppressLint("InflateParams") View headerTwo = LayoutInflater.from(this).inflate(R.layout.shang_pin_foot, null);
        webView = headerTwo.findViewById(R.id.webView);
        webView.setVisibility(View.VISIBLE);
        webView.getSettings().setJavaScriptEnabled(true); // 启用js
        webView.getSettings().setBlockNetworkImage(false); // 解决图片不显示
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        setAdapter(pinLunUntil.getAdapter());
        getAdapter().setHeaderView(head);
        getAdapter().setmHeadViewTwo(headerTwo);
        recyclerView.setAdapter(getAdapter());

    }

    /**
     * 点赞
     *
     * @param commentId 评论id
     */
    private void dianZan(String commentId) {
        MyApplication.getmInstance().setLoginDialogIsShow(false);
        if (MyApplication.getmInstance().getUid().equals("")) return;
        HttpParams httpParams = new HttpParams();
        httpParams.put("comment_id", commentId);
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        httpParams.put("activity_id", getIntent().getStringExtra("activity_id"));
        postResponse(HttpUntil.GetIntent().getIP() + "small.php/Strategy/activityThumbUp", httpParams, 5, true);
    }


    /*活动详情网页*/
    private void initWebView(String url) {
        //WebView加载web资源
        webView.loadUrl(url);
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
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
    public void onDestroy() {
        super.onDestroy();
        //释放资源
        webView.destroy();
        webView = null;
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        super.GetHttpBackStr(str, what);
        if (what == 5) {
            if (codeIsOne(str)) {
                ShuaXin();
            }
        }
    }

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            ActivityData data = new Gson().fromJson(str, ActivityData.class);
            Glide.with(mcontext).load(data.getData().getPic()).into(viewHolder.img);
            viewHolder.name.setText(data.getData().getOriginator_name());
            Glide.with(mcontext).load(data.getData().getOriginator_header()).into(viewHolder.head);

            String t = "可参加" + data.getData().getTotal_count() + "人";
            String p = "已参加" + data.getData().getPart_count() + "人";
            viewHolder.total.setText(t);
            viewHolder.part.setText(p);
            viewHolder.content.setText(data.getData().getDesc());
            viewHolder.leve.setRating(Float.parseFloat(data.getData().getOriginator_level()));
            viewHolder.title.setText(data.getData().getName());

            switch (data.getData().getStatus()) {
                case "0":
                    huoDongStatus.setText("活动未开始");
                    break;
                case "1":
                    huoDongStatus.setText("活动进行中");
                    break;
                case "2":
                    huoDongStatus.setText("活动已结束");
                    break;
            }

            switch (data.getData().getUid_status()) {//报名状态
                case "0":
                    liJiBaoMing.setText("立即报名");
                    liJiBaoMing.setBackgroundResource(R.color.appColor);
                    break;
                case "1":
                    liJiBaoMing.setText("已报名");
                    liJiBaoMing.setEnabled(false);
                    liJiBaoMing.setBackgroundResource(R.color.hui);
                    break;
                case "2":
                    liJiBaoMing.setText("报名结束");
                    liJiBaoMing.setEnabled(false);
                    liJiBaoMing.setBackgroundResource(R.color.hui);
                    break;
            }

            viewHolder.flow.setAdapter(new TagAdapter<String>(data.getData().getOriginator_tag()) {
                @Override
                public View getView(FlowLayout parent, int position, String str) {
                    @SuppressLint("InflateParams") View view = LayoutInflater.from(mcontext).inflate(R.layout.shang_pin_biao_qian, null);
                    TextView textView = view.findViewById(R.id.text);
                    textView.setText(str);
                    textView.setTextSize(14);
                    textView.setBackgroundResource(R.drawable.raido_bule_bian_kuang);
                    textView.setTextColor(getResources().getColor(R.color.appColor));
                    return view;
                }
            });
            HeadGridViewAdapter headGridViewAdapter = new HeadGridViewAdapter(data.getData().getPersonnel(), mcontext);
            viewHolder.linPeople.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mcontext, HuoDongPeoPleListActivity.class);
                    intent.putExtra("activity_id", getIntent().getStringExtra("activity_id"));
                    jumpToActivity(intent, false);
                }
            });
            viewHolder.gridView.setAdapter(headGridViewAdapter);
            new QBadgeView(this).bindTarget(viewHolder.zanNum).setBadgeNumber(Integer.parseInt(data.getData().getThumb_up_count())).setBadgeTextSize(7, true);
            new QBadgeView(this).bindTarget(viewHolder.pinLunNum).setBadgeNumber(Integer.parseInt(data.getData().getComment_count())).setBadgeTextSize(7, true);
            initWebView(data.getData().getActivity_desc());
            getLinearLayoutManager().scrollToPositionWithOffset(0, 0);
        }


    }


    @Override
    public void gengDuo(String str) {
       /* if (codeIsOne(str, false)) {
            ActivityData data = new Gson().fromJson(str, ActivityData.class);
            getList().addAll(data.getData().getComment());
            getAdapter().notifyDataSetChanged();
        }*/
    }

    @OnClick({R.id.liJiBaoMing})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.liJiBaoMing:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (!MyApplication.getmInstance().getUid().equals("")) {
                    baoMing();
                }
                break;
        }
    }

    /**
     * 立即报名
     */
    private void baoMing() {

        HuoDongBaoMingDialog huoDongBaoMingDialog = new HuoDongBaoMingDialog();
        huoDongBaoMingDialog.setOkStr("立即报名");
        huoDongBaoMingDialog.setClick(new HuoDongBaoMingDialog.Click() {
            @Override
            public void nameAndPhone(String name, String phone) {
                HttpParams httpParams = new HttpParams();
                httpParams.put("activity_id", getIntent().getStringExtra("activity_id"));
                httpParams.put("uid", MyApplication.getmInstance().getUid());
                httpParams.put("phone", phone);
                httpParams.put("name", name);
                postResponse(HttpUntil.GetIntent().StrategyactivitySignUp(), httpParams, 5, true);
            }
        });
        huoDongBaoMingDialog.show(getSupportFragmentManager(), "baoMing");
    }


    static class ViewHolder {
        @BindView(R.id.linPeople)
        ImageView linPeople;
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.content)
        TextView content;
        @BindView(R.id.total)
        TextView total;
        @BindView(R.id.part)
        TextView part;
        @BindView(R.id.zanNum)
        TextView zanNum;
        @BindView(R.id.pinLunNum)
        TextView pinLunNum;
        @BindView(R.id.head)
        RoundedImageView head;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.leve)
        RatingBar leve;
        @BindView(R.id.flow)
        TagFlowLayout flow;
        @BindView(R.id.gridview)
        NoScrollGridView gridView;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
