package com.test720.grasshoppercollege.module.gongLue;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.daimajia.slider.library.SliderLayout;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseFragment;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.gongLue.huoDong.ActivityList;
import com.test720.grasshoppercollege.module.gongLue.huoDong.ActivityXiangQing;
import com.test720.grasshoppercollege.module.gongLue.jiaZhang.bean.JiaZhangShouData;
import com.test720.grasshoppercollege.module.gongLue.jiaZhang.childrenEdu.EduListActivity;
import com.test720.grasshoppercollege.module.gongLue.jiaZhang.childrenEdu.EduVideoActivity;
import com.test720.grasshoppercollege.module.gongLue.jiaZhang.myActivity.MyActivityList;
import com.test720.grasshoppercollege.module.gongLue.jiaZhang.myCollection.MyCollectionActivity;
import com.test720.grasshoppercollege.module.gongLue.jiaZhang.myWenDa.MyWenDaActivity;
import com.test720.grasshoppercollege.module.gongLue.jiaZhang.myYuYue.MyYuYueActivity;
import com.test720.grasshoppercollege.module.gongLue.jiaZhang.touTiao.TouTiaoActivity;
import com.test720.grasshoppercollege.module.gongLue.jiaZhang.touTiao.TouTiaoListActivity;
import com.test720.grasshoppercollege.module.gongLue.student.myShiTing.ShiTingListActivity;
import com.test720.grasshoppercollege.module.gongLue.student.mySmallClass.SmallZhuanJiActivity;
import com.test720.grasshoppercollege.module.gongLue.student.shiTing.ShiTingKeActivity;
import com.test720.grasshoppercollege.module.gongLue.student.shiTing.ShiTingXiangQingActivity;
import com.test720.grasshoppercollege.module.gongLue.student.smallClass.SmallClassXiangQingActivity;
import com.test720.grasshoppercollege.module.gongLue.student.smallClass.WeiXueTangActivity;
import com.test720.grasshoppercollege.module.gongLue.teacher.pinTaiZiXun.yuYue.YuYueDingDanXiangQingActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.MyGsonUntil;
import com.test720.grasshoppercollege.untils.lunBo.Banner;
import com.test720.grasshoppercollege.untils.lunBo.LunBoUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 水东流 on 2018/5/7.
 */

public class JIaZhangFragment extends BaseFragment {

    private List<Banner> banner = new ArrayList<>();//轮播
    private List<JiaZhangShouData.DataBean.ConsultingBean> consulting = new ArrayList<>();//头条
    private List<JiaZhangShouData.DataBean.MethodsBean> methods = new ArrayList<>();//教子有方
    private List<JiaZhangShouData.DataBean.MicroBean> micro = new ArrayList<>();//微课堂
    private List<JiaZhangShouData.DataBean.TryListeningBean> try_listening = new ArrayList<>();//试听课
    private List<JiaZhangShouData.DataBean.ActivityZoneBean> activity_zone = new ArrayList<>();//活动
    private List<JiaZhangShouData.DataBean.Curriculum> curriculum = new ArrayList<>();

    MyBaseRecyclerAdapter touTiaoAdapter;
    MyBaseRecyclerAdapter jiaoZiAdapter;
    MyBaseRecyclerAdapter xinLianXinAdapter;
    MyBaseRecyclerAdapter shiTingAdapter;
    MyBaseRecyclerAdapter keChenBiaoAdapter;


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        banner .clear();
        consulting.clear();
        methods .clear();
        micro .clear();
        try_listening .clear();
        activity_zone .clear();
        curriculum .clear();
    }

    @Override
    public void initData() {
        /*刷新*/
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
                swipeRefresh.setRefreshing(false);
            }
        });

        touTiaoRecyclerView.setHasFixedSize(true);
        touTiaoRecyclerView.setNestedScrollingEnabled(false);

        jiaoZiYouFang.setHasFixedSize(true);
        jiaoZiYouFang.setNestedScrollingEnabled(false);

        xinLiannXin.setHasFixedSize(true);
        xinLiannXin.setNestedScrollingEnabled(false);

        shiTingViewPager.setHasFixedSize(true);
        shiTingViewPager.setNestedScrollingEnabled(false);

        keChenBiaoRecy.setHasFixedSize(true);
        keChenBiaoRecy.setNestedScrollingEnabled(false);

        shiTing();
        initGongGao();
        jiaoZiYoufang();
        toutiao();
        xinLianXinAdapter();
        getData();
    }

    /**
     * 获取数据
     */
    private void getData() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        post(HttpUntil.GetIntent().StrategyparentView(), httpParams, 1, false);
    }

    /**
     * 活动
     */
    public void huoDong() {
        if (activity_zone.size() == 0) {
            huoDongImg.setVisibility(View.GONE);

            huoDongTitle.setVisibility(View.GONE);
        } else {
            huoDongImg.setVisibility(View.VISIBLE);
            huoDongTitle.setVisibility(View.VISIBLE);
        }
        Glide.with(getActivity()).load(activity_zone.get(0).getCover()).into(huoDongImg);
        huoDongTitle.setText(activity_zone.get(0).getName());
        huoDongImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ActivityXiangQing.class);
                intent.putExtra("activity_id", activity_zone.get(0).getActivity_id());
                jumpToActivity(intent, false);
            }
        });
    }

    private void xinLianXinAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        //设置布局管理器
        xinLiannXin.setLayoutManager(layoutManager);
        //设置分隔线
        xinLiannXin.addItemDecoration(new DividerItemDecoration(mContext, 1));
        /*R.layout.gong_lue_book_item*/
        xinLianXinAdapter = new MyBaseRecyclerAdapter<JiaZhangShouData.DataBean.MicroBean>(micro, getActivity(), R.layout.gong_lue_online_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, JiaZhangShouData.DataBean.MicroBean item, int postion) {
              /*  holder.setText(R.id.title, item.getName());
                holder.setText(R.id.content, item.getAims());
                holder.setText(R.id.number, item.getSubscribe_count() + "人订阅");
                RatingBar ratingBar = holder.getView(R.id.ratingBar);
                ratingBar.setRating(Float.parseFloat(item.getLevel()));
                holder.setImageByUrl(R.id.book, item.getPic());*/

                holder.setText(R.id.title, item.getName());
                holder.setText(R.id.content, item.getAims());
                holder.setText(R.id.price, "￥" + item.getPrice());
                holder.setText(R.id.priceBeiZhu, "");
                holder.setText(R.id.vipPrice, "￥" + item.getVip_price());
                holder.setText(R.id.number, item.getSubscribe_count() + "人报名");
                holder.setImageByUrl(R.id.book, item.getPic());
            }

        };
        xinLianXinAdapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), SmallClassXiangQingActivity.class);
                GongLueData.getInstance().setModularType(GongLueData.PARENT);
                intent.putExtra("micro_id", micro.get(position).getMicro_id());
                intent.putExtra("name", micro
                        .get(position).getName());
                jumpToActivity(intent, false);
            }
        });
        xinLiannXin.setAdapter(xinLianXinAdapter);
    }

    private void toutiao() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        //设置布局管理器
        touTiaoRecyclerView.setLayoutManager(layoutManager);
        //设置分隔线
        touTiaoRecyclerView.addItemDecoration(new DividerItemDecoration(mContext, 1));
        touTiaoAdapter = new MyBaseRecyclerAdapter<JiaZhangShouData.DataBean.ConsultingBean>(consulting, getActivity(), R.layout.gong_lue_jiao_yu_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, JiaZhangShouData.DataBean.ConsultingBean item, int postion) {
                holder.setImageByUrl(R.id.book, item.getCover());
                holder.setText(R.id.title, item.getTitle());
                holder.setText(R.id.number, item.getTime());
            }
        };
        touTiaoAdapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent in = new Intent(mContext, TouTiaoActivity.class);
                in.putExtra("headline_id", consulting.get(position).getHeadline_id());
                in.putExtra("name", consulting.get(position).getTitle());
                jumpToActivity(in, false);
            }
        });
        touTiaoRecyclerView.setAdapter(touTiaoAdapter);
    }

    private void jiaoZiYoufang() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        //设置布局管理器
        jiaoZiYouFang.setLayoutManager(layoutManager);
        //设置分隔线
        jiaoZiYouFang.addItemDecoration(new DividerItemDecoration(mContext, 1));
        jiaoZiAdapter = new MyBaseRecyclerAdapter<JiaZhangShouData.DataBean.MethodsBean>(methods, getActivity(), R.layout.gong_lue_jiao_yu_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, JiaZhangShouData.DataBean.MethodsBean item, int postion) {
                holder.setImageByUrl(R.id.book, item.getCover());
                holder.setText(R.id.title, item.getName());
                holder.setText(R.id.number, item.getTime());
            }
        };
        jiaoZiAdapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent in = new Intent(mContext, EduVideoActivity.class);
                in.putExtra("title", methods.get(position).getName());
                in.putExtra("id", methods.get(position).getJiaozi_id());
                jumpToActivity(in, false);
            }
        });
        jiaoZiYouFang.setAdapter(jiaoZiAdapter);
    }

    private void shiTing() {
        shiTingViewPager.setLayoutManager(new LinearLayoutManager(getActivity()));
        /*R.layout.gong_lu_shi_ting_item*/
        shiTingAdapter = new MyBaseRecyclerAdapter<JiaZhangShouData.DataBean.TryListeningBean>(try_listening, getActivity(), R.layout.gong_lue_online_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, JiaZhangShouData.DataBean.TryListeningBean item, int postion) {
                /*holder.setText(R.id.title, item.getName());
                holder.setText(R.id.content, "");
                holder.setText(R.id.price, "￥" + item.getPrice());
                holder.setText(R.id.number, item.getSubscribe_count() + "人订阅");
                RatingBar ratingBar = holder.getView(R.id.ratingBar);
                ratingBar.setRating(Float.parseFloat("2"));
                holder.setImageByUrl(R.id.book, item.getPic());*/

                holder.setText(R.id.title, item.getName());
                holder.setText(R.id.content, item.getDesc());
                holder.setText(R.id.price, "￥" + item.getPrice());
                holder.setText(R.id.priceBeiZhu, "");

                holder.setText(R.id.vipPrice, "￥" + item.getVip_price());
                holder.setText(R.id.number, item.getSubscribe_count() + "人报名");
                holder.setImageByUrl(R.id.book, item.getPic());
            }
        };
        shiTingAdapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), ShiTingXiangQingActivity.class);
                intent.putExtra("name", try_listening.get(position).getName());
                intent.putExtra("micro_class_id", try_listening.get(position).getMicro_class_id());
                jumpToActivity(intent, false);
            }
        });
        shiTingViewPager.setAdapter(shiTingAdapter);
    }

    /**
     * 课程表
     */
    private void initGongGao() {
        keChenBiaoRecy.setLayoutManager(new LinearLayoutManager(getActivity()));
        keChenBiaoAdapter = new MyBaseRecyclerAdapter<JiaZhangShouData.DataBean.Curriculum>(curriculum, mContext, R.layout.gong_gao_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, JiaZhangShouData.DataBean.Curriculum item, int postion) {
                holder.setHeaderByUrl(R.id.pic, item.getHeader());
                holder.setText(R.id.name, item.getNickname());
                holder.setText(R.id.title, item.getService_name());
                holder.setText(R.id.classtime, item.getYuyue_time());
                holder.setText(R.id.times, item.getTimes());
            }
        };
        keChenBiaoAdapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(mContext, YuYueDingDanXiangQingActivity.class);
                intent.putExtra("status", "uid");
                intent.putExtra("yuyue_id", curriculum.get(position).getYuyue_id());
                jumpToActivity(intent, false);
            }
        });
        keChenBiaoRecy.setAdapter(keChenBiaoAdapter);
    }

    /**
     * 轮播
     */
    private void initLunBo() {
        LunBoUntil lunBoUntil = new LunBoUntil(slider, banner, getActivity());
        lunBoUntil.start();
    }


    @Override
    public void setListener() {

    }

    @Override
    public int setlayoutResID() {
        return R.layout.gong_lue_jia_zhang_fragment;
    }

    @Override
    public void getSuccess(String s, int what) {
        if (codeIsOne(s, false)) {
            JiaZhangShouData data = MyGsonUntil.getInstance().fromJson(s, JiaZhangShouData.class);
            //轮播
            if (banner.size() == 0) {
                banner.addAll(data.getData().getBanner());
                initLunBo();
            }
            consulting.clear();
            consulting.addAll(data.getData().getConsulting());

            methods.clear();
            methods.addAll(data.getData().getMethods());

            micro.clear();
            micro.addAll(data.getData().getMicro());

            try_listening.clear();
            try_listening.addAll(data.getData().getTry_listening());

            activity_zone.clear();
            activity_zone.addAll(data.getData().getActivity_zone());

            curriculum.clear();
            curriculum.addAll(data.getData().getCurriculum());
            if (curriculum.size() == 0) {
                noyuyue.setVisibility(View.VISIBLE);
            } else {
                noyuyue.setVisibility(View.GONE);
            }

            xinLianXinAdapter.notifyDataSetChanged();
            jiaoZiAdapter.notifyDataSetChanged();
            shiTingAdapter.notifyDataSetChanged();
            touTiaoAdapter.notifyDataSetChanged();
            keChenBiaoAdapter.notifyDataSetChanged();
            huoDong();
        }
    }


    @OnClick({R.id.edu_more, R.id.xin_xin_more, R.id.more_shi_ting,
            R.id.tou_tiao_more, R.id.huoDong, R.id.myClass, R.id.myShiTing,
            R.id.myAnswer, R.id.myYuYue, R.id.myCollection, R.id.myActivity
            , R.id.myKCBlin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.edu_more:
                Intent intent5 = new Intent(mContext, EduListActivity.class);
                intent5.putExtra("status", "1");
                jumpToActivity(intent5, false);
                break;
            case R.id.xin_xin_more:
                Intent intent = new Intent(getActivity(), WeiXueTangActivity.class);
                GongLueData.getInstance().setModularType(GongLueData.PARENT);
                jumpToActivity(intent, false);
                break;
            case R.id.more_shi_ting:
                Intent intent1 = new Intent(getActivity(), ShiTingKeActivity.class);
                GongLueData.getInstance().setModularType(GongLueData.PARENT);
                jumpToActivity(intent1, false);
                break;
            case R.id.tou_tiao_more:
                Intent intent7 = new Intent(mContext, TouTiaoListActivity.class);
                GongLueData.getInstance().setTouTiaoType(GongLueData.TOUTIAOJIAZHANG);
                jumpToActivity(intent7, false);
                break;
            case R.id.huoDong:
                Intent intent10 = new Intent(mContext, ActivityList.class);
                GongLueData.getInstance().setModularType(GongLueData.PARENT);
                jumpToActivity(intent10, false);
                break;
            case R.id.myClass:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (MyApplication.getmInstance().getUid().equals("")) return;
                Intent intent11 = new Intent(mContext, SmallZhuanJiActivity.class);
                GongLueData.getInstance().setModularType(GongLueData.PARENT);
                jumpToActivity(intent11, false);
                break;
            case R.id.myShiTing:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (MyApplication.getmInstance().getUid().equals("")) return;
                GongLueData.getInstance().setModularType(GongLueData.PARENT);
                jumpToActivity(ShiTingListActivity.class, false);
                break;
            case R.id.myAnswer:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (MyApplication.getmInstance().getUid().equals("")) return;
                jumpToActivity(MyWenDaActivity.class, false);
                break;
            case R.id.myYuYue:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (MyApplication.getmInstance().getUid().equals("")) return;
                jumpToActivity(MyYuYueActivity.class, false);
                break;
            case R.id.myCollection:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (MyApplication.getmInstance().getUid().equals("")) return;
                jumpToActivity(MyCollectionActivity.class, false);
                break;
            case R.id.myActivity:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (MyApplication.getmInstance().getUid().equals("")) return;
                jumpToActivity(MyActivityList.class, false);
                break;
            case R.id.myKCBlin:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (MyApplication.getmInstance().getUid().equals("")) return;
                jumpToActivity(com.test720.grasshoppercollege.module.gongLue.jiaZhang.keChenBiao.KeChenBiaoActivity.class, false);
                break;
        }
    }

    @BindView(R.id.huoDongImg)
    ImageView huoDongImg;
    @BindView(R.id.noyuyue)
    ImageView noyuyue;
    @BindView(R.id.huoDongTitle)
    TextView huoDongTitle;
    @BindView(R.id.slider)
    SliderLayout slider;
    @BindView(R.id.myKeChenBiao)
    RecyclerView keChenBiaoRecy;
    @BindView(R.id.edu_more)
    FrameLayout eduMore;
    @BindView(R.id.jiao_zi_you_fang)
    RecyclerView jiaoZiYouFang;
    @BindView(R.id.tou_tiao_recyclerView)
    RecyclerView touTiaoRecyclerView;
    @BindView(R.id.xin_liann_xin)
    RecyclerView xinLiannXin;
    @BindView(R.id.shiTingViewPager)
    RecyclerView shiTingViewPager;
    @BindView(R.id.huoDong)
    LinearLayout huoDong;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
}
