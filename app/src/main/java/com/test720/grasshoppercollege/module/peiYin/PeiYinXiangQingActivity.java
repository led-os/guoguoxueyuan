package com.test720.grasshoppercollege.module.peiYin;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.danCiTingXie.MyGridAdapter;
import com.test720.grasshoppercollege.module.peiYin.bean.PeiYinData;
import com.test720.grasshoppercollege.myViews.NoScrollGridView;
import com.test720.grasshoppercollege.untils.HttpUntil;

import butterknife.BindView;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCMediaManager;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import okhttp3.FormBody;

public class PeiYinXiangQingActivity extends BaseToolActivity {

    MyGridAdapter myGridAdapter;
    PeiYinData data;

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_pei_yin_xiang_qing;
    }

    @Override
    protected void initData() {
        String id = getIntent().getStringExtra("con_id");
        FormBody.Builder builder = new FormBody.Builder();
        if (id != null) builder.add("con_id", id + "");
        Post(HttpUntil.GetIntent().Dubbingcontent(), builder, 1);
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        switch (what) {
            case 1:
                data = new Gson().fromJson(str, PeiYinData.class);
                jcPlayStandard.setUp(data.getData().getInfo().getVideo_path(), JCVideoPlayer.SCREEN_LAYOUT_NORMAL, "");
                jcPlayStandard.startButton.performClick();
                title.setText(data.getData().getInfo().getVideo_name());
                if (data.getData().getCollection().equals("1")) {
                    tvCollect.setText("取消收藏");
                } else {
                    tvCollect.setText("收藏");
                }
                jianJie.setText(data.getData().getInfo().getDesc());

                myGridAdapter = new MyGridAdapter(mcontext, data.getData().getList());
                myGridAdapter.setItemClick(new MyGridAdapter.ItemClick() {
                    @Override
                    public void itemClick(int position) {
                        Intent intent = new Intent(mcontext, PeiYinPinLunActivity.class);
                        intent.putExtra("pei_id", data.getData().getList().get(position).getPei_id());
                        jumpToActivity(intent, true);
                    }
                });
                pPersons.setAdapter(myGridAdapter);

                ratingBar.setRating(Float.parseFloat(data.getData().getInfo().getLevel()));
                break;
            case 5:
                if (codeIsOne(str)) {
                    if (tvCollect.getText().toString().equals("收藏")) {
                        tvCollect.setText("取消收藏");
                    } else {
                        tvCollect.setText("收藏");
                    }
                }
                break;
        }
    }

    /**
     * 收藏
     */
    public void collection() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("uid", MyApplication.getmInstance().getUid());
        builder.add("con_id", getIntent().getStringExtra("con_id"));
        Post(HttpUntil.GetIntent().Dubbingcollection(), builder, 5);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        JCVideoPlayer.releaseAllVideos();

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
    protected void onResume() {
        super.onResume();
        try {
            if (JCMediaManager.instance() != null) {
                if (JCMediaManager.instance().mediaPlayer != null) {
                    JCMediaManager.instance().mediaPlayer.start();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.back, R.id.begin, R.id.share, R.id.shouchang, R.id.paihang_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.share:
                break;
            case R.id.shouchang:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (MyApplication.getmInstance().getUid().equals("")) return;
                collection();
                break;
            case R.id.paihang_more:
                Intent intent1 = new Intent(mcontext, PeiYinPaiHangActivity.class);
                intent1.putExtra("con_id", getIntent().getStringExtra("con_id"));
                jumpToActivity(intent1, false);
                break;
            case R.id.begin:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (MyApplication.getmInstance().getUid().equals("")) return;
                Intent intent = new Intent(mcontext, PeiYinHeChengActivity.class);
                intent.putExtra("con_id", getIntent().getStringExtra("con_id"));
                intent.putExtra("name", title.getText().toString());
                jumpToActivity(intent, true);
                break;
        }
    }


    @BindView(R.id.jc_play_standard)
    JCVideoPlayerStandard jcPlayStandard;
    @BindView(R.id.nandu)
    TextView nandu;
    @BindView(R.id.ratingBar)
    RatingBar ratingBar;
    @BindView(R.id.share)
    LinearLayout share;
    @BindView(R.id.iv_collect)
    ImageView ivCollect;
    @BindView(R.id.tv_collect)
    TextView tvCollect;
    @BindView(R.id.shouchang)
    LinearLayout shouchang;
    @BindView(R.id.begin)
    TextView begin;
    @BindView(R.id.jian_jie)
    TextView jianJie;
    @BindView(R.id.paihang_more)
    RelativeLayout paihangMore;
    @BindView(R.id.pPersons)
    NoScrollGridView pPersons;
    @BindView(R.id.title)
    TextView title;
}
