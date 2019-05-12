package com.test720.grasshoppercollege.myViews;

import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test720.grasshoppercollege.BaseOkDialogFragment;
import com.test720.grasshoppercollege.R;

import java.util.ArrayList;
import java.util.List;

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
public class VipTypeDialog extends BaseOkDialogFragment {
    @BindView(R.id.monthPrice)
    TextView monthPrice;
    @BindView(R.id.staus1)
    ImageView staus1;
    @BindView(R.id.month)
    LinearLayout month;
    @BindView(R.id.seasonPrice)
    TextView seasonPrice;
    @BindView(R.id.staus2)
    ImageView staus2;
    @BindView(R.id.season)
    LinearLayout season;
    @BindView(R.id.half_a_yearPrice)
    TextView halfAYearPrice;
    @BindView(R.id.staus3)
    ImageView staus3;
    @BindView(R.id.half_a_year)
    LinearLayout halfAYear;
    @BindView(R.id.yearPrice)
    TextView yearPrice;
    @BindView(R.id.staus4)
    ImageView staus4;
    @BindView(R.id.year)
    LinearLayout year;
    @BindView(R.id.submission)
    Button submission;
    @BindView(R.id.lin)
    LinearLayout lin;
    private String vipType;
    private String type;
    private String typePrice = "";
    private int index = 0;//选中的标示
    List<String> priceList = new ArrayList<>();

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

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
        return R.layout.vip_type_dialog;
    }

    @Override
    public void start() {
        monthPrice.setText(priceList.get(0));
        seasonPrice.setText(priceList.get(1));
        halfAYearPrice.setText(priceList.get(2));
        yearPrice.setText(priceList.get(3));
        type(index + 1);
    }

    @Override
    public void getSuccess(String s, int what) {

    }


    @OnClick({R.id.month, R.id.season, R.id.half_a_year, R.id.year, R.id.submission})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.month:
                type(1);
                break;
            case R.id.season:
                type(2);
                break;
            case R.id.half_a_year:
                type(3);
                break;
            case R.id.year:
                type(4);
                break;
            case R.id.submission:
                if (vipTypeBack != null) {
                    vipTypeBack.vipType(vipType, typePrice, type);
                }
                dismissAllowingStateLoss();
                break;
        }
    }

    /*充值类型选择*/
    private void type(int i) {
        setIndex(i - 1);
        staus1.setImageResource(R.mipmap.huiq);
        staus2.setImageResource(R.mipmap.huiq);
        staus3.setImageResource(R.mipmap.huiq);
        staus4.setImageResource(R.mipmap.huiq);
        vipType = priceList.get(i - 1);
        if (i == 1) {
            typePrice = priceList.get(0) + getString(R.string.monthPrice);
            staus1.setImageResource(R.mipmap.goux);
            type = "yue";
        } else if (i == 2) {
            typePrice = priceList.get(1) + getString(R.string.seasonPrice);
            staus2.setImageResource(R.mipmap.goux);
            type = "jidu";
        } else if (i == 3) {
            typePrice = priceList.get(2) + getString(R.string.halfprice);
            staus3.setImageResource(R.mipmap.goux);
            type = "bannian";
        } else if (i == 4) {
            typePrice = priceList.get(3) + getString(R.string.yearPrice);
            staus4.setImageResource(R.mipmap.goux);
            type = "yinian";
        }
    }

    public void setPriceList(List<String> priceList) {
        this.priceList = priceList;
    }

    VipTypeBack vipTypeBack;

    public void setVipTypeBack(VipTypeBack vipTypeBack) {
        this.vipTypeBack = vipTypeBack;
    }

    public interface VipTypeBack {
        void vipType(String vipPrice, String text, String type);
    }
}
