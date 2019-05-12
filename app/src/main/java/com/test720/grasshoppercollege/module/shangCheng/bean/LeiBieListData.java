package com.test720.grasshoppercollege.module.shangCheng.bean;

import java.util.List;

/**
 * Created by 水东流 on 2018/7/12.
 */

public class LeiBieListData {

    /**
     * code : 1
     * msg : 成功
     * data : {"list":[{"gid":"1","name":"漂亮的铅笔","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","original_price":"10.00","price":"8.00","vip_price":"6.00","use_points":"1"},{"gid":"4","name":"漂亮的铅笔","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","original_price":"10.00","price":"8.00","vip_price":"6.00","use_points":"1"},{"gid":"7","name":"漂亮的铅笔","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","original_price":"10.00","price":"8.00","vip_price":"6.00","use_points":"1"},{"gid":"10","name":"漂亮的铅笔","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","original_price":"10.00","price":"8.00","vip_price":"6.00","use_points":"1"},{"gid":"13","name":"漂亮的铅笔","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","original_price":"10.00","price":"8.00","vip_price":"6.00","use_points":"1"},{"gid":"2","name":"爱了","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","original_price":"20.00","price":"18.00","vip_price":"15.00","use_points":"3"},{"gid":"5","name":"爱了","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","original_price":"20.00","price":"18.00","vip_price":"15.00","use_points":"3"},{"gid":"8","name":"爱了","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","original_price":"20.00","price":"18.00","vip_price":"15.00","use_points":"3"},{"gid":"11","name":"爱了","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","original_price":"20.00","price":"18.00","vip_price":"15.00","use_points":"3"},{"gid":"14","name":"爱了","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","original_price":"20.00","price":"18.00","vip_price":"15.00","use_points":"3"}]}
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
             * name : 漂亮的铅笔
             * cover : https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg
             * original_price : 10.00
             * price : 8.00
             * vip_price : 6.00
             * use_points : 1
             */

            private String gid;
            private String name;
            private String cover;
            private String original_price;
            private String price;
            private String vip_price;
            private String use_points;

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

            public String getUse_points() {
                return use_points;
            }

            public void setUse_points(String use_points) {
                this.use_points = use_points;
            }
        }
    }
}
