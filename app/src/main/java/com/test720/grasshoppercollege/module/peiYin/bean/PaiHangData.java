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
 * 作者：水东流 编于 2018/8/30
 */
public class PaiHangData {

    /**
     * code : 1
     * msg : 成功
     * data : [{"pei_id":"5","thumb_up_count":"5","header":"","uid":"2780","nickname":"176****2828","level":"1","vip":0,"vip_time":"0"},{"pei_id":"4","thumb_up_count":"4","header":"","uid":"2790","nickname":"186****6283","level":"5","vip":0,"vip_time":"0"},{"pei_id":"3","thumb_up_count":"3","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fdeae37e51.jpg","uid":"2789","nickname":"水东流","level":"11","vip":0,"vip_time":"0"},{"pei_id":"2","thumb_up_count":"2","header":"","uid":"2785","nickname":"158****7306","level":"11","vip":1,"vip_time":"4294967295"},{"pei_id":"1","thumb_up_count":"1","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg","uid":"1252","nickname":"虚空恶犬二千","level":"1","vip":0,"vip_time":"13000000"}]
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
         * pei_id : 5
         * thumb_up_count : 5
         * header :
         * uid : 2780
         * nickname : 176****2828
         * level : 1
         * vip : 0
         * vip_time : 0
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
