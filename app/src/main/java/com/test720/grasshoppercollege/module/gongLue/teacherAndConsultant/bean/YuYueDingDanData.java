package com.test720.grasshoppercollege.module.gongLue.teacherAndConsultant.bean;

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
public class YuYueDingDanData {

    /**
     * code : 1
     * msg : 成功
     * data : {"service_id":"1","uid":"2785","name":"情感咨询","price_time_length":[{"price":"700元/次","time":"60分钟"},{"price":"400元/次","time":"30分钟"}],"orders_count":"45","nickname":"1111111","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg"}
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
         * service_id : 1
         * uid : 2785
         * name : 情感咨询
         * price_time_length : [{"price":"700元/次","time":"60分钟"},{"price":"400元/次","time":"30分钟"}]
         * orders_count : 45
         * nickname : 1111111
         * header : https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg
         */

        private String service_id;
        private String uid;
        private String name;
        private String orders_count;
        private String nickname;
        private String header;
        private String video_way;
        private String voice_way;

        public String getVideo_way() {
            return video_way;
        }

        public void setVideo_way(String video_way) {
            this.video_way = video_way;
        }

        public String getVoice_way() {
            return voice_way;
        }

        public void setVoice_way(String voice_way) {
            this.voice_way = voice_way;
        }

        private List<PriceTimeLengthBean> price_time_length;

        public String getService_id() {
            return service_id;
        }

        public void setService_id(String service_id) {
            this.service_id = service_id;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOrders_count() {
            return orders_count;
        }

        public void setOrders_count(String orders_count) {
            this.orders_count = orders_count;
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

        public List<PriceTimeLengthBean> getPrice_time_length() {
            return price_time_length;
        }

        public void setPrice_time_length(List<PriceTimeLengthBean> price_time_length) {
            this.price_time_length = price_time_length;
        }

        public static class PriceTimeLengthBean {
            /**
             * price : 700元/次
             * time : 60分钟
             */

            private String price;
            private String time;

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }
        }
    }
}
