package com.test720.grasshoppercollege.module.shuXue.kouSuan;

import android.content.Intent;

import com.test720.grasshoppercollege.PublicDingZhengActivity;

public class KouSuanjiLeiActivity extends PublicDingZhengActivity {
    @Override
    public String type() {
        return getIntent().getIntExtra("type", 1) + "";
    }

    @Override
    public void adapterItemClick(String id) {
        Intent intent = new Intent(this, KouSuanTiMuActivity.class);
        intent.putExtra("eq_id", id);
        intent.putExtra("practice", "2");
        intent.putExtra("type", getIntent().getIntExtra("type", 1));
        jumpToActivity(intent, true);
    }

    @Override
    protected String setTitle() {
        switch (getIntent().getIntExtra("type", 1)) {
            case 22:
                return "数学口算错题集";
            case 50:
                return "口算大王错题集";
        }
        return "数学口算错题集";
    }
}