package com.test720.grasshoppercollege.module.yuFaJiangJie;

import android.content.Intent;

import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.PinLunXiangQingActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;

import okhttp3.FormBody;

public class YuFaPinLunXiangQingActivity extends PinLunXiangQingActivity {


    @Override
    public void dianZan() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("book_id", getIntent().getStringExtra("book_id"));
        builder.add("uid", MyApplication.getmInstance().getUid());
        builder.add("comment_id", getIntent().getStringExtra("comment_id"));
        Post(HttpUntil.GetIntent().EnGrammarthumbUp(), builder, 5);
    }

    @Override
    public String url() {
        return HttpUntil.GetIntent().EnGrammarcommentInfo();
    }

    @Override
    public void pingLun() {
        Intent in = new Intent(mcontext, YuFaHuiFuActivity.class);
        in.putExtra("comment_id", getIntent().getStringExtra("comment_id"));
        in.putExtra("name", getIntent().getStringExtra("name"));
        in.putExtra("book_id", getIntent().getStringExtra("book_id"));

        jumpToActivity(in, false);
    }
}
