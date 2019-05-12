package com.test720.grasshoppercollege.module.shangCheng;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseTwoRecyclerViewActivity;
import com.test720.grasshoppercollege.ImagePagerActivity;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.shangCheng.bean.ShangPinXiangQingData;
import com.test720.grasshoppercollege.untils.HttpUntil;

import butterknife.BindView;
import okhttp3.FormBody;

public class ShangPinPinLunActivity extends BaseTwoRecyclerViewActivity<ShangPinXiangQingData.DataBean.GoodsBean.CommentBean> {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.root)
    LinearLayout root;


    private String gid;

    @Override
    protected int setlayoutResID() {
        return R.layout.base_shua_xin;
    }

    @Override
    protected void initData() {
        root.setBackgroundResource(R.color.white);
        setAdapter(new MyBaseRecyclerAdapter<ShangPinXiangQingData.DataBean.GoodsBean.CommentBean>(getList(), mcontext, R.layout.shang_pin_pinlun) {
            @Override
            public void convert(BaseRecyclerHolder holder, final ShangPinXiangQingData.DataBean.GoodsBean.CommentBean item, int postion) {
                holder.setHeaderByUrl(R.id.iv_icon, item.getHeader());
                holder.setText(R.id.name, item.getNickname());
                holder.setText(R.id.time, item.getTime());
                holder.setText(R.id.body, item.getContent());
                RatingBar ratingBar = holder.getView(R.id.ratingBar);
                ratingBar.setRating(Float.parseFloat(item.getLevel()));

                // 照片
                holder.getView(R.id.one).setVisibility(View.GONE);
                holder.getView(R.id.two).setVisibility(View.GONE);
                holder.getView(R.id.three).setVisibility(View.GONE);
                for (int i = 0; i < item.getPic().size(); i++) {
                    switch (i) {
                        case 0:
                            holder.getView(R.id.one).setVisibility(View.VISIBLE);
                            holder.setImageByUrl(R.id.one, item.getPic().get(0));
                            holder.getView(R.id.one).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ImagePagerActivity.startImagePagerActivity(mcontext, item.getPic(), 0, new ImagePagerActivity
                                            .ImageSize(v.getMeasuredWidth(), v.getMeasuredHeight()));
                                }
                            });
                            break;
                        case 1:
                            holder.getView(R.id.two).setVisibility(View.VISIBLE);
                            holder.setImageByUrl(R.id.two, item.getPic().get(1));
                            holder.getView(R.id.two).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ImagePagerActivity.startImagePagerActivity(mcontext, item.getPic(), 1, new ImagePagerActivity
                                            .ImageSize(v.getMeasuredWidth(), v.getMeasuredHeight()));
                                }
                            });
                            break;
                        case 2:
                            holder.getView(R.id.three).setVisibility(View.VISIBLE);
                            holder.setImageByUrl(R.id.three, item.getPic().get(2));
                            holder.getView(R.id.three).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ImagePagerActivity.startImagePagerActivity(mcontext, item.getPic(), 2, new ImagePagerActivity
                                            .ImageSize(v.getMeasuredWidth(), v.getMeasuredHeight()));
                                }
                            });
                            break;
                    }
                }

            }

        });
        recyclerView.setAdapter(getAdapter());
    }


    @Override
    public ReflshInit reflshInit() {
        return new ReflshInit() {
            @Override
            public String reTitle() {
                return "评论";
            }

            @Override
            public FormBody.Builder reFormBuilder() {
                gid = getIntent().getStringExtra("gid");
                FormBody.Builder builder = new FormBody.Builder();
                builder.add("gid", gid);
                builder.add("p", page + "");
                return builder;
            }

            @Override
            public String reUrl() {
                return HttpUntil.GetIntent().MallgoodsInfo();
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
            ShangPinXiangQingData data = new Gson().fromJson(str, ShangPinXiangQingData.class);
            getList().clear();
            getList().addAll(data.getData().getGoods().getComment());
            getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            ShangPinXiangQingData data = new Gson().fromJson(str, ShangPinXiangQingData.class);
            getList().addAll(data.getData().getGoods().getComment());
            getAdapter().notifyDataSetChanged();
        }
    }


}
