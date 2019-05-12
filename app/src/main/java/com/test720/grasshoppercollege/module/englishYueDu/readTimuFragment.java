package com.test720.grasshoppercollege.module.englishYueDu;

import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.BaseTiMuOkFragment;
import com.test720.grasshoppercollege.HttpBean.ReadTiMuData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.untils.HttpUntil;

/**
 * Created by 水东流 on 2018/6/26.
 */

public abstract class readTimuFragment extends BaseTiMuOkFragment {
    ReadTiMuData.DataBean.QuestionBean questionBean;

    public ReadTiMuData.DataBean.QuestionBean getQuestionBean() {
        return questionBean;
    }

    public void setQuestionBean(ReadTiMuData.DataBean.QuestionBean questionBean) {
        this.questionBean = questionBean;
    }

    @Override
    public String corrId() {
        return null;
    }

    @Override
    public String questionId() {
        return null;
    }

    @Override
    public void TongGuan() {
        HttpParams httpParams1 = new HttpParams();
        httpParams1.put("uid", MyApplication.getmInstance().getUid());
        httpParams1.put("fraction", getBaseTiMuActivity().getFen());
        httpParams1.put("read_id", getBaseTiMuActivity().getId());
        post(HttpUntil.GetIntent().EnReadadopt(), httpParams1, 123);
    }

    @Override
    public String GXShareTitile() {
        return "阅读训练";
    }


}
