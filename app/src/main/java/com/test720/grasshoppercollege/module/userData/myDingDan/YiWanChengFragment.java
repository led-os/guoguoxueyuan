package com.test720.grasshoppercollege.module.userData.myDingDan;

import android.content.Intent;

/**
 * Created by 水东流 on 2018/7/16.
 */

public class YiWanChengFragment extends BaseDingDanFragment {
    @Override
    public String status() {
        return "4";
    }

    @Override
    public void oneClick(DingDanData.DataBean dataBean, int pos) {

    }

    @Override
    public void twoClick(DingDanData.DataBean dataBean, int pos) {
        Intent intent = new Intent(getActivity(), DingDanPingJiaActivity.class);
        intent.putExtra("order_num", dataBean.getOrder_num());
        intent.putExtra("gid", dataBean.getGoods().get(pos).getGid());
        startActivityForResult(intent,1);
    }

    @Override
    public void threeClick(DingDanData.DataBean dataBean, int pos) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1&&resultCode==1){
            ShuaXin();
        }
    }

    @Override
    public void setListener() {

    }
}
