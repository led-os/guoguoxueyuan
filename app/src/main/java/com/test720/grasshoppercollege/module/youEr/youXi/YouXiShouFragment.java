package com.test720.grasshoppercollege.module.youEr.youXi;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseRecyclerViewFragment;
import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.GameActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.youEr.youXi.bean.YouXiListData;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.QuanXianUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 水东流 on 2018/8/2.
 */

public class YouXiShouFragment extends BaseRecyclerViewFragment {
    List<YouXiListData.DataBean.GameBean> list = new ArrayList<>();
    MyBaseRecyclerAdapter adapter;

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
        return HttpUntil.GetIntent().ChildGameindex();
    }


    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            YouXiListData data = new Gson().fromJson(str, YouXiListData.class);
            list.clear();
            list.addAll(data.getData().getGame());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            YouXiListData data = new Gson().fromJson(str, YouXiListData.class);
            list.addAll(data.getData().getGame());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public HttpParams httpParams() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("p", page);
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        httpParams.put("parent_id", getArguments().getString("parent_id"));
        return httpParams;
    }

    @Override
    public void init() {
        adapter = new MyBaseRecyclerAdapter<YouXiListData.DataBean.GameBean>(list, getActivity(), R.layout.si_wei_xu_lian_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, YouXiListData.DataBean.GameBean item, int postion) {
                holder.setImageByUrl(R.id.img, item.getPic());
                holder.setText(R.id.text, item.getName());
                if (item.getOpen_up() == 1) {
                    holder.getView(R.id.suo).setVisibility(View.GONE);
                } else {
                    holder.getView(R.id.suo).setVisibility(View.VISIBLE);
                }
            }
        };
        adapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final int position) {
                setItemIndexCount(position);
                String[] strings = new String[3];
                strings[0] = list.get(position).getTitle();
                strings[1] = list.get(position).getContent();
                strings[2] = HttpUntil.GetIntent().getShare() + 56;
                if (QuanXianUntil.GetIntent(getActivity()).isCanSee(list.get(position).getOpen_up() + "",
                        list.get(position).getRemaining_time(), strings,
                        new QuanXianUntil.QuanXianClick() {
                            @Override
                            public void QuanbuyCurr() {

                            }

                            @Override
                            public void QuanbuyGuoGuo() {

                            }

                            @Override
                            public void QuanshareCurr() {
                                HttpParams httpParams = new HttpParams();
                                httpParams.put("uid", MyApplication.getmInstance().getUid());
                                httpParams.put("game_id", list.get(position).getGame_id());
                                post(HttpUntil.GetIntent().ChildBabyshareUnlock(), httpParams, 123);
                            }
                        })) {
                    Intent in = new Intent(getActivity(), GameActivity.class);
                    in.putExtra("path", list.get(position).getPath());
                    in.putExtra("time", list.get(position).getRemaining_time());
                    in.putExtra("game_id", list.get(position).getGame_id());
                    jumpToActivity(in, true);
                }
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void ThreeSuccess(String s, int what) {
        super.ThreeSuccess(s, what);
        switch (what) {
            case 123:
                if (codeIsOne(s)) {
                    list.get(getItemIndexCount()).setOpen_up(1);
                    adapter.notifyDataSetChanged();
                }
                break;
        }
    }

    @Override
    public void onEvent(CurrencyEvent event) {
        super.onEvent(event);
        if (event.getWhat() == CurrencyEvent.UPDATEFRAGMENT) {
            ShuaXin();
        }
    }

    @Override
    public void setListener() {

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

}
