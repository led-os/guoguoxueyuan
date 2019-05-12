package com.test720.grasshoppercollege.module.shuXue.dongHuaShuXue;

import android.content.Intent;

import com.test720.grasshoppercollege.module.shuXue.BaseShuXueVideoMp4Activity;
import com.test720.grasshoppercollege.module.shuXue.ShuXuePingJIaActivity;

public class DongHuaVideoActivity extends BaseShuXueVideoMp4Activity {
    @Override
    protected void initData() {
        if (getIntent().getStringExtra("title") != null) {
            title.setText(getIntent().getStringExtra("title"));
        } else {
            title.setText("数学动画");
        }
    }

    @Override
    public void PingJia() {
        Intent in = new Intent(DongHuaVideoActivity.this, ShuXuePingJIaActivity.class);
        in.putExtra("math_id", getIntent().getStringExtra("math_id"));
        jumpToActivity(in, false);
    }




    @Override
    public int vodeoType() {
        return 2;
    }

    @Override
    public String vodeoId() {
        return getIntent().getStringExtra("math_id");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
