package com.test720.grasshoppercollege.module.ciDai;

import android.content.Intent;

import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.PublicAddBookActivity;
import com.test720.grasshoppercollege.bean.BookListItemData;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.io.File;

import okhttp3.FormBody;

public class CiDaiAddActivity extends PublicAddBookActivity {


    @Override
    protected String setTitle() {
        return "磁带";
    }

    @Override
    public String url() {
        return HttpUntil.GetIntent().EnCdbookList();
    }

    @Override
    public FormBody.Builder formBuilder() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("uid", MyApplication.getmInstance().getUid());
        builder.add("class", MyApplication.getmInstance().nianji);
        return builder;
    }


    /*添加课本接口*/
    @Override
    public void click(String bookId) {
    }

    @Override
    public void click(BookListItemData bean) {
        String f = HttpUntil.GetIntent().filePathHead(mcontext) + bean.getAlias();
        File file = new File(f);
        if (file.exists()) {
            FormBody.Builder builder = new FormBody.Builder();
            builder.add("uid", MyApplication.getmInstance().getUid());
            builder.add("book_id", bean.getBook_id());
            Post(HttpUntil.GetIntent().EnCdaddReadingPoints(), builder, 10000);
        } else {
            Intent intent = new Intent(mcontext, CiDaiDownActivity.class);
            intent.putExtra("bookId", bean.getBook_id());
            String fileName = bean.getBook_version() + bean.getClassX() + bean.getStatus();
            intent.putExtra("fileName", fileName);
            intent.putExtra("url", bean.getZip_path());
            intent.putExtra("version", bean.getVersion_number());
            jumpToActivity(intent, false);
        }
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        super.GetHttpBackStr(str, what);
        if (what == 10000) {//如果添加失败，则刷新界面
            if (!codeIsOne(str)) {
                ShuaXin();
            }
        }
    }
}
