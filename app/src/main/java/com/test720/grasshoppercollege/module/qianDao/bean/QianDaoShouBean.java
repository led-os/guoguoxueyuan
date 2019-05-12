package com.test720.grasshoppercollege.module.qianDao.bean;

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
 * 作者：水东流 编于 2018/10/18
 */
public class QianDaoShouBean {

    /**
     * code : 1
     * msg : 成功
     * data : {"total_sign_day":"0","sign_day":"0","days":[{"task":"0","sign":"0","day":"2018-10-01"},{"task":"0","sign":"0","day":"2018-10-02"},{"task":"0","sign":"0","day":"2018-10-03"},{"task":"0","sign":"0","day":"2018-10-04"},{"task":"0","sign":"0","day":"2018-10-05"},{"task":"0","sign":"0","day":"2018-10-06"},{"task":"0","sign":"0","day":"2018-10-07"},{"task":"0","sign":"0","day":"2018-10-08"},{"task":"0","sign":"0","day":"2018-10-09"},{"task":"0","sign":"0","day":"2018-10-10"},{"task":"0","sign":"0","day":"2018-10-11"},{"task":"0","sign":"0","day":"2018-10-12"},{"task":"0","sign":"0","day":"2018-10-13"},{"task":"0","sign":"0","day":"2018-10-14"},{"task":"0","sign":"0","day":"2018-10-15"},{"task":"0","sign":"0","day":"2018-10-16"},{"task":"0","sign":"0","day":"2018-10-17"},{"task":"0","sign":"0","day":"2018-10-18"},{"task":"0","sign":"0","day":"2018-10-19"},{"task":"0","sign":"0","day":"2018-10-20"},{"task":"0","sign":"0","day":"2018-10-21"},{"task":"0","sign":"0","day":"2018-10-22"},{"task":"0","sign":"0","day":"2018-10-23"},{"task":"0","sign":"0","day":"2018-10-24"},{"task":"0","sign":"0","day":"2018-10-25"},{"task":"0","sign":"0","day":"2018-10-26"},{"task":"0","sign":"0","day":"2018-10-27"},{"task":"0","sign":"0","day":"2018-10-28"},{"task":"0","sign":"0","day":"2018-10-29"},{"task":"0","sign":"0","day":"2018-10-30"},{"task":"0","sign":"0","day":"2018-10-31"}]}
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
         * total_sign_day : 0
         * sign_day : 0
         * days : [{"task":"0","sign":"0","day":"2018-10-01"},{"task":"0","sign":"0","day":"2018-10-02"},{"task":"0","sign":"0","day":"2018-10-03"},{"task":"0","sign":"0","day":"2018-10-04"},{"task":"0","sign":"0","day":"2018-10-05"},{"task":"0","sign":"0","day":"2018-10-06"},{"task":"0","sign":"0","day":"2018-10-07"},{"task":"0","sign":"0","day":"2018-10-08"},{"task":"0","sign":"0","day":"2018-10-09"},{"task":"0","sign":"0","day":"2018-10-10"},{"task":"0","sign":"0","day":"2018-10-11"},{"task":"0","sign":"0","day":"2018-10-12"},{"task":"0","sign":"0","day":"2018-10-13"},{"task":"0","sign":"0","day":"2018-10-14"},{"task":"0","sign":"0","day":"2018-10-15"},{"task":"0","sign":"0","day":"2018-10-16"},{"task":"0","sign":"0","day":"2018-10-17"},{"task":"0","sign":"0","day":"2018-10-18"},{"task":"0","sign":"0","day":"2018-10-19"},{"task":"0","sign":"0","day":"2018-10-20"},{"task":"0","sign":"0","day":"2018-10-21"},{"task":"0","sign":"0","day":"2018-10-22"},{"task":"0","sign":"0","day":"2018-10-23"},{"task":"0","sign":"0","day":"2018-10-24"},{"task":"0","sign":"0","day":"2018-10-25"},{"task":"0","sign":"0","day":"2018-10-26"},{"task":"0","sign":"0","day":"2018-10-27"},{"task":"0","sign":"0","day":"2018-10-28"},{"task":"0","sign":"0","day":"2018-10-29"},{"task":"0","sign":"0","day":"2018-10-30"},{"task":"0","sign":"0","day":"2018-10-31"}]
         */

        private String total_sign_day;
        private String sign_day;
        private String week;
        private String year;
        private String month;
        private String sign;
        private String today_task;

        public String getToday_task() {
            return today_task;
        }

        public void setToday_task(String today_task) {
            this.today_task = today_task;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        private List<DaysBean> days;

        public String getWeeek() {
            return week;
        }

        public void setWeeek(String weeek) {
            this.week = weeek;
        }

        public String getTotal_sign_day() {
            return total_sign_day;
        }

        public void setTotal_sign_day(String total_sign_day) {
            this.total_sign_day = total_sign_day;
        }

        public String getSign_day() {
            return sign_day;
        }

        public void setSign_day(String sign_day) {
            this.sign_day = sign_day;
        }

        public List<DaysBean> getDays() {
            return days;
        }

        public void setDays(List<DaysBean> days) {
            this.days = days;
        }

        public static class DaysBean {
            /**
             * task : 0
             * sign : 0
             * day : 2018-10-01
             */

            private String task;
            private String sign;
            private String day;

            public String getTask() {
                return task;
            }

            public void setTask(String task) {
                this.task = task;
            }

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }

            public String getDay() {
                return day;
            }

            public void setDay(String day) {
                this.day = day;
            }
        }
    }
}
