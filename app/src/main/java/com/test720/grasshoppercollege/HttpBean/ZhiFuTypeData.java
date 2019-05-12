package com.test720.grasshoppercollege.HttpBean;

/**
 * Created by 水东流 on 2018/4/12.
 */

public class ZhiFuTypeData {

    /**
     * code : 1
     * msg : 成功
     * data : {"bank":{"name":"哈哈","bank":"还一个","number":"124325234","id_card":"34523423","phone":"12314"},"zfb":{"name":"方法","zfb":"767192287"}}
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
         * bank : {"name":"哈哈","bank":"还一个","number":"124325234","id_card":"34523423","phone":"12314"}
         * zfb : {"name":"方法","zfb":"767192287"}
         */

        private BankBean bank;
        private ZfbBean zfb;

        public BankBean getBank() {
            return bank;
        }

        public void setBank(BankBean bank) {
            this.bank = bank;
        }

        public ZfbBean getZfb() {
            return zfb;
        }

        public void setZfb(ZfbBean zfb) {
            this.zfb = zfb;
        }

        public static class BankBean {
            /**
             * name : 哈哈
             * bank : 还一个
             * number : 124325234
             * id_card : 34523423
             * phone : 12314
             */

            private String name;
            private String bank;
            private String number;
            private String id_card;
            private String phone;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getBank() {
                return bank;
            }

            public void setBank(String bank) {
                this.bank = bank;
            }

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }

            public String getId_card() {
                return id_card;
            }

            public void setId_card(String id_card) {
                this.id_card = id_card;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }
        }

        public static class ZfbBean {
            /**
             * name : 方法
             * zfb : 767192287
             */

            private String name;
            private String zfb;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getZfb() {
                return zfb;
            }

            public void setZfb(String zfb) {
                this.zfb = zfb;
            }
        }
    }
}
