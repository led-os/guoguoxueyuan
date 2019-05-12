package com.test720.grasshoppercollege.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * _oo0oo_
 * o8888888o
 * 88" . "88
 * (| -_- |)
 * 0\  =  /0
 * ___/`---'\___
 * .' \\|     |// '.
 * / \\|||  :  |||// \
 * / _||||| -卍-|||||- \
 * |   | \\\  -  /// |   |
 * | \_|  ''\---/''  |_/ |
 * \  .-\__  '-'  ___/-. /
 * ___'. .'  /--.--\  `. .'___
 * ."" '<  `.___\_<|>_/___.' >' "".
 * | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 * \  \ `_.   \_ __\ /__ _/   .-` /  /
 * =====`-.____`.___ \_____/___.-`___.-'=====
 * `=---='
 * 佛祖保佑        永无BUG
 * 佛曰:
 * 程序园里程序天，程序天里程序员；
 * 程序猿人写程序，又拿程序换肉钱。
 * 肉饱继续桌前坐，饱暖还是桌前眠；
 * 半迷半醒日复日，码上码下年复年。
 * 但愿叱咤互联世，不愿搬砖码当前；
 * 诸葛周瑜算世事，我来算出得加钱。
 * 别人笑我忒直男，我笑自己太像猿；
 * 但见成都府国内，处处地地程序员。
 * Created by 水东流 on 2018/1/16 0016.
 */

public class BaseUntil {


    /**
     * code : 1
     * msg : 成功
     * data : {"info":{"class":"一年级","version":"人教版","status":"下册","pic":"Uploads/explain1/201801/5a65cc3e2c5b4.png"},"unit":[{"unit":"Unit 1","page":[{"page_id":"23","page_now":"page 1","experience":"0"}]}]}
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
         * info : {"class":"一年级","version":"人教版","status":"下册","pic":"Uploads/explain1/201801/5a65cc3e2c5b4.png"}
         * unit : [{"unit":"Unit 1","page":[{"page_id":"23","page_now":"page 1","experience":"0"}]}]
         */

        private InfoBean info;
        private List<UnitBean> unit;

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public List<UnitBean> getUnit() {
            return unit;
        }

        public void setUnit(List<UnitBean> unit) {
            this.unit = unit;
        }

        public static class InfoBean {
            /**
             * class : 一年级
             * version : 人教版
             * status : 下册
             * pic : Uploads/explain1/201801/5a65cc3e2c5b4.png
             */

            @SerializedName("class")
            private String classX;
            private String version;
            private String status;
            private String pic;

            public String getClassX() {
                return classX;
            }

            public void setClassX(String classX) {
                this.classX = classX;
            }

            public String getVersion() {
                return version;
            }

            public void setVersion(String version) {
                this.version = version;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }
        }

        public static class UnitBean {
            /**
             * unit : Unit 1
             * page : [{"page_id":"23","page_now":"page 1","experience":"0"}]
             */

            private String unit;
            private String experience;
            private List<PageBean> page;

            public String getExperience() {
                return experience;
            }

            public void setExperience(String experience) {
                this.experience = experience;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public List<PageBean> getPage() {
                return page;
            }

            public void setPage(List<PageBean> page) {
                this.page = page;
            }

            public static class PageBean {
                /**
                 * page_id : 23
                 * page_now : page 1
                 * experience : 0
                 */

                private String page_id;
                private String page_now;
                private String experience;

                public String getPage_id() {
                    return page_id;
                }

                public void setPage_id(String page_id) {
                    this.page_id = page_id;
                }

                public String getPage_now() {
                    return page_now;
                }

                public void setPage_now(String page_now) {
                    this.page_now = page_now;
                }

                public String getExperience() {
                    return experience;
                }

                public void setExperience(String experience) {
                    this.experience = experience;
                }
            }
        }
    }
}
