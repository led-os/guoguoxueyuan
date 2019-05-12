package com.test720.grasshoppercollege;

import android.widget.LinearLayout;

import com.just.agentweb.AgentWeb;
import com.test720.grasshoppercollege.untils.HttpUntil;

import butterknife.BindView;

public class TextActivity extends BaseToolActivity {


    @BindView(R.id.lin)
    LinearLayout lin;
    private AgentWeb mAgentWeb;

    @Override
    protected String setTitle() {
        return "用户协议";
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_text;
    }

    @Override
    protected void initData() {
        mAgentWeb = AgentWeb.with(this)//传入Activity
                .setAgentWebParent(lin, new LinearLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams
                .useDefaultIndicator()// 使用默认进度条
                .defaultProgressBarColor() // 使用默认进度条颜色
                .createAgentWeb()//
                .ready()
                .go(HttpUntil.GetIntent().getIP() + "small.php/User/aboutUs/type/4");
    }

    @Override
    public void GetHttpBackStr(String str, int what) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //释放资源
        mAgentWeb.destroy();
        mAgentWeb = null;
    }
}
