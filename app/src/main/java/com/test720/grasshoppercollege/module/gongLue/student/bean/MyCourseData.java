package com.test720.grasshoppercollege.module.gongLue.student.bean;

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
 * 作者：水东流 编于 2018/11/1
 */
public class MyCourseData {

    /**
     * code : 1
     * msg : 成功
     * data : [{"class_id":"15","line_id":"1","course_name":"未来咩学","class_time":"0000-00-00","week":"","times":"","status":"0","teacher_name":"22222222","teacher_header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg"},{"class_id":"1","line_id":"1","course_name":"未来咩学","class_time":"2018-09-30","week":"周五","times":"19:00","status":"1","teacher_name":"22222222","teacher_header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg"},{"class_id":"2","line_id":"1","course_name":"未来咩学","class_time":"2018-10-01","week":"周五","times":"19:00","status":"1","teacher_name":"22222222","teacher_header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg"},{"class_id":"3","line_id":"1","course_name":"未来咩学","class_time":"2018-10-02","week":"周五","times":"19:00","status":"1","teacher_name":"22222222","teacher_header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg"},{"class_id":"4","line_id":"1","course_name":"未来咩学","class_time":"2018-10-03","week":"周五","times":"19:00","status":"1","teacher_name":"22222222","teacher_header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg"},{"class_id":"5","line_id":"1","course_name":"未来咩学","class_time":"2018-10-04","week":"周五","times":"19:00","status":"1","teacher_name":"22222222","teacher_header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg"},{"class_id":"6","line_id":"1","course_name":"未来咩学","class_time":"2018-10-05","week":"周五","times":"19:00","status":"1","teacher_name":"22222222","teacher_header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg"},{"class_id":"7","line_id":"1","course_name":"未来咩学","class_time":"2018-10-06","week":"周五","times":"19:00","status":"1","teacher_name":"22222222","teacher_header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg"},{"class_id":"8","line_id":"1","course_name":"未来咩学","class_time":"2018-10-07","week":"周五","times":"19:00","status":"1","teacher_name":"22222222","teacher_header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg"},{"class_id":"9","line_id":"1","course_name":"未来咩学","class_time":"2018-10-08","week":"周五","times":"19:00","status":"0","teacher_name":"22222222","teacher_header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg"},{"class_id":"10","line_id":"1","course_name":"未来咩学","class_time":"2018-10-09","week":"周五","times":"19:00","status":"0","teacher_name":"22222222","teacher_header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg"},{"class_id":"11","line_id":"1","course_name":"未来咩学","class_time":"2018-10-10","week":"周五","times":"19:00","status":"0","teacher_name":"22222222","teacher_header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg"},{"class_id":"12","line_id":"1","course_name":"未来咩学","class_time":"2018-10-11","week":"周五","times":"19:00","status":"0","teacher_name":"22222222","teacher_header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg"},{"class_id":"13","line_id":"1","course_name":"未来咩学","class_time":"2018-10-12","week":"周五","times":"19:00","status":"0","teacher_name":"22222222","teacher_header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg"},{"class_id":"14","line_id":"1","course_name":"未来咩学","class_time":"2018-10-13","week":"周五","times":"19:00","status":"0","teacher_name":"22222222","teacher_header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg"}]
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
         * class_id : 15
         * line_id : 1
         * course_name : 未来咩学
         * class_time : 0000-00-00
         * week :
         * times :
         * status : 0
         * teacher_name : 22222222
         * teacher_header : https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg
         */

        private String class_id;
        private String line_id;
        private String course_name;
        private String class_time;
        private String week;
        private String times;
        private String status;
        private String teacher_name;
        private String teacher_header;

        public String getClass_id() {
            return class_id;
        }

        public void setClass_id(String class_id) {
            this.class_id = class_id;
        }

        public String getLine_id() {
            return line_id;
        }

        public void setLine_id(String line_id) {
            this.line_id = line_id;
        }

        public String getCourse_name() {
            return course_name;
        }

        public void setCourse_name(String course_name) {
            this.course_name = course_name;
        }

        public String getClass_time() {
            return class_time;
        }

        public void setClass_time(String class_time) {
            this.class_time = class_time;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public String getTimes() {
            return times;
        }

        public void setTimes(String times) {
            this.times = times;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTeacher_name() {
            return teacher_name;
        }

        public void setTeacher_name(String teacher_name) {
            this.teacher_name = teacher_name;
        }

        public String getTeacher_header() {
            return teacher_header;
        }

        public void setTeacher_header(String teacher_header) {
            this.teacher_header = teacher_header;
        }
    }
}
