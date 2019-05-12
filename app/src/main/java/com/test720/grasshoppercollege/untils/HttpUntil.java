package com.test720.grasshoppercollege.untils;

import android.content.Context;

import com.test720.grasshoppercollege.MyApplication;

/**
 * _oo0oo_
 * o8888888o
 * 88" . "88
 * (| -_- |)
 * 0\  =  /0
 * ___/`---'\___
 * .' \\|     |// '.
 * / \\|||  :  |||// \
 * / _||||| -卍-|||||- \
 * |   | \\\  -  /// |   |
 * | \_|  ''\---/''  |_/ |
 * \  .-\__  '-'  ___/-. /
 * ___'. .'  /--.--\  `. .'___
 * ."" '<  `.___\_<|>_/___.' >' "".
 * | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 * \  \ `_.   \_ __\ /__ _/   .-` /  /
 * =====`-.____`.___ \_____/___.-`___.-'=====
 * `=---='
 * 佛祖保佑        永无BUG
 * 佛曰:
 * 程序园里程序天，程序天里程序员；
 * 程序猿人写程序，又拿程序换肉钱。
 * 肉饱继续桌前坐，饱暖还是桌前眠；
 * 半迷半醒日复日，码上码下年复年。
 * 但愿叱咤互联世，不愿搬砖码当前；
 * 诸葛周瑜算世事，我来算出得加钱。
 * 别人笑我忒直男，我笑自己太像猿；
 * 但见成都府国内，处处地地程序员。
 * Created by 水东流 on 2018/2/27 0027.
 */

public class HttpUntil {
    private static HttpUntil httpUntil;

    private HttpUntil() {
    }

    //接口地址单列对象
    public static HttpUntil GetIntent() {
        if (httpUntil == null) {
            httpUntil = new HttpUntil();
        }
        return httpUntil;
    }

    //服务器地址
    private String IP = "https://www.hzggedu.com/ggxy/";
//    private String IP = "https://www.hzggedu.com/ggxytest/";
//    private String IP = "http://www.guoguoxueyuan.com/ggxy/";
//    private String IP = "http://www.guoguoxueyuan.com/ggxydemo/";


    //设置服务器地址
    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getIP() {
        return IP;
    }

    //本地文件保存地址路径
    public String filePathHead(Context context) {
        return context.getApplicationContext().getFilesDir() + "/downloads/";
    }

    /*更新*/
    public String checkUrl() {
        return getIP() + "small.php/IndexUser/edition";
    }

    /*年级列表*/
    public String getClassListUrl() {
        return getIP() + "index/classList";
    }


    /*注册*/
    public String getegisterUrl() {
        return getIP() + "small.php/Login/register";
    }

    /*获取验证码*/
    public String getMobileCode() {
        return getIP() + "small.php/Login/getMobileCode";
    }

    /*登陆*/
    public String getlogin() {
        return getIP() + "small.php/Login/login";
    }

    /*忘记密码*/
    public String getforgetPwd() {
        return getIP() + "small.php/Login/forgetPwd";
    }

    /*选择地址*/
    public String setArea() {
        return getIP() + "small.php/Login/setArea";
    }

    /*广告*/
    public String getAds() {
        return getIP() + "small.php/IndexUser/ads";
    }

    /*首页*/
    public String IndexUserindex() {
        return getIP() + "small.php/IndexUser/index";
    }

    /*消息列表*/
    public String msgList() {
        return getIP() + "small.php/IndexUser/msgList";
    }

    /***********************课文讲解模块*/
    /*课文讲解--首页*/
    public String getIndex() {
        return getIP() + "small.php/EnExplain/myBookList";
    }

    /*课本讲解，删除课本*/
    public String delBook() {
        return getIP() + "small.php/EnExplain/delBook";
    }

    /*课本讲解，课本列表*/
    public String EnExplainbookList() {
        return getIP() + "small.php/EnExplain/bookList";
    }

    /*课本讲解，添加课本*/
    public String EnExplainaddBook() {
        return getIP() + "small.php/EnExplain/addBook";
    }

    /*课本讲解，单元列表*/
    public String EnExplainunitList() {
        return getIP() + "small.php/EnExplain/unitList";
    }

    /*课本讲解，内容*/
    public String EnExplainbookContent() {
        return getIP() + "small.php/EnExplain/bookContent";
    }

    /*课本讲解，评论详情*/
    public String EnExplaincommentInfo() {
        return getIP() + "small.php/EnExplain/commentInfo";
    }

    /*课文讲解。。。点赞*/
    public String EnExplainthumbUp() {
        return getIP() + "small.php/EnExplain/thumbUp";
    }

    /*课文讲解。。。评价*/
    public String EnExplaincomment() {
        return getIP() + "small.php/EnExplain/comment";
    }

    /*************个人中心******/
    /*个人中心*/
    public String info() {
        return getIP() + "small.php/User/info";
    }

    /*设置支付密码*/
    public String setPayPassword() {
        return getIP() + "small.php/User/setPayPassword";
    }

    /*修改支付密码*/
    public String savePayPassword() {
        return getIP() + "small.php/User/savePayPassword";
    }

    /*修改手机号，验证旧手机*/
    public String checkOldPhone() {
        return getIP() + "small.php/User/checkOldPhone";
    }

    /*修改手机号*/
    public String editPhone() {
        return getIP() + "small.php/User/editPhone";
    }

    /*修改登陆密码*/
    public String editLoginPassword() {
        return getIP() + "small.php/User/editLoginPassword";
    }

    /*意见反馈*/
    public String Opinion() {
        return getIP() + "small.php/User/Opinion";
    }

    /*地址列表*/
    public String addressList() {
        return getIP() + "small.php/User/address";
    }

    /*设置默认地址*/
    public String setDefaultAddress() {
        return getIP() + "small.php/User/setDefaultAddress";
    }

    /*新加，修改地址*/
    public String saveAddress() {
        return getIP() + "small.php/User/saveAddress";
    }

    /*删除地址*/
    public String deleteAddress() {
        return getIP() + "small.php/User/deleteAddress";
    }

    /*推荐二维码*/
    public String getQrCode() {
        return getIP() + "small.php/User/getQrCode";
    }

    /*我的团队*/
    public String UserfenXiaoCenter() {
        return getIP() + "small.php/User/fenXiaoCenter";
    }

    /*代理首页*/
    public String UseragentIndex() {
        return getIP() + "small.php/User/agentIndex";
    }

    /*代理佣金查询*/
    public String UseragentRecord() {
        return getIP() + "small.php/User/agentRecord";
    }

    /*申请成为代理Ad页*/
    public String UserapplyAgentIndex() {
        return getIP() + "small.php/User/applyAgentIndex";
    }

    /*申请成为代理*/
    public String UserapplyAgent() {
        return getIP() + "small.php/User/applyAgent";
    }

    /*团队佣金*/
    public String teamCommissionRecord() {
        return getIP() + "small.php/User/teamCommissionRecord";
    }

    /*团队转出明细*/
    public String commissionOutRecord() {
        return getIP() + "small.php/User/commissionOutRecord";
    }

    /*代理金额转入余额*/
    public String UseragentIntoBalance() {
        return getIP() + "small.php/User/agentIntoBalance";
    }

    /*我的团队*/
    public String team() {
        return getIP() + "small.php/User/viewTeam";
    }

    /*查看佣金明细*/
    public String viewUsrCommission() {
        return getIP() + "small.php/user/viewUsrCommission";
    }

    /*充值*/
    public String recharge() {
        return getIP() + "small.php/User/recharge";
    }

    /*订单信息*/
    public String OrderorderStatus() {
        return getIP() + "small.php/Order/orderStatus";
    }

    /*评价*/
    public String ordergoodsComment() {
        return getIP() + "small.php/order/goodsComment";
    }

    /*订单详情*/
    public String orderorderInfo() {
        return getIP() + "small.php/order/orderInfo";
    }

    /*我的优惠劵*/
    public String UsermyCoupon() {
        return getIP() + "small.php/User/myCoupon";
    }

    //我的果果豆
    public String myPoints() {
        return getIP() + "small.php/User/myPoints";
    }


    /************个人资料****************/
    /*头像获取*/
    public String clickHeader() {
        return getIP() + "small.php/User/clickHeader";
    }

    /*设置头像*/
    public String setMyHeader() {
        return getIP() + "small.php/User/setMyHeader";
    }

    /*兑换头像*/
    public String exchangeHeader() {
        return getIP() + "small.php/User/exchangeHeader";
    }

    /*修改个人资料*/
    public String editInfo() {
        return getIP() + "small.php/User/editInfo";
    }

    /*排行绑*/
    public String rankingList() {
        return getIP() + "small.php/User/rankingList";
    }

    /*********财务相关***********/
    /*明细*/
    public String myFinance() {
        return getIP() + "small.php/User/myFinance";
    }

    /*提现*/
    public String draw() {
        return getIP() + "small.php/User/draw";
    }

    /*提现方式列表*/
    public String drawType() {
        return getIP() + "small.php/User/drawType";
    }

    /*解除绑定*/
    public String relieveBind() {
        return getIP() + "small.php/User/relieveBind";
    }

    /*添加提现方式*/
    public String addDrawType() {
        return getIP() + "small.php/User/addDrawType";
    }

    /*获取在线交易号*/
    public String getOutTradeNo() {
        return getIP() + "small.php/Pay/getOutTradeNo";
    }

    /*微信支付签名*/
    public String weiXinPay() {
        return getIP() + "small.php/Pay/weiXinPay";
    }

    /*支付宝回掉地址*/
    public String getZFBcalllBack() {
        return getIP() + "small.php/Pay/zfbNotify";
    }

    /*购买课程*/
    public String payCurr() {
        return getIP() + "small.php/Common/payCurr";
    }

    /*余额购买课程*/
    public String balancePayCurr() {
        return getIP() + "small.php/Pay/balancePayCurr";
    }


    /*******************点读**********************/
    /*点读*/
    public String myBookList() {
        return getIP() + "small.php/Reading/myBookList";
    }

    /*点读删除课本*/
    public String IndexdelBook() {
        return getIP() + "small.php/Reading/delBook";
    }


    /*电读课本列表*/
    public String ReadingbookList() {
        return getIP() + "small.php/Reading/bookList";
    }

    /*电读添加课本列表*/
    public String ReadingaddBook() {
        return getIP() + "small.php/Reading/addBook";
    }

    /*点读课本内容*/
    public String ReadingbookContent() {
        return getIP() + "small.php/Reading/bookContentAndroid";
    }

    /*点读目录*/
    public String bookContent1() {
        return getIP() + "small.php/Reading/bookContent1";
    }

    /*单条数据*/
    public String getPageContent() {
        return getIP() + "small.php/Reading/getPageContent";
    }


    /*******VIP相关***********************/
    /*vip信息*/
    public String vipInfo() {
        return getIP() + "small.php/User/vipInfo";
    }

    /*余额购买vip*/
    public String balancePayVip() {
        return getIP() + "small.php/Pay/balancePayVip";
    }


    /*************数学魔窟啊***********/
    /*首页*/
    public String MathAnimationindex() {
        return getIP() + "small.php/MathAnimation/index";
    }

    /*奥数内容*/
    public String MathAnimationcontent() {
        return getIP() + "small.php/MathAnimation/content";
    }

    /*点赞*/
    public String MathAnimationthumbUp() {
        return getIP() + "small.php/MathAnimation/thumbUp";
    }

    /*评论*/
    public String MathAnimationcomment() {

        return getIP() + "small.php/MathAnimation/comment";
    }

    /*评论详情*/
    public String MathAnimationcommentInfo() {
        return getIP() + "small.php/MathAnimation/commentInfo";
    }

    // /*************口算**************/
    /*首页*/
    public String MathMentalArithmeticindex() {
        return getIP() + "small.php/MathMentalArithmetic/index";
    }

    /*内容*/
    public String MathMentalArithmeticcontent() {
        return getIP() + "small.php/MathMentalArithmetic/content";
    }

    //*************基础练习*********/
    /*课本列表*/
    public String MathBasicPracticebookList() {
        return getIP() + "small.php/MathBasicPractice/bookList";
    }

    /*我的*/
    public String MathBasicPracticemyBookList() {
        return getIP() + "small.php/MathBasicPractice/myBookList";
    }

    /*删除*/
    public String MathBasicPracticedelBook() {
        return getIP() + "small.php/MathBasicPractice/delBook";
    }

    /*添加*/
    public String MathBasicPracticeaddBook() {
        return getIP() + "small.php/MathBasicPractice/addBook";
    }

    /*单元列表*/
    public String MathBasicPracticeunitList() {
        return getIP() + "small.php/MathBasicPractice/unitList";
    }

    /*内容*/
    public String MathBasicPracticebookContent() {
        return getIP() + "small.php/MathBasicPractice/bookContent";
    }


    //**********点读**************/

    /*首页*/
    public String ChineseReadingmyBookList() {
        return getIP() + "small.php/ChineseReading/myBookList";
    }

    /*删除*/
    public String ChineseReadingdelBook() {
        return getIP() + "small.php/ChineseReading/delBook";
    }

    /*添加*/
    public String ChineseReadingaddBook() {
        return getIP() + "small.php/ChineseReading/addBook";
    }

    /*内容*/
    public String ChineseReadingbookContent() {
        return getIP() + "small.php/ChineseReading/bookContent1";
    }


    /*课本内容列表*/
    public String ChineseReadingbookList() {
        return getIP() + "small.php/ChineseReading/bookList";
    }

    /***************听力助手************/
    /*首页*/
    public String ChineseListeningmyBookList() {
        return getIP() + "small.php/ChineseListening/myBookList";
    }

    /*课本列表*/
    public String ChineseListeningbookList() {
        return getIP() + "small.php/ChineseListening/bookList";
    }

    /*添加*/
    public String ChineseListeningaddBook() {
        return getIP() + "small.php/ChineseListening/addBook";
    }

    /*删除*/
    public String ChineseListeningdelBook() {
        return getIP() + "small.php/ChineseListening/delBook";
    }

    /*题目*/
    public String ChineseListeningcontent() {
        return getIP() + "small.php/ChineseListening/content";
    }

    /*内容列表*/
    public String contentIndex() {
        return getIP() + "small.php/ChineseListening/contentIndex";
    }

    /*************歇后语*****/
    /*练习-首页*/
    public String ChineseXieHouYuindex() {
        return getIP() + "small.php/ChineseXieHouYu/index";
    }

    /*过关*/
    public String ChineseXieHouYuadopt() {
        return getIP() + "small.php/ChineseXieHouYu/adopt";
    }

    /*过关内容*/
    public String ChineseXieHouYuadoptContent() {
        return getIP() + "small.php/ChineseXieHouYu/adoptContent";
    }

    /*练习内容*/
    public String ChineseXieHouYucontent() {
        return getIP() + "small.php/ChineseXieHouYu/content";
    }

    /*宝典首页*/
    public String ChineseXieHouYudictionaryLib() {
        return getIP() + "small.php/ChineseXieHouYu/dictionaryLib";
    }

    /*宝典类别列表*/
    public String ChineseXieHouYucatetoryList() {
        return getIP() + "small.php/ChineseXieHouYu/catetoryList";
    }

    /*歇后语信息*/
    public String ChineseXieHouYuinfo() {
        return getIP() + "small.php/ChineseXieHouYu/info";
    }

    /***************近反义词*********************/
    /*首页关卡*/
    public String ChineseJFindex() {
        return getIP() + "small.php/ChineseJF/index";
    }

    /*内容*/
    public String ChineseJFcontent() {
        return getIP() + "small.php/ChineseJF/content";
    }

    /*过关*/
    public String ChineseJFadopt() {
        return getIP() + "small.php/ChineseJF/adopt";
    }

    /*宝典*/
    public String ChineseJFdictionaryLib() {
        return getIP() + "small.php/ChineseJF/dictionaryLib";
    }

    /*******************拼音到字*********************/
    /*首页*/
    public String ChinesePinYinWritemyBookList() {
        return getIP() + "small.php/ChinesePinYinWrite/myBookList";
    }

    /*删除*/
    public String ChinesePinYinWritedelBook() {
        return getIP() + "small.php/ChinesePinYinWrite/delBook";
    }

    /*课本列表*/
    public String ChinesePinYinWritebookList() {
        return getIP() + "small.php/ChinesePinYinWrite/bookList";
    }

    /*课本列表*/
    public String ChinesePinYinWriteaddBook() {
        return getIP() + "small.php/ChinesePinYinWrite/addBook";
    }

    /*关卡信息*/
    public String ChinesePinYinWritecheckpoint() {
        return getIP() + "small.php/ChinesePinYinWrite/checkpoint";
    }

    /*内容*/
    public String ChinesePinYinWritecontent() {
        return getIP() + "small.php/ChinesePinYinWrite/content";
    }

    /***************诗词*****/
    /*首页*/
    public String ChineseAncientPoetryindex() {
        return getIP() + "small.php/ChineseAncientPoetry/index";
    }

    /*收藏列表*/
    public String ChineseAncientPoetrycollectionIndex() {
        return getIP() + "small.php/ChineseAncientPoetry/collectionIndex";
    }

    /*诗列表*/
    public String ChineseAncientPoetrycontentIndex() {
        return getIP() + "small.php/ChineseAncientPoetry/contentIndex";
    }

    /*内容*/
    public String ChineseAncientPoetrycontent() {
        return getIP() + "small.php/ChineseAncientPoetry/content";
    }

    /*练习*/
    public String ChineseAncientPoetrypracticeIndex() {
        return getIP() + "small.php/ChineseAncientPoetry/practiceIndex";
    }

    /*收藏*/
    public String ChineseAncientPoetrycollection() {
        return getIP() + "small.php/ChineseAncientPoetry/collection";
    }

    /*完成*/
    public String ChineseAncientPoetrycompleted() {
        return getIP() + "small.php/ChineseAncientPoetry/completed";
    }


    /***********语文阅读*******************8*/

    /*首页关卡*/
    public String ChineseReadindex() {
        return getIP() + "small.php/ChineseRead/index";
    }

    /*阅读内容*/
    public String ChineseReadreadContent() {
        return getIP() + "small.php/ChineseRead/readContent";
    }

    /*做题*/
    public String ChineseReadcontent() {
        return getIP() + "small.php/ChineseRead/content";
    }

    /************拼音版块****************/
    /*首页*/
    public String ChinesePinYinindex() {
        return getIP() + "small.php/ChinesePinYin/index";
    }

    /*内容信息*/
    public String ChinesePinYincontent() {
        return getIP() + "small.php/ChinesePinYin/content";
    }

    /*评论*/
    public String ChinesePinYincomment() {
        return getIP() + "small.php/ChinesePinYin/comment";
    }

    /*点赞*/
    public String ChinesePinYinthumbUp() {
        return getIP() + "small.php/ChinesePinYin/thumbUp";
    }

    /*评论详情*/
    public String ChinesePinYincommentInfo() {
        return getIP() + "small.php/ChinesePinYin/commentInfo";
    }

    /**********成语**************/
    /*看动画学成语 首页*/
    public String ChineseIdiomlookAnimation() {
        return getIP() + "small.php/ChineseIdiom/lookAnimation";
    }

    /*看动画学成语评论详情*/
    public String ChineseIdiomcommentInfo() {
        return getIP() + "small.php/ChineseIdiom/commentInfo";
    }

    /*看动画学成语评论*/
    public String ChineseIdiomcomment() {
        return getIP() + "small.php/ChineseIdiom/comment";
    }

    /*看动画学成语 点赞*/
    public String ChineseIdiomthumbUp() {
        return getIP() + "small.php/ChineseIdiom/thumbUp";
    }

    /*看动画学成语内容*/
    public String ChineseIdiomcontent() {
        return getIP() + "small.php/ChineseIdiom/content";
    }

    /*关卡首页*/
    public String ChineseIdiomlookPic() {
        return getIP() + "small.php/ChineseIdiom/lookPic";
    }

    /*成语内容*/
    public String ChineseIdiomidiomContent() {
        return getIP() + "small.php/ChineseIdiom/idiomContent";
    }

    /*过关*/
    public String ChineseIdiomadopt() {
        return getIP() + "small.php/ChineseIdiom/adopt";
    }

    /*年级*/
    public String ChineseIdiomidiomList() {
        return getIP() + "small.php/ChineseIdiom/idiomList";
    }

    /*类别*/
    public String ChineseIdiomcate() {
        return getIP() + "small.php/ChineseIdiom/cate";
    }

    /*成语接龙*/
    public String ChineseIdiombibleIndex() {
        return getIP() + "small.php/ChineseIdiom/bibleIndex";
    }

    /*成语接龙搜索*/
    public String ChineseIdiombibleSearch() {
        return getIP() + "small.php/ChineseIdiom/bibleSearch";
    }

    /*类别内容*/
    public String ChineseIdiomcateContent() {
        return getIP() + "small.php/ChineseIdiom/cateContent";
    }


    /********口语时间***************/
    /*首页*/
    public String EnOralindex() {
        return getIP() + "small.php/EnOral/index";
    }

    /*内容*/
    public String content() {
        return getIP() + "small.php/EnOral/content";
    }

    /*dian zan*/
    public String thumbUp() {
        return getIP() + "small.php/EnOral/thumbUp";
    }

    /*评论*/
    public String comment() {
        return getIP() + "small.php/EnOral/comment";
    }

    /***********磁带播放**************/
    /*首页*/
    public String EnCdmyBookList() {
        return getIP() + "small.php/EnCd/myBookList";
    }

    /*删除课本*/
    public String EnCddelBook() {
        return getIP() + "small.php/EnCd/delBook";
    }

    /*课本列表*/
    public String EnCdbookList() {
        return getIP() + "small.php/EnCd/bookList";
    }

    /*添加课本*/
    public String EnCdaddReadingPoints() {
        return getIP() + "small.php/EnCd/addBook";
    }

    /*内容*/
    public String bookContent() {
        return getIP() + "small.php/EnCd/bookContent";
    }

    /*************单词辩音*******/
    /*关卡*/
    public String wordBianYinindex() {
        return getIP() + "small.php/wordBianYin/index";
    }

    /*关卡*/
    public String wordBianYincontent() {
        return getIP() + "small.php/wordBianYin/content";
    }

    /*通关*/
    public String wordBianYinadopt() {
        return getIP() + "small.php/wordBianYin/adopt";
    }


    /***********国际音标*******************/
    /*关卡*/
    public String passInfo() {
        return getIP() + "small.php/EnIpa/index";
    }

    /*内容*/
    public String ipaInfo() {
        return getIP() + "small.php/EnIpa/content";
    }

    /*点赞*/
    public String EnIpathumbUp() {
        return getIP() + "small.php/EnIpa/thumbUp";
    }

    /*评论*/
    public String addComment() {
        return getIP() + "small.php/EnIpa/comment";
    }

    /*评论详情*/
    public String commentInfo() {
        return getIP() + "small.php/EnIpa/commentInfo";
    }


    /******************我爱听写**************/
    /*首页*/
    public String EnLoveDictationindex() {
        return getIP() + "small.php/EnLoveDictation/index";
    }

    /*内容*/
    public String EnLoveDictationcontent() {
        return getIP() + "small.php/EnLoveDictation/content";
    }

    /*点赞*/
    public String EnLoveDictationthumbUp() {
        return getIP() + "small.php/EnLoveDictation/thumbUp";
    }

    /*评论*/
    public String EnLoveDictationaddComment() {
        return getIP() + "small.php/EnLoveDictation/addComment";
    }

    /*评论详情*/
    public String EnLoveDictationcommentInfo() {
        return getIP() + "small.php/EnLoveDictation/commentInfo";
    }

    /*********语法练习************/
    /*首页*/
    public String GrammarPracticeindex() {
        return getIP() + "small.php/EnGrammarPractice/index";
    }

    /*首页二级*/
    public String GrammarPracticeindexList() {
        return getIP() + "small.php/EnGrammarPractice/indexList";
    }

    /*内容*/
    public String GrammarPracticecontent() {
        return getIP() + "small.php/EnGrammarPractice/content";
    }


    //******************语法讲解*************/
    /*首页*/
    public String EnGrammarindex() {
        return getIP() + "small.php/EnGrammar/index";
    }

    /*首页*/
    public String EnGrammarindexList() {
        return getIP() + "small.php/EnGrammar/indexList";
    }

    /*内容*/
    public String EnGrammarbookContent() {
        return getIP() + "small.php/EnGrammar/content";
    }

    /*评论*/
    public String EnGrammarcomment() {
        return getIP() + "small.php/EnGrammar/comment";
    }

    /*点赞*/
    public String EnGrammarthumbUp() {
        return getIP() + "small.php/EnGrammar/thumbUp";
    }

    /*评论详情*/
    public String EnGrammarcommentInfo() {
        return getIP() + "small.php/EnGrammar/commentInfo";
    }


    // /************英美文化**************/
    /*首页*/
    public String EnCultureindex() {
        return getIP() + "small.php/EnCulture/index";
    }

    /*内容信息*/
    public String EnCulturecontent() {
        return getIP() + "small.php/EnCulture/content";
    }

    /*评论*/
    public String EnCulturecomment() {
        return getIP() + "small.php/EnCulture/comment";
    }

    /*点赞*/
    public String EnCulturethumbUp() {
        return getIP() + "small.php/EnCulture/thumbUp";
    }

    /*评论详情*/
    public String EnCulturecommentInfo() {
        return getIP() + "small.php/EnCulture/commentInfo";
    }


    //**********************奥数练习************************/
    /*首页*/
    public String MathOlyPracticeindex() {
        return getIP() + "small.php/MathOlyPractice/index";
    }

    /*内容*/
    public String MathOlyPracticecontent() {

        return getIP() + "small.php/MathOlyPractice/content";
    }

    /*添加看视频积分*/
    public String addReadingScore() {
        return getIP() + "small.php/MathAnimation/addReadingScore";
    }

    /*添加分享积分*/
    public String addShareScore() {
        return getIP() + "small.php/MathAnimation/addShareScore";
    }


    ///***************翻译闯关***************/
    /*我的课本*/
    public String WordmyBookList() {
        return getIP() + "small.php/Word/myBookList";
    }

    /*删除课本*/
    public String WorddelBook() {
        return getIP() + "small.php/Word/delBook";
    }

    /*删除课本*/
    public String WordaddBook() {
        return getIP() + "small.php/Word/addBook";
    }

    /*课本列表*/
    public String WordbookList() {
        return getIP() + "small.php/Word/bookList";
    }

    /*关卡列表*/
    public String Wordindex() {
        return getIP() + "small.php/Word/index";
    }

    /*内容*/
    public String Wordcontent() {
        return getIP() + "small.php/Word/content";
    }

    /*****************每日一句***********************/
    /*首页*/
    public String EnEveryDayindex() {
        return getIP() + "small.php/EnEveryDay/index";
    }

    /*收藏*/
    public String EnEveryDaycollection() {
        return getIP() + "small.php/EnEveryDay/collection";
    }

    /*喜欢*/
    public String EnEveryDaylike() {
        return getIP() + "small.php/EnEveryDay/like";
    }

    /*发表*/
    public String EnEveryDayaddSound() {
        return getIP() + "small.php/EnEveryDay/addSound";
    }

    /*详情*/
    public String EnEveryDaydayInfo() {
        return getIP() + "small.php/EnEveryDay/dayInfo";
    }

    /*游戏或练习*/
    public String EnEveryDaygame() {
        return getIP() + "small.php/EnEveryDay/game";
    }

    /*******************配音****************/
    /*首页*/
    public String Dubbingindex() {
        return getIP() + "small.php/Dubbing/index";
    }

    /*专辑列表*/
    public String DubbingalbumList() {
        return getIP() + "small.php/Dubbing/albumList";
    }

    /*专辑内容列表*/
    public String DubbingcontentList() {
        return getIP() + "small.php/Dubbing/contentList";
    }

    /*教材版本*/
    public String DubbinggetVersion() {
        return getIP() + "small.php/Dubbing/getVersion";
    }

    /*教材版本*/
    public String Dubbingcollection() {
        return getIP() + "small.php/Dubbing/collection";
    }

    /*内容*/
    public String Dubbingcontent() {
        return getIP() + "small.php/Dubbing/content";
    }

    /*开始配音*/
    public String DubbingstartDubbing() {
        return getIP() + "small.php/Dubbing/startDubbing";
    }

    /*配音排行*/
    public String DubbingdubbingRankingList() {
        return getIP() + "small.php/Dubbing/dubbingRankingList";
    }

    /*上传配音*/
    public String DubbinguploadDubbing() {
        return getIP() + "small.php/Dubbing/uploadDubbing";
    }

    /*配音详情*/
    public String DubbingdubbingInfo() {
        return getIP() + "small.php/Dubbing/dubbingInfo";
    }

    /*评论*/
    public String Dubbingcomment() {
        return getIP() + "small.php/Dubbing/comment";
    }

    /*评论详情*/
    public String DubbingcommentInfo() {
        return getIP() + "small.php/Dubbing/commentInfo";
    }

    /*点赞*/
    public String DubbingthumbUp() {
        return getIP() + "small.php/Dubbing/thumbUp";
    }


    /*****************英语阅读**************/
    /*首页*/
    public String EnReadIndex() {
        return getIP() + "small.php/EnRead/index";
    }

    /*内容*/
    public String readContent() {
        return getIP() + "small.php/EnRead/readContent";
    }

    /*题目*/
    public String EnReadcontent() {
        return getIP() + "small.php/EnRead/content";
    }

    /*过关*/
    public String EnReadadopt() {
        return getIP() + "small.php/EnRead/adopt";
    }

    /******************************/
    /*百变听力*/
    /*首页*/
    public String EnListeningindex() {
        return getIP() + "small.php/EnListening/index";
    }

    /*内容*/
    public String EnListeningcontent() {
        return getIP() + "small.php/EnListening/content";
    }

    /*foot做题过关*/

    /*foot做题过关*/
    public String EnListeningadopt() {
        return getIP() + "small.php/EnListening/adopt";
    }

    /*foot信息*/
    public String EnListeningfootIndex() {
        return getIP() + "small.php/EnListening/footIndex";
    }

    //***********幼儿园******************/

    /*********宝宝听听**************/
    /*首页*/
    public String ChildBabyListeningindex() {
        return getIP() + "small.php/ChildBabyListening/index";
    }

    /*分类*/
    public String ChildBabyListeningcate() {
        return getIP() + "small.php/ChildBabyListening/cate";
    }

    /*故事列表*/
    public String ChildBabyListeningalbumIndex() {
        return getIP() + "small.php/ChildBabyListening/albumIndex";
    }

    /*获取更多的推荐专辑（精选） 精品专辑(重磅)*/
    public String ChildBabyListeninggetTuiJianAlbum() {
        return getIP() + "small.php/ChildBabyListening/getTuiJianAlbum";
    }

    /*故事内容*/
    public String ChildBabyListeningcontent() {
        return getIP() + "small.php/ChildBabyListening/content";
    }

    /*评论*/
    public String ChildBabyListeningcomment() {
        return getIP() + "small.php/ChildBabyListening/comment";
    }

    /*评论详情*/
    public String ChildBabyListeningcommentInfo() {
        return getIP() + "small.php/ChildBabyListening/commentInfo";
    }

    /*点赞*/
    public String ChildBabyListeningthumbUp() {
        return getIP() + "small.php/ChildBabyListening/thumbUp";
    }

    /*收藏&取消收藏*/
    public String ChildBabyListeningcollection() {
        return getIP() + "small.php/ChildBabyListening/collection";
    }

    /*我的收藏*/
    public String ChildBabyListeningmyCollection() {
        return getIP() + "small.php/ChildBabyListening/myCollection";
    }


    /************绘本****************/
    /*关卡*/
    public String ChildBookindex() {
        return getIP() + "small.php/ChildBook/index";
    }

    /*书籍列表*/
    public String ChildBookbookList() {
        return getIP() + "small.php/ChildBook/bookList";
    }

    /*书籍首页*/
    public String ChildBookbookIndex() {
        return getIP() + "small.php/ChildBook/bookIndex";
    }

    /*收藏，取消收藏*/
    public String ChildBookcollection() {
        return getIP() + "small.php/ChildBook/collection";
    }

    /*點贊*/
    public String ChildBookthumbUp() {
        return getIP() + "small.php/ChildBook/thumbUp";
    }

    /*内容*/
    public String ChildBookcontent() {
        return getIP() + "small.php/ChildBook/content";
    }

    /*我的作品*/
    public String ChildBookmyWorks() {
        return getIP() + "small.php/ChildBook/myWorks";
    }

    /*我的收藏*/
    public String ChildBookmyCollection() {
        return getIP() + "small.php/ChildBook/myCollection";
    }

    /*取消收藏*/
    public String ChildBookdelCollection() {
        return getIP() + "small.php/ChildBook/delCollection";
    }

    /*上传文件*/
    public String ChildBookuploadSoundRecording() {
        return getIP() + "small.php/ChildBook/uploadSoundRecording";
    }

    /****宝宝****/
    /*首页*/
    public String ChildBabyindex() {
        return getIP() + "small.php/ChildBaby/index";
    }

    /*阅读*/
    public String ChildBabyread() {
        return getIP() + "small.php/ChildBaby/read";
    }

    /*阅读*/
    public String ChildBabyselectThinking() {
        return getIP() + "small.php/ChildBaby/selectThinking";
    }

    /*****口算大王***/
    /*首页*/
    public String ChildMentalArithmeticindex() {
        return getIP() + "small.php/ChildMentalArithmetic/index";
    }

    /*内容*/
    public String ChildMentalArithmeticcontent() {
        return getIP() + "small.php/ChildMentalArithmetic/content";
    }

    /*****游戏****/
    /*首页*/
    public String ChildGameindex() {
        return getIP() + "small.php/ChildGame/index";
    }

    /*分类*/
    public String ChildGamecate() {
        return getIP() + "small.php/ChildGame/cate";
    }

    /*分享*/
    public String ChildBabyshareUnlock() {
        return getIP() + "small.php/ChildGame/shareUnlock";
    }

    /*统计时间*/
    public String ChildGamedecTime() {
        return getIP() + "small.php/ChildGame/decTime";
    }

    /********思维和注意力训练********/
    /*首页*/
    public String ChildThinkingindex() {
        return getIP() + "small.php/ChildThinking/index";
    }

    /*内容*/
    public String ChildThinkingcontent() {
        return getIP() + "small.php/ChildThinking/content";
    }

    /*评论*/
    public String ChildThinkingcomment() {
        return getIP() + "small.php/ChildThinking/comment";
    }

    /*评论详情*/
    public String ChildThinkingcommentInfo() {
        return getIP() + "small.php/ChildThinking/commentInfo";
    }

    /*点赞*/
    public String ChildThinkingthumbUp() {
        return getIP() + "small.php/ChildThinking/thumbUp";
    }


    //*****识字动画***********/
    /*首页*/
    public String ChildAnimationindex() {
        return getIP() + "small.php/ChildAnimation/index";
    }

    /*内容*/
    public String ChildAnimationcontent() {
        return getIP() + "small.php/ChildAnimation/content";
    }

    /*评论*/
    public String ChildAnimationcomment() {
        return getIP() + "small.php/ChildAnimation/comment";
    }

    /*评论详情*/
    public String ChildAnimationcommentInfo() {
        return getIP() + "small.php/ChildAnimation/commentInfo";
    }

    /*点赞*/
    public String ChildAnimationthumbUp() {
        return getIP() + "small.php/ChildAnimation/thumbUp";
    }

    //***************商城*********************/
    /*首页*/
    public String mallindex() {
        return getIP() + "small.php/mall/index";
    }

    /*一级分类*/
    public String MallcateList() {
        return getIP() + "small.php/Mall/cateList";
    }

    /*二级分类*/
    public String MallgetCateChildList() {
        return getIP() + "small.php/Mall/getCateChildList";
    }

    /*分类商品列表*/
    public String MallcateGoodsList() {
        return getIP() + "small.php/Mall/searchGoods";
    }

    /*商品详情*/
    public String MallgoodsInfo() {
        return getIP() + "small.php/Mall/goodsInfo";
    }

    /*商品规格信息*/
    public String MallgetGoodsSpec() {
        return getIP() + "small.php/Mall/getGoodsSpec";
    }

    /* 添加到购物车*/
    public String MalladdGoodsCart() {
        return getIP() + "small.php/Mall/addGoodsCart";
    }

    /*确认订单*/
    public String MallconfirmOrder() {
        return getIP() + "small.php/Mall/confirmOrder";
    }

    /*购物车列表*/
    public String MallmyCart() {
        return getIP() + "small.php/Mall/myCart";
    }

    /*设置购物车商品数量*/
    public String MallsetCartGoodsCount() {
        return getIP() + "small.php/Mall/setCartGoodsCount";
    }

    /*删除物车商品数量*/
    public String MalldelCartGoods() {
        return getIP() + "small.php/Mall/delCartGoods";
    }

    /*生成订单*/
    public String ordercreateOrder() {
        return getIP() + "small.php/order/createOrder";
    }

    /*余额支付*/
    public String paybalancePayOrder() {
        return getIP() + "small.php/pay/balancePayOrder";
    }

    /*确认收货*/
    public String PayconfirmGoods() {
        return getIP() + "small.php/Pay/confirmGoods";
    }

    /*取消订单*/
    public String ordercancelOrder() {
        return getIP() + "small.php/Order/cancelOrder";
    }

    /*催货*/
    public String Orderurge() {
        return getIP() + "small.php/Order/urge";
    }

    /****************攻略订单**************************/

    /*攻略确认订单信息*/
    public String StrategygetLineInfo() {
        return getIP() + "small.php/Strategy/getLineInfo";
    }
    /*攻略提交订单信息*/

    public String OrdercreateLineOrder() {
        return getIP() + "small.php/Order/createLineOrder";
    }

    /*余额支付*/
    public String PaybalancePayLine() {
        return getIP() + "small.php/Pay/balancePayLine";
    }

    /***************活动****************/
    /*活动列表*/
    public String StrategyactivityList() {
        return getIP() + "small.php/Strategy/activityList";
    }

    /*活动详情*/
    public String StrategyactivityInfo() {
        return getIP() + "small.php/Strategy/activityInfo";
    }

    /*活动评论*/
    public String StrategyactivityComment() {
        return getIP() + "small.php/Strategy/activityComment";
    }

    /*活动评论详情*/
    public String StrategyactivityCommentInfo() {
        return getIP() + "small.php/Strategy/activityCommentInfo";
    }

    /*活动评论点赞*/
    public String StrategyactivityThumbUp() {
        return getIP() + "small.php/Strategy/activityThumbUp";
    }

    /*活动报名*/
    public String StrategyactivitySignUp() {
        return getIP() + "small.php/Strategy/activitySignUp";
    }

    /*************攻略学生******************/

    /*学生首页*/
    public String Strategyindex() {
        return getIP() + "small.php/Strategy/index";
    }

    /*线上分类*/
    public String StrategylineCateList() {
        return getIP() + "small.php/Strategy/lineCateList";
    }

    /*线上线下列表*/
    public String StrategylineList() {
        return getIP() + "small.php/Strategy/lineList";
    }

    /*线上线下内容*/
    public String StrategylineInfo() {
        return getIP() + "small.php/Strategy/lineInfo";
    }

    /*线上线下评论*/
    public String getLineInfoCommentineInfo() {
        return getIP() + "small.php/Strategy/getLineInfoComment";
    }

    /*线上线下点赞*/
    public String StrategylineThumbUp() {
        return getIP() + "small.php/Strategy/lineThumbUp";
    }

    /*线上线下评论*/
    public String StrategylineComment() {
        return getIP() + "small.php/Strategy/lineComment";
    }

    /*线上线下评论详情*/
    public String StrategylineCommentInfo() {
        return getIP() + "small.php/Strategy/lineCommentInfo";
    }

    /*线上线下预约*/
    public String StrategylineYuYue() {
        return getIP() + "small.php/Strategy/lineYuYue";
    }

    /*学生-微学堂一级分类*/
    public String StrategymicroCateList() {
        return getIP() + "small.php/Strategy/microCateList";
    }

    /*学生-微学堂列表*/
    public String StrategymicroList() {
        return getIP() + "small.php/Strategy/microList";
    }

    /*学生-微学堂详情*/
    public String StrategymicroInfo() {
        return getIP() + "small.php/Strategy/microInfo";
    }

    /*学生-微学堂试听详情*/
    public String StrategytryListeningInfo() {
        return getIP() + "small.php/Strategy/tryListeningInfo";
    }/*学生-微学堂试听评论列表*/

    public String getTryListeningInfoComment() {
        return getIP() + "small.php/Strategy/getTryListeningInfoComment";
    }

    /*学生-微学堂详情列表*/
    public String StrategymyMicroInfo() {
        return getIP() + "small.php/Strategy/myMicroInfo";
    }

    /*学生-微学堂大纲详情*/
    public String StrategymicroClassInfo() {
        return getIP() + "small.php/Strategy/microClassInfo";
    }

    /*学生-微学堂大纲评论*/
    public String StrategymicroComment() {
        return getIP() + "small.php/Strategy/microComment";
    }

    /*学生-微学堂大纲点赞*/
    public String StrategymicroThumbUp() {
        return getIP() + "small.php/Strategy/microThumbUp";
    }

    /*学生-微学堂大纲评论详情*/
    public String StrategymicroCommentInfo() {
        return getIP() + "small.php/Strategy/microCommentInfo";
    }


    /*试听课分类*/
    public String StrategytryListeningCateList() {
        return getIP() + "small.php/Strategy/tryListeningCateList";
    }

    /**
     * 试听课列表
     */
    public String StrategytryListeningList() {
        return getIP() + "small.php/Strategy/tryListeningList";
    }

    //我的微课专辑列表
    public String StrategymyMicro() {
        return getIP() + "small.php/Strategy/myMicro";
    }

    //我的试听课
    public String StrategymyTryListening() {
        return getIP() + "small.php/Strategy/myTryListening";
    }

    //我的线上线下课程
    public String StrategymyLineCourse() {
        return getIP() + "small.php/Strategy/myLineCourse";
    }

    //我的收藏老师
    public String StrategymyCollection() {
        return getIP() + "small.php/Strategy/myCollection";
    }

    //我的活动
    public String StrategymyActivity() {
        return getIP() + "small.php/Strategy/myActivity";
    }

    //我的课程表
    public String StrategymyCurriculum() {
        return getIP() + "small.php/Strategy/myCurriculum";
    }

    //课程记录
    public String StrategyclassRecord() {
        return getIP() + "small.php/Strategy/classRecord";
    }

    /****************攻略家长*********************/
    /*培训首页*/
    public String StrategyparentView() {
        return getIP() + "small.php/Strategy/parentView";
    }

    /*教子有方列表*/
    public String StrategyjiaoziList() {
        return getIP() + "small.php/Strategy/jiaoziList";
    }

    /*教子有方内容*/
    public String StrategyjiaozInfo() {
        return getIP() + "small.php/Strategy/jiaozInfo";
    }

    /*教子有方评论*/
    public String StrategyparentJiaoZiComment() {
        return getIP() + "small.php/Strategy/parentJiaoZiComment";
    }

    /*教子有方点赞*/
    public String StrategyparentJiaoZiThumbUp() {
        return getIP() + "small.php/Strategy/parentJiaoZiThumbUp";
    }

    /*教子有方评论详情*/
    public String StrategyparentJiaoZiCommentInfo() {
        return getIP() + "small.php/Strategy/parentJiaoZiCommentInfo";
    }

    /****头条***/
    /*头条列表*/
    public String StrategyheadlineList() {
        return getIP() + "small.php/Strategy/headlineList";
    } /*头条内容*/

    public String StrategyheadlineInfo() {
        return getIP() + "small.php/Strategy/headlineInfo";
    }

    /*头条评论*/
    public String parentHeadlineComment() {
        return getIP() + "small.php/Strategy/parentHeadlineComment";
    }

    /*头条点赞*/
    public String parentHeadlineThumbUp() {
        return getIP() + "small.php/Strategy/parentHeadlineThumbUp";
    }

    /*头条评论详情*/
    public String parentHeadlineCommentInfo() {
        return getIP() + "small.php/Strategy/parentHeadlineCommentInfo";
    }

    //家长问答订单列表
    public String StrategyanswerOrder() {
        return getIP() + "small.php/Strategy/answerOrder";
    }

    //家长问答确认完成
    public String StrategyconfirmOrderReply() {
        return getIP() + "small.php/Strategy/confirmOrderReply";
    }

    //家长问答是否公开
    public String StrategyonlySelfLook() {
        return getIP() + "small.php/Strategy/onlySelfLook";
    }

    //预约订单列表
    public String StrategyyuYueOrder() {
        return getIP() + "small.php/Strategy/yuYueOrder";
    }

    //家长预约订单评价
    public String StrategyuserComment() {
        return getIP() + "small.php/Strategy/userComment";
    }

    //预约订单余额支付
    public String balancePayYuYueService() {
        return getIP() + "small.php/Pay/balancePayYuYueService";
    }


    /*************攻略教师***********/
    /*培训首页*/
    public String StrategyteacherIndex() {
        return getIP() + "small.php/Strategy/teacherIndex";
    }

    /*加入我们*/
    public String StrategyapplyInfo() {
        return getIP() + "small.php/Strategy/applyInfo";
    }

    /*加入我们*/
    public String StrategyapplyForTeacher() {
        return getIP() + "small.php/Strategy/applyForTeacher";
    }

    /*平台教师首页*/
    public String platformTeacherIndex() {
        return getIP() + "small.php/Strategy/platformTeacherIndex";
    }


    /*平台咨询师首页*/
    public String StrategyconsultingIndex() {
        return getIP() + "small.php/Strategy/consultingIndex";
    }

    /*平台咨询师详情*/
    public String StrategyteacherYuYueInfo() {
        return getIP() + "small.php/Strategy/teacherYuYueInfo";
    }

    /*平台咨询师预约列表*/
    public String StrategygetTeacherYuYue() {
        return getIP() + "small.php/Strategy/getTeacherYuYue";
    }

    /*平台咨询师写评论*/
    public String StrategysetYuYueComment() {
        return getIP() + "small.php/Strategy/setYuYueComment";
    }

    /*平台咨询问答在线*/
    public String StrategyanswerLine() {
        return getIP() + "small.php/Strategy/answerLine";
    }

    /*平台咨询问答在线*/
    public String StrategyconsultingLine() {
        return getIP() + "small.php/Strategy/consultingLine";
    }

    /*平台咨询问答列表*/
    public String StrategyteacherAnswerList() {
        return getIP() + "small.php/Strategy/teacherAnswerList";
    }

    /*平台咨询问答回复*/
    public String StrategyreplyAnswerOrder() {
        return getIP() + "small.php/Strategy/replyAnswerOrder";
    }

    //咨询问答列表（公开）
    public String teacherAnswerComplete() {
        return getIP() + "small.php/Strategy/teacherAnswerComplete";
    }

    //发起咨询问答
    public String consultingQuestion() {
        return getIP() + "small.php/Strategy/consultingQuestion";
    }

    //发起咨询问答支付
    public String payAnswerQuestion() {
        return getIP() + "small.php/Strategy/payAnswerQuestion";
    }

    /*平台咨询问答回复详情*/
    public String StrategyanswerOrderInfo() {
        return getIP() + "small.php/Strategy/answerOrderInfo";
    }

    /*平台咨询问答余额*/
    public String StrategygetAnswerMoney() {
        return getIP() + "small.php/Strategy/getAnswerMoney";
    }

    /*平台咨询问答设置价格*/
    public String StrategysetAnswerPrice() {
        return getIP() + "small.php/Strategy/setAnswerPrice";
    }

    /*平台咨询问答提交审核*/
    public String StrategysubmitAudit() {
        return getIP() + "small.php/Strategy/submitAudit";
    }


    //平台老师内容
    //课程学员
    public String StrategyviewStaff() {
        return getIP() + "small.php/Strategy/viewStaff";
    }

    //课程学员，添加评论
    public String StrategycommentCourse() {
        return getIP() + "small.php/Strategy/commentCourse";
    }

    //课程完结
    public String StrategyhaveClass() {
        return getIP() + "small.php/Strategy/haveClass";
    }

    //平台培训老师详情
    public String StrategyteacherInfo() {
        return getIP() + "small.php/Strategy/teacherInfo";
    }

    //平台培训老师关注
    public String StrategyguanzhuTeacher() {
        return getIP() + "small.php/Strategy/guanzhuTeacher";
    }

    //获取预约服务信息
    public String StrategygetYuYueServiceInfo() {
        return getIP() + "small.php/Strategy/getYuYueServiceInfo";
    }

    //获取预约服务时间
    public String StrategygetConsultantTime() {
        return getIP() + "small.php/Strategy/getConsultantTime";
    }

    //获取咨询预约
    public String StrategyyuYueService() {
        return getIP() + "small.php/Strategy/yuYueService";
    }

    //教师列表
    public String StrategygetTeacherList() {
        return getIP() + "small.php/Strategy/getTeacherList";
    }

    //教师主题
    public String StrategyteacherTheme() {
        return getIP() + "small.php/Strategy/teacherTheme";
    }


    /***********攻略培训*****************/
    /*培训首页*/
    public String StrategyTrainindex() {
        return getIP() + "small.php/StrategyTrain/index";
    }

    /*结构内容*/
    public String StrategyTrainschoolInfo() {
        return getIP() + "small.php/StrategyTrain/schoolInfo";
    }

    /*机构介绍*/
    public String StrategyTrainschoolInfoDesc() {
        return getIP() + "small.php/StrategyTrain/schoolInfoDesc";
    }

    /*机构列表*/
    public String StrategyTrainschoolList() {
        return getIP() + "small.php/StrategyTrain/schoolList";
    }

    /*课程特色&学校活动&所获荣誉&办学成就*/
    public String StrategyTraintuwen() {
        return getIP() + "small.php/StrategyTrain/tuwen";
    }

    /*优秀教师&优秀学员*/
    public String StrategyTraingoodTS() {
        return getIP() + "small.php/StrategyTrain/goodTS";
    }

    /*教师&学员详情*/
    public String StrategyTraingoodTSInfo() {
        return getIP() + "small.php/StrategyTrain/goodTSInfo";
    }

    /*校园环境&学校相册*/
    public String StrategyTrainschoolPhoto() {
        return getIP() + "small.php/StrategyTrain/schoolPhoto";
    }

    /*校园环境&学校相册*/
    public String StrategyTrainphotoList() {
        return getIP() + "small.php/StrategyTrain/photoList";
    }

    /*点赞*/
    public String StrategyTrainthumbUp() {
        return getIP() + "small.php/StrategyTrain/thumbUp";
    }

    /*课程列表*/
    public String StrategyTraincurrList() {
        return getIP() + "small.php/StrategyTrain/currList";
    }

    /*课程信息*/
    public String StrategyTraincurrInfo() {
        return getIP() + "small.php/StrategyTrain/currInfo";
    }

    /***************攻略幼儿****************/
    /*首页*/
    public String StrategyChildindex() {
        return getIP() + "small.php/StrategyChild/index";
    }

    /*结构内容*/
    public String StrategyChildschoolInfo() {
        return getIP() + "small.php/StrategyChild/schoolInfo";
    }

    /*机构介绍*/
    public String StrategyChildschoolInfoDesc() {
        return getIP() + "small.php/StrategyChild/schoolInfoDesc";
    }

    /*院長介紹*/
    public String StrategyChilddirectorIntroduce() {
        return getIP() + "small.php/StrategyChild/directorIntroduce";
    }

    /*教師列表*/
    public String StrategyChildclassTeacher() {
        return getIP() + "small.php/StrategyChild/classTeacher";
    }

    /*教師詳情*/
    public String StrategyChildgoodTSInfo() {
        return getIP() + "small.php/StrategyChild/goodTSInfo";
    }

    /*幼儿园荣誉&幼儿园特色&活动专区*/
    public String StrategyChildtuwen() {
        return getIP() + "small.php/StrategyChild/tuwen";
    }

    /*每周食谱*/
    public String StrategyChildeveryWeek() {
        return getIP() + "small.php/StrategyChild/everyWeek";
    }

    /*兴趣列表*/
    public String StrategyChildinterestList() {
        return getIP() + "small.php/StrategyChild/interestList";
    }

    /*兴趣班信息 招生信息*/
    public String StrategyChildinterestInfo() {
        return getIP() + "small.php/StrategyChild/interestInfo";
    }

    /*关注&取消关注*/
    public String StrategyChildcloseGuanZhu() {
        return getIP() + "small.php/StrategyChild/closeGuanZhu";
    }

    /*预约*/
    public String StrategyChildyuYueOrTryListening() {
        return getIP() + "small.php/StrategyChild/yuYueOrTryListening";
    }

    /*换一批*/
    public String StrategyChildinBatch() {
        return getIP() + "small.php/StrategyChild/inBatch";

    }

    /*班级相册列表*/
    public String getPhotoClass() {
        return getIP() + "small.php/StrategyChild/getPhotoClass";
    }

    /*班级相册列表*/
    public String StrategyChildclassPhoto() {
        return getIP() + "small.php/StrategyChild/classPhoto";
    }

    /*班级相册详情*/
    public String StrategyChildphotoList() {
        return getIP() + "small.php/StrategyChild/photoList";
    }


    /**********************全局接口 ************************/

    /*bug文档*/
    public String AppLogindex() {
        return getIP() + "small.php/AppLog/index";
    }


    /*添加分享花费记录*/
    public String addShareSpendRecord() {
        return getIP() + "small.php/Common/addShareSpendRecord";
    }

    /*错误的题目*/
    public String errorQuestion() {
        return getIP() + "small.php/Common/errorQuestion";
    }

    /*错误的题目订正*/
    public String correction() {
        return getIP() + "small.php/Common/correction";
    }

    /*错题中心列表*/
    public String errorList() {
        return getIP() + "small.php/Common/errorList";
    }

    /*分享*/
    public String getShare() {
        return getIP() + "small.php/User/share/weizhi/1/uid/" + MyApplication.getmInstance().getUid() + "/type/";
    }

    /******************竞赛***************/
    /*首页*/
    public String Match1index() {
        return getIP() + "small.php/Match1/index";
    }

    /*匹配*/
    public String startMatch() {
        return getIP() + "small.php/Match1/startMatch";
    }

    /*开始答题*/
    public String startAnswer() {
        return getIP() + "small.php/Match1/startAnswer";
    }

    /*答题*/
    public String Match1answer() {
        return getIP() + "small.php/Match1/answer";
    }

    /*中途退出*/
    public String MatchoutMatch() {
        return getIP() + "small.php/Match1/outMatch";
    }

    /*竞赛结束*/
    public String Match1calculate() {
        return getIP() + "small.php/Match1/calculate";
    }

    /*分享加果果豆*/
    public String addMatchSharePoints() {
        return getIP() + "small.php/Match1/addMatchSharePoints";
    }

    /*检查用户资格*/
    public String checkUserQualification() {
        return getIP() + "small.php/Match1/checkUserQualification";
    }

    /*根据分数添加用户果果豆*/
    public String CommonaddAnswerPoints() {
        return getIP() + "small.php/Common/addAnswerPoints";
    }
}
