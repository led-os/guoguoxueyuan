package com.test720.grasshoppercollege.module.qianDao;

import android.content.Context;
import android.content.Intent;

import com.test720.grasshoppercollege.MainActivity;
import com.test720.grasshoppercollege.module.baiBianTingLi.BaiBianShouYeActivity;
import com.test720.grasshoppercollege.module.ciDai.CiDaiShouYeActivity;
import com.test720.grasshoppercollege.module.danCiTingXie.DanCiTingXieShouYeActivity;
import com.test720.grasshoppercollege.module.diandu.DianDuShouYeActivity;
import com.test720.grasshoppercollege.module.englishYueDu.YueDuShouActivity;
import com.test720.grasshoppercollege.module.fanYiChuangGuan.FanYiShouYeActivity;
import com.test720.grasshoppercollege.module.guShiCi.GuShiShouYeActivity;
import com.test720.grasshoppercollege.module.guoguoDou.GuoGuoDouShouActivity;
import com.test720.grasshoppercollege.module.jiangjie.JiangJieShouYeActivity;
import com.test720.grasshoppercollege.module.jinFanYiCi.JinFanYiShouActivity;
import com.test720.grasshoppercollege.module.kouYuShiJian.KouYuShouYeActivity;
import com.test720.grasshoppercollege.module.meiRiYiJu.MeiRiShouYeActivity;
import com.test720.grasshoppercollege.module.peiYin.PeiYinShouActivity;
import com.test720.grasshoppercollege.module.shangCheng.ShangChengActivity;
import com.test720.grasshoppercollege.module.shuXue.aoShu.AoShuShouYeActivity;
import com.test720.grasshoppercollege.module.shuXue.aoShuLianXi.AoShuLIanXiShouActivity;
import com.test720.grasshoppercollege.module.shuXue.dongHuaShuXue.DongHuaShuXueShouYeActivity;
import com.test720.grasshoppercollege.module.shuXue.jiChu.JiChuShouActivity;
import com.test720.grasshoppercollege.module.shuXue.kouSuan.KouSuanShouActivity;
import com.test720.grasshoppercollege.module.shuXue.yingYong.YingYongShouActivity;
import com.test720.grasshoppercollege.module.tingLiTest.TingLiShouYeActivity;
import com.test720.grasshoppercollege.module.userData.PaiHangBangActivity;
import com.test720.grasshoppercollege.module.userData.PhoneCodeActivity;
import com.test720.grasshoppercollege.module.userData.geRenZiLiao.HeaderActivity;
import com.test720.grasshoppercollege.module.userData.geRenZiLiao.PersonActivity;
import com.test720.grasshoppercollege.module.userData.xiaoXi.XiaoXiActivity;
import com.test720.grasshoppercollege.module.woAidanCiTingXie.WoAiTingXieShouYeActivity;
import com.test720.grasshoppercollege.module.xieHouYu.XieHouYuShouActivity;
import com.test720.grasshoppercollege.module.yinBiao.YinBiaoShouYeActivity;
import com.test720.grasshoppercollege.module.yinMeiWenHua.YinMeiShouYeActivity;
import com.test720.grasshoppercollege.module.youEr.baoBao.BaoBaoShouActivity;
import com.test720.grasshoppercollege.module.youEr.baoBao.YouErBaoBaoShouActivity;
import com.test720.grasshoppercollege.module.youEr.baoBaoTingTing.BaoBaoTingTingShouActivity;
import com.test720.grasshoppercollege.module.youEr.erGeShiJie.ErGeShiJieShouActivity;
import com.test720.grasshoppercollege.module.youEr.kouSuanDaWang.KouSuanDaWangShouActivity;
import com.test720.grasshoppercollege.module.youEr.shiZiDongHua.ShiZiShouActivity;
import com.test720.grasshoppercollege.module.youEr.yingWenHuiBen.HuiBenShouActivity;
import com.test720.grasshoppercollege.module.youEr.youXi.YouXiShouYeActivity;
import com.test720.grasshoppercollege.module.yuFaJiangJie.YuFaShouYeActivity;
import com.test720.grasshoppercollege.module.yuFaTest.shouYe.YuFaFirstActivity;
import com.test720.grasshoppercollege.module.yuWen.chengYu.ChengYuShouActivity;
import com.test720.grasshoppercollege.module.yuWen.tingXieZhuShou.TingLiShouActivity;
import com.test720.grasshoppercollege.module.yuWen.yuWenJiChu.YuWenJIChuShouActivity;
import com.test720.grasshoppercollege.module.yuWen.yuYanPinYinShiZi.pinYin.PingYinShouActivity;
import com.test720.grasshoppercollege.module.yuWen.yuYanPinYinShiZi.shiZi.ShiZiShouYeActivity;
import com.test720.grasshoppercollege.module.yuWen.yuYanPinYinShiZi.yuYan.YuYanShouYeActivity;
import com.test720.grasshoppercollege.module.yuWen.yueDu.YuWenYueDuShouActivity;

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
 * 作者：水东流 编于 2018/10/19
 */
public class TaskUntil {

    private static final TaskUntil ourInstance = new TaskUntil();

    public static TaskUntil getInstance() {
        return ourInstance;
    }

    private TaskUntil() {
    }

    public void jump(Context context, String type) {
        Intent intent = null;
        switch (type) {
            case "22G01"://口算训练-22G01
                intent = new Intent(context, KouSuanShouActivity.class);
                break;
            case "17G01"://应用练习-17G01
                intent = new Intent(context, YingYongShouActivity.class);
                break;
            case "19G01"://基础练习-19G01
                intent = new Intent(context, JiChuShouActivity.class);
                break;
            case "02G01"://数学动画-02G01
                intent = new Intent(context, DongHuaShuXueShouYeActivity.class);
                break;
            case "01G01":// 奥数动画-01G01
                intent = new Intent(context, AoShuShouYeActivity.class);
                break;
            case "18G01":// 奥数练习-18G01
                intent = new Intent(context, AoShuLIanXiShouActivity.class);
                break;
            case "20G01":// 基础练习-20G01
                intent = new Intent(context, YuWenJIChuShouActivity.class);
                break;
            case "16G01":// 听写助手-16G01
                intent = new Intent(context, TingLiShouActivity.class);
                break;
            case "15G01":// 阅读训练-15G01
                intent = new Intent(context, YuWenYueDuShouActivity.class);
                break;
            case "34G01":// 古诗三百-34G01
                intent = new Intent(context, GuShiShouYeActivity.class);
                break;
            case "25G01":// 近反义词-25G01
                intent = new Intent(context, JinFanYiShouActivity.class);
                break;
            case "26G01":// 快乐歇后-26G01
                intent = new Intent(context, XieHouYuShouActivity.class);
                break;
            case "03G01":// 寓言故事-03G01
                intent = new Intent(context, YuYanShouYeActivity.class);
                break;
            case "30G01"://  成语接龙-30G01
                intent = new Intent(context, ChengYuShouActivity.class);
                break;
            case "05G01":// 拼音动画-05G01
                intent = new Intent(context, PingYinShouActivity.class);
                break;
            case "04G01":// 汉字动画-04G01
                intent = new Intent(context, ShiZiShouYeActivity.class);
                break;
            case "65G01":// 课本点读-65G01
                intent = new Intent(context, DianDuShouYeActivity.class);
                break;
            case "11G01"://  课文讲解-11G01
                intent = new Intent(context, JiangJieShouYeActivity.class);
                break;
            case "23G01"://  单词听写-23G01
                intent = new Intent(context, DanCiTingXieShouYeActivity.class);
                break;
            case "12G01"://  翻译闯关-12G01
                intent = new Intent(context, FanYiShouYeActivity.class);
                break;
            case "14G01"://  百辩听力-14G01
                intent = new Intent(context, BaiBianShouYeActivity.class);
                break;
            case "66G01"://  磁带播放-66G01
                intent = new Intent(context, CiDaiShouYeActivity.class);
                break;
            case "10G01"://  国际音标-10G01
                intent = new Intent(context, YinBiaoShouYeActivity.class);
                break;
            case "21G01"://   单词辨音-21G01
                intent = new Intent(context, TingLiShouYeActivity.class);
                break;
            case "06G01"://   语法讲解-06G01
                intent = new Intent(context, YuFaShouYeActivity.class);
                break;
            case "24G01"://    语法练习-24G01
                intent = new Intent(context, YuFaFirstActivity.class);
                break;
            case "13G01"://  阅读训练-13G01
                intent = new Intent(context, YueDuShouActivity.class);
                break;
            case "35G01"://  识字动画-35G01
                intent = new Intent(context, ShiZiShouActivity.class);
                break;
            case "47G01"://  汉字宝宝-47G01
                intent = new Intent(context, YouErBaoBaoShouActivity.class);
                intent.putExtra("type", 47);
                break;
            case "36G01"://  拼音动画-36G01
                intent = new Intent(context, com.test720.grasshoppercollege.module.youEr.pinYinDongHua.ShiZiShouActivity.class);
                intent.putExtra("type", 47);
                break;
            case "48G01"://  拼音宝宝-48G01
                intent = new Intent(context, BaoBaoShouActivity.class);
                intent.putExtra("type", 48);
                break;
            case "37G01"://   数学动画-37G01
                intent = new Intent(context, com.test720.grasshoppercollege.module.youEr.shuXueDongHua.ShiZiShouActivity.class);
                break;
            case "50G01"://   口算大王-50G01
                intent = new Intent(context, KouSuanDaWangShouActivity.class);
                intent.putExtra("type", 50);
                break;
            case "38G01"://   英语影院-38G01
                intent = new Intent(context, com.test720.grasshoppercollege.module.youEr.yingYuYinYuan.ShiZiShouActivity.class);
                break;
            case "49G01"://  英语宝宝-49G01
                intent = new Intent(context, BaoBaoShouActivity.class);
                intent.putExtra("type", 49);
                break;
            case "52G01"://  英文绘本-52G01
                intent = new Intent(context, HuiBenShouActivity.class);
                intent.putExtra("type", 52);
                break;
            case "53G01"://  我爱看书-53G01
                intent = new Intent(context, HuiBenShouActivity.class);
                intent.putExtra("type", 53);
                break;
            case "39G01"://   国学经典-39G01
                intent = new Intent(context, com.test720.grasshoppercollege.module.youEr.guoXueJingDian.ShiZiShouActivity.class);
                break;
            case "G01"://   探险任务-G01
                intent = new Intent(context, MainActivity.class);
                intent.putExtra("tanxian", "攻略");
                break;
            case "G02"://   商城任务-G02
                intent = new Intent(context, ShangChengActivity.class);
                break;
            case "G03"://  攻略任务-G03
                intent = new Intent(context, MainActivity.class);
                intent.putExtra("gonglue", "攻略");
                break;
            case "G04"://  分享有礼-G04

                break;
            case "G05":// 果果豆-G05
                intent = new Intent(context, GuoGuoDouShouActivity.class);
                break;
            case "G06"://  排行榜-G06
                intent = new Intent(context, PaiHangBangActivity.class);
                break;
            case "G07"://   任务消息-G07
                intent = new Intent(context, XiaoXiActivity.class);
                break;
            case "G08"://  背包页面的修改昵称-G08
                intent = new Intent(context, PersonActivity.class);
                break;
            case "G09"://  背包页面的换取头像-G09
                intent = new Intent(context, HeaderActivity.class);
                break;
            case "G10"://  背包页面设置支付密码-G10
                intent = new Intent(context, PhoneCodeActivity.class);
                break;
            case "07G01"://英语沙龙
                intent = new Intent(context, YinMeiShouYeActivity.class);
                break;
            case "08G01"://口语时间
                intent = new Intent(context, KouYuShouYeActivity.class);
                break;
            case "67G01"://每日一句
                intent = new Intent(context, MeiRiShouYeActivity.class);
                break;
            case "09G01"://我爱听写
                intent = new Intent(context, WoAiTingXieShouYeActivity.class);
                break;
            case "54G01"://我爱培养
                intent = new Intent(context, PeiYinShouActivity.class);
                break;

            //幼儿模块：幼儿识字动画-35G01 幼儿点读乐园-47G01 幼儿拼音动画-36G01 幼儿数学动画-37G01
            // 幼儿口算大王-50G01 幼儿英语影院-38G01
            // 幼儿我爱绘本-52G01 幼儿国学经典-39G01 幼儿宝宝听听-61G01 幼儿儿歌世界-44G01 幼儿益智游戏-46G01

            case "46G01"://幼儿益智游戏
                intent = new Intent(context, YouXiShouYeActivity.class);
                break;
            case "44G01"://幼儿儿歌世界
                intent = new Intent(context, ErGeShiJieShouActivity.class);
                break;
            case "61G01"://幼儿宝宝听听
                intent = new Intent(context, BaoBaoTingTingShouActivity.class);
                break;

        }

        if (intent != null) context.startActivity(intent);
    }

}
