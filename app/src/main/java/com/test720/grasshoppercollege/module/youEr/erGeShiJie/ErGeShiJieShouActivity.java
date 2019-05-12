package com.test720.grasshoppercollege.module.youEr.erGeShiJie;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.test720.grasshoppercollege.Adapter.FragmentViewPagerAdapter;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.myViews.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ErGeShiJieShouActivity extends BaseToolActivity {


    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.en)
    TextView en;
    @BindView(R.id.cn)
    TextView cn;
    @BindView(R.id.viewPager)
    CustomViewPager viewPager;

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_er_ge_shi_jie;
    }

    @Override
    protected void initData() {
        List<Fragment> list = new ArrayList<>();
        ErGeShiJieShouFragment erGeShiJieShouFragment = new ErGeShiJieShouFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", 45);//这里的values就是我们要传的值
        erGeShiJieShouFragment.setArguments(bundle);

        ErGeShiJieShouFragment erGeShiJieShouFragment1 = new ErGeShiJieShouFragment();
        Bundle bundle1 = new Bundle();
        bundle1.putInt("type", 44);//这里的values就是我们要传的值
        erGeShiJieShouFragment1.setArguments(bundle1);

        list.add(erGeShiJieShouFragment);
        list.add(erGeShiJieShouFragment1);
        viewPager.setAdapter(new FragmentViewPagerAdapter(getSupportFragmentManager(), list));
    }


    @Override
    public void GetHttpBackStr(String str, int what) {

    }


    @OnClick({R.id.back, R.id.en, R.id.cn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.en:
                en.setBackgroundResource(R.drawable.bule_back_kuang);
                en.setTextColor(getResources().getColor(R.color.white));
                cn.setBackgroundResource(R.drawable.white_bian_kuang);
                cn.setTextColor(getResources().getColor(R.color.appColor));
                viewPager.setCurrentItem(0);
                break;
            case R.id.cn:
                cn.setBackgroundResource(R.drawable.bule_back_kuang);
                cn.setTextColor(getResources().getColor(R.color.white));
                en.setBackgroundResource(R.drawable.white_bian_kuang);
                en.setTextColor(getResources().getColor(R.color.appColor));
                viewPager.setCurrentItem(1);
                break;
        }
    }
}
