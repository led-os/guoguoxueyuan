package com.test720.grasshoppercollege.module.gongLue.dingDan;

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
 * 作者：水东流 编于 2018/9/11
 */
public class QueRenDingDanData {


    /**
     * code : 1
     * msg : 成功
     * data : {"type":"56","name":"未来咩学","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg","course_number":"15","vip_price":"1000.00","price":"1200.00","is_mail":"1","address":{"address_id":"4","consignee":"tuwl","phone":"18200236361","province":"北京市","city":"北京市","area":"东城区","other":"hllo"},"vip":{"vip_price":"0.01"}}
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
         * course_number : 15
         * vip_price : 1000.00
         * price : 1200.00
         * is_mail : 1
         * address : {"address_id":"4","consignee":"tuwl","phone":"18200236361","province":"北京市","city":"北京市","area":"东城区","other":"hllo"}
         * vip : {"vip_price":"0.01"}
         */

        private String type;
        private String name;
        private String pic;
        private String expiry_date;
        private String course_number;
        private String vip_price;
        private String price;
        private String is_mail;
        private AddressBean address;
        private VipBean vip;

        public String getExpiry_date() {
            return expiry_date;
        }

        public void setExpiry_date(String expiry_date) {
            this.expiry_date = expiry_date;
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

        public String getIs_mail() {
            return is_mail;
        }

        public void setIs_mail(String is_mail) {
            this.is_mail = is_mail;
        }

        public AddressBean getAddress() {
            return address;
        }

        public void setAddress(AddressBean address) {
            this.address = address;
        }

        public VipBean getVip() {
            return vip;
        }

        public void setVip(VipBean vip) {
            this.vip = vip;
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

        public static class VipBean {
            /**
             * yue :
             * jidu :
             * bannian :
             * yinian :
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
