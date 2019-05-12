package com.test720.grasshoppercollege.module.gongLue.teacher.pinTaiZiXun.yuYue;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseRecyclerViewFragment;
import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.gongLue.teacher.bean.YuYueListData;
import com.test720.grasshoppercollege.untils.HttpUntil;

import org.greenrobot.eventbus.EventBus;

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
public class YuYueFragment extends BaseRecyclerViewFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    MyBaseRecyclerAdapter<YuYueListData.DataBean.ListBean> adapter;
    List<YuYueListData.DataBean.ListBean> list = new ArrayList<>();

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
        return HttpUntil.GetIntent().StrategygetTeacherYuYue();
    }

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            YuYueListData data = new Gson().fromJson(str, YuYueListData.class);
            list.clear();
            list.addAll(data.getData().getList());
            adapter.notifyDataSetChanged();
            CurrencyEvent currencyEvent = new CurrencyEvent("预约金额", CurrencyEvent.YUYUEJINE);
            currencyEvent.setObject(data.getData().getInfo());
            EventBus.getDefault().post(currencyEvent);
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            YuYueListData data = new Gson().fromJson(str, YuYueListData.class);
            list.addAll(data.getData().getList());
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
        adapter = new MyBaseRecyclerAdapter<YuYueListData.DataBean.ListBean>(list, mContext, R.layout.yu_yue_ding_dan_list_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, YuYueListData.DataBean.ListBean item, final int postion) {
                holder.setText(R.id.name, item.getNickname());
                holder.setText(R.id.title, item.getService_name());
                String s = "咨询时间" + item.getTimes() + "(共" + item.getTime_length() + "分钟)";
                holder.setText(R.id.time, s);
                holder.setHeaderByUrl(R.id.pic, item.getHeader());
                TextView textView = holder.getView(R.id.pinLun);
                assert getArguments() != null;
                switch (getArguments().getInt("status")) {
                    case 0:
                        textView.setText("代咨询");
                        break;
                    case 1:
                        textView.setText("待确认");
                        break;
                    case 2:
                        textView.setText("已完成");
                        holder.setText(R.id.profit, "获得收入：￥" + item.getProfit());
                        break;
                }
            }
        };

        adapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(mContext, YuYueDingDanXiangQingActivity.class);
                intent.putExtra("status", "tid");
                intent.putExtra("bohao", false);
                intent.putExtra("yuyue_id", list.get(position).getYuyue_id());
                jumpToActivity(intent, false);
            }
        });
        recyclerView.setAdapter(adapter);
    }

}
