package com.test720.grasshoppercollege.module.fanYiChuangGuan;

import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.PublicAddBookActivity;
import com.test720.grasshoppercollege.bean.BookListItemData;
import com.test720.grasshoppercollege.untils.HttpUntil;

import okhttp3.FormBody;

public class FanYiAddBookActivity extends PublicAddBookActivity {


    @Override
    protected String setTitle() {
        return "翻译闯关";
    }

    @Override
    public String url() {
        return HttpUntil.GetIntent().WordbookList();
    }

    @Override
    public FormBody.Builder formBuilder() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("uid", MyApplication.getmInstance().getUid());
        builder.add("type", "12");
        builder.add("class", MyApplication.getmInstance().nianji);
        return builder;
    }

    /*添加课本接口*/
    @Override
    public void click(String bookId) {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("uid", MyApplication.getmInstance().getUid());
        builder.add("book_id", bookId);
        Post(HttpUntil.GetIntent().WordaddBook(), builder, 10000);
    }

    @Override
    public void click(BookListItemData bean) {

    }


}
