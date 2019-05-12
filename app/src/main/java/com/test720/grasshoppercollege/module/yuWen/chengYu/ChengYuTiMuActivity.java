package com.test720.grasshoppercollege.module.yuWen.chengYu;

import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.FragmentViewPagerAdapter;
import com.test720.grasshoppercollege.BaseTiMuActivity;
import com.test720.grasshoppercollege.HttpBean.ChengYUTiMuData;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.yuWen.chengYu.chengYuBuZi.ChengYuBuZiFragment;
import com.test720.grasshoppercollege.myViews.CustomViewPager;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;

public class ChengYuTiMuActivity extends BaseTiMuActivity {


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
        return R.layout.activity_bu_ziti_mu;
    }

    @Override
    protected void initData() {
        setType(31);
        setId(getIntent().getIntExtra("idiom_id", 1) + "");//保存id
        setType(getIntent().getIntExtra("type", 1));
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("idiom_id", getIntent().getIntExtra("idiom_id", 1) + "");
        Post(HttpUntil.GetIntent().ChineseIdiomidiomContent(), builder, 1);
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        switch (what) {
            case 1:
                if (codeIsOne(str, false)) {
                    ChengYUTiMuData data = new Gson().fromJson(str, ChengYUTiMuData.class);
                    for (int i = 0; i < data.getData().size(); i++) {
                        ChengYuBuZiFragment chengYuBuZiFragment = new ChengYuBuZiFragment();
                        chengYuBuZiFragment.setViewPager(viewPager);
                        chengYuBuZiFragment.setBaseTiMuActivity(this);
                        chengYuBuZiFragment.setDataBean(data.getData().get(i));
                        list.add(chengYuBuZiFragment);
                    }
                    viewPager.setAdapter(new FragmentViewPagerAdapter(getSupportFragmentManager(), list));
                }
                break;
        }
    }


    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
