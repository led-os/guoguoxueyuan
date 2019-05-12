package com.test720.grasshoppercollege.module.shuXue.yingYong;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.FragmentViewPagerAdapter;
import com.test720.grasshoppercollege.HttpBean.AoShuShouYeGuanKaData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.jinShai.JinShaiShouYeActivity;
import com.test720.grasshoppercollege.module.shuXue.aoShuLianXi.AoShuLIanXiShouActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;
import okhttp3.FormBody;

public class YingYongShouActivity extends AoShuLIanXiShouActivity {
    List<Fragment> list = new ArrayList<>();

    @Override
    protected void initData() {
        getContentFrame().addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    left.setVisibility(View.GONE);
                    right.setVisibility(View.VISIBLE);
                } else if (position == getContentFrame().getAdapter().getCount() - 1) {
                    left.setVisibility(View.VISIBLE);
                    right.setVisibility(View.GONE);
                } else {
                    left.setVisibility(View.VISIBLE);
                    right.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        root.setBackgroundResource(R.mipmap.kousuanguankaback);
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("uid", MyApplication.getmInstance().getUid());
        builder.add("class", MyApplication.getmInstance().nianji);
        builder.add("type", "17");
        Post(HttpUntil.GetIntent().MathOlyPracticeindex(), builder, 1, false);
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        if (codeIsOne(str, false)) {
            AoShuShouYeGuanKaData aoShuShouYeGuanKaData = new Gson().fromJson(str, AoShuShouYeGuanKaData.class);
            for (int i = 0; i < aoShuShouYeGuanKaData.getData().size(); i++) {
                YingYongShouFragment aoShuLianXiOneFragment = new YingYongShouFragment();
                aoShuLianXiOneFragment.setIndex(i);
                list.add(aoShuLianXiOneFragment);
            }
            getContentFrame().setAdapter(new FragmentViewPagerAdapter(getSupportFragmentManager(), list));
        }
    }

    @Override
    public void AfterHttp() {
        if (list.size() == 0) {
            getContentFrame().setBackgroundResource(R.drawable.ic_chat_empty);
        } else {
            getContentFrame().setBackgroundResource(R.color.touming);
        }
    }

    @OnClick({R.id.back, R.id.lin1, R.id.lin2, R.id.lin3, R.id.left, R.id.right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin1:
                break;
            case R.id.lin2:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (MyApplication.getmInstance().getUid().equals("")) {
                    return;
                }
                Intent intent = new Intent(this, JinShaiShouYeActivity.class);
                intent.putExtra("type", "17");
                jumpToActivity(intent, false);
                break;
            case R.id.lin3:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (MyApplication.getmInstance().getUid().equals("")) {
                    return;
                }
                jumpToActivity(YingYongJiLeiActivity.class, false);
                break;
            case R.id.back:
                finish();
                break;
            case R.id.left:
                if (getContentFrame().getAdapter() == null) return;
                getContentFrame().setCurrentItem(getContentFrame().getCurrentItem() - 1);
                break;
            case R.id.right:
                if (getContentFrame().getAdapter() == null) return;
                getContentFrame().setCurrentItem(getContentFrame().getCurrentItem() + 1);
                break;
        }
    }
}
