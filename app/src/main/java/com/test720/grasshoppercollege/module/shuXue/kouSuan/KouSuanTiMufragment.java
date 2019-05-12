package com.test720.grasshoppercollege.module.shuXue.kouSuan;

import com.test720.grasshoppercollege.BaseTiMuOkFragment;
import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.HttpBean.KouSuanData;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by 水东流 on 2018/7/27.
 */

public abstract class KouSuanTiMufragment extends BaseTiMuOkFragment {
    KouSuanData.DataBean.ListBean listBean;
    int index = 0;//当前题目所在脚本


    public KouSuanData.DataBean.ListBean getListBean() {
        return listBean;
    }

    public void setListBean(KouSuanData.DataBean.ListBean listBean) {
        this.listBean = listBean;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public void TongGuan() {

    }

    @Override
    public String GXShareTitile() {
        return "口算练习";
    }

    @Override
    public String corrId() {
        return getListBean().getCorr_id();
    }

    @Override
    public String questionId() {
        return getListBean().getQuestion_id();
    }

    @Override
    public void onEvent(CurrencyEvent event) {
        super.onEvent(event);
        if (event.getWhat() == CurrencyEvent.KOUSUANTIME && isUserCanSee) {
            ShowToast("时间到");
            AddGuoGuoDou();
        }
    }

    @Override
    public void AddGuoGuoDou() {
        super.AddGuoGuoDou();
        EventBus.getDefault().post(new CurrencyEvent(CurrencyEvent.KOUSUANOVER, "已提交"));
    }

    boolean isUserCanSee = false;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isUserCanSee = isVisibleToUser;
    }
}
