package com.test720.grasshoppercollege.module.gongLue.teacherAndConsultant.bean;

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
 * 作者：水东流 编于 2018/9/21
 */
public class TimeData {

    /**
     * code : 1
     * msg : 成功
     * data : [{"day":"2018-09-22","week":"星期六","time":[{"time":"08:00","can":"0"},{"time":"08:30","can":"0"},{"time":"09:00","can":"0"},{"time":"09:30","can":"0"},{"time":"10:00","can":"0"},{"time":"10:30","can":"0"},{"time":"11:00","can":"0"},{"time":"11:30","can":"0"},{"time":"12:00","can":"0"},{"time":"12:30","can":"0"},{"time":"13:00","can":"0"},{"time":"13:30","can":"0"},{"time":"14:00","can":"0"},{"time":"14:30","can":"0"},{"time":"15:00","can":"0"},{"time":"15:30","can":"0"},{"time":"16:00","can":"0"},{"time":"16:30","can":"0"},{"time":"17:00","can":"0"},{"time":"17:30","can":"0"}]},{"day":"2018-09-23","week":"星期六","time":[{"time":"08:00","can":"0"},{"time":"08:30","can":"0"},{"time":"09:00","can":"0"},{"time":"09:30","can":"0"},{"time":"10:00","can":"0"},{"time":"10:30","can":"0"},{"time":"11:00","can":"0"},{"time":"11:30","can":"0"},{"time":"12:00","can":"0"},{"time":"12:30","can":"0"},{"time":"13:00","can":"0"},{"time":"13:30","can":"0"},{"time":"14:00","can":"0"},{"time":"14:30","can":"0"},{"time":"15:00","can":"0"},{"time":"15:30","can":"0"},{"time":"16:00","can":"0"},{"time":"16:30","can":"0"},{"time":"17:00","can":"0"},{"time":"17:30","can":"0"}]},{"day":"2018-09-24","week":"星期六","time":[{"time":"08:00","can":"0"},{"time":"08:30","can":"0"},{"time":"09:00","can":"0"},{"time":"09:30","can":"0"},{"time":"10:00","can":"0"},{"time":"10:30","can":"0"},{"time":"11:00","can":"0"},{"time":"11:30","can":"0"},{"time":"12:00","can":"0"},{"time":"12:30","can":"0"},{"time":"13:00","can":"0"},{"time":"13:30","can":"0"},{"time":"14:00","can":"0"},{"time":"14:30","can":"0"},{"time":"15:00","can":"0"},{"time":"15:30","can":"0"},{"time":"16:00","can":"0"},{"time":"16:30","can":"0"},{"time":"17:00","can":"0"},{"time":"17:30","can":"0"}]},{"day":"2018-09-25","week":"星期六","time":[{"time":"08:00","can":"0"},{"time":"08:30","can":"0"},{"time":"09:00","can":"0"},{"time":"09:30","can":"0"},{"time":"10:00","can":"0"},{"time":"10:30","can":"0"},{"time":"11:00","can":"0"},{"time":"11:30","can":"0"},{"time":"12:00","can":"0"},{"time":"12:30","can":"0"},{"time":"13:00","can":"0"},{"time":"13:30","can":"0"},{"time":"14:00","can":"0"},{"time":"14:30","can":"0"},{"time":"15:00","can":"0"},{"time":"15:30","can":"0"},{"time":"16:00","can":"0"},{"time":"16:30","can":"0"},{"time":"17:00","can":"0"},{"time":"17:30","can":"0"}]},{"day":"2018-09-26","week":"星期六","time":[{"time":"08:00","can":"0"},{"time":"08:30","can":"0"},{"time":"09:00","can":"0"},{"time":"09:30","can":"0"},{"time":"10:00","can":"0"},{"time":"10:30","can":"0"},{"time":"11:00","can":"0"},{"time":"11:30","can":"0"},{"time":"12:00","can":"0"},{"time":"12:30","can":"0"},{"time":"13:00","can":"0"},{"time":"13:30","can":"0"},{"time":"14:00","can":"0"},{"time":"14:30","can":"0"},{"time":"15:00","can":"0"},{"time":"15:30","can":"0"},{"time":"16:00","can":"0"},{"time":"16:30","can":"0"},{"time":"17:00","can":"0"},{"time":"17:30","can":"0"}]},{"day":"2018-09-27","week":"星期六","time":[{"time":"08:00","can":"0"},{"time":"08:30","can":"0"},{"time":"09:00","can":"0"},{"time":"09:30","can":"0"},{"time":"10:00","can":"0"},{"time":"10:30","can":"0"},{"time":"11:00","can":"0"},{"time":"11:30","can":"0"},{"time":"12:00","can":"0"},{"time":"12:30","can":"0"},{"time":"13:00","can":"0"},{"time":"13:30","can":"0"},{"time":"14:00","can":"0"},{"time":"14:30","can":"0"},{"time":"15:00","can":"0"},{"time":"15:30","can":"0"},{"time":"16:00","can":"0"},{"time":"16:30","can":"0"},{"time":"17:00","can":"0"},{"time":"17:30","can":"0"}]}]
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
         * day : 2018-09-22
         * week : 星期六
         * time : [{"time":"08:00","can":"0"},{"time":"08:30","can":"0"},{"time":"09:00","can":"0"},{"time":"09:30","can":"0"},{"time":"10:00","can":"0"},{"time":"10:30","can":"0"},{"time":"11:00","can":"0"},{"time":"11:30","can":"0"},{"time":"12:00","can":"0"},{"time":"12:30","can":"0"},{"time":"13:00","can":"0"},{"time":"13:30","can":"0"},{"time":"14:00","can":"0"},{"time":"14:30","can":"0"},{"time":"15:00","can":"0"},{"time":"15:30","can":"0"},{"time":"16:00","can":"0"},{"time":"16:30","can":"0"},{"time":"17:00","can":"0"},{"time":"17:30","can":"0"}]
         */

        private String day;
        private String week;
        private List<TimeBean> time;

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public List<TimeBean> getTime() {
            return time;
        }

        public void setTime(List<TimeBean> time) {
            this.time = time;
        }

        public static class TimeBean {
            /**
             * time : 08:00
             * can : 0
             */

            private String time;
            private String can;

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getCan() {
                return can;
            }

            public void setCan(String can) {
                this.can = can;
            }
        }
    }
}
