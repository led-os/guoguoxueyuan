package com.test720.grasshoppercollege.module.jinFanYiCi;

import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.FragmentViewPagerAdapter;
import com.test720.grasshoppercollege.BaseTiMuActivity;
import com.test720.grasshoppercollege.HttpBean.JInFanYITiMuData;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.myViews.CustomViewPager;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;

public class JinfanTiMuActivity extends BaseTiMuActivity {

    @BindView(R.id.viewPager)
    CustomViewPager viewPager;
    List<Fragment> list = new ArrayList<>();
    @BindView(R.id.back)
    ImageView back;

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_jinfan_ti_mu;
    }

    @Override
    protected void initData() {
        setType(25);
        setId(getIntent().getIntExtra("jf_id", -1) + "");
        FormBody.Builder formBuilder = new FormBody.Builder();

        if (getIntent().getIntExtra("id", -1) != -1) {
            setDingZheng(false);
            setId(getIntent().getIntExtra("id", 1) + "");
            formBuilder.add("jf_id", getId() + "");//请求参数一
            formBuilder.add("practice", "1");//请求参数二
        } else if (getIntent().getStringExtra("eq_id") != null) {
            setDingZheng(true);
            formBuilder.add("eq_id", getIntent().getStringExtra("eq_id"));//请求参数一
            formBuilder.add("practice", "2");//请求参数二
        }
        Post(HttpUntil.GetIntent().ChineseJFcontent(), formBuilder, 1);

    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        if (codeIsOne(str, false)) {
            JInFanYITiMuData data = new Gson().fromJson(str, JInFanYITiMuData.class);
            for (int i = 0; i < data.getData().size(); i++) {
                JinfanDanXuanFragment jinfanDanXuanFragment = new JinfanDanXuanFragment();
                jinfanDanXuanFragment.setViewPager(viewPager);
                jinfanDanXuanFragment.setBaseTiMuActivity(this);
                jinfanDanXuanFragment.setDataBean(data.getData().get(i));
                list.add(jinfanDanXuanFragment);
            }
            viewPager.setAdapter(new FragmentViewPagerAdapter(getSupportFragmentManager(), list));
        }
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
