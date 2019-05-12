package com.test720.grasshoppercollege.module.gongLue.peiXun.xiaoXi;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseRecyclerViewFragment;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.WebViewActivity;
import com.test720.grasshoppercollege.module.gongLue.GongLueData;
import com.test720.grasshoppercollege.module.gongLue.peiXun.bean.XiaoXiData;
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
 * 作者：水东流 编于 2018/8/22
 */
public class XiaoXiFragment extends BaseRecyclerViewFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    List<XiaoXiData.DataBean> list = new ArrayList<>();
    MyBaseRecyclerAdapter adapter;

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
        if (GongLueData.getInstance().getType() == GongLueData.JIGOU) {
            return HttpUntil.GetIntent().StrategyTraintuwen();
        } else {
            return HttpUntil.GetIntent().StrategyChildtuwen();
        }
    }

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            XiaoXiData msgdata = new Gson().fromJson(str, XiaoXiData.class);
            list.clear();
            list.addAll(msgdata.getData());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        haveLin = false;
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            XiaoXiData msgdata = new Gson().fromJson(str, XiaoXiData.class);
            list.addAll(msgdata.getData());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public HttpParams httpParams() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("p", page);
        httpParams.put("tuwen", GongLueData.getInstance().getTuwen());
        httpParams.put("school_id", GongLueData.getInstance().getSchoolId());
        return httpParams;
    }

    @Override
    public void init() {
        adapter = new MyBaseRecyclerAdapter<XiaoXiData.DataBean>(list, getActivity(), R.layout.xiao_xi_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, XiaoXiData.DataBean item, int postion) {
                holder.setText(R.id.time, item.getTime());
                holder.setImageByUrl(R.id.pic, item.getPic());
                holder.setText(R.id.body, item.getName());
            }
        };
        adapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                switch (list.get(position).getLocation_type()) {
                    case "1":
                    case "2":
                        Intent in = new Intent(getActivity(), WebViewActivity.class);
                        in.putExtra("title",list.get(position).getName());
                        in.putExtra("url", list.get(position).getLocation_link() + "/id/" + list.get(position).getMsg_id());
                        jumpToActivity(in, false);
                        break;
                    case "3":
                        try {
                            Intent intent = new Intent();
                            intent.setAction("android.intent.action.VIEW");
                            Uri content_url = Uri.parse(list.get(position).getLocation_link());
                            intent.setData(content_url);
                            startActivity(intent);
                        } catch (Exception e) {

                        }

                        break;
                }

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