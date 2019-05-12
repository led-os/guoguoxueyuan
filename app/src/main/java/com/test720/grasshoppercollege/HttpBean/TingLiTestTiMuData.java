package com.test720.grasshoppercollege.HttpBean;

import java.util.List;

/**
 * Created by 水东流 on 2018/7/4.
 */

public class TingLiTestTiMuData {

    /**
     * code : 1
     * msg : 成功
     * data : {"question":[{"question_id":"1","question_type":"2","question":"","question_content":"","select_a":"","select_b":"","select_c":"","select_d":"","answer":"","desc":"sdfsdf","question_gui":["le:","la:"],"question_content_gui":[{"q":"met","a":"0"},{"q":"met2","a":"0"},{"q":"mets","a":"0"},{"q":"messt","a":"0"},{"q":"gsst","a":"0"},{"q":"mfsst","a":"0"},{"q":"mevdsst","a":"1"},{"q":"mezzsst","a":"1"},{"q":"mesvst","a":"0"}]}]}
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
             * question_id : 1
             * question_type : 2
             * question :
             * question_content :
             * select_a :
             * select_b :
             * select_c :
             * select_d :
             * answer :
             * desc : sdfsdf
             * question_gui : ["le:","la:"]
             * question_content_gui : [{"q":"met","a":"0"},{"q":"met2","a":"0"},{"q":"mets","a":"0"},{"q":"messt","a":"0"},{"q":"gsst","a":"0"},{"q":"mfsst","a":"0"},{"q":"mevdsst","a":"1"},{"q":"mezzsst","a":"1"},{"q":"mesvst","a":"0"}]
             */

            private String question_id;
            private String corr_id;
            private String question_type;
            private String question;
            private String question_content;
            private String select_a;
            private String select_b;
            private String select_c;
            private String select_d;
            private String answer;
            private String desc;
            private List<String> question_gui;
            private List<QuestionContentGuiBean> question_content_gui;

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

            public List<String> getQuestion_gui() {
                return question_gui;
            }

            public void setQuestion_gui(List<String> question_gui) {
                this.question_gui = question_gui;
            }

            public List<QuestionContentGuiBean> getQuestion_content_gui() {
                return question_content_gui;
            }

            public void setQuestion_content_gui(List<QuestionContentGuiBean> question_content_gui) {
                this.question_content_gui = question_content_gui;
            }

            public static class QuestionContentGuiBean {
                /**
                 * q : met
                 * a : 0
                 */

                private String q;
                private String a;

                public String getQ() {
                    return q;
                }

                public void setQ(String q) {
                    this.q = q;
                }

                public String getA() {
                    return a;
                }

                public void setA(String a) {
                    this.a = a;
                }
            }
        }
    }
}
