package com.test720.grasshoppercollege.module.yuWen.yuYanPinYinShiZi;

import android.content.Intent;

import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.PinLunXiangQingActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;

import okhttp3.FormBody;

/**
 * Created by 水东流 on 2018/6/14.
 */

public class YYPYSZPinLunXiangQingActivity  extends PinLunXiangQingActivity {

    @Override
    public void dianZan() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("video_id", getIntent().getStringExtra("video_id"));
        builder.add("uid", MyApplication.getmInstance().getUid());
        builder.add("comment_id", getIntent().getStringExtra("comment_id"));
        Post(HttpUntil.GetIntent().ChinesePinYinthumbUp(), builder, 5, false);
    }

    @Override
    public void pingLun() {
        Intent in = new Intent(mcontext, BaseYuYanPinYinShiZIPingLunHuiFuActivity.class);
        in.putExtra("comment_id", getIntent().getStringExtra("comment_id"));
        in.putExtra("name", getIntent().getStringExtra("name"));
        in.putExtra("video_id", getIntent().getStringExtra("video_id"));
        jumpToActivity(in, false);
    }

    @Override
    public String url() {
        return HttpUntil.GetIntent().ChinesePinYincommentInfo();
    }
}
