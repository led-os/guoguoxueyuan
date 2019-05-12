package com.test720.grasshoppercollege.module.gongLue.student.smallClass.fragment;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.BaseRecyclerViewFragment;
import com.test720.grasshoppercollege.HttpBean.PingLunData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.gongLue.student.bean.OnLinePingLunData;
import com.test720.grasshoppercollege.module.gongLue.student.smallClass.daGang.HuiFuActivity;
import com.test720.grasshoppercollege.module.gongLue.student.smallClass.daGang.PingLunXiangQingActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.pinLunUntil.PinLunUntil;

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
 * 作者：水东流 编于 2018/9/12
 */
public class PingLunFragment extends BaseRecyclerViewFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    List<PingLunData> list = new ArrayList<>();
    PinLunUntil pinLunUntil;
    String line_id = "";

    public List<PingLunData> getList() {
        return list;
    }

    public void setList(List<PingLunData> list) {
        this.list = list;
    }

    @Override
    public void onStart() {
        super.onStart();
        pinLunUntil = new PinLunUntil(getList(), getActivity(), new PinLunUntil.PinLunInterface() {
            @Override
            public void Zan(String commentId, int pos) {
                HttpParams httpParams = new HttpParams();
                httpParams.put("micro_class_id", line_id);
                httpParams.put("uid", MyApplication.getmInstance().getUid());
                assert getArguments() != null;
                httpParams.put("comment_id", getArguments().getString("comment_id"));
                post(HttpUntil.GetIntent().StrategylineThumbUp(), httpParams, 5);
            }

            @Override
            public void Reply(String commentId, String name) {
                Intent in = new Intent(getActivity(), HuiFuActivity.class);
                in.putExtra("comment_id", commentId);
                in.putExtra("name", name);
                in.putExtra("micro_class_id", line_id);
                jumpToActivity(in, false);
            }

            @Override
            public void ItemClick(PingLunData item) {
                Intent in = new Intent(getActivity(), PingLunXiangQingActivity.class);
                in.putExtra("comment_id", item.getComment_id());
                in.putExtra("name", item.getNickname());
                in.putExtra("micro_class_id", line_id);
                jumpToActivity(in, false);
            }
        });
        recyclerView.setAdapter(pinLunUntil.getAdapter());
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
        return HttpUntil.GetIntent().getTryListeningInfoComment();
    }

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            OnLinePingLunData data = new Gson().fromJson(str, OnLinePingLunData.class);
            getList().clear();
            getList().addAll(data.getData().getComment());
            pinLunUntil.getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            OnLinePingLunData data = new Gson().fromJson(str, OnLinePingLunData.class);
            getList().addAll(data.getData().getComment());
            pinLunUntil.getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public HttpParams httpParams() {
        assert getArguments() != null;
        line_id = getArguments().getString("micro_class_id");
        HttpParams httpParams = new HttpParams();
        httpParams.put("micro_class_id", line_id);
        httpParams.put("p", page);
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        return httpParams;
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
