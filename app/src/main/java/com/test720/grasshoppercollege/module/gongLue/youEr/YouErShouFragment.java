package com.test720.grasshoppercollege.module.gongLue.youEr;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.daimajia.slider.library.SliderLayout;
import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseFragment;
import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.gongLue.GongLueListActivity;
import com.test720.grasshoppercollege.module.gongLue.youEr.bean.BestSchoolBean;
import com.test720.grasshoppercollege.module.gongLue.youEr.bean.HuanYiPiData;
import com.test720.grasshoppercollege.module.gongLue.youEr.bean.YouErShouData;
import com.test720.grasshoppercollege.untils.AdressUntil;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.MyGsonUntil;
import com.test720.grasshoppercollege.untils.lunBo.Banner;
import com.test720.grasshoppercollege.untils.lunBo.LunBoUntil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import www.test720.mylibrary.SPUtils;

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
 * 作者：水东流 编于 2018/8/21
 */
public class YouErShouFragment extends BaseFragment {

    private String province = "", city = "", area = "";//省市区
    private List<Banner> banner = new ArrayList<>();//轮播
    private List<YouErShouData.DataBean.NoticeBean> notice = new ArrayList<>();//公告
    private List<YouErShouData.DataBean.RecommendSchoolBean> recommend_school = new ArrayList<>();//推荐机构
    private List<BestSchoolBean> best_school = new ArrayList<>();//精品机构

    boolean iscanSee = false;
    boolean needzhuLi = false;
    MyBaseRecyclerAdapter recommend_schoolAdapter;
    MyBaseRecyclerAdapter best_schoolAdapter;


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        banner.clear();
        notice.clear();
        recommend_school.clear();
        best_school.clear();

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        iscanSee = isVisibleToUser;
        if (iscanSee && needzhuLi) {
            jumpToActivity(AdmissionActivity.class, false);
        }
    }

    @Override
    public void initData() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        jigourecyclerView.setHasFixedSize(true);
        jigourecyclerView.setNestedScrollingEnabled(false);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefresh.setRefreshing(false);
                updataAdress();
            }
        });

        initJiGou();
        jinXuan();
        updataAdress();
    }


    @Override
    public void onEvent(CurrencyEvent event) {
        Log.i("lxl", "event" + event.getWhat());
        if (event.getWhat() == CurrencyEvent.UPDATAADRESSTWO) {
            if (event.getMsg().equals("地址切换")) {
                //读取本地地址
                province = SPUtils.getProvince(mContext);
                city = SPUtils.getCity(mContext);
                area = SPUtils.getArea(mContext);
                adress.setText(area);
                //读取本地地址
                updataAdress();
            }
        }
    }


    /**
     * 获取地址，信息，更新界面数据
     */
    private void updataAdress() {
        province = SPUtils.getProvince(mContext);
        city = SPUtils.getCity(mContext);
        area = SPUtils.getArea(mContext);
        adress.setText(area);

        HttpParams httpParams = new HttpParams();
        httpParams.put("status", "1");
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        httpParams.put("province", province);
        httpParams.put("city", city);
        httpParams.put("area", area);
        post(HttpUntil.GetIntent().StrategyChildindex(), httpParams, 1, false);
    }


    /**
     * 轮播
     */


    private void initLunBo() {

        LunBoUntil lunBoUntil = new LunBoUntil(slider, banner, getActivity());
        lunBoUntil.start();

    }

    /**
     * 公告
     */
    private void initGongGao(final String type) {
        for (int i = 0; i < notice.size(); i++) {
            @SuppressLint("InflateParams") View view = LayoutInflater.from(getActivity()).inflate(R.layout.pei_xun_gong_gao, null);
            TextView textView = view.findViewById(R.id.text);
            textView.setText(notice.get(i).getName());
            LinearLayout lin = view.findViewById(R.id.lin);
            if (i < notice.size() - 1) {
                i++;
                TextView textViewtwo = view.findViewById(R.id.texttwo);
                textViewtwo.setText(notice.get(i).getName());
            }
            lin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, GongLueListActivity.class);
                    intent.putExtra("notice_type", type);
                    jumpToActivity(intent, false);
                }
            });
            gongGao.addView(view);
        }
    }

    /**
     * 精选机构
     */
    private void jinXuan() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        best_schoolAdapter = new MyBaseRecyclerAdapter<BestSchoolBean>(best_school, getActivity(), R.layout.nian_ji_lie_biao_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, BestSchoolBean item, int postion) {
                holder.setImageByUrl(R.id.img, item.getCover());
                holder.setText(R.id.text, item.getName());
            }
        };
        best_schoolAdapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), KindergartenActivity.class);
                intent.putExtra("school_id", best_school.get(position).getSchool_id());
                intent.putExtra("title", best_school.get(position).getName());
                jumpToActivity(intent, false);
            }
        });
        recyclerView.setAdapter(best_schoolAdapter);
    }

    /**
     * 推荐机构
     */
    private void initJiGou() {
        jigourecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recommend_schoolAdapter = new MyBaseRecyclerAdapter<YouErShouData.DataBean.RecommendSchoolBean>(recommend_school, getActivity(), R.layout.gong_lu_pei_xu_ji_gou_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, YouErShouData.DataBean.RecommendSchoolBean item, int postion) {
                holder.setText(R.id.name, item.getName());
                holder.setImageByUrl(R.id.pic, item.getRecommend_pic());
            }
        };
        recommend_schoolAdapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), KindergartenActivity.class);
                intent.putExtra("title", recommend_school.get(position).getName());
                intent.putExtra("school_id", recommend_school.get(position).getSchool_id());
                jumpToActivity(intent, false);
            }
        });
        jigourecyclerView.setAdapter(recommend_schoolAdapter);
    }

    /**
     * 换一批
     */
    private void updata() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("province", province);
        httpParams.put("city", city);
        httpParams.put("area", area);
        post(HttpUntil.GetIntent().StrategyChildinBatch(), httpParams, 5);
    }

    @Override
    public void setListener() {

    }

    @Override
    public int setlayoutResID() {
        return R.layout.fragment_gong_lue_you_er_shou;
    }


    @Override
    public void getSuccess(String s, int what) {
        switch (what) {
            case 1:
                if (codeIsOne(s, false)) {
                    final YouErShouData peiXunShouData = MyGsonUntil.getInstance().fromJson(s, YouErShouData.class);
                    //轮播

                    if (banner.size() == 0) {
                        banner.addAll(peiXunShouData.getData().getBanner());
                        initLunBo();
                    }

                    //公告
                    notice.clear();
                    notice.addAll(peiXunShouData.getData().getNotice());
                    initGongGao(peiXunShouData.getData().getNotice_type());
                    //推荐机构
                    recommend_school.clear();
                    recommend_school.addAll(peiXunShouData.getData().getRecommend_school());
                    recommend_schoolAdapter.notifyDataSetChanged();
                    //精品机构
                    best_school.clear();
                    best_school.addAll(peiXunShouData.getData().getBest_school());
                    best_schoolAdapter.notifyDataSetChanged();
                    //清空关注的数据
                    guanZhuOne.setText(getString(R.string.shangweiguanzhu));
                    guanZhuTwo.setText(getString(R.string.shangweiguanzhu));
                    guanZhuOneImg.setImageResource(R.mipmap.weiguanzhuone);
                    guanZhuTwoImg.setImageResource(R.mipmap.weiguanzhutwo);
                    guanZhuOneImg.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    });
                    guanZhuTwoImg.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
                    //有关注数据
                    for (int i = 0; i < peiXunShouData.getData().getGuanzhu().size(); i++) {
                        switch (i) {
                            case 0:
                                guanZhuOne.setText(peiXunShouData.getData().getGuanzhu().get(i).getName());
                                Glide.with(mContext).load(peiXunShouData.getData().getGuanzhu().get(i).getCover()).into(guanZhuOneImg);
                                guanZhuOneImg.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(getActivity(), KindergartenActivity.class);
                                        intent.putExtra("title", peiXunShouData.getData().getGuanzhu().get(0).getName());
                                        intent.putExtra("school_id", peiXunShouData.getData().getGuanzhu().get(0).getSchool_id());
                                        jumpToActivity(intent, false);
                                    }
                                });
                                break;
                            case 1:
                                Glide.with(mContext).load(peiXunShouData.getData().getGuanzhu().get(i).getCover()).into(guanZhuTwoImg);
                                guanZhuTwo.setText(peiXunShouData.getData().getGuanzhu().get(i).getName());
                                guanZhuTwoImg.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(getActivity(), KindergartenActivity.class);
                                        intent.putExtra("title", peiXunShouData.getData().getGuanzhu().get(1).getName());
                                        intent.putExtra("school_id", peiXunShouData.getData().getGuanzhu().get(1).getSchool_id());
                                        jumpToActivity(intent, false);
                                    }
                                });
                                break;
                        }
                    }
                    //助力弹框
                    if (peiXunShouData.getData().getZhuli().equals("1")) {
                        needzhuLi = true;
                    } else {
                        needzhuLi = false;
                        EventBus.getDefault().post(new CurrencyEvent("完成", CurrencyEvent.FINISHADMISSTWO));
                    }
                }
                break;
            case 5:
                if (codeIsOne(s, false)) {
                    HuanYiPiData data = new Gson().fromJson(s, HuanYiPiData.class);
                    best_school.clear();
                    best_school.addAll(data.getData());
                    best_schoolAdapter.notifyDataSetChanged();
                }
                break;
        }


    }

    @BindView(R.id.updata)
    TextView updata;
    @BindView(R.id.guanZhuOneImg)
    ImageView guanZhuOneImg;
    @BindView(R.id.guanZhuTwoImg)
    ImageView guanZhuTwoImg;
    @BindView(R.id.guanZhuTwo)
    TextView guanZhuTwo;
    @BindView(R.id.guanZhuOne)
    TextView guanZhuOne;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.slider)
    SliderLayout slider;
    @BindView(R.id.gongGao)
    ViewFlipper gongGao;
    @BindView(R.id.jigourecyclerView)
    RecyclerView jigourecyclerView;
    @BindView(R.id.more)
    TextView moreLin;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.adress)
    TextView adress;


    @OnClick({R.id.adress, R.id.updata, R.id.more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.adress:
                AdressUntil.getInstance().adressDialog(mContext, province, city, area, new AdressUntil.AdressBack() {
                    @Override
                    public void province(String pr) {
                        province = pr;
                        SPUtils.putString(mContext, SPUtils.PROVINCE, pr);
                    }

                    @Override
                    public void city(String pr) {
                        city = pr;
                        SPUtils.putString(mContext, SPUtils.CITY, pr);
                    }

                    @Override
                    public void district(String pr) {
                        area = pr;
                        adress.setText(pr);
                        SPUtils.putString(mContext, SPUtils.AREA, pr);
                    }

                    @Override
                    public void ShuaXin() {
                        updataAdress();
                    }
                });
                break;
            case R.id.updata:
                updata();
                break;
            case R.id.more://精选更多
                jumpToActivity(JingXuanListActivity.class, false);
                break;
        }
    }


}