package com.test720.grasshoppercollege.HttpBean;

/**
 * Created by 水东流 on 2018/6/21.
 */

public class JingSaiOverData {


    /**
     * code : 1
     * msg : 成功
     * data : {"user_1":{"uid":"1252","points":"20","experience_value":"20","value":"97","complete":"1","result":"1","nickname":"虚空恶犬二千","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg"},"user_2":{"value":"93","complete":"1","result":"0","nickname":"在下哈哈","header":"https://www.hzggedu.com/ggxy/Uploads/config/pic/2018-06-07/5b191a2245abd.png"},"match_share":"5","type":"13"}
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
         * user_1 : {"uid":"1252","points":"20","experience_value":"20","value":"97","complete":"1","result":"1","nickname":"虚空恶犬二千","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg"}
         * user_2 : {"value":"93","complete":"1","result":"0","nickname":"在下哈哈","header":"https://www.hzggedu.com/ggxy/Uploads/config/pic/2018-06-07/5b191a2245abd.png"}
         * match_share : 5
         * type : 13
         */

        private User1Bean user_1;
        private User2Bean user_2;
        private String match_share;
        private String type;

        public User1Bean getUser_1() {
            return user_1;
        }

        public void setUser_1(User1Bean user_1) {
            this.user_1 = user_1;
        }

        public User2Bean getUser_2() {
            return user_2;
        }

        public void setUser_2(User2Bean user_2) {
            this.user_2 = user_2;
        }

        public String getMatch_share() {
            return match_share;
        }

        public void setMatch_share(String match_share) {
            this.match_share = match_share;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public static class User1Bean {
            /**
             * uid : 1252
             * points : 20
             * experience_value : 20
             * value : 97
             * complete : 1
             * result : 1
             * nickname : 虚空恶犬二千
             * header : https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg
             */

            private String uid;
            private String points;
            private String experience_value;
            private String value;
            private String complete;
            private String result;
            private String nickname;
            private String header;

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getPoints() {
                return points;
            }

            public void setPoints(String points) {
                this.points = points;
            }

            public String getExperience_value() {
                return experience_value;
            }

            public void setExperience_value(String experience_value) {
                this.experience_value = experience_value;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getComplete() {
                return complete;
            }

            public void setComplete(String complete) {
                this.complete = complete;
            }

            public String getResult() {
                return result;
            }

            public void setResult(String result) {
                this.result = result;
            }

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

        public static class User2Bean {
            /**
             * value : 93
             * complete : 1
             * result : 0
             * nickname : 在下哈哈
             * header : https://www.hzggedu.com/ggxy/Uploads/config/pic/2018-06-07/5b191a2245abd.png
             */

            private String value;
            private String complete;
            private String result;
            private String nickname;
            private String header;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getComplete() {
                return complete;
            }

            public void setComplete(String complete) {
                this.complete = complete;
            }

            public String getResult() {
                return result;
            }

            public void setResult(String result) {
                this.result = result;
            }

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
