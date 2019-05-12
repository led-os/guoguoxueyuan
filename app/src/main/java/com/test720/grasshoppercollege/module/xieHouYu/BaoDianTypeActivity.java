package com.test720.grasshoppercollege.module.xieHouYu;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseRecyclerViewActivity;
import com.test720.grasshoppercollege.HttpBean.BiaoDianNeiBieData;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.FormBody;

public class BaoDianTypeActivity extends BaseRecyclerViewActivity {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    List<BiaoDianNeiBieData.DataBean> list = new ArrayList<>();
    MyBaseRecyclerAdapter adapter;

    @Override
    protected String setTitle() {
        return "类别";
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.base_shua_xin;
    }

    @Override
    protected void initData() {
        setNoGengDuo(true);
        adapter = new MyBaseRecyclerAdapter<BiaoDianNeiBieData.DataBean>(list, this, R.layout.bao_dian_type_list_item) {

            @Override
            public void convert(BaseRecyclerHolder holder, BiaoDianNeiBieData.DataBean item, int postion) {
                holder.setText(R.id.text, item.getName());
                holder.setImageByUrl(R.id.img, item.getPic());
            }
        };
        adapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(BaoDianTypeActivity.this, BaoDianActivity.class);
                intent.putExtra("lib_id", list.get(position).getLib_id());
                intent.putExtra("title", list.get(position).getName());
                jumpToActivity(intent, false);
            }
        });
        recyclerView.setAdapter(adapter);

    }

    @Override
    public int setCount() {
        return 3;
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
        return HttpUntil.GetIntent().ChineseXieHouYucatetoryList();
    }

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            BiaoDianNeiBieData dianNeiBieData = new Gson().fromJson(str, BiaoDianNeiBieData.class);
            list.clear();
            list.addAll(dianNeiBieData.getData());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            BiaoDianNeiBieData dianNeiBieData = new Gson().fromJson(str, BiaoDianNeiBieData.class);
            list.addAll(dianNeiBieData.getData());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public FormBody.Builder formBuilder() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("p", page + "");
        return builder;
    }

}
