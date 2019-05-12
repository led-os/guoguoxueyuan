package com.test720.grasshoppercollege.untils;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.lljjcoder.citypickerview.widget.CityPicker;

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
 * 作者：水东流 编于 2018/10/3
 */
public class AdressUntil {
    private static final AdressUntil ourInstance = new AdressUntil();
    CityPicker cityPicker;//地址弹框
    AdressBack adressBack;

    public static AdressUntil getInstance() {
        return ourInstance;
    }

    private AdressUntil() {

    }

    /**
     * 弹出地址弹框
     */
    public void adressDialog(Activity mContext, String province, String city, String district, final AdressBack adressBack) {
        View view = mContext.getWindow().peekDecorView();
        if (view != null) {
            // 隐藏虚拟键盘
            InputMethodManager inputmanger = (InputMethodManager) mContext
                    .getSystemService(Activity.INPUT_METHOD_SERVICE);
            assert inputmanger != null;
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        cityPicker = new CityPicker.Builder(mContext)
                .textSize(14)
                .title("请选择区域")
                .titleBackgroundColor("#FFFFFF")
                .confirTextColor("#696969")
                .cancelTextColor("#696969")
                .province(province)
                .city(city)
                .district(district)
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
                adressBack.province(province.trim());
                adressBack.city(city.trim());
                adressBack.district(district.trim());
                adressBack.ShuaXin();

            }

            @Override
            public void onCancel() {

            }
        });


    }

    public void adressDialog(Activity mContext, AdressBack adressBack) {
        adressDialog(mContext, "", "", "", adressBack);
    }

    public interface AdressBack {
        void province(String pr);

        void city(String pr);

        void district(String pr);

        void ShuaXin();
    }

}
