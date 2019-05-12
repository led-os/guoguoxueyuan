package com.test720.grasshoppercollege.module.yuWen;

import android.widget.RelativeLayout;

import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.bean.RangBean;
import com.test720.grasshoppercollege.myViews.EditableTextView;

import java.util.ArrayList;

import butterknife.BindView;

public class TestActivity extends BaseToolActivity {


    @BindView(R.id.text)
    EditableTextView text;
    @BindView(R.id.activity_test)
    RelativeLayout activityTest;
    //存放边界信息的列表
    private ArrayList<RangBean> ranges;

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_test;
    }

    @Override
    protected void initData() {
        //文本框显示的内容
        String str = "本人持有____国学生签证，在____国院校就读；";
        //根据内容中横线的位置设置答案存放边界列表
        ranges = new ArrayList<>();
        ranges.add(new RangBean(4, 8));
        ranges.add(new RangBean(15, 19));
        text.setData(str, ranges);

    }

    @Override
    public void GetHttpBackStr(String str, int what) {

    }
}