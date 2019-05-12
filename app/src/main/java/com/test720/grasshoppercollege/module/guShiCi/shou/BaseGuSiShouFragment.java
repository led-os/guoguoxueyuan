package com.test720.grasshoppercollege.module.guShiCi.shou;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseRecyclerViewFragment;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.guShiCi.GuSiListActivity;
import com.test720.grasshoppercollege.module.guShiCi.ShouYeData;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.lunBo.LunBoUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 水东流 on 2018/7/11.
 */

public abstract class BaseGuSiShouFragment extends BaseRecyclerViewFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.root)
    LinearLayout root;

    List<ShouYeData.DataBean.ListBean> list = new ArrayList<>();
    MyBaseRecyclerAdapter adapter;
    View head;
    ViewHolder viewHolder;

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
        return HttpUntil.GetIntent().ChineseAncientPoetryindex();
    }

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            ShouYeData data = new Gson().fromJson(str, ShouYeData.class);
            list.clear();
            list.addAll(data.getData().getList());
            adapter.notifyDataSetChanged();
            initSlide(data);
        }

    }

    /*初始化轮播*/
    boolean isFirst = true;
    boolean isClass = false;//是否是年紀刪選

    private void initSlide(ShouYeData data) {
        if (!isFirst) return;
        LunBoUntil lunBoUntil = new LunBoUntil(viewHolder.slider, data.getData().getBanner(), getActivity());
        lunBoUntil.start();
        isFirst = false;
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            ShouYeData data = new Gson().fromJson(str, ShouYeData.class);
            list.addAll(data.getData().getList());
            adapter.notifyDataSetChanged();
        }
    }


    @Override
    public void init() {
        head = LayoutInflater.from(getActivity()).inflate(R.layout.gu_si_shou_header, null);
        viewHolder = new ViewHolder(head);
        viewHolder.xiaoxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (MyApplication.getmInstance().getUid().equals("")) return;
                if (!MyApplication.getmInstance().getUid().equals("")) {
                    if (isClass) {
                        if (!TextUtils.isEmpty(viewHolder.edittext.getText())) {
                            Intent intent = new Intent(getActivity(), GuSiListActivity.class);
                            intent.putExtra("key", viewHolder.edittext.getText().toString());
                            jumpToActivity(intent, false);
                        }
                    } else {
                        ShuaXin();
                    }
                }
            }
        });
        adapter = new MyBaseRecyclerAdapter<ShouYeData.DataBean.ListBean>(list, getActivity(), R.layout.gu_si_shou_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, ShouYeData.DataBean.ListBean item, int postion) {
                holder.setText(R.id.text, item.getName());
                holder.setImageByUrl(R.id.img, item.getPic());
            }
        };
        adapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (MyApplication.getmInstance().getUid().equals("")) {
                    return;
                }
                Intent intent = new Intent(getActivity(), GuSiListActivity.class);
                intent.putExtra("cate_id", list.get(position).getCate_id());
                jumpToActivity(intent, false);
            }
        });
        adapter.setHeaderView(head);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setListener() {
        haveHead = true;
    }

    @Override
    public int setlayoutResID() {
        return R.layout.base_shua_xin;
    }

    static class ViewHolder {
        @BindView(R.id.slider)
        SliderLayout slider;
        @BindView(R.id.custom_indicator2)
        PagerIndicator customIndicator2;
        @BindView(R.id.edittext)
        EditText edittext;
        @BindView(R.id.xiaoxi)
        ImageView xiaoxi;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public ViewHolder getViewHolder() {
        return viewHolder;
    }

    public void setViewHolder(ViewHolder viewHolder) {
        this.viewHolder = viewHolder;
    }
}
