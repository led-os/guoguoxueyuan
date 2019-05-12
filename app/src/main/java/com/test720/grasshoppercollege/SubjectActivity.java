package com.test720.grasshoppercollege;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.just.agentweb.AgentWeb;
import com.test720.grasshoppercollege.sharePage.ShareUntil;
import com.umeng.socialize.UMShareAPI;

import butterknife.BindView;
import butterknife.OnClick;

public class SubjectActivity extends BaseToolActivity {

    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.rightImg)
    ImageView rightImg;
    @BindView(R.id.right)
    RelativeLayout right;
    @BindView(R.id.topRel)
    RelativeLayout topRel;
    @BindView(R.id.root)
    LinearLayout root;

    String url = "";
    String type = "";
    String titlestr = "";
    protected AgentWeb mAgentWeb;

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_subject;
    }

    @Override
    protected void initData() {
        if (getIntent().getStringExtra("path") != null) {
            url = getIntent().getStringExtra("path");
        }
        if (getIntent().getStringExtra("title") != null) {
            titlestr = getIntent().getStringExtra("title");
            title.setText(titlestr);
        }
        if (getIntent().getStringExtra("type") != null) {
            type = getIntent().getStringExtra("type");
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
    public void GetHttpBackStr(String str, int what) {

    }


    @OnClick({R.id.back, R.id.right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.right:
                share();
                break;
        }
    }

    /**
     * 分享
     */
    private void share() {
        String body = "我在蝈蝈学苑中参加" + type + "，你也一起来参加吧！";
        ShareUntil.getInstance().share(url, type, body, this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onPause() {
        if (mAgentWeb != null) mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        if (mAgentWeb != null) mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //释放资源
        if (mAgentWeb != null) mAgentWeb.destroy();
        mAgentWeb = null;
    }
}
