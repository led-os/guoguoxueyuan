package com.test720.grasshoppercollege.untils;

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
    String biaoShi = "7.7";//当前审核标示

    public String getBiaoShi() {
        return biaoShi;
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
