package com.test720.grasshoppercollege.module.baiBianTingLi;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.BaseTiMuActivity;
import com.test720.grasshoppercollege.HttpBean.BaiBianTingLiTiMuData;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.myViews.CustomViewPager;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;

public class BaiBianTiMuActivity extends BaseTiMuActivity {


    @BindView(R.id.viewPager)
    CustomViewPager viewPager;

    List<BaseBaiBianTiMuFragment> list = new ArrayList<>();

    int feetType = 1;//题目类型
    int listening_id = 1;

    public int getFeetType() {
        return feetType;
    }

    public void setFeetType(int feetType) {
        this.feetType = feetType;
    }

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_bai_bian_ti_mu;
    }

    @Override
    protected void initData() {
        setType(14);//类型

        Intent intent = getIntent();
        feetType = intent.getIntExtra("type", 1);
        listening_id = intent.getIntExtra("id", 1);
        setId(listening_id + "");

        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("type", feetType + "");//请求参数一
        formBuilder.add("listening_id", listening_id + "");//请求参数一
        Post(HttpUntil.GetIntent().EnListeningcontent(), formBuilder, 1);
        viewPager.setOffscreenPageLimit(1);
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        if (codeIsOne(str, false)) {
            BaiBianTingLiTiMuData data = new Gson().fromJson(str, BaiBianTingLiTiMuData.class);
            for (int i = 0; i < data.getData().size(); i++) {
                switch (feetType) {
                    case 1:
                        BaiBianDanXuanFragment baiBianDanXuanFragment = new BaiBianDanXuanFragment();
                        baiBianDanXuanFragment.setViewPager(viewPager);
                        baiBianDanXuanFragment.setPostion(i);
                        baiBianDanXuanFragment.setDataBean(data.getData().get(i));
                        baiBianDanXuanFragment.setBaseTiMuActivity(this);
                        list.add(baiBianDanXuanFragment);
                        break;
                    case 2:
                        BaiBianJuDanXuanFragment baiBianJuDanXuanFragment = new BaiBianJuDanXuanFragment();
                        baiBianJuDanXuanFragment.setViewPager(viewPager);
                        baiBianJuDanXuanFragment.setPostion(i);
                        baiBianJuDanXuanFragment.setDataBean(data.getData().get(i));
                        baiBianJuDanXuanFragment.setBaseTiMuActivity(this);
                        list.add(baiBianJuDanXuanFragment);
                        break;
                    case 3:
                        BaiBianTianKongFragment baiBianTianKongFragment = new BaiBianTianKongFragment();
                        baiBianTianKongFragment.setViewPager(viewPager);
                        baiBianTianKongFragment.setPostion(i);
                        baiBianTianKongFragment.setBaseTiMuActivity(this);
                        baiBianTianKongFragment.setDataBean(data.getData().get(i));
                        list.add(baiBianTianKongFragment);
                        break;
                    case 4:
                        BaiBianFourFragment baiBianFourFragment1 = new BaiBianFourFragment();
                        baiBianFourFragment1.setViewPager(viewPager);
                        baiBianFourFragment1.setPostion(i);
                        baiBianFourFragment1.setBaseTiMuActivity(this);
                        baiBianFourFragment1.setDataBean(data.getData().get(i));
                        list.add(baiBianFourFragment1);
                        break;
                    case 5:
                        BaiBianFourFragment baiBianFourFragment = new BaiBianFourFragment();
                        baiBianFourFragment.setViewPager(viewPager);
                        baiBianFourFragment.setPostion(i);
                        baiBianFourFragment.setBaseTiMuActivity(this);
                        baiBianFourFragment.setDataBean(data.getData().get(i));
                        list.add(baiBianFourFragment);
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
    }


    @OnClick({R.id.back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
        }
    }
}
