package com.test720.grasshoppercollege.untils;

import android.Manifest;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.myViews.GameIsOverDialog;
import com.test720.grasshoppercollege.myViews.TextDialog;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;


/**
 * Created by 水东流 on 2018/8/2.
 */

public class QuanXianUntil {

    private QuanXianUntil() {
    }

    private static QuanXianUntil quanXianUntil;
    private static FragmentActivity mactivity;
    String[] strs;
    QuanXianClick quanXianClick;

    //接口地址单列对象
    public static QuanXianUntil GetIntent(FragmentActivity activity) {
        mactivity = activity;
        if (quanXianUntil == null) {
            quanXianUntil = new QuanXianUntil();
        }
        return quanXianUntil;
    }

    public static QuanXianUntil getQuanXianUntil() {
        return quanXianUntil;
    }

    /**
     * @param permission 权限
     * @param isPlay     是否购买
     * @param open_up    是否解锁
     * @return
     */
    public boolean isCanSee(String permission, boolean isPlay, String open_up, String points, String[] ss, QuanXianClick listener) {
        strs = ss;
        quanXianClick = listener;
        /*已经解锁的关卡，直接执行*/
        if (open_up.equals("1")) {
            return true;
        }
        /*已经购买进入通关模式*/
        if (isPlay) {
            TextDialog textDialog = new TextDialog();
            textDialog.setTextStr("请先通过上一关卡！");
            textDialog.show(mactivity.getSupportFragmentManager().beginTransaction(), "dialog");
            return false;
        }
        /*游客模式全部可见*/
        if (permission.equals("1")) {
            return true;
        }
        /*非游客模式，先判断登录*/
        if (!permission.equals("1")) {
            MyApplication.getmInstance().setLoginDialogIsShow(false);
            if (MyApplication.getmInstance().getUid() == null || MyApplication.getmInstance().getUid().equals("")) {
                Toast.makeText(mactivity, "此课程须登录后查看，请先登录", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        /*需要购买的课程*/
        if (permission.equals("3")) {
            TextDialog textDialog = new TextDialog();
            textDialog.setTextStr("此课程须购买，请先购买或升级vip！");
            textDialog.setOkClick(new TextDialog.OkClick() {
                @Override
                public void click() {
                    quanXianClick.QuanbuyCurr();
                }

                @Override
                public void no() {
                }
            });
            textDialog.show(mactivity.getSupportFragmentManager(), "dialog");
            return false;//能执行到此处，说明需要购买，并且未购买
        }
        //须判断是否需要分享后可见
        if (permission.equals("4")) {
            TextDialog textDialog = new TextDialog();
            textDialog.setTextStr("此课程须分享后可观看，是否分享！");
            textDialog.setOkClick(new TextDialog.OkClick() {
                @Override
                public void click() {
                    share();
                }

                @Override
                public void no() {
                }
            });
            textDialog.show(mactivity.getSupportFragmentManager(), "share");
            return false;
        }
        /*须果果豆购买*/
        if (permission.equals("5")) {
            TextDialog textDialog = new TextDialog();
            textDialog.setTextStr("此课程须花费" + points + "果果豆，是否消费果果豆！");
            textDialog.setOkClick(new TextDialog.OkClick() {
                @Override
                public void click() {
                    quanXianClick.QuanbuyGuoGuo();
                }

                @Override
                public void no() {
                }
            });
            textDialog.show(mactivity.getSupportFragmentManager(), "doudou");
            return false;
        }
        return true;//跳过所有权限判断，则说明可见
    }

    /**
     * 游戏权限
     *
     * @param open
     * @param time
     * @param ss
     * @param quanXianClick
     * @return
     */
    public boolean isCanSee(String open, String time, String[] ss, QuanXianClick quanXianClick) {
        this.quanXianClick = quanXianClick;
        strs = ss;

        if (time.equals("0")) {
            GameIsOverDialog gameIsOverDialog = new GameIsOverDialog();
            gameIsOverDialog.setClosein(new GameIsOverDialog.Close() {
                @Override
                public void close() {
                    mactivity.finish();
                }
            });
            gameIsOverDialog.show(mactivity.getSupportFragmentManager(), "game");
            return false;
        }

        if (open.equals("1")) return true;

        /*需要登录*/
        MyApplication.getmInstance().setLoginDialogIsShow(false);
        if (MyApplication.getmInstance().getUid() == null || MyApplication.getmInstance().getUid().equals("")) {
            Toast.makeText(mactivity, "请先登录", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (open.equals("0")) {
            TextDialog textDialog = new TextDialog();
            textDialog.setTextStr("此游戏须分享后使用");
            textDialog.setOkClick(new TextDialog.OkClick() {
                @Override
                public void click() {
                    share();
                }

                @Override
                public void no() {

                }
            });
            textDialog.show(mactivity.getSupportFragmentManager(), "game");
            return false;
        }
        return true;
    }


    public interface QuanXianClick {
        /*购买课程*/
        void QuanbuyCurr();

        /*购买蝈蝈豆*/
        void QuanbuyGuoGuo();

        /*分享统计*/
        void QuanshareCurr();
    }

    public void share() {
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(mactivity, mPermissionList, 123);
        }
        UMImage image = new UMImage(mactivity, R.mipmap.icon_app);//分享图标
        String url = strs[2];
        final UMWeb web = new UMWeb(url); //切记切记 这里分享的链接必须是http开头

        web.setTitle(strs[0]);//标题
        web.setThumb(image);  //缩略图
        web.setDescription(strs[1]);//描述
        new ShareAction(mactivity)
                .setDisplayList(SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE)
                .setShareboardclickCallback(new ShareBoardlistener() {
                    @Override
                    public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                        if (share_media == SHARE_MEDIA.QQ) {
                            new ShareAction(mactivity).setPlatform(SHARE_MEDIA.QQ)
                                    .withMedia(web)
                                    .setCallback(umShareListener)
                                    .share();
                        } else if (share_media == SHARE_MEDIA.WEIXIN) {

                            new ShareAction(mactivity).setPlatform(SHARE_MEDIA.WEIXIN)
                                    .withMedia(web)
                                    .setCallback(umShareListener)
                                    .share();
                        } else if (share_media == SHARE_MEDIA.QZONE) {
                            new ShareAction(mactivity).setPlatform(SHARE_MEDIA.QZONE)
                                    .withMedia(web)
                                    .setCallback(umShareListener)
                                    .share();
                        } else if (share_media == SHARE_MEDIA.WEIXIN_CIRCLE) {
                            new ShareAction(mactivity).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
                                    .withMedia(web)
                                    .setCallback(umShareListener)
                                    .share();
                        }
                    }
                }).open();
    }

    //分享
    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            quanXianClick.QuanshareCurr();
            Toast.makeText(mactivity, platform + " 分享成功", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(mactivity, platform + " 分享失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(mactivity, platform + " 分享取消", Toast.LENGTH_SHORT).show();
        }
    };

    public QuanXianClick getQuanXianClick() {
        return quanXianClick;
    }

}
