package com.test720.grasshoppercollege.alipay;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.test720.grasshoppercollege.R;

import java.net.URLEncoder;

public class PayMain {

    private Activity mContext;
    private CallBack callBack;
    private String notify_url;
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            PayResult result = new PayResult((String) msg.obj);
            callBack.call(result);
            return false;
        }
    });


    public PayMain(Activity mContext, CallBack callBack, String notify_url) {
        super();
        this.mContext = mContext;
        this.callBack = callBack;
        this.notify_url = notify_url;
    }

    public void Pay(String price, String orderNo) {
        this.Pay(mContext.getString(R.string.app_name), mContext.getString(R.string.app_name), price, orderNo);
    }

    private void Pay(String subject, String body, String price, String orderNo) {
        try {
            Log.i("ExternalPartner", "onItemClick");
            String info = getOrderInfo(subject, body, price, orderNo); // 价格
            String sign = SignUtils.sign(info, Keys.PRIVATE);
            sign = URLEncoder.encode(sign, "UTF-8");
            info += "&sign=\"" + sign + "\"&" + getSignType();
            Log.i("ExternalPartner", "start pay");
            // start the pay.

            final String orderInfo = info;
            new Thread() {
                public void run() {
                    PayTask alipay = new PayTask(mContext);
                    // 设置为沙箱模式，不设置默认为线上环境
                    // alipay.setSandBox(true);
                    String result = alipay.pay(orderInfo);
                    Message msg = new Message();
                    msg.obj = result;
                    mHandler.sendMessage(msg);
                }
            }.start();

        } catch (Exception ex) {
            ex.printStackTrace();
            Toast.makeText(mContext, "Failure calling remote service",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private String getSignType() {
        return "sign_type=\"RSA\"";
    }

    /*private String getNewOrderInfo(String subject, String body, String price, String orderNo) {
        StringBuilder sb = new StringBuilder();
        sb.append("partner=\"");
        sb.append(Keys.DEFAULT_PARTNER);
        sb.append("\"&out_trade_no=\"");
        sb.append(orderNo);
        sb.append("\"&subject=\"");
        sb.append(subject);// 标题
        sb.append("\"&body=\"");
        sb.append(body); // 内容
        sb.append("\"&total_fee=\"");
        sb.append(price); // 价格
        sb.append("\"&notify_url=\"");
        sb.append(notify_url);
        // 网址需要做URL编码
        *//*try {
            sb.append(URLEncoder.encode(notify_url, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*//*
        sb.append("\"&service=\"mobile.securitypay.pay");
        sb.append("\"&_input_charset=\"UTF-8");
        sb.append("\"&return_url=\"");
        sb.append("http://m.alipay.com");
        sb.append("\"&payment_type=\"1");
        sb.append("\"&seller_id=\"");
        sb.append(Keys.DEFAULT_SELLER);

        // 如果show_url值为空，可不传
        sb.append("\"&show_url=\"");
        sb.append("http://www.cool-me.cn/");
        sb.append("\"&it_b_pay=\"1m");
        sb.append("\"");

        return new String(sb);
    }*/

    private String getOrderInfo(String subject, String body, String price, String orderNo) {
        // 签约合作者身份ID
        String orderInfo = "partner=" + "\"" + Keys.DEFAULT_PARTNER + "\"";

        // 签约卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" + Keys.DEFAULT_SELLER + "\"";

        // 商户网站唯一订单号
        orderInfo += "&out_trade_no=" + "\"" + orderNo + "\"";

        // 商品名称
        orderInfo += "&subject=" + "\"" + subject + "\"";

        // 商品详情
        orderInfo += "&body=" + "\"" + body + "\"";

        // 商品金额
        orderInfo += "&total_fee=" + "\"" + price + "\"";

        // 服务器异步通知页面路径
        orderInfo += "&notify_url=" + "\"" + notify_url
                + "\"";

        // 服务接口名称， 固定值
        orderInfo += "&service=\"mobile.securitypay.pay\"";

        // 支付类型， 固定值
        orderInfo += "&payment_type=\"1\"";

        // 参数编码， 固定值
        orderInfo += "&_input_charset=\"utf-8\"";

        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        orderInfo += "&it_b_pay=\"30m\"";

        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
        orderInfo += "&return_url=\"m.alipay.com\"";

        // 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
        // orderInfo += "&paymethod=\"expressGateway\"";

        Log.e("aaaaaaaaaaaaaaab", "支付宝：" + orderInfo);
        return orderInfo;
    }

}
