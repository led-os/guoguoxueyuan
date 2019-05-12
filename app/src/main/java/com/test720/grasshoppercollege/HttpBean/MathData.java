package com.test720.grasshoppercollege.HttpBean;

import java.util.List;

/**
 * Created by 水东流 on 2018/7/2.
 */

public class MathData {

    /**
     * code : 1
     * msg : 成功
     * data : [{"question_id":"1","question_type":"1","subscript":[""],"question_content_type":"1","sort":"0","question":"更具还是打开记事本的分别是的基本恨不得不舍得","question_content":"说实话还是说说","question_content1":"","select_a":"事实上打算","select_b":"士大夫似的","select_c":"示范点的","select_d":"发士大夫","answer":["A"],"desc":"胜多负少的AAAA "},{"question_id":"4","question_type":"2","subscript":[""],"question_content_type":"1","sort":"0","question":"ddddddccc","question_content":"sd是的方法实话dsdasdas","question_content1":"","select_a":"","select_b":"","select_c":"","select_d":"","answer":["0"],"desc":"胜多负少的AAAA sss"},{"question_id":"3","question_type":"2","subscript":[""],"question_content_type":"1","sort":"0","question":"ddddddcccssss","question_content":"ee实话dsxcxcvxd","question_content1":"","select_a":"","select_b":"","select_c":"","select_d":"","answer":["1"],"desc":"胜多负少的AAAA sss"},{"question_id":"2","question_type":"1","subscript":[""],"question_content_type":"1","sort":"0","question":"dddddd","question_content":"说实话dsdasdas","question_content1":"","select_a":"事实上d","select_b":"士dd夫似的","select_c":"示范d的","select_d":"ddsds士大夫","answer":["B"],"desc":"胜多负少的AAAA sss"},{"question_id":"6","question_type":"3","subscript":["8","24"],"question_content_type":"1","sort":"0","question":"算一算，舔一舔","question_content":"小狗：我到家的时间是5:59:50,小兔：我到家是5：05：59。谁先到家？","question_content1":"小狗先到家，早 ____ 秒\\n小兔到家，再过 ____ 秒时6：00","select_a":"","select_b":"","select_c":"","select_d":"","answer":["8","2"],"desc":"胜多负少的AAAA sss"}]
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
         * question_id : 1
         * question_type : 1
         * subscript : [""]
         * question_content_type : 1
         * sort : 0
         * question : 更具还是打开记事本的分别是的基本恨不得不舍得
         * question_content : 说实话还是说说
         * question_content1 :
         * select_a : 事实上打算
         * select_b : 士大夫似的
         * select_c : 示范点的
         * select_d : 发士大夫
         * answer : ["A"]
         * desc : 胜多负少的AAAA
         */

        private String question_id;
        private String question_type;
        private String question_content_type;
        private String sort;
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

        public List<String> getAnswer_select() {
            return answer_select;
        }

        public void setAnswer_select(List<String> answer_select) {
            this.answer_select = answer_select;
        }

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

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
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
    }
}
