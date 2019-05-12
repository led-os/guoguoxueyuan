package com.test720.grasshoppercollege.module.gongLue.peiXun.bean;

/**
 * 佛祖保佑        永无BUG
 * 佛曰:
 * 程序园里程序天，程序天里程序员；
 * 程序猿人写程序，又拿程序换肉钱。
 * 肉饱继续桌前坐，饱暖还是桌前眠；
 * 半迷半醒日复日，码上码下年复年。
 * 但愿叱咤互联世，不愿搬砖码当前；
 * 诸葛周瑜算世事，我算需求得加钱。
 * 别人笑我忒直男，我说自己是程猿；
 * 但见成都府国内，处处地地程序员。
 * 作者：水东流 编于 2018/9/1
 */
public class KeChengData {

    /**
     * code : 1
     * msg : 成功
     * data : {"curr_id":"20","school_id":"52","name":"asda","pic":"http://www.guoguoxueyuan.com/ggxy/Uploads/mire_img/2018-11-02/5bdbfa7f2e77a.jpg","pic_icon":"http://www.guoguoxueyuan.com/ggxy/Uploads/mire_img/2018-11-02/5bdbfa7f056f8.jpg","latitude":"30.2741702308","longitude":"120.1551656314","address":"浙江省杭州市拱墅区人民北路3号","introduce":"http://www.guoguoxueyuan.com/ggxy/small.php/StrategyTrain/introduceWeb/curr_id/20","arrange":"http://www.guoguoxueyuan.com/ggxy/small.php/StrategyTrain/arrangeWeb/curr_id/20","consulting_telephone":"18111111112","consulting_wx":"18111111113","wx_qrcode":"http://www.guoguoxueyuan.com/ggxy/Uploads/strategy_train/school/2018-10-30/5bd7e6621e60d.jpg"}
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
         * curr_id : 20
         * school_id : 52
         * name : asda
         * pic : http://www.guoguoxueyuan.com/ggxy/Uploads/mire_img/2018-11-02/5bdbfa7f2e77a.jpg
         * pic_icon : http://www.guoguoxueyuan.com/ggxy/Uploads/mire_img/2018-11-02/5bdbfa7f056f8.jpg
         * latitude : 30.2741702308
         * longitude : 120.1551656314
         * address : 浙江省杭州市拱墅区人民北路3号
         * introduce : http://www.guoguoxueyuan.com/ggxy/small.php/StrategyTrain/introduceWeb/curr_id/20
         * arrange : http://www.guoguoxueyuan.com/ggxy/small.php/StrategyTrain/arrangeWeb/curr_id/20
         * consulting_telephone : 18111111112
         * consulting_wx : 18111111113
         * wx_qrcode : http://www.guoguoxueyuan.com/ggxy/Uploads/strategy_train/school/2018-10-30/5bd7e6621e60d.jpg
         */

        private String curr_id;
        private String school_id;
        private String name;
        private String pic;
        private String pic_icon;
        private String latitude;
        private String longitude;
        private String address;
        private String introduce;
        private String arrange;
        private String consulting_telephone;
        private String consulting_wx;
        private String wx_qrcode;

        public String getCurr_id() {
            return curr_id;
        }

        public void setCurr_id(String curr_id) {
            this.curr_id = curr_id;
        }

        public String getSchool_id() {
            return school_id;
        }

        public void setSchool_id(String school_id) {
            this.school_id = school_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getPic_icon() {
            return pic_icon;
        }

        public void setPic_icon(String pic_icon) {
            this.pic_icon = pic_icon;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public String getArrange() {
            return arrange;
        }

        public void setArrange(String arrange) {
            this.arrange = arrange;
        }

        public String getConsulting_telephone() {
            return consulting_telephone;
        }

        public void setConsulting_telephone(String consulting_telephone) {
            this.consulting_telephone = consulting_telephone;
        }

        public String getConsulting_wx() {
            return consulting_wx;
        }

        public void setConsulting_wx(String consulting_wx) {
            this.consulting_wx = consulting_wx;
        }

        public String getWx_qrcode() {
            return wx_qrcode;
        }

        public void setWx_qrcode(String wx_qrcode) {
            this.wx_qrcode = wx_qrcode;
        }
    }
}
