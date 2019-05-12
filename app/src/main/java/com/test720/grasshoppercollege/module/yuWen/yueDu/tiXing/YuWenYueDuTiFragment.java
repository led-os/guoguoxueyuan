package com.test720.grasshoppercollege.module.yuWen.yueDu.tiXing;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.BaseTiMuActivity;
import com.test720.grasshoppercollege.HttpBean.FenPointsData;
import com.test720.grasshoppercollege.HttpBean.YuWenYueDuData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.fragment.BaseTiMuFragment;
import com.test720.grasshoppercollege.untils.HttpUntil;

/**
 * Created by 水东流 on 2018/7/5.
 */

public abstract class YuWenYueDuTiFragment extends BaseTiMuFragment {
   YuWenYueDuData.DataBean.ListBean dataBean;
    BaseTiMuActivity baseTiMuActivity;
    FenPointsData data;


    @Override
    public HttpParams CuoTiHttpParams() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("question_id", dataBean.getQuestion_id());

        httpParams.put("type", getBaseTiMuActivity().getType());
        return httpParams;
    }

    @Override
    public String corrId() {
        return null;
    }

    @Override
    public String status() {
        return null;
    }

    @Override
    public void AddGuoGuoDou() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        httpParams.put("fraction", getBaseTiMuActivity().getFen());
        httpParams.put("type", getBaseTiMuActivity().getType());
        post(HttpUntil.GetIntent().CommonaddAnswerPoints(), httpParams, 123);
    }

    @Override
    public String SharePoint() {
        if (data != null) {
            return data.getData().getShare_points();
        }
        return null;
    }
    @Override
    public String questionId() {
        return null;
    }

    @Override
    public void ShareAddGuoGuo() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        httpParams.put("points", SharePoint());
        httpParams.put("type", getBaseTiMuActivity().getType());
        post(HttpUntil.GetIntent().addMatchSharePoints(), httpParams, 124);
    }

    @Override
    public void getSuccess(String s, int what) {
        if (what == 123) {
            data = new Gson().fromJson(s, FenPointsData.class);
            showGongXiPop(data.getData().getPoints(), data.getData().getShare_points(), "蝈蝈学苑", "我在语文阅读训练中获得了" + getBaseTiMuActivity().getFen() + "分，大家一起来玩呀!", 25);
        }
    }

    public YuWenYueDuData.DataBean.ListBean getDataBean() {
        return dataBean;
    }

    public void setDataBean(YuWenYueDuData.DataBean.ListBean dataBean) {
        this.dataBean = dataBean;
    }

    public BaseTiMuActivity getBaseTiMuActivity() {
        return baseTiMuActivity;
    }

    public void setBaseTiMuActivity(BaseTiMuActivity baseTiMuActivity) {
        this.baseTiMuActivity = baseTiMuActivity;
    }
}