package com.test720.grasshoppercollege.module.meiRiYiJu;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.test720.grasshoppercollege.Adapter.FragmentViewPagerAdapter;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.myViews.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MeiRiShouYeActivity extends BaseToolActivity {


    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.one)
    LinearLayout one;
    @BindView(R.id.two)
    LinearLayout two;
    @BindView(R.id.viewPager)
    CustomViewPager viewPager;

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_mei_ri_shou_ye;
    }

    @Override
    protected void initData() {


        List<Fragment> list = new ArrayList<>();
        MeiRiListFragment meiRiListFragment = new MeiRiListFragment();
        meiRiListFragment.setType(0);
        list.add(meiRiListFragment);
        MeiRiListFragment meiRiListFragment1 = new MeiRiListFragment();
        meiRiListFragment1.setType(1);
        list.add(meiRiListFragment1);
        viewPager.setAdapter(new FragmentViewPagerAdapter(getSupportFragmentManager(), list));
    }

    @Override
    public void GetHttpBackStr(String str, int what) {

    }


    @OnClick({R.id.back, R.id.one, R.id.two})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.one:
//                one.setBackgroundResource(R.color.orange);
//                two.setBackgroundResource(R.color.appColor);
                viewPager.setCurrentItem(0);
                break;
            case R.id.two:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (MyApplication.getmInstance().getUid().equals("")) {
                    return;
                }
//                two.setBackgroundResource(R.color.orange);
//                one.setBackgroundResource(R.color.appColor);
                viewPager.setCurrentItem(1);
                break;
        }
    }

}
