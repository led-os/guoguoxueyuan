package com.test720.grasshoppercollege.module.gongLue;

import android.support.annotation.IntDef;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

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
 * 作者：水东流 编于 2018/9/1
 */
public class GongLueData {
    private static final GongLueData ourInstance = new GongLueData();


    public static GongLueData getInstance() {
        return ourInstance;
    }

    private GongLueData() {
    }


    private String ts = "1";//1-教师；2-学员
    public static final String T = "1";
    public static final String S = "2";

    @StringDef({T, S})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Ts {
    }

    public String getTs() {
        return ts;
    }

    public void setTs(@Ts String ts) {
        this.ts = ts;
    }


    private String photoType = "1";//1-校园环境；2-学校相册
    public static final String HJ = "1";
    public static final String XC = "2";

    @StringDef({HJ, XC})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Pt {
    }

    public String getPhotoType() {
        return photoType;
    }

    public void setPhotoType(@Pt String photoType) {
        this.photoType = photoType;
    }


    private String good = "1";//1-骨干 0-其他
    public static final String GG = "1";
    public static final String QT = "0";

    @StringDef({GG, QT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Good {
    }

    public String getGood() {
        return good;
    }

    public void setGood(@Good String good) {
        this.good = good;
    }


    private String tuwen = "1";//1-课程特色 2-学校活动 3-所获荣誉 4-办学成就,5-幼儿园荣誉；6-幼儿园特色；7-活动专区
    public static final String KCTS = "1";
    public static final String XXHD = "2";
    public static final String SHRY = "3";
    public static final String BXCJ = "4";
    public static final String YEYRY = "5";
    public static final String YEYTS = "6";
    public static final String HDZQ = "7";

    @StringDef({KCTS, XXHD, SHRY, BXCJ, YEYRY, YEYTS, HDZQ})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Tuwen {
    }

    public String getTuwen() {
        return tuwen;
    }

    public void setTuwen(@Tuwen String tuwen) {
        this.tuwen = tuwen;
    }


    public static final int SCHOOL = 2;
    public static final int JIGOU = 1;
    private int type = JIGOU;//1 培训机构，2 幼儿园

    @IntDef({SCHOOL, JIGOU})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Msj {
    }

    public int getType() {
        return type;
    }

    public void setType(@Msj int type) {
        this.type = type;
    }


    /**
     * 攻略中模块代号
     * 56 线上   57 线下    58 家长微课堂  59 学生微课堂
     */
    public static final int ONLINE = 56;//线上
    public static final int LINEDOWN = 57;//线下
    public static final int PARENT = 58;//家长微课堂。活动，试听
    public static final int STUDENT = 59;//学生微课堂
    private int modularType;//模块type

    @IntDef({ONLINE, LINEDOWN, PARENT, STUDENT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Duration {
    }

    public int getModularType() {
        return modularType;
    }

    public void setModularType(@Duration int modularType) {
        this.modularType = modularType;
    }


    private String schoolId = "";//机构id

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }


    public static final int TOUTIAOJIAZHANG = 58;//家长头条
    public static final int TOUTIAOTEACHER = 63;//老师头条

    @IntDef({TOUTIAOJIAZHANG, TOUTIAOTEACHER})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Toutiao {
    }

    private int touTiaoType = 58;//机构id

    public int getTouTiaoType() {
        return touTiaoType;
    }

    public void setTouTiaoType(@Toutiao int touTiaoType) {
        this.touTiaoType = touTiaoType;
    }


    public final static String YEONLINE = "1";//1-线上线下金额
    public final static String YETEACHERSMALL = "2";//2-教师微课堂金额
    public final static String YEZIXUNSHIYUYUE = "3";//3-咨询师预约金额
    public final static String YEZIXUNSHIWENDA = "4";// 4-咨询师问答金额
    public final static String YEZIXUNSHISMALL = "5";//5-咨询微课堂转入余额

    @StringDef({YEONLINE, YETEACHERSMALL, YEZIXUNSHIYUYUE, YEZIXUNSHIWENDA, YEZIXUNSHISMALL})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Ye {
    }

    private String yeType;

    public String getYeType() {
        return yeType;
    }

    public void setYeType(@Ye String yeType) {
        this.yeType = yeType;
    }


    /////////招生信息，兴趣班，课程信息区分
    public final static int ZHAOSHENG = 1; //招生
    public final static int XINGQU = 2;//兴趣
    public final static int KECHEN = 3;//课程信息

    @IntDef({ZHAOSHENG, XINGQU, KECHEN})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ZhaoXQ {

    }

    public int zhaoXQType;

    public int getZhaoXQType() {
        return zhaoXQType;
    }

    public void setZhaoXQType(@ZhaoXQ int zhaoXQType) {
        this.zhaoXQType = zhaoXQType;
    }
}
