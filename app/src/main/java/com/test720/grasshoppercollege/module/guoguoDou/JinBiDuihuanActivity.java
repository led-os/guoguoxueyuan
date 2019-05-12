package com.test720.grasshoppercollege.module.guoguoDou;

import android.content.Intent;
import android.graphics.Typeface;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.GameActivity;
import com.test720.grasshoppercollege.HttpBean.HuaYuanData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.WebViewActivity;
import com.test720.grasshoppercollege.module.guoguoDou.bean.JinBiDuiHuanData;
import com.test720.grasshoppercollege.module.qianDao.QianDaoShouActivity;
import com.test720.grasshoppercollege.module.userData.vip.GouMaiVipActivity;
import com.test720.grasshoppercollege.myViews.TanXianGuankaView;
import com.test720.grasshoppercollege.myViews.TextDialog;
import com.test720.grasshoppercollege.untils.HttpUntil;

import butterknife.BindView;
import butterknife.OnClick;

public class JinBiDuihuanActivity extends BaseToolActivity {


    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.oneGold)
    TextView oneGold;
    @BindView(R.id.oneDou)
    TextView oneDou;
    @BindView(R.id.one)
    LinearLayout one;
    @BindView(R.id.twoGold)
    TextView twoGold;
    @BindView(R.id.twoDou)
    TextView twoDou;
    @BindView(R.id.two)
    LinearLayout two;
    @BindView(R.id.threeGold)
    TextView threeGold;
    @BindView(R.id.threeDou)
    TextView threeDou;
    @BindView(R.id.three)
    LinearLayout three;
    @BindView(R.id.fourGold)
    TextView fourGold;
    @BindView(R.id.fourDou)
    TextView fourDou;
    @BindView(R.id.four)
    LinearLayout four;
    @BindView(R.id.oneGoldV)
    TextView oneGoldV;
    @BindView(R.id.oneDouV)
    TextView oneDouV;
    @BindView(R.id.oneV)
    LinearLayout oneV;
    @BindView(R.id.twoGoldV)
    TextView twoGoldV;
    @BindView(R.id.twoDouV)
    TextView twoDouV;
    @BindView(R.id.twoV)
    LinearLayout twoV;
    @BindView(R.id.threeGoldV)
    TextView threeGoldV;
    @BindView(R.id.threeDouV)
    TextView threeDouV;
    @BindView(R.id.threeV)
    LinearLayout threeV;
    @BindView(R.id.fourGoldV)
    TextView fourGoldV;
    @BindView(R.id.fourDouV)
    TextView fourDouV;
    @BindView(R.id.fourV)
    LinearLayout fourV;
    @BindView(R.id.getVip)
    TextView getVip;
    JinBiDuiHuanData duiHuanData;
    @BindView(R.id.feivipYun)
    TanXianGuankaView feivip;
    @BindView(R.id.vipYun)
    TanXianGuankaView vip;

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_jin_bi_duihuan;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (MyApplication.getmInstance().userData.getData().getVip().equals("1")) {
            getVip.setText("已经是vip啦");
        } else {
            getVip.setText("立即升级vip");
        }
    }

    @Override
    protected void initData() {
        Typeface fontFace = Typeface.createFromAsset(mcontext.getAssets(),
                "fonts/hkhbt.ttf");
        title.setTypeface(fontFace);

        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        postResponse(HttpUntil.GetIntent().getIP() + "small.php/User/getExchangeGoldInfo", httpParams, 1, true);
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        switch (what) {
            case 1:
                initData(str);
                break;
            case 2:
                if (codeIsOne(str)) {
                    GongXiDialog gongXiDialog = new GongXiDialog();
                    gongXiDialog.setJinBi(g);
                    gongXiDialog.setOkClickListener(new GongXiDialog.OkClickListener() {
                        @Override
                        public void ok() {
                            goNongChang();
                        }
                    });
                    gongXiDialog.show(getSupportFragmentManager(), "gx");
                }
                break;
            case 9:
                if (codeIsOne(str, false)) {
                    HuaYuanData huaYuanData = new Gson().fromJson(str, HuaYuanData.class);
                    Intent intent = new Intent(mcontext, GameActivity.class);
                    intent.putExtra("path", huaYuanData.getData().getLogin_url());
                    intent.putExtra("time", huaYuanData.getData().getRemaining_time());
                    jumpToActivity(intent, false);
                } else {
                    if (getJsonCode(str, true) == 7) {
                        Intent intent = new Intent(mcontext, QianDaoShouActivity.class);
                        jumpToActivity(intent, true);
                    }
                }
                break;
        }

    }

    private void initData(String str) {
        if (codeIsOne(str, false)) {
            duiHuanData = new Gson().fromJson(str, JinBiDuiHuanData.class);
            if (duiHuanData.getData().getVip() == 1) {
                vip.setVisibility(View.GONE);
                feivip.setVisibility(View.VISIBLE);
            } else {
                vip.setVisibility(View.VISIBLE);
                feivip.setVisibility(View.GONE);
            }
            for (int i = 0; i < duiHuanData.getData().getExchange().size(); i++) {
                JinBiDuiHuanData.DataBean.ExchangeBean bean = duiHuanData.getData().getExchange().get(i);
                String g = bean.getGold() + "金币";
                String d = bean.getPoints() + "颗果果豆";
                switch (i) {
                    case 0:
                        oneGold.setText(g);
                        oneDou.setText(d);
                        break;
                    case 1:
                        twoGold.setText(g);
                        twoDou.setText(d);
                        break;
                    case 2:
                        threeGold.setText(g);
                        threeDou.setText(d);
                        break;
                    case 3:
                        fourGold.setText(g);
                        fourDou.setText(d);
                        break;
                }
            }


            for (int j = 0; j < duiHuanData.getData().getVip_exchange().size(); j++) {
                JinBiDuiHuanData.DataBean.VipExchangeBean bean = duiHuanData.getData().getVip_exchange().get(j);
                String g = bean.getGold() + "金币";
                String d = bean.getPoints() + "颗果果豆";
                switch (j) {
                    case 0:
                        oneGoldV.setText(g);
                        oneDouV.setText(d);
                        break;
                    case 1:
                        twoGoldV.setText(g);
                        twoDouV.setText(d);
                        break;
                    case 2:
                        threeGoldV.setText(g);
                        threeDouV.setText(d);
                        break;
                    case 3:
                        fourGoldV.setText(g);
                        fourDouV.setText(d);
                        break;
                }
            }
        }
    }


    @OnClick({R.id.feivipYun, R.id.vipYun, R.id.back, R.id.one, R.id.two, R.id.three, R.id.four, R.id.oneV, R.id.twoV, R.id.threeV, R.id.fourV, R.id.getVip})
    public void onViewClicked(View view) {
        if (duiHuanData == null && view.getId() != R.id.back) return;
        switch (view.getId()) {
            case R.id.feivipYun:

                break;
            case R.id.vipYun:
                ShowToast("此优惠只限VIP用户哦!");
                break;
            case R.id.back:
                finish();
                break;
            case R.id.one:
                exchange(1, false);
                break;
            case R.id.two:
                exchange(2, false);
                break;
            case R.id.three:
                exchange(3, false);
                break;
            case R.id.four:
                exchange(4, false);
                break;
            case R.id.oneV:
                exchange(1, true);
                break;
            case R.id.twoV:
                exchange(2, true);
                break;
            case R.id.threeV:
                exchange(3, true);
                break;
            case R.id.fourV:
                exchange(4, true);
                break;
            case R.id.getVip:
                if (duiHuanData.getData().getVip() != 1)
                    jumpToActivity(GouMaiVipActivity.class, false);
                else {
                    Intent intent = new Intent(mcontext, WebViewActivity.class);
                    intent.putExtra("path", HttpUntil.GetIntent().getIP() + "small.php/User/aboutUs/type/6");
                    intent.putExtra("title", "vip权益");
                    jumpToActivity(intent, false);
                }
                break;
        }
    }

    /**
     * 前往农场
     */
    private void goNongChang() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        postResponse(HttpUntil.GetIntent().getIP() + "small.php/Farm/index", httpParams, 9, true);
    }

    /**
     * @param i        标示
     * @param b，是否为vip
     */
    String id, g, d;

    private void exchange(int i, boolean b) {
        if (b && duiHuanData.getData().getVip() != 1) {
            ShowToast("此优惠只限VIP用户哦!");
            return;
        }
        if (!b && duiHuanData.getData().getVip() == 1) {
            return;
        }
        if (b) {
            id = duiHuanData.getData().getVip_exchange().get(i - 1).getExchange_id();
            g = duiHuanData.getData().getVip_exchange().get(i - 1).getGold();
            d = duiHuanData.getData().getVip_exchange().get(i - 1).getPoints();
        } else {
            id = duiHuanData.getData().getExchange().get(i - 1).getExchange_id();
            g = duiHuanData.getData().getExchange().get(i - 1).getGold();
            d = duiHuanData.getData().getExchange().get(i - 1).getPoints();
        }

        TextDialog textDialog = new TextDialog();
        textDialog.setTitleStr("兑换提示");
        textDialog.setOkText("兑换");
        String s = "是否花费" + d + "颗果果豆\n兑换" + g + "金币？";
        textDialog.setTextStr(s);
        textDialog.setTextsize(20);
        textDialog.setOkClick(new TextDialog.OkClick() {
            @Override
            public void click() {
                HttpParams httpParams = new HttpParams();
                httpParams.put("uid", MyApplication.getmInstance().getUid());
                httpParams.put("exchange_id", id);
                postResponse(HttpUntil.GetIntent().getIP() + "small.php/User/exchangeGold", httpParams, 2, true);
            }

            @Override
            public void no() {

            }
        });
        textDialog.show(getSupportFragmentManager(), "text");


    }

}