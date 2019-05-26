package com.test720.grasshoppercollege.untils;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.test720.grasshoppercollege.MyApplication;

import www.test720.mylibrary.SPUtils;

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
 * 作者：水东流 编于 2018/12/24
 * 审核版本管理工具
 */
public class ShenHeUntil {
    private static final ShenHeUntil ourInstance = new ShenHeUntil();

    public static ShenHeUntil getInstance() {
        return ourInstance;
    }

    private ShenHeUntil() {
    }

    //2.0 百度3.0、、
    // 2.1 华为3.1,,
    // 2.2 腾讯 3.2
    // 2.3 小米
    //  2.4 oppo
    //  2.5 360
    //  2.6 vivo
    // 2.7 魅族
    //当前审核版本标示
    public String getBiaoShi() {
        String bs = "";
        switch (getChannel()) {
            case "baidu":
                bs = ".0";
                break;
            case "huawei":
                bs = ".1";
                break;
            case "tencent":
                bs = ".2";
                break;
            case "xiaomi":
                bs = ".3";
                break;
            case "oppo":
                bs = ".4";
                break;
            case "zhushou360":
                bs = ".5";
                break;
            case "vivo":
                bs = ".6";
                break;
            case "meizhu":
                bs = ".7";
                break;
        }
        return getVerCode() + bs;
    }

    /**
     * @return 渠道来源
     */
    private String getChannel() {
        try {
            PackageManager pm = MyApplication.getmInstance().getPackageManager();
            ApplicationInfo appInfo = pm.getApplicationInfo(MyApplication.getmInstance().getPackageName(), PackageManager.GET_META_DATA);
            return appInfo.metaData.getString("GUOGUO_CHANNEL");
        } catch (PackageManager.NameNotFoundException ignored) {
        }
        return "";
    }

    /**
     * 得到软件版本号
     *
     * @return 当前版本Code
     */
    private int getVerCode() {
        int verCode = -1;
        try {
            String packageName = MyApplication.getmInstance().getPackageName();
            verCode = MyApplication.getmInstance().getPackageManager().getPackageInfo(packageName, 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return verCode;
    }

    /**
     * 当前是否是审核
     *
     * @return
     */
    public boolean isShenHe() {
        return SPUtils.getBoolean(MyApplication.getmInstance().getApplicationContext(), SPUtils.ISSHENHE, true);
    }

    /**
     * "https://www.hzggedu.com/ggxy/";//正式地址
     * "https://www.hzggedu.com/ggxytest/";
     * "http://app.guoguoxueyuan.com/ggxytest/";//审核地址
     * "http://www.guoguoxueyuan.com/ggxy/";
     * "http://www.guoguoxueyuan.com/ggxydemo/";
     */
    public void setShenHe(boolean shenHe) {
        SPUtils.putBoolean(MyApplication.getmInstance().getApplicationContext(), SPUtils.ISSHENHE, shenHe);
        if (shenHe)
            HttpUntil.GetIntent().setIP("http://app.guoguoxueyuan.com/ggxytest/");
        else
            HttpUntil.GetIntent().setIP("https://www.hzggedu.com/ggxy/");
    }
}
