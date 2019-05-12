package com.test720.grasshoppercollege.module.shuXue.jiChu.tiXing;

import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.BaseTiMuOkFragment;
import com.test720.grasshoppercollege.HttpBean.MathData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.untils.HttpUntil;

/**
 * Created by 水东流 on 2018/7/2.
 */

public abstract class BaseMathTiMuFragment extends BaseTiMuOkFragment {


    @Override
    public String corrId() {
        return null;
    }

    @Override
    public String questionId() {
        return getDataBean().getQuestion_id();
    }

    @Override
    public String status() {
        return null;
    }


    @Override
    public void TongGuan() {
        if (getBaseTiMuActivity().getType() == 34) {
            if (getBaseTiMuActivity().getId() == null) return;
            HttpParams httpParams = new HttpParams();
            httpParams.put("uid", MyApplication.getmInstance().getUid());
            httpParams.put("fraction", getBaseTiMuActivity().getFen());
            httpParams.put("poetry_id", getBaseTiMuActivity().getId());
            post(HttpUntil.GetIntent().ChineseAncientPoetrycompleted(), httpParams, 124, false);
        }
    }

    @Override
    public String GXShareTitile() {
        if (getBaseTiMuActivity().getType() == 19) {
            return "数学基础练习";
        } else if (getBaseTiMuActivity().getType() == 20) {

        } else if (getBaseTiMuActivity().getType() == 15) {
            return "语文阅读训练";
        } else if (getBaseTiMuActivity().getType() == 34) {
            return "古诗词练习";
        }
        return null;
    }

    MathData.DataBean dataBean;

    public MathData.DataBean getDataBean() {
        return dataBean;
    }

    public void setDataBean(MathData.DataBean dataBean) {
        this.dataBean = dataBean;
    }
}
