package com.test720.grasshoppercollege.module.guoguoDou.bean;

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
 * 作者：水东流 编于 2018/10/18
 */
public class DuiHuanAdressData {

    /**
     * code : 1
     * msg : 成功
     * data : {"goods":{"name":"爱了","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","store_count":"91","price":"18.00","vip_price":"15.00","value":"红色->小狗-就 发-发射点","count":"1"},"address":{"address_id":"4","consignee":"tuwl","phone":"18200236361","province":"北京市","city":"北京市","area":"东城区","other":"hllo"},"order_price":"18"}
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
         * goods : {"name":"爱了","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","store_count":"91","price":"18.00","vip_price":"15.00","value":"红色->小狗-就 发-发射点","count":"1"}
         * address : {"address_id":"4","consignee":"tuwl","phone":"18200236361","province":"北京市","city":"北京市","area":"东城区","other":"hllo"}
         * order_price : 18
         */

        private GoodsBean goods;
        private AddressBean address;
        private String order_price;

        public GoodsBean getGoods() {
            return goods;
        }

        public void setGoods(GoodsBean goods) {
            this.goods = goods;
        }

        public AddressBean getAddress() {
            return address;
        }

        public void setAddress(AddressBean address) {
            this.address = address;
        }

        public String getOrder_price() {
            return order_price;
        }

        public void setOrder_price(String order_price) {
            this.order_price = order_price;
        }

        public static class GoodsBean {
            /**
             * name : 爱了
             * cover : https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg
             * store_count : 91
             * price : 18.00
             * vip_price : 15.00
             * value : 红色->小狗-就 发-发射点
             * count : 1
             */

            private String name;
            private String cover;
            private String store_count;
            private String price;
            private String vip_price;
            private String value;
            private String count;

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

            public String getStore_count() {
                return store_count;
            }

            public void setStore_count(String store_count) {
                this.store_count = store_count;
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

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }
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
    }
}
