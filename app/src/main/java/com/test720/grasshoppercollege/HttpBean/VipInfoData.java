package com.test720.grasshoppercollege.HttpBean;

/**
 * Created by 水东流 on 2018/4/16.
 */

public class VipInfoData {


    /**
     * code : 1
     * msg : 成功
     * data : {"vip_price":{"yue":"0.01","jidu":"0.02","bannian":"0.03","yinian":"0.05"},"vip_rights":"vip权益vip权益\\nvip权益vip权益\\nvip权益vip权益vip权益vip权益vip权益vip权益vip权益vip权益vip权益vip权益vip权益\\n1234444","vip_coupon":{"have":0,"coupon_info":null}}
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
         * vip_price : {"yue":"0.01","jidu":"0.02","bannian":"0.03","yinian":"0.05"}
         * vip_rights : vip权益vip权益\nvip权益vip权益\nvip权益vip权益vip权益vip权益vip权益vip权益vip权益vip权益vip权益vip权益vip权益\n1234444
         * vip_coupon : {"have":0,"coupon_info":null}
         */

        private VipPriceBean vip_price;
        private String vip_rights;
        private String wenzi;
        private VipCouponBean vip_coupon;

        public String getWenzi() {
            return wenzi;
        }

        public void setWenzi(String wenzi) {
            this.wenzi = wenzi;
        }

        public VipPriceBean getVip_price() {
            return vip_price;
        }

        public void setVip_price(VipPriceBean vip_price) {
            this.vip_price = vip_price;
        }

        public String getVip_rights() {
            return vip_rights;
        }

        public void setVip_rights(String vip_rights) {
            this.vip_rights = vip_rights;
        }

        public VipCouponBean getVip_coupon() {
            return vip_coupon;
        }

        public void setVip_coupon(VipCouponBean vip_coupon) {
            this.vip_coupon = vip_coupon;
        }

        public static class VipPriceBean {
            /**
             * yue : 0.01
             * jidu : 0.02
             * bannian : 0.03
             * yinian : 0.05
             */

            private String yue;
            private String jidu;
            private String bannian;
            private String yinian;

            public String getYue() {
                return yue;
            }

            public void setYue(String yue) {
                this.yue = yue;
            }

            public String getJidu() {
                return jidu;
            }

            public void setJidu(String jidu) {
                this.jidu = jidu;
            }

            public String getBannian() {
                return bannian;
            }

            public void setBannian(String bannian) {
                this.bannian = bannian;
            }

            public String getYinian() {
                return yinian;
            }

            public void setYinian(String yinian) {
                this.yinian = yinian;
            }
        }

        public static class VipCouponBean {
            /**
             * have : 0
             * coupon_info : null
             */

            private int have;
            private Object coupon_info;

            public int getHave() {
                return have;
            }

            public void setHave(int have) {
                this.have = have;
            }

            public Object getCoupon_info() {
                return coupon_info;
            }

            public void setCoupon_info(Object coupon_info) {
                this.coupon_info = coupon_info;
            }
        }
    }
}
