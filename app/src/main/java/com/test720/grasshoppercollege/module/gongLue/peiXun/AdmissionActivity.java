package com.test720.grasshoppercollege.module.gongLue.peiXun;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.gongLue.PhoneAndNameDialog;
import com.test720.grasshoppercollege.untils.AdressUntil;
import com.test720.grasshoppercollege.untils.HttpUntil;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;
import www.test720.mylibrary.SPUtils;

public class AdmissionActivity extends BaseToolActivity {
    @BindView(R.id.one)
    Button one;
    @BindView(R.id.two)
    Button two;
    @BindView(R.id.adress)
    TextView adress;
    String province, city, area;

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        isFull = true;
        return R.layout.admission_dialog;
    }


    @Override
    public void onEvent(CurrencyEvent event) {
        Log.i("lxl", "event" + event.getWhat());
        if (event.getWhat() == CurrencyEvent.FINISHADMISSONE) {
            finish();
        }
    }

    @Override
    protected void initData() {
        province = SPUtils.getProvince(mcontext);
        city = SPUtils.getCity(mcontext);
        area = SPUtils.getArea(mcontext);
        String s = province + city + area;
        adress.setText(s);

    }

    @OnClick({R.id.back, R.id.one, R.id.two, R.id.adress})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.one:
                admisssion(2);
                break;
            case R.id.two:
                admisssion(1);
                break;
            case R.id.adress:
                upDataAdress();
                break;
        }
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        if (codeIsOne(str, true)) {
        }
    }

    /**
     * 切换地址
     */
    private void upDataAdress() {
        AdressUntil.getInstance().adressDialog(mcontext, province, city, area, new AdressUntil.AdressBack() {
            @Override
            public void province(String pr) {
                province = pr;
                SPUtils.putString(mcontext, SPUtils.PROVINCE, pr);
            }

            @Override
            public void city(String pr) {
                city = pr;
                SPUtils.putString(mcontext, SPUtils.CITY, pr);
            }

            @Override
            public void district(String pr) {
                area = pr;
                SPUtils.putString(mcontext, SPUtils.AREA, pr);
            }

            @Override
            public void ShuaXin() {
                String s = province + city + area;
                adress.setText(s);
                EventBus.getDefault().post(new CurrencyEvent("地址切换", CurrencyEvent.UPDATAADRESSONE));
            }
        });
    }

    private void admisssion(final int i) {
        MyApplication.getmInstance().setLoginDialogIsShow(false);
        if (MyApplication.getmInstance().getUid().equals("")) {
            return;
        }
        if (i == 1) {
            HttpParams httpParams = new HttpParams();
            httpParams.put("uid", MyApplication.getmInstance().getUid());
            httpParams.put("status", i);
            httpParams.put("region", adress.getText().toString());
            postResponse(HttpUntil.GetIntent().getIP() + "small.php/StrategyTrain/clickUser", httpParams, 1, false);
            return;
        }
        PhoneAndNameDialog phoneAndNameDialog = new PhoneAndNameDialog();
        phoneAndNameDialog.setPhoneAndName(new PhoneAndNameDialog.PhoneAndName() {
            @Override
            public void phoneAndName(String name, String phone, String school_name) {
                HttpParams httpParams = new HttpParams();
                httpParams.put("uid", MyApplication.getmInstance().getUid());
                httpParams.put("status", i);
                httpParams.put("phone", phone);
                httpParams.put("name", name);
                httpParams.put("region", adress.getText().toString());
                httpParams.put("school_name", school_name);
                postResponse(HttpUntil.GetIntent().getIP() + "small.php/StrategyTrain/clickUser", httpParams, 1, false);
            }
        });
        phoneAndNameDialog.show(getSupportFragmentManager(), "name");
    }
}
