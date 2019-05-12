package com.test720.grasshoppercollege.module.youEr.baoBaoTingTing.bean;

import com.test720.grasshoppercollege.untils.lunBo.Banner;

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
public class BaoBaoTingTingShouData {


    /**
     * code : 1
     * msg : 成功
     * data : {"banner":[{"banner_id":"57","location_type":"1","location_link":"","pic":"http://www.guoguoxueyuan.com/ggxy/Uploads/ads/2018-10-30/5bd7f37632d11.jpg","name":"","g_id":"0","gid":"0"},{"banner_id":"68","location_type":"2","location_link":"http://www.guoguoxueyuan.com/ggxy/small.php/Mall/bannerWeb/id/68","pic":"http://www.guoguoxueyuan.com/ggxy/Uploads/ads/2018-11-09/5be4e181a1b2b.jpg","name":"","g_id":"0","gid":"0"},{"banner_id":"78","location_type":"3","location_link":"","pic":"http://www.guoguoxueyuan.com/ggxy/Uploads/ads/2018-11-09/5be4e3646b047.jpg","name":"","g_id":"51","gid":"51"}],"best_content":[{"con_id":"1","album_id":"1","title_t":"三国01","small_title":"三国01表浅","cover":"http://www.guoguoxueyuan.com/ggxy/Uploads/story/pic/2018-10-31/5bd95ec7a2f58.jpg","permissions":"1","points":"0","open_up":1,"pay":false,"title":"","content":""},{"con_id":"8","album_id":"2","title_t":"adsfasdfadfsadf","small_title":"sadfasdf","cover":"http://www.guoguoxueyuan.com/ggxy/Uploads/story/pic/2018-11-08/5be3a75588eaa.jpg","permissions":"2","points":"0","open_up":0,"pay":false,"title":"","content":""}],"tuijian_album":[{"album_id":"1","title":"三国演义","small_title":"三国时一个时代小标签","cover":"http://www.guoguoxueyuan.com/ggxy/Uploads/story/pic/2018-10-31/5bd95fda2828a.jpg","count":"5"}],"best_album":[]}
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
        private List<Banner> banner;
        private List<BestContentBean> best_content;
        private List<TuijianAlbumBean> tuijian_album;

        public List<Banner> getBanner() {
            return banner;
        }

        public void setBanner(List<Banner> banner) {
            this.banner = banner;
        }

        public List<BestContentBean> getBest_content() {
            return best_content;
        }

        public void setBest_content(List<BestContentBean> best_content) {
            this.best_content = best_content;
        }

        public List<TuijianAlbumBean> getTuijian_album() {
            return tuijian_album;
        }

        public void setTuijian_album(List<TuijianAlbumBean> tuijian_album) {
            this.tuijian_album = tuijian_album;
        }



        public static class BannerBean {
            /**
             * banner_id : 57
             * location_type : 1
             * location_link :
             * pic : http://www.guoguoxueyuan.com/ggxy/Uploads/ads/2018-10-30/5bd7f37632d11.jpg
             * name :
             * g_id : 0
             * gid : 0
             */

            private String banner_id;
            private String location_type;
            private String location_link;
            private String pic;
            private String name;
            private String g_id;
            private String gid;

            public String getBanner_id() {
                return banner_id;
            }

            public void setBanner_id(String banner_id) {
                this.banner_id = banner_id;
            }

            public String getLocation_type() {
                return location_type;
            }

            public void setLocation_type(String location_type) {
                this.location_type = location_type;
            }

            public String getLocation_link() {
                return location_link;
            }

            public void setLocation_link(String location_link) {
                this.location_link = location_link;
            }

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

            public String getG_id() {
                return g_id;
            }

            public void setG_id(String g_id) {
                this.g_id = g_id;
            }

            public String getGid() {
                return gid;
            }

            public void setGid(String gid) {
                this.gid = gid;
            }
        }

        public static class BestContentBean {
            /**
             * con_id : 1
             * album_id : 1
             * title_t : 三国01
             * small_title : 三国01表浅
             * cover : http://www.guoguoxueyuan.com/ggxy/Uploads/story/pic/2018-10-31/5bd95ec7a2f58.jpg
             * permissions : 1
             * points : 0
             * open_up : 1
             * pay : false
             * title :
             * content :
             */

            private String con_id;
            private String album_id;
            private String title_t;
            private String small_title;
            private String cover;
            private String permissions;
            private String points;
            private int open_up;
            private boolean pay;
            private String title;
            private String content;

            public String getCon_id() {
                return con_id;
            }

            public void setCon_id(String con_id) {
                this.con_id = con_id;
            }

            public String getAlbum_id() {
                return album_id;
            }

            public void setAlbum_id(String album_id) {
                this.album_id = album_id;
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
        }

        public static class TuijianAlbumBean {
            /**
             * album_id : 1
             * title : 三国演义
             * small_title : 三国时一个时代小标签
             * cover : http://www.guoguoxueyuan.com/ggxy/Uploads/story/pic/2018-10-31/5bd95fda2828a.jpg
             * count : 5
             */

            private String album_id;
            private String title;
            private String small_title;
            private String cover;
            private String count;

            public String getAlbum_id() {
                return album_id;
            }

            public void setAlbum_id(String album_id) {
                this.album_id = album_id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
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

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }
        }
    }
}
