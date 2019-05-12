package com.test720.grasshoppercollege.module.gongLue.teacher.pinTaiZiXun.smallCourseShouRu;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseTwoRecyclerViewActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.gongLue.GongLueData;
import com.test720.grasshoppercollege.module.gongLue.GongLueZhuanYEActivity;
import com.test720.grasshoppercollege.module.gongLue.ZhuanYEJLActivity;
import com.test720.grasshoppercollege.module.gongLue.teacher.bean.SmallJiLuData;
import com.test720.grasshoppercollege.untils.HttpUntil;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;

public class SmallCourseShouRuActivity extends BaseTwoRecyclerViewActivity<SmallJiLuData.DataBean.InfoBean> {


    @BindView(R.id.money)
    TextView money;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_small_course_shou_ru;
    }

    @Override
    protected void initData() {
        setAdapter(new MyBaseRecyclerAdapter<SmallJiLuData.DataBean.InfoBean>(getList(), mcontext, R.layout.small_ji_lv_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, SmallJiLuData.DataBean.InfoBean item, int postion) {
                holder.setText(R.id.title, item.getName());
                holder.setText(R.id.money, item.getPrice());
                holder.setText(R.id.time, item.getTime());

            }
        });
        recyclerView.setAdapter(getAdapter());
    }

    @Override
    public ReflshInit reflshInit() {
        return new ReflshInit() {
            @Override
            public String reTitle() {
                return null;
            }

            @Override
            public FormBody.Builder reFormBuilder() {
                FormBody.Builder builder = new FormBody.Builder();
                builder.add("p", page + "");
                builder.add("uid", MyApplication.getmInstance().getUid());
                builder.add("status", getIntent().getStringExtra("status"));
                return builder;
            }

            @Override
            public String reUrl() {
                if (GongLueData.getInstance().getYeType().equals(GongLueData.YEONLINE))//线上线下收支明细
                    return HttpUntil.GetIntent().getIP() + "small.php/Strategy/lineRecord";
                else
                    return HttpUntil.GetIntent().getIP() + "small.php/Strategy/microRecord";
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
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            SmallJiLuData data = new Gson().fromJson(str, SmallJiLuData.class);
            getList().clear();
            getList().addAll(data.getData().getInfo());
            getAdapter().notifyDataSetChanged();
            money.setText(data.getData().getMoney());
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            SmallJiLuData data = new Gson().fromJson(str, SmallJiLuData.class);
            getList().addAll(data.getData().getInfo());
            getAdapter().notifyDataSetChanged();
        }
    }


    @OnClick({R.id.back, R.id.zhuanYE, R.id.zhuanRu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.zhuanYE:
                Intent intent = new Intent(mcontext, GongLueZhuanYEActivity.class);
                if (!TextUtils.isEmpty(money.getText()))
                    intent.putExtra("money", money.getText().toString());
                jumpToActivity(intent, false);
                break;
            case R.id.zhuanRu:
                Intent intent1 = new Intent(mcontext, ZhuanYEJLActivity.class);
                jumpToActivity(intent1, false);
                break;
        }
    }


}
