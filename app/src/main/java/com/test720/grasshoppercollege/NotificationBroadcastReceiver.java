package com.test720.grasshoppercollege;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.test720.grasshoppercollege.module.youEr.baoBaoTingTing.MusicActivity;
import com.test720.grasshoppercollege.untils.musicMedia.MusicService;

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
 * 作者：水东流 编于 2018/8/23
 * <p>
 * 音乐播放状态栏点击和滑动监听
 */

public class NotificationBroadcastReceiver extends BroadcastReceiver {
    public static final String TYPE = "type"; //这个type是为了Notification更新信息的

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();
        int type = intent.getIntExtra("type", -1);
        if (type != -1) {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.cancel(type);
        }
        if (action.equals("notification_clicked")) {
            //处理点击事件
            Intent intent1 = new Intent(context, MusicActivity.class);
            intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent1);
        }

        if (action.equals("notification_cancelled")) {
            //处理滑动事件
            Intent intent2 = new Intent(context, MusicService.class);
            intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.stopService(intent2);
        }

        if (action.equals("notification_")) {
            switch (type) {
                case 1:
                    Intent intent3 = new Intent(context, MusicService.class);
                    intent3.putExtra("status", 1);
                    intent3.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent3);
                    break;
            }
        }
    }
}
