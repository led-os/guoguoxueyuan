package com.test720.grasshoppercollege.HttpBean;

import com.google.gson.annotations.SerializedName;
import com.test720.grasshoppercollege.untils.lunBo.Banner;

import java.util.List;

/**
 * Created by 水东流 on 2018/7/9.
 */

public class BaoDianShouData {


    /**
     * code : 1
     * msg : 成功
     * data : {"banner":[],"catetory":[{"lib_id":"5","name":"歇后语宝典1","pic":"http://cdn1.guoguoxueyuan.com/Uploads/chinese_xiehouyu/2018-12-08/5c0ba1bfe6702.jpg"},{"lib_id":"6","name":"歇后语宝典2测试长度长度长度长度长度长度长度长度长度长度长度长度长度长度长度长度长度长度长度长度长度长度长度长度长度长度长度长度长度长度长度长度长度长度长度长度长度长度长度vvvv结尾","pic":"http://cdn1.guoguoxueyuan.com/Uploads/chinese_xiehouyu/2018-12-10/5c0e051393e3c.jpg"},{"lib_id":"7","name":"歇后语宝典3","pic":"http://cdn1.guoguoxueyuan.com/Uploads/chinese_xiehouyu/2018-12-10/5c0e052404d10.jpg"},{"lib_id":"8","name":"歇后语宝典4","pic":"http://cdn1.guoguoxueyuan.com/Uploads/chinese_xiehouyu/2018-12-10/5c0e052d2c449.jpg"},{"lib_id":"9","name":"歇后语宝典5","pic":"http://cdn1.guoguoxueyuan.com/Uploads/chinese_xiehouyu/2018-12-10/5c0e053842b99.jpg"},{"lib_id":"10","name":"歇后语宝典6","pic":"http://cdn1.guoguoxueyuan.com/Uploads/chinese_xiehouyu/2018-12-10/5c0e0543b2ac3.jpg"},{"lib_id":"11","name":"歇后语宝典7","pic":"http://cdn1.guoguoxueyuan.com/Uploads/chinese_xiehouyu/2018-12-10/5c0e054f3ce31.jpg"},{"lib_id":"12","name":"歇后语宝典8","pic":"http://cdn1.guoguoxueyuan.com/Uploads/chinese_xiehouyu/2018-12-10/5c0e0559266df.jpg"},{"lib_id":"13","name":"歇后语宝典9","pic":"http://cdn1.guoguoxueyuan.com/Uploads/chinese_xiehouyu/2018-12-10/5c0e05641a83d.jpg"}],"new":[{"question":"方法接电话2","answer":"3方法2"},{"question":"方法接电话1","answer":"方法2"},{"question":"方法接电话","answer":"方法"},{"question":"方法接电话2","answer":"3方法2"},{"question":"方法接电话1","answer":"方法2"},{"question":"方法接电话","answer":"方法"},{"question":"阿三地方 ","answer":"撒旦f"},{"question":"大法师g","answer":"个 f"},{"question":"阿三地方a's'f'd","answer":"是的"},{"question":"阿三地方","answer":"士大夫"}]}
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
        private List<Banner> banner;
        private List<CatetoryBean> catetory;
        @SerializedName("new")
        private List<NewBean> newX;

        public List<Banner> getBanner() {
            return banner;
        }

        public void setBanner(List<Banner> banner) {
            this.banner = banner;
        }

        public List<CatetoryBean> getCatetory() {
            return catetory;
        }

        public void setCatetory(List<CatetoryBean> catetory) {
            this.catetory = catetory;
        }

        public List<NewBean> getNewX() {
            return newX;
        }

        public void setNewX(List<NewBean> newX) {
            this.newX = newX;
        }

        public static class CatetoryBean {
            /**
             * lib_id : 5
             * name : 歇后语宝典1
             * pic : http://cdn1.guoguoxueyuan.com/Uploads/chinese_xiehouyu/2018-12-08/5c0ba1bfe6702.jpg
             */

            private String lib_id;
            private String name;
            private String pic;

            public String getLib_id() {
                return lib_id;
            }

            public void setLib_id(String lib_id) {
                this.lib_id = lib_id;
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
        }

        public static class NewBean {
            /**
             * question : 方法接电话2
             * answer : 3方法2
             */

            private String question;
            private String answer;

            public String getQuestion() {
                return question;
            }

            public void setQuestion(String question) {
                this.question = question;
            }

            public String getAnswer() {
                return answer;
            }

            public void setAnswer(String answer) {
                this.answer = answer;
            }
        }
    }
}
