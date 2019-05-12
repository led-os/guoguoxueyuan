package com.test720.grasshoppercollege.module.gongLue.student.myCourse;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseRecyclerViewFragment;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.WebViewActivity;
import com.test720.grasshoppercollege.module.gongLue.student.bean.MyCourseListData;
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
 * 作者：水东流 编于 2018/9/19
 */
public class MyCourseFragment extends BaseRecyclerViewFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    List<MyCourseListData.DataBean.InfoBean> list = new ArrayList<>();
    MyBaseRecyclerAdapter<MyCourseListData.DataBean.InfoBean> adapter;

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
        return HttpUntil.GetIntent().StrategymyLineCourse();
    }

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            MyCourseListData data = new Gson().fromJson(str, MyCourseListData.class);
            list.clear();
            list.addAll(data.getData().getInfo());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            MyCourseListData data = new Gson().fromJson(str, MyCourseListData.class);
            list.addAll(data.getData().getInfo());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public HttpParams httpParams() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("p", page);
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        httpParams.put("type", getArguments().getString("type"));
        return httpParams;
    }

    @Override
    public void init() {
        adapter = new MyBaseRecyclerAdapter<MyCourseListData.DataBean.InfoBean>(list, mContext, R.layout.my_course_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, final MyCourseListData.DataBean.InfoBean item, int postion) {
                holder.setImageByUrl(R.id.img, item.getCover());
                if (item.getStart_time() != null)
                    holder.setText(R.id.start_time, item.getStart_time());
                if (item.getEnd_time() != null) holder.setText(R.id.end_time, item.getEnd_time());
                if (item.getName() != null) holder.setText(R.id.title, item.getName());
                String s = "共" + item.getCourse_number() + "节";
                holder.setText(R.id.all, s);
                String ss = "已授" + item.getComplete_course_number() + "节";
                holder.setText(R.id.daiShou, ss);
                if (getArguments().getString("type").equals("56")) {
                    holder.getView(R.id.adress).setVisibility(View.GONE);
                    holder.getView(R.id.zhiboadress).setVisibility(View.VISIBLE);
                } else {
                    holder.getView(R.id.adress).setVisibility(View.VISIBLE);
                    holder.getView(R.id.zhiboadress).setVisibility(View.GONE);
                }
                if (item.getAddress() != null) {
                    String adress = "";
                    if (item.getAddress().getProvince() != null) {
                        adress = adress + item.getAddress().getProvince();
                    }
                    if (item.getAddress().getCity() != null) {
                        adress = adress + item.getAddress().getCity();
                    }
                    if (item.getAddress().getArea() != null) {
                        adress = adress + item.getAddress().getArea();
                    }
                    holder.setText(R.id.adress, adress);
                }
                holder.getView(R.id.lianxi).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, WebViewActivity.class);
                        intent.putExtra("title",item.getName());
                        intent.putExtra("path", item.getCustomer_path());
                        jumpToActivity(intent, false);
                    }
                });
                holder.getView(R.id.zhiboadress).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, WebViewActivity.class);
                        intent.putExtra("path", item.getLive_path());
                        intent.putExtra("title",item.getName());
                        jumpToActivity(intent, false);
                    }
                });

            }
        };
        adapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(mContext, MyCourseActivity.class);
                intent.putExtra("order_id", list.get(position).getOrder_id());
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
