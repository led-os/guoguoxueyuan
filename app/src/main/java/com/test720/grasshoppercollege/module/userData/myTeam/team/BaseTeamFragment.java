package com.test720.grasshoppercollege.module.userData.myTeam.team;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseRecyclerViewFragment;
import com.test720.grasshoppercollege.HttpBean.TeamData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 水东流 on 2018/4/12.
 * 团队首页
 */

public abstract class BaseTeamFragment extends BaseRecyclerViewFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    List<TeamData.DataBean.ListBeanX.ListBean> list = new ArrayList();
    MyBaseRecyclerAdapter adapter;
    public String OtherUid;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            OtherUid = bundle.getString("uid");
        }
        return super.onCreateView(inflater, container, savedInstanceState);
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
    public String url() {
        return HttpUntil.GetIntent().team();
    }

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            TeamData data = new Gson().fromJson(str, TeamData.class);
            list.clear();
            list.addAll(data.getData().getList().getList());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            TeamData data = new Gson().fromJson(str, TeamData.class);
            list.addAll(data.getData().getList().getList());
            adapter.notifyDataSetChanged();
        }
    }

    public abstract String Teamtype();

    /*标识是自己还是他人团队*/
    public abstract String MyOrOther();


    @Override
    public HttpParams httpParams() {
        HttpParams httpParams = new HttpParams();
        if (OtherUid == null) httpParams.put("uid", MyApplication.getmInstance().getUid());
        else httpParams.put("uid", OtherUid);
        httpParams.put("type", Teamtype());
        httpParams.put("p", page);
        return httpParams;
    }

    @Override
    public void init() {
        if (MyOrOther().equals("1")) {//自己的
            adapter = new MyBaseRecyclerAdapter<TeamData.DataBean.ListBeanX.ListBean>(list, getActivity(), R.layout.team_item) {
                @Override
                public void convert(BaseRecyclerHolder holder, final TeamData.DataBean.ListBeanX.ListBean item, int postion) {
                    holder.setHeaderByUrl(R.id.head, item.getHeader());
                    holder.setText(R.id.name, item.getNickname());
                    holder.setText(R.id.phone, item.getPhone());
                    if (item.getVip() == 0) {
                        holder.setImageResource(R.id.vip, R.mipmap.novip);
                    } else {
                        holder.setImageResource(R.id.vip, R.mipmap.vip);
                    }
                    holder.setText(R.id.yongjin, "赚取佣金\n" + item.getCommission() + "元");
                    holder.getView(R.id.look).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent in = new Intent(getActivity(), OtherTeamActivity.class);
                            in.putExtra("uid", item.getUid());
                            jumpToActivity(in, false);
                        }
                    });

                    holder.getView(R.id.rel).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent in = new Intent(getActivity(), MyTeamYongJInActivity.class);
                            in.putExtra("m_uid", item.getUid());
                            jumpToActivity(in, false);
                        }
                    });
                }
            };
        } else {//他人团队
            adapter = new MyBaseRecyclerAdapter<TeamData.DataBean.ListBeanX.ListBean>(list, getActivity(), R.layout.other_team_item) {
                @Override
                public void convert(BaseRecyclerHolder holder, TeamData.DataBean.ListBeanX.ListBean item, int postion) {
                    holder.setHeaderByUrl(R.id.head, item.getHeader());
                    holder.setText(R.id.name, item.getNickname());
                    holder.setText(R.id.phone, item.getPhone());
                    if (item.getVip() == 0) {
                        holder.setImageResource(R.id.vip, R.mipmap.novip);
                    } else {
                        holder.setImageResource(R.id.vip, R.mipmap.vip);
                    }
                }
            };
        }
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setListener() {

    }

    @Override
    public int setlayoutResID() {
        return R.layout.base_shua_xin;
    }


}
