package com.test720.grasshoppercollege.module.youEr.pinYinDongHua;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseShouQuanXianActivity;
import com.test720.grasshoppercollege.BuyClassActivity;
import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.HttpBean.YouErShiZiShouYeData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.FormBody;

public class ShiZiShouActivity extends BaseShouQuanXianActivity {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    List<YouErShiZiShouYeData.DataBean> list = new ArrayList();

    MyBaseRecyclerAdapter adapter;
    public String[] shareStr = new String[3];//分享数据保存

    @Override

    protected String setTitle() {
        return "拼音动画";
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.shu_xue_shou_ye;
    }

    @Override
    protected void initData() {

        adapter = new MyBaseRecyclerAdapter<YouErShiZiShouYeData.DataBean>(list, this, R.layout.shu_xue_shou_item) {
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
                setItemClickIndex(position);
                //填充分享数据
                shareStr[0] = list.get(position).getTitle();
                shareStr[1] = list.get(position).getContent();
                shareStr[2] = HttpUntil.GetIntent().getShare() + 45;
                re_id = list.get(position).getChild_id();
                points = list.get(position).getPoints();
                boolean canSee = isCanSee(list.get(position).getPermissions(), list.get(position).isPay(), list.get(position).getOpen_up() + "");//标记是否可看
                if (canSee) {
                    Intent in = new Intent(mcontext, ShiZiDongHuaVideoActivity.class);
                    in.putExtra("title", list.get(position).getName());
                    in.putExtra("child_id", list.get(position).getChild_id());
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

    @Override
    public void buyCurr() {
        Intent intent = new Intent(mcontext, BuyClassActivity.class);
        intent.putExtra("curr_type", 36);
        intent.putExtra("type", "4");
        jumpToActivity(intent, false);
    }

    @Override
    public void buyGuoGuo() {
        if (re_id == null) return;
        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("uid", MyApplication.getmInstance().getUid());//请求参数一
        formBuilder.add("curr_type", "36");
        formBuilder.add("type", "2");
        formBuilder.add("points", points);
        formBuilder.add("re_id", re_id);
        Post(HttpUntil.GetIntent().addShareSpendRecord(), formBuilder, 123, false);
    }

    @Override
    public void shareCurr() {
        if (points == null) return;
        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("uid", MyApplication.getmInstance().getUid());//请求参数一
        formBuilder.add("curr_type", "36");
        formBuilder.add("type", "1");
        formBuilder.add("re_id", re_id);
        Post(HttpUntil.GetIntent().addShareSpendRecord(), formBuilder, 123, false);
    }

    @Override
    public String[] shareStr() {
        return shareStr;
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
    public FormBody.Builder formBuilder() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("p", page + "");
        builder.add("type", 36 + "");
        builder.add("parent_id", 0 + "");
        builder.add("uid", MyApplication.getmInstance().getUid() + "");
        return builder;
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        super.GetHttpBackStr(str, what);
        if (what == 123 && codeIsOne(str)) {
            if (getItemClickIndex() != -1) {
                list.get(getItemClickIndex()).setOpen_up(1);
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onEvent(CurrencyEvent currencyEvent) {
        super.onEvent(currencyEvent);
        if (currencyEvent.getWhat() == CurrencyEvent.UPDATEFRAGMENT) {
            ShuaXin();
        }
    }

}