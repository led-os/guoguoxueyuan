package com.test720.grasshoppercollege.module.youEr.baoBaoTingTing.bean;

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
 * 作者：水东流 编于 2018/9/4
 */
public class GuShiNeiRongData {

    /**
     * code : 1
     * msg : 成功
     * data : {"info":{"album_id":"1","album":{"title":"凯叔 · 三国演义","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/beishidaban+g3+x+en/Unit1/Lesson1/g3+beishidaban+x+u1+2.jpg","count":"10"},"subscript":"0"},"comment":[],"list":[{"con_id":"1","title_t":"超燃片花","small_title":"哈哈","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/beishidaban+g3+x+en/Unit1/Lesson1/g3+beishidaban+x+u1+2.jpg","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/beishidaban+g3+x+en/Unit1/Lesson1/g3+beishidaban+x+u1+2.jpg","time_length":"128","comment_count":"0","play_count":"0","permissions":"1","points":"0","path":"https://www.hzggedu.com/ggxydemo/Uploads/shuangxueqiannian.mp3","open_up":1,"pay":false,"title":"","content":"","collection":"1"},{"con_id":"2","title_t":"超燃片花123","small_title":"哈哈123","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/beishidaban+g3+x+en/Unit1/Lesson1/g3+beishidaban+x+u1+2.jpg","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/beishidaban+g3+x+en/Unit1/Lesson1/g3+beishidaban+x+u1+2.jpg","time_length":"128","comment_count":"0","play_count":"0","permissions":"2","points":"0","path":"https://www.hzggedu.com/ggxydemo/Uploads/reading/beishidaban+g3+x+en/Unit1/Lesson1/voice/g3+beishidaban+x+u1+3+2.mp3","open_up":1,"pay":false,"title":"","content":"","collection":"0"}]}
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
         * info : {"album_id":"1","album":{"title":"凯叔 · 三国演义","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/beishidaban+g3+x+en/Unit1/Lesson1/g3+beishidaban+x+u1+2.jpg","count":"10"},"subscript":"0"}
         * comment : []
         * list : [{"con_id":"1","title_t":"超燃片花","small_title":"哈哈","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/beishidaban+g3+x+en/Unit1/Lesson1/g3+beishidaban+x+u1+2.jpg","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/beishidaban+g3+x+en/Unit1/Lesson1/g3+beishidaban+x+u1+2.jpg","time_length":"128","comment_count":"0","play_count":"0","permissions":"1","points":"0","path":"https://www.hzggedu.com/ggxydemo/Uploads/shuangxueqiannian.mp3","open_up":1,"pay":false,"title":"","content":"","collection":"1"},{"con_id":"2","title_t":"超燃片花123","small_title":"哈哈123","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/beishidaban+g3+x+en/Unit1/Lesson1/g3+beishidaban+x+u1+2.jpg","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/beishidaban+g3+x+en/Unit1/Lesson1/g3+beishidaban+x+u1+2.jpg","time_length":"128","comment_count":"0","play_count":"0","permissions":"2","points":"0","path":"https://www.hzggedu.com/ggxydemo/Uploads/reading/beishidaban+g3+x+en/Unit1/Lesson1/voice/g3+beishidaban+x+u1+3+2.mp3","open_up":1,"pay":false,"title":"","content":"","collection":"0"}]
         */

        private InfoBean info;
        private List<PingLunData> comment;
        private List<ListBean> list;

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

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class InfoBean {
            /**
             * album_id : 1
             * album : {"title":"凯叔 · 三国演义","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/beishidaban+g3+x+en/Unit1/Lesson1/g3+beishidaban+x+u1+2.jpg","count":"10"}
             * subscript : 0
             */

            private String album_id;
            private AlbumBean album;
            private String subscript;

            public String getAlbum_id() {
                return album_id;
            }

            public void setAlbum_id(String album_id) {
                this.album_id = album_id;
            }

            public AlbumBean getAlbum() {
                return album;
            }

            public void setAlbum(AlbumBean album) {
                this.album = album;
            }

            public String getSubscript() {
                return subscript;
            }

            public void setSubscript(String subscript) {
                this.subscript = subscript;
            }

            public static class AlbumBean {
                /**
                 * title : 凯叔 · 三国演义
                 * cover : https://www.hzggedu.com/ggxydemo/Uploads/reading/beishidaban+g3+x+en/Unit1/Lesson1/g3+beishidaban+x+u1+2.jpg
                 * count : 10
                 */

                private String title;
                private String cover;
                private String count;
                private String comment_count;
                private String thumb_up_count;
                private String share_title;
                private String share_count;
                private String share_content;
                private int is_thumb_up;

                public String getShare_count() {
                    return share_count;
                }

                public void setShare_count(String share_count) {
                    this.share_count = share_count;
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

                public String getShare_title() {
                    return share_title;
                }

                public void setShare_title(String share_title) {
                    this.share_title = share_title;
                }

                public String getShare_content() {
                    return share_content;
                }

                public void setShare_content(String share_content) {
                    this.share_content = share_content;
                }

                public int getIs_thumb_up() {
                    return is_thumb_up;
                }

                public void setIs_thumb_up(int is_thumb_up) {
                    this.is_thumb_up = is_thumb_up;
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

                public String getCount() {
                    return count;
                }

                public void setCount(String count) {
                    this.count = count;
                }
            }
        }

        public static class ListBean {
            /**
             * con_id : 1
             * title_t : 超燃片花
             * small_title : 哈哈
             * cover : https://www.hzggedu.com/ggxydemo/Uploads/reading/beishidaban+g3+x+en/Unit1/Lesson1/g3+beishidaban+x+u1+2.jpg
             * pic : https://www.hzggedu.com/ggxydemo/Uploads/reading/beishidaban+g3+x+en/Unit1/Lesson1/g3+beishidaban+x+u1+2.jpg
             * time_length : 128
             * comment_count : 0
             * play_count : 0
             * permissions : 1
             * points : 0
             * path : https://www.hzggedu.com/ggxydemo/Uploads/shuangxueqiannian.mp3
             * open_up : 1
             * pay : false
             * title :
             * content :
             * collection : 1
             */

            private String con_id;
            private String title_t;
            private String small_title;
            private String cover;
            private String pic;
            private String time_length;
            private String comment_count;
            private String play_count;
            private String permissions;
            private String points;
            private String path;
            private int open_up;
            private boolean pay;
            private String title;
            private String content;
            private String collection;

            public String getCon_id() {
                return con_id;
            }

            public void setCon_id(String con_id) {
                this.con_id = con_id;
            }

            public String getTitle_t() {
                return title_t;
            }

            public void setTitle_t(String title_t) {
                this.title_t = title_t;
            }

            public String getSmall_title() {
                return small_title;
            }

            public void setSmall_title(String small_title) {
                this.small_title = small_title;
            }

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getTime_length() {
                return time_length;
            }

            public void setTime_length(String time_length) {
                this.time_length = time_length;
            }

            public String getComment_count() {
                return comment_count;
            }

            public void setComment_count(String comment_count) {
                this.comment_count = comment_count;
            }

            public String getPlay_count() {
                return play_count;
            }

            public void setPlay_count(String play_count) {
                this.play_count = play_count;
            }

            public String getPermissions() {
                return permissions;
            }

            public void setPermissions(String permissions) {
                this.permissions = permissions;
            }

            public String getPoints() {
                return points;
            }

            public void setPoints(String points) {
                this.points = points;
            }

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public int getOpen_up() {
                return open_up;
            }

            public void setOpen_up(int open_up) {
                this.open_up = open_up;
            }

            public boolean isPay() {
                return pay;
            }

            public void setPay(boolean pay) {
                this.pay = pay;
            }

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

            public String getCollection() {
                return collection;
            }

            public void setCollection(String collection) {
                this.collection = collection;
            }
        }
    }
}
