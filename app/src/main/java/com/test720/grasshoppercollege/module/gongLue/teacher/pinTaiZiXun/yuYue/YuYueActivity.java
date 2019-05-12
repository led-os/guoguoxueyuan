package com.test720.grasshoppercollege.module.gongLue.teacher.pinTaiZiXun.yuYue;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.gongLue.GongLueData;
import com.test720.grasshoppercollege.module.gongLue.GongLueZhuanYEActivity;
import com.test720.grasshoppercollege.module.gongLue.teacher.bean.YuYueListData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class YuYueActivity extends BaseToolActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.price)
    TextView price;
    String moneyStr = "";

    private List<String> mTitle = new ArrayList<>();
    private List<Fragment> mFragment = new ArrayList<>();

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_yu_yue;
    }

    @Override
    protected void initData() {
        title.setText("预约课程");


        mTitle.add("待咨询");
        mTitle.add("待确认");
        mTitle.add("已完成");

        for (int i = 0; i < 3; i++) {
            YuYueFragment yuYueFragment = new YuYueFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("status", i);
            yuYueFragment.setArguments(bundle);
            mFragment.add(yuYueFragment);
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


    @Override
    public void onEvent(CurrencyEvent currencyEvent) {
        if (currencyEvent.getWhat() == CurrencyEvent.YUYUEJINE) {
            YuYueListData.DataBean.InfoBean infoBean = (YuYueListData.DataBean.InfoBean) currencyEvent.getObject();
            moneyStr = infoBean.getMoney();
            String p = "累计收入：" + moneyStr + "元";
            price.setText(p);
        }
    }

    @OnClick({R.id.back, R.id.zhuanYE})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.zhuanYE:
                GongLueData.getInstance().setYeType(GongLueData.YEZIXUNSHIYUYUE);
                Intent intent = new Intent(mcontext, GongLueZhuanYEActivity.class);
                if (!moneyStr.equals(""))
                    intent.putExtra("money", moneyStr);
                jumpToActivity(intent, false);
                break;
        }
    }
}
