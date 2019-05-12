package com.test720.grasshoppercollege.module.guShiCi.shou;

import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.MyApplication;

/**
 * Created by 水东流 on 2018/7/11.
 */

public class NianJiFragment extends BaseGuSiShouFragment {
    @Override
    public HttpParams httpParams() {
        isClass = true;
        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        httpParams.put("type", 1);
        httpParams.put("p", page);
        return httpParams;
    }
}
