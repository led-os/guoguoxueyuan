package com.test720.grasshoppercollege.Adapter;

import android.content.Context;

import com.test720.grasshoppercollege.HttpBean.KeBenUnitData;

import java.util.List;

/**
 * Created by 水东流 on 2018/5/15.
 */

public class CiDaiAdapter extends UntilExpandableListViewAdapter {
    public CiDaiAdapter(List<KeBenUnitData.DataBean> list, Context context) {
        super(list, context);
    }

    @Override
    public void childrenClick(KeBenUnitData.DataBean.PageBean pageBean, String unit) {

    }


}
