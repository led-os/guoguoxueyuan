package com.test720.grasshoppercollege;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.HttpBean.GouMaikeChengData;
import com.test720.grasshoppercollege.HttpBean.YuPlayData;
import com.test720.grasshoppercollege.module.userData.youHuiJuan.YouHuiJuanChooseActivity;
import com.test720.grasshoppercollege.myViews.PayTypeDialog;
import com.test720.grasshoppercollege.myViews.PlayByYuEDialog;
import com.test720.grasshoppercollege.untils.HttpUntil;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;

public class BuyClassActivity extends BasePlayActivity {


    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.see)
    TextView see;
    @BindView(R.id.textView4)
    TextView textView4;
    @BindView(R.id.allPice)
    TextView allPice;
    @BindView(R.id.you_hui_num)
    TextView you_hui_num;
    @BindView(R.id.ok)
    Button ok;
    @BindView(R.id.gouCheck)
    CheckBox gouCheck;

    @BindView(R.id.modeSw)
    ImageView modeSw;
    @BindView(R.id.monthClass)
    RadioButton monthClass;
    @BindView(R.id.jiDuClass)
    RadioButton jiDuClass;
    @BindView(R.id.banNianClass)
    RadioButton banNianClass;
    @BindView(R.id.yearClass)
    RadioButton yearClass;
    @BindView(R.id.classType)
    RadioGroup classType;
    @BindView(R.id.vipSw)
    ImageView vipSw;
    @BindView(R.id.monthVip)
    RadioButton monthVip;
    @BindView(R.id.jiDuVip)
    RadioButton jiDuVip;
    @BindView(R.id.banNianVip)
    RadioButton banNianVip;
    @BindView(R.id.yearVip)
    RadioButton yearVip;
    @BindView(R.id.vipType)
    LinearLayout vipType;


    @Override
    protected String setTitle() {
        return "支付确认";
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_buy_class;
    }

    @Override
    public float getJinE() {
        return Float.parseFloat(allPice.getText().toString());
    }

    @Override
    public String getPlayWay() {
        return playType;
    }

    @Override
    public void initView() {
        super.initView();
    }

    @Override
    protected void initData() {
        envent();

        type = getIntent().getStringExtra("type");
        curr_type = getIntent().getIntExtra("curr_type", 0);

        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("curr_type", curr_type + "");//请求参数一
        formBuilder.add("type", type);
        formBuilder.add("uid", MyApplication.getmInstance().getUid());
        Post(HttpUntil.GetIntent().payCurr(), formBuilder, 1);
    }

    @Override
    public void onEvent(CurrencyEvent currencyEvent) {
        super.onEvent(currencyEvent);
        if (currencyEvent.getWhat() == CurrencyEvent.PAYOK) {
            EventBus.getDefault().post(new CurrencyEvent(CurrencyEvent.UPDATEFRAGMENT, "更新"));
            setResult(1, new Intent());
            finish();
        }
    }

    /**
     * 事件监听
     */
    private void envent() {
        modeSw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isVip = !isVip;
                vipOrModel();
            }
        });
        vipSw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isVip = !isVip;
                vipOrModel();
            }
        });
        monthClass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    module_type = MONTH;
                    mPrice = data.getData().getPrice().getYue();
                    if (!isVip) {
                        allPice.setText(mPrice);
                    }
                }
            }
        });
        jiDuClass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    module_type = JIDU;
                    mPrice = data.getData().getPrice().getJidu();
                    if (!isVip) {
                        allPice.setText(mPrice);
                    }
                }
            }
        });
        banNianClass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    module_type = BANNIAN;
                    mPrice = data.getData().getPrice().getBannian();
                    if (!isVip) {
                        allPice.setText(mPrice);
                    }
                }
            }
        });
        yearClass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    module_type = YEAR;
                    mPrice = data.getData().getPrice().getYinian();
                    if (!isVip) {
                        allPice.setText(mPrice);
                    }
                }
            }
        });
        monthVip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    vip_type = MONTH;
                    vPrice = data.getData().getVip_price().getYue();
                    priceChange();
                }
            }
        });
        jiDuVip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    vip_type = JIDU;
                    vPrice = data.getData().getVip_price().getJidu();
                    priceChange();
                }
            }
        });
        banNianVip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    vip_type = BANNIAN;
                    vPrice = data.getData().getVip_price().getBannian();
                    priceChange();
                }
            }
        });
        yearVip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    vip_type = YEAR;
                    vPrice = data.getData().getVip_price().getYinian();
                    priceChange();
                }
            }
        });
    }

    /**
     * 计算总价
     */
    private void priceChange() {
        float cid = Float.parseFloat(cidPrice);//vip优惠劵
        float price = Float.parseFloat(mPrice);//模块价
        float vprice = Float.parseFloat(vPrice);//vip价

        if (isVip) {
            float p = vprice - cid;
            allPice.setText(String.valueOf(p));
        } else {
            allPice.setText(String.valueOf(price));
        }
    }

    /**
     * 选择升级vip或者是购买模块
     */
    private void vipOrModel() {
        if (isVip) {
            allPice.setText(vPrice);
            vipSw.setImageResource(R.mipmap.goux);
            modeSw.setImageResource(R.mipmap.wei_xuan_zhong);
            vipType.setVisibility(View.VISIBLE);
            classType.setVisibility(View.GONE);
        } else {
            allPice.setText(mPrice);
            vipSw.setImageResource(R.mipmap.wei_xuan_zhong);
            modeSw.setImageResource(R.mipmap.goux);
            vipType.setVisibility(View.GONE);
            classType.setVisibility(View.VISIBLE);
        }
    }

    @OnClick({R.id.ok, R.id.see, R.id.you_hui_rel, R.id.xieyi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.xieyi:
                Intent intent34 = new Intent(mcontext, WebViewActivity.class);
                intent34.putExtra("title", "用户须知");
                intent34.putExtra("path", HttpUntil.GetIntent().getIP() + "small.php/User/aboutUs/type/14");
                jumpToActivity(intent34, false);
                break;
            case R.id.see:
                Intent intent88 = new Intent(mcontext, WebViewActivity.class);
                intent88.putExtra("title", "vip权益");
                intent88.putExtra("path", HttpUntil.GetIntent().getIP() + "small.php/User/aboutUs/type/6");
                jumpToActivity(intent88, false);
                break;
            case R.id.ok:
                if (!gouCheck.isChecked()) {
                    ShowToast("请查看用户须知");
                    return;
                }
                payTypeDialog = new PayTypeDialog();
                payTypeDialog.setPayBack(new PayTypeDialog.PayBack() {
                    @Override
                    public void payBack(String payType) {
                        playType = payType;
                        playEnvent();
                    }
                });
                payTypeDialog.show(getSupportFragmentManager(), "payType");
                break;
            case R.id.you_hui_rel:
                if (!vip_type.equals(YEAR)) {
                    ShowToast("只有年vip才可使用优惠劵");
                    return;
                }
                Intent intent = new Intent(mcontext, YouHuiJuanChooseActivity.class);
                intent.putExtra("coupon_type", "1");
                startActivityForResult(intent, 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //获得优惠劵id
        if (requestCode == 1 && resultCode == 2) {
            cid = data.getStringExtra("id");
            you_hui_num.setText(data.getStringExtra("name"));
            cidPrice = data.getStringExtra("price");
            priceChange();
        }
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        super.GetHttpBackStr(str, what);
        switch (what) {
            case 1:
                if (codeIsOne(str, false)) {
                    data = new Gson().fromJson(str, GouMaikeChengData.class);
                    title.setText(data.getData().getTitle());
                    String t = data.getData().getDesc();
                    textView4.setText(t);

                    vPrice = data.getData().getVip_price().getYinian();
                    mPrice = data.getData().getPrice().getYinian();
                    allPice.setText(mPrice);

                    //模块价格
                    String m = "￥" + data.getData().getPrice().getYue() + "/月";
                    String j = "￥" + data.getData().getPrice().getJidu() + "/季度";
                    String c = "￥" + data.getData().getPrice().getBannian() + "/半年";
                    String y = "￥" + data.getData().getPrice().getYinian() + "/年";
                    jiDuClass.setText(j);
                    banNianClass.setText(c);
                    monthClass.setText(m);
                    yearClass.setText(y);

                    //vip价格
                    String vm = "￥" + data.getData().getVip_price().getYue() + "/月";
                    String vj = "￥" + data.getData().getVip_price().getJidu() + "/季度";
                    String vc = "￥" + data.getData().getVip_price().getBannian() + "/半年";
                    String vy = "￥" + data.getData().getVip_price().getYinian() + "/年";
                    monthVip.setText(vm);
                    jiDuVip.setText(vj);
                    banNianVip.setText(vc);
                    yearVip.setText(vy);
                } else {
                    ShowToast("暂无购买信息");
                    finish();
                }
                break;
            case 3:
                if (codeIsOne(str)) {//余额购买
                    YuPlayData data = new Gson().fromJson(str, YuPlayData.class);
                    if (data.getData().getStatus() == 1) {
                        EventBus.getDefault().post(new CurrencyEvent(CurrencyEvent.PAYOK, "更新"));
                    }
                }
                break;
        }
    }

    @Override
    public void yuEZhiFu(final String data) {
        PlayByYuEDialog dialog = new PlayByYuEDialog();
        dialog.setMoneyStr(allPice.getText().toString());
        //余额支付
        if (!isVip) {
            dialog.setPlayOk(new PlayByYuEDialog.PlayOk() {
                @Override
                public void playOk(String pwd) {
                    FormBody.Builder builder = new FormBody.Builder();
                    builder.add("pay_password", pwd);
                    builder.add("order_num", data);
                    builder.add("uid", MyApplication.getmInstance().getUid());
                    builder.add("token", MyApplication.getmInstance().token);
                    Post(HttpUntil.GetIntent().balancePayCurr(), builder, 3);
                }

                @Override
                public void close() {

                }
            });
            dialog.show(getSupportFragmentManager(), "play");
        } else {
            //余额支付
            dialog.setPlayOk(new PlayByYuEDialog.PlayOk() {
                @Override
                public void playOk(String pwd) {
                    FormBody.Builder builder = new FormBody.Builder();
                    builder.add("pay_pwd", pwd);
                    builder.add("order_num", data);
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
     * 支付方法,
     */
    private void playEnvent() {
        if (!isVip)
            getOutTradeNo(type, null, curr_type + "", null, module_type, null);
        else
            getOutTradeNo("1", cid, curr_type + "", null, null, vip_type);
    }

    /* 购买vip*/
    boolean isVip = false;//是否购买vip
    String playType = "1";//充值方式 1，支付宝，2微信, 3余额
    String type = "";//模块或课程
    String cid = "";//购买vip选择的优惠劵
    String module_type = "yinian";//模块或课程
    String vip_type = "yinian";//模块或课程
    int curr_type = 0;//模块或课程id

    String vPrice = "0";//模块价格
    String mPrice = "0";//
    String cidPrice = "0";//优惠劵价格


    PayTypeDialog payTypeDialog;
    GouMaikeChengData data;

    final static String YEAR = "yinian";
    final static String MONTH = "yue";
    final static String JIDU = "jidu";
    final static String BANNIAN = "bannian";
}
