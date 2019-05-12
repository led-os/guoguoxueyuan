package com.test720.grasshoppercollege.module.yuWen.chengYu;

import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.BaseTiMuOkFragment;
import com.test720.grasshoppercollege.HttpBean.ChengYUTiMuData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.untils.HttpUntil;

/**
 * Created by 水东流 on 2018/7/5.
 */

public abstract class ChengYuTiMuFragment extends BaseTiMuOkFragment {

    ChengYUTiMuData.DataBean dataBean;

    public ChengYUTiMuData.DataBean getDataBean() {
        return dataBean;
    }

    public void setDataBean(ChengYUTiMuData.DataBean dataBean) {
        this.dataBean = dataBean;
    }

    @Override
    public void TongGuan() {
        HttpParams httpParams1 = new HttpParams();
        httpParams1.put("uid", MyApplication.getmInstance().getUid());
        httpParams1.put("fraction", getBaseTiMuActivity().getFen());
        httpParams1.put("idiom_id", getBaseTiMuActivity().getId());
        httpParams1.put("type", getBaseTiMuActivity().getType());
        post(HttpUntil.GetIntent().ChineseIdiomadopt(), httpParams1, 124);
    }

    @Override
    public String GXShareTitile() {
        return "成语接龙";
    }

    @Override
    public String corrId() {
        return null;
    }

    @Override
    public String questionId() {
        return getDataBean().getQuestion_id();
    }
}
