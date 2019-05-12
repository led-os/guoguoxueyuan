package com.test720.grasshoppercollege.module.gongLue.teacherAndConsultant.ziXunShi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.makeramen.roundedimageview.RoundedImageView;
import com.test720.grasshoppercollege.BasePlayActivity;
import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.WebViewActivity;
import com.test720.grasshoppercollege.module.gongLue.teacherAndConsultant.bean.YuYueDingDanData;
import com.test720.grasshoppercollege.module.gongLue.teacherAndConsultant.peiXunTeacher.checkTime.CheckTimeActivity;
import com.test720.grasshoppercollege.myViews.PayTypeDialog;
import com.test720.grasshoppercollege.myViews.PlayByYuEDialog;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.LogUtil;
import com.test720.grasshoppercollege.untils.glideUntil.GlideUntil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;

public class YuYueDingDanActivity extends BasePlayActivity {


    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.zi_xun_num)
    TextView ziXunNum;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.checkTimeText)
    TextView checkTimeText;
    @BindView(R.id.video)
    RadioButton video;
    @BindView(R.id.audio)
    RadioButton audio;
    @BindView(R.id.flowTime)
    TagFlowLayout flowTime;
    @BindView(R.id.checkTime)
    LinearLayout checkTime;
    @BindView(R.id.zi_xun_ci_shu)
    TextView ziXunCiShu;
    @BindView(R.id.he_ji)
    TextView heJi;
    @BindView(R.id.scrollView)
    ScrollView scrollView;

    @BindView(R.id.zi_liao_name)
    EditText ziLiaoName;
    @BindView(R.id.age)
    EditText age;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.bei_zhu)
    EditText beiZhu;

    @BindView(R.id.submission)
    Button submission;
    @BindView(R.id.head)
    RoundedImageView head;

    String serviceId = "";
    String times = "";//预约的时长
    String time_length = "";//时长
    String payType = "";
    PayTypeDialog payTypeDialog;
    static final int CHECKTIME = 8;
    private List<YuYueDingDanData.DataBean.PriceTimeLengthBean> price_time_length = new ArrayList<>();

    TagAdapter tagAdapter;
    int index = 0;//选择的时间坐标

    @Override
    protected String setTitle() {
        return "预约";
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_yu_yue_ding_dan;
    }

    @Override
    protected void initData() {
        scrollView.scrollTo(0, 0);
        tagAdapter = new TagAdapter<YuYueDingDanData.DataBean.PriceTimeLengthBean>(price_time_length) {
            @Override
            public View getView(FlowLayout parent, int position, YuYueDingDanData.DataBean.PriceTimeLengthBean item) {
                @SuppressLint("InflateParams") View view = LayoutInflater.from(mcontext).inflate(R.layout.shang_pin_biao_qian, null);
                TextView textView = view.findViewById(R.id.text);
                textView.setText(item.getTime());
                textView.setTextSize(14);
                textView.setBackgroundResource(R.drawable.bule_bian_kuang);
                textView.setTextColor(getResources().getColor(R.color.appColor));

                if (index == position) {
                    textView.setBackgroundResource(R.drawable.bule_back_kuang);
                    textView.setTextColor(getResources().getColor(R.color.white));
                }
                return view;
            }
        };
        flowTime.setAdapter(tagAdapter);
        flowTime.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                String p = price_time_length.get(position).getPrice() +
                        "/" + price_time_length.get(position).getTime();
                price.setText(p);
                heJi.setText(price_time_length.get(position).getPrice());
                index = position;
                tagAdapter.notifyDataChanged();
                time_length = price_time_length.get(position).getTime();
                return true;
            }
        });
        serviceId = getIntent().getStringExtra("service_id");
        HttpParams httpParams = new HttpParams();
        httpParams.put("service_id", serviceId);
        postResponse(HttpUntil.GetIntent().StrategygetYuYueServiceInfo(), httpParams, 1, true);
    }

    @Override
    public float getJinE() {
        //金额
        String je = heJi.getText().toString().replace("元/次", "");
        LogUtil.logError("je=" + je);
        return Float.parseFloat(je);
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
    public String getPlayWay() {
        return payType;
    }

    @Override
    public void yuEZhiFu(final String data) {
        //余额支付
        PlayByYuEDialog dialog = new PlayByYuEDialog();
        dialog.setMoneyStr(heJi.getText().toString());
        dialog.setPlayOk(new PlayByYuEDialog.PlayOk() {
            @Override
            public void playOk(String pwd) {
                FormBody.Builder builder = new FormBody.Builder();
                builder.add("password", pwd);
                builder.add("order_num", data);
                builder.add("uid", MyApplication.getmInstance().getUid());
                builder.add("token", MyApplication.getmInstance().token);
                Post(HttpUntil.GetIntent().balancePayYuYueService(), builder, 3);
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
                    YuYueDingDanData data = new Gson().fromJson(str, YuYueDingDanData.class);
                    initUI(data);
                }
                break;
            case 3:
                if (codeIsOne(str)) {
                    EventBus.getDefault().post(new CurrencyEvent(CurrencyEvent.PAYOK, "支付成功"));
                }
                break;
        }
    }

    private void initUI(final YuYueDingDanData data) {
        if (data.getData().getVideo_way().equals("0")) {
            video.setVisibility(View.GONE);
            audio.setChecked(true);
        }
        if (data.getData().getVoice_way().equals("0")) {
            audio.setVisibility(View.GONE);
            video.setChecked(true);
        }
        name.setText(data.getData().getNickname());
        GlideUntil.getInstance().headByUrl(mcontext, head, data.getData().getHeader());
        ziXunNum.setText(data.getData().getOrders_count());
        title.setText(data.getData().getName());
        String p = data.getData().getPrice_time_length().get(0).getPrice() + "/" + data.getData().getPrice_time_length().get(0).getTime();
        price.setText(p);
        heJi.setText(data.getData().getPrice_time_length().get(0).getPrice());
        time_length = data.getData().getPrice_time_length().get(0).getTime();
        price_time_length.addAll(data.getData().getPrice_time_length());
        tagAdapter.notifyDataChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CHECKTIME && data != null && data.getStringExtra("time") != null) {
            checkTimeText.setText(data.getStringExtra("time"));
            times = checkTimeText.getText().toString();
        }
    }

    @OnClick({R.id.checkTime, R.id.submission, R.id.xieyi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.xieyi:
                Intent intent88 = new Intent(mcontext, WebViewActivity.class);
                intent88.putExtra("title", "用户咨询协议");
                intent88.putExtra("path", HttpUntil.GetIntent().getIP() + "small.php/User/aboutUs/type/15");
                jumpToActivity(intent88, false);
                break;
            case R.id.checkTime:
                Intent intent = new Intent(mcontext, CheckTimeActivity.class);
                intent.putExtra("tid", getIntent().getStringExtra("tid"));
                intent.putExtra("service_id", getIntent().getStringExtra("service_id"));
                intent.putExtra("time", time_length);
                startActivityForResult(intent, CHECKTIME);
                break;
            case R.id.submission:
                sub();
                break;
        }
    }

    /**
     * 点击支付
     */
    private void sub() {
        if (TextUtils.isEmpty(ziLiaoName.getText())) {
            ziLiaoName.setError("请输入名字");
            return;
        }
        if (TextUtils.isEmpty(phone.getText())) {
            phone.setError("请输入电话");
            return;
        }
        if (phone.getText().toString().length() < 11) {
            phone.setError("请输入正确11位电话");
            return;
        }
        if (TextUtils.isEmpty(age.getText())) {
            age.setError("请输入年龄");
            return;
        }
        if (TextUtils.isEmpty(beiZhu.getText())) {
            beiZhu.setError("请备注");
            return;
        }
        if (times.equals("")) {
            ShowToast("请选择预约时间");
            return;
        }
        if (payTypeDialog == null) {
            payTypeDialog = new PayTypeDialog();
            payTypeDialog.setPayBack(new PayTypeDialog.PayBack() {
                @Override
                public void payBack(String pt) {
                    payType = pt;
                    getOrder();
                }
            });
            payTypeDialog.show(getSupportFragmentManager(), "payDialog");
        } else {
            payTypeDialog.show(getSupportFragmentManager(), "payDialog");
        }
    }

    /**
     * 获取订单
     */
    private void getOrder() {
        if (payTypeCheck()) return;
        //已经获得支付订单后直接调起支付
        /*if (getOrdernum() != null) {
            afterOrder();
            return;
        }*/
        HttpParams httpParams = new HttpParams();
        httpParams.put("tid", getIntent().getStringExtra("tid"));
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        httpParams.put("service_id", serviceId);
        if (video.isChecked()) {
            httpParams.put("way", "1");
        } else {
            httpParams.put("way", "2");
        }
        httpParams.put("times", times);
        httpParams.put("time_length", time_length);
        httpParams.put("number", 1);
        httpParams.put("name", ziLiaoName.getText().toString());
        httpParams.put("phone", phone.getText().toString());
        httpParams.put("age", age.getText().toString());
        httpParams.put("desc", beiZhu.getText().toString());
        postResponse(HttpUntil.GetIntent().StrategyyuYueService(), httpParams, 10001, true);
    }

}

