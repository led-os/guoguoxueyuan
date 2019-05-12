package com.test720.grasshoppercollege.module.jinShai;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.makeramen.roundedimageview.RoundedImageView;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.HttpBean.JingSaiOverData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.LogUtil;
import com.test720.grasshoppercollege.untils.glideUntil.GlideUntil;
import com.test720.grasshoppercollege.untils.musicMedia.MusicUntil;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;
import www.test720.mylibrary.fireview.MyView;

public class JinSaiOverActivity extends BaseToolActivity {


    @BindView(R.id.root)
    RelativeLayout root;
    @BindView(R.id.onePic)
    RoundedImageView onePic;
    @BindView(R.id.oneName)
    TextView oneName;
    @BindView(R.id.twoPic)
    RoundedImageView twoPic;
    @BindView(R.id.twoName)
    TextView twoName;
    @BindView(R.id.chengGong)
    TextView chengGong;
    @BindView(R.id.oneFen)
    TextView oneFen;
    @BindView(R.id.twoFen)
    TextView twoFen;
    @BindView(R.id.doudou)
    TextView doudou;
    @BindView(R.id.jingyan)
    TextView jingyan;
    @BindView(R.id.jixu)
    TextView jixu;
    @BindView(R.id.twoHG)
    ImageView twoHG;
    @BindView(R.id.oneHG)
    ImageView oneHG;
    @BindView(R.id.share)
    TextView share;
    @BindView(R.id.shareDou)
    TextView shareDou;
    @BindView(R.id.down)
    LinearLayout down;
    @BindView(R.id.myView)
    MyView myView;


    String point = "";
    String type = "";
    String title = "蝈蝈学苑";
    String body = "我在蝈蝈学苑pk竞赛中获得了胜利，大家一起来玩呀！";

    int timeInt = 1;

    MusicUntil musicUntil = new MusicUntil();

    @Override
    protected String setTitle() {
        return "竞赛";
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_jin_sai_over;
    }

    class OverRun implements Runnable {

        @Override
        public void run() {
            over();
        }
    }

    @Override
    protected void initData() {
        Typeface fontFace = Typeface.createFromAsset(getAssets(),
                "fonts/hkhbt.ttf");
        chengGong.setTypeface(fontFace);
        down.setVisibility(View.GONE);
        mainHandler.postDelayed(new OverRun(), 0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (musicUntil != null) musicUntil.canlce();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (myView != null) myView.setRunning(false);


    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        switch (what) {
            case 1:
                break;
            case 4:
                if (codeIsOne(str, false)) {
                    JingSaiOverData jingSaiOverData = new Gson().fromJson(str, JingSaiOverData.class);
                    if (jingSaiOverData.getData().getUser_2().getComplete().equals("1")) {
                        LogUtil.logError(str);
                        down.setVisibility(View.VISIBLE);
                        //***判断对方是否结束
                        type = jingSaiOverData.getData().getMatch_share();
                        point = jingSaiOverData.getData().getUser_1().getPoints();
                        oneName.setText(jingSaiOverData.getData().getUser_1().getNickname());
                        oneFen.setText(jingSaiOverData.getData().getUser_1().getValue());
                        String d = jingSaiOverData.getData().getUser_1().getPoints() + "";
                        doudou.setText(d);
                        String e = jingSaiOverData.getData().getUser_1().getExperience_value() + "";
                        jingyan.setText(e);
                        GlideUntil.getInstance().headByUrl(mcontext, onePic, jingSaiOverData.getData().getUser_1().getHeader());


                        GlideUntil.getInstance().headByUrl(mcontext, twoPic, jingSaiOverData.getData().getUser_2().getHeader());
                        twoName.setText(jingSaiOverData.getData().getUser_2().getNickname());
                        twoFen.setText(jingSaiOverData.getData().getUser_2().getValue());
                        shareDou.setText(jingSaiOverData.getData().getMatch_share());

                        if (jingSaiOverData.getData().getUser_1().getResult().equals("1")) {
                            chengGong.setText("挑战成功");
                            musicUntil.playRaw(mcontext, R.raw.jinshaichenggong);
                            oneHG.setVisibility(View.VISIBLE);
                            twoHG.setVisibility(View.INVISIBLE);
                            toBig(chengGong);
                            myView.setVisibility(View.VISIBLE);
                            myView.setRunning(true);
                            root.setBackgroundResource(R.mipmap.jingshaishibai);
                        } else {
                            chengGong.setText("挑战失败");
                            musicUntil.playRaw(mcontext, R.raw.jinsaishibai);
                            chengGong.setTextColor(getResources().getColor(R.color.white));
                            oneHG.setVisibility(View.INVISIBLE);
                            twoHG.setVisibility(View.VISIBLE);
                            myView.setRunning(false);
                            body = "我在蝈蝈学苑参加pk赛，大家一起来玩呀！";
                            root.setBackgroundResource(R.mipmap.jingshaichenggong);
                        }
                    } else {
                        mainHandler.postDelayed(new OverRun(), 2000);
                    }
                } else {
                    ShowToast("房间已销毁！");
                    finish();
                }
                break;
            case 5:
                if (codeIsOne(str)) {
                    finish();
                }
                break;
        }
    }

    /*竞赛结束*/
    public void over() {
        FormBody.Builder formBuilder = new FormBody.Builder();
        if (timeInt > 3) {
            formBuilder.add("last", "1");
        }
        formBuilder.add("room_id", getIntent().getStringExtra("room_id"));//请求参数一
        formBuilder.add("uid", MyApplication.getmInstance().getUid());
        Post(HttpUntil.GetIntent().Match1calculate(), formBuilder, 4, false);
        timeInt++;
    }


    @OnClick({R.id.jixu, R.id.share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.jixu:
                finish();
                break;
            case R.id.share:
                share(type, title, body);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    /*蝈蝈豆提示*/

    /******************************分享*****************/
    /*分享加分*/
    public void share(String points) {
        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("points", points);//请求参数一
        formBuilder.add("type", JinShaiShouYeActivity.type);
        formBuilder.add("uid", MyApplication.getmInstance().getUid());
        Post(HttpUntil.GetIntent().addMatchSharePoints(), formBuilder, 5, false);
    }


    public void share(String type, String shareTitle, String shareBody) {
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(mcontext, mPermissionList, 123);
        }
        UMImage image = new UMImage(mcontext, R.mipmap.icon_app);//分享图标
        String url = HttpUntil.GetIntent().getShare() + type;
        final UMWeb web = new UMWeb(url); //切记切记 这里分享的链接必须是http开头

        web.setTitle(shareTitle);//标题
        web.setThumb(image);  //缩略图
        web.setDescription(shareBody);//描述
        new ShareAction(mcontext)
                .setDisplayList(SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE)
                .setShareboardclickCallback(new ShareBoardlistener() {
                    @Override
                    public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                        if (share_media == SHARE_MEDIA.QQ) {
                            new ShareAction(mcontext).setPlatform(SHARE_MEDIA.QQ)
                                    .withMedia(web)
                                    .setCallback(umShareListener)
                                    .share();
                        } else if (share_media == SHARE_MEDIA.WEIXIN) {
                            new ShareAction(mcontext).setPlatform(SHARE_MEDIA.WEIXIN)
                                    .withMedia(web)
                                    .setCallback(umShareListener)
                                    .share();
                        } else if (share_media == SHARE_MEDIA.QZONE) {
                            new ShareAction(mcontext).setPlatform(SHARE_MEDIA.QZONE)
                                    .withMedia(web)
                                    .setCallback(umShareListener)
                                    .share();
                        } else if (share_media == SHARE_MEDIA.WEIXIN_CIRCLE) {
                            new ShareAction(mcontext).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
                                    .withMedia(web)
                                    .setCallback(umShareListener)
                                    .share();
                        }
                    }
                }).open();
    }


    @Override
    public void onEvent(CurrencyEvent currencyEvent) {
        super.onEvent(currencyEvent);
        if (currencyEvent.getWhat() == CurrencyEvent.SHAREOK) {
            if (point == null) {
                point = "0";
            }
            share(point);
        }
    }

    /**
     * 放大
     *
     * @param view
     */
    public void toBig(View view) {
        chengGong.setTextColor(getResources().getColor(R.color.chenggong));
        ObjectAnimator toViewAnimatorX = ObjectAnimator.ofFloat(view, "scaleX", 1f, 1.6f);
        toViewAnimatorX.setDuration(1000);
        toViewAnimatorX.start();

        ObjectAnimator toViewAnimatorY = ObjectAnimator.ofFloat(view, "scaleY", 1f, 1.6f);
        toViewAnimatorY.setDuration(1000);
        toViewAnimatorY.start();

        //放烟火
        LogUtil.logError("播放烟花");
        MyView myView = new MyView(mcontext);
        root.addView(myView);
        myView.setRunning(true);
    }

    //分享
    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            EventBus.getDefault().post(new CurrencyEvent("分享成功", CurrencyEvent.SHAREOK));
            Toast.makeText(mcontext, platform + " 分享成功", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(mcontext, platform + " 分享失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(mcontext, platform + " 分享取消", Toast.LENGTH_SHORT).show();
        }
    };


}
