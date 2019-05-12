package com.test720.grasshoppercollege.HttpBean;

import java.util.List;

/**
 * Created by 水东流 on 2018/7/3.
 */

public class YuFaTextTiMuData {


    /**
     * code : 1
     * msg : 成功
     * data : [{"question_id":"11","question_type":"3","subscript":[""],"question":"选择不正确的单词进行修改","question_content":[{"word":"","answer":"","right":1}],"question_content1":["I have an book"],"select_a":"","select_b":"","select_c":"","select_d":"","answer":["I have a book"],"desc":"","corr_id":"0","status":"0"},{"question_id":"9","question_type":"1","subscript":[""],"question":"","question_content":{"word":"","answer":"","right":0},"question_content1":[""],"select_a":"fsdf","select_b":"ggggg","select_c":"hhhhhhh","select_d":"aaaaa","answer":["B"],"desc":"ggsrf","question_select":["红色的风暴收到撒\n旦法 ____ nkjd"],"corr_id":"0","status":"0"},{"question_id":"10","question_type":"2","subscript":["0","0","0","0"],"question":"填入正确的不定冠词","question_content":{"word":"","answer":"","right":0},"question_content1":["____ apple","____ book","____ word","____ cat"],"select_a":"","select_b":"","select_c":"","select_d":"","answer":["an","a","a","a"],"desc":"","question_select":["a","an"],"corr_id":"0","status":"0"}]
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
         * question_id : 11
         * question_type : 3
         * subscript : [""]
         * question : 选择不正确的单词进行修改
         * question_content : [{"word":"","answer":"","right":1}]
         * question_content1 : ["I have an book"]
         * select_a :
         * select_b :
         * select_c :
         * select_d :
         * answer : ["I have a book"]
         * desc :
         * corr_id : 0
         * status : 0
         * question_select : ["红色的风暴收到撒\n旦法 ____ nkjd"]
         */

        private String question_id;
        private String question_type;
        private String question;
        private String select_a;
        private String select_b;
        private String select_c;
        private String select_d;
        private String desc;
        private String corr_id;
        private String status;
        private List<String> subscript;
        private List<QuestionContentBean> question_content;
        private List<String> question_content1;
        private List<String> answer;
        private List<String> question_select;

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

        public List<QuestionContentBean> getQuestion_content() {
            return question_content;
        }

        public void setQuestion_content(List<QuestionContentBean> question_content) {
            this.question_content = question_content;
        }

        public List<String> getQuestion_content1() {
            return question_content1;
        }

        public void setQuestion_content1(List<String> question_content1) {
            this.question_content1 = question_content1;
        }

        public List<String> getAnswer() {
            return answer;
        }

        public void setAnswer(List<String> answer) {
            this.answer = answer;
        }

        public List<String> getQuestion_select() {
            return question_select;
        }

        public void setQuestion_select(List<String> question_select) {
            this.question_select = question_select;
        }

        public static class QuestionContentBean {
            /**
             * word :
             * answer :
             * right : 1
             */

            private String word;
            private String answer;
            private int right;

            public String getWord() {
                return word;
            }

            public void setWord(String word) {
                this.word = word;
            }

            public String getAnswer() {
                return answer;
            }

            public void setAnswer(String answer) {
                this.answer = answer;
            }

            public int getRight() {
                return right;
            }

            public void setRight(int right) {
                this.right = right;
            }
        }
    }
}
