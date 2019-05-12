package com.test720.grasshoppercollege.fragment;

import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.BaseFragment;
import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.HttpBean.IndexData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.guoguoDou.GuoGuoDouShouActivity;
import com.test720.grasshoppercollege.module.qianDao.QianDaoDialog;
import com.test720.grasshoppercollege.module.qianDao.QianDaoShouActivity;
import com.test720.grasshoppercollege.module.siWeiXuLian.SiWeiXuLianShouActivity;
import com.test720.grasshoppercollege.module.userData.PaiHangBangActivity;
import com.test720.grasshoppercollege.module.userData.xiaoXi.XiaoXiActivity;
import com.test720.grasshoppercollege.module.zhuYiLiXuLian.ZhuYiLiXuLianShouActivity;
import com.test720.grasshoppercollege.myViews.GuoGuoFragmentDialog;
import com.test720.grasshoppercollege.myViews.MainViewPager;
import com.test720.grasshoppercollege.myViews.NianJiDialog;
import com.test720.grasshoppercollege.myViews.ShouYeGuangGaoDialog;
import com.test720.grasshoppercollege.myViews.YouHuiJuanDialog;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.ShenHeUntil;

import org.greenrobot.eventbus.EventBus;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import www.test720.mylibrary.SPUtils;

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
 * Created by 水东流 on 2018/3/21 0021.
 */

public class ChengBaoFragment extends BaseFragment {
    @BindView(R.id.frame)
    MainViewPager frame;
    @BindView(R.id.doudouTxt)
    TextView doudouTxt;
    @BindView(R.id.guoguodou)
    LinearLayout guoguodou;
    @BindView(R.id.qiandao)
    LinearLayout qiandao;
    @BindView(R.id.nianji)
    TextView nianji;
    @BindView(R.id.renwu)
    LinearLayout renwu;
    @BindView(R.id.paihangbang)
    LinearLayout paihangbang;
    public ArrayList<Fragment> fragmentList = new ArrayList<>();
    @BindView(R.id.kemu)
    TextView kemu;
    IndexData data;

    YinYuFragment yinYu = new YinYuFragment();
    YuWenFragment yuwen = new YuWenFragment();
    ShuXueFragment shuXueFragment = new ShuXueFragment();
    SiWeiXuLianFragment siWeiXuLianFragment = new SiWeiXuLianFragment();
    ZhuYiLiFragment zhuYiLiFragment = new ZhuYiLiFragment();
    YouErFragment youErFragment = new YouErFragment();

    @Override
    public void initData() {
        //审核模式
        if (ShenHeUntil.getInstance().isShenHe()) {
            kemu.setVisibility(View.INVISIBLE);
            nianji.setVisibility(View.INVISIBLE);
            guoguodou.setVisibility(View.INVISIBLE);
            qiandao.setVisibility(View.INVISIBLE);
            renwu.setVisibility(View.INVISIBLE);
            paihangbang.setVisibility(View.INVISIBLE);
        }

        frame.setOffscreenPageLimit(0);
        fragmentList.clear();
        fragmentList.add(yinYu);
        fragmentList.add(yuwen);
        fragmentList.add(shuXueFragment);
        fragmentList.add(siWeiXuLianFragment);
        fragmentList.add(zhuYiLiFragment);
        fragmentList.add(youErFragment);
        frame.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });
        /*加载设置*/
        kemu.setText(MyApplication.getmInstance().kemu);
        Drawable img = mContext.getResources().getDrawable(R.mipmap.xiajiantou);
// 调用setCompoundDrawables时，必须调用Drawable.setBounds()方法,否则图片不显示
        img.setBounds(0, 0, img.getMinimumWidth(), img.getMinimumHeight());
        nianji.setCompoundDrawables(null, null, img, null); //设置右图标
        nianji.setText(MyApplication.getmInstance().nianji);

        switch (MyApplication.getmInstance().kemu) {
            case "英语":
                frame.setCurrentItem(0);
                break;
            case "语文":
                frame.setCurrentItem(1);
                break;
            case "数学":
                frame.setCurrentItem(2);
                break;
            case "思维训练":
                break;
            case "注意力训练":
                break;
            case "作业":
//                frame.setCurrentItem(5);
                break;
        }
        if (MyApplication.getmInstance().nianji.equals("幼儿园")) {
            kemu.setVisibility(View.INVISIBLE);
            frame.setCurrentItem(5);
        }

        if (MyApplication.getmInstance().isAppSend && MyApplication.getmInstance().getUid() != null && !MyApplication.getmInstance().getUid().equals("")) {
            HttpParams httpParams = new HttpParams();
            httpParams.put("uid", MyApplication.getmInstance().getUid());//请求参数一
            post(HttpUntil.GetIntent().IndexUserindex(), httpParams, 1, false);
            MyApplication.getmInstance().isAppSend = false;
        }
    }

    @Override
    public void setListener() {

    }


    @Override
    public int setlayoutResID() {
        return R.layout.fragment_cheng_bao;
    }

    @Override
    public void getSuccess(String s, int what) {
        switch (what) {
            case 1://首页包含广告弹框等数据
                if (codeIsOne(s, false)) {
                    data = null;
                    data = new Gson().fromJson(s, IndexData.class);
                    //优惠劵
                    if (data.getData().getCoupon() != null) {
                        youHuiJuanDialog = new YouHuiJuanDialog();
                        youHuiJuanDialog.setCoupon(data.getData().getCoupon());
                        youHuiJuanDialog.show(getChildFragmentManager(), "youhui");
                    }
                    //广告弹框
                    if (data.getData().getInfo() != null) {
                        dialog = new ShouYeGuangGaoDialog();
                        dialog.setUrl(data.getData().getInfo().getLocation_href() + "/uid/" + MyApplication.getmInstance().getUid() + "/id/" + data.getData().getInfo().getMsg_id());
                        dialog.setTimeStr(data.getData().getInfo().getRelease_time());
                        if (youHuiJuanDialog == null) dialog.show(getChildFragmentManager(), "ad");
                    }
                    //签到
                    if (data.getData().getSign().equals("0")) {
                        qianDaoDialog = new QianDaoDialog();
                        if (youHuiJuanDialog == null && dialog == null) {
                            qianDaoDialog.show(getChildFragmentManager(), "qiandao");
                        }
                    }
                }
                break;
        }
    }

    YouHuiJuanDialog youHuiJuanDialog;//优惠劵弹框
    ShouYeGuangGaoDialog dialog;//广告弹框
    QianDaoDialog qianDaoDialog;//签到弹框

    @Override
    public void onEvent(CurrencyEvent event) {
        Log.i("lxl", "event" + event.getWhat());
        if (event.getWhat() == CurrencyEvent.YOUHUIJUAN) {//优惠劵销毁
            youHuiJuanDialog = null;
            if (dialog != null) {
                dialog.show(getChildFragmentManager(), "ad");
            } else {
                EventBus.getDefault().post(new CurrencyEvent("签到", CurrencyEvent.ADDIALOG));//广告销毁
            }
        } else if (event.getWhat() == CurrencyEvent.ADDIALOG) {//通知弹框销毁后
            dialog = null;
            if (qianDaoDialog != null) {
                qianDaoDialog.show(getChildFragmentManager(), "qianDao");
            }
        } else if (event.getWhat() == CurrencyEvent.QIANDAODIALOG && event.getMsg().equals("签到")) {
            qianDaoDialog = null;
        }
    }


    @OnClick({R.id.kemu, R.id.nianji, R.id.guoguodou, R.id.qiandao, R.id.renwu, R.id.paihangbang})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nianji:
                nianjiDialog();
                break;
            case R.id.kemu:
                showDialog();
                break;
            case R.id.guoguodou:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (!MyApplication.getmInstance().getUid().equals("")) {
                    jumpToActivity(GuoGuoDouShouActivity.class, false);
                }
                break;
            case R.id.qiandao:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (!MyApplication.getmInstance().getUid().equals("")) {
                    jumpToActivity(QianDaoShouActivity.class, false);
                }
                break;
            case R.id.renwu:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (!MyApplication.getmInstance().getUid().equals("")) {
                    jumpToActivity(XiaoXiActivity.class, false);
                }
                break;
            case R.id.paihangbang:
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (!MyApplication.getmInstance().getUid().equals("")) {
                    jumpToActivity(PaiHangBangActivity.class, false);
                }
                break;
        }
    }


    /**
     * 年纪弹框
     */
    NianJiDialog nianJiDialog;

    private void nianjiDialog() {
        if (nianJiDialog == null) {
            WeakReference<NianJiDialog> weakReference = new WeakReference<>(new NianJiDialog());
            nianJiDialog = weakReference.get();
            nianJiDialog.setMyDialogClick(new NianJiDialog.NianJiDialogClick() {
                @Override
                public void check(String s, int postion) {
                    Drawable img = getActivity().getResources().getDrawable(R.mipmap.xiajiantou);
// 调用setCompoundDrawables时，必须调用Drawable.setBounds()方法,否则图片不显示
                    img.setBounds(0, 0, img.getMinimumWidth(), img.getMinimumHeight());
                    nianji.setCompoundDrawables(null, null, img, null); //设置右图标
                    nianji.setText(s);
                    MyApplication.getmInstance().nianji = s;
                    SPUtils.putString(getActivity(), "nianji", s);
                    EventBus.getDefault().post(new CurrencyEvent("change", CurrencyEvent.ALLNIANJICHANGE));
                    if (s.equals("幼儿园")) {
                        kemu.setVisibility(View.INVISIBLE);
                        frame.setCurrentItem(6);
                    } else {
                        kemu.setVisibility(View.VISIBLE);
                        switch (MyApplication.getmInstance().kemu) {
                            case "英语":
                                frame.setCurrentItem(0);
                                break;
                            case "语文":
                                frame.setCurrentItem(1);
                                break;
                            case "数学":
                                frame.setCurrentItem(2);
                                break;
                            case "作业":
                                frame.setCurrentItem(5);
                                break;
                        }
                    }
                    nianJiDialog = null;
                }

                @Override
                public void close() {
                    Drawable img = getResources().getDrawable(R.mipmap.xiajiantou);
// 调用setCompoundDrawables时，必须调用Drawable.setBounds()方法,否则图片不显示
                    img.setBounds(0, 0, img.getMinimumWidth(), img.getMinimumHeight());
                    nianji.setCompoundDrawables(null, null, img, null); //设置右图标
                    nianji.setText(MyApplication.getmInstance().nianji);
                    nianJiDialog = null;
                }
            });
            nianJiDialog.show(getChildFragmentManager(), "dialog");
            Drawable img = getResources().getDrawable(R.mipmap.youjiantou);
// 调用setCompoundDrawables时，必须调用Drawable.setBounds()方法,否则图片不显示
            img.setBounds(0, 0, img.getMinimumWidth(), img.getMinimumHeight());
            nianji.setCompoundDrawables(null, null, img, null); //设置右图标
            nianji.setText(MyApplication.getmInstance().nianji);
        } else {
            nianJiDialog.show(getChildFragmentManager(), "dialog");
        }
    }


    /**
     * 判断是否被add过
     * 否则   隐藏当前的fragment，显示下一个
     * 选择课程类型
     */
    GuoGuoFragmentDialog kemuDialog;

    private void showDialog() {
        if (kemuDialog == null) {
            WeakReference<GuoGuoFragmentDialog> weakReference = new WeakReference<>(new GuoGuoFragmentDialog());
            kemuDialog = weakReference.get();
            kemuDialog.setType(GuoGuoFragmentDialog.Type.KEMU);
            kemuDialog.setMyDialogClick(new GuoGuoFragmentDialog.MyDialogClick() {
                @Override
                public void check(String s, int postion) {
                    if (postion == 3) {
                        jumpToActivity(SiWeiXuLianShouActivity.class, false);
                        return;
                    } else if (postion == 4) {
                        jumpToActivity(ZhuYiLiXuLianShouActivity.class, false);
                        return;
                    }
                    frame.setCurrentItem(postion);
                    kemu.setText(s);
                    MyApplication.getmInstance().kemu = s;
                    SPUtils.putString(getActivity(), "kemu", s);
                    kemuDialog = null;
                }

                @Override
                public void close() {
                    kemuDialog = null;
                }
            });
            kemuDialog.show(getChildFragmentManager(), "dialog");
        } else {
            kemuDialog.show(getChildFragmentManager(), "dialog");
        }
    }
}
