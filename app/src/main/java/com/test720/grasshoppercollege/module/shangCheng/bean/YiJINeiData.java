package com.test720.grasshoppercollege.module.shangCheng.bean;

import java.util.List;

/**
 * Created by 水东流 on 2018/7/10.
 */

public class YiJINeiData {

    /**
     * code : 1
     * msg : 成功
     * data : [{"parent_id":"1","name":"文具"},{"parent_id":"2","name":"书籍"}]
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
         * parent_id : 1
         * name : 文具
         */

        private String parent_id;
        private String name;

        public String getParent_id() {
            return parent_id;
        }

        public void setParent_id(String parent_id) {
            this.parent_id = parent_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
