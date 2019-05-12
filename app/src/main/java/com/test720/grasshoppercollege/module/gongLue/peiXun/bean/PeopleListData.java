package com.test720.grasshoppercollege.module.gongLue.peiXun.bean;

import com.google.gson.annotations.SerializedName;

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
public class PeopleListData {


    /**
     * code : 1
     * msg : 成功
     * data : [{"ts_id":"1","class":"打扮","name":"刘老师","pic":"https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b3217b61d8e2.jpg"}]
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
         * ts_id : 1
         * class : 打扮
         * name : 刘老师
         * pic : https://www.hzggedu.com/ggxydemo/Uploads/reading/pic/2018-06-26/5b3217b61d8e2.jpg
         */

        private String ts_id;
        @SerializedName("class")
        private String classX;
        private String name;
        private String pic;

        public String getTs_id() {
            return ts_id;
        }

        public void setTs_id(String ts_id) {
            this.ts_id = ts_id;
        }

        public String getClassX() {
            return classX;
        }

        public void setClassX(String classX) {
            this.classX = classX;
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
    }
}
