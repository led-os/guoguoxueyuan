package com.test720.grasshoppercollege.module.peiYin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.peiYin.bean.ShouData;
import com.test720.grasshoppercollege.module.peiYin.keNei.KeiNeiBookActivity;
import com.test720.grasshoppercollege.module.shangCheng.ShangChengActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;

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
 * 作者：水东流 编于 2018/8/29
 */

public class PeiYinShouActivity extends BaseToolActivity {

    private List<ShouData.DataBean.BannerBean> banner = new ArrayList<>();
    private List<ShouData.DataBean.TodayBean> today = new ArrayList<>();
    private List<ShouData.DataBean.EveryDayBean> every_day = new ArrayList<>();
    private List<ShouData.DataBean.AnimationBean> animation = new ArrayList<>();
    private List<ShouData.DataBean.EnlightenmentBean> enlightenment = new ArrayList<>();
    private List<ShouData.DataBean.InClassBean> in_class = new ArrayList<>();
    private List<ShouData.DataBean.BestDubbingBean> best_dubbing = new ArrayList<>();

    MyBaseRecyclerAdapter jinRiAdapter;
    MyBaseRecyclerAdapter meiRiAdapter;
    MyBaseRecyclerAdapter dongHuaAdapter;
    MyBaseRecyclerAdapter qiMengAdapter;
    MyBaseRecyclerAdapter keNeiAdapter;
    MyBaseRecyclerAdapter zuiXinAdapter;

    @Override
    protected String setTitle() {
        return "配音";
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_pei_yin_shou;
    }

    @Override
    protected void initData() {

        jinRiRecycler.setHasFixedSize(true);
        jinRiRecycler.setNestedScrollingEnabled(false);

        meiRiRecycler.setHasFixedSize(true);
        meiRiRecycler.setNestedScrollingEnabled(false);

        dongHuaRecyclerView.setHasFixedSize(true);
        dongHuaRecyclerView.setNestedScrollingEnabled(false);

        qiMengRecyclerView.setHasFixedSize(true);
        qiMengRecyclerView.setNestedScrollingEnabled(false);

        keNeiRecyclerView.setHasFixedSize(true);
        keNeiRecyclerView.setNestedScrollingEnabled(false);

        zuiXinRecyclerView.setHasFixedSize(true);
        zuiXinRecyclerView.setNestedScrollingEnabled(false);

        initJinRi();
        initMeiRi();
        initDongHua();
        initQiMeng();
        initKenNei();
        initZuiXin();
        FormBody.Builder formBuilder = new FormBody.Builder();
//        formBuilder.add("type",)
        Post(HttpUntil.GetIntent().Dubbingindex(), formBuilder, 1);
    }

    /**
     * 轮播
     */
    private void initBanner() {
        HashMap<String, String> urlMaps = new HashMap<>();//轮播
        /*添加轮播信息*/
        for (int i = 0; i < banner.size(); i++) {
            urlMaps.put(banner.get(i).getName(), banner.get(i).getPic());
        }
        for (String name : urlMaps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(mcontext);
            textSliderView
                    .description(name)//描述
                    .image(urlMaps.get(name))//image方法可以传入图片url、资源id、File
                    .setScaleType(BaseSliderView.ScaleType.Fit);//图片缩放类型
//                    .setOnSliderClickListener(onSliderClickListener);//图片点击
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle().putString("extra", name);//传入参数
            slider.addSlider(textSliderView);//添加一个滑动页面
        }
        slider.setPresetTransformer(SliderLayout.Transformer.Accordion);//滑动动画
        slider.setCustomIndicator(customIndicator2);//自定义指示器
        slider.setCustomAnimation(new DescriptionAnimation());//设置图片描述显示动画
        slider.setDuration(4000);//设置滚动时间，也是计时器时间
    }

    private void initJinRi() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mcontext, 2) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        jinRiRecycler.setLayoutManager(gridLayoutManager);
        jinRiAdapter = new MyBaseRecyclerAdapter<ShouData.DataBean.TodayBean>(today, mcontext, R.layout.nian_ji_lie_biao_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, ShouData.DataBean.TodayBean item, int postion) {
                holder.setImageByUrl(R.id.img, item.getPic());
                holder.setText(R.id.text, item.getVideo_name());
            }
        };
        jinRiAdapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(mcontext, PeiYinXiangQingActivity.class);
                intent.putExtra("con_id", today.get(position).getCon_id());
                jumpToActivity(intent, false);
            }
        });
        jinRiRecycler.setAdapter(jinRiAdapter);
    }

    private void initMeiRi() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mcontext, 2) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        meiRiRecycler.setLayoutManager(gridLayoutManager);
        meiRiAdapter = new MyBaseRecyclerAdapter<ShouData.DataBean.EveryDayBean>(every_day, mcontext, R.layout.nian_ji_lie_biao_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, ShouData.DataBean.EveryDayBean item, int postion) {
                holder.setImageByUrl(R.id.img, item.getPic());
                holder.setText(R.id.text, item.getVideo_name());
            }
        };
        meiRiAdapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(mcontext, PeiYinXiangQingActivity.class);
                intent.putExtra("con_id", every_day.get(position).getCon_id());
                jumpToActivity(intent, false);
            }
        });
        meiRiRecycler.setAdapter(meiRiAdapter);
    }

    private void initDongHua() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mcontext, 2) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        dongHuaRecyclerView.setLayoutManager(gridLayoutManager);
        dongHuaAdapter = new MyBaseRecyclerAdapter<ShouData.DataBean.AnimationBean>(animation, mcontext, R.layout.nian_ji_lie_biao_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, ShouData.DataBean.AnimationBean item, int postion) {
                holder.setImageByUrl(R.id.img, item.getPic());
                holder.setText(R.id.text, item.getVideo_name());
            }
        };
        dongHuaAdapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(mcontext, PeiYinXiangQingActivity.class);
                intent.putExtra("con_id", animation.get(position).getCon_id());
                jumpToActivity(intent, false);
            }
        });
        dongHuaRecyclerView.setAdapter(dongHuaAdapter);
    }

    private void initQiMeng() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mcontext, 2) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        qiMengRecyclerView.setLayoutManager(gridLayoutManager);
        qiMengAdapter = new MyBaseRecyclerAdapter<ShouData.DataBean.EnlightenmentBean>(enlightenment, mcontext, R.layout.nian_ji_lie_biao_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, ShouData.DataBean.EnlightenmentBean item, int postion) {
                holder.setImageByUrl(R.id.img, item.getPic());
                holder.setText(R.id.text, item.getVideo_name());
            }
        };
        qiMengAdapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(mcontext, PeiYinXiangQingActivity.class);
                intent.putExtra("con_id", enlightenment.get(position).getCon_id());
                jumpToActivity(intent, false);
            }
        });
        qiMengRecyclerView.setAdapter(qiMengAdapter);
    }

    private void initKenNei() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mcontext, 2) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        keNeiRecyclerView.setLayoutManager(gridLayoutManager);
        keNeiAdapter = new MyBaseRecyclerAdapter<ShouData.DataBean.InClassBean>(in_class, mcontext, R.layout.nian_ji_lie_biao_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, ShouData.DataBean.InClassBean item, int postion) {
                holder.setImageByUrl(R.id.img, item.getPic());
                holder.setText(R.id.text, item.getVideo_name());
            }
        };
        keNeiAdapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(mcontext, PeiYinXiangQingActivity.class);
                intent.putExtra("con_id", in_class.get(position).getCon_id());
                jumpToActivity(intent, false);
            }
        });
        keNeiRecyclerView.setAdapter(keNeiAdapter);
    }

    private void initZuiXin() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mcontext, 2) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        zuiXinRecyclerView.setLayoutManager(gridLayoutManager);
        zuiXinAdapter = new MyBaseRecyclerAdapter<ShouData.DataBean.BestDubbingBean>(best_dubbing, mcontext, R.layout.nian_ji_lie_biao_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, ShouData.DataBean.BestDubbingBean item, int postion) {
                holder.setImageByUrl(R.id.img, item.getPic());
                holder.setText(R.id.text, item.getVideo_name());
            }
        };
        zuiXinAdapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(mcontext, PeiYinXiangQingActivity.class);
                intent.putExtra("con_id", best_dubbing.get(position).getCon_id());
                jumpToActivity(intent, false);
            }
        });
        zuiXinRecyclerView.setAdapter(zuiXinAdapter);
    }


    @Override
    public void GetHttpBackStr(String str, int what) {
        switch (what) {
            case 1:
                if (codeIsOne(str, false)) {
                    ShouData shouData = new Gson().fromJson(str, ShouData.class);

                    /*轮播*/
                    banner.clear();
                    banner.addAll(shouData.getData().getBanner());
                    initBanner();

                    today.clear();
                    today.addAll(shouData.getData().getToday());
                    jinRiAdapter.notifyDataSetChanged();

                    every_day.clear();
                    every_day.addAll(shouData.getData().getEvery_day());
                    meiRiAdapter.notifyDataSetChanged();

                    Glide.with(mcontext).load(shouData.getData().getImg().get(0)).into(dongHuaImg);
                    animation.clear();
                    animation.addAll(shouData.getData().getAnimation());
                    dongHuaAdapter.notifyDataSetChanged();

                    enlightenment.clear();
                    enlightenment.addAll(shouData.getData().getEnlightenment());
                    qiMengAdapter.notifyDataSetChanged();

                    in_class.clear();
                    in_class.addAll(shouData.getData().getIn_class());
                    keNeiAdapter.notifyDataSetChanged();

                    best_dubbing.clear();
                    best_dubbing.addAll(shouData.getData().getBest_dubbing());
                    zuiXinAdapter.notifyDataSetChanged();


                }
                break;
        }

    }


    @OnClick({R.id.shangCheng, R.id.zuoPin, R.id.shouChang, R.id.dongHua, R.id.keNei, R.id.jin_ri_more, R.id.jin_ri_updata, R.id.mei_ri_more, R.id.mei_ri_updata, R.id.dong_hua_more, R.id.dong_hua_updata, R.id.qi_meng_more, R.id.qi_meng_updata, R.id.ke_nei_more, R.id.ke_nei_updata, R.id.zui_xin_more, R.id.zui_xin_updata})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shangCheng:
                jumpToActivity(ShangChengActivity.class, true);
                break;
            case R.id.zuoPin:
                break;
            case R.id.shouChang:
                break;
            case R.id.dongHua:
                MyApplication.getmInstance().setModuleType(55);
                jumpToActivity(PeiYinBookActivity.class, false);
                break;
            case R.id.keNei:
                MyApplication.getmInstance().setModuleType(54);
                jumpToActivity(KeiNeiBookActivity.class, false);
                break;
            case R.id.jin_ri_more:
                break;
            case R.id.jin_ri_updata:
                break;
            case R.id.mei_ri_more:
                break;
            case R.id.mei_ri_updata:
                break;
            case R.id.dong_hua_more:
                break;
            case R.id.dong_hua_updata:
                break;
            case R.id.qi_meng_more:
                break;
            case R.id.qi_meng_updata:
                break;
            case R.id.ke_nei_more:
                break;
            case R.id.ke_nei_updata:
                break;
            case R.id.zui_xin_more:
                break;
            case R.id.zui_xin_updata:
                break;
        }
    }

    @BindView(R.id.slider)
    SliderLayout slider;
    @BindView(R.id.custom_indicator2)
    PagerIndicator customIndicator2;
    @BindView(R.id.shangCheng)
    LinearLayout shangCheng;
    @BindView(R.id.zuoPin)
    LinearLayout zuoPin;
    @BindView(R.id.shouChang)
    LinearLayout shouChang;
    @BindView(R.id.dongHua)
    LinearLayout dongHua;
    @BindView(R.id.keNei)
    LinearLayout keNei;
    @BindView(R.id.jin_ri_recycler)
    RecyclerView jinRiRecycler;
    @BindView(R.id.mei_ri_recycler)
    RecyclerView meiRiRecycler;
    @BindView(R.id.jin_ri_more)
    TextView jinRiMore;
    @BindView(R.id.jin_ri_updata)
    TextView jinRiUpdata;
    @BindView(R.id.dong_hua_img)
    ImageView dongHuaImg;
    @BindView(R.id.dong_hua_recyclerView)
    RecyclerView dongHuaRecyclerView;
    @BindView(R.id.dong_hua_more)
    TextView dongHuaMore;
    @BindView(R.id.dong_hua_updata)
    TextView dongHuaUpdata;
    @BindView(R.id.qi_meng_recyclerView)
    RecyclerView qiMengRecyclerView;
    @BindView(R.id.qi_meng_more)
    TextView qiMengMore;
    @BindView(R.id.qi_meng_updata)
    TextView qiMengUpdata;
    @BindView(R.id.ke_nei_recyclerView)
    RecyclerView keNeiRecyclerView;
    @BindView(R.id.ke_nei_more)
    TextView keNeiMore;
    @BindView(R.id.ke_nei_updata)
    TextView keNeiUpdata;
    @BindView(R.id.zui_xin_recyclerView)
    RecyclerView zuiXinRecyclerView;
    @BindView(R.id.zui_xin_more)
    TextView zuiXinMore;
    @BindView(R.id.zui_xin_updata)
    TextView zuiXinUpdata;
}
