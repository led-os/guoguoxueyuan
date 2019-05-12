package com.test720.grasshoppercollege.module.yuWen.tingXieZhuShou;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseRecyclerViewActivity;
import com.test720.grasshoppercollege.HttpBean.TingLiListData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.SubjectActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.FormBody;

public class TingLiListActivity extends BaseRecyclerViewActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.root)
    LinearLayout root;
    MyBaseRecyclerAdapter adapter;
    List<TingLiListData.DataBean.ListBean> list = new ArrayList<>();


    @Override
    protected String setTitle() {
        if (getIntent().getStringExtra("title") != null) return getIntent().getStringExtra("title");
        return "小学语文" + MyApplication.getmInstance().nianji;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.base_shua_xin;
    }

    @Override
    protected void initData() {
        noGengDuo = true;
        root.setBackgroundResource(R.mipmap.tingxiezhushouguankaback);
        root.setPadding(0, 0, 0, 450);
        adapter = new MyBaseRecyclerAdapter<TingLiListData.DataBean.ListBean>(list, this, R.layout.ting_li_list_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, TingLiListData.DataBean.ListBean item, int postion) {
                holder.setText(R.id.text, item.getName());
            }
        };
        adapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
              /*  Intent intent = new Intent(mcontext, TingXieTypeActivity.class);
                intent.putExtra("id", Integer.parseInt(list.get(position).getListen_id()));
                jumpToActivity(intent, false);*/
                Intent intent = new Intent(mcontext, SubjectActivity.class);
                intent.putExtra("type", "听写助手");
                intent.putExtra("title", list.get(position).getName());
                intent.putExtra("path", list.get(position).getWeb_url());
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
        return HttpUntil.GetIntent().contentIndex();
    }

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            TingLiListData data = new Gson().fromJson(str, TingLiListData.class);
            list.clear();
            list.addAll(data.getData().getList());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void gengDuo(String str) {

    }

    @Override
    public FormBody.Builder formBuilder() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("book_id", getIntent().getStringExtra("book_id"));
        return builder;
    }


}
