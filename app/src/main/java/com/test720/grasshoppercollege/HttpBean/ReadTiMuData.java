package com.test720.grasshoppercollege.HttpBean;

import java.util.List;

/**
 * Created by 水东流 on 2018/6/25.
 */

public class ReadTiMuData {

    /**
     * code : 1
     * msg : 成功
     * data : {"question":[{"type":"2","question_content":"this is...","select_a":"","select_b":"","select_c":"","select_d":"","answer":"1","desc":""},{"type":"1","question_content":"hello word ","select_a":"adsd","select_b":"sds","select_c":"ddd","select_d":"fsd","answer":"A","desc":""}]}
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
        private List<QuestionBean> question;

        public List<QuestionBean> getQuestion() {
            return question;
        }

        public void setQuestion(List<QuestionBean> question) {
            this.question = question;
        }

        public static class QuestionBean {
            /**
             * type : 2
             * question_content : this is...
             * select_a :
             * select_b :
             * select_c :
             * select_d :
             * answer : 1
             * desc :
             */

            private String type;
            private String question_content;
            private String select_a;
            private String select_b;
            private String select_c;
            private String select_d;
            private String answer;
            private String desc;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
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
        }
    }
}
