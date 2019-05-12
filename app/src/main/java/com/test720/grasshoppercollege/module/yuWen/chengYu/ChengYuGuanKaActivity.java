package com.test720.grasshoppercollege.module.yuWen.chengYu;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.FragmentViewPagerAdapter;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.HttpBean.ChengYuGuanKaData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;

public class ChengYuGuanKaActivity extends BaseToolActivity {

    @BindView(R.id.viewPager)
    public ViewPager viewPager;
    List<Fragment> list = new ArrayList<>();
    int type = 31;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.left)
    ImageView left;
    @BindView(R.id.right)
    ImageView right;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_cheng_yu_guan_ka;
    }

    @Override
    protected void initData() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    left.setVisibility(View.GONE);
                    right.setVisibility(View.VISIBLE);
                } else if (position == viewPager.getAdapter().getCount() - 1) {
                    right.setVisibility(View.GONE);
                    left.setVisibility(View.VISIBLE);
                } else {
                    right.setVisibility(View.VISIBLE);
                    left.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        setType(getIntent().getIntExtra("type", 31));
        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("uid", MyApplication.getmInstance().getUid());//请求参数一
        formBuilder.add("type", getType() + "");
        Post(HttpUntil.GetIntent().ChineseIdiomlookPic(), formBuilder, 1);
    }


    @Override
    public void GetHttpBackStr(String str, int what) {
        if (codeIsOne(str, false)) {
            ChengYuGuanKaData data = new Gson().fromJson(str, ChengYuGuanKaData.class);
            list.clear();
            for (int i = 0; i < data.getData().size(); i++) {
                ChengYuGuanKaFragment yueDuShouFragment = new ChengYuGuanKaFragment();
                yueDuShouFragment.setIndex(i);
                yueDuShouFragment.setChengYuGuanKaActivity(this);
                list.add(yueDuShouFragment);
            }
            viewPager.setAdapter(new FragmentViewPagerAdapter(getSupportFragmentManager(), list));
            viewPager.setOffscreenPageLimit(data.getData().size());
        }
    }


    @OnClick({R.id.back, R.id.left, R.id.right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.left:
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                break;
            case R.id.right:
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                break;
        }
    }
}