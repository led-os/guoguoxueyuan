package com.test720.grasshoppercollege.module.yuWen.chengYu.kuiHua;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseRecyclerViewFragment;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.yuWen.chengYu.kuiHua.jielong.ChengYuJieLongData;
import com.test720.grasshoppercollege.module.yuWen.chengYu.kuiHua.jielong.ChengYuJieLongFlowActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 水东流 on 2018/7/5.
 */

public class ChengYuJieLong extends BaseRecyclerViewFragment {
    @BindView(R.id.edittext)
    EditText edittext;
    @BindView(R.id.shou)
    ImageView shou;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    public MyBaseRecyclerAdapter adapter;
    private List<ChengYuJieLongData.DataBean> list = new ArrayList<>();

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
    public HttpParams httpParams() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("p", page);
        httpParams.put("key", "");
        return httpParams;
    }

    @Override
    public void init() {
        if (MyApplication.getmInstance().getUid().equals("")) {
            edittext.setCursorVisible(false);
            edittext.setFocusable(false);
            edittext.setFocusableInTouchMode(false);
            edittext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyApplication.getmInstance().setLoginDialogIsShow(false);
                    MyApplication.getmInstance().getUid();
                }
            });
        }
        adapter = new MyBaseRecyclerAdapter<ChengYuJieLongData.DataBean>(list, getActivity(), R.layout.cheng_yu_jie_long_item) {
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
    public void setListener() {

    }


    @Override
    public int setlayoutResID() {
        return R.layout.kui_hua_nian_ji_fragment;
    }


    @OnClick({R.id.shou})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shou:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (!MyApplication.getmInstance().getUid().equals("")) {
                    if (TextUtils.isEmpty(edittext.getText())) {
                        edittext.setError("请输入关键字");
                        return;
                    }
                    Intent intent = new Intent(mContext, ChengYuJieLongFlowActivity.class);
                    intent.putExtra("key", edittext.getText().toString());
                    jumpToActivity(intent, false);
                }
                break;
        }
    }

}

