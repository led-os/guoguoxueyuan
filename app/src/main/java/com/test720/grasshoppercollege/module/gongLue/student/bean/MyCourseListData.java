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
 * 作者：水东流 编于 2018/11/1
 */
public class MyCourseListData {


    /**
     * code : 1
     * msg : 成功
     * data : {"info":[{"order_id":"15","cover":"http://www.guoguoxueyuan.com/ggxy/Uploads/strategy/line/pic/2018-11-01/5bda7dca400e4.jpg","name":"数学有时间","course_number":"15","start_time":"2018-11-01 周一 17:00","end_time":"2018-11-30 周一 17:00","address":{"province":"安徽省","city":"安庆市","area":"大观区","other":"万达"},"complete_course_number":"1","line_id":"22"},{"order_id":"14","cover":"http://www.guoguoxueyuan.com/ggxy/Uploads/strategy/line/pic/2018-11-01/5bda786867916.JPG","name":"线上课程01号数学","course_number":"10","start_time":" 周一 ","end_time":" 周一 ","address":{"province":"安徽省","city":"安庆市","area":"大观区","other":"万达"},"complete_course_number":"0","line_id":"21"}]}
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
        private List<InfoBean> info;

        public List<InfoBean> getInfo() {
            return info;
        }

        public void setInfo(List<InfoBean> info) {
            this.info = info;
        }

        public static class InfoBean {
            /**
             * order_id : 15
             * cover : http://www.guoguoxueyuan.com/ggxy/Uploads/strategy/line/pic/2018-11-01/5bda7dca400e4.jpg
             * name : 数学有时间
             * course_number : 15
             * start_time : 2018-11-01 周一 17:00
             * end_time : 2018-11-30 周一 17:00
             * address : {"province":"安徽省","city":"安庆市","area":"大观区","other":"万达"}
             * complete_course_number : 1
             * line_id : 22
             */

            private String order_id;
            private String cover;
            private String name;
            private String course_number;
            private String start_time;
            private String end_time;
            private AddressBean address;
            private String complete_course_number;
            private String line_id;
            private String live_path;
            private String customer_path;

            public String getLive_path() {
                return live_path;
            }

            public void setLive_path(String live_path) {
                this.live_path = live_path;
            }

            public String getCustomer_path() {
                return customer_path;
            }

            public void setCustomer_path(String customer_path) {
                this.customer_path = customer_path;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
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

            public String getCourse_number() {
                return course_number;
            }

            public void setCourse_number(String course_number) {
                this.course_number = course_number;
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

            public AddressBean getAddress() {
                return address;
            }

            public void setAddress(AddressBean address) {
                this.address = address;
            }

            public String getComplete_course_number() {
                return complete_course_number;
            }

            public void setComplete_course_number(String complete_course_number) {
                this.complete_course_number = complete_course_number;
            }

            public String getLine_id() {
                return line_id;
            }

            public void setLine_id(String line_id) {
                this.line_id = line_id;
            }

            public static class AddressBean {
                /**
                 * province : 安徽省
                 * city : 安庆市
                 * area : 大观区
                 * other : 万达
                 */

                private String province;
                private String city;
                private String area;
                private String other;

                public String getProvince() {
                    return province;
                }

                public void setProvince(String province) {
                    this.province = province;
                }

                public String getCity() {
                    return city;
                }

                public void setCity(String city) {
                    this.city = city;
                }

                public String getArea() {
                    return area;
                }

                public void setArea(String area) {
                    this.area = area;
                }

                public String getOther() {
                    return other;
                }

                public void setOther(String other) {
                    this.other = other;
                }
            }
        }
    }
}
