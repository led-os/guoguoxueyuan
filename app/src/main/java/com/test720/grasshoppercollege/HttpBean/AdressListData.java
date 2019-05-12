package com.test720.grasshoppercollege.HttpBean;

import java.util.List;

/**
 * Created by 水东流 on 2018/4/9.
 */

public class AdressListData {


    /**
     * code : 1
     * msg : 成功
     * data : [{"address_id":"2","consignee":"哈哈哈","phone":"18380288595","province":"香港特别行政区","city":"香港特别行政区","area":"其他","other":"哈哈哈还好还好","status":"0"},{"address_id":"3","consignee":"哈哈哈","phone":"18380288595","province":"香港特别行政区","city":"香港特别行政区","area":"其他","other":"哈哈哈还好还好","status":"0"},{"address_id":"4","consignee":"老家","phone":"18380288555","province":"安徽省","city":"安庆市","area":"枞阳县","other":"哦哦","status":"0"}]
     */

    private int code;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * address_id : 2
         * consignee : 哈哈哈
         * phone : 18380288595
         * province : 香港特别行政区
         * city : 香港特别行政区
         * area : 其他
         * other : 哈哈哈还好还好
         * status : 0
         */

        private String address_id;
        private String consignee;
        private String phone;
        private String province;
        private String city;
        private String area;
        private String other;
        private String status;

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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
