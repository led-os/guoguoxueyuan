package com.test720.grasshoppercollege.module.userData.myTeam.bean;

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
 * 作者：水东流 编于 2018/10/8
 */
public class DaiLiYongJinJiLuListData {

    /**
     * code : 1
     * msg : 成功
     * data : {"list":[{"money":"8.00","time":"2018-10-07 15:47:12","inout":"2"},{"money":"1.00","time":"2018-10-07 15:46:44","inout":"2"},{"money":"8.00","time":"2018-10-07 14:19:38","inout":"1"},{"money":"1.00","time":"2018-10-07 14:19:36","inout":"1"},{"money":"1.00","time":"2018-10-07 14:19:32","inout":"1"},{"money":"1.00","time":"0000-00-00 00:00:00","inout":"1"},{"money":"1.00","time":"0000-00-00 00:00:00","inout":"1"},{"money":"0.10","time":"0000-00-00 00:00:00","inout":"1"},{"money":"0.20","time":"0000-00-00 00:00:00","inout":"1"},{"money":"1.00","time":"0000-00-00 00:00:00","inout":"1"},{"money":"3.00","time":"0000-00-00 00:00:00","inout":"1"},{"money":"2.00","time":"2018-10-05 14:20:02","inout":"1"},{"money":"1.00","time":"2018-10-07 14:19:30","inout":"1"},{"money":"10.00","time":"2018-10-07 14:18:30","inout":"1"}],"info":{"total_money":"200.00"}}
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
         * list : [{"money":"8.00","time":"2018-10-07 15:47:12","inout":"2"},{"money":"1.00","time":"2018-10-07 15:46:44","inout":"2"},{"money":"8.00","time":"2018-10-07 14:19:38","inout":"1"},{"money":"1.00","time":"2018-10-07 14:19:36","inout":"1"},{"money":"1.00","time":"2018-10-07 14:19:32","inout":"1"},{"money":"1.00","time":"0000-00-00 00:00:00","inout":"1"},{"money":"1.00","time":"0000-00-00 00:00:00","inout":"1"},{"money":"0.10","time":"0000-00-00 00:00:00","inout":"1"},{"money":"0.20","time":"0000-00-00 00:00:00","inout":"1"},{"money":"1.00","time":"0000-00-00 00:00:00","inout":"1"},{"money":"3.00","time":"0000-00-00 00:00:00","inout":"1"},{"money":"2.00","time":"2018-10-05 14:20:02","inout":"1"},{"money":"1.00","time":"2018-10-07 14:19:30","inout":"1"},{"money":"10.00","time":"2018-10-07 14:18:30","inout":"1"}]
         * info : {"total_money":"200.00"}
         */

        private InfoBean info;
        private List<ListBean> list;

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class InfoBean {
            /**
             * total_money : 200.00
             */

            private String total_money;

            public String getTotal_money() {
                return total_money;
            }

            public void setTotal_money(String total_money) {
                this.total_money = total_money;
            }
        }

        public static class ListBean {
            /**
             * money : 8.00
             * time : 2018-10-07 15:47:12
             * inout : 2
             */

            private String money;
            private String time;
            private String inout;

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getInout() {
                return inout;
            }

            public void setInout(String inout) {
                this.inout = inout;
            }
        }
    }
}
