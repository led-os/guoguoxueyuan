package com.test720.grasshoppercollege.module.yinBiao;

import android.content.Intent;

import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.PinLunXiangQingActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;

import okhttp3.FormBody;

public class YinBiaoPinLunXiangQingActivity extends PinLunXiangQingActivity {


    @Override
    public void dianZan() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("ipa_id", getIntent().getStringExtra("ipa_id"));
        builder.add("number", getIntent().getStringExtra("number"));
        builder.add("uid", MyApplication.getmInstance().getUid());
        builder.add("comment_id", getIntent().getStringExtra("comment_id"));
        Post(HttpUntil.GetIntent().EnIpathumbUp(), builder, 5);
    }

    @Override
    public String url() {
        return HttpUntil.GetIntent().commentInfo();
    }

    @Override
    public void pingLun() {
        Intent in = new Intent(mcontext, YinBiaoHuiFuActivity.class);
        in.putExtra("comment_id", getIntent().getStringExtra("comment_id"));
        in.putExtra("name", getIntent().getStringExtra("name"));
        in.putExtra("ipa_id", getIntent().getStringExtra("ipa_id"));
        in.putExtra("number", getIntent().getStringExtra("number"));
        jumpToActivity(in, false);
    }
}