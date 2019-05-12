package com.test720.grasshoppercollege.module.yuWen.yuYanPinYinShiZi;

import android.view.View;

import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.BasePingJiaActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.List;

/**
 * Created by 水东流 on 2018/6/14.
 */

public class BaseYuYanPinYinShiZIPingLunHuiFuActivity extends BasePingJiaActivity {

    @Override
    public void initView() {
        super.initView();
        imgLin.setVisibility(View.GONE);
        topLin.setVisibility(View.GONE);
    }

    @Override
    protected void initData() {
        super.initData();
        topLin.setVisibility(View.GONE);
       /* title.setText("回复:");
        titleBody.setText("@" + getIntent().getStringExtra("name"));*/
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
        builder.put("video_id", getIntent().getStringExtra("video_id"));
        builder.put("uid", MyApplication.getmInstance().getUid());
        builder.put("comment_id", getIntent().getStringExtra("comment_id"));
        builder.put("content", body.getText().toString());
        for (int i = 0; i < pathList.size(); i++) {
            builder.put("file[" + i + "]", pathList.get(i));
        }
        postResponse(HttpUntil.GetIntent().ChinesePinYincomment(), builder, 5, true);
    }
}
