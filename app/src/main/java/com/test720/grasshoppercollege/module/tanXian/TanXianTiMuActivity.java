package com.test720.grasshoppercollege.module.tanXian;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.BaseTiMuActivity;
import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.tanXian.TanXianDialog.BreakThroughDialog;
import com.test720.grasshoppercollege.module.tanXian.TanXianDialog.SuccessfulEntryDialog;
import com.test720.grasshoppercollege.module.tanXian.TypeOfQuestion.BaseTanXianTiMuFragment;
import com.test720.grasshoppercollege.module.tanXian.TypeOfQuestion.XieHouYuLianXi.XieHouYuLianXiFragment;
import com.test720.grasshoppercollege.module.tanXian.TypeOfQuestion.XieHouYuLianXi.XieHouYuTongGuanFragment;
import com.test720.grasshoppercollege.module.tanXian.TypeOfQuestion.bianyin.BinYinFragment;
import com.test720.grasshoppercollege.module.tanXian.TypeOfQuestion.chengYu.BuZiFragment;
import com.test720.grasshoppercollege.module.tanXian.TypeOfQuestion.englishBaiBian.BaiBianFourFragment;
import com.test720.grasshoppercollege.module.tanXian.TypeOfQuestion.englishBaiBian.BaiBianOneFragment;
import com.test720.grasshoppercollege.module.tanXian.TypeOfQuestion.englishBaiBian.BaiBianTwoFragment;
import com.test720.grasshoppercollege.module.tanXian.TypeOfQuestion.englishBaiBian.BianBianThreeFragment;
import com.test720.grasshoppercollege.module.tanXian.TypeOfQuestion.englishFanYi.DanXuanFragment;
import com.test720.grasshoppercollege.module.tanXian.TypeOfQuestion.englishFanYi.TiankongFragment;
import com.test720.grasshoppercollege.module.tanXian.TypeOfQuestion.shuXueKouSuan.KouSuanOneFragment;
import com.test720.grasshoppercollege.module.tanXian.TypeOfQuestion.shuXueKouSuan.KouSuantwoFragment;
import com.test720.grasshoppercollege.module.tanXian.bean.TanXianTiMuBean;
import com.test720.grasshoppercollege.myViews.MainViewPager;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.musicMedia.MusicUntil;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class TanXianTiMuActivity extends BaseTiMuActivity {

    @BindView(R.id.viewPager)
    MainViewPager viewPager;

    MusicUntil tanXianMusic = new MusicUntil();//探险成功或者失败配音
    String checkpointId = "";
    List<BaseTanXianTiMuFragment> list = new ArrayList();
    TanXianTiMuBean bean;
    SuccessfulEntryDialog successfulEntryDialog;
    BreakThroughDialog breakThroughDialog;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tanXianMusic = null;
        list.clear();
        bean = null;
    }

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.tan_xian_ti_mu_activity;
    }

    @Override
    protected void initData() {
        viewPager.setOffscreenPageLimit(0);//不设缓冲
        HttpParams httpParams = new HttpParams();
        if (getIntent().getStringExtra("checkpoint_id") != null) {
            checkpointId = getIntent().getStringExtra("checkpoint_id");
            httpParams.put("checkpoint_id", checkpointId);
        }
        postResponse(HttpUntil.GetIntent().getIP() + "small.php/Adventure/checkpointQuestion", httpParams, 1, true);
    }


    @Override
    public void GetHttpBackStr(String str, int what) {
        switch (what) {
            case 1:
                addFragment(str);
                break;
            case 11:
                if (codeIsOne(str, false)) {
                    CurrencyEvent currencyEvent = new CurrencyEvent(CurrencyEvent.TANXIANTONGGUANOK, "通关");
                    EventBus.getDefault().post(currencyEvent);
                    String s = getFen() + "分";
                    try {
                        JSONObject jsonObject = new JSONObject(str);
                        if (jsonObject.getString("msg").equals("闯关成功") || jsonObject.getString("msg").equals("")) {
                            successfulEntryDialog = new SuccessfulEntryDialog();
                            successfulEntryDialog.setFenStr(s);
                            successfulEntryDialog.show(getSupportFragmentManager(), "success");
                            tanXianMusic.playRaw(mcontext, R.raw.tanxianchenggong);
                        } else {
                            breakThroughDialog = new BreakThroughDialog();
                            breakThroughDialog.setStrFen(s);
                            breakThroughDialog.show(getSupportFragmentManager(), "break");
                            tanXianMusic.playRaw(mcontext, R.raw.tanxianshibai);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }


    }

    private void addFragment(String str) {
        if (codeIsOne(str, false)) {
            bean = new Gson().fromJson(str, TanXianTiMuBean.class);
            list.clear();
            for (int i = 0; i < bean.getData().size(); i++) {
                TanXianTiMuBean.DataBean dataBean = bean.getData().get(i);
                switch (dataBean.getType()) {
                    case "12"://翻译闯关
                        switch (dataBean.getQuestion_type()) {
                            case "1"://选择
                                DanXuanFragment danXuanFragment = new DanXuanFragment();
                                danXuanFragment.setBean(dataBean);
                                danXuanFragment.setBaseTiMuActivity(this);
                                list.add(danXuanFragment);
                                break;
                            case "2"://判断
                                break;
                            case "3"://填空
                                TiankongFragment tiankongFragment = new TiankongFragment();
                                tiankongFragment.setBean(dataBean);
                                tiankongFragment.setBaseTiMuActivity(this);
                                list.add(tiankongFragment);
                                break;
                        }
                        break;
                    case "14"://百变听力
                        switch (dataBean.getFoot()) {
                            case "1":
                                BaiBianOneFragment baiBianOneFragment = new BaiBianOneFragment();
                                baiBianOneFragment.setBean(dataBean);
                                baiBianOneFragment.setBaseTiMuActivity(this);
                                list.add(baiBianOneFragment);
                                break;
                            case "2":
                                BaiBianTwoFragment baiBianTwoFragment = new BaiBianTwoFragment();
                                baiBianTwoFragment.setBean(dataBean);
                                baiBianTwoFragment.setBaseTiMuActivity(this);
                                list.add(baiBianTwoFragment);
                                break;
                            case "3":
                                BianBianThreeFragment bianBianThreeFragment = new BianBianThreeFragment();
                                bianBianThreeFragment.setBean(dataBean);
                                bianBianThreeFragment.setBaseTiMuActivity(this);
                                list.add(bianBianThreeFragment);
                                break;
                            case "4":
                            case "5":
                                BaiBianFourFragment baiBianFourFragment = new BaiBianFourFragment();
                                baiBianFourFragment.setBean(dataBean);
                                baiBianFourFragment.setBaseTiMuActivity(this);
                                list.add(baiBianFourFragment);
                                break;
                        }


                        break;
                    case "17"://数学应用
                        switch (dataBean.getQuestion_type()) {
                            case "1"://选择
                                com.test720.grasshoppercollege.module.tanXian.TypeOfQuestion.shuXueYingYong.DanXuanFragment danXuanFragment = new com.test720.grasshoppercollege.module.tanXian.TypeOfQuestion.shuXueYingYong.DanXuanFragment();
                                danXuanFragment.setBean(dataBean);
                                danXuanFragment.setBaseTiMuActivity(this);
                                list.add(danXuanFragment);
                                break;
                            case "2"://判断
                                break;
                            case "3"://填空
                                break;
                        }
                        break;
                    case "18"://数学奥数练习
                        com.test720.grasshoppercollege.module.tanXian.TypeOfQuestion.shuXueAoShuLianXi.DanXuanFragment danXuanFragment = new com.test720.grasshoppercollege.module.tanXian.TypeOfQuestion.shuXueAoShuLianXi.DanXuanFragment();
                        danXuanFragment.setBean(dataBean);
                        danXuanFragment.setBaseTiMuActivity(this);
                        list.add(danXuanFragment);
                        break;
                    case "21"://英语单词辩音
                        switch (dataBean.getQuestion_type()) {
                            case "1":
                                com.test720.grasshoppercollege.module.tanXian.TypeOfQuestion.bianyin.DanXuanFragment danXuanFragment1 = new com.test720.grasshoppercollege.module.tanXian.TypeOfQuestion.bianyin.DanXuanFragment();
                                danXuanFragment1.setBean(dataBean);
                                danXuanFragment1.setBaseTiMuActivity(this);
                                list.add(danXuanFragment1);
                                break;
                            case "2":
                                BinYinFragment binYinFragment = new BinYinFragment();
                                binYinFragment.setBean(dataBean);
                                binYinFragment.setBaseTiMuActivity(this);
                                list.add(binYinFragment);
                                break;
                        }

                        break;
                    case "22"://数学口算
                        switch (dataBean.getQuestion_content_type()) {
                            case "1":
                                KouSuanOneFragment kouSuanOneFragment = new KouSuanOneFragment();
                                kouSuanOneFragment.setBean(dataBean);
                                kouSuanOneFragment.setBaseTiMuActivity(this);
                                list.add(kouSuanOneFragment);
                                break;
                            case "2":
                                KouSuantwoFragment kouSuantwoFragment = new KouSuantwoFragment();
                                kouSuantwoFragment.setBean(dataBean);
                                kouSuantwoFragment.setBaseTiMuActivity(this);
                                list.add(kouSuantwoFragment);
                                break;
                        }
                        break;
                    case "23":// 英语单词听写
                        switch (dataBean.getQuestion_type()) {
                            case "1"://选择
                                com.test720.grasshoppercollege.module.tanXian.TypeOfQuestion.englishDanCiTingXie.DanXuanFragment danXuanFragment1 = new com.test720.grasshoppercollege.module.tanXian.TypeOfQuestion.englishDanCiTingXie.DanXuanFragment();
                                danXuanFragment1.setBean(dataBean);
                                danXuanFragment1.setBaseTiMuActivity(this);
                                list.add(danXuanFragment1);
                                break;
                            case "2"://判断
                                break;
                            case "3"://填空
                                com.test720.grasshoppercollege.module.tanXian.TypeOfQuestion.englishDanCiTingXie.TiankongFragment tiankongFragment = new com.test720.grasshoppercollege.module.tanXian.TypeOfQuestion.englishDanCiTingXie.TiankongFragment();
                                tiankongFragment.setBean(dataBean);
                                tiankongFragment.setBaseTiMuActivity(this);
                                list.add(tiankongFragment);
                                break;
                        }

                        break;
                    case "25"://语文近反义词
                        com.test720.grasshoppercollege.module.tanXian.TypeOfQuestion.yuWenJinFan.DanXuanFragment danXuanFragment1 = new com.test720.grasshoppercollege.module.tanXian.TypeOfQuestion.yuWenJinFan.DanXuanFragment();
                        danXuanFragment1.setBean(dataBean);
                        danXuanFragment1.setBaseTiMuActivity(this);
                        list.add(danXuanFragment1);
                        break;
                    case "26"://语文歇后语练习
                        XieHouYuLianXiFragment xieHouYuLianXiFragment = new XieHouYuLianXiFragment();
                        xieHouYuLianXiFragment.setBean(dataBean);
                        xieHouYuLianXiFragment.setBaseTiMuActivity(this);
                        list.add(xieHouYuLianXiFragment);
                        break;
                    case "31"://成语接龙，成语补字
                        BuZiFragment buZiFragment = new BuZiFragment();
                        buZiFragment.setBean(dataBean);
                        buZiFragment.setBaseTiMuActivity(this);
                        list.add(buZiFragment);
                        break;
                    case "29"://歇后语，通关
                        XieHouYuTongGuanFragment xieHouYuTongGuanFragment = new XieHouYuTongGuanFragment();
                        xieHouYuTongGuanFragment.setBean(dataBean);
                        xieHouYuTongGuanFragment.setBaseTiMuActivity(this);
                        list.add(xieHouYuTongGuanFragment);

                        break;
                    case "32"://成语接龙，看图猜成语
                        BuZiFragment buZiFragment1 = new BuZiFragment();
                        buZiFragment1.setBean(dataBean);
                        buZiFragment1.setBaseTiMuActivity(this);
                        list.add(buZiFragment1);


                        break;
                    case "33"://成语接龙，看中午猜成语
                        BuZiFragment buZiFragment2 = new BuZiFragment();
                        buZiFragment2.setBean(dataBean);
                        buZiFragment2.setBaseTiMuActivity(this);
                        list.add(buZiFragment2);


                        break;

                }

            }
            viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
                @Override
                public Fragment getItem(int position) {
                    return list.get(position);
                }

                @Override
                public int getCount() {
                    return list.size();
                }
            });
        }
    }


    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }

    boolean isAdd = false;

    @Override
    public void onEvent(CurrencyEvent currencyEvent) {
        if (currencyEvent.getWhat() == CurrencyEvent.TANXIANTIMU) {
            successfulEntryDialog = null;
            breakThroughDialog = null;
            if (viewPager.getAdapter() == null) return;
            if (viewPager.getCurrentItem() < viewPager.getAdapter().getCount() - 1) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            } else {
                if (isAdd) return;
                HttpParams httpParams = new HttpParams();
                httpParams.put("checkpoint_id", checkpointId);
                httpParams.put("uid", MyApplication.getmInstance().getUid());
                httpParams.put("fraction", getFen());
                postResponse(HttpUntil.GetIntent().getIP() + "small.php/Adventure/adopt", httpParams, 11, true);
                isAdd = true;
            }
        }

        //做题结束
        if (currencyEvent.getWhat() == CurrencyEvent.TANXIANZUOTIOK) {
            if (currencyEvent.getMsg().equals("ok")) {
                finish();
            }
        }

    }

}
