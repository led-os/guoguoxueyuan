package com.test720.grasshoppercollege.module.shangCheng.fragment;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.test720.grasshoppercollege.BaseFragment;
import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.userData.myDingDan.DaiFaHuoFragment;
import com.test720.grasshoppercollege.module.userData.myDingDan.DaiFuKuanFragment;
import com.test720.grasshoppercollege.module.userData.myDingDan.DaiShouHuoFragment;
import com.test720.grasshoppercollege.module.userData.myDingDan.TuiHuoShouHouFragment;
import com.test720.grasshoppercollege.module.userData.myDingDan.YiWanChengFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 佛祖保佑        永无BUG
 * 佛曰:
 * 程序园里程序天，程序天里程序员；
 * 程序猿人写程序，又拿程序换肉钱。
 * 肉饱继续桌前坐，饱暖还是桌前眠；
 * 半迷半醒日复日，码上码下年复年。
 * 但愿叱咤互联世，不愿搬砖码当前；
 * 诸葛周瑜算世事，我算需求得加钱。
 * 别人笑我忒直男，我说自己是程猿；
 * 但见成都府国内，处处地地程序员。
 * 作者：水东流 编于 2018/10/8
 */
public class MyOrderFragment extends BaseFragment {
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.activity_my_order)
    LinearLayout activityMyOrder;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.right)
    RelativeLayout share;
    private List<String> mTitle = new ArrayList<String>();
    private List<Fragment> mFragment = new ArrayList<Fragment>();
    int index = 1;

    @Override
    public void initData() {
        title.setText("我的订单");

        mTitle.add("待付款");
        mTitle.add("待发货");
        mTitle.add("待收货");
        mTitle.add("退货/售后");
        mTitle.add("已完成");

        mFragment.add(new DaiFuKuanFragment());
        mFragment.add(new DaiFaHuoFragment());
        mFragment.add(new DaiShouHuoFragment());
        mFragment.add(new TuiHuoShouHouFragment());
        mFragment.add(new YiWanChengFragment());

        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragment.get(position);
            }

            @Override
            public int getCount() {
                return mFragment.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return mTitle.get(position);
            }
        });
        //为TabLayout设置ViewPager
        tabs.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(index);
    }

    @Override
    public void setListener() {

    }


    @Override
    public void onEvent(CurrencyEvent event) {
        Log.i("lxl", "event" + event.getWhat());
        if (event.getWhat() == CurrencyEvent.MYORDER) {
            viewPager.setCurrentItem(1);
        }
    }

    @Override
    public int setlayoutResID() {
        return R.layout.activity_my_order;
    }

    @OnClick(R.id.back)
    public void onClick() {
        mContext.finish();
    }

    @Override
    public void getSuccess(String s, int what) {

    }
}
