package com.test720.grasshoppercollege.module.jiangjie;

import android.content.Intent;

import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.PinLunXiangQingActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;

import okhttp3.FormBody;

public class JiangJiePinJiaXiangQingActivity extends PinLunXiangQingActivity {
    @Override
    public void dianZan() {
        String commentId = getIntent().getStringExtra("comment_id");
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
    public void pingLun() {
        Intent in = new Intent(mcontext, JiangJieHuiFuActivity.class);
        in.putExtra("comment_id", getIntent().getStringExtra("comment_id"));
        in.putExtra("book_id", getIntent().getStringExtra("book_id"));
        in.putExtra("unit", getIntent().getStringExtra("unit"));
        in.putExtra("page", getIntent().getStringExtra("page"));
        in.putExtra("name", getIntent().getStringExtra("name"));
        jumpToActivity(in, false);

    }



    @Override
    public String url() {
        return HttpUntil.GetIntent().EnExplaincommentInfo();
    }
}
