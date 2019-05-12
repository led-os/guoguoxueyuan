package com.test720.grasshoppercollege.module.yuWen.chengYu.kuiHua;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseRecyclerViewFragment;
import com.test720.grasshoppercollege.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 水东流 on 2018/7/5.
 */

public class NianJiFragment extends BaseRecyclerViewFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    public MyBaseRecyclerAdapter<String> adapter;
    private List<String> list = new ArrayList<>();

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
        return null;
    }

    @Override
    public void shuaXin(String str) {

    }

    @Override
    public void gengDuo(String str) {

    }

    @Override
    public HttpParams httpParams() {
        return null;
    }

    @Override
    public void init() {
        list.clear();
        list.add("一年级");
        list.add("二年级");
        list.add("三年级");
        list.add("四年级");
        list.add("五年级");
        list.add("六年级");

        adapter = new MyBaseRecyclerAdapter<String>(list, getActivity(), R.layout.nian_ji_lie_biao_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, String item, int postion) {
                holder.setText(R.id.text, item);
                switch (postion) {
                    case 0:
                        holder.setImageResource(R.id.img, R.mipmap.yinianji);

                        break;
                    case 1:
                        holder.setImageResource(R.id.img, R.mipmap.ernianji);
                        break;
                    case 2:
                        holder.setImageResource(R.id.img, R.mipmap.sannianji);
                        break;
                    case 3:
                        holder.setImageResource(R.id.img, R.mipmap.fournj);
                        break;
                    case 4:
                        holder.setImageResource(R.id.img, R.mipmap.fivenj);
                        break;
                    case 5:
                        holder.setImageResource(R.id.img, R.mipmap.sixnj);
                        break;
                }
            }
        };
        //点击事件
        adapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), KuiHuaActivity.class);
                intent.putExtra("title", list.get(position));
                intent.putExtra("class", list.get(position));
                jumpToActivity(intent, false);
            }
        });
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
