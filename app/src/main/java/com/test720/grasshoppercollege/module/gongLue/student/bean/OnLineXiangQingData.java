package com.test720.grasshoppercollege.module.gongLue.student.bean;

import com.test720.grasshoppercollege.HttpBean.PingLunData;

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
public class OnLineXiangQingData {


    /**
     * code : 1
     * msg : 成功
     * data : {"type":"56","name":"未来咩学","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg","aims":"全面掌握拼音知识","sign_count":"1000","course_number":"15","vip_price":"1000.00","price":"1200.00","comment_count":"12","thumb_up_count":"23","times":"60","is_mail":"1","close_pay":"0","course_desc":"https://www.hzggedu.com/ggxydemo/small.php/Strategy/lineCourseDesc/line_id/1","teacher_desc":"https://www.hzggedu.com/ggxydemo/small.php/Strategy/teacherDesc/line_id/1","common_question":"https://www.hzggedu.com/ggxydemo/small.php/Strategy/lineQuestion/line_id/1/type/56","week":{"week":[{"week":"6","time_slot":["19：00"]},{"week":"7","time_slot":["20：00"]}],"start_time":"2018-09-05 10:30:14","end_time":"2018-12-04 10:30:21"},"comment":[{"comment_id":"3","uid":"1252","content":"发士大夫士大夫第三方速度7686734","time":"2018-09-04 10:36:52","thumb_up_count":"2","comment_count":"3","is_thumb_up":0,"img":[],"level":"1","vip":0,"honor":"","nickname":"虚空恶犬二千","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg","child":[{"comment_id":"4","uid":"2789","content":"个v搞不好","time":"2018-09-06 16:21:58","img":[],"level":"11","vip":0,"honor":"","nickname":"水东流","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fdeae37e51.jpg"}]},{"comment_id":"2","uid":"1252","content":"发士大夫士大夫第三方速度4566","time":"2018-09-04 10:36:52","thumb_up_count":"1","comment_count":"2","is_thumb_up":0,"img":[],"level":"1","vip":0,"honor":"","nickname":"虚空恶犬二千","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg","child":[]},{"comment_id":"1","uid":"1252","content":"发士大夫士大夫第三方速度123","time":"2018-09-04 10:36:52","thumb_up_count":"1","comment_count":"2","is_thumb_up":0,"img":[],"level":"1","vip":0,"honor":"","nickname":"虚空恶犬二千","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg","child":[]}],"address":{"address_id":"4","consignee":"tuwl","phone":"18200236361","province":"北京市","city":"北京市","area":"东城区","other":"hllo"},"vip":{"vip_price":"0.01","vip":0}}
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
         * type : 56
         * name : 未来咩学
         * pic : https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg
         * aims : 全面掌握拼音知识
         * sign_count : 1000
         * course_number : 15
         * vip_price : 1000.00
         * price : 1200.00
         * comment_count : 12
         * thumb_up_count : 23
         * times : 60
         * is_mail : 1
         * close_pay : 0
         * course_desc : https://www.hzggedu.com/ggxydemo/small.php/Strategy/lineCourseDesc/line_id/1
         * teacher_desc : https://www.hzggedu.com/ggxydemo/small.php/Strategy/teacherDesc/line_id/1
         * common_question : https://www.hzggedu.com/ggxydemo/small.php/Strategy/lineQuestion/line_id/1/type/56
         * week : {"week":[{"week":"6","time_slot":["19：00"]},{"week":"7","time_slot":["20：00"]}],"start_time":"2018-09-05 10:30:14","end_time":"2018-12-04 10:30:21"}
         * comment : [{"comment_id":"3","uid":"1252","content":"发士大夫士大夫第三方速度7686734","time":"2018-09-04 10:36:52","thumb_up_count":"2","comment_count":"3","is_thumb_up":0,"img":[],"level":"1","vip":0,"honor":"","nickname":"虚空恶犬二千","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg","child":[{"comment_id":"4","uid":"2789","content":"个v搞不好","time":"2018-09-06 16:21:58","img":[],"level":"11","vip":0,"honor":"","nickname":"水东流","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fdeae37e51.jpg"}]},{"comment_id":"2","uid":"1252","content":"发士大夫士大夫第三方速度4566","time":"2018-09-04 10:36:52","thumb_up_count":"1","comment_count":"2","is_thumb_up":0,"img":[],"level":"1","vip":0,"honor":"","nickname":"虚空恶犬二千","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg","child":[]},{"comment_id":"1","uid":"1252","content":"发士大夫士大夫第三方速度123","time":"2018-09-04 10:36:52","thumb_up_count":"1","comment_count":"2","is_thumb_up":0,"img":[],"level":"1","vip":0,"honor":"","nickname":"虚空恶犬二千","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg","child":[]}]
         * address : {"address_id":"4","consignee":"tuwl","phone":"18200236361","province":"北京市","city":"北京市","area":"东城区","other":"hllo"}
         * vip : {"vip_price":"0.01","vip":0}
         */

        private String type;
        private String name;
        private String pic;
        private String aims;
        private String sign_count;
        private String course_number;
        private String vip_price;
        private String price;
        private String is_pay;
        private String comment_count;
        private String thumb_up_count;
        private String times;
        private String is_mail;
        private String close_pay;
        private String course_desc;
        private String contact_customer;
        private String teacher_desc;
        private String common_question;
        private WeekBeanX week;
        private VipBean vip;
        private List<PingLunData> comment;

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

        public String getSign_count() {
            return sign_count;
        }

        public void setSign_count(String sign_count) {
            this.sign_count = sign_count;
        }

        public String getCourse_number() {
            return course_number;
        }

        public void setCourse_number(String course_number) {
            this.course_number = course_number;
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

        public String getTimes() {
            return times;
        }

        public void setTimes(String times) {
            this.times = times;
        }

        public String getIs_mail() {
            return is_mail;
        }

        public void setIs_mail(String is_mail) {
            this.is_mail = is_mail;
        }

        public String getClose_pay() {
            return close_pay;
        }

        public void setClose_pay(String close_pay) {
            this.close_pay = close_pay;
        }

        public String getCourse_desc() {
            return course_desc;
        }

        public void setCourse_desc(String course_desc) {
            this.course_desc = course_desc;
        }

        public String getTeacher_desc() {
            return teacher_desc;
        }

        public void setTeacher_desc(String teacher_desc) {
            this.teacher_desc = teacher_desc;
        }

        public String getCommon_question() {
            return common_question;
        }

        public void setCommon_question(String common_question) {
            this.common_question = common_question;
        }

        public WeekBeanX getWeek() {
            return week;
        }

        public void setWeek(WeekBeanX week) {
            this.week = week;
        }


        public VipBean getVip() {
            return vip;
        }

        public void setVip(VipBean vip) {
            this.vip = vip;
        }

        public List<PingLunData> getComment() {
            return comment;
        }

        public void setComment(List<PingLunData> comment) {
            this.comment = comment;
        }

        public static class WeekBeanX {
            /**
             * week : [{"week":"6","time_slot":["19：00"]},{"week":"7","time_slot":["20：00"]}]
             * start_time : 2018-09-05 10:30:14
             * end_time : 2018-12-04 10:30:21
             */

            private String start_time;
            private String end_time;
            private List<WeekBean> week;

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

            public List<WeekBean> getWeek() {
                return week;
            }

            public void setWeek(List<WeekBean> week) {
                this.week = week;
            }

            public static class WeekBean {
                /**
                 * week : 6
                 * time_slot : ["19：00"]
                 */

                private String week;
                private List<String> time_slot;


                public String getWeek() {
                    return week;
                }

                public void setWeek(String week) {
                    this.week = week;
                }

                public List<String> getTime_slot() {
                    return time_slot;
                }

                public void setTime_slot(List<String> time_slot) {
                    this.time_slot = time_slot;
                }
            }
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

    }
}
