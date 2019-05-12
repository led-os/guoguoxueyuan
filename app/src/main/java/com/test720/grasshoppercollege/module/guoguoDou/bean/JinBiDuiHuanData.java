package com.test720.grasshoppercollege.module.guoguoDou.bean;

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
 * 作者：水东流 编于 2018/12/1
 */
public class JinBiDuiHuanData {

    /**
     * code : 1
     * msg : 成功
     * data : {"vip_exchange":[{"exchange_id":"5","gold":"100","points":"1"},{"exchange_id":"6","gold":"200","points":"2"},{"exchange_id":"7","gold":"300","points":"3"},{"exchange_id":"8","gold":"400","points":"4"}],"exchange":[{"exchange_id":"1","gold":"10","points":"1"},{"exchange_id":"2","gold":"20","points":"2"},{"exchange_id":"3","gold":"30","points":"3"},{"exchange_id":"4","gold":"40","points":"4"}],"vip":0}
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
         * vip_exchange : [{"exchange_id":"5","gold":"100","points":"1"},{"exchange_id":"6","gold":"200","points":"2"},{"exchange_id":"7","gold":"300","points":"3"},{"exchange_id":"8","gold":"400","points":"4"}]
         * exchange : [{"exchange_id":"1","gold":"10","points":"1"},{"exchange_id":"2","gold":"20","points":"2"},{"exchange_id":"3","gold":"30","points":"3"},{"exchange_id":"4","gold":"40","points":"4"}]
         * vip : 0
         */

        private int vip;
        private List<VipExchangeBean> vip_exchange;
        private List<ExchangeBean> exchange;

        public int getVip() {
            return vip;
        }

        public void setVip(int vip) {
            this.vip = vip;
        }

        public List<VipExchangeBean> getVip_exchange() {
            return vip_exchange;
        }

        public void setVip_exchange(List<VipExchangeBean> vip_exchange) {
            this.vip_exchange = vip_exchange;
        }

        public List<ExchangeBean> getExchange() {
            return exchange;
        }

        public void setExchange(List<ExchangeBean> exchange) {
            this.exchange = exchange;
        }

        public static class VipExchangeBean {
            /**
             * exchange_id : 5
             * gold : 100
             * points : 1
             */

            private String exchange_id;
            private String gold;
            private String points;

            public String getExchange_id() {
                return exchange_id;
            }

            public void setExchange_id(String exchange_id) {
                this.exchange_id = exchange_id;
            }

            public String getGold() {
                return gold;
            }

            public void setGold(String gold) {
                this.gold = gold;
            }

            public String getPoints() {
                return points;
            }

            public void setPoints(String points) {
                this.points = points;
            }
        }

        public static class ExchangeBean {
            /**
             * exchange_id : 1
             * gold : 10
             * points : 1
             */

            private String exchange_id;
            private String gold;
            private String points;

            public String getExchange_id() {
                return exchange_id;
            }

            public void setExchange_id(String exchange_id) {
                this.exchange_id = exchange_id;
            }

            public String getGold() {
                return gold;
            }

            public void setGold(String gold) {
                this.gold = gold;
            }

            public String getPoints() {
                return points;
            }

            public void setPoints(String points) {
                this.points = points;
            }
        }
    }
}
