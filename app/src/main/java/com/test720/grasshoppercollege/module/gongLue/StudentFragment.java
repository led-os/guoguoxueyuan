package com.test720.grasshoppercollege.module.gongLue;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
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
import com.test720.grasshoppercollege.module.gongLue.student.bean.StudentShouData;
import com.test720.grasshoppercollege.module.gongLue.student.keChenBiao.KeChenBiaoActivity;
import com.test720.grasshoppercollege.module.gongLue.student.myActivity.MyActivityList;
import com.test720.grasshoppercollege.module.gongLue.student.myCollection.MyCollectionActivity;
import com.test720.grasshoppercollege.module.gongLue.student.myCourse.MyCourseListActivity;
import com.test720.grasshoppercollege.module.gongLue.student.myShiTing.ShiTingListActivity;
import com.test720.grasshoppercollege.module.gongLue.student.mySmallClass.SmallZhuanJiActivity;
import com.test720.grasshoppercollege.module.gongLue.student.onLine.OnLineShouActivity;
import com.test720.grasshoppercollege.module.gongLue.student.onLine.StudentOnLineFragment;
import com.test720.grasshoppercollege.module.gongLue.student.shiTing.ShiTingKeActivity;
import com.test720.grasshoppercollege.module.gongLue.student.shiTing.ShiTingXiangQingActivity;
import com.test720.grasshoppercollege.module.gongLue.student.smallClass.SmallClassXiangQingActivity;
import com.test720.grasshoppercollege.module.gongLue.student.smallClass.WeiXueTangActivity;
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

public class StudentFragment extends BaseFragment {

    private List<Banner> banner = new ArrayList<>();
    private List<StudentShouData.DataBean.OnlineBean> online = new ArrayList<>();
    private List<StudentShouData.DataBean.OnlineBean> underline = new ArrayList<>();
    private List<StudentShouData.DataBean.MicroBean> micro = new ArrayList<>();
    private List<StudentShouData.DataBean.TryListeningBean> try_listening = new ArrayList<>();
    private List<StudentShouData.DataBean.ActivityZoneBean> activity_zone = new ArrayList<>();
    private List<StudentShouData.DataBean.CurriculumBean> curriculum = new ArrayList<>();

    MyBaseRecyclerAdapter microAdapter;
    MyBaseRecyclerAdapter listeningAdapter;
    MyBaseRecyclerAdapter keChenBiaoAdapter;

    @Override
    public void initData() {
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
                swipeRefresh.setRefreshing(false);
            }
        });

        classviewPager.setHasFixedSize(true);
        classviewPager.setNestedScrollingEnabled(false);

        shiTingViewPager.setHasFixedSize(true);
        shiTingViewPager.setNestedScrollingEnabled(false);

        keChenBiaoRecy.setHasFixedSize(true);
        keChenBiaoRecy.setNestedScrollingEnabled(false);

        initGongGao();
        classViewPager();
        shiTing();
        getData();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        banner.clear();
        online.clear();
        underline.clear();
        micro.clear();
        try_listening.clear();
        activity_zone.clear();
        curriculum.clear();
    }

    /**
     * 获取数据
     */
    private void getData() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("class", MyApplication.getmInstance().nianji);
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        post(HttpUntil.GetIntent().Strategyindex(), httpParams, 1, false);
    }

    /**
     * 课程表
     */
    private void initGongGao() {
        keChenBiaoRecy.setLayoutManager(new LinearLayoutManager(getActivity()));
        keChenBiaoAdapter = new MyBaseRecyclerAdapter<StudentShouData.DataBean.CurriculumBean>(curriculum, mContext, R.layout.gong_gao_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, StudentShouData.DataBean.CurriculumBean item, int postion) {
                holder.setHeaderByUrl(R.id.pic, item.getTeacher_header());
                holder.setText(R.id.name, item.getTeacher_name());
                holder.setText(R.id.title, item.getCourse_name());
                holder.setText(R.id.classtime, item.getClass_time());
                holder.setText(R.id.times, item.getWeek() + " " + item.getTimes());
            }
        };
        keChenBiaoAdapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (MyApplication.getmInstance().getUid().equals("")) return;
                jumpToActivity(KeChenBiaoActivity.class, false);
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

    /**
     * 线上线下
     */
    List<Fragment> list = new ArrayList<>();
    List<String> titles = new ArrayList<>();
    StudentOnLineFragment studentOnLineFragmentOne = new StudentOnLineFragment();
    StudentOnLineFragment studentOnLineFragmentTwo = new StudentOnLineFragment();

    private void onLine() {
        list.clear();
        titles.clear();
        titles.add(getString(R.string.on_line_jin_pin));
        titles.add(getString(R.string.un_line_jin_pin));

        studentOnLineFragmentOne.setOnline(online);
        studentOnLineFragmentOne.setOnLine(true);

        studentOnLineFragmentTwo.setOnline(underline);
        studentOnLineFragmentOne.setOnLine(false);

        list.add(studentOnLineFragmentOne);
        list.add(studentOnLineFragmentTwo);
        lineViewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titles.get(position);
            }
        });
        linTab.setupWithViewPager(lineViewPager);
    }

    /**
     * 微课堂
     */
    private void classViewPager() {
        classviewPager.setLayoutManager(new LinearLayoutManager(getActivity()));
        /*R.layout.gong_lue_book_item*/
        microAdapter = new MyBaseRecyclerAdapter<StudentShouData.DataBean.MicroBean>(micro, getActivity(), R.layout.gong_lue_online_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, StudentShouData.DataBean.MicroBean item, int postion) {
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
        microAdapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                GongLueData.getInstance().setModularType(GongLueData.STUDENT);
                Intent intent = new Intent(getActivity(), SmallClassXiangQingActivity.class);
                intent.putExtra("micro_id", micro.get(position).getMicro_id());
                intent.putExtra("name", micro
                        .get(position).getName());
                jumpToActivity(intent, false);
            }
        });
        classviewPager.setAdapter(microAdapter);
    }

    /**
     * 试听课
     */
    private void shiTing() {
        shiTingViewPager.setLayoutManager(new LinearLayoutManager(getActivity()));
        /*R.layout.gong_lu_shi_ting_item*/
        listeningAdapter = new MyBaseRecyclerAdapter<StudentShouData.DataBean.TryListeningBean>(try_listening, getActivity(), R.layout.gong_lue_online_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, StudentShouData.DataBean.TryListeningBean item, int postion) {
            /*    holder.setText(R.id.title, item.getName());
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
        listeningAdapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                GongLueData.getInstance().setModularType(GongLueData.STUDENT);
                Intent intent = new Intent(getActivity(), ShiTingXiangQingActivity.class);
                intent.putExtra("name", try_listening
                        .get(position).getName());
                intent.putExtra("micro_class_id", try_listening.get(position).getMicro_class_id());
                jumpToActivity(intent, false);
            }
        });
        shiTingViewPager.setAdapter(listeningAdapter);
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
        Glide.with(mContext).load(activity_zone.get(0).getCover()).into(huoDongImg);
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

    @Override
    public void setListener() {

    }

    @Override
    public int setlayoutResID() {
        return R.layout.gong_lue_xue_sheng;
    }

    @Override
    public void getSuccess(String s, int what) {
        if (codeIsOne(s, false)) {
//            StudentShouData data = new Gson().fromJson(s, StudentShouData.class);
            StudentShouData data = MyGsonUntil.getInstance().fromJson(s, StudentShouData.class);
            //轮播
            if (banner.size() == 0) {
                banner.addAll(data.getData().getBanner());
                initLunBo();
            }
            //线上线下
            online.clear();
            online.addAll(data.getData().getOnline());
            underline.clear();
            underline.addAll(data.getData().getUnderline());
            onLine();
            //微课堂
            micro.clear();
            micro.addAll(data.getData().getMicro());
            microAdapter.notifyDataSetChanged();
            //试听课
            try_listening.clear();
            try_listening.addAll(data.getData().getTry_listening());
            listeningAdapter.notifyDataSetChanged();
            //课程表
            curriculum.clear();
            curriculum.addAll(data.getData().getCurriculum());
            if (curriculum.size() == 0) {
                noclass.setVisibility(View.VISIBLE);
            } else {
                noclass.setVisibility(View.GONE);
            }
            keChenBiaoAdapter.notifyDataSetChanged();

            // 活动
            activity_zone.clear();
            activity_zone.addAll(data.getData().getActivity_zone());
            huoDong();
        }

    }


    @OnClick({R.id.onLine_more, R.id.more_small_class, R.id.more_shi_ting, R.id.huoDong, R.id.mySmallClass,
            R.id.myShiTing, R.id.myCourse, R.id.myActivity, R.id.myCollection, R.id.myKCBlin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.onLine_more:
                if (lineViewPager.getCurrentItem() == 0) {
                    Intent intent = new Intent(getActivity(), OnLineShouActivity.class);
                    GongLueData.getInstance().setModularType(GongLueData.ONLINE);
                    jumpToActivity(intent, false);
                } else {
                    Intent intent = new Intent(getActivity(), OnLineShouActivity.class);
                    GongLueData.getInstance().setModularType(GongLueData.LINEDOWN);
                    jumpToActivity(intent, false);
                }
                break;

            case R.id.more_small_class:
                Intent intent = new Intent(getActivity(), WeiXueTangActivity.class);
                GongLueData.getInstance().setModularType(GongLueData.STUDENT);
                jumpToActivity(intent, false);
                break;

            case R.id.more_shi_ting:
                Intent intent1 = new Intent(getActivity(), ShiTingKeActivity.class);
                GongLueData.getInstance().setModularType(GongLueData.STUDENT);
                jumpToActivity(intent1, false);
                break;

            case R.id.huoDong:
                Intent intent10 = new Intent(mContext, ActivityList.class);
                GongLueData.getInstance().setModularType(GongLueData.STUDENT);
                jumpToActivity(intent10, false);
                break;

            case R.id.mySmallClass:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (MyApplication.getmInstance().getUid().equals("")) return;
                Intent intent11 = new Intent(mContext, SmallZhuanJiActivity.class);
                GongLueData.getInstance().setModularType(GongLueData.STUDENT);
                jumpToActivity(intent11, false);
                break;
            case R.id.myActivity:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (MyApplication.getmInstance().getUid().equals("")) return;
                jumpToActivity(MyActivityList.class, false);
                break;
            case R.id.myCourse:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (MyApplication.getmInstance().getUid().equals("")) return;
                jumpToActivity(MyCourseListActivity.class, false);
                break;
            case R.id.myShiTing:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (MyApplication.getmInstance().getUid().equals("")) return;
                GongLueData.getInstance().setModularType(GongLueData.STUDENT);
                jumpToActivity(ShiTingListActivity.class, false);
                break;
            case R.id.myCollection:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (MyApplication.getmInstance().getUid().equals("")) return;
                jumpToActivity(MyCollectionActivity.class, false);
                break;
            case R.id.myKCBlin:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (MyApplication.getmInstance().getUid().equals("")) return;
                jumpToActivity(KeChenBiaoActivity.class, false);
                break;
        }
    }


    @BindView(R.id.noclass)
    ImageView noclass;
    @BindView(R.id.huoDongImg)
    ImageView huoDongImg;
    @BindView(R.id.huoDongTitle)
    TextView huoDongTitle;
    @BindView(R.id.slider)
    SliderLayout slider;
    @BindView(R.id.myKeChenBiao)
    RecyclerView keChenBiaoRecy;
    @BindView(R.id.lin_tab)
    TabLayout linTab;
    @BindView(R.id.lineViewPager)
    ViewPager lineViewPager;
    @BindView(R.id.classviewPager)
    RecyclerView classviewPager;
    @BindView(R.id.shiTingViewPager)
    RecyclerView shiTingViewPager;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
}
