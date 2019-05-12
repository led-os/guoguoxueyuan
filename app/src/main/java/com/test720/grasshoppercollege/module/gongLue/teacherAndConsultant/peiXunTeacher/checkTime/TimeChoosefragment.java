package com.test720.grasshoppercollege.module.gongLue.teacherAndConsultant.peiXunTeacher.checkTime;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseRecyclerViewFragment;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.gongLue.teacherAndConsultant.bean.TimeData;

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
 * 作者：水东流 编于 2018/9/21
 */
public class TimeChoosefragment extends BaseRecyclerViewFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    TimeData.DataBean data;
    String day = "";

    public void setDay(String day) {
        this.day = day;
    }

    public void setData(TimeData.DataBean data) {
        this.data = data;
    }

    @Override
    public int setCount() {
        return 4;
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
        return null;
    }

    @Override
    public void shuaXin(String str) {

    }

    @Override
    public void gengDuo(String str) {

    }

    @Override
    public HttpParams httpParams() {
        return null;
    }

    @Override
    public void onStart() {
        super.onStart();
        MyBaseRecyclerAdapter adapter = new MyBaseRecyclerAdapter<TimeData.DataBean.TimeBean>(data.getTime(), getActivity(), R.layout.time_choose_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, TimeData.DataBean.TimeBean item, int postion) {
                holder.setText(R.id.time, item.getTime());
                TextView can = holder.getView(R.id.can);
                if (item.getCan().equals("0")) {
                    can.setText("可约");
                } else {
                    can.setText("不可约");
                    can.setTextColor(getResources().getColor(R.color.gwe));
                }
            }
        };
        adapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (data.getTime().get(position).getCan().equals("1")) {
                    ShowToast("此时间不可预约");
                    return;
                }
                String t = day + " " + data.getTime().get(position).getTime();
                Intent intent = new Intent();
                // 获取用户计算后的结果
                intent.putExtra("time", t); //将计算的值回传回去
                mContext.setResult(2, intent);
                mContext.finish(); //结束当前的activity的生命周期
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void init() {

    }

    @Override
    public void setListener() {

    }

    @Override
    public int setlayoutResID() {
        return R.layout.base_shua_xin;
    }


}

