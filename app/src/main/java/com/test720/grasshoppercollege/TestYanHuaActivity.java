package com.test720.grasshoppercollege;

import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.test720.mylibrary.fireview.MyView;

public class TestYanHuaActivity extends BaseToolActivity {


    @BindView(R.id.myView)
    MyView myView;

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_test_yan_hua;
    }

    @Override
    protected void initData() {
        myView.setRunning(true);
    }

    @Override
    public void GetHttpBackStr(String str, int what) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
