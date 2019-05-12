package com.test720.grasshoppercollege.module.gongLue.jiaZhang.bean;

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
public class TouTiaoListData {

    /**
     * code : 1
     * msg : 成功
     * data : [{"headline_id":"1","title":"能否收到","cover":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fdeae37e51.jpg","thumb_up_count":"123","comment_count":"11","click_count":"22","time":"0000-00-00 00:00:00"},{"headline_id":"2","title":"dedd方法","cover":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fdeae37e51.jpg","thumb_up_count":"0","comment_count":"0","click_count":"0","time":"0000-00-00 00:00:00"},{"headline_id":"3","title":"是从不是的","cover":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fdeae37e51.jpg","thumb_up_count":"0","comment_count":"0","click_count":"0","time":"0000-00-00 00:00:00"},{"headline_id":"4","title":"能否收到","cover":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fdeae37e51.jpg","thumb_up_count":"123","comment_count":"11","click_count":"22","time":"0000-00-00 00:00:00"},{"headline_id":"5","title":"dedd方法","cover":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fdeae37e51.jpg","thumb_up_count":"0","comment_count":"0","click_count":"0","time":"0000-00-00 00:00:00"},{"headline_id":"6","title":"是从不是的","cover":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fdeae37e51.jpg","thumb_up_count":"0","comment_count":"0","click_count":"0","time":"0000-00-00 00:00:00"}]
     */

    private int code;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * headline_id : 1
         * title : 能否收到
         * cover : https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fdeae37e51.jpg
         * thumb_up_count : 123
         * comment_count : 11
         * click_count : 22
         * time : 0000-00-00 00:00:00
         */

        private String headline_id;
        private String title;
        private String cover;
        private String thumb_up_count;
        private String comment_count;
        private String click_count;
        private String time;

        public String getHeadline_id() {
            return headline_id;
        }

        public void setHeadline_id(String headline_id) {
            this.headline_id = headline_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
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

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
