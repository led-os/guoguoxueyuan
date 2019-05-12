package com.test720.grasshoppercollege.module.userData.myDingDan;

import java.util.List;

/**
 * Created by 水东流 on 2018/7/16.
 */

public class DingDanXiangQingData {


    /**
     * code : 1
     * msg : 成功
     * data : {"info":{"order_id":"32","order_num":"20181122100555528062355","points":"0","express_fee":"1.00","order_price":"1.01","total_price":"0.01","vip_discount":"0.00","order_time":"2018-11-22 10:05:56","order_status":"0","address_id":"30","coupon_money":"0.00","points_money":null},"goods":[{"gid":"62","price":"0.01","count":"1","value":"","name":"测试商品0.01","cover":"http://www.guoguoxueyuan.com/ggxy/Uploads/goods/pic/2018-11-13/5bea262e893f3.jpg"}],"address":{"address_id":"30","consignee":"dhvnmq","phone":"13388556545","province":"安徽省","city":"安庆市","area":"枞阳县","other":"fhn"}}
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
         * info : {"order_id":"32","order_num":"20181122100555528062355","points":"0","express_fee":"1.00","order_price":"1.01","total_price":"0.01","vip_discount":"0.00","order_time":"2018-11-22 10:05:56","order_status":"0","address_id":"30","coupon_money":"0.00","points_money":null}
         * goods : [{"gid":"62","price":"0.01","count":"1","value":"","name":"测试商品0.01","cover":"http://www.guoguoxueyuan.com/ggxy/Uploads/goods/pic/2018-11-13/5bea262e893f3.jpg"}]
         * address : {"address_id":"30","consignee":"dhvnmq","phone":"13388556545","province":"安徽省","city":"安庆市","area":"枞阳县","other":"fhn"}
         */

        private InfoBean info;
        private AddressBean address;
        private List<GoodsBean> goods;

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public AddressBean getAddress() {
            return address;
        }

        public void setAddress(AddressBean address) {
            this.address = address;
        }

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        public static class InfoBean {
            /**
             * order_id : 32
             * order_num : 20181122100555528062355
             * points : 0
             * express_fee : 1.00
             * order_price : 1.01
             * total_price : 0.01
             * vip_discount : 0.00
             * order_time : 2018-11-22 10:05:56
             * order_status : 0
             * address_id : 30
             * coupon_money : 0.00
             * points_money : null
             */

            private String order_id;
            private String order_num;
            private String points;
            private String express_fee;
            private String order_price;
            private String total_price;
            private String vip_discount;
            private String order_time;
            private String order_status;
            private String address_id;
            private String coupon_money;
            private String points_money;

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public String getOrder_num() {
                return order_num;
            }

            public void setOrder_num(String order_num) {
                this.order_num = order_num;
            }

            public String getPoints() {
                return points;
            }

            public void setPoints(String points) {
                this.points = points;
            }

            public String getExpress_fee() {
                return express_fee;
            }

            public void setExpress_fee(String express_fee) {
                this.express_fee = express_fee;
            }

            public String getOrder_price() {
                return order_price;
            }

            public void setOrder_price(String order_price) {
                this.order_price = order_price;
            }

            public String getTotal_price() {
                return total_price;
            }

            public void setTotal_price(String total_price) {
                this.total_price = total_price;
            }

            public String getVip_discount() {
                return vip_discount;
            }

            public void setVip_discount(String vip_discount) {
                this.vip_discount = vip_discount;
            }

            public String getOrder_time() {
                return order_time;
            }

            public void setOrder_time(String order_time) {
                this.order_time = order_time;
            }

            public String getOrder_status() {
                return order_status;
            }

            public void setOrder_status(String order_status) {
                this.order_status = order_status;
            }

            public String getAddress_id() {
                return address_id;
            }

            public void setAddress_id(String address_id) {
                this.address_id = address_id;
            }

            public String getCoupon_money() {
                return coupon_money;
            }

            public void setCoupon_money(String coupon_money) {
                this.coupon_money = coupon_money;
            }

            public Object getPoints_money() {
                return points_money;
            }

            public void setPoints_money(String points_money) {
                this.points_money = points_money;
            }
        }

        public static class AddressBean {
            /**
             * address_id : 30
             * consignee : dhvnmq
             * phone : 13388556545
             * province : 安徽省
             * city : 安庆市
             * area : 枞阳县
             * other : fhn
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

        public static class GoodsBean {
            /**
             * gid : 62
             * price : 0.01
             * count : 1
             * value :
             * name : 测试商品0.01
             * cover : http://www.guoguoxueyuan.com/ggxy/Uploads/goods/pic/2018-11-13/5bea262e893f3.jpg
             */

            private String gid;
            private String price;
            private String count;
            private String value;
            private String name;
            private String cover;

            public String getGid() {
                return gid;
            }

            public void setGid(String gid) {
                this.gid = gid;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
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
