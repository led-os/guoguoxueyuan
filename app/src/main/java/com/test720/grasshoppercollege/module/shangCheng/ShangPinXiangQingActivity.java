package com.test720.grasshoppercollege.module.shangCheng;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseRecyclerViewActivity;
import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.ImagePagerActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.gongLue.student.onLine.ContactDialog;
import com.test720.grasshoppercollege.module.shangCheng.bean.ShangPinXiangQingData;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.FormBody;


public class ShangPinXiangQingActivity extends BaseRecyclerViewActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.activity_shang_pin_xiang_qing)
    LinearLayout activityShangPinXiangQing;
    @BindView(R.id.gouwuChe)
    TextView gouwuChe;
    @BindView(R.id.liJiGouMai)
    TextView liJiGouMai;
    ViewHolder headerView;
    String url = "";
    private MyBaseRecyclerAdapter adapter;
    private List<ShangPinXiangQingData.DataBean.GoodsBean.CommentBean> pinLunlist = new ArrayList<>();
    WebView webView;
    ShangPinXiangQingData data;
    String gid = "";

    @Override
    public void setSys() {
        super.setSys();
        noLine = true;
    }

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_shang_pin_xiang_qing;
    }

    @Override
    protected void initData() {
        /*头部信息*/
        View header = LayoutInflater.from(this).inflate(R.layout.shang_pin_header, null);
        View headerTwo = LayoutInflater.from(this).inflate(R.layout.shang_pin_foot, null);

        webView = headerTwo.findViewById(R.id.webView);
        webView.setVerticalScrollBarEnabled(false);
        webView.setVerticalScrollbarOverlay(false);
        webView.setHorizontalScrollBarEnabled(false);
        webView.setHorizontalScrollbarOverlay(false);

        webView.getSettings().setJavaScriptEnabled(true); // 启用js
        webView.getSettings().setBlockNetworkImage(false); // 解决图片不显示
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        headerView = new ViewHolder(header);
        initHeader();
        /*尾部信息*/
        initAdapter(header, headerTwo);
    }

    /*添加头部点击事件等等*/
    private void initHeader() {
        headerView.yuanjia.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        headerView.pinLunNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gid == null) return;
                Intent intent = new Intent(mcontext, ShangPinPinLunActivity.class);
                intent.putExtra("gid", gid);
                jumpToActivity(intent, false);
            }
        });
        headerView.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /*商品详情网页*/
    private void initWebView() {
        //自适应屏幕
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.getSettings().setLoadWithOverviewMode(true);
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
    protected void onDestroy() {
        super.onDestroy();
        webView.destroy();
        webView = null;
    }


    @Override
    public void onEvent(CurrencyEvent currencyEvent) {
        if (currencyEvent.getWhat() == CurrencyEvent.MYORDER) {
            LogUtil.logError(currencyEvent.getMsg().toString());
            finish();
        }
    }

    /*初始化adapter*/
    private void initAdapter(final View header, View foot) {
        adapter = new MyBaseRecyclerAdapter<ShangPinXiangQingData.DataBean.GoodsBean.CommentBean>(pinLunlist, ShangPinXiangQingActivity.this, R.layout.shang_pin_pinlun) {
            @Override
            public void convert(BaseRecyclerHolder holder, final ShangPinXiangQingData.DataBean.GoodsBean.CommentBean item, int postion) {
                holder.setHeaderByUrl(R.id.iv_icon, item.getHeader());
                holder.setText(R.id.name, item.getNickname());
                holder.setText(R.id.time, item.getTime());
                holder.setText(R.id.body, item.getContent());
                RatingBar ratingBar = holder.getView(R.id.ratingBar);
                ratingBar.setRating(Float.parseFloat(item.getLevel()));

                // 照片
                holder.getView(R.id.one).setVisibility(View.GONE);
                holder.getView(R.id.two).setVisibility(View.GONE);
                holder.getView(R.id.three).setVisibility(View.GONE);
                for (int i = 0; i < item.getPic().size(); i++) {
                    switch (i) {
                        case 0:
                            holder.getView(R.id.one).setVisibility(View.VISIBLE);
                            holder.setImageByUrl(R.id.one, item.getPic().get(0));
                            holder.getView(R.id.one).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ImagePagerActivity.startImagePagerActivity(mcontext, item.getPic(), 0, new ImagePagerActivity
                                            .ImageSize(v.getMeasuredWidth(), v.getMeasuredHeight()));
                                }
                            });
                            break;
                        case 1:
                            holder.getView(R.id.two).setVisibility(View.VISIBLE);
                            holder.setImageByUrl(R.id.two, item.getPic().get(1));
                            holder.getView(R.id.two).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ImagePagerActivity.startImagePagerActivity(mcontext, item.getPic(), 1, new ImagePagerActivity
                                            .ImageSize(v.getMeasuredWidth(), v.getMeasuredHeight()));
                                }
                            });
                            break;
                        case 2:
                            holder.getView(R.id.three).setVisibility(View.VISIBLE);
                            holder.setImageByUrl(R.id.three, item.getPic().get(2));
                            holder.getView(R.id.three).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ImagePagerActivity.startImagePagerActivity(mcontext, item.getPic(), 2, new ImagePagerActivity
                                            .ImageSize(v.getMeasuredWidth(), v.getMeasuredHeight()));
                                }
                            });
                            break;
                    }
                }

            }

        };
        adapter.setHeaderView(header);
        adapter.setmHeadViewTwo(foot);
        recyclerView.setAdapter(adapter);
    }


    @OnClick({R.id.gouwuChe, R.id.liJiGouMai, R.id.lianXiPinTai})
    public void onClick(View view) {
        MyApplication.getmInstance().setLoginDialogIsShow(false);
        if (MyApplication.getmInstance().getUid().equals("")) {
            return;
        }
        switch (view.getId()) {
            case R.id.gouwuChe:
                showGouWuPop(true);
                break;
            case R.id.liJiGouMai:
                showGouWuPop(false);
                break;
            case R.id.lianXiPinTai:
                if (data == null) return;
                ContactDialog contactDialog = new ContactDialog();
                contactDialog.setPath(data.getData().getContact_customer());
                contactDialog.show(getSupportFragmentManager(), "dialog");
                break;
        }
    }

    /*购物车弹框*/
    private void showGouWuPop(boolean isCar) {
        if (data == null) {
            ShowToast("数据加载中，求稍后");
            return;
        }
        DingDanTypeSetDialog dingDanTypeSetDialog = new DingDanTypeSetDialog();
        dingDanTypeSetDialog.setList(data.getData().getGoods().getSpec());
        dingDanTypeSetDialog.setCar(isCar);
        dingDanTypeSetDialog.setGoToPayListener(new DingDanTypeSetDialog.GoToPayListener() {
            @Override
            public void goToPay() {
                finish();
            }
        });
        dingDanTypeSetDialog.setGid(gid);
        dingDanTypeSetDialog.show(getSupportFragmentManager(), "diang");
    }

    @Override
    public int setCount() {
        return 1;
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
    public String url() {
        return HttpUntil.GetIntent().MallgoodsInfo();
    }

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            data = new Gson().fromJson(str, ShangPinXiangQingData.class);

            url = data.getData().getGoods().getInfo().getDesc();
            headerView.maijia.setText(data.getData().getGoods().getInfo().getPrice());
            headerView.yuanjia.setText("原价￥" + data.getData().getGoods().getInfo().getOriginal_price());
            headerView.vipPrice.setText("vip价格￥" + data.getData().getGoods().getInfo().getVip_price());
            headerView.saleCount.setText("已售:" + data.getData().getGoods().getInfo().getSale_count() + "件");
//            headerView.kuaiDiFei.setText("快递费"+data.getData().getGoods().getInfo().get);
            headerView.guoguodouNum.setText("可用果果豆" + data.getData().getGoods().getInfo().getUse_points());
            headerView.title.setText(data.getData().getGoods().getInfo().getName());
            headerView.pinLunNum.setText(data.getData().getGoods().getInfo().getComment_count());
            /*添加轮播信息*/
            initBanner(data);
            //商品介绍
            webView.setVisibility(View.VISIBLE);
            initWebView();
        }
    }

    private void initBanner(ShangPinXiangQingData data) {
        headerView.slider.removeAllSliders();
        for (int i = 0; i < data.getData().getGoods().getImg().size(); i++) {
            TextSliderView textSliderView = new TextSliderView(this);
            textSliderView
                    .description("")//描述
                    .image(data.getData().getGoods().getImg().get(i))//image方法可以传入图片url、资源id、File
                    .setScaleType(BaseSliderView.ScaleType.Fit);//图片缩放类型
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle().putString("extra", "");//传入参数
            headerView.slider.addSlider(textSliderView);//添加一个滑动页面
        }
        headerView.slider.setPresetTransformer(SliderLayout.Transformer.Accordion);//滑动动画
//        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);//默认指示器样式
        headerView.slider.setCustomIndicator(headerView.customIndicator2);//自定义指示器
        headerView.slider.setCustomAnimation(new DescriptionAnimation());//设置图片描述显示动画
        headerView.slider.setDuration(4000);//设置滚动时间，也是计时器时间
    }

    @Override
    public void gengDuo(String str) {

    }

    @Override
    public FormBody.Builder formBuilder() {
        gid = getIntent().getStringExtra("gid");
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("gid", gid);
        builder.add("p", page + "");
        return builder;
    }


    static class ViewHolder {
        @BindView(R.id.slider)
        SliderLayout slider;
        @BindView(R.id.custom_indicator2)
        PagerIndicator customIndicator2;
        @BindView(R.id.maijia)
        TextView maijia;
        @BindView(R.id.saleCount)
        TextView saleCount;
        @BindView(R.id.yuanjia)
        TextView yuanjia;
        @BindView(R.id.vipPrice)
        TextView vipPrice;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.guoguodouNum)
        TextView guoguodouNum;
        @BindView(R.id.xiangqing)
        TextView xiangqing;
        @BindView(R.id.pinLunNum)
        TextView pinLunNum;
        @BindView(R.id.back)
        ImageView back;


        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
