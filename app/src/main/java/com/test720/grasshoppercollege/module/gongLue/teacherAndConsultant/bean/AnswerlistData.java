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
 * 作者：水东流 编于 2018/9/27
 */
public class AnswerlistData {

    /**
     * code : 1
     * msg : 成功
     * data : [{"answer_id":"7","tid":"1252","uid":"2785","question":"我怎么了","question_voice":"","question_voice_time":"0","answer":"多大的","answer_voice":"http://www.guoguoxueyuan.com/ggxy/Uploads/consulting_voice/teacher/2018-09-20/5ba33f4581f61.AAC","answer_voice_time":"0","status":"2","price":"2.00","why_fail":"","answer_time":"2018-09-20 14:33:41","question_time":"2018-09-06 17:29:50","is_self":"0","user_header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg","user_nickname":"158****7306","teacher_header":"虚空恶犬二千","teacher_nickname":"虚空恶犬二千"},{"answer_id":"8","tid":"1252","uid":"2785","question":"我怎么了123","question_voice":"","question_voice_time":"0","answer":"","answer_voice":"","answer_voice_time":"0","status":"0","price":"3.00","why_fail":"","answer_time":"2018-09-06 17:30:08","question_time":"2018-09-06 17:29:50","is_self":"0","user_header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg","user_nickname":"158****7306","teacher_header":"虚空恶犬二千","teacher_nickname":"虚空恶犬二千"},{"answer_id":"9","tid":"1252","uid":"2785","question":"我怎么了123567","question_voice":"","question_voice_time":"0","answer":"方法","answer_voice":"","answer_voice_time":"0","status":"1","price":"2.00","why_fail":"","answer_time":"2018-09-13 14:31:26","question_time":"2018-09-13 14:31:08","is_self":"0","user_header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg","user_nickname":"158****7306","teacher_header":"虚空恶犬二千","teacher_nickname":"虚空恶犬二千"},{"answer_id":"10","tid":"1252","uid":"2785","question":"1我怎么了123111111111111","question_voice":"","question_voice_time":"0","answer":"滚滚滚","answer_voice":"","answer_voice_time":"0","status":"2","price":"2.00","why_fail":"","answer_time":"2018-09-13 14:33:05","question_time":"2018-09-13 14:33:16","is_self":"0","user_header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg","user_nickname":"158****7306","teacher_header":"虚空恶犬二千","teacher_nickname":"虚空恶犬二千"},{"answer_id":"11","tid":"1252","uid":"2785","question":"反反复复方法付付","question_voice":"","question_voice_time":"0","answer":"水水水水","answer_voice":"","answer_voice_time":"0","status":"4","price":"2.00","why_fail":"反反复复是是是456","answer_time":"2018-09-13 14:33:07","question_time":"2018-09-13 14:33:19","is_self":"0","user_header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg","user_nickname":"158****7306","teacher_header":"虚空恶犬二千","teacher_nickname":"虚空恶犬二千"},{"answer_id":"12","tid":"1252","uid":"2785","question":"的点点滴滴多多多多多多多多多多多多多多多多多多多多多多多","question_voice":"","question_voice_time":"0","answer":"灌灌灌灌","answer_voice":"","answer_voice_time":"0","status":"5","price":"0.00","why_fail":"","answer_time":"2018-09-13 14:33:11","question_time":"2018-09-13 14:33:22","is_self":"0","user_header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg","user_nickname":"158****7306","teacher_header":"虚空恶犬二千","teacher_nickname":"虚空恶犬二千"}]
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
         * answer_id : 7
         * tid : 1252
         * uid : 2785
         * question : 我怎么了
         * question_voice :
         * question_voice_time : 0
         * answer : 多大的
         * answer_voice : http://www.guoguoxueyuan.com/ggxy/Uploads/consulting_voice/teacher/2018-09-20/5ba33f4581f61.AAC
         * answer_voice_time : 0
         * status : 2
         * price : 2.00
         * why_fail :
         * answer_time : 2018-09-20 14:33:41
         * question_time : 2018-09-06 17:29:50
         * is_self : 0
         * user_header : https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg
         * user_nickname : 158****7306
         * teacher_header : 虚空恶犬二千
         * teacher_nickname : 虚空恶犬二千
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
