package com.test720.grasshoppercollege.module.guoguoDou;

import android.content.Intent;
import android.graphics.Typeface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.WebViewActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;

public class GuoGuoDouShouActivity extends BaseToolActivity {


    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.guiZe)
    TextView guiZe;
    @BindView(R.id.doudou)
    TextView doudou;
    @BindView(R.id.shangCheng)
    TextView shangCheng;

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_guo_guo_dou;
    }

    @Override
    protected void initData() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/hkhbt.ttf");
        title.setTypeface(typeface);
        guiZe.setTypeface(typeface);
        shangCheng.setTypeface(typeface);
        doudou.setTypeface(typeface);

        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        postResponse(HttpUntil.GetIntent().myPoints(), httpParams, 1, true);

    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        if (codeIsOne(str, false)) {
            try {
                JSONObject jsonObject = new JSONObject(str);
                String dou = jsonObject.getJSONObject("data").getString("points");
                doudou.setText(dou);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @OnClick({R.id.back, R.id.guiZe, R.id.doudou, R.id.shangCheng, R.id.jinbi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.guiZe:
                Intent intent = new Intent(mcontext, WebViewActivity.class);
                intent.putExtra("title","豆豆规则");
                intent.putExtra("path", HttpUntil.GetIntent().getIP() + "small.php/User/aboutUs/type/3");
                jumpToActivity(intent, false);
                break;
            case R.id.doudou:
                Intent intent1 = new Intent(mcontext, GuoGuoDouJiLvActivity.class);
                jumpToActivity(intent1, false);
                break;
            case R.id.shangCheng:
                jumpToActivity(JiFenShangChengActivity.class, false);
                break;
            case R.id.jinbi:
                jumpToActivity(JinBiDuihuanActivity.class, false);
                break;
        }
    }
}
