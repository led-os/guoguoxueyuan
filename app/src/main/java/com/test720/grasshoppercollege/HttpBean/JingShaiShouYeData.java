package com.test720.grasshoppercollege.HttpBean;

import java.util.List;

/**
 * Created by 水东流 on 2018/6/21.
 */

public class JingShaiShouYeData {

    /**
     * code : 1
     * msg : 成功
     * data : {"info":{"experience_value":"0","level":"1","nickname":"虚空恶犬二千","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg","points":"87"},"list":[{"mid":"1","name":"新手场-1","pic":"","level":"1","points":"10","limit_level":["1","5"]},{"mid":"2","name":"新手场-2","pic":"","level":"2","points":"10","limit_level":["5","10"]}]}
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
         * info : {"experience_value":"0","level":"1","nickname":"虚空恶犬二千","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg","points":"87"}
         * list : [{"mid":"1","name":"新手场-1","pic":"","level":"1","points":"10","limit_level":["1","5"]},{"mid":"2","name":"新手场-2","pic":"","level":"2","points":"10","limit_level":["5","10"]}]
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
             * experience_value : 0
             * level : 1
             * nickname : 虚空恶犬二千
             * header : https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg
             * points : 87
             */

            private String experience_value;
            private String level;
            private String nickname;
            private String header;
            private String points;

            public String getExperience_value() {
                return experience_value;
            }

            public void setExperience_value(String experience_value) {
                this.experience_value = experience_value;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
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

            public String getPoints() {
                return points;
            }

            public void setPoints(String points) {
                this.points = points;
            }
        }

        public static class ListBean {
            /**
             * mid : 1
             * name : 新手场-1
             * pic :
             * level : 1
             * points : 10
             * limit_level : ["1","5"]
             */

            private String mid;
            private String name;
            private String pic;
            private String level;
            private String points;
            private List<String> limit_level;

            public String getMid() {
                return mid;
            }

            public void setMid(String mid) {
                this.mid = mid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public String getPoints() {
                return points;
            }

            public void setPoints(String points) {
                this.points = points;
            }

            public List<String> getLimit_level() {
                return limit_level;
            }

            public void setLimit_level(List<String> limit_level) {
                this.limit_level = limit_level;
            }
        }
    }
}
