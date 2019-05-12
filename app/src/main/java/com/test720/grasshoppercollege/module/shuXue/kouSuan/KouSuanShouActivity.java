package com.test720.grasshoppercollege.module.shuXue.kouSuan;

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

public class KouSuanShouActivity extends AoShuLIanXiShouActivity {
    List<Fragment> list = new ArrayList<>();
    int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    protected void initData() {
        root.setBackgroundResource(R.mipmap.kousuanguankaback);
        setType(22);
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

        setType(getIntent().getIntExtra("type", 1));
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("uid", MyApplication.getmInstance().getUid());
        builder.add("class", MyApplication.getmInstance().nianji);
        builder.add("type", getType() + "");
        Post(HttpUntil.GetIntent().MathMentalArithmeticindex(), builder, 1, false);
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        if (codeIsOne(str, false)) {
            AoShuShouYeGuanKaData aoShuShouYeGuanKaData = new Gson().fromJson(str, AoShuShouYeGuanKaData.class);
            for (int i = 0; i < aoShuShouYeGuanKaData.getData().size(); i++) {
                KouSuanShouFragment aoShuLianXiOneFragment = new KouSuanShouFragment();
                aoShuLianXiOneFragment.setType(getType());
                aoShuLianXiOneFragment.setIndex(i);
                list.add(aoShuLianXiOneFragment);
            }
            if (aoShuShouYeGuanKaData.getData().size() == 1) {
                left.setVisibility(View.GONE);
                right.setVisibility(View.GONE);
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
                intent.putExtra("type", getType() + "");
                jumpToActivity(intent, false);
                break;
            case R.id.lin3:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (MyApplication.getmInstance().getUid().equals("")) {
                    return;
                }
                Intent intent1 = new Intent(this, KouSuanjiLeiActivity.class);
                intent1.putExtra("type", getType());
                jumpToActivity(intent1, false);
                break;
            case R.id.left:
                if (getContentFrame().getAdapter() == null) return;
                getContentFrame().setCurrentItem(getContentFrame().getCurrentItem() - 1);
                break;
            case R.id.right:
                if (getContentFrame().getAdapter() == null) return;
                getContentFrame().setCurrentItem(getContentFrame().getCurrentItem() + 1);
                break;
            case R.id.back:
                finish();
                break;
        }
    }
}
