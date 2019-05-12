package com.test720.grasshoppercollege.module.guoguoDou.bean;

import java.util.List;

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
public class ShangPXQData {

    /**
     * code : 1
     * msg : 成功
     * data : {"goods":{"info":{"gid":"1","name":"漂亮的铅笔123","original_price":"10.00","price":"0.11","vip_price":"6.00","use_points":"1","sale_count":"23","store_count":"857","comment_count":"10","desc":"http://www.guoguoxueyuan.com/ggxy/small.php/Mall/descWeb/gid/1"},"img":["https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg"],"comment":[],"spec":[{"value":"红色->小狗-就 发-发射点","store_count":"8","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","price":"0.00","vip_price":"0.00"},{"value":"蓝色-小熊-就 发-发射点","store_count":"31","pic":"https://www.hzggedu.com/ggxydemo/Uploads/comment_img/2018-06-29/5b35e5cd3bbc8.png","price":"0.00","vip_price":"0.00"},{"value":"蓝色-小狗-就 发-发射点","store_count":"2","pic":"https://www.hzggedu.com/ggxydemo/Uploads/comment_img/2018-06-29/5b35e5cd3ab6a.png","price":"0.00","vip_price":"0.00"},{"value":"黄色-小黄人-就 发-发射点","store_count":"9823","pic":"https://www.hzggedu.com/ggxydemo/Uploads/comment_img/2018-06-29/5b35e5bb54332.jpg","price":"0.00","vip_price":"0.00"}]}}
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
         * goods : {"info":{"gid":"1","name":"漂亮的铅笔123","original_price":"10.00","price":"0.11","vip_price":"6.00","use_points":"1","sale_count":"23","store_count":"857","comment_count":"10","desc":"http://www.guoguoxueyuan.com/ggxy/small.php/Mall/descWeb/gid/1"},"img":["https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg"],"comment":[],"spec":[{"value":"红色->小狗-就 发-发射点","store_count":"8","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","price":"0.00","vip_price":"0.00"},{"value":"蓝色-小熊-就 发-发射点","store_count":"31","pic":"https://www.hzggedu.com/ggxydemo/Uploads/comment_img/2018-06-29/5b35e5cd3bbc8.png","price":"0.00","vip_price":"0.00"},{"value":"蓝色-小狗-就 发-发射点","store_count":"2","pic":"https://www.hzggedu.com/ggxydemo/Uploads/comment_img/2018-06-29/5b35e5cd3ab6a.png","price":"0.00","vip_price":"0.00"},{"value":"黄色-小黄人-就 发-发射点","store_count":"9823","pic":"https://www.hzggedu.com/ggxydemo/Uploads/comment_img/2018-06-29/5b35e5bb54332.jpg","price":"0.00","vip_price":"0.00"}]}
         */

        private GoodsBean goods;

        public GoodsBean getGoods() {
            return goods;
        }

        public void setGoods(GoodsBean goods) {
            this.goods = goods;
        }

        public static class GoodsBean {
            /**
             * info : {"gid":"1","name":"漂亮的铅笔123","original_price":"10.00","price":"0.11","vip_price":"6.00","use_points":"1","sale_count":"23","store_count":"857","comment_count":"10","desc":"http://www.guoguoxueyuan.com/ggxy/small.php/Mall/descWeb/gid/1"}
             * img : ["https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg"]
             * comment : []
             * spec : [{"value":"红色->小狗-就 发-发射点","store_count":"8","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","price":"0.00","vip_price":"0.00"},{"value":"蓝色-小熊-就 发-发射点","store_count":"31","pic":"https://www.hzggedu.com/ggxydemo/Uploads/comment_img/2018-06-29/5b35e5cd3bbc8.png","price":"0.00","vip_price":"0.00"},{"value":"蓝色-小狗-就 发-发射点","store_count":"2","pic":"https://www.hzggedu.com/ggxydemo/Uploads/comment_img/2018-06-29/5b35e5cd3ab6a.png","price":"0.00","vip_price":"0.00"},{"value":"黄色-小黄人-就 发-发射点","store_count":"9823","pic":"https://www.hzggedu.com/ggxydemo/Uploads/comment_img/2018-06-29/5b35e5bb54332.jpg","price":"0.00","vip_price":"0.00"}]
             */

            private InfoBean info;
            private List<String> img;
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

            public List<SpecBean> getSpec() {
                return spec;
            }

            public void setSpec(List<SpecBean> spec) {
                this.spec = spec;
            }

            public static class InfoBean {
                /**
                 * gid : 1
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
                private String vip;
                private String name;
                private String original_price;
                private String price;
                private String vip_price;
                private String use_points;
                private String sale_count;
                private String store_count;
                private String comment_count;
                private String desc;

                public String getVip() {
                    return vip;
                }

                public void setVip(String vip) {
                    this.vip = vip;
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
