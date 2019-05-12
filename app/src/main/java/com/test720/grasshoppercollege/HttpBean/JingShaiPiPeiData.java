package com.test720.grasshoppercollege.HttpBean;

/**
 * Created by 水东流 on 2018/6/21.
 */

public class JingShaiPiPeiData {


    /**
     * code : 1
     * msg : 成功
     * data : {"room_id":"5","my":{"nickname":"虚空恶犬二千","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg"},"opponent":{"nickname":"在下哈哈","header":"https://www.hzggedu.com/ggxy/Uploads/config/pic/2018-06-07/5b191a2245abd.png"},"question":[{"question_id":"3","pass":"50","question":"444","select_a":"111","select_b":"123123","select_c":"123","select_d":"3123","answer":"3123"},{"question_id":"6","pass":"50","question":"77","select_a":"12312","select_b":"2331","select_c":"23","select_d":"123","answer":"312"},{"question_id":"9","pass":"50","question":"1010","select_a":"23","select_b":"12","select_c":"12","select_d":"3","answer":"12"},{"question_id":"10","pass":"50","question":"1111","select_a":"123","select_b":"3","select_c":"123","select_d":"123","answer":"3123"}],"start_time":"562565826"}
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
         * opponent : {"nickname":"在下哈哈","header":"https://www.hzggedu.com/ggxy/Uploads/config/pic/2018-06-07/5b191a2245abd.png"}
         * question : [{"question_id":"3","pass":"50","question":"444","select_a":"111","select_b":"123123","select_c":"123","select_d":"3123","answer":"3123"},{"question_id":"6","pass":"50","question":"77","select_a":"12312","select_b":"2331","select_c":"23","select_d":"123","answer":"312"},{"question_id":"9","pass":"50","question":"1010","select_a":"23","select_b":"12","select_c":"12","select_d":"3","answer":"12"},{"question_id":"10","pass":"50","question":"1111","select_a":"123","select_b":"3","select_c":"123","select_d":"123","answer":"3123"}]
         * start_time : 562565826
         */

        private String room_id;
        private MyBean my;
        private OpponentBean opponent;
        private String start_time;

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

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
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
             * nickname : 在下哈哈
             * header : https://www.hzggedu.com/ggxy/Uploads/config/pic/2018-06-07/5b191a2245abd.png
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

    }
}
