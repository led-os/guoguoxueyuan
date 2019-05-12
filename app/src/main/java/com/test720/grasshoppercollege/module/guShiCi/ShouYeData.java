package com.test720.grasshoppercollege.module.guShiCi;

import com.test720.grasshoppercollege.untils.lunBo.Banner;

import java.util.List;

/**
 * Created by 水东流 on 2018/7/10.
 */

public class ShouYeData {


    /**
     * code : 1
     * msg : 成功
     * data : {"list":[{"cate_id":"1","name":"一年级","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","total":"4","number":"0"},{"cate_id":"2","name":"二年级","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","total":"0","number":"0"},{"cate_id":"3","name":"三年级","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","total":"0","number":"0"},{"cate_id":"4","name":"四年级","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","total":"0","number":"0"},{"cate_id":"5","name":"五年级","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","total":"0","number":"0"},{"cate_id":"6","name":"六年级","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg","total":"0","number":"0"}],"banner":[{"banner_id":"4","location_type":"3","location_link":"https://www.hzggedu.com/ggxydemo/small.php/Mall/goodsInfo/gid/1","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32120fc5596.jpg","name":"vfbf"},{"banner_id":"5","location_type":"3","location_link":"https://www.hzggedu.com/ggxydemo/small.php/Mall/goodsInfo/gid/1","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b3217b61d8e2.jpg","name":"vfbf"}]}
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
             * cate_id : 1
             * name : 一年级
             * pic : https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b321494ec7bf.jpg
             * total : 4
             * number : 0
             */

            private String cate_id;
            private String name;
            private String pic;
            private String total;
            private String number;

            public String getCate_id() {
                return cate_id;
            }

            public void setCate_id(String cate_id) {
                this.cate_id = cate_id;
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

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }
        }

    }
}
