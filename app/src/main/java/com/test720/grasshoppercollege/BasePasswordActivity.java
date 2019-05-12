package com.test720.grasshoppercollege;

import com.test720.grasshoppercollege.myViews.PayPasswordView;

import butterknife.BindView;

public abstract class BasePasswordActivity extends BaseToolActivity {


    @BindView(R.id.pay_password)
    PayPasswordView payPassword;
    public String passwordOne;
    String passwordTwo;

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_set_pay_password;
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        passwordOne = null;
        passwordTwo = null;
        payPassword.getTitle().setText("输入密码");
        payPassword.clearStr();
    }

    @Override
    protected void initData() {
        payPassword.setOnFinishInput(new PayPasswordView.OnPasswordInputFinish() {
            @Override
            public void inputFinish() {
                payPassword.clearStr();
                if (passwordOne == null) {
                    passwordOne = payPassword.getStrPassword();
                    payPassword.getTitle().setText("再次输入密码");
                } else if (passwordOne != null && passwordTwo == null) {
                    passwordTwo = payPassword.getStrPassword();
                    if (passwordTwo.equals(passwordOne)) {
                        sendPasswordToIp();
                    } else {
                        passwordOne = null;
                        passwordTwo = null;
                        payPassword.getTitle().setText("输入密码");
                        ShowToast("两次密码不一致，重新设置");
                    }

                }
            }
        });
    }

    public abstract void sendPasswordToIp();

}
