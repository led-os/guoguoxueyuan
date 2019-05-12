package com.test720.grasshoppercollege.HttpBean;

import java.util.List;

/**
 * Created by 水东流 on 2018/6/26.
 */

public class BaiBianTingLiTiMuData {

    /**
     * code : 1
     * msg : 成功
     * data : [{"voice":"https://www.hzggedu.com/ggxy/Uploads/reading_small/renjiaoban+g3+x+en/Unit1/PartC/g3+renjiaoban+x+u1+11.mp3","type":"1","list":[{"question_type":"1","question":"","subscript":[""],"select_a":"s","select_b":"d","select_c":"f","select_d":"sf","answer":["A"],"desc":""}]},{"voice":"https://www.hzggedu.com/ggxy/Uploads/reading_small/renjiaoban+g3+x+en/Unit1/PartC/g3+renjiaoban+x+u1+11.mp3","type":"1","list":[{"question_type":"1","question":"","subscript":[""],"select_a":"s","select_b":"d","select_c":"f","select_d":"sf","answer":["A"],"desc":""}]}]
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
         * voice : https://www.hzggedu.com/ggxy/Uploads/reading_small/renjiaoban+g3+x+en/Unit1/PartC/g3+renjiaoban+x+u1+11.mp3
         * type : 1
         * list : [{"question_type":"1","question":"","subscript":[""],"select_a":"s","select_b":"d","select_c":"f","select_d":"sf","answer":["A"],"desc":""}]
         */

        private String voice;
        private String type;
        private List<ListBean> list;

        public String getVoice() {
            return voice;
        }

        public void setVoice(String voice) {
            this.voice = voice;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * question_type : 1
             * question :
             * subscript : [""]
             * select_a : s
             * select_b : d
             * select_c : f
             * select_d : sf
             * answer : ["A"]
             * desc :
             */

            private String question_type;
            private String question;
            private String select_a;
            private String select_b;
            private String select_c;
            private String select_d;
            private String desc;
            private List<String> subscript;
            private List<String> answer;

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
}
