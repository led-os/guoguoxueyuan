package com.test720.grasshoppercollege.module.youEr.baoBaoTingTing;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseTwoRecyclerViewActivity;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;

public class SearchZhuanJiActivity extends BaseTwoRecyclerViewActivity<SearchZhuanJiActivity.Data.DataBean> {


    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.edittext)
    EditText edittext;
    @BindView(R.id.go)
    ImageView go;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipe_refresh;

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_search_zhuan_ji;
    }

    @Override
    public void init() {
        super.init();
        if (getIntent().getStringExtra("key") != null) {
            edittext.setText(getIntent().getStringExtra("key"));
        }
    }

    @Override
    protected void initData() {
        setAdapter(new MyBaseRecyclerAdapter<Data.DataBean>(getList(), mcontext, R.layout.zhuan_ji_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, Data.DataBean item, int postion) {
                holder.setText(R.id.title, item.getTitle());
                holder.setText(R.id.content, item.getSmall_title());
                holder.setText(R.id.pice, item.getPrice());
                holder.setText(R.id.beiZhu, item.getCount() + "个故事");
                holder.setImageByUrl(R.id.img, item.getCover());
            }
        });
        getAdapter().setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(mcontext, ZhuanJiGuShiListActivity.class);
                intent.putExtra("album_id", getList().get(position).getAlbum_id());
                intent.putExtra("title", getList().get(position).getTitle());
                jumpToActivity(intent, false);
            }
        });
        recyclerView.setAdapter(getAdapter());
    }

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
                if (!TextUtils.isEmpty(edittext.getText())) {
                    builder.add("key", edittext.getText().toString());
                }
                builder.add("p", page + "");
                return builder;
            }

            @Override
            public String reUrl() {
                return HttpUntil.GetIntent().getIP() + "small.php/ChildBabyListening/searchAlbum";
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
                return swipe_refresh;
            }
        };
    }

    @Override
    public void shuaXin(String str) {
        Data data = new Gson().fromJson(str, Data.class);
        getList().clear();
        getList().addAll(data.getData());
        getAdapter().notifyDataSetChanged();
    }

    @Override
    public void gengDuo(String str) {
        Data data = new Gson().fromJson(str, Data.class);
        getList().addAll(data.getData());
        getAdapter().notifyDataSetChanged();
    }


    @OnClick({R.id.back, R.id.go})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.go:
                ShuaXin();
                break;
        }
    }

    class Data {

        /**
         * code : 1
         * msg : 成功
         * data : [{"album_id":"1","title":"凯叔 · 三国演义","small_title":"听英雄故事，长少年志气","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/beishidaban+g3+x+en/Unit1/Lesson1/g3+beishidaban+x+u1+2.jpg","count":"10","price":"120.00"}]
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
             * album_id : 1
             * title : 凯叔 · 三国演义
             * small_title : 听英雄故事，长少年志气
             * cover : https://www.hzggedu.com/ggxydemo/Uploads/reading/beishidaban+g3+x+en/Unit1/Lesson1/g3+beishidaban+x+u1+2.jpg
             * count : 10
             * price : 120.00
             */

            private String album_id;
            private String title;
            private String small_title;
            private String cover;
            private String count;
            private String price;

            public String getAlbum_id() {
                return album_id;
            }

            public void setAlbum_id(String album_id) {
                this.album_id = album_id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
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

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }
        }
    }
}
