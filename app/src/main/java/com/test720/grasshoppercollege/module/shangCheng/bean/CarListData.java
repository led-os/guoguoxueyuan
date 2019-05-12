package com.test720.grasshoppercollege.module.shangCheng.bean;

import java.util.List;

/**
 * Created by 水东流 on 2018/7/13.
 */

public class CarListData {

    /**
     * code : 1
     * msg : 成功
     * data : {"list":[{"gid":"1","name":"漂亮的铅笔123","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","use_points":"1","original_price":"10.00","price":"8.00","vip_price":"6.00","count":"1","spec":"","value":"蓝色-小熊-就 发-发射点","cart_id":"7","store_count":"60"},{"gid":"1","name":"漂亮的铅笔123","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","use_points":"1","original_price":"10.00","price":"8.00","vip_price":"6.00","count":"1","spec":"","value":"蓝色-小熊-就 发-发射点","cart_id":"8","store_count":"60"},{"gid":"1","name":"漂亮的铅笔123","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","use_points":"1","original_price":"10.00","price":"8.00","vip_price":"6.00","count":"1","spec":"","value":"蓝色-小熊-就 发-发射点","cart_id":"9","store_count":"60"},{"gid":"1","name":"漂亮的铅笔123","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","use_points":"1","original_price":"10.00","price":"8.00","vip_price":"6.00","count":"5","spec":"","value":"黄色-小黄人-就 发-发射点-法开发方面反面反面方面","cart_id":"12","store_count":null},{"gid":"1","name":"漂亮的铅笔123","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","use_points":"1","original_price":"10.00","price":"8.00","vip_price":"6.00","count":"14","spec":"","value":"黄色-小黄人-就 发-发射点","cart_id":"11","store_count":"10"}]}
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
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * gid : 1
             * name : 漂亮的铅笔123
             * cover : https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg
             * use_points : 1
             * original_price : 10.00
             * price : 8.00
             * vip_price : 6.00
             * count : 1
             * spec :
             * value : 蓝色-小熊-就 发-发射点
             * cart_id : 7
             * store_count : 60
             */

            private String gid;
            private String name;
            private String cover;
            private String use_points;
            private String original_price;
            private String price;
            private String vip_price;
            private String count;
            private String spec;
            private String value;
            private String cart_id;
            private String store_count;
            private boolean check=false;

            public boolean isCheck() {
                return check;
            }

            public void setCheck(boolean check) {
                this.check = check;
            }

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

            public String getUse_points() {
                return use_points;
            }

            public void setUse_points(String use_points) {
                this.use_points = use_points;
            }

            public String getOriginal_price() {
                return original_price;
            }

            public void setOriginal_price(String original_price) {
                this.original_price = original_price;
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

            public String getSpec() {
                return spec;
            }

            public void setSpec(String spec) {
                this.spec = spec;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getCart_id() {
                return cart_id;
            }

            public void setCart_id(String cart_id) {
                this.cart_id = cart_id;
            }

            public String getStore_count() {
                return store_count;
            }

            public void setStore_count(String store_count) {
                this.store_count = store_count;
            }
        }
    }
}
