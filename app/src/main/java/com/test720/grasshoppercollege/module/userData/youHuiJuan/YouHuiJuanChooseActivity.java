package com.test720.grasshoppercollege.module.userData.youHuiJuan;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseTwoRecyclerViewActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.HttpUntil;

import butterknife.BindView;
import okhttp3.FormBody;

public class YouHuiJuanChooseActivity extends BaseTwoRecyclerViewActivity<YouHuiJuanBean.DataBean> {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    @Override
    protected int setlayoutResID() {
        return R.layout.base_shua_xin;
    }

    @Override
    protected void initData() {
        setAdapter(new MyBaseRecyclerAdapter<YouHuiJuanBean.DataBean>(getList(), mcontext, R.layout.you_hui_juan_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, YouHuiJuanBean.DataBean item, int postion) {
                holder.setText(R.id.price, item.getCoupon());
                holder.setText(R.id.fullMoney, item.getFull_money());
                holder.setText(R.id.time, item.getExpire_time());
                holder.setText(R.id.beizhu, item.getName());
                if (item.getFull_money().equals("")) {
                    holder.getView(R.id.fullLin).setVisibility(View.GONE);
                }
                holder.getView(R.id.over).setVisibility(View.GONE);
            }
        });
        getAdapter().setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (getIntent().getStringExtra("money") != null) {
                    String m = getIntent().getStringExtra("money");
                    String p = getList().get(position).getFull_money();
                    Float one = Float.parseFloat(m);
                    Float two = Float.parseFloat(p);
                    if (one < two) {
                        ShowToast("订单满" + p + "才可使用此优惠劵");
                        return;
                    }
                }
                Intent intent = new Intent();
                intent.putExtra("price", getList().get(position).getCoupon());
                intent.putExtra("id", getList().get(position).getUser_cid()); //将计算的值回传回去
                intent.putExtra("name", getList().get(position).getName() + getList().get(position).getCoupon() + "元");
                //通过intent对象返回结果，必须要调用一个setResult方法，
                setResult(2, intent);
                finish();
            }
        });
        recyclerView.setAdapter(getAdapter());
    }

    @Override
    public ReflshInit reflshInit() {
        return new ReflshInit() {
            @Override
            public String reTitle() {
                return "优惠劵";
            }

            @Override
            public FormBody.Builder reFormBuilder() {
                FormBody.Builder builder = new FormBody.Builder();
                builder.add("uid", MyApplication.getmInstance().getUid());
                builder.add("status", "0");
                //商品优惠劵需传id
                if (getIntent().getStringExtra("gids") != null)
                    builder.add("gid", getIntent().getStringExtra("gids"));
                builder.add("coupon_type", getIntent().getStringExtra("coupon_type"));
                return builder;
            }

            @Override
            public String reUrl() {
                return HttpUntil.GetIntent().UsermyCoupon();
            }

            @Override
            public int reCount() {
                return 1;
            }

            @Override
            public RecyclerView reRecyclerView() {
                return recyclerView;
            }

            @Override
            public SwipeRefreshLayout reSwipeRefreshLayout() {
                return swipeRefresh;
            }
        };
    }

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            YouHuiJuanBean data = new Gson().fromJson(str, YouHuiJuanBean.class);
            getList().clear();
            getList().addAll(data.getData());
            getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void gengDuo(String str) {

    }

}
