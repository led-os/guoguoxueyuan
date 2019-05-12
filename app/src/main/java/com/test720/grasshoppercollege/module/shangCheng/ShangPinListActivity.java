package com.test720.grasshoppercollege.module.shangCheng;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseRecyclerViewActivity;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.shangCheng.bean.LeiBieListData;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.FormBody;

public class ShangPinListActivity extends BaseRecyclerViewActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.root)
    LinearLayout root;
    List<LeiBieListData.DataBean.ListBean> listBeen = new ArrayList<>();
    MyBaseRecyclerAdapter adapter;
    String key = "";

    @Override
    protected String setTitle() {
        return "商品列表";
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.base_shua_xin;
    }

    @Override
    protected void initData() {
        adapter = new MyBaseRecyclerAdapter<LeiBieListData.DataBean.ListBean>(listBeen, this, R.layout.shang_pin_shou_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, LeiBieListData.DataBean.ListBean item, int postion) {
                holder.setImageByUrl(R.id.pic, item.getCover());
                holder.setText(R.id.pice, "￥" + item.getPrice());
                holder.setText(R.id.content, item.getName());
            }
        };
        adapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(ShangPinListActivity.this, ShangPinXiangQingActivity.class);
                intent.putExtra("gid", listBeen.get(position).getGid());
                jumpToActivity(intent, false);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public int setCount() {
        return 2;
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
        return HttpUntil.GetIntent().MallcateGoodsList();
    }

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            LeiBieListData data = new Gson().fromJson(str, LeiBieListData.class);
            listBeen.clear();
            listBeen.addAll(data.getData().getList());
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            LeiBieListData data = new Gson().fromJson(str, LeiBieListData.class);
            listBeen.addAll(data.getData().getList());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public FormBody.Builder formBuilder() {
        FormBody.Builder builder = new FormBody.Builder();
        /*首页传递来的搜索信息*/
        if (getIntent().getStringExtra("key") != null) {
            key = getIntent().getStringExtra("key");
            builder.add("key", key);
        }
        if (getIntent().getStringExtra("cate_id") != null) {
            builder.add("cate_id", getIntent().getStringExtra("cate_id"));
        }
        builder.add("p", page + "");
        return builder;
    }

}
