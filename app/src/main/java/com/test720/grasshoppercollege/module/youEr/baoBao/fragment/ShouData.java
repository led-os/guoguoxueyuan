package com.test720.grasshoppercollege.module.youEr.baoBao.fragment;

import java.util.List;

/**
 * Created by 水东流 on 2018/8/1.
 */

public class ShouData {


    /**
     * code : 1
     * msg : 成功
     * data : [{"baby_id":"1","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/beishidaban+g3+x+en/Unit1/Lesson1/g3+beishidaban+x+u1+3.jpg"},{"baby_id":"2","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/beishidaban+g3+s+en/Unit6/Review/g3+beishidaban+s+u6+70.jpg"}]
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
         * baby_id : 1
         * pic : https://www.hzggedu.com/ggxydemo/Uploads/reading/beishidaban+g3+x+en/Unit1/Lesson1/g3+beishidaban+x+u1+3.jpg
         */

        private String baby_id;
        private String pic;
        private String permissions;
        private String open_up;
        private Boolean pay;
        private String title;
        private String points;
        private String content;

        public Boolean getPay() {
            return pay;
        }

        public void setPay(Boolean pay) {
            this.pay = pay;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPoints() {
            return points;
        }

        public void setPoints(String points) {
            this.points = points;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getPermissions() {
            return permissions;
        }

        public void setPermissions(String permissions) {
            this.permissions = permissions;
        }

        public String getOpen_up() {
            return open_up;
        }

        public void setOpen_up(String open_up) {
            this.open_up = open_up;
        }

        public String getBaby_id() {
            return baby_id;
        }

        public void setBaby_id(String baby_id) {
            this.baby_id = baby_id;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }
    }
}
