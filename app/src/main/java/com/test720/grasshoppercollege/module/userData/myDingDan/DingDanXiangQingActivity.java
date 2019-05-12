package com.test720.grasshoppercollege.module.userData.myDingDan;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseRecyclerViewActivity;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.FormBody;

public class DingDanXiangQingActivity extends BaseRecyclerViewActivity {
    MyBaseRecyclerAdapter adapter;
    DingDanXiangQingData dingDanXiangQingData;
    List<DingDanXiangQingData.DataBean.GoodsBean> list = new ArrayList<>();

    HeadHolder headHolder;
    FootHolder footHolder;

    @Override
    protected String setTitle() {
        return "订单详情";
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.base_shua_xin;
    }

    @Override
    protected void initData() {
        View head = LayoutInflater.from(mcontext).inflate(R.layout.ding_dan_xiang_qing_header, null);
        headHolder = new HeadHolder(head);
        View foot = LayoutInflater.from(mcontext).inflate(R.layout.ding_dan_xiang_qing_foot, null);
        footHolder = new FootHolder(foot);
        adapter = new MyBaseRecyclerAdapter<DingDanXiangQingData.DataBean.GoodsBean>(list, mcontext, R.layout.ding_dan_xiang_qing_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, final DingDanXiangQingData.DataBean.GoodsBean item, int postion) {
                holder.setText(R.id.name, item.getName());
                holder.setText(R.id.value, item.getValue());
                holder.setText(R.id.num, "*" + item.getCount());
                holder.setText(R.id.price, "￥" + item.getPrice());
                holder.setImageByUrl(R.id.img, item.getCover());
                if (dingDanXiangQingData.getData().getInfo().getOrder_status().equals("3")) {
                    holder.getView(R.id.one).setVisibility(View.VISIBLE);
                } else {
                    holder.getView(R.id.one).setVisibility(View.GONE);
                }
                holder.getView(R.id.one).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent in = new Intent(mcontext, DingDanPingJiaActivity.class);
                        in.putExtra("gid", item.getGid());
                        in.putExtra("order_num", dingDanXiangQingData.getData().getInfo().getOrder_num());
                        jumpToActivity(in, false);
                    }
                });
            }
        };
        adapter.setHeaderView(head);
        adapter.setmFoot(foot);
        recyclerView.setAdapter(adapter);
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
        return HttpUntil.GetIntent().orderorderInfo();
    }

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            dingDanXiangQingData = new Gson().fromJson(str, DingDanXiangQingData.class);
            list.clear();
            list.addAll(dingDanXiangQingData.getData().getGoods());
            adapter.notifyDataSetChanged();
            initHeader();
            initFoot();
        }
    }

    //加载尾
    private void initFoot() {
        footHolder.time.setText("下单时间" + dingDanXiangQingData.getData().getInfo().getOrder_time());
        footHolder.totalPrice.setText("￥" + dingDanXiangQingData.getData().getInfo().getTotal_price());
        footHolder.point.setText(dingDanXiangQingData.getData().getInfo().getPoints() + "颗");
        footHolder.yunPrice.setText("￥" + dingDanXiangQingData.getData().getInfo().getExpress_fee());
        footHolder.fuPrice.setText("￥" + dingDanXiangQingData.getData().getInfo().getOrder_price());
        footHolder.youHuiJuan.setText("-￥" + dingDanXiangQingData.getData().getInfo().getCoupon_money());
        if (dingDanXiangQingData.getData().getInfo().getPoints_money() != null)
            footHolder.pointPrice.setText("-￥" + dingDanXiangQingData.getData().getInfo().getPoints_money());
    }

    //加载头
    private void initHeader() {
        headHolder.orderNum.setText("订单号：" + dingDanXiangQingData.getData().getInfo().getOrder_num());
        switch (dingDanXiangQingData.getData().getInfo().getOrder_status()) {
            case "0":
                headHolder.orderStatus.setText("待付款");
                break;
            case "1":
                headHolder.orderStatus.setText("待发货");
                break;
            case "2":
                headHolder.orderStatus.setText("待收货");
                break;
            case "3":
                headHolder.orderStatus.setText("待评价");
                break;
            case "4":
                headHolder.orderStatus.setText("已完成");
                break;
            case "5":
                headHolder.orderStatus.setText("已取消");
                break;
        }
        headHolder.nameText.setText(dingDanXiangQingData.getData().getAddress().getConsignee());
        headHolder.phoneText.setText(dingDanXiangQingData.getData().getAddress().getPhone());
        headHolder.addressText.setText(dingDanXiangQingData.getData().getAddress().getProvince() +
                dingDanXiangQingData.getData().getAddress().getCity() +
                dingDanXiangQingData.getData().getAddress().getArea() +
                dingDanXiangQingData.getData().getAddress().getOther());
    }

    @Override
    public void gengDuo(String str) {

    }

    @Override
    public FormBody.Builder formBuilder() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("order_num", getIntent().getStringExtra("order_num"));
        return builder;
    }


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.root)
    LinearLayout root;

    static class HeadHolder {
        @BindView(R.id.order_num)
        TextView orderNum;
        @BindView(R.id.order_status)
        TextView orderStatus;
        @BindView(R.id.re1)
        RelativeLayout re1;
        @BindView(R.id.name_text)
        TextView nameText;
        @BindView(R.id.phone_text)
        TextView phoneText;
        @BindView(R.id.img_dress)
        ImageView imgDress;
        @BindView(R.id.address_text)
        TextView addressText;
        @BindView(R.id.re2)
        LinearLayout re2;
        @BindView(R.id.jb)
        ImageView jb;
        @BindView(R.id.good)
        TextView good;
        @BindView(R.id.re3)
        RelativeLayout re3;

        HeadHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class FootHolder {
        @BindView(R.id.total_name)
        TextView totalName;
        @BindView(R.id.total_price)
        TextView totalPrice;
        @BindView(R.id.re4)
        RelativeLayout re4;
        @BindView(R.id.yun_fei)
        TextView yunFei;
        @BindView(R.id.yun_price)
        TextView yunPrice;
        @BindView(R.id.youHuiJuan)
        TextView youHuiJuan;
        @BindView(R.id.re5)
        RelativeLayout re5;
        @BindView(R.id.shifu)
        TextView shifu;
        @BindView(R.id.fu_price)
        TextView fuPrice;
        @BindView(R.id.re6)
        RelativeLayout re6;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.point)
        TextView point;
        @BindView(R.id.pointPrice)
        TextView pointPrice;
        @BindView(R.id.re7)
        RelativeLayout re7;

        FootHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
