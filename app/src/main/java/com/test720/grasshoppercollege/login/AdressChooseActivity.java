package com.test720.grasshoppercollege.login;

import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.MainActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.myViews.choosetime.CityView;
import com.test720.grasshoppercollege.untils.HttpUntil;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;

public class AdressChooseActivity extends BaseToolActivity {


    @BindView(R.id.nikeName)
    EditText nikeName;
    @BindView(R.id.adress_lin)
    LinearLayout adressLin;
    @BindView(R.id.adress)
    CityView adress;
    @BindView(R.id.ok)
    TextView ok;
    /*省市区*/
    private String area;
    private String city;
    private String province;

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_adress_choose;
    }

    @Override
    protected void initData() {
        adress.setGetAdress(new CityView.GetAdress() {
            @Override
            public void getAdress(String sheng, String shi, String qu) {
                province = sheng;
                city = shi;
                area = qu;
            }
        });
    }


    @Override
    public void GetHttpBackStr(String str, int what) {
        switch (what) {
            case 1:
                if (codeIsOne(str)) {
                    jumpToActivity(MainActivity.class, true);
                }
                break;
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            //do something.
            return true;
        } else {
            return super.dispatchKeyEvent(event);
        }
    }

    @OnClick({R.id.ok})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ok:
                setArea();
                break;
        }
    }

    /*设置地区*/
    private void setArea() {
        if (area == null || area.equals("")) {
            ShowToast("请选择您所在地址");
            return;
        }
        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("uid", MyApplication.getmInstance().getUid());//请求参数一
        formBuilder.add("province", province);//请求参数二
        formBuilder.add("city", city);//请求参数二
        formBuilder.add("area", area);//请求参数二
        Post(HttpUntil.GetIntent().setArea(), formBuilder, 1);

    }


}
