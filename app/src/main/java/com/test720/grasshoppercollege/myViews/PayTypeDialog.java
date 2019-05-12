package com.test720.grasshoppercollege.myViews;

import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

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
 * 作者：水东流 编于 2018/9/27
 */
public class PayTypeDialog extends BaseOkDialogFragment {

    String payType = "1";//支付方式 1，支付宝，2微信, 3余额

    @Override
    public DialogInit dialogInit() {
        return new DialogInit() {
            @Override
            public boolean outTouch() {
                return true;
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
        return R.layout.pay_type_dialog;
    }

    @Override
    public void start() {
        type(Integer.parseInt(payType));
    }

    @Override
    public void getSuccess(String s, int what) {

    }

    /*充值类型选择*/
    private void type(int i) {
        staus1.setImageResource(R.mipmap.huiq);
        staus2.setImageResource(R.mipmap.huiq);
        staus3.setImageResource(R.mipmap.huiq);
        payType = i + "";
        if (i == 1) {
            staus1.setImageResource(R.mipmap.goux);

        } else if (i == 2) {
            staus2.setImageResource(R.mipmap.goux);

        } else if (i == 3) {
            staus3.setImageResource(R.mipmap.goux);

        }
    }


    @OnClick({R.id.zhifubao_layout, R.id.weixin_layout, R.id.ye_layout, R.id.submission, R.id.lin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zhifubao_layout:
                type(1);
                break;
            case R.id.weixin_layout:
                type(2);
                break;
            case R.id.ye_layout:
                type(3);
                break;
            case R.id.submission:
                if (payBack != null) payBack.payBack(payType);
                dismissAllowingStateLoss();
                break;
            case R.id.lin:
                dismissAllowingStateLoss();
                break;
        }
    }


    @BindView(R.id.staus1)
    ImageView staus1;
    @BindView(R.id.staus2)
    ImageView staus2;
    @BindView(R.id.staus3)
    ImageView staus3;


    PayBack payBack;

    public void setPayBack(PayBack payBack) {
        this.payBack = payBack;
    }

    public interface PayBack {
        void payBack(String payType);
    }
}
