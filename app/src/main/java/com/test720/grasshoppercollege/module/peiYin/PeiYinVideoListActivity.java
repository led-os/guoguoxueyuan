package com.test720.grasshoppercollege.module.peiYin;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseTwoRecyclerViewActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.peiYin.bean.ZhuaJiData;
import com.test720.grasshoppercollege.untils.HttpUntil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.FormBody;

public class PeiYinVideoListActivity extends BaseTwoRecyclerViewActivity<ZhuaJiData.DataBean.ListBean> {
    View head;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.rightImg)
    ImageView rightImg;
    @BindView(R.id.right)
    RelativeLayout right;
    @BindView(R.id.topRel)
    RelativeLayout topRel;
    @BindView(R.id.root)
    LinearLayout root;

    ViewHolder viewHolder;

    @Override
    public ReflshInit reflshInit() {
        return new ReflshInit() {
            @Override
            public String reTitle() {
                return null;
            }

            @Override
            public FormBody.Builder reFormBuilder() {
                FormBody.Builder builder = new FormBody.Builder();
                builder.add("p", page + "");
                builder.add("album_id", getIntent().getStringExtra("album_id"));
                return builder;
            }

            @Override
            public String reUrl() {
                return HttpUntil.GetIntent().DubbingcontentList();
            }

            @Override
            public int reCount() {
                return 2;
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
        haveHeader = true;
        return R.layout.pei_yin_video_list;
    }

    @Override
    protected void initData() {
        rightImg.setImageResource(R.mipmap.huix);
        if (getIntent().getStringExtra("title") != null) {
            title.setText(getIntent().getStringExtra("title"));
        } else {
            title.setText("专辑");
        }
        head = LayoutInflater.from(mcontext).inflate(R.layout.pei_yin_list_head, null);
        viewHolder = new ViewHolder(head);
        setAdapter(new MyBaseRecyclerAdapter<ZhuaJiData.DataBean.ListBean>(getList(), this, R.layout.pei_yin_shi_pin_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, ZhuaJiData.DataBean.ListBean item, int postion) {
                holder.setImageByUrl(R.id.iv_icon, item.getPic());
                holder.setText(R.id.tv_title, item.getVideo_name());
            }
        });
        getAdapter().setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(mcontext, PeiYinXiangQingActivity.class);
                intent.putExtra("con_id", getList().get(position).getCon_id());
                jumpToActivity(intent, false);
            }
        });
        getAdapter().setHeaderView(head);
        recyclerView.setAdapter(getAdapter());
    }

    /**
     * 收藏
     */
    public void collection() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("uid", MyApplication.getmInstance().getUid());
        builder.add("con_id", getIntent().getStringExtra("album_id"));
        Post(HttpUntil.GetIntent().Dubbingcollection(), builder, 5);
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        super.GetHttpBackStr(str, what);
        switch (what) {
            case 5:
                codeIsOne(str);

                break;
        }
    }

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            ZhuaJiData zhuaJiData = new Gson().fromJson(str, ZhuaJiData.class);
            getList().clear();
            getList().addAll(zhuaJiData.getData().getList());
            getAdapter().notifyDataSetChanged();

            Glide.with(mcontext).load(zhuaJiData.getData().getInfo().getPic()).into(viewHolder.header);
            viewHolder.level.setRating(Float.parseFloat(zhuaJiData.getData().getInfo().getLevel()));
            viewHolder.name.setText(zhuaJiData.getData().getInfo().getName());
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            ZhuaJiData zhuaJiData = new Gson().fromJson(str, ZhuaJiData.class);
            getList().addAll(zhuaJiData.getData().getList());
            getAdapter().notifyDataSetChanged();
        }
    }


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;


    @OnClick(R.id.right)
    public void onViewClicked() {
        collection();
    }

    static class ViewHolder {
        @BindView(R.id.header)
        ImageView header;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.level)
        RatingBar level;
        @BindView(R.id.top_rel)
        RelativeLayout topRel;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
