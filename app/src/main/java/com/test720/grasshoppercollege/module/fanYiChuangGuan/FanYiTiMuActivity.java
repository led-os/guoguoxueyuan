package com.test720.grasshoppercollege.module.fanYiChuangGuan;

import android.support.v4.app.Fragment;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.FragmentViewPagerAdapter;
import com.test720.grasshoppercollege.BaseTiMuActivity;
import com.test720.grasshoppercollege.HttpBean.FanYiNeiRongdata;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.fanYiChuangGuan.tixing.BuChongTiFragment;
import com.test720.grasshoppercollege.module.fanYiChuangGuan.tixing.DanXuanFragment;
import com.test720.grasshoppercollege.myViews.CustomViewPager;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;

public class FanYiTiMuActivity extends BaseTiMuActivity {


    @BindView(R.id.viewPager)
    CustomViewPager viewPager;
    List<Fragment> list = new ArrayList<>();
    FragmentViewPagerAdapter adapter;


    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_fan_yi_ti_mu;
    }

    @Override
    protected void initData() {
        viewPager.setScanScroll(false);
        setType(12);
        FormBody.Builder formBuilder = new FormBody.Builder();
        if (getIntent().getStringExtra("unit_id") != null) {
            setDingZheng(false);
            formBuilder.add("unit_id", getIntent().getStringExtra("unit_id"));//请求参数一
            formBuilder.add("practice", "1");//请求参数二
        }
        if (getIntent().getStringExtra("eq_id") != null) {
            setDingZheng(true);
            formBuilder.add("eq_id", getIntent().getStringExtra("eq_id"));//请求参数一
            formBuilder.add("practice", "2");//请求参数二
        }
        Post(HttpUntil.GetIntent().Wordcontent(), formBuilder, 1);

    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        switch (what) {
            case 1:
                if (codeIsOne(str, false)) {
                    FanYiNeiRongdata fanYiNeiRongdata = new Gson().fromJson(str, FanYiNeiRongdata.class);
                    for (int i = 0; i < fanYiNeiRongdata.getData().getList().size(); i++) {
                        switch (fanYiNeiRongdata.getData().getList().get(i).getType()) {
                            case "1"://选择题
                                DanXuanFragment danXuanFragment = new DanXuanFragment();
                                danXuanFragment.setBean(fanYiNeiRongdata.getData().getList().get(i));
                                danXuanFragment.setViewPager(viewPager);
                                danXuanFragment.setBaseTiMuActivity(this);
                                list.add(danXuanFragment);
                                break;
                            case "2"://填空
                                BuChongTiFragment buChongTiFragment = new BuChongTiFragment();
                                buChongTiFragment.setBean(fanYiNeiRongdata.getData().getList().get(i));
                                buChongTiFragment.setViewPager(viewPager);
                                buChongTiFragment.setBaseTiMuActivity(this);
                                list.add(buChongTiFragment);
                                break;
                            case "3"://填空题


                                break;
                        }
                    }
                    adapter = new FragmentViewPagerAdapter(getSupportFragmentManager(), list);
                    viewPager.setAdapter(adapter);
                }
                break;
        }
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
