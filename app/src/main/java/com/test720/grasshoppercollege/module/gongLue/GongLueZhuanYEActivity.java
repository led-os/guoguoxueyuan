package com.test720.grasshoppercollege.module.gongLue;

import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.BaseZhuanYEActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.untils.HttpUntil;

public class GongLueZhuanYEActivity extends BaseZhuanYEActivity {


    @Override
    protected void initData() {

    }

    @Override
    public void payOk(String pwd, String money) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        httpParams.put("mg", GongLueData.getInstance().getYeType());
        httpParams.put("draw_money", money);
        httpParams.put("password", pwd);
        postResponse(HttpUntil.GetIntent().getIP() + "small.php/Strategy/intoBalance", httpParams, 1, true);
    }
}
