package com.test720.grasshoppercollege.module.userData.caiWu.tiXian;

import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.untils.HttpUntil;

import okhttp3.FormBody;

public class ZFBXiangQingActivity extends BaseZFBActivity {


    @Override
    protected String setTitle() {
        return "支付宝账户";
    }

    @Override
    protected void initData() {
        ok.setText("解除绑定");
        /*初始化详情数据*/
        try {
            name.setText(getIntent().getStringExtra("name"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            zfb.setText(getIntent().getStringExtra("zfb"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        if (codeIsOne(str)) {
            MyApplication.getmInstance().userData.getData().setZfb(0);
            finish();
        }
    }

    @Override
    public void okClick() {
        FormBody.Builder build = new FormBody.Builder();
        build.add("uid", MyApplication.getmInstance().getUid());
        build.add("type", "2");
        Post(HttpUntil.GetIntent().relieveBind(), build, 1);
    }
}
