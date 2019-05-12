package com.test720.grasshoppercollege.module.guoguoDou.bean;

import com.test720.grasshoppercollege.untils.lunBo.Banner;

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
public class ShangChengData {

    /**
     * code : 1
     * msg : 成功
     * data : {"banner":[],"points":"6274","list":[{"gid":"3","name":"凯迪克","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","original_price":"32.00","price":"12.00","vip_price":"10.00","use_points":"5","store_count":"543"},{"gid":"4","name":"漂亮的铅笔","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","original_price":"10.00","price":"8.00","vip_price":"6.00","use_points":"1","store_count":"67"},{"gid":"5","name":"爱了","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","original_price":"20.00","price":"18.00","vip_price":"15.00","use_points":"3","store_count":"91"}]}
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
         * banner : []
         * points : 6274
         * list : [{"gid":"3","name":"凯迪克","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","original_price":"32.00","price":"12.00","vip_price":"10.00","use_points":"5","store_count":"543"},{"gid":"4","name":"漂亮的铅笔","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","original_price":"10.00","price":"8.00","vip_price":"6.00","use_points":"1","store_count":"67"},{"gid":"5","name":"爱了","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","original_price":"20.00","price":"18.00","vip_price":"15.00","use_points":"3","store_count":"91"}]
         */

        private String points;
        private List<Banner> banner;
        private List<ListBean> list;

        public String getPoints() {
            return points;
        }

        public void setPoints(String points) {
            this.points = points;
        }

        public List<Banner> getBanner() {
            return banner;
        }

        public void setBanner(List<Banner> banner) {
            this.banner = banner;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * gid : 3
             * name : 凯迪克
             * cover : https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg
             * original_price : 32.00
             * price : 12.00
             * vip_price : 10.00
             * use_points : 5
             * store_count : 543
             */

            private String gid;
            private String name;
            private String cover;
            private String original_price;
            private String price;
            private String vip_price;
            private String use_points;
            private String store_count;

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

            public String getStore_count() {
                return store_count;
            }

            public void setStore_count(String store_count) {
                this.store_count = store_count;
            }
        }
    }
}
