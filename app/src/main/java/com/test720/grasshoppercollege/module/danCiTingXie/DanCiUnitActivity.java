package com.test720.grasshoppercollege.module.danCiTingXie;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.FragmentViewPagerAdapter;
import com.test720.grasshoppercollege.HttpBean.FanYiGuanKaData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.PublicLianXiJinShaiActivity;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.jinShai.JinShaiShouYeActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;

public class DanCiUnitActivity extends PublicLianXiJinShaiActivity {
    List<Fragment> fragmentList = new ArrayList();
    String bookId = "";

    @Override
    public void JiLei() {
        Intent intent = new Intent(mcontext, DanCiJiLeiActivity.class);
        intent.putExtra("book_id", bookId);
        jumpToActivity(intent, false);
    }

    @Override
    public void jinShai() {
        Intent intent = new Intent(this, JinShaiShouYeActivity.class);
        intent.putExtra("type", "23");
        jumpToActivity(intent, false);
    }

    @Override
    protected void initData() {
        getContentFrame().setBackgroundResource(R.mipmap.beijqiu);
        getYou().setVisibility(View.VISIBLE);
        getContentFrame().setPadding(0, 300, 0, 100);
        bookId = getIntent().getStringExtra("book_id");
        setId(bookId);
        getData();
    }


    private void getData() {
        FormBody.Builder httpParams = new FormBody.Builder();
        httpParams.add("uid", MyApplication.getmInstance().getUid());
        httpParams.add("book_id", bookId);
        Post(HttpUntil.GetIntent().Wordindex(), httpParams, 1);
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        if (codeIsOne(str, false)) {
            FanYiGuanKaData guankaData = new Gson().fromJson(str, FanYiGuanKaData.class);
            fragmentList.clear();
            for (int i = 0; i < guankaData.getData().size(); i++) {
                DanCiUnitFragment fragment = new DanCiUnitFragment();
                fragment.setBookId(bookId);
                fragment.setIndex(i);
                fragment.setPublicLianXiJinShaiActivity(this);
                fragmentList.add(fragment);
            }
            getContentFrame().setAdapter(new FragmentViewPagerAdapter(getSupportFragmentManager(), fragmentList));
        }
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
}
