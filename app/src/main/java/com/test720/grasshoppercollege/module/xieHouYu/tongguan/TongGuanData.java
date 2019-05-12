package com.test720.grasshoppercollege.module.xieHouYu.tongguan;

import java.util.List;

/**
 * Created by 水东流 on 2018/7/6.
 */

public class TongGuanData {

    /**
     * code : 1
     * msg : 成功
     * data : [{"question_id":"1","question":"","question_content":["士大夫地方"],"answer":["A"],"desc":"ff "},{"question_id":"2","question":"","question_content":["士大sss"],"answer":["B"],"desc":"ff "}]
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
         * question :
         * question_content : ["士大夫地方"]
         * answer : ["A"]
         * desc : ff
         */

        private String question_id;
        private String question;
        private String desc;
        private List<String> question_content;
        private List<String> answer;

        public String getQuestion_id() {
            return question_id;
        }

        public void setQuestion_id(String question_id) {
            this.question_id = question_id;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public List<String> getQuestion_content() {
            return question_content;
        }

        public void setQuestion_content(List<String> question_content) {
            this.question_content = question_content;
        }

        public List<String> getAnswer() {
            return answer;
        }

        public void setAnswer(List<String> answer) {
            this.answer = answer;
        }
    }
}
