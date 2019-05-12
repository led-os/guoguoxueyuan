package com.test720.grasshoppercollege.module.gongLue.jiaZhang.bean;

import com.test720.grasshoppercollege.HttpBean.PingLunData;

import java.util.List;

/**
 * 佛祖保佑        永无BUG
 * 佛曰:
 * 程序园里程序天，程序天里程序员；
 * 程序猿人写程序，又拿程序换肉钱。
 * 肉饱继续桌前坐，饱暖还是桌前眠；
 * 半迷半醒日复日，码上码下年复年。
 * 但愿叱咤互联世，不愿搬砖码当前；
 * 诸葛周瑜算世事，我算需求得加钱。
 * 别人笑我忒直男，我说自己是程猿；
 * 但见成都府国内，处处地地程序员。
 * 作者：水东流 编于 2018/9/10
 */
public class EduData {

    /**
     * code : 1
     * msg : 成功
     * data : {"title":"拜托提大","path":"","pic":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fdeae37e51.jpg","thumb_up_count":"0","comment_count":"0","click_count":"0","is_thumb_up":0,"comment":[]}
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
         * title : 拜托提大
         * path :
         * pic : https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fdeae37e51.jpg
         * thumb_up_count : 0
         * comment_count : 0
         * click_count : 0
         * is_thumb_up : 0
         * comment : []
         */

        private String title;
        private String content;
        private String path;
        private String pic;
        private String thumb_up_count;
        private String comment_count;
        private String click_count;
        private int is_thumb_up;
        private List<PingLunData> comment;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
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

        public String getClick_count() {
            return click_count;
        }

        public void setClick_count(String click_count) {
            this.click_count = click_count;
        }

        public int getIs_thumb_up() {
            return is_thumb_up;
        }

        public void setIs_thumb_up(int is_thumb_up) {
            this.is_thumb_up = is_thumb_up;
        }

        public List<PingLunData> getComment() {
            return comment;
        }

        public void setComment(List<PingLunData> comment) {
            this.comment = comment;
        }
    }
}
