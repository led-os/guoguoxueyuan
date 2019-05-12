package com.test720.grasshoppercollege.module.gongLue.student.myCourse;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.test720.grasshoppercollege.Adapter.FragmentViewPagerAdapter;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.myViews.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MyCourseListActivity extends BaseToolActivity {

    @BindView(R.id.viewPager)
    CustomViewPager viewPager;
    @BindView(R.id.jin)
    TextView jin;
    @BindView(R.id.fan)
    TextView fan;

    @Override
    public void initData() {
        List<Fragment> list = new ArrayList<>();
        MyCourseFragment myCourseFragment = new MyCourseFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", "56");
        myCourseFragment.setArguments(bundle);

        MyCourseFragment myCourseFragment1 = new MyCourseFragment();
        Bundle bundle1 = new Bundle();
        bundle1.putString("type", "57");
        myCourseFragment1.setArguments(bundle1);

        list.add(myCourseFragment);
        list.add(myCourseFragment1);
        viewPager.setAdapter(new FragmentViewPagerAdapter(getSupportFragmentManager(), list));
    }

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_my_course_list;
    }


    @Override
    public void GetHttpBackStr(String str, int what) {

    }

    @OnClick({R.id.back, R.id.jin, R.id.fan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.jin:
                jin.setBackgroundResource(R.drawable.bule_back_kuang);
                jin.setTextColor(getResources().getColor(R.color.white));
                fan.setBackgroundResource(R.drawable.white_bian_kuang);
                fan.setTextColor(getResources().getColor(R.color.appColor));
                viewPager.setCurrentItem(0);
                break;
            case R.id.fan:
                fan.setBackgroundResource(R.drawable.bule_back_kuang);
                fan.setTextColor(getResources().getColor(R.color.white));
                jin.setBackgroundResource(R.drawable.white_bian_kuang);
                jin.setTextColor(getResources().getColor(R.color.appColor));
                viewPager.setCurrentItem(1);
                break;
        }
    }
}
