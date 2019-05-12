package com.test720.grasshoppercollege.HttpBean;

/**
 * Created by 水东流 on 2018/6/21.
 */

public class JinSaiFenShuData {

    /**
     * code : 1
     * msg : 成功
     * data : {"value_1":"91","value_2":91}
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
         * value_1 : 91
         * value_2 : 91
         */

        private String value_1;
        private String end;
        private int value_2;

        public String getEnd() {
            return end;
        }

        public void setEnd(String end) {
            this.end = end;
        }

        public String getValue_1() {
            return value_1;
        }

        public void setValue_1(String value_1) {
            this.value_1 = value_1;
        }

        public int getValue_2() {
            return value_2;
        }

        public void setValue_2(int value_2) {
            this.value_2 = value_2;
        }
    }
}
