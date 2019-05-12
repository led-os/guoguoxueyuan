package com.test720.grasshoppercollege.module.gongLue.youEr;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseTwoRecyclerViewActivity;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.gongLue.youEr.bean.JingXuanJiGouListData;
import com.test720.grasshoppercollege.untils.HttpUntil;

import www.test720.mylibrary.SPUtils;

import butterknife.BindView;
import okhttp3.FormBody;

public class JingXuanListActivity extends BaseTwoRecyclerViewActivity<JingXuanJiGouListData.DataBean> {
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
        setAdapter(new MyBaseRecyclerAdapter<JingXuanJiGouListData.DataBean>(getList(), mcontext, R.layout.ji_gou_list_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, JingXuanJiGouListData.DataBean item, int postion) {
                holder.setText(R.id.text, item.getName());
                holder.setImageByUrl(R.id.img, item.getCover());
            }
        });
        getAdapter().setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(mcontext, KindergartenActivity.class);
                intent.putExtra("school_id", getList().get(position).getSchool_id());
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
                return "精选机构";
            }

            @Override
            public FormBody.Builder reFormBuilder() {
                FormBody.Builder builder = new FormBody.Builder();
                builder.add("status", "1");
                builder.add("best", "1");
                builder.add("province", SPUtils.getProvince(mcontext));
                builder.add("city", SPUtils.getCity(mcontext));
                builder.add("area", SPUtils.getArea(mcontext));
                builder.add("p", page + "");
                return builder;
            }

            @Override
            public String reUrl() {
                return HttpUntil.GetIntent().getIP() + "small.php/StrategyChild/loadMoreSchool";
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

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            JingXuanJiGouListData data = new Gson().fromJson(str, JingXuanJiGouListData.class);
            getList().clear();
            getList().addAll(data.getData());
            getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            JingXuanJiGouListData data = new Gson().fromJson(str, JingXuanJiGouListData.class);
            getList().addAll(data.getData());
            getAdapter().notifyDataSetChanged();
        }
    }

}