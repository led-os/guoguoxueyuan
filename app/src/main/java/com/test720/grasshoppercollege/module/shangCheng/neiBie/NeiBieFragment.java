package com.test720.grasshoppercollege.module.shangCheng.neiBie;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseFragment;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.shangCheng.ShangPinListActivity;
import com.test720.grasshoppercollege.module.shangCheng.bean.LeiBieTeoData;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 水东流 on 2018/7/10.
 */

public class NeiBieFragment extends BaseFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.root)
    LinearLayout root;

    String parentId;
    MyBaseRecyclerAdapter adapter;
    List<LeiBieTeoData.DataBean> list = new ArrayList<>();

    @Override
    public void onStart() {
        super.onStart();
        if (getParentId() != null) {
            HttpParams httpParams = new HttpParams();
            httpParams.put("parent_id", getParentId());
            post(HttpUntil.GetIntent().MallgetCateChildList(), httpParams, 1);
        }

    }

    @Override
    public void initData() {
        GridLayoutManager linearLayoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(linearLayoutManager);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefresh.setRefreshing(false);
                ShuaXin();
            }
        });
        adapter = new MyBaseRecyclerAdapter<LeiBieTeoData.DataBean>(list, getActivity(), R.layout.shang_pin_type_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, LeiBieTeoData.DataBean item, int postion) {
                holder.setText(R.id.name, item.getName());
                holder.setHeaderByUrl(R.id.pic, item.getPic());
            }
        };
        adapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), ShangPinListActivity.class);
                intent.putExtra("cate_id", list.get(position).getCate_id());
                jumpToActivity(intent, false);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void ShuaXin() {
        if (getParentId() == null) return;
        HttpParams httpParams = new HttpParams();
        httpParams.put("parent_id", getParentId());
        post(HttpUntil.GetIntent().MallgetCateChildList(), httpParams, 1);
    }

    @Override
    public void setListener() {

    }

    @Override
    public int setlayoutResID() {
        return R.layout.base_shua_xin;
    }

    @Override
    public void getSuccess(String s, int what) {
        if (codeIsOne(s, false)) {
            LeiBieTeoData data = new Gson().fromJson(s, LeiBieTeoData.class);
            list.clear();
            list.addAll(data.getData());
            adapter.notifyDataSetChanged();
        }
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
