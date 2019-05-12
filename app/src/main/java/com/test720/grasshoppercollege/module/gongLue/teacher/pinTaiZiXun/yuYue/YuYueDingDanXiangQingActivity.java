package com.test720.grasshoppercollege.module.gongLue.teacher.pinTaiZiXun.yuYue;

import android.Manifest;
import android.support.annotation.NonNull;
import android.text.SpannableString;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.makeramen.roundedimageview.RoundedImageView;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.gongLue.teacher.bean.YuYueXiangQingData;
import com.test720.grasshoppercollege.myViews.GetPhoneDialog;
import com.test720.grasshoppercollege.myViews.PhoneCalDialog;
import com.test720.grasshoppercollege.untils.AppUploatUtils;
import com.test720.grasshoppercollege.untils.ExpressionUtil;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.callUntil.CallUntil;
import com.test720.grasshoppercollege.untils.glideUntil.GlideUntil;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class YuYueDingDanXiangQingActivity extends BaseToolActivity {


    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.zi_xun_zhu_ti)
    TextView ziXunZhuTi;
    @BindView(R.id.zi_xun_fang_shi)
    TextView ziXunFangShi;
    @BindView(R.id.zi_xun_shi_chang)
    TextView ziXunShiChang;
    @BindView(R.id.buy_num)
    TextView buyNum;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.age)
    TextView age;
    @BindView(R.id.dece)
    TextView dece;
    @BindView(R.id.zi_xun_price)
    TextView ziXunPrice;
    @BindView(R.id.bohao)
    TextView bohao;
    @BindView(R.id.pic)
    RoundedImageView pic;

    @Override
    protected String setTitle() {
        return "订单详情";
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_yu_yue_ding_dan_xiang_qing;
    }

    @Override
    protected void initData() {
        getData();
    }

    /**
     * 获取数据
     */
    private void getData() {

        if (!getIntent().getBooleanExtra("bohao", true)) {
            bohao.setVisibility(View.GONE);
        }
        HttpParams httpParams = new HttpParams();

        if (getIntent().getStringExtra("yuyue_id") != null)
            httpParams.put("yuyue_id", getIntent().getStringExtra("yuyue_id"));
        else ShowToast("未能获取到id");

        if (getIntent().getStringExtra("status") != null) {
            httpParams.put("status", getIntent().getStringExtra("status"));
        } else ShowToast("未能获取到i类别");

        postResponse(HttpUntil.GetIntent().StrategyteacherYuYueInfo(), httpParams, 1, true);
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        switch (what) {
            case 1:
                if (codeIsOne(str, false)) {
                    data = new Gson().fromJson(str, YuYueXiangQingData.class);
                    title.setText(data.getData().getNickname());
                    name.setText(data.getData().getName());
                    userPhone(data.getData().getPhone());

                    age.setText(data.getData().getAge());
                    GlideUntil.getInstance().headByUrl(mcontext, pic, data.getData().getHeader());
                    price.setText(data.getData().getPrice());

                    String s = data.getData().getTime_length() + "分钟/次";
                    ziXunShiChang.setText(s);
                    time.setText(data.getData().getTimes());
                    ziXunZhuTi.setText(data.getData().getService_name());

                    if (data.getData().getWay().equals("1")) {
                        ziXunFangShi.setText("视频");
                    } else {
                        ziXunFangShi.setText("音频");
                    }
                    String danJia = data.getData().getPrice() + "元/" + data.getData().getTime_length() + "分钟";
                    ziXunPrice.setText(danJia);
                    buyNum.setText(data.getData().getNumber());

                    /*评论内容，包含表情*/
                    int size = data.getData().getDesc().length();
                    SpannableString spannableString = ExpressionUtil
                            .getExpressionString(mcontext, data.getData().getDesc(), size);
                    dece.setText(spannableString);
                }
                break;
            case 5:
                if (codeIsOne(str)) {
                    try {
                        JSONObject jsonObject = new JSONObject(str);
                        String status = jsonObject.getJSONObject("data").getString("status");
                        final String phone = jsonObject.getJSONObject("data").getString("phone");
                        PhoneCalDialog phoneCalDialog = new PhoneCalDialog();
                        phoneCalDialog.setPhone(phone);
                        phoneCalDialog.setCallListener(new PhoneCalDialog.CallListener() {
                            @Override
                            public void call() {
                                AppUploatUtils.callPhoone(phone, mcontext);
                            }
                        });
                        phoneCalDialog.show(getSupportFragmentManager(), "phone");

                        //融云拨号
                      /*  if (status.equals("1") && callUntil != null) {
                            callUntil.startCall();
                        }*/
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }

    }

    /**
     * 预约人信息电话
     *
     * @param str 电话号码对老师不可见
     */
    private void userPhone(String str) {
        if (getIntent().getStringExtra("status").equals("tid") && str.length() > 7) {
            String maskNumber = str.substring(0, 3) + "****" + str.substring(7, str.length());
            phone.setText(maskNumber);
        } else
            phone.setText(str);
    }

    CallUntil callUntil;
    YuYueXiangQingData data;

    @OnClick(R.id.bohao)
    public void onViewClicked() {
        if (data == null) return;
        MyApplication.getmInstance().rong_token = data.getData().getRong_token();
        MyApplication.getmInstance().RongListener();
        callUntil = new CallUntil(mcontext, data.getData().getRong_tid(), data.getData().getWay());
        callStatus();
    }

    /**
     * 获取拨号状态
     */
    public void callStatus() {
        AndPermission.with(mcontext.getApplicationContext()).requestCode(100).permission(
                Manifest.permission.READ_PHONE_STATE).callback(new PermissionListener() {
            @Override
            public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {

                GetPhoneDialog getPhoneDialog = new GetPhoneDialog();
                getPhoneDialog.setListener(new GetPhoneDialog.Listener() {
                    @Override
                    public void phone(String str) {
                        HttpParams httpParams = new HttpParams();
                        httpParams.put("yuyue_id", getIntent().getStringExtra("yuyue_id"));
                        httpParams.put("phone", str);
                        postResponse(HttpUntil.GetIntent().getIP() + "small.php/Strategy/callStart", httpParams, 5, true);
                    }
                });
                getPhoneDialog.show(getSupportFragmentManager(), "");
            }
            @Override
            public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {

            }
        }).start();

    }
}
