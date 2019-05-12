package com.test720.grasshoppercollege.module.userData;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.login.LoginActivity;
import com.test720.grasshoppercollege.module.userData.updataPhone.UpdatePhoneOldActivity;
import com.test720.grasshoppercollege.untils.LoginUntil;

import butterknife.BindView;
import butterknife.OnClick;

public class SetActivity extends BaseToolActivity {


    @BindView(R.id.pay_password)
    RelativeLayout payPassword;
    @BindView(R.id.dresstext)
    TextView dresstext;
    @BindView(R.id.persionaladdress)
    RelativeLayout persionaladdress;
    @BindView(R.id.updata_phone)
    RelativeLayout updataPhone;
    @BindView(R.id.updata_password)
    RelativeLayout updataPassword;
    @BindView(R.id.clear_text)
    TextView clearText;
    @BindView(R.id.clear)
    RelativeLayout clear;
    @BindView(R.id.imageView3)
    ImageView imageView3;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.yijian)
    RelativeLayout yijian;
    @BindView(R.id.about_us)
    RelativeLayout aboutUs;
    @BindView(R.id.exit)
    TextView exit;
    @BindView(R.id.size)
    TextView size;
    @BindView(R.id.zhi_fu_text)
    TextView zhi_fu_text;
    @BindView(R.id.lin)
    LinearLayout lin;
    @BindView(R.id.activity_set)
    LinearLayout activitySet;

    @Override
    protected String setTitle() {
        return "设置";
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_set;
    }

    @Override
    protected void initData() {

    }


    @Override
    protected void onResume() {
        super.onResume();
        if (MyApplication.getmInstance().userData != null) {
            /*还没有支付密码*/
            if (MyApplication.getmInstance().userData.getData().getIs_pay_password() == 0) {
                zhi_fu_text.setText("设置");
            } else {
                zhi_fu_text.setText("修改");
            }
        }
    }

    @Override
    public void GetHttpBackStr(String str, int what) {

    }

    @OnClick({R.id.keFu, R.id.pay_password, R.id.persionaladdress, R.id.updata_phone, R.id.updata_password, R.id.clear, R.id.yijian, R.id.about_us, R.id.exit})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.pay_password:
                jumpToActivity(PhoneCodeActivity.class, false);
                break;
            case R.id.persionaladdress:
                jumpToActivity(MyAdressActivity.class, false);
                break;
            case R.id.updata_phone:
                jumpToActivity(UpdatePhoneOldActivity.class, false);
                break;
            case R.id.updata_password:
                jumpToActivity(UpdataPasswordActivity.class, false);
                break;
            case R.id.clear:
                jumpToActivity(ClearCacheActivity.class, false);
                break;
            case R.id.yijian:
                jumpToActivity(YiJianFanKuiActivity.class, false);
                break;
            case R.id.about_us:
                jumpToActivity(AboutAsActivity.class, false);
                break;
            case R.id.exit:
                tuiChuDengLu();
                break;
            case R.id.keFu:
                jumpToActivity(KeFuActivity.class, false);
                break;
        }
    }

    private void tuiChuDengLu() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("退出当前登录账号？")
                .setTitle("提示")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LoginUntil.getInstance().cleanLoginData(mcontext);
                        Intent intent = new Intent(mcontext, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .create().show();
    }
}
