package com.test720.grasshoppercollege.module.gongLue.huoDong;

import android.content.Intent;

import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.untils.HttpUntil;

import okhttp3.FormBody;

public class PinLunXiangQingActivity extends com.test720.grasshoppercollege.PinLunXiangQingActivity {

    @Override
    public void setSys() {
        super.setSys();
        noZhuan = true;
    }

    @Override
    public void dianZan() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("activity_id", getIntent().getStringExtra("activity_id"));
        builder.add("number", getIntent().getStringExtra("number"));
        builder.add("uid", MyApplication.getmInstance().getUid());
        builder.add("comment_id", getIntent().getStringExtra("comment_id"));
        Post(HttpUntil.GetIntent().StrategyactivityThumbUp(), builder, 5);
    }

    @Override
    public String url() {
        return HttpUntil.GetIntent().StrategyactivityCommentInfo();
    }

    @Override
    public void pingLun() {
        Intent in = new Intent(mcontext, HuiFuActivity.class);
        in.putExtra("comment_id", getIntent().getStringExtra("comment_id"));
        in.putExtra("name", getIntent().getStringExtra("name"));
        in.putExtra("activity_id", getIntent().getStringExtra("activity_id"));
        in.putExtra("number", getIntent().getStringExtra("number"));
        jumpToActivity(in, false);
    }
}