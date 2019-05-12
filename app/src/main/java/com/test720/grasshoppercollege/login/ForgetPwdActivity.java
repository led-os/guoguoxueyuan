package com.test720.grasshoppercollege.login;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.CountDownTimerUtil;
import com.test720.grasshoppercollege.untils.HttpUntil;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;

public class ForgetPwdActivity extends BaseToolActivity {


    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.code)
    EditText code;
    @BindView(R.id.get_code)
    TextView getCode;
    @BindView(R.id.next)
    TextView next;
    @BindView(R.id.activity_forget_pwd)
    LinearLayout activityForgetPwd;
    @BindView(R.id.pwd)
    EditText pwd;
    private CountDownTimerUtil countDownTimerUtil;

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_forget_pwd;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void GetHttpBackStr(String str, int what) {

        switch (what) {
            case 1:
                if (codeIsOne(str)) {
                    finish();
                }
                break;
            case 1000:
                if (codeIsOne(str)) {
                    countDownTimerUtil = new CountDownTimerUtil(time);
                    countDownTimerUtil.setTimeInterface(new CountDownTimerUtil.TimeInterface() {
                        @Override
                        public void upDataUi(int time) {
                            String s = String.valueOf(time) + "s";
                            getCode.setText(s);
                        }

                        @Override
                        public void timeIsOver() {
                            getCode.setText("获取验证码");
                            time = 60;
                        }
                    });
                    countDownTimerUtil.start();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimerUtil != null) countDownTimerUtil.over();
    }

    @OnClick({R.id.back, R.id.get_code, R.id.next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.get_code:
                GetCode();
                break;
            case R.id.next:
                findpwd();
                break;
        }
    }

    private void findpwd() {
        if (TextUtils.isEmpty(phone.getText()) || phone.getText().toString().length() < 11) {
            phone.setError("请准确输入您的电话号码");
            return;
        }
        if (TextUtils.isEmpty(code.getText())) {
            code.setError("请输入您的验证码");
            return;
        }
        if (TextUtils.isEmpty(pwd.getText())) {
            pwd.setError("请输入您的密码");
            return;
        }
        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("phone", phone.getText().toString());//请求参数一
        formBuilder.add("code", code.getText().toString());//请求参数二
        formBuilder.add("new_password", pwd.getText().toString());//请求参数三
        Post(HttpUntil.GetIntent().getforgetPwd(), formBuilder, 1);
    }

    int time = 60;

    private void GetCode() {
        if (TextUtils.isEmpty(phone.getText())) {
            phone.setError("请准确输入您的电话号码");
            return;
        }
        if (!getCode.getText().toString().equals("获取验证码")) {
            return;
        }
        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("phone", phone.getText().toString());//请求参数一
        formBuilder.add("type", "3");//请求参数二
        Post(HttpUntil.GetIntent().getMobileCode(), formBuilder, 1000);
    }
}
