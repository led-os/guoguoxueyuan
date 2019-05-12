package com.test720.grasshoppercollege.module.gongLue.jiaZhang.childrenEdu;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.test720.grasshoppercollege.HttpBean.PingLunData;
import com.test720.grasshoppercollege.HttpBean.YuWenYuYanData;
import com.test720.grasshoppercollege.Mp4Activity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.gongLue.jiaZhang.bean.EduData;
import com.test720.grasshoppercollege.untils.HttpUntil;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import okhttp3.FormBody;

public class EduVideoActivity extends Mp4Activity {
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        noShare = true;
        super.onCreate(savedInstanceState);
    }

    @Override
    public String url() {
        return HttpUntil.GetIntent().StrategyjiaozInfo();
    }

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            EduData data = new Gson().fromJson(str, EduData.class);
            zanQB.bindTarget(one).setBadgeNumber(Integer.parseInt(data.getData().getThumb_up_count())).setBadgeTextSize(7, true);
//            new QBadgeView(this).bindTarget(two).setBadgeNumber(Integer.parseInt(data.getData().getShare_count())).setBadgeTextSize(7, true);
            pinLunQB.bindTarget(three).setBadgeNumber(Integer.parseInt(data.getData().getComment_count())).setBadgeTextSize(7, true);

            /*得到果果豆*/
//            share_points = data.getData().getShare_points();
//            points = data.getData().getPoints();
//            is_reading = data.getData().getIs_reading();//是否得过积分
//            is_share = data.getData().getIs_share();//是否分享过
            shareTitle = data.getData().getTitle();//分享标题
            shareBody = data.getData().getContent();//分享内容

            /*点赞状态*/
            if (data.getData().getIs_thumb_up() == 1) {
                dianZan.setImageResource(R.mipmap.zan);
            } else {
                dianZan.setImageResource(R.mipmap.meizan);
            }

            list.clear();
            list.addAll(data.getData().getComment());
            adapter.notifyDataSetChanged();

            if (url == null) {
                url = data.getData().getPath();
                jcVideo.setUp(url, JCVideoPlayer.SCREEN_LAYOUT_NORMAL, data.getData().getTitle());
                jcVideo.startButton.performClick();
                Glide.with(mcontext).load(data.getData().getPic()).into(jcVideo.thumbImageView);
            }
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            YuWenYuYanData data = new Gson().fromJson(str, YuWenYuYanData.class);
            list.addAll(data.getData().getComment());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public FormBody.Builder formBuilder() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("jiaozi_id", getIntent().getStringExtra("id"));
        builder.add("p", page + "");
        builder.add("uid", MyApplication.getmInstance().getUid() + "");
        return builder;
    }

    @Override
    public void dianZan(String comment_id) {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("uid", MyApplication.getmInstance().getUid());
        builder.add("comment_id", comment_id);
        builder.add("jiaozi_id", getIntent().getStringExtra("id"));
        Post(HttpUntil.GetIntent().StrategyparentJiaoZiThumbUp(), builder, 5);
    }

    @Override
    public void huiFu(String comment_id, String name) {
        Intent in = new Intent(mcontext, EduPinLunHuiFuActivity.class);
        in.putExtra("comment_id", comment_id);
        in.putExtra("name", name);
        in.putExtra("jiaozi_id", getIntent().getStringExtra("id"));
        jumpToActivity(in, false);
    }

    @Override
    public void pingLunXiangQing(PingLunData item) {
        Intent in = new Intent(mcontext, EduPinLunXiangQingActivity.class);
        in.putExtra("comment_id", item.getComment_id());
        in.putExtra("name", item.getNickname());
        in.putExtra("jiaozi_id", getIntent().getStringExtra("id"));
        jumpToActivity(in, false);
    }

    @Override
    public void PingJia() {
        Intent in = new Intent(mcontext, EduPingJiaActivity.class);
        in.putExtra("comment_id", "0");
        in.putExtra("jiaozi_id", getIntent().getStringExtra("id"));
        jumpToActivity(in, false);
    }

    @Override
    public int vodeoType() {
        return 62;
    }

    @Override
    public String vodeoId() {
        return getIntent().getStringExtra("id");
    }

    @Override
    protected void initData() {

    }
}