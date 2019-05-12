package com.test720.grasshoppercollege.HttpBean;

/**
 * Created by Lenovo on 2018/4/3.
 */

public class ADData {


    /**
     * code : 1
     * msg : 成功
     * data : {"info":{"pic":"http://localhost/ggxy/Uploads/popup_ads/2018-02-23/5a8fdeae37e51.jpg","location_type":"1","location_link":""}}
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
         * info : {"pic":"http://localhost/ggxy/Uploads/popup_ads/2018-02-23/5a8fdeae37e51.jpg","location_type":"1","location_link":""}
         */

        private InfoBean info;

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public static class InfoBean {
            /**
             * pic : http://localhost/ggxy/Uploads/popup_ads/2018-02-23/5a8fdeae37e51.jpg
             * location_type : 1
             * location_link :
             */

            private String pic;
            private String location_type;
            private String location_link;

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getLocation_type() {
                return location_type;
            }

            public void setLocation_type(String location_type) {
                this.location_type = location_type;
            }

            public String getLocation_link() {
                return location_link;
            }

            public void setLocation_link(String location_link) {
                this.location_link = location_link;
            }
        }
    }
}
