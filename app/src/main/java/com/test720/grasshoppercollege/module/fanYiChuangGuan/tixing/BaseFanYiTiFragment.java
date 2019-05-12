package com.test720.grasshoppercollege.module.fanYiChuangGuan.tixing;

import com.test720.grasshoppercollege.BaseTiMuOkFragment;
import com.test720.grasshoppercollege.HttpBean.FanYiNeiRongdata;

/**
 * Created by 水东流 on 2018/6/26.
 */

public abstract class BaseFanYiTiFragment extends BaseTiMuOkFragment {

    public FanYiNeiRongdata.DataBean.ListBean getBean() {
        return bean;
    }

    public void setBean(FanYiNeiRongdata.DataBean.ListBean bean) {
        this.bean = bean;
    }
    FanYiNeiRongdata.DataBean.ListBean bean;//当前页数据

    @Override
    public String corrId() {
        return getBean().getCorr_id();
    }

    @Override
    public String questionId() {
        return getBean().getQuestion_id();
    }

    @Override
    public String GXShareTitile() {
        return "翻译闯关";
    }
}
