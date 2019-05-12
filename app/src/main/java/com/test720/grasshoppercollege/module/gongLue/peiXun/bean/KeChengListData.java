package com.test720.grasshoppercollege.module.gongLue.peiXun.bean;

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
 * 作者：水东流 编于 2018/9/1
 */
public class KeChengListData {


    /**
     * code : 1
     * msg : 成功
     * data : [{"curr_id":"1","name":"打印水水方法","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b3217b61d8e2.jpg","sign_up_number":"0","comment_count":"0","collection_count":"0","desc":"","price":"10.00"},{"curr_id":"2","name":"打印水水方法","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b3217b61d8e2.jpg","sign_up_number":"0","comment_count":"0","collection_count":"0","desc":"","price":"12.00"},{"curr_id":"3","name":"打印水水方法","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b3217b61d8e2.jpg","sign_up_number":"0","comment_count":"0","collection_count":"0","desc":"","price":"13.00"}]
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
         * curr_id : 1
         * name : 打印水水方法
         * cover : https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b3217b61d8e2.jpg
         * sign_up_number : 0
         * comment_count : 0
         * collection_count : 0
         * desc :
         * price : 10.00
         */

        private String curr_id;
        private String name;
        private String cover;
        private String sign_up_number;
        private String comment_count;
        private String collection_count;
        private String desc;
        private String price;

        public String getCurr_id() {
            return curr_id;
        }

        public void setCurr_id(String curr_id) {
            this.curr_id = curr_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getSign_up_number() {
            return sign_up_number;
        }

        public void setSign_up_number(String sign_up_number) {
            this.sign_up_number = sign_up_number;
        }

        public String getComment_count() {
            return comment_count;
        }

        public void setComment_count(String comment_count) {
            this.comment_count = comment_count;
        }

        public String getCollection_count() {
            return collection_count;
        }

        public void setCollection_count(String collection_count) {
            this.collection_count = collection_count;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }
}
