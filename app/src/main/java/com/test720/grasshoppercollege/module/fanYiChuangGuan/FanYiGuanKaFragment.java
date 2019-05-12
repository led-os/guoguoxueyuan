package com.test720.grasshoppercollege.module.fanYiChuangGuan;

import android.content.Intent;
import android.view.View;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BuyClassActivity;
import com.test720.grasshoppercollege.HttpBean.FanYiGuanKaData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.PubLicChuangGuanFragment;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
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
 * Created by 水东流 on 2018/3/23 0023.
 */

public class FanYiGuanKaFragment extends PubLicChuangGuanFragment {

    String[] strings;//分享信息

    private List<List<FanYiGuanKaData.DataBean>> data = new ArrayList<>();

    String bookId = "";
    int index = -1;

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    @Override
    public boolean isHORIZONTAL() {
        return true;
    }

    @Override
    public void init() {
        back1.setVisibility(View.GONE);
        adapter = new MyBaseRecyclerAdapter<List<FanYiGuanKaData.DataBean>>(data, getActivity(), R.layout.fan_yi_guan_ka) {
            @Override
            public void convert(BaseRecyclerHolder holder, final List<FanYiGuanKaData.DataBean> item, final int postion) {
                for (int j = 0; j < item.size(); j++) {
                    final int i = j;
                    strings = new String[3];
                    strings[0] = item.get(i).getTitle();
                    strings[1] = item.get(i).getContent();
                    strings[2] = HttpUntil.GetIntent().getShare() + 22;
                    switch (i) {
                        case 0:
                            holder.getView(R.id.one).setVisibility(View.VISIBLE);
                            if (item.get(i).getOpen_up() != 1) {
                                holder.getView(R.id.one).setBackgroundResource(R.mipmap.tangguo2);
                            } else {
                                holder.getView(R.id.one).setBackgroundResource(R.mipmap.tangguo);
                            }
                            holder.getView(R.id.one).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    unitClick(item, i);
                                    index = postion;
                                }
                            });
                            holder.setText(R.id.one, item.get(i).getUnit());


                            break;
                        case 1:
                            holder.getView(R.id.two).setVisibility(View.VISIBLE);
                            if (item.get(i).getOpen_up() != 1) {
                                holder.getView(R.id.two).setBackgroundResource(R.mipmap.tangguo2);
                            } else {
                                holder.getView(R.id.two).setBackgroundResource(R.mipmap.tangguo);
                            }
                            holder.getView(R.id.two).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    unitClick(item, i);
                                    index = postion;
                                }
                            });
                            holder.setText(R.id.two, item.get(i).getUnit());

                            break;
                        case 2:
                            holder.getView(R.id.three).setVisibility(View.VISIBLE);
                            if (item.get(i).getOpen_up() != 1) {
                                holder.getView(R.id.three).setBackgroundResource(R.mipmap.tangguo2);
                            } else {
                                holder.getView(R.id.three).setBackgroundResource(R.mipmap.tangguo);
                            }
                            holder.getView(R.id.three).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    unitClick(item, i);
                                    index = postion;
                                }
                            });
                            holder.setText(R.id.three, item.get(i).getUnit());

                            break;
                        case 3:
                            holder.getView(R.id.four).setVisibility(View.VISIBLE);
                            if (item.get(i).getOpen_up() != 1) {
                                holder.getView(R.id.four).setBackgroundResource(R.mipmap.tangguo2);
                            } else {
                                holder.getView(R.id.four).setBackgroundResource(R.mipmap.tangguo);
                            }
                            holder.getView(R.id.four).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    unitClick(item, i);
                                    index = postion;
                                }
                            });
                            holder.setText(R.id.four, item.get(i).getUnit());
                            break;
                    }
                }
            }
        };
        recyclerView.setAdapter(adapter);
        getData();
    }

    public void getData() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        httpParams.put("book_id", bookId);
        post(HttpUntil.GetIntent().Wordindex(), httpParams, 1);
    }

    @Override
    public void shuaXin() {
        super.shuaXin();
        getData();
    }

    /*item点击*/
    private void unitClick(List<FanYiGuanKaData.DataBean> item, int i) {
        re_id = item.get(i).getUnit_id();
        points = item.get(i).getPoints();
        if (isCanSee(item.get(i).getPermissions(), item.get(i).isPay(), item.get(i).getOpen_up() + "", item.get(i).getPoints(), strings)) {
            Intent in = new Intent(getActivity(), FanYiTiMuActivity.class);
            in.putExtra("unit_id", item.get(i).getUnit_id());
            jumpToActivity(in, false);
        }
    }

    @Override
    public void buyCurr() {
        Intent intent = new Intent(getActivity(), BuyClassActivity.class);
        intent.putExtra("curr_type", 12);
        intent.putExtra("type", "4");
        jumpToActivity(intent, false);
    }

    @Override
    public void buyGuoGuo() {
        if (re_id == null) return;
        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        httpParams.put("curr_type", 12);
        httpParams.put("type", 2);
        httpParams.put("points", points);
        httpParams.put("re_id", re_id);
        httpParams.put("book_id", getPublicLianXiJinShaiActivity().getId());
        post(HttpUntil.GetIntent().addShareSpendRecord(), httpParams, 123, false);
    }

    @Override
    public void shareCurr() {
        if (re_id == null) return;
        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        httpParams.put("curr_type", 12);
        httpParams.put("type", 1);
        httpParams.put("re_id", re_id);
        httpParams.put("book_id", getPublicLianXiJinShaiActivity().getId());
        post(HttpUntil.GetIntent().addShareSpendRecord(), httpParams, 123, false);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void getSuccess(String str, int what) {
        if (codeIsOne(str, false)) {
            FanYiGuanKaData guankaData = new Gson().fromJson(str, FanYiGuanKaData.class);
            data.clear();
            data.addAll(guankaData.getData());
            adapter.notifyDataSetChanged();
            if (data.size() > 0) {
                recyclerView.scrollToPosition(index);
            }
        }
    }
}
