package com.greendao.gen.bean;

import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

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
public class DownBeanData {
    @Id
    //必须使用包装类对象类型Long，而非基本类型long
    private Long id;

    @NotNull
    private String url;
    private String savePath;
    private String fileName;
    private String zipName;
    private int length;
    private int progress;
    private int threadCount;
    private String downState;

    @Generated(hash = 2034854548)
    public DownBeanData(Long id, @NotNull String url, String savePath,
                        String fileName, int length, int progress, int threadCount,
                        String downState) {
        this.id = id;
        this.url = url;
        this.savePath = savePath;
        this.fileName = fileName;
        this.length = length;
        this.progress = progress;
        this.threadCount = threadCount;
        this.downState = downState;
    }

    @Generated(hash = 1343780095)
    public DownBeanData() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSavePath() {
        return this.savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getProgress() {
        return this.progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getThreadCount() {
        return this.threadCount;
    }

    public void setThreadCount(int threadCount) {
        this.threadCount = threadCount;
    }

    public String getDownState() {
        return this.downState;
    }

    public void setDownState(String downState) {
        this.downState = downState;
    }

    public String getZipName() {
        return zipName;
    }

    public void setZipName(String zipName) {
        this.zipName = zipName;
    }
}
