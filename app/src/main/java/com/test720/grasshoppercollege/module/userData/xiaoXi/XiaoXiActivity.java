package com.test720.grasshoppercollege.module.userData.xiaoXi;

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

public class XiaoXiActivity extends BaseToolActivity {

    @BindView(R.id.xiaoxi)
    TextView xiaoxi;
    @BindView(R.id.tongzhi)
    TextView tongzhi;
    @BindView(R.id.viewPager)
    CustomViewPager viewPager;
    List<Fragment> list = new ArrayList<>();


    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_xiao_xi;
    }

    @Override
    protected void initData() {
        tongzhi.setVisibility(View.GONE);
        list.add(new XiaoXIFragment());
        list.add(new TongZhiFragment());
        viewPager.setAdapter(new FragmentViewPagerAdapter(getSupportFragmentManager(), list));
        viewPager.setCurrentItem(0);
    }

    @Override
    public void GetHttpBackStr(String str, int what) {

    }


    @OnClick({R.id.back, R.id.xiaoxi, R.id.tongzhi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.xiaoxi:
                xiaoxi.setTextColor(getResources().getColor(R.color.white));
                tongzhi.setTextColor(getResources().getColor(R.color.appColor));
                xiaoxi.setBackgroundResource(R.drawable.bule_back_kuang_);
                tongzhi.setBackgroundResource(R.drawable.white_back_);
                viewPager.setCurrentItem(0);
                break;
            case R.id.tongzhi:
                tongzhi.setTextColor(getResources().getColor(R.color.white));
                xiaoxi.setTextColor(getResources().getColor(R.color.appColor));
                tongzhi.setBackgroundResource(R.drawable.bule_back_kuang_);
                xiaoxi.setBackgroundResource(R.drawable.white_back_);
                viewPager.setCurrentItem(1);
                break;
        }
    }

    public CustomViewPager getViewPager() {
        return viewPager;
    }

    public void setViewPager(CustomViewPager viewPager) {
        this.viewPager = viewPager;
    }

    public List<Fragment> getList() {
        return list;
    }

    public void setList(List<Fragment> list) {
        this.list = list;
    }
}
