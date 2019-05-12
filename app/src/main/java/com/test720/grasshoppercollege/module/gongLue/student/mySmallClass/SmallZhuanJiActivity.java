package com.test720.grasshoppercollege.module.gongLue.student.mySmallClass;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.RatingBar;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseTwoRecyclerViewActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.gongLue.GongLueData;
import com.test720.grasshoppercollege.module.gongLue.student.bean.MySmallClassZhuanJiListData;
import com.test720.grasshoppercollege.untils.HttpUntil;

import butterknife.BindView;
import okhttp3.FormBody;

public class SmallZhuanJiActivity extends BaseTwoRecyclerViewActivity<MySmallClassZhuanJiListData.DataBean> {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    @Override
    public ReflshInit reflshInit() {
        return new ReflshInit() {
            @Override
            public String reTitle() {
                return "我的微课";
            }

            @Override
            public FormBody.Builder reFormBuilder() {
                FormBody.Builder builder = new FormBody.Builder();
                builder.add("uid", MyApplication.getmInstance().getUid());
                builder.add("type", GongLueData.getInstance().getModularType() + "");
                builder.add("p", page + "");
                return builder;
            }

            @Override
            public String reUrl() {
                return HttpUntil.GetIntent().StrategymyMicro();
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
            MySmallClassZhuanJiListData data = new Gson().fromJson(str, MySmallClassZhuanJiListData.class);
            getList().clear();
            getList().addAll(data.getData());
            getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            MySmallClassZhuanJiListData data = new Gson().fromJson(str, MySmallClassZhuanJiListData.class);
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
        setAdapter(new MyBaseRecyclerAdapter<MySmallClassZhuanJiListData.DataBean>(getList(), mcontext, R.layout.gong_lue_book_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, MySmallClassZhuanJiListData.DataBean item, int postion) {
                holder.setText(R.id.title, item.getName());
                holder.setText(R.id.content, item.getAims());
                holder.setText(R.id.number, "到期：" + item.getExpiry_time());
                RatingBar ratingBar = holder.getView(R.id.ratingBar);
                ratingBar.setRating(Float.parseFloat(item.getLevel()));
                holder.setImageByUrl(R.id.book, item.getCover());
            }
        });
        getAdapter().setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(mcontext, SmallClassListActivity.class);
                intent.putExtra("micro_id", getList().get(position).getMicro_id());
                intent.putExtra("title", getList().get(position).getName());
                jumpToActivity(intent, false);
            }
        });
        recyclerView.setAdapter(getAdapter());
    }


}
