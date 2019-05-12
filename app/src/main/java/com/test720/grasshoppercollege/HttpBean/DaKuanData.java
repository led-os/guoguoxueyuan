package com.test720.grasshoppercollege.HttpBean;

import java.util.List;

/**
 * Created by 水东流 on 2018/4/11.
 */

public class DaKuanData {

    /**
     * code : 1
     * msg : 成功
     * data : [{"money":"100","charge_money":"5.00","real_money":"95.00","create_time":"2018-04-08 11:54:19"}]
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
         * money : 100
         * charge_money : 5.00
         * real_money : 95.00
         * create_time : 2018-04-08 11:54:19
         */

        private String money;
        private String charge_money;
        private String real_money;
        private String create_time;

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getCharge_money() {
            return charge_money;
        }

        public void setCharge_money(String charge_money) {
            this.charge_money = charge_money;
        }

        public String getReal_money() {
            return real_money;
        }

        public void setReal_money(String real_money) {
            this.real_money = real_money;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }
}
