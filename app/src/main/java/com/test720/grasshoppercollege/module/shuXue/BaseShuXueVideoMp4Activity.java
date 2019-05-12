package com.test720.grasshoppercollege.module.shuXue;

import android.content.Intent;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.HttpBean.PingLunData;
import com.test720.grasshoppercollege.HttpBean.ShuXueNeiRongData;
import com.test720.grasshoppercollege.Mp4Activity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.HttpUntil;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import okhttp3.FormBody;

public abstract class BaseShuXueVideoMp4Activity extends Mp4Activity {
    String url;


    @Override
    public String url() {
        return HttpUntil.GetIntent().MathAnimationcontent();
    }

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            ShuXueNeiRongData data = new Gson().fromJson(str, ShuXueNeiRongData.class);
            /*点赞，分享，评论*/
            zanQB.bindTarget(one).setBadgeNumber(Integer.parseInt(data.getData().getContent().getThumb_up_count())).setBadgeTextSize(7, true);
            zhuanQB.bindTarget(two).setBadgeNumber(Integer.parseInt(data.getData().getContent().getShare_count())).setBadgeTextSize(7, true);
            pinLunQB.bindTarget(three).setBadgeNumber(Integer.parseInt(data.getData().getContent().getComment_count())).setBadgeTextSize(7, true);

            /*得到果果豆*/
            share_points = data.getData().getShare_points();
            points = data.getData().getPoints();

            is_reading = data.getData().getIs_reading();//是否得过积分
            is_share = data.getData().getIs_share();//是否分享过
            shareTitle = data.getData().getTitle();//分享标题
            shareBody = data.getData().getBody();//分享内容

            /*点赞状态*/
            if (data.getData().getContent().getIs_thumb_up().equals("1")) {
                dianZan.setImageResource(R.mipmap.zan);
            } else {
                dianZan.setImageResource(R.mipmap.meizan);
            }

            list.clear();
            list.addAll(data.getData().getComment());
            adapter.notifyDataSetChanged();
            if (url == null) {
                url = data.getData().getContent().getVideo_path();
                if (data.getData().getContent().getState().equals("2")) {//是h5的话就打开网页
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
            ShuXueNeiRongData data = new Gson().fromJson(str, ShuXueNeiRongData.class);
            list.addAll(data.getData().getComment());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public FormBody.Builder formBuilder() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("math_id", getIntent().getStringExtra("math_id"));
        builder.add("uid", MyApplication.getmInstance().getUid());
        builder.add("p", page + "");
        return builder;
    }


    @Override
    public void dianZan(String comment_id) {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("math_id", getIntent().getStringExtra("math_id"));
        builder.add("uid", MyApplication.getmInstance().getUid());
        builder.add("comment_id", comment_id);
        Post(HttpUntil.GetIntent().MathAnimationthumbUp(), builder, 5, false);
    }

    @Override
    public void huiFu(String comment_id, String name) {
        Intent in = new Intent(mcontext, ShuXuePingLunHuiFuActivity.class);
        in.putExtra("comment_id", comment_id);
        in.putExtra("name", name);
        in.putExtra("math_id", getIntent().getStringExtra("math_id"));
        jumpToActivity(in, false);
    }

    @Override
    public void pingLunXiangQing(PingLunData item) {
        Intent in = new Intent(mcontext, ShuXuePingLunXiangQingActivity.class);
        in.putExtra("comment_id", item.getComment_id());
        in.putExtra("name", item.getNickname());
        in.putExtra("math_id", getIntent().getStringExtra("math_id"));
        jumpToActivity(in, false);
    }


}