package com.test720.grasshoppercollege.HttpBean;

import java.util.List;

/**
 * Created by 水东流 on 2018/7/5.
 */

public class ChengYuVideoShouData {

    /**
     * code : 1
     * msg : 成功
     * data : [{"look_id":"1","name":"黑道皇帝","pic":"https://www.hzggedu.com/ggxy/Uploads/math_animation/pic/2018-06-14/5b22568b174a3.jpg","permissions":"1","is_permissions":"1","state":"1","points":"0","open_up":1,"pay":false,"title":"ff","content":"dfs"},{"look_id":"2","name":"黑道皇帝2","pic":"https://www.hzggedu.com/ggxy/Uploads/3.png","permissions":"2","is_permissions":"1","state":"1","points":"0","open_up":1,"pay":false,"title":"ff","content":"dfs"},{"look_id":"3","name":"黑道皇帝3","pic":"https://www.hzggedu.com/ggxy/Uploads/2.png","permissions":"3","is_permissions":"1","state":"1","points":"0","open_up":0,"pay":false,"title":"ff","content":"dfs"},{"look_id":"4","name":"黑道皇帝4","pic":"https://www.hzggedu.com/ggxydemo/Uploads/math_animation/pic/2018-06-25/5b30e317e2380.jpg","permissions":"4","is_permissions":"1","state":"1","points":"0","open_up":0,"pay":false,"title":"ff","content":"dfs"},{"look_id":"5","name":"黑道皇帝5","pic":"https://www.hzggedu.com/ggxy/Uploads/math_animation/pic/2018-06-14/5b22582a2f185.jpg","permissions":"5","is_permissions":"1","state":"1","points":"0","open_up":1,"pay":false,"title":"ff","content":"dfs"}]
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
         * look_id : 1
         * name : 黑道皇帝
         * pic : https://www.hzggedu.com/ggxy/Uploads/math_animation/pic/2018-06-14/5b22568b174a3.jpg
         * permissions : 1
         * is_permissions : 1
         * state : 1
         * points : 0
         * open_up : 1
         * pay : false
         * title : ff
         * content : dfs
         */

        private String look_id;
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

        public String getLook_id() {
            return look_id;
        }

        public void setLook_id(String look_id) {
            this.look_id = look_id;
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
