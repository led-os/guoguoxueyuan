package com.test720.grasshoppercollege;

import android.content.res.Configuration;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.just.agentweb.AgentWeb;

import butterknife.BindView;
import butterknife.OnClick;

public class WebViewActivity extends BaseToolActivity {


    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.root)
    LinearLayout root;
    @BindView(R.id.right)
    RelativeLayout share;
    String url = "";
    protected AgentWeb mAgentWeb;


    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_web_view;
    }

    @Override
    protected void initData() {
        if (getIntent().getStringExtra("url") != null) url = getIntent().getStringExtra("url");
        if (MyApplication.getmInstance().getUid() != null)
            url = url + "/uid/" + MyApplication.getmInstance().getUid();
        if (getIntent().getStringExtra("path") != null) {
            url = getIntent().getStringExtra("path");
        }
        if (getIntent().getStringExtra("title") != null) {
            title.setText(getIntent().getStringExtra("title"));
        }
        mAgentWeb = AgentWeb.with(this)//传入Activity
                .setAgentWebParent(root, new LinearLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams
                .useDefaultIndicator()// 使用默认进度条
                .defaultProgressBarColor() // 使用默认进度条颜色
                .createAgentWeb()//
                .ready()
                .go(url);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPause() {
        if(mAgentWeb!=null) mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
      if(mAgentWeb!=null) mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }


    @Override
    public void GetHttpBackStr(String str, int what) {

    }

    @Override
    public void setSys() {
        super.setSys();
        noSuPing = true;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //释放资源
        if(mAgentWeb!=null) mAgentWeb.destroy();
        mAgentWeb = null;
    }


    @OnClick({R.id.back, R.id.title})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.title:
              /*  int screenNum = getResources().getConfiguration().orientation;
                if (screenNum == 1) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                } else {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }*/
                break;
        }
    }

}
