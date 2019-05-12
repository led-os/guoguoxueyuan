package com.test720.grasshoppercollege.module.guShiCi.shou;

import android.text.TextUtils;

import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.MyApplication;

/**
 * Created by 水东流 on 2018/7/11.
 */

public class TypeFragment extends BaseGuSiShouFragment {
    @Override
    public HttpParams httpParams() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        httpParams.put("type", 2);
        if(getViewHolder()!=null&&!TextUtils.isEmpty(getViewHolder().edittext.getText())){
            httpParams.put("key", getViewHolder().edittext.getText().toString());
        }
        httpParams.put("p", page);
        return httpParams;
    }
}
