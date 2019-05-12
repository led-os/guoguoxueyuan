package com.test720.grasshoppercollege.module.yuFaTest;

import android.view.View;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.YuFaTestAdapter;
import com.test720.grasshoppercollege.BaseTiMuActivity;
import com.test720.grasshoppercollege.HttpBean.YuFaTextTiMuData;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.fragment.BaseTiMuFragment;
import com.test720.grasshoppercollege.myViews.CustomViewPager;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;

public class YuFaTestTiMuActivity extends BaseTiMuActivity {


    @BindView(R.id.viewPager)
    CustomViewPager viewPager;
    List<BaseTiMuFragment> list = new ArrayList<>();

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_yu_fa_test_ti_mu;
    }

    @Override
    protected void initData() {
        setType(24);
        FormBody.Builder formBuilder = new FormBody.Builder();
        if (getIntent().getStringExtra("book_id") != null) {
            setDingZheng(false);
            setId(getIntent().getStringExtra("book_id"));
            formBuilder.add("book_id", getIntent().getStringExtra("book_id"));//请求参数一
            formBuilder.add("practice", "1");//请求参数二
        }
        if (getIntent().getStringExtra("eq_id") != null) {
            setDingZheng(true);
            formBuilder.add("eq_id", getIntent().getStringExtra("eq_id"));//请求参数一
            formBuilder.add("practice", "2");//请求参数二
        }
        Post(HttpUntil.GetIntent().GrammarPracticecontent(), formBuilder, 1);


    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        if (codeIsOne(str, false)) {
            YuFaTextTiMuData data = new Gson().fromJson(str, YuFaTextTiMuData.class);
            for (int i = 0; i < data.getData().size(); i++) {
                switch (data.getData().get(i).getQuestion_type()) {
                    case "1":
                        YuFaDanXuanFragment yuFaDanXuanFragment = new YuFaDanXuanFragment();
                        yuFaDanXuanFragment.setViewPager(viewPager);
                        yuFaDanXuanFragment.setDataBean(data.getData().get(i));
                        yuFaDanXuanFragment.setBaseTiMuActivity(this);
                        list.add(yuFaDanXuanFragment);
                        break;
                    case "2":
                        YuFaLianXiBuCongFragment yuFaLianXiBuCongFragment = new YuFaLianXiBuCongFragment();
                        yuFaLianXiBuCongFragment.setViewPager(viewPager);
                        yuFaLianXiBuCongFragment.setBaseTiMuActivity(this);
                        yuFaLianXiBuCongFragment.setBean(data.getData().get(i));
                        list.add(yuFaLianXiBuCongFragment);
                        break;
                    case "3":
                        YuFaLianXiZhaoChaFragment yuFaLianXiZhaoChaFragment = new YuFaLianXiZhaoChaFragment();
                        yuFaLianXiZhaoChaFragment.setViewPager(viewPager);
                        yuFaLianXiZhaoChaFragment.setBaseTiMuActivity(this);
                        yuFaLianXiZhaoChaFragment.setBean(data.getData().get(i));
                        list.add(yuFaLianXiZhaoChaFragment);
                        break;
                }
            }
        }
        viewPager.setAdapter(new YuFaTestAdapter(getSupportFragmentManager(), list));
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
