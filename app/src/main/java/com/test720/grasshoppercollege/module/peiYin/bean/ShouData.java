package com.test720.grasshoppercollege.module.peiYin.bean;

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
 * 作者：水东流 编于 2018/8/29
 */
public class ShouData {

    /**
     * code : 1
     * msg : 成功
     * data : {"banner":[{"banner_id":"8","location_type":"2","location_link":"https://www.hzggedu.com/ggxydemo/small.php/Mall/bannerWeb/id/5","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b3217b61d8e2.jpg","name":"ddddd","g_id":"0"},{"banner_id":"11","location_type":"2","location_link":"https://www.hzggedu.com/ggxydemo/small.php/Mall/bannerWeb/id/5","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b3217b61d8e2.jpg","name":"ddddd","g_id":"0"},{"banner_id":"12","location_type":"2","location_link":"https://www.hzggedu.com/ggxydemo/small.php/Mall/bannerWeb/id/5","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b3217b61d8e2.jpg","name":"ddddd","g_id":"0"}],"today":[{"con_id":"1","video_name":"围追堵截1","pic":"https://www.hzggedu.com/oldggxy/Uploads/dubbing_file/video/yixingji/chuanxuezidemao1_weizhuidujie.png"}],"every_day":[{"con_id":"2","video_name":"围追堵截2","pic":"https://www.hzggedu.com/oldggxy/Uploads/dubbing_file/video/yixingji/chuanxuezidemao1_weizhuidujie.png"}],"animation":[{"con_id":"3","video_name":"围追堵截3","pic":"https://www.hzggedu.com/oldggxy/Uploads/dubbing_file/video/yixingji/chuanxuezidemao1_weizhuidujie.png"}],"enlightenment":[{"con_id":"4","video_name":"围追堵截4","pic":"https://www.hzggedu.com/oldggxy/Uploads/dubbing_file/video/yixingji/chuanxuezidemao1_weizhuidujie.png"}],"in_class":[{"con_id":"5","video_name":"围追堵截5","pic":"https://www.hzggedu.com/oldggxy/Uploads/dubbing_file/video/yixingji/chuanxuezidemao1_weizhuidujie.png"}],"best_dubbing":[{"con_id":"6","video_name":"围追堵截6","pic":"https://www.hzggedu.com/oldggxy/Uploads/dubbing_file/video/yixingji/chuanxuezidemao1_weizhuidujie.png"}],"img":["https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg"]}
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
        private List<BannerBean> banner;
        private List<TodayBean> today;
        private List<EveryDayBean> every_day;
        private List<AnimationBean> animation;
        private List<EnlightenmentBean> enlightenment;
        private List<InClassBean> in_class;
        private List<BestDubbingBean> best_dubbing;
        private List<String> img;

        public List<BannerBean> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerBean> banner) {
            this.banner = banner;
        }

        public List<TodayBean> getToday() {
            return today;
        }

        public void setToday(List<TodayBean> today) {
            this.today = today;
        }

        public List<EveryDayBean> getEvery_day() {
            return every_day;
        }

        public void setEvery_day(List<EveryDayBean> every_day) {
            this.every_day = every_day;
        }

        public List<AnimationBean> getAnimation() {
            return animation;
        }

        public void setAnimation(List<AnimationBean> animation) {
            this.animation = animation;
        }

        public List<EnlightenmentBean> getEnlightenment() {
            return enlightenment;
        }

        public void setEnlightenment(List<EnlightenmentBean> enlightenment) {
            this.enlightenment = enlightenment;
        }

        public List<InClassBean> getIn_class() {
            return in_class;
        }

        public void setIn_class(List<InClassBean> in_class) {
            this.in_class = in_class;
        }

        public List<BestDubbingBean> getBest_dubbing() {
            return best_dubbing;
        }

        public void setBest_dubbing(List<BestDubbingBean> best_dubbing) {
            this.best_dubbing = best_dubbing;
        }

        public List<String> getImg() {
            return img;
        }

        public void setImg(List<String> img) {
            this.img = img;
        }

        public static class BannerBean {
            /**
             * banner_id : 8
             * location_type : 2
             * location_link : https://www.hzggedu.com/ggxydemo/small.php/Mall/bannerWeb/id/5
             * pic : https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b3217b61d8e2.jpg
             * name : ddddd
             * g_id : 0
             */

            private String banner_id;
            private String location_type;
            private String location_link;
            private String pic;
            private String name;
            private String g_id;

            public String getBanner_id() {
                return banner_id;
            }

            public void setBanner_id(String banner_id) {
                this.banner_id = banner_id;
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

            public String getG_id() {
                return g_id;
            }

            public void setG_id(String g_id) {
                this.g_id = g_id;
            }
        }

        public static class TodayBean {
            /**
             * con_id : 1
             * video_name : 围追堵截1
             * pic : https://www.hzggedu.com/oldggxy/Uploads/dubbing_file/video/yixingji/chuanxuezidemao1_weizhuidujie.png
             */

            private String con_id;
            private String video_name;
            private String pic;

            public String getCon_id() {
                return con_id;
            }

            public void setCon_id(String con_id) {
                this.con_id = con_id;
            }

            public String getVideo_name() {
                return video_name;
            }

            public void setVideo_name(String video_name) {
                this.video_name = video_name;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }
        }

        public static class EveryDayBean {
            /**
             * con_id : 2
             * video_name : 围追堵截2
             * pic : https://www.hzggedu.com/oldggxy/Uploads/dubbing_file/video/yixingji/chuanxuezidemao1_weizhuidujie.png
             */

            private String con_id;
            private String video_name;
            private String pic;

            public String getCon_id() {
                return con_id;
            }

            public void setCon_id(String con_id) {
                this.con_id = con_id;
            }

            public String getVideo_name() {
                return video_name;
            }

            public void setVideo_name(String video_name) {
                this.video_name = video_name;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }
        }

        public static class AnimationBean {
            /**
             * con_id : 3
             * video_name : 围追堵截3
             * pic : https://www.hzggedu.com/oldggxy/Uploads/dubbing_file/video/yixingji/chuanxuezidemao1_weizhuidujie.png
             */

            private String con_id;
            private String video_name;
            private String pic;

            public String getCon_id() {
                return con_id;
            }

            public void setCon_id(String con_id) {
                this.con_id = con_id;
            }

            public String getVideo_name() {
                return video_name;
            }

            public void setVideo_name(String video_name) {
                this.video_name = video_name;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }
        }

        public static class EnlightenmentBean {
            /**
             * con_id : 4
             * video_name : 围追堵截4
             * pic : https://www.hzggedu.com/oldggxy/Uploads/dubbing_file/video/yixingji/chuanxuezidemao1_weizhuidujie.png
             */

            private String con_id;
            private String video_name;
            private String pic;

            public String getCon_id() {
                return con_id;
            }

            public void setCon_id(String con_id) {
                this.con_id = con_id;
            }

            public String getVideo_name() {
                return video_name;
            }

            public void setVideo_name(String video_name) {
                this.video_name = video_name;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }
        }

        public static class InClassBean {
            /**
             * con_id : 5
             * video_name : 围追堵截5
             * pic : https://www.hzggedu.com/oldggxy/Uploads/dubbing_file/video/yixingji/chuanxuezidemao1_weizhuidujie.png
             */

            private String con_id;
            private String video_name;
            private String pic;

            public String getCon_id() {
                return con_id;
            }

            public void setCon_id(String con_id) {
                this.con_id = con_id;
            }

            public String getVideo_name() {
                return video_name;
            }

            public void setVideo_name(String video_name) {
                this.video_name = video_name;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }
        }

        public static class BestDubbingBean {
            /**
             * con_id : 6
             * video_name : 围追堵截6
             * pic : https://www.hzggedu.com/oldggxy/Uploads/dubbing_file/video/yixingji/chuanxuezidemao1_weizhuidujie.png
             */

            private String con_id;
            private String video_name;
            private String pic;

            public String getCon_id() {
                return con_id;
            }

            public void setCon_id(String con_id) {
                this.con_id = con_id;
            }

            public String getVideo_name() {
                return video_name;
            }

            public void setVideo_name(String video_name) {
                this.video_name = video_name;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }
        }
    }
}
