package com.test720.grasshoppercollege.module.shangCheng.bean;

import java.util.List;

/**
 * Created by 水东流 on 2018/7/12.
 */

public class ShangPinXiangQingData {


    /**
     * code : 1
     * msg : 成功
     * data : {"goods":{"info":{"gid":"1","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","name":"漂亮的铅笔123","original_price":"10.00","price":"0.11","vip_price":"6.00","use_points":"1","sale_count":"23","store_count":"857","comment_count":"10","desc":"http://www.guoguoxueyuan.com/ggxy/small.php/Mall/descWeb/gid/1"},"img":["https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg"],"comment":[{"comment_id":"4","header":"","nickname":"","level":"4","content":"Assad\u2019sf017f017f012f013f013","time":"2018-09-04 11:22:52","pic":["https://www.hzggedu.com/ggxydemo/Uploads/","https://www.hzggedu.com/ggxydemo/Uploads/"]},{"comment_id":"3","header":"","nickname":"","level":"5","content":"哈哈黄金季节f023f023f023f023f023f023","time":"2018-07-17 19:41:40","pic":["https://www.hzggedu.com/ggxydemo/Uploads/","https://www.hzggedu.com/ggxydemo/Uploads/"]},{"comment_id":"2","header":"","nickname":"","level":"5","content":"还好还好哈f003f010f010f011f011f011f011f012f012","time":"2018-07-17 19:17:29","pic":["https://www.hzggedu.com/ggxydemo/Uploads/","https://www.hzggedu.com/ggxydemo/Uploads/"]},{"comment_id":"1","header":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","nickname":"下一次","level":"5","content":"好","time":"2018-03-06 16:44:18","pic":["https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg"]}],"spec":[{"value":"红色->小狗-就 发-发射点","store_count":"8","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","price":"0.00","vip_price":"0.00"},{"value":"蓝色-小熊-就 发-发射点","store_count":"31","pic":"https://www.hzggedu.com/ggxydemo/Uploads/comment_img/2018-06-29/5b35e5cd3bbc8.png","price":"0.00","vip_price":"0.00"},{"value":"蓝色-小狗-就 发-发射点","store_count":"2","pic":"https://www.hzggedu.com/ggxydemo/Uploads/comment_img/2018-06-29/5b35e5cd3ab6a.png","price":"0.00","vip_price":"0.00"},{"value":"黄色-小黄人-就 发-发射点","store_count":"9823","pic":"https://www.hzggedu.com/ggxydemo/Uploads/comment_img/2018-06-29/5b35e5bb54332.jpg","price":"0.00","vip_price":"0.00"}]}}
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
         * goods : {"info":{"gid":"1","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","name":"漂亮的铅笔123","original_price":"10.00","price":"0.11","vip_price":"6.00","use_points":"1","sale_count":"23","store_count":"857","comment_count":"10","desc":"http://www.guoguoxueyuan.com/ggxy/small.php/Mall/descWeb/gid/1"},"img":["https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg"],"comment":[{"comment_id":"4","header":"","nickname":"","level":"4","content":"Assad\u2019sf017f017f012f013f013","time":"2018-09-04 11:22:52","pic":["https://www.hzggedu.com/ggxydemo/Uploads/","https://www.hzggedu.com/ggxydemo/Uploads/"]},{"comment_id":"3","header":"","nickname":"","level":"5","content":"哈哈黄金季节f023f023f023f023f023f023","time":"2018-07-17 19:41:40","pic":["https://www.hzggedu.com/ggxydemo/Uploads/","https://www.hzggedu.com/ggxydemo/Uploads/"]},{"comment_id":"2","header":"","nickname":"","level":"5","content":"还好还好哈f003f010f010f011f011f011f011f012f012","time":"2018-07-17 19:17:29","pic":["https://www.hzggedu.com/ggxydemo/Uploads/","https://www.hzggedu.com/ggxydemo/Uploads/"]},{"comment_id":"1","header":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","nickname":"下一次","level":"5","content":"好","time":"2018-03-06 16:44:18","pic":["https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg"]}],"spec":[{"value":"红色->小狗-就 发-发射点","store_count":"8","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","price":"0.00","vip_price":"0.00"},{"value":"蓝色-小熊-就 发-发射点","store_count":"31","pic":"https://www.hzggedu.com/ggxydemo/Uploads/comment_img/2018-06-29/5b35e5cd3bbc8.png","price":"0.00","vip_price":"0.00"},{"value":"蓝色-小狗-就 发-发射点","store_count":"2","pic":"https://www.hzggedu.com/ggxydemo/Uploads/comment_img/2018-06-29/5b35e5cd3ab6a.png","price":"0.00","vip_price":"0.00"},{"value":"黄色-小黄人-就 发-发射点","store_count":"9823","pic":"https://www.hzggedu.com/ggxydemo/Uploads/comment_img/2018-06-29/5b35e5bb54332.jpg","price":"0.00","vip_price":"0.00"}]}
         */

        private GoodsBean goods;
        private String contact_customer;

        public String getContact_customer() {
            return contact_customer;
        }

        public void setContact_customer(String contact_customer) {
            this.contact_customer = contact_customer;
        }

        public GoodsBean getGoods() {
            return goods;
        }

        public void setGoods(GoodsBean goods) {
            this.goods = goods;
        }

        public static class GoodsBean {
            /**
             * info : {"gid":"1","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","name":"漂亮的铅笔123","original_price":"10.00","price":"0.11","vip_price":"6.00","use_points":"1","sale_count":"23","store_count":"857","comment_count":"10","desc":"http://www.guoguoxueyuan.com/ggxy/small.php/Mall/descWeb/gid/1"}
             * img : ["https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg"]
             * comment : [{"comment_id":"4","header":"","nickname":"","level":"4","content":"Assad\u2019sf017f017f012f013f013","time":"2018-09-04 11:22:52","pic":["https://www.hzggedu.com/ggxydemo/Uploads/","https://www.hzggedu.com/ggxydemo/Uploads/"]},{"comment_id":"3","header":"","nickname":"","level":"5","content":"哈哈黄金季节f023f023f023f023f023f023","time":"2018-07-17 19:41:40","pic":["https://www.hzggedu.com/ggxydemo/Uploads/","https://www.hzggedu.com/ggxydemo/Uploads/"]},{"comment_id":"2","header":"","nickname":"","level":"5","content":"还好还好哈f003f010f010f011f011f011f011f012f012","time":"2018-07-17 19:17:29","pic":["https://www.hzggedu.com/ggxydemo/Uploads/","https://www.hzggedu.com/ggxydemo/Uploads/"]},{"comment_id":"1","header":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","nickname":"下一次","level":"5","content":"好","time":"2018-03-06 16:44:18","pic":["https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg"]}]
             * spec : [{"value":"红色->小狗-就 发-发射点","store_count":"8","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","price":"0.00","vip_price":"0.00"},{"value":"蓝色-小熊-就 发-发射点","store_count":"31","pic":"https://www.hzggedu.com/ggxydemo/Uploads/comment_img/2018-06-29/5b35e5cd3bbc8.png","price":"0.00","vip_price":"0.00"},{"value":"蓝色-小狗-就 发-发射点","store_count":"2","pic":"https://www.hzggedu.com/ggxydemo/Uploads/comment_img/2018-06-29/5b35e5cd3ab6a.png","price":"0.00","vip_price":"0.00"},{"value":"黄色-小黄人-就 发-发射点","store_count":"9823","pic":"https://www.hzggedu.com/ggxydemo/Uploads/comment_img/2018-06-29/5b35e5bb54332.jpg","price":"0.00","vip_price":"0.00"}]
             */

            private InfoBean info;
            private List<String> img;
            private List<CommentBean> comment;
            private List<SpecBean> spec;

            public InfoBean getInfo() {
                return info;
            }

            public void setInfo(InfoBean info) {
                this.info = info;
            }

            public List<String> getImg() {
                return img;
            }

            public void setImg(List<String> img) {
                this.img = img;
            }

            public List<CommentBean> getComment() {
                return comment;
            }

            public void setComment(List<CommentBean> comment) {
                this.comment = comment;
            }

            public List<SpecBean> getSpec() {
                return spec;
            }

            public void setSpec(List<SpecBean> spec) {
                this.spec = spec;
            }

            public static class InfoBean {
                /**
                 * gid : 1
                 * cover : https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg
                 * name : 漂亮的铅笔123
                 * original_price : 10.00
                 * price : 0.11
                 * vip_price : 6.00
                 * use_points : 1
                 * sale_count : 23
                 * store_count : 857
                 * comment_count : 10
                 * desc : http://www.guoguoxueyuan.com/ggxy/small.php/Mall/descWeb/gid/1
                 */

                private String gid;
                private String cover;
                private String name;
                private String original_price;
                private String price;
                private String vip_price;
                private String use_points;
                private String sale_count;
                private String store_count;
                private String comment_count;
                private String desc;

                public String getGid() {
                    return gid;
                }

                public void setGid(String gid) {
                    this.gid = gid;
                }

                public String getCover() {
                    return cover;
                }

                public void setCover(String cover) {
                    this.cover = cover;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
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

                public String getSale_count() {
                    return sale_count;
                }

                public void setSale_count(String sale_count) {
                    this.sale_count = sale_count;
                }

                public String getStore_count() {
                    return store_count;
                }

                public void setStore_count(String store_count) {
                    this.store_count = store_count;
                }

                public String getComment_count() {
                    return comment_count;
                }

                public void setComment_count(String comment_count) {
                    this.comment_count = comment_count;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }
            }

            public static class CommentBean {
                /**
                 * comment_id : 4
                 * header :
                 * nickname :
                 * level : 4
                 * content : Assad’sf017f017f012f013f013
                 * time : 2018-09-04 11:22:52
                 * pic : ["https://www.hzggedu.com/ggxydemo/Uploads/","https://www.hzggedu.com/ggxydemo/Uploads/"]
                 */

                private String comment_id;
                private String header;
                private String nickname;
                private String level;
                private String content;
                private String time;
                private List<String> pic;

                public String getComment_id() {
                    return comment_id;
                }

                public void setComment_id(String comment_id) {
                    this.comment_id = comment_id;
                }

                public String getHeader() {
                    return header;
                }

                public void setHeader(String header) {
                    this.header = header;
                }

                public String getNickname() {
                    return nickname;
                }

                public void setNickname(String nickname) {
                    this.nickname = nickname;
                }

                public String getLevel() {
                    return level;
                }

                public void setLevel(String level) {
                    this.level = level;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public List<String> getPic() {
                    return pic;
                }

                public void setPic(List<String> pic) {
                    this.pic = pic;
                }
            }

            public static class SpecBean {
                /**
                 * value : 红色->小狗-就 发-发射点
                 * store_count : 8
                 * pic : https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg
                 * price : 0.00
                 * vip_price : 0.00
                 */

                private String value;
                private String store_count;
                private String pic;
                private String price;
                private String vip_price;
                private int index=0;

                public int getIndex() {
                    return index;
                }

                public void setIndex(int index) {
                    this.index = index;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                public String getStore_count() {
                    return store_count;
                }

                public void setStore_count(String store_count) {
                    this.store_count = store_count;
                }

                public String getPic() {
                    return pic;
                }

                public void setPic(String pic) {
                    this.pic = pic;
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
            }
        }
    }
}
