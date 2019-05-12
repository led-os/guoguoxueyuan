package com.test720.grasshoppercollege.module.guoguoDou.bean;

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
 * 作者：水东流 编于 2018/10/18
 */
public class DouDouJiLVData {

    /**
     * code : 1
     * msg : 成功
     * data : [{"content":"【课文讲解】加5果果豆","time":"2018-10-16 11:57:58"},{"content":"【国际音标】加5果果豆","time":"2018-10-10 09:57:47"},{"content":"【英美文化】加3果果豆","time":"2018-10-09 20:12:45"},{"content":"【国际音标】加5果果豆","time":"2018-10-09 19:43:19"},{"content":"签到得1果果豆","time":"2018-10-04 14:25:19"}]
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
         * content : 【课文讲解】加5果果豆
         * time : 2018-10-16 11:57:58
         */

        private String content;
        private String time;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
