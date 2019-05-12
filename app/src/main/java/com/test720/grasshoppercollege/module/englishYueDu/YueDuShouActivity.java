package com.test720.grasshoppercollege.module.englishYueDu;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.HttpBean.EngLishReadShouData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;

public class YueDuShouActivity extends BaseToolActivity {

    @BindView(R.id.viewPager)
    public ViewPager viewPager;
    List<Fragment> list = new ArrayList<>();
    @BindView(R.id.left)
    ImageView left;
    @BindView(R.id.right)
    ImageView right;
    @BindView(R.id.back)
    ImageView back;


    public List<Fragment> getList() {
        return list;
    }

    public FragmentPagerAdapter getFragmentViewPagerAdapter() {
        return fragmentViewPagerAdapter;
    }

    FragmentPagerAdapter fragmentViewPagerAdapter;

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_yue_du_shou;
    }

    @Override
    protected void initData() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    left.setVisibility(View.GONE);
                    right.setVisibility(View.VISIBLE);
                } else if (position == viewPager.getAdapter().getCount() - 1) {
                    right.setVisibility(View.GONE);
                    left.setVisibility(View.VISIBLE);
                } else {
                    right.setVisibility(View.VISIBLE);
                    left.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        fragmentViewPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }

        };
        viewPager.setAdapter(fragmentViewPagerAdapter);
        getData();
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        if (codeIsOne(str, false)) {
            EngLishReadShouData engLishReadShouData = new Gson().fromJson(str, EngLishReadShouData.class);
            list.clear();
            for (int i = 0; i < engLishReadShouData.getData().size(); i++) {
                YueDuShouFragment yueDuShouFragment = new YueDuShouFragment();
                yueDuShouFragment.setIndex(i);
                list.add(yueDuShouFragment);
            }
            fragmentViewPagerAdapter.notifyDataSetChanged();
            viewPager.setOffscreenPageLimit(engLishReadShouData.getData().size());
        }
    }

    /*刷新数据*/
    public void getData() {
        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("uid", MyApplication.getmInstance().getUid());//请求参数一
        formBuilder.add("class", MyApplication.getmInstance().nianji);
        Post(HttpUntil.GetIntent().EnReadIndex(), formBuilder, 1);
    }


    @OnClick({R.id.left, R.id.right, R.id.back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left:
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                break;
            case R.id.right:
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                break;
            case R.id.back:
                finish();
                break;
        }
    }
}
