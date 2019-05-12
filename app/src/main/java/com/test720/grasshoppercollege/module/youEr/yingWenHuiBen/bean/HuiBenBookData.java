package com.test720.grasshoppercollege.module.youEr.yingWenHuiBen.bean;

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
 * Created by 水东流 on 2018/8/16.
 */

public class HuiBenBookData {


    /**
     * code : 1
     * msg : 成功
     * data : {"info":{"guan_id":"1","name":"Number","pic":"http://www.guoguoxueyuan.com/ggxy/Uploads/child_book/pic/2018-10-12/5bc05453ee6bb.jpg","click_count":"0","name0":"0","collection":"0","listen":"0","recording":"0"},"ranking":[{"my_id":"1","uid":"1252","book_id":"1","release_time":"0000-00-00 00:00:00","click_count":"0","thumb_up_count":"1","vip":0,"nickname":"虚空恶犬二千","header":"http://www.guoguoxueyuan.com/ggxy/Uploads/header/2018-10-02/5bb3901071bc3.jpg","status":"1"},{"my_id":"2","uid":"2785","book_id":"1","release_time":"0000-00-00 00:00:00","click_count":"0","thumb_up_count":"0","vip":1,"nickname":"158****7306","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg","status":"1"},{"my_id":"3","uid":"1252","book_id":"1","release_time":"0000-00-00 00:00:00","click_count":"0","thumb_up_count":"0","vip":0,"nickname":"虚空恶犬二千","header":"http://www.guoguoxueyuan.com/ggxy/Uploads/header/2018-10-02/5bb3901071bc3.jpg","status":"1"}],"zip_path":"http://www.guoguoxueyuan.com/ggxy/Uploads/child_book/book_1.zip","zip_name":"book_1.zip"}
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
         * info : {"guan_id":"1","name":"Number","pic":"http://www.guoguoxueyuan.com/ggxy/Uploads/child_book/pic/2018-10-12/5bc05453ee6bb.jpg","click_count":"0","name0":"0","collection":"0","listen":"0","recording":"0"}
         * ranking : [{"my_id":"1","uid":"1252","book_id":"1","release_time":"0000-00-00 00:00:00","click_count":"0","thumb_up_count":"1","vip":0,"nickname":"虚空恶犬二千","header":"http://www.guoguoxueyuan.com/ggxy/Uploads/header/2018-10-02/5bb3901071bc3.jpg","status":"1"},{"my_id":"2","uid":"2785","book_id":"1","release_time":"0000-00-00 00:00:00","click_count":"0","thumb_up_count":"0","vip":1,"nickname":"158****7306","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg","status":"1"},{"my_id":"3","uid":"1252","book_id":"1","release_time":"0000-00-00 00:00:00","click_count":"0","thumb_up_count":"0","vip":0,"nickname":"虚空恶犬二千","header":"http://www.guoguoxueyuan.com/ggxy/Uploads/header/2018-10-02/5bb3901071bc3.jpg","status":"1"}]
         * zip_path : http://www.guoguoxueyuan.com/ggxy/Uploads/child_book/book_1.zip
         * zip_name : book_1.zip
         */

        private InfoBean info;
        private String zip_path;
        private String zip_name;
        private String path;

        private List<RankingBean> ranking;



        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public String getZip_path() {
            return zip_path;
        }

        public void setZip_path(String zip_path) {
            this.zip_path = zip_path;
        }

        public String getZip_name() {
            return zip_name;
        }

        public void setZip_name(String zip_name) {
            this.zip_name = zip_name;
        }

        public List<RankingBean> getRanking() {
            return ranking;
        }

        public void setRanking(List<RankingBean> ranking) {
            this.ranking = ranking;
        }

        public static class InfoBean {
            /**
             * guan_id : 1
             * name : Number
             * pic : http://www.guoguoxueyuan.com/ggxy/Uploads/child_book/pic/2018-10-12/5bc05453ee6bb.jpg
             * click_count : 0
             * name0 : 0
             * collection : 0
             * listen : 0
             * recording : 0
             */

            private String guan_id;
            private String name;
            private String pic;
            private String click_count;
            private String name0;
            private String collection;
            private String listen;
            private String recording;
            private String version_number;
            public String getVersion_number() {
                return version_number;
            }

            public void setVersion_number(String version_number) {
                this.version_number = version_number;
            }
            public String getGuan_id() {
                return guan_id;
            }

            public void setGuan_id(String guan_id) {
                this.guan_id = guan_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getClick_count() {
                return click_count;
            }

            public void setClick_count(String click_count) {
                this.click_count = click_count;
            }

            public String getName0() {
                return name0;
            }

            public void setName0(String name0) {
                this.name0 = name0;
            }

            public String getCollection() {
                return collection;
            }

            public void setCollection(String collection) {
                this.collection = collection;
            }

            public String getListen() {
                return listen;
            }

            public void setListen(String listen) {
                this.listen = listen;
            }

            public String getRecording() {
                return recording;
            }

            public void setRecording(String recording) {
                this.recording = recording;
            }
        }

        public static class RankingBean {
            /**
             * my_id : 1
             * uid : 1252
             * book_id : 1
             * release_time : 0000-00-00 00:00:00
             * click_count : 0
             * thumb_up_count : 1
             * vip : 0
             * nickname : 虚空恶犬二千
             * header : http://www.guoguoxueyuan.com/ggxy/Uploads/header/2018-10-02/5bb3901071bc3.jpg
             * status : 1
             */

            private String my_id;
            private String uid;
            private String book_id;
            private String release_time;
            private String click_count;
            private String thumb_up_count;
            private String is_thumb_up;
            private int vip;
            private String nickname;
            private String header;
            private String status;

            public String getIs_thumb_up() {
                return is_thumb_up;
            }

            public void setIs_thumb_up(String is_thumb_up) {
                this.is_thumb_up = is_thumb_up;
            }

            public String getMy_id() {
                return my_id;
            }

            public void setMy_id(String my_id) {
                this.my_id = my_id;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getBook_id() {
                return book_id;
            }

            public void setBook_id(String book_id) {
                this.book_id = book_id;
            }

            public String getRelease_time() {
                return release_time;
            }

            public void setRelease_time(String release_time) {
                this.release_time = release_time;
            }

            public String getClick_count() {
                return click_count;
            }

            public void setClick_count(String click_count) {
                this.click_count = click_count;
            }

            public String getThumb_up_count() {
                return thumb_up_count;
            }

            public void setThumb_up_count(String thumb_up_count) {
                this.thumb_up_count = thumb_up_count;
            }

            public int getVip() {
                return vip;
            }

            public void setVip(int vip) {
                this.vip = vip;
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

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }
    }
}
