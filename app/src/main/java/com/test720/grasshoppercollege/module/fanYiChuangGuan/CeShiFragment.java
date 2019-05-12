package com.test720.grasshoppercollege.module.fanYiChuangGuan;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseRecyclerViewFragment;
import com.test720.grasshoppercollege.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Lenovo on 2018/4/2.
 */

public class CeShiFragment extends BaseRecyclerViewFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    Unbinder unbinder;
    MyBaseRecyclerAdapter adapter;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;

    Unbinder unbinder1;

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
        title.setText("测试");
        List<String> list = new ArrayList();
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        adapter = new MyBaseRecyclerAdapter<String>(list, getActivity(), R.layout.ce_shi_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, String item, int postion) {

            }
        };
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setListener() {

    }

    @Override
    public int setlayoutResID() {
        return R.layout.fragment_ce_shi;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick(R.id.back)
    public void onViewClicked() {
        getActivity().finish();
    }
}
