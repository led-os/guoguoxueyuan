package com.test720.grasshoppercollege.module.yuWen.tingXieZhuShou;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;

import butterknife.BindView;
import butterknife.OnClick;

public class TingXieTypeActivity extends BaseToolActivity {


    @BindView(R.id.one)
    TextView one;
    @BindView(R.id.two)
    TextView two;
    @BindView(R.id.three)
    TextView three;
    @BindView(R.id.four)
    TextView four;

    @Override
    protected String setTitle() {
        return "小学语文" + MyApplication.getmInstance().nianji;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_ting_xie_type;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void GetHttpBackStr(String str, int what) {

    }

    @OnClick({R.id.back, R.id.one, R.id.two, R.id.three, R.id.four})
    public void onViewClicked(View view) {
        int type = 0;
        switch (view.getId()) {
            case R.id.one:
                type = 1;
                break;
            case R.id.two:
                type = 2;
                break;
            case R.id.three:
                type = 3;
                break;
            case R.id.four:
                type = 4;
                break;
            case R.id.back:
                finish();
                break;

        }
        if (view.getId() != R.id.back) {
            Intent intent = new Intent(this, TingXieTiMuActivity.class);
            intent.putExtra("type", type);
            intent.putExtra("id", getIntent().getIntExtra("id", 1));
            jumpToActivity(intent, false);
        }
    }
}
