package com.test720.grasshoppercollege.module.youEr.yingWenHuiBen.zuoPin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.BaseTwoRecyclerViewActivity;
import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.HttpBean.PingLunData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.youEr.yingWenHuiBen.pinLun.HuibenPinJiaHuiFuActivity;
import com.test720.grasshoppercollege.module.youEr.yingWenHuiBen.pinLun.HuibenPinLunXiangQingActivity;
import com.test720.grasshoppercollege.module.youEr.yingWenHuiBen.pinLun.PingJiaHuiBenActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.glideUntil.GlideUntil;
import com.test720.grasshoppercollege.untils.pinLunUntil.PinLunUntil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.FormBody;

public class ZuoPingXiangQingActivity extends BaseTwoRecyclerViewActivity<PingLunData> {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    PinLunUntil pinLunUntil;

    ViewHolder viewHolder;
    @BindView(R.id.pinlunEditText)
    TextView pinlunEditText;
    @BindView(R.id.dianZan)
    ImageView dianZan;
    @BindView(R.id.one)
    View one;
    @BindView(R.id.zan)
    RelativeLayout zan;
    @BindView(R.id.two)
    View two;
    @BindView(R.id.zhuan)
    RelativeLayout zhuan;
    @BindView(R.id.three)
    View three;
    @BindView(R.id.pinlun)
    RelativeLayout pinlun;
    @BindView(R.id.down)
    LinearLayout down;
    @BindView(R.id.activity_mp3_and_view_pager)
    LinearLayout activityMp3AndViewPager;
    ZuoPingData data;

    @Override
    protected int setlayoutResID() {
        return R.layout.hui_ben_zuo_ping_xiang_qing_activity;
    }

    @Override
    protected void initData() {
        @SuppressLint("InflateParams") View head = LayoutInflater.from(mcontext).inflate(R.layout.zuo_ping_xiang_qing_head, null);
        viewHolder = new ViewHolder(head);
        viewHolder.bookImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data == null) return;
                Intent intent = new Intent(mcontext, ZuoPingBoFangActivity.class);
                intent.putExtra("my_id", getIntent().getStringExtra("my_id"));
                if (data.getData().getInfo().getStatus().equals("1")) {
                    intent.putExtra("isOrientation", true);
                } else {
                    intent.putExtra("isOrientation", false);
                }
                jumpToActivity(intent, false);
            }
        });
        viewHolder.pinlun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(mcontext, PingJiaHuiBenActivity.class);
                in.putExtra("my_id", getIntent().getStringExtra("my_id"));
                jumpToActivity(in, false);
            }
        });
        viewHolder.zan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dianZan();
            }
        });
        pinLunUntil = new PinLunUntil(getList(), mcontext, new PinLunUntil.PinLunInterface() {
            @Override
            public void Zan(String commentId, int pos) {
                FormBody.Builder builder = new FormBody.Builder();
                builder.add("my_id", getIntent().getStringExtra("my_id"));
                builder.add("uid", MyApplication.getmInstance().getUid());
                builder.add("comment_id", commentId);
                Post(HttpUntil.GetIntent().getIP() + "small.php/ChildBook/thumbUp", builder, 5);
            }

            @Override
            public void Reply(String commentId, String name) {
                Intent in = new Intent(mcontext, HuibenPinJiaHuiFuActivity.class);
                in.putExtra("comment_id", commentId);
                in.putExtra("name", name);
                in.putExtra("my_id", getIntent().getStringExtra("my_id"));
                jumpToActivity(in, false);
            }

            @Override
            public void ItemClick(PingLunData item) {
                Intent in = new Intent(mcontext, HuibenPinLunXiangQingActivity.class);
                in.putExtra("comment_id", item.getComment_id());
                in.putExtra("name", item.getNickname());
                in.putExtra("my_id", getIntent().getStringExtra("my_id"));
                jumpToActivity(in, false);
            }
        });
        setAdapter(pinLunUntil.getAdapter());
        getAdapter().setHeaderView(head);
        recyclerView.setAdapter(getAdapter());

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        DismissDialong();
    }

    @Override
    public void onEvent(CurrencyEvent currencyEvent) {
        super.onEvent(currencyEvent);
        if (currencyEvent.getWhat() == CurrencyEvent.FABUCHENGGONG && currencyEvent.getMsg().equals("发布成功")) {
            ShuaXin();
        }
        if (currencyEvent.getWhat() == CurrencyEvent.PINGLUNOK) {
            ShuaXin();
        }
    }

    @Override
    public ReflshInit reflshInit() {
        return new ReflshInit() {
            @Override
            public String reTitle() {
                return "作品详情";
            }

            @Override
            public FormBody.Builder reFormBuilder() {
                FormBody.Builder builder = new FormBody.Builder();
                builder.add("my_id", getIntent().getStringExtra("my_id"));
                builder.add("uid", MyApplication.getmInstance().getUid());
                builder.add("p", page + "");
                return builder;
            }

            @Override
            public String reUrl() {
                return HttpUntil.GetIntent().getIP() + "small.php/ChildBook/worksInfo";
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
            data = new Gson().fromJson(str, ZuoPingData.class);
            getList().clear();
            getList().addAll(data.getData().getComment());
            getAdapter().notifyDataSetChanged();
            String zz = "作者：" + data.getData().getInfo().getNickname();
            if (data.getData().getInfo().getThumb_up().equals("1")) {
                viewHolder.zanImg.setImageResource(R.mipmap.zan);
            } else {
                viewHolder.zanImg.setImageResource(R.mipmap.meizan);
            }
            viewHolder.zuozhe.setText(zz);
            viewHolder.bofangNum.setText(data.getData().getInfo().getClick_count());
            viewHolder.zanMum.setText(data.getData().getInfo().getThumb_up_count());
            viewHolder.pinLunNun.setText(data.getData().getInfo().getComment_count());
            viewHolder.title.setText(data.getData().getInfo().getName());
            GlideUntil.getInstance().imgUrl(mcontext, viewHolder.bookImg, data.getData().getInfo().getPic());
        }
    }


    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            ZuoPingData data = new Gson().fromJson(str, ZuoPingData.class);
            getList().addAll(data.getData().getComment());
            getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        super.GetHttpBackStr(str, what);
        if (what == 5 && codeIsOne(str)) {
            ShuaXin();
        }
    }

    public void dianZan() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("my_id", getIntent().getStringExtra("my_id"));
        builder.add("uid", MyApplication.getmInstance().getUid());
        builder.add("comment_id", "0");
        Post(HttpUntil.GetIntent().getIP() + "small.php/ChildBook/thumbUp", builder, 5, false);
    }

    @OnClick({R.id.pinlunEditText, R.id.zan, R.id.pinlun})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zan:
                dianZan();
                break;
            case R.id.pinlunEditText:
            case R.id.pinlun:
                Intent in = new Intent(mcontext, PingJiaHuiBenActivity.class);
                in.putExtra("my_id", getIntent().getStringExtra("my_id"));
                jumpToActivity(in, false);
                break;
        }
    }

    class ZuoPingData {

        /**
         * code : 1
         * msg : 成功
         * data : {"info":{"my_id":"10","uid":"2799","pic":"http://www.guoguoxueyuan.com/ggxy/Uploads/child_book/book_21/ada.jpg","name":"h1","click_count":"0","thumb_up_count":"0","comment_count":"0","collection":"0","thumb_up":"0","nickname":"183****8595"},"comment":[]}
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
             * info : {"my_id":"10","uid":"2799","pic":"http://www.guoguoxueyuan.com/ggxy/Uploads/child_book/book_21/ada.jpg","name":"h1","click_count":"0","thumb_up_count":"0","comment_count":"0","collection":"0","thumb_up":"0","nickname":"183****8595"}
             * comment : []
             */

            private InfoBean info;
            private List<PingLunData> comment;

            public InfoBean getInfo() {
                return info;
            }

            public void setInfo(InfoBean info) {
                this.info = info;
            }

            public List<PingLunData> getComment() {
                return comment;
            }

            public void setComment(List<PingLunData> comment) {
                this.comment = comment;
            }

            public class InfoBean {
                /**
                 * my_id : 10
                 * uid : 2799
                 * pic : http://www.guoguoxueyuan.com/ggxy/Uploads/child_book/book_21/ada.jpg
                 * name : h1
                 * click_count : 0
                 * thumb_up_count : 0
                 * comment_count : 0
                 * collection : 0
                 * thumb_up : 0
                 * nickname : 183****8595
                 */

                private String my_id;
                private String uid;
                private String pic;
                private String name;
                private String click_count;
                private String thumb_up_count;
                private String comment_count;
                private String collection;
                private String thumb_up;
                private String nickname;
                private String status;//1横屏详情，2竖屏

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getMy_id() {
                    return my_id;
                }

                public void setMy_id(String my_id) {
                    this.my_id = my_id;
                }

                public String getUid() {
                    return uid;
                }

                public void setUid(String uid) {
                    this.uid = uid;
                }

                public String getPic() {
                    return pic;
                }

                public void setPic(String pic) {
                    this.pic = pic;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getClick_count() {
                    return click_count;
                }

                public void setClick_count(String click_count) {
                    this.click_count = click_count;
                }

                public String getThumb_up_count() {
                    return thumb_up_count;
                }

                public void setThumb_up_count(String thumb_up_count) {
                    this.thumb_up_count = thumb_up_count;
                }

                public String getComment_count() {
                    return comment_count;
                }

                public void setComment_count(String comment_count) {
                    this.comment_count = comment_count;
                }

                public String getCollection() {
                    return collection;
                }

                public void setCollection(String collection) {
                    this.collection = collection;
                }

                public String getThumb_up() {
                    return thumb_up;
                }

                public void setThumb_up(String thumb_up) {
                    this.thumb_up = thumb_up;
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

    static class ViewHolder {
        @BindView(R.id.bookImg)
        ImageView bookImg;
        @BindView(R.id.zanImg)
        ImageView zanImg;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.zuozhe)
        TextView zuozhe;
        @BindView(R.id.bofangNum)
        TextView bofangNum;
        @BindView(R.id.zanMum)
        TextView zanMum;
        @BindView(R.id.pinLunNun)
        TextView pinLunNun;
        @BindView(R.id.zan)
        LinearLayout zan;
        @BindView(R.id.pinlun)
        LinearLayout pinlun;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
