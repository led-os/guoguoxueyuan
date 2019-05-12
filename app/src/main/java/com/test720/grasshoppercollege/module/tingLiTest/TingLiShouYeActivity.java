package com.test720.grasshoppercollege.module.tingLiTest;

import android.content.Intent;
import android.support.v4.app.Fragment;

import com.test720.grasshoppercollege.Adapter.FragmentViewPagerAdapter;
import com.test720.grasshoppercollege.PublicLianXiJinShaiActivity;
import com.test720.grasshoppercollege.module.jinShai.JinShaiShouYeActivity;

import java.util.ArrayList;

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
 * Created by 水东流 on 2018/3/21 0021.
 */

public class TingLiShouYeActivity extends PublicLianXiJinShaiActivity {


    @Override
    public void JiLei() {
        jumpToActivity(TingLiTestJiLeiActivity.class, false);
    }

    @Override
    public void jinShai() {
        Intent intent = new Intent(this, JinShaiShouYeActivity.class);
        intent.putExtra("type", "21");
        jumpToActivity(intent, false);
    }

    @Override
    protected void initData() {
        TingLiShouLianXiFragment tingLiShouLianXiFragment = new TingLiShouLianXiFragment();
        ArrayList<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(tingLiShouLianXiFragment);
        getContentFrame().setAdapter(new FragmentViewPagerAdapter(getSupportFragmentManager(), fragmentList));
    }

    @Override
    public void GetHttpBackStr(String str, int what) {

    }
}
