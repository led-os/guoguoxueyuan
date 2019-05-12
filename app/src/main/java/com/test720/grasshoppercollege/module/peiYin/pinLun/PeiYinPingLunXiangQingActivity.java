package com.test720.grasshoppercollege.module.peiYin.pinLun;

import android.content.Intent;

import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.PinLunXiangQingActivity;
import com.test720.grasshoppercollege.module.shuXue.ShuXuePingLunHuiFuActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;

import okhttp3.FormBody;

public class PeiYinPingLunXiangQingActivity extends PinLunXiangQingActivity {


    @Override
    public void dianZan() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("pei_id", getIntent().getStringExtra("pei_id"));
        builder.add("uid", MyApplication.getmInstance().getUid());
        builder.add("comment_id", getIntent().getStringExtra("comment_id"));
        Post(HttpUntil.GetIntent().DubbingthumbUp(), builder, 5, false);
    }

    @Override
    public void pingLun() {
        Intent in = new Intent(mcontext, ShuXuePingLunHuiFuActivity.class);
        in.putExtra("comment_id", getIntent().getStringExtra("comment_id"));
        in.putExtra("name", getIntent().getStringExtra("name"));
        in.putExtra("pei_id", getIntent().getStringExtra("pei_id"));
        jumpToActivity(in, false);
    }

    @Override
    public String url() {
        return HttpUntil.GetIntent().DubbingcommentInfo();
    }
}
