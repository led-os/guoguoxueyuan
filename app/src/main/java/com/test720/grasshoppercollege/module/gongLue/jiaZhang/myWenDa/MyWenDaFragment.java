package com.test720.grasshoppercollege.module.gongLue.jiaZhang.myWenDa;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseRecyclerViewFragment;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.gongLue.jiaZhang.bean.MyWenDaListData;
import com.test720.grasshoppercollege.module.gongLue.teacher.pinTaiZiXun.wenDa.WenDaHuiFuActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 佛祖保佑        永无BUG
 * 佛曰:
 * 程序园里程序天，程序天里程序员；
 * 程序猿人写程序，又拿程序换肉钱。
 * 肉饱继续桌前坐，饱暖还是桌前眠；
 * 半迷半醒日复日，码上码下年复年。
 * 但愿叱咤互联世，不愿搬砖码当前；
 * 诸葛周瑜算世事，我算需求得加钱。
 * 别人笑我忒直男，我说自己是程猿；
 * 但见成都府国内，处处地地程序员。
 * 作者：水东流 编于 2018/9/20
 */
public class MyWenDaFragment extends BaseRecyclerViewFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    List<MyWenDaListData.DataBean.InfoBean> list = new ArrayList<>();
    MyBaseRecyclerAdapter<MyWenDaListData.DataBean.InfoBean> adapter;

    @Override
    public int setCount() {
        return 1;
    }

    @Override
    public RecyclerView initRecyclerView() {
        return recyclerView;
    }

    @Override
    public SwipeRefreshLayout initSwipeRefreshLayout() {
        return swipeRefresh;
    }

    @Override
    public String url() {
        return HttpUntil.GetIntent().StrategyanswerOrder();
    }

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            MyWenDaListData daListData = new Gson().fromJson(str, MyWenDaListData.class);
            list.clear();
            list.addAll(daListData.getData().getInfo());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            MyWenDaListData daListData = new Gson().fromJson(str, MyWenDaListData.class);
            list.addAll(daListData.getData().getInfo());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getSuccess(String str, int what) {
        super.getSuccess(str, what);
        if (what == 5 && codeIsOne(str)) {
            ShuaXin();
        }
    }

    @Override
    public HttpParams httpParams() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("p", page);
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        assert getArguments() != null;
        httpParams.put("status", getArguments().getInt("status"));
        return httpParams;
    }

    @Override
    public void init() {
        adapter = new MyBaseRecyclerAdapter<MyWenDaListData.DataBean.InfoBean>(list, mContext, R.layout.wen_da_list_jia_zhang_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, MyWenDaListData.DataBean.InfoBean item, final int postion) {
                holder.setText(R.id.content, item.getQuestion());
                holder.setText(R.id.name, item.getTeacher_nickname());
                String s = item.getQuestion_time();
                holder.setText(R.id.time, s);
                holder.setHeaderByUrl(R.id.pic, item.getTeacher_header());
                LinearLayout downLin = holder.getView(R.id.downLin);
                final TextView status = holder.getView(R.id.status);
                final TextView downTxt = holder.getView(R.id.down);
                Switch kaiGuan = holder.getView(R.id.kaiGuan);
                //	0-待回复 5-待确认 3-已完成
                switch (getArguments().getInt("status")) {
                    case 0:
                        status.setText("待回复");
                        break;
                    case 1:
                        status.setText("已回复");
                        if (item.getIs_self().equals("0")) {
                            downTxt.setText("公开");
                            kaiGuan.setChecked(true);
                        } else {
                            downTxt.setText("不公开");
                            kaiGuan.setChecked(false);
                        }
                        downLin.setVisibility(View.VISIBLE);
                        kaiGuan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if (isChecked) {
                                    downTxt.setText("公开");
                                    HttpParams httpParams = new HttpParams();
                                    httpParams.put("answer_id", list.get(postion).getAnswer_id());
                                    httpParams.put("status", "0");
                                    post(HttpUntil.GetIntent().StrategyonlySelfLook(), httpParams, 5, true);
                                } else {
                                    downTxt.setText("不公开");
                                    HttpParams httpParams = new HttpParams();
                                    httpParams.put("answer_id", list.get(postion).getAnswer_id());
                                    httpParams.put("status", "1");
                                    post(HttpUntil.GetIntent().StrategyonlySelfLook(), httpParams, 5, true);
                                }
                            }
                        });
                        break;
                }
            }
        };
        adapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(mContext, WenDaHuiFuActivity.class);
                intent.putExtra("answer_id", list.get(position).getAnswer_id());
                if (getArguments().getInt("status") == 0) {
                    intent.putExtra("type", WenDaHuiFuActivity.JIAZHANG_DHF);
                } else {
                    intent.putExtra("type", WenDaHuiFuActivity.JIAZHANG_YHF);
                }
                jumpToActivity(intent, false);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setListener() {

    }

    @Override
    public int setlayoutResID() {
        return R.layout.base_shua_xin;
    }

}
