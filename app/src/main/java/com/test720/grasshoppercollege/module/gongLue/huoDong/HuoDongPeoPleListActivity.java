package com.test720.grasshoppercollege.module.gongLue.huoDong;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseTwoRecyclerViewActivity;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.List;

import butterknife.BindView;
import okhttp3.FormBody;

public class HuoDongPeoPleListActivity extends BaseTwoRecyclerViewActivity<HuoDongPeoPleListActivity.PeoPleData.DataBean> {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    @Override
    public ReflshInit reflshInit() {
        return new ReflshInit() {
            @Override
            public String reTitle() {
                return "活动参与人";
            }

            @Override
            public FormBody.Builder reFormBuilder() {
                FormBody.Builder builder = new FormBody.Builder();
                builder.add("p", page + "");
                builder.add("activity_id", getIntent().getStringExtra("activity_id"));
                return builder;
            }

            @Override
            public String reUrl() {
                return HttpUntil.GetIntent().getIP() + "small.php/Strategy/getActivityPersonnel";
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
            PeoPleData data = new Gson().fromJson(str, PeoPleData.class);
            getList().clear();
            getList().addAll(data.getData());
            getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            PeoPleData data = new Gson().fromJson(str, PeoPleData.class);
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
        setAdapter(new MyBaseRecyclerAdapter<PeoPleData.DataBean>(getList(), mcontext, R.layout.can_yu_ren_activity) {
            @Override
            public void convert(BaseRecyclerHolder holder, PeoPleData.DataBean item, int postion) {
                holder.setText(R.id.name, item.getNickname());
                holder.setHeaderByUrl(R.id.pic, item.getHeader());
                ImageView vip = holder.getView(R.id.vip);
                if (item.getVip().equals("1")) {
                    vip.setImageResource(R.mipmap.vip);
                } else {
                    vip.setImageResource(R.mipmap.novip);
                }
            }
        });
        recyclerView.setAdapter(getAdapter());
    }

    class PeoPleData {


        /**
         * code : 1
         * msg : 成功
         * data : [{"header":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg","nickname":"123"},{"header":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg","nickname":"456"},{"header":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg","nickname":"111"},{"header":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg","nickname":"222"},{"header":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg","nickname":"333"},{"header":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg","nickname":"444"},{"header":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg","nickname":"555"},{"header":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg","nickname":"666"},{"header":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg","nickname":"7"},{"header":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg","nickname":"888"},{"header":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg","nickname":"999"}]
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
             * header : https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg
             * nickname : 123
             */

            private String header;
            private String nickname;
            private String vip;

            public String getVip() {
                return vip;
            }

            public void setVip(String vip) {
                this.vip = vip;
            }

            public String getHeader() {
                return header;
            }

            public void setHeader(String header) {
                this.header = header;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }
        }
    }

}
