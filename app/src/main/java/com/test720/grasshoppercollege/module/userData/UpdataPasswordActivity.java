package com.test720.grasshoppercollege.module.userData;


import android.content.Intent;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.login.LoginActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;
import www.test720.mylibrary.SPUtils;

public class UpdataPasswordActivity extends BaseToolActivity {

    @BindView(R.id.old_password)
    EditText oldPassword;
    @BindView(R.id.new_password)
    EditText newPassword;
    @BindView(R.id.confirm_password)
    EditText confirmPassword;
    @BindView(R.id.confirm)
    TextView confirm;

    @Override
    protected String setTitle() {
        return "修改登录密码";
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_updata_password;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        if (codeIsOne(str)) {
            SPUtils.putString(UpdataPasswordActivity.this, "phone", "");
            SPUtils.putString(UpdataPasswordActivity.this, "password", "");
            MyApplication.getmInstance().setUid("");
            MyApplication.getmInstance().userData = null;
            Intent intent = new Intent(mcontext, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
    }


    @OnClick(R.id.confirm)
    public void onViewClicked() {
        if (TextUtils.isEmpty(oldPassword.getText())) {
            oldPassword.setError("请输入原密码");
            return;
        }
        if (TextUtils.isEmpty(newPassword.getText())) {
            newPassword.setError("请输入新密码");
            return;
        }
        if (TextUtils.isEmpty(confirmPassword.getText())) {
            confirmPassword.setError("确认密码");
            return;
        }
        if (!confirmPassword.getText().toString().equals(newPassword.getText().toString())) {
            ShowToast("两次密码不一致，请重新输入");
            return;
        }
        if (newPassword.getText().toString().length() < 5) {
            ShowToast("密码最少6位");
            return;
        }
        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("new_password", newPassword.getText().toString());//请求参数一
        formBuilder.add("old_password", oldPassword.getText().toString());//请求参数一
        formBuilder.add("uid", MyApplication.getmInstance().getUid());//请求参数二
        Post(HttpUntil.GetIntent().editLoginPassword(), formBuilder, 1, true);

    }
}
