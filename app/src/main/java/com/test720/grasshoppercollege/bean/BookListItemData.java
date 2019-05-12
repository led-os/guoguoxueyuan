package com.test720.grasshoppercollege.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 水东流 on 2018/4/16.
 */

public class BookListItemData {
    /**
     * book_id : 2
     * book_version : 人教版
     * class : 三年级
     * book_type : 英语
     * status : 上册
     * pic : https://www.hzggedu.com/ggxy/Uploads/reading/renjiaoban/g6/s/en/g6+renjiaoban+s.jpg
     * is_pay : 0
     * is_reading : 0
     * is_add : 0
     */

    private String book_id;
    private String book_version;
    @SerializedName("class")
    private String classX;
    private String book_type;
    private String status;
    private String pic;
    private String alias;
    private int is_pay;
    private int is_reading;
    private String is_add;
    private String version_number;
    private String zip_path;

    public String getVersion_number() {
        return version_number;
    }

    public void setVersion_number(String version_number) {
        this.version_number = version_number;
    }

    public String getZip_path() {
        return zip_path;
    }

    public void setZip_path(String zip_path) {
        this.zip_path = zip_path;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getBook_version() {
        return book_version;
    }

    public void setBook_version(String book_version) {
        this.book_version = book_version;
    }

    public String getClassX() {
        return classX;
    }

    public void setClassX(String classX) {
        this.classX = classX;
    }

    public String getBook_type() {
        return book_type;
    }

    public void setBook_type(String book_type) {
        this.book_type = book_type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getIs_pay() {
        return is_pay;
    }

    public void setIs_pay(int is_pay) {
        this.is_pay = is_pay;
    }

    public int getIs_reading() {
        return is_reading;
    }

    public void setIs_reading(int is_reading) {
        this.is_reading = is_reading;
    }

    public String getIs_add() {
        return is_add;
    }

    public void setIs_add(String is_add) {
        this.is_add = is_add;
    }
}
