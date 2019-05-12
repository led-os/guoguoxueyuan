package com.test720.grasshoppercollege.module.xieHouYu;

import android.content.Intent;
import android.view.View;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BuyClassActivity;
import com.test720.grasshoppercollege.HttpBean.XieHouYuLianXiShouData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.PubLicChuangGuanFragment;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.xieHouYu.lianxi.LianXiTiMuActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by 水东流 on 2018/5/16.
 */

public class LianXiFragment extends PubLicChuangGuanFragment {

    private List<List<XieHouYuLianXiShouData.DataBean>> list = new ArrayList<>();
    MyBaseRecyclerAdapter adapter;
    String[] strings;//分享信息
    int index = -1;
    android.os.Handler handler = new android.os.Handler();

    @Override
    public boolean isHORIZONTAL() {
        return false;
    }

    @Override
    public void init() {
        initAdapter();
        getData();
    }

    @Override
    public void shuaXin() {
        super.shuaXin();
        getData();
    }

    public void getData() {
        HttpParams ht = new HttpParams();
        ht.put("uid", MyApplication.getmInstance().getUid());
        ht.put("type", "26");
        post(HttpUntil.GetIntent().ChineseXieHouYuindex(), ht, 1);
    }

    @Override
    public void setListener() {

    }


    @Override
    public void buyCurr() {
        Intent intent = new Intent(getActivity(), BuyClassActivity.class);
        intent.putExtra("curr_type", 26);
        intent.putExtra("type", "4");
        jumpToActivity(intent, false);
    }

    @Override
    public void buyGuoGuo() {
        if (re_id == null) return;
        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        httpParams.put("curr_type", 26);
        httpParams.put("type", 2);
        httpParams.put("points", points);
        httpParams.put("re_id", re_id);
        post(HttpUntil.GetIntent().addShareSpendRecord(), httpParams, 123, false);
    }

    @Override
    public void shareCurr() {
        if (re_id == null) return;
        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        httpParams.put("curr_type", 26);
        httpParams.put("type", 1);
        httpParams.put("re_id", re_id);
        post(HttpUntil.GetIntent().addShareSpendRecord(), httpParams, 123, false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

    @Override
    public void getSuccess(String s, int what) {
        if (what == 1 && codeIsOne(s, false)) {
            XieHouYuLianXiShouData data = new Gson().fromJson(s, XieHouYuLianXiShouData.class);
            list.clear();
            list.addAll(data.getData());
            Collections.reverse(list);
            adapter.notifyDataSetChanged();
            if (index == -1) {
                index = getindex();
                if (list.size() > 0) {
                    ShowDialong();
                    recyclerView.setVisibility(View.INVISIBLE);
                    getLayoutManager().scrollToPositionWithOffset(index, 0);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            DismissDialong();
                            recyclerView.setVisibility(View.VISIBLE);
                        }
                    }, 1000);
                }
            }

        }

    }

    private void initAdapter() {
        adapter = new MyBaseRecyclerAdapter<List<XieHouYuLianXiShouData.DataBean>>(list, getActivity(), R.layout.xie_hou_yu_lian_xi) {

            @Override
            public void convert(BaseRecyclerHolder holder, List<XieHouYuLianXiShouData.DataBean> item, final int postion) {
                holder.getView(R.id.one).setVisibility(View.INVISIBLE);
                holder.getView(R.id.two).setVisibility(View.INVISIBLE);
                holder.getView(R.id.three).setVisibility(View.INVISIBLE);
                holder.getView(R.id.four).setVisibility(View.INVISIBLE);
                holder.getView(R.id.five).setVisibility(View.INVISIBLE);
                holder.getView(R.id.six).setVisibility(View.INVISIBLE);
                /*当前页第一关*/
                for (int i = 0; i < item.size(); i++) {
                    final XieHouYuLianXiShouData.DataBean dataBean = item.get(i);
                    strings = new String[3];
                    strings[0] = dataBean.getTitle();
                    strings[1] = dataBean.getContent();
                    strings[2] = HttpUntil.GetIntent().getShare() + 20;
                    switch (i) {
                        case 0:
                            holder.getView(R.id.one).setVisibility(View.VISIBLE);
                            holder.setText(R.id.oneText, dataBean.getName());
                            /*是否解锁*/
                            if (dataBean.getOpen_up() == 1) {
                                holder.setImageResource(R.id.oneImg, R.mipmap.katchuan);
                            } else {
                                holder.setImageResource(R.id.oneImg, R.mipmap.wuchuan);
                            }
                            //关卡点击事件
                            holder.getView(R.id.one).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    re_id = dataBean.getXhy_id();
                                    index = postion;
                                    points = dataBean.getPoints();
                                    if (isCanSee(dataBean.getPermissions(), dataBean.isPay(), dataBean.getOpen_up() + "", dataBean.getPoints(), strings)) {
                                        itemClick(dataBean);
                                    }
                                }
                            });
                            break;
                        case 1:
                            holder.getView(R.id.two).setVisibility(View.VISIBLE);
                            holder.setText(R.id.twoText, dataBean.getName());
                            /*是否解锁*/
                            if (dataBean.getOpen_up() == 1) {
                                holder.setImageResource(R.id.twoImg, R.mipmap.katchuan);
                            } else {
                                holder.setImageResource(R.id.twoImg, R.mipmap.wuchuan);
                            }
                            //关卡点击事件
                            holder.getView(R.id.two).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    re_id = dataBean.getXhy_id();
                                    index = postion;
                                    points = dataBean.getPoints();
                                    if (isCanSee(dataBean.getPermissions(), dataBean.isPay(), dataBean.getOpen_up() + "", dataBean.getPoints(), strings)) {
                                        itemClick(dataBean);
                                    }
                                }
                            });
                            break;
                        case 2:
                            holder.getView(R.id.three).setVisibility(View.VISIBLE);
                            holder.setText(R.id.threeText, dataBean.getName());
                            /*是否解锁*/
                            if (dataBean.getOpen_up() == 1) {
                                holder.setImageResource(R.id.threeImg, R.mipmap.katchuan);
                            } else {
                                holder.setImageResource(R.id.threeImg, R.mipmap.wuchuan);
                            }
                            //关卡点击事件
                            holder.getView(R.id.three).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    re_id = dataBean.getXhy_id();
                                    index = postion;
                                    points = dataBean.getPoints();
                                    if (isCanSee(dataBean.getPermissions(), dataBean.isPay(), dataBean.getOpen_up() + "", dataBean.getPoints(), strings)) {
                                        itemClick(dataBean);
                                    }
                                }
                            });
                            break;
                        case 3:
                            holder.getView(R.id.four).setVisibility(View.VISIBLE);
                            holder.setText(R.id.fourText, dataBean.getName());
                            /*是否解锁*/
                            if (dataBean.getOpen_up() == 1) {
                                holder.setImageResource(R.id.fourImg, R.mipmap.katchuan);
                            } else {
                                holder.setImageResource(R.id.fourImg, R.mipmap.wuchuan);
                            }
                            //关卡点击事件
                            holder.getView(R.id.four).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    re_id = dataBean.getXhy_id();
                                    index = postion;
                                    points = dataBean.getPoints();
                                    if (isCanSee(dataBean.getPermissions(), dataBean.isPay(), dataBean.getOpen_up() + "", dataBean.getPoints(), strings)) {
                                        itemClick(dataBean);
                                    }
                                }
                            });
                            break;
                        case 4:
                            holder.getView(R.id.five).setVisibility(View.VISIBLE);
                            holder.setText(R.id.fiveText, dataBean.getName());
                            /*是否解锁*/
                            if (dataBean.getOpen_up() == 1) {
                                holder.setImageResource(R.id.fiveImg, R.mipmap.katchuan);
                            } else {
                                holder.setImageResource(R.id.fiveImg, R.mipmap.wuchuan);
                            }
                            //关卡点击事件
                            holder.getView(R.id.five).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    re_id = dataBean.getXhy_id();
                                    points = dataBean.getPoints();
                                    index = postion;
                                    if (isCanSee(dataBean.getPermissions(), dataBean.isPay(), dataBean.getOpen_up() + "", dataBean.getPoints(), strings)) {
                                        itemClick(dataBean);
                                    }
                                }
                            });
                            break;
                        case 5:
                            holder.getView(R.id.six).setVisibility(View.VISIBLE);
                            holder.setText(R.id.sixText, dataBean.getName());
                            /*是否解锁*/
                            if (dataBean.getOpen_up() == 1) {
                                holder.setImageResource(R.id.sixImg, R.mipmap.katchuan);
                            } else {
                                holder.setImageResource(R.id.sixImg, R.mipmap.wuchuan);
                            }
                            //关卡点击事件
                            holder.getView(R.id.six).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    re_id = dataBean.getXhy_id();
                                    points = dataBean.getPoints();
                                    index = postion;
                                    if (isCanSee(dataBean.getPermissions(), dataBean.isPay(), dataBean.getOpen_up() + "", dataBean.getPoints(), strings)) {
                                        itemClick(dataBean);
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

    /*点击跳转*/
    private void itemClick(XieHouYuLianXiShouData.DataBean dataBean) {
        Intent in = new Intent(getActivity(), LianXiTiMuActivity.class);
        in.putExtra("xhy_id", dataBean.getXhy_id());
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
