package com.greendao.gen.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

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
 * 作者：水东流 编于 2018/12/22
 */
@Entity
public class ZipFileData {
    @Id
    //必须使用包装类对象类型Long，而非基本类型long
    private Long id;

    @NotNull
    private String fileName;//对应的文件名称，比如，三年级上册
    private String moudle;//对应的文件模块，比如，点读，磁带，宝宝乐园等等
    private String version;//文件版本


    private double fileSize;//文件大小
    private String zipName;//目录名
    @Generated(hash = 142575018)
    public ZipFileData(Long id, @NotNull String fileName, String moudle,
            String version, double fileSize, String zipName) {
        this.id = id;
        this.fileName = fileName;
        this.moudle = moudle;
        this.version = version;
        this.fileSize = fileSize;
        this.zipName = zipName;
    }
    @Generated(hash = 1828370777)
    public ZipFileData() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFileName() {
        return this.fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getMoudle() {
        return this.moudle;
    }
    public void setMoudle(String moudle) {
        this.moudle = moudle;
    }
    public String getVersion() {
        return this.version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
    public double getFileSize() {
        return this.fileSize;
    }
    public void setFileSize(double fileSize) {
        this.fileSize = fileSize;
    }
    public String getZipName() {
        return this.zipName;
    }
    public void setZipName(String zipName) {
        this.zipName = zipName;
    }
  


}
