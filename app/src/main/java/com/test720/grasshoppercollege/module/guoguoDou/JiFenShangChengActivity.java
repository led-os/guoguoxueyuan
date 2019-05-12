package com.test720.grasshoppercollege.module.guoguoDou;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseTwoRecyclerViewActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.guoguoDou.bean.ShangChengData;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.lunBo.LunBoUntil;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.FormBody;

public class JiFenShangChengActivity extends BaseTwoRecyclerViewActivity<ShangChengData.DataBean.ListBean> {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    ViewHolder viewHolder;

    @Override
    protected int setlayoutResID() {
        haveHeader = true;
        return R.layout.base_shua_xin;
    }

    @Override
    protected void initData() {
        setAdapter(new MyBaseRecyclerAdapter<ShangChengData.DataBean.ListBean>(getList(), mcontext, R.layout.guo_guo_dou_shang_ping_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, ShangChengData.DataBean.ListBean item, int postion) {
                holder.setImageByUrl(R.id.img, item.getCover());
                String p = "果果豆:" + item.getPrice() + "颗";
                holder.setText(R.id.vip, p);
                String f = "库存" + item.getStore_count() + "件";
                holder.setText(R.id.feiVip, f);
                holder.setText(R.id.title, item.getName());
            }
        });
        View head = LayoutInflater.from(mcontext).inflate(R.layout.ji_fen_shang_cheng_head, null);
        getAdapter().setHeaderView(head);
        viewHolder = new ViewHolder(head);
        viewHolder.one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpToActivity(GuoGuoDouJiLvActivity.class, false);
            }
        });
        viewHolder.two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpToActivity(DuiHuanJiLvActivity.class, false);
            }
        });
        getAdapter().setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(mcontext, GuoGuoShangPingXQActivity.class);
                intent.putExtra("gid", getList().get(position).getGid());
                intent.putExtra("title", getList().get(position).getName());
                jumpToActivity(intent, false);
            }
        });
        recyclerView.setAdapter(getAdapter());
    }

    @Override
    public ReflshInit reflshInit() {
        return new ReflshInit() {
            @Override
            public String reTitle() {
                return "果果豆商城";
            }

            @Override
            public FormBody.Builder reFormBuilder() {
                FormBody.Builder builder = new FormBody.Builder();
                builder.add("uid", MyApplication.getmInstance().getUid());
                builder.add("p", page + "");
                return builder;
            }

            @Override
            public String reUrl() {
                return HttpUntil.GetIntent().getIP() + "small.php/User/pointsGoodsIndex";
            }

            @Override
            public int reCount() {
                return 2;
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

    boolean luBo = false;

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            ShangChengData data = new Gson().fromJson(str, ShangChengData.class);
            getList().clear();
            getList().addAll(data.getData().getList());
            getAdapter().notifyDataSetChanged();

            viewHolder.doudou.setText(data.getData().getPoints() + "颗");

            //轮播
            if (!luBo) {
                LunBoUntil lunBoUntil = new LunBoUntil(viewHolder.slider, data.getData().getBanner(), mcontext);
                lunBoUntil.start();
                luBo = true;
            }

        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            ShangChengData data = new Gson().fromJson(str, ShangChengData.class);
            getList().addAll(data.getData().getList());
            getAdapter().notifyDataSetChanged();
        }
    }

    static class ViewHolder {
        @BindView(R.id.slider)
        SliderLayout slider;
        @BindView(R.id.custom_indicator2)
        PagerIndicator customIndicator2;
        @BindView(R.id.lunRel)
        RelativeLayout lunRel;
        @BindView(R.id.doudou)
        TextView doudou;
        @BindView(R.id.one)
        LinearLayout one;
        @BindView(R.id.two)
        LinearLayout two;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
