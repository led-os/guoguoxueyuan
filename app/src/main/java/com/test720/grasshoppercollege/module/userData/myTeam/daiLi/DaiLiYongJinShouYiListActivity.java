package com.test720.grasshoppercollege.module.userData.myTeam.daiLi;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseTwoRecyclerViewActivity;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.userData.myTeam.bean.DaiLiYongJinJiLuListData;
import com.test720.grasshoppercollege.untils.HttpUntil;

import butterknife.BindView;
import okhttp3.FormBody;

public class DaiLiYongJinShouYiListActivity extends BaseTwoRecyclerViewActivity<DaiLiYongJinJiLuListData.DataBean.ListBean> {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.money)
    TextView money;
    @BindView(R.id.time)
    TextView time;

    String startTime, endTime, agentId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_dai_li_yong_jin;
    }

    @Override
    protected void initData() {
        setAdapter(new MyBaseRecyclerAdapter<DaiLiYongJinJiLuListData.DataBean.ListBean>(getList(), mcontext, R.layout.team_yong_jin_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, DaiLiYongJinJiLuListData.DataBean.ListBean item, int postion) {
                holder.setText(R.id.name, "时间");
                holder.setText(R.id.time, item.getTime());
                holder.setText(R.id.jine, "收益");
                TextView textView = holder.getView(R.id.beizhu);
                textView.setTextColor(getResources().getColor(R.color.cai_wu));
                if (item.getInout().equals("1")) {
                    holder.setText(R.id.beizhu, "转入" + item.getMoney());
                } else {
                    holder.setText(R.id.beizhu, "转出" + item.getMoney());
                }
            }
        });

        viewHeadEnvent();
        recyclerView.setAdapter(getAdapter());
    }

    private void viewHeadEnvent() {
        if (startTime == null) {
            startTime = getIntent().getStringExtra("startTime");
        }
        if (endTime == null) {
            endTime = getIntent().getStringExtra("endTime");

        }
        money.setText(getIntent().getStringExtra("money"));
        String t = startTime + " -- " + endTime;
        time.setText(t);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
                if (startTime == null) {
                    startTime = getIntent().getStringExtra("startTime");
                }
                if (endTime == null) {
                    endTime = getIntent().getStringExtra("endTime");

                }
                if (agentId == null) {
                    agentId = getIntent().getStringExtra("agentId");
                }
                FormBody.Builder builder = new FormBody.Builder();
                builder.add("startTime", startTime);
                builder.add("endTime", endTime);
                builder.add("p", page + "");
                builder.add("agent_id", agentId);
                return builder;
            }

            @Override
            public String reUrl() {
                return HttpUntil.GetIntent().UseragentRecord();
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
            DaiLiYongJinJiLuListData data = new Gson().fromJson(str, DaiLiYongJinJiLuListData.class);
            getList().clear();
            getList().addAll(data.getData().getList());
            getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            DaiLiYongJinJiLuListData data = new Gson().fromJson(str, DaiLiYongJinJiLuListData.class);
            getList().addAll(data.getData().getList());
            getAdapter().notifyDataSetChanged();
        }
    }

}
