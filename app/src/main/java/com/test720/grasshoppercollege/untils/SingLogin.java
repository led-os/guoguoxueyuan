package com.test720.grasshoppercollege.untils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import io.rong.imlib.RongIMClient;
import www.test720.mylibrary.LogUtil;

/**
 * 佛祖保佑        永无BUG
 * 佛曰:
 * 程序园里程序天，程序天里程序员；
 * 程序猿人写程序，又拿程序换肉钱。
 * 肉饱继续桌前坐，饱暖还是桌前眠；
 * 半迷半醒日复日，码上码下年复年。
 * 但愿叱咤互联世，不愿搬砖码当前；
 * 诸葛周瑜算世事，我算需求得加钱。
 * 别人笑我忒直男，我说自己是程猿；
 * 但见成都府国内，处处地地程序员。
 * 作者：水东流 编于 2018/10/11
 */
public class SingLogin implements RongIMClient.ConnectionStatusListener {
    private Context context;

    public SingLogin(Context context) {
        this.context = context;
    }

    @Override
    public void onChanged(ConnectionStatus connectionStatus) {
        switch (connectionStatus) {

            case CONNECTED://连接成功。
                LogUtil.logError("连接成功");
                break;
            case DISCONNECTED://断开连接。
                LogUtil.logError("断开连接");
                break;
            case CONNECTING://连接中。
                LogUtil.logError("连接中");
                break;
            case NETWORK_UNAVAILABLE://网络不可用。
                LogUtil.logError("网络不可用");
                break;
            case KICKED_OFFLINE_BY_OTHER_CLIENT://用户账户在其他设备登录，本机会被踢掉线
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        LoginUntil.getInstance().signlogin(context);
                        LogUtil.logError("异机登陆");
                    }
                });
                break;
        }
    }
}

