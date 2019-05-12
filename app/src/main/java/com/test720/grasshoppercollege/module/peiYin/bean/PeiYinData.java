package com.test720.grasshoppercollege.module.peiYin.bean;

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
 * 作者：水东流 编于 2018/8/29
 */
public class PeiYinData {

    /**
     * code : 1
     * msg : 成功
     * data : {"collection":"0","info":{"con_id":"1","album_id":"1","video_name":"围追堵截1","video_path":"https://www.hzggedu.com/oldggxy/Uploads/dubbing_file/video/yixingji/chuanxuezidemao1_weizhuidujie.mp4","desc":""},"list":[{"pei_id":"1","thumb_up_count":"0","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg","uid":"1252","nickname":"虚空恶犬二千","level":"1","vip":0,"vip_time":"13000000"}],"is_share":"0","share_points":"10"}
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
         * collection : 0
         * info : {"con_id":"1","album_id":"1","video_name":"围追堵截1","video_path":"https://www.hzggedu.com/oldggxy/Uploads/dubbing_file/video/yixingji/chuanxuezidemao1_weizhuidujie.mp4","desc":""}
         * list : [{"pei_id":"1","thumb_up_count":"0","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg","uid":"1252","nickname":"虚空恶犬二千","level":"1","vip":0,"vip_time":"13000000"}]
         * is_share : 0
         * share_points : 10
         */

        private String collection;
        private InfoBean info;
        private String is_share;
        private String share_points;
        private List<ListBean> list;

        public String getCollection() {
            return collection;
        }

        public void setCollection(String collection) {
            this.collection = collection;
        }

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public String getIs_share() {
            return is_share;
        }

        public void setIs_share(String is_share) {
            this.is_share = is_share;
        }

        public String getShare_points() {
            return share_points;
        }

        public void setShare_points(String share_points) {
            this.share_points = share_points;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class InfoBean {
            /**
             * con_id : 1
             * album_id : 1
             * video_name : 围追堵截1
             * video_path : https://www.hzggedu.com/oldggxy/Uploads/dubbing_file/video/yixingji/chuanxuezidemao1_weizhuidujie.mp4
             * desc :
             */

            private String con_id;
            private String album_id;
            private String video_name;
            private String video_path;
            private String desc;
            private String level;

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

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

            public String getVideo_name() {
                return video_name;
            }

            public void setVideo_name(String video_name) {
                this.video_name = video_name;
            }

            public String getVideo_path() {
                return video_path;
            }

            public void setVideo_path(String video_path) {
                this.video_path = video_path;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }
        }

        public static class ListBean {
            /**
             * pei_id : 1
             * thumb_up_count : 0
             * header : https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg
             * uid : 1252
             * nickname : 虚空恶犬二千
             * level : 1
             * vip : 0
             * vip_time : 13000000
             */

            private String pei_id;
            private String thumb_up_count;
            private String header;
            private String uid;
            private String nickname;
            private String level;
            private int vip;
            private String vip_time;

            public String getPei_id() {
                return pei_id;
            }

            public void setPei_id(String pei_id) {
                this.pei_id = pei_id;
            }

            public String getThumb_up_count() {
                return thumb_up_count;
            }

            public void setThumb_up_count(String thumb_up_count) {
                this.thumb_up_count = thumb_up_count;
            }

            public String getHeader() {
                return header;
            }

            public void setHeader(String header) {
                this.header = header;
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

            public String getVip_time() {
                return vip_time;
            }

            public void setVip_time(String vip_time) {
                this.vip_time = vip_time;
            }
        }
    }
}
