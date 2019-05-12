package com.test720.grasshoppercollege.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.makeramen.roundedimageview.RoundedImageView;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseRecyclerViewFragment;
import com.test720.grasshoppercollege.GameActivity;
import com.test720.grasshoppercollege.HttpBean.HuaYuanData;
import com.test720.grasshoppercollege.HttpBean.UserData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.WebViewActivity;
import com.test720.grasshoppercollege.module.guoguoDou.GuoGuoDouShouActivity;
import com.test720.grasshoppercollege.module.qianDao.QianDaoShouActivity;
import com.test720.grasshoppercollege.module.userData.LearningCommunity.LearningCommunityActivity;
import com.test720.grasshoppercollege.module.userData.PaiHangBangActivity;
import com.test720.grasshoppercollege.module.userData.SetActivity;
import com.test720.grasshoppercollege.module.userData.TuiJianActivity;
import com.test720.grasshoppercollege.module.userData.XiaoFeiActivity;
import com.test720.grasshoppercollege.module.userData.caiWu.CaiWuActivity;
import com.test720.grasshoppercollege.module.userData.geRenZiLiao.PersonActivity;
import com.test720.grasshoppercollege.module.userData.myTeam.TeamShouActivity;
import com.test720.grasshoppercollege.module.userData.vip.GouMaiVipActivity;
import com.test720.grasshoppercollege.module.userData.youHuiJuan.MyYouHuiJuanActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.ShenHeUntil;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.test720.mylibrary.ErWei.ErWeiMaActivity;

/**
 * Created by 水东流 on 2018/8/3.
 */

public class BeiBaoFragment extends BaseRecyclerViewFragment implements View.OnClickListener, MyBaseRecyclerAdapter.OnItemClickListener {

    View head;
    ViewHolder viewHolder;

    @Override
    public int setCount() {
        return 4;
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
        return HttpUntil.GetIntent().info();
    }

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            MyApplication.getmInstance().userData = new Gson().fromJson(str, UserData.class);
            setData();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ShuaXin();
    }

    /*展示个人信息F*/
    private void setData() {
        if (mContext.isFinishing()) return;
        ///****个人信息***/
        Glide.with(mContext).load(MyApplication.getmInstance().userData.getData().getHeader()).error(R.mipmap.pic_head_default).into(viewHolder.header);
        viewHolder.name.setText(MyApplication.getmInstance().userData.getData().getNickname());
        if (MyApplication.getmInstance().userData.getData().getVip().equals("0")) {//非vip
            viewHolder.vip.setImageResource(R.mipmap.novip);
        } else {
            viewHolder.vip.setImageResource(R.mipmap.vip);
        }
        if (MyApplication.getmInstance().userData.getData().getBackpack().equals("1")) {
            viewHolder.huayuan.setImageResource(R.mipmap.guoguohuayuan);
        } else {
            viewHolder.huayuan.setImageResource(R.mipmap.xuexijihuaback);
        }
     /*               如果为1则显示我的团队
        if (MyApplication.getmInstance().userData.getData().getTeam_status().equals("1")) {
            myteam.setVisibility(View.VISIBLE);
        } else {
            myteam.setVisibility(View.GONE);
        }*/
    }

    @Override
    public void gengDuo(String str) {

    }

    @Override
    public HttpParams httpParams() {
        if (!MyApplication.getmInstance().getUid().equals("")) {
            HttpParams httpParams = new HttpParams();
            httpParams.put("uid", MyApplication.getmInstance().getUid());//请求参数三
            return httpParams;
        }
        return null;
    }

    @SuppressLint("InflateParams")
    @Override
    public void init() {
        head = LayoutInflater.from(getActivity()).inflate(R.layout.bei_bao_head, null);
        viewHolder = new ViewHolder(head);
        viewHolder.set.setOnClickListener(this);
        viewHolder.header.setOnClickListener(this);
        viewHolder.name.setOnClickListener(this);
        viewHolder.doudou.setOnClickListener(this);
        viewHolder.vipRel.setOnClickListener(this);
        viewHolder.paihangbang.setOnClickListener(this);
        viewHolder.huayuan.setOnClickListener(this);
        viewHolder.daiFaHuo.setOnClickListener(this);
        viewHolder.daiShouHuo.setOnClickListener(this);
        viewHolder.daiFaHuo.setOnClickListener(this);
        viewHolder.daiFuKuan.setOnClickListener(this);

        List<String> list = new ArrayList<>();
        list.add("我的财务");
        list.add("我的消费");
        list.add("优惠劵");
        list.add("我的团队");
        list.add("学习社区");
        list.add("扫码学习");
        list.add("学苑介绍");
        list.add("推荐二维码");
        adapter = new MyBaseRecyclerAdapter<String>(list, getActivity(), R.layout.bei_bao_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, String item, int postion) {
                holder.setText(R.id.text, item);
                switch (postion) {
                    case 0:
                        holder.setImageResource(R.id.img, R.mipmap.caiwutubiao);
                        break;
                    case 1:
                        holder.setImageResource(R.id.img, R.mipmap.xiaofeitubiao);
                        break;
                    case 2:
                        holder.setImageResource(R.id.img, R.mipmap.youhuijuantubiao);
                        break;
                    case 3:
                        holder.setImageResource(R.id.img, R.mipmap.tuanduitubiao);

                        break;
                    case 4:
                        holder.setImageResource(R.id.img, R.mipmap.xuexishequtubiao);
                        break;
                    case 5:
                        holder.setImageResource(R.id.img, R.mipmap.tuijianerweima);
                        break;
                    case 6:
                        holder.setImageResource(R.id.img, R.mipmap.fenxiangyoulitubiao);
                        break;
                    case 7:
                        holder.setImageResource(R.id.img, R.mipmap.erweimatubiao);
                        break;
                }
            }
        };
        adapter.setHeaderView(head);
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);
        //审核模式
        if (ShenHeUntil.getInstance().isShenHe()) {
            viewHolder.vipRel.setVisibility(View.INVISIBLE);
            viewHolder.huayuan.setVisibility(View.GONE);
            viewHolder.doudou.setVisibility(View.INVISIBLE);
            viewHolder.paihangbang.setVisibility(View.INVISIBLE);
            viewHolder.fu_wu_lin.setVisibility(View.INVISIBLE);
            list.clear();
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setListener() {
        haveHead = true;
    }

    @Override
    public int setlayoutResID() {
        return R.layout.fragment_bei_bao;
    }


    MyBaseRecyclerAdapter adapter;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.caiwu:
                jumpToActivity(CaiWuActivity.class, false);
                break;
            case R.id.name:
            case R.id.header:
                jumpToActivity(PersonActivity.class, false);
                break;
            case R.id.paihangbang:
                jumpToActivity(PaiHangBangActivity.class, false);
                break;
            case R.id.doudou:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (!MyApplication.getmInstance().getUid().equals("")) {
                    jumpToActivity(GuoGuoDouShouActivity.class, false);
                }
                break;
            case R.id.vip_rel:
                if (MyApplication.getmInstance().userData.getData().getVip().equals("0")) {//非vip
                    jumpToActivity(GouMaiVipActivity.class, false);
                } else {
                    Intent intent88 = new Intent(mContext, WebViewActivity.class);
                    intent88.putExtra("title", "vip权益");
                    intent88.putExtra("path", HttpUntil.GetIntent().getIP() + "small.php/User/aboutUs/type/6");
                    jumpToActivity(intent88, false);
                }
                break;
            case R.id.huayuan:
                garden();
                break;
            case R.id.rongyuqiang:

                break;
            case R.id.set:
                jumpToActivity(SetActivity.class, false);
                break;
        }
    }

    /**
     * 花园
     */
    private void garden() {

        if (MyApplication.getmInstance().userData.getData().getBackpack().equals("1")) {
            ShowDialong();
            HttpParams httpParams = new HttpParams();
            httpParams.put("uid", MyApplication.getmInstance().getUid());
            post(HttpUntil.GetIntent().getIP() + "small.php/Farm/index", httpParams, 9, true);
        } else {
            Intent intent = new Intent(mContext, WebViewActivity.class);
            intent.putExtra("path", HttpUntil.GetIntent().getIP() + "small.php/User/aboutUs/type/13");
            intent.putExtra("title", "学习计划");
            jumpToActivity(intent, false);
        }
    }

    @Override
    public void getSuccess(String str, int what) {
        super.getSuccess(str, what);
        if (what == 9) {
            if (codeIsOne(str, false)) {
                HuaYuanData huaYuanData = new Gson().fromJson(str, HuaYuanData.class);
                Intent intent = new Intent(mContext, GameActivity.class);
                intent.putExtra("path", huaYuanData.getData().getLogin_url());
                intent.putExtra("time", huaYuanData.getData().getRemaining_time());
                jumpToActivity(intent, false);
            } else {
                if (getJsonCode(str, true) == 7) {
                    Intent intent = new Intent(mContext, QianDaoShouActivity.class);
                    jumpToActivity(intent, false);
                }
            }
        }
    }

    @Override
    public void onItemClick(int position) {
        /*  list.add("我的财务");0
        list.add("我的消费");1
        list.add("优惠劵");2
        list.add("我的团队");3
        list.add("学习社区");4
        list.add("扫码学习");5
        list.add("分享有礼");6
        list.add("推荐二维码");7*/
        switch (position) {
            case 0:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (!MyApplication.getmInstance().getUid().equals("")) {
                    jumpToActivity(CaiWuActivity.class, false);
                }
                break;
            case 1:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (!MyApplication.getmInstance().getUid().equals("")) {
                    jumpToActivity(XiaoFeiActivity.class, false);
                }
                break;
            case 2:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (!MyApplication.getmInstance().getUid().equals("")) {
                    jumpToActivity(MyYouHuiJuanActivity.class, false);
                }
                break;
            case 3:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (!MyApplication.getmInstance().getUid().equals("")) {
                    jumpToActivity(TeamShouActivity.class, false);
                }
                break;
            case 4:
                jumpToActivity(LearningCommunityActivity.class, false);
                break;
            case 5:
                AndPermission.with(mContext).requestCode(100).
                        permission(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
                        .callback(new PermissionListener() {
                            @Override
                            public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
                                jumpToActivity(ErWeiMaActivity.class, false);
                            }

                            @Override
                            public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {

                            }
                        }).start();
                break;
            case 6://分享有礼，蝈蝈学苑介绍
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (!MyApplication.getmInstance().getUid().equals("")) {
                    Intent intent = new Intent(mContext, WebViewActivity.class);
                    intent.putExtra("title", "学苑介绍");
                    intent.putExtra("path", MyApplication.getmInstance().userData.getData().getSchool_introduce());
                    jumpToActivity(intent, false);
                }
                break;
            case 7://推荐二维码
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (!MyApplication.getmInstance().getUid().equals("")) {
                    jumpToActivity(TuiJianActivity.class, false);
                }
                break;
        }
    }

    static class ViewHolder {
        @BindView(R.id.set)
        ImageView set;
        @BindView(R.id.vip)
        ImageView vip;
        @BindView(R.id.vip_rel)
        RelativeLayout vipRel;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.doudou)
        LinearLayout doudou;
        @BindView(R.id.fu_wu_lin)
        LinearLayout fu_wu_lin;
        @BindView(R.id.paihangbang)
        LinearLayout paihangbang;
        @BindView(R.id.huayuan)
        ImageButton huayuan;
        @BindView(R.id.header)
        RoundedImageView header;
        @BindView(R.id.daiFuKuan)
        LinearLayout daiFuKuan;
        @BindView(R.id.daiFaHuo)
        LinearLayout daiFaHuo;
        @BindView(R.id.daiShouHuo)
        LinearLayout daiShouHuo;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
