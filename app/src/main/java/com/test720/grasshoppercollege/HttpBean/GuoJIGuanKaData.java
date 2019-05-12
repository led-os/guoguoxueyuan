package com.test720.grasshoppercollege.HttpBean;

import java.util.List;

/**
 * Created by 水东流 on 2018/4/23.
 */

public class GuoJIGuanKaData {

    /**
     * code : 1
     * msg : 成功
     * data : [[{"ipa_id":"15","name":"1111","pic":"","permissions":"1","is_permissions":"0","points":"5","state":"1","open_up":1,"pay":false,"title":"奥数动画","content":"和女女好滴好"},{"ipa_id":"14","name":"1111","pic":"","permissions":"1","is_permissions":"0","points":"5","state":"1","open_up":1,"pay":false,"title":"奥数动画","content":"和女女好滴好"},{"ipa_id":"13","name":"1111","pic":"","permissions":"1","is_permissions":"0","points":"5","state":"1","open_up":1,"pay":false,"title":"奥数动画","content":"和女女好滴好"},{"ipa_id":"12","name":"1111","pic":"","permissions":"1","is_permissions":"0","points":"5","state":"1","open_up":1,"pay":false,"title":"奥数动画","content":"和女女好滴好"},{"ipa_id":"11","name":"1111","pic":"","permissions":"1","is_permissions":"0","points":"5","state":"1","open_up":1,"pay":false,"title":"奥数动画","content":"和女女好滴好"},{"ipa_id":"10","name":"1111","pic":"","permissions":"1","is_permissions":"0","points":"5","state":"1","open_up":1,"pay":false,"title":"奥数动画","content":"和女女好滴好"},{"ipa_id":"9","name":"1111","pic":"","permissions":"1","is_permissions":"0","points":"5","state":"1","open_up":1,"pay":false,"title":"奥数动画","content":"和女女好滴好"}],[{"ipa_id":"8","name":"1111","pic":"","permissions":"1","is_permissions":"0","points":"5","state":"1","open_up":1,"pay":false,"title":"奥数动画","content":"和女女好滴好"},{"ipa_id":"7","name":"1111","pic":"","permissions":"1","is_permissions":"0","points":"5","state":"1","open_up":1,"pay":false,"title":"奥数动画","content":"和女女好滴好"},{"ipa_id":"6","name":"1111","pic":"","permissions":"1","is_permissions":"0","points":"5","state":"1","open_up":1,"pay":false,"title":"奥数动画","content":"和女女好滴好"},{"ipa_id":"5","name":"1111","pic":"","permissions":"1","is_permissions":"0","points":"5","state":"1","open_up":1,"pay":false,"title":"奥数动画","content":"和女女好滴好"},{"ipa_id":"4","name":"1111","pic":"","permissions":"1","is_permissions":"0","points":"5","state":"1","open_up":1,"pay":false,"title":"奥数动画","content":"和女女好滴好"},{"ipa_id":"3","name":"1111","pic":"","permissions":"1","is_permissions":"0","points":"5","state":"1","open_up":1,"pay":false,"title":"奥数动画","content":"和女女好滴好"},{"ipa_id":"2","name":"1111","pic":"","permissions":"1","is_permissions":"0","points":"5","state":"1","open_up":1,"pay":false,"title":"奥数动画","content":"和女女好滴好"}],[{"ipa_id":"1","name":"1111","pic":"","permissions":"1","is_permissions":"0","points":"5","state":"1","open_up":1,"pay":false,"title":"奥数动画","content":"和女女好滴好"}]]
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
         * ipa_id : 15
         * name : 1111
         * pic :
         * permissions : 1
         * is_permissions : 0
         * points : 5
         * state : 1
         * open_up : 1
         * pay : false
         * title : 奥数动画
         * content : 和女女好滴好
         */

        private String ipa_id;
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

        public String getipa_id() {
            return ipa_id;
        }

        public void setipa_id(String ipa_id) {
            this.ipa_id = ipa_id;
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
