package com.test720.grasshoppercollege.module.userData.caiWu.tiXian;

import android.content.Intent;

import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.untils.HttpUntil;

import okhttp3.FormBody;

public class BangDingZFBActivity extends BaseZFBActivity {

    @Override
    protected String setTitle() {
        return "添加支付宝";
    }

    @Override
    protected void initData() {
        ok.setText("确认添加");
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        if (codeIsOne(str)) {
            setResult(1, new Intent());
            MyApplication.getmInstance().userData.getData().setZfb(1);
            finish();
        }
    }

    @Override
    public void okClick() {
        FormBody.Builder build = new FormBody.Builder();
        build.add("uid", MyApplication.getmInstance().getUid());
        build.add("type", "2");
        build.add("name", name.getText().toString());
        build.add("zfb", zfb.getText().toString());
        Post(HttpUntil.GetIntent().addDrawType(), build, 1);
    }
}
