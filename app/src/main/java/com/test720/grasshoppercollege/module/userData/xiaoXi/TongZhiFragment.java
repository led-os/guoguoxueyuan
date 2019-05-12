package com.test720.grasshoppercollege.module.userData.xiaoXi;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.BaseRecyclerViewFragment;
import com.test720.grasshoppercollege.R;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by 水东流 on 2018/4/19.
 */

public class TongZhiFragment extends BaseRecyclerViewFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    Unbinder unbinder;

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
    public void setListener() {

    }

    @Override
    public int setlayoutResID() {
        return R.layout.base_shua_xin;
    }

}
