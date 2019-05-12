package com.test720.grasshoppercollege.module.diandu;

import android.content.Intent;

import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.PublicAddBookActivity;
import com.test720.grasshoppercollege.bean.BookListItemData;
import com.test720.grasshoppercollege.module.diandu.downDianDu.DownActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.io.File;

import okhttp3.FormBody;

public class DianDuAddBookActivity extends PublicAddBookActivity {

    @Override
    protected String setTitle() {
        return "课本点读";
    }

    @Override
    public String url() {
        return HttpUntil.GetIntent().ReadingbookList();
    }

    @Override
    public FormBody.Builder formBuilder() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("uid", MyApplication.getmInstance().getUid());
        builder.add("class", MyApplication.getmInstance().nianji);
        return builder;
    }

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
            Post(HttpUntil.GetIntent().ReadingaddBook(), builder, 10000);
        } else {
            Intent intent = new Intent(mcontext, DownActivity.class);
            intent.putExtra("book_id", bean.getBook_id());
            jumpToActivity(intent, false);
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode
                == 1) {
            ShuaXin();
        }
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        super.GetHttpBackStr(str, what);
        if (what == 10000) {
            codeIsOne(str);
        }
    }
}
