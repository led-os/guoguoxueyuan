package com.test720.grasshoppercollege.module.siWeiXuLian.fragment;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseRecyclerViewFragment;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.shangCheng.ShangPinXiangQingActivity;
import com.test720.grasshoppercollege.module.siWeiXuLian.bean.SiWeiShouData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 水东流 on 2018/7/31.
 */

public class ShangPinFragment extends BaseRecyclerViewFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    List<SiWeiShouData.DataBean.CateBean.GoodsBean> list = new ArrayList<>();
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
        return null;
    }

    @Override
    public void shuaXin(String str) {

    }

    @Override
    public void gengDuo(String str) {

    }

    @Override
    public HttpParams httpParams() {
        return null;
    }

    @Override
    public void init() {

    }

    @Override
    public void onStart() {
        super.onStart();
        adapter = new MyBaseRecyclerAdapter<SiWeiShouData.DataBean.CateBean.GoodsBean>(list, getActivity(), R.layout.shang_pin_shou_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, SiWeiShouData.DataBean.CateBean.GoodsBean item, int postion) {
                holder.setImageByUrl(R.id.pic, item.getCover());
                holder.setText(R.id.pice, "￥" + item.getPrice());
                holder.setText(R.id.content, item.getName());
            }
        };
        adapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), ShangPinXiangQingActivity.class);
                intent.putExtra("gid", list.get(position).getGid());
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


    public List<SiWeiShouData.DataBean.CateBean.GoodsBean> getList() {
        return list;
    }

    public void setList(List<SiWeiShouData.DataBean.CateBean.GoodsBean> list) {
        this.list = list;
    }
}
