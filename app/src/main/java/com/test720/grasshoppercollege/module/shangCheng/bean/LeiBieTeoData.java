package com.test720.grasshoppercollege.module.shangCheng.bean;

import java.util.List;

/**
 * Created by 水东流 on 2018/7/10.
 */

public class LeiBieTeoData {

    /**
     * code : 1
     * msg : 成功
     * data : [{"cate_id":"5","name":"英语","pic":""},{"cate_id":"6","name":"语文","pic":""},{"cate_id":"7","name":"数学","pic":""}]
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
         * cate_id : 5
         * name : 英语
         * pic :
         */

        private String cate_id;
        private String name;
        private String pic;

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
    }
}
