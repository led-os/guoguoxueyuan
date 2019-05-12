package com.test720.grasshoppercollege.module.yinBiao;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BuyClassActivity;
import com.test720.grasshoppercollege.HttpBean.GuoJIGuanKaData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.PublickGuanKaActivity;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.FormBody;

public class YinBiaoShouYeActivity extends PublickGuanKaActivity {
    private List<List<GuoJIGuanKaData.DataBean>> list = new ArrayList<>();
    String[] strings;//分享信息
    boolean isFirst = true;

    @Override
    public void GetHttpBackStr(String str, int what) {
        if (what == 1 && codeIsOne(str, false)) {
            GuoJIGuanKaData data = new Gson().fromJson(str, GuoJIGuanKaData.class);
            list.clear();
            list.addAll(data.getData());
            Collections.reverse(list);
            adapter.notifyDataSetChanged();
            if (isFirst) {
                ShowDialong();
                recyclerView.setVisibility(View.INVISIBLE);
                getLayoutManager().scrollToPositionWithOffset(getindex(), 0);
                getLayoutManager().setStackFromEnd(true);
                mainHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        DismissDialong();
                        recyclerView.setVisibility(View.VISIBLE);
                        isFirst = false;
                    }
                }, 1000);
            }
        }
        //刷新
        if (what == 123 && codeIsOne(str, false)) {
            if (getChilditemCount() != -1 && getItemIndexCount() != -1) {
                list.get(getItemIndexCount()).get(getChilditemCount()).setOpen_up(1);
                adapter.notifyDataSetChanged();
            }
        }
    }

    /*刷新关卡数据*/
    @Override
    public void ShuaXin() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("uid", MyApplication.getmInstance().getUid());
        Post(HttpUntil.GetIntent().passInfo(), builder, 1);
    }

    @Override
    public boolean isHORIZONTAL() {
        return false;
    }


    @Override
    public void init() {
        recyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        initAdapter();
    }

    /*适配器*/
    private void initAdapter() {
        adapter = new MyBaseRecyclerAdapter<List<GuoJIGuanKaData.DataBean>>(list, this, R.layout.guo_ji_yin_biao) {
            @Override
            public void convert(BaseRecyclerHolder holder, List<GuoJIGuanKaData.DataBean> item, final int pos) {
                holder.getView(R.id.one).setVisibility(View.INVISIBLE);
                holder.getView(R.id.two).setVisibility(View.INVISIBLE);
                holder.getView(R.id.three).setVisibility(View.INVISIBLE);
                holder.getView(R.id.four).setVisibility(View.INVISIBLE);
                holder.getView(R.id.five).setVisibility(View.INVISIBLE);
                holder.getView(R.id.six).setVisibility(View.INVISIBLE);

                /*当前页第一关*/
                for (int i = 0; i < item.size(); i++) {
                    final int j = i;
                    final GuoJIGuanKaData.DataBean dataBean = item.get(i);
                    strings = new String[3];
                    strings[0] = dataBean.getTitle();
                    strings[1] = dataBean.getContent();
                    strings[2] = HttpUntil.GetIntent().getShare() + 20;

                    switch (i) {
                        case 0:
                            holder.getView(R.id.one).setVisibility(View.VISIBLE);
                            holder.setText(R.id.one_txt, dataBean.getName());
                            /*是否解锁*/
                            imgGuan(holder, R.id.one_img, R.id.one_suo, dataBean.getOpen_up());
                            //关卡点击事件
                            holder.getView(R.id.one).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    re_id = dataBean.getipa_id();
                                    points = dataBean.getPoints();
                                    setChilditemCount(j);
                                    setItemIndexCount(pos);
                                    if (isCanSee(dataBean.getPermissions(), dataBean.isPay(), dataBean.getOpen_up() + "", dataBean.getPoints(), strings)) {
                                        itemClick(dataBean.getipa_id(), dataBean.getState(), dataBean.getName());
                                    }
                                }
                            });
                            break;
                        case 1:
                            holder.getView(R.id.two).setVisibility(View.VISIBLE);
                            holder.setText(R.id.two_txt, dataBean.getName());
                            /*是否解锁*/
                            imgGuan(holder, R.id.two_img, R.id.two_suo, dataBean.getOpen_up());
                            //关卡点击事件
                            holder.getView(R.id.two).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    re_id = dataBean.getipa_id();
                                    points = dataBean.getPoints();
                                    setChilditemCount(j);
                                    setItemIndexCount(pos);
                                    if (isCanSee(dataBean.getPermissions(), dataBean.isPay(), dataBean.getOpen_up() + "", dataBean.getPoints(), strings)) {
                                        itemClick(dataBean.getipa_id(), dataBean.getState(), dataBean.getName());
                                    }
                                }
                            });
                            break;
                        case 2:
                            holder.getView(R.id.three).setVisibility(View.VISIBLE);
                            holder.setText(R.id.three_txt, dataBean.getName());
                            /*是否解锁*/
                            imgGuan(holder, R.id.three_img, R.id.three_suo, dataBean.getOpen_up());
                            //关卡点击事件
                            holder.getView(R.id.three).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    re_id = dataBean.getipa_id();
                                    points = dataBean.getPoints();
                                    setChilditemCount(j);
                                    setItemIndexCount(pos);
                                    if (isCanSee(dataBean.getPermissions(), dataBean.isPay(), dataBean.getOpen_up() + "", dataBean.getPoints(), strings)) {
                                        itemClick(dataBean.getipa_id(), dataBean.getState(), dataBean.getName());
                                    }
                                }
                            });
                            break;
                        case 3:
                            holder.getView(R.id.four).setVisibility(View.VISIBLE);
                            holder.setText(R.id.four_txt, dataBean.getName());
                            /*是否解锁*/
                            imgGuan(holder, R.id.four_img, R.id.four_suo, dataBean.getOpen_up());
                            //关卡点击事件
                            holder.getView(R.id.four).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    re_id = dataBean.getipa_id();
                                    points = dataBean.getPoints();
                                    setChilditemCount(j);
                                    setItemIndexCount(pos);
                                    if (isCanSee(dataBean.getPermissions(), dataBean.isPay(), dataBean.getOpen_up() + "", dataBean.getPoints(), strings)) {
                                        itemClick(dataBean.getipa_id(), dataBean.getState(), dataBean.getName());
                                    }
                                }
                            });
                            break;
                        case 4:
                            holder.getView(R.id.five).setVisibility(View.VISIBLE);
                            holder.setText(R.id.five_txt, dataBean.getName());
                            /*是否解锁*/
                            imgGuan(holder, R.id.five_img, R.id.five_suo, dataBean.getOpen_up());
                            //关卡点击事件
                            holder.getView(R.id.five).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    re_id = dataBean.getipa_id();
                                    points = dataBean.getPoints();
                                    setChilditemCount(j);
                                    setItemIndexCount(pos);
                                    if (isCanSee(dataBean.getPermissions(), dataBean.isPay(), dataBean.getOpen_up() + "", dataBean.getPoints(), strings)) {
                                        itemClick(dataBean.getipa_id(), dataBean.getState(), dataBean.getName());
                                    }
                                }
                            });
                            break;
                        case 5:
                            holder.getView(R.id.six).setVisibility(View.VISIBLE);
                            holder.setText(R.id.six_txt, dataBean.getName());
                            /*是否解锁*/
                            imgGuan(holder, R.id.six_img, R.id.six_suo, dataBean.getOpen_up());
                            //关卡点击事件
                            holder.getView(R.id.six).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    re_id = dataBean.getipa_id();
                                    points = dataBean.getPoints();
                                    setChilditemCount(j);
                                    setItemIndexCount(pos);
                                    if (isCanSee(dataBean.getPermissions(), dataBean.isPay(), dataBean.getOpen_up() + "", dataBean.getPoints(), strings)) {
                                        itemClick(dataBean.getipa_id(), dataBean.getState(), dataBean.getName());
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

    @Override
    public void buyCurr() {
        Intent intent = new Intent(mcontext, BuyClassActivity.class);
        intent.putExtra("curr_type", 10);
        intent.putExtra("type", "4");
        jumpToActivity(intent, false);
    }

    @Override
    public void buyGuoGuo() {
        if (re_id == null) return;
        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("uid", MyApplication.getmInstance().getUid());//请求参数一
        formBuilder.add("curr_type", "10");
        formBuilder.add("type", "2");
        formBuilder.add("points", points);
        formBuilder.add("re_id", re_id);
        Post(HttpUntil.GetIntent().addShareSpendRecord(), formBuilder, 123, false);
    }

    @Override
    public void shareCurr() {
        if (points == null) return;
        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("uid", MyApplication.getmInstance().getUid());//请求参数一
        formBuilder.add("curr_type", "10");
        formBuilder.add("type", "1");
        formBuilder.add("re_id", re_id);
        Post(HttpUntil.GetIntent().addShareSpendRecord(), formBuilder, 123, false);
    }

    /*点击跳转*/
    private void itemClick(String id, String state, String title) {
        Intent in = new Intent(this, YinBiaoVideoActivity.class);
        in.putExtra("ipa_id", id);
        in.putExtra("title", title);
        if (state.equals("1")) {
            in.putExtra("h5", false);
        } else {
            in.putExtra("h5", true);
        }
        jumpToActivity(in, false);
    }

    /**
     * 根据关卡，更新关卡状态
     *
     * @param holder 父控件
     * @param imgR   图片控件
     * @param suoR   加锁控件
     * @param open   是否开放
     */
    public void imgGuan(BaseRecyclerHolder holder, int imgR, int suoR, int open) {

        holder.getView(suoR).setVisibility(View.GONE);
        if (open == 1) {
            holder.setImageResource(imgR, R.mipmap.anniu1);
            ImageView img = holder.getView(imgR);
            img.setColorFilter(null);
        } else {
            holder.setImageResource(imgR, R.mipmap.anniu2);
          /*  ColorMatrix matrix = new ColorMatrix();
            matrix.setSaturation(0);//饱和度 0灰色 100过度彩色,50正常
            ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
            ImageView img = holder.getView(imgR);
            img.setColorFilter(filter);*/
        }
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
