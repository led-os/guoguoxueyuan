package com.test720.grasshoppercollege.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.just.agentweb.AgentWeb;
import com.test720.grasshoppercollege.BaseFragment;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.WebViewActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 佛祖保佑        永无BUG
 * 佛曰:
 * 程序园里程序天，程序天里程序员；
 * 程序猿人写程序，又拿程序换肉钱。
 * 肉饱继续桌前坐，饱暖还是桌前眠；
 * 半迷半醒日复日，码上码下年复年。
 * 但愿叱咤互联世，不愿搬砖码当前；
 * 诸葛周瑜算世事，我算需求得加钱。
 * 别人笑我忒直男，我说自己是程猿；
 * 但见成都府国内，处处地地程序员。
 * 作者：水东流 编于 2018/11/10
 */
public class MyWebFragment extends BaseFragment {
    @BindView(R.id.webView)
    FrameLayout view;

    AgentWeb mAgentWeb;
    String url = "";

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public MyWebFragment() {
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
        if (mAgentWeb != null) mAgentWeb.destroy();
        mAgentWeb = null;
    }


    @Override
    public void initData() {
        Bundle build = getArguments();
        url = build.getString("url");
        mAgentWeb = AgentWeb.with(this)//传入Activity
                .setAgentWebParent(view, new FrameLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams
                .useDefaultIndicator()// 使用默认进度条
                .createAgentWeb()//
                .ready()
                .go(url);
    }

    @Override
    public void setListener() {

    }

    @Override
    public int setlayoutResID() {
        return R.layout.my_web_fragment;
    }

    @Override
    public void getSuccess(String s, int what) {

    }


    @OnClick(R.id.seeMore)
    public void onViewClicked() {
        if (url != null) {
            Intent intent = new Intent(getActivity(), WebViewActivity.class);
            intent.putExtra("path", url);
            jumpToActivity(intent, false);
        }

    }
}
