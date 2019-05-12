package com.test720.grasshoppercollege.untils.downUntil;

import android.content.Context;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.HttpBean.CiDaiNeiRongData;
import com.test720.grasshoppercollege.module.diandu.bean.BookDataBean;
import com.test720.grasshoppercollege.untils.LogUtil;

import www.test720.mylibrary.SPUtils;

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
 * 作者：水东流 编于 2018/9/28
 */
//课本数据保存和查询工具
public class BookDataInsterUntil {
    static final String DIANDU = "diandu";
    static final String CIDAI = "cidai";
    static final String BAOBAO = "baobao";
    static final String HUIBEN = "huiben";

    public BookDataInsterUntil() {
    }

    /**
     * 点读课本插入数据
     */
    public int insert(Context context, BookDataBean bookDataBean) {
        String bookId = DIANDU + bookDataBean.getData().getBook().getBook_id();
        Gson gson = new Gson();
        String bookStr = gson.toJson(bookDataBean);
        SPUtils.putString(context, bookId, bookStr);
        return 1;
    }

    /**
     * 获取点读数据
     */

    public BookDataBean getBook(Context context, String bookId) {
        String str = SPUtils.getString(context, DIANDU + bookId, "");
        if (str.equals("")) {
            LogUtil.logError("此书不存在");
            return null;
        }
        return new Gson().fromJson(str, BookDataBean.class);
    }


    /**
     * 保存磁带资源版本
     *
     * @return 成功与否
     */
    public int saveCiDaiV(Context context, String bookId, String version) {
        SPUtils.putString(context, CIDAI + bookId, version);
        return 1;
    }

    /**
     * 保存磁带书籍数据
     *
     * @param context
     * @param ciDaiNeiRongData 书本内容
     * @return
     */
    public int saveCiDaiBean(Context context, CiDaiNeiRongData ciDaiNeiRongData) {
        String bookId = CIDAI + ciDaiNeiRongData.getData().getInfo().getBook_version() +
                ciDaiNeiRongData.getData().getInfo().getClassX() +
                ciDaiNeiRongData.getData().getInfo().getStatus();
        Gson gson = new Gson();
        String bookStr = gson.toJson(ciDaiNeiRongData);
        SPUtils.putString(context, bookId, bookStr);
        return 1;
    }

    /**
     * 读取磁带书本数据
     *
     * @param context
     * @param bookName
     * @return
     */
    public CiDaiNeiRongData getCiDaiBean(Context context, String bookName) {
        String str = SPUtils.getString(context, CIDAI + bookName, "");
        return new Gson().fromJson(str, CiDaiNeiRongData.class);
    }

    /**
     * 磁带本地版本获取
     */
    public String getCDVersion(Context context, String bookId) {
        String str = SPUtils.getString(context, CIDAI + bookId, "");
        if (str.equals("")) {
            LogUtil.logError("此书不存在");
            return null;
        }
        return str;
    }


    /**
     * 保存点读乐园资源版本
     *
     * @return 成功与否
     */
    public int saveBaoBaoV(Context context, String name, String version) {
        SPUtils.putString(context, BAOBAO + name, version);
        return 1;
    }

    /**
     * 点读乐园本地版本获取
     */
    public String getBBVersion(Context context, String name) {
        String str = SPUtils.getString(context, BAOBAO + name, "");
        if (str.equals("")) {
            LogUtil.logError("此资源不存在");
            return null;
        }
        return str;
    }

    /**
     * 保存绘本版本
     *
     * @param context
     * @param id
     * @param version
     * @return
     */
    public void saveHuiBenVer(Context context, String id, String version) {
        SPUtils.putString(context, HUIBEN + id, version);
    }

    /**
     * 获取绘本本地版本号
     *
     * @param context
     * @param id
     * @return
     */
    public String getHuiBenVer(Context context, String id) {
        return SPUtils.getString(context, HUIBEN + id, "");
    }

    /**
     * 保存绘本书籍数据
     *
     * @return
     */
    public int saveHuiBenBean(Context context, String bookId, String strJson) {
        SPUtils.putString(context, HUIBEN + bookId, strJson);
        return 1;
    }

    /**
     * 读取绘本书本数据
     *
     * @param context
     * @return
     */
    public String getHuiBenBean(Context context, String bookId) {
        String str = SPUtils.getString(context, HUIBEN + bookId, "");
        return str;
    }

}
