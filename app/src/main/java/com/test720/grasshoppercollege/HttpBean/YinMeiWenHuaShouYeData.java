package com.test720.grasshoppercollege.HttpBean;

import java.util.List;

/**
 * Created by 水东流 on 2018/4/24.
 */

public class YinMeiWenHuaShouYeData {


    /**
     * code : 1
     * msg : 成功
     * data : [{"encc_id":"4","name":"111","pic":"https://www.hzggedu.com/ggxy/Uploads/culture/pic/2018-06-15/5b231da622257.jpg","permissions":"1","is_permissions":"0","points":"0","state":"1","open_up":1,"pay":false,"title":"英美文化","content":"和女女好滴好"},{"encc_id":"5","name":"22222","pic":"https://www.hzggedu.com/ggxy/Uploads/culture/pic/2018-06-15/5b231df4c02cd.jpg","permissions":"2","is_permissions":"0","points":"0","state":"2","open_up":1,"pay":false,"title":"英美文化","content":"和女女好滴好"},{"encc_id":"6","name":"333333","pic":"https://www.hzggedu.com/ggxy/Uploads/culture/pic/2018-06-15/5b231e1a54027.jpg","permissions":"3","is_permissions":"0","points":"0","state":"1","open_up":0,"pay":false,"title":"英美文化","content":"和女女好滴好"}]
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
         * encc_id : 4
         * name : 111
         * pic : https://www.hzggedu.com/ggxy/Uploads/culture/pic/2018-06-15/5b231da622257.jpg
         * permissions : 1
         * is_permissions : 0
         * points : 0
         * state : 1
         * open_up : 1
         * pay : false
         * title : 英美文化
         * content : 和女女好滴好
         */

        private String encc_id;
        private String name;
        private String pic;
        private String permissions;
        private String is_permissions;
        private String points;
        private String state;
        private int open_up;
        private boolean pay;
        private String title;
        private String content;

        public String getEncc_id() {
            return encc_id;
        }

        public void setEncc_id(String encc_id) {
            this.encc_id = encc_id;
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

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
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
