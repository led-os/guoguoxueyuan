package com.test720.grasshoppercollege.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.BaseRecyclerViewFragment;
import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.tanXian.TanXianShouAdapter;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.Collections;
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
 * 作者：水东流 编于 2018/12/6
 */
public class TanXianListFragment extends BaseRecyclerViewFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    private List<Data.DataBean> list = new ArrayList<>();
    TanXianShouAdapter adapter;


    @Override
    public int setCount() {
        noGengDuo = true;
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
        return HttpUntil.GetIntent().getIP() + "small.php/Adventure/index";
    }

    @Override
    public void shuaXin(String str) {
        Data data = new Gson().fromJson(str, Data.class);
        list.clear();
        list.addAll(data.getData());
        Collections.reverse(list);
        adapter.notifyDataSetChanged();
        getLayoutManager().scrollToPositionWithOffset(getindex() / 2, 0);
    }

    @Override
    public void gengDuo(String str) {

    }

    @Override
    public void onResume() {
        super.onResume();
        ShuaXin();
    }


    @Override
    public void onEvent(CurrencyEvent event) {
        super.onEvent(event);
        if (event.getWhat() == CurrencyEvent.ALLNIANJICHANGE) {
            ShuaXin();
        }
    }

    @Override
    public synchronized void ShuaXin() {
        super.ShuaXin();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        list.clear();
    }

    @Override
    public HttpParams httpParams() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        httpParams.put("class", MyApplication.getmInstance().nianji);
        return httpParams;
    }

    @Override
    public void init() {
        adapter = new TanXianShouAdapter(mContext, list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setListener() {

    }

    @Override
    public int setlayoutResID() {
        haveLin = false;
        return R.layout.tan_xian_fragment_list;
    }

    /**
     * 获取脚标
     *
     * @return 脚标
     */
    public int getindex() {
        if (list.size() == 0) return 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i).getOpen_up() == 0) {
                return i;
            }
        }
        return list.size() - 1;
    }


    public class Data {

        /**
         * code : 1
         * msg : 成功
         * data : [{"index_type":1,"open_up":1},{"index_type":2,"open_up":0},{"index_type":3,"open_up":0},{"index_type":4,"open_up":0},{"index_type":5,"open_up":0},{"index_type":6,"open_up":0},{"index_type":7,"open_up":0},{"index_type":8,"open_up":0}]
         */

        private int code;
        private String msg;
        private List<Data.DataBean> data;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public List<Data.DataBean> getData() {
            return data;
        }

        public void setData(List<Data.DataBean> data) {
            this.data = data;
        }

        public class DataBean {
            /**
             * index_type : 1
             * open_up : 1
             */

            private int index_type;
            private int open_up;

            public int getIndex_type() {
                return index_type;
            }

            public void setIndex_type(int index_type) {
                this.index_type = index_type;
            }

            public int getOpen_up() {
                return open_up;
            }

            public void setOpen_up(int open_up) {
                this.open_up = open_up;
            }
        }
    }
}
