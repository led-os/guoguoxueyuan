package com.test720.grasshoppercollege.module.jinFanYiCi;

import android.content.Intent;
import android.os.Handler;
import android.view.View;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BuyClassActivity;
import com.test720.grasshoppercollege.HttpBean.JinFanYiShouData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.PubLicChuangGuanFragment;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by 水东流 on 2018/5/15.
 */

public class JinFanYIShouFragment extends PubLicChuangGuanFragment {

    private List<List<JinFanYiShouData.DataBean>> list = new ArrayList<>();
    private String[] strings;

    int index = -1;//下标
    Handler handler = new Handler();

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
        handler = null;
    }

    @Override
    public boolean isHORIZONTAL() {
        return false;
    }

    @Override
    public void init() {

        adapter = new MyBaseRecyclerAdapter<List<JinFanYiShouData.DataBean>>(list, getActivity(), R.layout.jin_fan_yi_shou_fragment) {
            @Override
            public void convert(BaseRecyclerHolder holder, final List<JinFanYiShouData.DataBean> item, final int postion) {
                for (int i = 0; i < item.size(); i++) {
                    final int j = i;
                    switch (i) {
                        case 0:
                            holder.getView(R.id.one).setVisibility(View.VISIBLE);
                            if (item.get(i).getOpen_up() == 1) {
                                holder.setImageResource(R.id.oneImg, R.mipmap.katann);
                            } else {
                                holder.setImageResource(R.id.oneImg, R.mipmap.wktong);
                            }
                            holder.setText(R.id.oneText, item.get(i).getName());
                            holder.getView(R.id.one).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    AdapterClick(item.get(j));
                                    index = postion;
                                }
                            });
                            break;
                        case 1:
                            holder.getView(R.id.two).setVisibility(View.VISIBLE);
                            if (item.get(i).getOpen_up() == 1) {
                                holder.setImageResource(R.id.twoImg, R.mipmap.katann);
                            } else {
                                holder.setImageResource(R.id.twoImg, R.mipmap.wktong);
                            }
                            holder.setText(R.id.twoText, item.get(i).getName());
                            holder.getView(R.id.two).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    AdapterClick(item.get(j));
                                    index = postion;
                                }
                            });
                            break;
                        case 2:
                            holder.getView(R.id.three).setVisibility(View.VISIBLE);
                            if (item.get(i).getOpen_up() == 1) {
                                holder.setImageResource(R.id.threeImg, R.mipmap.katann);
                            } else {
                                holder.setImageResource(R.id.threeImg, R.mipmap.wktong);
                            }
                            holder.setText(R.id.threeText, item.get(i).getName());
                            holder.getView(R.id.three).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    AdapterClick(item.get(j));
                                    index = postion;
                                }
                            });
                            break;
                        case 3:
                            holder.getView(R.id.four).setVisibility(View.VISIBLE);
                            if (item.get(i).getOpen_up() == 1) {
                                holder.setImageResource(R.id.fourImg, R.mipmap.katann);
                            } else {
                                holder.setImageResource(R.id.fourImg, R.mipmap.wktong);
                            }
                            holder.setText(R.id.fourText, item.get(i).getName());
                            holder.getView(R.id.four).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    AdapterClick(item.get(j));
                                    index = postion;
                                }
                            });
                            break;
                        case 4:
                            holder.getView(R.id.five).setVisibility(View.VISIBLE);
                            if (item.get(i).getOpen_up() == 1) {
                                holder.setImageResource(R.id.fiveImg, R.mipmap.katann);
                            } else {
                                holder.setImageResource(R.id.fiveImg, R.mipmap.wktong);
                            }
                            holder.setText(R.id.fiveText, item.get(i).getName());
                            holder.getView(R.id.five).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    AdapterClick(item.get(j));
                                    index = postion;
                                }
                            });
                            break;
                        case 5:
                            holder.getView(R.id.six).setVisibility(View.VISIBLE);
                            if (item.get(i).getOpen_up() == 1) {
                                holder.setImageResource(R.id.sixImg, R.mipmap.katann);
                            } else {
                                holder.setImageResource(R.id.sixImg, R.mipmap.wktong);
                            }
                            holder.setText(R.id.sixText, item.get(i).getName());
                            holder.getView(R.id.six).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    AdapterClick(item.get(j));
                                    index = postion;
                                }
                            });
                            break;
                    }
                }
            }
        };
        recyclerView.setAdapter(adapter);

        getData();

    }

    @Override
    public void shuaXin() {
        super.shuaXin();
        getData();
    }

    private void getData() {
        HttpParams builder = new HttpParams();
        builder.put("class", MyApplication.getmInstance().nianji);
        builder.put("uid", MyApplication.getmInstance().getUid());
        post(HttpUntil.GetIntent().ChineseJFindex(), builder, 1);
    }

    public void AdapterClick(JinFanYiShouData.DataBean dataBean) {
        re_id = dataBean.getJf_id();
        points = dataBean.getPoints();
        strings = new String[3];
        strings[0] = dataBean.getTitle();
        strings[1] = dataBean.getContent();
        strings[2] = HttpUntil.GetIntent().getShare() + 35;
        if (isCanSee(dataBean.getPermissions(), dataBean.isPay(), dataBean.getOpen_up() + "", dataBean.getPoints(), strings)) {
            Intent in = new Intent(getActivity(), JinfanTiMuActivity.class);
            in.putExtra("id", Integer.parseInt(dataBean.getJf_id().toString()));
            jumpToActivity(in, false);
        }
    }

    @Override
    public void buyCurr() {
        Intent intent = new Intent(getActivity(), BuyClassActivity.class);
        intent.putExtra("curr_type", 25);
        intent.putExtra("type", "4");
        jumpToActivity(intent, false);
    }

    @Override
    public void buyGuoGuo() {
        if (re_id == null) return;
        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        httpParams.put("curr_type", 25);
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
        httpParams.put("curr_type", 25);
        httpParams.put("type", 1);
        httpParams.put("re_id", re_id);
        post(HttpUntil.GetIntent().addShareSpendRecord(), httpParams, 123, false);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void getSuccess(String s, int what) {
        switch (what) {
            case 1:
                if (codeIsOne(s, false)) {
                    JinFanYiShouData data = new Gson().fromJson(s, JinFanYiShouData.class);
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
                break;
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
