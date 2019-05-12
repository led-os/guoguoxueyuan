package com.test720.grasshoppercollege.module.guoguoDou;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.guoguoDou.bean.DuiHuanAdressData;
import com.test720.grasshoppercollege.module.userData.MyAdressActivity;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.glideUntil.GlideUntil;

import butterknife.BindView;
import butterknife.OnClick;

public class GuoGuoDouDuihuanActivity extends BaseToolActivity {

    String addressId = "";

    @Override
    protected String setTitle() {
        return "确认订单";
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_guo_guo_dou_duihuan;
    }

    @Override
    protected void initData() {
        String c = "x" + getIntent().getIntExtra("count", 0);
        shangPingNum.setText(c);

        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        httpParams.put("gid", getIntent().getStringExtra("gid"));
        httpParams.put("value", getIntent().getStringExtra("value"));
        httpParams.put("count", getIntent().getIntExtra("count", 0));
        postResponse(HttpUntil.GetIntent().getIP() + "small.php/User/confirmExchangeGoods", httpParams, 1, true);

    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        switch (what) {
            case 1:
                if (codeIsOne(str, false)) {
                    DuiHuanAdressData duiHuanAdressData = new Gson().fromJson(str, DuiHuanAdressData.class);
                    //是否存在地址信息
                    if (duiHuanAdressData.getData().getAddress() == null) {
                        addAddress.setVisibility(View.VISIBLE);
                        addressRel.setVisibility(View.GONE);
                    } else {
                        addressRel.setVisibility(View.VISIBLE);
                        addAddress.setVisibility(View.GONE);
                        String s = "收货人：" + duiHuanAdressData.getData().getAddress().getConsignee();
                        shouHuoName.setText(s);
                        phonetext.setText(duiHuanAdressData.getData().getAddress().getPhone());
                        String a = duiHuanAdressData.getData().getAddress().getProvince() +
                                duiHuanAdressData.getData().getAddress().getCity() + duiHuanAdressData.getData().getAddress().getArea();
                        addresstext.setText(a);
                        addressId = duiHuanAdressData.getData().getAddress().getAddress_id();
                    }

                    //商品信息
                    GlideUntil.getInstance().imgUrl(mcontext, smallItemImage, duiHuanAdressData.getData().getGoods().getCover());
                    shangPinTitle.setText(duiHuanAdressData.getData().getGoods().getName());
                    smallPrice.setText(duiHuanAdressData.getData().getGoods().getPrice());
                    totalPrice.setText(duiHuanAdressData.getData().getOrder_price());
                }
                break;
            case 5:
                if (codeIsOne(str)) finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*地址选择回返*/
        if (requestCode == 123 && resultCode == 1) {
            addressRel.setVisibility(View.VISIBLE);
            addAddress.setVisibility(View.GONE);
            addressId = data.getStringExtra("id");
            addresstext.setText(data.getStringExtra("adress"));
            String s = "收货人:" + data.getStringExtra("name");
            shouHuoName.setText(s);
            phonetext.setText(data.getStringExtra("phone"));
        }
    }

    @OnClick({R.id.addressRel, R.id.add_address, R.id.ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.addressRel:
            case R.id.add_address:
                Intent intent = new Intent(mcontext, MyAdressActivity.class);
                startActivityForResult(intent, 123);
                break;
            case R.id.ok:
                queRen();
                break;
        }
    }

    private void queRen() {
        if (addressId.equals("")) {
            ShowToast("请设置收货地址");
            return;
        }
        HttpParams httpParams = new HttpParams();
        httpParams.put("gid", getIntent().getStringExtra("gid"));
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        httpParams.put("address_id", addressId);
        httpParams.put("value", getIntent().getStringExtra("value"));
        httpParams.put("count", getIntent().getIntExtra("count", 0));
        postResponse(HttpUntil.GetIntent().getIP() + "small.php/User/exchangeGoods", httpParams, 5, true);
    }


    @BindView(R.id.shouHuoName)
    TextView shouHuoName;
    @BindView(R.id.phonetext)
    TextView phonetext;
    @BindView(R.id.addresstext)
    TextView addresstext;
    @BindView(R.id.addressRel)
    RelativeLayout addressRel;
    @BindView(R.id.add_address)
    RelativeLayout addAddress;
    @BindView(R.id.small_item_image)
    ImageView smallItemImage;
    @BindView(R.id.shangPinTitle)
    TextView shangPinTitle;
    @BindView(R.id.shangPingNum)
    TextView shangPingNum;
    @BindView(R.id.small_price)
    TextView smallPrice;
    @BindView(R.id.total_price)
    TextView totalPrice;
}
