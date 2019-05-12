package com.test720.grasshoppercollege.module.peiYin;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseTwoRecyclerViewActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.peiYin.bean.VersionData;
import com.test720.grasshoppercollege.module.peiYin.bean.ZhuanJiListData;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.FormBody;

public class PeiYinBookActivity extends BaseTwoRecyclerViewActivity<ZhuanJiListData.DataBean> {
    int index = 0;
    List<String> topList = new ArrayList<>();
    String version;
    TagAdapter tagAdapter;

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_pei_yin_book;
    }

    @Override
    protected void initData() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("type", MyApplication.getmInstance().getModuleType() + "");
        Post(HttpUntil.GetIntent().DubbinggetVersion(), builder, 5);

        tagAdapter = new TagAdapter<String>(topList) {
            @Override
            public View getView(FlowLayout flowLayout, int i, final String str) {
                View view = LayoutInflater.from(mcontext).inflate(R.layout.pei_yin_biao_qian, null);
                TextView textView = view.findViewById(R.id.text);
                textView.setText(str);
                if (index == i) {
                    textView.setBackgroundResource(R.drawable.green_back);
                    textView.setTextColor(getResources().getColor(R.color.white));
                } else {
                    textView.setBackgroundResource(R.color.touming);
                    textView.setTextColor(getResources().getColor(R.color.gwe));
                }
                return view;
            }
        };

        flow.setAdapter(tagAdapter);
        flow.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int i, FlowLayout flowLayout) {
                index = i;
                flow.getAdapter().notifyDataChanged();
                version = topList.get(i);
                if (version.equals("全部")) {
                    version = "";
                }
                ShuaXin();
                return true;
            }
        });

        setAdapter(new MyBaseRecyclerAdapter<ZhuanJiListData.DataBean>(getList(), this, R.layout.pei_yin_shi_pin_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, ZhuanJiListData.DataBean item, int postion) {

            }
        });
        getAdapter().setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(mcontext, PeiYinVideoListActivity.class);
                intent.putExtra("name", getList().get(position).getName());
                intent.putExtra("album_id", getList().get(position).getAlbum_id());
                jumpToActivity(intent, false);
            }
        });
        recyclerView.setAdapter(getAdapter());
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        super.GetHttpBackStr(str, what);
        switch (what) {
            case 5:
                VersionData versionData = new Gson().fromJson(str, VersionData.class);
                topList.clear();
                topList.add("全部");
                topList.addAll(versionData.getData());
                tagAdapter.notifyDataChanged();
                break;
        }

    }

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            ZhuanJiListData zhuanJiListData = new Gson().fromJson(str, ZhuanJiListData.class);
            getList().clear();
            getList().addAll(zhuanJiListData.getData());
            getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            ZhuanJiListData zhuanJiListData = new Gson().fromJson(str, ZhuanJiListData.class);
            getList().addAll(zhuanJiListData.getData());
            getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public ReflshInit reflshInit() {
        return new ReflshInit() {
            @Override
            public String reTitle() {
                return "动漫配音";
            }

            @Override
            public FormBody.Builder reFormBuilder() {
                FormBody.Builder builder = new FormBody.Builder();
                if (version != null) builder.add("version", version);
                builder.add("type", MyApplication.getmInstance().getModuleType() + "");
                builder.add("p", page + "");
                return builder;
            }

            @Override
            public String reUrl() {
                return HttpUntil.GetIntent().DubbingalbumList();
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


    @BindView(R.id.flow)
    TagFlowLayout flow;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
}
