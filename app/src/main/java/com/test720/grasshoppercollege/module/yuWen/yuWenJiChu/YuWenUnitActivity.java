package com.test720.grasshoppercollege.module.yuWen.yuWenJiChu;

import android.content.Intent;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.UntilExpandableListViewAdapter;
import com.test720.grasshoppercollege.BuyClassActivity;
import com.test720.grasshoppercollege.HttpBean.KeBenUnitData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.PublicUnitlAndPageActivity;
import com.test720.grasshoppercollege.SubjectActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;

public class YuWenUnitActivity extends PublicUnitlAndPageActivity {
    List<KeBenUnitData.DataBean> list = new ArrayList<>();
    public UntilExpandableListViewAdapter adapter;
    String unitStr;

    @Override
    protected String setTitle() {
        return "基础练习";
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
                  /*  Intent intent = new Intent(mcontext, JiChuTiMuActivity.class);
                    intent.putExtra("page", pageBean.getPage());
                    intent.putExtra("unit", unit);
                    intent.putExtra("type", 20);
                    intent.putExtra("book_id", getIntent().getStringExtra("book_id"));
                    startActivity(intent);*/
                    Intent intent = new Intent(mcontext, SubjectActivity.class);
                    intent.putExtra("type","语文基础练习");
                    intent.putExtra("title", unit + pageBean.getPage());
                    intent.putExtra("path", pageBean.getWeb_url());
                    jumpToActivity(intent, false);
                }
            }
        };
        exListView.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    private void getData() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("book_id", getIntent().getStringExtra("book_id"));
        builder.add("uid", MyApplication.getmInstance().getUid());
        Post(HttpUntil.GetIntent().MathBasicPracticeunitList(), builder, 1);
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
            case 123:
                if (codeIsOne(str)) {
                    getData();
                }
                break;
        }

    }

    @Override
    public void buyCurr() {
        Intent intent = new Intent(mcontext, BuyClassActivity.class);
        intent.putExtra("curr_type", 20);
        intent.putExtra("type", "4");
        jumpToActivity(intent, false);
    }

    @Override
    public void buyGuoGuo() {
        if (re_id == null) return;
        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("uid", MyApplication.getmInstance().getUid());//请求参数一
        formBuilder.add("curr_type", "20");
        formBuilder.add("type", "2");
        formBuilder.add("points", points);
        formBuilder.add("re_id", re_id);
        formBuilder.add("unit", unitStr);
        formBuilder.add("book_id", getIntent().getStringExtra("book_id"));
        Post(HttpUntil.GetIntent().addShareSpendRecord(), formBuilder, 123, true);
    }

    @Override
    public void shareCurr() {
        if (points == null) return;
        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("uid", MyApplication.getmInstance().getUid());//请求参数一
        formBuilder.add("curr_type", "20");
        formBuilder.add("type", "1");
        formBuilder.add("re_id", re_id);
        formBuilder.add("unit", unitStr);
        formBuilder.add("book_id", getIntent().getStringExtra("book_id"));
        Post(HttpUntil.GetIntent().addShareSpendRecord(), formBuilder, 123, false);
    }
}