package com.test720.grasshoppercollege.login;

import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.MainActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.LoginUntil;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;
import www.test720.mylibrary.SPUtils;

public class ZhiJieLoginActivity extends BaseToolActivity {


    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.code)
    EditText code;
    @BindView(R.id.get_code)
    TextView getCode;
    @BindView(R.id.ok)
    TextView ok;
    @BindView(R.id.activity_zhi_jie_login)
    LinearLayout activityZhiJieLogin;
    private CountDownTimer countDownTimer;

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_zhi_jie_login;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        switch (what) {
            case 1:
                try {
                    JSONObject js = new JSONObject(str);
                    ShowToast(js.getString("msg"));
                    if (js.getInt("code") == 1) {
                        //保存手机号
                        SPUtils.putString(mcontext, SPUtils.PHONE, phone.getText().toString());
                        LoginUntil.getInstance().saveData(str, mcontext);
                        EventBus.getDefault().post(new CurrencyEvent(CurrencyEvent.LOGINOK, "ok"));
                        /*未设置地址*/
                        if (MyApplication.getmInstance().userData.getData().getArea_select().equals("0")) {
                            jumpToActivity(AdressChooseActivity.class, true);
                        } else {//设置了地址直接关闭界面
                            jumpToActivity(MainActivity.class, true);
                        }

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;

            case 1000:
                if (codeIsOne(str)) {
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
                    }.start();
                }
                break;
        }
    }


    @OnClick({R.id.back, R.id.get_code, R.id.ok})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.get_code:
                GetCode();
                break;
            case R.id.ok:
                login();
                break;
        }
    }

    private void login() {
        if (TextUtils.isEmpty(phone.getText())) {
            phone.setError("请准确输入您的电话号码");
            return;
        }
        if (TextUtils.isEmpty(code.getText())) {
            code.setError("请输入您的验证码");
            return;
        }

        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("phone", phone.getText().toString());//请求参数一
        formBuilder.add("code", code.getText().toString());//请求参数二
        formBuilder.add("type", 2 + "");//请求参数三
        Post(HttpUntil.GetIntent().getlogin(), formBuilder, 1);

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
        formBuilder.add("type", "2");//请求参数二
        Post(HttpUntil.GetIntent().getMobileCode(), formBuilder, 1000);
    }
}
