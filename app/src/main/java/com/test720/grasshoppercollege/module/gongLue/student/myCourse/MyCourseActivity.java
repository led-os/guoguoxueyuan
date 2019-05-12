package com.test720.grasshoppercollege.module.gongLue.student.myCourse;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseTwoRecyclerViewActivity;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.gongLue.student.bean.MyCourseData;
import com.test720.grasshoppercollege.untils.HttpUntil;

import butterknife.BindView;
import okhttp3.FormBody;

public class MyCourseActivity extends BaseTwoRecyclerViewActivity<MyCourseData.DataBean> {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    @Override
    public ReflshInit reflshInit() {
        return new ReflshInit() {
            @Override
            public String reTitle() {
                return "我的课程";
            }

            @Override
            public FormBody.Builder reFormBuilder() {
                FormBody.Builder builder = new FormBody.Builder();
                builder.add("order_id", getIntent().getStringExtra("order_id"));
                return builder;
            }

            @Override
            public String reUrl() {
                return HttpUntil.GetIntent().getIP() + "small.php/Strategy/myLineCourseInfo";
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
            MyCourseData data = new Gson().fromJson(str, MyCourseData.class);
            getList().clear();
            getList().addAll(data.getData());
            getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void gengDuo(String str) {

    }

    @Override
    protected int setlayoutResID() {
        return R.layout.base_shua_xin;
    }

    @Override
    protected void initData() {
        setAdapter(new MyBaseRecyclerAdapter<MyCourseData.DataBean>(getList(), mcontext, R.layout.mycourse_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, MyCourseData.DataBean item, int postion) {
                String p = (postion + 1) + "";
                holder.setText(R.id.num, p);
                String t = item.getClass_time() + "\t\t" + item.getWeek() + "\t\t" + item.getTimes();
                holder.setText(R.id.time, t);
                holder.setText(R.id.title, item.getCourse_name());
                switch (item.getStatus()) {
                    case "0":
                        holder.setText(R.id.status, "未上课");
                        break;
                    case "1":
                        holder.setText(R.id.status, "已上课");
                        break;
                    case "2":
                        holder.setText(R.id.status, "缺席");
                        break;
                }
            }
        });

        recyclerView.setAdapter(getAdapter());
    }

}
