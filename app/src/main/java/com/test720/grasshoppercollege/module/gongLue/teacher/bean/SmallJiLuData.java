package com.test720.grasshoppercollege.module.gongLue.teacher.bean;

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
 * 作者：水东流 编于 2018/11/6
 */
public class SmallJiLuData {

    /**
     * code : 1
     * msg : 成功
     * data : {"money":"50.00","info":[{"name":"奇幻拼音","price":"50.00","time":"2018-11-06"},{"name":"奇幻拼音","price":"50.00","time":"2018-11-06"},{"name":"奇幻拼音","price":"50.00","time":"2018-11-06"},{"name":"奇幻拼音","price":"50.00","time":"2018-11-06"},{"name":"奇幻拼音","price":"50.00","time":"2018-11-06"}]}
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
         * money : 50.00
         * info : [{"name":"奇幻拼音","price":"50.00","time":"2018-11-06"},{"name":"奇幻拼音","price":"50.00","time":"2018-11-06"},{"name":"奇幻拼音","price":"50.00","time":"2018-11-06"},{"name":"奇幻拼音","price":"50.00","time":"2018-11-06"},{"name":"奇幻拼音","price":"50.00","time":"2018-11-06"}]
         */

        private String money;
        private List<InfoBean> info;

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public List<InfoBean> getInfo() {
            return info;
        }

        public void setInfo(List<InfoBean> info) {
            this.info = info;
        }

        public static class InfoBean {
            /**
             * name : 奇幻拼音
             * price : 50.00
             * time : 2018-11-06
             */

            private String name;
            private String price;
            private String time;

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

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }
        }
    }
}
