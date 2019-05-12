package com.test720.grasshoppercollege.module.gongLue.youEr.bean;

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
 * 作者：水东流 编于 2018/9/1
 */
public class YouErJieShaoData {
    /**
     * code : 1
     * msg : 成功
     * data : {"info":{"school_id":"1","name":"学校1","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg","guanzhu":"1"},"notice":[{"name":"今天放假","location_type":"2","location_link":"https://www.hzggedu.com/ggxydemo/small.php/StrategyTrain/announcementWeb/id/1"},{"name":"今天放假123","location_type":"2","location_link":"https://www.hzggedu.com/ggxydemo/small.php/StrategyTrain/announcementWeb/id/2"}]}
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
         * info : {"school_id":"1","name":"学校1","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg","guanzhu":"1"}
         * notice : [{"name":"今天放假","location_type":"2","location_link":"https://www.hzggedu.com/ggxydemo/small.php/StrategyTrain/announcementWeb/id/1"},{"name":"今天放假123","location_type":"2","location_link":"https://www.hzggedu.com/ggxydemo/small.php/StrategyTrain/announcementWeb/id/2"}]
         */

        private InfoBean info;
        private String notice_type;
        private List<NoticeBean> notice;

        public String getNotice_type() {
            return notice_type;
        }

        public void setNotice_type(String notice_type) {
            this.notice_type = notice_type;
        }

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public List<NoticeBean> getNotice() {
            return notice;
        }

        public void setNotice(List<NoticeBean> notice) {
            this.notice = notice;
        }

        public static class InfoBean {
            /**
             * school_id : 1
             * name : 学校1
             * pic : https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg
             * guanzhu : 1
             */

            private String school_id;
            private String name;
            private String pic;
            private String guanzhu;
            private String guanzhu_time_limit;

            public String getGuanzhu_time_limit() {
                return guanzhu_time_limit;
            }

            public void setGuanzhu_time_limit(String guanzhu_time_limit) {
                this.guanzhu_time_limit = guanzhu_time_limit;
            }

            public String getSchool_id() {
                return school_id;
            }

            public void setSchool_id(String school_id) {
                this.school_id = school_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getGuanzhu() {
                return guanzhu;
            }

            public void setGuanzhu(String guanzhu) {
                this.guanzhu = guanzhu;
            }
        }

        public static class NoticeBean {
            /**
             * name : 今天放假
             * location_type : 2
             * location_link : https://www.hzggedu.com/ggxydemo/small.php/StrategyTrain/announcementWeb/id/1
             */

            private String name;
            private String location_type;
            private String location_link;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
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
}
