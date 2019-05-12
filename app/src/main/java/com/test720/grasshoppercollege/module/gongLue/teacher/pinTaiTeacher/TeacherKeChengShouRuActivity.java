package com.test720.grasshoppercollege.module.gongLue.teacher.pinTaiTeacher;

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
import com.test720.grasshoppercollege.module.gongLue.GongLueZhuanYEActivity;
import com.test720.grasshoppercollege.module.gongLue.ZhuanYEJLActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;

public class TeacherKeChengShouRuActivity extends BaseTwoRecyclerViewActivity<TeacherKeChengShouRuActivity.ShouRuData.DataBean.InfoBean> {

    @BindView(R.id.money)
    TextView money;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

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
                return HttpUntil.GetIntent().getIP() + "small.php/Strategy/lineRecord";
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
            ShouRuData data = new Gson().fromJson(str, ShouRuData.class);
            getList().clear();
            getList().addAll(data.getData().getInfo());
            getAdapter().notifyDataSetChanged();
            money.setText(data.getData().getMoney());
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            ShouRuData data = new Gson().fromJson(str, ShouRuData.class);
            getList().addAll(data.getData().getInfo());
            getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_small_course_shou_ru;
    }

    @Override
    protected void initData() {
        setAdapter(new MyBaseRecyclerAdapter<ShouRuData.DataBean.InfoBean>(getList(), mcontext, R.layout.small_ji_lv_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, ShouRuData.DataBean.InfoBean item, int postion) {
                holder.setText(R.id.title, item.getName());
                holder.setText(R.id.money, item.getTeacher_money());
                holder.setText(R.id.time, item.getTime());

            }
        });
        recyclerView.setAdapter(getAdapter());
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


    class ShouRuData {

        /**
         * code : 1
         * msg : 成功
         * data : {"info":[{"teacher_money":"100.00","time":"2018-12-09 23:30:40","course_name":"蝈蝈老师2828","name":"第一颗阿三地方阿飞啊打发是的发射点发生发生发阿三地方阿三地方"}],"money":"99.00"}
         */

        private int code;
        private String msg;
        private DataBean data;

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

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public class DataBean {
            /**
             * info : [{"teacher_money":"100.00","time":"2018-12-09 23:30:40","course_name":"蝈蝈老师2828","name":"第一颗阿三地方阿飞啊打发是的发射点发生发生发阿三地方阿三地方"}]
             * money : 99.00
             */

            private String money;
            private List<InfoBean> info;

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public List<InfoBean> getInfo() {
                return info;
            }

            public void setInfo(List<InfoBean> info) {
                this.info = info;
            }

            public class InfoBean {
                /**
                 * teacher_money : 100.00
                 * time : 2018-12-09 23:30:40
                 * course_name : 蝈蝈老师2828
                 * name : 第一颗阿三地方阿飞啊打发是的发射点发生发生发阿三地方阿三地方
                 */

                private String teacher_money;
                private String time;
                private String course_name;
                private String name;

                public String getTeacher_money() {
                    return teacher_money;
                }

                public void setTeacher_money(String teacher_money) {
                    this.teacher_money = teacher_money;
                }

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public String getCourse_name() {
                    return course_name;
                }

                public void setCourse_name(String course_name) {
                    this.course_name = course_name;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }
        }
    }
}
