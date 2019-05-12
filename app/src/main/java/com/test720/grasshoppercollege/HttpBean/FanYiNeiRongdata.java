package com.test720.grasshoppercollege.HttpBean;

import java.util.List;

/**
 * Created by 水东流 on 2018/5/17.
 */

public class FanYiNeiRongdata {


    /**
     * code : 1
     * msg : 成功
     * data : {"list":[{"question_id":"1","type":"1","subscript":["0"],"full_voice":"","question_type":"1","question":"我有一个新书包","question_content":"I have a_____book","select_a":"new","select_b":"test","select_c":"tst2","select_d":"test4","answer":"A","desc":"fsdfsd","answer1":"A"},{"question_id":"3","type":"2","subscript":["7","19"],"full_voice":"","question_type":"1","question":"我有一个新书包","question_content":"I have ____ laptop ____","select_a":"","select_b":"","select_c":"","select_d":"","answer":"new","desc":"","answer1":["new"]}]}
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
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * question_id : 1
             * type : 1
             * subscript : ["0"]
             * full_voice :
             * question_type : 1
             * question : 我有一个新书包
             * question_content : I have a_____book
             * select_a : new
             * select_b : test
             * select_c : tst2
             * select_d : test4
             * answer : A
             * desc : fsdfsd
             * answer1 : A
             */

            private String question_id;
            private String type;
            private String full_voice;
            private String question_type;
            private String question;
            private String question_content;
            private String select_a;
            private String select_b;
            private String select_c;
            private String select_d;
            private String answer;
            private String corr_id;
            private String desc;
            private String status;
            private List<String> subscript;
            private List<String> answer1;

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getCorr_id() {
                return corr_id;
            }

            public void setCorr_id(String corr_id) {
                this.corr_id = corr_id;
            }

            public String getQuestion_id() {
                return question_id;
            }

            public void setQuestion_id(String question_id) {
                this.question_id = question_id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getFull_voice() {
                return full_voice;
            }

            public void setFull_voice(String full_voice) {
                this.full_voice = full_voice;
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

            public String getAnswer() {
                return answer;
            }

            public void setAnswer(String answer) {
                this.answer = answer;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public List<String> getAnswer1() {
                return answer1;
            }

            public void setAnswer1(List<String> answer1) {
                this.answer1 = answer1;
            }

            public List<String> getSubscript() {
                return subscript;
            }

            public void setSubscript(List<String> subscript) {
                this.subscript = subscript;
            }
        }
    }
}
