package com.test720.grasshoppercollege;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.LogUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class SetIpActivity extends BaseToolActivity {


    String filePath = "/storage/emulated/0/MagazineUnlock/Balance(magazine)-05-2.3.001-bigpicture_05_3.jpg";
    @BindView(R.id.yanHua)
    Button yanHua;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.one)
    RadioButton one;
    @BindView(R.id.two)
    RadioButton two;
    @BindView(R.id.three)
    RadioButton three;
    @BindView(R.id.four)
    RadioButton four;
    @BindView(R.id.ok)
    FloatingActionButton ok;


    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_set_ip;
    }

    @Override
    protected void initData() {
        yanHua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpToActivity(TestYanHuaActivity.class, false);
            }
        });

     /*   //初始化OssService类，参数分别是Content，accessKeyId，accessKeySecret，endpoint，bucketName（后4个参数是您自己阿里云Oss中参数）
        OssService ossService = new OssService(this);
//开始上传，参数分别为content，上传的文件名filename，上传的文件路径filePath
        List<String> list = new ArrayList<>();
        list.add(filePath);
        list.add(filePath);
        list.add(filePath);
        ossService.beginUploads("2789", list, new OssService.SendBack() {
            @Override
            public void sucess(List<String> filepath) {

            }

            @Override
            public void failure() {

            }
        });*/
    }

    @Override
    public void GetHttpBackStr(String str, int what) {

    }


    @OnClick(R.id.ok)
    public void onViewClicked() {
        if (one.isChecked()) {
            HttpUntil.GetIntent().setIP(one.getText().toString());
        } else if (two.isChecked()) {
            HttpUntil.GetIntent().setIP(two.getText().toString());
        } else if (three.isChecked()) {
            HttpUntil.GetIntent().setIP(three.getText().toString());
        } else if (four.isChecked()) {
            HttpUntil.GetIntent().setIP(four.getText().toString());
        }
        jumpToActivity(StartActivity.class, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.logError("onCreate");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.logError("onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtil.logError("onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.logError("onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.logError("onDestroy");
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.logError("onStart");
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        LogUtil.logError("onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        LogUtil.logError("onRestoreInstanceState");
    }
}
