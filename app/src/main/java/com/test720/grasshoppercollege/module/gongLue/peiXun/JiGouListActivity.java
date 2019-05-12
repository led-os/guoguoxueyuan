package com.test720.grasshoppercollege.module.gongLue.peiXun;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseTwoRecyclerViewActivity;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.gongLue.peiXun.bean.JIHouListData;
import com.test720.grasshoppercollege.untils.HttpUntil;

import www.test720.mylibrary.SPUtils;

import butterknife.BindView;
import okhttp3.FormBody;

public class JiGouListActivity extends BaseTwoRecyclerViewActivity<JIHouListData.DataBean> {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    @Override
    protected int setlayoutResID() {
        return R.layout.base_shua_xin;
    }

    @Override
    protected void initData() {

        setAdapter(new MyBaseRecyclerAdapter<JIHouListData.DataBean>(getList(), mcontext, R.layout.ji_gou_list_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, JIHouListData.DataBean item, int postion) {
                holder.setImageByUrl(R.id.img, item.getCover());
                holder.setText(R.id.text, item.getName());
            }
        });
        getAdapter().setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(mcontext, MechanismActivity.class);
                intent.putExtra("school_id", getList().get(position).getSchool_id());
                intent.putExtra("title", getList().get(position).getName());
                jumpToActivity(intent, false);
            }
        });
        recyclerView.setAdapter(getAdapter());
    }

    @Override
    public ReflshInit reflshInit() {
        return new ReflshInit() {
            @Override
            public String reTitle() {
                if (getIntent().getStringExtra("title") != null)
                    return getIntent().getStringExtra("title");
                return "机构";
            }

            @Override
            public FormBody.Builder reFormBuilder() {
                FormBody.Builder builder = new FormBody.Builder();
                builder.add("cate_id", getIntent().getStringExtra("cate_id"));
                builder.add("status", "1");
                builder.add("province", SPUtils.getProvince(mcontext));
                builder.add("city", SPUtils.getCity(mcontext));
                builder.add("area", SPUtils.getArea(mcontext));
                builder.add("p", page + "");
                return builder;
            }

            @Override
            public String reUrl() {
                return HttpUntil.GetIntent().StrategyTrainschoolList();
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

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            JIHouListData data = new Gson().fromJson(str, JIHouListData.class);
            getList().clear();
            getList().addAll(data.getData());
            getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            JIHouListData data = new Gson().fromJson(str, JIHouListData.class);
            getList().addAll(data.getData());
            getAdapter().notifyDataSetChanged();
        }
    }

}
