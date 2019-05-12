package com.test720.grasshoppercollege.module.gongLue.teacher.pinTaiZiXun.wenDa;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseRecyclerViewFragment;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.gongLue.teacher.bean.WenDaListData;
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
 * 作者：水东流 编于 2018/9/17
 */
public class WenDaListFragment extends BaseRecyclerViewFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    TextView price;

    public TextView getPrice() {
        return price;
    }

    public void setPrice(TextView price) {
        this.price = price;
    }

    MyBaseRecyclerAdapter<WenDaListData.DataBean> adapter;
    List<WenDaListData.DataBean> list = new ArrayList<>();

    @Override
    public void setListener() {

    }

    @Override
    public int setlayoutResID() {
        return R.layout.base_shua_xin;
    }


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
        return HttpUntil.GetIntent().StrategyteacherAnswerList();
    }

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            WenDaListData data = new Gson().fromJson(str, WenDaListData.class);
            list.clear();
            list.addAll(data.getData());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getSuccess(String str, int what) {
        super.getSuccess(str, what);
        switch (what) {
            case 5:
                if (codeIsOne(str)) {
                    ShuaXin();
                }
                break;
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            WenDaListData data = new Gson().fromJson(str, WenDaListData.class);
            list.addAll(data.getData());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public HttpParams httpParams() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("tid", MyApplication.getmInstance().getUid());
        assert getArguments() != null;
        httpParams.put("status", getArguments().getInt("status"));
        httpParams.put("p", page);
        return httpParams;
    }

    @Override
    public void init() {
        adapter = new MyBaseRecyclerAdapter<WenDaListData.DataBean>(list, mContext, R.layout.wen_da_list_item) {
            @Override
            public void convert(final BaseRecyclerHolder holder, final WenDaListData.DataBean item, final int postion) {
                holder.setText(R.id.content, item.getQuestion());
                holder.setText(R.id.name, item.getUser_nickname());
                String s = item.getQuestion_time();
                holder.setText(R.id.time, s);
                holder.setHeaderByUrl(R.id.pic, item.getUser_header());
                TextView status = holder.getView(R.id.status);
                TextView down = holder.getView(R.id.down);
//0-待回复;1-已回复
                switch (getArguments().getInt("status")) {
                    case 0:
                        status.setText("待回复");
                        down.setTextColor(getResources().getColor(R.color.white));
                        down.setBackgroundResource(R.drawable.bule_back_kuang);
                        down.setText("回复");
                        down.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(mContext, WenDaHuiFuActivity.class);
                                intent.putExtra("answer_id", item.getAnswer_id());
                                intent.putExtra("edit", true);
                                startActivityForResult(intent, 1024);
                            }
                        });
                        break;
                    case 1:
                        status.setText("已回复");
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
                    intent.putExtra("type", WenDaHuiFuActivity.TEACHER_DHF);
                } else {
                    intent.putExtra("type", WenDaHuiFuActivity.TEACHER_YHF);
                }
                jumpToActivity(intent, false);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1024 && resultCode == 1) {
            ShuaXin();
        }
    }
}
