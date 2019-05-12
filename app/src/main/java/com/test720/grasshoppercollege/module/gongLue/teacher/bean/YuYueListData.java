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
 * 作者：水东流 编于 2018/9/17
 */
public class YuYueListData {


    /**
     * code : 1
     * msg : 成功
     * data : {"list":[{"yuyue_id":"8","tid":"2798","uid":"2804","service_id":"11","time_length":"1","number":"1","times":"2018-11-07 09:52","why_fail":"","user_comment":"啊路","way":"2","service_name":"心理咨询","header":"","nickname":"152****7812"},{"yuyue_id":"1","tid":"2798","uid":"2804","service_id":"11","time_length":"1","number":"1","times":"2018-11-06 14:20","why_fail":"","user_comment":"","way":"1","service_name":"心理咨询","header":"","nickname":"152****7812"}],"info":{"money":"2.00","nickname":"吴186咨询老师","header":"http://www.guoguoxueyuan.com/ggxy/Uploads/teacher/header/2018-10-31/5bd9707f8c1c9.jpg"}}
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
         * list : [{"yuyue_id":"8","tid":"2798","uid":"2804","service_id":"11","time_length":"1","number":"1","times":"2018-11-07 09:52","why_fail":"","user_comment":"啊路","way":"2","service_name":"心理咨询","header":"","nickname":"152****7812"},{"yuyue_id":"1","tid":"2798","uid":"2804","service_id":"11","time_length":"1","number":"1","times":"2018-11-06 14:20","why_fail":"","user_comment":"","way":"1","service_name":"心理咨询","header":"","nickname":"152****7812"}]
         * info : {"money":"2.00","nickname":"吴186咨询老师","header":"http://www.guoguoxueyuan.com/ggxy/Uploads/teacher/header/2018-10-31/5bd9707f8c1c9.jpg"}
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
             * money : 2.00
             * nickname : 吴186咨询老师
             * header : http://www.guoguoxueyuan.com/ggxy/Uploads/teacher/header/2018-10-31/5bd9707f8c1c9.jpg
             */

            private String money;
            private String nickname;
            private String header;

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getHeader() {
                return header;
            }

            public void setHeader(String header) {
                this.header = header;
            }
        }

        public static class ListBean {
            /**
             * yuyue_id : 8
             * tid : 2798
             * uid : 2804
             * service_id : 11
             * time_length : 1
             * number : 1
             * times : 2018-11-07 09:52
             * why_fail :
             * user_comment : 啊路
             * way : 2
             * service_name : 心理咨询
             * header :
             * nickname : 152****7812
             */

            private String yuyue_id;
            private String tid;
            private String uid;
            private String service_id;
            private String time_length;
            private String number;
            private String times;
            private String why_fail;
            private String user_comment;
            private String way;
            private String service_name;
            private String header;
            private String profit;
            private String nickname;

            public String getProfit() {
                return profit;
            }

            public void setProfit(String profit) {
                this.profit = profit;
            }

            public String getYuyue_id() {
                return yuyue_id;
            }

            public void setYuyue_id(String yuyue_id) {
                this.yuyue_id = yuyue_id;
            }

            public String getTid() {
                return tid;
            }

            public void setTid(String tid) {
                this.tid = tid;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getService_id() {
                return service_id;
            }

            public void setService_id(String service_id) {
                this.service_id = service_id;
            }

            public String getTime_length() {
                return time_length;
            }

            public void setTime_length(String time_length) {
                this.time_length = time_length;
            }

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }

            public String getTimes() {
                return times;
            }

            public void setTimes(String times) {
                this.times = times;
            }

            public String getWhy_fail() {
                return why_fail;
            }

            public void setWhy_fail(String why_fail) {
                this.why_fail = why_fail;
            }

            public String getUser_comment() {
                return user_comment;
            }

            public void setUser_comment(String user_comment) {
                this.user_comment = user_comment;
            }

            public String getWay() {
                return way;
            }

            public void setWay(String way) {
                this.way = way;
            }

            public String getService_name() {
                return service_name;
            }

            public void setService_name(String service_name) {
                this.service_name = service_name;
            }

            public String getHeader() {
                return header;
            }

            public void setHeader(String header) {
                this.header = header;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }
        }
    }
}
