package com.test720.grasshoppercollege.module.userData.caiWu;

import android.view.View;
import android.widget.TextView;

import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.userData.caiWu.chongZhi.ChongZhiActivity;
import com.test720.grasshoppercollege.module.userData.caiWu.tiXian.TiXianGuanLiActivity;
import com.test720.grasshoppercollege.module.userData.caiWu.tiXian.TiXianTypeActivity;

import butterknife.BindView;
import butterknife.OnClick;

import static com.test720.grasshoppercollege.R.id.total_price;

public class CaiWuActivity extends BaseToolActivity {
    @BindView(total_price)
    TextView totalPrice;


    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_cai_wu;
    }

    @Override
    protected void initData() {
        String p = "￥" + MyApplication.getmInstance().userData.getData().getMoney();
        totalPrice.setText(p);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void GetHttpBackStr(String str, int what) {

    }


    @OnClick({R.id.back, R.id.mybank, R.id.ming_xi, R.id.chong_zhi, R.id.ti_xian})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.ming_xi:
                jumpToActivity(CaiWuMingXiActivity.class, false);
                break;
            case R.id.chong_zhi:
                jumpToActivity(ChongZhiActivity.class, true);
                break;
            case R.id.mybank://我的账户
                jumpToActivity(TiXianTypeActivity.class, false);
                break;
            case R.id.ti_xian:
                jumpToActivity(TiXianGuanLiActivity.class, true);
                break;
        }
    }
}
