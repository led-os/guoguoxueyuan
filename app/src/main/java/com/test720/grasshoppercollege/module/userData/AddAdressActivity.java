package com.test720.grasshoppercollege.module.userData;

import android.content.Intent;

public class AddAdressActivity extends BaseSetAdressActivity {


    @Override
    protected String setTitle() {
        return "新增收货地址";
    }

    @Override
    protected void initData() {

    }


    @Override
    public void GetHttpBackStr(String str, int what) {
        super.GetHttpBackStr(str, what);
        if (codeIsOne(str)) {
            Intent in = new Intent();
            setResult(2, in);
            finish();
        }
    }
}
