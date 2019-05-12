package com.test720.grasshoppercollege.HttpBean;

import java.util.List;

/**
 * Created by 水东流 on 2018/6/25.
 */

public class EngLishReadShouData {


    /**
     * code : 1
     * msg : 成功
     * data : [[{"read_id":"1","name":"1","permissions":"1","is_permissions":"1","points":"0"},{"read_id":"2","name":"2","permissions":"2","is_permissions":"1","points":"0"},{"read_id":"3","name":"3","permissions":"3","is_permissions":"1","points":"0"},{"read_id":"4","name":"4","permissions":"4","is_permissions":"1","points":"5"},{"read_id":"5","name":"5","permissions":"5","is_permissions":"1","points":"5"},{"read_id":"6","name":"6","permissions":"5","is_permissions":"1","points":"5"},{"read_id":"7","name":"7","permissions":"5","is_permissions":"1","points":"5"},{"read_id":"8","name":"8","permissions":"5","is_permissions":"1","points":"5"},{"read_id":"9","name":"9","permissions":"5","is_permissions":"1","points":"5"},{"read_id":"10","name":"10","permissions":"5","is_permissions":"1","points":"5"},{"read_id":"11","name":"11","permissions":"5","is_permissions":"1","points":"5"},{"read_id":"12","name":"12","permissions":"5","is_permissions":"1","points":"5"},{"read_id":"13","name":"13","permissions":"5","is_permissions":"1","points":"5"},{"read_id":"14","name":"14","permissions":"5","is_permissions":"1","points":"5"},{"read_id":"15","name":"15","permissions":"5","is_permissions":"1","points":"5"},{"read_id":"16","name":"16","permissions":"5","is_permissions":"1","points":"5"}],[{"read_id":"17","name":"17","permissions":"5","is_permissions":"1","points":"5"},{"read_id":"18","name":"18","permissions":"5","is_permissions":"1","points":"5"}]]
     */

    private int code;
    private String msg;
    private List<List<DataBean>> data;

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

    public List<List<DataBean>> getData() {
        return data;
    }

    public void setData(List<List<DataBean>> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * read_id : 1
         * name : 1
         * permissions : 1
         * is_permissions : 1
         * points : 0
         */

        private String read_id;
        private String name;
        private String web_url;
        private String permissions;
        private String is_permissions;
        private String points;
        private String title;
        private String content;
        private int open_up;
        private boolean pay;

        public String getWeb_url() {
            return web_url;
        }

        public void setWeb_url(String web_url) {
            this.web_url = web_url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public boolean isPay() {
            return pay;
        }

        public void setPay(boolean pay) {
            this.pay = pay;
        }

        public int getOpen_up() {
            return open_up;
        }

        public void setOpen_up(int open_up) {
            this.open_up = open_up;
        }

        public String getRead_id() {
            return read_id;
        }

        public void setRead_id(String read_id) {
            this.read_id = read_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPermissions() {
            return permissions;
        }

        public void setPermissions(String permissions) {
            this.permissions = permissions;
        }

        public String getIs_permissions() {
            return is_permissions;
        }

        public void setIs_permissions(String is_permissions) {
            this.is_permissions = is_permissions;
        }

        public String getPoints() {
            return points;
        }

        public void setPoints(String points) {
            this.points = points;
        }
    }
}
