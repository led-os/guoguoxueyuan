package com.test720.grasshoppercollege.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

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
 * Created by 水东流 on 2018/1/15 0015.
 */

public class BaseKeBenLieBiao {


    /**
     * code : 1
     * msg : 成功
     * data : [{"name":"人教版","info":[{"book_id":"10","class":"一年级","version":"人教版","status":"上册","pic":"Uploads/explain1/201801/5a65cca25a2a9.png","experience":0},{"book_id":"9","class":"一年级","version":"人教版","status":"下册","pic":"Uploads/explain1/201801/5a65cc3e2c5b4.png","experience":0}]},{"name":"冀教版","info":[]},{"name":"外研社","info":[]},{"name":"新起点","info":[]},{"name":"苏教版","info":[]},{"name":"北师大版","info":[]}]
     */

    private int code;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * name : 人教版
         * info : [{"book_id":"10","class":"一年级","version":"人教版","status":"上册","pic":"Uploads/explain1/201801/5a65cca25a2a9.png","experience":0},{"book_id":"9","class":"一年级","version":"人教版","status":"下册","pic":"Uploads/explain1/201801/5a65cc3e2c5b4.png","experience":0}]
         */

        private String name;
        private List<InfoBean> info;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<InfoBean> getInfo() {
            return info;
        }

        public void setInfo(List<InfoBean> info) {
            this.info = info;
        }

        public static class InfoBean {
            /**
             * book_id : 10
             * class : 一年级
             * version : 人教版
             * status : 上册
             * pic : Uploads/explain1/201801/5a65cca25a2a9.png
             * experience : 0
             */

            private String book_id;
            @SerializedName("class")
            private String classX;
            private String version;
            private String status;
            private String pic;
            private String experience;

            public String getBook_id() {
                return book_id;
            }

            public void setBook_id(String book_id) {
                this.book_id = book_id;
            }

            public String getClassX() {
                return classX;
            }

            public void setClassX(String classX) {
                this.classX = classX;
            }

            public String getVersion() {
                return version;
            }

            public void setVersion(String version) {
                this.version = version;
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

            public String getExperience() {
                return experience;
            }

            public void setExperience(String experience) {
                this.experience = experience;
            }
        }
    }
}
