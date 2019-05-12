package com.test720.grasshoppercollege.module.youEr.kouSuanDaWang;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.FragmentViewPagerAdapter;
import com.test720.grasshoppercollege.HttpBean.AoShuShouYeGuanKaData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.shuXue.kouSuan.KouSuanShouActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;

public class KouSuanDaWangShouActivity extends KouSuanShouActivity {
    List<Fragment> list = new ArrayList<>();

    @Override
    protected void initData() {
        mainTabhost.setVisibility(View.GONE);
        setType(50);
        getContentFrame().addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    left.setVisibility(View.GONE);
                    right.setVisibility(View.VISIBLE);
                } else if (position == getContentFrame().getAdapter().getCount() - 1) {
                    left.setVisibility(View.VISIBLE);
                    right.setVisibility(View.GONE);
                } else {
                    left.setVisibility(View.VISIBLE);
                    right.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        setType(getIntent().getIntExtra("type", 1));
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("uid", MyApplication.getmInstance().getUid());
        builder.add("class", MyApplication.getmInstance().nianji);
        builder.add("type", getType() + "");
        Post(HttpUntil.GetIntent().ChildMentalArithmeticindex(), builder, 1, false);
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        if (codeIsOne(str, false)) {
            AoShuShouYeGuanKaData aoShuShouYeGuanKaData = new Gson().fromJson(str, AoShuShouYeGuanKaData.class);
            for (int i = 0; i < aoShuShouYeGuanKaData.getData().size(); i++) {
                KouSuanDaWangShouFragment aoShuLianXiOneFragment = new KouSuanDaWangShouFragment();
                aoShuLianXiOneFragment.setType(getType());
                aoShuLianXiOneFragment.setIndex(i);
                list.add(aoShuLianXiOneFragment);
            }
            if (aoShuShouYeGuanKaData.getData().size() == 1) {
                left.setVisibility(View.GONE);
                right.setVisibility(View.GONE);
            }
            getContentFrame().setAdapter(new FragmentViewPagerAdapter(getSupportFragmentManager(), list));
        }
    }

    @Override
    public void AfterHttp() {
        if (list.size() == 0) {
            getContentFrame().setBackgroundResource(R.drawable.ic_chat_empty);
        } else {
            getContentFrame().setBackgroundResource(R.color.touming);
        }
    }
}
