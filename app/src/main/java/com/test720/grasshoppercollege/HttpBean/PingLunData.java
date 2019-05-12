package com.test720.grasshoppercollege.HttpBean;

import java.util.List;

/**
 * Created by 水东流 on 2018/4/16.
 */

public class PingLunData {


    /**
     * comment_id : 48
     * uid : 1252
     * nickname : 虚空恶犬二千
     * header : Uploads/icon/15211673284104.jpg
     * content : 替你
     * time : 2018-06-11 21:20:58
     * thumb_up_count : 0
     * comment_count : 0
     * is_thumb_up : 0
     * img : ["https://www.hzggedu.com/ggxy/Uploads/comment_img/2018-06-11/5b1e773a47c87.jpg"]
     * level : 1
     * vip : 0
     * honor :
     * child : []
     */

    private String comment_id;
    private String uid;
    private String nickname;
    private String header;
    private String content;
    private String time;
    private String thumb_up_count;
    private String comment_count;
    private int is_thumb_up;
    private String level;
    private int vip;
    private String honor;
    private List<String> img;
    private List<ChildBean> child;

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

    public int getIs_thumb_up() {
        return is_thumb_up;
    }

    public void setIs_thumb_up(int is_thumb_up) {
        this.is_thumb_up = is_thumb_up;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getVip() {
        return vip;
    }

    public void setVip(int vip) {
        this.vip = vip;
    }

    public String getHonor() {
        return honor;
    }

    public void setHonor(String honor) {
        this.honor = honor;
    }

    public List<String> getImg() {
        return img;
    }

    public void setImg(List<String> img) {
        this.img = img;
    }

    public List<ChildBean> getChild() {
        return child;
    }

    public void setChild(List<ChildBean> child) {
        this.child = child;
    }


    public static class ChildBean {
        /**
         * comment_id : 40
         * uid : 1211
         * nickname : 182****6361
         * header : https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fa8844be4c.jpg
         * content : 好{
         * NSFont = &quot;&lt;UICTFont: 0x127749590&gt; font-family: \&quot;.PingFangSC-Regular\&quot;; font-weight: normal; font-style: normal; font-size: 16.00pt&quot;;
         * NSOriginalFont = &quot;&sdvsd666666666666jfffh交付解放军解放姐夫能否接受监督方式电风扇地方是否打开速度疯了疯了疯了疯了马拉
         * time : 2018-04-16 16:21:40
         * img : []
         * level : 1
         * vip : 1
         * honor :
         */

        private String comment_id;
        private String uid;
        private String nickname;
        private String header;
        private String content;
        private String time;
        private String level;
        private int vip;
        private String honor;
        private List<?> img;

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

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public int getVip() {
            return vip;
        }

        public void setVip(int vip) {
            this.vip = vip;
        }

        public String getHonor() {
            return honor;
        }

        public void setHonor(String honor) {
            this.honor = honor;
        }

        public List<?> getImg() {
            return img;
        }

        public void setImg(List<?> img) {
            this.img = img;
        }
    }
}
