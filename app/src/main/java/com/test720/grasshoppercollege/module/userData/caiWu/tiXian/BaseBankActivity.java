package com.test720.grasshoppercollege.module.userData.caiWu.tiXian;

import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.PublicUntil;

import butterknife.BindView;
import butterknife.OnClick;

public abstract class BaseBankActivity extends BaseToolActivity {


    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.bankName)
    EditText bankName;
    @BindView(R.id.cardId)
    EditText cardId;
    @BindView(R.id.id)
    EditText id;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.ok)
    Button ok;


    @Override
    protected int setlayoutResID() {
        return R.layout.activity_base_bank;
    }


    public abstract void okClick();

    @OnClick(R.id.ok)
    public void onViewClicked() {
        if (TextUtils.isEmpty(name.getText())) {
            name.setError("请输入名字");
            return;
        }
        if (TextUtils.isEmpty(phone.getText())) {
            phone.setError("请输入电话");
            return;
        }
        if (TextUtils.isEmpty(bankName.getText())) {
            bankName.setError("请输入银行名称");
            return;
        }
        if (TextUtils.isEmpty(cardId.getText())) {
            cardId.setError("请输入银行卡号");
            return;
        }
        if (TextUtils.isEmpty(id.getText())) {
            id.setError("请输入身份证");
            return;
        }
        if (phone.getText().toString().length() < 11) {
            phone.setError("请输入正确的电话号码");
            return;
        }
        if (!PublicUntil.isRealIDCard(id.getText().toString())) {
            id.setError("身份证格式有误");
            return;
        }
        okClick();
    }
}
