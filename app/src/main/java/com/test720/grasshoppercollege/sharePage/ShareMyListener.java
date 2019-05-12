package com.test720.grasshoppercollege.sharePage;

import android.content.Context;
import android.widget.Toast;

import com.test720.grasshoppercollege.CurrencyEvent;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

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
 * 作者：水东流 编于 2018/12/11
 */
public class ShareMyListener implements UMShareListener {
    Context context;

    public ShareMyListener(Context context) {
        this.context = context;
    }

    @Override
    public void onStart(SHARE_MEDIA share_media) {

    }

    @Override
    public void onResult(SHARE_MEDIA share_media) {
        EventBus.getDefault().post(new CurrencyEvent("分享成功", CurrencyEvent.SHAREOK));
        Toast.makeText(context, " 分享成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(SHARE_MEDIA share_media, Throwable throwable) {
        EventBus.getDefault().post(new CurrencyEvent("分享取消", CurrencyEvent.SHARE_SB));
        Toast.makeText(context, " 分享失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCancel(SHARE_MEDIA share_media) {
        EventBus.getDefault().post(new CurrencyEvent("分享取消", CurrencyEvent.SHARE_QX));
        Toast.makeText(context, " 分享取消", Toast.LENGTH_SHORT).show();
    }
}
