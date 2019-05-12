package com.test720.grasshoppercollege.module.danCiTingXie;

import android.content.Intent;

import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.PublicDingZhengActivity;

import okhttp3.FormBody;

public class DanCiJiLeiActivity extends PublicDingZhengActivity {

    @Override
    public String type() {
        return "23";
    }

    @Override
    public void adapterItemClick(String id) {
        Intent intent = new Intent(this, DanCiTingXieTiMuActivity.class);
        intent.putExtra("eq_id", id);
        jumpToActivity(intent, true);
    }

    @Override
    public FormBody.Builder formBuilder() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("uid", MyApplication.getmInstance().getUid());
        builder.add("class", MyApplication.getmInstance().nianji);
        builder.add("book_id", getIntent().getStringExtra("book_id"));
        builder.add("p", page + "");
        builder.add("type", type());
        return builder;
    }

    @Override
    protected String setTitle() {
        return "单词听写错题集";
    }
}
