package com.test720.grasshoppercollege.module.youEr.kouSuanDaWang;

import android.content.Intent;
import android.view.View;

import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.HttpBean.AoShuShouYeGuanKaData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.shuXue.kouSuan.KouSuanShouFragment;
import com.test720.grasshoppercollege.untils.HttpUntil;

/**
 * Created by 水东流 on 2018/8/2.
 */

public class KouSuanDaWangShouFragment extends KouSuanShouFragment {

    @Override
    public void getData() {
        HttpParams builder = new HttpParams();
        builder.put("uid", MyApplication.getmInstance().getUid());
        builder.put("class", MyApplication.getmInstance().nianji);
        builder.put("type", getType() + "");
        post(HttpUntil.GetIntent().ChildMentalArithmeticindex(), builder, 1, false);
    }


    @Override
    public void init() {
        back1.setVisibility(View.GONE);
        recyclerView.setPadding(20, 200, 20, 20);
        adapter = new MyBaseRecyclerAdapter<AoShuShouYeGuanKaData.DataBean>(list, getActivity(), R.layout.xin_xin_guan_ka_grild_view_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, AoShuShouYeGuanKaData.DataBean item, int postion) {

                if (item.getOpen_up() == 1) {//已开通关卡
                    holder.setImageResource(R.id.back, R.mipmap.jsbj);
                    holder.setText(R.id.text, item.getName());
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
                strings[2] = HttpUntil.GetIntent().getShare() + "27";
                if (isCanSee(list.get(position).getPermissions(), list.get(position).isPay(), list.get(position).getOpen_up() + "", list.get(position).getPoints(), strings)) {
                    Intent intent = new Intent(getActivity(), KouSuanDaWangTiMuActivity.class);
                    intent.putExtra("mop_id", list.get(position).getMop_id());
                    intent.putExtra("practice", "1");
                    intent.putExtra("type", getType());
                    intent.putExtra("title", list.get(position).getName());
                    jumpToActivity(intent, false);
                }
            }
        });
        recyclerView.setAdapter(adapter);
        getData();
    }
}