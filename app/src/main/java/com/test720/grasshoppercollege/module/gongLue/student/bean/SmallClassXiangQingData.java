package com.test720.grasshoppercollege.module.gongLue.student.bean;

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
 * 作者：水东流 编于 2018/9/6
 */
public class SmallClassXiangQingData {

    /**
     * code : 1
     * msg : 成功
     * data : {"name":"奇幻拼音","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg","aims":"运用多种记忆方法，通过情景式教学，让孩子们在奇幻旅行中，全民啊掌握拼音只是，会认！灰度！会拼！会写!","subscribe_count":"1020","vip_price":"90.00","price":"100.00","comment_count":"3","thumb_up_count":"2","expiry_date":"180","course_desc":"https://www.hzggedu.com/ggxydemo/small.php/Strategy/microCourseDesc/micro_id/1","common_question":"https://www.hzggedu.com/ggxydemo/small.php/Strategy/microQuestion/micro_id/1","address":[],"vip":{"vip_price":"0.01","vip":0},"course":[{"micro_class_id":"1","permissions":"4","points":"0","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg","name":"第一课 哈哈哈","desc":"不是大部分是的","state":"1","path":"https://www.hzggedu.com/ggxydemo/Uploads/math_animation/video/No.mp4","pay":false,"open_up":1,"title":"","content":""},{"micro_class_id":"2","permissions":"4","points":"0","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg","name":"第一课 哈哈哈","desc":"不是大部分是的","state":"1","path":"https://www.hzggedu.com/ggxydemo/Uploads/math_animation/video/No.mp4","pay":false,"open_up":1,"title":"","content":""}]}
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
         * name : 奇幻拼音
         * pic : https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg
         * aims : 运用多种记忆方法，通过情景式教学，让孩子们在奇幻旅行中，全民啊掌握拼音只是，会认！灰度！会拼！会写!
         * subscribe_count : 1020
         * vip_price : 90.00
         * price : 100.00
         * comment_count : 3
         * thumb_up_count : 2
         * expiry_date : 180
         * course_desc : https://www.hzggedu.com/ggxydemo/small.php/Strategy/microCourseDesc/micro_id/1
         * common_question : https://www.hzggedu.com/ggxydemo/small.php/Strategy/microQuestion/micro_id/1
         * address : []
         * vip : {"vip_price":"0.01","vip":0}
         * course : [{"micro_class_id":"1","permissions":"4","points":"0","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg","name":"第一课 哈哈哈","desc":"不是大部分是的","state":"1","path":"https://www.hzggedu.com/ggxydemo/Uploads/math_animation/video/No.mp4","pay":false,"open_up":1,"title":"","content":""},{"micro_class_id":"2","permissions":"4","points":"0","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg","name":"第一课 哈哈哈","desc":"不是大部分是的","state":"1","path":"https://www.hzggedu.com/ggxydemo/Uploads/math_animation/video/No.mp4","pay":false,"open_up":1,"title":"","content":""}]
         */

        private String name;
        private String type;
        private String pic;
        private String aims;
        private String subscribe_count;
        private String vip_price;
        private String price;
        private String comment_count;
        private String thumb_up_count;
        private String expiry_date;
        private String course_desc;
        private String common_question;
        private String contact_customer;
        private String is_pay;
        private VipBean vip;
        private List<?> address;
        private List<CourseBean> course;

        public String getIs_pay() {
            return is_pay;
        }

        public void setIs_pay(String is_pay) {
            this.is_pay = is_pay;
        }

        public String getContact_customer() {
            return contact_customer;
        }

        public void setContact_customer(String contact_customer) {
            this.contact_customer = contact_customer;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
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

        public String getComment_count() {
            return comment_count;
        }

        public void setComment_count(String comment_count) {
            this.comment_count = comment_count;
        }

        public String getThumb_up_count() {
            return thumb_up_count;
        }

        public void setThumb_up_count(String thumb_up_count) {
            this.thumb_up_count = thumb_up_count;
        }

        public String getExpiry_date() {
            return expiry_date;
        }

        public void setExpiry_date(String expiry_date) {
            this.expiry_date = expiry_date;
        }

        public String getCourse_desc() {
            return course_desc;
        }

        public void setCourse_desc(String course_desc) {
            this.course_desc = course_desc;
        }

        public String getCommon_question() {
            return common_question;
        }

        public void setCommon_question(String common_question) {
            this.common_question = common_question;
        }

        public VipBean getVip() {
            return vip;
        }

        public void setVip(VipBean vip) {
            this.vip = vip;
        }

        public List<?> getAddress() {
            return address;
        }

        public void setAddress(List<?> address) {
            this.address = address;
        }

        public List<CourseBean> getCourse() {
            return course;
        }

        public void setCourse(List<CourseBean> course) {
            this.course = course;
        }

        public static class VipBean {
            /**
             * vip_price : 0.01
             * vip : 0
             */

            private String vip_price;
            private int vip;

            public String getVip_price() {
                return vip_price;
            }

            public void setVip_price(String vip_price) {
                this.vip_price = vip_price;
            }

            public int getVip() {
                return vip;
            }

            public void setVip(int vip) {
                this.vip = vip;
            }
        }

        public static class CourseBean {
            /**
             * micro_class_id : 1
             * permissions : 4
             * points : 0
             * pic : https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg
             * name : 第一课 哈哈哈
             * desc : 不是大部分是的
             * state : 1
             * path : https://www.hzggedu.com/ggxydemo/Uploads/math_animation/video/No.mp4
             * pay : false
             * open_up : 1
             * title :
             * content :
             */

            private String micro_class_id;
            private String permissions;
            private String points;
            private String pic;
            private String name;
            private String desc;
            private String state;
            private String path;
            private boolean pay;
            private int open_up;
            private String title;
            private String content;

            public String getMicro_class_id() {
                return micro_class_id;
            }

            public void setMicro_class_id(String micro_class_id) {
                this.micro_class_id = micro_class_id;
            }

            public String getPermissions() {
                return permissions;
            }

            public void setPermissions(String permissions) {
                this.permissions = permissions;
            }

            public String getPoints() {
                return points;
            }

            public void setPoints(String points) {
                this.points = points;
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

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public boolean isPay() {
                return pay;
            }

            public void setPay(boolean pay) {
                this.pay = pay;
            }

            public int getOpen_up() {
                return open_up;
            }

            public void setOpen_up(int open_up) {
                this.open_up = open_up;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }
    }
}
