package com.test720.grasshoppercollege;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.test720.grasshoppercollege.untils.EndLessOnScrollListener;

import okhttp3.FormBody;

/*封装一个包含下拉刷新和上拉加载的基本界面*/
public abstract class BaseRecyclerViewActivity<T> extends BaseToolActivity {

    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefresh;

    public EndLessOnScrollListener endLessOnScrollListener;
    GridLayoutManager layoutManager;
    LinearLayoutManager linearLayoutManager;

    public LinearLayoutManager getLinearLayoutManager() {
        return linearLayoutManager;
    }

    public void setLayoutManager(GridLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    public static final int SHUAXIN = 1;
    public static final int GENGDUO = 2;
    public int page = 1;

    int itemClickIndex = -1;//当前标示

    public void setItemClickIndex(int itemClickIndex) {
        this.itemClickIndex = itemClickIndex;
    }

    public int getItemClickIndex() {
        return itemClickIndex;
    }

    /*初始化count，说明列表是几列*/
    public abstract int setCount();

    public abstract RecyclerView initRecyclerView();

    public abstract SwipeRefreshLayout initSwipeRefreshLayout();

    public abstract String url();

    public abstract void shuaXin(String str);

    public abstract void gengDuo(String str);

    public abstract FormBody.Builder formBuilder();

    public boolean haveHeader = false;

    public boolean noGengDuo = false;

    public boolean noLine = false;

    public boolean isNoGengDuo() {
        return noGengDuo;
    }

    public void setNoGengDuo(boolean noGengDuo) {

        this.noGengDuo = noGengDuo;
    }

    public void init() {

    }


    @Override
    public void initView() {
        recyclerViewInitView();
        init();
        recyclerView = initRecyclerView();
        //处理，list很多，有时，item不置顶
        recyclerView.setFocusable(false);
        swipeRefresh = initSwipeRefreshLayout();
/****单列数据*/
        if (setCount() == 1) {
            linearLayoutManager = new LinearLayoutManager(this);
            //设置布局管理器
            recyclerView.setLayoutManager(linearLayoutManager);
            //设置分隔线
            if (!noLine)
                recyclerView.addItemDecoration(new DividerItemDecoration(this, setCount()));
            //设置增加或删除条目的动画
            recyclerView.setItemAnimator(new DefaultItemAnimator());

            if (!noGengDuo) {
                endLessOnScrollListener = new EndLessOnScrollListener(linearLayoutManager) {

                    @Override
                    public void onLoadMore(int currentPage) {
                        page = currentPage;
                        getData(GENGDUO);
                    }
                };
                recyclerView.addOnScrollListener(endLessOnScrollListener);
            }

/*****多列数据*/
        } else {
            if (layoutManager == null) {
                layoutManager = new GridLayoutManager(this, setCount());
                /*如果有头部信息，那么，head需要占count格空间*/
                if (haveHeader) {
                    layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                        @Override
                        public int getSpanSize(int i) {
                            if (i == 0) return setCount();
                            else return 1;
                        }
                    });
                }
            }
            //设置为垂直布局，这也是默认的
            //设置布局管理器
            recyclerView.setLayoutManager(layoutManager);
            //设置增加或删除条目的动画
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            if (!noGengDuo) {
                endLessOnScrollListener = new EndLessOnScrollListener(layoutManager) {
                    @Override
                    public void onLoadMore(int currentPage) {
                        page = currentPage;
                        getData(GENGDUO);
                    }
                };
                recyclerView.addOnScrollListener(endLessOnScrollListener);
            }
        }
        if (swipeRefresh != null)
            swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    swipeRefresh.setRefreshing(false);
//                Toast.makeText(mcontext, "下拉刷新", Toast.LENGTH_SHORT).show();
                    ShuaXin();
                }
            });
        ShuaXin();
    }


    public void recyclerViewInitView() {
    }

    /*刷新数据*/
    public void ShuaXin() {
        if (endLessOnScrollListener != null) {
            page = 1;
            endLessOnScrollListener.onRefrsh();
        }
        getData(SHUAXIN);
    }

    /*获取数据方法*/
    protected void getData(int what) {
        if (formBuilder() == null) return;
        if (what == GENGDUO) {
            Post(url(), formBuilder(), what, false);
        } else {
            Post(url(), formBuilder(), what, true);
        }
    }


    @Override
    public void GetHttpBackStr(String str, int what) {
        switch (what) {
            case SHUAXIN:
                shuaXin(str);
                afterShuaXin();
                break;
            case GENGDUO:
                gengDuo(str);
                break;
        }
        ifNoData();
    }

    /**
     * 如果没有数据
     */
    public void ifNoData() {
        if (recyclerView.getAdapter().getItemCount() == 0) {
            if (swipeRefresh != null) swipeRefresh.setBackgroundResource(R.drawable.ic_chat_empty);
        } else {
            if (swipeRefresh != null) swipeRefresh.setBackgroundResource(R.color.touming);
        }
    }

    /**
     * 刷新完成后执行
     */
    public void afterShuaXin() {
        if (recyclerView.getAdapter() != null && recyclerView.getAdapter().getItemCount() > 0 && itemClickIndex != -1) {
            recyclerView.setScrollingTouchSlop(itemClickIndex);
        }
    }
}
