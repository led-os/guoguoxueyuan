package com.test720.grasshoppercollege.module.gongLue.student.smallClass.fragment;

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
import com.test720.grasshoppercollege.module.gongLue.GongLueData;
import com.test720.grasshoppercollege.module.gongLue.student.bean.SmallDaGangListData;
import com.test720.grasshoppercollege.module.gongLue.student.smallClass.daGang.DaGangVideoActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.QuanXianUntil;

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
public class SmallFragmentClass extends BaseRecyclerViewFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    List<SmallDaGangListData.DataBean> list = new ArrayList<>();
    MyBaseRecyclerAdapter<SmallDaGangListData.DataBean> adapter;

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
        return HttpUntil.GetIntent().StrategymyMicroInfo();
    }

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            SmallDaGangListData daGangListData = new Gson().fromJson(str, SmallDaGangListData.class);
            list.clear();
            list.addAll(daGangListData.getData());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            SmallDaGangListData daGangListData = new Gson().fromJson(str, SmallDaGangListData.class);
            list.addAll(daGangListData.getData());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public HttpParams httpParams() {
        HttpParams httpParams = new HttpParams();
        assert getArguments() != null;
        httpParams.put("micro_id", getArguments().getString("micro_id"));
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        httpParams.put("p", page);
        return httpParams;
    }

    @Override
    public void init() {
        adapter = new MyBaseRecyclerAdapter<SmallDaGangListData.DataBean>(list, getActivity(), R.layout.ke_cheng_da_gang_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, SmallDaGangListData.DataBean item, int postion) {
                holder.setText(R.id.title, item.getName());
                holder.setText(R.id.content, item.getDesc());
                holder.setImageByUrl(R.id.pic, item.getPic());
                if (item.getOpen_up() == 1) {
                    holder.getView(R.id.suo).setVisibility(View.GONE);
                } else {
                    holder.getView(R.id.suo).setVisibility(View.VISIBLE);
                }
            }
        };
        adapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                final SmallDaGangListData.DataBean item = list.get(position);
                String[] strs = new String[]{item.getTitle(), item.getContent(), HttpUntil.GetIntent().getShare() + (GongLueData.getInstance().getModularType() + 10)};
                boolean can = QuanXianUntil.GetIntent(getActivity()).isCanSee(item.getPermissions(), item.isPay(),
                        item.getOpen_up() + "", item.getPoints(), strs, new QuanXianUntil.QuanXianClick() {
                            @Override
                            public void QuanbuyCurr() {

                            }

                            @Override
                            public void QuanbuyGuoGuo() {
                                if (item.getMicro_class_id() == null) return;
                                HttpParams formBuilder = new HttpParams();
                                formBuilder.put("uid", MyApplication.getmInstance().getUid());//请求参数一
                                formBuilder.put("curr_type", GongLueData.getInstance().getModularType());
                                formBuilder.put("type", "2");
                                formBuilder.put("points", item.getPoints());
                                formBuilder.put("re_id", item.getMicro_class_id());
                                post(HttpUntil.GetIntent().addShareSpendRecord(), formBuilder, 123, false);
                            }

                            @Override
                            public void QuanshareCurr() {
                                HttpParams formBuilder = new HttpParams();
                                formBuilder.put("uid", MyApplication.getmInstance().getUid());//请求参数一
                                formBuilder.put("curr_type", GongLueData.getInstance().getModularType());
                                formBuilder.put("type", "1");
                                formBuilder.put("re_id", item.getMicro_class_id());
                                post(HttpUntil.GetIntent().addShareSpendRecord(), formBuilder, 123, false);
                            }
                        });
                if (can) {
                    Intent intent = new Intent(getActivity(), DaGangVideoActivity.class);
                    intent.putExtra("micro_class_id", list.get(position).getMicro_class_id());
                    if (list.get(position).getState().equals("1")) {
                        intent.putExtra("h5", false);
                    } else {
                        intent.putExtra("h5", true);
                    }
                    intent.putExtra("name", list.get(position).getName());
                    jumpToActivity(intent, false);
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
