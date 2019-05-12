package com.test720.grasshoppercollege.module.gongLue.teacher.pinTaiZiXun;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.daimajia.slider.library.SliderLayout;
import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.gongLue.GongLueData;
import com.test720.grasshoppercollege.module.gongLue.teacher.bean.ZiXunShouData;
import com.test720.grasshoppercollege.module.gongLue.teacher.pinTaiZiXun.smallCourseShouRu.SmallCourseShouRuActivity;
import com.test720.grasshoppercollege.module.gongLue.teacher.pinTaiZiXun.wenDa.WenDaActivity;
import com.test720.grasshoppercollege.module.gongLue.teacher.pinTaiZiXun.wenDa.WenDaHuiFuActivity;
import com.test720.grasshoppercollege.module.gongLue.teacher.pinTaiZiXun.yuYue.YuYueActivity;
import com.test720.grasshoppercollege.module.gongLue.teacher.pinTaiZiXun.yuYue.YuYueDingDanXiangQingActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.MyLinearLayoutManager;
import com.test720.grasshoppercollege.untils.lunBo.Banner;
import com.test720.grasshoppercollege.untils.lunBo.LunBoUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ZhiXunShiShouActivity extends BaseToolActivity {

    @BindView(R.id.slider)
    SliderLayout slider;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipe_refresh;
    @BindView(R.id.recyclerViewTwo)
    RecyclerView recyclerViewTwo;


    private List<Banner> banner = new ArrayList<>();
    private List<ZiXunShouData.DataBean.YuyueBean> yuyue = new ArrayList<>();
    private List<ZiXunShouData.DataBean.AnswerBean> answer = new ArrayList<>();

    MyBaseRecyclerAdapter adapter;
    MyBaseRecyclerAdapter adapterTwo;

    @Override
    protected String setTitle() {
        return "咨询师";
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_zhi_xun_shi_shou;
    }

    @Override
    protected void initData() {
        //解决数据加载不完的问题
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        //解决数据加载完成后, 没有停留在顶部的问题
        recyclerView.setFocusable(false);

        //解决数据加载不完的问题
        recyclerViewTwo.setNestedScrollingEnabled(false);
        recyclerViewTwo.setHasFixedSize(true);
        //解决数据加载完成后, 没有停留在顶部的问题
        recyclerViewTwo.setFocusable(false);
        swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe_refresh.setRefreshing(false);
                getData();
            }
        });

        getData();
        yuyueDingDan();
        answerinit();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getData();
    }

    /**
     * 预约订单
     */
    private void yuyueDingDan() {
        MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(mcontext);
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MyBaseRecyclerAdapter<ZiXunShouData.DataBean.YuyueBean>(yuyue, mcontext, R.layout.yu_yue_ding_dan_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, ZiXunShouData.DataBean.YuyueBean item, int postion) {
                holder.setText(R.id.name, item.getNickname());
                holder.setText(R.id.title, item.getService_name());
                String s = "咨询时间" + item.getTimes() + "(共" + item.getTime_length() + "分钟)";
                holder.setText(R.id.time, s);
                holder.setHeaderByUrl(R.id.pic, item.getHeader());
            }
        };
        adapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(mcontext, YuYueDingDanXiangQingActivity.class);
                intent.putExtra("status", "tid");
                intent.putExtra("bohao", false);
                intent.putExtra("yuyue_id", yuyue.get(position).getYuyue_id());
                jumpToActivity(intent, false);
            }
        });
        recyclerView.setAdapter(adapter);

    }

    /**
     * 问答
     */
    private void answerinit() {
        MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(mcontext);
        //设置布局管理器
        recyclerViewTwo.setLayoutManager(layoutManager);
        adapterTwo = new MyBaseRecyclerAdapter<ZiXunShouData.DataBean.AnswerBean>(answer, mcontext, R.layout.yu_yue_ding_dan_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, ZiXunShouData.DataBean.AnswerBean item, int postion) {
                holder.setText(R.id.type, "问答订单");
                holder.setText(R.id.topType, "待回复");
                holder.getView(R.id.name).setVisibility(View.GONE);
                holder.setText(R.id.title, item.getQuestion());
                String s = "咨询时间" + item.getQuestion_time();
                holder.setText(R.id.time, s);
                holder.setHeaderByUrl(R.id.pic, item.getUser_header());
            }
        };
        adapterTwo.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(mcontext, WenDaHuiFuActivity.class);
                intent.putExtra("answer_id", answer.get(position).getAnswer_id());
                intent.putExtra("type", WenDaHuiFuActivity.TEACHER_DHF);
                jumpToActivity(intent, false);
            }
        });
        recyclerViewTwo.setAdapter(adapterTwo);
    }

    /**
     * 获取数据
     */
    private void getData() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        postResponse(HttpUntil.GetIntent().StrategyconsultingIndex(), httpParams, 1, true);
    }


    /**
     * 轮播
     */
    boolean b = false;

    private void initLunBo() {
        if (b) return;
        LunBoUntil lunBoUntil = new LunBoUntil(slider, banner, mcontext);
        lunBoUntil.start();
        b = true;
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        if (codeIsOne(str, false)) {
            ZiXunShouData data = new Gson().fromJson(str, ZiXunShouData.class);
            //轮播
            banner.clear();
            banner.addAll(data.getData().getBanner());
            initLunBo();
            //预约
            yuyue.clear();
            yuyue.addAll(data.getData().getYuyue());
            adapter.notifyDataSetChanged();

            //问答
            answer.clear();
            answer.addAll(data.getData().getAnswer());
            adapterTwo.notifyDataSetChanged();

        }
    }


    @OnClick({R.id.appointment, R.id.answer_one_line, R.id.smallclass})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.appointment:
                jumpToActivity(YuYueActivity.class, false);
                break;
            case R.id.answer_one_line:
                jumpToActivity(WenDaActivity.class, false);
                break;
            case R.id.smallclass:
                Intent intent = new Intent(mcontext, SmallCourseShouRuActivity.class);
                intent.putExtra("status", "consultant");
                GongLueData.getInstance().setYeType(GongLueData.YEZIXUNSHISMALL);
                jumpToActivity(intent, false);
                break;
        }
    }


}
