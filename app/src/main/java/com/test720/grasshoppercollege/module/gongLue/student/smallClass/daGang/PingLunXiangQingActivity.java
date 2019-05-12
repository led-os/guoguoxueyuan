package com.test720.grasshoppercollege.module.gongLue.student.smallClass.daGang;

import android.content.Intent;

import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.PinLunXiangQingActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;

import okhttp3.FormBody;

public class PingLunXiangQingActivity extends PinLunXiangQingActivity {
    @Override
    public void setSys() {
        super.setSys();
        noZhuan = true;
    }

    @Override
    public void dianZan() {
        String commentId = getIntent().getStringExtra("comment_id");
        if (commentId == null) commentId = "0";
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("micro_class_id", getIntent().getStringExtra("micro_class_id"));
        builder.add("uid", MyApplication.getmInstance().getUid());
        builder.add("comment_id", commentId);
        Post(HttpUntil.GetIntent().StrategymicroThumbUp(), builder, 5);
    }

    @Override
    public void pingLun() {
        Intent in = new Intent(mcontext, HuiFuActivity.class);
        in.putExtra("comment_id", getIntent().getStringExtra("comment_id"));
        in.putExtra("micro_class_id", getIntent().getStringExtra("micro_class_id"));
        in.putExtra("name", getIntent().getStringExtra("name"));
        jumpToActivity(in, false);

    }


    @Override
    public String url() {
        return HttpUntil.GetIntent().StrategymicroCommentInfo();
    }
}
