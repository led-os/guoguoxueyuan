package com.test720.grasshoppercollege.sharePage;

import android.Manifest;
import android.app.Activity;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;


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
 * 作者：水东流 编于 2018/10/20
 */
public class ShareUntil {
    private static final ShareUntil ourInstance = new ShareUntil();

    public static ShareUntil getInstance() {
        return ourInstance;
    }

    private ShareUntil() {
    }

    Activity mcontext;

    public void share(int type, String shareTitle, String shareBody, Activity activity) {
        mcontext = activity;
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(mcontext, mPermissionList, 123);
        }
        UMImage image = new UMImage(mcontext, R.mipmap.icon_app);//分享图标
        String url = HttpUntil.GetIntent().getShare() + type;
        final UMWeb web = new UMWeb(url); //切记切记 这里分享的链接必须是http开头

        web.setTitle(shareTitle);//标题
        web.setThumb(image);  //缩略图
        web.setDescription(shareBody);//描述
        new ShareAction(mcontext)
                .setDisplayList(SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE)
                .setShareboardclickCallback(new ShareBoardlistener() {
                    @Override
                    public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                        if (share_media == SHARE_MEDIA.QQ) {
                            new ShareAction(mcontext).setPlatform(SHARE_MEDIA.QQ)
                                    .withMedia(web)
                                    .setCallback(new ShareMyListener(mcontext))
                                    .share();
                        } else if (share_media == SHARE_MEDIA.WEIXIN) {

                            new ShareAction(mcontext).setPlatform(SHARE_MEDIA.WEIXIN)
                                    .withMedia(web)
                                    .setCallback(new ShareMyListener(mcontext))
                                    .share();
                        } else if (share_media == SHARE_MEDIA.QZONE) {
                            new ShareAction(mcontext).setPlatform(SHARE_MEDIA.QZONE)
                                    .withMedia(web)
                                    .setCallback(new ShareMyListener(mcontext))
                                    .share();
                        } else if (share_media == SHARE_MEDIA.WEIXIN_CIRCLE) {
                            new ShareAction(mcontext).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
                                    .withMedia(web)
                                    .setCallback(new ShareMyListener(mcontext))
                                    .share();
                        }
                    }
                }).open();
    }

    public void share(String url, String shareTitle, String shareBody, Activity activity) {
        mcontext = activity;
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(mcontext, mPermissionList, 123);
        }
        UMImage image = new UMImage(mcontext, R.mipmap.icon_app);//分享图标
        final UMWeb web = new UMWeb(url); //切记切记 这里分享的链接必须是http开头

        web.setTitle(shareTitle);//标题
        web.setThumb(image);  //缩略图
        web.setDescription(shareBody);//描述
        new ShareAction(mcontext)
                .setDisplayList(SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE)
                .setShareboardclickCallback(new ShareBoardlistener() {
                    @Override
                    public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                        if (share_media == SHARE_MEDIA.QQ) {
                            new ShareAction(mcontext).setPlatform(SHARE_MEDIA.QQ)
                                    .withMedia(web)
                                    .setCallback(new ShareMyListener(mcontext))
                                    .share();
                        } else if (share_media == SHARE_MEDIA.WEIXIN) {

                            new ShareAction(mcontext).setPlatform(SHARE_MEDIA.WEIXIN)
                                    .withMedia(web)
                                    .setCallback(new ShareMyListener(mcontext))
                                    .share();
                        } else if (share_media == SHARE_MEDIA.QZONE) {
                            new ShareAction(mcontext).setPlatform(SHARE_MEDIA.QZONE)
                                    .withMedia(web)
                                    .setCallback(new ShareMyListener(mcontext))
                                    .share();
                        } else if (share_media == SHARE_MEDIA.WEIXIN_CIRCLE) {
                            new ShareAction(mcontext).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
                                    .withMedia(web)
                                    .setCallback(new ShareMyListener(mcontext))
                                    .share();
                        }
                    }
                }).open();
    }

    //分享


}
