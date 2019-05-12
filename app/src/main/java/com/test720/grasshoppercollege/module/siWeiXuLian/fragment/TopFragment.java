package com.test720.grasshoppercollege.module.siWeiXuLian.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BuyClassActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.PubLicChuangGuanFragment;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.siWeiXuLian.VideoActivity;
import com.test720.grasshoppercollege.module.siWeiXuLian.bean.SiWeiShouData;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 水东流 on 2018/7/31.
 */

public class TopFragment extends PubLicChuangGuanFragment {
    MyBaseRecyclerAdapter adapter;
    List<SiWeiShouData.DataBean.ThinkingBean> list = new ArrayList<>();

    @Override
    public void initData() {
        back1.setVisibility(View.GONE);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        //设置布局管理器
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
    }

    public void getData() {

    }

    @Override
    public void onStart() {
        super.onStart();
        adapter = new MyBaseRecyclerAdapter<SiWeiShouData.DataBean.ThinkingBean>(list, getActivity(), R.layout.si_wei_xun_lian_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, SiWeiShouData.DataBean.ThinkingBean item, int postion) {
                holder.setText(R.id.name, item.getName());
                holder.setImageByUrl(R.id.pic, item.getPic());
                if (item.getOpen_up() == 1) {
                    holder.getView(R.id.suo).setVisibility(View.GONE);
                } else {
                    holder.getView(R.id.suo).setVisibility(View.VISIBLE);
                }
            }
        };
        adapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                setIndex(position);
                re_id = list.get(position).getChild_id();
                points = list.get(position).getPoints();
                String[] strings = new String[3];
                strings[0] = list.get(position).getTitle();
                strings[1] = list.get(position).getContent();
                strings[2] = HttpUntil.GetIntent().getShare() + "50";
                if (isCanSee(list.get(position).getPermissions(), list.get(position).isPay(), list.get(position).getOpen_up() + "", list.get(position).getPoints(), strings)) {
                    Intent intent = new Intent(getActivity(), VideoActivity.class);
                    intent.putExtra("child_id", list.get(position).getChild_id());
                    intent.putExtra("title", list.get(position).getName());
                    jumpToActivity(intent, false);
                }
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setListener() {
    }

    @Override
    public boolean isHORIZONTAL() {
        return false;
    }

    @Override
    public void init() {

    }

    @Override
    public void buyCurr() {
        Intent intent = new Intent(getActivity(), BuyClassActivity.class);
        intent.putExtra("curr_type", 40);
        intent.putExtra("type", "4");
        jumpToActivity(intent, false);
    }

    @Override
    public void buyGuoGuo() {
        if (re_id == null) return;
        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        httpParams.put("curr_type", 40);
        httpParams.put("type", 2);
        httpParams.put("points", points);
        httpParams.put("re_id", re_id);
        post(HttpUntil.GetIntent().addShareSpendRecord(), httpParams, 123, false);
    }

    @Override
    public void shareCurr() {
        if (re_id == null) return;
        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        httpParams.put("curr_type", 40);
        httpParams.put("type", 1);
        httpParams.put("re_id", re_id);
        post(HttpUntil.GetIntent().addShareSpendRecord(), httpParams, 123, false);
    }

    @Override
    public void getSuccess(String s, int what) {
        if (what == 123 && codeIsOne(s) && getIndex() != -1) {
            list.get(getIndex()).setOpen_up(1);
            adapter.notifyDataSetChanged();
        }
    }

    public List<SiWeiShouData.DataBean.ThinkingBean> getList() {
        return list;
    }

    public void setList(List<SiWeiShouData.DataBean.ThinkingBean> list) {
        this.list = list;
    }
}
