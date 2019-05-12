package com.test720.grasshoppercollege.module.englishYueDu;

import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.FragmentViewPagerAdapter;
import com.test720.grasshoppercollege.BaseTiMuActivity;
import com.test720.grasshoppercollege.HttpBean.ReadTiMuData;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.myViews.CustomViewPager;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;

public class YueDuTIMuActivity extends BaseTiMuActivity {


    @BindView(R.id.viewPager)
    CustomViewPager viewPager;
    @BindView(R.id.back)
    ImageView back;
    List<Fragment> list = new ArrayList<>();
    FragmentViewPagerAdapter adapter;

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_yue_du_timu;
    }

    @Override
    protected void initData() {
        setType(13);
        setId(getIntent().getStringExtra("read_id"));
        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("read_id", getId());
        Post(HttpUntil.GetIntent().EnReadcontent(), formBuilder, 1);
    }


    @Override
    public void GetHttpBackStr(String str, int what) {
        if (codeIsOne(str, false)) {
            ReadTiMuData data = new Gson().fromJson(str, ReadTiMuData.class);
            for (int i = 0; i < data.getData().getQuestion().size(); i++) {
                switch (data.getData().getQuestion().get(i).getType()) {
                    case "1":
                        YueDuDanXuanFragment yueDuDanXuanFragment = new YueDuDanXuanFragment();
                        yueDuDanXuanFragment.setQuestionBean(data.getData().getQuestion().get(i));
                        yueDuDanXuanFragment.setBaseTiMuActivity(this);
                        yueDuDanXuanFragment.setViewPager(viewPager);
                        list.add(yueDuDanXuanFragment);
                        break;
                    case "2":
                        ReadPanDuanFragment readPanDuanFragment = new ReadPanDuanFragment();
                        readPanDuanFragment.setQuestionBean(data.getData().getQuestion().get(i));
                        readPanDuanFragment.setBaseTiMuActivity(this);
                        readPanDuanFragment.setViewPager(viewPager);
                        list.add(readPanDuanFragment);
                        break;
                }
            }
            adapter = new FragmentViewPagerAdapter(getSupportFragmentManager(), list);
            viewPager.setAdapter(adapter);
        }
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }


}
