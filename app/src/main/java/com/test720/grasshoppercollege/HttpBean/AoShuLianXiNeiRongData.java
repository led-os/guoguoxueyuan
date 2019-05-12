package com.test720.grasshoppercollege.HttpBean;

import java.util.List;

/**
 * Created by 水东流 on 2018/4/26.
 */

public class AoShuLianXiNeiRongData {

    /**
     * code : 1
     * msg : 成功
     * data : [{"question_id":"4","question_type":"2","question":"","question_content":"this ____","subscript":["5"],"select_a":"","select_b":"","select_c":"","select_d":"","answer":["is"],"desc":"sdfs","type":"","corr_id":"0","status":"0"},{"question_id":"1","question_type":"1","question":"","question_content":"this is this a ndh nka?","subscript":[""],"select_a":"asds","select_b":"dfdf","select_c":"gsdf","select_d":"rwer","answer":["A"],"desc":"ffsdfsdfs","type":"","corr_id":"0","status":"0"},{"question_id":"2","question_type":"1","question":"","question_content":"snhwmd is this a ndh nka?","subscript":[""],"select_a":"asds","select_b":"dfdf","select_c":"gsdf","select_d":"rwer","answer":["A"],"desc":"ffdfdfsdddddddd","type":"","corr_id":"0","status":"0"},{"question_id":"3","question_type":"1","question":"","question_content":"ffsdfdfsdf","subscript":[""],"select_a":"asds","select_b":"dfdf","select_c":"gsdf","select_d":"rwer","answer":["A"],"desc":"ffdf","type":"","corr_id":"0","status":"0"}]
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
         * question_id : 4
         * question_type : 2
         * question :
         * question_content : this ____
         * subscript : ["5"]
         * select_a :
         * select_b :
         * select_c :
         * select_d :
         * answer : ["is"]
         * desc : sdfs
         * type :
         * corr_id : 0
         * status : 0
         */

        private String question_id;
        private String question_type;
        private String question;
        private String question_content;
        private String select_a;
        private String select_b;
        private String select_c;
        private String select_d;
        private String desc;
        private String type;
        private String corr_id;
        private String status;
        private List<String> subscript;
        private List<String> answer;

        public String getQuestion_id() {
            return question_id;
        }

        public void setQuestion_id(String question_id) {
            this.question_id = question_id;
        }

        public String getQuestion_type() {
            return question_type;
        }

        public void setQuestion_type(String question_type) {
            this.question_type = question_type;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getQuestion_content() {
            return question_content;
        }

        public void setQuestion_content(String question_content) {
            this.question_content = question_content;
        }

        public String getSelect_a() {
            return select_a;
        }

        public void setSelect_a(String select_a) {
            this.select_a = select_a;
        }

        public String getSelect_b() {
            return select_b;
        }

        public void setSelect_b(String select_b) {
            this.select_b = select_b;
        }

        public String getSelect_c() {
            return select_c;
        }

        public void setSelect_c(String select_c) {
            this.select_c = select_c;
        }

        public String getSelect_d() {
            return select_d;
        }

        public void setSelect_d(String select_d) {
            this.select_d = select_d;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCorr_id() {
            return corr_id;
        }

        public void setCorr_id(String corr_id) {
            this.corr_id = corr_id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<String> getSubscript() {
            return subscript;
        }

        public void setSubscript(List<String> subscript) {
            this.subscript = subscript;
        }

        public List<String> getAnswer() {
            return answer;
        }

        public void setAnswer(List<String> answer) {
            this.answer = answer;
        }
    }
}
