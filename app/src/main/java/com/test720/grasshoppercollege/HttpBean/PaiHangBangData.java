package com.test720.grasshoppercollege.HttpBean;

import java.util.List;

/**
 * Created by 水东流 on 2018/4/9.
 */

public class PaiHangBangData {

    /**
     * code : 1
     * msg : 成功
     * data : {"info":{"uid":"1207","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fdeae37e51.jpg","nickname":"183****8595","points":"0","ranking":12},"list":[{"uid":"1211","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fa8844be4c.jpg","nickname":"182****6361","points":"1220"},{"uid":"1208","header":"","nickname":"158****7306","points":"0"},{"uid":"7","header":"","nickname":"","points":"0"},{"uid":"6","header":"","nickname":"","points":"0"},{"uid":"5","header":"","nickname":"","points":"0"},{"uid":"4","header":"","nickname":"","points":"0"},{"uid":"3","header":"","nickname":"","points":"0"},{"uid":"2","header":"","nickname":"","points":"0"},{"uid":"1","header":"","nickname":"","points":"0"},{"uid":"1210","header":"","nickname":"176****5858","points":"0"}]}
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
         * info : {"uid":"1207","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fdeae37e51.jpg","nickname":"183****8595","points":"0","ranking":12}
         * list : [{"uid":"1211","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fa8844be4c.jpg","nickname":"182****6361","points":"1220"},{"uid":"1208","header":"","nickname":"158****7306","points":"0"},{"uid":"7","header":"","nickname":"","points":"0"},{"uid":"6","header":"","nickname":"","points":"0"},{"uid":"5","header":"","nickname":"","points":"0"},{"uid":"4","header":"","nickname":"","points":"0"},{"uid":"3","header":"","nickname":"","points":"0"},{"uid":"2","header":"","nickname":"","points":"0"},{"uid":"1","header":"","nickname":"","points":"0"},{"uid":"1210","header":"","nickname":"176****5858","points":"0"}]
         */

        private InfoBean info;
        private List<ListBean> list;

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class InfoBean {
            /**
             * uid : 1207
             * header : https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fdeae37e51.jpg
             * nickname : 183****8595
             * points : 0
             * ranking : 12
             */

            private String uid;
            private String header;
            private String nickname;
            private String points;
            private int ranking;

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getHeader() {
                return header;
            }

            public void setHeader(String header) {
                this.header = header;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getPoints() {
                return points;
            }

            public void setPoints(String points) {
                this.points = points;
            }

            public int getRanking() {
                return ranking;
            }

            public void setRanking(int ranking) {
                this.ranking = ranking;
            }
        }

        public static class ListBean {
            /**
             * uid : 1211
             * header : https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fa8844be4c.jpg
             * nickname : 182****6361
             * points : 1220
             */

            private String uid;
            private String header;
            private String nickname;
            private String points;

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getHeader() {
                return header;
            }

            public void setHeader(String header) {
                this.header = header;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getPoints() {
                return points;
            }

            public void setPoints(String points) {
                this.points = points;
            }
        }
    }
}
