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
public class TouTiaoData {

    /**
     * code : 1
     * msg : 成功
     * data : {"thumb_up_count":"123","comment_count":"11","click_count":"22","is_thumb_up":0,"descWeb":"https://www.hzggedu.com/ggxydemo/small.php/Strategy/descWeb/id/1","comment":[]}
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
         * thumb_up_count : 123
         * comment_count : 11
         * click_count : 22
         * is_thumb_up : 0
         * descWeb : https://www.hzggedu.com/ggxydemo/small.php/Strategy/descWeb/id/1
         * comment : []
         */

        private String thumb_up_count;
        private String comment_count;
        private String click_count;
        private int is_thumb_up;
        private String descWeb;
        private List<PingLunData> comment;

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

        public String getDescWeb() {
            return descWeb;
        }

        public void setDescWeb(String descWeb) {
            this.descWeb = descWeb;
        }

        public List<PingLunData> getComment() {
            return comment;
        }

        public void setComment(List<PingLunData> comment) {
            this.comment = comment;
        }
    }
}
