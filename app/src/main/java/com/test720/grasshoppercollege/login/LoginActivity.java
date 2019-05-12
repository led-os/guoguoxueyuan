package com.test720.grasshoppercollege.login;

import android.content.Intent;
import android.view.View;

import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.MainActivity;
import com.test720.grasshoppercollege.R;

import butterknife.OnClick;

public class LoginActivity extends BaseToolActivity {

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {
    }

    @Override
    public void GetHttpBackStr(String str, int what) {

    }


    @OnClick({R.id.see, R.id.phone_log, R.id.two, R.id.three})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.see:
                Intent intent = new Intent(mcontext, MainActivity.class);
                intent.putExtra("chengbao", "1");
                jumpToActivity(intent, true);
                break;
            case R.id.phone_log:
                jumpToActivity(PhoneLoginActivity.class, false);
                break;
            case R.id.two:
                jumpToActivity(ZhiJieLoginActivity.class, false);
                break;
            case R.id.three:
                jumpToActivity(RegisterActivity.class, false);
                break;
        }
    }

    @Override
    public void onEvent(CurrencyEvent currencyEvent) {
        if (currencyEvent.getWhat() == CurrencyEvent.REGOK
                || currencyEvent.getWhat() == CurrencyEvent.LOGINOK) {
            finish();
        }
    }
}
