package com.test720.grasshoppercollege.module.userData.caiWu.tiXian;

import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
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
 * 作者：水东流 编于 2018/11/5
 */
public class TiXianTypeDialog extends BaseOkDialogFragment {
    @BindView(R.id.diss)
    TextView diss;
    @BindView(R.id.popuptitle)
    TextView popuptitle;
    @BindView(R.id.zhi_fu_bao)
    RelativeLayout zhiFuBao;
    @BindView(R.id.bank)
    RelativeLayout bank;
    @BindView(R.id.cancle)
    TextView cancle;

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
        return R.layout.zhi_fu_bao_and_bank;
    }

    @Override
    public void start() {

    }

    @Override
    public void getSuccess(String s, int what) {

    }

    public interface ItemClick {

        void item(String str, String type);

    }

    public ItemClick itemClick;

    public void setItemClick(ItemClick itemClick) {
        this.itemClick = itemClick;
    }

    @OnClick({R.id.zhi_fu_bao, R.id.bank, R.id.cancle})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zhi_fu_bao:
                itemClick.item("支付宝", "2");
                dismissAllowingStateLoss();
                break;
            case R.id.bank:
                itemClick.item("银行卡", "1");
                dismissAllowingStateLoss();
                break;
            case R.id.cancle:
                dismissAllowingStateLoss();
                break;
        }
    }
}
