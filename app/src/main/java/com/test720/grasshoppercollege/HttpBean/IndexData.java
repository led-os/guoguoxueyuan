package com.test720.grasshoppercollege.HttpBean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 水东流 on 2018/4/19.
 */

public class IndexData {

    /**
     * code : 1
     * msg : 成功
     * data : {"nickname":"水东流","header":"http://www.guoguoxueyuan.com/ggxy/Uploads/header/2018-09-25/5ba9fe67ad0b2.jpg","class":"","points":"4789","level":"11","money":"17531.15","vip":"1","info":{"msg_id":"24","start_time":"0000-00-00 00:00:00","end_time":"0000-00-00 00:00:00","release_time":"2018-10-26 11:01:13","location_type":"2","location_href":"http://www.guoguoxueyuan.com/ggxy/small.php/IndexUser/adsInfo"},"sign":"0","coupon":{"coupon":"1","coupon_info":{"cid":"1","name":"VIP优惠券","coupon":"12.00","full_money":"10.00"}}}
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
         * nickname : 水东流
         * header : http://www.guoguoxueyuan.com/ggxy/Uploads/header/2018-09-25/5ba9fe67ad0b2.jpg
         * class :
         * points : 4789
         * level : 11
         * money : 17531.15
         * vip : 1
         * info : {"msg_id":"24","start_time":"0000-00-00 00:00:00","end_time":"0000-00-00 00:00:00","release_time":"2018-10-26 11:01:13","location_type":"2","location_href":"http://www.guoguoxueyuan.com/ggxy/small.php/IndexUser/adsInfo"}
         * sign : 0
         * coupon : {"coupon":"1","coupon_info":{"cid":"1","name":"VIP优惠券","coupon":"12.00","full_money":"10.00"}}
         */

        private String nickname;
        private String header;
        @SerializedName("class")
        private String classX;
        private String points;
        private String level;
        private String money;
        private String vip;
        private InfoBean info;
        private String sign;
        private CouponBean coupon;

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

        public String getClassX() {
            return classX;
        }

        public void setClassX(String classX) {
            this.classX = classX;
        }

        public String getPoints() {
            return points;
        }

        public void setPoints(String points) {
            this.points = points;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getVip() {
            return vip;
        }

        public void setVip(String vip) {
            this.vip = vip;
        }

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public CouponBean getCoupon() {
            return coupon;
        }

        public void setCoupon(CouponBean coupon) {
            this.coupon = coupon;
        }

        public static class InfoBean {
            /**
             * msg_id : 24
             * start_time : 0000-00-00 00:00:00
             * end_time : 0000-00-00 00:00:00
             * release_time : 2018-10-26 11:01:13
             * location_type : 2
             * location_href : http://www.guoguoxueyuan.com/ggxy/small.php/IndexUser/adsInfo
             */

            private String msg_id;
            private String start_time;
            private String end_time;
            private String release_time;
            private String location_type;
            private String location_href;

            public String getMsg_id() {
                return msg_id;
            }

            public void setMsg_id(String msg_id) {
                this.msg_id = msg_id;
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

            public String getRelease_time() {
                return release_time;
            }

            public void setRelease_time(String release_time) {
                this.release_time = release_time;
            }

            public String getLocation_type() {
                return location_type;
            }

            public void setLocation_type(String location_type) {
                this.location_type = location_type;
            }

            public String getLocation_href() {
                return location_href;
            }

            public void setLocation_href(String location_href) {
                this.location_href = location_href;
            }
        }

        public static class CouponBean {
            /**
             * coupon : 1
             * coupon_info : {"cid":"1","name":"VIP优惠券","coupon":"12.00","full_money":"10.00"}
             */

            private String coupon;
            private CouponInfoBean coupon_info;

            public String getCoupon() {
                return coupon;
            }

            public void setCoupon(String coupon) {
                this.coupon = coupon;
            }

            public CouponInfoBean getCoupon_info() {
                return coupon_info;
            }

            public void setCoupon_info(CouponInfoBean coupon_info) {
                this.coupon_info = coupon_info;
            }

            public static class CouponInfoBean {
                /**
                 * cid : 1
                 * name : VIP优惠券
                 * coupon : 12.00
                 * full_money : 10.00
                 */

                private String cid;
                private String name;
                private String coupon;
                private String full_money;

                public String getCid() {
                    return cid;
                }

                public void setCid(String cid) {
                    this.cid = cid;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getCoupon() {
                    return coupon;
                }

                public void setCoupon(String coupon) {
                    this.coupon = coupon;
                }

                public String getFull_money() {
                    return full_money;
                }

                public void setFull_money(String full_money) {
                    this.full_money = full_money;
                }
            }
        }
    }
}
