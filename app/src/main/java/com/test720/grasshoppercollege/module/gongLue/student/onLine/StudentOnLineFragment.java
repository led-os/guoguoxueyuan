package com.test720.grasshoppercollege.module.gongLue.student.onLine;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseRecyclerViewFragment;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.gongLue.GongLueData;
import com.test720.grasshoppercollege.module.gongLue.student.bean.StudentShouData;

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

public class StudentOnLineFragment extends BaseRecyclerViewFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    boolean isOnLine = true;

    private List<StudentShouData.DataBean.OnlineBean> online = new ArrayList<>();

    public List<StudentShouData.DataBean.OnlineBean> getOnline() {
        return online;
    }

    public void setOnline(List<StudentShouData.DataBean.OnlineBean> online) {
        this.online = online;
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
        return null;
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


    }

    @Override
    public void onStart() {
        super.onStart();
        MyBaseRecyclerAdapter adapter = new MyBaseRecyclerAdapter<StudentShouData.DataBean.OnlineBean>(online, getActivity(), R.layout.gong_lue_online_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, StudentShouData.DataBean.OnlineBean item, int postion) {
                holder.setText(R.id.title, item.getName());
                holder.setText(R.id.content, item.getAims());
                holder.setText(R.id.price, "￥" + item.getPrice());

                String s = "(" + item.getCourse_number() + "节课)";
                holder.setText(R.id.priceBeiZhu, s);

                holder.setText(R.id.vipPrice, "￥" + item.getVip_price());
                holder.setText(R.id.number, item.getSign_count() + "人报名");
                holder.setImageByUrl(R.id.book, item.getCover());
            }
        };
        adapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (isOnLine) {
                    GongLueData.getInstance().setModularType(GongLueData.ONLINE);
                } else {
                    GongLueData.getInstance().setModularType(GongLueData.LINEDOWN);
                }
                Intent intent = new Intent(getActivity(), OnLineActivity.class);
                intent.putExtra("title", online.get(position).getName());
                intent.putExtra("line_id", online.get(position).getLine_id());
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
        return R.layout.list_view;
    }


    public boolean isOnLine() {
        return isOnLine;
    }

    public void setOnLine(boolean onLine) {
        isOnLine = onLine;
    }
}