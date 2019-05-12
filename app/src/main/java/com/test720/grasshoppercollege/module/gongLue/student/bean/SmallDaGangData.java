package com.test720.grasshoppercollege.module.gongLue.student.bean;

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
 * 作者：水东流 编于 2018/9/7
 */
public class SmallDaGangData {


    /**
     * code : 1
     * msg : 成功
     * data : {"content":{"micro_id":"1","name":"第一课 哈哈哈","path":"https://www.hzggedu.com/ggxydemo/Uploads/math_animation/video/No.mp4","desc":"不是大部分是的","price":"2.00","vip_price":"1.50","expiry_date":"7","thumb_up_count":"13","comment_count":"27","is_thumb_up":1,"album":{"micro_id":"1","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg","name":"奇幻拼音","type":"59","count":"2"}},"comment":[{"comment_id":"4","uid":"2789","content":"那你呢","time":"2018-09-13 10:47:41","thumb_up_count":"0","comment_count":"0","is_thumb_up":0,"img":["https://www.hzggedu.com/ggxydemo/Uploads/comment_img/2018-09-13/5b99cfcd8ec12.png"],"level":"11","vip":0,"honor":"","nickname":"水东流","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fdeae37e51.jpg","child":[]},{"comment_id":"3","uid":"2789","content":"GG哈哈哈","time":"2018-09-07 10:39:10","thumb_up_count":"0","comment_count":"0","is_thumb_up":0,"img":["https://www.hzggedu.com/ggxydemo/Uploads/comment_img/2018-09-07/5b91e4ce1ed9e.png"],"level":"11","vip":0,"honor":"","nickname":"水东流","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fdeae37e51.jpg","child":[]},{"comment_id":"2","uid":"1252","content":"Assad\u2019s","time":"2018-09-06 15:39:11","thumb_up_count":"1","comment_count":"0","is_thumb_up":0,"img":[],"level":"1","vip":0,"honor":"","nickname":"虚空恶犬二千","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg","child":[]},{"comment_id":"1","uid":"1252","content":"Assad\u2019sf010f012","time":"2018-09-06 15:29:31","thumb_up_count":"1","comment_count":"0","is_thumb_up":1,"img":[],"level":"1","vip":0,"honor":"","nickname":"虚空恶犬二千","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg","child":[]}],"title":"","body":""}
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
         * content : {"micro_id":"1","name":"第一课 哈哈哈","path":"https://www.hzggedu.com/ggxydemo/Uploads/math_animation/video/No.mp4","desc":"不是大部分是的","price":"2.00","vip_price":"1.50","expiry_date":"7","thumb_up_count":"13","comment_count":"27","is_thumb_up":1,"album":{"micro_id":"1","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg","name":"奇幻拼音","type":"59","count":"2"}}
         * comment : [{"comment_id":"4","uid":"2789","content":"那你呢","time":"2018-09-13 10:47:41","thumb_up_count":"0","comment_count":"0","is_thumb_up":0,"img":["https://www.hzggedu.com/ggxydemo/Uploads/comment_img/2018-09-13/5b99cfcd8ec12.png"],"level":"11","vip":0,"honor":"","nickname":"水东流","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fdeae37e51.jpg","child":[]},{"comment_id":"3","uid":"2789","content":"GG哈哈哈","time":"2018-09-07 10:39:10","thumb_up_count":"0","comment_count":"0","is_thumb_up":0,"img":["https://www.hzggedu.com/ggxydemo/Uploads/comment_img/2018-09-07/5b91e4ce1ed9e.png"],"level":"11","vip":0,"honor":"","nickname":"水东流","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fdeae37e51.jpg","child":[]},{"comment_id":"2","uid":"1252","content":"Assad\u2019s","time":"2018-09-06 15:39:11","thumb_up_count":"1","comment_count":"0","is_thumb_up":0,"img":[],"level":"1","vip":0,"honor":"","nickname":"虚空恶犬二千","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg","child":[]},{"comment_id":"1","uid":"1252","content":"Assad\u2019sf010f012","time":"2018-09-06 15:29:31","thumb_up_count":"1","comment_count":"0","is_thumb_up":1,"img":[],"level":"1","vip":0,"honor":"","nickname":"虚空恶犬二千","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg","child":[]}]
         * title :
         * body :
         */

        private ContentBean content;
        private String title;
        private String body;
        private List<PingLunData> comment;

        public ContentBean getContent() {
            return content;
        }

        public void setContent(ContentBean content) {
            this.content = content;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public List<PingLunData> getComment() {
            return comment;
        }

        public void setComment(List<PingLunData> comment) {
            this.comment = comment;
        }

        public static class ContentBean {
            /**
             * micro_id : 1
             * name : 第一课 哈哈哈
             * path : https://www.hzggedu.com/ggxydemo/Uploads/math_animation/video/No.mp4
             * desc : 不是大部分是的
             * price : 2.00
             * vip_price : 1.50
             * expiry_date : 7
             * thumb_up_count : 13
             * comment_count : 27
             * is_thumb_up : 1
             * album : {"micro_id":"1","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg","name":"奇幻拼音","type":"59","count":"2"}
             */

            private String micro_id;
            private String name;
            private String path;
            private String desc;
            private String price;
            private String vip_price;
            private String web_url;
            private String expiry_date;
            private String thumb_up_count;
            private String comment_count;
            private int is_thumb_up;
            private AlbumBean album;

            public String getWeb_url() {
                return web_url;
            }

            public void setWeb_url(String web_url) {
                this.web_url = web_url;
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

            public int getIs_thumb_up() {
                return is_thumb_up;
            }

            public void setIs_thumb_up(int is_thumb_up) {
                this.is_thumb_up = is_thumb_up;
            }

            public AlbumBean getAlbum() {
                return album;
            }

            public void setAlbum(AlbumBean album) {
                this.album = album;
            }

            public static class AlbumBean {
                /**
                 * micro_id : 1
                 * cover : https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg
                 * name : 奇幻拼音
                 * type : 59
                 * count : 2
                 */

                private String micro_id;
                private String cover;
                private String name;
                private String type;
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

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getCount() {
                    return count;
                }

                public void setCount(String count) {
                    this.count = count;
                }
            }
        }


    }
}
