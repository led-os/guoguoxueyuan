package com.test720.grasshoppercollege.module.userData.updataPhone;

import android.content.Intent;

import com.test720.grasshoppercollege.BasePhoneCodeActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.login.LoginActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;

import okhttp3.FormBody;
import www.test720.mylibrary.SPUtils;

public class UpdataPhoneNewActivity extends BasePhoneCodeActivity {

    @Override
    public String CodeType() {
        return "6";
    }

    @Override
    public void ok() {


        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("phone", phone.getText().toString());//请求参数一
        formBuilder.add("uid", MyApplication.getmInstance().getUid());//请求参数二
        formBuilder.add("code", code.getText().toString());//请求参数二
        formBuilder.add("token", MyApplication.getmInstance().token);//请求参数二
        Post(HttpUntil.GetIntent().editPhone(), formBuilder, 1, true);
    }

    @Override
    protected String setTitle() {
        return "新手机号";

    }

    @Override
    protected void initData() {
        phone.setHint("新手机号");
        getNext().setText("确定");
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        super.GetHttpBackStr(str, what);
        if (codeIsOne(str) && what == 1) {
            SPUtils.putString(UpdataPhoneNewActivity.this, "phone", "");
            MyApplication.getmInstance().setUid("");
            MyApplication.getmInstance().userData = null;
            Intent intent = new Intent(mcontext, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}
