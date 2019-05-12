package com.test720.grasshoppercollege;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.androidkun.breakpoints.event.EventMessage;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 水东流 on 2018/7/12.
 */

public abstract class MyDialogFragment extends DialogFragment {
    public Context mContext;
    private final static int OKHTTPCode = 10086;
    private Handler mainHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case OKHTTPCode:
                    try {
                        getSuccess(msg.getData().getString("str"), msg.getData().getInt("what"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
            return false;
        }
    });

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventMessage eventMessage) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public abstract void getSuccess(String s, int what);

    /**
     * *****************************************************************
     * post 请求
     *
     * @ b 是否打印请求成功后msg信息
     */
    public void post(final String url, final HttpParams httpParams, final int what) {

        OkGo.<String>post(url).tag(this).params(httpParams).execute(new StringCallback() {
            @Override
            public void onError(com.lzy.okgo.model.Response<String> response) {
                super.onError(response);
            }

            @Override
            public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                myOnSuccess(response.body(), what);
                Message message = new Message();
                message.what = 10086;
                Bundle data = new Bundle();
                data.putString("str", response.body());
                data.putInt("what", what);
                message.setData(data);
                mainHandler.sendMessage(message);
            }
        });
    }

    /**
     * 网络返回
     */
    public void myOnSuccess(String str, int what) {

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

    /**
     * Tost消息提醒
     */
    public void ShowToast(String text) {
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void show(FragmentManager manager, String tag) {
        try {
            FragmentTransaction ft = manager.beginTransaction();
            ft.add(this, tag).addToBackStack(null);
            ft.commitAllowingStateLoss();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }


}
