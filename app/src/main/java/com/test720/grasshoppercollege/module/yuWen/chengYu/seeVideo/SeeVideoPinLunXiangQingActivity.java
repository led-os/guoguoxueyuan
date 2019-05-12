package com.test720.grasshoppercollege.module.yuWen.chengYu.seeVideo;

import android.content.Intent;

import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.PinLunXiangQingActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;

import okhttp3.FormBody;

public class SeeVideoPinLunXiangQingActivity extends PinLunXiangQingActivity {


    @Override
    public void dianZan() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("look_id", getIntent().getStringExtra("look_id"));
        builder.add("uid", MyApplication.getmInstance().getUid());
        builder.add("comment_id", getIntent().getStringExtra("comment_id"));
        Post(HttpUntil.GetIntent().ChineseIdiomthumbUp(), builder, 5);
    }

    @Override
    public String url() {
        return HttpUntil.GetIntent().ChineseIdiomcommentInfo();
    }

    @Override
    public void pingLun() {
        Intent in = new Intent(mcontext, SeeVideoHuiFuActivity.class);
        in.putExtra("comment_id", getIntent().getStringExtra("comment_id"));
        in.putExtra("name", getIntent().getStringExtra("name"));
        in.putExtra("look_id", getIntent().getStringExtra("look_id"));
        jumpToActivity(in, false);
    }
}