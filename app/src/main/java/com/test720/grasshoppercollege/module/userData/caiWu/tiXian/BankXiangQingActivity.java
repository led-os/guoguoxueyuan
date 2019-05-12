package com.test720.grasshoppercollege.module.userData.caiWu.tiXian;

import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.untils.HttpUntil;

import okhttp3.FormBody;

public class BankXiangQingActivity extends BaseBankActivity {

    @Override
    protected String setTitle() {
        return "我的银行卡";
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
            bankName.setText(getIntent().getStringExtra("bankName"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            phone.setText(getIntent().getStringExtra("phone"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            id.setText(getIntent().getStringExtra("id"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            cardId.setText(getIntent().getStringExtra("bankCard"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        if (codeIsOne(str)) {
            MyApplication.getmInstance().userData.getData().setBank(0);
            finish();
        }
    }

    @Override
    public void okClick() {
        FormBody.Builder build = new FormBody.Builder();
        build.add("uid", MyApplication.getmInstance().getUid());
        build.add("type", "1");
        Post(HttpUntil.GetIntent().relieveBind(), build, 1);

    }
}
