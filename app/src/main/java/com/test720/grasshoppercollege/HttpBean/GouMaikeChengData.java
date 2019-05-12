package com.test720.grasshoppercollege.HttpBean;

/**
 * Created by 水东流 on 2018/6/13.
 */

public class GouMaikeChengData {

    /**
     * code : 1
     * msg : 成功
     * data : {"title":"奥数动画","desc":"据说顶发生了女性你楼上的你反对发你是肯定能发；是单反但凡能双开电脑","price":{"yue":null,"jidu":"1","bannian":"1","yinian":"1"},"vip_price":{"yue":"0.01","jidu":"0.02","bannian":"0.03","yinian":"0.05"}}
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
         * title : 奥数动画
         * desc : 据说顶发生了女性你楼上的你反对发你是肯定能发；是单反但凡能双开电脑
         * price : {"yue":null,"jidu":"1","bannian":"1","yinian":"1"}
         * vip_price : {"yue":"0.01","jidu":"0.02","bannian":"0.03","yinian":"0.05"}
         */

        private String title;
        private String desc;
        private PriceBean price;
        private VipPriceBean vip_price;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public PriceBean getPrice() {
            return price;
        }

        public void setPrice(PriceBean price) {
            this.price = price;
        }

        public VipPriceBean getVip_price() {
            return vip_price;
        }

        public void setVip_price(VipPriceBean vip_price) {
            this.vip_price = vip_price;
        }

        public static class PriceBean {
            /**
             * yue : 1
             * jidu : 1
             * bannian : 1
             * yinian : 1
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
    }
}
