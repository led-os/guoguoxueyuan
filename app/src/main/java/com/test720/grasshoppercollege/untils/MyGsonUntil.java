package com.test720.grasshoppercollege.untils;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.Primitives;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.MyApplication;

import java.lang.reflect.Type;

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
 * 作者：水东流 编于 2018/12/23
 */
public class MyGsonUntil {
    private static final MyGsonUntil ourInstance = new MyGsonUntil();

    public static MyGsonUntil getInstance() {
        return ourInstance;
    }

    private MyGsonUntil() {
        gson = new Gson();
    }

    Gson gson;

    public <T> T fromJson(String json, Class<T> classOfT) throws JsonSyntaxException {
        Object object = gson.fromJson((String) json, (Type) classOfT);
        json = null;
        return Primitives.wrap(classOfT).cast(object);
    }


    /**
     * *****************************************************************
     * post 请求
     *
     * @ b 是否打印请求成功后msg信息
     */
    public void post(Context mContext, final String url, final HttpParams httpParams, final OnOkGoListener onOkGoListener) {
        OkGo.<String>post(url)
                .headers("token", MyApplication.getmInstance().getTokenStr())
                .tag(mContext.getApplicationContext()).params(httpParams)
                .execute(new StringCallback() {
                    @Override
                    public void onError(com.lzy.okgo.model.Response<String> response) {
                        super.onError(response);
                        onOkGoListener.onError();
                    }

                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                        onOkGoListener.onSuccess(response.body());
                    }
                });
    }


    public interface OnOkGoListener {
        void onSuccess(String s);

        void onError();

    }


}
