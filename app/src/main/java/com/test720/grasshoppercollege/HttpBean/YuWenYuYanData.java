package com.test720.grasshoppercollege.HttpBean;

import java.util.List;

/**
 * Created by 水东流 on 2018/4/19.
 */

public class YuWenYuYanData {

    /**
     * code : 1
     * msg : 成功
     * data : {"info":{"yu_id":"1","name":"dfg","video_path":"dfsdfsd.mp4","comment_count":"12","share_count":"3","thumb_up_count":"4"},"comment":[{"comment_id":"1","uid":"1205","nickname":"","header":"","content":"sdfsdf","time":"0000-00-00 00:00:00","thumb_up_count":"0","comment_count":"0","is_thumb_up":0,"img":[],"child":[]}]}
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
         * info : {"yu_id":"1","name":"dfg","video_path":"dfsdfsd.mp4","comment_count":"12","share_count":"3","thumb_up_count":"4"}
         * comment : [{"comment_id":"1","uid":"1205","nickname":"","header":"","content":"sdfsdf","time":"0000-00-00 00:00:00","thumb_up_count":"0","comment_count":"0","is_thumb_up":0,"img":[],"child":[]}]
         */

        private InfoBean info;
        private List<PingLunData> comment;

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public List<PingLunData> getComment() {
            return comment;
        }

        public void setComment(List<PingLunData> comment) {
            this.comment = comment;
        }

        public static class InfoBean {
            /**
             * yu_id : 1
             * name : dfg
             * video_path : dfsdfsd.mp4
             * comment_count : 12
             * share_count : 3
             * thumb_up_count : 4
             */

            private String yu_id;
            private String name;
            private String video_path;
            private String comment_count;
            private String share_count;
            private String thumb_up_count;

            public String getYu_id() {
                return yu_id;
            }

            public void setYu_id(String yu_id) {
                this.yu_id = yu_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getVideo_path() {
                return video_path;
            }

            public void setVideo_path(String video_path) {
                this.video_path = video_path;
            }

            public String getComment_count() {
                return comment_count;
            }

            public void setComment_count(String comment_count) {
                this.comment_count = comment_count;
            }

            public String getShare_count() {
                return share_count;
            }

            public void setShare_count(String share_count) {
                this.share_count = share_count;
            }

            public String getThumb_up_count() {
                return thumb_up_count;
            }

            public void setThumb_up_count(String thumb_up_count) {
                this.thumb_up_count = thumb_up_count;
            }
        }

    }
}
