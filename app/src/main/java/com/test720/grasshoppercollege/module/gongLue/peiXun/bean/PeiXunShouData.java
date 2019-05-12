package com.test720.grasshoppercollege.module.gongLue.peiXun.bean;

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
 * 作者：水东流 编于 2018/8/31
 */
public class PeiXunShouData {

    /**
     * code : 1
     * msg : 成功
     * data : {"banner":[{"banner_id":"9","location_type":"2","location_link":"https://www.hzggedu.com/ggxydemo/small.php/Mall/bannerWeb/id/5","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b3217b61d8e2.jpg","name":"ddddd","g_id":"0"},{"banner_id":"10","location_type":"2","location_link":"https://www.hzggedu.com/ggxydemo/small.php/Mall/bannerWeb/id/5","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b3217b61d8e2.jpg","name":"ddddd","g_id":"0"}],"cate":[{"cate_id":"1","name":"舞蹈1","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg"},{"cate_id":"2","name":"舞蹈2","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg"},{"cate_id":"3","name":"舞蹈3","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg"},{"cate_id":"4","name":"舞蹈4","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg"},{"cate_id":"5","name":"舞蹈5","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg"},{"cate_id":"6","name":"舞蹈6","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg"},{"cate_id":"7","name":"舞蹈7","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg"},{"cate_id":"8","name":"舞蹈8","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg"}],"notice":[{"name":"今天放假","location_type":"2","location_link":"https://www.hzggedu.com/ggxydemo/small.php/StrategyTrain/announcementWeb/id/1"},{"name":"今天放假123","location_type":"2","location_link":"https://www.hzggedu.com/ggxydemo/small.php/StrategyTrain/announcementWeb/id/2"}],"recommend_school":[{"school_id":"1","name":"学校1","recommend_pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg"},{"school_id":"2","name":"学校2","recommend_pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg"},{"school_id":"3","name":"学校3","recommend_pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg"},{"school_id":"4","name":"学校4","recommend_pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg"},{"school_id":"5","name":"学校5","recommend_pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg"},{"school_id":"6","name":"学校6","recommend_pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg"}],"best_school":[{"school_id":"6","name":"学校6","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg"},{"school_id":"3","name":"学校3","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg"},{"school_id":"4","name":"学校4","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg"},{"school_id":"2","name":"学校2","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg"},{"school_id":"1","name":"学校1","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg"},{"school_id":"5","name":"学校5","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg"}]}
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
        private List<CateBean> cate;
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

        public List<CateBean> getCate() {
            return cate;
        }

        public void setCate(List<CateBean> cate) {
            this.cate = cate;
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


        public static class CateBean {
            /**
             * cate_id : 1
             * name : 舞蹈1
             * pic : https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg
             */

            private String cate_id;
            private String name;
            private String pic;

            public String getCate_id() {
                return cate_id;
            }

            public void setCate_id(String cate_id) {
                this.cate_id = cate_id;
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

        public static class RecommendSchoolBean {
            /**
             * school_id : 1
             * name : 学校1
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

        public static class BestSchoolBean {
            /**
             * school_id : 6
             * name : 学校6
             * cover : https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg
             */

            private String school_id;
            private String name;
            private String cover;

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

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }
        }
    }
}
