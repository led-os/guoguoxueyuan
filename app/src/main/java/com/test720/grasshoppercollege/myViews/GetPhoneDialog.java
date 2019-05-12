package com.test720.grasshoppercollege.myViews;

import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.test720.grasshoppercollege.BaseOkDialogFragment;
import com.test720.grasshoppercollege.MyApplication;
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
 * 作者：水东流 编于 2018/12/29
 */
public class GetPhoneDialog extends BaseOkDialogFragment {
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.ok)
    TextView ok;
    @BindView(R.id.close)
    ImageView close;


    @Override
    public DialogInit dialogInit() {
        return new DialogInit() {
            @Override
            public boolean outTouch() {
                return false;
            }

            @Override
            public int gravity() {
                return Gravity.CENTER;
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
        return R.layout.get_phone_dialog;
    }

    @Override
    public void start() {
        String phone = MyApplication.getmInstance().getB_j_phone();
//        if (phone == null) phone = AppUploatUtils.getTelephone(mContext);
        if (phone == null) phone = "";
        MyApplication.getmInstance().setB_j_phone(phone);
        name.setText(MyApplication.getmInstance().b_j_phone);
    }

    @Override
    public void getSuccess(String s, int what) {

    }


    @OnClick({R.id.ok, R.id.close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ok:
                if (TextUtils.isEmpty(name.getText())) {
                    name.setError("请输入本机号码");
                    return;
                }
                if (name.getText().length() < 11) {
                    name.setError("请输入正确的11位号码");
                    return;
                }
                MyApplication.getmInstance().setB_j_phone(name.getText().toString());
                if (listener != null) listener.phone(name.getText().toString());
                dismissAllowingStateLoss();
                break;
            case R.id.close:
                dismissAllowingStateLoss();
                break;
        }
    }

    Listener listener;

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public interface Listener {
        void phone(String str);
    }
}
