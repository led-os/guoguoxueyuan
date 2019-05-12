package com.test720.grasshoppercollege.HttpBean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 水东流 on 2018/4/12.
 */

public class DianDuMyBookData {


    /**
     * code : 1
     * msg : 成功
     * data : {"list":[{"book_id":"11","book_version":"人教版","class":"三年级","status":"下册","pic":"https://www.hzggedu.com/ggxy/Uploads/reading_small/2018-04-12/5acf1988e4588.png","is_pay":0,"is_reading":0}],"experience_time":"10","points":"5"}
     */

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * list : [{"book_id":"11","book_version":"人教版","class":"三年级","status":"下册","pic":"https://www.hzggedu.com/ggxy/Uploads/reading_small/2018-04-12/5acf1988e4588.png","is_pay":0,"is_reading":0}]
         * experience_time : 10
         * points : 5
         */

        private String experience_time;
        private String points;
        private List<ListBean> list;

        public String getExperience_time() {
            return experience_time;
        }

        public void setExperience_time(String experience_time) {
            this.experience_time = experience_time;
        }

        public String getPoints() {
            return points;
        }

        public void setPoints(String points) {
            this.points = points;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * book_id : 11
             * book_version : 人教版
             * class : 三年级
             * status : 下册
             * pic : https://www.hzggedu.com/ggxy/Uploads/reading_small/2018-04-12/5acf1988e4588.png
             * is_pay : 0
             * is_reading : 0
             */

            private String book_id;
            private String book_version;
            @SerializedName("class")
            private String classX;
            private String status;
            private String pic;
            private String zip_path;
            private String alias;//资源包本地地址目录
            private String version_number;//资源包版本号
            private int is_pay;
            private int is_reading;

            public String getAlias() {
                return alias;
            }

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
        }
    }
}
