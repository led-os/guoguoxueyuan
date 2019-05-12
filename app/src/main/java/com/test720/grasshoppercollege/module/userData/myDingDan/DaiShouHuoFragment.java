package com.test720.grasshoppercollege.module.userData.myDingDan;

import android.content.Intent;

import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.WebViewActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;

/**
 * Created by 水东流 on 2018/7/16.
 */

public class DaiShouHuoFragment extends BaseDingDanFragment {
    @Override
    public String status() {
        return "2";
    }

    @Override
    public void oneClick(DingDanData.DataBean dataBean, int pos) {
        Intent intent = new Intent(mContext, WebViewActivity.class);
        intent.putExtra("title", "物流");
        intent.putExtra("path", dataBean.getLogistics());
        jumpToActivity(intent, false);
    }

    //确认收货
    @Override
    public void twoClick(DingDanData.DataBean dataBean, int pos) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("order_goods_id", dataBean.getOrder_goods_id());
        post(HttpUntil.GetIntent().PayconfirmGoods(), httpParams, 1);
    }

    //退货
    @Override
    public void threeClick(DingDanData.DataBean dataBean, int pos) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("order_goods_id", dataBean.getOrder_goods_id());
        post(HttpUntil.GetIntent().getIP() + "small.php/Order/returnGoods", httpParams, 1);
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

    @Override
    public void setListener() {

    }
}
