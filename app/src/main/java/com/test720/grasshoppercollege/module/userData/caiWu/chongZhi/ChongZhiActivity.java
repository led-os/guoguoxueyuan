package com.test720.grasshoppercollege.module.userData.caiWu.chongZhi;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.BasePlayActivity;
import com.test720.grasshoppercollege.HttpBean.ChongZhiShuJuData;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.HttpUntil;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;

public class ChongZhiActivity extends BasePlayActivity {


    @BindView(R.id.money)
    TextView money;
    @BindView(R.id.one)
    LinearLayout one;
    @BindView(R.id.two)
    LinearLayout two;
    @BindView(R.id.three)
    LinearLayout three;
    @BindView(R.id.four)
    LinearLayout four;
    @BindView(R.id.staus1)
    ImageView staus1;
    @BindView(R.id.zhifubao_layout)
    RelativeLayout zhifubaoLayout;
    @BindView(R.id.staus2)
    ImageView staus2;
    @BindView(R.id.weixin_layout)
    RelativeLayout weixinLayout;
    @BindView(R.id.ok)
    Button ok;
    String playType = "1";//充值方式 1，支付宝，2微信
    String chongzhi = "";
    @BindView(R.id.oneText)
    TextView oneText;
    @BindView(R.id.oneDouDou)
    TextView oneDouDou;
    @BindView(R.id.twoText)
    TextView twoText;
    @BindView(R.id.twoDouDou)
    TextView twoDouDou;
    @BindView(R.id.threeText)
    TextView threeText;
    @BindView(R.id.threeDouDou)
    TextView threeDouDou;
    @BindView(R.id.fourText)
    TextView fourText;
    @BindView(R.id.fourDouDou)
    TextView fourDouDou;
    ChongZhiShuJuData data;

    @Override
    protected String setTitle() {
        return "充值";
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_chong_zhi;
    }

    @Override
    protected void initData() {
        FormBody.Builder formBuilder = new FormBody.Builder();
        Post(HttpUntil.GetIntent().recharge(), formBuilder, 1);
    }

    @Override
    public float getJinE() {
        if (TextUtils.isEmpty(money.getText())) {
            return 0;
        } else {
            float f = Float.parseFloat(money.getText().toString());
            return f;
        }
    }

    @Override
    public String getPlayWay() {
        return playType;
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        super.GetHttpBackStr(str, what);
        if (codeIsOne(str, false) && what == 1) {
            data = new Gson().fromJson(str, ChongZhiShuJuData.class);
            money.setText(data.getData().getList().get(0).getMoney());
            oneText.setText("￥" + data.getData().getList().get(0).getMoney());
            oneDouDou.setText("(送" + data.getData().getList().get(0).getPoints() + "果果豆)");
            twoText.setText("￥" + data.getData().getList().get(1).getMoney());
            twoDouDou.setText("(送" + data.getData().getList().get(1).getPoints() + "果果豆)");
            threeText.setText("￥" + data.getData().getList().get(2).getMoney());
            threeDouDou.setText("(送" + data.getData().getList().get(2).getPoints() + "果果豆)");
            fourText.setText("￥" + data.getData().getList().get(3).getMoney());
            fourDouDou.setText("(送" + data.getData().getList().get(3).getPoints() + "果果豆)");

        }
    }

    @Override
    public void yuEZhiFu(String data) {

    }

    @OnClick({R.id.one, R.id.two, R.id.three, R.id.four, R.id.zhifubao_layout, R.id.weixin_layout, R.id.ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.one:
                check(1);
                break;
            case R.id.two:
                check(2);
                break;
            case R.id.three:
                check(3);
                break;
            case R.id.four:
                check(4);
                break;
            case R.id.zhifubao_layout:
                type(1);
                break;
            case R.id.weixin_layout:
                type(2);
                break;
            case R.id.ok:
                chongZhi();
                break;
        }
    }

    /*充值*/
    private void chongZhi() {
        if (TextUtils.isEmpty(money.getText())) {
            ShowToast("请选择充值金额");
            return;
        }
        if (playType == null) {
            ShowToast("请选择支付方式");
            return;
        }
        getOutTradeNo("3");
    }

    /*充值类型选择*/
    private void type(int i) {
        playType = i + "";
        if (i == 1) {
            staus2.setImageResource(R.mipmap.huiq);
            staus1.setImageResource(R.mipmap.goux);
        } else {
            staus1.setImageResource(R.mipmap.huiq);
            staus2.setImageResource(R.mipmap.goux);
        }
    }

    /*选择金额*/
    private void check(int i) {
        one.setBackgroundResource(R.drawable.light_dive_back);
        two.setBackgroundResource(R.drawable.light_dive_back);
        three.setBackgroundResource(R.drawable.light_dive_back);
        four.setBackgroundResource(R.drawable.light_dive_back);
        switch (i) {
            case 1:
                chongzhi = data.getData().getList().get(0).getMoney();
                one.setBackgroundResource(R.drawable.light_bule_back);
                break;
            case 2:
                chongzhi = data.getData().getList().get(1).getMoney();
                two.setBackgroundResource(R.drawable.light_bule_back);
                break;
            case 3:
                chongzhi = data.getData().getList().get(2).getMoney();
                three.setBackgroundResource(R.drawable.light_bule_back);
                break;
            case 4:
                chongzhi = data.getData().getList().get(3).getMoney();
                four.setBackgroundResource(R.drawable.light_bule_back);
                break;
        }
        money.setText(chongzhi + "");
    }

}
