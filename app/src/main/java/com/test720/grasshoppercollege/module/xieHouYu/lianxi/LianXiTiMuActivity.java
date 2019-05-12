package com.test720.grasshoppercollege.module.xieHouYu.lianxi;

import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.FragmentViewPagerAdapter;
import com.test720.grasshoppercollege.BaseTiMuActivity;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.myViews.CustomViewPager;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;

public class LianXiTiMuActivity extends BaseTiMuActivity {


    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.viewPager)
    CustomViewPager viewPager;
    List<Fragment> list = new ArrayList<>();

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_lian_xi_ti_mu;
    }

    @Override
    protected void initData() {
        setType(26);
        setId(getIntent().getStringExtra("xhy_id"));
        FormBody.Builder builder = new FormBody.Builder();
        if (getIntent().getStringExtra("practice") != null) {
            builder.add("eq_id", getId());
            builder.add("practice", getIntent().getStringExtra("practice"));
            setDingZheng(true);
        } else {
            builder.add("xhy_id", getId());
            setDingZheng(false);
        }
        Post(HttpUntil.GetIntent().ChineseXieHouYucontent(), builder, 1);

    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        if (codeIsOne(str, false)) {
            LianXIData data = new Gson().fromJson(str, LianXIData.class);
            for (int i = 0; i < data.getData().size(); i++) {
                LianXiFragment lianXiFragment = new LianXiFragment();
                lianXiFragment.setViewPager(viewPager);
                lianXiFragment.setBaseTiMuActivity(this);
                lianXiFragment.setDataBean(data.getData().get(i));
                list.add(lianXiFragment);
            }
            viewPager.setAdapter(new FragmentViewPagerAdapter(getSupportFragmentManager(), list));
        }
    }


    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
