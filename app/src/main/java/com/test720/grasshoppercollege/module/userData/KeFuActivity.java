package com.test720.grasshoppercollege.module.userData;

import android.widget.FrameLayout;

import com.just.agentweb.AgentWeb;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.HttpUntil;

import butterknife.BindView;

public class KeFuActivity extends BaseToolActivity {


    @BindView(R.id.flow)
    FrameLayout flow;

    protected AgentWeb mAgentWeb;

    @Override
    protected String setTitle() {
        return "客服帮助";
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_about_as;
    }

    @Override
    protected void initData() {
        mAgentWeb = AgentWeb.with(this)//传入Activity
                .setAgentWebParent(flow, new FrameLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams
                .useDefaultIndicator()// 使用默认进度条
                .defaultProgressBarColor() // 使用默认进度条颜色
                .createAgentWeb()//
                .ready()
                .go(HttpUntil.GetIntent().getIP() + "small.php/User/aboutUs/type/2");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //释放资源
        mAgentWeb.destroy();
        mAgentWeb = null;
    }

    @Override
    public void GetHttpBackStr(String str, int what) {

    }


}