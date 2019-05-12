package com.test720.grasshoppercollege;

import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.HttpBean.FenPointsData;
import com.test720.grasshoppercollege.fragment.BaseTiMuFragment;
import com.test720.grasshoppercollege.untils.HttpUntil;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by 水东流 on 2018/7/6.
 */

public abstract class BaseTiMuOkFragment extends BaseTiMuFragment {
    FenPointsData data;
    boolean isAdd = false;//是否已经添加果果豆

    @Override
    public LinearLayout answerLin() {
        return null;
    }

    @Override
    public void setListener() {

    }

    @Override
    public HttpParams CuoTiHttpParams() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        httpParams.put("question_id", questionId());
        httpParams.put("class", MyApplication.getmInstance().nianji);
        httpParams.put("type", getBaseTiMuActivity().getType());
        return httpParams;
    }

    @Override
    public String status() {
        return null;
    }

    public abstract void TongGuan();

    @Override
    public void AddGuoGuoDou() {
        if (isAdd) return;
        if (getBaseTiMuActivity().isDingZheng()) {
            showGongXiPop(null, null, "蝈蝈学苑", "我在" + GXShareTitile() + "中获得了" + getBaseTiMuActivity().getFen() + "分，大家一起来玩呀!", (getBaseTiMuActivity().getType() + 10));
            return;
        }
        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        httpParams.put("fraction", getBaseTiMuActivity().getFen());
        httpParams.put("type", getBaseTiMuActivity().getType());
        post(HttpUntil.GetIntent().CommonaddAnswerPoints(), httpParams, 123);
        /*通关*/
        TongGuan();
        isAdd = true;
    }


    @Override
    public String SharePoint() {
        if (data != null) return data.getData().getShare_points();
        return null;
    }

    @Override
    public void ShareAddGuoGuo() {
        if (SharePoint() == null) return;
        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        httpParams.put("points", SharePoint());
        httpParams.put("type", getBaseTiMuActivity().getType());
        post(HttpUntil.GetIntent().addShareScore(), httpParams, 125, false);
        getActivity().finish();
    }

    @Override
    public void getSuccess(String s, int what) {

    }

    @Override
    public void ThreeSuccess(String s, int what) {
        switch (what) {
            case 123:
                if (codeIsOne(s, false)) {
                    data = new Gson().fromJson(s, FenPointsData.class);
                    showGongXiPop(data.getData().getPoints(), data.getData().getShare_points(), "蝈蝈学苑", "我在" + GXShareTitile() + "中获得了" + getBaseTiMuActivity().getFen() + "分，大家一起来玩呀!", (getBaseTiMuActivity().getType() + 10));
                } else {
                    showGongXiPop(null, null, "蝈蝈学苑", "我在" + GXShareTitile() + "中获得了" + getBaseTiMuActivity().getFen() + "分，大家一起来玩呀!", (getBaseTiMuActivity().getType() + 10));
                }
                break;
            case 124:
                if (codeIsOne(s)) {
                    EventBus.getDefault().post(new CurrencyEvent(CurrencyEvent.CHENGBAOTONGGUAN, ""));
                }
                break;
            case 125:

                break;

        }
    }

    /*分享加果果豆公用*/
    public abstract String GXShareTitile();
}
