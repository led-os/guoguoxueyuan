package com.test720.grasshoppercollege.HttpBean;

import java.util.List;

/**
 * Created by 水东流 on 2018/7/9.
 */

public class JinFanYiShouData {

    /**
     * code : 1
     * msg : 成功
     * data : [[{"jf_id":"1","name":"1","pic":"","permissions":"1","is_permissions":"1","points":"0","open_up":1,"pay":false,"title":"盛","content":"ff"},{"jf_id":"2","name":"2","pic":"","permissions":"2","is_permissions":"1","points":"0","open_up":1,"pay":false,"title":"盛","content":"ff"},{"jf_id":"3","name":"3","pic":"","permissions":"3","is_permissions":"1","points":"0","open_up":0,"pay":false,"title":"盛","content":"ff"},{"jf_id":"4","name":"4","pic":"","permissions":"4","is_permissions":"1","points":"0","open_up":0,"pay":false,"title":"盛","content":"ff"},{"jf_id":"5","name":"5","pic":"","permissions":"5","is_permissions":"1","points":"1","open_up":0,"pay":false,"title":"盛","content":"ff"},{"jf_id":"6","name":"6","pic":"","permissions":"1","is_permissions":"1","points":"0","open_up":1,"pay":false,"title":"盛","content":"ff"}],[{"jf_id":"7","name":"7","pic":"","permissions":"1","is_permissions":"1","points":"0","open_up":1,"pay":false,"title":"盛","content":"ff"},{"jf_id":"8","name":"8","pic":"","permissions":"1","is_permissions":"1","points":"0","open_up":1,"pay":false,"title":"盛","content":"ff"},{"jf_id":"9","name":"9","pic":"","permissions":"1","is_permissions":"1","points":"0","open_up":1,"pay":false,"title":"盛","content":"ff"},{"jf_id":"10","name":"10","pic":"","permissions":"1","is_permissions":"1","points":"0","open_up":1,"pay":false,"title":"盛","content":"ff"},{"jf_id":"11","name":"11","pic":"","permissions":"1","is_permissions":"1","points":"0","open_up":1,"pay":false,"title":"盛","content":"ff"},{"jf_id":"12","name":"12","pic":"","permissions":"1","is_permissions":"1","points":"0","open_up":1,"pay":false,"title":"盛","content":"ff"}],[{"jf_id":"13","name":"13","pic":"","permissions":"1","is_permissions":"1","points":"0","open_up":1,"pay":false,"title":"盛","content":"ff"},{"jf_id":"14","name":"14","pic":"","permissions":"1","is_permissions":"1","points":"0","open_up":1,"pay":false,"title":"盛","content":"ff"},{"jf_id":"15","name":"15","pic":"","permissions":"1","is_permissions":"1","points":"0","open_up":1,"pay":false,"title":"盛","content":"ff"},{"jf_id":"16","name":"16","pic":"","permissions":"1","is_permissions":"1","points":"0","open_up":1,"pay":false,"title":"盛","content":"ff"},{"jf_id":"17","name":"17","pic":"","permissions":"1","is_permissions":"1","points":"0","open_up":1,"pay":false,"title":"盛","content":"ff"}]]
     */

    private int code;
    private String msg;private List<List<DataBean>> data;


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
         * jf_id : 1
         * name : 1
         * pic :
         * permissions : 1
         * is_permissions : 1
         * points : 0
         * open_up : 1
         * pay : false
         * title : 盛
         * content : ff
         */

        private String jf_id;
        private String name;
        private String pic;
        private String permissions;
        private String is_permissions;
        private String points;
        private int open_up;
        private boolean pay;
        private String title;
        private String content;

        public String getJf_id() {
            return jf_id;
        }

        public void setJf_id(String jf_id) {
            this.jf_id = jf_id;
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
