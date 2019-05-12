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
public class DaiLiShouData {

    /**
     * code : 1
     * msg : 成功
     * data : {"agent_id":"1","province":"浙江省","city":"杭州市","area":"余杭区","money":"91.00","draw_money":"100.00","total_money":"200.00","expire_time":"2019-02-01 10:06:11","register_number":"2","level":"1","area_money":"10.00","area_number":"12","agent_sub_number":"0","area_list":[{"agent_id":"1","province":"浙江省","city":"杭州市","area":"余杭区"},{"agent_id":"2","province":"浙江省","city":"杭州市","area":"拱墅区"},{"agent_id":"3","province":"浙江省","city":"杭州市","area":"上城区"}],"list":[{"money":"8.00","time":"2018-10-07 15:47:12","inout":"2"},{"money":"1.00","time":"2018-10-07 15:46:44","inout":"2"},{"money":"8.00","time":"2018-10-07 14:19:38","inout":"1"},{"money":"1.00","time":"2018-10-07 14:19:36","inout":"1"},{"money":"1.00","time":"2018-10-07 14:19:32","inout":"1"},{"money":"1.00","time":"0000-00-00 00:00:00","inout":"1"},{"money":"1.00","time":"0000-00-00 00:00:00","inout":"1"},{"money":"0.10","time":"0000-00-00 00:00:00","inout":"1"},{"money":"0.20","time":"0000-00-00 00:00:00","inout":"1"},{"money":"1.00","time":"0000-00-00 00:00:00","inout":"1"},{"money":"3.00","time":"0000-00-00 00:00:00","inout":"1"},{"money":"2.00","time":"2018-10-05 14:20:02","inout":"1"},{"money":"1.00","time":"2018-10-07 14:19:30","inout":"1"},{"money":"10.00","time":"2018-10-07 14:18:30","inout":"1"}]}
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
         * agent_id : 1
         * province : 浙江省
         * city : 杭州市
         * area : 余杭区
         * money : 91.00
         * draw_money : 100.00
         * total_money : 200.00
         * expire_time : 2019-02-01 10:06:11
         * register_number : 2
         * level : 1
         * area_money : 10.00
         * area_number : 12
         * agent_sub_number : 0
         * area_list : [{"agent_id":"1","province":"浙江省","city":"杭州市","area":"余杭区"},{"agent_id":"2","province":"浙江省","city":"杭州市","area":"拱墅区"},{"agent_id":"3","province":"浙江省","city":"杭州市","area":"上城区"}]
         * list : [{"money":"8.00","time":"2018-10-07 15:47:12","inout":"2"},{"money":"1.00","time":"2018-10-07 15:46:44","inout":"2"},{"money":"8.00","time":"2018-10-07 14:19:38","inout":"1"},{"money":"1.00","time":"2018-10-07 14:19:36","inout":"1"},{"money":"1.00","time":"2018-10-07 14:19:32","inout":"1"},{"money":"1.00","time":"0000-00-00 00:00:00","inout":"1"},{"money":"1.00","time":"0000-00-00 00:00:00","inout":"1"},{"money":"0.10","time":"0000-00-00 00:00:00","inout":"1"},{"money":"0.20","time":"0000-00-00 00:00:00","inout":"1"},{"money":"1.00","time":"0000-00-00 00:00:00","inout":"1"},{"money":"3.00","time":"0000-00-00 00:00:00","inout":"1"},{"money":"2.00","time":"2018-10-05 14:20:02","inout":"1"},{"money":"1.00","time":"2018-10-07 14:19:30","inout":"1"},{"money":"10.00","time":"2018-10-07 14:18:30","inout":"1"}]
         */

        private String agent_id;
        private String province;
        private String city;
        private String area;
        private String money;
        private String draw_money;
        private String total_money;
        private String expire_time;
        private String register_number;
        private String level;
        private String area_money;
        private String area_number;
        private String agent_sub_number;
        private List<AreaListBean> area_list;
        private List<ListBean> list;

        public String getAgent_id() {
            return agent_id;
        }

        public void setAgent_id(String agent_id) {
            this.agent_id = agent_id;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getDraw_money() {
            return draw_money;
        }

        public void setDraw_money(String draw_money) {
            this.draw_money = draw_money;
        }

        public String getTotal_money() {
            return total_money;
        }

        public void setTotal_money(String total_money) {
            this.total_money = total_money;
        }

        public String getExpire_time() {
            return expire_time;
        }

        public void setExpire_time(String expire_time) {
            this.expire_time = expire_time;
        }

        public String getRegister_number() {
            return register_number;
        }

        public void setRegister_number(String register_number) {
            this.register_number = register_number;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getArea_money() {
            return area_money;
        }

        public void setArea_money(String area_money) {
            this.area_money = area_money;
        }

        public String getArea_number() {
            return area_number;
        }

        public void setArea_number(String area_number) {
            this.area_number = area_number;
        }

        public String getAgent_sub_number() {
            return agent_sub_number;
        }

        public void setAgent_sub_number(String agent_sub_number) {
            this.agent_sub_number = agent_sub_number;
        }

        public List<AreaListBean> getArea_list() {
            return area_list;
        }

        public void setArea_list(List<AreaListBean> area_list) {
            this.area_list = area_list;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class AreaListBean {
            /**
             * agent_id : 1
             * province : 浙江省
             * city : 杭州市
             * area : 余杭区
             */

            private String agent_id;
            private String province;
            private String city;
            private String area;

            public String getAgent_id() {
                return agent_id;
            }

            public void setAgent_id(String agent_id) {
                this.agent_id = agent_id;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
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
