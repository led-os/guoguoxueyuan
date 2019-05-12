package com.test720.grasshoppercollege.HttpBean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Lenovo on 2018/4/8.
 */

public class UserData {

    /**
     * code : 1
     * msg : 登录成功
     * data : {"uid":"2770","token":"sQQzcDroYfcmFuZFN0ciI6InNRUXpjeyJ1aWQiOiIyNzcwIiwiRHJvWWYiLCJ0aW1lIjoxNTI4ODgwNDM2fQ==","area":1,"rong_token":"","rong_uid":"small_2770","team_status":"1","vip":"0","header":"","nickname":"183****8595","sex":"女","birthday":"0000-00-00","points":"5","level":"1","money":"0.00","lock":"0","student_no":"","shool":"","class":"","is_pay_password":0,"customer_phone":"6677881","income_money":0,"draw_money":0,"draw_charge":"0.05","bank":0,"zfb":0,"min":"100"}
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
         * uid : 2770
         * token : sQQzcDroYfcmFuZFN0ciI6InNRUXpjeyJ1aWQiOiIyNzcwIiwiRHJvWWYiLCJ0aW1lIjoxNTI4ODgwNDM2fQ==
         * area : 1
         * rong_token :
         * rong_uid : small_2770
         * team_status : 1
         * vip : 0
         * header :
         * nickname : 183****8595
         * sex : 女
         * birthday : 0000-00-00
         * points : 5
         * level : 1
         * money : 0.00
         * lock : 0
         * student_no :
         * shool :
         * class :
         * is_pay_password : 0
         * customer_phone : 6677881
         * income_money : 0
         * draw_money : 0
         * draw_charge : 0.05
         * bank : 0
         * zfb : 0
         * min : 100
         */

        private String uid;
        private String backpack;
        private String token;
        private String tokenStr;
        private String area_select;
        private String rong_token;
        private String area;
        private String city;
        private String province;
        private String points;
        private String rong_uid;
        private String team_status;
        private String vip;
        private String header;
        private String nickname;
        private String sex;
        private String birthday;
        private String level;
        private String money;
        private String lock;
        private String student_no;
        private String shool;
        @SerializedName("class")
        private String classX;
        private int is_pay_password;
        private String customer_phone;
        private String income_money;
        private String draw_money;
        private String draw_charge;
        private String school_introduce;
        private int bank;
        private int zfb;
        private String min;

        public String getBackpack() {
            return backpack;
        }

        public void setBackpack(String backpack) {
            this.backpack = backpack;
        }

        public String getSchool_introduce() {
            return school_introduce;
        }

        public void setSchool_introduce(String school_introduce) {
            this.school_introduce = school_introduce;
        }

        public String getTokenStr() {
            return tokenStr;
        }

        public void setTokenStr(String tokenStr) {
            this.tokenStr = tokenStr;
        }

        public String getArea_select() {
            return area_select;
        }

        public void setArea_select(String area_select) {
            this.area_select = area_select;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getRong_token() {
            return rong_token;
        }

        public void setRong_token(String rong_token) {
            this.rong_token = rong_token;
        }

        public String getRong_uid() {
            return rong_uid;
        }

        public void setRong_uid(String rong_uid) {
            this.rong_uid = rong_uid;
        }

        public String getTeam_status() {
            return team_status;
        }

        public void setTeam_status(String team_status) {
            this.team_status = team_status;
        }

        public String getVip() {
            return vip;
        }

        public void setVip(String vip) {
            this.vip = vip;
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

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getPoints() {
            return points;
        }

        public void setPoints(String points) {
            this.points = points;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getLock() {
            return lock;
        }

        public void setLock(String lock) {
            this.lock = lock;
        }

        public String getStudent_no() {
            return student_no;
        }

        public void setStudent_no(String student_no) {
            this.student_no = student_no;
        }

        public String getShool() {
            return shool;
        }

        public void setShool(String shool) {
            this.shool = shool;
        }

        public String getClassX() {
            return classX;
        }

        public void setClassX(String classX) {
            this.classX = classX;
        }

        public int getIs_pay_password() {
            return is_pay_password;
        }

        public void setIs_pay_password(int is_pay_password) {
            this.is_pay_password = is_pay_password;
        }

        public String getCustomer_phone() {
            return customer_phone;
        }

        public void setCustomer_phone(String customer_phone) {
            this.customer_phone = customer_phone;
        }

        public String getIncome_money() {
            return income_money;
        }

        public void setIncome_money(String income_money) {
            this.income_money = income_money;
        }

        public String getDraw_money() {
            return draw_money;
        }

        public void setDraw_money(String draw_money) {
            this.draw_money = draw_money;
        }

        public String getDraw_charge() {
            return draw_charge;
        }

        public void setDraw_charge(String draw_charge) {
            this.draw_charge = draw_charge;
        }

        public int getBank() {
            return bank;
        }

        public void setBank(int bank) {
            this.bank = bank;
        }

        public int getZfb() {
            return zfb;
        }

        public void setZfb(int zfb) {
            this.zfb = zfb;
        }

        public String getMin() {
            return min;
        }

        public void setMin(String min) {
            this.min = min;
        }
    }
}
