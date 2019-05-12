package com.test720.grasshoppercollege.module.shuXue.jiChu;

import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.PublicAddBookActivity;
import com.test720.grasshoppercollege.bean.BookListItemData;
import com.test720.grasshoppercollege.untils.HttpUntil;

import okhttp3.FormBody;

public class JIChuAddActivity extends PublicAddBookActivity {


    @Override
    protected String setTitle() {
        return "基础练习";
    }

    @Override
    public String url() {
        return HttpUntil.GetIntent().MathBasicPracticebookList();
    }

    @Override
    public FormBody.Builder formBuilder() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("uid", MyApplication.getmInstance().getUid());
        builder.add("class", MyApplication.getmInstance().nianji);
        builder.add("type", "19");
        return builder;
    }

    @Override
    public void click(String bookId) {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("uid", MyApplication.getmInstance().getUid());
        builder.add("book_id", bookId);
        Post(HttpUntil.GetIntent().MathBasicPracticeaddBook(), builder, 10000);
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
