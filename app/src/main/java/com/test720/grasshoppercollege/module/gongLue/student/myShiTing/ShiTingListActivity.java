package com.test720.grasshoppercollege.module.gongLue.student.myShiTing;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RatingBar;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseTwoRecyclerViewActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.gongLue.GongLueData;
import com.test720.grasshoppercollege.module.gongLue.student.bean.MyShiTingListData;
import com.test720.grasshoppercollege.module.gongLue.student.shiTing.ShiTingXiangQingActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;

import butterknife.BindView;
import okhttp3.FormBody;

public class ShiTingListActivity extends BaseTwoRecyclerViewActivity<MyShiTingListData.DataBean> {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    @Override
    public ReflshInit reflshInit() {
        return new ReflshInit() {
            @Override
            public String reTitle() {
                return "我的试听课";
            }

            @Override
            public FormBody.Builder reFormBuilder() {
                FormBody.Builder builder = new FormBody.Builder();
                builder.add("type", GongLueData.getInstance().getModularType() + "");
                builder.add("uid", MyApplication.getmInstance().getUid());
                builder.add("p", page + "");
                return builder;
            }

            @Override
            public String reUrl() {
                return HttpUntil.GetIntent().StrategymyTryListening();
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
            MyShiTingListData data = new Gson().fromJson(str, MyShiTingListData.class);
            getList().clear();
            getList().addAll(data.getData());
            getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            MyShiTingListData data = new Gson().fromJson(str, MyShiTingListData.class);
            getList().addAll(data.getData());
            getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.base_shua_xin;
    }

    @Override
    protected void initData() {
        setAdapter(new MyBaseRecyclerAdapter<MyShiTingListData.DataBean>(getList(), mcontext, R.layout.small_ke_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, MyShiTingListData.DataBean item, int postion) {
                holder.setText(R.id.title, item.getName());
                holder.setText(R.id.time, item.getExpiry_time());
                holder.setText(R.id.number, item.getSubscribe_count() + "人订阅");
                holder.setText(R.id.content, "￥" + item.getPrice());
                RatingBar ratingBar = holder.getView(R.id.ratingBar);
                ratingBar.setVisibility(View.GONE);
                holder.setImageByUrl(R.id.book, item.getPic());
            }
        });
        getAdapter().setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(mcontext, ShiTingXiangQingActivity.class);
                intent.putExtra("micro_class_id", getList().get(position).getMicro_class_id());
                intent.putExtra("name", getList().get(position).getName());
                jumpToActivity(intent, false);
            }
        });
        recyclerView.setAdapter(getAdapter());
    }

}
