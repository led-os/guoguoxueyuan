package com.test720.grasshoppercollege.HttpBean;

import java.util.List;

/**
 * Created by 水东流 on 2018/7/3.
 */

public class TingLiBianYinShouData {


    /**
     * code : 1
     * msg : 成功
     * data : [[{"bian_id":"1","name":"1","pic":"","permissions":"1","is_permissions":"1","points":"0","open_up":1,"pay":false,"title":"单词辨音","content":"你说的"},{"bian_id":"2","name":"2","pic":"","permissions":"2","is_permissions":"1","points":"0","open_up":1,"pay":false,"title":"单词辨音","content":"你说的"},{"bian_id":"3","name":"3","pic":"","permissions":"3","is_permissions":"1","points":"0","open_up":0,"pay":false,"title":"单词辨音","content":"你说的"},{"bian_id":"4","name":"4","pic":"","permissions":"4","is_permissions":"1","points":"0","open_up":0,"pay":false,"title":"单词辨音","content":"你说的"},{"bian_id":"5","name":"5","pic":"","permissions":"5","is_permissions":"1","points":"1","open_up":0,"pay":false,"title":"单词辨音","content":"你说的"},{"bian_id":"6","name":"6","pic":"","permissions":"1","is_permissions":"1","points":"0","open_up":1,"pay":false,"title":"单词辨音","content":"你说的"},{"bian_id":"7","name":"7","pic":"","permissions":"1","is_permissions":"1","points":"0","open_up":1,"pay":false,"title":"单词辨音","content":"你说的"}],[{"bian_id":"8","name":"8","pic":"","permissions":"1","is_permissions":"1","points":"0","open_up":1,"pay":false,"title":"单词辨音","content":"你说的"},{"bian_id":"9","name":"9","pic":"","permissions":"1","is_permissions":"1","points":"0","open_up":1,"pay":false,"title":"单词辨音","content":"你说的"},{"bian_id":"10","name":"10","pic":"","permissions":"1","is_permissions":"1","points":"0","open_up":1,"pay":false,"title":"单词辨音","content":"你说的"},{"bian_id":"11","name":"11","pic":"","permissions":"1","is_permissions":"1","points":"0","open_up":1,"pay":false,"title":"单词辨音","content":"你说的"}]]
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
         * bian_id : 1
         * name : 1
         * pic :
         * permissions : 1
         * is_permissions : 1
         * points : 0
         * open_up : 1
         * pay : false
         * title : 单词辨音
         * content : 你说的
         */

        private String bian_id;
        private String name;
        private String pic;
        private String permissions;
        private String is_permissions;
        private String points;
        private int open_up;
        private boolean pay;
        private String title;
        private String content;

        public String getBian_id() {
            return bian_id;
        }

        public void setBian_id(String bian_id) {
            this.bian_id = bian_id;
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

        public int getOpen_up() {
            return open_up;
        }

        public void setOpen_up(int open_up) {
            this.open_up = open_up;
        }

        public boolean isPay() {
            return pay;
        }

        public void setPay(boolean pay) {
            this.pay = pay;
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
    }
}
