package com.test720.grasshoppercollege.module.userData;

import android.app.Activity;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lljjcoder.citypickerview.widget.CityPicker;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.HttpUntil;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;

/*编辑地址共用*/
public abstract class BaseSetAdressActivity extends BaseToolActivity {


    @BindView(R.id.text1)
    TextView text1;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.text2)
    TextView text2;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.text3)
    TextView text3;
    @BindView(R.id.sheng_shi_qu_adress)
    TextView shengShiQuAdress;
    @BindView(R.id.ima)
    ImageView ima;
    @BindView(R.id.add_address)
    RelativeLayout addAddress;
    @BindView(R.id.text4)
    TextView text4;
    @BindView(R.id.other)
    EditText other;
    @BindView(R.id.confirm)
    TextView confirm;
    @BindView(R.id.main)
    RelativeLayout main;

    String sheng;
    String shi;
    String qu;
    @BindView(R.id.dele)
    public RelativeLayout dele;


    @Override
    protected int setlayoutResID() {
        return R.layout.activity_add_adress;
    }

    private void selectAddress(String s, String c, String q) {
        View view = mcontext.getWindow().peekDecorView();
        if (view != null) {
            // 隐藏虚拟键盘
            InputMethodManager inputmanger = (InputMethodManager) mcontext
                    .getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }


        CityPicker cityPicker = new CityPicker.Builder(this)
                .textSize(14)
                .title("请选择区域")
                .titleBackgroundColor("#FFFFFF")
                .confirTextColor("#696969")
                .cancelTextColor("#696969")
                .province(s)
                .city(c)
                .district(q)
                .textColor(Color.parseColor("#000000"))
                .provinceCyclic(true)
                .cityCyclic(false)
                .districtCyclic(false)
                .visibleItemsCount(7)
                .itemPadding(10)
                .onlyShowProvinceAndCity(false)
                .build();
        cityPicker.show();
        //监听方法，获取选择结果
        cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
                //省份
                String province = citySelected[0];
                //城市
                String city = citySelected[1];
                //区县（如果设定了两级联动，那么该项返回空）
                String district = citySelected[2];
                //邮编
                String code = citySelected[3];
                //为TextView赋值
                shengShiQuAdress.setText(province.trim() + "-" + city.trim() + "-" + district.trim());
                sheng = province.trim();
                shi = city.trim();
                qu = district.trim();
            }

            @Override
            public void onCancel() {

            }
        });
    }

    @Override
    public void GetHttpBackStr(String str, int what) {

    }

    @OnClick({R.id.add_address, R.id.confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_address:
                if (sheng != null && shi != null && qu != null) {
                    selectAddress(sheng, shi, qu);
                } else {
                    selectAddress("", "", "");
                }
                break;
            case R.id.confirm:
                saveAdress(getIntent().getStringExtra("id"));
                break;
        }
    }

    private void saveAdress(String id) {
        if (TextUtils.isEmpty(other.getText())) {
            other.setError("请输入详细地址");
            return;
        }
        if (TextUtils.isEmpty(name.getText())) {
            name.setError("请输入收货人名字");
            return;
        }
        if (TextUtils.isEmpty(phone.getText())) {
            phone.setError("请输入收货人手机号");
            return;
        }
        if (phone.getText().toString().length() < 11) {
            phone.setError("请输入正确的手机号");
            return;
        }
        if (sheng == null) {
            ShowToast("请设置地址");
            return;
        }
        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("uid", MyApplication.getmInstance().getUid());//请求参数二
        if (id != null) formBuilder.add("address_id", id);//请求参数二
        formBuilder.add("consignee", name.getText().toString());//请求参数二
        formBuilder.add("phone", phone.getText().toString());//请求参数二
        formBuilder.add("province", sheng);//请求参数二
        formBuilder.add("city", shi);//请求参数二
        formBuilder.add("area", qu);//请求参数二
        formBuilder.add("other", other.getText().toString());//请求参数二
        Post(HttpUntil.GetIntent().saveAddress(), formBuilder, 2, true);

    }

}
