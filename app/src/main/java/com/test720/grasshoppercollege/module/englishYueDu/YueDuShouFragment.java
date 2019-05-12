package com.test720.grasshoppercollege.module.englishYueDu;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BuyClassActivity;
import com.test720.grasshoppercollege.HttpBean.EngLishReadShouData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.PubLicChuangGuanFragment;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.SubjectActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 水东流 on 2018/5/15.
 */

public class YueDuShouFragment extends PubLicChuangGuanFragment {
    List<EngLishReadShouData.DataBean> list = new ArrayList<>();


    private GridLayoutManager layoutManager;
    String[] strings;
    int count = -1;

    @Override
    public boolean isHORIZONTAL() {
        return false;
    }

    @Override
    public void initData() {
        back1.setVisibility(View.GONE);
        layoutManager = new GridLayoutManager(getActivity(), 4);
        //设置布局管理器
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        init();
    }

    @Override
    public void init() {
        if (adapter == null) {
            adapter = new MyBaseRecyclerAdapter<EngLishReadShouData.DataBean>(list, getActivity(), R.layout.yue_du_shou_item) {
                @Override
                public void convert(BaseRecyclerHolder holder, EngLishReadShouData.DataBean item, int postion) {
                    holder.setText(R.id.text, item.getName());
                    if (item.getOpen_up() == 0) {
                        holder.setImageResource(R.id.pic, R.mipmap.meirisuokuang);
                    } else {
                        holder.setImageResource(R.id.pic, R.mipmap.yousekuang);
                    }
                }
            };
            adapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    re_id = list.get(position).getRead_id();
                    points = list.get(position).getPoints();
                    strings = new String[3];
                    count = position;
                    strings[0] = list.get(position).getTitle();
                    strings[1] = list.get(position).getContent();
                    strings[2] = HttpUntil.GetIntent().getShare() + 23;
                    if (isCanSee(list.get(position).getPermissions(), list.get(position).isPay(), list.get(position).getOpen_up() + "", list.get(position).getPoints(), strings)) {
                        Intent intent = new Intent(mContext, SubjectActivity.class);
                        intent.putExtra("path", list.get(position).getWeb_url());
                        intent.putExtra("type", "英语阅读");
                        intent.putExtra("title", "阅读训练");
                        jumpToActivity(intent, false);
                    }
                }
            });
            recyclerView.setAdapter(adapter);
        }
        getData();
    }

    /*刷新数据*/
    public void getData() {
        HttpParams formBuilder = new HttpParams();
        formBuilder.put("uid", MyApplication.getmInstance().getUid());//请求参数一
        formBuilder.put("class", MyApplication.getmInstance().nianji);
        post(HttpUntil.GetIntent().EnReadIndex(), formBuilder, 1);
    }


    @Override
    public void shuaXin() {
        super.shuaXin();
        getData();
    }

    @Override
    public void buyCurr() {
        Intent intent = new Intent(getActivity(), BuyClassActivity.class);
        intent.putExtra("curr_type", 13);
        intent.putExtra("type", "4");
        jumpToActivity(intent, false);
    }

    @Override
    public void buyGuoGuo() {
        if (re_id == null) return;
        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        httpParams.put("curr_type", 13);
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
        httpParams.put("curr_type", 13);
        httpParams.put("type", 1);
        httpParams.put("re_id", re_id);
        post(HttpUntil.GetIntent().addShareSpendRecord(), httpParams, 123, false);
    }


    @Override
    public void setListener() {

    }

    @Override
    public void getSuccess(String s, int what) {
        if (what == 1 && codeIsOne(s, false)) {
            EngLishReadShouData engLishReadShouData = new Gson().fromJson(s, EngLishReadShouData.class);
            list.clear();
            if (engLishReadShouData.getData().size() > getIndex()) {
                list.addAll(engLishReadShouData.getData().get(getIndex()));
                adapter.notifyDataSetChanged();
                if (count == -1) count = list.size() - 1;
                recyclerView.scrollToPosition(count);
            }
        }
    }
}
