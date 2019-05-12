package com.test720.grasshoppercollege.module.userData;


import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.HttpUntil;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;

public class YiJianFanKuiActivity extends BaseToolActivity {


    @BindView(R.id.save_content)
    EditText saveContent;
    @BindView(R.id.save_opinion)
    Button saveOpinion;

    @Override
    protected String setTitle() {
        return "意见反馈";
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_yi_jian_fan_kui;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        if (codeIsOne(str)) {
            finish();
        }
    }


    @OnClick(R.id.save_opinion)
    public void onViewClicked() {
        if (TextUtils.isEmpty(saveContent.getText())) {
            saveContent.setError("意见不可为空");
            return;
        }
        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("content", saveContent.getText().toString());//请求参数一
        formBuilder.add("uid", MyApplication.getmInstance().getUid());//请求参数二
        Post(HttpUntil.GetIntent().Opinion(), formBuilder, 1, true);

    }
}
