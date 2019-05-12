package com.test720.grasshoppercollege.module.userData.caiWu;

import android.widget.TextView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.HttpBean.DaKuanData;
import com.test720.grasshoppercollege.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 水东流 on 2018/4/11.
 */

public class ShiBaiFragment extends BaseMingXiFragment {
    List<DaKuanData.DataBean> list = new ArrayList<>();
    MyBaseRecyclerAdapter adapter;

    @Override
    public int type() {
        return 2;
    }

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            list.clear();
            DaKuanData data = new Gson().fromJson(str, DaKuanData.class);
            list.addAll(data.getData());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            DaKuanData data = new Gson().fromJson(str, DaKuanData.class);
            list.addAll(data.getData());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void init() {
        adapter = new MyBaseRecyclerAdapter<DaKuanData.DataBean>(list, getActivity(), R.layout.da_kuan_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, DaKuanData.DataBean item, int postion) {
                holder.setText(R.id.time, item.getCreate_time());
                holder.setText(R.id.money, "提现￥" + item.getMoney());
                holder.setText(R.id.shouxu, "手续费￥" + item.getCharge_money());
                TextView textView = holder.getView(R.id.staue);
                textView.setTextColor(getResources().getColor(R.color.light_gray));
                textView.setText("失败");
            }
        };
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setListener() {

    }
}
