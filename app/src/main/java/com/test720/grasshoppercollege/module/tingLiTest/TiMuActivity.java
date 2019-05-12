package com.test720.grasshoppercollege.module.tingLiTest;

import android.support.v4.app.Fragment;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.FragmentViewPagerAdapter;
import com.test720.grasshoppercollege.BaseTiMuActivity;
import com.test720.grasshoppercollege.HttpBean.TingLiTestTiMuData;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.myViews.CustomViewPager;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;

public class TiMuActivity extends BaseTiMuActivity {
    List<Fragment> list = new ArrayList<>();

    @BindView(R.id.viewpager)
    CustomViewPager viewpager;
    @BindView(R.id.activity_ti_mu)
    RelativeLayout activityTiMu;

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_ti_mu;
    }

    @Override
    protected void initData() {
        setType(21);
        if (getIntent().getStringExtra("eq_id") != null) {
            setDingZheng(true);
            FormBody.Builder formBuilder = new FormBody.Builder();
            formBuilder.add("eq_id", getIntent().getStringExtra("eq_id"));//请求参数一
            formBuilder.add("practice", "2");//请求参数一
            Post(HttpUntil.GetIntent().wordBianYincontent(), formBuilder, 1, true);
        } else {
            setId(getIntent().getStringExtra("bian_id"));
            setDingZheng(false);
            FormBody.Builder formBuilder = new FormBody.Builder();
            formBuilder.add("bian_id", getId());//请求参数一
            formBuilder.add("practice", "1");//请求参数一
            Post(HttpUntil.GetIntent().wordBianYincontent(), formBuilder, 1, true);
        }


    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        if (codeIsOne(str, false)) {
            TingLiTestTiMuData data = new Gson().fromJson(str, TingLiTestTiMuData.class);
            for (int i = 0; i < data.getData().getQuestion().size(); i++) {
                switch (data.getData().getQuestion().get(i).getQuestion_type()) {
                    case "1":
                        TiLiDanXuanFragment danXuanFragment = new TiLiDanXuanFragment();
                        danXuanFragment.setBaseTiMuActivity(this);
                        danXuanFragment.setViewPager(viewpager);
                        danXuanFragment.setBean(data.getData().getQuestion().get(i));
                        list.add(danXuanFragment);
                        break;
                    case "2":
                        GuiLeiTiFragment guiLeiTiFragment = new GuiLeiTiFragment();
                        guiLeiTiFragment.setBean(data.getData().getQuestion().get(i));
                        guiLeiTiFragment.setBaseTiMuActivity(this);
                        guiLeiTiFragment.setViewPager(viewpager);
                        list.add(guiLeiTiFragment);
                        break;
                    case "3":
                        BianYinFragment bianYinFragment = new BianYinFragment();
                        bianYinFragment.setBean(data.getData().getQuestion().get(i));
                        bianYinFragment.setBaseTiMuActivity(this);
                        bianYinFragment.setViewPager(viewpager);
                        list.add(bianYinFragment);
                        break;
                }

            }
        }
        viewpager.setAdapter(new FragmentViewPagerAdapter(getSupportFragmentManager(), list));
    }


    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }

}
