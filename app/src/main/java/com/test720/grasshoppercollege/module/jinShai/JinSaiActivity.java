package com.test720.grasshoppercollege.module.jinShai;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.makeramen.roundedimageview.RoundedImageView;
import com.test720.grasshoppercollege.Adapter.FragmentViewPagerAdapter;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.HttpBean.JinSaiData;
import com.test720.grasshoppercollege.HttpBean.JinSaiFenShuData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.myViews.CustomViewPager;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.LogUtil;
import com.test720.grasshoppercollege.untils.glideUntil.GlideUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.FormBody;

public class JinSaiActivity extends BaseToolActivity implements JinShaiTiFragment.TallRightOrNot {


    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.viewPager)
    CustomViewPager viewPager;
    @BindView(R.id.my)
    ProgressBar my;
    @BindView(R.id.duiShou)
    ProgressBar duiShou;
    @BindView(R.id.onePic)
    RoundedImageView onePic;
    @BindView(R.id.oneName)
    TextView oneName;
    @BindView(R.id.twoName)
    TextView twoName;
    @BindView(R.id.myFen)
    TextView myFen;
    @BindView(R.id.duiFen)
    TextView duiFen;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.twoPic)
    RoundedImageView twoPic;
    private CountDownTimer countDownTimer;
    int timeInt = 5;
    boolean isOver = false;
    boolean out = false;
    int myfen = 0;//我的分数
    String roomId = "";
    Intent intent;//跳转结束界面的通知
    List<JinSaiData.DataBean.QuestionBean> list = new ArrayList<>();

    @Override
    public void MainHandlerVoid(Message msg) {
        super.MainHandlerVoid(msg);
        if (msg.what == 11) {
            if (timeInt == 0) {
                timeInt = 5;
                //  只要未到最后一题，就切换题目
                if (viewPager.getAdapter().getCount() > viewPager.getCurrentItem()) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                }
                //已经结束了
                if (isOver) {
                    if (intent != null) return;
                    intent = new Intent(this, JinSaiOverActivity.class);
                    intent.putExtra("room_id", roomId);
                    startActivity(intent);
                    finish();
                    out = true;
                }
                SendFen();//更新分数
                if (viewPager.getCurrentItem() == viewPager.getAdapter().getCount() - 1) {
                    isOver = true;
                }
            }
        }
    }

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_jin_sai;
    }

    @Override
    protected void initData() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        roomId = getIntent().getStringExtra("room_id");
        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("room_id", roomId);//请求参数一
        formBuilder.add("uid", MyApplication.getmInstance().getUid());
        Post(HttpUntil.GetIntent().startAnswer(), formBuilder, 1, false);
    }

    @Override
    public void GetHttpBackStr(String str, int what) {

        switch (what) {
            case 1://题目返回
                if (codeIsOne(str, false)) {
                    JinSaiData data = new Gson().fromJson(str, JinSaiData.class);
                    if (data.getData().getQuestion().size() == 0) {
                        finish();
                        return;
                    }
                    oneName.setText(data.getData().getMy().getNickname());
                    GlideUntil.getInstance().headByUrl(mcontext, onePic, data.getData().getMy().getHeader());
                    twoName.setText(data.getData().getOpponent().getNickname());
                    GlideUntil.getInstance().headByUrl(mcontext, twoPic, data.getData().getOpponent().getHeader());
                    list.clear();
                    list.addAll(data.getData().getQuestion());
                    my.setMax(list.size());
                    duiShou.setMax(list.size());
                    my.setProgress(0);
                    duiShou.setProgress(0);
                    List<Fragment> listFragment = new ArrayList<>();
                    for (int i = 0; i < list.size(); i++) {
                        JinShaiTiFragment jin = new JinShaiTiFragment();
                        jin.setaText(list.get(i).getSelect_a());
                        jin.setbText(list.get(i).getSelect_b());
                        jin.setcText(list.get(i).getSelect_c());
                        jin.setdText(list.get(i).getSelect_d());
                        jin.setTitle(list.get(i).getQuestion());
                        jin.setAnswer(list.get(i).getAnswer());
                        jin.setTallRightOrNot(this);
                        listFragment.add(jin);
                    }
                    viewPager.setAdapter(new FragmentViewPagerAdapter(getSupportFragmentManager(), listFragment));
                    if (countDownTimer == null) {
                        countDownTimer = new CountDownTimer(1000000, 1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                time.setText(String.valueOf(timeInt) + "s");
                                timeInt--;
                                Message meg = new Message();
                                meg.what = 11;
                                mainHandler.sendMessage(meg);
                            }

                            @Override
                            public void onFinish() {

                            }
                        }.start();
                    }

                } else {
                    finish();
                }
                break;
            case 2:
                if (!codeIsOne(str, false)) {
                    finish();
                    return;
                }
                JinSaiFenShuData data = new Gson().fromJson(str, JinSaiFenShuData.class);
                if (data.getData().getEnd().equals("1")) {//对手中途退出
                    out = true;
                    LogUtil.logError("对手早退");
                    if (intent == null) {
                        intent = new Intent(this, JinSaiOverActivity.class);
                        intent.putExtra("room_id", roomId);
                        startActivity(intent);
                    }
                    finish();
                }
                duiFen.setText(data.getData().getValue_2() + "");
                int dui = Integer.parseInt(duiFen.getText().toString()) - 90;
                duiShou.setProgress(dui);
                break;

        }
    }

    @Override
    public void tallRightOrNot(boolean b) {
        if (b) {
            myfen++;
            my.setProgress(myfen);
            int fen = myfen + 90;
            myFen.setText(fen + "");
        }
    }

    /*获得对手分数*/
    public void SendFen() {
        int fen = myfen + 90;
        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("room_id", getIntent().getStringExtra("room_id"));//请求参数一
        formBuilder.add("uid", MyApplication.getmInstance().getUid());
        formBuilder.add("value", fen + "");
        Post(HttpUntil.GetIntent().Match1answer(), formBuilder, 2, false);
    }

    /*中途退出*/
    public void tuiChu() {
        if (out) {
            return;
        }
        LogUtil.logError("中途退出");
        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("room_id", getIntent().getStringExtra("room_id"));//请求参数一
        formBuilder.add("uid", MyApplication.getmInstance().getUid());
        Post(HttpUntil.GetIntent().MatchoutMatch(), formBuilder, 3, false);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK)
            return true;//不执行父类点击事件
        return super.onKeyDown(keyCode, event);//继续执行父类其他点击事件
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null)
            countDownTimer.cancel();
        tuiChu();
    }
}
