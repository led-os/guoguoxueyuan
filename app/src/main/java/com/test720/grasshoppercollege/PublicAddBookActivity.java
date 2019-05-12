package com.test720.grasshoppercollege;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.BookItemAdapter;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.HttpBean.KeWenBookListData;
import com.test720.grasshoppercollege.myViews.NoScrollGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public abstract class PublicAddBookActivity extends BaseRecyclerViewActivity implements BookItemAdapter.ImgClick {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.activity_public_add_book)
    LinearLayout activityPublicAddBook;
    MyBaseRecyclerAdapter adapter;
    List<KeWenBookListData.DataBean.ListBean> listBeanList = new ArrayList<>();

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_public_add_book;
    }

    @Override
    public void init() {
        super.init();
        setNoGengDuo(true);//无分页
    }

    @Override
    protected void initData() {
        adapter = new MyBaseRecyclerAdapter<KeWenBookListData.DataBean.ListBean>(listBeanList, this, R.layout.add_book_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, KeWenBookListData.DataBean.ListBean item, int postion) {
                holder.setText(R.id.version, item.getBook_version());
                NoScrollGridView gridView = holder.getView(R.id.gridview);
                BookItemAdapter adapter = new BookItemAdapter(item.getInfo(), mcontext);
                adapter.setImgClick(PublicAddBookActivity.this);//添加课本点击事件
                gridView.setAdapter(adapter);
            }
        };
        recyclerView.setAdapter(adapter);
    }

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
    public void shuaXin(String str) {
        if (codeIsOne(str)) {
            listBeanList.clear();
            KeWenBookListData data = new Gson().fromJson(str, KeWenBookListData.class);
            listBeanList.addAll(data.getData().getList());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void gengDuo(String str) {
    }

}
