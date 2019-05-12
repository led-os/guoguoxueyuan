package com.test720.grasshoppercollege.untils;

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
 * 作者：水东流 编于 2018/9/26
 */

import android.os.CountDownTimer;

/**
 * 倒计时工具，
 */
public class CountDownTimerUtil {
    private CountDownTimer countDownTimer;//
    private int time;//剩余时间

    public CountDownTimerUtil(int t) {
        this.time = t;
        if (countDownTimer == null) {
            countDownTimer = new CountDownTimer(time * 1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    time--;
                    if (timeInterface != null) timeInterface.upDataUi(time);
                }

                @Override
                public void onFinish() {
                    if (timeInterface != null) timeInterface.timeIsOver();
                }
            };
        }
    }

    /**
     * 开始
     */
    public void start() {
        countDownTimer.start();
    }

    /**
     * 暂停
     */
    public void pause() {
        over();
    }

    /**
     * 继续
     */
    public void continueTime() {
        if (countDownTimer == null) {
            countDownTimer = new CountDownTimer(time * 1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    time--;
                    if (timeInterface != null) timeInterface.upDataUi(time);
                }

                @Override
                public void onFinish() {
                    if (timeInterface != null) timeInterface.timeIsOver();
                }
            }.start();
        }
    }

    /**
     * 结束
     */
    public void over() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
    }

    /**
     * 跳过
     */
    public void finishCount() {
        if (countDownTimer != null) {
            countDownTimer.onFinish();
            countDownTimer.cancel();
            countDownTimer = null;
        }
    }

    public int getTime() {
        return time;
    }

    TimeInterface timeInterface;

    public void setTimeInterface(TimeInterface timeInterface) {
        this.timeInterface = timeInterface;
    }

    public interface TimeInterface {
        void upDataUi(int time);

        void timeIsOver();
    }
}
