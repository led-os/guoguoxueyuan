package com.greendao.gen.bean;

import android.content.Context;

import com.greendao.gen.DaoMaster;
import com.greendao.gen.DaoSession;
import com.greendao.gen.ZipFileDataDao;

import org.greenrobot.greendao.database.Database;

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
public class DatabaseManage {
    private DaoSession mSession = null;
    private ZipFileDataDao zipFileDataDao = null;

    private DatabaseManage() {

    }

    public DatabaseManage init(Context context) {
        initData(context);
        return this;
    }

    //初始化数据库，新建名为myPerson的数据库
    private void initData(Context context) {
        final ReleaseOpenHelper helper = new ReleaseOpenHelper(context, "guoguo.db", null);
        final Database db = helper.getWritableDb();
        mSession = new DaoMaster(db).newSession();
        zipFileDataDao = mSession.getZipFileDataDao();//添加数据表
    }

    //静态内部类单例
    private static final class Holder {
        private static final DatabaseManage INSTANCE = new DatabaseManage();
    }

    //获取单例
    public static DatabaseManage getInstance() {
        return Holder.INSTANCE;
    }

    public ZipFileDataDao getZipFileDataDao() {
        return zipFileDataDao;
    }
}