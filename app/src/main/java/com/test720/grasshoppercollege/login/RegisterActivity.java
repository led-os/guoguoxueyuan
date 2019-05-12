package com.test720.grasshoppercollege.login;

import android.content.Intent;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.TextActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;

public class RegisterActivity extends BaseToolActivity {


    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.code)
    EditText code;
    @BindView(R.id.get_code)
    TextView getCode;
    @BindView(R.id.checkbox)
    CheckBox checkbox;
    @BindView(R.id.next)
    TextView next;
    @BindView(R.id.activity_register)
    RelativeLayout activityRegister;
    @BindView(R.id.password)
    EditText password;

    private CountDownTimer countDownTimer;

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_register;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onEvent(CurrencyEvent currencyEvent) {
        super.onEvent(currencyEvent);
        if (currencyEvent.getWhat() == CurrencyEvent.REGOK) {
            finish();
        }
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        switch (what) {
            case 1:
                if (codeIsOne(str)) {
                    countDownTimer = new CountDownTimer(60000, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            time--;
                            String t = String.valueOf(time) + "s";
                            getCode.setText(t);
                            if (time == 0) {
                                countDownTimer.onFinish();
                            }
                        }

                        @Override
                        public void onFinish() {
                            getCode.setText("获取验证码");
                            time = 60;
                        }
                    }.start();
                }
                break;
            case 2:
                codeIsOne(str);
                break;
        }
    }


    @OnClick({R.id.back, R.id.get_code, R.id.next, R.id.text})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.get_code:
                GetCode();
                break;
            case R.id.next:
                register();
                break;
            case R.id.text:
                jumpToActivity(TextActivity.class, false);
                break;
        }
    }

    /*注册*/
    private void register() {
        if (TextUtils.isEmpty(phone.getText()) || phone.getText().toString().length() < 11) {
            phone.setError("请准确输入您的电话号码");
            return;
        }
       /* if (!PublicUntil.isMobileNO(phone.getText().toString())) {
            phone.setError("请输入正确的手机号");
            return;
        }*/
        if (TextUtils.isEmpty(code.getText())) {
            code.setError("请准确输入验证码");
            return;
        }

        if (TextUtils.isEmpty(password.getText())) {
            password.setError("请输入您的密码");
            return;
        }
        if (password.length() < 6) {
            password.setError("密码不能少于6位");
            return;
        }
        if (!checkbox.isChecked()) {
            ShowToast("请确认用户协议");
            return;
        }
        Intent in = new Intent(this, RegisterTwoActivity.class);
        in.putExtra("phone", phone.getText().toString());//请求参数一
        in.putExtra("code", code.getText().toString());//请求参数二
        in.putExtra("password", password.getText().toString());//请求参数三
        jumpToActivity(in, false);
    }


    /*获取验证码*/
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
        formBuilder.add("type", "1");//请求参数二
        Post(HttpUntil.GetIntent().getMobileCode(), formBuilder, 1);


    }

}
