package com.test720.grasshoppercollege.HttpBean;

import java.util.List;

/**
 * Created by Lenovo on 2018/4/4.
 */

public class CheckData {

    /**
     * code : 1
     * msg : 成功
     * data : {"is_forced":"1","version_no_tow":"222222222","state":"2","version_no":"23","url":"http://www.360doc.com/content/14/0308/13/10573328_358753102.shtml"}
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
         * is_forced : 1
         * version_no_tow : 222222222
         * state : 2
         * version_no : 23
         * url : http://www.360doc.com/content/14/0308/13/10573328_358753102.shtml
         */

        private String is_forced;
        private List<String> version_no_tow;
        private String state;
        private String download_type;
        private String version_no;
        private String url;

        public String getDownload_type() {
            return download_type;
        }

        public void setDownload_type(String download_type) {
            this.download_type = download_type;
        }

        public String getIs_forced() {
            return is_forced;
        }

        public void setIs_forced(String is_forced) {
            this.is_forced = is_forced;
        }

        public List<String> getVersion_no_tow() {
            return version_no_tow;
        }

        public void setVersion_no_tow(List<String> version_no_tow) {
            this.version_no_tow = version_no_tow;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getVersion_no() {
            return version_no;
        }

        public void setVersion_no(String version_no) {
            this.version_no = version_no;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
