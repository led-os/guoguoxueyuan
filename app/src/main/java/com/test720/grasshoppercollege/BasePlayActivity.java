package com.test720.grasshoppercollege;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.test720.grasshoppercollege.HttpBean.OutTradeNoData;
import com.test720.grasshoppercollege.alipay.CallBack;
import com.test720.grasshoppercollege.alipay.PayMain;
import com.test720.grasshoppercollege.alipay.PayResult;
import com.test720.grasshoppercollege.bean.BaseNameValue;
import com.test720.grasshoppercollege.bean.NameValue;
import com.test720.grasshoppercollege.module.userData.PhoneCodeActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.PublicUntil;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

import okhttp3.FormBody;
import www.test720.mylibrary.MD5;


/**
 * Created by 水东流 on 2018/4/12.
 */

public abstract class BasePlayActivity extends BaseToolActivity {
    private String appid;
    private String sign;
    private PayMain payMain;
    private String ordernum;//交易订单号

    public String getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(String ordernum) {
        this.ordernum = ordernum;
    }

    /**
     * 获取在线支付交易号
     * <p>
     * type
     * 购买类型 1-购买vip；2-课程购买；3-充值；4模块 是 [string]
     * <p>
     * uid
     * 用户ID 是 [string]
     * <p>
     * cid
     * 优惠券id 是 [string]
     * <p>
     * pay_way
     * 1-支付宝 2-微信' 是 [string]
     * <p>
     * module
     * 是 [string]
     * <p>
     * book_type
     * 课本类型 是 [string]
     */
    public void getOutTradeNo(String type, String cid, String curr_type, String book_type) {
        getOutTradeNo(type, cid, curr_type, book_type, null, null);
    }

    public void getOutTradeNo(String type, String cid, String vip_type) {
        getOutTradeNo(type, cid, null, null, null, vip_type);
    }

    /**
     * 获取在线支付交易号
     * <p>
     * type
     * 购买类型 1-购买vip；2-课程购买；3-充值；4模块 是 [string]
     * <p>
     * uid
     * 用户ID 是 [string]
     * <p>
     * cid
     * 优惠券id 是 [string]
     * <p>
     * pay_way
     * 1-支付宝 2-微信' 是 [string]
     * <p>
     * module
     * 是 [string]
     * <p>
     * book_type
     * 课本类型 是 [string]
     */
    public void getOutTradeNo(String type, String cid, String curr_type, String book_type, String module_type, String vip_type) {
        //检测支付方式是否可行
        if (payTypeCheck()) return;

        if (MyApplication.getmInstance().getUid().equals("")) return;
        FormBody.Builder build = new FormBody.Builder();
        build.add("uid", MyApplication.getmInstance().getUid());
        if (type != null) build.add("type", type);
        build.add("pay_way", getPlayWay());
        if (cid != null) build.add("cid", cid);
        if (curr_type != null) build.add("curr_type", curr_type);
        if (book_type != null) build.add("book_id", book_type);
        if (module_type != null) build.add("module_type", module_type);
        if (vip_type != null) build.add("vip_type", vip_type);
        build.add("pay_money", getJinE() + "");
        Post(HttpUntil.GetIntent().getOutTradeNo(), build, 10001);

    }

    public boolean payTypeCheck() {
        switch (getPlayWay()) {
            case "1":
                if (!PublicUntil.checkAliPayInstalled(mcontext)) {
                    ShowToast(getString(R.string.noAlipay));
                    return true;
                }
                break;
            case "2":
                if (!PublicUntil.isWeixinAvilible(mcontext)) {
                    ShowToast(getString(R.string.noWeiXin));
                    return true;
                }
                break;
        }

        if (getJinE() == 0) {
            ShowToast("金额不能为0");
            return true;
        }
        return false;
    }

    /**
     * 获取在线支付交易号
     */
    public void getOutTradeNo(String type) {
        getOutTradeNo(type, null, null, null);
    }

    /**
     * 获取在线支付交易号
     */
    public void getOutTradeNo(String type, String cid) {
        getOutTradeNo(type, cid, null, null);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        payMain = new PayMain(mcontext, callBack, HttpUntil.GetIntent().getZFBcalllBack());
    }

    public abstract float getJinE();

    public abstract String getPlayWay();

    @Override
    public void GetHttpBackStr(String str, int what) {
        switch (what) {
            case 10001://获取在线支付交易号
                if (codeIsOne(str)) {
                    OutTradeNoData orderData = new Gson().fromJson(str, OutTradeNoData.class);
                    ordernum = orderData.getData().getOrder_num();
                    afterOrder();
                }
                break;
            case 10002:
                JSONObject jsonObject;
                try {
                    jsonObject = new JSONObject(str);
                    Log.e("~~~~~~~~~===", "jsonObject.toString():" + jsonObject.toString());
                    sign = jsonObject.getJSONObject("data").getJSONObject("result").getString("sign");
                    appid = jsonObject.getJSONObject("data").getJSONObject("result").getString("appid");
                    String nonce_str = jsonObject.getJSONObject("data").getJSONObject("result").getString("nonce_str");
                    String prepay_id = jsonObject.getJSONObject("data").getJSONObject("result").getString("prepay_id");
                    String mch_id = jsonObject.getJSONObject("data").getJSONObject("result").getString("mch_id");
                    genPayReq(mch_id, prepay_id, nonce_str);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }

    }

    /**
     * 获取到订单之后执行逻辑
     */
    public void afterOrder() {
        switch (getPlayWay()) {
            case "1":
                if (payMain == null) {
                    ShowToast("请选择微信或者余额支付方式");
                    return;
                }
                payMain.Pay(getJinE() + "", ordernum);
                break;
            case "2":
                FormBody.Builder builder = new FormBody.Builder();
                builder.add("sn", ordernum);
                builder.add("total_price", getJinE() + "");
                Log.e("~~~~~~~~~~~~", "params:" + builder.toString());
                Post(HttpUntil.GetIntent().weiXinPay(), builder, 10002, true);
                break;
            case "3": //余额支付
                /*未设置支付密码时，须跳转*/
                if (MyApplication.getmInstance().userData.getData().getIs_pay_password() != 1) {
                    jumpToActivity(PhoneCodeActivity.class, false);
                    ShowToast("请设置支付密码！");
                    return;
                }
                yuEZhiFu(ordernum);
                break;
        }
    }

    /*余额支付*/
    public abstract void yuEZhiFu(String ordernum);

    /*支付宝回调*/
    CallBack callBack = new CallBack(mcontext) {

        @Override
        public void call(int what, Object obj) {

        }

        @Override
        public void call(PayResult result) {
            // String resultInfo = result.getResult();
            String resultStatus = result.getResultStatus();
            // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
            if (TextUtils.equals(resultStatus, "9000")) {
                Toast.makeText(mcontext, "付款成功",
                        Toast.LENGTH_SHORT).show();
                EventBus.getDefault().post(new CurrencyEvent("支付成功", CurrencyEvent.PAYOK));
            } else {
                // 判断resultStatus 为非“9000”则代表可能支付失败
                // “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                if (TextUtils.equals(resultStatus, "8000")) {
                    Toast.makeText(mcontext, "支付结果确认中",
                            Toast.LENGTH_SHORT).show();

                } else {
                    // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                    Toast.makeText(mcontext, "付款失败",
                            Toast.LENGTH_SHORT).show();
                }
            }
        }
    };


    //调起微信

    private void genPayReq(String partnerId, String prepayId, String nonceStr) {
        if (!MyApplication.getmInstance().api.isWXAppInstalled()) {
            Toast.makeText(mcontext, "没有安装微信", Toast.LENGTH_LONG)
                    .show();
            return;
        }
        if (!MyApplication.getmInstance().api.isWXAppSupportAPI()) {
            Toast.makeText(mcontext, "当前版本不支持微信支付功能",
                    Toast.LENGTH_LONG).show();
            return;
        }
        PayReq req = new PayReq();
//        req.appId = "wxa1698826e51a9b03";
        req.appId = appid;
        req.partnerId = partnerId;
        req.prepayId = prepayId;
        req.packageValue = sign;
//        req.packageValue = "Sign=WXPay";
        req.nonceStr = nonceStr;
        req.timeStamp = String.valueOf((System.currentTimeMillis() / 1000) + "");
        List<NameValue> signParams = new LinkedList<>();
        signParams.add(new BaseNameValue("appid", req.appId));
        signParams.add(new BaseNameValue("noncestr", req.nonceStr));
        signParams.add(new BaseNameValue("package", req.packageValue));
        signParams.add(new BaseNameValue("partnerid", req.partnerId));
        signParams.add(new BaseNameValue("prepayid", req.prepayId));
        signParams.add(new BaseNameValue("timestamp", req.timeStamp));
        req.sign = genAppSign(signParams);

        Log.e("aaaaaaa", "appId：" + appid);
        Log.e("aaaaaaa", "partnerId：" + partnerId);
        Log.e("aaaaaaa", "prepayId：" + prepayId);
        Log.e("aaaaaaa", "sign：" + sign);
        Log.e("aaaaaaa", "nonceStr：" + nonceStr);
        Log.e("aaaaaaa", "timeStamp：" + String.valueOf((System.currentTimeMillis() / 1000) + ""));
        Log.e("aaaaaaa", "package：" + req.packageValue);
        MyApplication.getmInstance().api.sendReq(req);
        Log.e("aaaaaaa", "req：" + req.toString());
    }

    //微信签名
    private String genAppSign(List<NameValue> params) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < params.size(); i++) {
            sb.append(params.get(i).getName());
            sb.append('=');
            sb.append(params.get(i).getValue());
            sb.append('&');
        }
        sb.append("key=");
//        sb.append("aa4c22471dd98c240714f76bcd8e3bf4");
        sb.append("jinguanguoguo8888888888888888888");
        String appSign = MD5.getMessageDigest(sb.toString().getBytes());
        assert appSign != null;
        String UappSign = appSign.toUpperCase();
        Log.e("orion", appSign);
        return UappSign;
    }

}
