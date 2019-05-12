package com.test720.grasshoppercollege;

import android.animation.ObjectAnimator;
import android.os.CountDownTimer;
import android.os.Message;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.just.agentweb.AgentWeb;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.myViews.GameIsOverDialog;
import com.test720.grasshoppercollege.myViews.MoveTextView;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.luYinUntil.TimeUtils;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;

public class GameActivity extends BaseToolActivity {


    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.root)
    LinearLayout root;
    @BindView(R.id.time)
    MoveTextView time;

    String url = "";
    protected AgentWeb mAgentWeb;
    private CountDownTimer countDownTimer;
    private int timeInt;//剩余时间

    /*起始时间动画*/
    private void startAnim(int x, int y) {
        ObjectAnimator transAnimy;
        transAnimy = ObjectAnimator.ofFloat(time, "translationY", 0, y - time.getHeight() / 2);
        transAnimy.setDuration(1500);
        transAnimy.start();

        ObjectAnimator transAnimx = ObjectAnimator.ofFloat(time, "translationX", 0, x - time.getWidth() / 2);
        transAnimx.setDuration(1500);
        transAnimx.start();
    }

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_game;
    }

    @Override
    protected void initData() {
        if (getIntent().getStringExtra("path") != null) {
            url = getIntent().getStringExtra("path");
            timeInt = Integer.parseInt(getIntent().getStringExtra("time"));
        }
        mAgentWeb = AgentWeb.with(this)//传入Activity
                .setAgentWebParent(root, new LinearLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams
                .useDefaultIndicator()// 使用默认进度条
                .defaultProgressBarColor() // 使用默认进度条颜色
                .createAgentWeb()// n 
                .ready()
                .go(url);

        countDownTimer = new CountDownTimer((timeInt + 2) * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Message meg = new Message();
                meg.what = 11;
                mainHandler.sendMessage(meg);
            }

            @Override
            public void onFinish() {
            }
        }.start();

        //获取屏幕的宽高
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int w = dm.widthPixels;
        int h = (dm.heightPixels);
        startAnim(w / 2, h / 2);
    }

    @Override
    public void MainHandlerVoid(Message msg) {
        super.MainHandlerVoid(msg);
        if (msg.what == 11) {
            timeInt--;
            if (timeInt % 60000 == 0) {
                EndEnvent();
            }
            if (timeInt >= 0) {
                time.setText(TimeUtils.long2String(timeInt * 1000));
                if (timeInt <= 0) {
                    countDownTimer.cancel();
                    timeInt = 0;
                    HaveNoTime();
                }
            }
        }

    }

    private void HaveNoTime() {
        GameIsOverDialog gameIsOverDialog = new GameIsOverDialog();
        gameIsOverDialog.setClosein(new GameIsOverDialog.Close() {
            @Override
            public void close() {
                finish();
            }
        });
        gameIsOverDialog.show(getSupportFragmentManager(), "game");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAgentWeb.destroy();
        mAgentWeb = null;
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
        EndEnvent();//益智游戏
    }

    /**
     * 花园
     */
    private void huaYuanEnvent() {
        if (isNetwork(mcontext)) {
            ShowToast("网络已断开");
            finish();
        }
        HttpParams httpParams = new HttpParams();
        httpParams.put("remaining_time", timeInt);
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        postResponse(HttpUntil.GetIntent().getIP() + "small.php/Farm/setFarmTime", httpParams, 1, false);

    }


    /*结束时触发*/
    private void EndEnvent() {
        if (isNetwork(mcontext)) {
            ShowToast("网络已断开");
            finish();
        }
        if (getIntent().getStringExtra("game_id") != null) {
            FormBody.Builder build = new FormBody.Builder();
            build.add("uid", MyApplication.getmInstance().getUid());
            build.add("game_id", getIntent().getStringExtra("game_id"));
            build.add("time", timeInt + "");
            Post(HttpUntil.GetIntent().ChildGamedecTime(), build, 1, false);
        } else {
            huaYuanEnvent();//花园
        }
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        switch (what) {
            case 1:

                break;
        }
    }

    @Override
    public void setSys() {
        super.setSys();
        isFull = true;
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }

}
