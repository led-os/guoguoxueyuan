package com.test720.grasshoppercollege.module.qianDao;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseTwoRecyclerViewActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.guoguoDou.bean.DouDouJiLVData;
import com.test720.grasshoppercollege.untils.HttpUntil;

import butterknife.BindView;
import okhttp3.FormBody;

public class QianDaoJiLvActivity extends BaseTwoRecyclerViewActivity<DouDouJiLVData.DataBean> {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    @Override
    protected int setlayoutResID() {
        return R.layout.base_shua_xin;
    }

    @Override
    protected void initData() {
        setAdapter(new MyBaseRecyclerAdapter<DouDouJiLVData.DataBean>(getList(), mcontext, R.layout.dou_dou_ji_lv) {
            @Override
            public void convert(BaseRecyclerHolder holder, DouDouJiLVData.DataBean item, int postion) {
                holder.setText(R.id.title, item.getContent());
                holder.setText(R.id.time, item.getTime());
            }
        });
        recyclerView.setAdapter(getAdapter());
    }

    @Override
    public ReflshInit reflshInit() {
        return new ReflshInit() {
            @Override
            public String reTitle() {
                return "签到记录";
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
                return HttpUntil.GetIntent().getIP() + "small.php/IndexUser/signRecord";//果果豆记录
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
            DouDouJiLVData douDouJiLVData = new Gson().fromJson(str, DouDouJiLVData.class);
            getList().clear();
            getList().addAll(douDouJiLVData.getData());
            getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            DouDouJiLVData douDouJiLVData = new Gson().fromJson(str, DouDouJiLVData.class);
            getList().addAll(douDouJiLVData.getData());
            getAdapter().notifyDataSetChanged();
        }
    }

}