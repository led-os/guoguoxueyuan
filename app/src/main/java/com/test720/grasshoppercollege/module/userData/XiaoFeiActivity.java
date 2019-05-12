package com.test720.grasshoppercollege.module.userData;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseRecyclerViewActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.FormBody;

public class XiaoFeiActivity extends BaseRecyclerViewActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.activity_xiao_fei)
    RelativeLayout activityXiaoFei;

    MyBaseRecyclerAdapter adapter;
    List<Data.DataBean> list = new ArrayList<>();

    @Override
    protected String setTitle() {
        return "消费";
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_xiao_fei;
    }

    @Override
    protected void initData() {
        adapter = new MyBaseRecyclerAdapter<Data.DataBean>(list, this, R.layout.xiao_fei_item) {

            @Override
            public void convert(BaseRecyclerHolder holder, Data.DataBean item, int postion) {
                holder.setText(R.id.body, item.getContent());
                holder.setText(R.id.time, item.getTime());
            }
        };
        recyclerView.setAdapter(adapter);
    }

    @Override
    public int setCount() {
        return 1;
    }

    @Override
    public RecyclerView initRecyclerView() {
        return recyclerView;
    }

    @Override
    public SwipeRefreshLayout initSwipeRefreshLayout() {
        return swipeRefresh;
    }

    @Override
    public String url() {
        return HttpUntil.GetIntent().getIP() + "small.php/User/usedRecord";
    }

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            Data data = new Gson().fromJson(str, Data.class);
            list.clear();
            list.addAll(data.getData());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            Data data = new Gson().fromJson(str, Data.class);
            list.addAll(data.getData());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public FormBody.Builder formBuilder() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("uid", MyApplication.getmInstance().getUid());
        builder.add("p", page + "");
        return builder;
    }

    class Data {

        /**
         * code : 1
         * msg : 成功
         * data : [{"content":"购买【VIP】和【课程 线下课程】，共202元","time":"2018-11-09 15:40:40"},{"content":"购买【VIP】和【课程 买这个课程】，共202元","time":"2018-11-09 15:40:18"},{"content":"","time":"0000-00-00 00:00:00"},{"content":"购买模块【幼儿点读乐园】，价格8.70元","time":"2018-11-08 20:47:48"},{"content":"购买模块【幼儿点读乐园】，价格8.70元","time":"2018-11-08 20:43:36"},{"content":"购买模块【幼儿点读乐园】，价格8.70元","time":"2018-11-08 20:36:56"},{"content":"购买模块【幼儿点读乐园】，价格8.70元","time":"2018-11-08 20:29:50"},{"content":"购买模块【幼儿点读乐园】，价格8.70元","time":"2018-11-08 20:18:58"},{"content":"购买模块【幼儿点读乐园】，价格8.40元","time":"2018-11-08 19:27:18"},{"content":"购买模块【幼儿点读乐园】，价格8.40元","time":"2018-11-08 19:16:10"}]
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
             * content : 购买【VIP】和【课程 线下课程】，共202元
             * time : 2018-11-09 15:40:40
             */

            private String content;
            private String time;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }
        }
    }

}
