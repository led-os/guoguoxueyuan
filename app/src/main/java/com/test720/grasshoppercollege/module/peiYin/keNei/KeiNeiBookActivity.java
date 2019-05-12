package com.test720.grasshoppercollege.module.peiYin.keNei;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.peiYin.bean.VersionData;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.FormBody;

public class KeiNeiBookActivity extends BaseToolActivity {

    private List<String> mTitle = new ArrayList<String>();
    private List<Fragment> mFragment = new ArrayList<Fragment>();

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_kei_nei_book;
    }

    @Override
    protected void initData() {
        title.setText("课内配音");
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("type", MyApplication.getmInstance().getModuleType() + "");
        Post(HttpUntil.GetIntent().DubbinggetVersion(), builder, 5);

    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        switch (what) {
            case 5:
                VersionData versionData = new Gson().fromJson(str, VersionData.class);
                mTitle.clear();
                mTitle.add("全部");
                mTitle.addAll(versionData.getData());

                for (int i = 0; i < mTitle.size(); i++) {
                    KeNeiBookFragment keNeiBookFragment = new KeNeiBookFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("version", mTitle.get(i));
                    keNeiBookFragment.setArguments(bundle);
                    mFragment.add(keNeiBookFragment);
                }

                viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
                    @Override
                    public Fragment getItem(int position) {
                        return mFragment.get(position);
                    }

                    @Override
                    public int getCount() {
                        return mFragment.size();
                    }

                    @Override
                    public CharSequence getPageTitle(int position) {
                        return mTitle.get(position);
                    }
                });
                tabs.setupWithViewPager(viewPager);
                break;
        }
    }


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
    @BindView(R.id.activity_my_order)
    LinearLayout activityMyOrder;
}
