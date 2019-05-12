package com.test720.grasshoppercollege.module.youEr.shiZiDongHua;

import android.content.Intent;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.HttpBean.PingLunData;
import com.test720.grasshoppercollege.HttpBean.YinMeiWenHuaVideoData;
import com.test720.grasshoppercollege.HttpBean.YuWenYuYanData;
import com.test720.grasshoppercollege.Mp4Activity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.LogUtil;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import okhttp3.FormBody;

public class ShiZiDongHuaVideoActivity extends Mp4Activity {
    String url;

    @Override
    public String url() {
        return HttpUntil.GetIntent().ChildAnimationcontent();
    }

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            YinMeiWenHuaVideoData data = new Gson().fromJson(str, YinMeiWenHuaVideoData.class);

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
                LogUtil.logError("url=" + url);
                if (data.getData().getContent().getState().equals("2")) {//是h5的话就打开网页
                    initWeb(url);
                } else {
                    jcVideo.setUp(url, JCVideoPlayer.SCREEN_LAYOUT_NORMAL, "");
                    jcVideo.startButton.performClick();
                }
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
        builder.add("child_id", getIntent().getStringExtra("child_id"));
        builder.add("uid", MyApplication.getmInstance().getUid());
        builder.add("p", page + "");
        return builder;
    }

    @Override
    public void dianZan(String comment_id) {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("child_id", getIntent().getStringExtra("child_id"));
        builder.add("uid", MyApplication.getmInstance().getUid());
        builder.add("comment_id", comment_id);
        Post(HttpUntil.GetIntent().ChildAnimationthumbUp(), builder, 5);
    }

    @Override
    public void huiFu(String comment_id, String name) {
        Intent in = new Intent(mcontext, HuiFuActivity.class);
        in.putExtra("comment_id", comment_id);
        in.putExtra("name", name);
        in.putExtra("child_id", getIntent().getStringExtra("child_id"));
        jumpToActivity(in, false);
    }

    @Override
    public void pingLunXiangQing(PingLunData item) {
        Intent in = new Intent(mcontext, XiangQingActivity.class);
        in.putExtra("comment_id", item.getComment_id());
        in.putExtra("name", item.getNickname());
        in.putExtra("child_id", getIntent().getStringExtra("child_id"));
        jumpToActivity(in, false);
    }

    @Override
    public void PingJia() {
        Intent in = new Intent(mcontext, PingLunActivity.class);
        in.putExtra("comment_id", "0");
        in.putExtra("child_id", getIntent().getStringExtra("child_id"));
        jumpToActivity(in, false);
    }

    @Override
    protected void initData() {
        if (getIntent().getStringExtra("title") != null) {
            title.setText(getIntent().getStringExtra("title"));
        } else {
            title.setText("识字动画");
        }
    }


    @Override
    public int vodeoType() {
        return 35;
    }

    @Override
    public String vodeoId() {
        return getIntent().getStringExtra("child_id");
    }
}