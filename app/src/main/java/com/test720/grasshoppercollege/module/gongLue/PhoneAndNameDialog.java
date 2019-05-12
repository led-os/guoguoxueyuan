package com.test720.grasshoppercollege.module.gongLue;

import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.test720.grasshoppercollege.BaseOkDialogFragment;
import com.test720.grasshoppercollege.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 佛祖保佑        永无BUG
 * 佛曰:
 * 程序园里程序天，程序天里程序员；
 * 程序猿人写程序，又拿程序换肉钱。
 * 肉饱继续桌前坐，饱暖还是桌前眠；
 * 半迷半醒日复日，码上码下年复年。
 * 但愿叱咤互联世，不愿搬砖码当前；
 * 诸葛周瑜算世事，我算需求得加钱。
 * 别人笑我忒直男，我说自己是程猿；
 * 但见成都府国内，处处地地程序员。
 * 作者：水东流 编于 2018/10/19
 */
public class PhoneAndNameDialog extends BaseOkDialogFragment {
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.school_name)
    EditText school_name;
    @BindView(R.id.ok)
    Button ok;
    @BindView(R.id.rel)
    RelativeLayout rel;

    @Override
    public DialogInit dialogInit() {
        return new DialogInit() {
            @Override
            public boolean outTouch() {
                return false;
            }

            @Override
            public int gravity() {
                return Gravity.BOTTOM;
            }

            @Override
            public int widthLayout() {
                return WindowManager.LayoutParams.MATCH_PARENT;
            }

            @Override
            public int heightLayout() {
                return WindowManager.LayoutParams.MATCH_PARENT;
            }
        };
    }

    @Override
    public int layoutId() {
        return R.layout.phone_and_name_dialog;
    }

    @Override
    public void start() {

    }

    @Override
    public void getSuccess(String s, int what) {

    }


    @OnClick({R.id.ok, R.id.rel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ok:
                if (TextUtils.isEmpty(name.getText())) {
                    name.setError("请输入名字");
                    return;
                }
                if (TextUtils.isEmpty(phone.getText())) {
                    phone.setError("请输入电话");
                    return;
                }
                if (TextUtils.isEmpty(school_name.getText())) {
                    phone.setError("请输入机构名");
                    return;
                }
                if (phoneAndName != null) {
                    phoneAndName.phoneAndName(name.getText().toString(), phone.getText().toString(), school_name.getText().toString());
                    dismissAllowingStateLoss();
                }
                break;
            case R.id.rel:
                dismissAllowingStateLoss();
                break;
        }
    }

    PhoneAndName phoneAndName;

    public void setPhoneAndName(PhoneAndName phoneAndName) {
        this.phoneAndName = phoneAndName;
    }

    public interface PhoneAndName {
        void phoneAndName(String name, String phone, String school_name);
    }
}
