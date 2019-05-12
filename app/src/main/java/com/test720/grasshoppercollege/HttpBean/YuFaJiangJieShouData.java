package com.test720.grasshoppercollege.HttpBean;

import java.util.List;

/**
 * Created by 水东流 on 2018/6/16.
 */

public class YuFaJiangJieShouData {
    /**
     * code : 1
     * msg : 成功
     * data : [{"book_id":"20","name":"b1","pic":"https://www.hzggedu.com/ggxy/Uploads/math_animation/pic/2018-06-14/5b22599cedae6.jpg","permissions":"4","is_permissions":"1","state":"1","points":"0","open_up":0,"pay":false,"title":"动画数学","content":"好滴好滴好滴好"}]
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
         * book_id : 20
         * name : b1
         * pic : https://www.hzggedu.com/ggxy/Uploads/math_animation/pic/2018-06-14/5b22599cedae6.jpg
         * permissions : 4
         * is_permissions : 1
         * state : 1
         * points : 0
         * open_up : 0
         * pay : false
         * title : 动画数学
         * content : 好滴好滴好滴好
         */

        private String book_id;
        private String name;
        private String pic;
        private String permissions;
        private String is_permissions;
        private String state;
        private String points;
        private int open_up;
        private boolean pay;
        private String title;
        private String content;

        public String getbook_id() {
            return book_id;
        }

        public void setbook_id(String book_id) {
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

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
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
