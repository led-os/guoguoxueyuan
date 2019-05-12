package com.test720.grasshoppercollege;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.myViews.XHLoadingView;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.LoadingState;
import com.test720.grasshoppercollege.untils.LoginUntil;
import com.test720.grasshoppercollege.untils.MyGsonUntil;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.Objects;

import butterknife.ButterKnife;
import butterknife.Unbinder;

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

public abstract class BaseFragment extends Fragment {
    public Activity mContext;
    private XHLoadingView loadingView;
    private Dialog gifLoadingView;
    public View rootView;
    Unbinder unbinder;
    private final static int OKHTTPCode = 10086;
    private final static int NETEMPTY = 101;
    private final static int NONET = 102;
    private final static int NETFAIL = 103;


    public MyHandler myHandler = new MyHandler(BaseFragment.this);
    public Handler mainHandler = new Handler(msg -> false);

    private static class MyHandler extends Handler {
        WeakReference<BaseFragment> mInterface;

        private MyHandler(BaseFragment mInterface) {
            this.mInterface = new WeakReference<>(mInterface);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            BaseFragment baseFragment = mInterface.get();
            baseFragment.handEvent(msg);
        }
    }

    /**
     * 主线程执行事件
     */
    private void handEvent(Message msg) {
        switch (msg.what) {
            case OKHTTPCode:
                try {
                    getSuccess(msg.getData().getString("str"), msg.getData().getInt("what"));
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
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (myHandler != null) myHandler.removeCallbacksAndMessages(null);
        if (mainHandler != null) mainHandler.removeCallbacksAndMessages(null);
        mainHandler = null;
        myHandler = null;
        EventBus.getDefault().unregister(this);
    }

    public abstract void initData();

    public abstract void setListener();

    @LayoutRes
    public abstract int setlayoutResID();

    public abstract void getSuccess(String s, int what);

    /*工作线程的网络请求返回*/
    public void ThreeSuccess(String s, int what) {

    }

    /*网络请求结束*/
    public void AfterHttp() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(setlayoutResID(), null);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = getActivity();
        initDialog();//初始化加载框
        setListener();
        initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        unbindDrawables(getView());
        rootView = null;
    }

    /**
     * 释放fragment
     *
     * @param view
     */
    private void unbindDrawables(View view) {
        if (view.getBackground() != null) {
            view.getBackground().setCallback(null);
        }
        if (view instanceof ViewGroup && !(view instanceof AdapterView)) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                unbindDrawables(((ViewGroup) view).getChildAt(i));
            }
            ((ViewGroup) view).removeAllViews();
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(CurrencyEvent event) {
    }

    /**
     * *****************************************************************
     * post 请求
     *
     * @ b 是否打印请求成功后msg信息
     */
    public void post(final String url, final HttpParams httpParams, final int what, final boolean b) {
        if (b) {
            ShowDialong();
        }
        if (loadingView != null) {
            loadingView.withOnRetryListener(new XHLoadingView.OnRetryListener() {
                @Override
                public void onRetry() {
                    post(url, httpParams, what, b);
                }
            });
        }
        if (!isConnectingToInternet()) {
            Message mesage = new Message();
            mesage.what = NONET;
            if (myHandler != null) myHandler.sendMessage(mesage);
            return;
        }

        MyGsonUntil.getInstance().post(mContext, url, httpParams, new MyGsonUntil.OnOkGoListener() {
            @Override
            public void onSuccess(String s) {
                if (s == null) {
                    Message message = new Message();
                    message.what = NETEMPTY;
                    if (myHandler != null) myHandler.sendMessage(message);
                } else {
                    DismissDialong();
                }
                if (LoginUntil.getInstance().login(s, getActivity())) {//判断是否异登陆
                    return;
                }
                ThreeSuccess(s, what);
                Message message = new Message();
                message.what = 10086;
                Bundle data = new Bundle();
                data.putString("str", s);
                data.putInt("what", what);
                message.setData(data);
                if (myHandler != null) myHandler.sendMessage(message);
            }

            @Override
            public void onError() {
                Message message = new Message();
                message.what = NETFAIL;
                if (myHandler != null) myHandler.sendMessage(message);
                getFail();
            }
        });

    }

    /**
     * *****************************************************************
     * post 请求
     *
     * @ b 是否打印请求成功后msg信息
     */
    public void post(final String url, final HttpParams httpParams, final int what) {
        post(url, httpParams, what, true);
    }

    /*网络请求失败后的方法*/
    public void getFail() {

    }

    /*判断网络请求返回是否code===1*/
    public boolean codeIsOne(String s) {
        return codeIsOne(s, true);
    }

    /*判断网络请求返回是否code===1*/
    public boolean codeIsOne(String s, boolean b) {
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(s);
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

    /**
     * 检查当前网络是否可用
     * 检测当前版本,
     *
     * @return 网络是否正常
     */
    public boolean isConnectingToInternet() {
        if (getActivity() == null || getActivity().isFinishing()) {
            return true;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        //新版本调用方法获取网络状态
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            assert connectivityManager != null;
            Network[] networks = connectivityManager.getAllNetworks();
            NetworkInfo networkInfo;
            for (Network mNetwork : networks) {
                networkInfo = connectivityManager.getNetworkInfo(mNetwork);
                if (networkInfo.getState().equals(NetworkInfo.State.CONNECTED)) {
                    return true;
                }
            }
        } else {
            //否则调用旧版本方法
            if (connectivityManager != null) {
                NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
                if (info != null) {
                    for (NetworkInfo anInfo : info) {
                        if (anInfo.getState() == NetworkInfo.State.CONNECTED) {
                            Log.d("Network",
                                    "NETWORKNAME: " + anInfo.getTypeName());
                            return true;
                        }
                    }
                }
            }
        }
        //提示网络没有连接
        ShowToast("网络未连接");
        return false;
    }

    /**
     * 显示一个加载过渡框
     */
    public void ShowDialong() {
        /*加载中*/
        if (loadingView != null && gifLoadingView != null) {
            loadingView.setVisibility(View.VISIBLE);
            loadingView.setState(LoadingState.STATE_LOADING);
            gifLoadingView.show();
        } else {
            initDialog();
            ShowDialong();
        }
    }

    private void initDialog() {
        if (gifLoadingView == null) {
            @SuppressLint("InflateParams") View view = LayoutInflater.from(getActivity()).inflate(R.layout.progress_bar_style, null);
            loadingView = view.findViewById(R.id.loading);
            loadingView.withLoadEmptyText("≥﹏≤ , 啥也木有 !").withEmptyIcon(R.drawable.disk_file_no_data).withBtnEmptyEnnable(false)
                    .withErrorIco(R.drawable.ic_chat_empty1).withLoadErrorText("(῀( ˙᷄ỏ˙᷅ )῀)ᵒᵐᵍᵎᵎᵎ,我家程序猿跑路了 !").withBtnErrorText("再试试!!!")
                    .withLoadNoNetworkText("你挡着信号啦o(￣ヘ￣o)☞ᗒᗒ 你走").withNoNetIcon(R.drawable.ic_chat_empty1).withBtnNoNetText("网弄好了，重试")
                    .withLoadingIcon(R.drawable.loading_animation).withLoadingText("加载中...").withOnRetryListener(new XHLoadingView.OnRetryListener() {
                @Override
                public void onRetry() {
                    ShowToast("已经在努力重试了");
                }
            }).build();
            loadingView.build();
            gifLoadingView = new Dialog(mContext, R.style.messagebox_style);
           /* Window window = gifLoadingView.getWindow();
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
        if (loadingView != null) {
            loadingView.setVisibility(View.VISIBLE);
            loadingView.setState(LoadingState.STATE_ERROR);
        }
    }

    /*数据为空*/
    public void emptyDialog() {
        if (loadingView != null) {
            loadingView.setVisibility(View.VISIBLE);
            loadingView.setState(LoadingState.STATE_EMPTY);
        }
    }

    /*无网络*/
    public void noNetDialog() {
        if (loadingView != null) {
            loadingView.setVisibility(View.VISIBLE);
            loadingView.setState(LoadingState.STATE_NO_NET);
        }
    }

    /**
     * 消失过渡框
     */
    public void DismissDialong() {
        if (mainHandler != null) myHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (gifLoadingView != null) {
                    gifLoadingView.dismiss();
                    gifLoadingView = null;
                }
            }
        }, 500);
    }

    /**
     * Tost消息提醒
     */
    public void ShowToast(String text) {
        Toast.makeText(getActivity().getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    /**
     * 封装跳转方式
     *
     * @param clazz  跳转到指定页面
     * @param finish 是否关闭当前页面
     */
    public void jumpToActivity(Class<? extends Activity> clazz, boolean finish) {
        Intent intent = new Intent(getActivity(), clazz);
        startActivity(intent);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getActivity()).overridePendingTransition(R.anim.exit_anim, R.anim.out_to_up);
            if (finish) {
                getActivity().finish();
            }
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
            Objects.requireNonNull(getActivity()).overridePendingTransition(R.anim.exit_anim, R.anim.out_to_up);
            if (finish) {
                getActivity().finish();
            }
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
        Intent intent = new Intent(getActivity(), clazz);
        if (bundle != null)
            intent.putExtras(bundle);
        startActivity(intent);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getActivity()).overridePendingTransition(R.anim.exit_anim, R.anim.out_to_up);
            if (finish) {
                getActivity().finish();
            }
        }
    }

    /**
     * 启动Activity，
     *
     * @param clazz  到指定页面
     * @param bundle 传递参数
     * @param flags  Intent.setFlag参数，设置为小于0，则不设置
     * @param finish 是否结束当前界面
     */
    public void jumpToActivity(Class<? extends Activity> clazz, Bundle bundle, int flags, boolean finish) {
        Intent intent = new Intent(getActivity(), clazz);
        if (bundle != null)
            intent.putExtras(bundle);
        if (flags > 0) {
            intent.setFlags(flags);
        }
        startActivity(intent);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getActivity()).overridePendingTransition(R.anim.exit_anim, R.anim.out_to_up);
            if (finish) {
                getActivity().finish();
            }
        }
    }


    /*分享*/
    public void share() {
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(Objects.requireNonNull(getActivity()), mPermissionList, 123);
        }
        UMImage image = new UMImage(getActivity(), R.mipmap.icon_app);//分享图标
        String url = HttpUntil.GetIntent().getShare() + 1;
        final UMWeb web = new UMWeb(url); //切记切记 这里分享的链接必须是http开头
        web.setTitle("蝈蝈学苑");//标题
        web.setThumb(image);  //缩略图
        web.setDescription("蝈蝈学苑app是一款不错的移动学习软件，包括听写，口述等");//描述
        new ShareAction(getActivity())
                .setDisplayList(SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE)
                .setShareboardclickCallback(new ShareBoardlistener() {
                    @Override
                    public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                        if (share_media == SHARE_MEDIA.QQ) {
                            new ShareAction(getActivity()).setPlatform(SHARE_MEDIA.QQ)
                                    .withMedia(web)
                                    .setCallback(umShareListener)
                                    .share();
                        } else if (share_media == SHARE_MEDIA.WEIXIN) {

                            new ShareAction(getActivity()).setPlatform(SHARE_MEDIA.WEIXIN)
                                    .withMedia(web)
                                    .setCallback(umShareListener)
                                    .share();
                        } else if (share_media == SHARE_MEDIA.QZONE) {
                            new ShareAction(getActivity()).setPlatform(SHARE_MEDIA.QZONE)
                                    .withMedia(web)
                                    .setCallback(umShareListener)
                                    .share();
                        } else if (share_media == SHARE_MEDIA.WEIXIN_CIRCLE) {
                            new ShareAction(getActivity()).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
                                    .withMedia(web)
                                    .setCallback(umShareListener)
                                    .share();
                        }
                    }
                }).open();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        new ShareAction(getActivity()).withText("hello").setDisplayList(SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN)
                .setCallback(umShareListener).open();

    }

    //分享
    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(getActivity(), platform + " 分享成功", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(getActivity(), platform + " 分享失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(getActivity(), platform + " 分享取消", Toast.LENGTH_SHORT).show();
        }
    };
}
