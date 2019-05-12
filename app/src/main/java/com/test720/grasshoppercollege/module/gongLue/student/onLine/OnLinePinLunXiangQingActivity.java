package com.test720.grasshoppercollege.module.gongLue.student.onLine;

import android.content.Intent;

import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.PinLunXiangQingActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;

import okhttp3.FormBody;

public class OnLinePinLunXiangQingActivity extends PinLunXiangQingActivity {

    @Override
    public void setSys() {
        super.setSys();
        noZhuan=true;
    }
    @Override
    public void dianZan() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("line_id", getIntent().getStringExtra("line_id"));
        builder.add("uid", MyApplication.getmInstance().getUid());
        builder.add("comment_id", getIntent().getStringExtra("comment_id"));
        Post(HttpUntil.GetIntent().StrategylineThumbUp(), builder, 5);
    }

    @Override
    public String url() {
        return HttpUntil.GetIntent().StrategylineCommentInfo();
    }

    @Override
    public void pingLun() {
        Intent in = new Intent(mcontext, OnLineHuiFuActivity.class);
        in.putExtra("comment_id", getIntent().getStringExtra("comment_id"));
        in.putExtra("name", getIntent().getStringExtra("name"));
        in.putExtra("line_id", getIntent().getStringExtra("line_id"));
        jumpToActivity(in, false);
    }
}