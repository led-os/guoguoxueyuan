package com.test720.grasshoppercollege.module.gongLue.jiaZhang.childrenEdu;

import android.content.Intent;

import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.PinLunXiangQingActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;

import okhttp3.FormBody;

public class EduPinLunXiangQingActivity extends PinLunXiangQingActivity {


    @Override
    public void dianZan() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("jiaozi_id", getIntent().getStringExtra("jiaozi_id"));
        builder.add("uid", MyApplication.getmInstance().getUid());
        builder.add("comment_id", getIntent().getStringExtra("comment_id"));
        Post(HttpUntil.GetIntent().EnIpathumbUp(), builder, 5);
    }

    @Override
    public String url() {
        return HttpUntil.GetIntent().StrategyparentJiaoZiCommentInfo();
    }

    @Override
    public void pingLun() {
        Intent in = new Intent(mcontext, EduPinLunHuiFuActivity.class);
        in.putExtra("comment_id", getIntent().getStringExtra("comment_id"));
        in.putExtra("name", getIntent().getStringExtra("name"));
        in.putExtra("jiaozi_id", getIntent().getStringExtra("jiaozi_id"));
        jumpToActivity(in, false);
    }
}