package com.test720.grasshoppercollege.module.shuXue;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseShouQuanXianActivity;
import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.HttpBean.ShuXueShouYeData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.FormBody;

public abstract class BaseShuXueShouYeActivity extends BaseShouQuanXianActivity {
    String[] shareStr = new String[3];//分享数据保存

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    MyBaseRecyclerAdapter adapter;


    public abstract int type();

    List<ShuXueShouYeData.DataBean> list = new ArrayList<>();


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
        adapter = new MyBaseRecyclerAdapter<ShuXueShouYeData.DataBean>(list, this, R.layout.shu_xue_shou_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, ShuXueShouYeData.DataBean item, int postion) {
                holder.setImageByUrl(R.id.pic, item.getPic());

                TextView textView = holder.getView(R.id.name);
                textView.setText(item.getName());
               // Typeface fontFace = Typeface.createFromAsset(getAssets(), "fonts/hkhbt.ttf");
                //textView.setTypeface(fontFace);

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
                if (type() == 1) {
                    shareStr[2] = HttpUntil.GetIntent().getShare() + 12;
                } else {
                    shareStr[2] = HttpUntil.GetIntent().getShare() + 11;
                }
                re_id = list.get(position).getMath_id();
                points = list.get(position).getPoints();
                /*判断权限*/
                boolean canSee = isCanSee(list.get(position).getPermissions(), list.get(position).isPay(), list.get(position).getOpen_up() + "");
                if (canSee) {
                    adapterItemClick(list.get(position).getMath_id(),
                            list.get(position).getState(), list.get(position).getName());
                }
            }
        });
        recyclerView.setAdapter(adapter);
    }

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
        return HttpUntil.GetIntent().MathAnimationindex();
    }

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            ShuXueShouYeData data = new Gson().fromJson(str, ShuXueShouYeData.class);
            list.clear();
            list.addAll(data.getData());
            adapter.notifyDataSetChanged();

        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            ShuXueShouYeData data = new Gson().fromJson(str, ShuXueShouYeData.class);
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
        switch (what) {
            case 122:
            case 123:
                if (codeIsOne(str)) {
                    if (getItemClickIndex() != -1) {
                        list.get(getItemClickIndex()).setOpen_up(1);
                        adapter.notifyDataSetChanged();
                    }
                }
                break;
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
