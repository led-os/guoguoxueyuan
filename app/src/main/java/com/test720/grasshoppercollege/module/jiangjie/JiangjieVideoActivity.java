package com.test720.grasshoppercollege.module.jiangjie;

import android.content.Intent;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.HttpBean.JiangJieVideoData;
import com.test720.grasshoppercollege.HttpBean.PingLunData;
import com.test720.grasshoppercollege.Mp4Activity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.HttpUntil;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import okhttp3.FormBody;

public class JiangjieVideoActivity extends Mp4Activity {
    String url;

    @Override
    public String url() {
        return HttpUntil.GetIntent().EnExplainbookContent();
    }

    @Override
    public FormBody.Builder formBuilder() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("book_id", getIntent().getStringExtra("book_id"));
        builder.add("unit", getIntent().getStringExtra("unit"));
        builder.add("uid", MyApplication.getmInstance().getUid());
        builder.add("page", getIntent().getStringExtra("page"));
        builder.add("p", page + "");
        return builder;
    }

    @Override
    protected void initData() {
        if (getIntent().getStringExtra("unit") != null) {
            String n = getIntent().getStringExtra("unit") + getIntent().getStringExtra("page");
            title.setText(n);
        }

    }

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            JiangJieVideoData data = new Gson().fromJson(str, JiangJieVideoData.class);
            /*点赞，分享，评论*/
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
            JiangJieVideoData data = new Gson().fromJson(str, JiangJieVideoData.class);
            list.addAll(data.getData().getComment());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        super.GetHttpBackStr(str, what);
        if (what == 5) {//点赞返回
            codeIsOne(str);
        }
    }

    @Override
    public void PingJia() {
        Intent in = new Intent(JiangjieVideoActivity.this, JiangJiePingJiaActivity.class);
        in.putExtra("book_id", getIntent().getStringExtra("book_id"));
        in.putExtra("unit", getIntent().getStringExtra("unit"));
        in.putExtra("page", getIntent().getStringExtra("page"));
        jumpToActivity(in, false);
    }


    @Override
    public void dianZan(String commentId) {
        if (commentId == null) commentId = "0";
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("book_id", getIntent().getStringExtra("book_id"));
        builder.add("uid", MyApplication.getmInstance().getUid());
        builder.add("comment_id", commentId);
        builder.add("unit", getIntent().getStringExtra("unit"));
        builder.add("page", getIntent().getStringExtra("page"));
        Post(HttpUntil.GetIntent().EnExplainthumbUp(), builder, 5);
    }

    @Override
    public void huiFu(String comment_id, String name) {
        Intent in = new Intent(JiangjieVideoActivity.this, JiangJieHuiFuActivity.class);
        in.putExtra("comment_id", comment_id);
        in.putExtra("name", name);
        in.putExtra("book_id", getIntent().getStringExtra("book_id"));
        in.putExtra("unit", getIntent().getStringExtra("unit"));
        in.putExtra("page", getIntent().getStringExtra("page"));
        jumpToActivity(in, false);
    }

    @Override
    public void pingLunXiangQing(PingLunData item) {
        Intent in = new Intent(mcontext, JiangJiePinJiaXiangQingActivity.class);
        in.putExtra("comment_id", item.getComment_id());
        in.putExtra("name", item.getNickname());
        in.putExtra("book_id", getIntent().getStringExtra("book_id"));
        in.putExtra("page", getIntent().getStringExtra("page"));
        in.putExtra("unit", getIntent().getStringExtra("unit"));
        jumpToActivity(in, false);
    }

    @Override
    public String bookId() {
        return getIntent().getStringExtra("book_id");
    }

    @Override
    public String unitId() {
        return getIntent().getStringExtra("unit");
    }

    @Override
    public String vodeoId() {
        return getIntent().getStringExtra("page");
    }

    @Override
    public int vodeoType() {
        return 11;
    }


}
