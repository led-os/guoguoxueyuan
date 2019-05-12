package com.test720.grasshoppercollege;

import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.HttpBean.CodeData;
import com.test720.grasshoppercollege.untils.HttpUntil;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;

public abstract class BasePhoneCodeActivity extends BaseToolActivity {

    @BindView(R.id.phone)
    public EditText phone;
    @BindView(R.id.code)
    public EditText code;
    @BindView(R.id.get_code)
    TextView getCode;
    @BindView(R.id.next)
    TextView next;
    private CountDownTimer countDownTimer;
    public String returnCode;

    public TextView getNext() {
        return next;
    }

    public void setNext(TextView next) {
        this.next = next;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_phone_code;
    }

    @OnClick({R.id.get_code, R.id.next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.get_code:
                GetCode();
                break;
            case R.id.next:
                nextBtn();
                break;
        }
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        switch (what) {
            case 1000:
                if (codeIsOne(str)) {
                    countDownTimer.start();
                    CodeData codeData = new Gson().fromJson(str, CodeData.class);
                    returnCode = codeData.getData().getCode();
                }
                break;
        }

    }

    private void nextBtn() {
        if (TextUtils.isEmpty(phone.getText()) || phone.getText().toString().length() < 11) {
            phone.setError("请准确输入您的电话号码");
            return;
        }
        if (TextUtils.isEmpty(code.getText())) {
            code.setError("请输入您的验证码");
            return;
        }
        ok();
    }

    /**
     * 验证码类型
     */
    public abstract String CodeType();

    /**
     * 下一步点击事件
     */
    public abstract void ok();

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
        formBuilder.add("type", CodeType());//请求参数二
        Post(HttpUntil.GetIntent().getMobileCode(), formBuilder, 1000);

        countDownTimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time--;
                getCode.setText(String.valueOf(time) + "s");
            }

            @Override
            public void onFinish() {
                getCode.setText("获取验证码");
                time = 60;
            }
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
