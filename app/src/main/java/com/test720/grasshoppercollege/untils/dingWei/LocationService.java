package com.test720.grasshoppercollege.untils.dingWei;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;

import com.test720.grasshoppercollege.untils.LogUtil;

import java.util.List;
import java.util.Locale;

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
 * 作者：水东流 编于 2018/10/2
 */
public class LocationService extends Service {


    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        init();
        return super.onStartCommand(intent, flags, startId);
    }

    private void init() {
        LocationManager ls = (LocationManager) getSystemService(LOCATION_SERVICE);
        //获取所有可用的位置提供器
        assert ls != null;
        List<String> providers = ls.getProviders(true);
        String locationProvider = "";
        if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
            //如果是Network
            locationProvider = LocationManager.NETWORK_PROVIDER;
        } else if (providers.contains(LocationManager.GPS_PROVIDER)) {
            //如果是GPS
            locationProvider = LocationManager.GPS_PROVIDER;
        } else {
            LogUtil.logError("没有可用的位置提供器");
        }
        LogUtil.logError("locationProvider=" + locationProvider);
        //获取Location
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            LogUtil.logError("未获取权限");
        } else {
            MyListener listener = new MyListener();
            //不是一次就能成功的
            Location locl = ls.getLastKnownLocation(locationProvider);
            if (locl != null) {
                ls.requestLocationUpdates("gps", 60000, 1, listener);
                saveLocation(locl);
            } else {
                //监视地理位置变化
                ls.requestLocationUpdates(locationProvider, 3000, 1, listener);
                LogUtil.logError("location==null");
            }
        }
    }

    public void saveLocation(Location location) {

        LogUtil.logError("location==ok");
        double longitude = location.getLongitude();
        double altitude = location.getLatitude();
//        MyApplication.getmInstance().setLatitude(altitude + "");
//        MyApplication.getmInstance().setLongitude(location + "");
        LogUtil.logError("longitude=" + longitude);

        LogUtil.logError("altitude=" + altitude);
        List<Address> result;
        try {
            Geocoder gc = new Geocoder(this, Locale.getDefault());
            result = gc.getFromLocation(location.getLatitude(),
                    location.getLongitude(), 1);
//            MyApplication.getmInstance().setCity(result.get(0).getLocality());
            LogUtil.logError("市区位置获取成功" + result.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        LogUtil.logError("定位ok");
    }

    private class MyListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            // TODO Auto-generated method stub
            saveLocation(location);
            LogUtil.logError("locationChange");
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub
            LogUtil.logError("locationEnable");
        }

        @Override
        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub

        }

    }

}
