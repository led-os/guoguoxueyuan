package com.test720.grasshoppercollege.module.gongLue.jiaZhang.myYuYue;

import android.view.View;

import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.BasePingJiaActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.List;

public class PinJiaActivity extends BasePingJiaActivity {

    @Override
    public void initView() {
        super.initView();
        imgLin.setVisibility(View.GONE);
        topLin.setVisibility(View.VISIBLE);
        biaoqing.setVisibility(View.GONE);
    }

    @Override
    protected void initData() {
        super.initData();
        title.setText("回复:");
        titleBody.setText("@" + getIntent().getStringExtra("name"));
        body.setHint("请输入回复");
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        if (codeIsOne(str)) {
            finish();
        }
    }

    @Override
    protected String setTitle() {
        return "评语";
    }

    @Override
    public void pinLun(List<String> filepath) {
        HttpParams builder = new HttpParams();
        builder.put("yuyue_id", getIntent().getStringExtra("yuyue_id"));
        builder.put("content", body.getText().toString());
        postResponse(HttpUntil.GetIntent().StrategyuserComment(), builder, 5, true);
    }
}
