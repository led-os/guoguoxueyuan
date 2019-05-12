package com.test720.grasshoppercollege.HttpBean;

import java.util.List;

/**
 * Created by 水东流 on 2018/4/12.
 */

public class TeamData {


    /**
     * code : 1
     * msg : 成功
     * data : {"info":{"count":{"first_partner":0,"second_partner":0,"third_partner":0}},"list":{"list":[{"uid":"1210","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fdeae37e51.jpg","nickname":"176****5858","vip":0,"commission":0},{"uid":"1209","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fdeae37e51.jpg","nickname":"186****6283","vip":0,"commission":0},{"uid":"1208","header":"","nickname":"158****7306","vip":0,"commission":0},{"uid":"1207","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fdeae37e51.jpg","nickname":"帅哥大大好好干","vip":0,"commission":"80.00"},{"uid":"8","header":"","nickname":"","vip":0,"commission":0},{"uid":"7","header":"","nickname":"","vip":0,"commission":0},{"uid":"6","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fdeae37e51.jpg","nickname":"2343","vip":0,"commission":0},{"uid":"5","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fdeae37e51.jpg","nickname":"dfdf","vip":0,"commission":0},{"uid":"4","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fdeae37e51.jpg","nickname":"23123","vip":0,"commission":0},{"uid":"3","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fdeae37e51.jpg","nickname":"23","vip":0,"commission":0}]}}
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
         * info : {"count":{"first_partner":0,"second_partner":0,"third_partner":0}}
         * list : {"list":[{"uid":"1210","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fdeae37e51.jpg","nickname":"176****5858","vip":0,"commission":0},{"uid":"1209","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fdeae37e51.jpg","nickname":"186****6283","vip":0,"commission":0},{"uid":"1208","header":"","nickname":"158****7306","vip":0,"commission":0},{"uid":"1207","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fdeae37e51.jpg","nickname":"帅哥大大好好干","vip":0,"commission":"80.00"},{"uid":"8","header":"","nickname":"","vip":0,"commission":0},{"uid":"7","header":"","nickname":"","vip":0,"commission":0},{"uid":"6","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fdeae37e51.jpg","nickname":"2343","vip":0,"commission":0},{"uid":"5","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fdeae37e51.jpg","nickname":"dfdf","vip":0,"commission":0},{"uid":"4","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fdeae37e51.jpg","nickname":"23123","vip":0,"commission":0},{"uid":"3","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fdeae37e51.jpg","nickname":"23","vip":0,"commission":0}]}
         */

        private InfoBean info;
        private ListBeanX list;

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public ListBeanX getList() {
            return list;
        }

        public void setList(ListBeanX list) {
            this.list = list;
        }

        public static class InfoBean {
            /**
             * count : {"first_partner":0,"second_partner":0,"third_partner":0}
             */

            private CountBean count;

            public CountBean getCount() {
                return count;
            }

            public void setCount(CountBean count) {
                this.count = count;
            }

            public static class CountBean {
                /**
                 * first_partner : 0
                 * second_partner : 0
                 * third_partner : 0
                 */

                private String first_partner;
                private String second_partner;
                private String third_partner;
                private String order;
                private String commission;

                public String getOrder() {
                    return order;
                }

                public void setOrder(String order) {
                    this.order = order;
                }

                public String getCommission() {
                    return commission;
                }

                public void setCommission(String commission) {
                    this.commission = commission;
                }

                public String getFirst_partner() {
                    return first_partner;
                }

                public void setFirst_partner(String first_partner) {
                    this.first_partner = first_partner;
                }

                public String getSecond_partner() {
                    return second_partner;
                }

                public void setSecond_partner(String second_partner) {
                    this.second_partner = second_partner;
                }

                public String getThird_partner() {
                    return third_partner;
                }

                public void setThird_partner(String third_partner) {
                    this.third_partner = third_partner;
                }
            }
        }

        public static class ListBeanX {
            private List<ListBean> list;

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * uid : 1210
                 * header : https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fdeae37e51.jpg
                 * nickname : 176****5858
                 * vip : 0
                 * commission : 0
                 */

                private String uid;
                private String tid;
                private String header;
                private String phone;
                private String nickname;
                private int vip;
                private String commission;

                public String getPhone() {
                    return phone;
                }

                public void setPhone(String phone) {
                    this.phone = phone;
                }

                public String getTid() {
                    return tid;
                }

                public void setTid(String tid) {
                    this.tid = tid;
                }

                public String getUid() {
                    return uid;
                }

                public void setUid(String uid) {
                    this.uid = uid;
                }

                public String getHeader() {
                    return header;
                }

                public void setHeader(String header) {
                    this.header = header;
                }

                public String getNickname() {
                    return nickname;
                }

                public void setNickname(String nickname) {
                    this.nickname = nickname;
                }

                public int getVip() {
                    return vip;
                }

                public void setVip(int vip) {
                    this.vip = vip;
                }

                public String getCommission() {
                    return commission;
                }

                public void setCommission(String commission) {
                    this.commission = commission;
                }
            }
        }
    }
}
