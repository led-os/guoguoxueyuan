package com.test720.grasshoppercollege.module.gongLue.teacher;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseFragment;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.WebViewActivity;
import com.test720.grasshoppercollege.module.gongLue.GongLueData;
import com.test720.grasshoppercollege.module.gongLue.jiaZhang.myCollection.MyCollectionActivity;
import com.test720.grasshoppercollege.module.gongLue.jiaZhang.touTiao.TouTiaoActivity;
import com.test720.grasshoppercollege.module.gongLue.jiaZhang.touTiao.TouTiaoListActivity;
import com.test720.grasshoppercollege.module.gongLue.teacher.bean.TeacherShouData;
import com.test720.grasshoppercollege.module.gongLue.teacher.pinTaiTeacher.PinTaiTeacherActivity;
import com.test720.grasshoppercollege.module.gongLue.teacher.pinTaiZiXun.ZhiXunShiShouActivity;
import com.test720.grasshoppercollege.module.gongLue.teacherAndConsultant.PeiXunOrZiXunlistActivity;
import com.test720.grasshoppercollege.module.gongLue.teacherAndConsultant.peiXunTeacher.PeiXunTeacherXiangQingActivity;
import com.test720.grasshoppercollege.module.gongLue.teacherAndConsultant.ziXunShi.ZiXunShiXiangQingActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.lunBo.Banner;
import com.test720.grasshoppercollege.untils.lunBo.LunBoUntil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

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
 * 作者：水东流 编于 2018/8/23
 */
public class TeacherShouFragment extends BaseFragment {

    @BindView(R.id.slider)
    SliderLayout slider;
    @BindView(R.id.teacher_help)
    RecyclerView teacherHelp;
    @BindView(R.id.consultant_tuijian)
    RecyclerView consultant_recycler;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.tou_tiao_recyclerView)
    RecyclerView touTiaoRecycler;
    @BindView(R.id.pinTaiTeacher)
    TextView pinTaiTeacher;
    @BindView(R.id.pinTaiCosultant)
    TextView pinTaiCosultant;


    private List<Banner> banner = new ArrayList<>();
    private List<TeacherShouData.DataBean.ConsultingBean> consulting = new ArrayList<>();//咨询头条
    private List<TeacherShouData.DataBean.TeacherTuijianBean> teacher_tuijian = new ArrayList<>();//教师推荐
    private List<TeacherShouData.DataBean.ConsultantTuijianBean> consultant_tuijian = new ArrayList<>();//咨询师推荐

    MyBaseRecyclerAdapter touTiaoAdapter;
    MyBaseRecyclerAdapter teacherAdapter;
    MyBaseRecyclerAdapter consultantAdapter;

    String teacherStatua = "0";
    String ziXunShiStatua = "0";
    TeacherShouData data;

    @Override
    public void initData() {
        teacherHelp.setHasFixedSize(true);
        teacherHelp.setNestedScrollingEnabled(false);

        consultant_recycler.setHasFixedSize(true);
        consultant_recycler.setNestedScrollingEnabled(false);

        touTiaoRecycler.setHasFixedSize(true);
        touTiaoRecycler.setNestedScrollingEnabled(false);

        /*刷新*/
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefresh.setRefreshing(false);
                getData();
            }
        });

        consultant();
        teacher();
        getData();
        toutiao();
    }

    /**
     * 获取数据
     */
    private void getData() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        post(HttpUntil.GetIntent().StrategyteacherIndex(), httpParams, 1, false);
    }

    /**
     * 咨询师
     */
    private void consultant() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        //设置布局管理器
        consultant_recycler.setLayoutManager(layoutManager);
        //设置分隔线
        consultant_recycler.addItemDecoration(new DividerItemDecoration(mContext, 1));
        consultantAdapter = new MyBaseRecyclerAdapter<TeacherShouData.DataBean.ConsultantTuijianBean>(consultant_tuijian, getActivity(), R.layout.zai_xian_zhuan_jia) {
            @Override
            public void convert(BaseRecyclerHolder holder, TeacherShouData.DataBean.ConsultantTuijianBean item, int postion) {
                holder.setText(R.id.name, item.getNickname());
                holder.setText(R.id.consultant_num, "咨询次数：" + item.getConsulting_number() + "次");
                float praise = Float.parseFloat(item.getGood_praise());
                int good = (int) (praise * 100);
                holder.setText(R.id.praise, good + "");
                holder.setHeaderByUrl(R.id.pic, item.getHeader());
                TagFlowLayout tagFlowLayout = holder.getView(R.id.flow);
                tagFlowLayout.setAdapter(new TagAdapter<String>(item.getTag()) {
                    @Override
                    public View getView(FlowLayout parent, int position, String o) {
                        @SuppressLint("InflateParams") View view = LayoutInflater.from(getActivity()).inflate(R.layout.shang_pin_biao_qian, null);
                        TextView textView = view.findViewById(R.id.text);
                        textView.setText(o);
                        textView.setBackgroundResource(R.drawable.button_bian_kuang);
                        textView.setTextSize(12);
                        textView.setTextColor(getResources().getColor(R.color.appColor));
                        return view;
                    }
                });
            }
        };
        consultantAdapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(mContext, ZiXunShiXiangQingActivity.class);
                intent.putExtra("tid", consultant_tuijian.get(position).getTid());
                intent.putExtra("status", "consultant");
                jumpToActivity(intent, false);
            }
        });
        consultant_recycler.setAdapter(consultantAdapter);
    }

    /**
     * 教师
     */
    private void teacher() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        //设置布局管理器
        teacherHelp.setLayoutManager(layoutManager);

        //设置分隔线
        teacherHelp.addItemDecoration(new DividerItemDecoration(mContext, 1));

        teacherAdapter = new MyBaseRecyclerAdapter<TeacherShouData.DataBean.TeacherTuijianBean>(teacher_tuijian, mContext, R.layout.teacher_gong_lu_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, TeacherShouData.DataBean.TeacherTuijianBean item, int postion) {
                holder.setText(R.id.name, item.getNickname());
                holder.setText(R.id.fans_num, item.getFans() + "人");
                float praise = Float.parseFloat(item.getGood_praise());
                int good = (int) (praise * 100);
                holder.setText(R.id.praise, good + "");
                holder.setHeaderByUrl(R.id.pic, item.getHeader());
                TagFlowLayout tagFlowLayout = holder.getView(R.id.flow);
                tagFlowLayout.setAdapter(new TagAdapter<String>(item.getTag()) {
                    @Override
                    public View getView(FlowLayout parent, int position, String o) {
                        @SuppressLint("InflateParams") View view = LayoutInflater.from(getActivity()).inflate(R.layout.shang_pin_biao_qian, null);
                        TextView textView = view.findViewById(R.id.text);
                        textView.setText(o);
                        textView.setBackgroundResource(R.drawable.button_bian_kuang);
                        textView.setTextSize(12);
                        textView.setTextColor(getResources().getColor(R.color.appColor));
                        return view;
                    }
                });
            }
        };
        teacherAdapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(mContext, PeiXunTeacherXiangQingActivity.class);
                intent.putExtra("tid", teacher_tuijian.get(position).getTid());
                intent.putExtra("status", "teacher");
                jumpToActivity(intent, false);
            }
        });
        teacherHelp.setAdapter(teacherAdapter);
    }

    /**
     * 轮播
     */

    private void initLunBo() {
        LunBoUntil lunBoUntil = new LunBoUntil(slider, banner, mContext);
        lunBoUntil.start();
    }

    /**
     * 头条
     */
    private void toutiao() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        //设置布局管理器
        touTiaoRecycler.setLayoutManager(layoutManager);
        //设置分隔线
        touTiaoRecycler.addItemDecoration(new DividerItemDecoration(mContext, 1));
        touTiaoAdapter = new MyBaseRecyclerAdapter<TeacherShouData.DataBean.ConsultingBean>(consulting, getActivity(), R.layout.gong_lue_jiao_yu_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, TeacherShouData.DataBean.ConsultingBean item, int postion) {
                holder.setImageByUrl(R.id.book, item.getCover());
                holder.setText(R.id.title, item.getTitle());
                holder.setText(R.id.num, item.getTime());
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
        touTiaoRecycler.setAdapter(touTiaoAdapter);
    }


    @Override
    public void setListener() {

    }

    @Override
    public int setlayoutResID() {
        return R.layout.gong_lue_teacher_shou_fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        banner.clear();
        consulting.clear();
        teacher_tuijian.clear();
        consultant_tuijian.clear();
    }

    @Override
    public void getSuccess(String s, int what) {
        if (codeIsOne(s, false)) {
            data = new Gson().fromJson(s, TeacherShouData.class);
            //轮播
            if (banner.size() == 0) {
                banner.addAll(data.getData().getBanner());
                initLunBo();
            }

            //头条
            consulting.clear();
            consulting.addAll(data.getData().getConsulting());
            touTiaoAdapter.notifyDataSetChanged();

            //教师
            teacher_tuijian.clear();
            teacher_tuijian.addAll(data.getData().getTeacher_tuijian());
            teacherAdapter.notifyDataSetChanged();

            //咨询师
            consultant_tuijian.clear();
            consultant_tuijian.addAll(data.getData().getConsultant_tuijian());
            consultantAdapter.notifyDataSetChanged();

            //我的状态
            statua(data.getData().getTeacher_status(), data.getData().getConsultant_status());
        }
    }

    /**
     * 我的个人状态
     *
     * @param teacher_status    教师状态
     * @param consultant_status 咨询师状态
     *                          老师审核状态 0-待审核 1-审核通过；2-审核失败；3
     */
    private void statua(String teacher_status, String consultant_status) {
        teacherStatua = teacher_status;
        ziXunShiStatua = consultant_status;
        switch (teacher_status) {
            case "0":
                pinTaiTeacher.setText("教师待审核中");
                pinTaiTeacher.setTextColor(getResources().getColor(R.color.gwe));
                break;
            case "1":
                pinTaiTeacher.setText("我是培训教师");
                pinTaiTeacher.setTextColor(getResources().getColor(R.color.chengyu4));
                break;
            case "2":
                pinTaiTeacher.setText("培训教师-重新审核");
                pinTaiTeacher.setTextColor(getResources().getColor(R.color.red));
                break;
            case "3":
                pinTaiTeacher.setText("成为培训教师");
                pinTaiTeacher.setTextColor(getResources().getColor(R.color.gwe));
                break;

        }
        switch (consultant_status) {
            case "0":
                pinTaiCosultant.setText("咨询师审核中");
                pinTaiCosultant.setTextColor(getResources().getColor(R.color.gwe));
                break;
            case "1":
                pinTaiCosultant.setText("我是咨询教师");
                pinTaiCosultant.setTextColor(getResources().getColor(R.color.chengyu4));
                break;
            case "2":
                pinTaiCosultant.setText("咨询师-重新审核");
                pinTaiCosultant.setTextColor(getResources().getColor(R.color.red));
                break;
            case "3":
                pinTaiCosultant.setText("成为咨询教师");
                pinTaiCosultant.setTextColor(getResources().getColor(R.color.gwe));
                break;

        }


    }


    @OnClick({R.id.zixunshishouchang, R.id.collection, R.id.edu_more, R.id.teacher_more, R.id.consultation_more, R.id.peixunlin, R.id.zixunlin, R.id.sousuo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zixunshishouchang:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (MyApplication.getmInstance().getUid().equals("")) return;
                jumpToActivity(MyCollectionActivity.class, false);
                break;
            case R.id.sousuo:
                if (data == null) return;
                Intent intent19 = new Intent(mContext, WebViewActivity.class);
                intent19.putExtra("title", "板块预览");
                intent19.putExtra("path", data.getData().getSearch_teacher());
                jumpToActivity(intent19, false);
                break;
            case R.id.collection:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (MyApplication.getmInstance().getUid().equals("")) return;
                jumpToActivity(com.test720.grasshoppercollege.module.gongLue.student.myCollection.MyCollectionActivity.class, false);
                break;
            case R.id.edu_more:
                Intent intent7 = new Intent(mContext, TouTiaoListActivity.class);
                GongLueData.getInstance().setTouTiaoType(GongLueData.TOUTIAOTEACHER);
                jumpToActivity(intent7, false);
                break;
            case R.id.teacher_more:
                Intent intent = new Intent(mContext, PeiXunOrZiXunlistActivity.class);
                intent.putExtra("status", "teacher");
                jumpToActivity(intent, false);
                break;
            case R.id.consultation_more:
                Intent intent1 = new Intent(mContext, PeiXunOrZiXunlistActivity.class);
                intent1.putExtra("status", "consultant");
                jumpToActivity(intent1, false);
                break;
            case R.id.peixunlin:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (MyApplication.getmInstance().getUid().equals("")) return;
                pingTaiTeacher("1");
                break;
            case R.id.zixunlin:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (MyApplication.getmInstance().getUid().equals("")) return;
                pingTaiTeacher("2");
                break;

        }
    }

    /**
     * 平台点击逻辑
     * 0-待审核 1-审核通过；2-审核失败,3审核失败
     *
     * @param s 状态 1 为教师，2为咨询师
     */
    private void pingTaiTeacher(String s) {
        switch (s) {
            case "1":
                switch (teacherStatua) {
                    case "0":
                        return;
                    case "1"://已经是老师
                        jumpToActivity(PinTaiTeacherActivity.class, false);
                        break;
                    case "2":
                    case "3":
                        Intent intent = new Intent(mContext, JoinUsActivity.class);
                        intent.putExtra("apply_for", s);
                        startActivityForResult(intent, 1);
                        break;
                }

                break;
            case "2":
                switch (ziXunShiStatua) {
                    case "0":
                        return;
                    case "1":
                        jumpToActivity(ZhiXunShiShouActivity.class, false);
                        break;
                    case "2":
                    case "3":
                        Intent intent = new Intent(mContext, JoinUsActivity.class);
                        intent.putExtra("apply_for", s);
                        startActivityForResult(intent, 1);
                        break;
                }

                break;
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            getData();
        }
    }
}
