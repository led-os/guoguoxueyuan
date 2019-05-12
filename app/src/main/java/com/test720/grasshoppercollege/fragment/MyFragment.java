package com.test720.grasshoppercollege.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.makeramen.roundedimageview.RoundedImageView;
import com.test720.grasshoppercollege.BaseFragment;
import com.test720.grasshoppercollege.HttpBean.UserData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.userData.PaiHangBangActivity;
import com.test720.grasshoppercollege.module.userData.SetActivity;
import com.test720.grasshoppercollege.module.userData.TuiJianActivity;
import com.test720.grasshoppercollege.module.userData.XiaoFeiActivity;
import com.test720.grasshoppercollege.module.userData.caiWu.CaiWuActivity;
import com.test720.grasshoppercollege.module.userData.geRenZiLiao.PersonActivity;
import com.test720.grasshoppercollege.module.userData.myTeam.team.MyTeamActivity;
import com.test720.grasshoppercollege.module.userData.vip.GouMaiVipActivity;
import com.test720.grasshoppercollege.module.userData.vip.VipQuanYiActivity;
import com.test720.grasshoppercollege.module.userData.youHuiJuan.MyYouHuiJuanActivity;
import com.test720.grasshoppercollege.myViews.RingView;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.WidgetController;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * _oo0oo_
 * o8888888o
 * 88" . "88
 * (| -_- |)
 * 0\  =  /0
 * ___/`---'\___
 * .' \\|     |// '.
 * / \\|||  :  |||// \
 * / _||||| -卍-|||||- \
 * |   | \\\  -  /// |   |
 * | \_|  ''\---/''  |_/ |
 * \  .-\__  '-'  ___/-. /
 * ___'. .'  /--.--\  `. .'___
 * ."" '<  `.___\_<|>_/___.' >' "".
 * | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 * \  \ `_.   \_ __\ /__ _/   .-` /  /
 * =====`-.____`.___ \_____/___.-`___.-'=====
 * `=---='
 * 佛祖保佑        永无BUG
 * 佛曰:
 * 程序园里程序天，程序天里程序员；
 * 程序猿人写程序，又拿程序换肉钱。
 * 肉饱继续桌前坐，饱暖还是桌前眠；
 * 半迷半醒日复日，码上码下年复年。
 * 但愿叱咤互联世，不愿搬砖码当前；
 * 诸葛周瑜算世事，我来算出得加钱。
 * 别人笑我忒直男，我笑自己太像猿；
 * 但见成都府国内，处处地地程序员。
 * Created by 水东流 on 2018/3/19 0019.
 */

public class MyFragment extends BaseFragment {
    @BindView(R.id.set)
    ImageView set;
    @BindView(R.id.header)
    RoundedImageView header;
    @BindView(R.id.ringView)
    RingView ringView;
    @BindView(R.id.center)
    View center;
    @BindView(R.id.lin1)
    LinearLayout lin1;
    @BindView(R.id.layout_ranking)
    RelativeLayout layoutRanking;
    @BindView(R.id.myIder)
    LinearLayout myIder;
    @BindView(R.id.zong)
    TextView zong;
    @BindView(R.id.yitixian)
    TextView yitixian;
    @BindView(R.id.ketixian)
    TextView ketixian;
    @BindView(R.id.xiaofei)
    LinearLayout xiaofei;
    @BindView(R.id.youhuijuan)
    LinearLayout youhuijuan;
    Unbinder unbinder;
    List<Integer> colorList;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.vipImg)
    ImageView vipImg;
    @BindView(R.id.myteam)
    LinearLayout myteam;

    @Override
    public void initData() {
        // 添加的是颜色
        colorList = new ArrayList<>();
        colorList.add(R.color.red);
        colorList.add(R.color.green);


        //  添加的是百分比
        List<Float> rateList = new ArrayList<>();
        rateList.add(10f);
        rateList.add(90f);
        ringView.setShow(colorList, rateList, false, true);
        WidgetController.setLayoutX(ringView, -220);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!MyApplication.getmInstance().getUid().equals("")) {
            HttpParams httpParams = new HttpParams();
            httpParams.put("uid", MyApplication.getmInstance().getUid());//请求参数三
            post(HttpUntil.GetIntent().info(), httpParams, 1);
        }
    }

    @Override
    public void setListener() {

    }

    @Override
    public int setlayoutResID() {
        return R.layout.fragment_my;
    }

    @Override
    public void getSuccess(String s, int what) {
        switch (what) {
            case 1:
                if (codeIsOne(s, false)) {
                    MyApplication.getmInstance().userData = new Gson().fromJson(s, UserData.class);
                    setData();
                }
                break;

        }

    }

    /*展示个人信息F*/
    private void setData() {
        float zongf = Float.parseFloat(MyApplication.getmInstance().userData.getData().getIncome_money());
        float keyongf = Float.parseFloat(MyApplication.getmInstance().userData.getData().getMoney());
        float yitif = Float.parseFloat(MyApplication.getmInstance().userData.getData().getDraw_money());

        String z = "总收入：" + zongf;
        String k = "可提现：" + keyongf;
        String y = "已提现：" + yitif;
        zong.setText(z);
        ketixian.setText(k);
        yitixian.setText(y);
        float yu = (keyongf * 100) / zongf;
        float over = ((yitif * 100) / zongf);
        if (zongf == 0) {
            yu = 100;
            over = 0;
        }
        //  添加的是百分比
        List<Float> rateList = new ArrayList<>();
        rateList.add(over);
        rateList.add(yu);
        ringView.setShow(colorList, rateList, false, true);
        WidgetController.setLayoutX(ringView, -220);

        //个人信息
        Glide.with(this).load(MyApplication.getmInstance().userData.getData().getHeader()).error(R.mipmap.pic_head_default).into(header);
        name.setText(MyApplication.getmInstance().userData.getData().getNickname());
        if (MyApplication.getmInstance().userData.getData().getVip().equals("0")) {//非vip
            vipImg.setImageResource(R.mipmap.novip);
        } else {
            vipImg.setImageResource(R.mipmap.vip);
        }
        /*如果为1则显示我的团队*/
        if (MyApplication.getmInstance().userData.getData().getTeam_status().equals("1")) {
            myteam.setVisibility(View.VISIBLE);
        } else {
            myteam.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.daiFaHuo, R.id.daiShouHuo, R.id.daiFuKuan, R.id.share, R.id.vip_lin, R.id.tui_jian_er, R.id.caiwu, R.id.header, R.id.wodekajuan, R.id.myteam, R.id.doudou, R.id.huayuan, R.id.rongyuqiang, R.id.youhuijuan, R.id.xiaofei, R.id.paihangbang, R.id.set, R.id.layout_ranking, R.id.myIder})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.caiwu:
                jumpToActivity(CaiWuActivity.class, false);
                break;
            case R.id.header:
                jumpToActivity(PersonActivity.class, false);
                break;
            case R.id.youhuijuan:
                jumpToActivity(MyYouHuiJuanActivity.class, false);
                break;
            case R.id.xiaofei:
                jumpToActivity(XiaoFeiActivity.class, false);
                break;
            case R.id.paihangbang:
                jumpToActivity(PaiHangBangActivity.class, false);
                break;
            case R.id.doudou:

                break;
            case R.id.vip_lin:
                if (MyApplication.getmInstance().userData.getData().getVip().equals("0")) {//非vip
                    jumpToActivity(GouMaiVipActivity.class, false);
                } else {
                    jumpToActivity(VipQuanYiActivity.class, false);
                }
                break;
            case R.id.huayuan:

                break;
            case R.id.rongyuqiang:

                break;
            case R.id.wodekajuan:

                break;
            case R.id.myteam:
                jumpToActivity(MyTeamActivity.class, false);
                break;
            case R.id.tui_jian_er://推荐二维码
                jumpToActivity(TuiJianActivity.class, false);
                break;
            case R.id.set:
                jumpToActivity(SetActivity.class, false);
                break;
            case R.id.layout_ranking:
                break;
            case R.id.share:
                share();
                break;
        }
    }

}
