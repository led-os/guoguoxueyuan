package com.test720.grasshoppercollege.module.gongLue.teacher.bean;

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
 * 作者：水东流 编于 2018/9/10
 */
public class TeacherShouData {


    /**
     * code : 1
     * msg : 成功
     * data : {"banner":[{"banner_id":"19","location_type":"2","location_link":"https://www.hzggedu.com/ggxydemo/small.php/Mall/bannerWeb/id/5","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b3217b61d8e2.jpg","name":"123","g_id":"0"},{"banner_id":"20","location_type":"2","location_link":"https://www.hzggedu.com/ggxydemo/small.php/Mall/bannerWeb/id/5","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b3217b61d8e2.jpg","name":"333","g_id":"0"},{"banner_id":"21","location_type":"2","location_link":"https://www.hzggedu.com/ggxydemo/small.php/Mall/bannerWeb/id/5","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b3217b61d8e2.jpg","name":"444","g_id":"0"}],"teacher_status":"1","consultant_status":"1","consulting":[{"headline_id":"7","title":"能否收到","cover":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fdeae37e51.jpg","thumb_up_count":"124","comment_count":"14","click_count":"22","time":"0000-00-00 00:00:00"},{"headline_id":"8","title":"dedd方法","cover":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fdeae37e51.jpg","thumb_up_count":"0","comment_count":"0","click_count":"0","time":"0000-00-00 00:00:00"}],"teacher_tuijian":[{"tid":"2785","nickname":"158****7306","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg","tag":["恋爱婚姻","解梦"],"good_praise":"0.98","fans":"1200","consulting_number":"10","teacher_start":"0.00","consultant_start":"0.00"}],"consultant_tuijian":[{"tid":"2785","nickname":"158****7306","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg","tag":["恋爱婚姻","解梦"],"good_praise":"0.98","fans":"1200","consulting_number":"10","teacher_start":"0.00","consultant_start":"0.00"}]}
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
         * banner : [{"banner_id":"19","location_type":"2","location_link":"https://www.hzggedu.com/ggxydemo/small.php/Mall/bannerWeb/id/5","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b3217b61d8e2.jpg","name":"123","g_id":"0"},{"banner_id":"20","location_type":"2","location_link":"https://www.hzggedu.com/ggxydemo/small.php/Mall/bannerWeb/id/5","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b3217b61d8e2.jpg","name":"333","g_id":"0"},{"banner_id":"21","location_type":"2","location_link":"https://www.hzggedu.com/ggxydemo/small.php/Mall/bannerWeb/id/5","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b3217b61d8e2.jpg","name":"444","g_id":"0"}]
         * teacher_status : 1
         * consultant_status : 1
         * consulting : [{"headline_id":"7","title":"能否收到","cover":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fdeae37e51.jpg","thumb_up_count":"124","comment_count":"14","click_count":"22","time":"0000-00-00 00:00:00"},{"headline_id":"8","title":"dedd方法","cover":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fdeae37e51.jpg","thumb_up_count":"0","comment_count":"0","click_count":"0","time":"0000-00-00 00:00:00"}]
         * teacher_tuijian : [{"tid":"2785","nickname":"158****7306","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg","tag":["恋爱婚姻","解梦"],"good_praise":"0.98","fans":"1200","consulting_number":"10","teacher_start":"0.00","consultant_start":"0.00"}]
         * consultant_tuijian : [{"tid":"2785","nickname":"158****7306","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg","tag":["恋爱婚姻","解梦"],"good_praise":"0.98","fans":"1200","consulting_number":"10","teacher_start":"0.00","consultant_start":"0.00"}]
         */

        private String teacher_status;
        private String search_teacher;
        private String consultant_status;
        private List<Banner> banner;
        private List<ConsultingBean> consulting;
        private List<TeacherTuijianBean> teacher_tuijian;
        private List<ConsultantTuijianBean> consultant_tuijian;

        public String getSearch_teacher() {
            return search_teacher;
        }

        public void setSearch_teacher(String search_teacher) {
            this.search_teacher = search_teacher;
        }

        public String getTeacher_status() {
            return teacher_status;
        }

        public void setTeacher_status(String teacher_status) {
            this.teacher_status = teacher_status;
        }

        public String getConsultant_status() {
            return consultant_status;
        }

        public void setConsultant_status(String consultant_status) {
            this.consultant_status = consultant_status;
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

        public List<TeacherTuijianBean> getTeacher_tuijian() {
            return teacher_tuijian;
        }

        public void setTeacher_tuijian(List<TeacherTuijianBean> teacher_tuijian) {
            this.teacher_tuijian = teacher_tuijian;
        }

        public List<ConsultantTuijianBean> getConsultant_tuijian() {
            return consultant_tuijian;
        }

        public void setConsultant_tuijian(List<ConsultantTuijianBean> consultant_tuijian) {
            this.consultant_tuijian = consultant_tuijian;
        }

        public static class ConsultingBean {
            /**
             * headline_id : 7
             * title : 能否收到
             * cover : https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fdeae37e51.jpg
             * thumb_up_count : 124
             * comment_count : 14
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

        public static class TeacherTuijianBean {
            /**
             * tid : 2785
             * nickname : 158****7306
             * header : https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg
             * tag : ["恋爱婚姻","解梦"]
             * good_praise : 0.98
             * fans : 1200
             * consulting_number : 10
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

        public static class ConsultantTuijianBean {
            /**
             * tid : 2785
             * nickname : 158****7306
             * header : https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg
             * tag : ["恋爱婚姻","解梦"]
             * good_praise : 0.98
             * fans : 1200
             * consulting_number : 10
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
}
