package com.test720.grasshoppercollege.module.peiYin.keNei;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseRecyclerViewFragment;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.peiYin.PeiYinVideoListActivity;
import com.test720.grasshoppercollege.module.peiYin.bean.ZhuanJiListData;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 水东流 on 2018/8/8.
 */

public class KeNeiBookFragment extends BaseRecyclerViewFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.root)
    LinearLayout root;
    List<ZhuanJiListData.DataBean> list = new ArrayList();
    MyBaseRecyclerAdapter adapter;

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
        return HttpUntil.GetIntent().DubbingalbumList();
    }

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            ZhuanJiListData zhuanJiListData = new Gson().fromJson(str, ZhuanJiListData.class);
            list.clear();
            list.addAll(zhuanJiListData.getData());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            ZhuanJiListData zhuanJiListData = new Gson().fromJson(str, ZhuanJiListData.class);
            list.addAll(zhuanJiListData.getData());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public HttpParams httpParams() {
        Bundle bundle = getArguments();
        HttpParams builder = new HttpParams();
        if (bundle.getString("version") != null) {
            if (bundle.getString("version").equals("全部")) {
                builder.put("version", "");
            } else {
                builder.put("version", bundle.getString("version"));
            }
        }
        builder.put("type", MyApplication.getmInstance().getModuleType()+"");
        builder.put("p", page + "");
        return builder;
    }

    @Override
    public void init() {
        adapter = new MyBaseRecyclerAdapter<ZhuanJiListData.DataBean>(list, getContext(), R.layout.pei_yin_shi_pin_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, ZhuanJiListData.DataBean item, int postion) {

            }
        };
        adapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), PeiYinVideoListActivity.class);
                intent.putExtra("album_id", list.get(position).getAlbum_id());
                intent.putExtra("name", list.get(position).getName());
                jumpToActivity(intent, false);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setListener() {

    }

    @Override
    public int setlayoutResID() {
        return R.layout.base_shua_xin;
    }

}
