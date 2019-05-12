package com.test720.grasshoppercollege;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.HttpBean.ADData;
import com.test720.grasshoppercollege.HttpBean.CheckData;
import com.test720.grasshoppercollege.login.AdressChooseActivity;
import com.test720.grasshoppercollege.myViews.CheckUpdataDialog;
import com.test720.grasshoppercollege.untils.AppUploatUtils;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.LogUtil;
import com.test720.grasshoppercollege.untils.LoginUntil;
import com.test720.grasshoppercollege.untils.ShenHeUntil;
import com.test720.grasshoppercollege.untils.downUntil.DownApkDialog;

import butterknife.BindView;
import okhttp3.FormBody;
import www.test720.mylibrary.SPUtils;


public class StartActivity extends BaseToolActivity {


    @BindView(R.id.book)
    ImageView book;
    @BindView(R.id.one)
    ImageView one;
    @BindView(R.id.two)
    ImageView two;
    @BindView(R.id.three)
    ImageView three;
    @BindView(R.id.four)
    ImageView four;
    @BindView(R.id.five)
    ImageView five;
    @BindView(R.id.six)
    ImageView six;
    @BindView(R.id.seven)
    ImageView seven;
    @BindView(R.id.eight)
    ImageView eight;
    @BindView(R.id.activity_start)
    RelativeLayout activityStart;

    /*************广告****************/
    ADData adData;
    boolean needSeeAd = false;/*标记是否需要跳转到广告*/
    //**************登录******/

    //***************更新**********/
    CheckData checkData;
    boolean needGengXin = false;

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            init();
        }
    };

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_start;
    }

    @Override
    protected void initData() {
        /*开始，登陆，广告，更新，同时请求*/
        toLogin();
        ad();
        check();
        mainHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                moveXanim();
            }
        }, 500);
        mainHandler.postDelayed(runnable, 5000);

    }

    //重新显示界面，做判断
    @Override
    protected void onRestart() {
        super.onRestart();
        init();
    }

    /*动画执行完成,执行广告，更新，登陆，等的逻辑*/
    private void init() {
        if (needGengXin) {
            ShowUpDataPop();
        } else if (SPUtils.getBoolean(StartActivity.this, "first", true)) {
            Intent intent = new Intent(mcontext, YinDaoActivity.class);
            startActivityForResult(intent, 1);
        } else if (needSeeAd) {
            Intent intent = new Intent(StartActivity.this, ADActivity.class);
            intent.putExtra("pic", adData.getData().getInfo().getPic());
            intent.putExtra("location_type", adData.getData().getInfo().getLocation_type());
            intent.putExtra("location_link", adData.getData().getInfo().getLocation_link());
            startActivityForResult(intent, 2);
            needSeeAd = false;//广告只查看一次
        } else if (MyApplication.getmInstance().userData != null) {
            /*未设置地址*/
            if (MyApplication.getmInstance().userData.getData().getArea_select() != null && MyApplication.getmInstance().userData.getData().getArea_select().equals("0")) {
                LogUtil.logError("area_select=" + MyApplication.getmInstance().userData.getData().getArea_select());
                jumpToActivity(AdressChooseActivity.class, true);
            } else {//设置了地址直接跳转
                jumpToActivity(MainActivity.class, true);
            }
        } else {
            jumpToActivity(MainActivity.class, true);
        }
    }

    /*更新*/
    private void check() {
        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("device", 1 + "");//请求参数
        Post(HttpUntil.GetIntent().checkUrl(), formBuilder, 3, false);
    }

    /*广告*/
    private void ad() {
        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("type", 1 + "");//请求参数
        Post(HttpUntil.GetIntent().getAds(), formBuilder, 1, false);
    }

    /*登陆*/
    private void toLogin() {
        LogUtil.logError("phone" + SPUtils.getString(this, SPUtils.PHONE, ""));
        LogUtil.logError("psd" + SPUtils.getString(this, SPUtils.PASSWORD, ""));
        if (!SPUtils.getString(this, SPUtils.PHONE, "").equals("") && !SPUtils.getString(this, SPUtils.PASSWORD, "").equals("")) {
            LogUtil.logError("开始自动登陆");
            FormBody.Builder formBuilder = new FormBody.Builder();
            formBuilder.add("phone", SPUtils.getString(this, SPUtils.PHONE, ""));//请求参数一
            formBuilder.add("password", SPUtils.getString(this, SPUtils.PASSWORD, ""));//请求参数二
            formBuilder.add("type", 1 + "");//请求参数三
            Post(HttpUntil.GetIntent().getlogin(), formBuilder, 2, false);
        } else {
            MyApplication.getmInstance().userData = null;//如果未能获取登陆信息，则清除缓存数据，防止容错
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mainHandler != null) mainHandler.removeCallbacks(runnable);
        //进入相应ip
        if (ShenHeUntil.getInstance().isShenHe()) {
            ShenHeUntil.getInstance().setShenHe(ShenHeUntil.getInstance().isShenHe());
        }
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        switch (what) {
            case 1://广告
                if (codeIsOne(str, false)) {
                    adData = new Gson().fromJson(str, ADData.class);
                    needSeeAd = true;
                }
                break;
            case 2://登陆
                if (codeIsOne(str, false)) {
                    LoginUntil.getInstance().saveData(str, mcontext);
                }
                break;

            case 3://更新
                if (codeIsOne(str, false)) {
                    checkData = new Gson().fromJson(str, CheckData.class);
                    String verName = AppUploatUtils.getVerName(StartActivity.this);
                    /*网络版本和当前版本不一致*/
                    LogUtil.logError("网络版本" + checkData.getData().getVersion_no());
                    LogUtil.logError("本地版本" + verName);
                    if (checkData.getData().getVersion_no() != null && !verName.equals(checkData.getData().getVersion_no())) {
                        needGengXin = true;
                    }
                    /*是否审核*/
                    if (checkData.getData().getState().equals("1")//审核状态开启
                            && checkData.getData().getVersion_no_tow().size() > 0//当前有平台处于审核中
                            && checkData.getData().getVersion_no_tow().contains(ShenHeUntil.getInstance().getBiaoShi())
                    ) {//此包为审核包
                        Log.e("lxl", "审核中");
                        needGengXin = false;
                        ShenHeUntil.getInstance().setShenHe(true);//正处于审核
                    } else {
                        ShenHeUntil.getInstance().setShenHe(false);
                    }

                }

                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 || requestCode == 2) {
//            init();
        }
    }

    public void moveXanim() {
        eight.setVisibility(View.VISIBLE);

        ObjectAnimator animator = ObjectAnimator.ofFloat(eight, "alpha", 0, 0.5f, 1);
        animator.setDuration(500);
        animator.start();

        ObjectAnimator transAnimx = ObjectAnimator.ofFloat(eight, "translationX", -eight.getX(), 0);
        transAnimx.setDuration(500);
        transAnimx.start();
        ObjectAnimator transAnimy = ObjectAnimator.ofFloat(eight, "translationY", -eight.getY(), 0);
        transAnimy.setDuration(500);
        transAnimy.start();

        new Handler().postDelayed(new Runnable() {
            public void run() {
                seven.setVisibility(View.VISIBLE);
                ObjectAnimator transAnimx = ObjectAnimator.ofFloat(seven, "translationX", -seven.getX(), 0);
                transAnimx.setDuration(500);
                transAnimx.start();
                ObjectAnimator transAnimy = ObjectAnimator.ofFloat(seven, "translationY", -seven.getY(), 0);
                transAnimy.setDuration(500);
                transAnimy.start();
            }
        }, 500);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                six.setVisibility(View.VISIBLE);
                ObjectAnimator transAnimx = ObjectAnimator.ofFloat(six, "translationX", -six.getX(), 0);
                transAnimx.setDuration(500);
                transAnimx.start();
                ObjectAnimator transAnimy = ObjectAnimator.ofFloat(six, "translationY", -six.getY(), 0);
                transAnimy.setDuration(500);
                transAnimy.start();
            }
        }, 1000);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                five.setVisibility(View.VISIBLE);
                ObjectAnimator transAnimx = ObjectAnimator.ofFloat(five, "translationX", -five.getX(), 0);
                transAnimx.setDuration(500);
                transAnimx.start();
                ObjectAnimator transAnimy = ObjectAnimator.ofFloat(five, "translationY", -five.getY(), 0);
                transAnimy.setDuration(500);
                transAnimy.start();
            }
        }, 1500);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                four.setVisibility(View.VISIBLE);
                ObjectAnimator transAnimx = ObjectAnimator.ofFloat(four, "translationX", -four.getX(), 0);
                transAnimx.setDuration(500);
                transAnimx.start();
                ObjectAnimator transAnimy = ObjectAnimator.ofFloat(four, "translationY", -four.getY(), 0);
                transAnimy.setDuration(500);
                transAnimy.start();

            }
        }, 2000);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                three.setVisibility(View.VISIBLE);
                ObjectAnimator transAnimx = ObjectAnimator.ofFloat(three, "translationX", -three.getX(), 0);
                transAnimx.setDuration(500);
                transAnimx.start();
                ObjectAnimator transAnimy = ObjectAnimator.ofFloat(three, "translationY", -three.getY(), 0);
                transAnimy.setDuration(500);
                transAnimy.start();

            }
        }, 2500);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                two.setVisibility(View.VISIBLE);
                ObjectAnimator transAnimx = ObjectAnimator.ofFloat(two, "translationX", -two.getX(), 0);
                transAnimx.setDuration(500);
                transAnimx.start();
                ObjectAnimator transAnimy = ObjectAnimator.ofFloat(two, "translationY", -two.getY(), 0);
                transAnimy.setDuration(500);
                transAnimy.start();

            }
        }, 3000);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                one.setVisibility(View.VISIBLE);
                ObjectAnimator transAnimx = ObjectAnimator.ofFloat(one, "translationX", -one.getX(), 0);
                transAnimx.setDuration(500);
                transAnimx.start();
                ObjectAnimator transAnimy = ObjectAnimator.ofFloat(one, "translationY", -one.getY(), 0);
                transAnimy.setDuration(500);
                transAnimy.start();

            }
        }, 3500);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                book.setVisibility(View.VISIBLE);
                ObjectAnimator transAnimx = ObjectAnimator.ofFloat(book, "translationX", -book.getX(), 0);
                transAnimx.setDuration(500);
                transAnimx.start();

                ObjectAnimator transAnimy = ObjectAnimator.ofFloat(book, "translationY", -book.getY(), 0);
                transAnimy.setDuration(500);
                transAnimy.start();
            }
        }, 4000);

    }

    public void ShowUpDataPop() {
        LogUtil.logError("apkurl" + checkData.getData().getUrl());
        CheckUpdataDialog checkUpdataDialog = new CheckUpdataDialog();
        checkUpdataDialog.setIs_forced(checkData.getData().getIs_forced());
        checkUpdataDialog.setClick(new CheckUpdataDialog.Click() {
            @Override
            public void btnClick() {
                if (checkData.getData().getDownload_type().equals("2")) {// 下载apk
                    DownApkDialog downApkDialog = new DownApkDialog();
                    downApkDialog.setPath(checkData.getData().getUrl());
                    downApkDialog.show(getSupportFragmentManager(), "apk");
                } else if (checkData.getData().getDownload_type().equals("1")) {//网页更新
                    try {
                        // 网页
                        Intent intent = new Intent();
                        intent.setAction("android.intent.action.VIEW");
                        Uri content_url = Uri.parse(checkData.getData().getUrl());
                        intent.setData(content_url);
                        startActivity(Intent.createChooser(intent, "请选择浏览器"));
                        mainHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, 1000);

//                        startActivityForResult(intent, 1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void noUpdata() {
                needGengXin = false;
                init();
            }
        });
        checkUpdataDialog.show(getSupportFragmentManager(), "check");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return false;
    }

    @Override
    public void onEvent(CurrencyEvent currencyEvent) {

    }
}
