package com.test720.grasshoppercollege.untils.downUntil;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.androidkun.breakpoints.services.DownloadService;
import com.greendao.gen.ZipFileDataDao;
import com.greendao.gen.bean.ZipFileData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.untils.FileSizeUntil;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.LogUtil;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

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
 * 作者：水东流 编于 2018/10/11
 */
//* 解压文件
public class ZipUntil {

    private String name;
    private String version;
    private String moudle;
    ZipFileDataDao zipFileDataDao;

    public ZipUntil() {
        zipFileDataDao = MyApplication.getmInstance().getManage().getZipFileDataDao();
    }

    public ZipUntil(String name, String version, String moudle) {
        this.name = name;
        this.version = version;
        this.moudle = moudle;
        zipFileDataDao = MyApplication.getmInstance().getManage().getZipFileDataDao();
    }

    /**
     * 解压缩一个文件
     *
     * @param zipFile 压缩文件
     * @throws IOException 当解压缩过程出错时抛出
     */
    public void upZipFile(final File zipFile, Context mcontext, final ZipOkEnvent zipOkEnvent) {
        final String folderPath = mcontext.getFilesDir() + "/downloads";
        AndPermission.with(mcontext).requestCode(100).permission(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE).callback(new PermissionListener() {
            @Override
            public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
                new Thread(new MyRun(folderPath, zipFile, zipOkEnvent)).start();
            }

            @Override
            public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
            }
        }).start();


    }

    /**
     * 解压方法
     *
     * @param folderPath
     * @param zipFile
     * @param zipOkEnvent
     */
    private void runEvent(String folderPath, File zipFile, ZipOkEnvent zipOkEnvent) {
        File desDir = new File(folderPath);
        if (!desDir.exists()) {
            desDir.mkdirs();
        }
        ZipFile zf = null;
        try {
            zf = new ZipFile(zipFile);
            LogUtil.logError("zip文件名" + zf.getName());
            for (Enumeration<?> entries = zf.entries(); entries.hasMoreElements(); ) {
                ZipEntry entry = ((ZipEntry) entries.nextElement());
                if (entry.isDirectory()) {
                    continue;
                }
                InputStream in = zf.getInputStream(entry);
                String str = folderPath + File.separator + entry.getName();
                str = new String(str.getBytes(), "utf-8");
                LogUtil.logError("开始解压" + str + "文件");
                File desFile = new File(str);
                if (!desFile.exists()) {
                    File fileParentDir = desFile.getParentFile();
                    if (!fileParentDir.exists()) {
                        fileParentDir.mkdirs();
                        LogUtil.logError(fileParentDir.getPath() + "目录不存在，正在创建目录");
                    }
                    desFile.createNewFile();
                }
                OutputStream out = new FileOutputStream(desFile);
                LogUtil.logError(desFile.getName() + "文件流创建成功");
                byte buffer[] = new byte[1024 * 1024];
                int realLength;
                while ((realLength = in.read(buffer)) > 0) {
                    out.write(buffer, 0, realLength);
                }
                LogUtil.logError(desFile.getName() + "解压完成");
                in.close();
                out.close();
            }
            LogUtil.logError("解压" + zipFile.getName() + "完成");
            if (name != null && version != null && moudle != null) {
                ZipFileData zipFileData = new ZipFileData();
                zipFileData.setFileName(name);
                zipFileData.setMoudle(moudle);
                zipFileData.setVersion(version);
                LogUtil.logError("zf.getname=" + zf.getName());
                String zfname = zf.getName();
                if (zf.getName().lastIndexOf("/") != -1) {
                    zfname = zf.getName().substring(zf.getName().lastIndexOf("/") + 1);
                }
                LogUtil.logError("zfname=" + zfname);
                String prefix = "";
                if (zfname.lastIndexOf(".") != -1) {
                    prefix = zfname.substring(zfname.lastIndexOf("."));
                    LogUtil.logError("prefix=" + prefix);
                }
                String fileOtherName = zfname.substring(0, zfname.length() - prefix.length());
                zipFileData.setZipName(fileOtherName);
                LogUtil.logError("zip目录名：" + fileOtherName);
                double fileSize = FileSizeUntil.getInstance().getFileOrFilesSize(zipFile.getPath(), FileSizeUntil.SIZETYPE_MB);
                zipFileData.setFileSize(fileSize);
                zipFileDataDao.insert(zipFileData);
                LogUtil.logError(zipFile.getName() + "信息插入数据库完成");
            }
            zipFile.delete();
            if (zipOkEnvent != null) zipOkEnvent.zipOkEnvent();
        } catch (IOException e) {
            e.printStackTrace();
            LogUtil.logError("解压错误信息：" + e.getMessage());
        }
    }

    /**
     * 解压线程
     */
    class MyRun implements Runnable {
        String folderPath;
        File zipFile;
        ZipOkEnvent zipOkEnvent;

        public MyRun(String folderPath, File zipFile, ZipOkEnvent zipOkEnvent) {
            this.folderPath = folderPath;
            this.zipFile = zipFile;
            this.zipOkEnvent = zipOkEnvent;
        }

        @SuppressLint("CheckResult")
        @Override
        public void run() {
            Flowable.just(1)
                    .observeOn(Schedulers.io())
                    .subscribe(integer -> runEvent(folderPath, zipFile, zipOkEnvent));
        }
    }

    /**
     * 删除本地资源
     */
    public void deleZipFile(final String path, final Context mcontext, final DeleListener deleListener) {
        final String folderPath = HttpUntil.GetIntent().filePathHead(mcontext) + path;
        LogUtil.logError("准备删除：" + folderPath);
        AndPermission.with(mcontext).requestCode(100).permission(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE).callback(new PermissionListener() {
            @Override
            public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
                new Thread(() -> {
                    File file = new File(folderPath);
                    deleteDirWihtFile(file);
                    List<ZipFileData> list = zipFileDataDao.queryBuilder().where(ZipFileDataDao.Properties.ZipName.eq(path)).list();
                    if (list.size() > 0) {
                        zipFileDataDao.delete(list.get(0));
                    }
                    Intent intent = new Intent(mcontext, DownloadService.class);
                    if (deleListener != null) deleListener.deleOk();
                    mcontext.stopService(intent);
                    //更改本地资源库时，同时清理一下glide中的缓存数据
                    FileSizeUntil.getInstance().clearImageAllCache(mcontext);
                }).start();
            }

            @Override
            public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
            }
        }).start();

    }

    /**
     * 删除本地资源文件，因图片加载器中有缓存，
     * 未避免更新包后本地更新不及时，删除本地资源时同时调用图片缓存清理
     *
     * @param path
     * @param mcontext
     */
    public void deleZipFile(final String path, final Context mcontext) {
        Flowable.just(1)
                .observeOn(Schedulers.io())
                .subscribe(integer -> deleZipFile(path, mcontext, null));
    }


    /**
     * 删除文件
     *
     * @param dir 目录
     */
    public void deleteDirWihtFile(File dir) {

        if (dir == null || !dir.exists() || !dir.isDirectory())
            return;
        for (File file : dir.listFiles()) {
            if (file.isFile()) {
                file.delete(); // 删除所有文件
                LogUtil.logError("删除了" + file.getName());
            } else if (file.isDirectory())
                deleteDirWihtFile(file); // 递规的方式删除文件夹
        }
        dir.delete();// 删除目录本身
        LogUtil.logError("删除了" + dir.getName());
    }

    public interface ZipOkEnvent {
        void zipOkEnvent();
    }

    public interface DeleListener {
        void deleOk();
    }
}
