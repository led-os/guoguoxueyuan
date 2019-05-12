package com.test720.grasshoppercollege.module.userData.myTeam.bean;

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
 * 作者：水东流 编于 2018/10/8
 */
public class MyTeamBean {

    /**
     * code : 1
     * msg : 成功
     * data : {"commis_money":"500.00","nickname":"158****7306","header":"https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg","daili_status":"1","agent_id":"4"}
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
         * commis_money : 500.00
         * nickname : 158****7306
         * header : https://www.hzggedu.com/ggxy/Uploads/popup_ads/2018-02-23/5a8fde9fee502.jpg
         * daili_status : 1
         * agent_id : 4
         */

        private String commis_money;
        private String commission;
        private String nickname;
        private String header;
        private String daili_status;
        private String agent_id;
        private String team_instructions;

        public String getCommission() {
            return commission;
        }

        public void setCommission(String commission) {
            this.commission = commission;
        }

        public String getTeam_instructions() {
            return team_instructions;
        }

        public void setTeam_instructions(String team_instructions) {
            this.team_instructions = team_instructions;
        }

        public String getCommis_money() {
            return commis_money;
        }

        public void setCommis_money(String commis_money) {
            this.commis_money = commis_money;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getHeader() {
            return header;
        }

        public void setHeader(String header) {
            this.header = header;
        }

        public String getDaili_status() {
            return daili_status;
        }

        public void setDaili_status(String daili_status) {
            this.daili_status = daili_status;
        }

        public String getAgent_id() {
            return agent_id;
        }

        public void setAgent_id(String agent_id) {
            this.agent_id = agent_id;
        }
    }
}
