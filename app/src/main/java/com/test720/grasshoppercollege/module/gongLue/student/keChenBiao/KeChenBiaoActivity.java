package com.test720.grasshoppercollege.module.gongLue.student.keChenBiao;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseTwoRecyclerViewActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.gongLue.student.bean.KeChenBiaoData;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.Calendar;

import butterknife.BindView;
import okhttp3.FormBody;

public class KeChenBiaoActivity extends BaseTwoRecyclerViewActivity<KeChenBiaoData.DataBean> {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    TextView num;
    CalendarView datePicker;

    int year, month, day;

    @Override
    public ReflshInit reflshInit() {
        return new ReflshInit() {
            @Override
            public String reTitle() {
                return "我的课程表";
            }

            @Override
            public FormBody.Builder reFormBuilder() {
                FormBody.Builder builder = new FormBody.Builder();
                builder.add("p", page + "");
                builder.add("uid", MyApplication.getmInstance().getUid());
                builder.add("class_name", year + "-" + month + "-" + day);
                return builder;
            }

            @Override
            public String reUrl() {
                return HttpUntil.GetIntent().StrategymyCurriculum();
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
    protected int setlayoutResID() {
        return R.layout.base_shua_xin;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Calendar now = Calendar.getInstance();
        year = now.get(Calendar.YEAR);
        month = now.get(Calendar.MONTH) + 1;
        day = now.get(Calendar.DAY_OF_MONTH);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initData() {
        setAdapter(new MyBaseRecyclerAdapter<KeChenBiaoData.DataBean>(getList(), mcontext, R.layout.ke_cheng_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, final KeChenBiaoData.DataBean item, int postion) {
                holder.setHeaderByUrl(R.id.pic, item.getTeacher_header());
                holder.setText(R.id.name, item.getTeacher_name());
                String ts = item.getWeek() + " " + item.getTimes();
                holder.setText(R.id.time, ts);
                holder.setText(R.id.day, item.getClass_time());
                holder.setText(R.id.title, item.getCourse_name());
                switch (item.getStatus()) {
                    case "0":
                        holder.setText(R.id.status, "未上课");
                        holder.getView(R.id.seeJiLv).setVisibility(View.GONE);
                        break;
                    case "1":
                        holder.setText(R.id.status, "已上课");
                        holder.getView(R.id.seeJiLv).setVisibility(View.VISIBLE);
                        break;
                    case "2":
                        holder.setText(R.id.status, "缺席");
                        holder.getView(R.id.seeJiLv).setVisibility(View.GONE);
                        break;
                }
                holder.getView(R.id.seeJiLv).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mcontext, KeChenJiLuActivity.class);
                        intent.putExtra("name", item.getTeacher_name());
                        intent.putExtra("time", item.getTimes());
                        intent.putExtra("class_id", item.getClass_id());
                        intent.putExtra("title", item.getCourse_name());
                        intent.putExtra("head", item.getTeacher_header());
                        jumpToActivity(intent, false);

                    }
                });
            }
        });
        @SuppressLint("InflateParams") View head = LayoutInflater.from(mcontext).inflate(R.layout.activity_ke_chen_biao, null);
        datePicker = head.findViewById(R.id.dataPicker);
        num = head.findViewById(R.id.num);
        datePicker.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int y, int m, int dayOfMonth) {
                year = y;
                month = m + 1;
                day = dayOfMonth;
                ShuaXin();
            }
        });
        getAdapter().setHeaderView(head);
        recyclerView.setAdapter(getAdapter());

    }


    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            KeChenBiaoData data = new Gson().fromJson(str, KeChenBiaoData.class);
            getList().clear();
            getList().addAll(data.getData());
            getAdapter().notifyDataSetChanged();
            if (num != null) {
                num.setText(data.getData().size() + "");
            }
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            KeChenBiaoData data = new Gson().fromJson(str, KeChenBiaoData.class);
            getList().addAll(data.getData());
            getAdapter().notifyDataSetChanged();
        }
    }

}
