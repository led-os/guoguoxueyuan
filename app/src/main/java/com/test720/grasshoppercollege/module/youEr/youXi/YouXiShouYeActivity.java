package com.test720.grasshoppercollege.module.youEr.youXi;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.youEr.youXi.bean.TypeBeanData;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;

public class YouXiShouYeActivity extends BaseToolActivity {
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
        return R.layout.activity_you_xi_shou_ye;
    }

    @Override
    protected void initData() {
        FormBody.Builder builder = new FormBody.Builder();
        Post(HttpUntil.GetIntent().ChildGamecate(), builder, 1);
        title.setText("益智游戏");
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        switch (what) {
            case 1:
                if (codeIsOne(str, false)) {
                    TypeBeanData beanData = new Gson().fromJson(str, TypeBeanData.class);
                    for (int i = 0; i < beanData.getData().size(); i++) {
                        mTitle.add(beanData.getData().get(i).getName());
                        YouXiShouFragment youXiShouFragment = new YouXiShouFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("parent_id", beanData.getData().get(i).getParent_id());
                        youXiShouFragment.setArguments(bundle);
                        mFragment.add(youXiShouFragment);
                    }
                    MyAdapter adapter = new MyAdapter(getSupportFragmentManager(), mTitle, mFragment);
                    viewPager.setAdapter(adapter);
                    //为TabLayout设置ViewPager
                    tabs.setupWithViewPager(viewPager);
                    //使用ViewPager的适配器
                    tabs.setTabsFromPagerAdapter(adapter);
                    int count = getIntent().getIntExtra("count", 0);
                    viewPager.setCurrentItem(count);
                }
                break;

        }
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
