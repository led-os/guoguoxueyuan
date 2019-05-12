package com.test720.grasshoppercollege.module.yinMeiWenHua;

import android.content.Intent;

import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.PinLunXiangQingActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;

import okhttp3.FormBody;

public class YinMeiWenHuaPinLInXiangQingActivity extends PinLunXiangQingActivity {


    @Override
    public void dianZan() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("encc_id", getIntent().getStringExtra("encc_id"));
        builder.add("uid", MyApplication.getmInstance().getUid());
        builder.add("comment_id", getIntent().getStringExtra("comment_id"));
        Post(HttpUntil.GetIntent().EnCulturethumbUp(), builder, 5);
    }

    @Override
    public String url() {
        return HttpUntil.GetIntent().EnCulturecommentInfo();
    }

    @Override
    public void pingLun() {
        Intent in = new Intent(mcontext, YinMeiWenHuaHuiFuActivity.class);
        in.putExtra("comment_id", getIntent().getStringExtra("comment_id"));
        in.putExtra("name", getIntent().getStringExtra("name"));
        in.putExtra("encc_id", getIntent().getStringExtra("encc_id"));
        jumpToActivity(in, false);
    }
}
