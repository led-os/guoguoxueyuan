package com.test720.grasshoppercollege.module.gongLue.dingDan;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.BasePlayActivity;
import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.WebViewActivity;
import com.test720.grasshoppercollege.module.gongLue.GongLueData;
import com.test720.grasshoppercollege.module.userData.MyAdressActivity;
import com.test720.grasshoppercollege.module.userData.youHuiJuan.YouHuiJuanChooseActivity;
import com.test720.grasshoppercollege.myViews.PayTypeDialog;
import com.test720.grasshoppercollege.myViews.PlayByYuEDialog;
import com.test720.grasshoppercollege.untils.HttpUntil;

import org.greenrobot.eventbus.EventBus;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;

public class BuyClassDingDanActivity extends BasePlayActivity {

    String playType = "1";//支付方式 1，支付宝，2微信, 3余额
    float priceF = 0;//支付金额
    String startTime = "";//开始时间
    String endTime = "";//结束时间
    String week = "";//结束时间
    String keyId = "";//课程id
    int day = 0;//周
    String timeSlot = "";//上课的时段
    String selectType = "1";//1-普通购买（包括vip）;2-普通用户购买并升级为vip
    String type = "";//模块
    String vipPrice = "0";//vip价格
    PayTypeDialog payTypeDialog;
    QueRenDingDanData dingDanData;
    String vip_type = null;

    private String cid;//课程优惠劵id
    private String cidprice = "0";//课程优惠劵价格
    private String vipCidprice = "0";//vip优惠劵价格
    private String vipCid;//vip优惠劵

    final static String YEAR = "yinian";
    final static String MONTH = "yue";
    final static String JIDU = "jidu";
    final static String BANNIAN = "bannian";

    @Override
    protected String setTitle() {
        return "确认订单";
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_buy_class_ding_dan;
    }

    @Override
    protected void initData() {
        //线上，线下才显示优惠劵
        if (GongLueData.getInstance().getModularType() == GongLueData.ONLINE
                || GongLueData.getInstance().getModularType() == GongLueData.LINEDOWN) {
            juanRel.setVisibility(View.VISIBLE);
        } else {
            juanRel.setVisibility(View.GONE);
        }

        //是否显示vip购买
        if (MyApplication.getmInstance().userData.getData().getVip().equals("1")) {
            vipLin.setVisibility(View.GONE);
        } else {
            vipLin.setVisibility(View.VISIBLE);
        }

        vipEvent();


        keyId = getIntent().getStringExtra("key_id");
        startTime = getIntent().getStringExtra("startTime");
        endTime = getIntent().getStringExtra("endTime");
        type = getIntent().getStringExtra("type");
        day = getIntent().getIntExtra("day", 1);
        timeSlot = getIntent().getStringExtra("time_slot");
        week = getIntent().getStringExtra("week");

        String time = "";

        if (startTime != null && !startTime.equals(""))
            time = startTime + "-";

        if (endTime != null && !endTime.equals(""))
            time = time + endTime;

        if (week != null && !week.equals(""))
            time = time + "\t\t\t\t" + week;

        if (timeSlot != null && !timeSlot.equals(""))
            time = time + timeSlot;

        timeTxt.setText(time);


        HttpParams httpParams = new HttpParams();
        if (getIntent().getStringExtra("status") != null) {
            httpParams.put("status", getIntent().getStringExtra("status"));
        }
        httpParams.put("line_id", keyId);
        httpParams.put("type", type);
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        postResponse(HttpUntil.GetIntent().StrategygetLineInfo(), httpParams, 1, true);
    }

    /**
     * vip 事件监听
     */
    private void vipEvent() {
        vipSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (dingDanData == null) return;
                if (!isChecked) {
                    vipRG.setVisibility(View.GONE);
                } else {
                    vipRG.setVisibility(View.VISIBLE);
                }
                priceChange();
            }
        });

        monthVip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && dingDanData != null) {
                    vipPrice = dingDanData.getData().getVip().getYue();
                    vip_type = MONTH;
                    priceChange();
                }
            }
        });
        jiDuVip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && dingDanData != null) {
                    vipPrice = dingDanData.getData().getVip().getJidu();
                    vip_type = JIDU;
                    priceChange();
                }
            }
        });
        banNianVip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && dingDanData != null) {
                    vipPrice = dingDanData.getData().getVip().getBannian();
                    vip_type = BANNIAN;
                    priceChange();
                }
            }
        });
        yearVip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && dingDanData != null) {
                    vipPrice = dingDanData.getData().getVip().getYinian();
                    vip_type = YEAR;
                    priceChange();
                }
            }
        });
    }

    /**
     * 计算总价格
     */
    private void priceChange() {
        if (!vip_type.equals(YEAR)) {
            vipJuanText.setText("选择优惠劵");
            vipCidprice = "0";
            vipCid = null;
        }
        float cid = Float.parseFloat(cidprice);//优惠劵
        float vipCid = Float.parseFloat(vipCidprice);//优惠劵
        //用户为vip
        if (MyApplication.getmInstance().userData.getData().getVip().equals("1")) {//是否为vip
            priceF = Float.parseFloat(dingDanData.getData().getVip_price()) - cid - vipCid;
            //2位小数
            DecimalFormat decimalFormat = new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
            String p = decimalFormat.format(priceF);//format 返回的是字符串
            priceF = Float.parseFloat(p);
            String s = "提交订单 ￥" + p;
            submission.setText(s);
            selectType = "1";
            return;
        }

        if (vipSwitch.isChecked()) {
            float one = Float.parseFloat(vipPrice);
            float two = Float.parseFloat(dingDanData.getData().getVip_price());
            priceF = one + two - cid - vipCid;
            //2位小数
            DecimalFormat decimalFormat = new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
            String p = decimalFormat.format(priceF);//format 返回的是字符串
            priceF = Float.parseFloat(p);
            String s = "提交订单 ￥" + p;
            submission.setText(s);
            selectType = "2";
        } else {
            priceF = Float.parseFloat(dingDanData.getData().getPrice()) - cid;
            //2位小数
            DecimalFormat decimalFormat = new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
            String p = decimalFormat.format(priceF);//format 返回的是字符串
            priceF = Float.parseFloat(p);
            String s = "提交订单 ￥" + p;
            submission.setText(s);
            selectType = "1";
        }
    }

    @Override
    public float getJinE() {
        return priceF;
    }

    @Override
    public String getPlayWay() {
        return playType;
    }

    @Override
    public void onEvent(CurrencyEvent currencyEvent) {
        super.onEvent(currencyEvent);
        if (currencyEvent.getWhat() == CurrencyEvent.PAYOK) {
            EventBus.getDefault().post(new CurrencyEvent(CurrencyEvent.GONGLUEBUGOK, "ok"));
            setResult(1, new Intent());
            finish();
        }
    }

    @Override
    public void yuEZhiFu(final String data) {
        //余额支付
        PlayByYuEDialog dialog = new PlayByYuEDialog();
        dialog.setMoneyStr(priceF + "");
        dialog.setPlayOk(new PlayByYuEDialog.PlayOk() {
            @Override
            public void playOk(String pwd) {
                FormBody.Builder builder = new FormBody.Builder();
                builder.add("password", pwd);
                builder.add("order_num", data);


                builder.add("uid", MyApplication.getmInstance().getUid());
                builder.add("token", MyApplication.getmInstance().token);
                Post(HttpUntil.GetIntent().PaybalancePayLine(), builder, 3);
            }

            @Override
            public void close() {

            }
        });
        dialog.show(getSupportFragmentManager(), "play");
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        super.GetHttpBackStr(str, what);
        switch (what) {
            case 1:
                if (codeIsOne(str, false)) {
                    dingDanData = new Gson().fromJson(str, QueRenDingDanData.class);
                    if (dingDanData.getData().getAddress() != null
                            && !dingDanData.getData().getAddress().getAddress_id().equals("0")
                            && dingDanData.getData().getIs_mail().equals("1")) {
                        add.setVisibility(View.GONE);
                        addressRel.setVisibility(View.VISIBLE);
                        //地址
                        String adrt = dingDanData.getData().getAddress().getProvince() +
                                dingDanData.getData().getAddress().getCity() +
                                dingDanData.getData().getAddress().getArea() +
                                dingDanData.getData().getAddress().getOther();
                        addresstext.setText(adrt);
                        phonetext.setText(dingDanData.getData().getAddress().getPhone());
                        nametext.setText(dingDanData.getData().getAddress().getConsignee());
                    } else if (dingDanData.getData().getAddress().getAddress_id().equals("0")
                            && dingDanData.getData().getIs_mail().equals("1")) {
                        add.setVisibility(View.VISIBLE);
                        addressRel.setVisibility(View.GONE);
                    } else {
                        add.setVisibility(View.GONE);
                        addressRel.setVisibility(View.GONE);
                    }
                    Glide.with(mcontext).load(dingDanData.getData().getPic()).into(img);
                    title.setText(dingDanData.getData().getName());

                    String j;
                    if (type.equals("61")) {//61为宝宝听听专辑购买，其他为攻略课程购买
                        j = "共:" + dingDanData.getData().getCourse_number() + "个媒体";
                    } else
                        j = "共:" + dingDanData.getData().getCourse_number() + "节课";
                    content.setText(j);


                    String p = "￥" + dingDanData.getData().getPrice();
                    price.setText(p);

                    String pv = "￥" + dingDanData.getData().getVip_price();
                    vipCoursePrice.setText(pv);

                    if (!dingDanData.getData().getExpiry_date().equals("")) {
                        String t = "有效期：" + dingDanData.getData().getExpiry_date() + "天";
                        timeTxt.setText(t);
                    }

                    vip_type = YEAR;
                    //vip价格
                    String vm = dingDanData.getData().getVip().getYue() + "/月";
                    String vj = dingDanData.getData().getVip().getJidu() + "/季度";
                    String vc = dingDanData.getData().getVip().getBannian() + "/半年";
                    String vy = dingDanData.getData().getVip().getYinian() + "/年";
                    monthVip.setText(vm);
                    jiDuVip.setText(vj);
                    banNianVip.setText(vc);
                    yearVip.setText(vy);
                    vipPrice = dingDanData.getData().getVip().getYinian();
                    priceChange();

                }
                break;
            case 3:
                if (codeIsOne(str)) {
                    EventBus.getDefault().post(new CurrencyEvent(CurrencyEvent.PAYOK, "支付成功"));
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*地址选择回返*/
        if (requestCode == 123 && resultCode == 1) {
            //是否地址
            add.setVisibility(View.GONE);
            addressRel.setVisibility(View.VISIBLE);
            if (dingDanData != null)
                dingDanData.getData().getAddress().setAddress_id(data.getStringExtra("id"));
            addresstext.setText(data.getStringExtra("adress"));
            String name = "收货人" + data.getStringExtra("name");
            nametext.setText(name);
            phonetext.setText(data.getStringExtra("phone"));

        }

        //课程优惠劵id
        if (requestCode == 1 && resultCode == 2) {
            cid = data.getStringExtra("id");
            juanText.setText(data.getStringExtra("name"));
            cidprice = data.getStringExtra("price");
            priceChange();
        }

        //vip优惠劵
        if (requestCode == 2 && resultCode == 2) {
            vipCid = data.getStringExtra("id");
            vipJuanText.setText(data.getStringExtra("name"));
            vipCidprice = data.getStringExtra("price");
            priceChange();
        }
    }

    @OnClick({R.id.vipJuanText, R.id.chooseCourse, R.id.add, R.id.address, R.id.submission, R.id.seeVip, R.id.youHuiJuan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.vipJuanText:
                if (vip_type == null) return;
                if (!vip_type.equals(YEAR)) {
                    ShowToast("只有年vip才可使用优惠劵");
                    return;
                }
                Intent intent19 = new Intent(mcontext, YouHuiJuanChooseActivity.class);
                intent19.putExtra("coupon_type", "1");
                startActivityForResult(intent19, 2);

                break;
            case R.id.youHuiJuan:
                Intent intent9 = new Intent(mcontext, YouHuiJuanChooseActivity.class);
                intent9.putExtra("money", dingDanData.getData().getPrice());
                intent9.putExtra("coupon_type", "2");
                startActivityForResult(intent9, 1);
                break;
            case R.id.chooseCourse:

                break;
            case R.id.address:
            case R.id.add:
                Intent intent = new Intent(mcontext, MyAdressActivity.class);
                startActivityForResult(intent, 123);
                break;
            case R.id.submission:

                if (vipSwitch.isChecked()) {
                    if (vip_type == null) {
                        ShowToast("请选择购买vip的时限");
                        return;
                    }
                }
                payTypeDialog = new PayTypeDialog();
                payTypeDialog.setPayBack(new PayTypeDialog.PayBack() {
                    @Override
                    public void payBack(String payType) {
                        playType = payType;
                        submissionOrder();
                    }
                });
                payTypeDialog.show(getSupportFragmentManager(), "payType");
                break;
            case R.id.seeVip:
                Intent intent88 = new Intent(mcontext, WebViewActivity.class);
                intent88.putExtra("title", "vip权益");
                intent88.putExtra("path", HttpUntil.GetIntent().getIP() + "small.php/User/aboutUs/type/6");
                jumpToActivity(intent88, false);
                break;

        }
    }

    /**
     * 提交订单
     */
    private void submissionOrder() {

        if (payTypeCheck()) return;

        if (dingDanData == null) return;
        HttpParams httpParams = new HttpParams();
//优惠劵
        String c = "";
        if (cid != null) c = cid;
        if (vipCid != null && cid == null) c = vipCid;
        if (vipCid != null && cid != null) c = c + "," + vipCid;
        if (!c.equals("")) httpParams.put("user_cid", c);

        httpParams.put("uid", MyApplication.getmInstance().getUid());
        httpParams.put("select_type", selectType);
        httpParams.put("key_id", keyId);
        if (dingDanData.getData().getAddress() != null) {
            httpParams.put("address_id", dingDanData.getData().getAddress().getAddress_id());
        }
        if (getIntent().getStringExtra("try_listening") != null) {
            httpParams.put("try_listening", getIntent().getStringExtra("try_listening"));
        } else {
            httpParams.put("try_listening", "0");
        }
        httpParams.put("start_time", startTime);
        httpParams.put("end_time", endTime);
        httpParams.put("time_slot", timeSlot);
        httpParams.put("day", day);
        httpParams.put("pay_type", playType);
        if (vipSwitch.isChecked() && vip_type != null) httpParams.put("vip_type", vip_type);
        httpParams.put("type", dingDanData.getData().getType());
        postResponse(HttpUntil.GetIntent().OrdercreateLineOrder(), httpParams, 10001, true);
    }


    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.time)
    TextView timeTxt;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.vipCoursePrice)
    TextView vipCoursePrice;
    @BindView(R.id.vip)
    TextView vip;
    @BindView(R.id.vipSwitch)
    Switch vipSwitch;
    @BindView(R.id.vipLin)
    LinearLayout vipLin;
    @BindView(R.id.submission)
    Button submission;
    @BindView(R.id.add)
    RelativeLayout add;
    @BindView(R.id.nametext)
    TextView nametext;
    @BindView(R.id.phonetext)
    TextView phonetext;
    @BindView(R.id.addresstext)
    TextView addresstext;
    @BindView(R.id.juanText)
    TextView juanText;
    @BindView(R.id.vipJuanText)
    TextView vipJuanText;
    @BindView(R.id.address)
    RelativeLayout addressRel;
    @BindView(R.id.youHuiJuan)
    LinearLayout juanRel;

    @BindView(R.id.monthVip)
    RadioButton monthVip;
    @BindView(R.id.jiDuVip)
    RadioButton jiDuVip;
    @BindView(R.id.banNianVip)
    RadioButton banNianVip;
    @BindView(R.id.yearVip)
    RadioButton yearVip;
    @BindView(R.id.vipRG)
    LinearLayout vipRG;


}
