package com.test720.grasshoppercollege.HttpBean;

import java.util.List;

/**
 * Created by 水东流 on 2018/6/20.
 */

public class ChongZhiShuJuData {

    /**
     * code : 1
     * msg : 成功
     * data : {"min":"100","list":[{"money":"0.01","points":"50"},{"money":"2000","points":"100"},{"money":"3000","points":"150"},{"money":"4000","points":"200"}]}
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
         * min : 100
         * list : [{"money":"0.01","points":"50"},{"money":"2000","points":"100"},{"money":"3000","points":"150"},{"money":"4000","points":"200"}]
         */

        private String min;
        private List<ListBean> list;

        public String getMin() {
            return min;
        }

        public void setMin(String min) {
            this.min = min;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * money : 0.01
             * points : 50
             */

            private String money;
            private String points;

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getPoints() {
                return points;
            }

            public void setPoints(String points) {
                this.points = points;
            }
        }
    }
}
