package com.test720.grasshoppercollege.module.gongLue.peiXun.xiaoXi;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class XiaoXiListActivity extends BaseToolActivity {

    @BindView(R.id.viewpager)
    ViewPager viewpager;

    @Override
    protected String setTitle() {
        if (getIntent().getStringExtra("title") != null) {
            return getIntent().getStringExtra("title");
        } else {
            return "";
        }
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_xiao_xi_gl;
    }

    @Override
    protected void initData() {
        final List<Fragment> list = new ArrayList<>();
        list.add(new XiaoXiFragment());
        viewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(0);
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
