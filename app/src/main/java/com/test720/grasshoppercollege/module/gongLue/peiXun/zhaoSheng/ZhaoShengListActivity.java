package com.test720.grasshoppercollege.module.gongLue.peiXun.zhaoSheng;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseTwoRecyclerViewActivity;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.gongLue.peiXun.bean.KeChengListData;
import com.test720.grasshoppercollege.module.gongLue.GongLueData;
import com.test720.grasshoppercollege.untils.HttpUntil;

import butterknife.BindView;
import okhttp3.FormBody;

public class ZhaoShengListActivity extends BaseTwoRecyclerViewActivity<KeChengListData.DataBean> {

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

        setAdapter(new MyBaseRecyclerAdapter<KeChengListData.DataBean>(getList(), mcontext, R.layout.zhao_sheng_list_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, KeChengListData.DataBean item, int postion) {
                holder.setText(R.id.name, item.getName());
                holder.setText(R.id.shouChangNum, item.getCollection_count());
                holder.setText(R.id.pinglunNum, item.getComment_count());
                holder.setText(R.id.penpleNum, item.getSign_up_number());
                holder.setText(R.id.pice, "￥" + item.getPrice());
                holder.setText(R.id.centent, item.getDesc());
                holder.setImageByUrl(R.id.img, item.getCover());
            }
        });
        getAdapter().setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(mcontext, ZhaoShengXiangQingActivity.class);
                intent.putExtra("curr_id", getList().get(position).getCurr_id());
                intent.putExtra("name", getList().get(position).getName());
                jumpToActivity(intent, false);
            }
        });
        recyclerView.setAdapter(getAdapter());
    }

    @Override
    public ReflshInit reflshInit() {
        return new ReflshInit() {
            @Override
            public String reTitle() {
                if (getIntent().getStringExtra("title") != null)
                    return getIntent().getStringExtra("title");
                return "信息";
            }

            @Override
            public FormBody.Builder reFormBuilder() {
                FormBody.Builder builder = new FormBody.Builder();
                builder.add("school_id", GongLueData.getInstance().getSchoolId());
                builder.add("p", page + "");
                return builder;
            }

            @Override
            public String reUrl() {
                if (GongLueData.getInstance().getType() == GongLueData.SCHOOL) {
                    return HttpUntil.GetIntent().StrategyChildinterestList();
                } else {
                    return HttpUntil.GetIntent().StrategyTraincurrList();
                }
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
            KeChengListData data = new Gson().fromJson(str, KeChengListData.class);
            getList().clear();
            getList().addAll(data.getData());
            getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            KeChengListData data = new Gson().fromJson(str, KeChengListData.class);
            getList().addAll(data.getData());
            getAdapter().notifyDataSetChanged();
        }
    }


}
