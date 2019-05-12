package com.test720.grasshoppercollege.module.xieHouYu;

import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.FragmentViewPagerAdapter;
import com.test720.grasshoppercollege.BaseTiMuActivity;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.xieHouYu.tongguan.TongGuanData;
import com.test720.grasshoppercollege.module.xieHouYu.tongguan.XieHouTongGuanFragment;
import com.test720.grasshoppercollege.myViews.CustomViewPager;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;

public class TongGuanActivity extends BaseTiMuActivity {



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
        return R.layout.activity_tong_guan;
    }

    @Override
    protected void initData() {
        setType(29);
        setId(getIntent().getStringExtra("xhy_id"));
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("xhy_id", getId());
        Post(HttpUntil.GetIntent().ChineseXieHouYuadoptContent(), builder, 1);

    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        if (codeIsOne(str, false)) {
            TongGuanData data = new Gson().fromJson(str, TongGuanData.class);
            for (int i = 0; i < data.getData().size(); i++) {
                XieHouTongGuanFragment xieHouTongGuanFragment = new XieHouTongGuanFragment();
                xieHouTongGuanFragment.setDataBean(data.getData().get(i));
                xieHouTongGuanFragment.setViewPager(viewPager);
                xieHouTongGuanFragment.setBaseTiMuActivity(this);
                list.add(xieHouTongGuanFragment);
            }
            viewPager.setAdapter(new FragmentViewPagerAdapter(getSupportFragmentManager(), list));
        }
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }


}
