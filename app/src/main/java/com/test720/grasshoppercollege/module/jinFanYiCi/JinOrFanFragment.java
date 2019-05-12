package com.test720.grasshoppercollege.module.jinFanYiCi;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseRecyclerViewFragment;
import com.test720.grasshoppercollege.HttpBean.JInOrFanListData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 水东流 on 2018/5/15.
 */

public abstract class JinOrFanFragment extends BaseRecyclerViewFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    MyBaseRecyclerAdapter adapter;
    List<String> list = new ArrayList<>();
    @BindView(R.id.edittext)
    EditText edittext;
    @BindView(R.id.cha)
    RelativeLayout cha;


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
        return HttpUntil.GetIntent().ChineseJFdictionaryLib();
    }

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            JInOrFanListData data = new Gson().fromJson(str, JInOrFanListData.class);
            list.clear();
            list.addAll(data.getData());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            JInOrFanListData data = new Gson().fromJson(str, JInOrFanListData.class);
            list.addAll(data.getData());
            adapter.notifyDataSetChanged();
        }
    }

    public abstract String type();

    @Override
    public HttpParams httpParams() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("p", page);
        if (!TextUtils.isEmpty(edittext.getText())) {
            httpParams.put("key", edittext.getText().toString());
        }
        httpParams.put("type", type());
        return httpParams;
    }

    @Override
    public void init() {
        adapter = new MyBaseRecyclerAdapter<String>(list, getActivity(), R.layout.jin_or_fan_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, String item, int postion) {
                holder.setText(R.id.text, item);
            }
        };
        adapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
//                jumpToActivity(JinfanTiMuActivity.class, false);
            }
        });
        recyclerView.setAdapter(adapter);

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
    }

    @Override
    public void setListener() {
        setHaveLin(false);
    }

    @Override
    public int setlayoutResID() {
        return R.layout.jin_or_fan_fragment;
    }


    @OnClick(R.id.cha)
    public void onViewClicked() {
        MyApplication.getmInstance().setLoginDialogIsShow(false);
        if (!MyApplication.getmInstance().getUid().equals("")) {
            ShuaXin();
        } else {
            edittext.setText("");
        }

    }
}
