package com.test720.grasshoppercollege.module.gongLue.peiXun.xiangChe;

import android.graphics.drawable.Drawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseTwoRecyclerViewActivity;
import com.test720.grasshoppercollege.ImagePagerActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.gongLue.peiXun.bean.XiangCeXiangQingData;
import com.test720.grasshoppercollege.untils.HttpUntil;

import butterknife.BindView;
import okhttp3.FormBody;

public class XiangCeXiangQingActivity extends BaseTwoRecyclerViewActivity<XiangCeXiangQingData.DataBean.ListBean> {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    TextView dece, zanNum;

    @Override
    public ReflshInit reflshInit() {
        return new ReflshInit() {

            @Override
            public String reTitle() {
                if (getIntent().getStringExtra("title") != null)
                    return getIntent().getStringExtra("title");
                return "相册";
            }

            @Override
            public FormBody.Builder reFormBuilder() {
                FormBody.Builder builder = new FormBody.Builder();
                builder.add("p", page + "");
                builder.add("photo_id", getIntent().getStringExtra("photo_id"));
                builder.add("uid", MyApplication.getmInstance().getUid());
                return builder;
            }

            @Override
            public String reUrl() {
                return HttpUntil.GetIntent().StrategyTrainphotoList();
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
    protected void initData() {
        final View head = LayoutInflater.from(mcontext).inflate(R.layout.xiang_ce_xiang_qing_head, null);
        dece = head.findViewById(R.id.dece);
        zanNum = head.findViewById(R.id.zanNum);
        zanNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpParams httpParams = new HttpParams();
                httpParams.put("photo_id", getIntent().getStringExtra("photo_id"));
                httpParams.put("uid", MyApplication.getmInstance().getUid());
                postResponse(HttpUntil.GetIntent().StrategyTrainthumbUp(), httpParams, 5, false);
            }
        });

        setAdapter(new MyBaseRecyclerAdapter<XiangCeXiangQingData.DataBean.ListBean>(getList(), mcontext, R.layout.xiang_ce_xiang_qing_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, final XiangCeXiangQingData.DataBean.ListBean item, int postion) {
                holder.setText(R.id.time, item.getTime());
                RecyclerView recyclerView = holder.getView(R.id.reitem);
                recyclerView.setLayoutManager(new GridLayoutManager(mcontext, 4));
                MyBaseRecyclerAdapter adapter = new MyBaseRecyclerAdapter<String>(item.getPhoto(), mcontext, R.layout.image_item) {
                    @Override
                    public void convert(BaseRecyclerHolder holder, String childitem, final int childpostion) {
                        holder.setImageByUrl(R.id.ivImage, childitem);
                        holder.getView(R.id.ivImage).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ImagePagerActivity.startImagePagerActivity(mcontext, item.getPhoto(), childpostion, new ImagePagerActivity
                                        .ImageSize(v.getMeasuredWidth(), v.getMeasuredHeight()));
                            }
                        });
                    }
                };
                recyclerView.setAdapter(adapter);
            }
        });
//        getAdapter().setHeaderView(head);
        recyclerView.setAdapter(getAdapter());
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        super.GetHttpBackStr(str, what);
        switch (what) {
            case 5:
                if (codeIsOne(str)) {
                    ShuaXin();
                }
                break;
        }
    }

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            XiangCeXiangQingData data = new Gson().fromJson(str, XiangCeXiangQingData.class);
            getList().clear();
            getList().addAll(data.getData().getList());
            getAdapter().notifyDataSetChanged();
            if (data.getData().getInfo().getThumb_up().equals("0")) {
                Drawable left = getResources().getDrawable(R.mipmap.meizan);
                left.setBounds(0, 0, left.getIntrinsicWidth(), left.getIntrinsicHeight());
                zanNum.setCompoundDrawables(left, null, null, null);
            } else {
                Drawable left = getResources().getDrawable(R.mipmap.zan);
                left.setBounds(0, 0, left.getIntrinsicWidth(), left.getIntrinsicHeight());
                zanNum.setCompoundDrawables(left, null, null, null);
            }
            dece.setText(data.getData().getInfo().getName());
            zanNum.setText("  " + data.getData().getInfo().getThumb_up_count());
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            XiangCeXiangQingData data = new Gson().fromJson(str, XiangCeXiangQingData.class);
            getList().addAll(data.getData().getList());
            getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.base_shua_xin;
    }


}
