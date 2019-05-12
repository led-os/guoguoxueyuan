package com.test720.grasshoppercollege.module.guShiCi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseRecyclerViewActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.myViews.MuseDialogFragment;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.FormBody;

public class GuSiListActivity extends BaseRecyclerViewActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.muse)
    TextView muse;
    @BindView(R.id.edittext)
    EditText edittext;
    @BindView(R.id.xiaoxi)
    ImageView xiaoxi;
    @BindView(R.id.back)
    ImageView back;


    List<SiListData.DataBean.ListBean> list = new ArrayList<>();
    MyBaseRecyclerAdapter adapter;

    String status = "-1";

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_seach_gu_si;
    }

    @SuppressLint("InflateParams")
    @Override
    protected void initData() {
        initViewHead();
        adapter = new MyBaseRecyclerAdapter<SiListData.DataBean.ListBean>(list, this, R.layout.gu_si_shou_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, SiListData.DataBean.ListBean item, int postion) {
                holder.setText(R.id.text, item.getName());
                holder.setImageByUrl(R.id.img, item.getCover());
            }
        };

        adapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(GuSiListActivity.this, GuShiXiangQingActivity.class);
                intent.putExtra("title", list.get(position).getName());
                intent.putExtra("poetry_id", list.get(position).getPoetry_id());
                jumpToActivity(intent, false);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    /*头部点击*/
    private void initViewHead() {
        muse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MuseDialogFragment museDialogFragment = new MuseDialogFragment();
                museDialogFragment.setItemClick(new MuseDialogFragment.ItemClick() {
                    @Override
                    public void yixue() {
                        status = "2";
                        ShuaXin();
                        muse.setText("已学");
                    }

                    @Override
                    public void weixue() {
                        status = "0";
                        ShuaXin();
                        muse.setText("未学");
                    }

                    @Override
                    public void zhengxue() {
                        status = "1";
                        ShuaXin();
                        muse.setText("正学");
                    }

                    @Override
                    public void quanBu() {
                        status = "";
                        ShuaXin();
                        muse.setText("全部");
                    }
                });
                museDialogFragment.show(getSupportFragmentManager(), "muse");
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        xiaoxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShuaXin();
            }
        });
    }

    @Override
    public void init() {
        super.init();
        if (TextUtils.isEmpty(edittext.getText()) && getIntent().getStringExtra("key") != null) {
            edittext.setText(getIntent().getStringExtra("key"));
        }
    }

    @Override
    public int setCount() {
        return 2;
    }

    @Override
    public RecyclerView initRecyclerView() {
        return recyclerView;
    }

    @Override
    public SwipeRefreshLayout initSwipeRefreshLayout() {
        return swipeRefresh;
    }

    @Override
    public String url() {
        return HttpUntil.GetIntent().ChineseAncientPoetrycontentIndex();
    }

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            SiListData data = new Gson().fromJson(str, SiListData.class);
            list.clear();
            list.addAll(data.getData().getList());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            SiListData data = new Gson().fromJson(str, SiListData.class);
            list.addAll(data.getData().getList());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public FormBody.Builder formBuilder() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("p", page + "");
        if (getIntent().getStringExtra("cate_id") != null) {
            builder.add("cate_id", getIntent().getStringExtra("cate_id"));
        }
        builder.add("uid", MyApplication.getmInstance().getUid());
        if (!status.equals("-1")) {
            builder.add("status", status);
        }
        if (!TextUtils.isEmpty(edittext.getText())) {
            builder.add("key", edittext.getText().toString());
        }
        return builder;
    }


}
