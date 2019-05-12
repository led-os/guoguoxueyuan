package com.test720.grasshoppercollege.module.userData.caiWu.tiXian;

import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.R;

import butterknife.BindView;
import butterknife.OnClick;

public  abstract class BaseZFBActivity extends BaseToolActivity {


    @BindView(R.id.zfb)
    EditText zfb;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.ok)
    Button ok;

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_base_zfb;
    }

    public abstract void okClick();
    @OnClick(R.id.ok)
    public void onViewClicked() {
        if (TextUtils.isEmpty(name.getText())) {
            name.setError("请输入名字");
            return;
        }
        if (TextUtils.isEmpty(zfb.getText())) {
            zfb.setError("请输入账户");
            return;
        }
        okClick();
    }
}
