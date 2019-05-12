package com.test720.grasshoppercollege.module.userData;

import android.content.Intent;

import com.test720.grasshoppercollege.BasePhoneCodeActivity;
import www.test720.mylibrary.SPUtils;

/*修改支付密码，验证手机*/
public class PhoneCodeActivity extends BasePhoneCodeActivity {

    @Override
    protected String setTitle() {
        return "手机号验证";
    }


    @Override
    protected void initData() {
        phone.setText(SPUtils.getString(this, "phone", ""));
        phone.setFocusable(false);
        phone.setFocusableInTouchMode(false);
    }

    @Override
    public String CodeType() {
        return "7";
    }

    @Override
    public void ok() {
        if (returnCode == null) {
            ShowToast("验证码有误");
            return;
        }
        if (returnCode.equals(code.getText().toString())) {
            Intent in = new Intent(this, SetPayPasswordActivity.class);
            in.putExtra("code", code.getText().toString());
            in.putExtra("phone", phone.getText().toString());
            jumpToActivity(in, true);
        } else {
            ShowToast("验证码有误");
        }
    }
}
