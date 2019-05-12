package com.test720.grasshoppercollege.module.youEr.yingWenHuiBen.pinLun;

import android.content.Intent;
import android.os.Bundle;

import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.PinLunXiangQingActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;

import okhttp3.FormBody;

public class HuibenPinLunXiangQingActivity extends PinLunXiangQingActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        noZhuan = true;
    }

    @Override
    public void dianZan() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("my_id", getIntent().getStringExtra("my_id"));
        builder.add("uid", MyApplication.getmInstance().getUid());
        builder.add("comment_id", getIntent().getStringExtra("comment_id"));
        Post(HttpUntil.GetIntent().getIP() + "small.php/ChildBook/thumbUp", builder, 5);
    }

    @Override
    public String url() {
        return HttpUntil.GetIntent().getIP() + "small.php/ChildBook/commentInfo";
    }

    @Override
    public void pingLun() {
        Intent in = new Intent(mcontext, HuibenPinJiaHuiFuActivity.class);
        in.putExtra("comment_id", getIntent().getStringExtra("comment_id"));
        in.putExtra("name", getIntent().getStringExtra("name"));
        in.putExtra("my_id", getIntent().getStringExtra("my_id"));
        jumpToActivity(in, false);
    }
}
