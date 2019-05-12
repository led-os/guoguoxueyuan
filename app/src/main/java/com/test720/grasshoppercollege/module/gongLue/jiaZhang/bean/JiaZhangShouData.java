package com.test720.grasshoppercollege.module.gongLue.jiaZhang.bean;

import com.test720.grasshoppercollege.untils.lunBo.Banner;

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
 * 作者：水东流 编于 2018/9/7
 */
public class JiaZhangShouData {

    /**
     * code : 1
     * msg : 成功
     * data : {"banner":[{"banner_id":"16","location_type":"2","location_link":"https://www.hzggedu.com/ggxydemo/small.php/Mall/bannerWeb/id/5","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b3217b61d8e2.jpg","name":"123","g_id":"0"},{"banner_id":"17","location_type":"2","location_link":"https://www.hzggedu.com/ggxydemo/small.php/Mall/bannerWeb/id/5","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b3217b61d8e2.jpg","name":"12312312222222","g_id":"0"},{"banner_id":"18","location_type":"2","location_link":"https://www.hzggedu.com/ggxydemo/small.php/Mall/bannerWeb/id/5","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b3217b61d8e2.jpg","name":"123123123","g_id":"0"}],"consulting":[{"headline_id":"1","title":"能否收到","cover":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fdeae37e51.jpg","thumb_up_count":"123","comment_count":"11","click_count":"22","time":"0000-00-00 00:00:00"},{"headline_id":"2","title":"dedd方法","cover":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fdeae37e51.jpg","thumb_up_count":"0","comment_count":"0","click_count":"0","time":"0000-00-00 00:00:00"}],"methods":[{"jiaozi_id":"1","title":"拜托提大","cover":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fdeae37e51.jpg","time":"0000-00-00 00:00:00"}],"micro":[{"micro_id":"6","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg","name":"奇幻拼音","level":"1","aims":"运用多种记忆方法，通过情景式教学，让孩子们在奇幻旅行中，全民啊掌握拼音只是，会认！灰度！会拼！会写!","subscribe_count":"1020"}],"try_listening":[{"micro_class_id":"2","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg","name":"第一课 哈哈哈","price":"2.00","subscribe_count":"100"}],"activity_zone":[{"activity_id":"2","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg","name":"少儿英语体验课","desc":"哈哈哈哈哈哈哈哈说的话阿斯顿","start_time":"2018-09-01 10:05:53","end_time":"2018-09-07 10:05:58","status":"2"}]}
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
        private List<Banner> banner;
        private List<ConsultingBean> consulting;
        private List<MethodsBean> methods;
        private List<MicroBean> micro;
        private List<TryListeningBean> try_listening;
        private List<ActivityZoneBean> activity_zone;
        private List<Curriculum> curriculum;

        public List<Curriculum> getCurriculum() {
            return curriculum;
        }

        public void setCurriculum(List<Curriculum> curriculum) {
            this.curriculum = curriculum;
        }

        public List<Banner> getBanner() {
            return banner;
        }

        public void setBanner(List<Banner> banner) {
            this.banner = banner;
        }

        public List<ConsultingBean> getConsulting() {
            return consulting;
        }

        public void setConsulting(List<ConsultingBean> consulting) {
            this.consulting = consulting;
        }

        public List<MethodsBean> getMethods() {
            return methods;
        }

        public void setMethods(List<MethodsBean> methods) {
            this.methods = methods;
        }

        public List<MicroBean> getMicro() {
            return micro;
        }

        public void setMicro(List<MicroBean> micro) {
            this.micro = micro;
        }

        public List<TryListeningBean> getTry_listening() {
            return try_listening;
        }

        public void setTry_listening(List<TryListeningBean> try_listening) {
            this.try_listening = try_listening;
        }

        public List<ActivityZoneBean> getActivity_zone() {
            return activity_zone;
        }

        public void setActivity_zone(List<ActivityZoneBean> activity_zone) {
            this.activity_zone = activity_zone;
        }

        public static class Curriculum {

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

        public static class ConsultingBean {
            /**
             * headline_id : 1
             * title : 能否收到
             * cover : https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fdeae37e51.jpg
             * thumb_up_count : 123
             * comment_count : 11
             * click_count : 22
             * time : 0000-00-00 00:00:00
             */

            private String headline_id;
            private String title;
            private String cover;
            private String thumb_up_count;
            private String comment_count;
            private String click_count;
            private String time;

            public String getHeadline_id() {
                return headline_id;
            }

            public void setHeadline_id(String headline_id) {
                this.headline_id = headline_id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
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

            public String getClick_count() {
                return click_count;
            }

            public void setClick_count(String click_count) {
                this.click_count = click_count;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }
        }

        public static class MethodsBean {
            /**
             * jiaozi_id : 1
             * title : 拜托提大
             * cover : https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fdeae37e51.jpg
             * time : 0000-00-00 00:00:00
             */

            private String jiaozi_id;
            private String cover;
            private String time;
            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getJiaozi_id() {
                return jiaozi_id;
            }

            public void setJiaozi_id(String jiaozi_id) {
                this.jiaozi_id = jiaozi_id;
            }


            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }
        }

        public static class MicroBean {
            /**
             * micro_id : 6
             * pic : https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg
             * name : 奇幻拼音
             * level : 1
             * aims : 运用多种记忆方法，通过情景式教学，让孩子们在奇幻旅行中，全民啊掌握拼音只是，会认！灰度！会拼！会写!
             * subscribe_count : 1020
             */

            private String micro_id;
            private String pic;
            private String name;
            private String level;
            private String vip_price;
            private String price;
            private String aims;
            private String subscribe_count;

            public String getVip_price() {
                return vip_price;
            }

            public void setVip_price(String vip_price) {
                this.vip_price = vip_price;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getMicro_id() {
                return micro_id;
            }

            public void setMicro_id(String micro_id) {
                this.micro_id = micro_id;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public String getAims() {
                return aims;
            }

            public void setAims(String aims) {
                this.aims = aims;
            }

            public String getSubscribe_count() {
                return subscribe_count;
            }

            public void setSubscribe_count(String subscribe_count) {
                this.subscribe_count = subscribe_count;
            }
        }

        public static class TryListeningBean {
            /**
             * micro_class_id : 2
             * pic : https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg
             * name : 第一课 哈哈哈
             * price : 2.00
             * subscribe_count : 100
             */

            private String micro_class_id;
            private String pic;
            private String name;
            private String desc;
            private String price;
            private String vip_price;
            private String subscribe_count;

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getVip_price() {
                return vip_price;
            }

            public void setVip_price(String vip_price) {
                this.vip_price = vip_price;
            }

            public String getMicro_class_id() {
                return micro_class_id;
            }

            public void setMicro_class_id(String micro_class_id) {
                this.micro_class_id = micro_class_id;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getSubscribe_count() {
                return subscribe_count;
            }

            public void setSubscribe_count(String subscribe_count) {
                this.subscribe_count = subscribe_count;
            }
        }

        public static class ActivityZoneBean {
            /**
             * activity_id : 2
             * cover : https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg
             * name : 少儿英语体验课
             * desc : 哈哈哈哈哈哈哈哈说的话阿斯顿
             * start_time : 2018-09-01 10:05:53
             * end_time : 2018-09-07 10:05:58
             * status : 2
             */

            private String activity_id;
            private String cover;
            private String name;
            private String desc;
            private String start_time;
            private String end_time;
            private String status;

            public String getActivity_id() {
                return activity_id;
            }

            public void setActivity_id(String activity_id) {
                this.activity_id = activity_id;
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

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getStart_time() {
                return start_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }
    }
}
