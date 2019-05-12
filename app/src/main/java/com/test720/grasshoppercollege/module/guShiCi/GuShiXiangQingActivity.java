package com.test720.grasshoppercollege.module.guShiCi;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.just.agentweb.AgentWeb;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.BuyClassActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.SubjectActivity;
import com.test720.grasshoppercollege.myViews.TextDialog;
import com.test720.grasshoppercollege.untils.HttpUntil;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCMediaManager;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import okhttp3.FormBody;

public class GuShiXiangQingActivity extends BaseToolActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.shouchang)
    ImageView shouchang;
    @BindView(R.id.lianxi)
    ImageView lianxi;
    @BindView(R.id.jcVideo)
    JCVideoPlayerStandard jcVideo;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.webLin)
    LinearLayout webLin;

    SiCiXiangQingData data;
    private AgentWeb mAgentWeb;
    String poetryId;

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.ancient_poetry_xiang_qing;
    }

    @Override
    protected void initData() {
        if (getIntent().getStringExtra("title") != null) {
            title.setText(getIntent().getStringExtra("title"));
        }
        jcVideo.backButton.setVisibility(View.GONE);
        jcVideo.titleTextView.setVisibility(View.GONE);
        jcVideo.tinyBackImageView.setVisibility(View.GONE);

        poetryId = getIntent().getStringExtra("poetry_id");

    }

    private void getData() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("poetry_id", poetryId);
        builder.add("uid", MyApplication.getmInstance().getUid());
        Post(HttpUntil.GetIntent().ChineseAncientPoetrycontent(), builder, 1);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        switch (what) {
            case 1:
                if (codeIsOne(str, false)) {
                    data = new Gson().fromJson(str, SiCiXiangQingData.class);
                    if (data.getData().getCollection().equals("1")) {
                        shouchang.setImageResource(R.mipmap.aixin2);
                    } else {
                        shouchang.setImageResource(R.mipmap.aixinsc);
                    }
                    jcVideo.setUp(data.getData().getVideo_path(), JCVideoPlayer.SCREEN_LAYOUT_NORMAL, "");
                    jcVideo.startVideo();
                    mAgentWeb = AgentWeb.with(this)//传入Activity
                            .setAgentWebParent(webLin, new LinearLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams
                            .useDefaultIndicator()// 使用默认进度条
                            .defaultProgressBarColor() // 使用默认进度条颜色
                            .createAgentWeb()//
                            .ready()
                            .go(data.getData().getDesc_web());
                }
                break;
            case 2:
                if (codeIsOne(str)) {
                    try {
                        JSONObject jsonObject = new JSONObject(str);
                        if (jsonObject.getString("msg").equals("收藏成功")) {
                            shouchang.setImageResource(R.mipmap.aixin2);
                        } else {
                            shouchang.setImageResource(R.mipmap.aixinsc);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }

    }


    @OnClick({R.id.back, R.id.lianxi, R.id.shouchang})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.lianxi:
                lianXi();
                break;
            case R.id.shouchang:
                shouChang();
                break;
        }
    }

    private void lianXi() {
        if (data.getData().isPay()) {
           /* Intent in = new Intent(this, GuSiTiMuActivity.class);
            in.putExtra("poetry_id", poetryId);
            jumpToActivity(in, false);*/
            Intent intent = new Intent(mcontext, SubjectActivity.class);
            intent.putExtra("type","古诗词");
            intent.putExtra("path", data.getData().getWeb_url());
            intent.putExtra("title", "古诗词练习");
            jumpToActivity(intent, false);
        } else {
            TextDialog textDialog = new TextDialog();
            textDialog.setTextStr("此课程须购买，请购买该课程，或者升级成为vip");
            textDialog.setOkText("去购买");
            textDialog.setOkClick(new TextDialog.OkClick() {
                @Override
                public void click() {
                    Intent intent = new Intent(mcontext, BuyClassActivity.class);
                    intent.putExtra("curr_type", 34);
                    intent.putExtra("type", "4");
                    jumpToActivity(intent, false);
                }

                @Override
                public void no() {

                }
            });
            textDialog.show(getSupportFragmentManager(), "buy");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            if (JCMediaManager.instance().mediaPlayer != null) {
                JCMediaManager.instance().mediaPlayer.pause();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        JCVideoPlayer.releaseAllVideos();
        if (mAgentWeb != null) {
            mAgentWeb.destroy();
            mAgentWeb = null;
        }
    }

    /*收藏*/
    private void shouChang() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("poetry_id", poetryId);
        builder.add("uid", MyApplication.getmInstance().getUid());
        Post(HttpUntil.GetIntent().ChineseAncientPoetrycollection(), builder, 2);
    }


}
