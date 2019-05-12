package com.test720.grasshoppercollege.module.userData.updataPhone;


import com.test720.grasshoppercollege.BasePhoneCodeActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.untils.HttpUntil;
import www.test720.mylibrary.SPUtils;

import okhttp3.FormBody;

public class UpdatePhoneOldActivity extends BasePhoneCodeActivity {


    @Override
    protected String setTitle() {
        return "原手机号";
    }


    @Override
    public String CodeType() {
        return "5";
    }

    @Override
    public void ok() {
        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("phone", phone.getText().toString());//请求参数一
        formBuilder.add("uid", MyApplication.getmInstance().getUid());//请求参数二
        formBuilder.add("code", code.getText().toString());//请求参数二
        Post(HttpUntil.GetIntent().checkOldPhone(), formBuilder, 1, true);
    }

    @Override
    protected void initData() {
        phone.setHint("原手机号码");
        phone.setText(SPUtils.getString(this, SPUtils.PHONE, ""));
        phone.setFocusable(false);
        phone.setFocusableInTouchMode(false);
    }


    @Override
    public void GetHttpBackStr(String str, int what) {
        super.GetHttpBackStr(str, what);
        switch (what) {
            case 1:
                if (codeIsOne(str)) {
                    jumpToActivity(UpdataPhoneNewActivity.class, true);
                }
                break;
        }
    }
}
