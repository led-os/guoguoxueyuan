package com.test720.grasshoppercollege.HttpBean;

/**
 * Created by 水东流 on 2018/4/12.
 */

public class TuijianData {

    /**
     * code : 1
     * msg : 成功
     * data : {"qrcode":"https://www.hzggedu.com/ggxy/Uploads/regqrcode/1207.png"}
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
         * qrcode : https://www.hzggedu.com/ggxy/Uploads/regqrcode/1207.png
         */

        private String qrcode;
        private String qrcode_background;
        private String qrcode_str;

        public String getQrcode_str() {
            return qrcode_str;
        }

        public void setQrcode_str(String qrcode_str) {
            this.qrcode_str = qrcode_str;
        }

        public String getQrcode_background() {
            return qrcode_background;
        }

        public void setQrcode_background(String qrcode_background) {
            this.qrcode_background = qrcode_background;
        }

        public String getQrcode() {
            return qrcode;
        }

        public void setQrcode(String qrcode) {
            this.qrcode = qrcode;
        }
    }
}
