package com.test720.grasshoppercollege.module.gongLue.teacher.pinTaiZiXun.wenDa;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class WenDaActivity extends BaseToolActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewPager)
    ViewPager viewPager;


    private List<String> mTitle = new ArrayList<>();
    private List<Fragment> mFragment = new ArrayList<>();

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_wen_da;
    }

    @Override
    protected void initData() {
        title.setText("在线问答");

        mTitle.add("待回复");
        mTitle.add("已回复");
        for (int i = 0; i < 2; i++) {
            WenDaListFragment wenDaListFragment = new WenDaListFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("status", i);
            wenDaListFragment.setArguments(bundle);
            mFragment.add(wenDaListFragment);
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

        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        postResponse(HttpUntil.GetIntent().StrategygetAnswerMoney(), httpParams, 1, false);

    }

    @Override
    public void GetHttpBackStr(String str, int what) {

    }


    @OnClick({R.id.back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
           /* case R.id.setPrice:
                EditDialog editDialog = new EditDialog();
                editDialog.setEditClick(new EditDialog.EditClick() {
                    @Override
                    public void editClick(String str) {
                        HttpParams httpParams = new HttpParams();
                        httpParams.put("tid", MyApplication.getmInstance().getUid());
                        httpParams.put("price", str);
                        postResponse(HttpUntil.GetIntent().StrategysetAnswerPrice(), httpParams, 5, false);
                    }
                });
                editDialog.show(getSupportFragmentManager(), "ed");
                break;*/
        }
    }
}
