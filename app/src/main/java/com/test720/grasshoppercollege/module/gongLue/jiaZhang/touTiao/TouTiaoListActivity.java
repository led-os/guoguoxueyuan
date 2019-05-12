package com.test720.grasshoppercollege.module.gongLue.jiaZhang.touTiao;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseTwoRecyclerViewActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.gongLue.GongLueData;
import com.test720.grasshoppercollege.module.gongLue.jiaZhang.bean.TouTiaoListData;
import com.test720.grasshoppercollege.untils.HttpUntil;

import butterknife.BindView;
import okhttp3.FormBody;

public class TouTiaoListActivity extends BaseTwoRecyclerViewActivity<TouTiaoListData.DataBean> {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    public String[] shareStr = new String[3];//分享数据保存

    @Override
    public ReflshInit reflshInit() {
        return new ReflshInit() {
            @Override
            public String reTitle() {
                return getString(R.string.edu_header);
            }

            @Override
            public FormBody.Builder reFormBuilder() {
                FormBody.Builder builder = new FormBody.Builder();
                builder.add("uid", MyApplication.getmInstance().getUid());
                builder.add("type", GongLueData.getInstance().getTouTiaoType() + "");
                builder.add("p", page + "");
                return builder;
            }

            @Override
            public String reUrl() {
                return HttpUntil.GetIntent().StrategyheadlineList();
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
            TouTiaoListData data = new Gson().fromJson(str, TouTiaoListData.class);
            getList().clear();
            getList().addAll(data.getData());
            getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            TouTiaoListData data = new Gson().fromJson(str, TouTiaoListData.class);
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
        setAdapter(new MyBaseRecyclerAdapter<TouTiaoListData.DataBean>(getList(), mcontext, R.layout.gong_lue_jiao_yu_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, TouTiaoListData.DataBean item, int postion) {
                holder.setImageByUrl(R.id.book, item.getCover());
                holder.setText(R.id.title, item.getTitle());
                holder.setText(R.id.number, item.getTime());
            }
        });

        getAdapter().setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent in = new Intent(mcontext, TouTiaoActivity.class);
                in.putExtra("headline_id", getList().get(position).getHeadline_id());
                in.putExtra("name", getList().get(position).getTitle());
                jumpToActivity(in, false);

            }
        });
        recyclerView.setAdapter(getAdapter());
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        super.GetHttpBackStr(str, what);
        if (what == 123 && codeIsOne(str)) {
            ShuaXin();
        }
    }
}
