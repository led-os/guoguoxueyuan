package com.test720.grasshoppercollege.module.userData;

import com.test720.grasshoppercollege.BasePasswordActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.untils.HttpUntil;

import okhttp3.FormBody;

public class UpdataPayPasswordActivity extends BasePasswordActivity {


    @Override
    protected String setTitle() {
        return "设置密码";
    }

    @Override
    protected void initData() {
        super.initData();
        if (getIntent().getStringExtra("phone") == null || getIntent().getStringExtra("code") == null) {
            ShowToast("请传如手机号和验证码");
            finish();
        }
        if (MyApplication.getmInstance().getUid().equals("")) {
            ShowToast("游客模式不能设置");
            finish();
        }
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        super.GetHttpBackStr(str, what);
        if (codeIsOne(str,false)) {
            MyApplication.getmInstance().userData.getData().setIs_pay_password(1);
            finish();
        } else {
            jumpToActivity(PhoneCodeActivity.class, true);
        }

    }

    @Override
    public void sendPasswordToIp() {
        if (MyApplication.getmInstance().getUid().equals("")) return;
        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("phone", getIntent().getStringExtra("phone"));//请求参数一
        formBuilder.add("code", getIntent().getStringExtra("code"));//请求参数一
        formBuilder.add("pay_password", passwordOne);//请求参数一
        formBuilder.add("uid", MyApplication.getmInstance().getUid());//请求参数二
        Post(HttpUntil.GetIntent().savePayPassword(), formBuilder, 1, true);
    }
}
