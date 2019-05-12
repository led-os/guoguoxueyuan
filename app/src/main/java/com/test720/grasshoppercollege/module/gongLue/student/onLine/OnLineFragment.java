package com.test720.grasshoppercollege.module.gongLue.student.onLine;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseRecyclerViewFragment;
import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.gongLue.BaseKeMuShouYeActivity;
import com.test720.grasshoppercollege.module.gongLue.GongLueData;
import com.test720.grasshoppercollege.module.gongLue.student.bean.OnLinelistData;
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
 * Created by 水东流 on 2018/8/17.
 */

public class OnLineFragment extends BaseRecyclerViewFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    public MyBaseRecyclerAdapter adapter;
    List<OnLinelistData.DataBean> list = new ArrayList<>();
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    BaseKeMuShouYeActivity baseKeMuShouYeActivity;

    public BaseKeMuShouYeActivity getBaseKeMuShouYeActivity() {
        return baseKeMuShouYeActivity;
    }

    public void setBaseKeMuShouYeActivity(BaseKeMuShouYeActivity baseKeMuShouYeActivity) {
        this.baseKeMuShouYeActivity = baseKeMuShouYeActivity;
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
        return HttpUntil.GetIntent().StrategylineList();
    }

    @Override
    public void onEvent(CurrencyEvent event) {
        super.onEvent(event);
        if(event.getWhat()==CurrencyEvent.NIANJICHANGE){
            ShuaXin();
        }
    }
    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            OnLinelistData data = new Gson().fromJson(str, OnLinelistData.class);
            list.clear();
            list.addAll(data.getData());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            OnLinelistData data = new Gson().fromJson(str, OnLinelistData.class);
            list.addAll(data.getData());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public HttpParams httpParams() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("book_type", getArguments().getString("book_type"));
        httpParams.put("class", getBaseKeMuShouYeActivity().getNianJi());
        httpParams.put("type", GongLueData.getInstance().getModularType());
        httpParams.put("p", page);
        return httpParams;
    }

    @Override
    public void init() {
        adapter = new MyBaseRecyclerAdapter<OnLinelistData.DataBean>(list, getActivity(), R.layout.gong_lue_online_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, OnLinelistData.DataBean item, int postion) {
              /*  holder.setText(R.id.title, item.getName());
                holder.setText(R.id.content, "");
                holder.setText(R.id.price, "￥" + item.getPrice());
                holder.setText(R.id.vipPrice, "￥" + item.getVip_price());
                holder.setText(R.id.number, item.getCourse_number() + "人报名");
                holder.setImageByUrl(R.id.book, item.getCover());*/

                holder.setText(R.id.title, item.getName());
                holder.setText(R.id.content, item.getAims());
                holder.setText(R.id.price, "￥" + item.getPrice());

                String s = "(" + item.getCourse_number() + "节课)";
                holder.setText(R.id.priceBeiZhu, s);

                holder.setText(R.id.vipPrice, "￥" + item.getVip_price());
                holder.setText(R.id.number, item.getCourse_number() + "人报名");
                holder.setImageByUrl(R.id.book, item.getCover());
            }
        };
        adapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), OnLineActivity.class);
                intent.putExtra("title", list.get(position).getName());
                intent.putExtra("line_id", list.get(position).getLine_id());
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