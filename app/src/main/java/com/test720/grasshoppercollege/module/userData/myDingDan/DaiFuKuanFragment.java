package com.test720.grasshoppercollege.module.userData.myDingDan;


import android.content.Intent;

import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.module.shangCheng.QueRenDingDanActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;

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
 * Created by 水东流 on 2018/3/9 0009.
 */

public class DaiFuKuanFragment extends BaseDingDanFragment {

    @Override
    public void setListener() {

    }

    @Override
    public String status() {
        return "0";
    }

    @Override
    public void oneClick(DingDanData.DataBean dataBean, int pos) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("order_num", dataBean.getOrder_num());
        post(HttpUntil.GetIntent().ordercancelOrder(), httpParams, 1);
    }

    @Override
    public void twoClick(DingDanData.DataBean dataBean, int pos) {
        Intent intent = new Intent(getActivity(), QueRenDingDanActivity.class);
        intent.putExtra("order_num", dataBean.getOrder_num());
        jumpToActivity(intent, false);
    }

    @Override
    public void threeClick(DingDanData.DataBean dataBean, int pos) {

    }

    @Override
    public void getSuccess(String str, int what) {
        super.getSuccess(str, what);
        switch (what) {
            case 1:
                if (codeIsOne(str)) {
                    ShuaXin();
                }
                break;
        }
    }

}
