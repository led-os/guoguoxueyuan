package com.test720.grasshoppercollege.module.gongLue;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.widget.TextView;

import com.test720.grasshoppercollege.BaseFragment;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.gongLue.peiXun.PeiXunShouFragment;
import com.test720.grasshoppercollege.module.gongLue.teacher.TeacherShouFragment;
import com.test720.grasshoppercollege.module.gongLue.youEr.YouErShouFragment;
import com.test720.grasshoppercollege.myViews.CustomViewPager;
import com.test720.grasshoppercollege.untils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * _oo0oo_
 * o8888888o
 * 88" . "88
 * (| -_- |)
 * 0\  =  /0
 * ___/`---'\___
 * .' \\|     |// '.
 * / \\|||  :  |||// \
 * / _||||| -卍-|||||- \
 * |   | \\\  -  /// |   |
 * | \_|  ''\---/''  |_/ |
 * \  .-\__  '-'  ___/-. /
 * ___'. .'  /--.--\  `. .'___
 * ."" '<  `.___\_<|>_/___.' >' "".
 * | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 * \  \ `_.   \_ __\ /__ _/   .-` /  /
 * =====`-.____`.___ \_____/___.-`___.-'=====
 * `=---='
 * 佛祖保佑        永无BUG
 * 佛曰:
 * 程序园里程序天，程序天里程序员；
 * 程序猿人写程序，又拿程序换肉钱。
 * 肉饱继续桌前坐，饱暖还是桌前眠；
 * 半迷半醒日复日，码上码下年复年。
 * 但愿叱咤互联世，不愿搬砖码当前；
 * 诸葛周瑜算世事，我来算出得加钱。
 * 别人笑我忒直男，我笑自己太像猿；
 * 但见成都府国内，处处地地程序员。
 * Created by 水东流 on 2018/3/22 0022.
 */

public class GongLueFragment extends BaseFragment {

    @BindView(R.id.viewPager)
    CustomViewPager viewPager;
    @BindView(R.id.one)
    TextView one;
    @BindView(R.id.two)
    TextView two;
    @BindView(R.id.three)
    TextView three;
    @BindView(R.id.four)
    TextView four;
    @BindView(R.id.five)
    TextView five;

    int index = 0;
    // 管理Fragment

    StudentFragment studentFragment;
    JIaZhangFragment jIaZhangFragment;
    TeacherShouFragment teacherShouFragment;
    YouErShouFragment youErShouFragment;
    PeiXunShouFragment peiXunShouFragment;
    List<Fragment> list = new ArrayList<>();


    @Override
    public void initData() {
        if (studentFragment == null) studentFragment = new StudentFragment();
        if (jIaZhangFragment == null) jIaZhangFragment = new JIaZhangFragment();
        if (teacherShouFragment == null) teacherShouFragment = new TeacherShouFragment();
        if (peiXunShouFragment == null) peiXunShouFragment = new PeiXunShouFragment();
        if (youErShouFragment == null) youErShouFragment = new YouErShouFragment();
        viewPager.setOffscreenPageLimit(0);
        list.clear();
        list.add(studentFragment);
        list.add(jIaZhangFragment);
        list.add(teacherShouFragment);
        list.add(peiXunShouFragment);
        list.add(youErShouFragment);

        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });

        //开启事务
        LogUtil.logError("攻略界面重置");
        LogUtil.logError("viewpager位置" + index + "");
    }

    @Override
    public void onStart() {
        super.onStart();
        switch (index) {
            case 0:
                cleanBtn(one);
                break;
            case 1:
                cleanBtn(two);
                break;
            case 2:
                cleanBtn(three);
                break;
            case 3:
                cleanBtn(four);
                break;
            case 4:
                cleanBtn(five);
                break;
        }
    }

    @Override
    public void setListener() {

    }


    @Override
    public int setlayoutResID() {
        return R.layout.fragment_gong_lu;
    }

    @Override
    public void getSuccess(String s, int what) {

    }

    @OnClick({R.id.one, R.id.two, R.id.three, R.id.four, R.id.five})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.one:
                viewPager.setCurrentItem(0);
                cleanBtn(one);
                break;
            case R.id.two:
                viewPager.setCurrentItem(1);
                cleanBtn(two);
                break;
            case R.id.three:
                viewPager.setCurrentItem(2);
                cleanBtn(three);
                break;
            case R.id.four:
                viewPager.setCurrentItem(3);
                cleanBtn(four);
                break;
            case R.id.five:
                viewPager.setCurrentItem(4);
                cleanBtn(five);
                break;
        }
    }


    public void cleanBtn(TextView button) {
        one.setTextColor(getResources().getColor(R.color.gwe));
        one.setBackgroundColor(getResources().getColor(R.color.touming));

        two.setTextColor(getResources().getColor(R.color.gwe));
        two.setBackgroundColor(getResources().getColor(R.color.touming));

        three.setBackgroundColor(getResources().getColor(R.color.touming));
        three.setTextColor(getResources().getColor(R.color.gwe));

        four.setBackgroundColor(getResources().getColor(R.color.touming));
        four.setTextColor(getResources().getColor(R.color.gwe));

        five.setBackgroundColor(getResources().getColor(R.color.touming));
        five.setTextColor(getResources().getColor(R.color.gwe));

        button.setBackgroundColor(getResources().getColor(R.color.touming));
        button.setTextColor(getResources().getColor(R.color.appColor));
        index = viewPager.getCurrentItem();
        LogUtil.logError(index + "");
    }

}
