package com.test720.grasshoppercollege.HttpBean;

import java.util.List;

/**
 * Created by 水东流 on 2018/7/9.
 */

public class XieHouYUBaoDianData {

    /**
     * code : 1
     * msg : 成功
     * data : {"img":"","info":[{"question":"我有一个新书包","answer":"111111111"},{"question":"撒旦法是的飞洒的发","answer":"撒旦发射点"},{"question":"阿三地方a's'f'd","answer":"啊撒范德萨的发射点"},{"question":"阿三地方撒d'f","answer":"的方法大概f"},{"question":"阿斯顿f","answer":"发射点f"},{"question":"阿迪斯","answer":"阿斯顿f"},{"question":"阿三地方ds'f","answer":"是飞洒地方 "},{"question":"阿三地方","answer":"阿三地方"},{"question":"阿三地方a's'f'd","answer":"阿斯顿f"},{"question":"阿三地方a's'f'd","answer":" 撒地方"}]}
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
         * img :
         * info : [{"question":"我有一个新书包","answer":"111111111"},{"question":"撒旦法是的飞洒的发","answer":"撒旦发射点"},{"question":"阿三地方a's'f'd","answer":"啊撒范德萨的发射点"},{"question":"阿三地方撒d'f","answer":"的方法大概f"},{"question":"阿斯顿f","answer":"发射点f"},{"question":"阿迪斯","answer":"阿斯顿f"},{"question":"阿三地方ds'f","answer":"是飞洒地方 "},{"question":"阿三地方","answer":"阿三地方"},{"question":"阿三地方a's'f'd","answer":"阿斯顿f"},{"question":"阿三地方a's'f'd","answer":" 撒地方"}]
         */

        private String img;
        private List<InfoBean> info;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public List<InfoBean> getInfo() {
            return info;
        }

        public void setInfo(List<InfoBean> info) {
            this.info = info;
        }

        public static class InfoBean {
            /**
             * question : 我有一个新书包
             * answer : 111111111
             */

            private String question;
            private String answer;

            public String getQuestion() {
                return question;
            }

            public void setQuestion(String question) {
                this.question = question;
            }

            public String getAnswer() {
                return answer;
            }

            public void setAnswer(String answer) {
                this.answer = answer;
            }
        }
    }
}
