package com.test720.grasshoppercollege.module.shangCheng.bean;

import java.util.List;

/**
 * Created by 水东流 on 2018/7/13.
 */

public class QueRenDingDanData {

    /**
     * code : 1
     * msg : 成功
     * data : {"list":[{"gid":"1","name":"漂亮的铅笔123","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","price":"1.00","vip_price":"6.00","count":"5","value":"黄色-小黄人-就 发-发射点-法开发方面反面反面方面"}],"address":{"address_id":"4","consignee":"tuwl","phone":"18200236361","province":"北京市","city":"北京市","area":"东城区","other":"hllo"},"points_pro":"10","user_coupon":[],"express_fee":"0","order_price":5,"points":"1301","order_points":0,"user_cid":0,"points_money":0,"use_points":"10"}
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
         * list : [{"gid":"1","name":"漂亮的铅笔123","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","price":"1.00","vip_price":"6.00","count":"5","value":"黄色-小黄人-就 发-发射点-法开发方面反面反面方面"}]
         * address : {"address_id":"4","consignee":"tuwl","phone":"18200236361","province":"北京市","city":"北京市","area":"东城区","other":"hllo"}
         * points_pro : 10
         * user_coupon : []
         * express_fee : 0
         * order_price : 5
         * points : 1301
         * order_points : 0
         * user_cid : 0
         * points_money : 0
         * use_points : 10
         */

        private AddressBean address;
        private String points_pro;
        private String express_fee;
        private float order_price;
        private String points;
        private int order_points;
        private String min_price;
        private int user_cid;
        private int points_money;
        private String use_points;
        private List<ListBean> list;
        private List<?> user_coupon;

        public String getMin_price() {
            return min_price;
        }

        public void setMin_price(String min_price) {
            this.min_price = min_price;
        }

        public AddressBean getAddress() {
            return address;
        }

        public void setAddress(AddressBean address) {
            this.address = address;
        }

        public String getPoints_pro() {
            return points_pro;
        }

        public void setPoints_pro(String points_pro) {
            this.points_pro = points_pro;
        }

        public String getExpress_fee() {
            return express_fee;
        }

        public void setExpress_fee(String express_fee) {
            this.express_fee = express_fee;
        }

        public float getOrder_price() {
            return order_price;
        }

        public void setOrder_price(int order_price) {
            this.order_price = order_price;
        }

        public String getPoints() {
            return points;
        }

        public void setPoints(String points) {
            this.points = points;
        }

        public int getOrder_points() {
            return order_points;
        }

        public void setOrder_points(int order_points) {
            this.order_points = order_points;
        }

        public int getUser_cid() {
            return user_cid;
        }

        public void setUser_cid(int user_cid) {
            this.user_cid = user_cid;
        }

        public int getPoints_money() {
            return points_money;
        }

        public void setPoints_money(int points_money) {
            this.points_money = points_money;
        }

        public String getUse_points() {
            return use_points;
        }

        public void setUse_points(String use_points) {
            this.use_points = use_points;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public List<?> getUser_coupon() {
            return user_coupon;
        }

        public void setUser_coupon(List<?> user_coupon) {
            this.user_coupon = user_coupon;
        }

        public static class AddressBean {
            /**
             * address_id : 4
             * consignee : tuwl
             * phone : 18200236361
             * province : 北京市
             * city : 北京市
             * area : 东城区
             * other : hllo
             */

            private String address_id;
            private String consignee;
            private String phone;
            private String province;
            private String city;
            private String area;
            private String other;

            public String getAddress_id() {
                return address_id;
            }

            public void setAddress_id(String address_id) {
                this.address_id = address_id;
            }

            public String getConsignee() {
                return consignee;
            }

            public void setConsignee(String consignee) {
                this.consignee = consignee;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

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

        public static class ListBean {
            /**
             * gid : 1
             * name : 漂亮的铅笔123
             * cover : https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg
             * price : 1.00
             * vip_price : 6.00
             * count : 5
             * value : 黄色-小黄人-就 发-发射点-法开发方面反面反面方面
             */

            private String gid;
            private String name;
            private String cover;
            private String price;
            private String vip_price;
            private String count;
            private String value;

            public String getGid() {
                return gid;
            }

            public void setGid(String gid) {
                this.gid = gid;
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

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getVip_price() {
                return vip_price;
            }

            public void setVip_price(String vip_price) {
                this.vip_price = vip_price;
            }

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }
    }
}
