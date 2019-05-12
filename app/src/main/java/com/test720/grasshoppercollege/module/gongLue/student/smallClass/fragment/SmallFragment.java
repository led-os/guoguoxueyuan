package com.test720.grasshoppercollege.module.gongLue.student.smallClass.fragment;

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
import com.test720.grasshoppercollege.module.gongLue.student.bean.SmallListData;
import com.test720.grasshoppercollege.module.gongLue.student.smallClass.SmallClassXiangQingActivity;
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
 * 作者：水东流 编于 2018/9/6
 */
public class SmallFragment extends BaseRecyclerViewFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    public MyBaseRecyclerAdapter adapter;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    BaseKeMuShouYeActivity baseKeMuShouYeActivity;
    List<SmallListData.DataBean> list = new ArrayList<>();

    public void setBaseKeMuShouYeActivity(BaseKeMuShouYeActivity baseKeMuShouYeActivity) {
        this.baseKeMuShouYeActivity = baseKeMuShouYeActivity;
    }

    @Override
    public void onEvent(CurrencyEvent event) {
        super.onEvent(event);
        if(event.getWhat()==CurrencyEvent.NIANJICHANGE){
            ShuaXin();
        }
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
        return HttpUntil.GetIntent().StrategymicroList();
    }

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            SmallListData data = new Gson().fromJson(str, SmallListData.class);
            list.clear();
            list.addAll(data.getData());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            SmallListData data = new Gson().fromJson(str, SmallListData.class);
            list.addAll(data.getData());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public HttpParams httpParams() {
        HttpParams httpParams = new HttpParams();
        assert getArguments() != null;
        httpParams.put("book_type", getArguments().getString("book_type"));
        httpParams.put("class", baseKeMuShouYeActivity.getNianJi());
        httpParams.put("type", GongLueData.getInstance().getModularType());
        httpParams.put("p", page);
        return httpParams;
    }

    @Override
    public void init() {
        /*R.layout.small_ke_item*/
        adapter = new MyBaseRecyclerAdapter<SmallListData.DataBean>(list, getActivity(), R.layout.gong_lue_online_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, SmallListData.DataBean item, int postion) {
              /*  holder.setText(R.id.title, item.getName());
                holder.setText(R.id.content, item.getAims());
                holder.setText(R.id.number, item.getSubscribe_count() + "人订阅");
                RatingBar ratingBar = holder.getView(R.id.ratingBar);
                ratingBar.setRating(Float.parseFloat(item.getLevel()));
                holder.setImageByUrl(R.id.book, item.getPic());
                holder.setText(R.id.price, "￥" + item.getPrice());*/

                holder.setText(R.id.title, item.getName());
                holder.setText(R.id.content, item.getAims());
                holder.setText(R.id.price, "￥" + item.getPrice());
                holder.setText(R.id.priceBeiZhu, "");

                holder.setText(R.id.vipPrice, "￥" + item.getVip_price());
                holder.setText(R.id.number, item.getSubscribe_count() + "人报名");
                holder.setImageByUrl(R.id.book, item.getPic());
            }
        };
        adapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), SmallClassXiangQingActivity.class);
                intent.putExtra("micro_id", list.get(position).getMicro_id());
                intent.putExtra("name",list.get(position).getName());
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