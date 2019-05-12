package com.test720.grasshoppercollege.module.yuWen.tingXieZhuShou;

import android.content.Intent;
import android.view.View;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.MyBookAdapter;
import com.test720.grasshoppercollege.HttpBean.DianDuMyBookData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.PublickMyBooksActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;

public class TingLiShouActivity extends PublickMyBooksActivity {
    public List<DianDuMyBookData.DataBean.ListBean> list = new ArrayList();
    private MyBookAdapter adapter;

    @Override
    public String url() {
        return HttpUntil.GetIntent().ChineseListeningmyBookList();
    }

    @Override
    public void shuaXin(String str) {
        DianDuMyBookData data = new Gson().fromJson(str, DianDuMyBookData.class);
        if (data.getCode() == 1) {
            list.clear();
            list.addAll(data.getData().getList());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void gengDuo(String str) {
        DianDuMyBookData data = new Gson().fromJson(str, DianDuMyBookData.class);
        if (data.getCode() == 1) {
            list.addAll(data.getData().getList());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public FormBody.Builder formBuilder() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("uid", MyApplication.getmInstance().getUid());
        builder.add("p", page + "");
        builder.add("class", MyApplication.getmInstance().nianji);
        return builder;
    }


    @Override
    public void wanCheng() {
        if (adapter != null) {
            adapter.setSet(false);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public String Booktitle() {
        return "听写助手";
    }

    @Override
    public void setBook() {
        if (adapter != null) {
            adapter.setSet(true);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void add() {
        jumpToActivity(TingLiAddBookActivity.class, false);
    }

    @Override
    protected void initData() {

        adapter = new MyBookAdapter<DianDuMyBookData.DataBean.ListBean>(list, this) {
            @Override
            public void onBindViewHolder(MyBookAdapter.ViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                final int pos = position * 2;
                Glide.with(TingLiShouActivity.this).load(list.get(pos).getPic()).into(holder.one);
                holder.oneUp.setText(list.get(pos).getBook_version());
                holder.oneDown.setText(list.get(pos).getClassX() + " " + list.get(pos).getStatus());
                holder.one_sc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        delBook(list.get(pos).getBook_id());
                        list.remove(pos);
                        notifyDataSetChanged();
                    }
                });

                if (list.size() > (pos + 1)) {
                    Glide.with(TingLiShouActivity.this).load(list.get(pos + 1).getPic()).into(holder.two);
                    holder.twoUp.setText(list.get(pos + 1).getBook_version());
                    holder.twoDown.setText(list.get(pos + 1).getClassX() + " " + list.get(pos + 1).getStatus());
                    holder.two_sc.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            delBook(list.get(pos + 1).getBook_id());
                            list.remove(pos + 1);
                            notifyDataSetChanged();
                        }
                    });
                }
            }

            @Override
            public void bookClick(int position) {
                Intent in = new Intent(TingLiShouActivity.this, TingLiListActivity.class);
                in.putExtra("book_id", list.get(position).getBook_id());
                in.putExtra("title", list.get(position).getBook_version()
                        + list.get(position).getClassX()
                        + list.get(position).getStatus());
                jumpToActivity(in, false);
            }
        };
        recyclerView.setAdapter(adapter);
    }


    @Override
    protected String setTitle() {
        return null;
    }

    /*删除课本*/
    public void delBook(String id) {
        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("book_id", id + "");//请求参数一
        formBuilder.add("uid", MyApplication.getmInstance().getUid());//请求参数二
        Post(HttpUntil.GetIntent().ChineseListeningdelBook(), formBuilder, 5);
    }
}
