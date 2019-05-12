package com.test720.grasshoppercollege.module.fanYiChuangGuan;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

import com.test720.grasshoppercollege.PublicLianXiJinShaiActivity;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.jinShai.JinShaiShouYeActivity;

import java.util.ArrayList;


public class FanYiGuanKaActivity extends PublicLianXiJinShaiActivity {
    ArrayList<Fragment> list = new ArrayList<>();

    @Override
    public void JiLei() {
        Intent intent = new Intent(mcontext, FanYiJiLeiActivity.class);
        intent.putExtra("book_id", getId());
        jumpToActivity(intent, false);
    }

    @Override
    public void initView() {
        super.initView();
        getContentFrame().setBackgroundResource(R.mipmap.ditutang);
    }

    @Override
    public void jinShai() {
        Intent intent = new Intent(this, JinShaiShouYeActivity.class);
        intent.putExtra("type", "12");
        jumpToActivity(intent, false);
    }

    @Override
    protected void initData() {
        getContentFrame().setOffscreenPageLimit(0);
        getYou().setVisibility(View.GONE);

        getContentFrame().setPadding(0, 100, 0, 100);
        setId(getIntent().getStringExtra("book_id"));

        FanYiGuanKaFragment fanYiGuanKaFragment = new FanYiGuanKaFragment();
        fanYiGuanKaFragment.setIndex(0);
        fanYiGuanKaFragment.setBookId(getId());
        fanYiGuanKaFragment.setPublicLianXiJinShaiActivity(this);
        list.add(fanYiGuanKaFragment);
        getContentFrame().setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
    }


    @Override
    public void GetHttpBackStr(String str, int what) {

    }
}
