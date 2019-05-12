package com.test720.grasshoppercollege.module.gongLue.jiaZhang.bean;

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
 * 作者：水东流 编于 2018/10/3
 */
public class JiaZhangKeBiaoData {

    /**
     * code : 1
     * msg : 成功
     * data : [{"yuyue_id":"13","tid":"2785","service_id":"1","yuyue_time":"2018-09-26","times":"17:00","user_status":"0","nickname":"1111111","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg","service_name":"情感咨询"},{"yuyue_id":"1","tid":"2789","service_id":"1","yuyue_time":"2018-10-02","times":"19:00","user_status":"0","nickname":"33333","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg","service_name":"情感咨询"},{"yuyue_id":"2","tid":"2789","service_id":"1","yuyue_time":"2018-10-02","times":"18:00","user_status":"2","nickname":"33333","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg","service_name":"情感咨询"},{"yuyue_id":"3","tid":"2789","service_id":"1","yuyue_time":"2018-10-02","times":"17:00","user_status":"0","nickname":"33333","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg","service_name":"情感咨询"},{"yuyue_id":"4","tid":"2789","service_id":"1","yuyue_time":"2018-10-02","times":"16:00","user_status":"0","nickname":"33333","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg","service_name":"情感咨询"},{"yuyue_id":"5","tid":"2789","service_id":"1","yuyue_time":"2018-10-02","times":"15:00","user_status":"0","nickname":"33333","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg","service_name":"情感咨询"},{"yuyue_id":"6","tid":"2789","service_id":"1","yuyue_time":"2018-10-02","times":"14:00","user_status":"0","nickname":"33333","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg","service_name":"情感咨询"}]
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
         * yuyue_id : 13
         * tid : 2785
         * service_id : 1
         * yuyue_time : 2018-09-26
         * times : 17:00
         * user_status : 0
         * nickname : 1111111
         * header : https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg
         * service_name : 情感咨询
         */

        private String yuyue_id;
        private String tid;
        private String service_id;
        private String yuyue_time;
        private String times;
        private String user_status;
        private String nickname;
        private String header;
        private String service_name;

        public String getYuyue_id() {
            return yuyue_id;
        }

        public void setYuyue_id(String yuyue_id) {
            this.yuyue_id = yuyue_id;
        }

        public String getTid() {
            return tid;
        }

        public void setTid(String tid) {
            this.tid = tid;
        }

        public String getService_id() {
            return service_id;
        }

        public void setService_id(String service_id) {
            this.service_id = service_id;
        }

        public String getYuyue_time() {
            return yuyue_time;
        }

        public void setYuyue_time(String yuyue_time) {
            this.yuyue_time = yuyue_time;
        }

        public String getTimes() {
            return times;
        }

        public void setTimes(String times) {
            this.times = times;
        }

        public String getUser_status() {
            return user_status;
        }

        public void setUser_status(String user_status) {
            this.user_status = user_status;
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

        public String getService_name() {
            return service_name;
        }

        public void setService_name(String service_name) {
            this.service_name = service_name;
        }
    }
}
