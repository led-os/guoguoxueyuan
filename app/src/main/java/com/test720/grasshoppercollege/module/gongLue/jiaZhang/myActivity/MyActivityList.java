package com.test720.grasshoppercollege.module.gongLue.jiaZhang.myActivity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseTwoRecyclerViewActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.gongLue.huoDong.ActivityXiangQing;
import com.test720.grasshoppercollege.module.gongLue.student.bean.MyActivityData;
import com.test720.grasshoppercollege.untils.HttpUntil;

import butterknife.BindView;
import okhttp3.FormBody;

public class MyActivityList extends BaseTwoRecyclerViewActivity<MyActivityData.DataBean> {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    @Override
    public ReflshInit reflshInit() {
        return new ReflshInit() {
            @Override
            public String reTitle() {
                return "我的活动";
            }

            @Override
            public FormBody.Builder reFormBuilder() {
                FormBody.Builder builder = new FormBody.Builder();
                builder.add("uid", MyApplication.getmInstance().getUid());
                builder.add("p", page + "");
                builder.add("type", "58");
                return builder;
            }

            @Override
            public String reUrl() {
                return HttpUntil.GetIntent().StrategymyActivity();
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
            MyActivityData myActivityData = new Gson().fromJson(str, MyActivityData.class);
            getList().clear();
            getList().addAll(myActivityData.getData());
            getAdapter().notifyDataSetChanged();

        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            MyActivityData myActivityData = new Gson().fromJson(str, MyActivityData.class);
            getList().addAll(myActivityData.getData());
            getAdapter().notifyDataSetChanged();

        }
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.base_shua_xin;
    }

    @Override
    protected void initData() {
        setAdapter(new MyBaseRecyclerAdapter<MyActivityData.DataBean>(getList(), mcontext, R.layout.huo_dong_list_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, MyActivityData.DataBean item, int postion) {
                holder.setText(R.id.title, item.getName());
                holder.setText(R.id.time, item.getStart_time() + item.getEnd_time());
                holder.setText(R.id.dece, item.getDesc());
                holder.setImageByUrl(R.id.img, item.getCover());
            }
        });
        getAdapter().setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(mcontext, ActivityXiangQing.class);
                intent.putExtra("activity_id", getList().get(position).getActivity_id());
                jumpToActivity(intent, false);
            }
        });
        recyclerView.setAdapter(getAdapter());
    }
}
