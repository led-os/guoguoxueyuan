package com.test720.grasshoppercollege.HttpBean;

import java.util.List;

/**
 * Created by 水东流 on 2018/7/5.
 */

public class YuWenYueDuData {

    /**
     * code : 1
     * msg : 成功
     * data : {"list":[{"question_id":"1","question_type":"1","subscript":[""],"question_content_type":"1","question":"","question_content":"换地方还是大家回复","question_content1":"","select_a":"士大夫","select_b":"电风扇电风扇","select_c":"范德萨发达","select_d":"费个个都是","answer":["A"],"answer_select":[""],"desc":""},{"question_id":"2","question_type":"2","subscript":[""],"question_content_type":"1","question":"","question_content":"换地方还是大家回复","question_content1":"","select_a":"","select_b":"","select_c":"","select_d":"","answer":["1"],"answer_select":[""],"desc":""},{"question_id":"3","question_type":"3","subscript":["10"],"question_content_type":"1","question":"","question_content":"","question_content1":"换地方还是大家回复 ____","select_a":"","select_b":"","select_c":"","select_d":"","answer":["1"],"answer_select":[""],"desc":""},{"question_id":"4","question_type":"4","subscript":["14"],"question_content_type":"1","question":"","question_content":"","question_content1":"换地方还是ssss大家回复 ____","select_a":"","select_b":"","select_c":"","select_d":"","answer":["个个股纷纷"],"answer_select":["个个股纷纷","发的","示范点是的","hfgfhsgfh"],"desc":""}]}
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
             * question_type : 1
             * subscript : [""]
             * question_content_type : 1
             * question :
             * question_content : 换地方还是大家回复
             * question_content1 :
             * select_a : 士大夫
             * select_b : 电风扇电风扇
             * select_c : 范德萨发达
             * select_d : 费个个都是
             * answer : ["A"]
             * answer_select : [""]
             * desc :
             */

            private String question_id;
            private String question_type;
            private String question_content_type;
            private String question;
            private String question_content;
            private String question_content1;
            private String select_a;
            private String select_b;
            private String select_c;
            private String select_d;
            private String desc;
            private List<String> subscript;
            private List<String> answer;
            private List<String> answer_select;

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

            public String getQuestion_content_type() {
                return question_content_type;
            }

            public void setQuestion_content_type(String question_content_type) {
                this.question_content_type = question_content_type;
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

            public String getQuestion_content1() {
                return question_content1;
            }

            public void setQuestion_content1(String question_content1) {
                this.question_content1 = question_content1;
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

            public List<String> getAnswer_select() {
                return answer_select;
            }

            public void setAnswer_select(List<String> answer_select) {
                this.answer_select = answer_select;
            }
        }
    }
}
