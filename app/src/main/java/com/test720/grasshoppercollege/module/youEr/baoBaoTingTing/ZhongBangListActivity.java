package com.test720.grasshoppercollege.module.youEr.baoBaoTingTing;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseTwoRecyclerViewActivity;
import com.test720.grasshoppercollege.BuyClassActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.QuanXianUntil;
import com.test720.grasshoppercollege.untils.musicMedia.MusicServiceData;

import java.util.List;

import butterknife.BindView;
import okhttp3.FormBody;

public class ZhongBangListActivity extends BaseTwoRecyclerViewActivity<ZhongBangListActivity.ZhongBangData.DataBean> {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    @Override
    public ReflshInit reflshInit() {
        return new ReflshInit() {
            @Override
            public String reTitle() {
                return "重磅精品";
            }

            @Override
            public FormBody.Builder reFormBuilder() {
                FormBody.Builder builder = new FormBody.Builder();
                builder.add("p", page + "");
                builder.add("uid", MyApplication.getmInstance().getUid());
                return builder;
            }

            @Override
            public String reUrl() {
                return HttpUntil.GetIntent().getIP() + "small.php/ChildBabyListening/getBestContent";
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
        if (codeIsOne(str, false)) {
            ZhongBangData data = new Gson().fromJson(str, ZhongBangData.class);
            getList().clear();
            getList().addAll(data.getData());
            getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            ZhongBangData data = new Gson().fromJson(str, ZhongBangData.class);
            getList().addAll(data.getData());
            getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.base_shua_xin;
    }

    @Override
    protected void initData() {
        setAdapter(new MyBaseRecyclerAdapter<ZhongBangData.DataBean>(getList(), mcontext, R.layout.baobao_ting_shou_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, ZhongBangData.DataBean item, int postion) {
                holder.setImageByUrl(R.id.img, item.getCover());
                holder.setText(R.id.title, item.getTitle_t());
                if (item.getOpen_up() == 1) {
                    holder.getView(R.id.suo).setVisibility(View.GONE);
                } else {
                    holder.getView(R.id.suo).setVisibility(View.VISIBLE);
                }
            }
        });
        getAdapter().setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                zhongBangItem(getList().get(position));
            }
        });
        recyclerView.setAdapter(getAdapter());
    }

    /**
     * 重磅,点击直接播放
     */
    private void zhongBangItem(final ZhongBangData.DataBean item) {
        String[] strs = new String[3];
        strs[0] = item.getTitle();
        strs[1] = item.getContent();
        strs[2] = HttpUntil.GetIntent().getShare() + "71";
        boolean can = QuanXianUntil.GetIntent(mcontext).isCanSee(item.getPermissions(), item.isPay(), item.getOpen_up() + "", item.getPoints()
                , strs, new QuanXianUntil.QuanXianClick() {
                    @Override
                    public void QuanbuyCurr() {
                        Intent intent = new Intent(mcontext, BuyClassActivity.class);
                        intent.putExtra("curr_type", getIntent().getIntExtra("type", 61));
                        intent.putExtra("type", "4");
                        jumpToActivity(intent, false);
                    }

                    @Override
                    public void QuanbuyGuoGuo() {
                        if (item.getCon_id() == null) return;
                        FormBody.Builder formBuilder = new FormBody.Builder();
                        formBuilder.add("uid", MyApplication.getmInstance().getUid());//请求参数一
                        formBuilder.add("curr_type", "61");
                        formBuilder.add("type", "2");
                        formBuilder.add("points", item.getPoints());
                        formBuilder.add("re_id", item.getCon_id());
                        Post(HttpUntil.GetIntent().addShareSpendRecord(), formBuilder, 123, false);
                    }

                    @Override
                    public void QuanshareCurr() {
                        if (item.getPoints() == null) return;
                        FormBody.Builder formBuilder = new FormBody.Builder();
                        formBuilder.add("uid", MyApplication.getmInstance().getUid());//请求参数一
                        formBuilder.add("curr_type", "61");
                        formBuilder.add("type", "1");
                        formBuilder.add("re_id", item.getCon_id());
                        Post(HttpUntil.GetIntent().addShareSpendRecord(), formBuilder, 123, false);
                    }
                });
        if (can) {
            Intent intent = new Intent(mcontext, MusicActivity.class);
            MusicServiceData.getInstance().setId(item.getCon_id());
            intent.putExtra("updata", "updata");
            jumpToActivity(intent, false);
        }
    }

    class ZhongBangData {

        /**
         * code : 1
         * msg : 成功
         * data : [{"con_id":"1","album_id":"1","title_t":"三国01","small_title":"三国01表浅","cover":"http://www.guoguoxueyuan.com/ggxy/http://www.guoguoxueyuan.comUploads/story/pic/2018-10-31/5bd95ec7a2f58.jpg","permissions":"1","points":"0","pay":false,"open_up":1,"title":"宝宝听听标题","content":"宝宝听听内容"},{"con_id":"8","album_id":"2","title_t":"adsfasdfadfsadf","small_title":"sadfasdf","cover":"http://www.guoguoxueyuan.com/ggxy/http://www.guoguoxueyuan.comUploads/story/pic/2018-11-08/5be3a75588eaa.jpg","permissions":"2","points":"0","pay":false,"open_up":1,"title":"宝宝听听标题","content":"宝宝听听内容"}]
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
             * con_id : 1
             * album_id : 1
             * title_t : 三国01
             * small_title : 三国01表浅
             * cover : http://www.guoguoxueyuan.com/ggxy/http://www.guoguoxueyuan.comUploads/story/pic/2018-10-31/5bd95ec7a2f58.jpg
             * permissions : 1
             * points : 0
             * pay : false
             * open_up : 1
             * title : 宝宝听听标题
             * content : 宝宝听听内容
             */

            private String con_id;
            private String album_id;
            private String title_t;
            private String small_title;
            private String cover;
            private String permissions;
            private String points;
            private boolean pay;
            private int open_up;
            private String title;
            private String content;

            public String getCon_id() {
                return con_id;
            }

            public void setCon_id(String con_id) {
                this.con_id = con_id;
            }

            public String getAlbum_id() {
                return album_id;
            }

            public void setAlbum_id(String album_id) {
                this.album_id = album_id;
            }

            public String getTitle_t() {
                return title_t;
            }

            public void setTitle_t(String title_t) {
                this.title_t = title_t;
            }

            public String getSmall_title() {
                return small_title;
            }

            public void setSmall_title(String small_title) {
                this.small_title = small_title;
            }

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public String getPermissions() {
                return permissions;
            }

            public void setPermissions(String permissions) {
                this.permissions = permissions;
            }

            public String getPoints() {
                return points;
            }

            public void setPoints(String points) {
                this.points = points;
            }

            public boolean isPay() {
                return pay;
            }

            public void setPay(boolean pay) {
                this.pay = pay;
            }

            public int getOpen_up() {
                return open_up;
            }

            public void setOpen_up(int open_up) {
                this.open_up = open_up;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }
    }
}
