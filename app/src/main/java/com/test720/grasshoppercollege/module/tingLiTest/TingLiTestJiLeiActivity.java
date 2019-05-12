package com.test720.grasshoppercollege.module.tingLiTest;

import android.content.Intent;

import com.test720.grasshoppercollege.PublicDingZhengActivity;

public class TingLiTestJiLeiActivity extends PublicDingZhengActivity {

    @Override
    public String type() {
        return "21";
    }

    @Override
    public void adapterItemClick(String id) {
        Intent intent = new Intent(this, TiMuActivity.class);
        intent.putExtra("eq_id", id);
        jumpToActivity(intent, true);
    }

    @Override
    protected String setTitle() {
        return "单词辩音错题集";
    }
}
