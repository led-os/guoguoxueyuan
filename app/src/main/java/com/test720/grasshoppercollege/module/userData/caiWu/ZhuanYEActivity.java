package com.test720.grasshoppercollege.module.userData.caiWu;

import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.BaseZhuanYEActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.untils.HttpUntil;

public class ZhuanYEActivity extends BaseZhuanYEActivity {
    public static final int DAILI = 1;//代理金额转余额
    public static final int YONGJIN = 2;//佣金转余额
    int type = -1;//类型

    @Override
    protected void initData() {
        type = getIntent().getIntExtra("type", -1);
    }

    @Override
    public void payOk(String pwd, String money) {
        if (type == DAILI) {
            HttpParams httpParams = new HttpParams();
            httpParams.put("uid", MyApplication.getmInstance().getUid());
            httpParams.put("agent_id", getIntent().getStringExtra("agent_id"));
            httpParams.put("draw_money", money);
            httpParams.put("password", pwd);
            postResponse(HttpUntil.GetIntent().UseragentIntoBalance(), httpParams, 1, true);
        } else if (type == YONGJIN) {
            HttpParams httpParams = new HttpParams();
            httpParams.put("uid", MyApplication.getmInstance().getUid());
            httpParams.put("draw_money", money);
            httpParams.put("password", pwd);
            postResponse(HttpUntil.GetIntent().getIP() + "small.php/User/commissionIntoBalance", httpParams, 1, true);
        }
    }

}
