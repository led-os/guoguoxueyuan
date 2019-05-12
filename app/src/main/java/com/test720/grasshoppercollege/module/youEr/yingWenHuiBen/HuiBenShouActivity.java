package com.test720.grasshoppercollege.module.youEr.yingWenHuiBen;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.youEr.yingWenHuiBen.bean.HuiBenGuanKaData;
import com.test720.grasshoppercollege.module.youEr.yingWenHuiBen.zuoPin.MyHuiBenActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class HuiBenShouActivity extends BaseToolActivity {
    HuiBenGuanKaData data;

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_hui_ben_shou;
    }

    @Override
    protected void initData() {
      /*  FormBody.Builder builder = new FormBody.Builder();
        builder.add("type", getIntent().getIntExtra("type", 52) + "");
        builder.add("uid", MyApplication.getmInstance().getUid());
        Post(HttpUntil.GetIntent().ChildBookindex(), builder, 1);*/
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        switch (what) {
            case 1:
                if (codeIsOne(str, false)) {
                    data = new Gson().fromJson(str, HuiBenGuanKaData.class);
                    /*****顶部*****/
                    initHead();
                }
                break;
        }

    }

    /**
     * 头部加载
     */
    private void initHead() {

    }

    @BindView(R.id.back)
    ImageView back;


    private void jum(int size) {
        MyApplication.getmInstance().setLoginDialogIsShow(false);
        if(MyApplication.getmInstance().getUid().equals(""))return;
        Intent intent = new Intent(mcontext, HuiBenBookListActivity.class);
        intent.putExtra("checkpoint", size);
        jumpToActivity(intent, false);
    }

    @OnClick({R.id.back, R.id.myZuoPin, R.id.myShouChang, R.id.one, R.id.two, R.id.three, R.id.four, R.id.five, R.id.six, R.id.seven, R.id.eight, R.id.nine, R.id.ten, R.id.eleven, R.id.twelve, R.id.thirth, R.id.forth, R.id.fivth, R.id.sixth})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.myZuoPin:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if(MyApplication.getmInstance().getUid().equals(""))return;
                Intent intent = new Intent(this, MyHuiBenActivity.class);
                intent.putExtra("type", getIntent().getIntExtra("type", 52));
                jumpToActivity(intent, false);
                break;
            case R.id.myShouChang:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if(MyApplication.getmInstance().getUid().equals(""))return;
                Intent intent1 = new Intent(this, MyShouChangActivity.class);
                intent1.putExtra("type", getIntent().getIntExtra("type", 52));
                jumpToActivity(intent1, false);
                break;
            case R.id.one:
                jum(1);
                break;
            case R.id.two:
                jum(2);
                break;
            case R.id.three:
                jum(3);
                break;
            case R.id.four:
                jum(4);
                break;
            case R.id.five:
                jum(5);
                break;
            case R.id.six:
                jum(6);
                break;
            case R.id.seven:
                jum(7);
                break;
            case R.id.eight:
                jum(8);
                break;
            case R.id.nine:
                jum(9);
                break;
            case R.id.ten:
                jum(10);
                break;
            case R.id.eleven:
                jum(11);
                break;
            case R.id.twelve:
                jum(12);
                break;
            case R.id.thirth:
                jum(13);
                break;
            case R.id.forth:
                jum(14);
                break;
            case R.id.fivth:
                jum(15);
                break;
            case R.id.sixth:
                jum(16);
                break;
        }
    }
}
