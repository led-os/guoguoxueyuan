package com.test720.grasshoppercollege.module.userData.myTeam.team;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseRecyclerViewActivity;
import com.test720.grasshoppercollege.HttpBean.MyTeamYongJinData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.FormBody;

public class MyTeamYongJInActivity extends BaseRecyclerViewActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    MyBaseRecyclerAdapter adapter;
    List<MyTeamYongJinData.DataBean> list = new ArrayList<>();

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
        return HttpUntil.GetIntent().viewUsrCommission();
    }

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            MyTeamYongJinData data = new Gson().fromJson(str, MyTeamYongJinData.class);
            list.clear();
            list.addAll(data.getData());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            MyTeamYongJinData data = new Gson().fromJson(str, MyTeamYongJinData.class);
            list.addAll(data.getData());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public FormBody.Builder formBuilder() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("uid", MyApplication.getmInstance().getUid());
        builder.add("m_uid", getIntent().getStringExtra("m_uid"));
        builder.add("p", page + "");
        return builder;
    }

    @Override
    protected String setTitle() {
        return "赚取佣金";
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.base_shua_xin;
    }

    @Override
    protected void initData() {
        adapter = new MyBaseRecyclerAdapter<MyTeamYongJinData.DataBean>(list, this, R.layout.team_yong_jin_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, MyTeamYongJinData.DataBean item, int postion) {
                holder.setText(R.id.name, item.getContent());
                holder.setText(R.id.time, item.getTime());
                holder.setText(R.id.jine, item.getPrice());
                holder.setText(R.id.beizhu, "赚取佣金￥" + item.getMoney());
            }

        };
        recyclerView.setAdapter(adapter);
    }

}
