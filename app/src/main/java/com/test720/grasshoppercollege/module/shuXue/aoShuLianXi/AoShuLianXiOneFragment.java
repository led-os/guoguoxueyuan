package com.test720.grasshoppercollege.module.shuXue.aoShuLianXi;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BuyClassActivity;
import com.test720.grasshoppercollege.HttpBean.AoShuShouYeGuanKaData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.PubLicChuangGuanFragment;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 水东流 on 2018/4/26.
 */

public class AoShuLianXiOneFragment extends PubLicChuangGuanFragment {
    public MyBaseRecyclerAdapter adapter;
    public List<AoShuShouYeGuanKaData.DataBean> list = new ArrayList<>();
    private GridLayoutManager layoutManager;

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
        back1.setVisibility(View.GONE);
        recyclerView.setPadding(20, 200, 20, 20);
        adapter = new MyBaseRecyclerAdapter<AoShuShouYeGuanKaData.DataBean>(list, getActivity(), R.layout.shu_xue_ao_shu_ying_yong_shou_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, AoShuShouYeGuanKaData.DataBean item, int postion) {

                if (item.getOpen_up() == 1) {//已开通关卡
                    holder.setImageResource(R.id.back, R.mipmap.jsbj);
                    holder.setText(R.id.text, item.getName());
                    holder.setText(R.id.notext,"");
                } else {//未开通关卡
                    holder.setImageResource(R.id.back, R.mipmap.hbj);
//                    holder.setImageResource(R.id.one, R.mipmap.wujxh_88);
                    holder.setText(R.id.notext, item.getName());
                }
            }
        };
        adapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                re_id = list.get(position).getMop_id();
                points = list.get(position).getPoints();
                String[] strings = new String[3];
                strings[0] = list.get(position).getTitle();
                strings[1] = list.get(position).getContent();
                strings[2] = HttpUntil.GetIntent().getShare() + (getType() + 10);
                if (isCanSee(list.get(position).getPermissions(), list.get(position).isPay(), list.get(position).getOpen_up() + "", list.get(position).getPoints(), strings)) {
                    Intent intent = new Intent(getActivity(), AoShuLianXiNeiRongActivity.class);
                    intent.putExtra("mop_id", list.get(position).getMop_id());
                    intent.putExtra("practice", "1");
                    jumpToActivity(intent, false);
                }
            }
        });
        recyclerView.setAdapter(adapter);
        getData();
    }

    @Override
    public void buyCurr() {
        Intent intent = new Intent(getActivity(), BuyClassActivity.class);
        intent.putExtra("curr_type", getType());
        intent.putExtra("type", "4");
        jumpToActivity(intent, false);
    }

    @Override
    public void shuaXin() {
        super.shuaXin();
        getData();
    }

    /**
     * 获取数据
     */
    public void getData() {
        HttpParams builder = new HttpParams();
        builder.put("uid", MyApplication.getmInstance().getUid());
        builder.put("class", MyApplication.getmInstance().nianji);
        builder.put("type", getType() + "");
        post(HttpUntil.GetIntent().MathOlyPracticeindex(), builder, 1, false);
    }

    @Override
    public void buyGuoGuo() {
        if (re_id == null) return;
        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        httpParams.put("curr_type", getType());
        httpParams.put("type", 2);
        httpParams.put("points", points);
        httpParams.put("re_id", re_id);
        post(HttpUntil.GetIntent().addShareSpendRecord(), httpParams, 123, false);
    }

    public int getType() {
        return 18;
    }

    @Override
    public void shareCurr() {
        if (re_id == null) return;
        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        httpParams.put("curr_type", getType());
        httpParams.put("type", 1);
        httpParams.put("re_id", re_id);
        post(HttpUntil.GetIntent().addShareSpendRecord(), httpParams, 123, false);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void getSuccess(String s, int what) {
        if (codeIsOne(s, false)) {
            AoShuShouYeGuanKaData aoShuShouYeGuanKaData = new Gson().fromJson(s, AoShuShouYeGuanKaData.class);
            list.clear();
            list.addAll(aoShuShouYeGuanKaData.getData().get(getIndex()));
            adapter.notifyDataSetChanged();
        }
    }


}
