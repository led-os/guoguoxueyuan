package com.test720.grasshoppercollege;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.bean.BaseNianJiLiebiao;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import okhttp3.FormBody;

public abstract class PublickNianJiActivity extends BaseRecyclerViewActivity {


    @BindView(R.id.slider)
    SliderLayout mDemoSlider;
    @BindView(R.id.custom_indicator2)
    PagerIndicator customIndicator2;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    private List<BaseNianJiLiebiao.DataBean.ListBean> list = new ArrayList<>();
    public MyBaseRecyclerAdapter<BaseNianJiLiebiao.DataBean.ListBean> adapter;

    public abstract MyBaseRecyclerAdapter.OnItemClickListener setItemClick();

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_publick_nian_ji;
    }

    @Override
    protected void initData() {
        setNoGengDuo(true);//不支持加载更多

        HashMap<String, String> urlMaps = new HashMap<>();
        urlMaps.put("蝈蝈点读", "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
        urlMaps.put("蝈蝈好用", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
        urlMaps.put("使用蝈蝈学习英语", "http://cdn3.nflximg.net/images/3093/2043093.jpg");

        for (String name : urlMaps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(this);
            textSliderView
                    .description(name)//描述
                    .image(urlMaps.get(name))//image方法可以传入图片url、资源id、File
                    .setScaleType(BaseSliderView.ScaleType.Fit);//图片缩放类型
//                    .setOnSliderClickListener(onSliderClickListener);//图片点击
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle().putString("extra", name);//传入参数
            mDemoSlider.addSlider(textSliderView);//添加一个滑动页面
        }

        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);//滑动动画
//        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);//默认指示器样式
        mDemoSlider.setCustomIndicator(customIndicator2);//自定义指示器
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());//设置图片描述显示动画
        mDemoSlider.setDuration(4000);//设置滚动时间，也是计时器时间
        mDemoSlider.addOnPageChangeListener(onPageChangeListener);

        adapter = new MyBaseRecyclerAdapter<BaseNianJiLiebiao.DataBean.ListBean>(list, this, R.layout.nian_ji_lie_biao_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, BaseNianJiLiebiao.DataBean.ListBean item, int postion) {
                holder.setText(R.id.text, item.getClassX());
                holder.setImageByUrl(R.id.img, HttpUntil.GetIntent().getIP() + item.getPic());
            }
        };
        //点击事件
        adapter.setOnItemClickListener(setItemClick());
        recyclerView.setAdapter(adapter);


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
        return HttpUntil.GetIntent().getClassListUrl();
    }

    @Override
    public void shuaXin(String str) {
        BaseNianJiLiebiao data = new Gson().fromJson(str, BaseNianJiLiebiao.class);
        if (data.getCode() == 1) {
            list.clear();
            list.addAll(data.getData().getList());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void gengDuo(String str) {
        BaseNianJiLiebiao data = new Gson().fromJson(str, BaseNianJiLiebiao.class);
        if (data.getCode() == 1) {
            list.addAll(data.getData().getList());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public FormBody.Builder formBuilder() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("book_type", "课本点读");
        return builder;
    }

    //页面改变监听
    private ViewPagerEx.OnPageChangeListener onPageChangeListener = new ViewPagerEx.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            Log.d("ansen", "Page Changed: " + position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };
}
