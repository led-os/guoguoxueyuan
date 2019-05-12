package com.test720.grasshoppercollege.module.meiRiYiJu;

import android.support.v4.app.Fragment;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.FragmentViewPagerAdapter;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.HttpBean.MeiRiGameData;
import com.test720.grasshoppercollege.HttpBean.MeiRiYiJuLianXiAndGameData;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.meiRiYiJu.game.MeiRiYiJuTianKongYouXiFragment;
import com.test720.grasshoppercollege.module.meiRiYiJu.lianxi.MeiRiLianXiFragment;
import com.test720.grasshoppercollege.myViews.CustomViewPager;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.FormBody;

public class MeiRiTianZiActivity extends BaseToolActivity {

    @BindView(R.id.viewPager)
    CustomViewPager viewPager;
    List<Fragment> list = new ArrayList<>();
    String type = "";

    @Override
    protected String setTitle() {
        return "每日一句";
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_mei_ri_tian_zi;
    }

    @Override
    protected void initData() {
        type = getIntent().getStringExtra("type");
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("type", type);
        builder.add("day_id", getIntent().getStringExtra("day_id"));
        Post(HttpUntil.GetIntent().EnEveryDaygame(), builder, 1, false);

    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        switch (what) {
            case 1:
                if (codeIsOne(str, false)) {

                    switch (type) {
                        case "2":
                            MeiRiGameData data = new Gson().fromJson(str, MeiRiGameData.class);
                            MeiRiYiJuTianKongYouXiFragment meiRiYiJuTianKongYouXiFragment = new MeiRiYiJuTianKongYouXiFragment();
                            meiRiYiJuTianKongYouXiFragment.setData(data);
                            list.add(meiRiYiJuTianKongYouXiFragment);
                            break;
                        case "3":
                            MeiRiYiJuLianXiAndGameData data1 = new Gson().fromJson(str, MeiRiYiJuLianXiAndGameData.class);
                            MeiRiLianXiFragment meiRiLianXiFragment = new MeiRiLianXiFragment();
                            meiRiLianXiFragment.setData(data1);
                            list.add(meiRiLianXiFragment);

                            break;
                    }

                    viewPager.setAdapter(new FragmentViewPagerAdapter(getSupportFragmentManager(), list));

                }
                break;
        }
    }


}
