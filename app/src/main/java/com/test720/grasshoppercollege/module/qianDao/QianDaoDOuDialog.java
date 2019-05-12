package com.test720.grasshoppercollege.module.qianDao;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

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
 * 作者：水东流 编于 2018/10/18
 */
public class QianDaoDOuDialog extends BaseOkDialogFragment {
    @BindView(R.id.point)
    TextView point;
    String pin = "";


    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(false);
    }

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
        return R.layout.gx_dou_dou;
    }

    @Override
    public void start() {
        point.setText(pin);
    }

    @Override
    public void getSuccess(String s, int what) {

    }


    @OnClick({R.id.ok, R.id.rel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ok:
                if (okListener != null) okListener.ok();
            case R.id.rel:
                dismissAllowingStateLoss();
                break;
        }
    }

    OkListener okListener;

    public OkListener getOkListener() {
        return okListener;
    }

    public void setOkListener(OkListener okListener) {
        this.okListener = okListener;
    }

    public interface OkListener {
        void ok();
    }
}
