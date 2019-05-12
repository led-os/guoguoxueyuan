package com.test720.grasshoppercollege.module.ciDai;

import android.widget.ExpandableListView;

import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.R;

import butterknife.BindView;

public class CiDaiListActivity extends BaseToolActivity {


    @BindView(R.id.exListView)
    ExpandableListView exListView;


    @Override
    protected String setTitle() {
        return "磁带播放";
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_ci_dai_list;
    }

    @Override
    protected void initData() {
       /* KeBenUnitData.DataBean dataBean = new KeBenUnitData.DataBean();
        KeBenUnitData.DataBean.PageBean page1Bean = new KeBenUnitData.DataBean.PageBean();
        page1Bean.setPage("page");
        page1Bean.setState("stat");
        List<KeBenUnitData.DataBean.PageBean> list = new ArrayList<>();
        dataBean.setPage(list);
        List<String> page = new ArrayList<>();
        page.add("1223");
        page.add("1223");
        page.add("1223");
        dataBean.setPage(page);
        dataBean.setUnit("unit");
        List<KeBenUnitData.DataBean> list1 = new ArrayList<>();


        list1.add(dataBean);
        list1.add(dataBean);
        list1.add(dataBean);
        exListView.setAdapter(new CiDaiAdapter(list1, this));*/

    }

    @Override
    public void GetHttpBackStr(String str, int what) {

    }

}
