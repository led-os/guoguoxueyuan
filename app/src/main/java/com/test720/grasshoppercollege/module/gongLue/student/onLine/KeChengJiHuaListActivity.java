package com.test720.grasshoppercollege.module.gongLue.student.onLine;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseTwoRecyclerViewActivity;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.List;

import butterknife.BindView;
import okhttp3.FormBody;

public class KeChengJiHuaListActivity extends BaseTwoRecyclerViewActivity<KeChengJiHuaListActivity.JiHuaData.DataBean> {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    @Override
    public ReflshInit reflshInit() {
        return new ReflshInit() {
            @Override
            public String reTitle() {
                return "课程计划";
            }

            @Override
            public FormBody.Builder reFormBuilder() {
                FormBody.Builder builder = new FormBody.Builder();
                builder.add("line_id", getIntent().getStringExtra("line_id"));
                builder.add("start_time", getIntent().getStringExtra("start_time"));
                builder.add("end_time", getIntent().getStringExtra("end_time"));
                builder.add("week", getIntent().getStringExtra("week"));
                builder.add("time_slot", getIntent().getStringExtra("time_slot"));
                return builder;
            }

            @Override
            public String reUrl() {
                return HttpUntil.GetIntent().getIP() + "small.php/Strategy/linePlan";
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
        JiHuaData data = new Gson().fromJson(str, JiHuaData.class);
        getList().clear();
        getList().addAll(data.getData());
        getAdapter().notifyDataSetChanged();
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
        setAdapter(new MyBaseRecyclerAdapter<JiHuaData.DataBean>(getList(), mcontext, R.layout.ke_cheng_ji_hua_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, JiHuaData.DataBean item, int postion) {
                holder.setText(R.id.text, item.getName());
                holder.setText(R.id.time, item.getTimes());
            }
        });
        recyclerView.setAdapter(getAdapter());
    }

    class JiHuaData {


        /**
         * code : 1
         * msg : 成功
         * data : [{"name":"1","times":"1970-01-01 周 08:00"},{"name":"2","times":"1970-01-08 周 08:00"},{"name":"3","times":"1970-01-15 周 08:00"}]
         */

        private int code;
        private String msg;
        private List<DataBean> data;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public class DataBean {
            /**
             * name : 1
             * times : 1970-01-01 周 08:00
             */

            private String name;
            private String times;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTimes() {
                return times;
            }

            public void setTimes(String times) {
                this.times = times;
            }
        }
    }

}
