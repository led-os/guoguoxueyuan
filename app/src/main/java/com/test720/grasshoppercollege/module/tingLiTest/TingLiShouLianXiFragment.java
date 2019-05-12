package com.test720.grasshoppercollege.module.tingLiTest;

import android.content.Intent;
import android.view.View;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BuyClassActivity;
import com.test720.grasshoppercollege.HttpBean.TingLiBianYinShouData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.PubLicChuangGuanFragment;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * _oo0oo_
 * o8888888o
 * 88" . "88
 * (| -_- |)
 * 0\  =  /0
 * ___/`---'\___
 * .' \\|     |// '.
 * / \\|||  :  |||// \
 * / _||||| -卍-|||||- \
 * |   | \\\  -  /// |   |
 * | \_|  ''\---/''  |_/ |
 * \  .-\__  '-'  ___/-. /
 * ___'. .'  /--.--\  `. .'___
 * ."" '<  `.___\_<|>_/___.' >' "".
 * | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 * \  \ `_.   \_ __\ /__ _/   .-` /  /
 * =====`-.____`.___ \_____/___.-`___.-'=====
 * `=---='
 * 佛祖保佑        永无BUG
 * 佛曰:
 * 程序园里程序天，程序天里程序员；
 * 程序猿人写程序，又拿程序换肉钱。
 * 肉饱继续桌前坐，饱暖还是桌前眠；
 * 半迷半醒日复日，码上码下年复年。
 * 但愿叱咤互联世，不愿搬砖码当前；
 * 诸葛周瑜算世事，我来算出得加钱。
 * 别人笑我忒直男，我笑自己太像猿；
 * 但见成都府国内，处处地地程序员。
 * Created by 水东流 on 2018/3/21 0021.
 */
/*单词辩音首页*/
public class TingLiShouLianXiFragment extends PubLicChuangGuanFragment {

    private List<List<TingLiBianYinShouData.DataBean>> list = new ArrayList<>();
    int index = -1;

    @Override
    public boolean isHORIZONTAL() {
        return false;
    }

    @Override
    public void init() {
        back1.setVisibility(View.GONE);
        adapter = new MyBaseRecyclerAdapter<List<TingLiBianYinShouData.DataBean>>(list, getActivity(), R.layout.dan_ci_bian_yin) {
            @Override
            public void convert(BaseRecyclerHolder holder, final List<TingLiBianYinShouData.DataBean> item, final int postion) {
                holder.getView(R.id.oneRel).setVisibility(View.INVISIBLE);
                holder.getView(R.id.twoRel).setVisibility(View.INVISIBLE);
                holder.getView(R.id.threeRel).setVisibility(View.INVISIBLE);
                holder.getView(R.id.fourRel).setVisibility(View.INVISIBLE);
                holder.getView(R.id.fiveRel).setVisibility(View.INVISIBLE);
                holder.getView(R.id.sixRel).setVisibility(View.INVISIBLE);
                for (int i = 0; i < item.size(); i++) {
                    final int j = i;
                    switch (i) {
                        case 0:
                            holder.getView(R.id.oneRel).setVisibility(View.VISIBLE);
                            if (item.get(j).getOpen_up() == 1) {
                                holder.getView(R.id.oneSuo).setVisibility(View.GONE);
                            } else {
                                holder.getView(R.id.oneSuo).setVisibility(View.VISIBLE);
                            }
                            holder.setText(R.id.oneNum, item.get(j).getName());
                            holder.getView(R.id.oneRel).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    itemClick(item, j);
                                    index = postion;
                                }
                            });
                            break;
                        case 1:
                            holder.getView(R.id.twoRel).setVisibility(View.VISIBLE);
                            if (item.get(i).getOpen_up() == 1) {
                                holder.getView(R.id.twoSuo).setVisibility(View.GONE);
                            } else {
                                holder.getView(R.id.twoSuo).setVisibility(View.VISIBLE);
                            }
                            holder.setText(R.id.twoNum, item.get(j).getName());
                            holder.getView(R.id.twoRel).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    itemClick(item, j);
                                    index = postion;
                                }
                            });
                            break;
                        case 2:
                            holder.getView(R.id.threeRel).setVisibility(View.VISIBLE);
                            if (item.get(i).getOpen_up() == 1) {
                                holder.getView(R.id.threeSuo).setVisibility(View.GONE);
                            } else {
                                holder.getView(R.id.threeSuo).setVisibility(View.VISIBLE);
                            }
                            holder.setText(R.id.threeNum, item.get(j).getName());
                            holder.getView(R.id.threeRel).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    itemClick(item, j);
                                    index = postion;
                                }
                            });
                            break;
                        case 3:
                            holder.getView(R.id.fourRel).setVisibility(View.VISIBLE);
                            if (item.get(i).getOpen_up() == 1) {
                                holder.getView(R.id.fourSuo).setVisibility(View.GONE);
                            } else {
                                holder.getView(R.id.fourSuo).setVisibility(View.VISIBLE);
                            }
                            holder.setText(R.id.fourNum, item.get(j).getName());
                            holder.getView(R.id.fourRel).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    itemClick(item, j);
                                    index = postion;
                                }
                            });
                            break;
                        case 4:
                            holder.getView(R.id.fiveRel).setVisibility(View.VISIBLE);
                            if (item.get(i).getOpen_up() == 1) {
                                holder.getView(R.id.fiveSuo).setVisibility(View.GONE);
                            } else {
                                holder.getView(R.id.fiveSuo).setVisibility(View.VISIBLE);
                            }
                            holder.setText(R.id.fiveNum, item.get(j).getName());
                            holder.getView(R.id.fiveRel).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    itemClick(item, j);
                                    index = postion;
                                }
                            });
                            break;
                        case 5:
                            holder.getView(R.id.sixRel).setVisibility(View.VISIBLE);
                            if (item.get(i).getOpen_up() == 1) {
                                holder.getView(R.id.sixSuo).setVisibility(View.GONE);
                            } else {
                                holder.getView(R.id.sixSuo).setVisibility(View.VISIBLE);
                            }
                            holder.setText(R.id.sixNum, item.get(j).getName());
                            holder.getView(R.id.sixRel).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    itemClick(item, j);
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

    public void getData() {
        HttpParams builder = new HttpParams();
        builder.put("uid", MyApplication.getmInstance().getUid());
        builder.put("class", MyApplication.getmInstance().nianji);
        post(HttpUntil.GetIntent().wordBianYinindex(), builder, 1);
    }

    //点击事件
    private void itemClick(List<TingLiBianYinShouData.DataBean> item, int j) {
        String[] strs = new String[3];
        strs[0] = item.get(j).getTitle();
        strs[1] = item.get(j).getContent();
        strs[2] = HttpUntil.GetIntent().getShare() + 31;
        points = item.get(j).getPoints();
        re_id = item.get(j).getBian_id();
        if (isCanSee(item.get(j).getPermissions(), item.get(j).isPay(), item.get(j).getOpen_up() + "", item.get(j).getPoints(), strs)) {
            Intent in = new Intent(getActivity(), TiMuActivity.class);
            in.putExtra("bian_id", item.get(j).getBian_id());
            jumpToActivity(in, false);
        }
    }


    @Override
    public void buyCurr() {
        Intent intent = new Intent(getActivity(), BuyClassActivity.class);
        intent.putExtra("curr_type", 21);
        intent.putExtra("type", "4");
        jumpToActivity(intent, false);
    }

    @Override
    public void buyGuoGuo() {
        if (re_id == null) return;
        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        httpParams.put("curr_type", 21);
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
        httpParams.put("curr_type", 21);
        httpParams.put("type", 1);
        httpParams.put("re_id", re_id);
        post(HttpUntil.GetIntent().addShareSpendRecord(), httpParams, 123, false);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void getSuccess(String s, int what) {
        if (what == 1 && codeIsOne(s, false)) {
            TingLiBianYinShouData data = new Gson().fromJson(s, TingLiBianYinShouData.class);
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
                    mainHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            DismissDialong();
                            recyclerView.setVisibility(View.VISIBLE);
                        }
                    }, 1000);
                }
            }

            if (list.size() == 0) {
                getRoot().setBackgroundResource(R.mipmap.beijche);
            }
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
