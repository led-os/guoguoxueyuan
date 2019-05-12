package com.test720.grasshoppercollege;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.test720.grasshoppercollege.login.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginDialogActivity extends Activity {

    @BindView(R.id.quxiao)
    Button quxiao;
    @BindView(R.id.ok)
    Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deng_lu_dialog);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.quxiao, R.id.ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.quxiao:
                this.finish();
                break;
            case R.id.ok:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.finish();
        return true;
    }
}
