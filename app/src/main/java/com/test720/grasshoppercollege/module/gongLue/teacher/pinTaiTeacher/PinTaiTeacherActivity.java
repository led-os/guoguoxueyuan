package com.test720.grasshoppercollege.module.gongLue.teacher.pinTaiTeacher;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.makeramen.roundedimageview.RoundedImageView;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.gongLue.GongLueData;
import com.test720.grasshoppercollege.module.gongLue.teacher.bean.PinTaiTeacherData;
import com.test720.grasshoppercollege.module.gongLue.teacher.pinTaiZiXun.smallCourseShouRu.SmallCourseShouRuActivity;
import com.test720.grasshoppercollege.untils.AppUploatUtils;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.glideUntil.GlideUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class PinTaiTeacherActivity extends BaseToolActivity {
    List<String> titles = new ArrayList<>();
    List<Fragment> list = new ArrayList<>();


    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_my_self;
    }

    @Override
    protected void initData() {
        toolbar.post(new Runnable() {
            @Override
            public void run() {
                dealWithViewPager();
            }
        });
        title.setText("平台老师");
        titles.clear();
        titles.add("待上课程");
        titles.add("已上待填写");
        titles.add("审核课程");
        titles.add("审核成功");
        titles.add("审核失败");
        list.clear();
        for (int i = 0; i < 5; i++) {
            PinTaiFragment pinTaiFragment = new PinTaiFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("status", i);
            pinTaiFragment.setArguments(bundle);
            list.add(pinTaiFragment);
        }
        viewPager.setOffscreenPageLimit(0);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titles.get(position);
            }
        });
        tabs.setupWithViewPager(viewPager);

        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        httpParams.put("status", "0");
        postResponse(HttpUntil.GetIntent().platformTeacherIndex(), httpParams, 1, true);
    }

    private void dealWithViewPager() {
        int toolBarPositionY = toolbar.getHeight();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1) {
            ViewGroup.LayoutParams params = viewPager.getLayoutParams();
            params.height = AppUploatUtils.getScreenHeightPx(mcontext) - toolBarPositionY + 1;
            viewPager.setLayoutParams(params);
        }
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        if (codeIsOne(str, false)) {
            PinTaiTeacherData data = new Gson().fromJson(str, PinTaiTeacherData.class);
            name.setText(data.getData().getInfo().getNickname());
            GlideUntil.getInstance().headByUrl(mcontext, pic, data.getData().getInfo().getHeader());
            smallMoney.setText(data.getData().getInfo().getMicro_money());
            onlineMoney.setText(data.getData().getInfo().getMoney());
        }
    }


    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.pic)
    RoundedImageView pic;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.money)
    TextView money;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.onlineMoney)
    TextView onlineMoney;
    @BindView(R.id.smallMoney)
    TextView smallMoney;

    @OnClick({R.id.back, R.id.small_course, R.id.onLineZhuan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;

            case R.id.small_course:
                Intent intent = new Intent(mcontext, SmallCourseShouRuActivity.class);
                intent.putExtra("status", "teacher");
                GongLueData.getInstance().setYeType(GongLueData.YETEACHERSMALL);
                jumpToActivity(intent, false);
                break;

            case R.id.onLineZhuan:
                Intent intent1 = new Intent(mcontext, TeacherKeChengShouRuActivity.class);
                intent1.putExtra("status", "teacher");
                GongLueData.getInstance().setYeType(GongLueData.YEONLINE);
                jumpToActivity(intent1, false);
                break;

        }
    }


}
