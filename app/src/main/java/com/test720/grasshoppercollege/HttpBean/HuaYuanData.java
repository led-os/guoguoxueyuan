package com.test720.grasshoppercollege.HttpBean;

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
 * 作者：水东流 编于 2018/12/1
 */
public class HuaYuanData {

    /**
     * code : 1
     * msg : 成功
     * data : {"login_url":"http://guoguo.hebqingxi.com/game/index.html?Code=d4o5216nTtvrXnsgCdE21DHoj8dm8uCtJreMpJLb2jzR1YMKKwCwLk6ehNU7f5dR","remaining_time":"100"}
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
         * login_url : http://guoguo.hebqingxi.com/game/index.html?Code=d4o5216nTtvrXnsgCdE21DHoj8dm8uCtJreMpJLb2jzR1YMKKwCwLk6ehNU7f5dR
         * remaining_time : 100
         */

        private String login_url;
        private String remaining_time;

        public String getLogin_url() {
            return login_url;
        }

        public void setLogin_url(String login_url) {
            this.login_url = login_url;
        }

        public String getRemaining_time() {
            return remaining_time;
        }

        public void setRemaining_time(String remaining_time) {
            this.remaining_time = remaining_time;
        }
    }
}
