package com.test720.grasshoppercollege.untils;

import android.content.Context;
import android.content.Intent;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.HttpBean.UserData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.SingLoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

import io.rong.imkit.RongIM;
import www.test720.mylibrary.LogUtil;
import www.test720.mylibrary.MyLibUserData;
import www.test720.mylibrary.SPUtils;

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
 * 作者：水东流 编于 2018/10/30
 */
public class LoginUntil {
    private static final LoginUntil ourInstance = new LoginUntil();

    public static LoginUntil getInstance() {
        return ourInstance;
    }

    private LoginUntil() {
    }

    /**
     * 保存登陆数据
     *
     * @param str      登陆网络返回数据
     * @param mcontext 操作界面
     */
    public void saveData(String str, Context mcontext) {
        MyApplication.getmInstance().userData = new Gson().fromJson(str, UserData.class);
        MyApplication.getmInstance().setTokenStr(MyApplication.getmInstance().userData.getData().getTokenStr());
        MyApplication.getmInstance().setUid(MyApplication.getmInstance().userData.getData().getUid());
        MyLibUserData.getInstance().setUid(MyApplication.getmInstance().userData.getData().getUid());
        MyApplication.getmInstance().token = MyApplication.getmInstance().userData.getData().getToken();
        MyApplication.getmInstance().rong_token = MyApplication.getmInstance().userData.getData().getRong_token();
        MyApplication.getmInstance().rong_uid = MyApplication.getmInstance().userData.getData().getRong_uid() + "";
        /*登录融云*/
        MyApplication.getmInstance().RongListener();

        if (SPUtils.getString(mcontext, SPUtils.PROVINCE, "").equals("")) {
            //****若本地未保存地址将注册地址保存至本地**/
            SPUtils.putString(mcontext, SPUtils.PROVINCE, MyApplication.getmInstance().userData.getData().getProvince());
            SPUtils.putString(mcontext, SPUtils.CITY, MyApplication.getmInstance().userData.getData().getCity());
            SPUtils.putString(mcontext, SPUtils.AREA, MyApplication.getmInstance().userData.getData().getArea());
        }

    }

    /**
     * 每次接口请求时判断是否登陆已经失效
     *
     * @param str
     * @param context
     */
    public boolean login(String str, Context context) {
        if (codeIsThree(str)) {
            signlogin(context);
            return true;
        }
        return false;
    }

    //判断网络请求返回是否code===3等于三，说明登陆失效
    public boolean codeIsThree(String str) {
        try {
            JSONObject jsonObject = new JSONObject(str);
            if (jsonObject.getInt("code") == 1089) return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }


    /**
     * 异登陆
     */
    public void signlogin(Context context) {
        cleanLoginData(context);
        Intent intent = new Intent(context, SingLoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        LogUtil.logError("异机登陆");
    }

    /**
     * 清除登陆信息
     */
    public void cleanLoginData(Context context) {
        cleanCashData();
        SPUtils.putString(context, "phone", "");
        SPUtils.putString(context, "password", "");
        //退出融云登陆
        RongIM.getInstance().logout();
        //断开融云连接
        RongIM.getInstance().disconnect();
    }


    /**
     * 清除缓存数据
     */
    public void cleanCashData() {
        MyApplication.getmInstance().setUid("");
        MyApplication.getmInstance().rong_token = "";
        MyApplication.getmInstance().rong_uid = "";
        MyApplication.getmInstance().setTokenStr("guoguoxueyuan1000");//登陆token重置
        MyApplication.getmInstance().userData = null;

    }
}
