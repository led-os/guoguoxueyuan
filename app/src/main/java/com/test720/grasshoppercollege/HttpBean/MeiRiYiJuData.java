package com.test720.grasshoppercollege.HttpBean;

import java.util.List;

/**
 * Created by 水东流 on 2018/6/28.
 */

public class MeiRiYiJuData {


    /**
     * code : 1
     * msg : 成功
     * data : {"info":{"day_id":"1","cn":"个","en":"g","voice":"ggg.mp3","picture":"gdfgdf.jpg"},"list":[{"u_day_id":"2","uid":"13","nickname":"ggg","header":"dds","time":"2018-03-19 16:17:00","like_count":"1","voice":"sdfsdf.mp3"},{"u_day_id":"3","uid":"1205","nickname":"fff","header":"sss","time":"2018-03-19 16:16:41","like_count":"12","voice":"jjdjd.mp3"}]}
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
         * info : {"day_id":"1","cn":"个","en":"g","voice":"ggg.mp3","picture":"gdfgdf.jpg"}
         * list : [{"u_day_id":"2","uid":"13","nickname":"ggg","header":"dds","time":"2018-03-19 16:17:00","like_count":"1","voice":"sdfsdf.mp3"},{"u_day_id":"3","uid":"1205","nickname":"fff","header":"sss","time":"2018-03-19 16:16:41","like_count":"12","voice":"jjdjd.mp3"}]
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
             * day_id : 1
             * cn : 个
             * en : g
             * voice : ggg.mp3
             * picture : gdfgdf.jpg
             */

            private String day_id;
            private String cn;
            private String en;
            private String voice;
            private String picture;

            public String getDay_id() {
                return day_id;
            }

            public void setDay_id(String day_id) {
                this.day_id = day_id;
            }

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

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }
        }

        public static class ListBean {
            /**
             * u_day_id : 2
             * uid : 13
             * nickname : ggg
             * header : dds
             * time : 2018-03-19 16:17:00
             * like_count : 1
             * voice : sdfsdf.mp3
             */

            private String u_day_id;
            private String uid;
            private String nickname;
            private String header;
            private String time;
            private String like_count;
            private String voice;
            private String voice_time;
            private String is_like;

            public String getVoice_time() {
                return voice_time;
            }

            public void setVoice_time(String voice_time) {
                this.voice_time = voice_time;
            }

            public String getIs_like() {
                return is_like;
            }

            public void setIs_like(String is_like) {
                this.is_like = is_like;
            }

            public String getU_day_id() {
                return u_day_id;
            }

            public void setU_day_id(String u_day_id) {
                this.u_day_id = u_day_id;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
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

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getLike_count() {
                return like_count;
            }

            public void setLike_count(String like_count) {
                this.like_count = like_count;
            }

            public String getVoice() {
                return voice;
            }

            public void setVoice(String voice) {
                this.voice = voice;
            }
        }
    }
}
