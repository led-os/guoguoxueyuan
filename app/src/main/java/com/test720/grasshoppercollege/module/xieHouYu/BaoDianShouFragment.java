package com.test720.grasshoppercollege.module.xieHouYu;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseRecyclerViewFragment;
import com.test720.grasshoppercollege.HttpBean.BaoDianShouData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.lunBo.Banner;
import com.test720.grasshoppercollege.untils.lunBo.LunBoUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 水东流 on 2018/5/16.
 */

public class BaoDianShouFragment extends BaseRecyclerViewFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.root)
    LinearLayout root;
    MyBaseRecyclerAdapter adapter;
    MyBaseRecyclerAdapter adapter1;//顶部头类别列表

    View header;
    List<BaoDianShouData.DataBean.NewBean> list = new ArrayList<>();
    List<BaoDianShouData.DataBean.CatetoryBean> typeList = new ArrayList<>();
    ViewHolder viewHolder;

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
        return HttpUntil.GetIntent().ChineseXieHouYudictionaryLib();
    }

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            BaoDianShouData dianShouData = new Gson().fromJson(str, BaoDianShouData.class);
            list.clear();
            /*列表信息*/
            list.addAll(dianShouData.getData().getNewX());
            /*添加类别信息*/
            typeList.clear();
            typeList.addAll(dianShouData.getData().getCatetory());
            /*添加轮播信息*/
            initLunBo(dianShouData.getData().getBanner());
            initAdapter();
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            BaoDianShouData dianShouData = new Gson().fromJson(str, BaoDianShouData.class);
            list.addAll(dianShouData.getData().getNewX());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public HttpParams httpParams() {
        HttpParams ht = new HttpParams();
        if (viewHolder != null && !TextUtils.isEmpty(viewHolder.edittext.getText())) {
            ht.put("key", viewHolder.edittext.getText().toString());
        }
        ht.put("p", page);
        return ht;
    }

    @Override
    public void init() {
        recyclerView.setBackgroundResource(R.color.baodian);
        header = LayoutInflater.from(getActivity()).inflate(R.layout.bao_dian_shou_header, null);
        viewHolder = new ViewHolder(header);
        if (MyApplication.getmInstance().getUid().equals("")) {
            viewHolder.edittext.setCursorVisible(false);
            viewHolder.edittext.setFocusable(false);
            viewHolder.edittext.setFocusableInTouchMode(false);
            viewHolder.edittext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyApplication.getmInstance().setLoginDialogIsShow(false);
                    MyApplication.getmInstance().getUid();
                }
            });
        }
        viewHolder.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        viewHolder.typeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpToActivity(BaoDianTypeActivity.class, false);
            }
        });
        viewHolder.xiaoxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (!MyApplication.getmInstance().getUid().equals("")) {
                    ShuaXin();
                }
            }
        });
    }

    /*初始化适配器*/
    private void initAdapter() {
        initType();
        adapter = new MyBaseRecyclerAdapter<BaoDianShouData.DataBean.NewBean>(list, getActivity(), R.layout.bao_dian_shou_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, final BaoDianShouData.DataBean.NewBean item, int postion) {
                holder.setText(R.id.text, item.getQuestion());
                holder.setText(R.id.right, "*****");
                final TextView textView = holder.getView(R.id.right);
                Drawable drawable = getResources().getDrawable(R.mipmap.biyan);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                textView.setCompoundDrawables(null, null, drawable, null);
                textView.setText("*****");
                holder.getView(R.id.right).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (textView.getText().toString().equals("*****")) {
                            Drawable drawable = getResources().getDrawable(R.mipmap.seeyan);
                            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                            textView.setCompoundDrawables(null, null, drawable, null);
                            textView.setText(item.getAnswer());
                        } else {
                            Drawable drawable = getResources().getDrawable(R.mipmap.biyan);
                            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                            textView.setCompoundDrawables(null, null, drawable, null);
                            textView.setText("*****");
                        }
                    }
                });
            }
        };
        adapter.setHeaderView(header);
        recyclerView.setAdapter(adapter);
    }

    boolean luBo = false;

    /**
     * 轮播
     *
     * @param banner
     */
    private void initLunBo(List<Banner> banner) {
        if (luBo) return;
        LunBoUntil lunBoUntil = new LunBoUntil(viewHolder.slider, banner, getActivity());
        lunBoUntil.start();
        luBo = true;
    }

    /*头部信息*/
    private void initType() {
        /*类别*/
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getActivity());
        linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        viewHolder.recyclerViewType.setLayoutManager(linearLayoutManager1);
        adapter1 = new MyBaseRecyclerAdapter<BaoDianShouData.DataBean.CatetoryBean>(typeList, getActivity(), R.layout.bao_dian_type_item) {

            @Override
            public void convert(BaseRecyclerHolder holder, BaoDianShouData.DataBean.CatetoryBean item, int postion) {
                holder.setText(R.id.text, item.getName());
                holder.setImageByUrl(R.id.img, item.getPic());
            }
        };
        adapter1.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), BaoDianActivity.class);
                intent.putExtra("lib_id", typeList.get(position).getLib_id());
                intent.putExtra("title", typeList.get(position).getName());
                jumpToActivity(intent, false);
            }
        });
        viewHolder.recyclerViewType.setAdapter(adapter1);
    }


    @Override
    public void setListener() {

    }

    @Override
    public int setlayoutResID() {
        haveLin = false;
        return R.layout.base_shua_xin;
    }


    static class ViewHolder {
        @BindView(R.id.back)
        ImageView back;
        @BindView(R.id.slider)
        SliderLayout slider;
        @BindView(R.id.custom_indicator2)
        PagerIndicator customIndicator2;
        @BindView(R.id.edittext)
        EditText edittext;
        @BindView(R.id.xiaoxi)
        ImageView xiaoxi;
        @BindView(R.id.type_more)
        TextView typeMore;
        @BindView(R.id.recyclerViewType)
        RecyclerView recyclerViewType;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}