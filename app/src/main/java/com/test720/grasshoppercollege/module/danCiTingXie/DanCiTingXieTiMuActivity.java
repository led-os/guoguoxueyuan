package com.test720.grasshoppercollege.module.danCiTingXie;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.BaseTiMuActivity;
import com.test720.grasshoppercollege.HttpBean.FanYiNeiRongdata;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.danCiTingXie.timu.BaseDanCiFragment;
import com.test720.grasshoppercollege.module.danCiTingXie.timu.DanXuanFragment;
import com.test720.grasshoppercollege.module.danCiTingXie.timu.TianKongFragment;
import com.test720.grasshoppercollege.myViews.CustomViewPager;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;

public class DanCiTingXieTiMuActivity extends BaseTiMuActivity {
    @BindView(R.id.viewPager)
    CustomViewPager viewPager;
    @BindView(R.id.back)
    ImageView back;
    List<BaseDanCiFragment> list = new ArrayList<>();


    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_dan_ci_ting_xie_ti_mu;
    }

    @Override
    protected void initData() {
        setType(23);
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

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == 2) {
                    list.get(viewPager.getCurrentItem()).playVoic();
                }
            }
        });
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
                                danXuanFragment.setIndex(i);
                                list.add(danXuanFragment);
                                break;
                            case "2"://填空
                                TianKongFragment tianKongFragment = new TianKongFragment();
                                tianKongFragment.setBean(fanYiNeiRongdata.getData().getList().get(i));
                                tianKongFragment.setViewPager(viewPager);
                                tianKongFragment.setBaseTiMuActivity(this);
                                tianKongFragment.setIndex(i);
                                list.add(tianKongFragment);
                                break;
                            case "3"://填空题

                                break;
                        }
                    }
                    viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
                        @Override
                        public Fragment getItem(int position) {
                            return list.get(position);
                        }

                        @Override
                        public int getCount() {
                            return list.size();
                        }
                    });
                }
                break;
        }
    }



    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
