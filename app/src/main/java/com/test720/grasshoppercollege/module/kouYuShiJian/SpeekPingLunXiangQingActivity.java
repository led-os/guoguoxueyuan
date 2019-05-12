package com.test720.grasshoppercollege.module.kouYuShiJian;

import android.content.Intent;

import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.PinLunXiangQingActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;

import okhttp3.FormBody;

public class SpeekPingLunXiangQingActivity extends PinLunXiangQingActivity {

    @Override
    public void dianZan() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("oral_id", getIntent().getStringExtra("oral_id"));
        builder.add("uid", MyApplication.getmInstance().getUid());
        builder.add("comment_id", getIntent().getStringExtra("comment_id"));
        Post(HttpUntil.GetIntent().thumbUp(), builder, 5);
    }

    @Override
    public void pingLun() {
        Intent in = new Intent(mcontext, SpeekHuiFuActivity.class);
        in.putExtra("comment_id", getIntent().getStringExtra("comment_id"));
        in.putExtra("name", getIntent().getStringExtra("name"));
        in.putExtra("oral_id", getIntent().getStringExtra("oral_id"));
        jumpToActivity(in, false);
    }

    @Override
    public String url() {
        return HttpUntil.GetIntent().getIP() + "small.php/EnOral/commentInfo";
    }
}
