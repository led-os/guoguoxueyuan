package com.test720.grasshoppercollege.module.gongLue.teacher.bean;

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
 * 作者：水东流 编于 2018/9/21
 */
public class TeacherListData {

    /**
     * code : 1
     * msg : 成功
     * data : [{"tid":"1252","nickname":"22222222","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg","tag":[],"good_praise":"0.00","fans":"0","consulting_number":"0","teacher_start":"0.00","consultant_start":"0.00"},{"tid":"2785","nickname":"1111111","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg","tag":["恋爱婚姻","解梦"],"good_praise":"0.98","fans":"1200","consulting_number":"10","teacher_start":"0.00","consultant_start":"0.00"},{"tid":"2789","nickname":"33333","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg","tag":[],"good_praise":"0.00","fans":"0","consulting_number":"0","teacher_start":"0.00","consultant_start":"0.00"}]
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
         * tid : 1252
         * nickname : 22222222
         * header : https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg
         * tag : []
         * good_praise : 0.00
         * fans : 0
         * consulting_number : 0
         * teacher_start : 0.00
         * consultant_start : 0.00
         */

        private String tid;
        private String nickname;
        private String header;
        private String good_praise;
        private String fans;
        private String consulting_number;
        private String teacher_start;
        private String consultant_start;
        private List<String> tag;

        public String getTid() {
            return tid;
        }

        public void setTid(String tid) {
            this.tid = tid;
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

        public String getGood_praise() {
            return good_praise;
        }

        public void setGood_praise(String good_praise) {
            this.good_praise = good_praise;
        }

        public String getFans() {
            return fans;
        }

        public void setFans(String fans) {
            this.fans = fans;
        }

        public String getConsulting_number() {
            return consulting_number;
        }

        public void setConsulting_number(String consulting_number) {
            this.consulting_number = consulting_number;
        }

        public String getTeacher_start() {
            return teacher_start;
        }

        public void setTeacher_start(String teacher_start) {
            this.teacher_start = teacher_start;
        }

        public String getConsultant_start() {
            return consultant_start;
        }

        public void setConsultant_start(String consultant_start) {
            this.consultant_start = consultant_start;
        }

        public List<String> getTag() {
            return tag;
        }

        public void setTag(List<String> tag) {
            this.tag = tag;
        }
    }
}
