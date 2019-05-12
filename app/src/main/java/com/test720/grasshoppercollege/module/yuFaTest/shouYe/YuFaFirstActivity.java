package com.test720.grasshoppercollege.module.yuFaTest.shouYe;

import android.content.Intent;
import android.support.v4.app.Fragment;

import com.test720.grasshoppercollege.Adapter.FragmentViewPagerAdapter;
import com.test720.grasshoppercollege.PublicLianXiJinShaiActivity;
import com.test720.grasshoppercollege.module.jinShai.JinShaiShouYeActivity;
import com.test720.grasshoppercollege.module.yuFaTest.JileiActivity;

import java.util.ArrayList;
import java.util.List;

public class YuFaFirstActivity extends PublicLianXiJinShaiActivity {


    @Override
    public void JiLei() {
        jumpToActivity(JileiActivity.class, false);
    }

    @Override
    public void jinShai() {
        Intent intent = new Intent(this, JinShaiShouYeActivity.class);
        intent.putExtra("type", "24");
        jumpToActivity(intent, false);
    }

    @Override
    protected void initData() {
        List<Fragment> list = new ArrayList<>();
        list.add(new YuFaFirstFragment());
        getContentFrame().setAdapter(new FragmentViewPagerAdapter(getSupportFragmentManager(), list));
    }

    @Override
    public void GetHttpBackStr(String str, int what) {

    }
}
