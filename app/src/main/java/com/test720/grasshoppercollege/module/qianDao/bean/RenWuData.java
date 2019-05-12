package com.test720.grasshoppercollege.module.qianDao.bean;

import java.util.List;

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
 * 作者：水东流 编于 2018/11/1
 */
public class RenWuData {

    /**
     * code : 1
     * msg : 成功
     * data : [{"number":"22G01","name":"口算训练","points":"5","complete":"0"},{"number":"17G01","name":"应用练习","points":"4","complete":"0"},{"number":"19G01","name":"基础练习","points":"3","complete":"0"},{"number":"02G01","name":"数学动画","points":"2","complete":"0"},{"number":"01G01","name":"奥数动画","points":"1","complete":"0"},{"number":"18G01","name":"奥数练习","points":"7","complete":"0"}]
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
         * number : 22G01
         * name : 口算训练
         * points : 5
         * complete : 0
         */

        private String number;
        private String name;
        private String points;
        private String complete;

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPoints() {
            return points;
        }

        public void setPoints(String points) {
            this.points = points;
        }

        public String getComplete() {
            return complete;
        }

        public void setComplete(String complete) {
            this.complete = complete;
        }
    }
}
