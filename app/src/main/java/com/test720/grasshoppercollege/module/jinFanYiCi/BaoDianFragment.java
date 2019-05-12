package com.test720.grasshoppercollege.module.jinFanYiCi;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.test720.grasshoppercollege.Adapter.FragmentViewPagerAdapter;
import com.test720.grasshoppercollege.BaseFragment;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.myViews.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 水东流 on 2018/7/9.
 */

public class BaoDianFragment extends BaseFragment {

    @BindView(R.id.viewPager)
    CustomViewPager viewPager;
    @BindView(R.id.jin)
    TextView jin;
    @BindView(R.id.fan)
    TextView fan;

    @Override
    public void initData() {
        List<Fragment> list = new ArrayList<>();
        list.add(new JinYICiFragment());
        list.add(new FanYiCiFragment());
        viewPager.setAdapter(new FragmentViewPagerAdapter(getActivity().getSupportFragmentManager(), list));
    }

    @Override
    public void setListener() {

    }

    @Override
    public int setlayoutResID() {
        return R.layout.activity_jin_orfan;
    }

    @Override
    public void getSuccess(String s, int what) {

    }

    @OnClick({R.id.jin, R.id.fan, R.id.back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.jin:
                jin.setBackgroundResource(R.drawable.bule_back_kuang);
                jin.setTextColor(getResources().getColor(R.color.white));
                fan.setBackgroundResource(R.drawable.white_bian_kuang);
                fan.setTextColor(getResources().getColor(R.color.appColor));
                viewPager.setCurrentItem(0);
                break;
            case R.id.fan:
                fan.setBackgroundResource(R.drawable.bule_back_kuang);
                fan.setTextColor(getResources().getColor(R.color.white));
                jin.setBackgroundResource(R.drawable.white_bian_kuang);
                jin.setTextColor(getResources().getColor(R.color.appColor));
                viewPager.setCurrentItem(1);
                break;
            case R.id.back:
                mContext.finish();
                break;
        }
    }
}
