package com.test720.grasshoppercollege.module.gongLue.teacherAndConsultant;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.BaseRecyclerViewFragment;
import com.test720.grasshoppercollege.HttpBean.PingLunData;
import com.test720.grasshoppercollege.R;
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
 * 作者：水东流 编于 2018/9/21
 */
public class TeacherPinLunFragment extends BaseRecyclerViewFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    List<PingLunData> list = new ArrayList<>();
    PinLunUntil pinLunUntil;

    public List<PingLunData> getList() {
        return list;
    }

    public void setList(List<PingLunData> list) {
        this.list = list;
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
        pinLunUntil = new PinLunUntil(list, mContext, new PinLunUntil.PinLunInterface() {
            @Override
            public void Zan(String commentId, int pos) {

            }

            @Override
            public void Reply(String commentId, String name) {

            }

            @Override
            public void ItemClick(PingLunData item) {

            }
        });
        recyclerView.setAdapter(pinLunUntil.getAdapter());
    }

    @Override
    public void onStart() {
        super.onStart();
        pinLunUntil.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void setListener() {

    }

    @Override
    public int setlayoutResID() {
        return R.layout.base_shua_xin;
    }


}
