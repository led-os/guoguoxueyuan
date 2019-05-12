package com.test720.grasshoppercollege.module.siWeiXuLian;

import android.content.Intent;

import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.PinLunXiangQingActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;

import okhttp3.FormBody;

public class XiangQingActivity extends PinLunXiangQingActivity {


    @Override
    public void dianZan() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("child_id", getIntent().getStringExtra("child_id"));
        builder.add("uid", MyApplication.getmInstance().getUid());
        builder.add("comment_id", getIntent().getStringExtra("comment_id"));
        Post(HttpUntil.GetIntent().ChildThinkingthumbUp(), builder, 5);
    }

    @Override
    public String url() {
        return HttpUntil.GetIntent().ChildThinkingcommentInfo();
    }

    @Override
    public void pingLun() {
        Intent in = new Intent(mcontext, HuiFuActivity.class);
        in.putExtra("comment_id", getIntent().getStringExtra("comment_id"));
        in.putExtra("name", getIntent().getStringExtra("name"));
        in.putExtra("child_id", getIntent().getStringExtra("child_id"));
        jumpToActivity(in, false);
    }
}
