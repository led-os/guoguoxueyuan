package com.test720.grasshoppercollege.module.gongLue.jiaZhang.bean;

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
 * 作者：水东流 编于 2018/9/20
 */
public class YuYuelistData {


    /**
     * code : 1
     * msg : 成功
     * data : [{"yuyue_id":"2","tid":"2789","uid":"1252","service_id":"1","time_length":"60","number":"1","times":"2018-09-09 19:00","why_fail":"","user_comment":"","service_name":"情感咨询","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fdeae37e51.jpg","nickname":"水东流"}]
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
         * yuyue_id : 2
         * tid : 2789
         * uid : 1252
         * service_id : 1
         * time_length : 60
         * number : 1
         * times : 2018-09-09 19:00
         * why_fail :
         * user_comment :
         * service_name : 情感咨询
         * header : https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fdeae37e51.jpg
         * nickname : 水东流
         */

        private String yuyue_id;
        private String tid;
        private String uid;
        private String way;
        private String service_id;
        private String time_length;
        private String number;
        private String times;
        private String why_fail;
        private String user_comment;
        private String service_name;
        private String header;
        private String nickname;
        private String rong_uid;
        private String rong_tid;
        private String rong_token;

        public String getRong_tid() {
            return rong_tid;
        }

        public void setRong_tid(String rong_tid) {
            this.rong_tid = rong_tid;
        }

        public String getWay() {
            return way;
        }

        public void setWay(String way) {
            this.way = way;
        }

        public String getRong_uid() {
            return rong_uid;
        }

        public void setRong_uid(String rong_uid) {
            this.rong_uid = rong_uid;
        }

        public String getRong_token() {
            return rong_token;
        }

        public void setRong_token(String rong_token) {
            this.rong_token = rong_token;
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
