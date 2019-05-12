package com.test720.grasshoppercollege.module.userData.myDingDan;

import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.untils.HttpUntil;

/**
 * Created by 水东流 on 2018/7/16.
 */

public class DaiFaHuoFragment extends BaseDingDanFragment {
    @Override
    public String status() {
        return "1";
    }

    @Override
    public void oneClick(DingDanData.DataBean dataBean, int pos) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("order_num", dataBean.getOrder_num());
        httpParams.put("gid", dataBean.getGoods().get(0).getGid());
        post(HttpUntil.GetIntent().Orderurge(), httpParams, 1);
    }

    @Override
    public void twoClick(DingDanData.DataBean dataBean, int pos) {

    }

    @Override
    public void threeClick(DingDanData.DataBean dataBean, int pos) {

    }


    @Override
    public void setListener() {

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
