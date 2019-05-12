package com.test720.grasshoppercollege.module.gongLue.student.shiTing;

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

public class ShiTingKeActivity extends BaseKeMuShouYeActivity {

    OnLineTypeData data;

    @Override
    protected void initData() {
        title.setText("试听课");

        HttpParams httpParams = new HttpParams();
        httpParams.put("type", GongLueData.getInstance().getModularType());
        postResponse(HttpUntil.GetIntent().StrategytryListeningCateList(), httpParams, 1, true);
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        if (codeIsOne(str, false)) {
            data = new Gson().fromJson(str, OnLineTypeData.class);
            getList().clear();
            for (int i = 0; i < data.getData().size(); i++) {
                ShiTinglistFragment smallFragment = new ShiTinglistFragment();
                Bundle bundle = new Bundle();
                bundle.putString("book_type", data.getData().get(i));
                smallFragment.setArguments(bundle);
                smallFragment.setBaseKeMuShouYeActivity(this);
                getList().add(smallFragment);
            }
            viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
                @Override
                public Fragment getItem(int position) {
                    return getList().get(position);
                }

                @Override
                public int getCount() {
                    return getList().size();
                }

                @Nullable
                @Override
                public CharSequence getPageTitle(int position) {
                    if (data == null) return "";
                    return data.getData().get(position);
                }
            });
            tabs.setupWithViewPager(viewPager);
        }
    }


}
