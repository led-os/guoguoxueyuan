package com.test720.grasshoppercollege.module.gongLue.teacherAndConsultant.peiXunTeacher.checkTime;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.Adapter.FragmentViewPagerAdapter;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.gongLue.teacherAndConsultant.bean.TimeData;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CheckTimeActivity extends BaseToolActivity {


    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.quxiao)
    TextView quxiao;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tabs)
    TabLayout tabs;

    List<Fragment> list = new ArrayList<>();
    List<String> titles = new ArrayList<>();

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_time_choose;
    }

    @Override
    public void setSys() {
        super.setSys();
        isTouMing = false;
    }

    @Override
    protected void initData() {

        HttpParams httpParams = new HttpParams();
        httpParams.put("tid", getIntent().getStringExtra("tid"));
        httpParams.put("time", getIntent().getStringExtra("time"));
        httpParams.put("service_id", getIntent().getStringExtra("service_id"));
        postResponse(HttpUntil.GetIntent().StrategygetConsultantTime(), httpParams, 1, true);
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        if (codeIsOne(str, false)) {
            TimeData data = new Gson().fromJson(str, TimeData.class);
            for (int i = 0; i < data.getData().size(); i++) {
                TimeChoosefragment timeChoosefragment = new TimeChoosefragment();
                timeChoosefragment.setData(data.getData().get(i));
                timeChoosefragment.setDay(data.getData().get(i).getDay());
                list.add(timeChoosefragment);
                titles.add(data.getData().get(i).getWeek() + "\n" + data.getData().get(i).getDay());
            }
            viewPager.setAdapter(new FragmentViewPagerAdapter(getSupportFragmentManager(), list, titles));
            tabs.setupWithViewPager(viewPager);

        }

    }


    @OnClick({R.id.back, R.id.quxiao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.quxiao:
                finish();
                break;
        }
    }

}
