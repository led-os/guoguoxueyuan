package com.test720.grasshoppercollege.module.peiYin.bean;

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
 * 作者：水东流 编于 2018/8/31
 */
public class PeiYinPinLuListData {

    /**
     * code : 1
     * msg : 成功
     * data : {"info":{"con_id":"1","playback":"3","video_path":"https://www.hzggedu.com/oldggxy/Uploads/dubbing_file/video/yixingji/chuanxuezidemao1_weizhuidujie.mp4","video_name":"围追堵截1","upload_time":"2018-08-27 11:49:57","thumb_up_count":"2","comment_count":"0","type":"0","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg","nickname":"虚空恶犬二千","thumb_up":"1"},"comment":[],"is_share":"0","share_points":"10"}
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
         * info : {"con_id":"1","playback":"3","video_path":"https://www.hzggedu.com/oldggxy/Uploads/dubbing_file/video/yixingji/chuanxuezidemao1_weizhuidujie.mp4","video_name":"围追堵截1","upload_time":"2018-08-27 11:49:57","thumb_up_count":"2","comment_count":"0","type":"0","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg","nickname":"虚空恶犬二千","thumb_up":"1"}
         * comment : []
         * is_share : 0
         * share_points : 10
         */

        private InfoBean info;
        private String is_share;
        private String share_points;
        private List<PingLunData> comment;

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

        public List<PingLunData> getComment() {
            return comment;
        }

        public void setComment(List<PingLunData> comment) {
            this.comment = comment;
        }

        public static class InfoBean {
            /**
             * con_id : 1
             * playback : 3
             * video_path : https://www.hzggedu.com/oldggxy/Uploads/dubbing_file/video/yixingji/chuanxuezidemao1_weizhuidujie.mp4
             * video_name : 围追堵截1
             * upload_time : 2018-08-27 11:49:57
             * thumb_up_count : 2
             * comment_count : 0
             * type : 0
             * header : https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg
             * nickname : 虚空恶犬二千
             * thumb_up : 1
             */

            private String con_id;
            private String playback;
            private String video_path;
            private String video_name;
            private String upload_time;
            private String thumb_up_count;
            private String comment_count;
            private String type;
            private String header;
            private String nickname;
            private String thumb_up;

            public String getCon_id() {
                return con_id;
            }

            public void setCon_id(String con_id) {
                this.con_id = con_id;
            }

            public String getPlayback() {
                return playback;
            }

            public void setPlayback(String playback) {
                this.playback = playback;
            }

            public String getVideo_path() {
                return video_path;
            }

            public void setVideo_path(String video_path) {
                this.video_path = video_path;
            }

            public String getVideo_name() {
                return video_name;
            }

            public void setVideo_name(String video_name) {
                this.video_name = video_name;
            }

            public String getUpload_time() {
                return upload_time;
            }

            public void setUpload_time(String upload_time) {
                this.upload_time = upload_time;
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

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

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

            public String getThumb_up() {
                return thumb_up;
            }

            public void setThumb_up(String thumb_up) {
                this.thumb_up = thumb_up;
            }
        }
    }
}
