package com.test720.grasshoppercollege;

import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.test720.grasshoppercollege.module.userData.PhoneCodeActivity;
import com.test720.grasshoppercollege.myViews.PlayByYuEDialog;

import butterknife.BindView;
import butterknife.OnClick;

public abstract class BaseZhuanYEActivity extends BaseToolActivity {

    @BindView(R.id.price)
    TextView price;//当前余额展示控件
    @BindView(R.id.edit)
    EditText edit;
    @BindView(R.id.zhuanRu)
    TextView zhuanRu;


    @Override
    protected String setTitle() {
        return "转入余额";
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_zhuan_ye;
    }

    @Override
    public void initView() {
        super.initView();
        if (getIntent().getStringExtra("money") != null)
            price.setText(getIntent().getStringExtra("money"));
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        if (codeIsOne(str)) {
            finish();
        }
    }


    @OnClick(R.id.zhuanRu)
    public void onViewClicked() {
        if (TextUtils.isEmpty(edit.getText())) {
            edit.setError("请输入金额");
            return;
        }
        if (TextUtils.isEmpty(price.getText())) {
            ShowToast("无可转佣金");
            return;
        }
        Float f = Float.parseFloat(price.getText().toString());
        Float e = Float.parseFloat(edit.getText().toString());

        if (e == 0) {
            ShowToast("金额不可为零 ");
            return;
        }

        if (e > f) {
            ShowToast("提取佣金超额");
            return;
        }

        /*未设置支付密码时，须跳转*/
        if (MyApplication.getmInstance().userData.getData().getIs_pay_password() != 1) {
            jumpToActivity(PhoneCodeActivity.class, false);
            ShowToast("请设置支付密码！");
            return;
        }

        //余额支付
        PlayByYuEDialog dialog = new PlayByYuEDialog();
        dialog.setMoneyStr(edit.getText().toString());
        dialog.setPlayOk(new PlayByYuEDialog.PlayOk() {
            @Override
            public void playOk(String pwd) {
                payOk(pwd, edit.getText().toString());
            }

            @Override
            public void close() {

            }
        });
        dialog.show(getSupportFragmentManager(), "play");
    }

    public abstract void payOk(String pwd, String money);
}
