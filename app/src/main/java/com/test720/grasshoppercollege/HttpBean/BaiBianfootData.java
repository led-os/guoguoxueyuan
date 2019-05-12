package com.test720.grasshoppercollege.HttpBean;

/**
 * Created by 水东流 on 2018/7/3.
 */

public class BaiBianfootData {

    /**
     * code : 1
     * msg : 成功
     * data : {"foot1":"1","foot2":"1","foot3":"1","foot4":"1","foot5":"1"}
     */

    private int code;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * foot1 : 1
         * foot2 : 1
         * foot3 : 1
         * foot4 : 1
         * foot5 : 1
         */

        private String foot1;
        private String foot2;
        private String foot3;
        private String foot4;
        private String foot5;

        public String getFoot1() {
            return foot1;
        }

        public void setFoot1(String foot1) {
            this.foot1 = foot1;
        }

        public String getFoot2() {
            return foot2;
        }

        public void setFoot2(String foot2) {
            this.foot2 = foot2;
        }

        public String getFoot3() {
            return foot3;
        }

        public void setFoot3(String foot3) {
            this.foot3 = foot3;
        }

        public String getFoot4() {
            return foot4;
        }

        public void setFoot4(String foot4) {
            this.foot4 = foot4;
        }

        public String getFoot5() {
            return foot5;
        }

        public void setFoot5(String foot5) {
            this.foot5 = foot5;
        }
    }
}
