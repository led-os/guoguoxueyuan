package com.test720.grasshoppercollege.HttpBean;

import java.util.List;

/**
 * Created by 水东流 on 2018/4/18.
 */

public class PingLunXiangQingData {

    /**
     * code : 1
     * msg : 成功
     * data : {"info":{"comment_id":"1","uid":"23","nickname":"sdvd","header":"sffff","content":"123123","time":"2018-03-20 11:49:14","thumb_up_count":"2","comment_count":"3"},"list":[{"comment_id":"2","uid":"1123","nickname":"sdfsdf","header":"wewe","content":"df3","time":"2018-03-20 11:53:42"}]}
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
         * info : {"comment_id":"1","uid":"23","nickname":"sdvd","header":"sffff","content":"123123","time":"2018-03-20 11:49:14","thumb_up_count":"2","comment_count":"3"}
         * list : [{"comment_id":"2","uid":"1123","nickname":"sdfsdf","header":"wewe","content":"df3","time":"2018-03-20 11:53:42"}]
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
             * comment_id : 1
             * uid : 23
             * nickname : sdvd
             * header : sffff
             * content : 123123
             * time : 2018-03-20 11:49:14
             * thumb_up_count : 2
             * comment_count : 3
             */

            private String comment_id;
            private String uid;
            private String nickname;
            private String header;
            private String content;
            private String time;
            private String thumb_up_count;
            private String comment_count;
            private String vip;
            private List<String> img;
            public List<String> getImg() {
                return img;
            }

            public void setImg(List<String> img) {
                this.img = img;
            }

            public String getVip() {
                return vip;
            }

            public void setVip(String vip) {
                this.vip = vip;
            }

            public String getComment_id() {
                return comment_id;
            }

            public void setComment_id(String comment_id) {
                this.comment_id = comment_id;
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

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getThumb_up_count() {
                return thumb_up_count;
            }

            public void setThumb_up_count(String thumb_up_count) {
                this.thumb_up_count = thumb_up_count;
            }

            public String getComment_count() {
                return comment_count;
            }

            public void setComment_count(String comment_count) {
                this.comment_count = comment_count;
            }
        }

        public static class ListBean {
            /**
             * comment_id : 2
             * uid : 1123
             * nickname : sdfsdf
             * header : wewe
             * content : df3
             * time : 2018-03-20 11:53:42
             */

            private String comment_id;
            private String uid;
            private String nickname;
            private String header;
            private String content;
            private String time;
            private String vip;

            public String getVip() {
                return vip;
            }

            public void setVip(String vip) {
                this.vip = vip;
            }

            public String getComment_id() {
                return comment_id;
            }

            public void setComment_id(String comment_id) {
                this.comment_id = comment_id;
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

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }
        }
    }
}
