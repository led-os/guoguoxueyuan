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

public class BaseNianJiLiebiao {


    /**
     * code : 1
     * msg : 成功
     * data : {"list":[{"class":"一年级","pic":"Uploads/class/1.jpg"},{"class":"二年级","pic":"Uploads/class/2.jpg"},{"class":"三年级","pic":"Uploads/class/3.jpg"},{"class":"四年级","pic":"Uploads/class/4.jpg"},{"class":"五年级","pic":"Uploads/class/5.jpg"},{"class":"六年级","pic":"Uploads/class/6.jpg"}],"url":"Uploads/class/7.jpg"}
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
         * list : [{"class":"一年级","pic":"Uploads/class/1.jpg"},{"class":"二年级","pic":"Uploads/class/2.jpg"},{"class":"三年级","pic":"Uploads/class/3.jpg"},{"class":"四年级","pic":"Uploads/class/4.jpg"},{"class":"五年级","pic":"Uploads/class/5.jpg"},{"class":"六年级","pic":"Uploads/class/6.jpg"}]
         * url : Uploads/class/7.jpg
         */

        private String url;
        private List<ListBean> list;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * class : 一年级
             * pic : Uploads/class/1.jpg
             */

            @SerializedName("class")
            private String classX;
            private String pic;

            public String getClassX() {
                return classX;
            }

            public void setClassX(String classX) {
                this.classX = classX;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }
        }
    }
}
