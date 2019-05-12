package com.test720.grasshoppercollege.module.userData.myDingDan;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseRecyclerViewFragment;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * _oo0oo_
 * o8888888o
 * 88" . "88
 * (| -_- |)
 * 0\  =  /0
 * ___/`---'\___
 * .' \\|     |// '.
 * / \\|||  :  |||// \
 * / _||||| -卍-|||||- \
 * |   | \\\  -  /// |   |
 * | \_|  ''\---/''  |_/ |
 * \  .-\__  '-'  ___/-. /
 * ___'. .'  /--.--\  `. .'___
 * ."" '<  `.___\_<|>_/___.' >' "".
 * | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 * \  \ `_.   \_ __\ /__ _/   .-` /  /
 * =====`-.____`.___ \_____/___.-`___.-'=====
 * `=---='
 * 佛祖保佑        永无BUG
 * 佛曰:
 * 程序园里程序天，程序天里程序员；
 * 程序猿人写程序，又拿程序换肉钱。
 * 肉饱继续桌前坐，饱暖还是桌前眠；
 * 半迷半醒日复日，码上码下年复年。
 * 但愿叱咤互联世，不愿搬砖码当前；
 * 诸葛周瑜算世事，我来算出得加钱。
 * 别人笑我忒直男，我笑自己太像猿；
 * 但见成都府国内，处处地地程序员。
 * Created by 水东流 on 2018/3/20 0020.
 */

public abstract class BaseDingDanFragment extends BaseRecyclerViewFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    List<DingDanData.DataBean> list = new ArrayList<>();
    private MyBaseRecyclerAdapter adapter;
    DingDanData dingDanData;

    @Override
    public int setCount() {
        return 1;
    }

    @Override
    public String url() {
        return HttpUntil.GetIntent().OrderorderStatus();
    }

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            dingDanData = new Gson().fromJson(str, DingDanData.class);
            list.clear();
            list.addAll(dingDanData.getData());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void gengDuo(String str) {
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
    public void init() {
        adapter = new MyBaseRecyclerAdapter<DingDanData.DataBean>(list, getActivity(), R.layout.ding_dan_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, final DingDanData.DataBean fatheritem, final int pos) {
                RecyclerView recyclerView = holder.getView(R.id.recyclerView);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(linearLayoutManager);
                holder.setText(R.id.zong, "共" + fatheritem.getTotal_count() + "件商品 合计：￥" + fatheritem.getOrder_price());

                holder.getView(R.id.one).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        oneClick(fatheritem, pos);
                    }
                });
                holder.getView(R.id.two).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        twoClick(fatheritem, pos);
                    }
                });

                holder.getView(R.id.three).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        threeClick(fatheritem, pos);
                    }
                });
                holder.getView(R.id.three).setVisibility(View.GONE);
                switch (fatheritem.getOrder_status()) {//0-待付款；1-待发货；2-待收货-；4-已完成-待评价;5-已取消6,退货，售后
                    case "0":
                        holder.setText(R.id.one, "取消订单");
                        holder.setText(R.id.two, "立即付款");
                        holder.setText(R.id.type, "待付款");
                        break;
                    case "1":
                        holder.setText(R.id.one, "提醒发货");
                        holder.getView(R.id.two).setVisibility(View.GONE);
                        holder.setText(R.id.type, "待发货");
                        break;
                    case "2":
                        holder.getView(R.id.three).setVisibility(View.VISIBLE);
                        holder.setText(R.id.one, "查看物流");
                        holder.setText(R.id.two, "确认收货");
                        holder.setText(R.id.three, "退货");
                        holder.setText(R.id.type, "待收货");
                        break;
                    case "3":
                        holder.getView(R.id.one).setVisibility(View.GONE);
                        holder.setText(R.id.two, "评价");
                        holder.setText(R.id.type, "待评价");
                        break;
                    case "4":
                        holder.getView(R.id.one).setVisibility(View.GONE);
                        holder.getView(R.id.two).setVisibility(View.GONE);
                        holder.setText(R.id.type, "已完成");
                        break;
                    case "5":
                        holder.getView(R.id.one).setVisibility(View.GONE);
                        holder.getView(R.id.two).setVisibility(View.GONE);
                        holder.setText(R.id.type, "已取消");
                        break;
                    case "6":
                        holder.getView(R.id.one).setVisibility(View.GONE);
                        holder.getView(R.id.two).setVisibility(View.GONE);
                        holder.setText(R.id.type, "待退货");
                        break;
                    case "7":
                        holder.getView(R.id.one).setVisibility(View.GONE);
                        holder.setText(R.id.two, "已完成");
                        holder.setText(R.id.type, "退货完成");
                        break;
                }

                MyBaseRecyclerAdapter adapter = new MyBaseRecyclerAdapter<DingDanData.DataBean.GoodsBean>(fatheritem.getGoods(), getActivity(), R.layout.ding_dan_children_item) {
                    @Override
                    public void convert(BaseRecyclerHolder holder, final DingDanData.DataBean.GoodsBean item, final int postion) {
                        holder.setText(R.id.name, item.getName());
                        holder.setText(R.id.value, item.getValue());
                        holder.setText(R.id.price, "￥" + item.getPrice());
                        holder.setText(R.id.num, "数量：" + item.getCount());
                        holder.setImageByUrl(R.id.img, item.getCover());

                    }
                };
                recyclerView.setAdapter(adapter);
                adapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent intent = new Intent(getActivity(), DingDanXiangQingActivity.class);
                        intent.putExtra("order_num", fatheritem.getOrder_num());
                        jumpToActivity(intent, false);
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
    }

    @Override
    public HttpParams httpParams() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        httpParams.put("status", status());
        return httpParams;
    }

    public abstract String status();

    public abstract void oneClick(DingDanData.DataBean dataBean, int pos);

    public abstract void twoClick(DingDanData.DataBean dataBean, int pos);

    public abstract void threeClick(DingDanData.DataBean dataBean, int pos);

    @Override
    public int setlayoutResID() {
        return R.layout.base_ding_dan;
    }


}
