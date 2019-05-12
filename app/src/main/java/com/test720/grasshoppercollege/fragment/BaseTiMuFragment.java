package com.test720.grasshoppercollege.fragment;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.BaseFragment;
import com.test720.grasshoppercollege.BaseTiMuActivity;
import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.myViews.GetGuoGuoDouDialog;
import com.test720.grasshoppercollege.sharePage.ShareMyListener;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.musicMedia.MusicUntil;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;


/**
 * Created by 水东流 on 2018/6/19.
 */

public abstract class BaseTiMuFragment extends BaseFragment {
    ViewPager viewPager;
    public boolean isAnswer = false;//是否已经作答
    public boolean answerIsRight = true;//默认答题是否正确
    BaseTiMuActivity baseTiMuActivity;
    MusicUntil jifenawr = new MusicUntil();

    public BaseTiMuActivity getBaseTiMuActivity() {
        return baseTiMuActivity;
    }

    public void setBaseTiMuActivity(BaseTiMuActivity baseTiMuActivity) {
        this.baseTiMuActivity = baseTiMuActivity;
    }

    public boolean isAnswer() {
        return isAnswer;
    }

    public void setAnswer(boolean answer) {
        isAnswer = answer;
    }

    public boolean isAnswerIsRight() {
        return answerIsRight;
    }

    public void setAnswerIsRight(boolean answerIsRight) {
        this.answerIsRight = answerIsRight;
    }

    public ViewPager getViewPager() {
        return viewPager;
    }

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    public abstract LinearLayout answerLin();

    /*错题上传参数*/
    public abstract HttpParams CuoTiHttpParams();

    public void DingZheng() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("corr_id", corrId());
        post(HttpUntil.GetIntent().correction(), httpParams, 889, false);
    }

    /*获取订正 id*/
    public abstract String corrId();//订正过后返给后台的id

    /*题目 id*/
    public abstract String questionId();//订正过后返给后台的id

    /*获取订正 状态*/
    public abstract String status();//1表示是订正过的题目

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (corrId() != null && !corrId().equals("0") && isAnswerIsRight() && isAnswer()) {
            DingZheng();
        }
        //添加错题集
        if (CuoTiHttpParams() != null && !isAnswerIsRight() && isAnswer()) {
            CuoTiHttpParams().put("class", MyApplication.getmInstance().nianji);
            post(HttpUntil.GetIntent().errorQuestion(), CuoTiHttpParams(), 888, false);
        }
        jifenawr.canlce();
    }

    @Override
    public void onStart() {
        super.onStart();
        /*已经订正过的题目，直接显示解析和答案*/
        if (status() != null && status().equals("1")) {
            setAnswer(true);//已经订正的，直接显示答案
            if (answerLin() != null) answerLin().setVisibility(View.VISIBLE);
        } else {
            if (answerLin() != null) answerLin().setVisibility(View.GONE);
        }
//订正题默认错误，正常
        if (corrId() != null && !corrId().equals("0")) {
            setAnswerIsRight(false);
        } else {
            setAnswerIsRight(true);
        }
    }

    /*恭喜弹框*/
    public void showGongXiPop(String points, String share_points, final String title, final String body, final int type) {
        if (getBaseTiMuActivity().getFen() >= 95) {
            jifenawr.playRaw(getContext(), R.raw.excellent);
        } else if (getBaseTiMuActivity().isDingZheng() && getBaseTiMuActivity().getType() == 22) {//口算积累本播放95以上声音
            jifenawr.playRaw(getContext(), R.raw.excellent);
        } else {
            jifenawr.playRaw(getContext(), R.raw.notexcellent);
        }
        GetGuoGuoDouDialog dialog = new GetGuoGuoDouDialog();
        dialog.setPoints(points);
        if (getBaseTiMuActivity().isDingZheng() && getBaseTiMuActivity().getType() == 22) {//口算积累本显示特定提示
            dialog.setFenStr("恭喜你答对了" + (int) getBaseTiMuActivity().getFen()
                    + "道题,\n（总共：" + getViewPager().getAdapter().getCount() + "道题）。");
        } else {
            dialog.setFenStr("获得了" + getBaseTiMuActivity().getFen() + "分");
        }

        dialog.setShare_points(share_points);
        dialog.setShareTitle(title);
        dialog.setShareBody(body);
        dialog.setShareType(type);
        dialog.setShareClick(new GetGuoGuoDouDialog.Share() {
            @Override
            public void share() {
                UMshare(title, body, type);
            }

            @Override
            public void back() {
                getBaseTiMuActivity().finish();
            }
        });
        dialog.show(getChildFragmentManager(), "share");
    }


    /*蝈蝈豆提示*/


    /**
     * 做完题目增加果果豆
     */
    public abstract void AddGuoGuoDou();

    /**
     * 分享添加果果豆
     */
    public abstract String SharePoint();

    /**
     * 分享加果果豆
     */
    public abstract void ShareAddGuoGuo();

    @Override
    public void onEvent(CurrencyEvent event) {
        Log.i("lxl", "event" + event.getWhat());
        if (event.getWhat() == CurrencyEvent.SHAREOK) {
            if (event.getMsg().equals("分享成功")) {
                ShareAddGuoGuo();
            }
        }
//分享失败或者取消
        if (event.getWhat() == CurrencyEvent.SHARE_QX || event.getWhat() == CurrencyEvent.SHARE_SB) {
            getActivity().finish();
        }
    }

    /******************************分享*****************/
    public void UMshare(String shareTitle, String shareBody, int type) {
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(getActivity(), mPermissionList, 123);
        }
        UMImage image = new UMImage(mContext, R.mipmap.icon_app);//分享图标
        String url = HttpUntil.GetIntent().getShare() + type;
        final UMWeb web = new UMWeb(url); //切记切记 这里分享的链接必须是http开头

        web.setTitle(shareTitle);//标题
        web.setThumb(image);  //缩略图
        web.setDescription(shareBody);//描述
        new ShareAction(getActivity())
                .setDisplayList(SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE)
                .setShareboardclickCallback(new ShareBoardlistener() {
                    @Override
                    public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                        if (share_media == SHARE_MEDIA.QQ) {
                            new ShareAction(getActivity()).setPlatform(SHARE_MEDIA.QQ)
                                    .withMedia(web)
                                    .setCallback(new ShareMyListener(getActivity()))
                                    .share();
                        } else if (share_media == SHARE_MEDIA.WEIXIN) {

                            new ShareAction(getActivity()).setPlatform(SHARE_MEDIA.WEIXIN)
                                    .withMedia(web)
                                    .setCallback(new ShareMyListener(getActivity()))
                                    .share();
                        } else if (share_media == SHARE_MEDIA.QZONE) {
                            new ShareAction(getActivity()).setPlatform(SHARE_MEDIA.QZONE)
                                    .withMedia(web)
                                    .setCallback(new ShareMyListener(getActivity()))
                                    .share();
                        } else if (share_media == SHARE_MEDIA.WEIXIN_CIRCLE) {
                            new ShareAction(getActivity()).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
                                    .withMedia(web)
                                    .setCallback(new ShareMyListener(getActivity()))
                                    .share();
                        }
                    }
                }).open();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(getActivity()).onActivityResult(requestCode, resultCode, data);
    }


}
