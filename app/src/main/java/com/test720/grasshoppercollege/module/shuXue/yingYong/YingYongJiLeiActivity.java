package com.test720.grasshoppercollege.module.shuXue.yingYong;

import android.content.Intent;

import com.test720.grasshoppercollege.PublicDingZhengActivity;

public class YingYongJiLeiActivity extends PublicDingZhengActivity {

    @Override
    public String type() {
        return "17";
    }

    @Override
    public void adapterItemClick(String id) {
        Intent intent = new Intent(this, YingYongNeiRongActivity.class);
        intent.putExtra("eq_id", id);
        intent.putExtra("practice", "2");
        jumpToActivity(intent, true);
    }

    @Override
    protected String setTitle() {
        return "数学应用错题集";
    }
}
