package com.test720.grasshoppercollege.module.youEr.erGeShiJie;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseRecyclerViewFragment;
import com.test720.grasshoppercollege.BuyClassActivity;
import com.test720.grasshoppercollege.HttpBean.YouErShiZiShouYeData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.QuanXianUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 水东流 on 2018/8/1.
 */

public class ErGeShiJieShouFragment extends BaseRecyclerViewFragment {

    public void buyCurr() {
        Intent intent = new Intent(getActivity(), BuyClassActivity.class);
        intent.putExtra("curr_type", getType());
        intent.putExtra("type", "4");
        jumpToActivity(intent, false);
    }

    public void buyGuoGuo() {
        if (re_id == null) return;
        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        httpParams.put("curr_type", getType());
        httpParams.put("type", 2);
        httpParams.put("points", points);
        httpParams.put("re_id", re_id);
        post(HttpUntil.GetIntent().addShareSpendRecord(), httpParams, 123, false);
    }

    public void shareCurr() {
        if (re_id == null) return;
        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        httpParams.put("curr_type", getType());
        httpParams.put("type", 1);
        httpParams.put("re_id", re_id);
        post(HttpUntil.GetIntent().addShareSpendRecord(), httpParams, 123, false);
    }

    @Override
    public void getSuccess(String str, int what) {
        super.getSuccess(str, what);
        if (codeIsOne(str, false) && what == 123 && getItemIndexCount() != -1) {
            list.get(getItemIndexCount()).setOpen_up(1);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setListener() {

    }

    @Override
    public int setlayoutResID() {
        return R.layout.base_shua_xin;
    }

    @Override
    public int setCount() {
        return 2;
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
        return HttpUntil.GetIntent().ChildAnimationindex();
    }

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            YouErShiZiShouYeData data = new Gson().fromJson(str, YouErShiZiShouYeData.class);
            list.clear();
            list.addAll(data.getData());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            YouErShiZiShouYeData data = new Gson().fromJson(str, YouErShiZiShouYeData.class);
            list.addAll(data.getData());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public HttpParams httpParams() {
        setType(getArguments().getInt("type"));
        HttpParams builder = new HttpParams();
        builder.put("p", page + "");
        builder.put("type", getType() + "");
        builder.put("parent_id", 0 + "");
        builder.put("uid", MyApplication.getmInstance().getUid() + "");
        return builder;
    }

    @Override
    public void init() {
        adapter = new MyBaseRecyclerAdapter<YouErShiZiShouYeData.DataBean>(list, getActivity(), R.layout.shu_xue_shou_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, YouErShiZiShouYeData.DataBean item, int postion) {
                holder.setImageByUrl(R.id.pic, item.getPic());
                holder.setText(R.id.name, item.getName());
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
                //填充分享数据
                setItemIndexCount(position);
                shareStr[0] = list.get(position).getTitle();
                shareStr[1] = list.get(position).getContent();
                shareStr[2] = HttpUntil.GetIntent().getShare() + 45;
                re_id = list.get(position).getChild_id();
                points = list.get(position).getPoints();
                boolean canSee = QuanXianUntil.GetIntent(getActivity()).isCanSee(list.get(position).getPermissions(), list.get(position).isPay(), list.get(position).getOpen_up() + "", list.get(position).getPoints(), shareStr, new QuanXianUntil.QuanXianClick() {
                    @Override
                    public void QuanbuyCurr() {
                        buyCurr();
                    }

                    @Override
                    public void QuanbuyGuoGuo() {
                        buyGuoGuo();
                    }

                    @Override
                    public void QuanshareCurr() {
                        shareCurr();
                    }
                });//标记是否可看
                if (canSee) {
                    Intent in = new Intent(getActivity(), VideoActivity.class);
                    in.putExtra("child_id", list.get(position).getChild_id());
                    in.putExtra("type", getType());
                    in.putExtra("title", list.get(position).getName());
                    String state = list.get(position).getState();
                    if (state.equals("1")) {
                        in.putExtra("h5", false);
                    } else {
                        in.putExtra("h5", true);
                    }
                    jumpToActivity(in, false);
                }
            }


        });
        recyclerView.setAdapter(adapter);
    }


    List<YouErShiZiShouYeData.DataBean> list = new ArrayList();
    MyBaseRecyclerAdapter adapter;
    public String[] shareStr = new String[3];//分享数据保存
    public String re_id;//果果豆购买，或者分享积分所需
    public String points;//购买所需蝈蝈豆
    int type;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


}
