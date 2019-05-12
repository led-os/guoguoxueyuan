package com.test720.grasshoppercollege.module.diandu.bean;

import com.test720.grasshoppercollege.CurrencyEvent;

import org.greenrobot.eventbus.EventBus;

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
 * 作者：水东流 编于 2018/10/12
 */
public class PointReadingStatus {
    private static final PointReadingStatus ourInstance = new PointReadingStatus();

    public static PointReadingStatus getInstance() {
        return ourInstance;
    }


    private PointReadingStatus() {
    }

    boolean isDanQu = false;//单曲
    boolean isFanYi = true;//翻译
    boolean dianDuQu = false;//点读区
    float speedF = 1;//语速

    public boolean isDanQu() {
        return isDanQu;
    }

    public void setDanQu(boolean danQu) {
        isDanQu = danQu;
        CurrencyEvent event = new CurrencyEvent(getInstance(), 91);
        EventBus.getDefault().post(event);
    }

    public boolean isFanYi() {
        return isFanYi;
    }

    public void setFanYi(boolean fanYi) {
        isFanYi = fanYi;
        CurrencyEvent event = new CurrencyEvent(getInstance(), 92);
        EventBus.getDefault().post(event);
    }

    public boolean isDianDuQu() {
        return dianDuQu;
    }

    public void setDianDuQu(boolean dianDuQu) {
        this.dianDuQu = dianDuQu;
        CurrencyEvent event = new CurrencyEvent(getInstance(), 93);
        EventBus.getDefault().post(event);
    }

    public float getSpeedF() {
        return speedF;
    }

    public void setSpeedF(float speedF) {
        this.speedF = speedF;
        CurrencyEvent event = new CurrencyEvent(getInstance(), 94);
        EventBus.getDefault().post(event);
    }


}
