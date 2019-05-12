package com.test720.grasshoppercollege.module.jiangjie;

import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.BasePingJiaActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.List;

public class JiangJiePingJiaActivity extends BasePingJiaActivity {

    @Override
    protected String setTitle() {
        return "新评论";
    }

    @Override
    protected void initData() {

    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        if (codeIsOne(str)) {
            finish();
        }
    }

    @Override
    public void pinLun(List<String> pathList) {
        HttpParams builder = new HttpParams();
        builder.put("book_id", getIntent().getStringExtra("book_id"));
        builder.put("uid", MyApplication.getmInstance().getUid());
        builder.put("comment_id", "0");
        builder.put("content", body.getText().toString());
        builder.put("unit", getIntent().getStringExtra("unit"));
        builder.put("page", getIntent().getStringExtra("page"));
        for (int i = 0; i < pathList.size(); i++) {
            builder.put("file[" + i + "]", pathList.get(i));
        }
        postResponse(HttpUntil.GetIntent().EnExplaincomment(), builder, 5, true);
    }

}
