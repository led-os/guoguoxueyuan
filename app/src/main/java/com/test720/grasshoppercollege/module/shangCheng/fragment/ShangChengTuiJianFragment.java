package com.test720.grasshoppercollege.module.shangCheng.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseRecyclerViewFragment;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.shangCheng.SearchDialog;
import com.test720.grasshoppercollege.module.shangCheng.ShangPinListActivity;
import com.test720.grasshoppercollege.module.shangCheng.ShangPinXiangQingActivity;
import com.test720.grasshoppercollege.module.shangCheng.bean.ShangChengShouYeData;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.lunBo.Banner;
import com.test720.grasshoppercollege.untils.lunBo.LunBoUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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
 * 作者：水东流 编于 2018/10/8
 */
public class ShangChengTuiJianFragment extends BaseRecyclerViewFragment {
    private List<Banner> banner = new ArrayList<>();
    private List<ShangChengShouYeData.DataBean.ListBean> list = new ArrayList<>();

    MyBaseRecyclerAdapter adapter;
    ViewHolder viewHolder;
    SearchDialog searchDialog;//热搜标签

    @Override
    public void setListener() {

    }

    @Override
    public int setlayoutResID() {
        return R.layout.base_shua_xin;
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
        return HttpUntil.GetIntent().getIP() + "small.php/mall/index";
    }

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            ShangChengShouYeData data = new Gson().fromJson(str, ShangChengShouYeData.class);
            //热搜
            searchDialog = new SearchDialog();
            searchDialog.setHot_search(data.getData().getHot_search());

            //轮播
            banner.clear();
            banner.addAll(data.getData().getBanner());
            initBanner();

            list.clear();
            list.addAll(data.getData().getList());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void gengDuo(String str) {

    }

    @Override
    public HttpParams httpParams() {
        return new HttpParams();
    }

    @Override
    public void init() {
        adapter = new MyBaseRecyclerAdapter<ShangChengShouYeData.DataBean.ListBean>(list, mContext, R.layout.shang_cheng_tui_jian_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, final ShangChengShouYeData.DataBean.ListBean item, int postion) {
                //类别背景
                holder.setImageByUrl(R.id.img, item.getCover());
                holder.getView(R.id.img).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), ShangPinListActivity.class);
                        intent.putExtra("cate_id", item.getCate_id());
                        jumpToActivity(intent, false);
                    }
                });

                RecyclerView recyclerView = holder.getView(R.id.recyclerView);
                LinearLayoutManager lm = new LinearLayoutManager(mContext);
                lm.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(lm);

                MyBaseRecyclerAdapter childAdapter = new MyBaseRecyclerAdapter<ShangChengShouYeData.DataBean.ListBean.GoodsBean>(item.getGoods(), mContext, R.layout.shang_ping_item) {
                    @Override
                    public void convert(BaseRecyclerHolder holder, ShangChengShouYeData.DataBean.ListBean.GoodsBean childitem, int postion) {
                        holder.setImageByUrl(R.id.pic, childitem.getCover());
                        holder.setText(R.id.name, childitem.getName());
                        holder.setText(R.id.sale_count, "已售" + childitem.getSale_count() + "件");
                        String s = getString(R.string.jinE) + childitem.getPrice();
                        holder.setText(R.id.price, s);
                    }
                };
                childAdapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent intent = new Intent(mContext, ShangPinXiangQingActivity.class);
                        intent.putExtra("gid", item.getGoods().get(position).getGid());
                        jumpToActivity(intent, false);
                    }
                });
                recyclerView.setAdapter(childAdapter);
            }
        };
        //头
        @SuppressLint("InflateParams") View head = LayoutInflater.from(mContext).inflate(R.layout.shang_cheng_tui_jian_head, null);
        viewHolder = new ViewHolder(head);
        viewHolder.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.finish();
            }
        });
        viewHolder.xiaoxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (searchDialog != null) searchDialog.show(getChildFragmentManager(), "dialog");
            }
        });
        viewHolder.edittext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (searchDialog != null) searchDialog.show(getChildFragmentManager(), "dialog");
            }
        });
        adapter.setHeaderView(head);
        recyclerView.setAdapter(adapter);
    }

    /*加载轮播*/
    boolean luBo = false;

    private void initBanner() {
        if (luBo) return;
        LunBoUntil lunBoUntil = new LunBoUntil(viewHolder.slider, banner, getActivity());
        lunBoUntil.start();
        luBo = true;
    }

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    static class ViewHolder {
        @BindView(R.id.back)
        ImageView back;
        @BindView(R.id.edittext)
        TextView edittext;
        @BindView(R.id.xiaoxi)
        ImageView xiaoxi;
        @BindView(R.id.slider)
        SliderLayout slider;
        @BindView(R.id.custom_indicator2)
        PagerIndicator customIndicator2;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
