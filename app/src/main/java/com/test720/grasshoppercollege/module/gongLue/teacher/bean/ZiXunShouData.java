package com.test720.grasshoppercollege.module.gongLue.teacher.bean;

import com.test720.grasshoppercollege.untils.lunBo.Banner;

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
public class ZiXunShouData {

    /**
     * code : 1
     * msg : 成功
     * data : {"banner":[{"banner_id":"23","location_type":"2","location_link":"https://www.hzggedu.com/ggxydemo/small.php/Mall/bannerWeb/id/5","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b3217b61d8e2.jpg","name":"1","g_id":"0"},{"banner_id":"24","location_type":"2","location_link":"https://www.hzggedu.com/ggxydemo/small.php/Mall/bannerWeb/id/5","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b3217b61d8e2.jpg","name":"2","g_id":"0"},{"banner_id":"25","location_type":"2","location_link":"https://www.hzggedu.com/ggxydemo/small.php/Mall/bannerWeb/id/5","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b3217b61d8e2.jpg","name":"3","g_id":"0"}],"info":{"consulting_line":"0","answer_line":"1","consultant_money":"0.00"},"yuyue":[{"yuyue_id":"7","tid":"1252","uid":"2789","service_id":"1","time_length":"60","number":"1","times":"2018-09-09 19:00","why_fail":"","user_comment":"","service_name":"情感咨询","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fdeae37e51.jpg","nickname":"水东流"}]}
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
         * banner : [{"banner_id":"23","location_type":"2","location_link":"https://www.hzggedu.com/ggxydemo/small.php/Mall/bannerWeb/id/5","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b3217b61d8e2.jpg","name":"1","g_id":"0"},{"banner_id":"24","location_type":"2","location_link":"https://www.hzggedu.com/ggxydemo/small.php/Mall/bannerWeb/id/5","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b3217b61d8e2.jpg","name":"2","g_id":"0"},{"banner_id":"25","location_type":"2","location_link":"https://www.hzggedu.com/ggxydemo/small.php/Mall/bannerWeb/id/5","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b3217b61d8e2.jpg","name":"3","g_id":"0"}]
         * info : {"consulting_line":"0","answer_line":"1","consultant_money":"0.00"}
         * yuyue : [{"yuyue_id":"7","tid":"1252","uid":"2789","service_id":"1","time_length":"60","number":"1","times":"2018-09-09 19:00","why_fail":"","user_comment":"","service_name":"情感咨询","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fdeae37e51.jpg","nickname":"水东流"}]
         */

        private InfoBean info;
        private List<Banner> banner;
        private List<YuyueBean> yuyue;
        private List<AnswerBean> answer;

        public List<AnswerBean> getAnswer() {
            return answer;
        }

        public void setAnswer(List<AnswerBean> answer) {
            this.answer = answer;
        }

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public List<Banner> getBanner() {
            return banner;
        }

        public void setBanner(List<Banner> banner) {
            this.banner = banner;
        }

        public List<YuyueBean> getYuyue() {
            return yuyue;
        }

        public void setYuyue(List<YuyueBean> yuyue) {
            this.yuyue = yuyue;
        }

        public static class InfoBean {
            /**
             * consulting_line : 0
             * consultant_money : 0.00
             */

            private String consulting_line;
            private String consultant_money;

            public String getConsulting_line() {
                return consulting_line;
            }

            public void setConsulting_line(String consulting_line) {
                this.consulting_line = consulting_line;
            }

            public String getConsultant_money() {
                return consultant_money;
            }

            public void setConsultant_money(String consultant_money) {
                this.consultant_money = consultant_money;
            }
        }


        public static class YuyueBean {
            /**
             * yuyue_id : 7
             * tid : 1252
             * uid : 2789
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
            private String service_id;
            private String time_length;
            private String number;
            private String times;
            private String why_fail;
            private String user_comment;
            private String service_name;
            private String header;
            private String nickname;

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

        public static class AnswerBean {

            /**
             * answer_id : 19
             * tid : 2798
             * uid : 2804
             * question : 巴黎
             * question_voice : http://www.guoguoxueyuan.com/ggxy/Uploads/consulting_voice/user/2018-11-06/5be13fa9e5f3f.aac
             * question_voice_time : 8
             * answer : 111
             * answer_voice :
             * answer_voice_time : 0
             * status : 0
             * price : 0.00
             * why_fail :
             * answer_time : 0000-00-00 00:00:00
             * question_time : 2018-11-06 15:15:56
             * is_self : 0
             * user_header :
             * user_nickname : 152****7812
             * teacher_header : 吴186咨询老师
             * teacher_nickname : 吴186咨询老师
             */

            private String answer_id;
            private String tid;
            private String uid;
            private String question;
            private String question_voice;
            private String question_voice_time;
            private String answer;
            private String answer_voice;
            private String answer_voice_time;
            private String status;
            private String price;
            private String why_fail;
            private String answer_time;
            private String question_time;
            private String is_self;
            private String user_header;
            private String user_nickname;
            private String teacher_header;
            private String teacher_nickname;

            public String getAnswer_id() {
                return answer_id;
            }

            public void setAnswer_id(String answer_id) {
                this.answer_id = answer_id;
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

            public String getQuestion() {
                return question;
            }

            public void setQuestion(String question) {
                this.question = question;
            }

            public String getQuestion_voice() {
                return question_voice;
            }

            public void setQuestion_voice(String question_voice) {
                this.question_voice = question_voice;
            }

            public String getQuestion_voice_time() {
                return question_voice_time;
            }

            public void setQuestion_voice_time(String question_voice_time) {
                this.question_voice_time = question_voice_time;
            }

            public String getAnswer() {
                return answer;
            }

            public void setAnswer(String answer) {
                this.answer = answer;
            }

            public String getAnswer_voice() {
                return answer_voice;
            }

            public void setAnswer_voice(String answer_voice) {
                this.answer_voice = answer_voice;
            }

            public String getAnswer_voice_time() {
                return answer_voice_time;
            }

            public void setAnswer_voice_time(String answer_voice_time) {
                this.answer_voice_time = answer_voice_time;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getWhy_fail() {
                return why_fail;
            }

            public void setWhy_fail(String why_fail) {
                this.why_fail = why_fail;
            }

            public String getAnswer_time() {
                return answer_time;
            }

            public void setAnswer_time(String answer_time) {
                this.answer_time = answer_time;
            }

            public String getQuestion_time() {
                return question_time;
            }

            public void setQuestion_time(String question_time) {
                this.question_time = question_time;
            }

            public String getIs_self() {
                return is_self;
            }

            public void setIs_self(String is_self) {
                this.is_self = is_self;
            }

            public String getUser_header() {
                return user_header;
            }

            public void setUser_header(String user_header) {
                this.user_header = user_header;
            }

            public String getUser_nickname() {
                return user_nickname;
            }

            public void setUser_nickname(String user_nickname) {
                this.user_nickname = user_nickname;
            }

            public String getTeacher_header() {
                return teacher_header;
            }

            public void setTeacher_header(String teacher_header) {
                this.teacher_header = teacher_header;
            }

            public String getTeacher_nickname() {
                return teacher_nickname;
            }

            public void setTeacher_nickname(String teacher_nickname) {
                this.teacher_nickname = teacher_nickname;
            }
        }
    }
}
