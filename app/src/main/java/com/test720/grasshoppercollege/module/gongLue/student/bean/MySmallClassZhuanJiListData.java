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
 * 作者：水东流 编于 2018/9/18
 */
public class MySmallClassZhuanJiListData {

    /**
     * code : 1
     * msg : 成功
     * data : [{"micro_id":"1","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg","name":"奇幻拼音","aims":"运用多种记忆方法，通过情景式教学，让孩子们在奇幻旅行中，全民啊掌握拼音只是，会认！灰度！会拼！会写!","level":"1","expiry_time":"2018-09-17 10:32:00"}]
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
         * micro_id : 1
         * cover : https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg
         * name : 奇幻拼音
         * aims : 运用多种记忆方法，通过情景式教学，让孩子们在奇幻旅行中，全民啊掌握拼音只是，会认！灰度！会拼！会写!
         * level : 1
         * expiry_time : 2018-09-17 10:32:00
         */

        private String micro_id;
        private String cover;
        private String name;
        private String aims;
        private String level;
        private String expiry_time;

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

        public String getAims() {
            return aims;
        }

        public void setAims(String aims) {
            this.aims = aims;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getExpiry_time() {
            return expiry_time;
        }

        public void setExpiry_time(String expiry_time) {
            this.expiry_time = expiry_time;
        }
    }
}
