package com.test720.grasshoppercollege.module.guShiCi.shou;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseRecyclerViewFragment;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.guShiCi.GuShiXiangQingActivity;
import com.test720.grasshoppercollege.module.guShiCi.SiListData;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 水东流 on 2018/7/11.
 */

public class ShouChangFragment extends BaseRecyclerViewFragment {
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
        return HttpUntil.GetIntent().ChineseAncientPoetrycollectionIndex();
    }

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            SiListData data = new Gson().fromJson(str, SiListData.class);
            list.clear();
            list.addAll(data.getData().getList());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            SiListData data = new Gson().fromJson(str, SiListData.class);
            list.addAll(data.getData().getList());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public HttpParams httpParams() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        httpParams.put("p", page);
        return httpParams;
    }

    @Override
    public void init() {
        head = LayoutInflater.from(getActivity()).inflate(R.layout.gu_shi_shou_chang_header, null);
        viewHolder = new ViewHolder(head);
        initViewHead();
        adapter = new MyBaseRecyclerAdapter<SiListData.DataBean.ListBean>(list, getActivity(), R.layout.gu_si_shou_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, SiListData.DataBean.ListBean item, int postion) {
                holder.setText(R.id.text, item.getName());
                holder.setImageByUrl(R.id.img, item.getCover());
              /*  if (item.getStatus().equals("0")) {
                    holder.setText(R.id.right, "未学");
                } else if (item.getStatus().equals("1")) {
                    holder.setText(R.id.right, "在学");
                } else if (item.getStatus().equals("2")) {
                    holder.setText(R.id.right, "已学");
                }*/
            }
        };

        adapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), GuShiXiangQingActivity.class);
                intent.putExtra("poetry_id", list.get(position).getPoetry_id());
                intent.putExtra("title", list.get(position).getName());
                jumpToActivity(intent, false);
            }
        });
        adapter.setHeaderView(head);
        recyclerView.setAdapter(adapter);
    }

    /*头部点击*/
    private void initViewHead() {
        viewHolder.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }

    @Override
    public void setListener() {
        haveHead = true;
    }

    @Override
    public int setlayoutResID() {
        return R.layout.base_shua_xin;
    }

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.root)
    LinearLayout root;

    List<SiListData.DataBean.ListBean> list = new ArrayList<>();
    MyBaseRecyclerAdapter adapter;
    View head;
    ViewHolder viewHolder;

    String status = "-1";

    static class ViewHolder {
        @BindView(R.id.back)
        ImageView back;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
