package com.test720.grasshoppercollege.module.gongLue.student.onLine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.module.gongLue.BaseKeMuShouYeActivity;
import com.test720.grasshoppercollege.module.gongLue.GongLueData;
import com.test720.grasshoppercollege.module.gongLue.student.bean.OnLineTypeData;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

public class OnLineShouActivity extends BaseKeMuShouYeActivity {
    List<Fragment> list = new ArrayList<>();
    OnLineTypeData data;

    @Override
    protected void initData() {
        if (GongLueData.getInstance().getModularType() == (GongLueData.ONLINE)) {
            title.setText("线上精品");
        } else {
            title.setText("线下精品");
        }

        HttpParams httpParams = new HttpParams();
        httpParams.put("type", GongLueData.getInstance().getModularType());
        postResponse(HttpUntil.GetIntent().StrategylineCateList(), httpParams, 1, true);
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        if (codeIsOne(str, false)) {
            data = new Gson().fromJson(str, OnLineTypeData.class);
            for (int i = 0; i < data.getData().size(); i++) {
                OnLineFragment onLineFragment = new OnLineFragment();
                Bundle bundle = new Bundle();
                bundle.putString("book_type", data.getData().get(i));
                onLineFragment.setArguments(bundle);
                onLineFragment.setBaseKeMuShouYeActivity(this);
                list.add(onLineFragment);
            }
            viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
                @Override
                public Fragment getItem(int position) {
                    return list.get(position);
                }

                @Override
                public int getCount() {
                    return list.size();
                }

                @Nullable
                @Override
                public CharSequence getPageTitle(int position) {
                    return data.getData().get(position);
                }
            });
            tabs.setupWithViewPager(viewPager);
        }

    }
}