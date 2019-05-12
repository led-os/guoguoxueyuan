package com.test720.grasshoppercollege.HttpBean;

import java.util.List;

/**
 * Created by 水东流 on 2018/4/9.
 */

public class HeaderData {

    /**
     * code : 1
     * msg : 成功
     * data : {"obtain":[{"header_id":"1","pic":"22.jpg"},{"header_id":"2","pic":"21.jpg"}],"no_obtain":[{"header_id":"4","points":"2","pic":"12.jpg"},{"header_id":"3","points":"20","pic":"4.jpg"}],"info":{"header":"","nickname":"158****7306","level":"1"}}
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
         * obtain : [{"header_id":"1","pic":"22.jpg"},{"header_id":"2","pic":"21.jpg"}]
         * no_obtain : [{"header_id":"4","points":"2","pic":"12.jpg"},{"header_id":"3","points":"20","pic":"4.jpg"}]
         * info : {"header":"","nickname":"158****7306","level":"1"}
         */

        private InfoBean info;
        private List<ObtainBean> obtain;
        private List<NoObtainBean> no_obtain;

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public List<ObtainBean> getObtain() {
            return obtain;
        }

        public void setObtain(List<ObtainBean> obtain) {
            this.obtain = obtain;
        }

        public List<NoObtainBean> getNo_obtain() {
            return no_obtain;
        }

        public void setNo_obtain(List<NoObtainBean> no_obtain) {
            this.no_obtain = no_obtain;
        }

        public static class InfoBean {
            /**
             * header :
             * nickname : 158****7306
             * level : 1
             */

            private String header;
            private String nickname;
            private String level;

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

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }
        }

        public static class ObtainBean {
            /**
             * header_id : 1
             * pic : 22.jpg
             */

            private String header_id;
            private String pic;
            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getHeader_id() {
                return header_id;
            }

            public void setHeader_id(String header_id) {
                this.header_id = header_id;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }
        }

        public static class NoObtainBean {
            /**
             * header_id : 4
             * points : 2
             * pic : 12.jpg
             */

            private String header_id;
            private String points;
            private String pic;
            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getHeader_id() {
                return header_id;
            }

            public void setHeader_id(String header_id) {
                this.header_id = header_id;
            }

            public String getPoints() {
                return points;
            }

            public void setPoints(String points) {
                this.points = points;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }
        }
    }
}
