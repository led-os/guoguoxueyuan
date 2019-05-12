
package com.test720.grasshoppercollege.module.gongLue.student.smallClass.daGang;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.test720.grasshoppercollege.HttpBean.PingLunData;
import com.test720.grasshoppercollege.HttpBean.SpeekTimeVideoData;
import com.test720.grasshoppercollege.Mp4Activity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.SubjectActivity;
import com.test720.grasshoppercollege.module.gongLue.student.bean.SmallDaGangData;
import com.test720.grasshoppercollege.untils.HttpUntil;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import okhttp3.FormBody;

public class DaGangVideoActivity extends Mp4Activity {
    String url;
    ImageView zhuanPic;
    TextView zhuanJiTitle;
    TextView zhuanJiContent;

    SmallDaGangData data;

    @Override
    public String url() {
        return HttpUntil.GetIntent().StrategymicroClassInfo();
    }

    @Override
    public void recyclerViewInitView() {
        super.recyclerViewInitView();
        @SuppressLint("InflateParams") View head = LayoutInflater.from(mcontext).inflate(R.layout.da_gang_zhuan_ji_head, null);
        zhuanPic = head.findViewById(R.id.zhuanJiPic);
        zhuanJiTitle = head.findViewById(R.id.title);
        zhuanJiContent = head.findViewById(R.id.content);
        setHeadTwo(head);
        head.findViewById(R.id.lin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    public void setSys() {
        super.setSys();
        noShare = true;
        //添加顶部点击
        this.setRightTopListenter(new RightTopListenter() {
            @Override
            public int imgRouse() {
                return R.mipmap.sp_top_right;
            }

            @Override
            public void clickListener() {
                if (data != null) {
                    Intent intent = new Intent(mcontext, SubjectActivity.class);
                    intent.putExtra("type", getIntent().getStringExtra("name") + "练习");
                    intent.putExtra("title", data.getData().getContent().getName());
                    intent.putExtra("path", data.getData().getContent().getWeb_url());
                    jumpToActivity(intent, false);
                }
            }
        });
    }

    @Override
    public int vodeoType() {
        return 8;
    }

    @Override
    public String vodeoId() {
        return getIntent().getStringExtra("micro_class_id");
    }

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            data = new Gson().fromJson(str, SmallDaGangData.class);
            //大纲专辑信息
            Glide.with(mcontext).load(data.getData().getContent().getAlbum().getCover()).into(zhuanPic);
            zhuanJiTitle.setText(data.getData().getContent().getAlbum().getName());
            zhuanJiContent.setText(data.getData().getContent().getAlbum().getCount());

            /*点赞，分享，评论*/
            zanQB.bindTarget(one).setBadgeNumber(Integer.parseInt(data.getData().getContent().getThumb_up_count())).setBadgeTextSize(7, true);
            pinLunQB.bindTarget(three).setBadgeNumber(Integer.parseInt(data.getData().getContent().getComment_count())).setBadgeTextSize(7, true);
            shareTitle = data.getData().getTitle();//分享标题
            shareBody = data.getData().getBody();//分享内容

            /*点赞状态*/
            if (data.getData().getContent().getIs_thumb_up() == 1) {
                dianZan.setImageResource(R.mipmap.zan);
            } else {
                dianZan.setImageResource(R.mipmap.meizan);
            }

            list.clear();
            list.addAll(data.getData().getComment());
            adapter.notifyDataSetChanged();

            if (url == null) {
                url = data.getData().getContent().getPath();
                if (isWeb) {//是h5的话就打开网页
                    initWeb(url);
                } else {
                    jcVideo.setUp(url, JCVideoPlayer.SCREEN_LAYOUT_NORMAL, data.getData().getContent().getName());
                    jcVideo.startButton.performClick();
                }
            }
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            SpeekTimeVideoData data = new Gson().fromJson(str, SpeekTimeVideoData.class);
            list.addAll(data.getData().getComment());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public FormBody.Builder formBuilder() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("micro_class_id", getIntent().getStringExtra("micro_class_id"));
        builder.add("uid", MyApplication.getmInstance().getUid());
        builder.add("p", page + "");
        return builder;
    }

    @Override
    public void dianZan(String comment_id) {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("micro_class_id", getIntent().getStringExtra("micro_class_id"));
        builder.add("uid", MyApplication.getmInstance().getUid());
        builder.add("comment_id", comment_id);
        Post(HttpUntil.GetIntent().StrategymicroThumbUp(), builder, 5);
    }

    @Override
    public void huiFu(String comment_id, String name) {
        Intent in = new Intent(mcontext, HuiFuActivity.class);
        in.putExtra("comment_id", comment_id);
        in.putExtra("name", name);
        in.putExtra("micro_class_id", getIntent().getStringExtra("micro_class_id"));
        jumpToActivity(in, false);
    }

    @Override
    public void pingLunXiangQing(PingLunData item) {
        Intent in = new Intent(mcontext, PingLunXiangQingActivity.class);
        in.putExtra("comment_id", item.getComment_id());
        in.putExtra("name", item.getNickname());
        in.putExtra("micro_class_id", getIntent().getStringExtra("micro_class_id"));
        jumpToActivity(in, false);
    }

    @Override
    public void PingJia() {
        Intent in = new Intent(mcontext, PingJiaActivity.class);
        in.putExtra("comment_id", "0");
        in.putExtra("micro_class_id", getIntent().getStringExtra("micro_class_id"));
        jumpToActivity(in, false);
    }

    @Override
    protected void initData() {
        title.setText(getIntent().getStringExtra("name"));

    }


}
