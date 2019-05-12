package com.test720.grasshoppercollege.module.baiBianTingLi;

import android.content.Intent;
import android.view.View;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BuyClassActivity;
import com.test720.grasshoppercollege.HttpBean.BaiBianShouData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.PublickGuanKaActivity;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.FormBody;

public class BaiBianShouYeActivity extends PublickGuanKaActivity {
    private List<List<BaiBianShouData.DataBean>> list = new ArrayList<>();
    private String[] strings;


    @Override
    public void GetHttpBackStr(String str, int what) {
        if (what == 1 && codeIsOne(str, false)) {
            BaiBianShouData data = new Gson().fromJson(str, BaiBianShouData.class);
            list.clear();
            list.addAll(data.getData());
            Collections.reverse(list);
            initAdapter();


            ShowDialong();
            recyclerView.setVisibility(View.INVISIBLE);
            getLayoutManager().scrollToPositionWithOffset(getindex(), 0);

            mainHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    DismissDialong();
                    recyclerView.setVisibility(View.VISIBLE);
                }
            }, 1000);

        }

        if (what == 123 && codeIsOne(str, false)) {
            if (getChilditemCount() != -1 && getItemIndexCount() != -1) {
                list.get(getItemIndexCount()).get(getChilditemCount()).setOpen_up(1);
                adapter.notifyDataSetChanged();
            }
        }

    }

    @Override
    public void ShuaXin() {
        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("uid", MyApplication.getmInstance().getUid());//请求参数一
        formBuilder.add("class", MyApplication.getmInstance().nianji);
        Post(HttpUntil.GetIntent().EnListeningindex(), formBuilder, 1);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        ShuaXin();
    }

    @Override
    public boolean isHORIZONTAL() {
        return false;
    }

    @Override
    public void init() {
        jiLei.setVisibility(View.GONE);
    }

    private void initAdapter() {
        adapter = new MyBaseRecyclerAdapter<List<BaiBianShouData.DataBean>>(list, this, R.layout.bai_bian_guan_ka) {
            @Override
            public void convert(BaseRecyclerHolder holder, final List<BaiBianShouData.DataBean> item, final int postion) {
                holder.getView(R.id.one).setVisibility(View.INVISIBLE);
                holder.getView(R.id.two).setVisibility(View.INVISIBLE);
                holder.getView(R.id.three).setVisibility(View.INVISIBLE);
                holder.getView(R.id.four).setVisibility(View.INVISIBLE);
                holder.getView(R.id.five).setVisibility(View.INVISIBLE);
                holder.getView(R.id.six).setVisibility(View.INVISIBLE);
                for (int i = 0; i < item.size(); i++) {
                    final int j = i;
                    switch (i) {
                        case 0:
                            holder.getView(R.id.one).setVisibility(View.VISIBLE);
                            if (item.get(i).getOpen_up() == 1) {
                                holder.setImageResource(R.id.oneImg, R.mipmap.gqheye);
                            } else {
                                holder.setImageResource(R.id.oneImg, R.mipmap.qinwasuo);
                            }
                            holder.setText(R.id.oneText, item.get(i).getName());
                            holder.getView(R.id.one).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    setItemIndexCount(postion);
                                    setChilditemCount(j);
                                    AdapterClick(item.get(j));
                                }
                            });
                            break;
                        case 1:
                            holder.getView(R.id.two).setVisibility(View.VISIBLE);
                            if (item.get(i).getOpen_up() == 1) {
                                holder.setImageResource(R.id.twoImg, R.mipmap.gqheye);
                            } else {
                                holder.setImageResource(R.id.twoImg, R.mipmap.qinwasuo);
                            }
                            holder.setText(R.id.twoText, item.get(i).getName());
                            holder.getView(R.id.two).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    setItemIndexCount(postion);
                                    setChilditemCount(j);
                                    AdapterClick(item.get(j));
                                }
                            });
                            break;
                        case 2:
                            holder.getView(R.id.three).setVisibility(View.VISIBLE);
                            if (item.get(i).getOpen_up() == 1) {
                                holder.setImageResource(R.id.threeImg, R.mipmap.gqheye);
                            } else {
                                holder.setImageResource(R.id.threeImg, R.mipmap.qinwasuo);
                            }
                            holder.setText(R.id.threeText, item.get(i).getName());
                            holder.getView(R.id.three).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    setItemIndexCount(postion);
                                    setChilditemCount(j);
                                    AdapterClick(item.get(j));
                                }
                            });
                            break;
                        case 3:
                            holder.getView(R.id.four).setVisibility(View.VISIBLE);
                            if (item.get(i).getOpen_up() == 1) {
                                holder.setImageResource(R.id.fourImg, R.mipmap.gqheye);
                            } else {
                                holder.setImageResource(R.id.fourImg, R.mipmap.qinwasuo);
                            }
                            holder.setText(R.id.fourText, item.get(i).getName());
                            holder.getView(R.id.four).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    setItemIndexCount(postion);
                                    setChilditemCount(j);
                                    AdapterClick(item.get(j));
                                }
                            });
                            break;
                        case 4:
                            holder.getView(R.id.five).setVisibility(View.VISIBLE);
                            if (item.get(i).getOpen_up() == 1) {
                                holder.setImageResource(R.id.fiveImg, R.mipmap.gqheye);
                            } else {
                                holder.setImageResource(R.id.fiveImg, R.mipmap.qinwasuo);
                            }
                            holder.setText(R.id.fiveText, item.get(i).getName());
                            holder.getView(R.id.five).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    setItemIndexCount(postion);
                                    setChilditemCount(j);
                                    AdapterClick(item.get(j));
                                }
                            });
                            break;
                        case 5:
                            holder.getView(R.id.six).setVisibility(View.VISIBLE);
                            if (item.get(i).getOpen_up() == 1) {
                                holder.setImageResource(R.id.sixImg, R.mipmap.gqheye);
                            } else {
                                holder.setImageResource(R.id.sixImg, R.mipmap.qinwasuo);
                            }
                            holder.setText(R.id.sixText, item.get(i).getName());
                            holder.getView(R.id.six).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    setItemIndexCount(postion);
                                    setChilditemCount(j);
                                    AdapterClick(item.get(j));
                                }
                            });
                            break;
                    }
                }
            }
        };

        recyclerView.setAdapter(adapter);
    }

    public void AdapterClick(BaiBianShouData.DataBean dataBean) {
        re_id = dataBean.getListening_id();
        points = dataBean.getPoints();
        strings = new String[3];
        strings[0] = dataBean.getTitle();
        strings[1] = dataBean.getContent();
        strings[2] = HttpUntil.GetIntent().getShare() + 24;
        if (isCanSee(dataBean.getPermissions(), dataBean.isPay(), dataBean.getOpen_up() + "", dataBean.getPoints(), strings)) {
            Intent in = new Intent(this, BaiBianTingLiTypeActivity.class);
            in.putExtra("id", Integer.parseInt(dataBean.getListening_id().toString()));
            jumpToActivity(in, false);
        }
    }

    @Override
    public void buyCurr() {
        Intent intent = new Intent(mcontext, BuyClassActivity.class);
        intent.putExtra("curr_type", 14);
        intent.putExtra("type", "4");
        jumpToActivity(intent, false);
    }

    @Override
    public void buyGuoGuo() {
        if (re_id == null) return;
        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("uid", MyApplication.getmInstance().getUid());//请求参数一
        formBuilder.add("curr_type", "14");
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
        formBuilder.add("curr_type", "14");
        formBuilder.add("type", "1");
        formBuilder.add("re_id", re_id);
        Post(HttpUntil.GetIntent().addShareSpendRecord(), formBuilder, 123, false);
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
