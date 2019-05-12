package com.test720.grasshoppercollege.module.userData.myTeam.daiLi;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseTwoRecyclerViewActivity;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.userData.myTeam.bean.DaiLiYongJinJiLuListData;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;

public class DaiLiTimeChaXunActivity extends BaseTwoRecyclerViewActivity<DaiLiYongJinJiLuListData.DataBean.ListBean> {
    @BindView(R.id.startTime)
    TextView startTime;
    @BindView(R.id.endTime)
    TextView endTime;
    @BindView(R.id.ok)
    TextView ok;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipe_refresh;

    DatePickerDialog startDialog, endDialog;
    private int mYear, mMonth, mDay;
    String startTimeStr = "";
    String endTimeStr = "";

    @Override
    public ReflshInit reflshInit() {
        return new ReflshInit() {
            @Override
            public String reTitle() {
                return "收益明细";
            }

            @Override
            public FormBody.Builder reFormBuilder() {
                FormBody.Builder builder = new FormBody.Builder();
                builder.add("p", page + "");
                builder.add("agent_id", getIntent().getStringExtra("agentId"));
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
                return swipe_refresh;
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

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_dai_li_time_cha_xun;
    }

    @Override
    protected void initData() {
        Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);
        startDialog = new DatePickerDialog(mcontext, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month++;
                String s = year + "-" + month + "-" + dayOfMonth;
                startTimeStr = s;
                startTime.setText(startTimeStr);
            }
        }, mYear, mMonth, mDay);
        endDialog = new DatePickerDialog(mcontext, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month++;
                String s = year + "-" + month + "-" + dayOfMonth;
                endTimeStr = s;
                endTime.setText(endTimeStr);
            }
        }, mYear, mMonth, mDay);

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
        recyclerView.setAdapter(getAdapter());
    }


    @OnClick({R.id.startTime, R.id.endTime, R.id.ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.startTime:
                startDialog.show();
                break;
            case R.id.endTime:
                endDialog.show();
                break;
            case R.id.ok:
                if (startTimeStr.equals("") || endTimeStr.equals("")) {
                    ShowToast("请设置时间 ");
                    return;
                }
                Intent intent = new Intent(mcontext, DaiLiYongJinShouYiListActivity.class);
                intent.putExtra("startTime", startTimeStr);
                intent.putExtra("endTime", endTimeStr);
                intent.putExtra("money", getIntent().getStringExtra("money"));
                intent.putExtra("agentId", getIntent().getStringExtra("agentId"));
                jumpToActivity(intent, true);
                break;
        }
    }
}
