package com.test720.grasshoppercollege.login;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.MainActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.qianDao.QianDaoDOuDialog;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.LoginUntil;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;
import www.test720.mylibrary.SPUtils;

public class PhoneLoginActivity extends BaseToolActivity {

    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.pwd)
    EditText pwd;
    @BindView(R.id.go)
    TextView go;
    @BindView(R.id.forgetpswd)
    LinearLayout forgetpswd;
    @BindView(R.id.activity_phone_login)
    RelativeLayout activityPhoneLogin;

    @Override
    public void setSys() {
        super.setSys();
        isTouMing = false;
    }

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_phone_login;
    }


    @Override
    protected void initData() {
        if (getIntent().getStringExtra("phone") != null)
            phone.setText(getIntent().getStringExtra("phone"));
        /*弹出果果豆框*/
        if (getIntent().getStringExtra("dou") != null) {
            QianDaoDOuDialog qianDaoDOuDialog = new QianDaoDOuDialog();
            qianDaoDOuDialog.setOkListener(new QianDaoDOuDialog.OkListener() {
                @Override
                public void ok() {
                }
            });
            qianDaoDOuDialog.setPin(getIntent().getStringExtra("dou"));
            qianDaoDOuDialog.show(getSupportFragmentManager(), "point");
        }
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        switch (what) {
            case 1:
                try {
                    JSONObject js = new JSONObject(str);
                    ShowToast(js.getString("msg"));
                    if (js.getInt("code") == 1) {

                        SPUtils.putString(mcontext, SPUtils.PHONE, phone.getText().toString());
                        SPUtils.putString(mcontext, SPUtils.PASSWORD, pwd.getText().toString());

                        LoginUntil.getInstance().saveData(str, mcontext);
                        EventBus.getDefault().post(new CurrencyEvent(CurrencyEvent.LOGINOK, "ok"));
                        /*未设置地址*/
                        if (MyApplication.getmInstance().userData.getData().getArea_select().equals("0")) {
                            jumpToActivity(AdressChooseActivity.class, true);
                        } else {//设置了地址直接跳转
                            jumpToActivity(MainActivity.class, true);
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }
    }


    @OnClick({R.id.back, R.id.go, R.id.forgetpswd})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.go:
                login();
                break;
            case R.id.forgetpswd:
                jumpToActivity(ForgetPwdActivity.class, false);
                break;
        }
    }


    private void login() {

        if (TextUtils.isEmpty(phone.getText()) || phone.getText().toString().length() < 11) {
            phone.setError("请准确输入您的电话号码");
            return;
        }
        if (TextUtils.isEmpty(pwd.getText())) {
            pwd.setError("请输入您的密码");
            return;
        }
        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("phone", phone.getText().toString());//请求参数一
        formBuilder.add("password", pwd.getText().toString());//请求参数二
        formBuilder.add("type", 1 + "");//请求参数三
        Post(HttpUntil.GetIntent().getlogin(), formBuilder, 1);

    }

}
