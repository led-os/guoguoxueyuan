package com.test720.grasshoppercollege;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.just.agentweb.AgentWeb;

import butterknife.BindView;

/**
 * Created by 水东流 on 2018/5/8.
 */
public class WebFragment extends BaseFragment {
    @BindView(R.id.root)
    FrameLayout root;

    String url = "https://www.baidu.com/";
    protected AgentWeb mAgentWeb;

    @Override
    public void initData() {
        Bundle bundle = getArguments();
        if (bundle != null && bundle.getString("url") != null) {
            url = bundle.getString("url");
        }
        mAgentWeb = AgentWeb.with(this)//传入Activity
                .setAgentWebParent(root, new FrameLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams
                .useDefaultIndicator()// 使用默认进度条
                .createAgentWeb()//
                .ready()
                .go(url);
    }

    @Override
    public void onPause() {
        if (mAgentWeb != null) mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();
    }

    @Override
    public void onResume() {
        if (mAgentWeb != null) mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //释放资源
        mAgentWeb.destroy();
        mAgentWeb = null;
    }

    @Override
    public void setListener() {

    }

    @Override
    public int setlayoutResID() {
        return R.layout.web_layout;
    }

    @Override
    public void getSuccess(String s, int what) {

    }

}
