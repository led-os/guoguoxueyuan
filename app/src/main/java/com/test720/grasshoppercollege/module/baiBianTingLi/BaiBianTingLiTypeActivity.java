package com.test720.grasshoppercollege.module.baiBianTingLi;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.HttpBean.BaiBianfootData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.HttpUntil;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;

public class BaiBianTingLiTypeActivity extends BaseToolActivity {

    @BindView(R.id.one)
    Button one;
    @BindView(R.id.two)
    Button two;
    @BindView(R.id.three)
    Button three;
    @BindView(R.id.four)
    Button four;
    @BindView(R.id.five)
    Button five;
    BaiBianfootData data;

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_bai_bian_ting_li_type;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("uid", MyApplication.getmInstance().getUid());//请求参数一
        formBuilder.add("listening_id", getIntent().getIntExtra("id", 1) + "");
        Post(HttpUntil.GetIntent().EnListeningfootIndex(), formBuilder, 1);
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        if (codeIsOne(str,false)) {
            data = new Gson().fromJson(str, BaiBianfootData.class);
           /* if (data.getData().getFoot1().equals("0")) {
                one.setBackgroundResource(R.color.bantouming);
            } else {
                one.setBackgroundResource(R.color.touming);
            }
            *//*****//*
            if (data.getData().getFoot2().equals("0")) {
                two.setBackgroundResource(R.color.bantouming);
            } else {
                two.setBackgroundResource(R.color.touming);
            }
            *//*****//*
            if (data.getData().getFoot3().equals("0")) {
                three.setBackgroundResource(R.color.bantouming);
            } else {
                three.setBackgroundResource(R.color.touming);
            }
            *//*****//*
            if (data.getData().getFoot4().equals("0")) {
                four.setBackgroundResource(R.color.bantouming);
            } else {
                four.setBackgroundResource(R.color.touming);
            }
            *//*****//*
            if (data.getData().getFoot5().equals("0")) {
                five.setBackgroundResource(R.color.bantouming);
            } else {
                five.setBackgroundResource(R.color.touming);
            }*/

        }
    }


    @OnClick({R.id.back, R.id.one, R.id.two, R.id.three, R.id.four, R.id.five})
    public void onViewClicked(View view) {
        int type = -1;
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.one:
                if (data != null && data.getData().getFoot1().equals("1")) {
                    type = 1;
                }
                break;
            case R.id.two:
                if (data != null && data.getData().getFoot2().equals("1")) {
                    type = 2;
                } else {
                    ShowToast("须先通过foot1");
                }
                break;
            case R.id.three:
                if (data != null && data.getData().getFoot3().equals("1")) {
                    type = 3;
                } else {
                    ShowToast("须先通过foot2");
                }
                break;
            case R.id.four:
                if (data != null && data.getData().getFoot4().equals("1")) {
                    type = 4;
                } else {
                    ShowToast("须先通过foot3");
                }
                break;
            case R.id.five:
                if (data != null && data.getData().getFoot5().equals("1")) {
                    type = 5;
                } else {
                    ShowToast("须先通过foot4");
                }
                break;
        }

        if (view.getId() != R.id.back) {
            if (data == null || type == -1) return;
            Intent intent = new Intent(this, BaiBianTiMuActivity.class);
            intent.putExtra("type", type);
            intent.putExtra("id", getIntent().getIntExtra("id", 1));
            jumpToActivity(intent, false);
        }
    }
}
