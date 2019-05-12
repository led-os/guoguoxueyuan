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
public class ZhuanJiListData {


    /**
     * code : 1
     * msg : 成功
     * data : [{"album_id":"1","name":"哈哈哈","cover":"https://www.hzggedu.com/oldggxy/Uploads/dubbing_file/video/yixingji/chuanxuezidemao1_weizhuidujie.png","playback":"0"}]
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
         * album_id : 1
         * name : 哈哈哈
         * cover : https://www.hzggedu.com/oldggxy/Uploads/dubbing_file/video/yixingji/chuanxuezidemao1_weizhuidujie.png
         * playback : 0
         */

        private String album_id;
        private String name;
        private String cover;
        private String playback;

        public String getAlbum_id() {
            return album_id;
        }

        public void setAlbum_id(String album_id) {
            this.album_id = album_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getPlayback() {
            return playback;
        }

        public void setPlayback(String playback) {
            this.playback = playback;
        }
    }
}
