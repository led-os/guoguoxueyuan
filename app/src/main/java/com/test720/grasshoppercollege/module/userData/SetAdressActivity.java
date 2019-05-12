package com.test720.grasshoppercollege.module.userData;

import android.content.Intent;
import android.view.View;

import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.myViews.TextDialog;
import com.test720.grasshoppercollege.untils.HttpUntil;

import okhttp3.FormBody;

public class SetAdressActivity extends BaseSetAdressActivity {


    @Override
    protected String setTitle() {
        return "修改地址";
    }

    @Override
    protected void initData() {
        sheng = getIntent().getStringExtra("sheng");
        shi = getIntent().getStringExtra("shi");
        qu = getIntent().getStringExtra("qu");
        if (getIntent().getStringExtra("name") != null) {
            name.setText(getIntent().getStringExtra("name"));
        }
        if (getIntent().getStringExtra("phone") != null) {
            phone.setText(getIntent().getStringExtra("phone"));
        }
        if (getIntent().getStringExtra("other") != null) {
            other.setText(getIntent().getStringExtra("other"));
        }
        if (sheng != null && shi != null && qu != null) {
            shengShiQuAdress.setText(sheng.trim() + "-" + shi.trim() + "-" + qu.trim());
        }

        dele.setVisibility(View.VISIBLE);
        dele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleAddress(getIntent().getStringExtra("id"));
            }
        });
    }

    /*删除地址*/
    public void deleAddress(final String id) {
        TextDialog textDialog = new TextDialog();
        textDialog.setTextStr("是否删除？");
        textDialog.setOkText("确定");
        textDialog.setOkClick(new TextDialog.OkClick() {
            @Override
            public void click() {
                /***获取地址列表*/
                FormBody.Builder formBuilder = new FormBody.Builder();
                formBuilder.add("uid", MyApplication.getmInstance().getUid());//请求参数二
                formBuilder.add("address_id", id);//请求参数二
                Post(HttpUntil.GetIntent().deleteAddress(), formBuilder, 3, false);
            }

            @Override
            public void no() {

            }
        });
        textDialog.show(getSupportFragmentManager(), "text");

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
