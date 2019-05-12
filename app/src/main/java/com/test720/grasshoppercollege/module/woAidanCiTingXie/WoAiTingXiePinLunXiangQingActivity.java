package com.test720.grasshoppercollege.module.woAidanCiTingXie;

import android.content.Intent;

import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.PinLunXiangQingActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;

import okhttp3.FormBody;

public class WoAiTingXiePinLunXiangQingActivity extends PinLunXiangQingActivity {


    @Override
    public void dianZan() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("love_id", getIntent().getStringExtra("love_id"));
        builder.add("uid", MyApplication.getmInstance().getUid());
        builder.add("comment_id", getIntent().getStringExtra("comment_id"));
        Post(HttpUntil.GetIntent().EnLoveDictationthumbUp(), builder, 5);
    }

    @Override
    public String url() {
        return HttpUntil.GetIntent().EnLoveDictationcommentInfo();
    }

    @Override
    public void pingLun() {
        Intent in = new Intent(mcontext, WoAiTingXiePinJiaHuiFuActivity.class);
        in.putExtra("comment_id", getIntent().getStringExtra("comment_id"));
        in.putExtra("name", getIntent().getStringExtra("name"));
        in.putExtra("love_id", getIntent().getStringExtra("love_id"));
        in.putExtra("number", getIntent().getStringExtra("number"));
        jumpToActivity(in, false);
    }
}
