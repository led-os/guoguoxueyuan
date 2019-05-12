package com.test720.grasshoppercollege.module.userData.caiWu.tiXian;

import android.content.Intent;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;
import www.test720.mylibrary.SPUtils;

public class TiXianGuanLiActivity extends BaseToolActivity {


    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.root)
    ScrollView root;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.yu_e)
    TextView yuE;
    @BindView(R.id.ti_xiang_edit)
    EditText tiXiangEdit;
    @BindView(R.id.shouxu)
    TextView shouxu;
    @BindView(R.id.shiji)
    TextView shiji;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.code)
    EditText code;
    @BindView(R.id.get_code)
    TextView getCode;
    @BindView(R.id.type_text)
    TextView typeText;
    @BindView(R.id.minText)
    TextView minText;
    @BindView(R.id.type)
    RelativeLayout type;
    @BindView(R.id.ok)
    Button ok;
    private CountDownTimer countDownTimer;
    private float charge_money;//手续费
    private float real_money;//实际金额
    private String ti_xian_type;//提现类型
    private float piceDouble;//提现金额
    float yu;//余额
    float min;//最小提现金额
    DecimalFormat df = new DecimalFormat("######0.00");

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_ti_xian_guan_li;
    }

    @Override
    protected void initData() {
        phone.setText(SPUtils.getString(this, SPUtils.PHONE, ""));
        yuE.setText(MyApplication.getmInstance().userData.getData().getMoney());
        tiXiangEdit.addTextChangedListener(textWatcher);
        yu = Float.parseFloat(yuE.getText().toString());
        min = Float.parseFloat(MyApplication.getmInstance().userData.getData().getMin());
        String m = "最小提现金额:" + min + "元";
        minText.setText(m);
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        switch (what) {
            case 1000:
                if (codeIsOne(str)) {
                    countDownTimer.start();
                }
                break;
            case 1:
                if (codeIsOne(str)) {
                    finish();
                }
                break;
        }

    }


    @OnClick({R.id.back, R.id.get_code, R.id.type, R.id.ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.get_code:
                GetCode();
                break;
            case R.id.type:
                typeChoosePop();
                break;
            case R.id.ok:
                tixian();
                break;
        }
    }

    /*类型选择弹框*/
    private void typeChoosePop() {
        TiXianTypeDialog tiXianTypeDialog = new TiXianTypeDialog();
        tiXianTypeDialog.setItemClick(new TiXianTypeDialog.ItemClick() {
            @Override
            public void item(String str, String type) {
                ti_xian_type = type;
                typeText.setText(str);
            }
        });
        tiXianTypeDialog.show(getSupportFragmentManager(), "type");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    @Override
    public void setSys() {
        super.setSys();
        isTouMing = false;
    }

    private void tixian() {
        if (TextUtils.isEmpty(tiXiangEdit.getText())) {
            tiXiangEdit.setError("请输入提现金额");
            return;
        }
        if (TextUtils.isEmpty(code.getText())) {
            code.setError("请输入验证码");
            return;
        }
        if (ti_xian_type == null) {
            ShowToast("请选择提现方式");
            return;
        }
        if (piceDouble > yu) {
            ShowToast("提现金额不能大于余额");
            return;
        }
        if (piceDouble < min) {
            ShowToast("最小提现金额为" + min + "元");
            return;
        }
        FormBody.Builder build = new FormBody.Builder();
        build.add("uid", MyApplication.getmInstance().getUid());
        build.add("money", tiXiangEdit.getText().toString());
        build.add("charge_money", charge_money + "");
        build.add("real_money", real_money + "");
        build.add("phone", phone.getText().toString());
        build.add("code", code.getText().toString());
        build.add("type", ti_xian_type);
        Post(HttpUntil.GetIntent().draw(), build, 1);
    }

    int time = 60;

    private void GetCode() {
        if (TextUtils.isEmpty(phone.getText())) {
            phone.setError("请准确输入您的电话号码");
            return;
        }
        if (TextUtils.isEmpty(tiXiangEdit.getText())) {
            tiXiangEdit.setError("请输入提现金额");
            return;
        }
        if (piceDouble < min) {
            ShowToast("最小提现金额为" + min + "元");
            return;
        }
        if (piceDouble > yu) {
            ShowToast("提现金额不能大于余额");
            return;
        }
        if (!getCode.getText().toString().equals("获取验证码")) {
            return;
        }
        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("phone", phone.getText().toString());//请求参数一
        formBuilder.add("type", "8");//请求参数二
        Post(HttpUntil.GetIntent().getMobileCode(), formBuilder, 1000);

        countDownTimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time--;
                String t = String.valueOf(time) + "s";
                getCode.setText(t);
            }

            @Override
            public void onFinish() {
                getCode.setText("获取验证码");
                time = 60;
            }
        };

    }

    /*限制金额格式*/
    /**
     * 限制输入一个小数点  小数点后最多2位
     */
    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            //这部分是处理如果输入框内小数点后有俩位，那么舍弃最后一位赋值，光标移动到最后
            if (s.toString().contains(".")) {
                if (s.length() - 1 - s.toString().indexOf(".") > 2) {

                    tiXiangEdit.setText(s.toString().subSequence(0,
                            s.toString().indexOf(".") + 3));

                    tiXiangEdit.setSelection(s.toString().trim().length() - 1);
                }
            }
            /*最后金额不能大于余额*/
            if (tiXiangEdit.getText().length() > 0) {
                piceDouble = Float.parseFloat(tiXiangEdit.getText().toString());

                if (piceDouble > yu) {
                    tiXiangEdit.setError("提现金额不能大于余额");
                    return;
                }
                if (piceDouble < min) {
                    tiXiangEdit.setError("最小提现金额为" + min);
                    return;
                }
                float shouXuBiLie = Float.parseFloat(MyApplication.getmInstance().userData.getData().getDraw_charge());
                charge_money = piceDouble * shouXuBiLie;
                shouxu.setText("手续费：" + df.format(charge_money) + "");
                shiji.setText("实际金额：" + df.format(piceDouble - charge_money) + "");
            }
        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != 1) return;//如果不为1，说明绑定失败
        switch (requestCode) {
            case 1://绑定支付宝成功后返回
                ti_xian_type = "1";
                typeText.setText("支付宝");
                break;
            case 2://绑定银行卡成功后返回
                typeText.setText("银行卡");
                ti_xian_type = "2";
                break;
        }

    }
}
