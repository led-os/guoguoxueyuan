package com.test720.grasshoppercollege.module.gongLue.peiXun.bean;

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
 * 作者：水东流 编于 2018/9/1
 */
public class JiGouData {

    /**
     * code : 1
     * msg : 成功
     * data : {"school_id":"1","name":"学校1","cover":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg","telephone":"15882137306","province":"浙江省","city":"杭州市","area":"拱墅区","other":"哈哈","desc":"https://www.hzggedu.com/ggxydemo/small.php/StrategyTrain/schoolDesc/id/1","img":[{"name":"","img":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b3217b61d8e2.jpg"},{"name":"","img":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b3217b61d8e2.jpg"},{"name":"","img":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b3217b61d8e2.jpg"},{"name":"","img":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b3217b61d8e2.jpg"}]}
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
         * school_id : 1
         * name : 学校1
         * cover : https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32100899007.jpg
         * telephone : 15882137306
         * province : 浙江省
         * city : 杭州市
         * area : 拱墅区
         * other : 哈哈
         * desc : https://www.hzggedu.com/ggxydemo/small.php/StrategyTrain/schoolDesc/id/1
         * img : [{"name":"","img":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b3217b61d8e2.jpg"},{"name":"","img":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b3217b61d8e2.jpg"},{"name":"","img":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b3217b61d8e2.jpg"},{"name":"","img":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b3217b61d8e2.jpg"}]
         */

        private String school_id;
        private String name;
        private String cover;
        private String telephone;
        private String province;
        private String city;
        private String area;
        private String other;
        private String desc;
        private List<ImgBean> img;

        public String getSchool_id() {
            return school_id;
        }

        public void setSchool_id(String school_id) {
            this.school_id = school_id;
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

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
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

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public List<ImgBean> getImg() {
            return img;
        }

        public void setImg(List<ImgBean> img) {
            this.img = img;
        }

        public static class ImgBean {
            /**
             * name :
             * img : https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b3217b61d8e2.jpg
             */

            private String name;
            private String img;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }
        }
    }
}
