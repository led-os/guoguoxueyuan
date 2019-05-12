package com.test720.grasshoppercollege.module.gongLue.teacherAndConsultant;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseRecyclerViewFragment;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.gongLue.student.onLine.OnLineActivity;
import com.test720.grasshoppercollege.module.gongLue.teacherAndConsultant.bean.PeiXunTeacherXiangQingData;

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
 * 作者：水东流 编于 2018/9/21
 */
public class FuWuFragment extends BaseRecyclerViewFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    private List<PeiXunTeacherXiangQingData.DataBean.ServiceBean> service = new ArrayList<>();
    MyBaseRecyclerAdapter<PeiXunTeacherXiangQingData.DataBean.ServiceBean> adapter;

    public void setService(List<PeiXunTeacherXiangQingData.DataBean.ServiceBean> service) {
        this.service = service;
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
    public void init() {
        adapter = new MyBaseRecyclerAdapter<PeiXunTeacherXiangQingData.DataBean.ServiceBean>(service, getActivity(), R.layout.fu_wu_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, PeiXunTeacherXiangQingData.DataBean.ServiceBean item, int postion) {
                holder.setText(R.id.text, item.getName());
                holder.setText(R.id.price, item.getPrice_time_length().get(0).getPrice() +
                        "/" + item.getPrice_time_length().get(0).getTime());
                holder.setText(R.id.peopleNum, item.getOrders_count() + "人已报名");
            }
        };
        adapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (getArguments() != null) {
                    Intent intent = new Intent(getActivity(), OnLineActivity.class);
                    intent.putExtra("title", service.get(position).getName());
                    intent.putExtra("line_id", service.get(position).getService_id());
                    jumpToActivity(intent, false);
                }
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setListener() {

    }

    @Override
    public int setlayoutResID() {
        return R.layout.base_shua_xin;
    }

}

