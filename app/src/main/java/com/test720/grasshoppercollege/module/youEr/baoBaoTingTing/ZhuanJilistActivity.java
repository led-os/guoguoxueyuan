package com.test720.grasshoppercollege.module.youEr.baoBaoTingTing;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseTwoRecyclerViewActivity;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.youEr.baoBaoTingTing.bean.BaoBaoZhuanJiListData;
import com.test720.grasshoppercollege.untils.HttpUntil;

import butterknife.BindView;
import okhttp3.FormBody;

public class ZhuanJilistActivity extends BaseTwoRecyclerViewActivity<BaoBaoZhuanJiListData.DataBean> {


    @Override
    public ReflshInit reflshInit() {
        return new ReflshInit() {
            @Override
            public String reTitle() {
                if (getIntent().getStringExtra("title") != null)
                    return getIntent().getStringExtra("title");
                return "宝宝听听";
            }

            @Override
            public FormBody.Builder reFormBuilder() {
                FormBody.Builder builder = new FormBody.Builder();
                if (getIntent().getStringExtra("cate_id") != null)
                    builder.add("cate_id", getIntent().getStringExtra("cate_id"));
                builder.add("status", getIntent().getStringExtra("status"));
                builder.add("p", page + "");
                return builder;
            }

            @Override
            public String reUrl() {
                return HttpUntil.GetIntent().ChildBabyListeninggetTuiJianAlbum();
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
    protected int setlayoutResID() {
        return R.layout.activity_zhuan_ji_list;
    }

    @Override
    protected void initData() {

        setAdapter(new MyBaseRecyclerAdapter<BaoBaoZhuanJiListData.DataBean>(getList(), mcontext, R.layout.zhuan_ji_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, BaoBaoZhuanJiListData.DataBean item, int postion) {
                holder.setText(R.id.title, item.getTitle());
                holder.setText(R.id.content, item.getSmall_title());
                holder.setText(R.id.pice, "￥" + item.getPrice());
                holder.setText(R.id.beiZhu, item.getCount() + "个故事");
                holder.setImageByUrl(R.id.img, item.getCover());
            }
        });
        getAdapter().setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(mcontext, ZhuanJiGuShiListActivity.class);
                intent.putExtra("album_id", getList().get(position).getAlbum_id());
                intent.putExtra("title", getList().get(position).getTitle());
                jumpToActivity(intent, false);
            }
        });
        recyclerView.setAdapter(getAdapter());
    }


    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            BaoBaoZhuanJiListData data = new Gson().fromJson(str, BaoBaoZhuanJiListData.class);
            getList().clear();
            getList().addAll(data.getData());
            getAdapter().notifyDataSetChanged();
        }

    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            BaoBaoZhuanJiListData data = new Gson().fromJson(str, BaoBaoZhuanJiListData.class);
            getList().addAll(data.getData());
            getAdapter().notifyDataSetChanged();

        }
    }


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.root)
    LinearLayout root;


}
