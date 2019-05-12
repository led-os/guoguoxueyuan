package com.test720.grasshoppercollege.module.shuXue.aoShu;

import android.content.Intent;

import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.module.shuXue.BaseShuXueVideoMp4Activity;
import com.test720.grasshoppercollege.module.shuXue.ShuXuePingJIaActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;

import okhttp3.FormBody;

public class AoShuVideoActivity extends BaseShuXueVideoMp4Activity {
    @Override
    protected void initData() {
        if (getIntent().getStringExtra("title") != null) {
            title.setText(getIntent().getStringExtra("title"));
        } else {
            title.setText("奥数动画");
        }
    }

    @Override
    public void PingJia() {
        Intent in = new Intent(AoShuVideoActivity.this, ShuXuePingJIaActivity.class);
        in.putExtra("math_id", getIntent().getStringExtra("math_id"));
        jumpToActivity(in, false);
    }

    @Override
    public void AddGuoGuoDou(String type) {
        super.AddGuoGuoDou(type);
        if (points == null || share_points == null) return;
        if (type.equals("1")) {
            FormBody.Builder builder = new FormBody.Builder();
            builder.add("type", "1");
            builder.add("uid", MyApplication.getmInstance().getUid());
            builder.add("points", points);
            Post(HttpUntil.GetIntent().addReadingScore(), builder, 6, false);
        } else if (type.equals("2")) {
            FormBody.Builder builder = new FormBody.Builder();
            builder.add("type", "1");
            builder.add("uid", MyApplication.getmInstance().getUid());
            builder.add("points", share_points);
            Post(HttpUntil.GetIntent().addShareScore(), builder, 7, false);
        }
    }


    @Override
    public int vodeoType() {
        return 2;
    }

    @Override
    public String vodeoId() {
        return getIntent().getStringExtra("math_id");
    }
}
