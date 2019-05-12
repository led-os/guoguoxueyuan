package com.test720.grasshoppercollege.module.peiYin.pinLun;

import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.BasePingJiaActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.List;

public class PeiYinPinJiaActivity extends BasePingJiaActivity {

    @Override
    protected String setTitle() {
        return "配音评论";
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
        builder.put("pei_id", getIntent().getStringExtra("pei_id"));
        builder.put("uid", MyApplication.getmInstance().getUid());
        builder.put("comment_id","0");
        builder.put("content", body.getText().toString());
        for (int i = 0; i < pathList.size(); i++) {
            builder.put("file[" + i + "]",pathList.get(i));
        }
        postResponse(HttpUntil.GetIntent().Dubbingcomment(), builder, 5, true);
    }
}
