package com.test720.grasshoppercollege.module.gongLue;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseTwoRecyclerViewActivity;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.WebViewActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.List;

import butterknife.BindView;
import okhttp3.FormBody;

public class GongLueListActivity extends BaseTwoRecyclerViewActivity<GongLueListActivity.GongGaoList.DataBean> {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    @Override
    public void setSys() {
        super.setSys();
        noLine = true;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.base_shua_xin;
    }

    @Override
    protected void initData() {
        setAdapter(new MyBaseRecyclerAdapter<GongGaoList.DataBean>(getList(), mcontext, R.layout.xiao_xi_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, GongGaoList.DataBean item, int postion) {
                holder.setText(R.id.time, item.getTime());
                holder.setImageByUrl(R.id.pic, item.getCover());
                holder.setText(R.id.body, item.getName());
            }
        });

        getAdapter().setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                switch (getList().get(position).getLocation_type()) {
                    case "1":
                    case "2":
                        Intent in = new Intent(mcontext, WebViewActivity.class);
                        in.putExtra("title", getList().get(position).getName());
                        in.putExtra("path", getList().get(position).getLocation_link());
                        jumpToActivity(in, false);
                        break;
                    case "3":
                        try {
                            Intent intent = new Intent();
                            intent.setAction("android.intent.action.VIEW");
                            Uri content_url = Uri.parse(getList().get(position).getLocation_link());
                            intent.setData(content_url);
                            startActivity(intent);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                }
            }
        });
        recyclerView.setAdapter(getAdapter());
    }

    @Override
    public ReflshInit reflshInit() {
        return new ReflshInit() {
            @Override
            public String reTitle() {
                return "公告";
            }

            @Override
            public FormBody.Builder reFormBuilder() {
                FormBody.Builder builder = new FormBody.Builder();
                if (getIntent().getStringExtra("school_id") != null) {
                    builder.add("school_id", getIntent().getStringExtra("school_id"));
                }
                if (getIntent().getStringExtra("notice_type") != null)
                    builder.add("notice_type", getIntent().getStringExtra("notice_type"));
                builder.add("p", page + "");
                return builder;
            }

            @Override
            public String reUrl() {
                return HttpUntil.GetIntent().getIP() + "small.php/StrategyChild/notice";
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
            GongGaoList data = new Gson().fromJson(str, GongGaoList.class);
            getList().clear();
            getList().addAll(data.getData());
            getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            GongGaoList data = new Gson().fromJson(str, GongGaoList.class);
            getList().addAll(data.getData());
            getAdapter().notifyDataSetChanged();
        }
    }


    public class GongGaoList {

        /**
         * code : 1
         * msg : 成功
         * data : [{"notice_id":"9","name":"共高列表你翻翻付付","cover":"http://www.guoguoxueyuan.com/ggxy/Uploads/notice/2018-11-01/5bda75a3de78a.jpg","time":"2018-11-01 11:40:20","location_type":"2","location_link":"http://www.guoguoxueyuan.com/ggxy/small.php/Mall/bannerWeb/id/9"}]
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
             * notice_id : 9
             * name : 共高列表你翻翻付付
             * cover : http://www.guoguoxueyuan.com/ggxy/Uploads/notice/2018-11-01/5bda75a3de78a.jpg
             * time : 2018-11-01 11:40:20
             * location_type : 2
             * location_link : http://www.guoguoxueyuan.com/ggxy/small.php/Mall/bannerWeb/id/9
             */

            private String notice_id;
            private String name;
            private String cover;
            private String time;
            private String location_type;
            private String location_link;

            public String getNotice_id() {
                return notice_id;
            }

            public void setNotice_id(String notice_id) {
                this.notice_id = notice_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getLocation_type() {
                return location_type;
            }

            public void setLocation_type(String location_type) {
                this.location_type = location_type;
            }

            public String getLocation_link() {
                return location_link;
            }

            public void setLocation_link(String location_link) {
                this.location_link = location_link;
            }
        }
    }

}
