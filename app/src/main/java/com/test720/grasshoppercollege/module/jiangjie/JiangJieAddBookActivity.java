package com.test720.grasshoppercollege.module.jiangjie;

import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.PublicAddBookActivity;
import com.test720.grasshoppercollege.bean.BookListItemData;
import com.test720.grasshoppercollege.untils.HttpUntil;

import okhttp3.FormBody;

public class JiangJieAddBookActivity extends PublicAddBookActivity {


    @Override
    protected String setTitle() {
        return "课文讲解";
    }

    @Override
    public String url() {
        return HttpUntil.GetIntent().EnExplainbookList();
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
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("uid", MyApplication.getmInstance().getUid());
        builder.add("book_id", bookId);
        Post(HttpUntil.GetIntent().EnExplainaddBook(), builder, 10000);
    }

    @Override
    public void click(BookListItemData bean) {

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
