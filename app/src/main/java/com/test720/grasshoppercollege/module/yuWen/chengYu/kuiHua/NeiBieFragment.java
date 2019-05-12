package com.test720.grasshoppercollege.module.yuWen.chengYu.kuiHua;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseRecyclerViewFragment;
import com.test720.grasshoppercollege.HttpBean.NeiBieData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 水东流 on 2018/7/5.
 */

public class NeiBieFragment extends BaseRecyclerViewFragment {
    @BindView(R.id.edittext)
    EditText edittext;
    @BindView(R.id.shou)
    ImageView shou;
    @BindView(R.id.shouRel)
    RelativeLayout shouRel;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    public MyBaseRecyclerAdapter adapter;
    private List<NeiBieData.DataBean> list = new ArrayList<>();

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
        return HttpUntil.GetIntent().ChineseIdiomcate();
    }

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            NeiBieData data = new Gson().fromJson(str, NeiBieData.class);
            list.clear();
            list.addAll(data.getData());
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public void gengDuo(String str) {

    }

    @Override
    public HttpParams httpParams() {
        HttpParams httpParams = new HttpParams();
        return httpParams;
    }

    @Override
    public void init() {
        shouRel.setVisibility(View.GONE);
        adapter = new MyBaseRecyclerAdapter<NeiBieData.DataBean>(list, getActivity(), R.layout.nian_ji_lie_biao_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, NeiBieData.DataBean item, int postion) {
                holder.setText(R.id.text, item.getName());
                TextView textView = holder.getView(R.id.text);
                textView.setTextColor(getResources().getColor(R.color.gwe));
                holder.setImageByUrl(R.id.img, item.getPic());
            }
        };

        //点击事件
        adapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), KuiHuaActivity.class);
                intent.putExtra("title",list.get(position).getName());
                intent.putExtra("cate_id", list.get(position).getCate_id() + "");
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
        return R.layout.kui_hua_nian_ji_fragment;
    }


    @OnClick(R.id.shou)
    public void onViewClicked() {
        MyApplication.getmInstance().setLoginDialogIsShow(false);
        if (!MyApplication.getmInstance().getUid().equals("")) {

        }
    }
}
