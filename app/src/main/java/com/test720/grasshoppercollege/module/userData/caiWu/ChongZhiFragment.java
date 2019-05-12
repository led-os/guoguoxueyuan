package com.test720.grasshoppercollege.module.userData.caiWu;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.HttpBean.ChongZhiData;
import com.test720.grasshoppercollege.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by流 on 2018/4/11.
 */

public class ChongZhiFragment extends BaseMingXiFragment {
    List<ChongZhiData.DataBean> list = new ArrayList<>();
    MyBaseRecyclerAdapter adapter;

    @Override
    public int type() {
        return 3;
    }

    @Override
    public void setListener() {
    }

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            list.clear();
            ChongZhiData data = new Gson().fromJson(str, ChongZhiData.class);
            list.addAll(data.getData());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            ChongZhiData data = new Gson().fromJson(str, ChongZhiData.class);
            list.addAll(data.getData());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void init() {
        adapter = new MyBaseRecyclerAdapter<ChongZhiData.DataBean>(list, getActivity(), R.layout.chong_zhi_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, ChongZhiData.DataBean item, int postion) {
                holder.setText(R.id.time, item.getCreate_time());
                holder.setText(R.id.money, "￥" + item.getMoney());
                if (item.getPay_type().equals("1")) {
                    holder.setText(R.id.type, "支付宝");
                } else if (item.getPay_type().equals("2")) {
                    holder.setText(R.id.type, "微信");
                } else {
                    holder.setText(R.id.type, "余额");
                }
            }
        };
        recyclerView.setAdapter(adapter);
    }
}