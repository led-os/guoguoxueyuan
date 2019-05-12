package com.test720.grasshoppercollege.module.yuFaTest;

import android.content.Intent;

import com.test720.grasshoppercollege.PublicDingZhengActivity;
import com.test720.grasshoppercollege.module.fanYiChuangGuan.FanYiTiMuActivity;

public class JileiActivity extends PublicDingZhengActivity {

    @Override
    public String type() {
        return "24";
    }

    @Override
    public void adapterItemClick(String id) {
        Intent intent = new Intent(this, YuFaTestTiMuActivity.class);
        intent.putExtra("eq_id", id);
        jumpToActivity(intent, true);
    }

    @Override
    protected String setTitle() {
        return "语法练习错题集";
    }
}
