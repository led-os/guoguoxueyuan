package com.test720.grasshoppercollege.module.youEr.baoBao;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.youEr.baoBao.fragment.ShouData;
import com.test720.grasshoppercollege.module.youEr.baoBao.fragment.ShouFragment;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;

public class BaoBaoShouActivity extends BaseToolActivity {


    @BindView(R.id.zuo)
    Button zuo;
    @BindView(R.id.you)
    Button you;

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    public void setSys() {
        super.setSys();
        noSuPing = true;
        isFull = true;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_bao_bao_shou;
    }

    @Override
    protected void initData() {
        zuo.setVisibility(View.GONE);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (list.size() == position + 1) {
                    you.setVisibility(View.GONE);
                    zuo.setVisibility(View.VISIBLE);
                } else if (position == 0) {
                    zuo.setVisibility(View.GONE);
                    you.setVisibility(View.VISIBLE);
                } else {
                    you.setVisibility(View.VISIBLE);
                    zuo.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("type", YouErBaoBaoShouActivity.type + "");
        builder.add("uid", MyApplication.getmInstance().getUid());
        Post(HttpUntil.GetIntent().ChildBabyindex(), builder, 1);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        DismissDialong();
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        DismissDialong();
        if (codeIsOne(str, false)) {
            ShouData data = new Gson().fromJson(str, ShouData.class);
            for (int i = 0; i < data.getData().size(); i++) {
                ShouFragment shouFragment = new ShouFragment();
                shouFragment.setIndex(i);
                list.add(shouFragment);
            }
            viewPager.setOffscreenPageLimit(data.getData().size() / 2);
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


    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }


    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.back)
    ImageView back;
    List<Fragment> list = new ArrayList<>();


    @OnClick({R.id.zuo, R.id.you})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zuo:
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                break;
            case R.id.you:
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                break;
        }
    }
}
