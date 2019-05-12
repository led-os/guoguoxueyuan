package com.test720.grasshoppercollege.HttpBean;

import java.util.List;

/**
 * Created by 水东流 on 2018/5/18.
 */

public class FanYiGuanKaData {


    /**
     * code : 1
     * msg : 成功
     * data : [[{"unit_id":"1","unit":"Unit 1","permissions":"1","is_permissions":"1","points":"0","open_up":1,"pay":false,"title":"奥数动画","content":"和女女好滴好"},{"unit_id":"2","unit":"Unit 2","permissions":"3","is_permissions":"1","points":"0","open_up":0,"pay":false,"title":"奥数动画","content":"和女女好滴好"},{"unit_id":"3","unit":"Unit 3","permissions":"4","is_permissions":"1","points":"0","open_up":0,"pay":false,"title":"奥数动画","content":"和女女好滴好"},{"unit_id":"4","unit":"Unit 4","permissions":"5","is_permissions":"1","points":"5","open_up":0,"pay":false,"title":"奥数动画","content":"和女女好滴好"}]]
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
         * unit_id : 1
         * unit : Unit 1
         * permissions : 1
         * is_permissions : 1
         * points : 0
         * open_up : 1
         * pay : false
         * title : 奥数动画
         * content : 和女女好滴好
         */

        private String unit_id;
        private String unit;
        private String permissions;
        private String is_permissions;
        private String points;
        private int open_up;
        private boolean pay;
        private String title;
        private String content;

        public String getUnit_id() {
            return unit_id;
        }

        public void setUnit_id(String unit_id) {
            this.unit_id = unit_id;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
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
