package com.test720.grasshoppercollege.module.gongLue.jiaZhang.myWenDa;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MyWenDaActivity extends BaseToolActivity {

    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.rightImg)
    ImageView rightImg;
    @BindView(R.id.right)
    RelativeLayout right;
    @BindView(R.id.topRel)
    RelativeLayout topRel;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private List<String> mTitle = new ArrayList<>();
    private List<Fragment> mFragment = new ArrayList<>();

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_my_wen_da;
    }

    @Override
    protected void initData() {
        title.setText("我的问答");

        mTitle.add("待回复");
        mTitle.add("已回复");

        MyWenDaFragment dai = new MyWenDaFragment();
        Bundle build = new Bundle();
        build.putInt("status", 0);
        dai.setArguments(build);
        mFragment.add(dai);

        MyWenDaFragment yi = new MyWenDaFragment();
        Bundle build1 = new Bundle();
        build1.putInt("status", 1);
        yi.setArguments(build1);
        mFragment.add(yi);

       /* MyWenDaFragment wan = new MyWenDaFragment();
        Bundle build2 = new Bundle();
        build2.putInt("status", 3);
        wan.setArguments(build2);
        mFragment.add(wan);*/

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragment.get(position);
            }

            @Override
            public int getCount() {
                return mFragment.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return mTitle.get(position);
            }
        });
        tabs.setupWithViewPager(viewPager);
    }

    @Override
    public void GetHttpBackStr(String str, int what) {

    }


    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
