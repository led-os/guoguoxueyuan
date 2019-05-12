package com.test720.grasshoppercollege.module.userData.vip;

import android.content.Intent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.BasePlayActivity;
import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.HttpBean.VipInfoData;
import com.test720.grasshoppercollege.HttpBean.YuPlayData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.WebViewActivity;
import com.test720.grasshoppercollege.module.userData.PhoneCodeActivity;
import com.test720.grasshoppercollege.module.userData.youHuiJuan.YouHuiJuanChooseActivity;
import com.test720.grasshoppercollege.myViews.PlayByYuEDialog;
import com.test720.grasshoppercollege.untils.HttpUntil;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;

public abstract class BaseVipActivity extends BasePlayActivity {


    @BindView(R.id.no_vip)
    LinearLayout noVip;
    @BindView(R.id.pice)
    TextView pice;
    @BindView(R.id.you_hui_num)
    TextView youHuiNum;
    @BindView(R.id.you_hui_rel)
    RelativeLayout youHuiRel;
    @BindView(R.id.zaixian)
    TextView zaixian;
    @BindView(R.id.lianXiPinTaiwenzi)
    TextView lianXiPinTaiwenzi;
    @BindView(R.id.jihuoma)
    TextView jihuoma;
    @BindView(R.id.staus1)
    ImageView staus1;
    @BindView(R.id.zhifubao_layout)
    RelativeLayout zhifubaoLayout;
    @BindView(R.id.staus2)
    ImageView staus2;
    @BindView(R.id.staus3)
    ImageView staus3;
    @BindView(R.id.weixin_layout)
    RelativeLayout weixinLayout;
    @BindView(R.id.zaixian_rad)
    LinearLayout zaixianRad;
    @BindView(R.id.jihuoma_lin)
    LinearLayout jihuomaLin;
    @BindView(R.id.play)
    TextView play;
    @BindView(R.id.lin)
    LinearLayout lin;
    @BindView(R.id.monthClass)
    RadioButton monthClass;
    @BindView(R.id.jiDuClass)
    RadioButton jiDuClass;
    @BindView(R.id.banNianClass)
    RadioButton banNianClass;
    @BindView(R.id.yearClass)
    RadioButton yearClass;

    float pc = 0;//实际金额

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_vip;
    }


    @Override
    public float getJinE() {
        return Float.parseFloat(pice.getText().toString());
    }

    @Override
    public String getPlayWay() {
        return playType;
    }

    @Override
    protected void initData() {
        //获取vip信息
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("uid", MyApplication.getmInstance().getUid());
        Post(HttpUntil.GetIntent().vipInfo(), builder, 1);
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        super.GetHttpBackStr(str, what);
        switch (what) {
            case 1://vip信息
                if (codeIsOne(str, false)) {
                    final VipInfoData data = new Gson().fromJson(str, VipInfoData.class);
                    if (lin.getVisibility() == View.GONE) return;//如果是查看权益，则不弹出vip购买
                    lianXiPinTaiwenzi.setText(data.getData().getWenzi());
                    //vip价格
                    String vm = "￥" + data.getData().getVip_price().getYue() + "/月";
                    String vj = "￥" + data.getData().getVip_price().getJidu() + "/季度";
                    String vc = "￥" + data.getData().getVip_price().getBannian() + "/半年";
                    String vy = "￥" + data.getData().getVip_price().getYinian() + "/年";
                    monthClass.setText(vm);
                    jiDuClass.setText(vj);
                    banNianClass.setText(vc);
                    yearClass.setText(vy);

                    vip_type = YEAR;
                    vipPrice = data.getData().getVip_price().getYinian();
                    priceChange();
                    monthClass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (isChecked) {
                                vip_type = MONTH;
                                youHuiRel.setVisibility(View.GONE);
                                vipPrice = data.getData().getVip_price().getYue();
                                priceChange();
                            }
                        }
                    });
                    jiDuClass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (isChecked) {
                                vip_type = JIDU;
                                youHuiRel.setVisibility(View.GONE);
                                vipPrice = data.getData().getVip_price().getJidu();
                                priceChange();
                            }

                        }
                    });
                    banNianClass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (isChecked) {
                                vip_type = BANNIAN;
                                youHuiRel.setVisibility(View.GONE);
                                vipPrice = data.getData().getVip_price().getBannian();
                                priceChange();
                            }

                        }
                    });
                    yearClass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (isChecked) {
                                vip_type = YEAR;
                                youHuiRel.setVisibility(View.VISIBLE);
                                vipPrice = data.getData().getVip_price().getYinian();
                                priceChange();
                            }

                        }
                    });
                }
                break;
            case 3://余额购买vip
                if (codeIsOne(str)) {
                    YuPlayData data = new Gson().fromJson(str, YuPlayData.class);
                    if (data.getData().getStatus() == 1) {
                        finish();
                    }
                }
                break;
            case 9:
                codeIsOne(str);
                break;

        }
    }

    /**
     * 计算价格
     */
    protected void priceChange() {
        float p = Float.parseFloat(vipPrice);
        float c = Float.parseFloat(cidPrice);
        if (vip_type.equals(YEAR)) {
            pc = p - c;
        } else {
            cid = null;
            youHuiNum.setText("选择优惠劵");
            cidPrice = "0";
            pc = p;
        }
        pice.setText(String.valueOf(pc));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //获得优惠劵id
        if (requestCode == 1 && resultCode == 2) {
            cid = data.getStringExtra("id");
            youHuiNum.setText(data.getStringExtra("name"));
            cidPrice = data.getStringExtra("price");
            priceChange();
        }
    }


    @Override
    public void onEvent(CurrencyEvent currencyEvent) {
        super.onEvent(currencyEvent);
        if (currencyEvent.getWhat() == 10000) {
            finish();
        }
    }

    @OnClick({R.id.no_vip, R.id.play, R.id.you_hui_rel, R.id.jihuoma, R.id.zaixian, R.id.zhifubao_layout, R.id.weixin_layout, R.id.ye_layout, R.id.jihuoma_lin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.no_vip:
                Intent intent88 = new Intent(mcontext, WebViewActivity.class);
                intent88.putExtra("title", "vip权益");
                intent88.putExtra("path", HttpUntil.GetIntent().getIP() + "small.php/User/aboutUs/type/6");
                jumpToActivity(intent88, false);
                break;
            case R.id.play:
                if (isPlayByApp) {
                    playEnvent();
                } else {
                    codeBuy();
                }
                break;
            case R.id.you_hui_rel://获取优惠劵
                if (!vip_type.equals(YEAR)) {
                    ShowToast("只有年vip才可使用优惠劵");
                    return;
                }
                Intent intent = new Intent(mcontext, YouHuiJuanChooseActivity.class);
                intent.putExtra("coupon_type", "1");
                startActivityForResult(intent, 1);
                break;
            case R.id.jihuoma:
                jihuomaLin.setVisibility(View.VISIBLE);
                zaixianRad.setVisibility(View.GONE);
                play.setVisibility(View.GONE);
                isPlayByApp = false;
                jihuoma.setBackgroundResource(R.color.bantoumingblue);
                zaixian.setBackgroundResource(R.color.white);
                lianXiPingTai();
                break;
            case R.id.zaixian:
                jihuoma.setBackgroundResource(R.color.white);
                zaixian.setBackgroundResource(R.color.bantoumingblue);
                jihuomaLin.setVisibility(View.GONE);
                zaixianRad.setVisibility(View.VISIBLE);
                play.setText("去支付");
                play.setVisibility(View.VISIBLE);
                isPlayByApp = true;
                break;

            case R.id.zhifubao_layout:
                type(1);
                break;
            case R.id.weixin_layout:
                type(2);
                break;
            case R.id.ye_layout:
                type(3);
                break;
            case R.id.jihuoma_lin:

                break;
        }
    }

    /**
     * 练习平台
     */
    boolean isLianXi = false;

    private void lianXiPingTai() {
        if (isLianXi) return;
        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        postResponse(HttpUntil.GetIntent().getIP() + "small.php/User/contactPlatform", httpParams, 9, false);
        isLianXi = true;
    }

    /*激活码购买vip*/
    private void codeBuy() {
       /* if (TextUtils.isEmpty(codeVip.getText())) {
            codeVip.setError("输入激活码");
            return;
        }
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("code", codeVip.getText().toString());
        builder.add("uid", MyApplication.getmInstance().getUid());*/
    }


    @Override
    public void yuEZhiFu(final String ordernum) {
        if (playType.equals("3")) {
            /*未设置支付密码时，须跳转*/
            if (MyApplication.getmInstance().userData.getData().getIs_pay_password() != 1) {
                jumpToActivity(PhoneCodeActivity.class, false);
                ShowToast("请设置支付密码！");
                return;
            }
            //余额支付
            PlayByYuEDialog dialog = new PlayByYuEDialog();
            dialog.setMoneyStr(pice.getText().toString());
            dialog.setPlayOk(new PlayByYuEDialog.PlayOk() {
                @Override
                public void playOk(String pwd) {
                    FormBody.Builder builder = new FormBody.Builder();
//                    builder.add("code", codeVip.getText().toString());
                    builder.add("pay_pwd", pwd);
                    builder.add("order_num", ordernum);
                    builder.add("uid", MyApplication.getmInstance().getUid());
                    builder.add("token", MyApplication.getmInstance().token);
                    Post(HttpUntil.GetIntent().balancePayVip(), builder, 3);
                }

                @Override
                public void close() {

                }
            });
            dialog.show(getSupportFragmentManager(), "play");
        }
    }

    /**
     * 支付方法
     */
    private void playEnvent() {
        if (vip_type.equals("")) {
            ShowToast("请选择vip类型");
            return;
        }
        getOutTradeNo("1", cid, vip_type);
    }

    /*充值类型选择*/
    private void type(int i) {
        staus1.setImageResource(R.mipmap.huiq);
        staus2.setImageResource(R.mipmap.huiq);
        staus3.setImageResource(R.mipmap.huiq);
        playType = i + "";
        if (i == 1) {
            staus1.setImageResource(R.mipmap.goux);

        } else if (i == 2) {
            staus2.setImageResource(R.mipmap.goux);

        } else if (i == 3) {
            staus3.setImageResource(R.mipmap.goux);

        }
    }


    String playType = "1";//充值方式 1，支付宝，2微信, 3余额
    private boolean isPlayByApp = true;//区分是否是激活码购买
    private String vipPrice = "0";//vip价格
    private String cidPrice = "0";//优惠劵价格
    private String vip_type = "";//vip类型
    String cid = null;//优惠劵id

    final static String YEAR = "yinian";
    final static String MONTH = "yue";
    final static String JIDU = "jidu";
    final static String BANNIAN = "bannian";
}
