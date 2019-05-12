package com.test720.grasshoppercollege.module.userData.vip;

import android.view.View;

public class VipQuanYiActivity extends BaseVipActivity {


    @Override
    protected String setTitle() {
        return "我的权益";
    }

    @Override
    protected void initData() {
        super.initData();
        lin.setVisibility(View.GONE);
    }

    @Override
    public void yuEZhiFu(String data) {

    }
}
