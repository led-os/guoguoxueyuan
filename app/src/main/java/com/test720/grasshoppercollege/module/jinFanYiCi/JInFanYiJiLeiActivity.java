package com.test720.grasshoppercollege.module.jinFanYiCi;

import android.content.Intent;

import com.test720.grasshoppercollege.PublicDingZhengActivity;

public class JInFanYiJiLeiActivity extends PublicDingZhengActivity {

    @Override
    public String type() {
        return "25";
    }

    @Override
    public void adapterItemClick(String id) {
        Intent intent = new Intent(this, JinfanTiMuActivity.class);
        intent.putExtra("eq_id", id);
        jumpToActivity(intent, true);
    }

    @Override
    protected String setTitle() {
        return "近反义词错题集";
    }
}