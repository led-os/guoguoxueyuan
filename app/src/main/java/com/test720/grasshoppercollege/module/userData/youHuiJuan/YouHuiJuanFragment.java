package com.test720.grasshoppercollege.module.userData.youHuiJuan;

import android.support.v4.widget.SwipeRefreshLayout;
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
 * 佛祖保佑        永无BUG
 * 佛曰:
 * 程序园里程序天，程序天里程序员；
 * 程序猿人写程序，又拿程序换肉钱。
 * 肉饱继续桌前坐，饱暖还是桌前眠；
 * 半迷半醒日复日，码上码下年复年。
 * 但愿叱咤互联世，不愿搬砖码当前；
 * 诸葛周瑜算世事，我算需求得加钱。
 * 别人笑我忒直男，我说自己是程猿；
 * 但见成都府国内，处处地地程序员。
 * 作者：水东流 编于 2018/10/7
 */
public class YouHuiJuanFragment extends BaseRecyclerViewFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    MyBaseRecyclerAdapter adapter;
    List<YouHuiJuanBean.DataBean> list = new ArrayList<>();

    @Override
    public int setCount() {
        return 1;
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
        return HttpUntil.GetIntent().UsermyCoupon();
    }

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            YouHuiJuanBean data = new Gson().fromJson(str, YouHuiJuanBean.class);
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
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        assert getArguments() != null;
        httpParams.put("status", getArguments().getString("status"));
        httpParams.put("coupon_type", getArguments().getString("coupon_type"));
        return httpParams;
    }

    @Override
    public void init() {
        adapter = new MyBaseRecyclerAdapter<YouHuiJuanBean.DataBean>(list, mContext, R.layout.you_hui_juan_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, YouHuiJuanBean.DataBean item, int postion) {
                if (getArguments().getString("status").equals("2")) {
                    holder.getView(R.id.over).setVisibility(View.VISIBLE);
                } else {
                    holder.getView(R.id.over).setVisibility(View.GONE);
                }
                if (getArguments().getString("status").equals("0")) {
                    holder.getView(R.id.topLin).setBackgroundResource(R.drawable.bule_back_kuang);
                } else {
                    holder.getView(R.id.topLin).setBackgroundResource(R.drawable.hui_back_kuang);
                }

                holder.setText(R.id.price, "￥" + item.getCoupon());
                holder.setText(R.id.time, item.getExpire_time());
                holder.setText(R.id.beizhu, item.getName());
                holder.setText(R.id.fullMoney, "￥" + item.getFull_money());
                if (item.getFull_money().equals("")) {
                    holder.getView(R.id.fullLin).setVisibility(View.GONE);
                }
            }
        };
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
