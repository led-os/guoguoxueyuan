package com.test720.grasshoppercollege.module.peiYin;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class PeiYinWanChengActivity extends BaseToolActivity {


    String path;//视频地址

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_pei;
    }

    @Override
    protected void initData() {
        title.setText("我的配音");
        path = getIntent().getStringExtra("path");
        jcPlayStandard.backButton.setVisibility(View.GONE);
        jcPlayStandard.setUp(path, JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "");
        jcPlayStandard.startButton.performClick();
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        switch (what) {
            case 5:
                if (codeIsOne(str)) {
                    setResult(2, new Intent());
                    finish(); //结束当前的activity的生命周期
                }
                break;
        }
    }

    /**
     * 发布
     */
    private void send() {
        HttpParams builder = new HttpParams();
        builder.put("con_id", getIntent().getStringExtra("con_id"));
        builder.put("uid", MyApplication.getmInstance().getUid());
        builder.put("file", new File(path));
        postResponse(HttpUntil.GetIntent().DubbinguploadDubbing(), builder, 5, true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        JCVideoPlayer.releaseAllVideos();
    }

    @OnClick({R.id.back, R.id.layout_shar, R.id.release, R.id.my_return})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.layout_shar:
                break;
            case R.id.release:
                send();
                break;
            case R.id.my_return:
                setResult(1, new Intent());
                finish(); //结束当前的activity的生命周期
                break;
        }
    }

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
    @BindView(R.id.jc_play_standard)
    JCVideoPlayerStandard jcPlayStandard;
    @BindView(R.id.layout_shar)
    RelativeLayout layoutShar;
    @BindView(R.id.release)
    RelativeLayout release;
    @BindView(R.id.my_return)
    RelativeLayout myReturn;
}
