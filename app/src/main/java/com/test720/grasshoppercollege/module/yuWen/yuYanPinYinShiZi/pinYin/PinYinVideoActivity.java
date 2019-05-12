package com.test720.grasshoppercollege.module.yuWen.yuYanPinYinShiZi.pinYin;

import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.module.yuWen.yuYanPinYinShiZi.BaseYuYanPinYinShiZIVideo;
import com.test720.grasshoppercollege.untils.HttpUntil;

import okhttp3.FormBody;

public class PinYinVideoActivity extends BaseYuYanPinYinShiZIVideo {


    @Override
    public void AddGuoGuoDou(String type) {
        if (points == null || share_points == null) return;
        if (type.equals("1")) {
            FormBody.Builder builder = new FormBody.Builder();
            builder.add("type", "5");
            builder.add("video_id", getIntent().getStringExtra("video_id"));
            builder.add("uid", MyApplication.getmInstance().getUid());
            builder.add("points", points);
            Post(HttpUntil.GetIntent().addReadingScore(), builder, 6, false);
        } else if (type.equals("2")) {
            FormBody.Builder builder = new FormBody.Builder();
            builder.add("type", "5");
            builder.add("video_id", getIntent().getStringExtra("video_id"));
            builder.add("uid", MyApplication.getmInstance().getUid());
            builder.add("points", share_points);
            Post(HttpUntil.GetIntent().addShareScore(), builder, 7, false);
        }
    }

    @Override
    public int vodeoType() {
        return 5;
    }

    @Override
    public String vodeoId() {
        return getIntent().getStringExtra("video_id");
    }
}