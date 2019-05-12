package com.test720.grasshoppercollege.module.woAidanCiTingXie;

import android.content.Intent;
import android.view.View;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BuyClassActivity;
import com.test720.grasshoppercollege.HttpBean.WoAiTingXieGuanKaData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.PublickGuanKaActivity;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.FormBody;

public class WoAiTingXieShouYeActivity extends PublickGuanKaActivity {
    private List<List<WoAiTingXieGuanKaData.DataBean>> list = new ArrayList<>();
    WoAiTingXieGuanKaData data;
    String[] strings;//分享信息
    boolean isFirst = true;

    @Override
    public void GetHttpBackStr(String str, int what) {
        switch (what) {
            case 1:
                if (codeIsOne(str, false)) {
                    data = new Gson().fromJson(str, WoAiTingXieGuanKaData.class);
                    list.clear();
                    list = data.getData();
                    Collections.reverse(list);
                    initAdapter();
                    adapter.notifyDataSetChanged();
                    if (isFirst) {
                        ShowDialong();
                        recyclerView.setVisibility(View.INVISIBLE);
                        getLayoutManager().scrollToPositionWithOffset(getindex(), 0);
                        isFirst = false;
                        mainHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                DismissDialong();
                                recyclerView.setVisibility(View.VISIBLE);
                            }
                        }, 1000);
                    }
                }
                break;
            case 123:
                if (codeIsOne(str)) {
                    if (getChilditemCount() != -1 && getItemIndexCount() != -1) {
                        list.get(getItemIndexCount()).get(getChilditemCount()).setOpen_up(1);
                        adapter.notifyDataSetChanged();
                    }
                }
                break;
        }

    }

    @Override
    public boolean isHORIZONTAL() {
        return false;
    }

    @Override
    public void init() {

    }

    /*适配器*/
    private void initAdapter() {
        adapter = new MyBaseRecyclerAdapter<List<WoAiTingXieGuanKaData.DataBean>>(list, this, R.layout.wo_ai_ting_xie_shou) {
            @Override
            public void convert(BaseRecyclerHolder holder, List<WoAiTingXieGuanKaData.DataBean> item, final int pos) {
                holder.getView(R.id.one).setVisibility(View.INVISIBLE);
                holder.getView(R.id.two).setVisibility(View.INVISIBLE);
                holder.getView(R.id.three).setVisibility(View.INVISIBLE);
                holder.getView(R.id.four).setVisibility(View.INVISIBLE);
                holder.getView(R.id.five).setVisibility(View.INVISIBLE);
                holder.getView(R.id.six).setVisibility(View.INVISIBLE);
                /*当前页第一关*/
                for (int i = 0; i < item.size(); i++) {
                    final WoAiTingXieGuanKaData.DataBean dataBean = item.get(i);
                    strings = new String[3];
                    strings[0] = dataBean.getTitle();
                    strings[1] = dataBean.getContent();
                    strings[2] = HttpUntil.GetIntent().getShare() + 19;
                    switch (i) {
                        case 0:
                            holder.getView(R.id.one).setVisibility(View.VISIBLE);
                            holder.setText(R.id.one_txt, dataBean.getName());
                            /*是否解锁*/
                            if (dataBean.getOpen_up() == 1) {
                                holder.setImageResource(R.id.one_img, R.mipmap.anniu1);
                                holder.getView(R.id.one_suo).setVisibility(View.GONE);
                            } else {
                                holder.setImageResource(R.id.one_img, R.mipmap.meikaigk);
                                holder.getView(R.id.one_suo).setVisibility(View.GONE);
                            }
                            //关卡点击事件
                            holder.getView(R.id.one).setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    setItemIndexCount(pos);
                                    setChilditemCount(0);
                                    re_id = dataBean.getLove_id();
                                    points = dataBean.getPoints();
                                    if (isCanSee(dataBean.getPermissions(), dataBean.isPay(), dataBean.getOpen_up() + "", dataBean.getPoints(), strings)) {
                                        itemClick(dataBean.getLove_id(), dataBean.getState(), dataBean.getName());
                                    }
                                }
                            });
                            break;
                        case 1:
                            holder.getView(R.id.two).setVisibility(View.VISIBLE);
                            holder.setText(R.id.two_txt, dataBean.getName());
                            /*是否解锁*/
                            if (dataBean.getOpen_up() == 1) {
                                holder.getView(R.id.two_suo).setVisibility(View.GONE);
                                holder.setImageResource(R.id.two_img, R.mipmap.anniu1);
                            } else {
                                holder.getView(R.id.two_suo).setVisibility(View.GONE);
                                holder.setImageResource(R.id.two_img, R.mipmap.meikaigk);
                            }
                            //关卡点击事件
                            holder.getView(R.id.two).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    setItemIndexCount(pos);
                                    setChilditemCount(1);
                                    re_id = dataBean.getLove_id();
                                    points = dataBean.getPoints();
                                    if (isCanSee(dataBean.getPermissions(), dataBean.isPay(), dataBean.getOpen_up() + "", dataBean.getPoints(), strings)) {
                                        itemClick(dataBean.getLove_id(), dataBean.getState(), dataBean.getName());
                                    }
                                }
                            });
                            break;
                        case 2:
                            holder.getView(R.id.three).setVisibility(View.VISIBLE);
                            holder.setText(R.id.three_txt, dataBean.getName());
                            /*是否解锁*/
                            if (dataBean.getOpen_up() == 1) {
                                holder.getView(R.id.three_suo).setVisibility(View.GONE);
                                holder.setImageResource(R.id.three_img, R.mipmap.anniu1);
                            } else {
                                holder.getView(R.id.three_suo).setVisibility(View.GONE);
                                holder.setImageResource(R.id.three_img, R.mipmap.meikaigk);
                            }
                            //关卡点击事件
                            holder.getView(R.id.three).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    setItemIndexCount(pos);
                                    setChilditemCount(2);
                                    re_id = dataBean.getLove_id();
                                    points = dataBean.getPoints();
                                    if (isCanSee(dataBean.getPermissions(), dataBean.isPay(), dataBean.getOpen_up() + "", dataBean.getPoints(), strings)) {
                                        itemClick(dataBean.getLove_id(), dataBean.getState(), dataBean.getName());
                                    }
                                }
                            });
                            break;
                        case 3:
                            holder.getView(R.id.four).setVisibility(View.VISIBLE);
                            holder.setText(R.id.four_txt, dataBean.getName());
                            /*是否解锁*/
                            if (dataBean.getOpen_up() == 1) {
                                holder.getView(R.id.four_suo).setVisibility(View.GONE);
                                holder.setImageResource(R.id.four_img, R.mipmap.anniu1);
                            } else {
                                holder.getView(R.id.four_suo).setVisibility(View.GONE);
                                holder.setImageResource(R.id.four_img, R.mipmap.meikaigk);
                            }
                            //关卡点击事件
                            holder.getView(R.id.four).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    setItemIndexCount(pos);
                                    setChilditemCount(3);
                                    re_id = dataBean.getLove_id();
                                    points = dataBean.getPoints();
                                    if (isCanSee(dataBean.getPermissions(), dataBean.isPay(), dataBean.getOpen_up() + "", dataBean.getPoints(), strings)) {
                                        itemClick(dataBean.getLove_id(), dataBean.getState(), dataBean.getName());
                                    }
                                }
                            });
                            break;
                        case 4:
                            holder.getView(R.id.five).setVisibility(View.VISIBLE);
                            holder.setText(R.id.five_txt, dataBean.getName());
                            /*是否解锁*/
                            if (dataBean.getOpen_up() == 1) {
                                holder.getView(R.id.five_suo).setVisibility(View.GONE);
                                holder.setImageResource(R.id.five_img, R.mipmap.anniu1);
                            } else {
                                holder.getView(R.id.five_suo).setVisibility(View.GONE);
                                holder.setImageResource(R.id.five_img, R.mipmap.meikaigk);
                            }
                            //关卡点击事件
                            holder.getView(R.id.five).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    setItemIndexCount(pos);
                                    setChilditemCount(4);
                                    re_id = dataBean.getLove_id();
                                    points = dataBean.getPoints();
                                    if (isCanSee(dataBean.getPermissions(), dataBean.isPay(), dataBean.getOpen_up() + "", dataBean.getPoints(), strings)) {
                                        itemClick(dataBean.getLove_id(), dataBean.getState(), dataBean.getName());
                                    }
                                }
                            });
                            break;
                        case 5:
                            holder.getView(R.id.six).setVisibility(View.VISIBLE);
                            holder.setText(R.id.six_txt, dataBean.getName());
                            /*是否解锁*/
                            if (dataBean.getOpen_up() == 1) {
                                holder.getView(R.id.six_suo).setVisibility(View.GONE);
                                holder.setImageResource(R.id.six_img, R.mipmap.anniu1);
                            } else {
                                holder.getView(R.id.six_suo).setVisibility(View.GONE);
                                holder.setImageResource(R.id.six_img, R.mipmap.meikaigk);
                            }
                            //关卡点击事件
                            holder.getView(R.id.six).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    setItemIndexCount(pos);
                                    setChilditemCount(5);
                                    re_id = dataBean.getLove_id();
                                    points = dataBean.getPoints();
                                    if (isCanSee(dataBean.getPermissions(), dataBean.isPay(), dataBean.getOpen_up() + "", dataBean.getPoints(), strings)) {
                                        itemClick(dataBean.getLove_id(), dataBean.getState(), dataBean.getName());
                                    }
                                }
                            });
                            break;
                    }
                }
            }
        };
        recyclerView.setAdapter(adapter);
    }


    /*刷新关卡数据*/
    @Override
    public void ShuaXin() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("class", MyApplication.getmInstance().nianji);
        builder.add("uid", MyApplication.getmInstance().getUid());
        Post(HttpUntil.GetIntent().EnLoveDictationindex(), builder, 1);
    }

    @Override
    public void buyCurr() {
        Intent intent = new Intent(mcontext, BuyClassActivity.class);
        intent.putExtra("curr_type", 9);
        intent.putExtra("type", "4");
        jumpToActivity(intent, false);
    }

    @Override
    public void buyGuoGuo() {
        if (re_id == null) return;
        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("uid", MyApplication.getmInstance().getUid());//请求参数一
        formBuilder.add("curr_type", "9");
        formBuilder.add("type", "2");
        formBuilder.add("points", points);
        formBuilder.add("re_id", re_id);
        Post(HttpUntil.GetIntent().addShareSpendRecord(), formBuilder, 123, false);
    }

    @Override
    public void shareCurr() {
        if (re_id == null) return;
        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("uid", MyApplication.getmInstance().getUid());//请求参数一
        formBuilder.add("curr_type", "9");
        formBuilder.add("type", "1");
        formBuilder.add("re_id", re_id);
        Post(HttpUntil.GetIntent().addShareSpendRecord(), formBuilder, 123, false);
    }

    /*点击跳转*/
    private void itemClick(String id, String state, String title) {
        Intent in = new Intent(this, WoAiTingXieVideoActivity.class);
        in.putExtra("love_id", id);
        in.putExtra("title", title);
        if (state.equals("1")) {
            in.putExtra("h5", false);
        } else {
            in.putExtra("h5", true);
        }
        jumpToActivity(in, false);
    }


    /**
     * 获取脚标
     *
     * @return 脚标
     */
    public int getindex() {
        if (list.size() == 0) return 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            for (int j = list.get(i).size() - 1; j >= 0; j--) {
                if (list.get(i).get(j).getOpen_up() == 0) {
                    return i;
                }
            }
        }
        return list.size() - 1;
    }
}
