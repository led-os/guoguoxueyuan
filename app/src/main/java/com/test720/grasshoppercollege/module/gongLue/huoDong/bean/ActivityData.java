package com.test720.grasshoppercollege.module.gongLue.huoDong.bean;

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
 * 作者：水东流 编于 2018/9/12
 */
public class ActivityData {

    /**
     * code : 1
     * msg : 成功
     * data : {"pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg","name":"少儿英语体验课","desc":"哈哈哈哈哈哈哈哈说的话阿斯顿","total_count":"30","part_count":"20","comment_count":"13","thumb_up_count":"15","originator_name":"刘山上","originator_header":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg","originator_level":"3","originator_tag":["二次","小鲜肉"],"start_time":"2018-09-01 10:05:53","end_time":"2018-09-07 10:05:58","activity_desc":"https://www.hzggedu.com/ggxydemo/small.php/strategy/activityDesc/activity_id/1","status":"2","personnel":[{"header":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg","nickname":"123"},{"header":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg","nickname":"456"},{"header":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg","nickname":"111"},{"header":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg","nickname":"222"},{"header":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg","nickname":"333"}],"uid_status":"1","comment":[{"comment_id":"3","uid":"1252","content":"Asdfsadf","time":"2018-09-10 09:12:49","thumb_up_count":"0","comment_count":"0","is_thumb_up":0,"img":["https://www.hzggedu.com/ggxydemo/Uploads/comment_img/2018-09-10/5b95c511c7655.jpg"],"level":"1","vip":0,"honor":"","nickname":"虚空恶犬二千","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg","child":[]},{"comment_id":"2","uid":"1252","content":"123456","time":"2018-09-10 09:12:38","thumb_up_count":"0","comment_count":"0","is_thumb_up":0,"img":[],"level":"1","vip":0,"honor":"","nickname":"虚空恶犬二千","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg","child":[]},{"comment_id":"1","uid":"1252","content":"123456","time":"2018-09-10 09:09:51","thumb_up_count":"0","comment_count":"0","is_thumb_up":0,"img":[],"level":"1","vip":0,"honor":"","nickname":"虚空恶犬二千","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg","child":[]}]}
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
         * pic : https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg
         * name : 少儿英语体验课
         * desc : 哈哈哈哈哈哈哈哈说的话阿斯顿
         * total_count : 30
         * part_count : 20
         * comment_count : 13
         * thumb_up_count : 15
         * originator_name : 刘山上
         * originator_header : https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg
         * originator_level : 3
         * originator_tag : ["二次","小鲜肉"]
         * start_time : 2018-09-01 10:05:53
         * end_time : 2018-09-07 10:05:58
         * activity_desc : https://www.hzggedu.com/ggxydemo/small.php/strategy/activityDesc/activity_id/1
         * status : 2
         * personnel : [{"header":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg","nickname":"123"},{"header":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg","nickname":"456"},{"header":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg","nickname":"111"},{"header":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg","nickname":"222"},{"header":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg","nickname":"333"}]
         * uid_status : 1
         * comment : [{"comment_id":"3","uid":"1252","content":"Asdfsadf","time":"2018-09-10 09:12:49","thumb_up_count":"0","comment_count":"0","is_thumb_up":0,"img":["https://www.hzggedu.com/ggxydemo/Uploads/comment_img/2018-09-10/5b95c511c7655.jpg"],"level":"1","vip":0,"honor":"","nickname":"虚空恶犬二千","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg","child":[]},{"comment_id":"2","uid":"1252","content":"123456","time":"2018-09-10 09:12:38","thumb_up_count":"0","comment_count":"0","is_thumb_up":0,"img":[],"level":"1","vip":0,"honor":"","nickname":"虚空恶犬二千","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg","child":[]},{"comment_id":"1","uid":"1252","content":"123456","time":"2018-09-10 09:09:51","thumb_up_count":"0","comment_count":"0","is_thumb_up":0,"img":[],"level":"1","vip":0,"honor":"","nickname":"虚空恶犬二千","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg","child":[]}]
         */

        private String pic;
        private String name;
        private String desc;
        private String total_count;
        private String part_count;
        private String comment_count;
        private String thumb_up_count;
        private String originator_name;
        private String originator_header;
        private String originator_level;
        private String start_time;
        private String end_time;
        private String activity_desc;
        private String status;
        private String uid_status;
        private List<String> originator_tag;
        private List<PersonnelBean> personnel;
        private List<PingLunData> comment;

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getTotal_count() {
            return total_count;
        }

        public void setTotal_count(String total_count) {
            this.total_count = total_count;
        }

        public String getPart_count() {
            return part_count;
        }

        public void setPart_count(String part_count) {
            this.part_count = part_count;
        }

        public String getComment_count() {
            return comment_count;
        }

        public void setComment_count(String comment_count) {
            this.comment_count = comment_count;
        }

        public String getThumb_up_count() {
            return thumb_up_count;
        }

        public void setThumb_up_count(String thumb_up_count) {
            this.thumb_up_count = thumb_up_count;
        }

        public String getOriginator_name() {
            return originator_name;
        }

        public void setOriginator_name(String originator_name) {
            this.originator_name = originator_name;
        }

        public String getOriginator_header() {
            return originator_header;
        }

        public void setOriginator_header(String originator_header) {
            this.originator_header = originator_header;
        }

        public String getOriginator_level() {
            return originator_level;
        }

        public void setOriginator_level(String originator_level) {
            this.originator_level = originator_level;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getActivity_desc() {
            return activity_desc;
        }

        public void setActivity_desc(String activity_desc) {
            this.activity_desc = activity_desc;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getUid_status() {
            return uid_status;
        }

        public void setUid_status(String uid_status) {
            this.uid_status = uid_status;
        }

        public List<String> getOriginator_tag() {
            return originator_tag;
        }

        public void setOriginator_tag(List<String> originator_tag) {
            this.originator_tag = originator_tag;
        }

        public List<PersonnelBean> getPersonnel() {
            return personnel;
        }

        public void setPersonnel(List<PersonnelBean> personnel) {
            this.personnel = personnel;
        }

        public List<PingLunData> getComment() {
            return comment;
        }

        public void setComment(List<PingLunData> comment) {
            this.comment = comment;
        }

        public static class PersonnelBean {
            /**
             * header : https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg
             * nickname : 123
             */

            private String header;
            private String nickname;

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
        }

    }
}
