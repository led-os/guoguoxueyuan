package com.test720.grasshoppercollege.module.fanYiChuangGuan;

import android.content.Intent;

import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.PublicDingZhengActivity;

import okhttp3.FormBody;

public class FanYiJiLeiActivity extends PublicDingZhengActivity {

    @Override
    public String type() {
        return "12";
    }

    @Override
    public void adapterItemClick(String id) {
        Intent intent = new Intent(this, FanYiTiMuActivity.class);
        intent.putExtra("eq_id", id);
        jumpToActivity(intent, true);
    }

    @Override
    public FormBody.Builder formBuilder() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("uid", MyApplication.getmInstance().getUid());
        builder.add("class", MyApplication.getmInstance().nianji);
        builder.add("p", page + "");
        builder.add("book_id", getIntent().getStringExtra("book_id"));
        builder.add("type", type());
        return builder;
    }

    @Override
    protected String setTitle() {
        return "翻译闯关错题集";
    }
}
