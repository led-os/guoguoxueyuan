package com.test720.grasshoppercollege;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.HttpBean.DingZhengListData;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.FormBody;

/**
 * Created by 水东流 on 2018/6/20.
 */

public abstract class PublicDingZhengActivity extends BaseRecyclerViewActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    MyBaseRecyclerAdapter adapter;
    List<DingZhengListData.DataBean> list = new ArrayList<>();

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
        return HttpUntil.GetIntent().errorList();
    }

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            DingZhengListData dingZhengListData = new Gson().fromJson(str, DingZhengListData.class);
            list.clear();
            list.addAll(dingZhengListData.getData());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        ShuaXin();
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            DingZhengListData dingZhengListData = new Gson().fromJson(str, DingZhengListData.class);
            list.addAll(dingZhengListData.getData());
            adapter.notifyDataSetChanged();
        }
    }

    public abstract String type();

    @Override
    public FormBody.Builder formBuilder() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("uid", MyApplication.getmInstance().getUid());
        builder.add("class", MyApplication.getmInstance().nianji);
        builder.add("p", page + "");
        builder.add("type", type());
        return builder;
    }


    @Override
    protected int setlayoutResID() {
        return R.layout.activity_ding_zheng;
    }

    @Override
    protected void initData() {
        adapter = new MyBaseRecyclerAdapter<DingZhengListData.DataBean>(list, this, R.layout.ding_zheng_list_item) {

            @Override
            public void convert(BaseRecyclerHolder holder, DingZhengListData.DataBean item, int postion) {
                holder.setText(R.id.time, item.getTime());
                TextView goDo = holder.getView(R.id.goDo);
                if (item.getStatus().equals("1")) {
                    goDo.setBackgroundResource(R.drawable.green_back);
                    goDo.setText("已订正");
                } else {
                    goDo.setText("未订正");
                    goDo.setBackgroundResource(R.drawable.red_back);
                }

            }
        };
        adapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                adapterItemClick(list.get(position).getEq_id());
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        switch (what) {
            case SHUAXIN:
                shuaXin(str);
                break;
            case GENGDUO:
                gengDuo(str);
                break;
        }
    }

    public abstract void adapterItemClick(String id);

}
