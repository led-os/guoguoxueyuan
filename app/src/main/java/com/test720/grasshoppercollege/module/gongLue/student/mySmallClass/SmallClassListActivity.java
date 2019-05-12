package com.test720.grasshoppercollege.module.gongLue.student.mySmallClass;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseTwoRecyclerViewActivity;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.gongLue.student.bean.SmallDaGangListData;
import com.test720.grasshoppercollege.module.gongLue.student.smallClass.daGang.DaGangVideoActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;

import butterknife.BindView;
import okhttp3.FormBody;

public class SmallClassListActivity extends BaseTwoRecyclerViewActivity<SmallDaGangListData.DataBean> {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;


    @Override
    public ReflshInit reflshInit() {
        return new ReflshInit() {
            @Override
            public String reTitle() {
                return getIntent().getStringExtra("title");
            }

            @Override
            public FormBody.Builder reFormBuilder() {
                FormBody.Builder httpParams = new FormBody.Builder();
                httpParams.add("micro_id", getIntent().getStringExtra("micro_id"));
                httpParams.add("p", page + "");
                return httpParams;
            }

            @Override
            public String reUrl() {
                return HttpUntil.GetIntent().StrategymyMicroInfo();
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
            SmallDaGangListData daGangListData = new Gson().fromJson(str, SmallDaGangListData.class);
            getList().clear();
            getList().addAll(daGangListData.getData());
            getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            SmallDaGangListData daGangListData = new Gson().fromJson(str, SmallDaGangListData.class);
            getList().addAll(daGangListData.getData());
            getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.base_shua_xin;
    }

    @Override
    protected void initData() {
        setAdapter(new MyBaseRecyclerAdapter<SmallDaGangListData.DataBean>(getList(), mcontext, R.layout.ke_cheng_da_gang_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, SmallDaGangListData.DataBean item, int postion) {
                holder.setText(R.id.title, item.getName());
                holder.setText(R.id.content, item.getDesc());
                holder.setImageByUrl(R.id.pic, item.getPic());
                holder.getView(R.id.suo).setVisibility(View.GONE);
            }
        });
        getAdapter().setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(mcontext, DaGangVideoActivity.class);
                intent.putExtra("micro_class_id", getList().get(position).getMicro_class_id());
                intent.putExtra("name", getList().get(position).getName());
                if (getList().get(position).getState().equals("1")) {
                    intent.putExtra("h5", false);
                } else {
                    intent.putExtra("h5", true);
                }
                jumpToActivity(intent, false);
            }
        });
        recyclerView.setAdapter(getAdapter());
    }


}
