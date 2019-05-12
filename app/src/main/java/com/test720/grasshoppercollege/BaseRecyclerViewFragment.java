package com.test720.grasshoppercollege;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.untils.EndLessOnScrollListener;

/**
 * _oo0oo_
 * o8888888o
 * 88" . "88
 * (| -_- |)
 * 0\  =  /0
 * ___/`---'\___
 * .' \\|     |// '.
 * / \\|||  :  |||// \
 * / _||||| -卍-|||||- \
 * |   | \\\  -  /// |   |
 * | \_|  ''\---/''  |_/ |
 * \  .-\__  '-'  ___/-. /
 * ___'. .'  /--.--\  `. .'___
 * ."" '<  `.___\_<|>_/___.' >' "".
 * | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 * \  \ `_.   \_ __\ /__ _/   .-` /  /
 * =====`-.____`.___ \_____/___.-`___.-'=====
 * `=---='
 * 佛祖保佑        永无BUG
 * 佛曰:
 * 程序园里程序天，程序天里程序员；
 * 程序猿人写程序，又拿程序换肉钱。
 * 肉饱继续桌前坐，饱暖还是桌前眠；
 * 半迷半醒日复日，码上码下年复年。
 * 但愿叱咤互联世，不愿搬砖码当前；
 * 诸葛周瑜算世事，我来算出得加钱。
 * 别人笑我忒直男，我笑自己太像猿；
 * 但见成都府国内，处处地地程序员。
 * Created by 水东流 on 2018/3/20 0020.
 */

public abstract class BaseRecyclerViewFragment extends BaseFragment {
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefresh;

    public EndLessOnScrollListener endLessOnScrollListener;

    public static final int SHUAXIN = 10001;
    public static final int GENGDUO = 10002;
    public int page = 1;
    public int itemIndexCount = -1;//当前位置标示

    LinearLayoutManager layoutManager;

    public int getItemIndexCount() {
        return itemIndexCount;
    }

    public void setItemIndexCount(int itemIndexCount) {
        this.itemIndexCount = itemIndexCount;
    }

    /*初始化count，说明列表是几列*/
    public abstract int setCount();

    public abstract RecyclerView initRecyclerView();

    public abstract SwipeRefreshLayout initSwipeRefreshLayout();

    public abstract String url();

    public abstract void shuaXin(String str);

    public abstract void gengDuo(String str);

    public abstract HttpParams httpParams();

    public boolean noGengDuo = false;

    public boolean haveHead = false;

    public boolean haveLin = true;//是否有分割线

    public void setHaveLin(boolean haveLin) {
        this.haveLin = haveLin;
    }

    public LinearLayoutManager getLayoutManager() {
        return layoutManager;
    }

    @Override
    public void initData() {
        recyclerView = initRecyclerView();
        //处理，list很多，有时，item不置顶
        recyclerView.setFocusable(false);
        swipeRefresh = initSwipeRefreshLayout();

        if (setCount() == 1) {
            layoutManager = new LinearLayoutManager(getActivity());
            //设置布局管理器
            recyclerView.setLayoutManager(layoutManager);
            //设置分隔线
            if (haveLin)
                recyclerView.addItemDecoration(new DividerItemDecoration(mContext, setCount()));
            if (!noGengDuo) {
                endLessOnScrollListener = new EndLessOnScrollListener(layoutManager) {
                    @Override
                    public void onLoadMore(int currentPage) {
                        if (!noGengDuo) {
                            page = currentPage;
                            getData(GENGDUO);
                        }
                    }
                };
                recyclerView.addOnScrollListener(endLessOnScrollListener);
            }
        } else {
            GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), setCount());
            //设置为垂直布局，这也是默认的
            if (haveHead) {
                layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int i) {
                        if (i == 0) return setCount();
                        return 1;
                    }
                });
            }
            //设置布局管理器
            recyclerView.setLayoutManager(layoutManager);

            if (!noGengDuo) {
                endLessOnScrollListener = new EndLessOnScrollListener(layoutManager) {
                    @Override
                    public void onLoadMore(int currentPage) {
                        if (!noGengDuo) {
                            page = currentPage;
                            getData(GENGDUO);
                        }
                    }
                };
                recyclerView.addOnScrollListener(endLessOnScrollListener);
            }
        }
        //设置增加或删除条目的动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        if (swipeRefresh != null)
            swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    swipeRefresh.setRefreshing(false);
                    ShuaXin();
                }
            });
        init();
        ShuaXin();
    }

    public abstract void init();

    /*刷新数据*/
    public void ShuaXin() {
        page = 1;
        if (endLessOnScrollListener != null)
            endLessOnScrollListener.onRefrsh();
        getData(SHUAXIN);
    }

    /*获取数据方法*/
    protected void getData(int what) {
        if (url() != null && httpParams() != null) {
            switch (what) {
                case SHUAXIN:
                    post(url(), httpParams(), what, true);
                    break;
                case GENGDUO:
                    post(url(), httpParams(), what, false);
                    break;
            }
        }

    }

    @Override
    public void getSuccess(String str, int what) {
    }

    @Override
    public void ThreeSuccess(String str, int what) {
        super.ThreeSuccess(str, what);
        switch (what) {
            case SHUAXIN:
                shuaXin(str);
                if (recyclerView.getAdapter() != null && recyclerView.getAdapter().getItemCount() > 0 && itemIndexCount != -1) {
                    recyclerView.setScrollingTouchSlop(getItemIndexCount());
                }
                if (recyclerView.getAdapter().getItemCount() == 0) {
                    swipeRefresh.setBackgroundResource(R.drawable.ic_chat_empty);
                } else {
                    swipeRefresh.setBackgroundResource(R.color.touming);
                }
                break;
            case GENGDUO:
                gengDuo(str);
                break;
        }
    }
}
