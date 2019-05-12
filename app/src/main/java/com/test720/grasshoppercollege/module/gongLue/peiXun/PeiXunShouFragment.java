package com.test720.grasshoppercollege.module.gongLue.peiXun;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

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
import com.test720.grasshoppercollege.module.gongLue.peiXun.bean.JingPingHuanYIPiData;
import com.test720.grasshoppercollege.module.gongLue.peiXun.bean.PeiXunShouData;
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
 * Created by 水东流 on 2018/8/17.
 */

public class PeiXunShouFragment extends BaseFragment {

    private List<Banner> banner = new ArrayList<>();//轮播
    private List<PeiXunShouData.DataBean.CateBean> cate = new ArrayList<>();//分类
    private List<PeiXunShouData.DataBean.NoticeBean> notice = new ArrayList<>();//公告
    private List<PeiXunShouData.DataBean.RecommendSchoolBean> recommend_school = new ArrayList<>();//推荐机构
    private List<PeiXunShouData.DataBean.BestSchoolBean> best_school = new ArrayList<>();//精品机构

    MyBaseRecyclerAdapter cateAdapter;
    MyBaseRecyclerAdapter recommend_schoolAdapter;
    MyBaseRecyclerAdapter best_schoolAdapter;
    boolean iscanSee = false;
    boolean needzhuLi = false;
    private String province = "浙江省", city = "杭州市", area = "拱墅区";//省市区


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        banner.clear();
        cate.clear();
        notice.clear();
        recommend_school.clear();
        best_school.clear();
    }

    @Override
    public void onEvent(CurrencyEvent event) {
        Log.i("lxl", "event" + event.getWhat());
        if (event.getWhat() == CurrencyEvent.UPDATAADRESSONE) {
            if (event.getMsg().equals("地址切换")) {
                //读取本地地址
                province = SPUtils.getProvince(mContext);
                city = SPUtils.getCity(mContext);
                area = SPUtils.getArea(mContext);
                adress.setText(area);
                getData();
            }
        }
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

        oneRecyclerview.setHasFixedSize(true);
        oneRecyclerview.setNestedScrollingEnabled(false);

        jigourecyclerView.setHasFixedSize(true);
        jigourecyclerView.setNestedScrollingEnabled(false);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefresh.setRefreshing(false);
                getData();
            }
        });


        initJiGou();
        initType();
        jinXuan();
        getData();
    }

    /**
     * 获取数据
     */
    private void getData() {
        //读取本地地址
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
        post(HttpUntil.GetIntent().StrategyTrainindex(), httpParams, 1, false);

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
     * 类别
     */
    private void initType() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 4);
        oneRecyclerview.setLayoutManager(gridLayoutManager);
        cateAdapter = new MyBaseRecyclerAdapter<PeiXunShouData.DataBean.CateBean>(cate, getActivity(), R.layout.gong_lu_type) {
            @Override
            public void convert(BaseRecyclerHolder holder, PeiXunShouData.DataBean.CateBean item, int postion) {
                holder.setImageByUrl(R.id.img, item.getPic());
                holder.setText(R.id.text, item.getName());
            }
        };
        cateAdapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(mContext, JiGouListActivity.class);
                intent.putExtra("title", cate.get(position).getName());
                intent.putExtra("cate_id", cate.get(position).getCate_id());
                jumpToActivity(intent, false);
            }
        });
        oneRecyclerview.setAdapter(cateAdapter);
    }

    /**
     * 精选机构
     */
    private void jinXuan() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        best_schoolAdapter = new MyBaseRecyclerAdapter<PeiXunShouData.DataBean.BestSchoolBean>(best_school, getActivity(), R.layout.ji_gou_list_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, PeiXunShouData.DataBean.BestSchoolBean item, int postion) {
                holder.setImageByUrl(R.id.img, item.getCover());
                holder.setText(R.id.text, item.getName());
            }
        };
        best_schoolAdapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), MechanismActivity.class);
                intent.putExtra("title", best_school.get(position).getName());
                intent.putExtra("school_id", best_school.get(position).getSchool_id());
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
        recommend_schoolAdapter = new MyBaseRecyclerAdapter<PeiXunShouData.DataBean.RecommendSchoolBean>(recommend_school, getActivity(), R.layout.gong_lu_pei_xu_ji_gou_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, PeiXunShouData.DataBean.RecommendSchoolBean item, int postion) {
                holder.setText(R.id.name, item.getName());
                holder.setImageByUrl(R.id.pic, item.getRecommend_pic());
            }
        };
        recommend_schoolAdapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), MechanismActivity.class);
                intent.putExtra("title", recommend_school.get(position).getName());
                intent.putExtra("school_id", recommend_school.get(position).getSchool_id());
                jumpToActivity(intent, false);
            }
        });
        jigourecyclerView.setAdapter(recommend_schoolAdapter);
    }


    @Override
    public void setListener() {

    }

    @Override
    public int setlayoutResID() {
        return R.layout.fragment_pei_xun_shou;
    }

    @Override
    public void getSuccess(String s, int what) {
        switch (what) {
            case 1:
                if (codeIsOne(s, false)) {
                    PeiXunShouData peiXunShouData = MyGsonUntil.getInstance().fromJson(s, PeiXunShouData.class);
                    //轮播
                    if (banner.size() == 0) {
                        banner.addAll(peiXunShouData.getData().getBanner());
                        initLunBo();
                    }

                    //类别
                    cate.clear();
                    cate.addAll(peiXunShouData.getData().getCate());
                    cateAdapter.notifyDataSetChanged();
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

                    //助力弹框
                    if (peiXunShouData.getData().getZhuli().equals("1")) {
                        needzhuLi = true;
                    } else {
                        needzhuLi = false;
                        EventBus.getDefault().post(new CurrencyEvent("完成", CurrencyEvent.FINISHADMISSONE));
                    }
                }
                break;
            case 5:
                if (codeIsOne(s, false)) {
                    JingPingHuanYIPiData data = new Gson().fromJson(s, JingPingHuanYIPiData.class);
                    best_school.clear();
                    best_school.addAll(data.getData());
                    best_schoolAdapter.notifyDataSetChanged();
                }
                break;
        }

    }


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.slider)
    SliderLayout slider;
    @BindView(R.id.gongGao)
    ViewFlipper gongGao;
    @BindView(R.id.oneRecyclerview)
    RecyclerView oneRecyclerview;
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
                        getData();
                    }
                });
                break;
            case R.id.updata:
                updata();
                break;
            case R.id.more://精选更多
                jumpToActivity(JingXuanJiGouListActivity.class, false);
                break;
        }
    }

    /**
     * 换一批
     */
    private void updata() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("province", province);
        httpParams.put("city", city);
        httpParams.put("area", area);
        post(HttpUntil.GetIntent().getIP() + "small.php/StrategyTrain/inBatch", httpParams, 5);
    }
}
