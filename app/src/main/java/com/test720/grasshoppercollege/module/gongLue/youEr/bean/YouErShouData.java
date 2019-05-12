package com.test720.grasshoppercollege.module.gongLue.youEr.bean;

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
 * 作者：水东流 编于 2018/9/1
 */
public class YouErShouData {

    /**
     * code : 1
     * msg : 成功
     * data : {"banner":[{"banner_id":"9","location_type":"2","location_link":"https://www.hzggedu.com/ggxydemo/small.php/Mall/bannerWeb/id/5","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b3217b61d8e2.jpg","name":"ddddd","g_id":"0"},{"banner_id":"10","location_type":"2","location_link":"https://www.hzggedu.com/ggxydemo/small.php/Mall/bannerWeb/id/5","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b3217b61d8e2.jpg","name":"ddddd","g_id":"0"}],"guanzhu":[],"notice":[{"name":"今天放假","location_type":"2","location_link":"https://www.hzggedu.com/ggxydemo/small.php/StrategyTrain/announcementWeb/id/1"},{"name":"今天放假123","location_type":"2","location_link":"https://www.hzggedu.com/ggxydemo/small.php/StrategyTrain/announcementWeb/id/2"}],"recommend_school":[{"school_id":"7","name":"学校7","recommend_pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg"},{"school_id":"8","name":"学校8","recommend_pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg"},{"school_id":"9","name":"学校9","recommend_pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg"},{"school_id":"10","name":"学校10","recommend_pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg"},{"school_id":"11","name":"学校11","recommend_pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg"},{"school_id":"12","name":"学12","recommend_pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg"}],"best_school":[{"school_id":"7","name":"学校7","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg"},{"school_id":"8","name":"学校8","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg"},{"school_id":"9","name":"学校9","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg"},{"school_id":"10","name":"学校10","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg"},{"school_id":"11","name":"学校11","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg"},{"school_id":"12","name":"学12","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg"}]}
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
        private List<GuanZhu> guanzhu;
        private List<NoticeBean> notice;
        private List<RecommendSchoolBean> recommend_school;
        private List<BestSchoolBean> best_school;
        private String zhuli;
        private String notice_type;

        public String getNotice_type() {
            return notice_type;
        }

        public void setNotice_type(String notice_type) {
            this.notice_type = notice_type;
        }

        public String getZhuli() {
            return zhuli;
        }

        public void setZhuli(String zhuli) {
            this.zhuli = zhuli;
        }

        public List<Banner> getBanner() {
            return banner;
        }

        public void setBanner(List<Banner> banner) {
            this.banner = banner;
        }

        public List<GuanZhu> getGuanzhu() {
            return guanzhu;
        }

        public void setGuanzhu(List<GuanZhu> guanzhu) {
            this.guanzhu = guanzhu;
        }

        public List<NoticeBean> getNotice() {
            return notice;
        }

        public void setNotice(List<NoticeBean> notice) {
            this.notice = notice;
        }

        public List<RecommendSchoolBean> getRecommend_school() {
            return recommend_school;
        }

        public void setRecommend_school(List<RecommendSchoolBean> recommend_school) {
            this.recommend_school = recommend_school;
        }

        public List<BestSchoolBean> getBest_school() {
            return best_school;
        }

        public void setBest_school(List<BestSchoolBean> best_school) {
            this.best_school = best_school;
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

        public static class GuanZhu {
            /**
             * name : 今天放假
             * location_type : 2
             * location_link : https://www.hzggedu.com/ggxydemo/small.php/StrategyTrain/announcementWeb/id/1
             */

            private String name;
            private String school_id;
            private String cover;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSchool_id() {
                return school_id;
            }

            public void setSchool_id(String school_id) {
                this.school_id = school_id;
            }

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }
        }

        public static class RecommendSchoolBean {
            /**
             * school_id : 7
             * name : 学校7
             * recommend_pic : https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg
             */

            private String school_id;
            private String name;
            private String recommend_pic;

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

            public String getRecommend_pic() {
                return recommend_pic;
            }

            public void setRecommend_pic(String recommend_pic) {
                this.recommend_pic = recommend_pic;
            }
        }


    }
}
