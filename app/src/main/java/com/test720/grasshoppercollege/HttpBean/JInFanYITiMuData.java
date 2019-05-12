package com.test720.grasshoppercollege.HttpBean;

import java.util.List;

/**
 * Created by 水东流 on 2018/7/9.
 */

public class JInFanYITiMuData {

    /**
     * code : 1
     * msg : 成功
     * data : [{"question_id":"1","question_content":"回复速度看到快速打开疯狂数独诺夫卡收费觉得","select_a":"顺丰速递","select_b":"士大夫","select_c":"发射点","select_d":"阿斯顿","answer":"A","desc":"似懂非懂"},{"question_id":"2","question_content":"地方","select_a":"告诉对方","select_b":"士啊","select_c":"发vs的","select_d":"阿斯大幅度","answer":"B","desc":"似懂非懂"}]
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
         * question_content : 回复速度看到快速打开疯狂数独诺夫卡收费觉得
         * select_a : 顺丰速递
         * select_b : 士大夫
         * select_c : 发射点
         * select_d : 阿斯顿
         * answer : A
         * desc : 似懂非懂
         */

        private String question_id;
        private String  corr_id;
        private String question_content;
        private String select_a;
        private String select_b;
        private String select_c;
        private String select_d;
        private String answer;
        private String desc;

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
