package com.test720.grasshoppercollege;

import com.androidkun.breakpoints.event.EventMessage;

/**
 * Created by jie on 2016/12/7
 * .
 */

public class CurrencyEvent extends EventMessage {
    public static final int PAYOK = 10000;//支付成功
    public static final int SHAREOK = 10002;//分享成功
    public static final int SHARE_SB = 10003;//分享失败
    public static final int SHARE_QX = 10004;//取消分享


    public static final int UPDATAADRESSONE = 553;//培训地址更新
    public static final int UPDATAADRESSTWO = 554;//幼儿地址更新

    public static final int FINISHADMISSONE = 556;//关闭培训助力界面
    public static final int FINISHADMISSTWO = 557;//关闭培训助力界面

    public static final int MYORDER = 559;//商城付款成功
    public static final int YOUHUIJUAN = 559;//优惠劵弹框销毁
    public static final int ADDIALOG = 560;//通知广告销毁
    public static final int QIANDAODIALOG = 5560;//签到弹框销毁
    public static final int KOUSUANTIME = 561;//口算时间到
    public static final int KOUSUANOVER = 562;//口算时间到
    public static final int YUYUEJINE = 563;//预约金额更新
    public static final int DDCDBF = 1563;//点读点击磁带播放
    public static final int MRYJSC = 2003;//每日一句收藏


    public static final int TANXIANTIMU = 100111;//探险题目完成切换下一题
    public static final int TANXIANTONGGUANOK = 100112;//探险题目通关成功
    public static final int HUIBENMYID = 100114;//绘本录制id
    public static final int HUIBENCLICKIMG = 100115;//绘本img点击
    public static final int BAOBAOPAUSE = 100116;//宝宝听听播放暂停或停止
    public static final int BAOBAOSTART = 100117;//宝宝听听开始播放
    public static final int UPDATEFRAGMENT = 100218;//刷新fragment关卡信息数据
    public static final int TANXIANZUOTIOK = 110010;//探险做题完成
    public static final int CHENGBAOTONGGUAN = 120010;//城堡通关

    public static final int NIANJICHANGE = 130011;//微课堂试听课，年级变化
    public static final int ALLNIANJICHANGE = 130013;//全局年级变化

    public static final int GONGLUEBUGOK = 140011;//攻略模块购买支付成功

    public static final int REGOK = 150011;//注册成功
    public static final int LOGINOK = 150012;//登陆成功
    public static final int FINISHMUSIC = 160001;//关闭宝宝听听音乐

    public static final int JOINUSIMG = 170004;//攻略上传证件成功
    public static final int PINGLUNOK = 180003;//评论成功
    public static final int FABUCHENGGONG = 180004;//绘本发布成功

    public static final int CIDAIPLAYALL = 280004;//磁带整本书播放
    public static final int CIDAIPLAYUNTIL = 280005;//磁带单元播放


    public CurrencyEvent(int type, Object object) {
        super(type, object);
    }

    public CurrencyEvent(Object object, int type) {
        super(type, object);
    }

    public Object getMsg() {
        return getObject();
    }

    public int getWhat() {
        return getType();
    }


}
