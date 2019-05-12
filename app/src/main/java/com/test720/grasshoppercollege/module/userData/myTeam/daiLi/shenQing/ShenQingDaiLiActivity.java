package com.test720.grasshoppercollege.module.userData.myTeam.daiLi.shenQing;

import android.text.TextUtils;
import android.widget.EditText;

import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.HttpUntil;

import butterknife.BindView;
import butterknife.OnClick;

public class ShenQingDaiLiActivity extends BaseToolActivity {
    String provice = "";
    String city = "";
    String area = "";
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.beiZhu)
    EditText beiZhu;

    @Override
    protected String setTitle() {
        return "代理申请";
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_shen_qing_dai_li;
    }

    @Override
    protected void initData() {
        if (getIntent() != null) {
            provice = getIntent().getStringExtra("provice");
            city = getIntent().getStringExtra("city");
            area = getIntent().getStringExtra("area");
        }
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        if (codeIsOne(str)) {
            finish();
        }
    }


    @OnClick(R.id.toDaiLi)
    public void onViewClicked() {
        if (TextUtils.isEmpty(name.getText())) {
            name.setError("请输入名字");
            return;
        }
        if (TextUtils.isEmpty(beiZhu.getText())) {
            beiZhu.setError("请输入备注");
            return;
        }
        if (TextUtils.isEmpty(phone.getText()) || phone.getText().toString().length() < 11) {
            phone.setError("请输入正确电话");
            return;
        }
        HttpParams httpParams = new HttpParams();
        httpParams.put("name", name.getText().toString());
        httpParams.put("phone", phone.getText().toString());
        httpParams.put("desc", beiZhu.getText().toString());
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        httpParams.put("province", provice);
        httpParams.put("city", city);
        httpParams.put("area", area);
        postResponse(HttpUntil.GetIntent().UserapplyAgent(), httpParams, 1, true);
    }
}
