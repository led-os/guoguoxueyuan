package com.test720.grasshoppercollege.HttpBean;

import java.util.List;

/**
 * Created by 水东流 on 2018/6/21.
 */

public class JinSaiData {

    /**
     * code : 1
     * msg : 成功
     * data : {"room_id":"5","my":{"nickname":"虚空恶犬二千","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg"},"opponent":null,"question":[{"question_id":"3","pass":"50","question":"444","select_a":"111","select_b":"123123","select_c":"123","select_d":"3123","answer":"3123"},{"question_id":"6","pass":"50","question":"77","select_a":"12312","select_b":"2331","select_c":"23","select_d":"123","answer":"312"},{"question_id":"9","pass":"50","question":"1010","select_a":"23","select_b":"12","select_c":"12","select_d":"3","answer":"12"},{"question_id":"10","pass":"50","question":"1111","select_a":"123","select_b":"3","select_c":"123","select_d":"123","answer":"3123"}]}
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
         * room_id : 5
         * my : {"nickname":"虚空恶犬二千","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg"}
         * opponent : null
         * question : [{"question_id":"3","pass":"50","question":"444","select_a":"111","select_b":"123123","select_c":"123","select_d":"3123","answer":"3123"},{"question_id":"6","pass":"50","question":"77","select_a":"12312","select_b":"2331","select_c":"23","select_d":"123","answer":"312"},{"question_id":"9","pass":"50","question":"1010","select_a":"23","select_b":"12","select_c":"12","select_d":"3","answer":"12"},{"question_id":"10","pass":"50","question":"1111","select_a":"123","select_b":"3","select_c":"123","select_d":"123","answer":"3123"}]
         */

        private String room_id;
        private MyBean my;
        private OpponentBean opponent;
        private List<QuestionBean> question;

        public String getRoom_id() {
            return room_id;
        }

        public void setRoom_id(String room_id) {
            this.room_id = room_id;
        }

        public MyBean getMy() {
            return my;
        }

        public void setMy(MyBean my) {
            this.my = my;
        }

        public OpponentBean getOpponent() {
            return opponent;
        }

        public void setOpponent(OpponentBean opponent) {
            this.opponent = opponent;
        }

        public List<QuestionBean> getQuestion() {
            return question;
        }

        public void setQuestion(List<QuestionBean> question) {
            this.question = question;
        }

        public static class MyBean {
            /**
             * nickname : 虚空恶犬二千
             * header : https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg
             */

            private String nickname;
            private String header;

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getHeader() {
                return header;
            }

            public void setHeader(String header) {
                this.header = header;
            }
        }
        public static class OpponentBean {
            /**
             * nickname : 虚空恶犬二千
             * header : https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg
             */

            private String nickname;
            private String header;

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getHeader() {
                return header;
            }

            public void setHeader(String header) {
                this.header = header;
            }
        }

        public static class QuestionBean {
            /**
             * question_id : 3
             * pass : 50
             * question : 444
             * select_a : 111
             * select_b : 123123
             * select_c : 123
             * select_d : 3123
             * answer : 3123
             */

            private String question_id;
            private String pass;
            private String question;
            private String select_a;
            private String select_b;
            private String select_c;
            private String select_d;
            private String answer;

            public String getQuestion_id() {
                return question_id;
            }

            public void setQuestion_id(String question_id) {
                this.question_id = question_id;
            }

            public String getPass() {
                return pass;
            }

            public void setPass(String pass) {
                this.pass = pass;
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

            public String getAnswer() {
                return answer;
            }

            public void setAnswer(String answer) {
                this.answer = answer;
            }
        }
    }
}
