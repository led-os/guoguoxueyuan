package com.test720.grasshoppercollege.module.gongLue.student.bean;

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
public class ShiTingClassData {

    /**
     * code : 1
     * msg : 成功
     * data : {"type":"59","micro_id":"1","name":"第一课 哈哈哈","path":"https://www.hzggedu.com/ggxydemo/Uploads/math_animation/video/No.mp4","desc":"不是大部分是的","price":"2.00","vip_price":"1.50","expiry_date":"7","thumb_up_count":"12","comment_count":"26","subscribe_count":"100","is_pay":"1","album":{"micro_id":"1","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg","name":"奇幻拼音","count":"2"},"course_desc":"https://www.hzggedu.com/ggxydemo/small.php/Strategy/microCourseDesc/micro_id/1","common_question":"https://www.hzggedu.com/ggxydemo/small.php/Strategy/microQuestion/micro_id/1/type/59","address":[],"vip":{"vip_price":"0.01","vip":0},"course":[],"comment":[{"comment_id":"3","uid":"2789","content":"GG哈哈哈","time":"2018-09-07 10:39:10","thumb_up_count":"0","comment_count":"0","is_thumb_up":0,"img":["https://www.hzggedu.com/ggxydemo/Uploads/comment_img/2018-09-07/5b91e4ce1ed9e.png"],"level":"11","vip":0,"honor":"","nickname":"水东流","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fdeae37e51.jpg","child":[]},{"comment_id":"2","uid":"1252","content":"Assad\u2019s","time":"2018-09-06 15:39:11","thumb_up_count":"1","comment_count":"0","is_thumb_up":1,"img":[],"level":"1","vip":0,"honor":"","nickname":"虚空恶犬二千","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg","child":[]},{"comment_id":"1","uid":"1252","content":"Assad\u2019sf010f012","time":"2018-09-06 15:29:31","thumb_up_count":"1","comment_count":"0","is_thumb_up":0,"img":[],"level":"1","vip":0,"honor":"","nickname":"虚空恶犬二千","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg","child":[]}]}
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
         * type : 59
         * micro_id : 1
         * name : 第一课 哈哈哈
         * path : https://www.hzggedu.com/ggxydemo/Uploads/math_animation/video/No.mp4
         * desc : 不是大部分是的
         * price : 2.00
         * vip_price : 1.50
         * expiry_date : 7
         * thumb_up_count : 12
         * comment_count : 26
         * subscribe_count : 100
         * is_pay : 1
         * album : {"micro_id":"1","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg","name":"奇幻拼音","count":"2"}
         * course_desc : https://www.hzggedu.com/ggxydemo/small.php/Strategy/microCourseDesc/micro_id/1
         * common_question : https://www.hzggedu.com/ggxydemo/small.php/Strategy/microQuestion/micro_id/1/type/59
         * address : []
         * vip : {"vip_price":"0.01","vip":0}
         * course : []
         * comment : [{"comment_id":"3","uid":"2789","content":"GG哈哈哈","time":"2018-09-07 10:39:10","thumb_up_count":"0","comment_count":"0","is_thumb_up":0,"img":["https://www.hzggedu.com/ggxydemo/Uploads/comment_img/2018-09-07/5b91e4ce1ed9e.png"],"level":"11","vip":0,"honor":"","nickname":"水东流","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fdeae37e51.jpg","child":[]},{"comment_id":"2","uid":"1252","content":"Assad\u2019s","time":"2018-09-06 15:39:11","thumb_up_count":"1","comment_count":"0","is_thumb_up":1,"img":[],"level":"1","vip":0,"honor":"","nickname":"虚空恶犬二千","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg","child":[]},{"comment_id":"1","uid":"1252","content":"Assad\u2019sf010f012","time":"2018-09-06 15:29:31","thumb_up_count":"1","comment_count":"0","is_thumb_up":0,"img":[],"level":"1","vip":0,"honor":"","nickname":"虚空恶犬二千","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg","child":[]}]
         */

        private String type;
        private String micro_id;
        private String name;
        private String path;
        private String desc;
        private String price;
        private String state;
        private String vip_price;
        private String expiry_date;
        private String thumb_up_count;
        private String comment_count;
        private String subscribe_count;
        private String contact_customer;
        private String is_pay;
        private AlbumBean album;
        private String course_desc;
        private String web_url;
        private String common_question;
        private VipBean vip;

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getWeb_url() {
            return web_url;
        }

        public void setWeb_url(String web_url) {
            this.web_url = web_url;
        }

        public String getContact_customer() {
            return contact_customer;
        }

        public void setContact_customer(String contact_customer) {
            this.contact_customer = contact_customer;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getMicro_id() {
            return micro_id;
        }

        public void setMicro_id(String micro_id) {
            this.micro_id = micro_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getVip_price() {
            return vip_price;
        }

        public void setVip_price(String vip_price) {
            this.vip_price = vip_price;
        }

        public String getExpiry_date() {
            return expiry_date;
        }

        public void setExpiry_date(String expiry_date) {
            this.expiry_date = expiry_date;
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

        public String getSubscribe_count() {
            return subscribe_count;
        }

        public void setSubscribe_count(String subscribe_count) {
            this.subscribe_count = subscribe_count;
        }

        public String getIs_pay() {
            return is_pay;
        }

        public void setIs_pay(String is_pay) {
            this.is_pay = is_pay;
        }

        public AlbumBean getAlbum() {
            return album;
        }

        public void setAlbum(AlbumBean album) {
            this.album = album;
        }

        public String getCourse_desc() {
            return course_desc;
        }

        public void setCourse_desc(String course_desc) {
            this.course_desc = course_desc;
        }

        public String getCommon_question() {
            return common_question;
        }

        public void setCommon_question(String common_question) {
            this.common_question = common_question;
        }

        public VipBean getVip() {
            return vip;
        }

        public void setVip(VipBean vip) {
            this.vip = vip;
        }


        public static class AlbumBean {
            /**
             * micro_id : 1
             * cover : https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg
             * name : 奇幻拼音
             * count : 2
             */

            private String micro_id;
            private String cover;
            private String name;
            private String count;

            public String getMicro_id() {
                return micro_id;
            }

            public void setMicro_id(String micro_id) {
                this.micro_id = micro_id;
            }

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }
        }

        public static class VipBean {
            /**
             * vip_price : 0.01
             * vip : 0
             */

            private String vip_price;
            private int vip;

            public String getVip_price() {
                return vip_price;
            }

            public void setVip_price(String vip_price) {
                this.vip_price = vip_price;
            }

            public int getVip() {
                return vip;
            }

            public void setVip(int vip) {
                this.vip = vip;
            }
        }

        public static class CommentBean {
            /**
             * comment_id : 3
             * uid : 2789
             * content : GG哈哈哈
             * time : 2018-09-07 10:39:10
             * thumb_up_count : 0
             * comment_count : 0
             * is_thumb_up : 0
             * img : ["https://www.hzggedu.com/ggxydemo/Uploads/comment_img/2018-09-07/5b91e4ce1ed9e.png"]
             * level : 11
             * vip : 0
             * honor :
             * nickname : 水东流
             * header : https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fdeae37e51.jpg
             * child : []
             */

            private String comment_id;
            private String uid;
            private String content;
            private String time;
            private String thumb_up_count;
            private String comment_count;
            private int is_thumb_up;
            private String level;
            private int vip;
            private String honor;
            private String nickname;
            private String header;
            private List<String> img;
            private List<?> child;

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

            public List<String> getImg() {
                return img;
            }

            public void setImg(List<String> img) {
                this.img = img;
            }

            public List<?> getChild() {
                return child;
            }

            public void setChild(List<?> child) {
                this.child = child;
            }
        }
    }
}
