package com.test720.grasshoppercollege.module.shangCheng.bean;

import com.test720.grasshoppercollege.untils.lunBo.Banner;

import java.util.List;

/**
 * Created by 水东流 on 2018/7/10.
 */

public class ShangChengShouYeData {

    /**
     * code : 1
     * msg : 成功
     * data : {"list":[{"cover":"https://www.hzggedu.com/ggxydemo/Uploads/comment_img/2018-06-29/5b35e5cd3bbc8.png","goods":[{"gid":"2","name":"爱了","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","price":"0.01"}]},{"cover":"https://www.hzggedu.com/ggxydemo/Uploads/comment_img/2018-06-29/5b35e5cd3bbc8.png","goods":[{"gid":"16","name":"1111","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","price":"0.10"},{"gid":"17","name":"222","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","price":"0.10"},{"gid":"18","name":"33","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","price":"0.10"},{"gid":"19","name":"1111","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","price":"0.10"},{"gid":"20","name":"222","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","price":"0.10"},{"gid":"28","name":"111111","cover":"http://localhost/ggxy/Uploads/goods/pic/2018-09-18/5ba09c71e4674.jpg","price":"111.00"}]},{"cover":"https://www.hzggedu.com/ggxydemo/Uploads/comment_img/2018-06-29/5b35e5bb4b4b1.jpg","goods":[{"gid":"22","name":"1111","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","price":"0.10"},{"gid":"23","name":"1111","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","price":"0.10"},{"gid":"24","name":"222","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","price":"0.10"},{"gid":"25","name":"22212","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","price":"0.10"},{"gid":"26","name":"222","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","price":"0.10"},{"gid":"27","name":"22212","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","price":"0.10"}]}],"banner":[{"banner_id":"38","location_type":"1","location_link":"","pic":"http://www.guoguoxueyuan.com/ggxy/Uploads/ads/2018-10-18/5bc7fe791d754.jpg","name":"","g_id":"0","gid":"0"},{"banner_id":"39","location_type":"2","location_link":"http://www.guoguoxueyuan.com/ggxy/small.php/Mall/bannerWeb/id/39","pic":"http://www.guoguoxueyuan.com/ggxy/Uploads/ads/2018-10-18/5bc7fe8956fad.jpg","name":"","g_id":"0","gid":"0"}]}
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
        private List<Banner> banner;
        private List<String> hot_search;

        public List<String> getHot_search() {
            return hot_search;
        }

        public void setHot_search(List<String> hot_search) {
            this.hot_search = hot_search;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public List<Banner> getBanner() {
            return banner;
        }

        public void setBanner(List<Banner> banner) {
            this.banner = banner;
        }

        public static class ListBean {
            /**
             * cover : https://www.hzggedu.com/ggxydemo/Uploads/comment_img/2018-06-29/5b35e5cd3bbc8.png
             * goods : [{"gid":"2","name":"爱了","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","price":"0.01"}]
             */

            private String cover;
            private String cate_id;
            private List<GoodsBean> goods;

            public String getCate_id() {
                return cate_id;
            }

            public void setCate_id(String cate_id) {
                this.cate_id = cate_id;
            }

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public List<GoodsBean> getGoods() {
                return goods;
            }

            public void setGoods(List<GoodsBean> goods) {
                this.goods = goods;
            }

            public static class GoodsBean {
                /**
                 * gid : 2
                 * name : 爱了
                 * cover : https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg
                 * price : 0.01
                 */

                private String gid;
                private String name;
                private String cover;
                private String price;
                private String sale_count;

                public String getSale_count() {
                    return sale_count;
                }

                public void setSale_count(String sale_count) {
                    this.sale_count = sale_count;
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

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }
            }
        }

    }
}
