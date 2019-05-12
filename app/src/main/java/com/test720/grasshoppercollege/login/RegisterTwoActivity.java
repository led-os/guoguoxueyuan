package com.test720.grasshoppercollege.login;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.myViews.choosetime.HengXiangCityView;
import com.test720.grasshoppercollege.untils.HttpUntil;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;

public class RegisterTwoActivity extends BaseToolActivity {

    @BindView(R.id.register)
    TextView register;
    @BindView(R.id.cityview)
    HengXiangCityView cityview;

    /*省市区*/
    private String qu;
    private String shi;
    private String sheng;

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_register_two;
    }

    @Override
    protected void initData() {
        cityview.setGetAdress(new HengXiangCityView.GetAdress() {
            @Override
            public void getAdress(String province, String city, String area) {
                sheng = province;
                shi = city;
                qu = area;
            }
        });
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        switch (what) {
            case 1:
                try {
                    JSONObject jsonObject = new JSONObject(str);
                    ShowToast(jsonObject.getString("msg"));
                    if (jsonObject.getInt("code") == 1) {
                        RegisterData data = new Gson().fromJson(str, RegisterData.class);
                        /*弹出果果豆弹框*/
                        showDialog(data);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void showDialog(RegisterData data) {
        Intent intent = new Intent(mcontext, PhoneLoginActivity.class);
        intent.putExtra("phone", getIntent().getStringExtra("phone"));
        intent.putExtra("dou", data.getData().getPoints());
        EventBus.getDefault().post(new CurrencyEvent(CurrencyEvent.REGOK, "OK"));
        startActivity(intent);
        finish();
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


    /*注册*/
    private void register() {
        if (qu == null || qu.equals("")) {
            ShowToast("请选择您所在地址");
            return;
        }
        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("phone", getIntent().getStringExtra("phone"));//请求参数一
        formBuilder.add("code", getIntent().getStringExtra("code"));//请求参数二
        formBuilder.add("password", getIntent().getStringExtra("password"));//请求参数三
        formBuilder.add("area", qu);//请求参数四
        formBuilder.add("city", shi);//请求参数四
        formBuilder.add("province", sheng);//请求参数四
        Post(HttpUntil.GetIntent().getegisterUrl(), formBuilder, 1);
    }


    @OnClick({R.id.register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.register:
                register();
                break;
        }
    }

    /*注册返回数据类*/
    class RegisterData {

        /**
         * code : 1
         * msg : 注册成功
         * data : {"uid":"1206","token":"LBM7OHff6AcmFuZFN0ciI6IkxCTTdPeyJ1aWQiOiIxMjA2IiwiSGZmNkEiLCJ0aW1lIjoxNTIwMjE4ODcyfQ==","rong_token":"","rong_uid":0}
         */

        private int code;
        private String msg;
        private DataBean data;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public class DataBean {
            /**
             * uid : 1206
             * token : LBM7OHff6AcmFuZFN0ciI6IkxCTTdPeyJ1aWQiOiIxMjA2IiwiSGZmNkEiLCJ0aW1lIjoxNTIwMjE4ODcyfQ==
             * rong_token :
             * rong_uid : 0
             */

            private String uid;
            private String points;
            private String token;
            private String rong_token;
            private int rong_uid;

            public String getPoints() {
                return points;
            }

            public void setPoints(String points) {
                this.points = points;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }

            public String getRong_token() {
                return rong_token;
            }

            public void setRong_token(String rong_token) {
                this.rong_token = rong_token;
            }

            public int getRong_uid() {
                return rong_uid;
            }

            public void setRong_uid(int rong_uid) {
                this.rong_uid = rong_uid;
            }
        }
    }
}
