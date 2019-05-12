package com.test720.grasshoppercollege.module.shangCheng.fragment;

import android.view.Gravity;
import android.view.WindowManager;

import com.test720.grasshoppercollege.BaseOkDialogFragment;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.myViews.ViewAmount;

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
 * 作者：水东流 编于 2018/11/6
 */
public class AmountDialog extends BaseOkDialogFragment {
    @BindView(R.id.amount)
    ViewAmount amount;

    int max = 1;//最大
    int min = 0;//最小
    int count = 0;//默认数量

    public void setMax(int max) {
        this.max = max;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setCount(int count) {
        this.count = count;
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
        return R.layout.num_check_dialog;
    }

    @Override
    public void start() {
        amount.setMinNum(min);
        amount.setGoods_storage(max);
        amount.setAmount(count);
    }

    @Override
    public void getSuccess(String s, int what) {

    }

    public interface AmountBack {
        void amountNum(int num);
    }

    AmountBack amountBack;

    public void setAmountBack(AmountBack amountBack) {
        this.amountBack = amountBack;
    }

    @OnClick(R.id.ok)
    public void onViewClicked() {
        if (amountBack != null) amountBack.amountNum(amount.getAmount());
        dismissAllowingStateLoss();
    }
}
