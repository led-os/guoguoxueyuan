package com.test720.grasshoppercollege.HttpBean;

import java.util.List;

/**
 * Created by 水东流 on 2018/6/28.
 */

public class MeiRiYiJuLianXiAndGameData {

    /**
     * code : 1
     * msg : 成功
     * data : {"cn":"嗨","en":"hello1 world1","voice":"https://www.hzggedu.com/ggxydemo/Uploads/reading/beishidaban+g3+x+en/Unit1/Lesson1/g3+beishidaban+x+u1+3.mp3","question":"","subscript":["7"],"answer":[""]}
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
        /**
         * cn : 嗨
         * en : hello1 world1
         * voice : https://www.hzggedu.com/ggxydemo/Uploads/reading/beishidaban+g3+x+en/Unit1/Lesson1/g3+beishidaban+x+u1+3.mp3
         * question :
         * subscript : ["7"]
         * answer : [""]
         */

        private String cn;
        private String en;
        private String voice;
        private String question;
        private List<String> subscript;
        private List<String> answer;

        public String getCn() {
            return cn;
        }

        public void setCn(String cn) {
            this.cn = cn;
        }

        public String getEn() {
            return en;
        }

        public void setEn(String en) {
            this.en = en;
        }

        public String getVoice() {
            return voice;
        }

        public void setVoice(String voice) {
            this.voice = voice;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
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
