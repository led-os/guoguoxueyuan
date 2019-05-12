package com.test720.grasshoppercollege.module.userData.youHuiJuan;

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
 * 作者：水东流 编于 2018/10/7
 */
public class YouHuiJuanBean {

    /**
     * code : 1
     * msg : 成功
     * data : [{"user_cid":"2","name":"线上课程优惠券","coupon":"100.00","full_money":"0.00","expire_time":"2019-01-01 23:59:59"},{"user_cid":"3","name":"通用优惠券","coupon":"50.00","full_money":"0.00","expire_time":"2019-06-01 23:59:59"}]
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
         * user_cid : 2
         * name : 线上课程优惠券
         * coupon : 100.00
         * full_money : 0.00
         * expire_time : 2019-01-01 23:59:59
         */

        private String user_cid;
        private String name;
        private String coupon;
        private String full_money;
        private String expire_time;

        public String getUser_cid() {
            return user_cid;
        }

        public void setUser_cid(String user_cid) {
            this.user_cid = user_cid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCoupon() {
            return coupon;
        }

        public void setCoupon(String coupon) {
            this.coupon = coupon;
        }

        public String getFull_money() {
            return full_money;
        }

        public void setFull_money(String full_money) {
            this.full_money = full_money;
        }

        public String getExpire_time() {
            return expire_time;
        }

        public void setExpire_time(String expire_time) {
            this.expire_time = expire_time;
        }
    }
}
