package com.test720.grasshoppercollege.module.yuFaTest;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseShouQuanXianActivity;
import com.test720.grasshoppercollege.BuyClassActivity;
import com.test720.grasshoppercollege.HttpBean.YuFaTestShouTwoData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.FormBody;

public class YuFaTestShouTwoActivity extends BaseShouQuanXianActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    String[] shareStr = new String[3];//分享数据保存
    MyBaseRecyclerAdapter adapter;
    List<YuFaTestShouTwoData.DataBean> list = new ArrayList<>();

    @Override
    protected String setTitle() {
        if (getIntent().getStringExtra("title") != null) return getIntent().getStringExtra("title");
        return "语法练习";
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.base_shua_xin;
    }

    @Override
    protected void initData() {
        adapter = new MyBaseRecyclerAdapter<YuFaTestShouTwoData.DataBean>(list, this, R.layout.shu_xue_shou_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, YuFaTestShouTwoData.DataBean item, int postion) {
                holder.setImageByUrl(R.id.pic, item.getPic());
                holder.setText(R.id.name, item.getName());
                if (item.getOpen_up() == 1) {
                    holder.getView(R.id.suo).setVisibility(View.GONE);
                } else {
                    holder.getView(R.id.suo).setVisibility(View.VISIBLE);
                }
            }
        };
        adapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //填充分享数据
                shareStr[0] = list.get(position).getTitle();
                shareStr[1] = list.get(position).getContent();
                shareStr[2] = HttpUntil.GetIntent().getShare() + 16;
                setItemIndex(position);
                re_id = list.get(position).getBook_id();
                points = list.get(position).getPoints();
                /*判断权限*/
                boolean canSee = isCanSee(list.get(position).getPermissions(), list.get(position).isPay(), list.get(position).getOpen_up() + "");
                if (canSee) {
                    adapterItemClick(position);
                }
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void adapterItemClick(int postion) {
        Intent in = new Intent(mcontext, YuFaTestTiMuActivity.class);
        in.putExtra("book_id", list.get(postion).getBook_id());
        jumpToActivity(in, false);

    }


    @Override
    public void buyCurr() {
        Intent intent = new Intent(mcontext, BuyClassActivity.class);
        intent.putExtra("curr_type", 24);
        intent.putExtra("type", "4");
        jumpToActivity(intent, false);
    }

    @Override
    public void buyGuoGuo() {
        if (re_id == null) return;
        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("uid", MyApplication.getmInstance().getUid());//请求参数一
        formBuilder.add("curr_type", "24");
        formBuilder.add("type", "2");
        formBuilder.add("points", points);
        formBuilder.add("re_id", re_id);
        Post(HttpUntil.GetIntent().addShareSpendRecord(), formBuilder, 123, false);
    }

    @Override
    public void shareCurr() {
        if (points == null) return;
        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("uid", MyApplication.getmInstance().getUid());//请求参数一
        formBuilder.add("curr_type", "24");
        formBuilder.add("type", "1");
        formBuilder.add("re_id", re_id);
        Post(HttpUntil.GetIntent().addShareSpendRecord(), formBuilder, 123, false);
    }

    @Override
    public String[] shareStr() {
        return shareStr;
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
        return HttpUntil.GetIntent().GrammarPracticeindexList();
    }

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            YuFaTestShouTwoData data = new Gson().fromJson(str, YuFaTestShouTwoData.class);
            list.clear();
            list.addAll(data.getData());
            adapter.notifyDataSetChanged();

        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            YuFaTestShouTwoData data = new Gson().fromJson(str, YuFaTestShouTwoData.class);
            list.addAll(data.getData());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public FormBody.Builder formBuilder() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("uid", MyApplication.getmInstance().getUid());
        builder.add("p", page + "");
        builder.add("book_id", getIntent().getStringExtra("book_id"));
        return builder;
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        super.GetHttpBackStr(str, what);
        switch (what) {
            case 122:
            case 123:
                if (codeIsOne(str) && getItemIndex() != -1) {
                    list.get(getItemIndex()).setOpen_up(1);
                    adapter.notifyDataSetChanged();
                }
                break;
        }
    }
}
