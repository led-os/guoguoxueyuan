package com.test720.grasshoppercollege.module.userData.xiaoXi;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseRecyclerViewFragment;
import com.test720.grasshoppercollege.HttpBean.Msgdata;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.WebViewActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 水东流 on 2018/4/19.
 */

public class XiaoXIFragment extends BaseRecyclerViewFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    List<Msgdata.DataBean> list = new ArrayList<>();
    MyBaseRecyclerAdapter adapter;

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
        return HttpUntil.GetIntent().msgList();
    }

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            Msgdata msgdata = new Gson().fromJson(str, Msgdata.class);
            list.clear();
            list.addAll(msgdata.getData());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            Msgdata msgdata = new Gson().fromJson(str, Msgdata.class);
            list.addAll(msgdata.getData());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public HttpParams httpParams() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("p", page);
        return httpParams;
    }

    @Override
    public void init() {
        adapter = new MyBaseRecyclerAdapter<Msgdata.DataBean>(list, getActivity(), R.layout.xiao_xi_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, Msgdata.DataBean item, int postion) {
                holder.setText(R.id.time, item.getRelease_time());
                holder.setImageByUrl(R.id.pic, item.getPic());
                holder.setText(R.id.body, item.getTitle());
            }
        };
        adapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                switch (list.get(position).getLocation_type()) {
                    case "1":
                    case "2":
                        Intent in = new Intent(getActivity(), WebViewActivity.class);
                        in.putExtra("title", list.get(position).getTitle());
                        in.putExtra("url", list.get(position).getLocation_link() + "/id/" + list.get(position).getMsg_id());
                        jumpToActivity(in, false);
                        break;
                    case "3":
                        try {
                            Intent intent = new Intent();
                            intent.setAction("android.intent.action.VIEW");
                            Uri content_url = Uri.parse(list.get(position).getLocation_link());
                            intent.setData(content_url);
                            startActivity(intent);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        break;
                }

            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setListener() {

    }

    @Override
    public int setlayoutResID() {
        haveLin = false;
        return R.layout.base_shua_xin;
    }


}
