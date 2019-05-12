package com.test720.grasshoppercollege.module.gongLue.peiXun.bean;

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
 * 作者：水东流 编于 2018/9/1
 */
public class XiaoXiData {

    /**
     * code : 1
     * msg : 成功
     * data : [{"msg_id":"1","location_type":"2","location_link":"https://www.hzggedu.com/ggxydemo/small.php/StrategyTrain/tuwenInfo/id/1","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32120fc5596.jpg","name":"123","time":"0"}]
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
         * msg_id : 1
         * location_type : 2
         * location_link : https://www.hzggedu.com/ggxydemo/small.php/StrategyTrain/tuwenInfo/id/1
         * pic : https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b32120fc5596.jpg
         * name : 123
         * time : 0
         */

        private String msg_id;
        private String location_type;
        private String location_link;
        private String pic;
        private String name;
        private String time;

        public String getMsg_id() {
            return msg_id;
        }

        public void setMsg_id(String msg_id) {
            this.msg_id = msg_id;
        }

        public String getLocation_type() {
            return location_type;
        }

        public void setLocation_type(String location_type) {
            this.location_type = location_type;
        }

        public String getLocation_link() {
            return location_link;
        }

        public void setLocation_link(String location_link) {
            this.location_link = location_link;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
