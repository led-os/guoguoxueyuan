package com.test720.grasshoppercollege.module.youEr.baoBaoTingTing.pingLun;

import android.content.Intent;

import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.untils.HttpUntil;

import okhttp3.FormBody;

public class PinLunXiangQingActivity extends com.test720.grasshoppercollege.PinLunXiangQingActivity {


    @Override
    public void dianZan() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("album_id", getIntent().getStringExtra("album_id"));
        builder.add("uid", MyApplication.getmInstance().getUid());
        builder.add("comment_id", getIntent().getStringExtra("comment_id"));
        Post(HttpUntil.GetIntent().ChildBabyListeningthumbUp(), builder, 5);
    }

    @Override
    public String url() {
        return HttpUntil.GetIntent().ChildBabyListeningcommentInfo();
    }

    @Override
    public void pingLun() {
        Intent in = new Intent(mcontext, HuiFuActivity.class);
        in.putExtra("comment_id", getIntent().getStringExtra("comment_id"));
        in.putExtra("name", getIntent().getStringExtra("name"));
        in.putExtra("album_id", getIntent().getStringExtra("album_id"));
        jumpToActivity(in, false);
    }
}