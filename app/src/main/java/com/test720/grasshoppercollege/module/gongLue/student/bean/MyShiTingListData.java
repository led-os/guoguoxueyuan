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
 * 作者：水东流 编于 2018/9/18
 */
public class MyShiTingListData {


    /**
     * code : 1
     * msg : 成功
     * data : [{"micro_class_id":"1","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg","name":"第一课 哈哈哈","price":"2.00","subscribe_count":"100"}]
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
         * micro_class_id : 1
         * pic : https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg
         * name : 第一课 哈哈哈
         * price : 2.00
         * subscribe_count : 100
         */

        private String micro_class_id;
        private String pic;
        private String name;
        private String price;
        private String expiry_time;
        private String subscribe_count;

        public String getExpiry_time() {
            return expiry_time;
        }

        public void setExpiry_time(String expiry_time) {
            this.expiry_time = expiry_time;
        }

        public String getMicro_class_id() {
            return micro_class_id;
        }

        public void setMicro_class_id(String micro_class_id) {
            this.micro_class_id = micro_class_id;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getSubscribe_count() {
            return subscribe_count;
        }

        public void setSubscribe_count(String subscribe_count) {
            this.subscribe_count = subscribe_count;
        }
    }
}
