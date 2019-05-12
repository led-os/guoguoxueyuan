package com.test720.grasshoppercollege.module.yuWen.yuYanPinYinShiZi;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseShouQuanXianActivity;
import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.HttpBean.YuWenShouYeData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.FormBody;

/**
 * Created by 水东流 on 2018/6/14.
 */

public abstract class BaseShouYeActivity extends BaseShouQuanXianActivity {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    MyBaseRecyclerAdapter adapter;


    public abstract int type();

    List<YuWenShouYeData.DataBean> list = new ArrayList<>();
    public String[] shareStr = new String[3];//分享数据保存

    @Override
    public String[] shareStr() {
        return shareStr;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.shu_xue_shou_ye;
    }

    @Override
    protected void initData() {

        adapter = new MyBaseRecyclerAdapter<YuWenShouYeData.DataBean>(list, this, R.layout.shu_xue_shou_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, YuWenShouYeData.DataBean item, int postion) {
                holder.setImageByUrl(R.id.pic, item.getPic());
                holder.setText(R.id.name, item.getName());
                TextView textView = holder.getView(R.id.name);
               /* Typeface fontFace = Typeface.createFromAsset(getAssets(),
                        "fonts/hkhbt.ttf");
                textView.setTypeface(fontFace);*/
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
                setItemIndex(position);
                //填充分享数据
                shareStr[0] = list.get(position).getTitle();
                shareStr[1] = list.get(position).getContent();
                if (type() == 5) {
                    shareStr[2] = HttpUntil.GetIntent().getShare() + 15;
                } else if (type() == 4) {
                    shareStr[2] = HttpUntil.GetIntent().getShare() + 14;
                } else if (type() == 3) {
                    shareStr[2] = HttpUntil.GetIntent().getShare() + 13;
                }
                re_id = list.get(position).getVideo_id();
                points = list.get(position).getPoints();

                boolean canSee = isCanSee(list.get(position).getPermissions(), list.get(position).isPay(), list.get(position).getOpen_up() + "");//标记是否可看
                if (canSee) {
                    adapterItemClick(list.get(position).getVideo_id(), list.get(position).getState(), list.get(position).getName());
                }
            }
        });
        recyclerView.setAdapter(adapter);
    }

    /*购买课程*/
    public abstract void buyCurr();

    public abstract void adapterItemClick(String id, String state, String title);

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
        return HttpUntil.GetIntent().ChinesePinYinindex();
    }

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            YuWenShouYeData data = new Gson().fromJson(str, YuWenShouYeData.class);
            list.clear();
            list.addAll(data.getData());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            YuWenShouYeData data = new Gson().fromJson(str, YuWenShouYeData.class);
            list.addAll(data.getData());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public FormBody.Builder formBuilder() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("type", type() + "");
        builder.add("uid", MyApplication.getmInstance().getUid());
        builder.add("p", page + "");
        builder.add("class", MyApplication.getmInstance().nianji);
        return builder;
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        super.GetHttpBackStr(str, what);
        if (what == 123 && codeIsOne(str) && getItemIndex() != -1) {
            list.get(getItemIndex()).setOpen_up(1);
            adapter.notifyDataSetChanged();
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