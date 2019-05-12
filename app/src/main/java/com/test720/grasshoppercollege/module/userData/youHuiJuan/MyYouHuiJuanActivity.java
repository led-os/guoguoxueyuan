package com.test720.grasshoppercollege.module.userData.youHuiJuan;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MyYouHuiJuanActivity extends BaseToolActivity {

    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.activity_my_order)
    LinearLayout activityMyOrder;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.right)
    RelativeLayout share;
    private List<String> mTitle = new ArrayList<String>();
    private List<Fragment> mFragment = new ArrayList<Fragment>();

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_my_you_hui_juan;
    }

    @Override
    protected void initData() {
        title.setText("我的优惠劵");

        mTitle.add("未使用");
        mTitle.add("已使用");
        mTitle.add("已过期");

        YouHuiJuanFragment youHuiJuanFragment = new YouHuiJuanFragment();
        Bundle bundle = new Bundle();
        bundle.putString("status", "0");
        bundle.putString("coupon_type", getIntent().getStringExtra("coupon_type"));
        youHuiJuanFragment.setArguments(bundle);
        mFragment.add(youHuiJuanFragment);

        YouHuiJuanFragment youHuiJuanFragment1 = new YouHuiJuanFragment();
        Bundle bundle1 = new Bundle();
        bundle1.putString("status", "1");
        bundle1.putString("coupon_type", getIntent().getStringExtra("coupon_type"));
        youHuiJuanFragment1.setArguments(bundle1);
        mFragment.add(youHuiJuanFragment1);


        YouHuiJuanFragment youHuiJuanFragment2 = new YouHuiJuanFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putString("status", "2");
        bundle2.putString("coupon_type", getIntent().getStringExtra("coupon_type"));
        youHuiJuanFragment2.setArguments(bundle2);
        mFragment.add(youHuiJuanFragment2);


        MyAdapter adapter = new MyAdapter(getSupportFragmentManager(), mTitle, mFragment);
        viewPager.setAdapter(adapter);
        //为TabLayout设置ViewPager
        tabs.setupWithViewPager(viewPager);
        //使用ViewPager的适配器
        tabs.setTabsFromPagerAdapter(adapter);
    }

    @Override
    public void GetHttpBackStr(String str, int what) {

    }


    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }

    class MyAdapter extends FragmentPagerAdapter {

        private List<String> title;
        private List<Fragment> views;

        public MyAdapter(FragmentManager fm, List<String> title, List<Fragment> views) {
            super(fm);
            this.title = title;
            this.views = views;
        }

        @Override
        public Fragment getItem(int position) {
            return views.get(position);
        }

        @Override
        public int getCount() {
            return views.size();
        }

        //配置标题的方法
        @Override
        public CharSequence getPageTitle(int position) {
            return title.get(position);
        }
    }
}
