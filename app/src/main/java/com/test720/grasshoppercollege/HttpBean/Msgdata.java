package com.test720.grasshoppercollege.HttpBean;

import java.util.List;

/**
 * Created by 水东流 on 2018/4/19.
 */

public class Msgdata {


    /**
     * code : 1
     * msg : 成功
     * data : [{"msg_id":"13","title":"qqqq111","pic":"https://www.hzggedu.com/ggxytest/Uploads/ads/2018-06-27/5b33395fa9b97.jpg","end_time":"0000-00-00 00:00:00","release_time":"2018-06-27 15:14:57","location_type":"2","location_link":"https://www.hzggedu.com/ggxytest/small.php/IndexUser/adsInfo"},{"msg_id":"12","title":"oooooooooo","pic":"https://www.hzggedu.com/ggxytest/Uploads/ads/2018-06-25/5b309159df2c4.jpg","end_time":"0000-00-00 00:00:00","release_time":"2018-06-25 14:54:17","location_type":"3","location_link":"http://www.163.com"},{"msg_id":"11","title":"","pic":"https://www.hzggedu.com/ggxytest/Uploads/ads/2018-06-22/5b2c92266c529.jpg","end_time":"0000-00-00 00:00:00","release_time":"2018-06-22 14:08:34","location_type":"3","location_link":"http://www.163.com/"},{"msg_id":"10","title":"10","pic":"https://www.hzggedu.com/ggxytest/Uploads/ads/2018-06-22/5b2c9203879f6.jpg","end_time":"0000-00-00 00:00:00","release_time":"2018-06-22 14:08:26","location_type":"2","location_link":"https://www.hzggedu.com/ggxytest/small.php/IndexUser/adsInfo"},{"msg_id":"9","title":"9","pic":"https://www.hzggedu.com/ggxytest/Uploads/ads/2018-06-22/5b2c91f841b67.jpg","end_time":"0000-00-00 00:00:00","release_time":"2018-06-22 14:08:25","location_type":"2","location_link":"https://www.hzggedu.com/ggxytest/small.php/IndexUser/adsInfo"},{"msg_id":"8","title":"8","pic":"https://www.hzggedu.com/ggxytest/Uploads/ads/2018-06-22/5b2c91e4e0845.jpg","end_time":"0000-00-00 00:00:00","release_time":"2018-06-22 14:08:24","location_type":"2","location_link":"https://www.hzggedu.com/ggxytest/small.php/IndexUser/adsInfo"},{"msg_id":"7","title":"7","pic":"https://www.hzggedu.com/ggxytest/Uploads/ads/2018-06-22/5b2c91dadb075.jpg","end_time":"0000-00-00 00:00:00","release_time":"2018-06-22 14:08:19","location_type":"2","location_link":"https://www.hzggedu.com/ggxytest/small.php/IndexUser/adsInfo"},{"msg_id":"6","title":"6","pic":"https://www.hzggedu.com/ggxytest/Uploads/ads/2018-06-22/5b2c91cdbfc7a.jpg","end_time":"0000-00-00 00:00:00","release_time":"2018-06-22 14:08:18","location_type":"2","location_link":"https://www.hzggedu.com/ggxytest/small.php/IndexUser/adsInfo"},{"msg_id":"5","title":"5","pic":"https://www.hzggedu.com/ggxytest/Uploads/ads/2018-06-22/5b2c91c065ddc.jpg","end_time":"0000-00-00 00:00:00","release_time":"2018-06-22 14:08:07","location_type":"2","location_link":"https://www.hzggedu.com/ggxytest/small.php/IndexUser/adsInfo"},{"msg_id":"4","title":"4","pic":"https://www.hzggedu.com/ggxytest/Uploads/ads/2018-06-22/5b2c91a73fa67.jpg","end_time":"0000-00-00 00:00:00","release_time":"2018-06-22 14:08:04","location_type":"2","location_link":"https://www.hzggedu.com/ggxytest/small.php/IndexUser/adsInfo"}]
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
         * msg_id : 13
         * title : qqqq111
         * pic : https://www.hzggedu.com/ggxytest/Uploads/ads/2018-06-27/5b33395fa9b97.jpg
         * end_time : 0000-00-00 00:00:00
         * release_time : 2018-06-27 15:14:57
         * location_type : 2
         * location_link : https://www.hzggedu.com/ggxytest/small.php/IndexUser/adsInfo
         */

        private String msg_id;
        private String title;
        private String pic;
        private String end_time;
        private String release_time;
        private String location_type;
        private String location_link;

        public String getMsg_id() {
            return msg_id;
        }

        public void setMsg_id(String msg_id) {
            this.msg_id = msg_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getRelease_time() {
            return release_time;
        }

        public void setRelease_time(String release_time) {
            this.release_time = release_time;
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
    }
}
