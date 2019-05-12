package com.test720.grasshoppercollege.HttpBean;

import java.util.List;

/**
 * Created by 水东流 on 2018/4/16.
 */

public class KeBenUnitData {


    /**
     * code : 1
     * msg : 成功
     * data : [{"unit":"Unit 1","page":[{"page":"Page 1","state":"1","permissions":"1","points":"5","is_permissions":"1","open_up":0,"pay":false,"title":"课文讲解","content":"等级登记等级登记等级登记的女女"},{"page":"Page 2","state":"1","permissions":"4","points":"0","is_permissions":"1","open_up":0,"pay":false,"title":"课文讲解","content":"等级登记等级登记等级登记的女女"}]},{"unit":"Unit 2","page":[{"page":"Page 1","state":"1","permissions":"5","points":"2","is_permissions":"1","open_up":0,"pay":false,"title":"课文讲解","content":"等级登记等级登记等级登记的女女"},{"page":"Page 2","state":"1","permissions":"3","points":"0","is_permissions":"1","open_up":0,"pay":false,"title":"课文讲解","content":"等级登记等级登记等级登记的女女"}]}]
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
         * unit : Unit 1
         * page : [{"page":"Page 1","state":"1","permissions":"1","points":"5","is_permissions":"1","open_up":0,"pay":false,"title":"课文讲解","content":"等级登记等级登记等级登记的女女"},{"page":"Page 2","state":"1","permissions":"4","points":"0","is_permissions":"1","open_up":0,"pay":false,"title":"课文讲解","content":"等级登记等级登记等级登记的女女"}]
         */

        private String unit;
        private List<PageBean> page;

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public List<PageBean> getPage() {
            return page;
        }

        public void setPage(List<PageBean> page) {
            this.page = page;
        }

        public static class PageBean {
            /**
             * page : Page 1
             * state : 1
             * permissions : 1
             * points : 5
             * is_permissions : 1
             * open_up : 0
             * pay : false
             * title : 课文讲解
             * content : 等级登记等级登记等级登记的女女
             */

            private String page;
            private String web_url;
            private String state;
            private String permissions;
            private String points;
            private String is_permissions;
            private int open_up;
            private boolean pay;
            private String title;
            private String content;

            public String getWeb_url() {
                return web_url;
            }

            public void setWeb_url(String web_url) {
                this.web_url = web_url;
            }

            public String getPage() {
                return page;
            }

            public void setPage(String page) {
                this.page = page;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getPermissions() {
                return permissions;
            }

            public void setPermissions(String permissions) {
                this.permissions = permissions;
            }

            public String getPoints() {
                return points;
            }

            public void setPoints(String points) {
                this.points = points;
            }

            public String getIs_permissions() {
                return is_permissions;
            }

            public void setIs_permissions(String is_permissions) {
                this.is_permissions = is_permissions;
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
}
