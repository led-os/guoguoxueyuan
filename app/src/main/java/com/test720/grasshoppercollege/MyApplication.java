package com.test720.grasshoppercollege;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.multidex.MultiDex;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;

import com.greendao.gen.bean.DatabaseManage;
import com.lzy.okgo.OkGo;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.test720.grasshoppercollege.HttpBean.UserData;
import com.test720.grasshoppercollege.untils.CrashHelp.CrashHandler;
import com.test720.grasshoppercollege.untils.LogUtil;
import com.test720.grasshoppercollege.untils.SingLogin;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import okhttp3.OkHttpClient;
import www.test720.mylibrary.SPUtils;

/**
 * _oo0oo_
 * o8888888o
 * 88" . "88
 * (| -_- |)
 * 0\  =  /0
 * ___/`---'\___
 * .' \\|     |// '.
 * / \\|||  :  |||// \
 * / _||||| -卍-|||||- \
 * |   | \\\  -  /// |   |
 * | \_|  ''\---/''  |_/ |
 * \  .-\__  '-'  ___/-. /
 * ___'. .'  /--.--\  `. .'___
 * ."" '<  `.___\_<|>_/___.' >' "".
 * | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 * \  \ `_.   \_ __\ /__ _/   .-` /  /
 * =====`-.____`.___ \_____/___.-`___.-'=====
 * `=---='
 * 佛祖保佑        永无BUG
 * 佛曰:
 * 程序园里程序天，程序天里程序员；
 * 程序猿人写程序，又拿程序换肉钱。
 * 肉饱继续桌前坐，饱暖还是桌前眠；
 * 半迷半醒日复日，码上码下年复年。
 * 但愿叱咤互联世，不愿搬砖码当前；
 * 诸葛周瑜算世事，我来算出得加钱。
 * 别人笑我忒直男，我笑自己太像猿；
 * 但见成都府国内，处处地地程序员。
 * Created by 水东流 on 2018/2/27 0027.
 */

public class MyApplication extends Application implements Thread.UncaughtExceptionHandler {
    public final static int APP_STATUS_KILLED = 0; // 表示应用是被杀死后在启动的
    public final static int APP_STATUS_NORMAL = 1; // 表示应用时正常的启动流程
    public static int APP_STATUS = APP_STATUS_KILLED; // 记录App的启动状态


    private static MyApplication mInstance;
    public OkHttpClient httpClient = new OkHttpClient();
    private String uid;
    public boolean isAppSend;//是否首次啓動
    public String token;
    public String tokenStr;//网络头标示
    public String rong_uid;
    public String rong_token;
    public UserData userData;
    public String nianji;
    public String kemu;
    public String b_j_phone;
    public IWXAPI api;

    DatabaseManage manage;//数据库

    public DatabaseManage getManage() {
        return manage;
    }

    private boolean loginDialogIsShow = true;

    public String getTokenStr() {
        return tokenStr;
    }

    public void setTokenStr(String tokenStr) {
        this.tokenStr = tokenStr;
    }

    public boolean isLoginDialogIsShow() {
        return loginDialogIsShow;
    }

    public void setLoginDialogIsShow(boolean loginDialogIsShow) {
        this.loginDialogIsShow = loginDialogIsShow;
    }

    public String getB_j_phone() {
        return b_j_phone;
    }

    public void setB_j_phone(String b_j_phone) {
        this.b_j_phone = b_j_phone;
    }

    int moduleType = -1;//当前正在浏览的模块，方便接口调用（部分接口参数须传递模块type）;

    public int getModuleType() {
        return moduleType;
    }

    public void setModuleType(int moduleType) {
        this.moduleType = moduleType;
    }

    /*身份id*/
    public String getUid() {
        if (uid == null || uid.equals("")) {
            uid = "";
            if (!isLoginDialogIsShow()) {
                Intent intent = new Intent(this, LoginDialogActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                setLoginDialogIsShow(true);
            }
        }
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        MultiDex.install(this);
        //         通过WXAPIFactory工厂,获取IWXAPI的实例
//        api = WXAPIFactory.createWXAPI(this, "wxa1698826e51a9b03", true);
        api = WXAPIFactory.createWXAPI(this, "wx3376bd22f9a30def", true);
//         将APP_ID注册到微信l
//        api.registerApp("wxa1698826e51a9b03");
        api.registerApp("wx3376bd22f9a30def");

        //提取保存在本地的数据
        nianji = SPUtils.getString(this, SPUtils.NIANGJI, "一年级");
        kemu = SPUtils.getString(this, SPUtils.KEMU, "英语");
        manage = DatabaseManage.getInstance().init(this);//初始化数据库
        OkGo.getInstance().init(this);


        /*融云*/
        /*融云*/
        RongIM.init(this);

        /*初始化友盟*/
//        UMConfigure.init(this, "585b874982b635649b000cc7", "umeng", UMConfigure.DEVICE_TYPE_PHONE, "");
        UMConfigure.init(this, "5c0f51c4f1f5562c830001e7", "umeng", UMConfigure.DEVICE_TYPE_PHONE, "");
        //友盟相关平台配置。注意友盟官方新文档中没有这项配置，但是如果不配置会吊不起来相关平台的授权界面
//        PlatformConfig.setWeixin("wxa1698826e51a9b03", "42edf87761bc5f103fb691e6c5ce6da7");//微信APPID和AppSecret
//        PlatformConfig.setWeixin("wx3376bd22f9a30def", "2d0b27003e2e2cb19a0f51d8fb6cc298");//微信APPID和AppSecret
        PlatformConfig.setWeixin("wx3376bd22f9a30def", "dfcd2629b1712e1517f1e257d7a88b3e");//微信APPID和AppSecret

        // PlatformConfig.setQQZone("1105830983", "Tz0Vd28Wdyb9v8V2");//QQAPPID和AppSecret
        PlatformConfig.setQQZone("1106052743", "v1fkLTAO8dbsfwtN");//QQAPPID和AppSecret
        UMConfigure.setLogEnabled(true);
        CrashHandler.getInstance().init(this);
        // 设置点击事件间隔
        registerActivityLifecycleCallbacks(lifecycleCallbacks);
        //重置tokenstr为游客模式
        setTokenStr("guoguoxueyuan1000");
    }


    public void RongListener() {
        RongIM.connect(
                rong_token,
                new RongIMClient.ConnectCallback() {
                    /**
                     * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
                     *                  2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
                     */
                    @Override
                    public void onTokenIncorrect() {
                        LogUtil.logError("token" + rong_token);
                        LogUtil.logError("--token失效");
                    }

                    /**
                     * 连接融云成功
                     * @param userid 当前 token 对应的用户 id
                     */
                    @Override
                    public void onSuccess(String userid) {
                        LogUtil.logError("-融云连接成功" + userid);
                    }

                    /**
                     * 连接融云失败
                     * @param errorCode 错误码，可到官网 查看错误码对应的注释
                     */
                    @Override
                    public void onError(RongIMClient.ErrorCode errorCode) {
                        LogUtil.logError("--融云连接失败" + errorCode.getMessage());
                    }
                });
        /*挤出登陆*/
        RongIM.setConnectionStatusListener(new SingLogin(getmInstance()));
    }


    /**
     * 重新初始化应用界面，清空当前Activity棧，并启动欢迎页面
     */
    public static void reInitApp() {
        Intent intent = new Intent(getmInstance(), AppFirstActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        getmInstance().startActivity(intent);
    }

    private ActivityLifecycleCallbacks lifecycleCallbacks = new ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {
            fixViewMutiClickInShortTime(activity);
        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {

        }
    };

    //防止短时间内多次点击，弹出多个activity 或者 dialog ，等操作
    private void fixViewMutiClickInShortTime(final Activity activity) {
        activity.getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                proxyOnlick(activity.getWindow().getDecorView(), 5);
            }
        });
    }

    private void proxyOnlick(View view, int recycledContainerDeep) {
        if (view.getVisibility() == View.VISIBLE) {
            if (view instanceof ViewGroup) {
                boolean existAncestorRecycle = recycledContainerDeep > 0;
                ViewGroup p = (ViewGroup) view;
                if (!(p instanceof AbsListView) || existAncestorRecycle) {
                    getClickListenerForView(view);
                    if (existAncestorRecycle) {
                        recycledContainerDeep++;
                    }
                } else {
                    recycledContainerDeep = 1;
                }
                int childCount = p.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View child = p.getChildAt(i);
                    proxyOnlick(child, recycledContainerDeep);
                }
            } else {
                getClickListenerForView(view);
            }
        }
    }

    /**
     * 通过反射  查找到view 的clicklistener
     *
     * @param view
     */
    public static void getClickListenerForView(View view) {
        try {
            Class viewClazz = Class.forName("android.view.View");
            //事件监听器都是这个实例保存的
            Method listenerInfoMethod = viewClazz.getDeclaredMethod("getListenerInfo");
            if (!listenerInfoMethod.isAccessible()) {
                listenerInfoMethod.setAccessible(true);
            }
            Object listenerInfoObj = listenerInfoMethod.invoke(view);

            @SuppressLint("PrivateApi") Class listenerInfoClazz = Class.forName("android.view.View$ListenerInfo");

            Field onClickListenerField = listenerInfoClazz.getDeclaredField("mOnClickListener");

            if (null != onClickListenerField) {
                if (!onClickListenerField.isAccessible()) {
                    onClickListenerField.setAccessible(true);
                }
                View.OnClickListener mOnClickListener = (View.OnClickListener) onClickListenerField.get(listenerInfoObj);
                if (!(mOnClickListener instanceof ProxyOnclickListener)) {
                    //自定义代理事件监听器
                    View.OnClickListener onClickListenerProxy = new ProxyOnclickListener(mOnClickListener);
                    //更换
                    onClickListenerField.set(listenerInfoObj, onClickListenerProxy);
                } else {
                    Log.e("OnClickListenerProxy", "setted proxy listener ");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //自定义的代理事件监听器
    static class ProxyOnclickListener implements View.OnClickListener {
        private View.OnClickListener onclick;

        private int MIN_CLICK_DELAY_TIME = 50;

        private long lastClickTime = 0;

        ProxyOnclickListener(View.OnClickListener onclick) {
            this.onclick = onclick;
        }

        @Override
        public void onClick(View v) {
            //点击时间控制
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
                lastClickTime = currentTime;
                Log.e("OnClickListenerProxy", "OnClickListenerProxy" + this);
                try {
                    if (onclick != null && v != null) onclick.onClick(v);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    /**
     * 此处判断是否为游客
     */
    public static synchronized MyApplication getmInstance() {
        return mInstance;
    }


    /*********************************activity管理**********************************************8*/


    @Override
    public void uncaughtException(final Thread thread, final Throwable e) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e("TAG", "currentThread:" + Thread.currentThread() + "---thread:" + thread.getId() + "---ex:" + e.toString());
            }
        }).start();
        SystemClock.sleep(2000);
        android.os.Process.killProcess(android.os.Process.myPid());
    }

}
