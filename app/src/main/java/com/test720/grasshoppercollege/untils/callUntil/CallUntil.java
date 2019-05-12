package com.test720.grasshoppercollege.untils.callUntil;

import android.content.Context;

import io.rong.callkit.RongCallKit;

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
 * 作者：水东流 编于 2018/10/9
 */
public class CallUntil {
    Context context;
    String targetId;//会话id
    String way;//拨号类别

    public CallUntil(Context context, String targetId, String way) {
        this.context = context;
        this.targetId = targetId;
        this.way = way;

    }

    /**
     * 拨号
     */
    public void startCall() {
        if (way.equals("1")) {
            startSingleVideoCall();
        } else {
            startSingleAudioCall();
        }
    }

    /**
     * 发起单人音频通话。
     */
    public void startSingleVideoCall() {

        RongCallKit.startSingleCall(context, targetId, RongCallKit.CallMediaType.CALL_MEDIA_TYPE_VIDEO);
    }

    /**
     * 发起单人视频频通话。
     */
    public void startSingleAudioCall() {

        RongCallKit.startSingleCall(context, targetId, RongCallKit.CallMediaType.CALL_MEDIA_TYPE_AUDIO);
    }

}
