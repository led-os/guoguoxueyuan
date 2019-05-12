package com.test720.grasshoppercollege;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.myViews.XHLoadingView;
import com.test720.grasshoppercollege.untils.LoadingState;
import com.test720.grasshoppercollege.untils.LogUtil;
import com.test720.grasshoppercollege.untils.LoginUntil;
import com.test720.grasshoppercollege.untils.StatusBar;
import com.test720.grasshoppercollege.untils.ToolBarHelper;
import com.umeng.socialize.UMShareAPI;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.ref.WeakReference;

import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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
 * 绿水青山，独恋西川。人生几何，与友共欢。世事无常，随心翱翔。千思系乡，乡位西方。愿与东风共舞，舞飞家乡。
 * 思亲友夜语，独我异乡。且一叶扁舟，观潮起潮落。浪有礁岸击声似乐，舟有鸥旋航扬为伴，碧水有蓝天一线，独我孤思远乡，
 * 观此云飘云散。
 * Created by 水东流 on 2018/2/27 0027.
 */

public abstract class BaseToolActivity extends AppCompatActivity {
    private Dialog gifLoadingView;
    private XHLoadingView loadingView;
    private final static int OKHTTPCode = 51;
    private final static int NETEMPTY = 101;
    private final static int NONET = 102;
    private final static int NETFAIL = 103;
    private final static int TOAST = 304;
    private final static int INITDIALOG = 404;
    public AppCompatActivity mcontext;
    public String TAG = "BarBaseActivity";
    public ToolBarHelper mToolBarHelper;
    public Toolbar toolbar;
    public boolean isFull = false;//设置是否全屏
    public boolean isTouMing = true;//设置状态栏是否透明
    public boolean noSuPing = false;//是否设置默认竖屏

    public Handler mainHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            MainHandlerVoid(msg);
            return false;
        }
    });
    private MyHandler myHandler = new MyHandler(this);

    private static class MyHandler extends Handler {
        WeakReference<BaseToolActivity> mInterface;

        private MyHandler(BaseToolActivity mInterface) {
            this.mInterface = new WeakReference<>(mInterface);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            BaseToolActivity baseFragment = mInterface.get();
            baseFragment.handEvent(msg);
        }
    }

    /**
     * 主线程 处理事件
     *
     * @param msg 消息
     */
    private void handEvent(Message msg) {
        switch (msg.what) {
            case OKHTTPCode:
                try {
                    GetHttpBackStr(msg.getData().getString("str"), msg.getData().getInt("what"));
                    AfterHttp();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case NONET:
                noNetDialog();
                break;
            case NETEMPTY:
                emptyDialog();
                break;
            case NETFAIL:
                failDialog();
                break;
            case TOAST:
                Toast.makeText(getApplicationContext(), msg.getData().getString("text"), Toast.LENGTH_SHORT).show();
                break;
            case INITDIALOG://初始化加载框
                if (gifLoadingView == null) {
                    initDialog();
                }
                /*加载中*/
                if (gifLoadingView != null) {
                    loadingView.setVisibility(View.VISIBLE);
                    loadingView.setState(LoadingState.STATE_LOADING);
                    gifLoadingView.show();
                }
                break;
        }
    }

    /*发送信息给handler，外部实现*/
    public void MainHandlerVoid(Message msg) {
    }


    //工具帮助

    /**
     * Tost消息提醒
     */
    public void ShowToast(String text) {
        Message message = new Message();
        message.what = TOAST;
        Bundle bundle = new Bundle();
        bundle.putString("text", text);
        message.setData(bundle);
        if (myHandler != null) myHandler.sendMessage(message);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(CurrencyEvent currencyEvent) {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (MyApplication.APP_STATUS != MyApplication.APP_STATUS_NORMAL) { // 非正常启动流程，直接重新初始化应用界面
            LogUtil.logError("非正常启动");
            MyApplication.reInitApp();
            finish();
        } else { // 正常启动流程
            setSys();
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            if (isFull) {
                requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏标题
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
            }
            super.onCreate(savedInstanceState);
            mcontext = this;
            //获取当前显示activity的名字
            TAG = this.getLocalClassName();
            //实例化过渡框
            setContentView(setlayoutResID());
            //注解声明
            ButterKnife.bind(this);
            //键盘默认为不弹出状态
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            EventBus.getDefault().register(this);
            initView();
            initData();
        }
    }

    public void setSys() {
    }

    /*判断网络请求返回是否code===1*/
    public boolean codeIsOne(String s) {
        return codeIsOne(s, true);
    }


    /*判断网络请求返回是否code===1*/
    public boolean codeIsOne(String s, boolean b) {
        try {
            JSONObject jsonObject = new JSONObject(s);
            if (b) {
                ShowToast(jsonObject.getString("msg"));
            }
            if (jsonObject.getInt("code") == 1) return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public int getJsonCode(String s, boolean b) {
        JSONObject jsonObject;
        int code = -1;
        try {
            jsonObject = new JSONObject(s);
            if (b) {
                ShowToast(jsonObject.getString("msg"));
            }
            code = jsonObject.getInt("code");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return code;
    }

    //此方法只是关闭软键盘 可以在finish之前调用一下
    public void hintKbTwo() {
        View view = getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            assert inputMethodManager != null;
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * 检查当前网络是否可用
     *
     * @return 是否有网 true 无网，，false 有网
     */
    public boolean isNetwork(Activity activity) {
        Context context = activity.getApplicationContext();
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) {
            return true;
        }
        // 获取NetworkInfo对象
        NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();
        if (networkInfo != null && networkInfo.length > 0) {
            for (int i = 0; i < networkInfo.length; i++) {
                System.out.println(i + "===状态===" + networkInfo[i].getState());
                System.out.println(i + "===类型===" + networkInfo[i].getTypeName());
                if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
                    return false;
                }
                // 判断当前网络状态是否为连接状态
            }
        }
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        hintKbTwo();
    }


    //************************************************** ToolBar设置 **************************************************/

    /**
     * 重写setContentView
     * <p>
     * 1.添加沉浸式状态栏    initWindow();
     * 2.添加强制竖屏   setRequestedOrientation(ActivityInfoSCREEN_ORIENTATION_PORTRAIT);
     * 3.是否添加toolbar
     */
    @Override
    public void setContentView(int layoutResID) {
        if (isFull) {
            super.setContentView(layoutResID);
            return;
        }
        //沉浸式要在设置布局前设置
        initWindow();
        if (!noSuPing) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//竖屏
        }
        SetSystemOperation();
        //判断是否有标题有则设置toolbar
        if (setTitle() != null) {
            mToolBarHelper = new ToolBarHelper(getApplicationContext(), layoutResID, this);
            toolbar = mToolBarHelper.getToolBar();
            toolbar.setTitle("");
            setContentView(mToolBarHelper.getContentView());
            /*把 toolbar 设置到Activity 中*/
            setSupportActionBar(toolbar);
            /*自定义的一些操作*/
            onCreateCustomToolBar(toolbar);
            mToolBarHelper.setTitle(setTitle());
        } else {
            super.setContentView(layoutResID);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mToolBarHelper = null;
        gifLoadingView = null;
        loadingView = null;
        mToolBarHelper = null;
        if (myHandler != null) myHandler.removeCallbacksAndMessages(null);
        if (myHandler != null) myHandler = null;
        if (mainHandler != null) mainHandler.removeCallbacksAndMessages(null);
        setContentView(R.layout.view_null);
        EventBus.getDefault().unregister(this);
    }

    //post请求
    public void Post(String url, FormBody.Builder formBuilder, final int what) {
        Post(url, formBuilder, what, true);
    }

    /**
     * *****************************************************************
     * post 请求
     * 发文件方法
     *
     * @ b 是否打印请求成功后msg信息
     */
    public void postResponse(final String url, final HttpParams httpParams, final int what, final boolean b) {
        if (httpParams == null || url == null) return;
        if (b) {
            ShowDialong();
        }
        if (loadingView != null)
            loadingView.withOnRetryListener(new XHLoadingView.OnRetryListener() {
                @Override
                public void onRetry() {
                    postResponse(url, httpParams, what, b);
                }
            });
        if (isNetwork(mcontext)) {
            Message mesage = new Message();
            mesage.what = NONET;
            if (myHandler != null) myHandler.sendMessage(mesage);
            return;
        }

        OkGo.<String>post(url).headers("token", MyApplication.getmInstance().getTokenStr()).tag(this).params(httpParams).execute(new StringCallback() {
            @Override
            public void onError(com.lzy.okgo.model.Response<String> response) {
                super.onError(response);
                // LogUtil.logError(response.body());
                Message message = new Message();
                message.what = NETFAIL;
                if (myHandler != null) myHandler.sendMessage(message);
                netFail();
            }

            @Override
            public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body());
                    if (LoginUntil.getInstance().login(response.body(), mcontext)) {//判断是否异登陆
                        return;
                    }
                    if (jsonObject.getString("data") == null) {
                        Message message = new Message();
                        message.what = NETEMPTY;
                        if (myHandler != null) myHandler.sendMessage(message);
                    } else {
                        DismissDialong();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Message message = new Message();
                    message.what = NETFAIL;
                    if (myHandler != null) myHandler.sendMessage(message);
                }
                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putString("str", response.body());
                bundle.putInt("what", what);
                message.setData(bundle);
                message.what = OKHTTPCode;
                if (myHandler != null) myHandler.sendMessage(message);
                ThreadHttpBack(response.body(), what);
                DismissDialong();
            }
        });
    }


    //post请求
    public void Post(final String url, final FormBody.Builder formBuilder, final int what, final boolean b) {
        if (formBuilder == null || url == null) return;
        if (b) {
            ShowDialong();
        }
        if (loadingView != null)
            loadingView.withOnRetryListener(new XHLoadingView.OnRetryListener() {
                @Override
                public void onRetry() {
                    Post(url, formBuilder, what, b);
                }
            });
        if (isNetwork(mcontext)) {
            Message mesage = new Message();
            mesage.what = NONET;
            if (myHandler != null) myHandler.sendMessage(mesage);
            return;
        }
        final RequestBody requestBody = formBuilder.build();
        Request.Builder builder = new Request.Builder()
                .url(url)
                .header("cookie", "JSESSIONID=EB36DE5E50E342D86C55DAE0CDDD4F6D")
                .addHeader("content-type", "text/html;charset:utf-8")
                .addHeader("token", MyApplication.getmInstance().getTokenStr())// 自定义的header
                .post(requestBody);
        Call call = MyApplication.getmInstance().httpClient.newCall(builder.build());
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                LogUtil.logError("onFailure");
                e.printStackTrace();
                Message message = new Message();
                message.what = NETFAIL;
                if (myHandler != null) myHandler.sendMessage(message);
                netFail();
                DismissDialong();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                //从response从获取服务器返回的数据，转成字符串处理,返回到主线程
                try {
                    assert response.body() != null;
                    String str = new String(response.body().bytes(), "utf-8");
                    if (LoginUntil.getInstance().login(str, mcontext)) {//判断是否异登陆
                        return;
                    }
                    JSONObject jsonObject = new JSONObject(str);
                    if (jsonObject.getString("data") == null) {
                        Message netmsg = new Message();
                        netmsg.what = NETEMPTY;
                        if (myHandler != null) myHandler.sendMessage(netmsg);
                    } else {
                        ThreadHttpBack(str, what);
                        Message message = new Message();
                        Bundle bundle = new Bundle();
                        bundle.putString("str", str);
                        bundle.putInt("what", what);
                        message.setData(bundle);
                        message.what = OKHTTPCode;
                        if (myHandler != null) myHandler.sendMessage(message);
                    }
                    DismissDialong();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Message message = new Message();
                    message.what = NETFAIL;
                    if (myHandler != null) myHandler.sendMessage(message);
                }
                DismissDialong();
            }
        });//加入调度队列
    }


    /*网络请求失败*/
    public void netFail() {
    }


    //设置布局之前的操作
    public void SetSystemOperation() {

    }

    public void onCreateCustomToolBar(Toolbar toolbar) {
        toolbar.setContentInsetsRelative(0, 0);
    }

    //设置toolbar标题,toolbar存在有效
    protected abstract String setTitle();


    //设置布局
    @LayoutRes
    protected abstract int setlayoutResID();

    //初始化控件
    public void initView() {

    }

    //初始化数据
    protected abstract void initData();

    /**
     * 网络请求返回
     *
     * @param str  返回字符
     * @param what 网络请求标示
     */
    public abstract void GetHttpBackStr(String str, int what);

    /**
     * 网络返回
     */
    public void ThreadHttpBack(String str, int what) {

    }

    /*网络请求返回处理逻辑结束后的状态处理事件*/
    public void AfterHttp() {

    }

    /**
     * 设置沉浸式状态栏
     */
    private void initWindow() {
        // 仅挨api_19以上支持
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//			// 透明导航栏--透明后，在虚拟键盘的华为手机测试很丑，故不透明
//			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            Window win = this.getWindow();
            ViewGroup decorViewGroup = (ViewGroup) win.getDecorView();
            View statusBarView = new View(this);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, StatusBar.getStatusBarHeight(this));
            if (isTouMing) {
                statusBarView.setBackgroundResource(R.color.touming);// 运用主题色
            } else {
                statusBarView.setBackgroundResource(R.color.bluebantoumingtwo);// 运用主题色
            }
            decorViewGroup.addView(statusBarView, lp);
        }
    }


    /**
     * 显示一个加载过渡框
     */
    public void ShowDialong() {
        if (mcontext == null || mcontext.isDestroyed() || mcontext.isFinishing()) {
            return;
        }
        Message message = new Message();
        message.what = INITDIALOG;
        if (myHandler != null) myHandler.sendMessage(message);

    }

    private void initDialog() {
        if (gifLoadingView == null) {
            @SuppressLint("InflateParams") View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.progress_bar_style, null);
            loadingView = view.findViewById(R.id.loading);
            loadingView.withLoadEmptyText("≥﹏≤ , 啥也木有 !").withEmptyIcon(R.drawable.disk_file_no_data).withBtnEmptyEnnable(false)
                    .withErrorIco(R.drawable.ic_chat_empty1).withLoadErrorText("(῀( ˙᷄ỏ˙᷅ )῀)ᵒᵐᵍᵎᵎᵎ,我家程序猿跑路了!").withBtnErrorText("重试!!!")
                    .withLoadNoNetworkText("当前无网络，请检查网络").withNoNetIcon(R.drawable.ic_chat_empty1).withBtnNoNetText("网弄好了，重试")
                    .withLoadingIcon(R.drawable.loading_animation).withLoadingText("加载中...")
                    .withOnRetryListener(new XHLoadingView.OnRetryListener() {
                        @Override
                        public void onRetry() {
                            ShowToast("已经在努力重试了");
                        }
                    }).build();

            loadingView.build();

            gifLoadingView = new Dialog(mcontext, R.style.messagebox_style);
         /*   Window window = gifLoadingView.getWindow();
            window.setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
            if (window != null) {
                window.setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND,
                        WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
                window.setGravity(Gravity.CENTER);
            }*/
            gifLoadingView.setContentView(view);
            gifLoadingView.setCanceledOnTouchOutside(false);
        }
    }

    /*加载失败*/

    public void failDialog() {
        if (mcontext == null || mcontext.isDestroyed() || mcontext.isFinishing()) {
            return;
        }
        if (loadingView != null) {
            loadingView.setVisibility(View.VISIBLE);
            loadingView.setState(LoadingState.STATE_ERROR);
        }
    }

    /*数据为空*/
    public void emptyDialog() {
        if (mcontext == null || mcontext.isDestroyed() || mcontext.isFinishing()) {
            return;
        }
        if (loadingView != null) {
            loadingView.setVisibility(View.VISIBLE);
            loadingView.setState(LoadingState.STATE_EMPTY);
        }
    }

    /*无网络*/
    public void noNetDialog() {
        if (mcontext == null || mcontext.isDestroyed() || mcontext.isFinishing()) {
            return;
        }
        if (loadingView != null) {
            loadingView.setVisibility(View.VISIBLE);
            loadingView.setState(LoadingState.STATE_NO_NET);
        }
    }

    /**
     * 消失过渡框
     */
    public void DismissDialong() {
      /*  if (mcontext == null || mcontext.isDestroyed() || mcontext
      .isFinishing()) {
            return;
        }*/
        if (myHandler != null) myHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    LogUtil.logError("关闭网络dialog");
                    if (gifLoadingView != null) {
                        gifLoadingView.dismiss();
                        gifLoadingView = null;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 500);
    }


    /**
     * 封装跳转方式
     *
     * @param clazz  跳转到指定页面
     * @param finish 是否关闭当前页面
     */
    public void jumpToActivity(Class<? extends Activity> clazz, boolean finish) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            overridePendingTransition(R.anim.exit_anim, R.anim.out_to_up);
        }
        if (finish) {
            finish();
        }
    }

    /**
     * 封装跳转方式
     *
     * @param intent 传递参数
     * @param finish 是否关闭当前页面
     */
    public void jumpToActivity(Intent intent, boolean finish) {
        startActivity(intent);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            overridePendingTransition(R.anim.exit_anim, R.anim.out_to_up);
        }
        if (finish) {
            finish();
        }
    }


    /**
     * 封装跳转方式
     *
     * @param clazz  跳转到指定页面
     * @param bundle 传递参数
     * @param finish 是否关闭当前页面
     */
    public void jumpToActivity(Class<? extends Activity> clazz, Bundle bundle, boolean finish) {
        Intent intent = new Intent(this, clazz);
        if (bundle != null)
            intent.putExtras(bundle);
        startActivity(intent);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            overridePendingTransition(R.anim.exit_anim, R.anim.out_to_up);
        }
        if (finish) {
            finish();
        }

    }

    /**
     * 启动Activity，
     *
     * @param clazz  到指定页面
     * @param bundle 传递参数
     * @param flags  Intent.setFlag参数，设置为小于0，则不设置
     * @param finish 是否关闭当前界面
     */
    public void jumpToActivity(Class<? extends Activity> clazz, Bundle bundle, int flags, boolean finish) {
        Intent intent = new Intent(this, clazz);
        if (bundle != null)
            intent.putExtras(bundle);
        if (flags > 0) {
            intent.setFlags(flags);
        }
        startActivity(intent);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            overridePendingTransition(R.anim.exit_anim, R.anim.out_to_up);
        }
        if (finish) {
            finish();
        }
    }


    //在API23+以上，不仅要在AndroidManifest.xml里面添加权限 还要在JAVA代码中请求权限：
// Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    /**
     * Checks if the app has permission to write to device storage
     * <p>
     * If the app does not has permission then the user will be prompted to
     * grant permissions
     *
     * @param activity 界面
     */
    public void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permission1 = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.CAMERA);

        if (permission != PackageManager.PERMISSION_GRANTED
                || permission1 != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the u
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
