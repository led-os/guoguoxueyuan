package com.test720.grasshoppercollege.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.test720.grasshoppercollege.fragment.BaseTiMuFragment;

import java.util.List;

/**
 * Created by 水东流 on 2018/7/3.
 */

public class YuFaTestAdapter extends FragmentStatePagerAdapter {

    List<BaseTiMuFragment> list;
    List<String> titles;

    public YuFaTestAdapter(FragmentManager fm, List<BaseTiMuFragment> list, List<String> titles) {
        super(fm);
        this.list = list;
        this.titles = titles;
    }

    public YuFaTestAdapter(FragmentManager fm, List<BaseTiMuFragment> list) {
        super(fm);
        this.list = list;
    }


    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (titles != null) {
            return titles.get(position);
        } else {
            return super.getPageTitle(position);
        }

    }
}