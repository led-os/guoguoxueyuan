package com.test720.grasshoppercollege.module.userData.caiWu;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CaiWuMingXiActivity extends BaseToolActivity {


    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    List<Fragment> list = new ArrayList<>();
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    private List<String> mTitle = new ArrayList<String>();

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_cai_wu_ming_xi;
    }

    @Override
    protected void initData() {
        mTitle.add("充值");
        mTitle.add("待审核");
        mTitle.add("已提现");
        mTitle.add("提现失败");


        list.add(new ChongZhiFragment());
        list.add(new DaiShengHeFragment());
        list.add(new YIDaKuanFragment());
        list.add(new ShiBaiFragment());


        MyAdapter adapter = new MyAdapter(getSupportFragmentManager(), mTitle, list);
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
    public void onViewClicked() {
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
