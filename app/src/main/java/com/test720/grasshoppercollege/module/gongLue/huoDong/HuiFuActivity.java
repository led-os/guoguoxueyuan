package com.test720.grasshoppercollege.module.gongLue.huoDong;

import android.view.View;

import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.BasePingJiaActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.List;

public class HuiFuActivity extends BasePingJiaActivity {

    @Override
    public void initView() {
        super.initView();
        imgLin.setVisibility(View.GONE);
        topLin.setVisibility(View.GONE);
    }

    @Override
    protected void initData() {
        super.initData();
      /*  title.setText("回复:");
        String name = getIntent().getStringExtra("name");
        if (name != null) {
            name = "@" + name;
            titleBody.setText(name);
        }*/
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
        return "回复";
    }

    @Override
    public void pinLun(List<String> filepath) {
        HttpParams builder = new HttpParams();
        builder.put("activity_id", getIntent().getStringExtra("activity_id"));
        builder.put("uid", MyApplication.getmInstance().getUid());
        builder.put("comment_id", getIntent().getStringExtra("comment_id"));
        builder.put("content", body.getText().toString());
        for (int i = 0; i < filepath.size(); i++) {
            builder.put("file[" + i + "]", filepath.get(i));
        }
        postResponse(HttpUntil.GetIntent().StrategyactivityComment(), builder, 5, true);
    }

}
