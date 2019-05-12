package com.test720.grasshoppercollege.module.tingLiTest;

import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.BaseTiMuOkFragment;
import com.test720.grasshoppercollege.HttpBean.TingLiTestTiMuData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.untils.HttpUntil;

/**
 * Created by 水东流 on 2018/7/4.
 */

public abstract class TingLiTiMuFragment extends BaseTiMuOkFragment {
    TingLiTestTiMuData.DataBean.QuestionBean bean;

    @Override
    public String corrId() {
        return getBean().getCorr_id();
    }

    @Override
    public String questionId() {
        return getBean().getQuestion_id();
    }

    @Override
    public String status() {
        return null;
    }


    @Override
    public void TongGuan() {
        if (getBaseTiMuActivity().getId() == null) return;
        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        httpParams.put("fraction", getBaseTiMuActivity().getFen());
        httpParams.put("bian_id", getBaseTiMuActivity().getId());
        post(HttpUntil.GetIntent().wordBianYinadopt(), httpParams, 124, false);
    }

    @Override
    public String GXShareTitile() {
        return "单词辩音";
    }

    public TingLiTestTiMuData.DataBean.QuestionBean getBean() {
        return bean;
    }

    public void setBean(TingLiTestTiMuData.DataBean.QuestionBean bean) {
        this.bean = bean;
    }


}
