package com.test720.grasshoppercollege.HttpBean;

import java.util.List;

/**
 * Created by 水东流 on 2018/7/3.
 */

public class YuFaTestShouTwoData {

    /**
     * code : 1
     * msg : 成功
     * data : [{"book_id":"6","name":"名词5","pic":"","permissions":"1","is_permissions":"1","points":"0","open_up":1,"pay":false,"title":null,"content":null},{"book_id":"5","name":"名词4","pic":"","permissions":"1","is_permissions":"1","points":"0","open_up":1,"pay":false,"title":null,"content":null},{"book_id":"4","name":"名词3","pic":"","permissions":"1","is_permissions":"1","points":"0","open_up":1,"pay":false,"title":null,"content":null},{"book_id":"3","name":"名词2","pic":"","permissions":"1","is_permissions":"1","points":"0","open_up":1,"pay":false,"title":null,"content":null}]
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
         * book_id : 6
         * name : 名词5
         * pic :
         * permissions : 1
         * is_permissions : 1
         * points : 0
         * open_up : 1
         * pay : false
         * title : null
         * content : null
         */

        private String book_id;
        private String name;
        private String pic;
        private String permissions;
        private String is_permissions;
        private String points;
        private int open_up;
        private boolean pay;
        private String title;
        private String content;

        public String getBook_id() {
            return book_id;
        }

        public void setBook_id(String book_id) {
            this.book_id = book_id;
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
