package com.test720.grasshoppercollege.module.shuXue.aoShuLianXi;

import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.FragmentViewPagerAdapter;
import com.test720.grasshoppercollege.BaseTiMuActivity;
import com.test720.grasshoppercollege.HttpBean.AoShuLianXiNeiRongData;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.myViews.CustomViewPager;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;

public class AoShuLianXiNeiRongActivity extends BaseTiMuActivity {

    @BindView(R.id.viewPager)
    CustomViewPager viewPager;
    @BindView(R.id.back)
    ImageView back;
    List<Fragment> list = new ArrayList<>();

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_ao_shu_lian_xi_nei_rong;
    }

    @Override
    protected void initData() {
        setType(18);
        if (getIntent().getStringExtra("eq_id") != null) {
            setId(getIntent().getStringExtra("eq_id"));
            setDingZheng(true);
            FormBody.Builder builder = new FormBody.Builder();
            builder.add("eq_id", getIntent().getStringExtra("eq_id"));
            builder.add("practice", "2");
            Post(HttpUntil.GetIntent().MathOlyPracticecontent(), builder, 2);
        } else {
            setDingZheng(false);
            setId(getIntent().getStringExtra("mop_id"));
            FormBody.Builder builder = new FormBody.Builder();
            builder.add("mop_id", getIntent().getStringExtra("mop_id"));
            builder.add("practice", "1");
            Post(HttpUntil.GetIntent().MathOlyPracticecontent(), builder, 1);
        }
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        if (codeIsOne(str, false)) {
            AoShuLianXiNeiRongData data = new Gson().fromJson(str, AoShuLianXiNeiRongData.class);
            for (int i = 0; i < data.getData().size(); i++) {
                AoShuLianXiDanXuanFragment fragment = new AoShuLianXiDanXuanFragment();
                fragment.setListBean(data.getData().get(i));
                fragment.setBaseTiMuActivity(this);
                fragment.setViewPager(viewPager);
                list.add(fragment);
            }
            viewPager.setOffscreenPageLimit(0);
            viewPager.setAdapter(new FragmentViewPagerAdapter(getSupportFragmentManager(), list));
        }

    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
