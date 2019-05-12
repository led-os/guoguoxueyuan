package com.test720.grasshoppercollege.module.userData.caiWu.tiXian;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.HttpBean.ZhiFuTypeData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.HttpUntil;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;


public class TiXianTypeActivity extends BaseToolActivity {


    @BindView(R.id.bankName)
    TextView bankName;
    @BindView(R.id.bankType)
    TextView bankType;
    @BindView(R.id.num)
    TextView num;
    @BindView(R.id.bank_Rel)
    RelativeLayout bankRel;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.zfb)
    TextView zfb;
    @BindView(R.id.zhi_fu_bao_Rel)
    RelativeLayout zhiFuBaoRel;
    @BindView(R.id.no_bank)
    TextView noBank;
    @BindView(R.id.no_zhi_fu_bao)
    TextView noZhiFuBao;
    ZhiFuTypeData data;

    @Override
    protected String setTitle() {
        return "我的银行卡";
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_ti_xian_type;
    }

    @Override
    protected void initData() {
        initdata();
    }

    /*更新ui*/
    private void initdata() {
        if (MyApplication.getmInstance().userData.getData().getZfb() == 0) {
            noZhiFuBao.setVisibility(View.VISIBLE);
            zhiFuBaoRel.setVisibility(View.GONE);
        } else {
            noZhiFuBao.setVisibility(View.GONE);
            zhiFuBaoRel.setVisibility(View.VISIBLE);
        }
        if (MyApplication.getmInstance().userData.getData().getBank() == 0) {
            noBank.setVisibility(View.VISIBLE);
            bankRel.setVisibility(View.GONE);
        } else {
            noBank.setVisibility(View.GONE);
            bankRel.setVisibility(View.VISIBLE);
        }

        FormBody.Builder build = new FormBody.Builder();
        build.add("uid", MyApplication.getmInstance().getUid());
        Post(HttpUntil.GetIntent().drawType(), build, 1);
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        if (codeIsOne(str, false)) {
            data = new Gson().fromJson(str, ZhiFuTypeData.class);
            if (data.getData().getBank() != null)
                bankName.setText(data.getData().getBank().getBank());
            if (data.getData().getBank() != null)
                bankType.setText(data.getData().getBank().getName());
            if (data.getData().getBank() != null)
                num.setText(data.getData().getBank().getNumber());
            if (data.getData().getZfb() != null) zfb.setText(data.getData().getZfb().getZfb());
            if (data.getData().getZfb() != null) name.setText(data.getData().getZfb().getName());
        }
    }


    @OnClick({R.id.bank_Rel, R.id.zhi_fu_bao_Rel, R.id.no_bank, R.id.no_zhi_fu_bao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bank_Rel:
                Intent in = new Intent(this, BankXiangQingActivity.class);
                in.putExtra("name", data.getData().getBank().getName());
                in.putExtra("bankCard", data.getData().getBank().getNumber());
                in.putExtra("bankName", data.getData().getBank().getBank());
                in.putExtra("phone", data.getData().getBank().getPhone());
                in.putExtra("id", data.getData().getBank().getId_card());
                startActivityForResult(in, 1);
                break;
            case R.id.zhi_fu_bao_Rel:
                Intent in1 = new Intent(this, ZFBXiangQingActivity.class);
                in1.putExtra("name", data.getData().getZfb().getName());
                in1.putExtra("zfb", data.getData().getZfb().getZfb());
                startActivityForResult(in1, 2);
                break;
            case R.id.no_bank:
                startActivityForResult(new Intent(this, BangDingBankActivity.class), 3);
                break;
            case R.id.no_zhi_fu_bao:
                startActivityForResult(new Intent(this, BangDingZFBActivity.class), 4);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        initdata();
    }
}
