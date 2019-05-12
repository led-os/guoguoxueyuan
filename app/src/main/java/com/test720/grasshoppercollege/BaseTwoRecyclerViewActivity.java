package com.test720.grasshoppercollege;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;

public abstract class BaseTwoRecyclerViewActivity<T> extends BaseRecyclerViewActivity {

    List<T> list = new ArrayList<>();
    MyBaseRecyclerAdapter<T> adapter;

    @Override
    public int setCount() {
        return reflshInit().reCount();
    }

    @Override
    public RecyclerView initRecyclerView() {
        return reflshInit().reRecyclerView();
    }

    @Override
    public SwipeRefreshLayout initSwipeRefreshLayout() {
        return reflshInit().reSwipeRefreshLayout();
    }

    @Override
    public String url() {
        return reflshInit().reUrl();
    }


    @Override
    public FormBody.Builder formBuilder() {
        return reflshInit().reFormBuilder();
    }

    @Override
    protected String setTitle() {
        return reflshInit().reTitle();
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public MyBaseRecyclerAdapter<T> getAdapter() {
        return adapter;
    }

    public void setAdapter(MyBaseRecyclerAdapter<T> adapter) {
        this.adapter = adapter;
    }

    public abstract ReflshInit reflshInit();


    public interface ReflshInit {

        String reTitle();

        FormBody.Builder reFormBuilder();

        String reUrl();

        int reCount();

        RecyclerView reRecyclerView();

        SwipeRefreshLayout reSwipeRefreshLayout();

    }
}
