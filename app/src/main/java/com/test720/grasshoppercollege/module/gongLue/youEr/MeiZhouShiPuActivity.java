package com.test720.grasshoppercollege.module.gongLue.youEr;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.WebFragment;
import com.test720.grasshoppercollege.module.gongLue.GongLueData;
import com.test720.grasshoppercollege.module.gongLue.youEr.bean.MeiZhouShiPuData;
import com.test720.grasshoppercollege.myViews.CustomViewPager;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MeiZhouShiPuActivity extends BaseToolActivity {

    @BindView(R.id.viewPager)
    CustomViewPager viewPager;
    @BindView(R.id.biaoti)
    TextView biaoti;
    List<Fragment> list = new ArrayList<>();
    List<String> titles = new ArrayList<>();


    @Override
    protected String setTitle() {
        return "每周食谱";
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_mei_zhou_shi_pu;
    }

    @Override
    protected void initData() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                biaoti.setText(titles.get(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        HttpParams httpParams = new HttpParams();
        httpParams.put("school_id", GongLueData.getInstance().getSchoolId());
        postResponse(HttpUntil.GetIntent().StrategyChildeveryWeek(), httpParams, 1, true);
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        if (codeIsOne(str, false)) {
            MeiZhouShiPuData data = new Gson().fromJson(str, MeiZhouShiPuData.class);
            for (int i = 0; i < data.getData().size(); i++) {
                WebFragment webFragment = new WebFragment();
                Bundle bundle = new Bundle();
                bundle.putString("url", data.getData().get(i).getContent());
                webFragment.setArguments(bundle);
                list.add(webFragment);
                titles.add(data.getData().get(i).getName());
            }
            viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
                @Override
                public Fragment getItem(int position) {
                    return list.get(position);
                }

                @Override
                public int getCount() {
                    return titles.size();
                }
            });
            biaoti.setText(titles.get(0));
        }
    }


    @OnClick({R.id.left, R.id.right})
    public void onViewClicked(View view) {
        if (titles.size() == 0) return;
        switch (view.getId()) {
            case R.id.left:
                if (viewPager.getCurrentItem() > 0)
                    viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                break;
            case R.id.right:
                if (viewPager.getCurrentItem() < list.size() - 1)
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                break;
        }
    }
}
