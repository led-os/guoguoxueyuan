package com.test720.grasshoppercollege.module.userData.LearningCommunity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseTwoRecyclerViewActivity;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.WebViewActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.glideUntil.GlideUntil;

import java.util.List;

import butterknife.BindView;
import okhttp3.FormBody;

public class LearningCommunityActivity extends BaseTwoRecyclerViewActivity<LearningCommunityActivity.LearningData.DataBean.ListBean> {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    ImageView headPic;
    TextView headText;
    LearningData learningData;

    @Override
    protected int setlayoutResID() {
        haveHeader = true;
        return R.layout.base_shua_xin;
    }


    @Override
    protected void initData() {
        setAdapter(new MyBaseRecyclerAdapter<LearningData.DataBean.ListBean>(getList(), mcontext, R.layout.shu_xue_shou_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, LearningData.DataBean.ListBean item, int postion) {
                holder.getView(R.id.suo).setVisibility(View.GONE);
                holder.setImageByUrl(R.id.pic, item.getCover());
                holder.setText(R.id.name, item.getName());

            }
        });
        View view = LayoutInflater.from(mcontext).inflate(R.layout.learning_community_head, null);
        headPic = view.findViewById(R.id.pic);
        headText = view.findViewById(R.id.name);
        getAdapter().setHeaderView(view);

        getAdapter().setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (getList().get(position).getLocation_type().equals("2")) {
                    Intent intent = new Intent(mcontext, WebViewActivity.class);
                    intent.putExtra("title", getList().get(position).getName());
                    intent.putExtra("path", getList().get(position).getLocation_link());
                    jumpToActivity(intent, false);
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
                return "学习社区";
            }

            @Override
            public FormBody.Builder reFormBuilder() {
                FormBody.Builder builder = new FormBody.Builder();
                builder.add("p", "" + page);
                return builder;
            }

            @Override
            public String reUrl() {
                return HttpUntil.GetIntent().getIP() + "small.php/User/community";
            }

            @Override
            public int reCount() {
                return 2;
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
        learningData = new Gson().fromJson(str, LearningData.class);
        getList().clear();
        if (learningData.getData().getInfo() != null) {
            GlideUntil.getInstance().imgUrl(mcontext, headPic, learningData.getData().getInfo().getCover());
            headText.setText(learningData.getData().getInfo().getName());
            headPic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (learningData.getData().getInfo().getLocation_type().equals("2")) {
                        Intent intent = new Intent(mcontext, WebViewActivity.class);
                        intent.putExtra("title", learningData.getData().getInfo().getName());
                        intent.putExtra("path", learningData.getData().getInfo().getLocation_link());
                        jumpToActivity(intent, false);
                    }
                }
            });

        }
        getList().addAll(learningData.getData().getList());
        getAdapter().notifyDataSetChanged();
    }

    @Override
    public void gengDuo(String str) {
        LearningData learningData = new Gson().fromJson(str, LearningData.class);
        getList().addAll(learningData.getData().getList());
        getAdapter().notifyDataSetChanged();
    }

    class LearningData {


        /**
         * code : 1
         * msg : 成功
         * data : {"info":{"cover":"http://www.guoguoxueyuan.com/ggxy/Uploads/community/pic/2018-11-07/5be24d5a67997.jpg","name":"1111","location_type":"2","location_link":"http://www.guoguoxueyuan.com/ggxy/small.php/communityWeb/id/8"},"list":[{"cover":"http://www.guoguoxueyuan.com/ggxy/Uploads/community/pic/2018-11-07/5be24dba4fb1b.jpg","name":"测试水水水水","location_type":"2","location_link":"http://www.guoguoxueyuan.com/ggxy/small.php/communityWeb/id/9"},{"cover":"http://www.guoguoxueyuan.com/ggxy/Uploads/community/pic/2018-11-07/5be24d5a249b8.jpg","name":"1111","location_type":"2","location_link":"http://www.guoguoxueyuan.com/ggxy/small.php/communityWeb/id/8"}]}
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
             * info : {"cover":"http://www.guoguoxueyuan.com/ggxy/Uploads/community/pic/2018-11-07/5be24d5a67997.jpg","name":"1111","location_type":"2","location_link":"http://www.guoguoxueyuan.com/ggxy/small.php/communityWeb/id/8"}
             * list : [{"cover":"http://www.guoguoxueyuan.com/ggxy/Uploads/community/pic/2018-11-07/5be24dba4fb1b.jpg","name":"测试水水水水","location_type":"2","location_link":"http://www.guoguoxueyuan.com/ggxy/small.php/communityWeb/id/9"},{"cover":"http://www.guoguoxueyuan.com/ggxy/Uploads/community/pic/2018-11-07/5be24d5a249b8.jpg","name":"1111","location_type":"2","location_link":"http://www.guoguoxueyuan.com/ggxy/small.php/communityWeb/id/8"}]
             */

            private InfoBean info;
            private List<ListBean> list;

            public InfoBean getInfo() {
                return info;
            }

            public void setInfo(InfoBean info) {
                this.info = info;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public class InfoBean {
                /**
                 * cover : http://www.guoguoxueyuan.com/ggxy/Uploads/community/pic/2018-11-07/5be24d5a67997.jpg
                 * name : 1111
                 * location_type : 2
                 * location_link : http://www.guoguoxueyuan.com/ggxy/small.php/communityWeb/id/8
                 */

                private String cover;
                private String name;
                private String location_type;
                private String location_link;

                public String getCover() {
                    return cover;
                }

                public void setCover(String cover) {
                    this.cover = cover;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
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

            public class ListBean {
                /**
                 * cover : http://www.guoguoxueyuan.com/ggxy/Uploads/community/pic/2018-11-07/5be24dba4fb1b.jpg
                 * name : 测试水水水水
                 * location_type : 2
                 * location_link : http://www.guoguoxueyuan.com/ggxy/small.php/communityWeb/id/9
                 */

                private String cover;
                private String name;
                private String location_type;
                private String location_link;

                public String getCover() {
                    return cover;
                }

                public void setCover(String cover) {
                    this.cover = cover;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
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
}
