package com.test720.grasshoppercollege;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.androidkun.breakpoints.services.DownloadService;

/**
 * app 的启动初始界面，标示当前是否是正常启动
 */
public class AppFirstActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.APP_STATUS = MyApplication.APP_STATUS_NORMAL;
        MyApplication.getmInstance().isAppSend = true;
        //启动前先停止之前的下载服务
        Intent intent = new Intent(this,
                DownloadService.class);
        stopService(intent);
        goNext();
    }

    private void goNext() {
        Intent intent = new Intent(AppFirstActivity.this, StartActivity.class);
        startActivity(intent);
        finish();
    }
}
