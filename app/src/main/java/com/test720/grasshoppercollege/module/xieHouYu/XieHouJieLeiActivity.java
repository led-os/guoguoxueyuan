package com.test720.grasshoppercollege.module.xieHouYu;

import android.content.Intent;

import com.test720.grasshoppercollege.PublicDingZhengActivity;
import com.test720.grasshoppercollege.module.xieHouYu.lianxi.LianXiTiMuActivity;

public class XieHouJieLeiActivity extends PublicDingZhengActivity {

    @Override
    public String type() {
        return "26";
    }

    @Override
    public void adapterItemClick(String id) {
        Intent intent = new Intent(this, LianXiTiMuActivity.class);
        intent.putExtra("xhy_id", id);
        intent.putExtra("practice", "2");
        jumpToActivity(intent, true);
    }

    @Override
    protected String setTitle() {
        return "快乐歇后错题集";
    }
}