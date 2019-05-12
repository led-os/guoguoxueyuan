package com.test720.grasshoppercollege.module.gongLue.student.bean;

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
 * 作者：水东流 编于 2018/9/5
 */
public class StudentShouData {


    /**
     * code : 1
     * msg : 成功
     * data : {"banner":[{"banner_id":"7","location_type":"3","location_link":"https://www.hzggedu.com/ggxydemo/small.php/Mall/bannerWeb/id/5","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b3217b61d8e2.jpg","name":"ddddd","g_id":"1","gid":"1"},{"banner_id":"13","location_type":"2","location_link":"https://www.hzggedu.com/ggxydemo/small.php/Mall/bannerWeb/id/5","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b3217b61d8e2.jpg","name":"ddddd","g_id":"0","gid":"0"},{"banner_id":"14","location_type":"2","location_link":"https://www.hzggedu.com/ggxydemo/small.php/Mall/bannerWeb/id/5","pic":"http://www.guoguoxueyuan.com/ggxy/Uploads/ads/2018-09-27/5bac94ef95eef.jpg","name":"ddddd","g_id":"0","gid":"0"}],"online":[{"line_id":"1","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg","name":"未来咩学123","sign_count":"1000","vip_price":"1000.00","price":"1200.00","course_number":"15"}],"underline":[{"line_id":"6","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg","name":"未来咩学","sign_count":"1000","vip_price":"1000.00","price":"1200.00","course_number":"15"}],"micro":[{"micro_id":"1","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg","price":"100.00","name":"奇幻拼音","level":"1","aims":"运用多种记忆方法，通过情景式教学，让孩子们在奇幻旅行中，全民啊掌握拼音只是，会认！灰度！会拼！会写!","subscribe_count":"1020"}],"try_listening":[{"micro_class_id":"1","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg","name":"第一课 哈哈哈","state":"1","price":"2.00","subscribe_count":"100","desc":"不是大部分是的","level":"0"},{"micro_class_id":"2","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg","name":"第二课 哈哈哈","state":"1","price":"2.00","subscribe_count":"100","desc":"反对反对顶顶顶","level":"0"}],"activity_zone":[{"activity_id":"1","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg","name":"少儿英语体验课","desc":"哈哈哈哈哈哈哈哈说的话阿斯顿","start_time":"2018-09-01 10:05:53","end_time":"2018-09-07 10:05:58","status":"2"}],"curriculum":[{"class_id":"9","tid":"0","line_id":"1","course_name":"未来咩学","class_time":"2018-10-08","week":"周五","times":"19:00","status":"0","teacher_name":"1111111","teacher_header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg"},{"class_id":"10","tid":"0","line_id":"1","course_name":"未来咩学","class_time":"2018-10-09","week":"周五","times":"19:00","status":"0","teacher_name":"1111111","teacher_header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg"}]}
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
        private List<OnlineBean> online;
        private List<OnlineBean> underline;
        private List<MicroBean> micro;
        private List<TryListeningBean> try_listening;
        private List<ActivityZoneBean> activity_zone;
        private List<CurriculumBean> curriculum;

        public List<Banner> getBanner() {
            return banner;
        }

        public void setBanner(List<Banner> banner) {
            this.banner = banner;
        }

        public List<OnlineBean> getOnline() {
            return online;
        }

        public void setOnline(List<OnlineBean> online) {
            this.online = online;
        }

        public List<OnlineBean> getUnderline() {
            return underline;
        }

        public void setUnderline(List<OnlineBean> underline) {
            this.underline = underline;
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

        public List<CurriculumBean> getCurriculum() {
            return curriculum;
        }

        public void setCurriculum(List<CurriculumBean> curriculum) {
            this.curriculum = curriculum;
        }


        public static class OnlineBean {
            /**
             * line_id : 1
             * cover : https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg
             * name : 未来咩学123
             * sign_count : 1000
             * vip_price : 1000.00
             * price : 1200.00
             * course_number : 15
             */

            private String line_id;
            private String cover;
            private String name;
            private String aims;
            private String sign_count;
            private String vip_price;
            private String price;
            private String course_number;

            public String getAims() {
                return aims;
            }

            public void setAims(String aims) {
                this.aims = aims;
            }

            public String getLine_id() {
                return line_id;
            }

            public void setLine_id(String line_id) {
                this.line_id = line_id;
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

            public String getSign_count() {
                return sign_count;
            }

            public void setSign_count(String sign_count) {
                this.sign_count = sign_count;
            }

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

            public String getCourse_number() {
                return course_number;
            }

            public void setCourse_number(String course_number) {
                this.course_number = course_number;
            }
        }

        public static class MicroBean {
            /**
             * micro_id : 1
             * pic : https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg
             * price : 100.00
             * name : 奇幻拼音
             * level : 1
             * aims : 运用多种记忆方法，通过情景式教学，让孩子们在奇幻旅行中，全民啊掌握拼音只是，会认！灰度！会拼！会写!
             * subscribe_count : 1020
             */

            private String micro_id;
            private String pic;
            private String price;
            private String vip_price;
            private String name;
            private String level;
            private String aims;
            private String subscribe_count;

            public String getVip_price() {
                return vip_price;
            }

            public void setVip_price(String vip_price) {
                this.vip_price = vip_price;
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

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
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
             * micro_class_id : 1
             * pic : https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg
             * name : 第一课 哈哈哈
             * state : 1
             * price : 2.00
             * subscribe_count : 100
             * desc : 不是大部分是的
             * level : 0
             */

            private String micro_class_id;
            private String pic;
            private String name;
            private String state;
            private String price;
            private String vip_price;
            private String subscribe_count;
            private String desc;
            private String level;

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

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
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

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }
        }

        public static class ActivityZoneBean {
            /**
             * activity_id : 1
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

        public static class CurriculumBean {
            /**
             * class_id : 9
             * tid : 0
             * line_id : 1
             * course_name : 未来咩学
             * class_time : 2018-10-08
             * week : 周五
             * times : 19:00
             * status : 0
             * teacher_name : 1111111
             * teacher_header : https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg
             */

            private String class_id;
            private String tid;
            private String line_id;
            private String course_name;
            private String class_time;
            private String week;
            private String times;
            private String status;
            private String teacher_name;
            private String teacher_header;

            public String getClass_id() {
                return class_id;
            }

            public void setClass_id(String class_id) {
                this.class_id = class_id;
            }

            public String getTid() {
                return tid;
            }

            public void setTid(String tid) {
                this.tid = tid;
            }

            public String getLine_id() {
                return line_id;
            }

            public void setLine_id(String line_id) {
                this.line_id = line_id;
            }

            public String getCourse_name() {
                return course_name;
            }

            public void setCourse_name(String course_name) {
                this.course_name = course_name;
            }

            public String getClass_time() {
                return class_time;
            }

            public void setClass_time(String class_time) {
                this.class_time = class_time;
            }

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public String getTimes() {
                return times;
            }

            public void setTimes(String times) {
                this.times = times;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getTeacher_name() {
                return teacher_name;
            }

            public void setTeacher_name(String teacher_name) {
                this.teacher_name = teacher_name;
            }

            public String getTeacher_header() {
                return teacher_header;
            }

            public void setTeacher_header(String teacher_header) {
                this.teacher_header = teacher_header;
            }
        }
    }
}
