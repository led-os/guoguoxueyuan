package com.test720.grasshoppercollege.module.youEr.baoBaoTingTing;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.youEr.baoBaoTingTing.bean.BaoBaoTingTingTypeData;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class BaoBaoTypeActivity extends BaseToolActivity {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    MyBaseRecyclerAdapter adapter;
    List<BaoBaoTingTingTypeData.DataBean> list = new ArrayList<>();
    @BindView(R.id.title)
    TextView title;


    @Override
    protected String setTitle() {
        return "全部分类";
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_bao_bao_type;
    }

    @Override
    protected void initData() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mcontext));

        adapter = new MyBaseRecyclerAdapter<BaoBaoTingTingTypeData.DataBean>(list, mcontext, R.layout.bao_bao_type_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, final BaoBaoTingTingTypeData.DataBean item, final int postion) {
                final TagFlowLayout tagFlowLayout = holder.getView(R.id.flow);
                TagAdapter tagAdapter = new TagAdapter<BaoBaoTingTingTypeData.DataBean.ListBean>(item.getList()) {
                    @Override
                    public View getView(FlowLayout flowLayout, int i, BaoBaoTingTingTypeData.DataBean.ListBean childrenItem) {
                        View view = LayoutInflater.from(mcontext).inflate(R.layout.baobao_type_biao_qian, null);
                        TextView textView = view.findViewById(R.id.text);
                        textView.setText(childrenItem.getName());
                        return view;
                    }
                };
                tagFlowLayout.setAdapter(tagAdapter);
                tagFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                    @Override
                    public boolean onTagClick(View view, int i, FlowLayout flowLayout) {
                        Intent intent1 = new Intent(mcontext, ZhuanJilistActivity.class);
                        intent1.putExtra("status", "3");
                        intent1.putExtra("cate_id", item.getList().get(i).getCate_id());
                        jumpToActivity(intent1, false);
                        return true;
                    }
                });
                holder.setText(R.id.title, item.getName());
            }

        };
        recyclerView.setAdapter(adapter);

        HttpParams httpParams = new HttpParams();
        postResponse(HttpUntil.GetIntent().ChildBabyListeningcate(), httpParams, 1, true);
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        if (codeIsOne(str, false)) {
            BaoBaoTingTingTypeData data = new Gson().fromJson(str, BaoBaoTingTingTypeData.class);
            list.clear();
            list.addAll(data.getData());
            adapter.notifyDataSetChanged();
        }
    }


    @OnClick({R.id.back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
        }
    }
}
