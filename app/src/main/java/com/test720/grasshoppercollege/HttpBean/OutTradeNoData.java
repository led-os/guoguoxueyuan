package com.test720.grasshoppercollege.HttpBean;

/**
 * Created by 水东流 on 2018/4/16.
 */

public class OutTradeNoData {

    /**
     * code : 1
     * msg : 成功
     * data : {"order_num":"C_oAsrQccsoe_1524721671_1211"}
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
         * order_num : C_oAsrQccsoe_1524721671_1211
         */

        private String order_num;

        public String getOrder_num() {
            return order_num;
        }

        public void setOrder_num(String order_num) {
            this.order_num = order_num;
        }
    }
}
