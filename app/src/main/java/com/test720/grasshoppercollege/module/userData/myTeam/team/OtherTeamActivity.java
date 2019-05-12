package com.test720.grasshoppercollege.module.userData.myTeam.team;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.FragmentViewPagerAdapter;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.HttpBean.TeamData;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.myViews.CustomViewPager;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;

public class OtherTeamActivity extends BaseToolActivity {
    List<Fragment> list = new ArrayList<>();
    @BindView(R.id.one)
    TextView one;
    @BindView(R.id.one_lin)
    LinearLayout oneLin;
    @BindView(R.id.two)
    TextView two;
    @BindView(R.id.two_lin)
    LinearLayout twoLin;
    @BindView(R.id.three)
    TextView three;
    @BindView(R.id.three_lin)
    LinearLayout threeLin;
    @BindView(R.id.viewPager)
    CustomViewPager viewPager;
    String uid;
    String tid;

    @Override
    protected String setTitle() {
        return "他人团队";
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_other_team;
    }

    @Override
    protected void initData() {
        uid = getIntent().getStringExtra("uid");
        if (uid == null) {
            ShowToast("暂无该团队数据");
            finish();
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("uid", uid);//这里的values就是我们要传的值
        OtherOneFragment otherOneFragment = new OtherOneFragment();
        otherOneFragment.setArguments(bundle);
        OtherTwoFragment otherTwoFragment = new OtherTwoFragment();
        otherTwoFragment.setArguments(bundle);
        OtherThreeFragment otherThreeFragment = new OtherThreeFragment();
        otherThreeFragment.setArguments(bundle);
        list.add(otherOneFragment);
        list.add(otherTwoFragment);
        list.add(otherThreeFragment);
        viewPager.setAdapter(new FragmentViewPagerAdapter(getSupportFragmentManager(), list));

        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("uid", uid);//请求参数一
        formBuilder.add("type", "1");//请求参数二
        formBuilder.add("p", "1");//请求参数二
        Post(HttpUntil.GetIntent().team(), formBuilder, 1, false);
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        if (codeIsOne(str, false)) {
            TeamData data = new Gson().fromJson(str, TeamData.class);
            one.setText(data.getData().getInfo().getCount().getFirst_partner() + "");
            two.setText(data.getData().getInfo().getCount().getSecond_partner() + "");
            three.setText(data.getData().getInfo().getCount().getThird_partner() + "");
        }
    }


    @OnClick({R.id.one_lin, R.id.two_lin, R.id.three_lin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.one_lin:
                oneLin.setBackgroundColor(getResources().getColor(R.color.bantoumingblue));
                twoLin.setBackgroundColor(getResources().getColor(R.color.white));
                threeLin.setBackgroundColor(getResources().getColor(R.color.white));
                viewPager.setCurrentItem(0);
                break;
            case R.id.two_lin:
                oneLin.setBackgroundColor(getResources().getColor(R.color.white));
                twoLin.setBackgroundColor(getResources().getColor(R.color.bantoumingblue));
                threeLin.setBackgroundColor(getResources().getColor(R.color.white));
                viewPager.setCurrentItem(1);
                break;
            case R.id.three_lin:
                oneLin.setBackgroundColor(getResources().getColor(R.color.white));
                twoLin.setBackgroundColor(getResources().getColor(R.color.white));
                threeLin.setBackgroundColor(getResources().getColor(R.color.bantoumingblue));
                viewPager.setCurrentItem(2);
                break;
        }
    }
}
