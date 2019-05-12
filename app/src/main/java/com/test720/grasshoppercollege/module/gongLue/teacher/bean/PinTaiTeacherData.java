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
 * 作者：水东流 编于 2018/9/14
 */
public class PinTaiTeacherData {

    /**
     * code : 1
     * msg : 成功
     * data : {"info":{"header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg","nickname":"虚空恶犬二千","money":"997.00"},"course":[{"line_id":"1","course_name":"未来咩学","course":[{"course_id":"1","name":"未来咩学1","times":"","why_fail":""},{"course_id":"2","name":"未来咩学2","times":"","why_fail":""},{"course_id":"3","name":"未来咩学3","times":"","why_fail":""}]}]}
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
         * info : {"header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg","nickname":"虚空恶犬二千","money":"997.00"}
         * course : [{"line_id":"1","course_name":"未来咩学","course":[{"course_id":"1","name":"未来咩学1","times":"","why_fail":""},{"course_id":"2","name":"未来咩学2","times":"","why_fail":""},{"course_id":"3","name":"未来咩学3","times":"","why_fail":""}]}]
         */

        private InfoBean info;
        private List<CourseBeanX> course;

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public List<CourseBeanX> getCourse() {
            return course;
        }

        public void setCourse(List<CourseBeanX> course) {
            this.course = course;
        }

        public static class InfoBean {
            /**
             * header : https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg
             * nickname : 虚空恶犬二千
             * money : 997.00
             */

            private String header;
            private String nickname;
            private String money;
            private String micro_money;

            public String getMicro_money() {
                return micro_money;
            }

            public void setMicro_money(String micro_money) {
                this.micro_money = micro_money;
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

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }
        }

        public static class CourseBeanX {
            /**
             * line_id : 1
             * course_name : 未来咩学
             * course : [{"course_id":"1","name":"未来咩学1","times":"","why_fail":""},{"course_id":"2","name":"未来咩学2","times":"","why_fail":""},{"course_id":"3","name":"未来咩学3","times":"","why_fail":""}]
             */

            private String line_id;
            private String course_name;
            private List<CourseBean> course;

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

            public List<CourseBean> getCourse() {
                return course;
            }

            public void setCourse(List<CourseBean> course) {
                this.course = course;
            }

            public static class CourseBean {
                /**
                 * course_id : 1
                 * name : 未来咩学1
                 * times :
                 * why_fail :
                 */

                private String course_id;
                private String name;
                private String times;
                private String why_fail;

                public String getCourse_id() {
                    return course_id;
                }

                public void setCourse_id(String course_id) {
                    this.course_id = course_id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
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
            }
        }
    }
}
