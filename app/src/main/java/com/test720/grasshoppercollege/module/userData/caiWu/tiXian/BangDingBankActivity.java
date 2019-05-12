package com.test720.grasshoppercollege.module.userData.caiWu.tiXian;

import android.content.Intent;

import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.untils.HttpUntil;

import okhttp3.FormBody;

public class BangDingBankActivity extends BaseBankActivity {

    @Override
    protected String setTitle() {
        return "绑定银行卡";
    }

    @Override
    protected void initData() {
        ok.setText("确认绑定");

    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        if (codeIsOne(str)) {
            setResult(1, new Intent());//如果绑定成功，告诉上一界面，同时更新本地标识
            MyApplication.getmInstance().userData.getData().setBank(1);//标记为已绑定
            finish();
        }
    }

    @Override
    public void okClick() {
        FormBody.Builder build = new FormBody.Builder();
        build.add("uid", MyApplication.getmInstance().getUid());
        build.add("type", "1");
        build.add("name", name.getText().toString());
        build.add("bank", bankName.getText().toString());
        build.add("number", cardId.getText().toString());
        build.add("id_card", id.getText().toString());
        build.add("phone", phone.getText().toString());
        Post(HttpUntil.GetIntent().addDrawType(), build, 1);

    }
}
