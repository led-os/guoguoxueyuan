package com.test720.grasshoppercollege.module.gongLue.student.bean;

import java.util.List;

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
 * 作者：水东流 编于 2018/9/6
 */
public class OnLinelistData {

    /**
     * code : 1
     * msg : 成功
     * data : [{"line_id":"1","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg","name":"未来咩学","sign_count":"1000","vip_price":"1000.00","price":"1200.00","course_number":"15"}]
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
         * line_id : 1
         * cover : https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg
         * name : 未来咩学
         * sign_count : 1000
         * vip_price : 1000.00
         * price : 1200.00
         * course_number : 15
         */

        private String line_id;
        private String cover;
        private String name;
        private String aims;
        private String sign_count;
        private String vip_price;
        private String price;
        private String course_number;

        public String getAims() {
            return aims;
        }

        public void setAims(String aims) {
            this.aims = aims;
        }

        public String getLine_id() {
            return line_id;
        }

        public void setLine_id(String line_id) {
            this.line_id = line_id;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSign_count() {
            return sign_count;
        }

        public void setSign_count(String sign_count) {
            this.sign_count = sign_count;
        }

        public String getVip_price() {
            return vip_price;
        }

        public void setVip_price(String vip_price) {
            this.vip_price = vip_price;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getCourse_number() {
            return course_number;
        }

        public void setCourse_number(String course_number) {
            this.course_number = course_number;
        }
    }
}
