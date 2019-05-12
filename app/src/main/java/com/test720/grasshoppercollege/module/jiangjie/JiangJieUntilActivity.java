package com.test720.grasshoppercollege.module.jiangjie;

import android.content.Intent;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.UntilExpandableListViewAdapter;
import com.test720.grasshoppercollege.BuyClassActivity;
import com.test720.grasshoppercollege.HttpBean.KeBenUnitData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.PublicUnitlAndPageActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;

public class JiangJieUntilActivity extends PublicUnitlAndPageActivity {
    List<KeBenUnitData.DataBean> list = new ArrayList<>();
    public UntilExpandableListViewAdapter adapter;
    String unitStr;

    @Override
    protected String setTitle() {
        return "课文讲解";
    }

    @Override
    public void initView() {
        super.initView();
    }

    @Override
    protected void initData() {

        adapter = new UntilExpandableListViewAdapter(list, this) {
            @Override
            public void childrenClick(KeBenUnitData.DataBean.PageBean pageBean, String unit) {
                String[] strs = new String[3];
                strs[0] = pageBean.getTitle();
                strs[1] = pageBean.getContent();
                strs[2] = HttpUntil.GetIntent().getShare() + "21";
                re_id = pageBean.getPage();
                points = pageBean.getPoints();
                unitStr = unit;
                if (isCanSee(pageBean.getPermissions(), pageBean.isPay(), pageBean.getOpen_up() + "", pageBean.getPoints(), strs)) {
                    Intent intent = new Intent(JiangJieUntilActivity.this, JiangjieVideoActivity.class);
                    intent.putExtra("page", pageBean.getPage());
                    intent.putExtra("state", pageBean.getState());
                    intent.putExtra("unit", unit);
                    intent.putExtra("book_id", getIntent().getStringExtra("book_id"));
                    if (pageBean.getState().equals("2")) {
                        intent.putExtra("h5", true);
                    } else {
                        intent.putExtra("h5", false);
                    }
                    startActivity(intent);
                }
            }
        };
        exListView.setAdapter(adapter);
        getData(true);
    }

    @Override
    public void getData(boolean dialog) {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("book_id", getIntent().getStringExtra("book_id"));
        builder.add("uid", MyApplication.getmInstance().getUid());
        Post(HttpUntil.GetIntent().EnExplainunitList(), builder, 1, dialog);
    }


    @Override
    public void ThreadHttpBack(String str, int what) {
        super.ThreadHttpBack(str, what);
        if (what == 123 && codeIsOne(str)) {
            getData(false);
        }
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        switch (what) {
            case 1:
                if (codeIsOne(str, false)) {
                    KeBenUnitData data = new Gson().fromJson(str, KeBenUnitData.class);
                    list.clear();
                    list.addAll(data.getData());
                    adapter.notifyDataSetChanged();
                }
                break;
        }

    }

    @Override
    public void buyCurr() {
        Intent intent = new Intent(mcontext, BuyClassActivity.class);
        intent.putExtra("curr_type", 11);
        intent.putExtra("type", "4");
        jumpToActivity(intent, false);
    }

    @Override
    public void buyGuoGuo() {
        if (re_id == null) return;
        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("uid", MyApplication.getmInstance().getUid());//请求参数一
        formBuilder.add("curr_type", "11");
        formBuilder.add("type", "2");
        formBuilder.add("points", points);
        formBuilder.add("re_id", re_id);
        formBuilder.add("unit", unitStr);
        formBuilder.add("book_id", getIntent().getStringExtra("book_id"));
        Post(HttpUntil.GetIntent().addShareSpendRecord(), formBuilder, 123, false);
    }

    @Override
    public void shareCurr() {
        if (points == null) return;
        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("uid", MyApplication.getmInstance().getUid());//请求参数一
        formBuilder.add("curr_type", "11");
        formBuilder.add("type", "1");
        formBuilder.add("re_id", re_id);
        formBuilder.add("unit", unitStr);
        formBuilder.add("book_id", getIntent().getStringExtra("book_id"));
        Post(HttpUntil.GetIntent().addShareSpendRecord(), formBuilder, 123, false);
    }
}
