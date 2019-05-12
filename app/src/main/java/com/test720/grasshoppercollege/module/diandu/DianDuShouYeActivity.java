package com.test720.grasshoppercollege.module.diandu;

import android.content.Intent;
import android.view.View;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.MyBookAdapter;
import com.test720.grasshoppercollege.HttpBean.DianDuMyBookData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.PublickMyBooksActivity;
import com.test720.grasshoppercollege.module.diandu.bean.BookDataBean;
import com.test720.grasshoppercollege.untils.downUntil.BookDataInsterUntil;
import com.test720.grasshoppercollege.module.diandu.downDianDu.DownActivity;
import com.test720.grasshoppercollege.untils.downUntil.ZipUntil;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;

public class DianDuShouYeActivity extends PublickMyBooksActivity {
    public List<DianDuMyBookData.DataBean.ListBean> list = new ArrayList();
    private MyBookAdapter adapter;

    @Override
    public String url() {
        return HttpUntil.GetIntent().myBookList();
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
        return "课本点读";
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
        jumpToActivity(DianDuAddBookActivity.class, false);
    }

    @Override
    protected void initData() {

        adapter = new MyBookAdapter<DianDuMyBookData.DataBean.ListBean>(list, this) {
            @Override
            public void onBindViewHolder(ViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                final int pos = position * 2;
                Glide.with(DianDuShouYeActivity.this).load(list.get(pos).getPic()).into(holder.one);
                holder.oneUp.setText(list.get(pos).getBook_version());
                holder.oneDown.setText(list.get(pos).getClassX() + " " + list.get(pos).getStatus());
                holder.one_sc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        delBook(list.get(pos).getBook_id(), list.get(pos).getAlias());
                        list.remove(pos);
                        notifyDataSetChanged();
                    }
                });

                if (list.size() > (pos + 1)) {
                    Glide.with(DianDuShouYeActivity.this).load(list.get(pos + 1).getPic()).into(holder.two);
                    holder.twoUp.setText(list.get(pos + 1).getBook_version());
                    holder.twoDown.setText(list.get(pos + 1).getClassX() + " " + list.get(pos + 1).getStatus());
                    holder.two_sc.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            delBook(list.get(pos + 1).getBook_id(), list.get(pos + 1).getAlias());
                            list.remove(pos + 1);
                            notifyDataSetChanged();
                        }
                    });
                }
            }

            @Override
            public void bookClick(final int position) {
                final String f = HttpUntil.GetIntent().filePathHead(mcontext) + list.get(position).getAlias();
                File file = new File(f);
                //获取本地序列表数据
                BookDataInsterUntil bookDataInsterUntil = new BookDataInsterUntil();
                BookDataBean bookDataBean = bookDataInsterUntil.getBook(mcontext, list.get(position).getBook_id());
                String version_num = "";
                if (bookDataBean != null)
                    version_num = bookDataBean.getData().getBook().getVersion_number();//本地保存资源版本
                if (file.exists() && list.get(position).getVersion_number().equals(version_num)) {//本地存在，并且版本对应
                    Intent in = new Intent(DianDuShouYeActivity.this, BookDianDuActivity.class);
                    in.putExtra("book_id", list.get(position).getBook_id());
                    in.putExtra("book_version", list.get(position).getBook_version());
                    in.putExtra("nianji", list.get(position).getClassX());
                    jumpToActivity(in, false);
                } else {
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            File file = new File(f);
                            if (file.exists()) {//本地存在资源，版本不对应，则删除
                                ShowToast("资源需更新");
                                ZipUntil zipUntil = new ZipUntil();
                                zipUntil.deleZipFile(file.getPath(), mcontext);
                            } else {
                                ShowToast("需加载资源包");
                            }
                            Intent intent = new Intent(mcontext, DownActivity.class);
                            intent.putExtra("book_id", list.get(position).getBook_id());
                            jumpToActivity(intent, false);
                        }
                    };
                    mainHandler.postDelayed(runnable, 0);

                }
            }
        };
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected String setTitle() {
        return null;
    }

    /*删除课本*/
    public void delBook(final String id, final String path) {
        ShowDialong();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                ShowDialong();
                ZipUntil zipUntil = new ZipUntil();
                zipUntil.deleZipFile(path, mcontext, new ZipUntil.DeleListener() {
                    @Override
                    public void deleOk() {
                        DismissDialong();
                    }
                });
                FormBody.Builder formBuilder = new FormBody.Builder();
                formBuilder.add("book_id", id + "");//请求参数一
                formBuilder.add("uid", MyApplication.getmInstance().getUid());//请求参数二
                Post(HttpUntil.GetIntent().IndexdelBook(), formBuilder, 5, false);
            }
        };
        mainHandler.postDelayed(runnable, 0);
    }


}
