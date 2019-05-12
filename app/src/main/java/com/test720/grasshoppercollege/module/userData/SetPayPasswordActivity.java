package com.test720.grasshoppercollege.module.userData;


import com.test720.grasshoppercollege.BasePasswordActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.untils.HttpUntil;

import okhttp3.FormBody;
/*设置支付密码*/
public class SetPayPasswordActivity extends BasePasswordActivity {

    @Override
    protected String setTitle() {
        return "设置密码";
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        super.GetHttpBackStr(str, what);
        if (codeIsOne(str)) {
            MyApplication.getmInstance().userData.getData().setIs_pay_password(1);
            finish();
        }
    }

    @Override
    public void sendPasswordToIp() {
        if (MyApplication.getmInstance().getUid().equals("")) return;
        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("pay_password", passwordOne);//请求参数一
        formBuilder.add("uid", MyApplication.getmInstance().getUid());//请求参数二
        Post(HttpUntil.GetIntent().setPayPassword(), formBuilder, 1, true);
    }

}
