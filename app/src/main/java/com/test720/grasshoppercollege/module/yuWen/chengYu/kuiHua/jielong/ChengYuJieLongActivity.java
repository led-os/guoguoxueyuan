package com.test720.grasshoppercollege.module.yuWen.chengYu.kuiHua.jielong;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseRecyclerViewActivity;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;

public class ChengYuJieLongActivity extends BaseRecyclerViewActivity {

    public MyBaseRecyclerAdapter adapter;
    @BindView(R.id.edittext)
    EditText edittext;
    @BindView(R.id.shou)
    ImageView shou;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    private List<ChengYuJieLongData.DataBean> list = new ArrayList<>();

    @Override
    protected String setTitle() {
        if (getIntent().getStringExtra("key") != null)
            return getIntent().getStringExtra("key");
        return "成语接龙";
    }

    @Override
    protected int setlayoutResID() {
        noLine = true;
        return R.layout.activity_cheng_yu_jie_long_flow;
    }

    @Override
    protected void initData() {
        adapter = new MyBaseRecyclerAdapter<ChengYuJieLongData.DataBean>(list, mcontext, R.layout.cheng_yu_jie_long_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, ChengYuJieLongData.DataBean item, int postion) {
                TextView textView = holder.getView(R.id.text);
                textView.setText(item.getName());
                holder.setText(R.id.dece, item.getDesc());
            }
        };
        recyclerView.setAdapter(adapter);
    }

    @Override
    public int setCount() {
        return 1;
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
        return HttpUntil.GetIntent().ChineseIdiombibleSearch();
    }

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            ChengYuJieLongData data = new Gson().fromJson(str, ChengYuJieLongData.class);
            list.clear();
            list.addAll(data.getData());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            ChengYuJieLongData data = new Gson().fromJson(str, ChengYuJieLongData.class);
            list.addAll(data.getData());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public FormBody.Builder formBuilder() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("p", page + "");
        if (getIntent().getStringExtra("value") != null) {
            builder.add("desc", getIntent().getStringExtra("value"));
        }
        if (getIntent().getStringExtra("key") != null) {
            builder.add("key", getIntent().getStringExtra("key"));
        }
        return builder;
    }


    @OnClick({R.id.edittext, R.id.shou})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.edittext:
            case R.id.shou:
                finish();
                break;
        }
    }
}
