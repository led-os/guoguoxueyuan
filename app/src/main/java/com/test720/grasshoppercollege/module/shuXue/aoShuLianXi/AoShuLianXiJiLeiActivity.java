package com.test720.grasshoppercollege.module.shuXue.aoShuLianXi;

import android.content.Intent;

import com.test720.grasshoppercollege.PublicDingZhengActivity;

public class AoShuLianXiJiLeiActivity extends PublicDingZhengActivity {

    @Override
    public String type() {
        return "18";
    }

    @Override
    public void adapterItemClick(String id) {
        Intent intent = new Intent(this, AoShuLianXiNeiRongActivity.class);
        intent.putExtra("eq_id", id);
        intent.putExtra("practice", "2");
        jumpToActivity(intent, true);
    }

    @Override
    protected String setTitle() {
        return "奥数练习错题集";
    }
}
