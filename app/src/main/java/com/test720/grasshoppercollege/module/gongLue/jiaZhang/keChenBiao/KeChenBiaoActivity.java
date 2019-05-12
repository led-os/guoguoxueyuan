package com.test720.grasshoppercollege.module.gongLue.jiaZhang.keChenBiao;

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
import com.test720.grasshoppercollege.module.gongLue.jiaZhang.bean.JiaZhangKeBiaoData;
import com.test720.grasshoppercollege.module.gongLue.teacher.pinTaiZiXun.yuYue.YuYueDingDanXiangQingActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.Calendar;

import butterknife.BindView;
import okhttp3.FormBody;

public class KeChenBiaoActivity extends BaseTwoRecyclerViewActivity<JiaZhangKeBiaoData.DataBean> {


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
                return "我的预约";
            }

            @Override
            public FormBody.Builder reFormBuilder() {


                FormBody.Builder builder = new FormBody.Builder();
                builder.add("p", page + "");
                builder.add("uid", MyApplication.getmInstance().getUid());
                builder.add("yuyue_time", year + "-" + month + "-" + day);
                return builder;
            }

            @Override
            public String reUrl() {
                return HttpUntil.GetIntent().getIP() + "small.php/Strategy/curriculumParents";
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
        //初始化时间
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initData() {
        setAdapter(new MyBaseRecyclerAdapter<JiaZhangKeBiaoData.DataBean>(getList(), mcontext, R.layout.ke_cheng_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, JiaZhangKeBiaoData.DataBean item, int postion) {
                holder.setHeaderByUrl(R.id.pic, item.getHeader());
                holder.setText(R.id.name, item.getNickname());
                holder.setText(R.id.time, item.getTimes());
                holder.setText(R.id.day, item.getYuyue_time());
                holder.setText(R.id.title, item.getService_name());
                holder.getView(R.id.seeJiLv).setVisibility(View.GONE);
                switch (item.getUser_status()) {
                    case "0":
                        holder.setText(R.id.status, "待咨询");
                        break;
                    case "1":
                        holder.setText(R.id.status, "待评价");
                        break;
                    case "2":
                        holder.setText(R.id.status, "已咨询");
                        break;
                }

            }
        });
        @SuppressLint("InflateParams") View head = LayoutInflater.from(mcontext).inflate(R.layout.jia_zhang_ke_cheng_biao_head, null);
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
        getAdapter().setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(mcontext, YuYueDingDanXiangQingActivity.class);
                intent.putExtra("status", "uid");
                intent.putExtra("yuyue_id", getList().get(position).getYuyue_id());
                jumpToActivity(intent, false);

            }
        });
        recyclerView.setAdapter(getAdapter());

    }


    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            JiaZhangKeBiaoData data = new Gson().fromJson(str, JiaZhangKeBiaoData.class);
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
            JiaZhangKeBiaoData data = new Gson().fromJson(str, JiaZhangKeBiaoData.class);
            getList().addAll(data.getData());
            getAdapter().notifyDataSetChanged();
        }
    }

}