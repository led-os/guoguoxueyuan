package com.test720.grasshoppercollege.module.yuFaJiangJie;

import android.view.View;

import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.BasePingJiaActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.List;

public class YuFaHuiFuActivity extends BasePingJiaActivity {

    @Override
    public void initView() {
        super.initView();
        imgLin.setVisibility(View.GONE);
        topLin.setVisibility(View.VISIBLE);
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
        return "回复";
    }

    @Override
    public void pinLun(List<String> pathList) {
        HttpParams builder = new HttpParams();
        builder.put("book_id", getIntent().getStringExtra("book_id"));
        builder.put("uid", MyApplication.getmInstance().getUid());
        builder.put("comment_id", getIntent().getStringExtra("comment_id"));
        builder.put("content", body.getText().toString());
        for (int i = 0; i < pathList.size(); i++) {
            builder.put("file[" + i + "]", pathList.get(i));
        }
        postResponse(HttpUntil.GetIntent().EnGrammarcomment(), builder, 5, true);
    }
}